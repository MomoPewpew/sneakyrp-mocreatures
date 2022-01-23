package drzhark.mocreatures.client.renderer.entity;
import drzhark.mocreatures.entity.passive.MoCEntityMouse;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class MoCRenderMouse extends MoCRenderMoC<MoCEntityMouse> {
  public MoCRenderMouse(ModelBase modelbase, float f) {
    super(modelbase, f);
  }


  public void doRender(MoCEntityMouse entitymouse, double d, double d1, double d2, float f, float f1) {
    super.doRender(entitymouse, d, d1, d2, f, f1);
  }


  protected float handleRotationFloat(MoCEntityMouse entitymouse, float f) {
    stretch(entitymouse);
    return entitymouse.ticksExisted + f;
  }


  protected void preRenderCallback(MoCEntityMouse entitymouse, float f) {
    if (entitymouse.upsideDown()) {
      upsideDown(entitymouse);
    }

    if (entitymouse.climbing()) {
      rotateAnimal(entitymouse);
    }
  }

  protected void rotateAnimal(MoCEntityMouse entitymouse) {
    GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
  }

  protected void stretch(MoCEntityMouse entitymouse) {
    float f = 0.6F;
    GL11.glScalef(f, f, f);
  }

  protected void upsideDown(MoCEntityMouse entitymouse) {
    GL11.glRotatef(-90.0F, -1.0F, 0.0F, 0.0F);

    GL11.glTranslatef(-0.55F, 0.0F, 0.0F);
  }


  protected ResourceLocation getEntityTexture(MoCEntityMouse entitymouse) {
    return entitymouse.getTexture();
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderMouse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
