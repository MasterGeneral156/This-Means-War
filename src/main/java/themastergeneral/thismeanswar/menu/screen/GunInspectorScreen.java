package themastergeneral.thismeanswar.menu.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.themastergeneral.ctdcore.helpers.ModUtils;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.TMWUtils;
import themastergeneral.thismeanswar.menu.CrusherMenu;
import themastergeneral.thismeanswar.menu.FirearmInspectorMenu;

public class GunInspectorScreen extends AbstractContainerScreen<FirearmInspectorMenu> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(TMWMain.MODID, "textures/gui/inspector.png");
	
	public GunInspectorScreen(FirearmInspectorMenu menu, Inventory p_97742_, Component p_97743_) 
	{
		super(menu, p_97742_, p_97743_);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float p_97788_, int mouseX, int mouseY) {
		renderTooltip(guiGraphics, mouseX, mouseY);
		renderBackground(guiGraphics);
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);
	}
	
	@Override
	protected void renderLabels(GuiGraphics guigfx, int x, int y) {
        super.renderLabels(guigfx, x, y);
        ItemStack menuStack = menu.getSlotStack();
        int height = 18;
        if (menu.isGunItem(menuStack))
		{
        	if (menu.getGunDamage(menuStack) > 0F)
        	{
        		guigfx.drawString(this.font, ModUtils.displayTranslation("thismeanswar.container.firearm_inspector.damage").getString() + ": " + TMWUtils.makeFloatReadable(menu.getGunDamage(menuStack)), 34, height, 4210752, false); // +12 down +16 spacing
        		height = height + 12;
        	}
        	if (menu.getGunSpread(menuStack) > 0F)
        	{
        		guigfx.drawString(this.font, ModUtils.displayTranslation("thismeanswar.container.firearm_inspector.spread").getString() + ": " + menu.getGunSpread(menuStack), 34, height, 4210752, false); // +12 down +16 spacing
        		height = height + 12;
        	}
        	if (menu.getGunSpeed(menuStack) > 0F)
        	{
        		guigfx.drawString(this.font, ModUtils.displayTranslation("thismeanswar.container.firearm_inspector.velocity").getString() + ": " + TMWUtils.makeFloatReadable(menu.getGunSpeed(menuStack)), 34, height, 4210752, false); // +12 down +16 spacing
        		height = height + 12;
        	}
		}
        if (menu.isMagItem(menuStack))
		{
        	if ((menu.getMagRoundItem(menuStack) != Items.AIR))
        	{
        		guigfx.drawString(this.font, ModUtils.displayTranslation(menu.getMagRoundItem(menuStack).getDescriptionId()), 34, height, 4210752, false); // +12 down +16 spacing
        		height = height + 12;
        	}
        	if ((menu.getMagAmmo(menuStack) > -1) && (menu.getMagMaxAmmo(menuStack) > -1))
        	{
        		guigfx.drawString(this.font, ModUtils.displayTranslation("thismeanswar.container.firearm_inspector.capacity").getString() + ": " + menu.getMagAmmo(menuStack)+ " / " + menu.getMagMaxAmmo(menuStack), 34, height, 4210752, false); // +12 down +16 spacing
        		height = height + 12;
        	}
        	if (menu.getMagCapUpgrades(menuStack) > -1)
        	{
        		guigfx.drawString(this.font, ModUtils.displayTranslation("thismeanswar.container.firearm_inspector.cap_upgrades").getString() + ": " + menu.getMagCapUpgrades(menuStack), 34, height, 4210752, false); // +12 down +16 spacing
        		height = height + 12;
        	}
		}
    }

}
