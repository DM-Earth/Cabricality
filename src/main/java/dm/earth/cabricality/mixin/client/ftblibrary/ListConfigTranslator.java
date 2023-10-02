package dm.earth.cabricality.mixin.client.ftblibrary;

import dev.ftb.mods.ftblibrary.config.ListConfig;
import net.minecraft.text.Text;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@ClientOnly
@Mixin(ListConfig.class)
public class ListConfigTranslator {
	@Mutable
	@Shadow(remap = false)
	@Final
	public static final Text EMPTY_LIST = Text.literal("[   ]");
}
