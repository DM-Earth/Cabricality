package dm.earth.cabricality.network;

import static dm.earth.cabricality.network.CabfNetworking.*;

import dm.earth.cabricality.network.receiver.HeldItemInfoReceiver;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

import com.dm.earth.cabricality.network.receiver.*;

public class CabfReceiver {
	public static void registerClient() {
	}

	public static void registerServer() {
		ServerPlayNetworking.registerGlobalReceiver(HELD_ITEM_INFO, new HeldItemInfoReceiver());
	}
}
