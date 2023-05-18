package io.github.hopsickle.betterweaponprogression.common.block.entity;

import io.github.hopsickle.betterweaponprogression.BWPMod;
import io.github.hopsickle.betterweaponprogression.common.block.entity.BWPBlockEntities.BWPImbuingForgeBlockEntity;
import io.github.hopsickle.betterweaponprogression.init.BWPModBlockInit;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BWPModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
			DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, BWPMod.MOD_ID);
	
	public static final RegistryObject<BlockEntityType<BWPImbuingForgeBlockEntity>> BWPIMBUINGFORGEBLOCKENTITY =
			BLOCK_ENTITIES.register("bwp_imbuing_forge_block_entity", () ->
					BlockEntityType.Builder.of(BWPImbuingForgeBlockEntity::new,
							BWPModBlockInit.BWP_IMBUING_FORGE.get()).build(null));
	
	public static void register(IEventBus eventBus) {
		BLOCK_ENTITIES.register(eventBus);
	}
}
