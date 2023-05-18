package io.github.hopsickle.betterweaponprogression.common.item;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import io.github.hopsickle.betterweaponprogression.BWPMod;
import io.github.hopsickle.betterweaponprogression.init.BWPModItemInit;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BWPNetheriteSword extends BWPSwordItem{
	
    public BWPNetheriteSword(Tier tier, int adjustedDmg, float adjustedSpeed, Properties properties) {
		super(tier, adjustedDmg, adjustedSpeed, properties);
		// TODO Auto-generated constructor stub
	}
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BWPMod.MOD_ID);
        
	public static final RegistryObject<SwordItem> BWPNETHERITESWORD = BWPModItemInit.ITEMS.register("bwpmod_netherite_sword", 
			() -> new BWPNetheriteSword(Tiers.NETHERITE, 4, -2.4f, new Item.Properties().tab(BWPMod.BWPMod_TAB).durability(2000)));
        	
	@Override
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()) {
            pTooltipComponents.add(new TranslatableComponent("tooltip.item.bwpmod.bwpmod_netherite_sword.tooltip.shift"));
        } else {
            pTooltipComponents.add(new TranslatableComponent("tooltip.item.bwpmod.bwpmod_netherite_sword.tooltip"));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
	
}
