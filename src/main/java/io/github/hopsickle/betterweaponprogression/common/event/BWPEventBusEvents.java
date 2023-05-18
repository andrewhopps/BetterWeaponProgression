package io.github.hopsickle.betterweaponprogression.common.event;

import io.github.hopsickle.betterweaponprogression.BWPMod;
import io.github.hopsickle.betterweaponprogression.common.recipe.BWPImbuingForgeRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BWPMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BWPEventBusEvents {

	
	@SubscribeEvent
	public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
		Registry.register(Registry.RECIPE_TYPE, BWPImbuingForgeRecipe.Type.ID, BWPImbuingForgeRecipe.Type.INSTANCE);
	}
	
}
