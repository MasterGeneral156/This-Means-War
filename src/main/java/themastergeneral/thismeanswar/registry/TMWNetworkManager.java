package themastergeneral.thismeanswar.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.network.packet.MagAmmoChangePacket;

public class TMWNetworkManager {
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(TMWMain.MODID, "main"),
            () -> "1.0",
            s -> true,
            s -> true
    );

    public static void registerMessages() {
        int id = 0;
        INSTANCE.registerMessage(id++, MagAmmoChangePacket.class, MagAmmoChangePacket::encode, MagAmmoChangePacket::decode, MagAmmoChangePacket::handle);
    }
}
