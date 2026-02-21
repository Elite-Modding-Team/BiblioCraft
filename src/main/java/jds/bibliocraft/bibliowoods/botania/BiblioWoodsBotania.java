package jds.bibliocraft.bibliowoods.botania;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;

public class BiblioWoodsBotania 
{
    public static CreativeTabs creativeTab;
    
	public static void init() 
	{
		if (Loader.isModLoaded("botania"))
		{
			// TODO these work, but I am not sure how excited I am about botania?, maybe I could ask twitter what blocks i hsould do?
			Block planks = Block.REGISTRY.getObject(new ResourceLocation("botania:shimmerwoodPlanks"));
			Block planks2 = Block.REGISTRY.getObject(new ResourceLocation("botania:livingwood"));
			Block planks3 = Block.REGISTRY.getObject(new ResourceLocation("botania:dreamwood"));
			Block planks5 = Block.REGISTRY.getObject(new ResourceLocation("botania:livingrock"));
			testBlock(planks);
			testBlock(planks2);
			testBlock(planks3);
			testBlock(planks5);
		}
	}
	
	public static void testBlock(Block block)
	{
		if (block != null)
		{
			for (int i = 0; i < 16; i++)
			{
				ItemStack stack = new ItemStack(block, 1, i);
				if (stack != ItemStack.EMPTY)
					System.out.println("stack " + i + "   = " + stack.getDisplayName() + "   translationKey = " + stack.getTranslationKey());
			}
		}
	}
}
