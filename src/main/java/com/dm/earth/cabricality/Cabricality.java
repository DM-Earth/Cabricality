package com.dm.earth.cabricality;

import com.dm.earth.cabricality.content.entries.CabfSounds;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.item.group.api.QuiltItemGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dm.earth.cabricality.content.alchemist.Alchemist;
import com.dm.earth.cabricality.content.core.TechThread;
import com.dm.earth.cabricality.content.entries.CabfBlockEntityTypes;
import com.dm.earth.cabricality.content.entries.CabfBlocks;
import com.dm.earth.cabricality.content.entries.CabfFluids;
import com.dm.earth.cabricality.content.entries.CabfItems;
import com.dm.earth.cabricality.content.trading.data.recipe.Trading;
import com.dm.earth.cabricality.listener.DeployerCuttingRecipeHandler;
import com.dm.earth.cabricality.listener.UseEntityListener;
import com.dm.earth.cabricality.tweak.TagTweaks;

import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.minecraft.item.ItemGroup;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Cabricality implements ModInitializer {
	public static final String NAME = "Cabricality";

	public static final String ID = "cabricality";
	public static final Logger LOGGER = LoggerFactory.getLogger(ID);

	/* RRPs */
	public static final RuntimeResourcePack CLIENT_RESOURCES = RuntimeResourcePack.create(id("client_resources"));
	public static final RuntimeResourcePack SERVER_RESOURCES = RuntimeResourcePack.create(id("server_resources"));

	/* Item Groups */
	public static ItemGroup MAIN_GROUP = QuiltItemGroup.createWithIcon(Cabricality.id("main"),
			() -> Registry.ITEM.get(Cabricality.id("andesite_machine")).getDefaultStack());
	public static ItemGroup SUBSTRATES_GROUP = QuiltItemGroup.createWithIcon(Cabricality.id("substrates"),
			() -> Registry.ITEM.get(Cabricality.id("jar")).getDefaultStack());

	/* Generator Utils */
	@Contract("_ -> new")
	public static @NotNull Identifier id(String... id) {
		return new Identifier(ID, String.join("/", id));
	}

	@Contract("_,_ -> new")
	public static @NotNull String genTranslationKey(String type, String... path) {
		return type + "." + ID + "." + String.join(".", path);
	}

	@Contract("_,_ -> new")
	public static @NotNull TranslatableText genTranslatableText(String type, String... path) {
		return new TranslatableText(genTranslationKey(type, path));
	}

	/* Loggers */
	public static void logInfo(@NotNull String message) {
		LOGGER.info("[" + NAME + "] " + message);
	}

	public static void logWarn(@NotNull String message) {
		LOGGER.warn("[" + NAME + "] " + message);
	}

	public static void logError(@NotNull String message) {
		LOGGER.error("[" + NAME + "] " + message);
	}

	public static void logError(@NotNull String message, @NotNull Throwable throwable) {
		LOGGER.error("[" + NAME + "] " + message, throwable);
	}

	public static void logDebug(@NotNull String message) {
		LOGGER.debug("[" + NAME + "] " + message);
	}

	public static void logDebug(@NotNull String message, @NotNull Throwable throwable) {
		LOGGER.debug("[" + NAME + "] " + message, throwable);
	}

	public static void logTrace(@NotNull String message) {
		LOGGER.trace("[" + NAME + "] " + message);
	}

	public static void logTrace(@NotNull String message, @NotNull Throwable throwable) {
		LOGGER.trace("[" + NAME + "] " + message, throwable);
	}

	public static void logDebugAndError(@NotNull String message, @NotNull Throwable throwable) {
		logDebug(message, throwable);
		logError(message);
	}

	/* Initialization */
	@Override
	public void onInitialize(ModContainer mod) {
		logInfo("Initializing... 📦");

		Trading.load();
		Alchemist.load();
		DeployerCuttingRecipeHandler.load();
		CabfItems.register();
		CabfBlocks.register();
		CabfFluids.register();
		CabfSounds.register();
		CabfBlockEntityTypes.register();
		TagTweaks.load();
		for (TechThread thread : TechThread.THREADS)
			thread.load();
		UseEntityListener.load();
		initClientAssets();

		RRPCallback.AFTER_VANILLA.register(list -> list.add(SERVER_RESOURCES));

		//ResourceLoader.registerBuiltinResourcePack(id("data_overrides"),
		//ResourcePackActivationType.ALWAYS_ENABLED);
	}

	@ClientOnly
	private static void initClientAssets() {
		RRPCallback.AFTER_VANILLA.register(list -> list.add(CLIENT_RESOURCES));
	}
}
