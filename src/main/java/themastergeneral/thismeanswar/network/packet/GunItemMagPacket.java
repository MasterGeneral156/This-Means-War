package themastergeneral.thismeanswar.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import themastergeneral.thismeanswar.items.NuGunItem;
import themastergeneral.thismeanswar.items.NuMagazineItem;

import java.util.function.Supplier;

public class GunItemMagPacket {
    private final int itemSlot;

    public GunItemMagPacket(int itemSlot) {
        this.itemSlot = itemSlot;
    }

    // Methods to encode/decode the packet
    public static void encode(GunItemMagPacket msg, FriendlyByteBuf buffer) {
        buffer.writeInt(msg.itemSlot);
    }

    public static GunItemMagPacket decode(FriendlyByteBuf buffer) {
        return new GunItemMagPacket(buffer.readInt());
    }

    public static void handle(GunItemMagPacket msg, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                ItemStack stack = player.getInventory().getItem(msg.itemSlot);
                if (stack.getItem() instanceof NuGunItem gun) {
                    gun.handleMagazineInsertion(stack, player);
                }
            }
        });
        context.setPacketHandled(true);
    }
}
