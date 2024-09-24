package themastergeneral.thismeanswar.items;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.themastergeneral.ctdcore.helpers.ModUtils;
import com.themastergeneral.ctdcore.item.CTDDurabilityItem;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.util.RandomSource;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import themastergeneral.thismeanswar.items.interfaces.AbstractModItem;

public class DurabilityItem extends AbstractModItem {

	private final Supplier<Integer> durabilitySupplier;
	protected int durability;

	public DurabilityItem(Supplier<Integer> durabilitySupplier, int placeholderDurability) {
		super(new Item.Properties().durability(placeholderDurability));  // Set placeholder initially
		this.durabilitySupplier = durabilitySupplier;
		this.durability = placeholderDurability;  // Start with the placeholder
	}

	@Override
	public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		if (this.getMaxDamage(stack) != durabilitySupplier.get())
			this.updateDurability();
	}

	public void updateDurability() {
		this.durability = durabilitySupplier.get();  // Update durability to the config value
	}

	@Override
	public int getMaxDamage(ItemStack stack) {
		return durability;  // Return the current durability
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) 
	{
		if (stack.isDamageableItem())
			if (Screen.hasShiftDown())
				tooltip.add(ModUtils.displayString("Durability: " + this.remainingDamage(stack) + " / " + this.getMaxDamage(stack)));
			else
				tooltip.add(ModUtils.displayString("Durability: " + ModUtils.returnShortenedNumber(this.remainingDamage(stack)) + " / " + ModUtils.returnShortenedNumber(this.getMaxDamage(stack))));
	}

	@Override
	public ItemStack getCraftingRemainingItem(ItemStack itemStack)
	{
		ItemStack stack = itemStack.copy();
		if(stack.hurt(1, RandomSource.createNewThreadLocalInstance(), null))
			return ItemStack.EMPTY;
		else
			return stack;
	}

	@Override
	public boolean hasCraftingRemainingItem(ItemStack stack)
	{
		return true;
	}

	protected int remainingDamage(ItemStack stack)
	{
		return stack.getMaxDamage() - stack.getDamageValue();
	}

}
