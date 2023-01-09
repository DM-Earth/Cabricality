package com.dm.earth.cabricality.core.plugin;

import java.util.List;
import java.util.Set;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.util.debug.CabfLogger;
import org.objectweb.asm.tree.ClassNode;
import org.quiltmc.loader.api.QuiltLoader;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public class CabfMixinConfigPlugin implements IMixinConfigPlugin {

	@Override
	public void onLoad(String mixinPackage) {}

	@Override
	public String getRefMapperConfig() {
		return null;
	}

	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
		if (targetClassName.contains("ftbquests"))
			return QuiltLoader.isModLoaded("ftbquests");
		if (mixinClassName.matches(".*\\.log\\..*")) {
			Cabricality.CONFIG.load();
			return Cabricality.CONFIG.cleanerLog;
		}
		return true;
	}

	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

	@Override
	public List<String> getMixins() {
		return null;
	}

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}
