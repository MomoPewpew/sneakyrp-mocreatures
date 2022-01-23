package drzhark.mocreatures.client.renderer.entity;
import drzhark.mocreatures.entity.passive.MoCEntityOstrich;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class MoCRenderOstrich extends MoCRenderMoC<MoCEntityOstrich> {
  public MoCRenderOstrich(ModelBase modelbase, float f) {
    super(modelbase, 0.5F);
  }


  protected ResourceLocation getEntityTexture(MoCEntityOstrich entityostrich) {
    return entityostrich.getTexture();
  }

  protected void adjustHeight(MoCEntityOstrich entityliving, float FHeight) {
    GL11.glTranslatef(0.0F, FHeight, 0.0F);
  }


  protected void preRenderCallback(MoCEntityOstrich entityliving, float f) {
    MoCEntityOstrich entityostrich = entityliving;
    if (entityostrich.getType() == 1) {
      stretch(entityostrich);
    }

    super.preRenderCallback(entityliving, f);
  }



  protected void stretch(MoCEntityOstrich entityostrich) {
    float f = entityostrich.getEdad() * 0.01F;
    GL11.glScalef(f, f, f);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderOstrich.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
