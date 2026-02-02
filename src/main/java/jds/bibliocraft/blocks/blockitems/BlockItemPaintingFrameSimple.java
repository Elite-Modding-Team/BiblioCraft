package jds.bibliocraft.blocks.blockitems;

import jds.bibliocraft.blocks.BlockPaintingFrameSimple;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

import java.util.List;

public class BlockItemPaintingFrameSimple extends BiblioWoodBlockItem
{
	public static final BlockItemPaintingFrameSimple instance = new BlockItemPaintingFrameSimple(BlockPaintingFrameSimple.instance);
	
	public BlockItemPaintingFrameSimple(Block block)
	{
		super(block, BlockPaintingFrameSimple.name);
		setRegistryName(BlockPaintingFrameSimple.name);
	}
	
	@Override
    public void addInformation(ItemStack stack, World playerIn, List<String> tooltip, ITooltipFlag advanced) 
	{
		tooltip.add(I18n.translateToLocal("paintingFrame.2tier"));
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
}