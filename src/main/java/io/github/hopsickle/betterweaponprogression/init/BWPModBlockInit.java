package io.github.hopsickle.betterweaponprogression.init;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.google.common.base.Supplier;

import io.github.hopsickle.betterweaponprogression.BWPMod;
import io.github.hopsickle.betterweaponprogression.common.block.BWPImbuingForgeBlock;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BWPModBlockInit {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BWPMod.MOD_ID);
		
	public static final RegistryObject<Block> BWP_IMBUING_FORGE = registerBlock("bwpmod_imbuing_forge", 
			() -> new BWPImbuingForgeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()),
			BWPMod.BWPMod_TAB);
	
	@SuppressWarnings("unused")
	private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, 
			Supplier<T> block) {
		return BLOCKS.register(name, block);
	}
	
	@SuppressWarnings("unused")
	private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, 
																	CreativeModeTab tab, String tooltipKey) {
		RegistryObject<T> toReturn = BLOCKS.register(name, block);
		registerBlockItem(name, toReturn, tab, tooltipKey);
		return toReturn;
	}
	
	public static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
																			CreativeModeTab tab, String tooltipKey){
		return BWPModItemInit.ITEMS.register(name, () -> new BlockItem(block.get(),
				new Item.Properties().tab(tab)){
			@Override
			public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
				pTooltip.add(new TranslatableComponent(tooltipKey));
			}
		});
	}
	
	private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
		RegistryObject<T> toReturn = BLOCKS.register(name, block);
		registerBlockItem(name, toReturn, tab);
		return toReturn;
	}
	
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
            																CreativeModeTab tab) {
    		return BWPModItemInit.ITEMS.register(name, () -> new BlockItem(block.get(),
    				new Item.Properties().tab(tab)));
    }

	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
	}
}	
