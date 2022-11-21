package com.dm.earth.cabricality.content.alchemist.block;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.alchemist.Reagents;
import com.dm.earth.cabricality.content.alchemist.substrate.Reagent;
import com.dm.earth.cabricality.content.alchemist.substrate.Substrate;
import com.dm.earth.cabricality.content.entries.CabfBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ReagentJarBlock extends SubstrateJarBlock {
	public ReagentJarBlock(Settings settings) {
		super(settings);
	}

	@Override
	public Substrate getSubstrate() {
		return Reagents.getReagentFromBlock(this);
	}

	@Override
	public @NotNull Identifier getDefaultBlockId() {
		return Cabricality.id("reagent_jar");
	}

	@Override
	public Identifier getBlockModelId() {
		return Cabricality.id("block/jar/reagent");
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if ((!player.isSneaking()) || !player.getStackInHand(Hand.MAIN_HAND).isEmpty() || this.getSubstrate() == null)
			return ActionResult.PASS;
		world.setBlockState(pos, CabfBlocks.JAR.getDefaultState());
		player.giveItemStack(((Reagent) Objects.requireNonNull(this.getSubstrate())).getItem().getDefaultStack());
		return ActionResult.SUCCESS;
	}
}
