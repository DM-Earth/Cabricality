package com.dm.earth.cabricality.client.plugin.rei;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.registry.screen.ExclusionZonesProvider;
import net.minecraft.client.MinecraftClient;
import slimeknights.tconstruct.tables.client.inventory.TinkerStationScreen;

import java.util.Collection;
import java.util.Collections;

public class TinkersAnvilExclusionZones implements ExclusionZonesProvider<TinkerStationScreen> {
	@Override
	public Collection<Rectangle> provide(TinkerStationScreen screen) {
		int width = 420, height = 180, x = MinecraftClient.getInstance().getWindow().getScaledWidth() / 2, y = MinecraftClient.getInstance().getWindow().getScaledHeight() / 2;
		return Collections.singletonList(new Rectangle(x - width / 2, y - height / 2, width, height));
	}
}
