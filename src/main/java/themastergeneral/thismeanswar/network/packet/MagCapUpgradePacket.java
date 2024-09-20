package themastergeneral.thismeanswar.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import themastergeneral.thismeanswar.items.NuGunItem;
import themastergeneral.thismeanswar.items.NuMagazineItem;
import themastergeneral.thismeanswar.items.upgrade.UpgradeGunBayonetItem;
import themastergeneral.thismeanswar.items.upgrade.UpgradeMagCapacityItem;

import java.util.function.Supplier;

public class MagCapUpgradePacket {
    private final ItemStack mainHand;

    public MagCapUpgradePacket(ItemStack mainHand) {
        this.mainHand = mainHand;
    }

    // Methods to encode/decode the packet
    public static void encode(MagCapUpgradePacket msg, FriendlyByteBuf buffer) {
        buffer.writeItem(msg.mainHand);
    }

    public static MagCapUpgradePacket decode(FriendlyByteBuf buffer) {
        return new MagCapUpgradePacket( buffer.readItem());
    }

    public static void handle(MagCapUpgradePacket msg, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                if (msg.mainHand.getItem() instanceof UpgradeMagCapacityItem magCap)
                    magCap.addCapUpgradeToGun(msg.mainHand, player);
                else if (msg.mainHand.getItem() instanceof NuMagazineItem mag)
                    mag.playerRemoveCapUpgrade(msg.mainHand, player);
            }
        });
        context.setPacketHandled(true);
    }

}
