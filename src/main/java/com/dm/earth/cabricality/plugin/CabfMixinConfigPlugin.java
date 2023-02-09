package com.dm.earth.cabricality.plugin;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.objectweb.asm.tree.ClassNode;
import org.quiltmc.loader.api.QuiltLoader;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import com.dm.earth.cabricality.Cabricality;

public class CabfMixinConfigPlugin implements IMixinConfigPlugin {
	@Override
	public void onLoad(String mixinPackage) {
	}

	@Override
	public String getRefMapperConfig() {
		return null;
	}

	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
		if (mixinClassName.startsWith("com.dm.earth.cabricality")) {
			AtomicBoolean ret = new AtomicBoolean(true);
			String[] modIds = { "ftbquests", "ftblibrary" };
			Arrays.stream(modIds).forEach(id -> {
				if (targetClassName.matches(".*\\." + id + "\\..*"))
					ret.set(QuiltLoader.isModLoaded(id));
			});
			if (mixinClassName.matches(".*\\.log\\..*"))
				return Cabricality.CONFIG.cleanerLog;
		}
		return true;
	}

	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

	}

	@Override
	public List<String> getMixins() {
		return null;
	}

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

	}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

	}
}
