package jds.bibliocraft;

import jds.bibliocraft.helpers.RecipeBiblioAtlas;
import jds.bibliocraft.items.*;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.oredict.RecipeSorter;


public class ItemLoader 
{
	//public static Enchantment readingChant;
	//public static Enchantment deathCompChant;
	
	
	
	
	public static void initItems(RegistryEvent.Register<Item> event)
	{
		
		// OBJ models
		if (Config.enableWaypointCompass)
		{
			//GameRegistry.registerItem(ItemWaypointCompass.instance, ItemWaypointCompass.name);
			event.getRegistry().register(ItemWaypointCompass.instance);
		}
		if (Config.enableMapFrame)
		{
			event.getRegistry().register(ItemMapTool.instance);
		}
		if (Config.enableSeat)
		{
			event.getRegistry().register(ItemSeatBack.instance);
			event.getRegistry().register(ItemSeatBack2.instance);
			event.getRegistry().register(ItemSeatBack3.instance);
			event.getRegistry().register(ItemSeatBack4.instance);
			event.getRegistry().register(ItemSeatBack5.instance);
		}
		
		// Vanilla models
		if (Config.enableStockroomCatalog)
		{
			event.getRegistry().register(ItemStockroomCatalog.instance);
		}
		
		if (Config.enablePlumbLine)
		{
			event.getRegistry().register(ItemPlumbLine.instance);
		}
		
		if (Config.enableFurniturePaneler)
		{
			event.getRegistry().register(ItemFramingSaw.instance);
			event.getRegistry().register(ItemFramingBoard.instance);
			event.getRegistry().register(ItemFramingSheet.instance);
		}
		
		if (Config.enableTesterItem)
		{
			event.getRegistry().register(ItemNameTester.instance);
		}
		
		if (Config.enableAtlas)
		{
			event.getRegistry().register(ItemAtlas.instance);
			event.getRegistry().register(ItemAtlasPlate.instance);
		}
		
		if (Config.enableAtlas && Config.enableDeathCompass)
		{
			event.getRegistry().register(ItemDeathCompass.instance);
			//deathCompChant = new EnchantmentDeathCompass();
		}
		
		if (Config.enablePainting)
		{
			event.getRegistry().register(ItemPaintingCanvas.instance);
		}
		if (Config.enableBigBook)
		{
			event.getRegistry().register(ItemBigBook.instance);
		}
		
		if (Config.enableFancyWorkbench)
		{
			event.getRegistry().register(ItemRecipeBook.instance);
		}
		
		if (Config.enableSlottedBook)
		{
			event.getRegistry().register(ItemSlottedBook.instance);
		}
		
		if (Config.enableHandDrill)
		{
			event.getRegistry().register(ItemHandDrill.instance);
		}

		if (Config.enableTapemeasure)
		{
			event.getRegistry().register(ItemTapeMeasure.instance);
			event.getRegistry().register(ItemTape.instance);
		}
		if (Config.enablePrintpressTypeMachine)
		{
			event.getRegistry().register(ItemChase.instance);
			event.getRegistry().register(ItemPlate.instance);
			event.getRegistry().register(ItemEnchantedPlate.instance);
		}
		if (Config.enableRedstonebook)
		{
			event.getRegistry().register(ItemRedstoneBook.instance);
		}
		if (Config.enableReadingglasses)
		{
			event.getRegistry().register(ItemReadingGlasses.instance);
		}
		if (Config.enableDrill)
		{
			event.getRegistry().register(ItemDrill.instance);
		}
		if (Config.enableLock)
		{
			event.getRegistry().register(ItemLock.instance);
		}
		if (Config.enableClipboard)
		{
			event.getRegistry().register(ItemClipboard.instance);
		}
		//readingChant = new EnchantmentReading();
		//Enchantment.addToBookList(readingChant); 
		
		
	}

	public static void addRecipies(RegistryEvent.Register<IRecipe> event)
	{
		
		ItemStack enchantedbook = new ItemStack(Items.ENCHANTED_BOOK, 1, 0);
		ItemStack enchantedreadingbook = new ItemStack(Items.ENCHANTED_BOOK, 1, 0);
		if (Config.enableAtlas)
		{
			if (Config.enableDeathCompass && Config.enableWaypointCompass)
			{			
				ResourceLocation regName = new ResourceLocation("bibliocraft:enchantedatlas");
				ResourceLocation regNameb = new ResourceLocation("bibliocraft:enchantedatlasalt");
				ItemStack waypointCompass = new ItemStack(ItemWaypointCompass.instance, 1, 0);
				ItemStack enderPearl = new ItemStack(Items.ENDER_PEARL, 1, 0);
				RecipeSorter.register("bibliocraft:enchantedatlas", RecipeBiblioAtlas.class, RecipeSorter.Category.SHAPED, "");
				IRecipe recipea = RecipeBiblioAtlas.addAtlasEnchantRecipe(new ItemStack(ItemAtlas.instance, 1, 0), "PBP", "CAC", "PBP", 'P', enderPearl, 'B', enchantedbook, 'C', waypointCompass, 'A', new ItemStack(ItemAtlas.instance));
				recipea.setRegistryName(regName);
				event.getRegistry().register(recipea);
				IRecipe recipeb =  RecipeBiblioAtlas.addAtlasEnchantRecipe(new ItemStack(ItemAtlas.instance, 1, 0), "PCP", "BAB", "PCP", 'P', enderPearl, 'B', enchantedbook, 'C', waypointCompass, 'A', new ItemStack(ItemAtlas.instance));
				recipeb.setRegistryName(regNameb);
				event.getRegistry().register(recipeb);
			}
		}
		
	}
}
