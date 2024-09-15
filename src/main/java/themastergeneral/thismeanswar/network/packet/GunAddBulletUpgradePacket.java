package themastergeneral.thismeanswar.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import themastergeneral.thismeanswar.items.upgrade.UpgradeBulletType;
import themastergeneral.thismeanswar.items.NuGunItem;

import java.util.function.Supplier;

public class GunAddBulletUpgradePacket {
    private final int itemSlot;

    public GunAddBulletUpgradePacket(int itemSlot) {
        this.itemSlot = itemSlot;
    }

    // Methods to encode/decode the packet
    public static void encode(GunAddBulletUpgradePacket msg, FriendlyByteBuf buffer) {
        buffer.writeInt(msg.itemSlot);
    }

    public static GunAddBulletUpgradePacket decode(FriendlyByteBuf buffer) {
        return new GunAddBulletUpgradePacket(buffer.readInt());
    }

    public static void handle(GunAddBulletUpgradePacket msg, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                ItemStack stack = player.getInventory().getItem(msg.itemSlot);
                if (stack.getItem() instanceof UpgradeBulletType upgrade) {
                    upgrade.playerApplyUpgrade(player, stack); // Apply the upgrade on the server side
                }
                else if (stack.getItem() instanceof NuGunItem gun) {
                    gun.playerRemoveRoundUpgrade(player, stack); // Apply the upgrade on the server side
                }
            }
        });
        context.setPacketHandled(true);
    }
}
