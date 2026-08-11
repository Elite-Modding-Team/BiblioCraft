package jds.bibliocraft.blocks.blockitems;

import jds.bibliocraft.blocks.BlockClipboard;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

/**
 * The wall clipboard block is an implementation detail of {@link jds.bibliocraft.items.ItemClipboard}.
 * It still needs an ItemBlock for the block registry/model system, but it must not be offered as a
 * directly obtainable item in creative mode.
 */
public class BlockItemClipboard extends ItemBlock
{
    public static final BlockItemClipboard instance = new BlockItemClipboard(BlockClipboard.instance);

    public BlockItemClipboard(Block block)
    {
        super(block);
        setRegistryName(BlockClipboard.name);
        setCreativeTab(null);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems)
    {
        // The clipboard item is the only supported way to obtain/place this block.
    }
}
