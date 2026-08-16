package jds.bibliocraft.events;

import jds.bibliocraft.Config;
import jds.bibliocraft.items.ItemClipboard;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderClipboardText 
{
	// thanks to Vazkii and https://github.com/Vazkii/Botania/blob/master/src/main/java/vazkii/botania/client/core/handler/RenderLexicon.java for figuring this out
	private static final float HAND_TEXT_SCALE = 0.0036F;
	private static final double TEXT_SPACING = -0.0658D;
	
	@SubscribeEvent
	public void renderItem(RenderSpecificHandEvent event)
	{
		// this only ever runs in first person mode
		Minecraft mc = Minecraft.getMinecraft();
		boolean is1stperson = mc.gameSettings.thirdPersonView == 0;
		ItemStack stack = mc.player.getHeldItem(event.getHand());
		if (Config.enableClipboard && stack.getItem() == ItemClipboard.instance && is1stperson)
		{
			try
			{
				render(mc, event, stack);
			}
			catch (Throwable throwable) 
			{
				System.out.println("Failed to render text on clipboard");
			}
		}
	}
	
	private void render(Minecraft mc, RenderSpecificHandEvent event, ItemStack stack) throws Throwable
	{
		boolean isRightHand = (event.getHand() == EnumHand.MAIN_HAND) == (Minecraft.getMinecraft().player.getPrimaryHand() == EnumHandSide.RIGHT);
		NBTTagCompound cliptags = stack.getTagCompound();
    	if (cliptags != null)
    	{
    		int currentPage = cliptags.getInteger("currentPage");
    		String pagenum = "page"+currentPage;
    		NBTTagCompound pagetag = cliptags.getCompoundTag(pagenum);
    		if (pagetag != null)
			{
				NBTTagCompound tasks = pagetag.getCompoundTag("tasks");
				if (tasks != null && event.getEquipProgress() == 0.0 && event.getSwingProgress() == 0.0)
				{
					renderText(mc, event, stack, pagetag.getString("title"), isRightHand, 0);
					renderText(mc, event, stack, tasks.getString("task1"), isRightHand, 1);
					renderText(mc, event, stack, tasks.getString("task2"), isRightHand, 2);
					renderText(mc, event, stack, tasks.getString("task3"), isRightHand, 3);
					renderText(mc, event, stack, tasks.getString("task4"), isRightHand, 4);
					renderText(mc, event, stack, tasks.getString("task5"), isRightHand, 5);
					renderText(mc, event, stack, tasks.getString("task6"), isRightHand, 6);
					renderText(mc, event, stack, tasks.getString("task7"), isRightHand, 7);
					renderText(mc, event, stack, tasks.getString("task8"), isRightHand, 8);
					renderText(mc, event, stack, tasks.getString("task9"), isRightHand, 9);
				}
			}
		}
	}
	
	private void renderText(Minecraft mc, RenderSpecificHandEvent event, ItemStack stack, String text,
			boolean isRightHand, int verticalPos)
	{
		GlStateManager.pushMatrix();
		applyFirstPersonItemTransform(mc, event, stack, isRightHand);
		double y = verticalPos == 0 ? 0.825D : 0.76D + TEXT_SPACING * (verticalPos - 1);
		double z = verticalPos == 0 ? 0.5D : 0.222D;
		GlStateManager.translate(0.037D, y, z);
		GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
		GlStateManager.scale(HAND_TEXT_SCALE, HAND_TEXT_SCALE, HAND_TEXT_SCALE);
		int textX = verticalPos == 0 ? -mc.fontRenderer.getStringWidth(text) / 2 : 0;
		mc.fontRenderer.drawString(text, textX, 0, 0x000000, false); 
		GlStateManager.popMatrix();
	}

	private void applyFirstPersonItemTransform(Minecraft mc, RenderSpecificHandEvent event, ItemStack stack,
			boolean isRightHand)
	{
		int handSign = isRightHand ? 1 : -1;
		GlStateManager.translate(handSign * 0.56F, -0.52F + event.getEquipProgress() * -0.6F, -0.72F);

		IBakedModel model = mc.getRenderItem().getItemModelWithOverrides(stack, mc.world, mc.player);
		ItemCameraTransforms.TransformType transformType = isRightHand
				? ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND
				: ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND;
		ForgeHooksClient.handleCameraTransforms(model, transformType, !isRightHand);
	}
}
