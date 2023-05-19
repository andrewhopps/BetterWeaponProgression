package io.github.hopsickle.betterweaponprogression.common.item;

import io.github.hopsickle.betterweaponprogression.BWPMod;
import io.github.hopsickle.betterweaponprogression.init.BWPModItemInit;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BWPWoodenSword extends BWPSwordItem{	
 
public BWPWoodenSword(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
		super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
		// TODO Auto-generated constructor stub
	}
        
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BWPMod.MOD_ID);
    
	public static final RegistryObject<SwordItem> BWPWOODENSWORD = BWPModItemInit.ITEMS.register("bwpmod_wooden_sword", 
			() -> new BWPWoodenSword(Tiers.WOOD, 4, -2.4f, new Item.Properties().tab(BWPMod.BWPMod_TAB).durability(1)));	
	
	@Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.item.bwpmod.bwpmod_wooden_sword.tooltip.shift"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.item.bwpmod.bwpmod_wooden_sword.tooltip"));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

}
