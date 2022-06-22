package themastergeneral.thismeanswar.items;

import java.text.NumberFormat;

import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import themastergeneral.thismeanswar.ModConstant;
import themastergeneral.thismeanswar.TMWMain;

public class BaseHealingItem extends BaseTMWItem {

	protected int ticksUsed = 0;
	protected int ticks;
	protected float health;
	
	public BaseHealingItem(float health, int seconds) 
	{
		super(new Properties().tab(TMWMain.ITEMGROUP));
		this.health = health;
		this.ticks = seconds * 20;
	}
	
	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) 
	{
	      return 0.0F;
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level levelIn, Player playerIn, InteractionHand handIn) 
	{
		ItemStack stackIn = playerIn.getItemInHand(handIn);
		if (playerIn.getHealth() <= playerIn.getMaxHealth())
		{
			playerIn.setHealth(playerIn.getHealth() + health);
			playerIn.getCooldowns().addCooldown(this, ticks);
			playerIn.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, this.ticks, 25));
			stackIn.shrink(1);
			
			TextComponent message = new TextComponent(health + " health recovered.");
			playerIn.displayClientMessage(message, true);
		}
		else
		{
			TextComponent message = new TextComponent("Cannot use item when fully healed.");
			playerIn.displayClientMessage(message, true);
		}
		return InteractionResultHolder.sidedSuccess(stackIn, levelIn.isClientSide());
	}

}
