package jds.bibliocraft.jei;

import jds.bibliocraft.Config;
import jds.bibliocraft.blocks.blockitems.BlockItemClipboard;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.item.ItemStack;

/** Keeps the implementation-only clipboard block out of JEI's ingredient list. */
@JEIPlugin
public class BiblioCraftJeiPlugin implements IModPlugin
{
    @Override
    public void register(IModRegistry registry)
    {
        if (!Config.enableClipboard)
        {
            return;
        }

        // The ingredient filter is not created until after mod plugins are registered.
        // The blacklist is intentionally designed to be populated during this callback.
        registry.getJeiHelpers().getItemBlacklist()
                .addItemToBlacklist(new ItemStack(BlockItemClipboard.instance));
    }
}
