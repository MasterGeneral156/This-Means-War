package themastergeneral.thismeanswar.items.armors;

import com.themastergeneral.ctdcore.helpers.ModUtils;

import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class WarArmorItem extends ArmorItem {

	public WarArmorItem(ArmorMaterial p_40386_, Type p_266831_) {
		super(p_40386_, p_266831_, new Properties().stacksTo(1));
	}
	
	//When worn, we will attempt to keep the player healed when their health is low,
	//otherwise, we will attempt repairs on the armor.
	//We will stop healing the player when the durability is below 10%
	public void onArmorTick(ItemStack stack, Level level, Player player)
    {
		Double durabilityPercent = getDurabilityPercent(stack);
		//make sure item is not on a cooldown
		if (!player.getCooldowns().isOnCooldown(stack.getItem()))
		{
			//player health less than max, and the armor set has at least 10% durability remaining
			if ((player.getHealth() < player.getMaxHealth()) && (durabilityPercent > 10))
			{
				player.setHealth(player.getHealth() + 0.5F);
				//deal 10% total damage for a health heal
				stack.hurt(stack.getMaxDamage() / 10, RandomSource.createNewThreadLocalInstance(), null);
				player.getCooldowns().addCooldown(stack.getItem(), 20);
			}
			else if (stack.isDamaged() && player.getHealth() == player.getMaxHealth())
			{
				stack.hurt(-1, RandomSource.createNewThreadLocalInstance(), null);
				player.getCooldowns().addCooldown(stack.getItem(), 40);
			}
			//force repair at 10 to prevent it from destroying itself
			else if (durabilityPercent <= 10)
			{
				stack.hurt(-1, RandomSource.createNewThreadLocalInstance(), null);
				player.getCooldowns().addCooldown(stack.getItem(), 40);
			}
			//catch all to not repeat this...
			if (player.getHealth() > player.getMaxHealth())
				player.setHealth(player.getMaxHealth());
		}
    }
	
	@Override
    public boolean isFoil(ItemStack stack) {
        if (this.getDurabilityPercent(stack) > 10)
        	return true;
        else
        	return false;
    }
	
	protected Double getDurabilityPercent(ItemStack stack) 
	{
		Integer currentDurability = stack.getMaxDamage() - stack.getDamageValue();
		return ((double) currentDurability / (double) stack.getMaxDamage() * 100);
	}
}
