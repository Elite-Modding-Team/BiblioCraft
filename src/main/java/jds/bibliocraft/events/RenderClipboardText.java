package jds.bibliocraft.events;

import jds.bibliocraft.Config;
import jds.bibliocraft.items.ItemClipboard;
import jds.bibliocraft.rendering.ClipboardTextLayout;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderClipboardText
{
	private static final float HAND_TEXT_SCALE = 0.0036F;
	private static final double HAND_TEXT_Y_OFFSET = -0.44D;
	private static final double HAND_TEXT_Z_OFFSET = 0.20D;

	@SubscribeEvent
	public void renderItem(RenderSpecificHandEvent event)
	{
		Minecraft mc = Minecraft.getMinecraft();
		ItemStack stack = event.getItemStack();
		if (!Config.enableClipboard || mc.player == null || mc.gameSettings.thirdPersonView != 0
				|| stack.isEmpty() || stack.getItem() != ItemClipboard.instance)
		{
			return;
		}

		try
		{
			render(mc, event, stack);
		}
		catch (Throwable throwable)
		{
			System.err.println("Failed to render text on clipboard");
			throwable.printStackTrace();
		}
	}

	private void render(Minecraft mc, RenderSpecificHandEvent event, ItemStack stack)
	{
		boolean isRightHand = (event.getHand() == EnumHand.MAIN_HAND)
				== (mc.player.getPrimaryHand() == EnumHandSide.RIGHT);
		ClipboardTextLayout textLayout = getTextLayout(stack);
		for (int row = 0; row < ClipboardTextLayout.ROW_COUNT; row++)
		{
			renderText(mc, event, stack, textLayout.getText(row),
					textLayout.getModelY(row) + HAND_TEXT_Y_OFFSET,
					textLayout.getHandModelZ(row) + HAND_TEXT_Z_OFFSET, isRightHand);
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

	private void renderText(Minecraft mc, RenderSpecificHandEvent event, ItemStack stack, String text,
			double y, double z, boolean isRightHand)
	{
		GlStateManager.pushMatrix();
		try
		{
			applyFirstPersonItemTransform(mc, event, stack, isRightHand);
			GlStateManager.translate(ClipboardTextLayout.MODEL_TEXT_X, y, z);
			GlStateManager.depthMask(false);
			GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
			GlStateManager.scale(HAND_TEXT_SCALE, HAND_TEXT_SCALE, HAND_TEXT_SCALE);
			mc.fontRenderer.drawString(text, 0, 0, 0x000000, false);
		}
		finally
		{
			GlStateManager.depthMask(true);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.popMatrix();
		}
	}

	private void applyFirstPersonItemTransform(Minecraft mc, RenderSpecificHandEvent event, ItemStack stack,
			boolean isRightHand)
	{
		EnumHandSide handSide = isRightHand ? EnumHandSide.RIGHT : EnumHandSide.LEFT;
		boolean activelyUsingClipboard = mc.player.isHandActive()
				&& mc.player.getItemInUseCount() > 0
				&& mc.player.getActiveHand() == event.getHand()
				&& stack.getItemUseAction() == EnumAction.NONE;
		if (activelyUsingClipboard)
		{
			transformSideFirstPerson(handSide, event.getEquipProgress());
		}
		else
		{
			float swingProgress = event.getSwingProgress();
			float swingX = -0.4F * MathHelper.sin(MathHelper.sqrt(swingProgress) * (float)Math.PI);
			float swingY = 0.2F * MathHelper.sin(MathHelper.sqrt(swingProgress) * ((float)Math.PI * 2F));
			float swingZ = -0.2F * MathHelper.sin(swingProgress * (float)Math.PI);
			int handSign = isRightHand ? 1 : -1;
			GlStateManager.translate(handSign * swingX, swingY, swingZ);
			transformSideFirstPerson(handSide, event.getEquipProgress());
			transformFirstPerson(handSide, swingProgress);
		}

		IBakedModel model = mc.getRenderItem().getItemModelWithOverrides(stack, mc.world, mc.player);
		TransformType transformType = isRightHand ? TransformType.FIRST_PERSON_RIGHT_HAND
				: TransformType.FIRST_PERSON_LEFT_HAND;
		ForgeHooksClient.handleCameraTransforms(model, transformType, !isRightHand);
	}

	private void transformSideFirstPerson(EnumHandSide hand, float equipProgress)
	{
		int handSign = hand == EnumHandSide.RIGHT ? 1 : -1;
		GlStateManager.translate(handSign * 0.56F, -0.52F + equipProgress * -0.6F, -0.72F);
	}

	private void transformFirstPerson(EnumHandSide hand, float swingProgress)
	{
		int handSign = hand == EnumHandSide.RIGHT ? 1 : -1;
		float swing = MathHelper.sin(swingProgress * swingProgress * (float)Math.PI);
		GlStateManager.rotate(handSign * (45.0F + swing * -20.0F), 0.0F, 1.0F, 0.0F);
		float swingRoot = MathHelper.sin(MathHelper.sqrt(swingProgress) * (float)Math.PI);
		GlStateManager.rotate(handSign * swingRoot * -20.0F, 0.0F, 0.0F, 1.0F);
		GlStateManager.rotate(swingRoot * -80.0F, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(handSign * -45.0F, 0.0F, 1.0F, 0.0F);
	}
}
