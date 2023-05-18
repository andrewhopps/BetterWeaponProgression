package io.github.hopsickle.betterweaponprogression.init;

import io.github.hopsickle.betterweaponprogression.BWPMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BWPModItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BWPMod.MOD_ID);
		
	public static final RegistryObject<Item> BWP_BLAZE_DROP = ITEMS.register("bwpmod_blaze_drop", 
			() -> new Item(new Item.Properties().tab(BWPMod.BWPMod_TAB).stacksTo(16)));
	
	public static final RegistryObject<Item> BWP_COW_DROP = ITEMS.register("bwpmod_cow_drop", 
			() -> new Item(new Item.Properties().tab(BWPMod.BWPMod_TAB).stacksTo(16)));
	
	public static final RegistryObject<Item> BWP_CREEPER_DROP = ITEMS.register("bwpmod_creeper_drop", 
			() -> new Item(new Item.Properties().tab(BWPMod.BWPMod_TAB).stacksTo(16)));
	
	public static final RegistryObject<Item> BWP_ELDERGUARDIAN_DROP = ITEMS.register("bwpmod_elderguardian_drop", 
			() -> new Item(new Item.Properties().tab(BWPMod.BWPMod_TAB).stacksTo(16)));
	
	public static final RegistryObject<Item> BWP_ENDDRAGON_DROP = ITEMS.register("bwpmod_enddragon_drop", 
			() -> new Item(new Item.Properties().tab(BWPMod.BWPMod_TAB).stacksTo(16)));
	
	public static final RegistryObject<Item> BWP_ENDERMAN_DROP = ITEMS.register("bwpmod_enderman_drop", 
			() -> new Item(new Item.Properties().tab(BWPMod.BWPMod_TAB).stacksTo(16)));
		
	public static final RegistryObject<Item> BWP_PHANTOM_DROP = ITEMS.register("bwpmod_phantom_drop", 
			() -> new Item(new Item.Properties().tab(BWPMod.BWPMod_TAB).stacksTo(16)));
		
	public static final RegistryObject<Item> BWP_PIG_DROP = ITEMS.register("bwpmod_pig_drop", 
			() -> new Item(new Item.Properties().tab(BWPMod.BWPMod_TAB).stacksTo(16)));
	
	public static final RegistryObject<Item> BWP_PIGLIN_DROP = ITEMS.register("bwpmod_piglin_drop", 
			() -> new Item(new Item.Properties().tab(BWPMod.BWPMod_TAB).stacksTo(16)));
	
	public static final RegistryObject<Item> BWP_RAVAGER_DROP = ITEMS.register("bwpmod_ravager_drop", 
			() -> new Item(new Item.Properties().tab(BWPMod.BWPMod_TAB).stacksTo(16)));
			
	public static final RegistryObject<Item> BWP_SKELETON_DROP = ITEMS.register("bwpmod_skeleton_drop", 
			() -> new Item(new Item.Properties().tab(BWPMod.BWPMod_TAB).stacksTo(16)));
		
	public static final RegistryObject<Item> BWP_SPIDER_DROP = ITEMS.register("bwpmod_spider_drop", 
			() -> new Item(new Item.Properties().tab(BWPMod.BWPMod_TAB).stacksTo(16)));
	
	public static final RegistryObject<Item> BWP_WITCH_DROP = ITEMS.register("bwpmod_witch_drop", 
			() -> new Item(new Item.Properties().tab(BWPMod.BWPMod_TAB).stacksTo(16)));
	
	public static final RegistryObject<Item> BWP_WITHERSKELETON_DROP = ITEMS.register("bwpmod_witherskeleton_drop", 
			() -> new Item(new Item.Properties().tab(BWPMod.BWPMod_TAB).stacksTo(16)));
	
	public static final RegistryObject<Item> BWP_ZOMBIE_DROP = ITEMS.register("bwpmod_zombie_drop", 
			() -> new Item(new Item.Properties().tab(BWPMod.BWPMod_TAB).stacksTo(16)));
	
	public static final RegistryObject<Item> BWP_TIER1_REAGENT = ITEMS.register("bwpmod_tier1_reagent", 
			() -> new Item(new Item.Properties().tab(BWPMod.BWPMod_TAB).stacksTo(16)));
	 
	public static final RegistryObject<Item> BWP_TIER2_REAGENT = ITEMS.register("bwpmod_tier2_reagent", 
			() -> new Item(new Item.Properties().tab(BWPMod.BWPMod_TAB).stacksTo(16)));

	public static final RegistryObject<Item> BWP_TIER3_REAGENT = ITEMS.register("bwpmod_tier3_reagent", 
			() -> new Item(new Item.Properties().tab(BWPMod.BWPMod_TAB).stacksTo(16)));
	
	public static final RegistryObject<Item> BWP_TIER4_REAGENT = ITEMS.register("bwpmod_tier4_reagent", 
			() -> new Item(new Item.Properties().tab(BWPMod.BWPMod_TAB).stacksTo(16)));
	
	public static final RegistryObject<Item> BWP_TIER5_REAGENT = ITEMS.register("bwpmod_tier5_reagent", 
			() -> new Item(new Item.Properties().tab(BWPMod.BWPMod_TAB).stacksTo(16)));
	
}
