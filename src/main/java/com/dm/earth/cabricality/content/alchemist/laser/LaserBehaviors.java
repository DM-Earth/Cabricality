package com.dm.earth.cabricality.content.alchemist.laser;

import java.util.ArrayList;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.dm.earth.cabricality.content.alchemist.Alchemist;
import com.dm.earth.cabricality.content.entries.CabfItems;
import com.dm.earth.cabricality.lib.math.PositionUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.vehicle.HopperMinecartEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import static com.dm.earth.cabricality.ModEntry.*;

public class LaserBehaviors {
	public static void attackNearby(@NotNull ServerWorld world, BlockPos pos, float power) {
		float len = power * 2.5F;
		Box box = Box.of(PositionUtil.fromBlockPos(pos), len, len, len);
		world.getEntitiesByClass(LivingEntity.class, box, Entity::isLiving)
				.forEach(entity -> entity.damage(DamageSource.GENERIC,
						(float) (Math.max(Math.max(0, entity.getBlockPos().getSquaredDistance(pos) / len), 1)
								* power)));
	}

	// pos should be the lamp's blockPos
	public static ActionResult process(ServerWorld world, BlockPos pos, Direction direction,
			@NotNull LaserProperties properties) {
		ActionResult returnResult = ActionResult.FAIL;

		ArrayList<HopperMinecartEntity> minecarts = new ArrayList<>();
		for (int i = 0; i < properties.length(); i++)
			minecarts.addAll(world.getEntitiesByClass(HopperMinecartEntity.class,
					Box.of(PositionUtil.fromBlockPos(pos.offset(direction, i)), 1, 1, 1),
					minecart -> !minecart.isEmpty()));
		for (HopperMinecartEntity cart : minecarts) {
			double subPower = properties.power() / Math.sqrt(minecarts.size());
			// Generic Recipe Processing
			int capability = (int) Math.pow(subPower, 3);
			for (int i = 0; i < 5; i++) {
				ItemStack stack = cart.getStack(i);
				if (stack.isEmpty())
					continue;
				LaserRecipe laserRecipe = getGenericRecipe(stack.getItem());
				if (laserRecipe != null && stack.getCount() <= capability) {
					ItemStack result = new ItemStack(laserRecipe.output(), stack.getCount());
					cart.setStack(i, result);
					capability -= stack.getCount();
					returnResult = ActionResult.SUCCESS;
					laserRecipe.spawnParticles(world, cart.getPos());
				} else if (laserRecipe != null && capability > 0) {
					ItemStack result = new ItemStack(laserRecipe.output(), capability);
					for (int p = 0; p < 5; p++)
						if (cart.getStack(p).isEmpty()) {
							stack.decrement(capability);
							cart.setStack(i, stack);
							cart.setStack(p, result);
							returnResult = ActionResult.SUCCESS;
							laserRecipe.spawnParticles(world, cart.getPos());
							break;
						}
					break;
				} else if (capability <= 0)
					break;
			}
			Alchemist.processChaoticRecipe(cart, properties);
			attackNearby(world, cart.getBlockPos(), (float) subPower);
			cart.damage(DamageSource.MAGIC, (float) (subPower / 5.0F));
		}
		return returnResult;
	}

	@Contract(pure = true)
	@Nullable
	private static LaserRecipe getGenericRecipe(Item item) {
		if (item == Items.BASALT)
			return new LaserRecipe(CabfItems.BASALZ_SHARD, ParticleTypes.FLAME);
		if (item == Items.SNOWBALL)
			return new LaserRecipe(CabfItems.BLIZZ_CUBE, ParticleTypes.SNOWFLAKE);
		if (item == IR.asItem("nikolite_ingot"))
			return new LaserRecipe(IR.asItem("enriched_nikolite_ingot"), ParticleTypes.ELECTRIC_SPARK);
		if (item == IR.asItem("nikolite_dust"))
			return new LaserRecipe(IR.asItem("enriched_nikolite_dust"), ParticleTypes.ELECTRIC_SPARK);
		return null;
	}

	public record LaserRecipe(@NotNull Item output, @Nullable ParticleEffect effect) {
		public void spawnParticles(ServerWorld world, Vec3d pos) {
			if (effect != null)
				world.spawnParticles(effect, pos.getX(), pos.getY(), pos.getZ(), 10, 0, 0, 0, 1.D);
		}
	}
}
