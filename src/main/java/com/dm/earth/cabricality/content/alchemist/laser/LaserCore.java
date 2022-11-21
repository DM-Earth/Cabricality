package com.dm.earth.cabricality.content.alchemist.laser;

import java.util.ArrayList;
import java.util.Objects;

import org.jetbrains.annotations.NotNull;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.util.CabfDebugger;

import net.darktree.led.block.DirectionalDiodeLampBlock;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.TagKey;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class LaserCore implements AttackBlockCallback, UseBlockCallback {
	@Override
	public ActionResult interact(PlayerEntity player, @NotNull World world, Hand hand, BlockPos pos,
			Direction direction) {
		// Check if the target block is a laser source
		BlockState state = world.getBlockState(pos);
		// TODO
		// player.getStackInHand(hand).isEmpty()
		if (!(state.isIn(TagKey.of(Registry.BLOCK_KEY, Cabricality.id("laser_source")))))
			return ActionResult.PASS;
		CabfDebugger.debug("Laser source detected");

		ArrayList<Direction> availableDirections = new ArrayList<>();
		for (Direction director : Direction.values()) {
			if (director == direction)
				continue;
			if (world.getBlockState(pos.offset(director)).getBlock() instanceof DirectionalDiodeLampBlock lamp
					&& LaserProperties.generate(world.getBlockState(pos.offset(director)), lamp, 1) != null)
				availableDirections.add(director);
		}

		if (availableDirections.isEmpty())
			return ActionResult.PASS;

		for (Direction director : availableDirections) {
			CabfDebugger.debug("Available direction: " + director.toString());
			BlockPos startPos = pos.offset(director);
			LaserProperties properties = Objects.requireNonNull(LaserProperties.generate(world.getBlockState(startPos),
					(DirectionalDiodeLampBlock) world.getBlockState(startPos).getBlock(), availableDirections.size()));
			for (float i = 0.0F; i < properties.length(); i += 0.1F) {
				double x = startPos.getX() + 0.5D + (director.getOffsetX() * i);
				double y = startPos.getY() + 0.5D + (director.getOffsetY() * i);
				double z = startPos.getZ() + 0.5D + (director.getOffsetZ() * i);
				world.addParticle(properties.toDustParticleEffect(), x, y, z, 0.0D, 0.0D, 0.0D);
			}
			LaserBehaviors.process(world, startPos, director, properties);
			world.playSound(startPos.getX() + 0.5D, startPos.getY() + 0.5D, startPos.getZ() + 0.5D,
					SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST, SoundCategory.BLOCKS, 0.55F, 0.5F, false);
		}

		return ActionResult.SUCCESS;
	}

	@Override
	public ActionResult interact(PlayerEntity player, World world, Hand hand, @NotNull BlockHitResult hitResult) {
		if (!player.getStackInHand(hand).isEmpty()  && player.getName().getString().equals("Deployer"))
			return ActionResult.PASS;
		return this.interact(player, world, hand, hitResult.getBlockPos(), hitResult.getSide());
	}

	public static void load() {
		LaserCore thisT = new LaserCore();
		AttackBlockCallback.EVENT.register(thisT);
		UseBlockCallback.EVENT.register(thisT);
	}
}
