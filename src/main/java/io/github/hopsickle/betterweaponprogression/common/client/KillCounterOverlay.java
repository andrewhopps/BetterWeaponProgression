package io.github.hopsickle.betterweaponprogression.common.client;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.hopsickle.betterweaponprogression.BWPMod;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class KillCounterOverlay {
    private static final ResourceLocation  EMPTY_HEART = new ResourceLocation(BWPMod.MOD_ID, "textures/hud/bleeding_heart_empty.png");
    private static final ResourceLocation  FULL_HEART = new ResourceLocation(BWPMod.MOD_ID, "textures/hud/bleeding_heart_full.png");

    public static final IGuiOverlay HUD_KILLS = ((gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        int x = screenWidth / 2;
        int y = screenHeight;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
        RenderSystem.setShaderTexture(0, EMPTY_HEART);
        for(int i =0; i < 1; i++){
            GuiComponent.blit(poseStack, x-16,y-53,0,0,32,24,32,24);
        }
    });
}
