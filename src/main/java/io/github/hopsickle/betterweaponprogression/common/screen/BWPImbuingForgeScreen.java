package io.github.hopsickle.betterweaponprogression.common.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import io.github.hopsickle.betterweaponprogression.BWPMod;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class BWPImbuingForgeScreen extends AbstractContainerScreen<BWPImbuingForgeMenu>{
	private static final ResourceLocation TEXTURE =
			new ResourceLocation(BWPMod.MOD_ID, "textures/gui/bwpmod_imbuing_forge_gui.png");

	public BWPImbuingForgeScreen(BWPImbuingForgeMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
		super(pMenu, pPlayerInventory, pTitle);
		// TODO Auto-generated constructor stub
	}

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = ((height - 300) / 2); // subtraction /hardcode 256px

        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);
        
        if(menu.isCrafting()) {
        	blit(pPoseStack, x + 182, y + 41, 176, 0, 8, menu.getScaledProgress());
        }
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }

}
