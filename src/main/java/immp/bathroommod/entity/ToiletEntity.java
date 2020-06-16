package immp.bathroommod.entity;

import java.util.List;


import immp.bathroommod.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ToiletEntity extends Entity {

    private BlockPos source;
    
	public ToiletEntity(World world)
	{
        super(ModEntities.LOOSEAT, world);
        this.noClip = true;
	}
	
    private ToiletEntity(World world, BlockPos source, double yOffset)
    {
        this(world);
        this.source = source;
        this.setPosition(source.getX() + 0.5, source.getY() + yOffset, source.getZ() + 0.5);
    }
	
	@Override
	protected void registerData() {}

	@Override
	protected void readAdditional(CompoundNBT tag) {}

	@Override
	protected void writeAdditional(CompoundNBT tag) {}

	@Override
	public IPacket<?> createSpawnPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
    @Override
    public void tick()
    {
        super.tick();
        if(source == null)
        {
            source = this.getPosition();
        }
        if(!this.world.isRemote)
        {
            if(this.getPassengers().isEmpty() || this.world.isAirBlock(source))
            {
                this.remove();
                world.updateComparatorOutputLevel(getPosition(), world.getBlockState(getPosition()).getBlock());
            }
        }
    }
	
    @Override
    protected boolean canBeRidden(Entity entity)
    {
        return true;
    }
	
    public static boolean create(World world, BlockPos pos, double yOffset, PlayerEntity player)
    {
        if(!world.isRemote)
        {
            List<ToiletEntity> toilets = world.getEntitiesWithinAABB(ToiletEntity.class, new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1.0, pos.getY() + 1.0, pos.getZ() + 1.0));
            if(toilets.isEmpty())
            {
            	ToiletEntity seat = new ToiletEntity(world, pos, yOffset);
                world.addEntity(seat);
                player.startRiding(seat, false);
            }
        }
        return true;
    }
}
