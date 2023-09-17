package com.dm.earth.cabricality;

import com.dm.earth.cabricality.config.CabfConfig;
import com.dm.earth.cabricality.content.alchemist.Alchemist;
import com.dm.earth.cabricality.content.core.TechThread;
import com.dm.earth.cabricality.content.entries.CabfBlockEntityTypes;
import com.dm.earth.cabricality.content.entries.CabfBlocks;
import com.dm.earth.cabricality.content.entries.CabfFluids;
import com.dm.earth.cabricality.content.entries.CabfItems;
import com.dm.earth.cabricality.content.entries.CabfRecipeSerializers;
import com.dm.earth.cabricality.content.entries.CabfSounds;
import com.dm.earth.cabricality.content.trading.data.recipe.Trading;
import com.dm.earth.cabricality.listener.DeployerCuttingRecipeHandler;
import com.dm.earth.cabricality.listener.UseEntityListener;
import com.dm.earth.cabricality.network.CabfReceiver;
import com.dm.earth.cabricality.tweak.BlockTagTweaks;
import com.dm.earth.cabricality.tweak.ItemTagTweaks;
import io.github.fabricators_of_create.porting_lib.util.EnvExecutor;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.krlite.equator.visual.color.AccurateColor;
import net.krlite.equator.visual.texture.Texture;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.resource.loader.api.ResourceLoader;
import org.quiltmc.qsl.resource.loader.api.ResourcePackActivationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;

import java.util.function.Supplier;

public class Cabricality implements ModInitializer {
	public static class Colors {
		public static final AccurateColor CABF_PURPLE = AccurateColor.fromARGB(0x6117DE);
		public static final AccurateColor CABF_MID_PURPLE = AccurateColor.fromARGB(0x3A1677);
		public static final AccurateColor CABF_DIM_PURPLE = AccurateColor.fromARGB(0x1B1329);
		public static final AccurateColor CABF_GRAY_PURPLE = AccurateColor.fromARGB(0x2F2939);
		public static final AccurateColor CABF_BRIGHT_PURPLE = AccurateColor.fromARGB(0xE0DBE8);
		public static final AccurateColor CABF_BLACK = AccurateColor.fromARGB(0x0D0C0E);
		public static final AccurateColor QUEST_DEPENDENCY = AccurateColor.fromARGB(0x4BFE90);
		public static final AccurateColor QUEST_DEPENDENT = AccurateColor.fromARGB(0x7B62FF);
	}

	public static class Textures {
		public static final Texture CABRICALITY_LOGO = texture("gui", "title", "logo");
		public static final Texture CABRICALITY_LOGO_NEON = texture("gui", "title", "logo_neon");
	}

	public static class Sounds {
		public static final SoundEvent[] FINISH_LOADING = {
				SoundEvents.BLOCK_BEACON_ACTIVATE, SoundEvents.BLOCK_BELL_RESONATE
		};
	}

	public static class ItemGroups {
		public static ItemGroup GENERAL = create(Cabricality.id("main"),
				() -> Registries.ITEM.get(Cabricality.id("andesite_machine")).getDefaultStack());
		public static ItemGroup SUBSTRATES = create(Cabricality.id("substrates"),
				() -> Registries.ITEM.get(Cabricality.id("jar")).getDefaultStack());

		private static ItemGroup create(Identifier identifier, Supplier<ItemStack> icon) {
			RegistryKey<ItemGroup> key = RegistryKey.of(RegistryKeys.ITEM_GROUP, identifier);

			return Registry.register(Registries.ITEM_GROUP, key, FabricItemGroup.builder()
					.icon(icon)
					.build());
		}
	}

	public static class RRPs {
		public static final RuntimeResourcePack CLIENT_RESOURCES = RuntimeResourcePack
				.create(id("client_resources"));
		public static final RuntimeResourcePack SERVER_RESOURCES = RuntimeResourcePack
				.create(id("server_resources"));
	}

	public static final String ID = "cabricality", NAME = "Cabricality";
	public static final Logger LOGGER = LoggerFactory.getLogger(ID);
	public static final CabfConfig CONFIG = new CabfConfig();
	private static long initTime = -1;

	@Contract("_ -> new")
	public static @NotNull Identifier id(String... paths) {
		return new Identifier(ID, String.join("/", paths));
	}

	@Contract("_ -> new")
	public static @NotNull Texture texture(String... paths) {
		return Texture.fromNamespacePath(ID, paths);
	}

	@Contract("_,_ -> new")
	public static @NotNull String genTranslationKey(String type, String... path) {
		return type + "." + ID + "." + String.join(".", path);
	}

	@Contract("_,_ -> new")
	public static @NotNull MutableText genTranslatableText(String type, String... path) {
		return Text.translatable(genTranslationKey(type, path));
	}

	@ClientOnly
	private static void initClientAssets() {
		RRPCallback.AFTER_VANILLA.register(list -> list.add(RRPs.CLIENT_RESOURCES));
	}

	@Override
	public void onInitialize(ModContainer mod) {
		CONFIG.save();
		LOGGER.info("📦 Initializing " + NAME + "...");
		initTime = System.currentTimeMillis();

		CabfReceiver.registerServer();

		Trading.load();
		Alchemist.load();
		DeployerCuttingRecipeHandler.load();
		CabfItems.register();
		CabfBlocks.register();
		CabfFluids.register();
		CabfSounds.register();
		CabfBlockEntityTypes.register();
		CabfRecipeSerializers.register();

		ItemTagTweaks.load();
		BlockTagTweaks.load();
		for (TechThread thread : TechThread.THREADS)
			thread.load();
		UseEntityListener.load();
		EnvExecutor.runWhenOn(EnvType.CLIENT, () -> Cabricality::initClientAssets);
		RRPCallback.AFTER_VANILLA.register(list -> list.add(RRPs.SERVER_RESOURCES));

		ResourceLoader.registerBuiltinResourcePack(id("data_overrides"),
				ResourcePackActivationType.ALWAYS_ENABLED);
	}

	public static void finishLoading() {
		if (initTime != -1) {
			double millis = System.currentTimeMillis() - initTime;

			Cabricality.LOGGER.info("⚙️ " + NAME + " has initialized in "
					+ (millis >= 1000 ? (millis / 1000.0 + "s") : (millis + "ms")) + "!");

			initTime = -1;
		}
	}
}
