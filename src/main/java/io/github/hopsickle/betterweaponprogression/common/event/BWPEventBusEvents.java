package io.github.hopsickle.betterweaponprogression.common.event;

import io.github.hopsickle.betterweaponprogression.BWPMod;
import io.github.hopsickle.betterweaponprogression.common.recipe.BWPImbuingForgeRecipe;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = BWPMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BWPEventBusEvents {

	
	@SubscribeEvent
	public static void registerRecipeTypes(final RegisterEvent event) {

		// Recipes
		event.register(ForgeRegistries.Keys.RECIPE_TYPES, helper -> {
			helper.register(new ResourceLocation(BWPMod.MOD_ID, BWPImbuingForgeRecipe.Type.ID),
					BWPImbuingForgeRecipe.Type.INSTANCE);
		});

	}
	
}
