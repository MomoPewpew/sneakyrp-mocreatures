package drzhark.mocreatures.client.renderer.entity;

import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.client.MoCClientProxy;
import drzhark.mocreatures.client.model.MoCModelWere;
import drzhark.mocreatures.client.model.MoCModelWereHuman;
import drzhark.mocreatures.entity.monster.MoCEntityWerewolf;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MoCRenderWerewolf extends RenderLiving<MoCEntityWerewolf> {
  public MoCRenderWerewolf(MoCModelWereHuman modelwerehuman, ModelBase modelbase, float f) {
    super(MoCClientProxy.mc.getRenderManager(), modelbase, f);
    addLayer(new LayerMoCWereHuman(this));
    this.tempWerewolf = (MoCModelWere)modelbase;
  }


  public void doRender(MoCEntityWerewolf entitywerewolf, double d, double d1, double d2, float f, float f1) {
    this.tempWerewolf.hunched = entitywerewolf.getIsHunched();
    super.doRender(entitywerewolf, d, d1, d2, f, f1);
  }

  private final MoCModelWere tempWerewolf;

  protected ResourceLocation getEntityTexture(MoCEntityWerewolf entitywerewolf) {
    return entitywerewolf.getTexture();
  }

  private class LayerMoCWereHuman
    implements LayerRenderer<MoCEntityWerewolf> {
    private final MoCRenderWerewolf mocRenderer;
    private final MoCModelWereHuman mocModel = new MoCModelWereHuman();

    public LayerMoCWereHuman(MoCRenderWerewolf render) {
      this.mocRenderer = render;
    }

    public void doRenderLayer(MoCEntityWerewolf entity, float f, float f1, float f2, float f3, float f4, float f5, float f6) {
      int myType = entity.getType();

      if (!entity.getIsHumanForm()) {
        MoCRenderWerewolf.this.bindTexture(MoCreatures.proxy.getTexture("wereblank.png"));
      } else {
        switch (myType) {

          case 1:
            MoCRenderWerewolf.this.bindTexture(MoCreatures.proxy.getTexture("weredude.png"));
            break;
          case 2:
            MoCRenderWerewolf.this.bindTexture(MoCreatures.proxy.getTexture("werehuman.png"));
            break;
          case 3:
            MoCRenderWerewolf.this.bindTexture(MoCreatures.proxy.getTexture("wereoldie.png"));
            break;
          case 4:
            MoCRenderWerewolf.this.bindTexture(MoCreatures.proxy.getTexture("werewoman.png"));
            break;
          default:
            MoCRenderWerewolf.this.bindTexture(MoCreatures.proxy.getTexture("wereoldie.png"));
            break;
        }

      }
      this.mocModel.setModelAttributes(this.mocRenderer.getMainModel());
      this.mocModel.setLivingAnimations((EntityLivingBase)entity, f, f1, f2);
      this.mocModel.render((Entity)entity, f, f1, f3, f4, f5, f6);
    }


    public boolean shouldCombineTextures() {
      return true;
    }
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderWerewolf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
