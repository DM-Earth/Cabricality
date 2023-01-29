package com.dm.earth.cabricality.mixin.client;

import net.minecraft.client.gui.screen.ChatScreen;

import net.minecraft.client.gui.screen.Screen;

import net.minecraft.text.Text;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(ChatScreen.class)
public class ChatScreenExpander extends Screen {
	protected ChatScreenExpander(Text title) {
		super(title);
	}

	@ModifyArgs(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ChatScreen;fill(Lnet/minecraft/client/util/math/MatrixStack;IIIII)V"))
	private void renderChatArea(Args args) {
		args.set(1, 0); // X1
		args.set(2, this.height - 16); // Y1
		args.set(3, this.width); // X2
		args.set(4, this.height); // Y2
	}
}
