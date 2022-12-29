package com.dm.earth.cabricality;

import java.awt.Color;
import java.util.Arrays;

import com.dm.earth.cabricality.util.func.CabfBlur;
import com.dm.earth.cabricality.util.PushUtil;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.item.group.api.QuiltItemGroup;
import org.quiltmc.qsl.resource.loader.api.ResourceLoader;
import org.quiltmc.qsl.resource.loader.api.ResourcePackActivationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dm.earth.cabricality.content.alchemist.Alchemist;
import com.dm.earth.cabricality.content.core.TechThread;
import com.dm.earth.cabricality.content.entries.CabfBlockEntityTypes;
import com.dm.earth.cabricality.content.entries.CabfBlocks;
import com.dm.earth.cabricality.content.entries.CabfFluids;
import com.dm.earth.cabricality.content.entries.CabfItems;
import com.dm.earth.cabricality.content.entries.CabfSounds;
import com.dm.earth.cabricality.content.trading.data.recipe.Trading;
import com.dm.earth.cabricality.listener.DeployerCuttingRecipeHandler;
import com.dm.earth.cabricality.listener.UseEntityListener;
import com.dm.earth.cabricality.tweak.TagTweaks;
import com.dm.earth.cabricality.util.ScreenUtil;
import com.dm.earth.cabricality.util.debug.CabfLogger;
import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Cabricality implements ModInitializer {
	public static final String NAME = "Cabricality";
	public static final String ID = "cabricality";
	public static final Logger LOGGER = LoggerFactory.getLogger(ID);

	// Colors
	public static final Color CABF_PURPLE = new Color(0x6117DE);
	public static final Color CABF_MID_PURPLE = new Color(0x3A1677);
	public static final Color CABF_DIM_PURPLE = new Color(0x1B1329);
	public static final Color CABF_GRAY_PURPLE = new Color(0x2F2939);
	public static final Color CABF_BRIGHT_PURPLE = new Color(0xE0DBE8);
	public static final Color CABF_BLACK = new Color(0x0D0C0E);

	// Textures
	public static final Identifier CABRICALITY_TITLE_TEXTURE =
			id("textures", "gui", "title", "cabricality.png");
	public static final Identifier MINECRAFT_SUBTITLE_TEXTURE =
			id("textures", "gui", "title", "minecraft.png");

	// Sounds
	public static final SoundEvent FINISH_LOADING = SoundEvents.BLOCK_AMETHYST_BLOCK_PLACE;

	// RRPs
	public static final RuntimeResourcePack CLIENT_RESOURCES =
			RuntimeResourcePack.create(id("client_resources"));
	public static final RuntimeResourcePack SERVER_RESOURCES =
			RuntimeResourcePack.create(id("server_resources"));

	// Item groups
	public static ItemGroup MAIN_GROUP = QuiltItemGroup.createWithIcon(Cabricality.id("main"),
			() -> Registry.ITEM.get(Cabricality.id("andesite_machine")).getDefaultStack());
	public static ItemGroup SUBSTRATES_GROUP =
			QuiltItemGroup.createWithIcon(Cabricality.id("substrates"),
					() -> Registry.ITEM.get(Cabricality.id("jar")).getDefaultStack());

	// Generators
	@Contract("_ -> new")
	public static @NotNull Identifier id(String... id) {
		return new Identifier(ID, String.join("/", id));
	}

	@Contract("_,_ -> new")
	public static @NotNull String genTranslationKey(String type, String... path) {
		return type + "." + ID + "."
				+ String.join(".", Arrays.stream(path).filter(p -> !p.isEmpty()).toList());
	}

	@Contract("_,_ -> new")
	public static @NotNull TranslatableText genTranslatableText(String type, String... path) {
		return new TranslatableText(genTranslationKey(type, path));
	}

	// Initialization
	@Override
	public void onInitialize(ModContainer mod) {
		CabfLogger.logInfo("Initializing... 📦");

		Trading.load();
		Alchemist.load();
		DeployerCuttingRecipeHandler.load();
		CabfItems.register();
		CabfBlocks.register();
		CabfFluids.register();
		CabfSounds.register();
		CabfBlockEntityTypes.register();
		ScreenUtil.registerEvents();
		PushUtil.register();
		CabfBlur.INSTANCE.init();
		TagTweaks.load();
		for (TechThread thread : TechThread.THREADS)
			thread.load();
		UseEntityListener.load();
		initClientAssets();

		RRPCallback.AFTER_VANILLA.register(list -> list.add(SERVER_RESOURCES));

		ResourceLoader.registerBuiltinResourcePack(id("data_overrides"),
				ResourcePackActivationType.ALWAYS_ENABLED);
	}

	@ClientOnly
	private static void initClientAssets() {
		RRPCallback.AFTER_VANILLA.register(list -> list.add(CLIENT_RESOURCES));
	}
}
