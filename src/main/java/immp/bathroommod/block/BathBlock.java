package immp.bathroommod.block;

import java.util.stream.Stream;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.World;

public class BathBlock extends Block {
	
	private static final VoxelShape SHAPE = Stream.of(
			Block.makeCuboidShape(0.062, 0, 0, 0.938, 0.062, 1),
			Block.makeCuboidShape(0.188, 0.062, 0, 0.812, 0.5, 0.25),
			Block.makeCuboidShape(0.25, 0.5, 0.062, 0.375, 0.562, 0.188),
			Block.makeCuboidShape(0.438, 0.5, 0.062, 0.562, 0.688, 0.125),
			Block.makeCuboidShape(0.438, 0.625, 0.062, 0.562, 0.688, 0.375),
			Block.makeCuboidShape(0.625, 0.5, 0.062, 0.75, 0.562, 0.188),
			Block.makeCuboidShape(0.062, 0.062, 0, 0.188, 0.5, 1),
		    Block.makeCuboidShape(0.812, 0.062, 0, 0.938, 0.5, 1))
			.reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR))
			.get();
	
	public BathBlock(final Properties properties) {
		super(properties);
		
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
	{
		world.setBlockState(pos.offset(placer.getHorizontalFacing()), BathBlock.getStateById(0),11);
	}
}
