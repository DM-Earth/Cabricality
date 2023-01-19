package com.dm.earth.cabricality.networking;

import com.dm.earth.cabricality.Cabricality;
import net.krlite.equator.util.IdentifierBuilder;
import net.minecraft.util.Identifier;

// Networking keys
public class CabfNetworking {
	private static final IdentifierBuilder.Specified ID_BUILDER = new IdentifierBuilder.Specified(Cabricality.ID + "_networking");

	// Commands
	public static final Identifier HELD_ITEM_INFO = ID_BUILDER.id("command", "held_item_info");
}
