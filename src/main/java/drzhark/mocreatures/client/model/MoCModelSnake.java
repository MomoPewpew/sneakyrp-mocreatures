package drzhark.mocreatures.client.model;

import drzhark.mocreatures.entity.passive.MoCEntitySnake;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;


















































































































































































































































































































































































































@SideOnly(Side.CLIENT)
public class MoCModelSnake
  extends ModelBase
{
  ModelRenderer Head;
  ModelRenderer Nose;
  ModelRenderer Tongue0;
  ModelRenderer Tongue;
  ModelRenderer Tongue1;
  ModelRenderer LNose;
  ModelRenderer TeethUR;
  ModelRenderer TeethUL;
  ModelRenderer Tail;
  ModelRenderer Wing1L;
  ModelRenderer Wing1R;
  ModelRenderer Wing2L;
  ModelRenderer Wing2R;
  ModelRenderer Wing3R;
  ModelRenderer Wing3L;
  ModelRenderer Wing4R;
  ModelRenderer Wing4L;
  ModelRenderer Wing5L;
  ModelRenderer Wing5R;
  public ModelRenderer[] bodySnake;
  private final int bodyparts = 40;

  public MoCModelSnake() {
    this.textureWidth = 64;
    this.textureHeight = 32;
    getClass();
    this.bodySnake = new ModelRenderer[40];
    float factor = -0.3F;
    float fsegm = 0.125F;
    float flength = 0.0F;
    float fsep = -1.6F;
    int i = 0;
    getClass();
    for (; i < 40; i++) {
      getClass();
      flength = (40 / 2 - i) * fsep;
      getClass();
      float fport = (i + 1.0F) / 40.0F;
      if (fport < fsegm) {
        factor = -0.2F;
      } else if (fport < fsegm * 2.0F) {
        factor = -0.15F;
      } else if (fport < fsegm * 4.0F) {
        factor = 0.0F;
      } else if (fport < fsegm * 6.0F) {
        factor = 0.0F;
      } else if (fport < fsegm * 7.0F) {
        factor = -0.15F;
      } else {
        factor = -0.2F;
      }
      int j = 0;
      if (i % 2 == 0) {
        j = 0;
      } else {
        j = 4;
      }
      this.bodySnake[i] = new ModelRenderer(this, 8, j);
      this.bodySnake[i].addBox(-1.0F, -0.5F, 0.0F, 2, 2, 2, factor);
      this.bodySnake[i].setRotationPoint(0.0F, 23.0F, flength);
    }
    this.Tail = new ModelRenderer(this, 36, 0);
    this.Tail.addBox(-0.5F, 0.5F, -1.0F, 1, 1, 5);
    this.Tail.setRotationPoint(0.0F, 23.0F, flength);
    getClass();
    flength = (40 / 2) * fsep;
    this.Head = new ModelRenderer(this, 0, 0);
    this.Head.addBox(-1.0F, -0.5F, -2.0F, 2, 2, 2);
    this.Head.setRotationPoint(0.0F, 23.0F, flength);
    this.Nose = new ModelRenderer(this, 16, 0);
    this.Nose.addBox(-0.5F, -0.3F, -4.0F, 1, 1, 2);
    this.Nose.setRotationPoint(0.0F, 23.0F, flength);
    this.LNose = new ModelRenderer(this, 22, 0);
    this.LNose.addBox(-0.5F, 0.3F, -4.0F, 1, 1, 2);
    this.LNose.setRotationPoint(0.0F, 23.0F, flength);
    this.TeethUR = new ModelRenderer(this, 46, 0);
    this.TeethUR.addBox(-0.4F, 0.3F, -3.8F, 0, 1, 1);
    this.TeethUR.setRotationPoint(0.0F, 23.0F, flength);
    this.TeethUL = new ModelRenderer(this, 44, 0);
    this.TeethUL.addBox(0.4F, 0.3F, -3.8F, 0, 1, 1);
    this.TeethUL.setRotationPoint(0.0F, 23.0F, flength);
    this.Tongue = new ModelRenderer(this, 28, 0);
    this.Tongue.addBox(-0.5F, 0.5F, -6.0F, 1, 0, 3);
    this.Tongue.setRotationPoint(0.0F, 23.0F, flength);
    this.Tongue1 = new ModelRenderer(this, 28, 0);
    this.Tongue1.addBox(-0.5F, 0.5F, -5.0F, 1, 0, 3);
    this.Tongue1.setRotationPoint(0.0F, 23.0F, flength);
    this.Tongue0 = new ModelRenderer(this, 28, 0);
    this.Tongue0.addBox(-0.5F, 0.25F, -4.0F, 1, 0, 3);
    this.Tongue0.setRotationPoint(0.0F, 23.0F, flength);
    this.Wing1L = new ModelRenderer(this, 8, 4);
    this.Wing1L.addBox(0.0F, -0.5F, 0.0F, 2, 2, 2);
    getClass();
    this.Wing1L.setRotationPoint(0.0F, 23.0F, (40 / 2 - 1) * fsep);
    this.Wing1R = new ModelRenderer(this, 8, 4);
    this.Wing1R.addBox(-2.0F, -0.5F, 0.0F, 2, 2, 2);
    getClass();
    this.Wing1R.setRotationPoint(0.0F, 23.0F, (40 / 2 - 1) * fsep);
    this.Wing2L = new ModelRenderer(this, 8, 4);
    this.Wing2L.addBox(0.5F, -0.5F, 0.0F, 2, 2, 2);
    getClass();
    this.Wing2L.setRotationPoint(0.0F, 23.0F, (40 / 2 - 2) * fsep);
    this.Wing2R = new ModelRenderer(this, 8, 4);
    this.Wing2R.addBox(-2.5F, -0.5F, 0.0F, 2, 2, 2);
    getClass();
    this.Wing2R.setRotationPoint(0.0F, 23.0F, (40 / 2 - 2) * fsep);
    this.Wing3R = new ModelRenderer(this, 16, 4);
    this.Wing3R.addBox(-3.0F, -0.5F, 0.0F, 2, 2, 2);
    getClass();
    this.Wing3R.setRotationPoint(0.0F, 23.0F, (40 / 2 - 3) * fsep);
    this.Wing3L = new ModelRenderer(this, 16, 4);
    this.Wing3L.addBox(1.0F, -0.5F, 0.0F, 2, 2, 2);
    getClass();
    this.Wing3L.setRotationPoint(0.0F, 23.0F, (40 / 2 - 3) * fsep);
    this.Wing4R = new ModelRenderer(this, 16, 8);
    this.Wing4R.addBox(-2.5F, -0.5F, 0.0F, 2, 2, 2);
    getClass();
    this.Wing4R.setRotationPoint(0.0F, 23.0F, (40 / 2 - 4) * fsep);
    this.Wing4L = new ModelRenderer(this, 16, 8);
    this.Wing4L.addBox(0.5F, -0.5F, 0.0F, 2, 2, 2);
    getClass();
    this.Wing4L.setRotationPoint(0.0F, 23.0F, (40 / 2 - 4) * fsep);
    this.Wing5L = new ModelRenderer(this, 16, 8);
    this.Wing5L.addBox(0.0F, -0.5F, 0.0F, 2, 2, 2);
    getClass();
    this.Wing5L.setRotationPoint(0.0F, 23.0F, (40 / 2 - 5) * fsep);
    this.Wing5R = new ModelRenderer(this, 16, 8);
    this.Wing5R.addBox(-2.0F, -0.5F, 0.0F, 2, 2, 2);
    getClass();
    this.Wing5R.setRotationPoint(0.0F, 23.0F, (40 / 2 - 5) * fsep);
  }

  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    MoCEntitySnake entitysnake = (MoCEntitySnake)entity;
    int typeI = entitysnake.getType();
    float tongueOff = entitysnake.getfTongue();
    float mouthOff = entitysnake.getfMouth();
    float rattleOff = entitysnake.getfRattle();
    boolean climbing = entitysnake.isClimbing();
    boolean isresting = entitysnake.isResting();
    int movInt = entitysnake.getMovInt();
    float f6 = entitysnake.bodyswing;
    boolean nearplayer = entitysnake.getNearPlayer();
    boolean picked = entitysnake.pickedUp();
    setRotationAngles(f3, f4, tongueOff, mouthOff, rattleOff, nearplayer, typeI);
    float sidef = 0.0F;
    float A = 0.4F;
    float w = 1.5F;
    float t = f / 2.0F;
    int i = 0;
    getClass();
    for (; i < 40; i++) {
      float sideperf = 1.0F;
      float yOff = 0.0F;
      GL11.glPushMatrix();
      if (!isresting) {
        getClass();
        if (climbing && i < 40 / 2) {
          getClass();
          yOff = (i - 40 / 2) * 0.08F;
          GL11.glTranslatef(0.0F, yOff / 3.0F, -yOff * 1.2F);
        } else if (nearplayer || picked) {
          getClass();
          if (i < 40 / 3) {
            getClass();
            yOff = (i - 40 / 3) * 0.09F;
            getClass();
            float zOff = (i - 40 / 3) * 0.065F;
            GL11.glTranslatef(0.0F, yOff / 1.5F, -zOff * f6);
          }
          getClass();
          if (i < 40 / 6) {
            sideperf = 0.0F;
          } else {
            getClass();
            sideperf = (i - 7) / 40.0F / 3.0F;
            if (sideperf > 1.0F)
              sideperf = 1.0F;
          }
        }
      }
      getClass();
      if (typeI == 7 && nearplayer && i > 5 * 40 / 6 && !picked) {
        getClass();
        yOff = 0.55F + (i - 40) * 0.08F;
        GL11.glTranslatef(0.0F, -yOff / 1.5F, 0.0F);
      }
      getClass();
      if (picked && i > 40 / 2) {
        getClass();
        yOff = (i - 40 / 2) * 0.08F;
        GL11.glTranslatef(0.0F, yOff / 1.5F, -yOff);
      }
      sidef = 0.5F * MathHelper.sin(w * t - 0.3F * i) - movInt / 20.0F * MathHelper.sin(0.8F * t - 0.2F * i);
      sidef *= sideperf;
      GL11.glTranslatef(sidef, 0.0F, 0.0F);
      this.bodySnake[i].render(f5);
      if (i == 0) {
        this.Head.render(f5);
        this.Nose.render(f5);
        this.LNose.render(f5);
        this.TeethUR.render(f5);
        this.TeethUL.render(f5);
        if (tongueOff != 0.0F) {
          if (mouthOff != 0.0F || tongueOff < 2.0F || tongueOff > 7.0F) {
            this.Tongue1.render(f5);
          } else {
            this.Tongue.render(f5);
          }
        } else {
          this.Tongue0.render(f5);
        }
      }
      if (typeI == 6 && nearplayer) {
        if (i == 1) {
          this.Wing1L.render(f5);
          this.Wing1R.render(f5);
        }
        if (i == 2) {
          this.Wing2L.render(f5);
          this.Wing2R.render(f5);
        }
        if (i == 3) {
          this.Wing3L.render(f5);
          this.Wing3R.render(f5);
        }
        if (i == 4) {
          this.Wing4L.render(f5);
          this.Wing4R.render(f5);
        }
        if (i == 5) {
          this.Wing5L.render(f5);
          this.Wing5R.render(f5);
        }
      }
      getClass();
      if (i == 40 - 1 && typeI == 7)
        this.Tail.render(f5);
      GL11.glPopMatrix();
    }
  }

  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

  public void setRotationAngles(float f3, float f4, float f6, float f7, float frattle, boolean nearP, int type) {
    float rAX = f4 / 57.29578F;
    float rAY = f3 / 57.29578F;
    this.Head.rotateAngleX = rAX;
    this.Head.rotateAngleY = rAY;
    (this.bodySnake[0]).rotateAngleX = rAX * 0.95F;
    (this.bodySnake[1]).rotateAngleX = rAX * 0.9F;
    (this.bodySnake[2]).rotateAngleX = rAX * 0.85F;
    (this.bodySnake[3]).rotateAngleX = rAX * 0.8F;
    (this.bodySnake[4]).rotateAngleX = rAX * 0.75F;
    float f8 = MathHelper.cos(f6 * 10.0F) / 40.0F;
    this.Head.rotateAngleX -= f7;
    this.Head.rotateAngleX += f7;
    this.Head.rotateAngleX += f8;
    this.Head.rotateAngleX += f8;
    this.Tongue0.rotateAngleX = this.LNose.rotateAngleX;
    this.Head.rotateAngleX -= f7;
    this.Head.rotateAngleX -= f7;
    (this.bodySnake[0]).rotateAngleY = 0.0F + rAY * 0.85F;
    (this.bodySnake[1]).rotateAngleY = 0.0F + rAY * 0.65F;
    (this.bodySnake[2]).rotateAngleY = 0.0F + rAY * 0.45F;
    (this.bodySnake[3]).rotateAngleY = 0.0F + rAY * 0.25F;
    (this.bodySnake[4]).rotateAngleY = 0.0F + rAY * 0.1F;
    this.Nose.rotateAngleY = this.Head.rotateAngleY;
    this.Tongue.rotateAngleY = this.Head.rotateAngleY;
    this.Tongue0.rotateAngleY = this.Head.rotateAngleY;
    this.Tongue1.rotateAngleY = this.Head.rotateAngleY;
    this.LNose.rotateAngleY = this.Head.rotateAngleY;
    this.TeethUR.rotateAngleY = this.Head.rotateAngleY;
    this.TeethUL.rotateAngleY = this.Head.rotateAngleY;
    if (type == 6) {
      this.Wing1L.rotateAngleX = (this.bodySnake[1]).rotateAngleX;
      this.Wing1L.rotateAngleY = (this.bodySnake[1]).rotateAngleY;
      this.Wing1R.rotateAngleX = (this.bodySnake[1]).rotateAngleX;
      this.Wing1R.rotateAngleY = (this.bodySnake[1]).rotateAngleY;
      this.Wing2L.rotateAngleX = (this.bodySnake[2]).rotateAngleX;
      this.Wing2L.rotateAngleY = (this.bodySnake[2]).rotateAngleY;
      this.Wing2R.rotateAngleX = (this.bodySnake[2]).rotateAngleX;
      this.Wing2R.rotateAngleY = (this.bodySnake[2]).rotateAngleY;
      this.Wing3L.rotateAngleX = (this.bodySnake[3]).rotateAngleX;
      this.Wing3L.rotateAngleY = (this.bodySnake[3]).rotateAngleY;
      this.Wing3R.rotateAngleX = (this.bodySnake[3]).rotateAngleX;
      this.Wing3R.rotateAngleY = (this.bodySnake[3]).rotateAngleY;
      this.Wing4L.rotateAngleX = (this.bodySnake[4]).rotateAngleX;
      this.Wing4L.rotateAngleY = (this.bodySnake[4]).rotateAngleY;
      this.Wing4R.rotateAngleX = (this.bodySnake[4]).rotateAngleX;
      this.Wing4R.rotateAngleY = (this.bodySnake[4]).rotateAngleY;
      this.Wing5L.rotateAngleX = (this.bodySnake[4]).rotateAngleX;
      this.Wing5L.rotateAngleY = (this.bodySnake[4]).rotateAngleY;
      this.Wing5R.rotateAngleX = (this.bodySnake[4]).rotateAngleX;
      this.Wing5R.rotateAngleY = (this.bodySnake[4]).rotateAngleY;
    }
    if (type == 7)
      if (nearP || frattle != 0.0F) {
        this.Tail.rotateAngleX = (MathHelper.cos(f3 * 10.0F) * 20.0F + 90.0F) / 57.29578F;
      } else {
        this.Tail.rotateAngleX = 0.0F;
      }
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelSnake.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
