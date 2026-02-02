package jds.bibliocraft.tileentities;

import jds.bibliocraft.blocks.BiblioWoodBlock.EnumWoodType;
import jds.bibliocraft.blocks.BlockFurniturePaneler;
import jds.bibliocraft.blocks.blockitems.BiblioWoodBlockItem;
import jds.bibliocraft.containers.ContainerFurniturePaneler;
import jds.bibliocraft.items.*;
import jds.bibliocraft.network.BiblioNetworking;
import jds.bibliocraft.network.packet.client.BiblioPanelerClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class TileEntityFurniturePaneler extends BiblioTileEntity
{
	public EntityPlayer playerFromBlock = null;
	public ContainerFurniturePaneler eventHandler;
	
	public String customCraftingTex = "none";
	
	public TileEntityFurniturePaneler()
	{
		super(3, true);
	}
	
	public boolean checkIfFramedBiblioCraftBlock(ItemStack block)
	{
		if (block != ItemStack.EMPTY && block.getItemDamage() == EnumWoodType.FRAME.getID())
		{
			if (block.getItem() instanceof BiblioWoodBlockItem ||
					block.getItem() instanceof ItemSeatBack ||
					block.getItem() instanceof ItemSeatBack2 ||
					block.getItem() instanceof ItemSeatBack3 ||
					block.getItem() instanceof ItemSeatBack4 ||
					block.getItem() instanceof ItemSeatBack5)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean addItemsToBlock(ItemStack stack, int slot, EntityPlayer player)
	{
		ItemStack currStack = getStackInSlot(slot);
		if (currStack == ItemStack.EMPTY)
		{
			setInventorySlotContents(slot, stack);
			updateRecipeManager();
			return true;
		}
		return false;
	}
	
	public void updateCraftingTexture()
	{
		// ByteBuf buffer = Unpooled.buffer();
    	// ByteBufUtils.writeItemStack(buffer, getStackInSlot(0));
    	// //ByteBufUtils.writeItemStack(buffer, getStackInSlot(1));
    	// buffer.writeInt(this.pos.getX());
    	// buffer.writeInt(this.pos.getY());
    	// buffer.writeInt(this.pos.getZ());
		if (this.playerFromBlock != null)
		{
			BiblioNetworking.INSTANCE.sendTo(new BiblioPanelerClient(getStackInSlot(0), this.pos), (EntityPlayerMP) this.playerFromBlock);
			// BiblioCraft.ch_BiblioPaneler.sendTo(new FMLProxyPacket(new PacketBuffer(buffer), "BiblioPaneler"), (EntityPlayerMP) this.playerFromBlock);
		}
	}
	
	public void updateRecipeManager()
	{
		ItemStack input = getStackInSlot(1);
		ItemStack panels = getStackInSlot(0);
		ItemStack output = ItemStack.EMPTY;
		if (input != ItemStack.EMPTY && panels != ItemStack.EMPTY && !this.customCraftingTex.equals("none"))
		{
			output = input.copy();
			NBTTagCompound tags = output.getTagCompound();
			if (tags == null)
			{
				tags = new NBTTagCompound();
			}
			tags.setString("renderTexture", this.customCraftingTex); 
			output.setTagCompound(tags);
			output.setCount(1);
		}
		setInventorySlotContents(2, output);
	}
	
	public void updateRecipeManagerFromServer(ItemStack stack)
	{
		setInventorySlotContents(2, stack);
	}
	
	public void executeRecipe()
	{
		ItemStack input = getStackInSlot(1);
		ItemStack panel = getStackInSlot(0);
		if (input.getCount() > 1)
		{
			input.setCount(input.getCount() - 1);
			setInventorySlotContents(1, input);
		}
		else
		{
			setInventorySlotContents(1, ItemStack.EMPTY);
		}
		if (panel.getCount() > 1)
		{
			panel.setCount(panel.getCount() - 1);
			setInventorySlotContents(0, panel);
		}
		else
		{
			setInventorySlotContents(0, ItemStack.EMPTY);
		}
	}
	
	public void setCustomCraftingTex(String tex)
	{
		this.customCraftingTex = tex;
		updateRecipeManager();
		getWorld().notifyBlockUpdate(getPos(), getWorld().getBlockState(getPos()), getWorld().getBlockState(getPos()), 3);
	}
	
	public String getCustomCraftingTex()
	{
		return this.customCraftingTex;
	}
	
	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack)
	{
		return false;
	}

	@Override
	public String getName() 
	{
		return BlockFurniturePaneler.name;
	}

	@Override
	public void setInventorySlotContentsAdditionalCommands(int slot, ItemStack stack) 
	{
		if (slot != 2)
		{
			updateRecipeManager();
		}

		if (slot == 0)
		{
			updateCraftingTexture();
		}
	}

	@Override
	public void loadCustomNBTData(NBTTagCompound nbt) {
		
		this.customCraftingTex = nbt.getString("customCraftingTexture");
	}

	@Override
	public NBTTagCompound writeCustomNBTData(NBTTagCompound nbt) 
	{
		nbt.setString("customCraftingTexture", this.customCraftingTex);
		return nbt;
	}

	@Override
	public ITextComponent getDisplayName() 
	{
		ITextComponent chat = new TextComponentString(getName());
		return chat;
	}


}
