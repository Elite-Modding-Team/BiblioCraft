package jds.bibliocraft.helpers;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class InventorySet 
{
	public String inventoryName;
	public int tileX;
	public int tileY;
	public int tileZ;
	public ArrayList inventoryList;
	
	public InventorySet(String name, int x, int y, int z, ArrayList<ItemStack> inventory)
	{
		this.inventoryName = name;
		this.tileX = x;
		this.tileY = y;
		this.tileZ = z;
		this.inventoryList = inventory;
	}
	

}
