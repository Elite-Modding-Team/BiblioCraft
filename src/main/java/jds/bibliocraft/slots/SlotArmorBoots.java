package jds.bibliocraft.slots;

import net.minecraft.entity.EntityLiving;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import jds.bibliocraft.containers.ContainerArmor;

public class SlotArmorBoots extends SlotArmor
{
	final ContainerArmor armorStand;

	public SlotArmorBoots(ContainerArmor armorContainer, IInventory iInventory, int i, int j, int k)
	{
		super(iInventory, i, j, k);
		this.armorStand = armorContainer;
	}

	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return EntityLiving.getSlotForItemStack(stack) == EntityEquipmentSlot.FEET;
	}
}
