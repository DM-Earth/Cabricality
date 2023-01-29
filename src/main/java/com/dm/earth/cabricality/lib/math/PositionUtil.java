package com.dm.earth.cabricality.lib.math;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class PositionUtil {
	public static Vec3d fromBlockPos(BlockPos pos) {
		return new Vec3d(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
	}
}
