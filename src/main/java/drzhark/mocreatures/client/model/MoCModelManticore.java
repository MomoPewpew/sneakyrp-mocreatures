package drzhark.mocreatures.client.model;

import drzhark.mocreatures.entity.monster.MoCEntityManticore;
import net.minecraft.entity.Entity;

public class MoCModelManticore
  extends MoCModelNewBigCat
{
  public void updateAnimationModifiers(Entity entity) {
    MoCEntityManticore bigcat = (MoCEntityManticore)entity;
    this.isFlyer = true;
    this.isSaddled = bigcat.getIsRideable();
    this.flapwings = (bigcat.wingFlapCounter != 0);
    this.floating = (this.isFlyer && bigcat.isOnAir());
    this.poisoning = bigcat.swingingTail();
    this.isRidden = bigcat.isBeingRidden();
    this.hasMane = true;
    this.hasSaberTeeth = true;
    this.onAir = bigcat.isOnAir();
    this.hasStinger = true;
    this.isMovingVertically = (bigcat.motionY != 0.0D);
    this.hasChest = false;
    this.isTamed = false;
    this.hasChest = false;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelManticore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
