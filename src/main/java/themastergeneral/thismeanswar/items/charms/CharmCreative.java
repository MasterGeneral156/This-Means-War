package themastergeneral.thismeanswar.items.charms;

import com.themastergeneral.ctdcore.helpers.ModUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import themastergeneral.thismeanswar.block.BlockAmmoStorage;
import themastergeneral.thismeanswar.block.BlockMedicBox;
import themastergeneral.thismeanswar.block.entity.BlockEntityAmmoStorage;
import themastergeneral.thismeanswar.block.entity.BlockEntityMedicBox;
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.items.BasicItem;
import themastergeneral.thismeanswar.items.interfaces.AbstractGunItem;
import themastergeneral.thismeanswar.items.interfaces.AbstractMagazineItem;

public class CharmCreative extends BasicItem 
{

	@Override
	public InteractionResult useOn(UseOnContext context) 
	{
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState state = level.getBlockState(pos);
		Block block = state.getBlock();
		//Max fill ammo block if its got ammo in it
		if (block instanceof BlockAmmoStorage)
		{
			BlockEntityAmmoStorage ammoBlockEntity = (BlockEntityAmmoStorage) level.getBlockEntity(pos);
			if (ammoBlockEntity.getAmmoItem() != null)
			{
				ammoBlockEntity.applyCreativeCharm();
				context.getPlayer().displayClientMessage(ModUtils.displayTranslation("thismeanswar.creative_charm_applied_ammo"), true);
				context.getPlayer().getCooldowns().addCooldown(this, 100);
				return InteractionResult.PASS;
			}
			else
			{
				context.getPlayer().displayClientMessage(ModUtils.displayTranslation("thismeanswar.creative_charm_failed_ammo"), true);
				return InteractionResult.FAIL;
			}
		}
		else if (block instanceof BlockMedicBox)
		{
			BlockEntityMedicBox medicBoxEntity = (BlockEntityMedicBox) level.getBlockEntity(pos);
			medicBoxEntity.applyCreativeCharm();
			context.getPlayer().displayClientMessage(ModUtils.displayTranslation("thismeanswar.creative_charm_applied_medic"), true);
			context.getPlayer().getCooldowns().addCooldown(this, 100);
			return InteractionResult.PASS;
		}
		else
		{
			context.getPlayer().displayClientMessage(ModUtils.displayTranslation("thismeanswar.creative_charm_failed"), true);
			return InteractionResult.FAIL;
		}
	   }
}
