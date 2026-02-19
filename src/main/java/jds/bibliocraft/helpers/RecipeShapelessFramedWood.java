package jds.bibliocraft.helpers;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class RecipeShapelessFramedWood extends ShapelessRecipes
{
	private static ArrayList<WoodRegistryEntry> registry;

	public RecipeShapelessFramedWood(String group, ItemStack output, NonNullList<Ingredient> inputList) 
	{
		super(group, output, inputList);
		if (registry == null)
			registry = new ArrayList<WoodRegistryEntry>();
	}

	public static IRecipe addShapedWoodRecipe(ResourceLocation registryName, ItemStack stack, WoodRegistryEntry entry, Object ... stuff)
	{
		if (registry == null)
			registry = new ArrayList<WoodRegistryEntry>();
		
		registry.add(entry);

		NonNullList<Ingredient> inputstacks = NonNullList.<Ingredient>create();
		for (int i = 0; i < stuff.length; i++)
		{
            Object object = stuff[i];
			if (object instanceof ItemStack)
			{
				inputstacks.add(Ingredient.fromStacks((ItemStack)object));
			}
            else if (object instanceof Item)
            {
                inputstacks.add(Ingredient.fromItem((Item)object));
            }
            else if (object instanceof Block)
            {
                inputstacks.add(Ingredient.fromStacks(new ItemStack((Block)object)));
            }
            else if (object instanceof String)
            {
                inputstacks.add(new net.minecraftforge.oredict.OreIngredient((String)object));
            }
		}
		
        NBTTagCompound tags = new NBTTagCompound();
        tags.setString("renderTexture", entry.getTextureString());
        stack.setTagCompound(tags);
        
		IRecipe shapedrecipe = new RecipeShapelessFramedWood("", stack, inputstacks);
		shapedrecipe.setRegistryName(registryName);
		return shapedrecipe;
	}
	
	@Override
    public ItemStack getCraftingResult(InventoryCrafting inv)
    {
        ItemStack itemstack = this.getRecipeOutput().copy();
        WoodRegistryEntry match;
        String texture = "none";
        for (int i = 0; i < 9; i++)
        {
            if (i >= inv.getSizeInventory()) break;
        	match = foundMatch(inv.getStackInSlot(i));
        	if (match.getIfReal())
        	{
        		texture = match.getTextureString();
        		break;
        	}
        }
        NBTTagCompound tags = new NBTTagCompound();
        tags.setString("renderTexture", texture);
        itemstack.setTagCompound(tags);
        return itemstack;
    }
	
	private WoodRegistryEntry foundMatch(ItemStack stack)
	{
	    if (stack.isEmpty()) return new WoodRegistryEntry("none", "none", "none", false);
	    
		WoodRegistryEntry result = new WoodRegistryEntry("none", "none", "none", false);
		
		for (int i = 0; i < registry.size(); i++)
		{
			WoodRegistryEntry entry = registry.get(i);
			if (entry.hasMatch(stack.getTranslationKey()))
			{
				result = entry;
				break;
			}
		}
		
		return result;
	}
}
