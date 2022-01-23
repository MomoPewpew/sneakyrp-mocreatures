package drzhark.mocreatures.client.model;

import drzhark.mocreatures.entity.ambient.MoCEntityButterfly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class MoCModelButterfly
  extends ModelBase {
  ModelRenderer Abdomen;
  ModelRenderer FrontLegs;
  ModelRenderer RightAntenna;
  ModelRenderer LeftAntenna;
  ModelRenderer RearLegs;
  ModelRenderer MidLegs;
  ModelRenderer Head;
  ModelRenderer Thorax;
  ModelRenderer WingRight;
  ModelRenderer WingLeft;
  ModelRenderer Mouth;
  ModelRenderer WingLeftFront;
  ModelRenderer WingRightFront;
  ModelRenderer WingRightBack;
  ModelRenderer WingLeftBack;

  public MoCModelButterfly() {
    this.textureWidth = 32;
    this.textureHeight = 32;

    this.Head = new ModelRenderer(this, 0, 11);
    this.Head.addBox(-0.5F, 0.0F, 0.0F, 1, 1, 1);
    this.Head.setRotationPoint(0.0F, 21.9F, -1.3F);
    setRotation(this.Head, -2.171231F, 0.0F, 0.0F);

    this.Mouth = new ModelRenderer(this, 0, 8);
    this.Mouth.addBox(0.0F, 0.0F, 0.0F, 1, 2, 0);
    this.Mouth.setRotationPoint(-0.2F, 22.0F, -2.5F);
    setRotation(this.Mouth, 0.6548599F, 0.0F, 0.0F);

    this.RightAntenna = new ModelRenderer(this, 0, 7);
    this.RightAntenna.addBox(-0.5F, 0.0F, -1.0F, 1, 0, 1);
    this.RightAntenna.setRotationPoint(-0.5F, 21.7F, -2.3F);
    setRotation(this.RightAntenna, -1.041001F, 0.7853982F, 0.0F);

    this.LeftAntenna = new ModelRenderer(this, 4, 7);
    this.LeftAntenna.addBox(-0.5F, 0.0F, -1.0F, 1, 0, 1);
    this.LeftAntenna.setRotationPoint(0.5F, 21.7F, -2.3F);
    setRotation(this.LeftAntenna, -1.041001F, -0.7853982F, 0.0F);

    this.Thorax = new ModelRenderer(this, 0, 0);
    this.Thorax.addBox(-0.5F, 1.5F, -1.0F, 1, 1, 2);
    this.Thorax.setRotationPoint(0.0F, 20.0F, -1.0F);

    this.Abdomen = new ModelRenderer(this, 8, 1);
    this.Abdomen.addBox(-0.5F, 0.0F, -1.0F, 1, 3, 1);
    this.Abdomen.setRotationPoint(0.0F, 21.5F, 0.0F);
    setRotation(this.Abdomen, 1.427659F, 0.0F, 0.0F);

    this.FrontLegs = new ModelRenderer(this, 0, 8);
    this.FrontLegs.addBox(-1.0F, 0.0F, 0.0F, 2, 3, 0);
    this.FrontLegs.setRotationPoint(0.0F, 21.5F, -1.8F);
    setRotation(this.FrontLegs, 0.1487144F, 0.0F, 0.0F);

    this.MidLegs = new ModelRenderer(this, 4, 8);
    this.MidLegs.addBox(-1.0F, 0.0F, 0.0F, 2, 3, 0);
    this.MidLegs.setRotationPoint(0.0F, 22.0F, -1.2F);
    setRotation(this.MidLegs, 0.5948578F, 0.0F, 0.0F);

    this.RearLegs = new ModelRenderer(this, 0, 8);
    this.RearLegs.addBox(-1.0F, 0.0F, 0.0F, 2, 3, 0);
    this.RearLegs.setRotationPoint(0.0F, 22.5F, -0.4F);
    setRotation(this.RearLegs, 1.070744F, 0.0F, 0.0F);

    this.WingLeftFront = new ModelRenderer(this, 4, 20);
    this.WingLeftFront.addBox(0.0F, 0.0F, -4.0F, 8, 0, 6);
    this.WingLeftFront.setRotationPoint(0.3F, 21.4F, -1.0F);

    this.WingLeft = new ModelRenderer(this, 4, 26);
    this.WingLeft.addBox(0.0F, 0.0F, -1.0F, 8, 0, 6);
    this.WingLeft.setRotationPoint(0.3F, 21.5F, -0.5F);

    this.WingLeftBack = new ModelRenderer(this, 4, 0);
    this.WingLeftBack.addBox(0.0F, 0.0F, -1.0F, 5, 0, 8);
    this.WingLeftBack.setRotationPoint(0.3F, 21.2F, -1.0F);
    setRotation(this.WingLeftBack, 0.0F, 0.0F, 0.5934119F);








    this.WingRightFront = new ModelRenderer(this, 4, 8);
    this.WingRightFront.addBox(-8.0F, 0.0F, -4.0F, 8, 0, 6);
    this.WingRightFront.setRotationPoint(-0.3F, 21.4F, -1.0F);

    this.WingRight = new ModelRenderer(this, 4, 14);
    this.WingRight.addBox(-8.0F, 0.0F, -1.0F, 8, 0, 6);
    this.WingRight.setRotationPoint(-0.3F, 21.5F, -0.5F);

    this.WingRightBack = new ModelRenderer(this, 14, 0);
    this.WingRightBack.addBox(-5.0F, 0.0F, -1.0F, 5, 0, 8);
    this.WingRightBack.setRotationPoint(0.3F, 21.2F, -1.0F);
    setRotation(this.WingRightBack, 0.0F, 0.0F, -0.5934119F);
  }


  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    super.render(entity, f, f1, f2, f3, f4, f5);
    MoCEntityButterfly butterfly = (MoCEntityButterfly)entity;
    boolean flying = (butterfly.getIsFlying() || butterfly.motionY < -0.1D);
    setRotationAngles(f, f1, f2, f3, f4, f5, !flying);
    this.Abdomen.render(f5);
    this.FrontLegs.render(f5);
    this.RightAntenna.render(f5);
    this.LeftAntenna.render(f5);
    this.RearLegs.render(f5);
    this.MidLegs.render(f5);
    this.Head.render(f5);
    this.Thorax.render(f5);

    this.Mouth.render(f5);

    GL11.glPushMatrix();
    GL11.glEnable(3042);
    float transparency = 0.8F;
    GL11.glBlendFunc(770, 771);
    GL11.glColor4f(0.8F, 0.8F, 0.8F, transparency);

    this.WingRight.render(f5);
    this.WingLeft.render(f5);
    this.WingRightFront.render(f5);
    this.WingLeftFront.render(f5);
    this.WingRightBack.render(f5);
    this.WingLeftBack.render(f5);
    GL11.glDisable(3042);
    GL11.glPopMatrix();
  }

  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }













  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, boolean onGround) {
    float f2a = f2 % 100.0F;
    float WingRot = 0.0F;
    float legMov = 0.0F;
    float legMovB = 0.0F;

    if (!onGround) {

      WingRot = MathHelper.cos(f2 * 0.9F) * 0.9F;





      legMov = f1 * 1.5F;
      legMovB = legMov;
    } else {
      legMov = MathHelper.cos(f * 1.5F + 3.141593F) * 2.0F * f1;
      legMovB = MathHelper.cos(f * 1.5F) * 2.0F * f1;
      if ((((f2a > 40.0F) ? 1 : 0) & ((f2a < 60.0F) ? 1 : 0)) != 0)
      {
        WingRot = MathHelper.cos(f2 * 0.15F) * 0.9F;
      }
    }


    float baseAngle = 0.52359F;

    this.WingLeft.rotateAngleZ = -baseAngle + WingRot;
    this.WingRight.rotateAngleZ = baseAngle - WingRot;
    this.WingLeftFront.rotateAngleZ = -baseAngle + WingRot;

    this.WingLeftBack.rotateAngleZ = 0.5934119F + -baseAngle + WingRot;
    this.WingRightFront.rotateAngleZ = baseAngle - WingRot;
    this.WingRightBack.rotateAngleZ = -0.5934119F + baseAngle - WingRot;

    this.FrontLegs.rotateAngleX = 0.1487144F + legMov;
    this.MidLegs.rotateAngleX = 0.5948578F + legMovB;
    this.RearLegs.rotateAngleX = 1.070744F + legMov;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelButterfly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
