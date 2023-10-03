package dm.earth.cabricality.network;

import static dm.earth.cabricality.network.CabfNetworking.*;

import dm.earth.cabricality.network.receiver.HeldItemInfoReceiver;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class CabfReceiver {
	public static void registerClient() {
	}

	public static void registerServer() {
		ServerPlayNetworking.registerGlobalReceiver(HELD_ITEM_INFO, new HeldItemInfoReceiver());
	}
}
