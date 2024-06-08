package themastergeneral.thismeanswar.menu.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.themastergeneral.ctdcore.helpers.ModUtils;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.menu.CrusherMenu;
import themastergeneral.thismeanswar.menu.FactoryHolderMenu;

public class FactoryHolderScreen extends AbstractContainerScreen<FactoryHolderMenu> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(TMWMain.MODID, "textures/gui/factory_holder.png");
	
	public FactoryHolderScreen(FactoryHolderMenu menu, Inventory p_97742_, Component p_97743_) 
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
        guigfx.drawString(this.font, ModUtils.displayTranslation("thismeanswar.container.fh.desc.1"), 84, 20, 4210752, false);
        guigfx.drawString(this.font, ModUtils.displayTranslation("thismeanswar.container.fh.desc.2"), 84, 32, 4210752, false);
        guigfx.drawString(this.font, ModUtils.displayTranslation("thismeanswar.container.fh.desc.3"), 84, 44, 4210752, false);
    }

}
