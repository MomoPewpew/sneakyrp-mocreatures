package drzhark.mocreatures.client.model;

import drzhark.mocreatures.entity.passive.MoCEntityPetScorpion;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MoCModelPetScorpion
  extends MoCModelScorpion
{
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    MoCEntityPetScorpion scorpy = (MoCEntityPetScorpion)entity;
    this.poisoning = scorpy.swingingTail();
    this.isTalking = (scorpy.mouthCounter != 0);
    this.babies = scorpy.getHasBabies();
    this.attacking = scorpy.armCounter;
    this.sitting = scorpy.getIsSitting();
    setRotationAngles(f, f1, f2, f3, f4, f5);
    renderParts(f5);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelPetScorpion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
