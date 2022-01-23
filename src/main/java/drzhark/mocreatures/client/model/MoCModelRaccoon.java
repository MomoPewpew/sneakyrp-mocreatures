package drzhark.mocreatures.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class MoCModelRaccoon
  extends ModelBase
{
  ModelRenderer Head;
  ModelRenderer Snout;
  ModelRenderer RightEar;
  ModelRenderer LeftEar;
  ModelRenderer LeftSideburn;
  ModelRenderer RightSideburn;
  ModelRenderer RightRearFoot;
  ModelRenderer Neck;
  ModelRenderer Body;
  ModelRenderer TailA;
  ModelRenderer TailB;
  ModelRenderer RightFrontLegA;
  ModelRenderer RightFrontLegB;
  ModelRenderer RightFrontFoot;
  ModelRenderer LeftFrontLegA;
  ModelRenderer LeftFrontLegB;
  ModelRenderer LeftFrontFoot;
  ModelRenderer RightRearLegA;
  ModelRenderer RightRearLegB;
  ModelRenderer LeftRearLegB;
  ModelRenderer LeftRearLegA;
  ModelRenderer LeftRearFoot;
  private float radianF = 57.29578F;

  public MoCModelRaccoon() {
    this.textureWidth = 64;
    this.textureHeight = 64;

    this.Head = new ModelRenderer(this, 38, 21);
    this.Head.addBox(-4.0F, -3.5F, -6.5F, 8, 6, 5);
    this.Head.setRotationPoint(0.0F, 17.0F, -4.0F);











    this.RightSideburn = new ModelRenderer(this, 0, 32);
    this.RightSideburn.addBox(-3.0F, -2.0F, -2.0F, 3, 4, 4);
    this.RightSideburn.setRotationPoint(-2.5F, 0.5F, -3.2F);
    setRotation(this.RightSideburn, 0.0F, -0.5235988F, 0.0F);
    this.Head.addChild(this.RightSideburn);

    this.LeftSideburn = new ModelRenderer(this, 0, 40);
    this.LeftSideburn.addBox(0.0F, -2.0F, -2.0F, 3, 4, 4);
    this.LeftSideburn.setRotationPoint(2.5F, 0.5F, -3.2F);
    setRotation(this.LeftSideburn, 0.0F, 0.5235988F, 0.0F);
    this.Head.addChild(this.LeftSideburn);

    this.Snout = new ModelRenderer(this, 24, 25);
    this.Snout.addBox(-1.5F, -0.5F, -10.5F, 3, 3, 4);
    this.Snout.setRotationPoint(0.0F, 17.0F, -4.0F);

    this.RightEar = new ModelRenderer(this, 24, 22);
    this.RightEar.addBox(-4.0F, -5.5F, -3.5F, 3, 2, 1);
    this.RightEar.setRotationPoint(0.0F, 17.0F, -4.0F);

    this.LeftEar = new ModelRenderer(this, 24, 18);
    this.LeftEar.addBox(1.0F, -5.5F, -3.5F, 3, 2, 1);
    this.LeftEar.setRotationPoint(0.0F, 17.0F, -4.0F);

    this.RightRearFoot = new ModelRenderer(this, 46, 0);
    this.RightRearFoot.addBox(-5.0F, 5.0F, -2.0F, 3, 1, 3);
    this.RightRearFoot.setRotationPoint(0.0F, 18.0F, 4.0F);

    this.Neck = new ModelRenderer(this, 46, 4);
    this.Neck.addBox(-2.5F, -2.0F, -3.0F, 5, 4, 3);
    this.Neck.setRotationPoint(0.0F, 17.0F, -4.0F);
    setRotation(this.Neck, -0.4461433F, 0.0F, 0.0F);

    this.Body = new ModelRenderer(this, 0, 0);
    this.Body.addBox(-3.0F, 0.0F, -3.0F, 6, 6, 12);
    this.Body.setRotationPoint(0.0F, 15.0F, -2.0F);

    this.TailA = new ModelRenderer(this, 0, 3);
    this.TailA.addBox(-1.5F, -6.0F, -1.5F, 3, 6, 3);
    this.TailA.setRotationPoint(0.0F, 16.5F, 6.5F);
    setRotation(this.TailA, -2.024582F, 0.0F, 0.0F);

    this.TailB = new ModelRenderer(this, 24, 3);
    this.TailB.addBox(-1.5F, -11.0F, 0.3F, 3, 6, 3);
    this.TailB.setRotationPoint(0.0F, 16.5F, 6.5F);
    setRotation(this.TailB, -1.689974F, 0.0F, 0.0F);

    this.RightFrontLegA = new ModelRenderer(this, 36, 0);
    this.RightFrontLegA.addBox(-4.0F, -1.0F, -1.0F, 2, 5, 3);
    this.RightFrontLegA.setRotationPoint(0.0F, 18.0F, -4.0F);
    setRotation(this.RightFrontLegA, 0.5205006F, 0.0F, 0.0F);

    this.RightFrontLegB = new ModelRenderer(this, 46, 11);
    this.RightFrontLegB.addBox(-3.5F, 1.0F, 2.0F, 2, 4, 2);
    this.RightFrontLegB.setRotationPoint(0.0F, 18.0F, -4.0F);
    setRotation(this.RightFrontLegB, -0.3717861F, 0.0F, 0.0F);

    this.RightFrontFoot = new ModelRenderer(this, 46, 0);
    this.RightFrontFoot.addBox(-4.0F, 5.0F, -1.0F, 3, 1, 3);
    this.RightFrontFoot.setRotationPoint(0.0F, 18.0F, -4.0F);

    this.LeftFrontLegA = new ModelRenderer(this, 36, 8);
    this.LeftFrontLegA.addBox(2.0F, -1.0F, -1.0F, 2, 5, 3);
    this.LeftFrontLegA.setRotationPoint(0.0F, 18.0F, -4.0F);
    setRotation(this.LeftFrontLegA, 0.5205006F, 0.0F, 0.0F);

    this.LeftFrontLegB = new ModelRenderer(this, 54, 11);
    this.LeftFrontLegB.addBox(1.5F, 1.0F, 2.0F, 2, 4, 2);
    this.LeftFrontLegB.setRotationPoint(0.0F, 18.0F, -4.0F);
    setRotation(this.LeftFrontLegB, -0.3717861F, 0.0F, 0.0F);

    this.LeftFrontFoot = new ModelRenderer(this, 46, 0);
    this.LeftFrontFoot.addBox(1.0F, 5.0F, -1.0F, 3, 1, 3);
    this.LeftFrontFoot.setRotationPoint(0.0F, 18.0F, -4.0F);

    this.RightRearLegA = new ModelRenderer(this, 12, 18);
    this.RightRearLegA.addBox(-5.0F, -2.0F, -3.0F, 2, 5, 4);
    this.RightRearLegA.setRotationPoint(0.0F, 18.0F, 4.0F);
    setRotation(this.RightRearLegA, 0.9294653F, 0.0F, 0.0F);

    this.RightRearLegB = new ModelRenderer(this, 0, 27);
    this.RightRearLegB.addBox(-4.5F, 2.0F, -5.0F, 2, 2, 3);
    this.RightRearLegB.setRotationPoint(0.0F, 18.0F, 4.0F);
    setRotation(this.RightRearLegB, 0.9294653F, 0.0F, 0.0F);

    this.LeftRearLegB = new ModelRenderer(this, 10, 27);
    this.LeftRearLegB.addBox(2.5F, 2.0F, -5.0F, 2, 2, 3);
    this.LeftRearLegB.setRotationPoint(0.0F, 18.0F, 4.0F);
    setRotation(this.LeftRearLegB, 0.9294653F, 0.0F, 0.0F);

    this.LeftRearLegA = new ModelRenderer(this, 0, 18);
    this.LeftRearLegA.addBox(3.0F, -2.0F, -3.0F, 2, 5, 4);
    this.LeftRearLegA.setRotationPoint(0.0F, 18.0F, 4.0F);
    setRotation(this.LeftRearLegA, 0.9294653F, 0.0F, 0.0F);

    this.LeftRearFoot = new ModelRenderer(this, 46, 0);
    this.LeftRearFoot.addBox(2.0F, 5.0F, -2.0F, 3, 1, 3);
    this.LeftRearFoot.setRotationPoint(0.0F, 18.0F, 4.0F);
  }



  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    this.Head.render(f5);
    this.Snout.render(f5);
    this.RightEar.render(f5);
    this.LeftEar.render(f5);


    this.RightRearFoot.render(f5);
    this.Neck.render(f5);
    this.Body.render(f5);
    this.TailA.render(f5);
    this.TailB.render(f5);
    this.RightFrontLegA.render(f5);
    this.RightFrontLegB.render(f5);
    this.RightFrontFoot.render(f5);
    this.LeftFrontLegA.render(f5);
    this.LeftFrontLegB.render(f5);
    this.LeftFrontFoot.render(f5);
    this.RightRearLegA.render(f5);
    this.RightRearLegB.render(f5);
    this.LeftRearLegB.render(f5);
    this.LeftRearLegA.render(f5);
    this.LeftRearFoot.render(f5);
  }

  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
    this.Head.rotateAngleY = f3 / 57.29578F;
    this.Head.rotateAngleX = f4 / 57.29578F;
    this.Snout.rotateAngleY = this.Head.rotateAngleY;
    this.Snout.rotateAngleX = this.Head.rotateAngleX;
    this.RightEar.rotateAngleX = this.Head.rotateAngleX;
    this.RightEar.rotateAngleY = this.Head.rotateAngleY;
    this.LeftEar.rotateAngleX = this.Head.rotateAngleX;
    this.LeftEar.rotateAngleY = this.Head.rotateAngleY;





    float RLegXRot = MathHelper.cos(f * 1.0F + 3.141593F) * 0.8F * f1;
    float LLegXRot = MathHelper.cos(f * 1.0F) * 0.8F * f1;

    this.RightFrontLegA.rotateAngleX = 30.0F / this.radianF + RLegXRot;
    this.LeftFrontLegA.rotateAngleX = 30.0F / this.radianF + LLegXRot;
    this.RightRearLegA.rotateAngleX = 53.0F / this.radianF + LLegXRot;
    this.LeftRearLegA.rotateAngleX = 53.0F / this.radianF + RLegXRot;

    this.RightFrontLegB.rotateAngleX = -21.0F / this.radianF + RLegXRot;
    this.RightFrontFoot.rotateAngleX = RLegXRot;
    this.LeftFrontLegB.rotateAngleX = -21.0F / this.radianF + LLegXRot;
    this.LeftFrontFoot.rotateAngleX = LLegXRot;

    this.RightRearLegB.rotateAngleX = 53.0F / this.radianF + LLegXRot;
    this.RightRearFoot.rotateAngleX = LLegXRot;
    this.LeftRearLegB.rotateAngleX = 53.0F / this.radianF + RLegXRot;
    this.LeftRearFoot.rotateAngleX = RLegXRot;

    this.TailA.rotateAngleY = MathHelper.cos(f * 0.6662F) * 0.7F * f1;
    this.TailB.rotateAngleY = this.TailA.rotateAngleY;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelRaccoon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
