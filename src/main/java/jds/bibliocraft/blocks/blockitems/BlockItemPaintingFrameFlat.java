package jds.bibliocraft.blocks.blockitems;

import jds.bibliocraft.blocks.BlockPaintingFrameFlat;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

import java.util.List;

public class BlockItemPaintingFrameFlat extends BiblioWoodBlockItem
{
	public static final BlockItemPaintingFrameFlat instance = new BlockItemPaintingFrameFlat(BlockPaintingFrameFlat.instance);
	
	public BlockItemPaintingFrameFlat(Block block)
	{
		super(block, BlockPaintingFrameFlat.name);
		setRegistryName(BlockPaintingFrameFlat.name);
	}
	
	@Override
    public void addInformation(ItemStack stack, World playerIn, List<String> tooltip, ITooltipFlag advanced) 
	{
		tooltip.add(I18n.translateToLocal("paintingFrame.1tier"));
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
}