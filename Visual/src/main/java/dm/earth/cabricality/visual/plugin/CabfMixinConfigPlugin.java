package dm.earth.cabricality.visual.plugin;

import dm.earth.cabricality.visual.CabricalityVisualClient;
import org.objectweb.asm.tree.ClassNode;
import org.quiltmc.loader.api.QuiltLoader;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

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
		final AtomicBoolean shouldApply = new AtomicBoolean(true);

		if (mixinClassName.startsWith(CabricalityVisualClient.class.getPackageName())) {
			// FTB
			String[] modIds = { "ftbquests", "ftblibrary" };
			Arrays.stream(modIds).forEach(id -> {
				if (targetClassName.matches(".*\\." + id + "\\..*"))
					shouldApply.set(QuiltLoader.isModLoaded(id));
			});
		}

		return shouldApply.get();
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
