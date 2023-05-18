package io.github.hopsickle.betterweaponprogression.common.recipe;

import io.github.hopsickle.betterweaponprogression.BWPMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BWPRecipes {
	public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
			DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, BWPMod.MOD_ID);
	
	public static final RegistryObject<RecipeSerializer<BWPImbuingForgeRecipe>> BWP_IMBUING_FORGE_SERIALIZER =
			SERIALIZERS.register("bwp_imbuing_forge", () -> BWPImbuingForgeRecipe.Serializer.INSTANCE);
	
	public static void register(IEventBus eventBus) {
		SERIALIZERS.register(eventBus);
	}
}
