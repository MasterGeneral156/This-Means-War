package themastergeneral.thismeanswar.network.packet;

import mastergeneral156.chasethedragon.radial.RadialMenuOption;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;
import themastergeneral.thismeanswar.items.upgrade.UpgradeBulletType;
import themastergeneral.thismeanswar.items.upgrade.UpgradeGunBayonetItem;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class GunBayonetUpdatePacket {
    private final ItemStack offHand;

    public GunBayonetUpdatePacket(ItemStack offHand) {
        this.offHand = offHand;
    }

    // Methods to encode/decode the packet
    public static void encode(GunBayonetUpdatePacket msg, FriendlyByteBuf buffer) {
        buffer.writeItem(msg.offHand);
    }

    public static GunBayonetUpdatePacket decode(FriendlyByteBuf buffer) {
        return new GunBayonetUpdatePacket( buffer.readItem());
    }

    public static void handle(GunBayonetUpdatePacket msg, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                if (player.getMainHandItem().getItem() instanceof UpgradeGunBayonetItem bayonet)
                    bayonet.applyBayonetToGun(player.getOffhandItem(), player);
            }
        });
        context.setPacketHandled(true);
    }

}
