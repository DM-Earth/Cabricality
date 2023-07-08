package com.dm.earth.cabricality.mixin.ad_astra;

import com.github.alexnijjar.ad_astra.entities.vehicles.VehicleEntity;
import io.github.fabricators_of_create.porting_lib.item.UseFirstBehaviorItem;
import io.github.fabricators_of_create.porting_lib.util.DamageableItem;
import io.github.fabricators_of_create.porting_lib.util.ShieldBlockItem;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.EntityDamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VehicleEntity.class)
public class VehicleEntityMixin {
	@Inject(method = "damage", at = @At("HEAD"), cancellable = true)
	private void fix(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		if (source instanceof EntityDamageSource source1 && source1.getAttacker() instanceof PlayerEntity player && isTCTool(player.getStackInHand(Hand.MAIN_HAND).getItem()))
			cir.setReturnValue(false);
	}

	@Unique
	private static boolean isTCTool(Item item) {
		return Registry.ITEM.getId(item).getNamespace().equals("tconstruct") && item instanceof UseFirstBehaviorItem && item instanceof DamageableItem && item instanceof ShieldBlockItem;
	}
}
