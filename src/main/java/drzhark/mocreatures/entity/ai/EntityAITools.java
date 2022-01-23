package drzhark.mocreatures.entity.ai;

import drzhark.mocreatures.entity.IMoCTameable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAITools {
  protected static boolean IsNearPlayer(EntityLiving entityliving, double d) {
    EntityPlayer entityplayer1 = entityliving.world.getClosestPlayerToEntity((Entity)entityliving, d);
    if (entityplayer1 != null) {
      return true;
    }
    return false;
  }

  protected static EntityPlayer getIMoCTameableOwner(IMoCTameable pet) {
    if (pet.getOwnerId() == null) {
      return null;
    }

    for (int i = 0; i < ((EntityLiving)pet).world.playerEntities.size(); i++) {
      EntityPlayer entityplayer = ((EntityLiving)pet).world.playerEntities.get(i);

      if (pet.getOwnerId().equals(entityplayer.getUniqueID())) {
        return entityplayer;
      }
    }
    return null;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ai\EntityAITools.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
