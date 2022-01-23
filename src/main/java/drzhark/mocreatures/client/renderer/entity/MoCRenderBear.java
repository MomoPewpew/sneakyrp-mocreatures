package drzhark.mocreatures.client.renderer.entity;
import drzhark.mocreatures.client.model.MoCModelBear;
import drzhark.mocreatures.entity.passive.MoCEntityBear;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

@SideOnly(Side.CLIENT)
public class MoCRenderBear extends MoCRenderMoC<MoCEntityBear> {
  public MoCRenderBear(MoCModelBear modelbase, float f) {
    super(modelbase, f);
  }


  protected void preRenderCallback(MoCEntityBear entitybear, float f) {
    stretch(entitybear);
    super.preRenderCallback(entitybear, f);
  }


  protected void stretch(MoCEntityBear entitybear) {
    float sizeFactor = entitybear.getEdad() * 0.01F;
    if (entitybear.getIsAdult()) {
      sizeFactor = 1.0F;
    }
    sizeFactor *= entitybear.getBearSize();
    GL11.glScalef(sizeFactor, sizeFactor, sizeFactor);
  }


  protected ResourceLocation getEntityTexture(MoCEntityBear entitybear) {
    return entitybear.getTexture();
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderBear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
