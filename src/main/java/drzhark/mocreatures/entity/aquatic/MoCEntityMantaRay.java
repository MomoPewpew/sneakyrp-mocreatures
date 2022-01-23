package drzhark.mocreatures.entity.aquatic;

import drzhark.mocreatures.MoCreatures;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MoCEntityMantaRay
  extends MoCEntityRay {
  public MoCEntityMantaRay(World world) {
    super(world);
    setSize(1.8F, 1.0F);
    setEdad(80 + this.rand.nextInt(100));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
  }


  public int getMaxEdad() {
    return 180;
  }


  public ResourceLocation getTexture() {
    return MoCreatures.proxy.getTexture("mantray.png");
  }


  public boolean isMantaRay() {
    return true;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\aquatic\MoCEntityMantaRay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
