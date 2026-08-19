package jds.bibliocraft.rendering;

import net.minecraft.nbt.NBTTagCompound;

/**
 * The single source of truth for Clipboard text order and layout.
 *
 * GUI coordinates and model coordinates intentionally live together here so
 * the three render paths cannot silently drift apart again.
 */
public final class ClipboardTextLayout
{
	public static final int TASK_COUNT = 9;
	public static final int ROW_COUNT = TASK_COUNT + 1;

	public static final double MODEL_TEXT_X = 0.037D;
	public static final double MODEL_TITLE_Y = 0.825D;
	public static final double MODEL_TASK_Y = 0.76D;
	public static final double MODEL_TASK_Z = 0.222D;
	/*
	 * First-person item transforms turn the clipboard's Z axis around.  These
	 * coordinates are therefore deliberately separate from the block renderer
	 * coordinates above.
	 */
	public static final double HAND_TITLE_CENTER_Z = 0.5D;
	public static final double HAND_TASK_Z = 0.70D;
	public static final double MODEL_TEXT_SPACING = -0.0658D;
	public static final float MODEL_TEXT_SCALE = 0.0045F;

	/*
	 * These are pixel widths, not character limits.  A character limit cannot
	 * keep the rendered text inside the paper because Minecraft fonts have
	 * different widths for different characters and languages.
	 */
	public static final int DISPLAY_TITLE_WIDTH = 125;
	public static final int DISPLAY_TASK_WIDTH = 115;

	public static final int GUI_TITLE_X = 34;
	public static final int GUI_TITLE_Y = 14;
	public static final int GUI_TITLE_WIDTH = DISPLAY_TITLE_WIDTH;
	public static final int GUI_TASK_X = 44;
	public static final int GUI_TASK_Y = 29;
	public static final int GUI_TASK_WIDTH = DISPLAY_TASK_WIDTH;
	public static final int GUI_ROW_SPACING = 15;

	private final String title;
	private final String[] tasks;

	private ClipboardTextLayout(String title, String[] tasks)
	{
		this.title = title == null ? "" : title;
		this.tasks = new String[TASK_COUNT];
		for (int i = 0; i < TASK_COUNT; i++)
		{
			this.tasks[i] = tasks != null && i < tasks.length && tasks[i] != null ? tasks[i] : "";
		}
	}

	public static ClipboardTextLayout fromPage(NBTTagCompound page)
	{
		if (page == null)
		{
			return new ClipboardTextLayout("", null);
		}

		NBTTagCompound taskTag = page.getCompoundTag("tasks");
		String[] tasks = new String[TASK_COUNT];
		for (int i = 0; i < TASK_COUNT; i++)
		{
			tasks[i] = taskTag.getString("task" + (i + 1));
		}
		return new ClipboardTextLayout(page.getString("title"), tasks);
	}

	public static ClipboardTextLayout fromText(String title, String... tasks)
	{
		return new ClipboardTextLayout(title, tasks);
	}

	public String getTitle()
	{
		return title;
	}

	public String getTask(int index)
	{
		return tasks[index];
	}

	public String getText(int row)
	{
		return row == 0 ? title : getTask(row - 1);
	}

	public double getModelY(int row)
	{
		return row == 0 ? MODEL_TITLE_Y : MODEL_TASK_Y + MODEL_TEXT_SPACING * (row - 1);
	}

	/**
	 * Returns the Z origin used by the wall-mounted clipboard.  The title is
	 * centered in the paper using its final, scaled width; task rows retain
	 * their fixed left alignment beside their checkboxes.
	 */
	public double getModelZ(int row, double renderedTextWidth)
	{
		if (row == 0)
		{
			/*
			 * renderText() starts at the model's centre and rotates the font's
			 * horizontal axis towards decreasing Z.  Moving the origin forward by
			 * half the rendered width therefore places the title's centre exactly
			 * at the centre of the paper.
			 */
			return renderedTextWidth * MODEL_TEXT_SCALE / 2.0D;
		}
		return MODEL_TASK_Z;
	}

	public static int getDisplayWidth(int row)
	{
		return row == 0 ? DISPLAY_TITLE_WIDTH : DISPLAY_TASK_WIDTH;
	}

	/**
	 * Returns a scale that makes a text row fit its paper area without changing
	 * the text stored in NBT.  Short text keeps the normal size; long text is
	 * scaled down just enough to fit.
	 */
	public static float getDisplayScale(int row, int textWidth)
	{
		int displayWidth = getDisplayWidth(row);
		if (textWidth <= 0 || textWidth <= displayWidth)
		{
			return 1.0F;
		}
		return (float)displayWidth / (float)textWidth;
	}

	/**
	 * Returns the first-person Z coordinate for a text row.  The title's
	 * origin is moved by half its rendered width so that it remains centered
	 * when its contents change; task rows stay aligned with the GUI checkbox
	 * column.
	 */
	public double getHandModelZ(int row, double renderedTextWidth)
	{
		if (row == 0)
		{
			/*
			 * The first-person camera transform scales the clipboard and this
			 * text together after this position has been applied.  Therefore the
			 * origin must use the complete model-space width here; multiplying it
			 * by the hand item's scale places long titles too far to the right.
			 */
			return HAND_TITLE_CENTER_Z
					+ renderedTextWidth * MODEL_TEXT_SCALE / 2.0D;
		}
		return HAND_TASK_Z;
	}

	public static int getGuiTaskY(int taskIndex)
	{
		return GUI_TASK_Y + taskIndex * GUI_ROW_SPACING;
	}
}
