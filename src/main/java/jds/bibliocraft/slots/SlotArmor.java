package jds.bibliocraft.slots;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class SlotArmor extends Slot
{
	public SlotArmor(IInventory inventory, int index, int xPosition, int yPosition)
	{
		super(inventory, index, xPosition, yPosition);
	}

	@Override
	public int getSlotStackLimit()
	{
		return 1;
	}

	@Override
	public boolean canTakeStack(EntityPlayer player)
	{
		ItemStack itemstack = this.getStack();
		return (itemstack.isEmpty() || player.isCreative() || !EnchantmentHelper.hasBindingCurse(itemstack)) && super.canTakeStack(player);
	}
}
