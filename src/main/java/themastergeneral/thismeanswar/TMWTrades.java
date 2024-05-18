package themastergeneral.thismeanswar;

import javax.annotation.Nullable;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;

public class TMWTrades implements VillagerTrades.ItemListing {

	 	private final Item buyItem;
	    private final int buyCount;
	    private final Item sellItem;
	    private final int sellCount;
	    private final int maxUses;
	    private final int experience;
	    private final float priceMultiplier;

	    public TMWTrades(Item buyItem, int buyCount, Item sellItem, int sellCount, int maxUses, int experience, float priceMultiplier) {
	        this.buyItem = buyItem;
	        this.sellItem = sellItem;
	        this.buyCount = buyCount;
	        this.sellCount = sellCount;
	        this.maxUses = maxUses;
	        this.experience = experience;
	        this.priceMultiplier = priceMultiplier;
	    }

	    @Nullable
		@Override
		public MerchantOffer getOffer(Entity e, RandomSource random) {
	    	ItemStack buyStack = new ItemStack(buyItem, buyCount);
	    	ItemStack sellStack = new ItemStack(sellItem, sellCount);
	        return new MerchantOffer(buyStack, sellStack, maxUses, experience, priceMultiplier);
		}

}