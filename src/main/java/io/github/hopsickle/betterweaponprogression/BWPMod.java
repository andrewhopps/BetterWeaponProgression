package io.github.hopsickle.betterweaponprogression;

import io.github.hopsickle.betterweaponprogression.common.block.BWPImbuingForgeBlock;
import io.github.hopsickle.betterweaponprogression.common.block.entity.BWPModBlockEntities;
import io.github.hopsickle.betterweaponprogression.common.item.BWPDiamondSword;
import io.github.hopsickle.betterweaponprogression.common.item.BWPGoldenSword;
import io.github.hopsickle.betterweaponprogression.common.item.BWPIronSword;
import io.github.hopsickle.betterweaponprogression.common.item.BWPNetheriteSword;
import io.github.hopsickle.betterweaponprogression.common.item.BWPStoneSword;
import io.github.hopsickle.betterweaponprogression.common.item.BWPWoodenSword;
import io.github.hopsickle.betterweaponprogression.common.recipe.BWPRecipes;
import io.github.hopsickle.betterweaponprogression.common.screen.BWPImbuingForgeScreen;
import io.github.hopsickle.betterweaponprogression.common.screen.BWPMenuTypes;
import io.github.hopsickle.betterweaponprogression.init.BWPModBlockInit;
import io.github.hopsickle.betterweaponprogression.init.BWPModItemInit;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("bwpmod")
public class BWPMod {
	
	public static final String MOD_ID = "bwpmod";
	
	public BWPMod() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		BWPWoodenSword.ITEMS.register(bus);
		BWPStoneSword.ITEMS.register(bus);
		BWPIronSword.ITEMS.register(bus);
		BWPGoldenSword.ITEMS.register(bus);
		BWPDiamondSword.ITEMS.register(bus);
		BWPNetheriteSword.ITEMS.register(bus);
		BWPModItemInit.ITEMS.register(bus);
		BWPModBlockInit.BLOCKS.register(bus);
		BWPModBlockEntities.BLOCK_ENTITIES.register(bus);
		BWPMenuTypes.MENUS.register(bus);
		BWPRecipes.register(bus);
		
		bus.addListener(this::clientSetup);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	public static final CreativeModeTab BWPMod_TAB = new CreativeModeTab(MOD_ID) {
		
		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack makeIcon() {
			// TODO Auto-generated method stub
			return new ItemStack(BWPNetheriteSword.BWPNETHERITESWORD.get());
		}
	};
	
	private void clientSetup(final FMLClientSetupEvent event) {
		ItemBlockRenderTypes.setRenderLayer(BWPModBlockInit.BWP_IMBUING_FORGE.get(), RenderType.translucent());
		
		MenuScreens.register(BWPMenuTypes.BWP_IMBUING_FORGE_MENU.get(), BWPImbuingForgeScreen::new);
	}
	
}
