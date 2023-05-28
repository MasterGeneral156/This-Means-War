package themastergeneral.thismeanswar.block;

import com.themastergeneral.ctdcore.helpers.EntityHelper;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import themastergeneral.thismeanswar.items.TMWItems;

public class RedTeamBlock extends BlockStone 
{

	@Override
	public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) 
	{
		if (entity instanceof Player)
		{
			Player player = (Player) entity;
			if (EntityHelper.getPlayerLegs(player).getItem() == TMWItems.red_war_armor_legs.asItem());
				player.addEffect(new MobEffectInstance(MobEffects.JUMP, 20, 0, true, false));
			if (EntityHelper.getPlayerBoots(player).getItem() == TMWItems.red_war_armor_boots.asItem())
				player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, 0, true, false));
			if (EntityHelper.getPlayerChest(player).getItem() == TMWItems.red_war_armor_chest.asItem())
				player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20, 0, true, false));
			if (EntityHelper.getPlayerHelmet(player).getItem() == TMWItems.red_war_armor_helm.asItem())
				player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 20, 0, true, false));
		}
	}
}
