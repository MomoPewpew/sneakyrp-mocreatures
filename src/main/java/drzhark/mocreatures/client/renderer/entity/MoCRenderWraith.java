package drzhark.mocreatures.client.renderer.entity;
import drzhark.mocreatures.client.MoCClientProxy;
import drzhark.mocreatures.entity.monster.MoCEntityWraith;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class MoCRenderWraith extends RenderLiving<MoCEntityWraith> {
  public MoCRenderWraith(ModelBiped modelbiped, float f) {
    super(MoCClientProxy.mc.getRenderManager(), (ModelBase)modelbiped, f);
  }




  public void doRender(MoCEntityWraith entitywraith, double d, double d1, double d2, float f, float f1) {
    boolean flag = false;

    GL11.glPushMatrix();
    GL11.glEnable(3042);
    if (!flag) {
      float transparency = 0.6F;
      GL11.glBlendFunc(770, 771);
      GL11.glColor4f(0.8F, 0.8F, 0.8F, transparency);
    } else {
      GL11.glBlendFunc(770, 1);
    }
    super.doRender(entitywraith, d, d1, d2, f, f1);
    GL11.glDisable(3042);
    GL11.glPopMatrix();
  }



  protected ResourceLocation getEntityTexture(MoCEntityWraith entitywraith) {
    return entitywraith.getTexture();
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderWraith.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
