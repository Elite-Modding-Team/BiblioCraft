package jds.bibliocraft.blocks.blockitems;

import jds.bibliocraft.blocks.BlockPaintingFrameMiddle;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

import java.util.List;

public class BlockItemPaintingFrameMiddle extends BiblioWoodBlockItem
{
	public static final BlockItemPaintingFrameMiddle instance = new BlockItemPaintingFrameMiddle(BlockPaintingFrameMiddle.instance);
	
	public BlockItemPaintingFrameMiddle(Block block)
	{
		super(block, BlockPaintingFrameMiddle.name);
		setRegistryName(BlockPaintingFrameMiddle.name);
	}
	
	@Override
    public void addInformation(ItemStack stack, World playerIn, List<String> tooltip, ITooltipFlag advanced) 
	{
		tooltip.add(I18n.translateToLocal("paintingFrame.3tier"));
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
}