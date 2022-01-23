package drzhark.mocreatures.client.renderer.entity;
import drzhark.mocreatures.entity.passive.MoCEntityBunny;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class MoCRenderBunny extends MoCRenderMoC<MoCEntityBunny> {
  protected ResourceLocation getEntityTexture(MoCEntityBunny entitybunny) {
    return entitybunny.getTexture();
  }

  public MoCRenderBunny(ModelBase modelbase, float f) {
    super(modelbase, f);
  }


  public void doRender(MoCEntityBunny entitybunny, double d, double d1, double d2, float f, float f1) {
    super.doRender(entitybunny, d, d1, d2, f, f1);
  }


  protected float handleRotationFloat(MoCEntityBunny entitybunny, float f) {
    if (!entitybunny.getIsAdult()) {
      stretch(entitybunny);
    }
    return entitybunny.ticksExisted + f;
  }


  protected void preRenderCallback(MoCEntityBunny entitybunny, float f) {
    rotBunny(entitybunny);
    adjustOffsets(entitybunny.getAdjustedXOffset(), entitybunny.getAdjustedYOffset(), entitybunny.getAdjustedZOffset());
  }

  protected void rotBunny(MoCEntityBunny entitybunny) {
    if (!entitybunny.onGround && entitybunny.getRidingEntity() == null) {
      if (entitybunny.motionY > 0.5D) {
        GL11.glRotatef(35.0F, -1.0F, 0.0F, 0.0F);
      } else if (entitybunny.motionY < -0.5D) {
        GL11.glRotatef(-35.0F, -1.0F, 0.0F, 0.0F);
      } else {
        GL11.glRotatef((float)(entitybunny.motionY * 70.0D), -1.0F, 0.0F, 0.0F);
      }
    }
  }

  protected void stretch(MoCEntityBunny entitybunny) {
    float f = entitybunny.getEdad() * 0.01F;
    GL11.glScalef(f, f, f);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderBunny.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
