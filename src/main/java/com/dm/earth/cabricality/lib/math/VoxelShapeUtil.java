package com.dm.earth.cabricality.lib.math;

import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class VoxelShapeUtil {
	public static VoxelShape simpleBox(double x1, double y1, double z1, double x2, double y2, double z2) {
		Box box = new Box(x1, y1, z1, x2, y2, z2);
		return VoxelShapes.cuboid(box.minX / 16.0, box.minY / 16.0, box.minZ / 16.0, box.maxX / 16.0, box.maxY / 16.0, box.maxZ / 16.0);
	}

	//input: north
	public static VoxelShape simpleBox(Direction direction, double x1, double y1, double z1, double x2, double y2, double z2) {
		VoxelShape returnBox = simpleBox(x1, y1, z1, x2, y2, z2);
		switch (direction) {
			case NORTH -> {
				returnBox = simpleBox(x1, y1, z1, x2, y2, z2);
			}
			case WEST -> {
				returnBox = simpleBox(z1, y1, 16 - x1, z2, y2, 16 - x2);
			}
			case SOUTH -> {
				returnBox = simpleBox(16 - x1, y1, 16 - z1, 16 - x2, y2, 16 - z2);
			}
			case EAST -> {
				returnBox = simpleBox(16 - z1, y1, x1, 16 - z2, y2, x2);
			}
			case DOWN -> throw new UnsupportedOperationException("Unimplemented case: " + direction);
			case UP -> throw new UnsupportedOperationException("Unimplemented case: " + direction);
			default -> throw new IllegalArgumentException("Unexpected value: " + direction);
		}
		return returnBox;
	}
}
