package drzhark.mocreatures.client.renderer.entity;

import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.client.MoCClientProxy;
import drzhark.mocreatures.entity.IMoCEntity;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class MoCRenderMoC<T extends EntityLiving> extends RenderLiving<T> {
  public MoCRenderMoC(ModelBase modelbase, float f) {
    super(MoCClientProxy.mc.getRenderManager(), modelbase, f);
  }


  public void doRender(T entity, double d, double d1, double d2, float f, float f1) {
    doRenderMoC(entity, d, d1, d2, f, f1);
  }

  public void doRenderMoC(T entity, double d, double d1, double d2, float f, float f1) {
    super.doRender(entity, d, d1, d2, f, f1);
    IMoCEntity entityMoC = (IMoCEntity)entity;
    boolean flag = (MoCreatures.proxy.getDisplayPetName() && !entityMoC.getPetName().isEmpty());
    boolean flag1 = MoCreatures.proxy.getDisplayPetHealth();
    if (entityMoC.renderName()) {
      float f2 = 1.6F;
      float f3 = 0.01666667F * f2;
      float f5 = ((Entity)entityMoC).getDistance(this.renderManager.renderViewEntity);
      if (f5 < 16.0F) {
        String s = "";
        s = s + entityMoC.getPetName();
        float f7 = 0.1F;
        FontRenderer fontrenderer = getFontRendererFromRenderManager();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d + 0.0F, (float)d1 + f7, (float)d2);
        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(-f3, -f3, f3);
        GL11.glDisable(2896);
        Tessellator tessellator1 = Tessellator.getInstance();
        int yOff = entityMoC.nameYOffset();
        if (flag1) {
          GL11.glDisable(3553);
          if (!flag) {
            yOff += 8;
          }
          tessellator1.getBuffer().begin(7, DefaultVertexFormats.POSITION_COLOR);

          float f8 = ((EntityLiving)entityMoC).getHealth();
          float f9 = ((EntityLiving)entityMoC).getMaxHealth();
          float f10 = f8 / f9;
          float f11 = 40.0F * f10;
          tessellator1.getBuffer().pos((-20.0F + f11), (-10 + yOff), 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
          tessellator1.getBuffer().pos((-20.0F + f11), (-6 + yOff), 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
          tessellator1.getBuffer().pos(20.0D, (-6 + yOff), 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
          tessellator1.getBuffer().pos(20.0D, (-10 + yOff), 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
          tessellator1.getBuffer().pos(-20.0D, (-10 + yOff), 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
          tessellator1.getBuffer().pos(-20.0D, (-6 + yOff), 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
          tessellator1.getBuffer().pos((f11 - 20.0F), (-6 + yOff), 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
          tessellator1.getBuffer().pos((f11 - 20.0F), (-10 + yOff), 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
          tessellator1.draw();
          GL11.glEnable(3553);
        }
        if (flag) {
          GL11.glDepthMask(false);
          GL11.glDisable(2929);
          GL11.glEnable(3042);
          GL11.glBlendFunc(770, 771);
          GL11.glDisable(3553);
          tessellator1.getBuffer().begin(7, DefaultVertexFormats.POSITION_COLOR);
          int i = fontrenderer.getStringWidth(s) / 2;
          tessellator1.getBuffer().pos((-i - 1), (-1 + yOff), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
          tessellator1.getBuffer().pos((-i - 1), (8 + yOff), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
          tessellator1.getBuffer().pos((i + 1), (8 + yOff), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
          tessellator1.getBuffer().pos((i + 1), (-1 + yOff), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
          tessellator1.draw();
          GL11.glEnable(3553);
          fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, yOff, 553648127);
          GL11.glEnable(2929);
          GL11.glDepthMask(true);
          fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, yOff, -1);
          GL11.glDisable(3042);
          GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
        GL11.glEnable(2896);
        GL11.glPopMatrix();
      }
    }
  }


  protected void stretch(IMoCEntity mocreature) {
    float f = mocreature.getSizeFactor();
    if (f != 0.0F) {
      GL11.glScalef(f, f, f);
    }
  }


  protected void preRenderCallback(T entityliving, float f) {
    IMoCEntity mocreature = (IMoCEntity)entityliving;
    super.preRenderCallback(entityliving, f);


    adjustPitch(mocreature);
    adjustRoll(mocreature);
    adjustYaw(mocreature);
    stretch(mocreature);
  }







  protected void adjustPitch(IMoCEntity mocreature) {
    float f = mocreature.pitchRotationOffset();

    if (f != 0.0F) {
      GL11.glRotatef(f, -1.0F, 0.0F, 0.0F);
    }
  }






  protected void adjustRoll(IMoCEntity mocreature) {
    float f = mocreature.rollRotationOffset();

    if (f != 0.0F) {
      GL11.glRotatef(f, 0.0F, 0.0F, -1.0F);
    }
  }

  protected void adjustYaw(IMoCEntity mocreature) {
    float f = mocreature.yawRotationOffset();
    if (f != 0.0F) {
      GL11.glRotatef(f, 0.0F, -1.0F, 0.0F);
    }
  }





  protected void adjustOffsets(float xOffset, float yOffset, float zOffset) {
    GL11.glTranslatef(xOffset, yOffset, zOffset);
  }


  protected ResourceLocation getEntityTexture(EntityLiving entity) {
    return ((IMoCEntity)entity).getTexture();
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderMoC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
