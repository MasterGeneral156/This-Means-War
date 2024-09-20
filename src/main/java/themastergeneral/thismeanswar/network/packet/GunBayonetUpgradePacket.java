package themastergeneral.thismeanswar.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import themastergeneral.thismeanswar.items.NuGunItem;
import themastergeneral.thismeanswar.items.upgrade.UpgradeGunBayonetItem;

import java.util.function.Supplier;

public class GunBayonetUpgradePacket {
    private final ItemStack mainHand;

    public GunBayonetUpgradePacket(ItemStack mainHand) {
        this.mainHand = mainHand;
    }

    // Methods to encode/decode the packet
    public static void encode(GunBayonetUpgradePacket msg, FriendlyByteBuf buffer) {
        buffer.writeItem(msg.mainHand);
    }

    public static GunBayonetUpgradePacket decode(FriendlyByteBuf buffer) {
        return new GunBayonetUpgradePacket( buffer.readItem());
    }

    public static void handle(GunBayonetUpgradePacket msg, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                if (msg.mainHand.getItem() instanceof UpgradeGunBayonetItem bayonet)
                    bayonet.applyBayonetToGun(player.getOffhandItem(), player);
                else if (msg.mainHand.getItem() instanceof NuGunItem gun)
                    gun.playerRemoveBayonet(player.getMainHandItem(), player);

            }
        });
        context.setPacketHandled(true);
    }

}
