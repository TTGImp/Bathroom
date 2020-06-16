package immp.bathroommod.init;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import immp.bathroommod.BathroomMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ModItemGroups {
	
	public static final ItemGroup MOD_ITEM_GROUP = new ModItemGroup(BathroomMod.MODID, () -> new ItemStack(Items.PAPER));
	
	public static class ModItemGroup extends ItemGroup{

		private final Supplier<ItemStack> iconSupplier;

		public ModItemGroup(@Nonnull final String name, @Nonnull final Supplier<ItemStack> iconSupplier) {
			super(name);
			this.iconSupplier = iconSupplier;
		}
		
		@Override
		public ItemStack createIcon() {
			return iconSupplier.get();
		}
	}
}
