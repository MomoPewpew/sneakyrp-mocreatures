package drzhark.mocreatures.client.renderer.entity;
import drzhark.mocreatures.client.model.MoCModelGolem;
import drzhark.mocreatures.entity.monster.MoCEntityGolem;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class MoCRenderGolem extends MoCRenderMoC<MoCEntityGolem> {
  public MoCRenderGolem(ModelBase modelbase, float f) {
    super(modelbase, f);
    addLayer(new LayerMoCGolem(this));
  }


  protected ResourceLocation getEntityTexture(MoCEntityGolem par1Entity) {
    return par1Entity.getTexture();
  }

  private class LayerMoCGolem
    implements LayerRenderer<MoCEntityGolem> {
    private final MoCRenderGolem mocRenderer;
    private final MoCModelGolem mocModel = new MoCModelGolem();

    public LayerMoCGolem(MoCRenderGolem render) {
      this.mocRenderer = render;
    }


    public void doRenderLayer(MoCEntityGolem entity, float f, float f1, float f2, float f3, float f4, float f5, float f6) {
      ResourceLocation effectTexture = entity.getEffectTexture();
      if (effectTexture != null) {
        GL11.glDepthMask(false);

        float var4 = entity.ticksExisted + f1;
        MoCRenderGolem.this.bindTexture(effectTexture);
        GL11.glMatrixMode(5890);
        GL11.glLoadIdentity();
        float var5 = var4 * 0.01F;
        float var6 = var4 * 0.01F;
        GL11.glTranslatef(var5, var6, 0.0F);


        GL11.glMatrixMode(5888);
        GL11.glEnable(3042);
        float var7 = 0.5F;
        GL11.glColor4f(var7, var7, var7, 1.0F);
        GL11.glDisable(2896);
        GL11.glBlendFunc(1, 1);

        this.mocModel.setModelAttributes(this.mocRenderer.getMainModel());
        this.mocModel.setLivingAnimations((EntityLivingBase)entity, f, f1, f2);
        this.mocModel.render((Entity)entity, f, f1, f3, f4, f5, f6);
        GL11.glMatrixMode(5890);
        GL11.glLoadIdentity();
        GL11.glMatrixMode(5888);
        GL11.glEnable(2896);
        GL11.glDisable(3042);
      }
    }


    public boolean shouldCombineTextures() {
      return true;
    }
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
