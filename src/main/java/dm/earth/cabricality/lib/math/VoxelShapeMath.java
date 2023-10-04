package dm.earth.cabricality.lib.math;

import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class VoxelShapeMath {
	public static VoxelShape simpleBox(double x1, double y1, double z1, double x2, double y2, double z2) {
		Box box = new Box(x1, y1, z1, x2, y2, z2);
		return VoxelShapes.cuboid(box.minX / 16.0, box.minY / 16.0, box.minZ / 16.0, box.maxX / 16.0, box.maxY / 16.0, box.maxZ / 16.0);
	}

	public static VoxelShape simpleBox(Direction direction, double x1, double y1, double z1, double x2, double y2, double z2) {
		return switch (direction) {
			case NORTH -> simpleBox(x1, y1, z1, x2, y2, z2);
			case WEST -> simpleBox(z1, y1, 16 - x1, z2, y2, 16 - x2);
			case SOUTH -> simpleBox(16 - x1, y1, 16 - z1, 16 - x2, y2, 16 - z2);
			case EAST -> simpleBox(16 - z1, y1, x1, 16 - z2, y2, x2);
			case UP, DOWN -> throw new UnsupportedOperationException("Unimplemented case: " + direction);
		};
	}
}
