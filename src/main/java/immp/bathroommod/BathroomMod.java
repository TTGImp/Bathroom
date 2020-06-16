package immp.bathroommod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import immp.bathroommod.entity.ToiletEntity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.common.Mod;

@Mod(BathroomMod.MODID)
public class BathroomMod {
	public static final String MODID = "bathroommod";

	public static final Logger LOGGER = LogManager.getLogger(MODID);
	
	
	public BathroomMod() {
		LOGGER.debug("Hello from Bathroom Mod!");

		
	}
}
