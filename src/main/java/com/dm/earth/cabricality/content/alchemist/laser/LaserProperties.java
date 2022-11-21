package com.dm.earth.cabricality.content.alchemist.laser;

import com.dm.earth.cabricality.mixin.DiodeLampBlockAccessor;

import net.minecraft.block.BlockState;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.util.registry.Registry;

import net.darktree.led.block.DirectionalDiodeLampBlock;
import net.darktree.led.util.DiodeVariant;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record LaserProperties(int tint, int length, float power) {
	public static int DEFAULT_LENGTH = 4;
	public static int DEFAULT_POWER = 2;

	@Contract(" -> new")
	public @NotNull DustParticleEffect toDustParticleEffect() {
		return new DustParticleEffect(new Vec3f(Vec3d.unpackRgb(this.tint())), this.power() * 0.5F);
	}

	@Nullable
	public static LaserProperties generate(BlockState state, DirectionalDiodeLampBlock block, int count) {
		DiodeVariant variant = ((DiodeLampBlockAccessor) block).getVariant();
		Identifier id = Registry.BLOCK.getId(block);
		for (DyeColor color : DyeColor.values())
			if (id.getPath().contains(color.getName())) {
				boolean lit = state.get(DirectionalDiodeLampBlock.LIT);
				if ((!lit) || variant.isShaded()) return null;
				return new LaserProperties(color.getFireworkColor(), (variant.isReinforced() ? DEFAULT_LENGTH + 2 : DEFAULT_LENGTH), DEFAULT_POWER * (variant.getLightLevel() / (4F * count)));
			}
		return null;
	}
}
