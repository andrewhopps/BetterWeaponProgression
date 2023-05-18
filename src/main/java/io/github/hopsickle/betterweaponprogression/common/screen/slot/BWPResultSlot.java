package io.github.hopsickle.betterweaponprogression.common.screen.slot;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class BWPResultSlot extends SlotItemHandler{

	public BWPResultSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
		// TODO Auto-generated constructor stub
	}
			//Prevents items from being place in result slot
	@Override
	public boolean mayPlace(ItemStack stack) {
		return false;
	}

	
}
