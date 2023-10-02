package dm.earth.cabricality.content.alchemist.laser;

import net.minecraft.block.BlockState;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.registry.Registries;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public record LaserProperties(int tint, int length, float power) {

	public static int DEFAULT_LENGTH = 4;
	public static int DEFAULT_POWER = 2;

	@Contract(" -> new")
	public @NotNull DustParticleEffect toDustParticleEffect() {
		return new DustParticleEffect(
				Vec3d.unpackRgb(this.tint()).toVector3f(),
				(float) Math.pow(this.power(), 0.65F) * 0.45F
		);
	}

	/*
	@Nullable
	public static LaserProperties generate(BlockState state, DirectionalDiodeLampBlock block, int count) {
		DiodeVariant variant = ((DiodeLampBlockAccessor) block).getVariant();
		Identifier id = Registries.BLOCK.getId(block);

		if (!state.get(DirectionalDiodeLampBlock.LIT) || variant.isShaded()) return null;

		return Arrays.stream(DyeColor.values())
				.filter(color -> id.getPath().contains(color.getName()))
				.map(color -> new LaserProperties(
						color.getFireworkColor(),
						(variant.isReinforced() ? DEFAULT_LENGTH + 2 : DEFAULT_LENGTH),
						DEFAULT_POWER * (variant.getLightLevel() / (4F * count))))
				.findFirst().orElse(null);
	}

	 */
}
