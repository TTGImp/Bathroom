package immp.bathroommod.block;

import java.util.ArrayList;
import java.util.List;

import immp.bathroommod.entity.ToiletEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.util.math.shapes.VoxelShape;

public class ToiletBlock extends Block   {
	
	public static final DirectionProperty FACING  = BlockStateProperties.HORIZONTAL_FACING;
	public final VoxelShape N_SHAPE;
	public final VoxelShape W_SHAPE;
	public final VoxelShape E_SHAPE;
	public final VoxelShape S_SHAPE;

	public ToiletBlock() {
		super(Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL));
		this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH));
		N_SHAPE = this.generateShape("north");
		W_SHAPE = this.generateShape("west");
		S_SHAPE = this.generateShape("south");
		E_SHAPE = this.generateShape("east");
    }	

    private VoxelShape generateShape(String facing)
    {
    	List<VoxelShape> shapes = new ArrayList<>();
    	
    	switch(facing) {
    	case "north":
    		shapes.add(VoxelShapes.create(0.25, 0, 0.25, 0.75, 0.062, 0.75));
    		shapes.add(VoxelShapes.create(0.375, 0.062, 0.375, 0.625, 0.125, 0.625));
    		shapes.add(VoxelShapes.create(0.375, 0.125, 0.375, 0.625, 0.188, 0.688));
    		shapes.add(VoxelShapes.create(0.25, 0.188, 0.25, 0.375, 0.5, 0.75));
    		shapes.add(VoxelShapes.create(0.625, 0.188, 0.25, 0.75, 0.5, 0.75));
    		shapes.add(VoxelShapes.create(0.312, 0.188, 0.25, 0.688, 0.5, 0.375));
    		shapes.add(VoxelShapes.create(0.312, 0.188, 0.688, 0.688, 0.5, 0.812));
    		shapes.add(VoxelShapes.create(0.438, 0.25, 0.125, 0.562, 0.375, 0.312));
    		shapes.add(VoxelShapes.create(0.438, 0.25, 0.062, 0.562, 0.562, 0.188));
    		shapes.add(VoxelShapes.create(0.125, 0.562, 0, 0.875, 0.938, 0.25));
    		shapes.add(VoxelShapes.create(0.062, 0.812, 0.125, 0.125, 0.875, 0.188));
    		shapes.add(VoxelShapes.create(0.062, 0.938, 0, 0.938, 1, 0.312));
    		break;
    	case "west":
    		shapes.add(VoxelShapes.create(0.25, 0, 0.25, 0.75, 0.062, 0.75));
    		shapes.add(VoxelShapes.create(0.375, 0.062, 0.375, 0.625, 0.125, 0.625));
    		shapes.add(VoxelShapes.create(0.312, 0.125, 0.375, 0.625, 0.188, 0.625));
    		shapes.add(VoxelShapes.create(0.25, 0.188, 0.25, 0.75, 0.5, 0.375));
    		shapes.add(VoxelShapes.create(0.25, 0.188, 0.625, 0.75, 0.5, 0.75));
    		shapes.add(VoxelShapes.create(0.625, 0.188, 0.312, 0.75, 0.5, 0.688));
    		shapes.add(VoxelShapes.create(0.188, 0.188, 0.312, 0.312, 0.5, 0.688));
    		shapes.add(VoxelShapes.create(0.688, 0.25, 0.438, 0.875, 0.375, 0.562));
    		shapes.add(VoxelShapes.create(0.812, 0.25, 0.438, 0.938, 0.562, 0.562));
    		shapes.add(VoxelShapes.create(0.75, 0.562, 0.125, 1, 0.938, 0.875));
    		shapes.add(VoxelShapes.create(0.812, 0.812, 0.062, 0.875, 0.875, 0.125));
    		shapes.add(VoxelShapes.create(0.688, 0.938, 0.062, 1, 1, 0.938));
    		break;
    	case "south":
    		shapes.add(VoxelShapes.create(0.25, 0, 0.25, 0.75, 0.062, 0.75));
    		shapes.add(VoxelShapes.create(0.375, 0.062, 0.375, 0.625, 0.125, 0.625));
    		shapes.add(VoxelShapes.create(0.375, 0.125, 0.312, 0.625, 0.188, 0.625));
    		shapes.add(VoxelShapes.create(0.625, 0.188, 0.25, 0.75, 0.5, 0.75));
    		shapes.add(VoxelShapes.create(0.25, 0.188, 0.25, 0.375, 0.5, 0.75));
    		shapes.add(VoxelShapes.create(0.312, 0.188, 0.625, 0.688, 0.5, 0.75));
    		shapes.add(VoxelShapes.create(0.312, 0.188, 0.188, 0.688, 0.5, 0.312));
    		shapes.add(VoxelShapes.create(0.438, 0.25, 0.688, 0.562, 0.375, 0.875));
    		shapes.add(VoxelShapes.create(0.438, 0.25, 0.812, 0.562, 0.562, 0.938));
    		shapes.add(VoxelShapes.create(0.125, 0.562, 0.75, 0.875, 0.938, 1));
    		shapes.add(VoxelShapes.create(0.875, 0.812, 0.812, 0.938, 0.875, 0.875));
    		shapes.add(VoxelShapes.create(0.062, 0.938, 0.688, 0.938, 1, 1));
    		break;
    	case "east":
    		shapes.add(VoxelShapes.create(0.25, 0, 0.25, 0.75, 0.062, 0.75));
    		shapes.add(VoxelShapes.create(0.375, 0.062, 0.375, 0.625, 0.125, 0.625));
    		shapes.add(VoxelShapes.create(0.375, 0.125, 0.375, 0.688, 0.188, 0.625));
    		shapes.add(VoxelShapes.create(0.25, 0.188, 0.625, 0.75, 0.5, 0.75));
    		shapes.add(VoxelShapes.create(0.25, 0.188, 0.25, 0.75, 0.5, 0.375));
    		shapes.add(VoxelShapes.create(0.25, 0.188, 0.312, 0.375, 0.5, 0.688));
    		shapes.add(VoxelShapes.create(0.688, 0.188, 0.312, 0.812, 0.5, 0.688));
    		shapes.add(VoxelShapes.create(0.125, 0.25, 0.438, 0.312, 0.375, 0.562));
    		shapes.add(VoxelShapes.create(0.062, 0.25, 0.438, 0.188, 0.562, 0.562));
    		shapes.add(VoxelShapes.create(0, 0.562, 0.125, 0.25, 0.938, 0.875));
    		shapes.add(VoxelShapes.create(0.125, 0.812, 0.875, 0.188, 0.875, 0.938));
    		shapes.add(VoxelShapes.create(0, 0.938, 0.062, 0.312, 1, 0.938));
    		break;
    	}
    	
		VoxelShape result = VoxelShapes.empty();
		for(VoxelShape shape : shapes)
			result = VoxelShapes.combine(result, shape, IBooleanFunction.OR);

		return result;
    }
    
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos,  ISelectionContext context) {
	    switch(state.get(FACING))
	    {
    	case NORTH:
    		return N_SHAPE;
    	case WEST:
    		return W_SHAPE;
    	case SOUTH:
    		return S_SHAPE;
    	case EAST:
    		return E_SHAPE;
    	default:
    		return N_SHAPE;
	    }
	}
	

	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
	    switch(state.get(FACING))
	    {
    	case NORTH:
    		return N_SHAPE;
    	case WEST:
    		return W_SHAPE;
    	case SOUTH:
    		return S_SHAPE;
    	case EAST:
    		return E_SHAPE;
    	default:
    		return N_SHAPE;
	    }
	}
	
    @Override
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.CUTOUT_MIPPED;
	}
	
    @Override
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult)
    {
        return ToiletEntity.create(world, pos, 0.3, player);
    }
    
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation rotation)
    {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror)
    {
    	return state.rotate(mirror.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
    }
    
}

