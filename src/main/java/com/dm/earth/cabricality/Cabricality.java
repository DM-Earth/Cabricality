package com.dm.earth.cabricality;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.item.group.api.QuiltItemGroup;
import org.quiltmc.qsl.resource.loader.api.ResourceLoader;
import org.quiltmc.qsl.resource.loader.api.ResourcePackActivationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.dm.earth.cabricality.tweak.ItemTagTweaks;
import com.dm.earth.cabricality.util.mod.CabfModConflict;
import io.github.fabricators_of_create.porting_lib.util.EnvExecutor;
import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.fabricmc.api.EnvType;
import net.krlite.equator.color.PreciseColor;
import net.krlite.equator.util.IdentifierBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Cabricality implements ModInitializer {
	public static class Colors {
		public static final PreciseColor CABF_PURPLE = PreciseColor.of(0x6117DE);
		public static final PreciseColor CABF_MID_PURPLE = PreciseColor.of(0x3A1677);
		public static final PreciseColor CABF_DIM_PURPLE = PreciseColor.of(0x1B1329);
		public static final PreciseColor CABF_GRAY_PURPLE = PreciseColor.of(0x2F2939);
		public static final PreciseColor CABF_BRIGHT_PURPLE = PreciseColor.of(0xE0DBE8);
		public static final PreciseColor CABF_BLACK = PreciseColor.of(0x0D0C0E);
		public static final PreciseColor QUEST_DEPENDENCY = PreciseColor.of(0x4BFE90);
		public static final PreciseColor QUEST_DEPENDENT = PreciseColor.of(0x7B62FF);
	}

	public static class Textures {
		public static final Identifier CABRICALITY_TITLE_TEXTURE =
				id("textures", "gui", "title", "cabricality.png");
		public static final Identifier MINECRAFT_SUBTITLE_TEXTURE =
				id("textures", "gui", "title", "minecraft.png");
	}

	public static class Sounds {
		public static final SoundEvent FINISH_LOADING = SoundEvents.BLOCK_AMETHYST_BLOCK_PLACE;
	}

	public static class ItemGroups {
		public static ItemGroup MAIN_GROUP = QuiltItemGroup.createWithIcon(Cabricality.id("main"),
				() -> Registry.ITEM.get(Cabricality.id("andesite_machine")).getDefaultStack());
		public static ItemGroup SUBSTRATES_GROUP =
				QuiltItemGroup.createWithIcon(Cabricality.id("substrates"),
						() -> Registry.ITEM.get(Cabricality.id("jar")).getDefaultStack());
	}

	public static class RRPs {
		public static final RuntimeResourcePack CLIENT_RESOURCES =
				RuntimeResourcePack.create(id("client_resources"));
		public static final RuntimeResourcePack SERVER_RESOURCES =
				RuntimeResourcePack.create(id("server_resources"));
	}

	public static final String NAME = "Cabricality";
	public static final String ID = "cabricality";
	public static final Logger LOGGER = LoggerFactory.getLogger(ID);
	public static final IdentifierBuilder.Specified ID_BUILDER =
			new IdentifierBuilder.Specified(ID);
	public static final CabfConfig CONFIG =
			new CabfConfig(QuiltLoader.getConfigDir().resolve("cabricality.toml").toFile());

	@Contract("_ -> new")
	public static @NotNull Identifier id(String... paths) {
		return ID_BUILDER.id(paths);
	}

	@Contract("_,_ -> new")
	public static @NotNull String genTranslationKey(String type, String... path) {
		return ID_BUILDER.translationKey(type, path);
	}

	@Contract("_,_ -> new")
	public static @NotNull TranslatableText genTranslatableText(String type, String... path) {
		return ID_BUILDER.localization(type, path);
	}

	@ClientOnly
	private static void initClientAssets() {
		RRPCallback.AFTER_VANILLA.register(list -> list.add(RRPs.CLIENT_RESOURCES));
	}

	@Override
	public void onInitialize(ModContainer mod) {
		CONFIG.load();
		CONFIG.save();

		CabfModConflict.checkAndExit();

		LOGGER.info("Initializing " + NAME + "... 📦");

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
		for (TechThread thread : TechThread.THREADS)
			thread.load();
		UseEntityListener.load();
		EnvExecutor.runWhenOn(EnvType.CLIENT, () -> Cabricality::initClientAssets);
		RRPCallback.AFTER_VANILLA.register(list -> list.add(RRPs.SERVER_RESOURCES));

		ResourceLoader.registerBuiltinResourcePack(id("data_overrides"),
				ResourcePackActivationType.ALWAYS_ENABLED);
	}
}
