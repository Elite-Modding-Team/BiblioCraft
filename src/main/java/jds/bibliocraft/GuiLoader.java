package jds.bibliocraft;

import jds.bibliocraft.containers.*;
import jds.bibliocraft.gui.*;
import jds.bibliocraft.items.ItemAtlas;
import jds.bibliocraft.items.ItemSlottedBook;
import jds.bibliocraft.tileentities.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

//@EventHandler
public class GuiLoader implements IGuiHandler
{
	@Override
	public Object getServerGuiElement (int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		if (id == 100)
		{
			ItemStack currentItem = player.inventory.getCurrentItem();
			if (currentItem != null && currentItem.getTranslationKey().equals(ItemAtlas.instance.getTranslationKey()))
			{
				return new ContainerAtlas(player.inventory, world);
			}
		}
		if (id == 101)
		{
			ItemStack currentItem = player.inventory.getCurrentItem();
			if (currentItem != null && currentItem.getTranslationKey().equals(ItemSlottedBook.instance.getTranslationKey()))
			{
				return new ContainerSlottedBook(player.inventory);
			}
		}
		if (id == 102)
		{
			return new ContainerNameTester(player.inventory);
		}
		if (tileEntity instanceof TileEntityBookcase)
		{
			return new ContainerBookcase(player.inventory, tileEntity);
		}
		
		if (tileEntity instanceof TileEntityArmorStand)
		{
			return new ContainerArmor(player.inventory, (TileEntityArmorStand) tileEntity);
		}
		
		if (tileEntity instanceof TileEntityPotionShelf)
		{
			return new ContainerPotionShelf(player.inventory, (TileEntityPotionShelf) tileEntity);
		}
		
		if (tileEntity instanceof TileEntityShelf)
		{
			return new ContainerGenericShelf(player.inventory, (TileEntityShelf) tileEntity);
		}
		
		if (tileEntity instanceof TileEntityToolRack)
		{
			return new ContainerWeaponRack(player.inventory, (TileEntityToolRack) tileEntity);
		}
		if (tileEntity instanceof TileEntityCase)
		{
			return new ContainerWeaponCase(player.inventory, (TileEntityCase) tileEntity);
		}
		if (tileEntity instanceof TileEntityLabel)
		{
			return new ContainerLabel (player.inventory, (TileEntityLabel) tileEntity);
		}
		if (tileEntity instanceof TileEntityDesk)
		{
			return new ContainerWritingDesk (player.inventory, (TileEntityDesk) tileEntity);
		}
		if (tileEntity instanceof TileEntityPrintPress)
		{
			//return new ContainerPrintPress (player.inventory, (TileEntityPrintPress) tileEntity);
		}
		if (tileEntity instanceof TileEntityTable)
		{
			return new ContainerTable(player.inventory, (TileEntityTable)tileEntity);
		}
		if (tileEntity instanceof TileEntityCookieJar)
		{
			return new ContainerCookieJar(player.inventory, (TileEntityCookieJar)tileEntity);
		}
		if (tileEntity instanceof TileEntityDinnerPlate)
		{
			return new ContainerDinnerPlate(player.inventory, (TileEntityDinnerPlate)tileEntity);
		}
		if (tileEntity instanceof TileEntityDiscRack)
		{
			return new ContainerDiscRack(player.inventory, (TileEntityDiscRack)tileEntity);
		}
		if (tileEntity instanceof TileEntityFancySign)
		{
			return new ContainerFancySign(player.inventory, (TileEntityFancySign)tileEntity);
		}
		if (tileEntity instanceof TileEntityFancyWorkbench)
		{
			TileEntityFancyWorkbench bench = (TileEntityFancyWorkbench)tileEntity;
			int lx = getXcoordForWorkbenchBookcase(true, bench.getAngle(), bench.getPos().getX());
			int lz = getZcoordForWorkbenchBookcase(true, bench.getAngle(), bench.getPos().getZ());
			int rx = getXcoordForWorkbenchBookcase(false, bench.getAngle(), bench.getPos().getX());
			int rz = getZcoordForWorkbenchBookcase(false, bench.getAngle(), bench.getPos().getZ());
			int lry = bench.getPos().getY();
			TileEntityBookcase leftBookcase = null;
			TileEntityBookcase rightBookcase = null;
			
			if (!(world.isAirBlock(new BlockPos(lx, lry, lz))))
			{
				TileEntity left = world.getTileEntity(new BlockPos(lx, lry, lz));
				if (left != null && left instanceof TileEntityBookcase)
				{
					leftBookcase = (TileEntityBookcase)left;
				}
			}
			if (!(world.isAirBlock(new BlockPos(rx, lry, rz))))
			{
				TileEntity right = world.getTileEntity(new BlockPos(rx, lry, rz));
				if (right != null && right instanceof TileEntityBookcase)
				{
					rightBookcase = (TileEntityBookcase)right;
				}
			}
			return new ContainerFancyWorkbench(player.inventory, world, bench, player.getEntityId(), leftBookcase, rightBookcase);
		}
		
		if (tileEntity instanceof TileEntityPaintPress)
		{
			return new ContainerPaintPress(player.inventory, (TileEntityPaintPress)tileEntity);
		}
		if (tileEntity instanceof TileEntityPainting)
		{
			return new ContainerPainting(player.inventory, (TileEntityPainting)tileEntity);
		}
		if (tileEntity instanceof TileEntityFurniturePaneler)
		{
			return new ContainerFurniturePaneler(player.inventory, (TileEntityFurniturePaneler)tileEntity);
		}
		
		if (tileEntity instanceof TileEntityFramedChest)
		{
			TileEntityFramedChest chest = (TileEntityFramedChest)tileEntity;
			TileEntityFramedChest chest2 = null;
			if (chest.getIsDouble())
			{
				int x2 = getXcoordForChest(chest.getIsLeft(), chest.getAngle(), chest.getPos().getX());
				int z2 = getZcoordForChest(chest.getIsLeft(), chest.getAngle(), chest.getPos().getZ());
				TileEntity tile2 = world.getTileEntity(new BlockPos(x2, chest.getPos().getY(), z2)	);
				if (tile2 != null && tile2 instanceof TileEntityFramedChest)
				{
					if (((TileEntityFramedChest)tile2).getIsDouble())
					{
						chest2 = (TileEntityFramedChest)tile2;
					}
				}
			}
			return new ContainerFramedChest(player.inventory, chest, chest2); 
		}
		
		return null;
	}
	
	private int getXcoordForChest(boolean isLeft, EnumFacing angle, int oldX)
	{
		if (isLeft)
		{
			switch (angle)
			{
				case SOUTH: return oldX;
				case WEST: return oldX-1;
				case NORTH: return oldX;
				case EAST: return oldX+1;
				default: break;
			}
		}
		else
		{
			switch (angle)
			{
				case SOUTH: return oldX;
				case WEST: return oldX+1;
				case NORTH: return oldX;
				case EAST: return oldX-1;
				default: break;
			}
		}
		return -1;
	}
	
	private int getZcoordForChest(boolean isLeft, EnumFacing angle, int oldZ)
	{
		if (isLeft)
		{
			switch (angle)
			{
				case SOUTH: return oldZ+1;
				case WEST: return oldZ;
				case NORTH: return oldZ-1;
				case EAST: return oldZ;
				default: break;
			}
		}
		else //isRight
		{
			switch (angle)
			{
				case SOUTH: return oldZ-1;
				case WEST: return oldZ;
				case NORTH: return oldZ+1;
				case EAST: return oldZ;
				default: break;
			}
		}
		return -1;
	}
	
	private int getXcoordForWorkbenchBookcase(boolean isLeft, EnumFacing angle, int oldX)
	{
		if (isLeft)
		{
			switch (angle)
			{
				case SOUTH: return oldX;
				case WEST: return oldX+1;
				case NORTH: return oldX;
				case EAST: return oldX-1;
				default: break;
			}
		}
		else //isRight
		{
			switch (angle)
			{
				case SOUTH: return oldX;
				case WEST: return oldX-1;
				case NORTH: return oldX;
				case EAST: return oldX+1;
				default: break;
			}
		}
		return -1;
	}
	
	private int getZcoordForWorkbenchBookcase(boolean isLeft, EnumFacing angle, int oldZ)
	{
		if (isLeft)
		{
			switch (angle)
			{
				case SOUTH: return oldZ-1;
				case WEST: return oldZ;
				case NORTH: return oldZ+1;
				case EAST: return oldZ;
				default: break;
			}
		}
		else //isRight
		{
			switch (angle)
			{
				case SOUTH: return oldZ+1;
				case WEST: return oldZ;
				case NORTH: return oldZ-1;
				case EAST: return oldZ;
				default: break;
			}
		}
		return -1;
	}
	
	//returns an instance of the Gui you made earlier
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int i, int j, int k)
	{
		TileEntity tileEntity = world.getTileEntity(new BlockPos(i, j, k));
		if (id == 100)
		{
			ItemStack currentItem = player.inventory.getCurrentItem();
			if (currentItem != null && currentItem.getTranslationKey().equals(ItemAtlas.instance.getTranslationKey()))
			{
				return new GuiAtlas(player.inventory, world, player);
			}
		}
		
		if (id == 101)
		{
			if (tileEntity instanceof TileEntityDesk)
			{
				//return new GuiSlottedBook(player.inventory, player.getHeldItem(EnumHand.MAIN_HAND), false, i, j, k);
			}
			else
			{
				ItemStack currentItem = player.inventory.getCurrentItem();
				if (currentItem != null && currentItem.getTranslationKey().equals(ItemSlottedBook.instance.getTranslationKey()))
				{
					return new GuiSlottedBook(player.inventory, player.getHeldItem(EnumHand.MAIN_HAND), true, 0, 0, 0);
				}
			}
		}
		if (id == 102)
		{
			return new GuiNameTester(player.inventory, player.getHeldItem(EnumHand.MAIN_HAND));
		}
		if (tileEntity instanceof TileEntityBookcase)
		{
			return new GuiBookcase(player.inventory,  tileEntity);
		}
		if (tileEntity instanceof TileEntityShelf)
		{
			return new GuiGenericShelf(player.inventory, (TileEntityShelf) tileEntity);
		}
		
		if (tileEntity instanceof TileEntityArmorStand)
		{
			return new GuiArmorStand(player.inventory,(TileEntityArmorStand) tileEntity);
		}
		if (tileEntity instanceof TileEntityPotionShelf)
		{
			return new GuiPotionShelf(player.inventory, (TileEntityPotionShelf) tileEntity);
		}

		if (tileEntity instanceof TileEntityToolRack)
		{
			return new GuiWeaponRack(player.inventory, (TileEntityToolRack) tileEntity);
		}
		if (tileEntity instanceof TileEntityCase)
		{
			return new GuiWeaponCase(player.inventory, (TileEntityCase) tileEntity);
		}
		if (tileEntity instanceof TileEntityLabel)
		{
			return new GuiLabel (player.inventory, (TileEntityLabel) tileEntity);
		}
		if (tileEntity instanceof TileEntityDesk)
		{
			return new GuiWritingDesk (player.inventory, (TileEntityDesk) tileEntity);
		} 
		if (tileEntity instanceof TileEntityPrintPress)
		{
			//return new GuiPrintPress (player.inventory, (TileEntityPrintPress) tileEntity);
		}
		if (tileEntity instanceof TileEntityTable)
		{
			return new GuiTable (player.inventory, (TileEntityTable) tileEntity);
		}
		if (tileEntity instanceof TileEntityCookieJar)
		{
			return new GuiCookieJar (player.inventory, (TileEntityCookieJar) tileEntity);
		}
		if (tileEntity instanceof TileEntityDinnerPlate)
		{
			return new GuiDinnerPlate(player.inventory, (TileEntityDinnerPlate) tileEntity);
		}
		if (tileEntity instanceof TileEntityDiscRack)
		{
			return new GuiDiscRack(player.inventory, (TileEntityDiscRack) tileEntity);
		}
		if (tileEntity instanceof TileEntityFancySign)
		{
			return new GuiFancySign(player.inventory, (TileEntityFancySign)tileEntity);
		}
		if (tileEntity instanceof TileEntityFancyWorkbench)
		{
			TileEntityFancyWorkbench bench = (TileEntityFancyWorkbench)tileEntity;
			int lx = getXcoordForWorkbenchBookcase(true, bench.getAngle(), bench.getPos().getX());
			int lz = getZcoordForWorkbenchBookcase(true, bench.getAngle(), bench.getPos().getZ());
			int rx = getXcoordForWorkbenchBookcase(false, bench.getAngle(), bench.getPos().getX());
			int rz = getZcoordForWorkbenchBookcase(false, bench.getAngle(), bench.getPos().getZ());
			int lry = bench.getPos().getY();
			TileEntityBookcase leftBookcase = null;
			TileEntityBookcase rightBookcase = null;
			
			if (!(world.isAirBlock(new BlockPos(lx, lry, lz))))
			{
				TileEntity left = world.getTileEntity(new BlockPos(lx, lry, lz));
				if (left != null && left instanceof TileEntityBookcase)
				{
					leftBookcase = (TileEntityBookcase)left;
				}
			}
			if (!(world.isAirBlock(new BlockPos(rx, lry, rz))))
			{
				TileEntity right = world.getTileEntity(new BlockPos(rx, lry, rz));
				if (right != null && right instanceof TileEntityBookcase)
				{
					rightBookcase = (TileEntityBookcase)right;
				}
			}
			return new GuiFancyWorkbench(player.inventory, world, (TileEntityFancyWorkbench)tileEntity, player.getEntityId(), leftBookcase, rightBookcase);
		}
		
		if (tileEntity instanceof TileEntityPaintPress)
		{
			return new GuiPaintPress(player.inventory, (TileEntityPaintPress)tileEntity);
		}
		if (tileEntity instanceof TileEntityPainting)
		{
			return new GuiPainting(player.inventory, (TileEntityPainting)tileEntity);
		}
		if (tileEntity instanceof TileEntityFurniturePaneler)
		{
			return new GuiFurniturePaneler(player.inventory, (TileEntityFurniturePaneler)tileEntity);
		}
		if (tileEntity instanceof TileEntityFramedChest)
		{
			TileEntityFramedChest chest = (TileEntityFramedChest)tileEntity;
			TileEntityFramedChest chest2 = null;
			if (chest.getIsDouble())
			{
				int x2 = getXcoordForChest(chest.getIsLeft(), chest.getAngle(), chest.getPos().getX());
				int z2 = getZcoordForChest(chest.getIsLeft(), chest.getAngle(), chest.getPos().getZ());
				TileEntity tile2 = world.getTileEntity(new BlockPos(x2, chest.getPos().getY(), z2));
				if (tile2 != null && tile2 instanceof TileEntityFramedChest)
				{
					if (((TileEntityFramedChest)tile2).getIsDouble())
					{
						chest2 = (TileEntityFramedChest)tile2;
					}
				}
			}
			return new GuiFramedChest(player.inventory, chest, chest2); 
		}
		
		return null;
	}
}
