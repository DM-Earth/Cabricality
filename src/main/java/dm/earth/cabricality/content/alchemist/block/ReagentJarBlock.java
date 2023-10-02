package dm.earth.cabricality.content.alchemist.block;

import java.util.Objects;

import dm.earth.cabricality.Cabricality;
import dm.earth.cabricality.content.alchemist.Reagents;
import dm.earth.cabricality.content.alchemist.core.Reagent;
import dm.earth.cabricality.content.alchemist.core.Substrate;
import dm.earth.cabricality.content.entries.CabfBlocks;

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
	public Identifier getDefaultBlockId() {
		return Cabricality.id("reagent_jar");
	}

	@Override
	public Substrate getSubstrate() {
		return Reagents.getReagentFromBlock(this);
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if ((!player.isSneaking()) || !player.getStackInHand(Hand.MAIN_HAND).isEmpty() || this.getSubstrate() == null) return ActionResult.PASS;

		world.setBlockState(pos, CabfBlocks.JAR.getDefaultState());
		player.giveItemStack(((Reagent) Objects.requireNonNull(this.getSubstrate())).getItem().getDefaultStack());

		return ActionResult.SUCCESS;
	}
}
