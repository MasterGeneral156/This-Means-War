package themastergeneral.thismeanswar.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import themastergeneral.thismeanswar.items.NuMagazineItem;

import java.util.function.Supplier;

public class MagAmmoChangePacket {
    private final int itemSlot;
    private final int changeAmount;

    public MagAmmoChangePacket(int itemSlot, int changeAmount) {
        this.itemSlot = itemSlot;
        this.changeAmount = changeAmount;
    }

    // Methods to encode/decode the packet
    public static void encode(MagAmmoChangePacket msg, FriendlyByteBuf buffer) {
        buffer.writeInt(msg.itemSlot);
        buffer.writeInt(msg.changeAmount);
    }

    public static MagAmmoChangePacket decode(FriendlyByteBuf buffer) {
        return new MagAmmoChangePacket(buffer.readInt(), buffer.readInt());
    }

    public static void handle(MagAmmoChangePacket msg, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                ItemStack stack = player.getInventory().getItem(msg.itemSlot);
                if (stack.getItem() instanceof NuMagazineItem mag) {
                    // Apply the change on the server side
                    if (msg.changeAmount > 0) {
                        // Add ammo
                        mag.playerAddAmmo(stack, player, msg.changeAmount);
                    } else {
                        // Remove ammo
                        mag.playerRemoveAmmo(stack, player, -msg.changeAmount);
                    }
                }
            }
        });
        context.setPacketHandled(true);
    }
}
