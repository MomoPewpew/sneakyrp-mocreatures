package drzhark.mocreatures.client.renderer.entity;
import drzhark.mocreatures.entity.MoCEntityInsect;
import drzhark.mocreatures.entity.ambient.MoCEntityButterfly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

@SideOnly(Side.CLIENT)
public class MoCRenderButterfly extends MoCRenderInsect<MoCEntityButterfly> {
  public MoCRenderButterfly(ModelBase modelbase) {
    super(modelbase);
  }



  protected void preRenderCallback(MoCEntityButterfly entitybutterfly, float par2) {
    if (entitybutterfly.isOnAir() || !entitybutterfly.onGround) {
      adjustHeight(entitybutterfly, entitybutterfly.tFloat());
    }
    if (entitybutterfly.climbing()) {
      rotateAnimal(entitybutterfly);
    }
    stretch(entitybutterfly);
  }

  protected void adjustHeight(MoCEntityButterfly entitybutterfly, float FHeight) {
    GL11.glTranslatef(0.0F, FHeight, 0.0F);
  }


  protected ResourceLocation getEntityTexture(MoCEntityButterfly entitybutterfly) {
    return entitybutterfly.getTexture();
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderButterfly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
