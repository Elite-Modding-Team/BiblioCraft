package jds.bibliocraft.bibliowoods.forestry;

import jds.bibliocraft.blocks.BlockBookcase;
import jds.bibliocraft.helpers.BiblioWoodHelperTab;
import jds.bibliocraft.helpers.RegisterCustomFramedBlocks;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;

public class BiblioWoodsForestry 
{
    public static CreativeTabs creativeTab;
    
	public static void init() 
	{
		if (Loader.isModLoaded("forestry"))
		{
			Block planks1 = Block.REGISTRY.getObject(new ResourceLocation("forestry:planks.0")); 
			Block planks2 = Block.REGISTRY.getObject(new ResourceLocation("forestry:planks.1")); 
			Block slabs1 = Block.REGISTRY.getObject(new ResourceLocation("forestry:slabs.0")); 
			Block slabs2 = Block.REGISTRY.getObject(new ResourceLocation("forestry:slabs.1"));
			Block slabs3 = Block.REGISTRY.getObject(new ResourceLocation("forestry:slabs.2"));
			Block slabs4 = Block.REGISTRY.getObject(new ResourceLocation("forestry:slabs.3"));
            
            if (planks1 == null || planks2 == null || slabs1 == null || slabs2 == null || slabs3 == null || slabs4 == null) return;
            
			Block[] planks = {planks1, planks1, planks1, planks1, planks1, planks1, planks1, planks1, planks1, planks1, planks1, planks1, planks1, planks1, planks1, planks1,
							  planks2, planks2, planks2, planks2, planks2, planks2, planks2, planks2, planks2, planks2, planks2, planks2, planks2};
			int[] plankID = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0,1,2,3,4,5,6,7,8,9,10,11,12};
			Block[] slabs = {slabs1, slabs1, slabs1, slabs1, slabs1, slabs1, slabs1, slabs1, slabs2, slabs2, slabs2, slabs2, slabs2, slabs2, slabs2, slabs2,
							 slabs3, slabs3, slabs3, slabs3, slabs3, slabs3, slabs3, slabs3, slabs4, slabs4, slabs4, slabs4, slabs4};
			int[] slabID = {0,1,2,3,4,5,6,7,0,1,2,3,4,5,6,7,0,1,2,3,4,5,6,7,0,1,2,3,4};
			String[] textures = {
					"forestry:blocks/wood/planks.larch",
					"forestry:blocks/wood/planks.teak",
					"forestry:blocks/wood/planks.acacia",
					"forestry:blocks/wood/planks.lime",
					"forestry:blocks/wood/planks.chestnut",
					"forestry:blocks/wood/planks.wenge",
					"forestry:blocks/wood/planks.baobab",
					"forestry:blocks/wood/planks.sequoia",
					"forestry:blocks/wood/planks.kapok",
					"forestry:blocks/wood/planks.ebony",
					"forestry:blocks/wood/planks.mahogany",
					"forestry:blocks/wood/planks.balsa",
					"forestry:blocks/wood/planks.willow",
					"forestry:blocks/wood/planks.walnut",
					"forestry:blocks/wood/planks.greenheart",
					"forestry:blocks/wood/planks.cherry",
					"forestry:blocks/wood/planks.mahoe",
					"forestry:blocks/wood/planks.poplar",
					"forestry:blocks/wood/planks.palm",
					"forestry:blocks/wood/planks.papaya",
					"forestry:blocks/wood/planks.pine",
					"forestry:blocks/wood/planks.plum",
					"forestry:blocks/wood/planks.maple",
					"forestry:blocks/wood/planks.citrus",
					"forestry:blocks/wood/planks.giganteum",
					"forestry:blocks/wood/planks.ipe",
					"forestry:blocks/wood/planks.padauk",
					"forestry:blocks/wood/planks.cocobolo",
					"forestry:blocks/wood/planks.zebrawood"
			};

			ItemStack icon = new ItemStack(BlockBookcase.instance, 1, 6);
			NBTTagCompound tags = new NBTTagCompound();
			tags.setString("renderTexture", "forestry:blocks/wood/planks.cocobolo");
			icon.setTagCompound(tags);
			creativeTab = new BiblioWoodHelperTab("bibliowoodforestrytab", textures, icon);
			for (int i = 0; i < textures.length; i++)
			{
				RegisterCustomFramedBlocks reg = new RegisterCustomFramedBlocks(textures[i]);
				reg.registerRecipies(new ItemStack(planks[i], 1, plankID[i]), new ItemStack(slabs[i], 1, slabID[i]));
			}
		}
	}
}
