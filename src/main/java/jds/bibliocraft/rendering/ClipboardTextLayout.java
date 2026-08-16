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
	public static final double MODEL_TITLE_Z = 0.27D;
	public static final double HAND_TITLE_Z = 0.27D;
	public static final double MODEL_TASK_Z = 0.222D;
	public static final double MODEL_TEXT_SPACING = -0.0658D;
	public static final float MODEL_TEXT_SCALE = 0.0045F;

	public static final int GUI_TITLE_X = 34;
	public static final int GUI_TITLE_Y = 14;
	public static final int GUI_TITLE_WIDTH = 125;
	public static final int GUI_TASK_X = 44;
	public static final int GUI_TASK_Y = 29;
	public static final int GUI_TASK_WIDTH = 115;
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

	public double getModelZ(int row)
	{
		return row == 0 ? MODEL_TITLE_Z : MODEL_TASK_Z;
	}

	public double getHandModelZ(int row)
	{
		return row == 0 ? HAND_TITLE_Z : MODEL_TASK_Z;
	}

	public boolean isTitle(int row)
	{
		return row == 0;
	}

	public static int getGuiTaskY(int taskIndex)
	{
		return GUI_TASK_Y + taskIndex * GUI_ROW_SPACING;
	}
}
