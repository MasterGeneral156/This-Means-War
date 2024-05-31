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
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.menu.AlloySmelterMenu;

public class AlloySmelterScreen extends AbstractContainerScreen<AlloySmelterMenu> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(TMWMain.MODID, "textures/gui/alloy_smelter.png");
	
	public AlloySmelterScreen(AlloySmelterMenu menu, Inventory p_97742_, Component p_97743_) 
	{
		super(menu, p_97742_, p_97743_);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float p_97788_, int p_97789_, int p_97790_) {
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
        double processTime = Math.round(((double) this.menu.getProcessTime() / this.menu.getMaxProcessTime()) * 100);
        int burnTime = this.menu.getBurnTime();
        
        guigfx.drawString(this.font, "Process: " + processTime + "%", 84, 20, 4210752, false);
        if (Screen.hasShiftDown())
        	guigfx.drawString(this.font, "Fuel: " + burnTime, 84, 32, 4210752, false);
        else
        	guigfx.drawString(this.font, "Fuel: " + ModUtils.returnShortenedNumber(burnTime), 84, 32, 4210752, false);
    }

}
