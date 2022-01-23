package drzhark.mocreatures.client.renderer.entity;
import drzhark.mocreatures.client.MoCClientProxy;
import drzhark.mocreatures.client.model.MoCModelCrocodile;
import drzhark.mocreatures.entity.passive.MoCEntityCrocodile;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class MoCRenderCrocodile extends RenderLiving<MoCEntityCrocodile> {
  public MoCRenderCrocodile(MoCModelCrocodile modelbase, float f) {
    super(MoCClientProxy.mc.getRenderManager(), (ModelBase)modelbase, f);
    this.croc = modelbase;
  }
  public MoCModelCrocodile croc;

  protected ResourceLocation getEntityTexture(MoCEntityCrocodile entitycrocodile) {
    return entitycrocodile.getTexture();
  }


  public void doRender(MoCEntityCrocodile entitycrocodile, double d, double d1, double d2, float f, float f1) {
    super.doRender(entitycrocodile, d, d1, d2, f, f1);
  }


  protected void preRenderCallback(MoCEntityCrocodile entitycrocodile, float f) {
    this.croc.biteProgress = entitycrocodile.biteProgress;
    this.croc.swimming = entitycrocodile.isSwimming();
    this.croc.resting = entitycrocodile.getIsSitting();
    if (entitycrocodile.isSpinning()) {
      spinCroc(entitycrocodile, (EntityLiving)entitycrocodile.getRidingEntity());
    }
    stretch(entitycrocodile);
    if (entitycrocodile.getIsSitting() &&
      !entitycrocodile.isInsideOfMaterial(Material.WATER)) {
      adjustHeight(entitycrocodile, 0.2F);
    }
  }







  protected void rotateAnimal(MoCEntityCrocodile entitycrocodile) {}






  protected void adjustHeight(MoCEntityCrocodile entitycrocodile, float FHeight) {
    GL11.glTranslatef(0.0F, FHeight, 0.0F);
  }

  protected void spinCroc(MoCEntityCrocodile entitycrocodile, EntityLiving prey) {
    int intSpin = entitycrocodile.spinInt;
    int direction = 1;
    if (intSpin > 40) {
      intSpin -= 40;
      direction = -1;
    }
    int intEndSpin = intSpin;
    if (intSpin >= 20) {
      intEndSpin = 20 - intSpin - 20;
    }
    if (intEndSpin == 0) {
      intEndSpin = 1;
    }
    float f3 = (intEndSpin - 1.0F) / 20.0F * 1.6F;
    f3 = MathHelper.sqrt(f3);
    if (f3 > 1.0F) {
      f3 = 1.0F;
    }
    f3 *= direction;
    GL11.glRotatef(f3 * 90.0F, 0.0F, 0.0F, 1.0F);

    if (prey != null) {
      prey.deathTime = intEndSpin;
    }
  }


  protected void stretch(MoCEntityCrocodile entitycrocodile) {
    float f = entitycrocodile.getEdad() * 0.01F;

    GL11.glScalef(f, f, f);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderCrocodile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
