package jds.bibliocraft.statemappers;

import com.google.common.collect.Maps;
import jds.bibliocraft.blocks.BlockMarkerPole;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;

import java.util.Map;

public class MarkerPoleStateMapper extends DefaultStateMapper
{

	public final static MarkerPoleStateMapper instance = new MarkerPoleStateMapper();
	
	public MarkerPoleStateMapper() {}
	
	@Override
	public Map putStateModelLocations(Block block)
	{
		Map modelLocations = Maps.newLinkedHashMap();
		if (block instanceof BlockMarkerPole)
		{
			ModelResourceLocation modelLocation = new ModelResourceLocation("bibliocraft:" + BlockMarkerPole.name); 
			BlockMarkerPole marker = (BlockMarkerPole)block;
			IBlockState state = marker.getDefaultState();
			modelLocations.put(state, modelLocation);
		}
		return modelLocations;
	}
}
