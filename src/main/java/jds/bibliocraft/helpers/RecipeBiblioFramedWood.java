package jds.bibliocraft.helpers;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;

public class RecipeBiblioFramedWood extends ShapedRecipes
{
	private static ArrayList<WoodRegistryEntry> registry;

	public RecipeBiblioFramedWood(String group, int width, int height, NonNullList<Ingredient> ingredientsIn, ItemStack output) 
	{
		super(group, width, height, ingredientsIn, output);
		if (registry == null)
			registry = new ArrayList<WoodRegistryEntry>();
		
	}
	
	public static IRecipe addShapedWoodRecipe(ResourceLocation registryName, ItemStack stack, WoodRegistryEntry entry, Object ... stuff)
	{
		if (registry == null)
			registry = new ArrayList<WoodRegistryEntry>();
		
		registry.add(entry);
		
		String recipe = "";
		int i = 0;
		int width = 0;
		int height = 0;

        if (stuff[i] instanceof String[])
        {
            String[] astring = (String[])((String[])stuff[i++]);

            for (int l = 0; l < astring.length; ++l)
            {
                String s1 = astring[l];
                ++height;
                width = s1.length();
                recipe = recipe + s1;
            }
        }
        else
        {
            while (stuff[i] instanceof String)
            {
                String s2 = (String)stuff[i++];
                ++height;
                width = s2.length();
                recipe = recipe + s2;
            }
        }

        HashMap<Character, Ingredient> hashmap = new HashMap<Character, Ingredient>();

        for (; i < stuff.length; i += 2)
        {
            Character character = (Character)stuff[i];
            Object object = stuff[i + 1];
            Ingredient ingredient = Ingredient.EMPTY;

            if (object instanceof Item)
            {
                ingredient = Ingredient.fromItem((Item)object);
            }
            else if (object instanceof Block)
            {
                ingredient = Ingredient.fromStacks(new ItemStack((Block)object, 1, 32767));
            }
            else if (object instanceof ItemStack)
            {
                ingredient = Ingredient.fromStacks((ItemStack)object);
            }
            else if (object instanceof String)
            {
                ingredient = new net.minecraftforge.oredict.OreIngredient((String)object);
            }

            hashmap.put(character, ingredient);
        }

        NonNullList<Ingredient> stackarray = NonNullList.<Ingredient>withSize(width * height, Ingredient.EMPTY);

        for (int j = 0; j < width * height; ++j)
        {
            char c0 = recipe.charAt(j);

            if (hashmap.containsKey(Character.valueOf(c0)))
            {
                stackarray.set(j, hashmap.get(Character.valueOf(c0)));
            }
        }

        NBTTagCompound tags = new NBTTagCompound();
        tags.setString("renderTexture", entry.getTextureString());
        stack.setTagCompound(tags);
		IRecipe shapedrecipe = new RecipeBiblioFramedWood("", width, height, stackarray, stack);
		shapedrecipe.setRegistryName(registryName);
		return shapedrecipe;
	}
	
	@Override
    public ItemStack getCraftingResult(InventoryCrafting inv)
    {
        ItemStack itemstack = this.getRecipeOutput().copy();
        WoodRegistryEntry match;
        String texture = "none";
        for (int i = 0; i < 9; i++) // 3x3 grid
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
