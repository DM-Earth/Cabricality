package dm.earth.cabricality.content.alchemist.block;

import java.util.Random;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.lib.math.PositionUtil;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ChaoticCatalystJarBlock extends CatalystJarBlock {
    public ChaoticCatalystJarBlock(Settings settings) {
        super(settings);
    }

    @Override
    public Identifier getBlockModelId() {
        return Cabricality.id("block", "jar", "chaos_catalyst");
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        Random random = new Random();
        if (random.nextInt(2) == 0) {
            Vec3d vec3d = PositionUtil.fromBlockPos(pos);
            world.createExplosion(
					null,
					MinecraftClient.getInstance().world.getDamageSources().magic(),
					null,
					vec3d.getX(), vec3d.getY(), vec3d.getZ(),
					random.nextFloat(0.1F, 7.5F),
					false, World.ExplosionSourceType.TNT
			);
        }
        super.onBreak(world, pos, state, player);
    }
}
