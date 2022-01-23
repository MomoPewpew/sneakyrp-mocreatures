package drzhark.mocreatures.client.renderer.entity;
import drzhark.mocreatures.entity.MoCEntityInsect;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class MoCRenderInsect<T extends MoCEntityInsect> extends MoCRenderMoC<T> {
  public MoCRenderInsect(ModelBase modelbase) {
    super(modelbase, 0.0F);
  }



  protected void preRenderCallback(T entityinsect, float par2) {
    if (entityinsect.climbing()) {
      rotateAnimal(entityinsect);
    }

    stretch(entityinsect);
  }

  protected void rotateAnimal(T entityinsect) {
    GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
  }

  protected void stretch(T entityinsect) {
    float sizeFactor = entityinsect.getSizeFactor();
    GL11.glScalef(sizeFactor, sizeFactor, sizeFactor);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderInsect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
