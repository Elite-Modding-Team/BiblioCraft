package jds.bibliocraft.statemappers;

import com.google.common.collect.Maps;
import jds.bibliocraft.BlockLoader;
import jds.bibliocraft.blocks.*;
import jds.bibliocraft.helpers.EnumColor;
import jds.bibliocraft.models.*;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;

import java.util.Map;

public class BiblioBlockStateMapper extends DefaultStateMapper
{
	public final static BiblioBlockStateMapper instance = new BiblioBlockStateMapper();
	
	public BiblioBlockStateMapper() {}
	
	@Override
	public Map putStateModelLocations(Block block)
	{
		Map modelLocations = Maps.newLinkedHashMap();
		
		if (block instanceof BlockBookcaseCreative)
		{
			BlockBookcaseCreative bookcase = (BlockBookcaseCreative)block;
			for (int i = 0; i <= BlockLoader.NUMBER_OF_WOODS; i++)
			{
				IBlockState state = bookcase.getStateFromMeta(i);
				modelLocations.put(state, ModelBookcase.modelResourceLocationFilledBookcase);
			}
		}
		
		if (block instanceof BlockBookcase)
		{
			BlockBookcase bookcase = (BlockBookcase)block;
			for (int i = 0; i <= BlockLoader.NUMBER_OF_WOODS; i++)
			{
				IBlockState state = bookcase.getStateFromMeta(i);
				modelLocations.put(state, ModelBookcase.modelResourceLocation);
			}
		}
		
		if (block instanceof BlockShelf)
		{
			BlockShelf bookcase = (BlockShelf)block;
			for (int i = 0; i <= BlockLoader.NUMBER_OF_WOODS; i++)
			{
				IBlockState state = bookcase.getStateFromMeta(i);
				modelLocations.put(state, ModelShelf.modelResourceLocation);
			}
		}
		
		if (block instanceof BlockFurniturePaneler)
		{
			BlockFurniturePaneler myBlock = (BlockFurniturePaneler)block;
			for (int i = 0; i <= BlockLoader.NUMBER_OF_WOODS; i++)
			{
				IBlockState state = myBlock.getStateFromMeta(i);
				modelLocations.put(state, ModelFurniturePaneler.modelResourceLocation);
			}
		}
		
		if (block instanceof BlockLabel)
		{
			BlockLabel myBlock = (BlockLabel)block;
			for (int i = 0; i <= BlockLoader.NUMBER_OF_WOODS; i++)
			{
				IBlockState state = myBlock.getStateFromMeta(i);
				modelLocations.put(state, ModelLabel.modelResourceLocation);
			}
		}
		
		if (block instanceof BlockToolRack)
		{
			BlockToolRack myBlock = (BlockToolRack)block;
			for (int i = 0; i <= BlockLoader.NUMBER_OF_WOODS; i++)
			{
				IBlockState state = myBlock.getStateFromMeta(i);
				modelLocations.put(state, ModelToolRack.modelResourceLocation);
			}
		}
		
		if (block instanceof BlockPotionShelf)
		{
			BlockPotionShelf myBlock = (BlockPotionShelf)block;
			for (int i = 0; i <= BlockLoader.NUMBER_OF_WOODS; i++)
			{
				IBlockState state = myBlock.getStateFromMeta(i);
				modelLocations.put(state, ModelPotionShelf.modelResourceLocation);
			}
		}
		
		if (block instanceof BlockSeat)
		{
			BlockSeat myBlock = (BlockSeat)block;
			for (int i = 0; i <= BlockLoader.NUMBER_OF_WOODS; i++)
			{
				IBlockState state = myBlock.getStateFromMeta(i);
				modelLocations.put(state, ModelSeat.modelResourceLocation);
			}
		}
		
		if (block instanceof BlockFancySign)
		{
			BlockFancySign myBlock = (BlockFancySign)block;
			for (int i = 0; i <= BlockLoader.NUMBER_OF_WOODS; i++)
			{
				IBlockState state = myBlock.getStateFromMeta(i);
				modelLocations.put(state, ModelFancySign.modelResourceLocation);
			}
		}
		
		if (block instanceof BlockFancyWorkbench)
		{
			BlockFancyWorkbench myBlock = (BlockFancyWorkbench)block;
			for (int i = 0; i <= BlockLoader.NUMBER_OF_WOODS; i++)
			{
				IBlockState state = myBlock.getStateFromMeta(i);
				modelLocations.put(state, ModelFancyWorkbench.modelResourceLocation);
			}
		}
		
		if (block instanceof BlockFramedChest)
		{
			BlockFramedChest myBlock = (BlockFramedChest)block;
			for (int i = 0; i <= BlockLoader.NUMBER_OF_WOODS; i++)
			{
				IBlockState state = myBlock.getStateFromMeta(i);
				modelLocations.put(state, ModelFramedChest.modelResourceLocation);
			}
		}
		
		if (block instanceof BlockDesk)
		{
			BlockDesk myBlock = (BlockDesk)block;
			for (int i = 0; i <= BlockLoader.NUMBER_OF_WOODS; i++)
			{
				IBlockState state = myBlock.getStateFromMeta(i);
				modelLocations.put(state, ModelDesk.modelResourceLocation);
			}
		}
		
		if (block instanceof BlockTable)
		{
			BlockTable myBlock = (BlockTable)block;
			for (int i = 0; i <= BlockLoader.NUMBER_OF_WOODS; i++)
			{
				IBlockState state = myBlock.getStateFromMeta(i);
				modelLocations.put(state, ModelTable.modelResourceLocation);
			}
		}
		
		if (block instanceof BlockClock)
		{
			BlockClock myBlock = (BlockClock)block;
			for (int i = 0; i <= BlockLoader.NUMBER_OF_WOODS; i++)
			{
				IBlockState state = myBlock.getStateFromMeta(i);
				modelLocations.put(state, ModelClock.modelResourceLocation);
			}
		}
		
		if (block instanceof BlockMapFrame)
		{
			BlockMapFrame myBlock = (BlockMapFrame)block;
			for (int i = 0; i <= BlockLoader.NUMBER_OF_WOODS; i++)
			{
				IBlockState state = myBlock.getStateFromMeta(i);
				modelLocations.put(state, ModelMapFrame.modelResourceLocation);
			}
		}
		
		if (block instanceof BlockCase)
		{
			BlockCase myBlock = (BlockCase)block;
			for (int i = 0; i <= BlockLoader.NUMBER_OF_WOODS; i++)
			{
				IBlockState state = myBlock.getStateFromMeta(i);
				modelLocations.put(state, ModelCase.modelResourceLocation);
			}
		}
		
		if (block instanceof BlockPaintingFrameBorderless)
		{
			BlockPaintingFrameBorderless myBlock = (BlockPaintingFrameBorderless)block;
			for (int i = 0; i <= BlockLoader.NUMBER_OF_WOODS; i++)
			{
				IBlockState state = myBlock.getStateFromMeta(i);
				modelLocations.put(state, ModelPainting.modelResourceLocationBorderless);
			}
		}
		
		if (block instanceof BlockPaintingFrameFlat)
		{
			BlockPaintingFrameFlat myBlock = (BlockPaintingFrameFlat)block;
			for (int i = 0; i <= BlockLoader.NUMBER_OF_WOODS; i++)
			{
				IBlockState state = myBlock.getStateFromMeta(i);
				modelLocations.put(state, ModelPainting.modelResourceLocationFlat);
			}
		}
		
		if (block instanceof BlockPaintingFrameSimple)
		{
			BlockPaintingFrameSimple myBlock = (BlockPaintingFrameSimple)block;
			for (int i = 0; i <= BlockLoader.NUMBER_OF_WOODS; i++)
			{
				IBlockState state = myBlock.getStateFromMeta(i);
				modelLocations.put(state, ModelPainting.modelResourceLocationSimple);
			}
		}
		
		if (block instanceof BlockPaintingFrameMiddle)
		{
			BlockPaintingFrameMiddle myBlock = (BlockPaintingFrameMiddle)block;
			for (int i = 0; i <= BlockLoader.NUMBER_OF_WOODS; i++)
			{
				IBlockState state = myBlock.getStateFromMeta(i);
				modelLocations.put(state, ModelPainting.modelResourceLocationMiddle);
			}
		}
		
		if (block instanceof BlockPaintingFrameFancy)
		{
			BlockPaintingFrameFancy myBlock = (BlockPaintingFrameFancy)block;
			for (int i = 0; i <= BlockLoader.NUMBER_OF_WOODS; i++)
			{
				IBlockState state = myBlock.getStateFromMeta(i);
				modelLocations.put(state, ModelPainting.modelResourceLocationFancy);
			}
		}
		
		if (block instanceof BlockTypeWriter)
		{
			BlockTypeWriter myBlock = (BlockTypeWriter)block;
			for (int i = 0; i < EnumColor.values().length; i++)
			{
				IBlockState state = myBlock.getStateFromMeta(i);
				modelLocations.put(state, ModelTypewriter.modelResourceLocation);
			}
		}
		
		if (block instanceof BlockSwordPedestal)
		{
			BlockSwordPedestal myBlock = (BlockSwordPedestal)block;
			for (int i = 0; i < EnumColor.values().length; i++)
			{
				IBlockState state = myBlock.getStateFromMeta(i);
				modelLocations.put(state, ModelSwordPedestal.modelResourceLocation);
			}
		}
		
		if (block instanceof BlockArmorStand)
		{
			BlockArmorStand myBlock = (BlockArmorStand)block;
			for (int i = 0; i <= BlockLoader.NUMBER_OF_WOODS; i++)
			{
				IBlockState state = myBlock.getStateFromMeta(i);
				modelLocations.put(state, ModelArmorStand.modelResourceLocation);
			}
		}
		if (block instanceof BlockBell)
		{
			BlockBell myBlock = (BlockBell)block;
			IBlockState state = myBlock.getStateFromMeta(0);
			modelLocations.put(state, ModelBell.modelResourceLocation); 
		}
		
		if (block instanceof BlockCookieJar)
		{
			BlockCookieJar myBlock = (BlockCookieJar)block;
			IBlockState state = myBlock.getStateFromMeta(0);
			modelLocations.put(state, ModelCookieJar.modelResourceLocation);
		}
		
		if (block instanceof BlockPaintingPress)
		{
			BlockPaintingPress myBlock = (BlockPaintingPress)block;
			IBlockState state = myBlock.getStateFromMeta(0);
			modelLocations.put(state, ModelPaintingPress.modelResourceLocation);
		}
		
		if (block instanceof BlockDinnerPlate)
		{
			BlockDinnerPlate myBlock = (BlockDinnerPlate)block;
			IBlockState state = myBlock.getStateFromMeta(0);
			modelLocations.put(state, ModelDinnerPlate.modelResourceLocation);
		}
		
		if (block instanceof BlockDiscRack)
		{
			BlockDiscRack myBlock = (BlockDiscRack)block;
			IBlockState state = myBlock.getStateFromMeta(0);
			modelLocations.put(state, ModelDiscRack.modelResourceLocation);
		}
		
		if (block instanceof BlockTypesettingTable)
		{
			BlockTypesettingTable myBlock = (BlockTypesettingTable)block;
			IBlockState state = myBlock.getStateFromMeta(0);
			modelLocations.put(state, ModelTypesettingTable.modelResourceLocation);
		}
		
		if (block instanceof BlockPrintingPress)
		{
			BlockPrintingPress myBlock = (BlockPrintingPress)block;
			IBlockState state = myBlock.getStateFromMeta(0);
			modelLocations.put(state, ModelPrintingPress.modelResourceLocation);
		}
		
		return modelLocations;
	}
}
