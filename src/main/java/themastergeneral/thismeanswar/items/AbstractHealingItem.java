package themastergeneral.thismeanswar.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import themastergeneral.thismeanswar.TMWMain;

public class AbstractHealingItem extends AbstractModItem {

	protected int ticksUsed = 0;
	protected int ticks;
	protected float health;
	
	public AbstractHealingItem(float health, int seconds) 
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
			doTreatmentTick(playerIn);
			stackIn.shrink(1);
			
			TextComponent message = new TextComponent("" + health + " ");
			message.append(new TranslatableComponent("thismeanswar.medic_success"));
			playerIn.displayClientMessage(message, true);
		}
		else
		{
			TranslatableComponent message = new TranslatableComponent("thismeanswar.medic_error");
			playerIn.displayClientMessage(message, true);
		}
		return InteractionResultHolder.sidedSuccess(stackIn, levelIn.isClientSide());
	}
	
	protected void doTreatmentTick(Player playerIn)
	{
		playerIn.getCooldowns().addCooldown(this, ticks);
		playerIn.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, this.ticks, 25, false, false));
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) 
	{
		if (this.health > 0)
			tooltip.add(new TranslatableComponent("Instantly recovers " + Math.round(this.health) + " health."));
	}

}
