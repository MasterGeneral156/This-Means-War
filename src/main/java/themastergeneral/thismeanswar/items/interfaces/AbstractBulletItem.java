package themastergeneral.thismeanswar.items.interfaces;

import com.themastergeneral.ctdcore.helpers.ModUtils;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import themastergeneral.thismeanswar.TMWUtils;
import themastergeneral.thismeanswar.config.Constants;

import javax.annotation.Nullable;
import java.util.List;

public class AbstractBulletItem extends AbstractModItem {

	protected AbstractModItem bulletCasing;
	protected AbstractModItem bulletTip;
	protected TagKey<Item> compatBullet;
	protected float damage;
	//No ammo stack limit...
	public AbstractBulletItem(AbstractModItem casing, AbstractModItem tip, float damage)
	{
		super(new Properties());
		this.bulletCasing = casing;
		this.bulletTip = tip;
		this.compatBullet = null;
		this.damage = damage;
		
	}
	
	//No ammo stack limit, but has tag for bullet compatibily.
	public AbstractBulletItem(AbstractModItem casing, AbstractModItem tip, float damage, TagKey<Item> compatBullet)
	{
		super(new Properties());
		this.bulletCasing = casing;
		this.bulletTip = tip;
		this.compatBullet = compatBullet;
		this.damage = damage;
	}
	
	public AbstractBulletItem(float damage)
	{
		super(new Properties());
		this.bulletCasing = null;
		this.bulletTip = null;
		this.compatBullet = null;
		this.damage = damage;
		
	}
	
	public AbstractBulletItem(float damage, TagKey<Item> compatBullet)
	{
		super(new Properties());
		this.compatBullet = compatBullet;
		this.damage = damage;
	}
	
	//Ammo stack limit...
	public AbstractBulletItem(AbstractModItem casing, AbstractModItem tip, int maxSize, float damage)
	{
		super(new Properties().stacksTo(maxSize));
		this.bulletCasing = casing;
		this.bulletTip = tip;
		this.compatBullet = null;
		this.damage = damage;
	}
	
	//Ammo stack limit with bullet share tag
	public AbstractBulletItem(AbstractModItem casing, AbstractModItem tip, int maxSize, float damage, TagKey<Item> compatBullet)
	{
		super(new Properties().stacksTo(maxSize));
		this.bulletCasing = casing;
		this.bulletTip = tip;
		this.compatBullet = compatBullet;
		this.damage = damage;
	}
	
	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) 
	{
	      return 0.0F;
	}
	
	public Item returnCasingItem()
	{
		return bulletCasing;
	}
	
	public Item returnTipItem()
	{
		return bulletTip;
	}
	
	public TagKey<Item> getCompatBullet()
	{
		if (compatBullet != null)
			return compatBullet;
		else
			return null;
	}

	public float returnBaseDamage()
	{
		return damage;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		tooltip.add(ModUtils.displayString("Damage: " + TMWUtils.infoScaleBar(Math.round((damage * 100.0f) / 100.0f), 15)));
	}

}
