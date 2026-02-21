package jds.bibliocraft.bibliowoods.bop;

import jds.bibliocraft.blocks.BlockBookcase;
import jds.bibliocraft.helpers.BiblioWoodHelperTab;
import jds.bibliocraft.helpers.RegisterCustomFramedBlocks;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;

public class BiblioWoodsBoP 
{
    public static CreativeTabs creativeTab;
    
	public static void init() 
	{
		if (Loader.isModLoaded("biomesoplenty"))
		{
			Block planks = Block.REGISTRY.getObject(new ResourceLocation("biomesoplenty:planks_0")); 
			Block slabs1 = Block.REGISTRY.getObject(new ResourceLocation("biomesoplenty:wood_slab_0"));
			Block slabs2 = Block.REGISTRY.getObject(new ResourceLocation("biomesoplenty:wood_slab_1"));
            
            if (planks == null || slabs1 == null || slabs2 == null) return;

			Block[] slabs = {slabs1, slabs1, slabs1, slabs1, slabs1, slabs1, slabs1, slabs1, slabs2, slabs2, slabs2, slabs2, slabs2, slabs2, slabs2, slabs2};
			int[] slabMetas = {0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7};
			String[] textures = {
					"biomesoplenty:blocks/sacred_oak_planks",
					"biomesoplenty:blocks/cherry_planks",
					"biomesoplenty:blocks/umbran_planks",
					"biomesoplenty:blocks/fir_planks",
					"biomesoplenty:blocks/ethereal_planks",
					"biomesoplenty:blocks/magic_planks",
					"biomesoplenty:blocks/mangrove_planks",
					"biomesoplenty:blocks/palm_planks",
					"biomesoplenty:blocks/redwood_planks",
					"biomesoplenty:blocks/willow_planks",
					"biomesoplenty:blocks/pine_planks",
					"biomesoplenty:blocks/hellbark_planks",
					"biomesoplenty:blocks/jacaranda_planks",
					"biomesoplenty:blocks/mahogany_planks",
					"biomesoplenty:blocks/ebony_planks",
					"biomesoplenty:blocks/eucalyptus_planks"
				};
			ItemStack icon = new ItemStack(BlockBookcase.instance, 1, 6);
			NBTTagCompound tags = new NBTTagCompound();
			tags.setString("renderTexture", "biomesoplenty:blocks/sacred_oak_planks");
			icon.setTagCompound(tags);
			creativeTab = new BiblioWoodHelperTab("bibliowoodboptab", textures, icon);
			for (int i = 0; i < textures.length; i++)
			{
				RegisterCustomFramedBlocks reg = new RegisterCustomFramedBlocks(textures[i]);
				reg.registerRecipies(new ItemStack(planks, 1, i), new ItemStack(slabs[i], 1, slabMetas[i]));
			}
		}
	}
}
