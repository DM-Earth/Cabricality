package com.dm.earth.cabricality.mixin;

import com.github.reoseah.catwalksinc.CIncSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.world.World;

import com.simibubi.create.content.equipment.wrench.WrenchItem;
import io.github.coolmineman.bitsandchisels.BitsAndChisels;
import io.github.coolmineman.bitsandchisels.BitsBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(WrenchItem.class)
public class WrenchItemMixin {
	@Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
	private void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
		World world = context.getWorld();
		BlockPos pos = context.getBlockPos();
		BlockState state = world.getBlockState(pos);
		PlayerEntity player = context.getPlayer();
		Hand hand = context.getHand();

		// Ad Astra!
		if (state.getBlock() instanceof com.github.alexnijjar.ad_astra.blocks.pipes.Wrenchable wrenchable) {
			wrenchable.handleWrench(world, context.getBlockPos(), world.getBlockState(context.getBlockPos()),
					context.getSide(), context.getPlayer(), context.getHitPos());
			cir.setReturnValue(ActionResult.SUCCESS);
		}

		// Catwalks Inc.
		if (state.getBlock() instanceof com.github.reoseah.catwalksinc.block.Wrenchable wrenchable) {
			if (wrenchable.useWrench(state, world, pos, context.getSide(), player, hand, context.getHitPos())) {
				if (player != null) {
					context.getStack().damage(1, player, playerEntity -> playerEntity.sendToolBreakStatus(hand));
					world.playSound(
							null, player.getX(), player.getY(), player.getZ(),
							CIncSoundEvents.WRENCH_USE, SoundCategory.PLAYERS,
							1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F)
					);
				}

				cir.setReturnValue(ActionResult.SUCCESS);
			}
		}

		// Bits and Chisels
		if (!context.getWorld().isClient() && state.isOf(BitsAndChisels.BITS_BLOCK)) {
			BlockEntity blockEntity = context.getWorld().getBlockEntity(context.getBlockPos());
			if (blockEntity instanceof BitsBlockEntity bitsBlockEntity) {
				if (Objects.requireNonNull(context.getPlayer()).isSneaking()) {
					invert(context.getSide().getAxis(), bitsBlockEntity);
				} else {
					rotate(context.getSide().getAxis(), bitsBlockEntity);
				}
				cir.setReturnValue(ActionResult.SUCCESS);
			}
		}
	}

	@Unique
	void invert(Axis axis, BitsBlockEntity bitsBlockEntity) {
		BlockState[][][] rotated = new BlockState[16][16][16];
		switch (axis) {
			case X -> {
				for (int i = 0; i < 16; i++) {
					for (int j = 0; j < 16; j++) {
						for (int k = 0; k < 16; k++) {
							rotated[i][j][k] = bitsBlockEntity.getState(i, j, -k + 15);
						}
					}
				}
			}
			case Y -> {
				for (int i = 0; i < 16; i++) {
					for (int j = 0; j < 16; j++) {
						for (int k = 0; k < 16; k++) {
							rotated[i][j][k] = bitsBlockEntity.getState(i, -j + 15, k);
						}
					}
				}
			}
			case Z -> {
				for (int i = 0; i < 16; i++) {
					for (int j = 0; j < 16; j++) {
						for (int k = 0; k < 16; k++) {
							rotated[i][j][k] = bitsBlockEntity.getState(-i + 15, j, k);
						}
					}
				}
			}
		}
		bitsBlockEntity.setStates(rotated);
		bitsBlockEntity.rebuildServer();
		bitsBlockEntity.sync();
	}

	// Rotation Algorithm from
	// https://stackoverflow.com/questions/53110374/how-to-rotate-2-d-array-in-java,
	// extended to 3D
	@Unique
	void rotate(Axis axis, BitsBlockEntity e) {
		BlockState[][][] rotated = new BlockState[16][16][16];
		switch (axis) {
			case X -> {
				for (int i = 0; i < 16; i++) {
					for (int j = 0; j < 16; j++) {
						for (int k = 0; k < 16; k++) {
							rotated[i][j][k] = e.getState(i, 16 - k - 1, j);
						}
					}
				}
			}
			case Y -> {
				for (int i = 0; i < 16; i++) {
					for (int j = 0; j < 16; j++) {
						for (int k = 0; k < 16; k++) {
							rotated[i][j][k] = e.getState(16 - k - 1, j, i);
						}
					}
				}
			}
			case Z -> {
				for (int i = 0; i < 16; i++) {
					for (int j = 0; j < 16; j++) {
						for (int k = 0; k < 16; k++) {
							rotated[i][j][k] = e.getState(16 - j - 1, i, k);
						}
					}
				}
			}
		}
		e.setStates(rotated);
		e.rebuildServer();
		e.sync();
	}
}
