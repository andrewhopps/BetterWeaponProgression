package io.github.hopsickle.betterweaponprogression.common.block.entity.BWPBlockEntities;

import io.github.hopsickle.betterweaponprogression.common.block.entity.BWPModBlockEntities;
import io.github.hopsickle.betterweaponprogression.common.recipe.BWPImbuingForgeRecipe;
import io.github.hopsickle.betterweaponprogression.common.screen.BWPImbuingForgeMenu;
import io.github.hopsickle.betterweaponprogression.init.BWPModItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Optional;

public class BWPImbuingForgeBlockEntity extends BlockEntity implements MenuProvider{
	private final ItemStackHandler itemHandler = new ItemStackHandler(5) {
		@Override
		protected void onContentsChanged(int slot) {
			setChanged();
		}
	};
	
	private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

	protected final ContainerData data;
	private int progress = 0;
	private int maxProgress = 72;
	
	 public BWPImbuingForgeBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
	        super(BWPModBlockEntities.BWPIMBUINGFORGEBLOCKENTITY.get(), pWorldPosition, pBlockState);
	        this.data = new ContainerData() {
	            public int get(int index) {
	                switch (index) {
	                    case 0: return BWPImbuingForgeBlockEntity.this.progress;
	                    case 1: return BWPImbuingForgeBlockEntity.this.maxProgress;
	                    default: return 0;
	                }
	            }

	            public void set(int index, int value) {
	                switch(index) {
	                    case 0: BWPImbuingForgeBlockEntity.this.progress = value; break;
	                    case 1: BWPImbuingForgeBlockEntity.this.maxProgress = value; break;
	                }
	            }

	            public int getCount() {
	                return 2;
	            }
	        };
	    }

	@Override
	public Component getDisplayName() {
		// TODO Auto-generated method stub
		return Component.literal("Imbuing Forge");
	}
	
	@Nonnull
	@Override
	public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
		// TODO Auto-generated method stub
		return new BWPImbuingForgeMenu(pContainerId, pPlayerInventory, this, this.data);
	}
	
	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side){
		if(cap == ForgeCapabilities.ITEM_HANDLER) {
			return lazyItemHandler.cast();
		}
		
		return super.getCapability(cap, side);
	}

	@Override
	public void onLoad() {
		super.onLoad();
		lazyItemHandler = LazyOptional.of(() -> itemHandler);
	}
	
	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		lazyItemHandler.invalidate();
	}
	
	@Override
	protected void saveAdditional(@NotNull CompoundTag tag) {
		tag.put("inventory", itemHandler.serializeNBT());
		tag.putInt("bwp_imbuing_forge.progress", progress);
		super.saveAdditional(tag);
	}
	
	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		itemHandler.deserializeNBT(nbt.getCompound("inventory"));
		progress = nbt.getInt("bwp_imbuing_forge.progress");
	}
	
	public void drops() {
		SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
		for(int i = 0; i < itemHandler.getSlots(); i++) {
			inventory.setItem(1, itemHandler.getStackInSlot(1));
		}
		
		Containers.dropContents(this.level, this.worldPosition, inventory);
	}

	public static void tick(Level pLevel, BlockPos pPos, BlockState pState, BWPImbuingForgeBlockEntity pBlockEntity) {
        if(hasRecipe(pBlockEntity)) {
            pBlockEntity.progress++;
            setChanged(pLevel, pPos, pState);
            if(pBlockEntity.progress > pBlockEntity.maxProgress) {
                craftItem(pBlockEntity);
            }
        } else {
            pBlockEntity.resetProgress();
            setChanged(pLevel, pPos, pState);
        }
    }

    private static boolean hasRecipe(BWPImbuingForgeBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<BWPImbuingForgeRecipe> match = level.getRecipeManager()
                .getRecipeFor(BWPImbuingForgeRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem())
                && hasWaterInWaterSlot(entity) && hasToolsInToolSlot(entity);
    }

    /*  Remove these two methods and their conditions from hasRecipe right above.   */
    
    private static boolean hasWaterInWaterSlot(BWPImbuingForgeBlockEntity entity) {
        return PotionUtils.getPotion(entity.itemHandler.getStackInSlot(0)) == Potions.WATER;
    }

    private static boolean hasToolsInToolSlot(BWPImbuingForgeBlockEntity entity) {
        return entity.itemHandler.getStackInSlot(2).getItem() == BWPModItemInit.BWP_TIER1_REAGENT.get();
    }

    private static void craftItem(BWPImbuingForgeBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<BWPImbuingForgeRecipe> match = level.getRecipeManager()
                .getRecipeFor(BWPImbuingForgeRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            entity.itemHandler.extractItem(0,1, false);
            entity.itemHandler.extractItem(1,1, false);
			entity.itemHandler.getStackInSlot(2).hurt(1, RandomSource.create(), null);

            entity.itemHandler.setStackInSlot(3, new ItemStack(match.get().getResultItem().getItem(),
                    entity.itemHandler.getStackInSlot(3).getCount() + 1));

            entity.resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(3).getItem() == output.getItem() || inventory.getItem(3).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(3).getMaxStackSize() > inventory.getItem(3).getCount();
    }

	
/*
 * 					Crafting Slot Layout
 * 				---------------------------
	 				Slot: 0 	SwordSlot
	 				Slot: 1 	ReagentSlot
	 				Slot: 2 	MaterialSlot
	 				Slot: 3 	FuelSlot
	 				Slot: 4		ResultSlot


	    private static void craftItem(BWPImbuingForgeBlockEntity entity) {
	    	System.out.println("Trying to Craft");
	        entity.itemHandler.extractItem(1, 1, false);
	        entity.itemHandler.extractItem(2, 2, false);
	        entity.itemHandler.extractItem(3, 1, false);
//	        entity.itemHandler.getStackInSlot(2).hurt(1, new Random(), null);

	        if(entity.itemHandler.getStackInSlot(0).getItem() == BWPWoodenSword.BWPWOODENSWORD.get()) {
	        	System.out.println("Wooden Sword Detected");
	        	entity.itemHandler.extractItem(0, 1, false);
	        	entity.itemHandler.setStackInSlot(4, new ItemStack(BWPStoneSword.BWPSTONESWORD.get(),
    			entity.itemHandler.getStackInSlot(4).getCount() + 1)); }  
	        else if(entity.itemHandler.getStackInSlot(0).getItem() == BWPStoneSword.BWPSTONESWORD.get()) {
	        	System.out.println("Stone Sword Detected");
	        	entity.itemHandler.extractItem(0, 1, false);
	        	entity.itemHandler.setStackInSlot(4, new ItemStack(BWPIronSword.BWPIRONSWORD.get(),
    			entity.itemHandler.getStackInSlot(4).getCount() + 1)); }  
	        else if(entity.itemHandler.getStackInSlot(0).getItem() == BWPIronSword.BWPIRONSWORD.get()) {
	        	System.out.println("Iron Sword Detected");
	        	entity.itemHandler.extractItem(0, 1, false);
	        	entity.itemHandler.setStackInSlot(4, new ItemStack(BWPGoldenSword.BWPGOLDENSWORD.get(),
    			entity.itemHandler.getStackInSlot(4).getCount() + 1)); }  
	        else if(entity.itemHandler.getStackInSlot(0).getItem() == BWPGoldenSword.BWPGOLDENSWORD.get()) {
	        	System.out.println("Golden Sword Detected");
	        	entity.itemHandler.extractItem(0, 1, false);
	        	entity.itemHandler.setStackInSlot(4, new ItemStack(BWPDiamondSword.BWPDIAMONDSWORD.get(),
    			entity.itemHandler.getStackInSlot(4).getCount() + 1)); }  
	        else if(entity.itemHandler.getStackInSlot(0).getItem() == BWPDiamondSword.BWPDIAMONDSWORD.get()) {
	        	System.out.println("Diamond Sword Detected");
	        	entity.itemHandler.extractItem(0, 1, false);
	        	entity.itemHandler.setStackInSlot(4, new ItemStack(BWPNetheriteSword.BWPNETHERITESWORD.get(),
    			entity.itemHandler.getStackInSlot(4).getCount() + 1)); }   
	    }

	    private static boolean hasRecipe(BWPImbuingForgeBlockEntity entity) {
	        boolean hasItemInWaterSlot = entity.itemHandler.getStackInSlot(0).getItem() == BWPWoodenSword.BWPWOODENSWORD.get();
	        boolean hasItemInFirstSlot = entity.itemHandler.getStackInSlot(1).getItem() == BWPModItemInit.BWP_TIER1_REAGENT.get();
	        boolean hasItemInSecondSlot = entity.itemHandler.getStackInSlot(2).getItem() == Items.STONE;
	        boolean hasItemInThirdSlot = entity.itemHandler.getStackInSlot(3).getItem() == Items.COAL;

	        return hasItemInWaterSlot && hasItemInFirstSlot && hasItemInSecondSlot && hasItemInThirdSlot;
	    }
	    
*/



	
	
}
