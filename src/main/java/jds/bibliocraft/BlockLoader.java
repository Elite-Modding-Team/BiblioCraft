package jds.bibliocraft;

import jds.bibliocraft.blocks.*;
import jds.bibliocraft.blocks.blockitems.*;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;

public class BlockLoader 
{	
    public static String[] dyes =
    {
        "dyeWhite",
        "dyeLightGray",
        "dyeGray",
        "dyeBlack",
        "dyeRed",
        "dyeOrange",
        "dyeYellow",
        "dyeLime",
        "dyeGreen",
        "dyeCyan",
        "dyeLightBlue",
        "dyeBlue",
        "dyePurple",
        "dyeMagenta",
        "dyePink",
        "dyeBrown"
    };
	
    public static String[] dyes2 =
    {        
        "dyeBlack",
        "dyeRed",
        "dyeGreen",
        "dyeBrown",
        "dyeBlue",
        "dyePurple",
        "dyeCyan",
        "dyeLightGray",
        "dyeGray",
        "dyePink",
        "dyeLime",
        "dyeYellow",
        "dyeLightBlue",
        "dyeMagenta",
        "dyeOrange",
        "dyeWhite"
        
    };
	
	public static int creativetabID = CreativeTabs.getNextID();
	
	public static final CreativeTabs biblioTab = new BiblioTab("BiblioCraft");
	public static CreativeTabs biblioLightsTab;// = new BiblioLightsTab("BiblioCraftLights");
	public static void initLightTab()
	{
		biblioLightsTab = new BiblioLightsTab("BiblioCraftLights");
	}
	
	/** The number of woods starting at 0 so easy use in loops.  */
	public final static int NUMBER_OF_WOODS = 6;
	
	
	
	/** BiblioBlocks */ 
	/*
	@ObjectHolder(BlockBookcase.name)
	public static final Block bookcase_block = BlockBookcase.instance;
	@ObjectHolder(BlockBookcase.name)
	public static final Item bookcase_item = BlockItemBookcase.instance;
	*/
	

	
	public static void initBlocks(RegistryEvent.Register<Block> event)
	{
		if (Config.enableBookcase)
		{
			event.getRegistry().register(BlockBookcase.instance);
			event.getRegistry().register(BlockBookcaseCreative.instance);
		}
		if (Config.enableGenericshelf)
		{	
			event.getRegistry().register(BlockShelf.instance);
		}
		if (Config.enableTapemeasure)
		{
			event.getRegistry().register(BlockMarkerPole.instance);
		}
		if (Config.enableClipboard)
		{
			event.getRegistry().register(BlockClipboard.instance);
		}
		
		if (Config.enableLantern)
		{	
			event.getRegistry().register(BlockLanternGold.instance);
			event.getRegistry().register(BlockLanternIron.instance);
		}
		if (Config.enableLamp)
		{	
			event.getRegistry().register(BlockLampGold.instance);
			event.getRegistry().register(BlockLampIron.instance);
		}
		if (Config.enableFurniturePaneler)
		{
			event.getRegistry().register(BlockFurniturePaneler.instance);
		}
		if (Config.enableFramedChest)
		{
			event.getRegistry().register(BlockFramedChest.instance);
		}
		if (Config.enableFancySign)
		{
			event.getRegistry().register(BlockFancySign.instance);
		}
		if (Config.enableFancyWorkbench)
		{
			event.getRegistry().register(BlockFancyWorkbench.instance);
		}
		if (Config.enablePotionshelf)
		{
			event.getRegistry().register(BlockPotionShelf.instance);
		}
		if (Config.enableToolrack)
		{	
			event.getRegistry().register(BlockToolRack.instance);
		}
		if (Config.enableWoodLabel)
		{	
			event.getRegistry().register(BlockLabel.instance);
		}
		if (Config.enableWritingdesk)
		{	
			event.getRegistry().register(BlockDesk.instance);
		}
		if (Config.enableTable)
		{	
			event.getRegistry().register(BlockTable.instance);
		}
		if (Config.enableSeat)
		{
			event.getRegistry().register(BlockSeat.instance);
		}
		
		if (Config.enableClock)
		{
			event.getRegistry().register(BlockClock.instance);
		}
		if (Config.enableWeaponcase)
		{	
			event.getRegistry().register(BlockCase.instance);
		}
		if (Config.enableMapFrame)
		{
			event.getRegistry().register(BlockMapFrame.instance);
		}
		
		if (Config.enablePainting)
		{
			event.getRegistry().register(BlockPaintingFrameFlat.instance);
			event.getRegistry().register(BlockPaintingFrameSimple.instance);
			event.getRegistry().register(BlockPaintingFrameMiddle.instance);
			event.getRegistry().register(BlockPaintingFrameFancy.instance);
			event.getRegistry().register(BlockPaintingFrameBorderless.instance);

			event.getRegistry().register(BlockPaintingPress.instance);
		}
		if (Config.enableTypewriter)
		{
			event.getRegistry().register(BlockTypeWriter.instance);
		}
		if (Config.enableSwordPedestal)
		{
			event.getRegistry().register(BlockSwordPedestal.instance);
		}
		if (Config.enableArmorstand)
		{	
			event.getRegistry().register(BlockArmorStand.instance);
		}
		if (Config.enableDeskBell)
		{
			event.getRegistry().register(BlockBell.instance); 
		}
		if (Config.enablePrintpressTypeMachine)
		{	
			event.getRegistry().register(BlockTypesettingTable.instance);
			event.getRegistry().register(BlockPrintingPress.instance); 
		}
		if (Config.enableCookieJar)
		{
			event.getRegistry().register(BlockCookieJar.instance); 
		}
		if (Config.enableDinnerPlate)
		{
			event.getRegistry().register(BlockDinnerPlate.instance); 
		}
		if (Config.enableDiscRack)
		{
			event.getRegistry().register(BlockDiscRack.instance); 
		}
	}
	
	public static void initBlockItems(RegistryEvent.Register<Item> event)
	{
		if (Config.enableBookcase)
		{
			event.getRegistry().register(BlockItemBookcase.instance);

			event.getRegistry().register(BlockItemBookcaseCreative.instance); 
		}
		if (Config.enableGenericshelf)
		{	
			event.getRegistry().register(BlockItemShelf.instance); 
		}
		if (Config.enableTapemeasure)
		{
			event.getRegistry().register(new ItemBlock(BlockMarkerPole.instance).setRegistryName(BlockMarkerPole.name)); 
		}
		if (Config.enableClipboard)
		{
			event.getRegistry().register(new ItemBlock(BlockClipboard.instance).setRegistryName(BlockClipboard.name)); 
		}
		
		if (Config.enableLantern)
		{	
			event.getRegistry().register(BlockItemLantern.instanceGold); 
			event.getRegistry().register(BlockItemLantern.instanceIron); 
		}
		if (Config.enableLamp)
		{	
			event.getRegistry().register(BlockItemLamp.instanceGold); 
			event.getRegistry().register(BlockItemLamp.instanceIron); 
		}
		if (Config.enableFurniturePaneler)
		{
			event.getRegistry().register(BlockItemFurniturePaneler.instance); 
		}
		if (Config.enableFramedChest)
		{
			event.getRegistry().register(BlockItemFramedChest.instance); 
		}
		if (Config.enableFancySign)
		{
			event.getRegistry().register(BlockItemFancySign.instance); 
		}
		if (Config.enableFancyWorkbench)
		{
			event.getRegistry().register(BlockItemFancyWorkbench.instance); 
		}
		if (Config.enablePotionshelf)
		{
			event.getRegistry().register(BlockItemPotionShelf.instance); 
		}
		if (Config.enableToolrack)
		{	
			event.getRegistry().register(BlockItemToolRack.instance); 
		}
		if (Config.enableWoodLabel)
		{	
			event.getRegistry().register(BlockItemLabel.instance); 
		}
		if (Config.enableWritingdesk)
		{	
			event.getRegistry().register(BlockItemDesk.instance); 
		}
		if (Config.enableTable)
		{	
			event.getRegistry().register(BlockItemTable.instance); 
		}
		if (Config.enableSeat)
		{
			event.getRegistry().register(BlockItemSeat.instance); 
		}
		
		if (Config.enableClock)
		{
			event.getRegistry().register(BlockItemClock.instance); 
		}
		if (Config.enableWeaponcase)
		{	
			event.getRegistry().register(BlockItemCase.instance); 
		}
		if (Config.enableMapFrame)
		{
			event.getRegistry().register(BlockItemMapFrame.instance); 
		}
		
		if (Config.enablePainting)
		{
			event.getRegistry().register(BlockItemPaintingFrameFlat.instance); 
			event.getRegistry().register(BlockItemPaintingFrameSimple.instance); 
			event.getRegistry().register(BlockItemPaintingFrameMiddle.instance); 
			event.getRegistry().register(BlockItemPaintingFrameFancy.instance); 
			event.getRegistry().register(BlockItemPaintingFrameBorderless.instance); 
			event.getRegistry().register(new ItemBlock(BlockPaintingPress.instance).setRegistryName(BlockPaintingPress.name)); 
		}
		if (Config.enableTypewriter)
		{
			event.getRegistry().register(BlockItemTypewriter.instance); 
		}
		if (Config.enableSwordPedestal)
		{
			event.getRegistry().register(BlockItemSwordPedestal.instance); 
		}
		if (Config.enableArmorstand)
		{	
			event.getRegistry().register(BlockItemArmorStand.instance); 
		}
		if (Config.enableDeskBell)
		{
			event.getRegistry().register(new ItemBlock(BlockBell.instance).setRegistryName(BlockBell.name)); 
		}
		if (Config.enablePrintpressTypeMachine)
		{	
			event.getRegistry().register(new ItemBlock(BlockTypesettingTable.instance).setRegistryName(BlockTypesettingTable.name)); 
			event.getRegistry().register(new ItemBlock(BlockPrintingPress.instance).setRegistryName(BlockPrintingPress.name)); 
		}
		if (Config.enableCookieJar)
		{
			event.getRegistry().register(new ItemBlock(BlockCookieJar.instance).setRegistryName(BlockCookieJar.name)); 
		}
		if (Config.enableDinnerPlate)
		{
			event.getRegistry().register(new ItemBlock(BlockDinnerPlate.instance).setRegistryName(BlockDinnerPlate.name)); 
		}
		if (Config.enableDiscRack)
		{
			event.getRegistry().register(new ItemBlock(BlockDiscRack.instance).setRegistryName(BlockDiscRack.name)); 
		}
	}
}
