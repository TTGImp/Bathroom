package immp.bathroommod;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import immp.bathroommod.block.BathBlock;
import immp.bathroommod.block.ToiletBlock;
import immp.bathroommod.entity.ToiletEntity;
import immp.bathroommod.init.ModItemGroups;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;

@EventBusSubscriber(modid = BathroomMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {
	private static final Logger LOGGER = LogManager.getLogger(BathroomMod.MODID + " Mod Event Subscriber");

	@SubscribeEvent
	public static void onRegisterBlocks(final RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(
				setup(new ToiletBlock(),"toilet")
						);
		LOGGER.debug("Registered Blocks");
	}
	
	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		
		
		for (final Block block : ForgeRegistries.BLOCKS.getValues()) {
			final ResourceLocation blockRegistryName = block.getRegistryName();
			
			Preconditions.checkNotNull(blockRegistryName, "Registry Name of Block \"" + block + "\" of class \"" + block.getClass().getName() + "\"is null! This is not allowed!");
			
			if (!blockRegistryName.getNamespace().equals(BathroomMod.MODID)) {
				continue;
			}

			final Item.Properties properties = new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP);
			final BlockItem blockItem = new BlockItem(block, properties);
			registry.register(setup(blockItem, blockRegistryName));
		}
	}
	
	
	@Nonnull
	public static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final String name) {
		Preconditions.checkNotNull(name, "Name to assign to entry cannot be null!");
		return setup(entry, new ResourceLocation(BathroomMod.MODID, name));
	}

	@Nonnull
	private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final ResourceLocation registryName) {
		Preconditions.checkNotNull(entry, "Entry cannot be null!");
		Preconditions.checkNotNull(registryName, "Registry name to assign to entry cannot be null!");
		entry.setRegistryName(registryName);
		return entry;
	}
}

