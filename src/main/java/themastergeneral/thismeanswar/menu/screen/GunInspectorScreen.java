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
import themastergeneral.thismeanswar.config.Constants;
import themastergeneral.thismeanswar.menu.CrusherMenu;
import themastergeneral.thismeanswar.menu.FirearmInspectorMenu;

public class GunInspectorScreen extends AbstractContainerScreen<FirearmInspectorMenu> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(TMWMain.MODID, "textures/gui/inspector.png");
	int timer = 0;
	int maxTime = 1000;
	public GunInspectorScreen(FirearmInspectorMenu menu, Inventory p_97742_, Component p_97743_) 
	{
		super(menu, p_97742_, p_97743_);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float p_97788_, int mouseX, int mouseY) {
		renderBackground(guiGraphics);
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);
        renderTooltip(guiGraphics, mouseX, mouseY);
	}
	
	@Override
	public void containerTick()
	{
		super.containerTick();
		timer++;
		if (timer >= maxTime * 2)
			timer = 0;
	}
	
	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		super.renderLabels(guiGraphics, mouseX, mouseY);
		ItemStack menuStack = menu.getSlotStack();
		int yPosition = 18; // Initial Y position for label placement

		if (menu.isGunItem(menuStack)) {
				displayGunInfo(guiGraphics, menuStack, yPosition);
		} else if (menu.isMagItem(menuStack)) {
			displayMagInfo(guiGraphics, menuStack, yPosition);
		}
	}

	private void displayGunInfo(GuiGraphics guiGraphics, ItemStack menuStack, int yPosition) {
		if (menu.getGunDamage(menuStack) > -1F) {
			guiGraphics.drawString(this.font, ModUtils.displayString("Damage " + TMWUtils.guiScaleBar(menu.getGunDamage(menuStack), 30, 42)), 34, yPosition, 4210752, false);
			yPosition += 10;
		}
		if (menu.getGunSpread(menuStack) > -1F) {
			guiGraphics.drawString(this.font, ModUtils.displayString("Spread " + TMWUtils.guiScaleBar(menu.getGunSpread(menuStack), 5, 42)), 34, yPosition, 4210752, false);
			yPosition += 10;
		}
		if (menu.getGunSpeed(menuStack) > -1F) {
			guiGraphics.drawString(this.font, ModUtils.displayString("Speed " + TMWUtils.guiScaleBar(menu.getGunSpeed(menuStack), 12, 45)), 34, yPosition, 4210752, false);
			yPosition += 10;
		}
		if (menu.getGunReload(menuStack) > -1) {
			/*String rofString = (menu.getGunROF(menuStack) == Constants.fireRateAuto) ? "thismeanswar.firearm_rof_full" : "thismeanswar.firearm_rof_semi";
			guiGraphics.drawString(this.font, ModUtils.displayTranslation("thismeanswar.container.firearm_inspector.rof").getString() + ": " + ModUtils.displayTranslation(rofString).getString(), 34, yPosition, 4210752, false);
			*/
			guiGraphics.drawString(this.font, ModUtils.displayString("Reload " + TMWUtils.guiScaleBar(menu.getGunReload(menuStack), 100, 44)), 34, yPosition, 4210752, false);
			yPosition += 10;
		}
	}

	private void displayMagInfo(GuiGraphics guiGraphics, ItemStack menuStack, int yPosition) {
		if (menu.getMagRoundItem(menuStack) != Items.AIR) {
			guiGraphics.drawString(this.font, ModUtils.displayTranslation(menu.getMagRoundItem(menuStack).getDescriptionId()), 34, yPosition, 4210752, false);
			yPosition += 10;
		}
		if (menu.getMagAmmo(menuStack) > -1 && menu.getMagMaxAmmo(menuStack) > -1) {
			guiGraphics.drawString(this.font, ModUtils.displayTranslation("thismeanswar.container.firearm_inspector.capacity").getString() + ": " + menu.getMagAmmo(menuStack) + " / " + menu.getMagMaxAmmo(menuStack), 34, yPosition, 4210752, false);
			yPosition += 10;
		}
		if (menu.getMagCapUpgrades(menuStack) > -1) {
			guiGraphics.drawString(this.font, ModUtils.displayTranslation("thismeanswar.container.firearm_inspector.cap_upgrades").getString() + ": " + menu.getMagCapUpgrades(menuStack), 34, yPosition, 4210752, false);
			yPosition += 10;
		}
	}

}
