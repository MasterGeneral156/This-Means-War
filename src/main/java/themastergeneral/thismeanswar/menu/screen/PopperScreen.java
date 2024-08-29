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
import themastergeneral.thismeanswar.TMWUtils;
import themastergeneral.thismeanswar.menu.PopperMenu;
import themastergeneral.thismeanswar.menu.PressMenu;

public class PopperScreen extends AbstractContainerScreen<PopperMenu> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(TMWMain.MODID, "textures/gui/popper.png");
	
	public PopperScreen(PopperMenu menu, Inventory p_97742_, Component p_97743_) 
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
	protected void renderLabels(GuiGraphics guigfx, int x, int y) {
        super.renderLabels(guigfx, x, y);
        int errorCode = this.menu.getErrorCode();
        float processTime = this.menu.getProcessTime();
        int burnTime = this.menu.getBurnTime();
        int burnTimeTotal = this.menu.getTotalBurnTime();
        float maxProcessTime = this.menu.getMaxProcessTime();
        if (errorCode > 0)
        {
            guigfx.drawString(this.font, ModUtils.displayTranslation("thismeanswar.container.molder.error." + errorCode), 84, 20, 4210752, false);
        }
        else {
            guigfx.drawString(this.font, "Process", 83, 20, 4210752, false);
            guigfx.drawString(this.font, TMWUtils.guiScaleBar(processTime, maxProcessTime, 38), 83, 30, 4210752, false);
            guigfx.drawString(this.font, "Fuel", 83, 40, 4210752, false);
            guigfx.drawString(this.font, TMWUtils.guiScaleBar((float) burnTime, burnTimeTotal, 38), 83, 50, 4210752, false);
        }
    }

}
