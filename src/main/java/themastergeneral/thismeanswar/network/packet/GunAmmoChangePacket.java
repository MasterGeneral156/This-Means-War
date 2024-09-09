package themastergeneral.thismeanswar.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import themastergeneral.thismeanswar.items.NuGunItem;
import themastergeneral.thismeanswar.items.NuMagazineItem;

import java.util.function.Supplier;

public class GunAmmoChangePacket {
    private final int itemSlot;
    private final int changeAmount;

    public GunAmmoChangePacket(int itemSlot, int changeAmount) {
        this.itemSlot = itemSlot;
        this.changeAmount = changeAmount;
    }

    // Methods to encode/decode the packet
    public static void encode(GunAmmoChangePacket msg, FriendlyByteBuf buffer) {
        buffer.writeInt(msg.itemSlot);
        buffer.writeInt(msg.changeAmount);
    }

    public static GunAmmoChangePacket decode(FriendlyByteBuf buffer) {
        return new GunAmmoChangePacket(buffer.readInt(), buffer.readInt());
    }

    public static void handle(GunAmmoChangePacket msg, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                ItemStack stack = player.getInventory().getItem(msg.itemSlot);
                if (stack.getItem() instanceof NuGunItem gun) {
                    // Apply the change on the server side
                    if (msg.changeAmount > 0) {
                        // Add ammo
                        gun.handleFillInternalMag(stack, player);
                    } else {
                        // Remove ammo
                        gun.handleRemoveInternalMag(stack, player);
                    }
                }
            }
        });
        context.setPacketHandled(true);
    }
}
