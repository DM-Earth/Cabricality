package com.dm.earth.cabricality.listener;

import com.mojang.datafixers.schemas.Schema;
import net.minecraft.datafixer.schema.IdentifierNormalizingSchema;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.datafixerupper.api.QuiltDataFixerBuilder;
import org.quiltmc.qsl.datafixerupper.api.QuiltDataFixes;
import org.quiltmc.qsl.datafixerupper.api.SimpleFixes;
import org.quiltmc.qsl.registry.api.event.RegistryEvents;

import java.util.HashMap;
import java.util.Map;

public class DataFixerListener {
	private static final Map<Identifier, Identifier> dataFixerMapItem = new HashMap<>();
	private static final Map<Identifier, Identifier> dataFixerMapBlock = new HashMap<>();

	public static void load() {
		RegistryEvents.getEntryAddEvent(Registry.ITEM).register(context -> {
			if (context.id().getNamespace().equals("cabricality")) {
				if (context.value() instanceof BlockItem) {
					dataFixerMapBlock.put(new Identifier("kubejs", context.id().getPath()), context.id());
					dataFixerMapItem.put(new Identifier("kubejs", context.id().getPath()), context.id());
				} else if (context.value() != null)
					dataFixerMapItem.put(new Identifier("kubejs", context.id().getPath()), context.id());
			}
		});
	}

	public static void registerDataFixers() {
		if (dataFixerMapBlock.isEmpty() && dataFixerMapItem.isEmpty()) return;
		QuiltDataFixerBuilder builder = new QuiltDataFixerBuilder(1);
		builder.addSchema(0, QuiltDataFixes.BASE_SCHEMA);
		Schema schema = builder.addSchema(1, IdentifierNormalizingSchema::new);
		dataFixerMapItem.forEach((oldId, newId) -> SimpleFixes.addItemRenameFix(builder, "Cabricality old namespace fixer: Items", oldId, newId, schema));
		dataFixerMapBlock.forEach((oldId, newId) -> SimpleFixes.addItemRenameFix(builder, "Cabricality old namespace fixer: Blocks", oldId, newId, schema));
	}
}
