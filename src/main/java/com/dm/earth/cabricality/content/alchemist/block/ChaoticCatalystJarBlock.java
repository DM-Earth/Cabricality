package com.dm.earth.cabricality.content.alchemist.block;

import java.util.Random;

import org.jetbrains.annotations.Nullable;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.client.CabricalityClient;
import com.dm.earth.cabricality.util.PositionUtil;

import net.minecraft.block.BlockState;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion.DestructionType;

public class ChaoticCatalystJarBlock extends CatalystJarBlock {

    public ChaoticCatalystJarBlock(Settings settings) {
        super(settings);
    }

    @Override
    public Identifier getBlockModelId() {
        return Cabricality.id("block/jar/chaos_catalyst");
    }

    @Override
    public @Nullable String getContent() {
        return null;
    }

    @Override
    public String getTranslationKey() {
        return CabricalityClient.genTranslationKey("block", "chaos_catalyst_jar");
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        Random random = new Random();
        if (random.nextInt(2) == 0) {
            Vec3d vec3d = PositionUtil.fromBlockPos(pos);
            world.createExplosion(null, DamageSource.MAGIC, null, vec3d.getX(), vec3d.getY(), vec3d.getZ(), random.nextFloat(0.1F, 7.5F), false, DestructionType.DESTROY);
        }
        super.onBreak(world, pos, state, player);
    }

}
