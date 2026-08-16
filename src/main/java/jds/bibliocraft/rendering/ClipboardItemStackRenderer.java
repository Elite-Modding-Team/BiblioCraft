package jds.bibliocraft.rendering;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.lwjgl.opengl.GL11;

public class ClipboardItemStackRenderer extends TileEntityItemStackRenderer
{
	private static ItemStack pendingHandStack;

	public static void renderTextForNextHand(ItemStack stack)
	{
		pendingHandStack = stack;
	}

	@Override
	public void renderByItem(ItemStack stack)
	{
		Minecraft mc = Minecraft.getMinecraft();
		RenderItem renderItem = mc.getRenderItem();
		IBakedModel model = renderItem.getItemModelWithOverrides(stack, mc.world, mc.player);
		boolean renderText = pendingHandStack != null && ItemStack.areItemStacksEqual(pendingHandStack, stack);
		pendingHandStack = null;

		/* RenderItem has already applied the camera transform and -0.5 model offset. */
		renderItem.renderModel(model, stack);
		if (renderText)
		{
			renderText(mc, stack);
		}
	}

	private void renderText(Minecraft mc, ItemStack stack)
	{
		ClipboardTextLayout textLayout = getTextLayout(stack);
		GlStateManager.pushMatrix();
		try
		{
			GlStateManager.translate(ClipboardTextLayout.MODEL_TEXT_X, 0.0D, 0.0D);
			GlStateManager.depthFunc(GL11.GL_ALWAYS);
			GlStateManager.depthMask(false);
			for (int row = 0; row < ClipboardTextLayout.ROW_COUNT; row++)
			{
				GlStateManager.pushMatrix();
				try
				{
					String text = textLayout.getText(row);
					GlStateManager.translate(0.0D, textLayout.getModelY(row),
							textLayout.getHandModelZ(row, mc.fontRenderer.getStringWidth(text)));
					GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
					GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
					GlStateManager.scale(ClipboardTextLayout.MODEL_TEXT_SCALE,
							ClipboardTextLayout.MODEL_TEXT_SCALE, ClipboardTextLayout.MODEL_TEXT_SCALE);
					mc.fontRenderer.drawString(text, 0, 0, 0x000000, false);
				}
				finally
				{
					GlStateManager.popMatrix();
				}
			}
		}
		finally
		{
			GlStateManager.depthFunc(GL11.GL_LEQUAL);
			GlStateManager.depthMask(true);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.popMatrix();
		}
	}

	private ClipboardTextLayout getTextLayout(ItemStack stack)
	{
		NBTTagCompound clipboardTags = stack.getTagCompound();
		if (clipboardTags == null)
		{
			return ClipboardTextLayout.fromPage(null);
		}

		int currentPage = Math.max(1, clipboardTags.getInteger("currentPage"));
		return ClipboardTextLayout.fromPage(clipboardTags.getCompoundTag("page" + currentPage));
	}
}
