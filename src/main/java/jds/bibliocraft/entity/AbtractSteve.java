package jds.bibliocraft.entity;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.world.World;

import java.util.UUID;

public class AbtractSteve extends AbstractClientPlayer
{
	private static GameProfile gp = new GameProfile(UUID.randomUUID(), "BiblioSteve");
	
	public AbtractSteve(World world)
	{
		super(world, gp);
		setInvisible(true);
	}
}
