package drzhark.mocreatures.client.model;
import drzhark.mocreatures.entity.aquatic.MoCEntityRay;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.Entity;
@SideOnly(Side.CLIENT)
public class MoCModelRay extends ModelBase { public boolean isMantaRay; public boolean attacking;
  ModelRenderer Tail;
  ModelRenderer Body;
  ModelRenderer Right;
  ModelRenderer Left;
  ModelRenderer BodyU;
  ModelRenderer RWingA;
  ModelRenderer RWingB;
  ModelRenderer RWingC;
  ModelRenderer RWingD;
  ModelRenderer RWingE;

  public MoCModelRay() {
    this.textureWidth = 64;
    this.textureHeight = 32;

    this.Body = new ModelRenderer(this, 26, 0);
    this.Body.addBox(-4.0F, -1.0F, 0.0F, 8, 2, 11);
    this.Body.setRotationPoint(0.0F, 22.0F, -5.0F);

    this.Right = new ModelRenderer(this, 10, 26);
    this.Right.addBox(-0.5F, -1.0F, -4.0F, 1, 2, 4);
    this.Right.setRotationPoint(-3.0F, 22.0F, -4.8F);

    this.Left = new ModelRenderer(this, 0, 26);
    this.Left.addBox(-0.5F, -1.0F, -4.0F, 1, 2, 4);
    this.Left.setRotationPoint(3.0F, 22.0F, -4.8F);

    this.BodyU = new ModelRenderer(this, 0, 11);
    this.BodyU.addBox(-3.0F, -1.0F, 0.0F, 6, 1, 8);
    this.BodyU.setRotationPoint(0.0F, 21.0F, -4.0F);

    this.Tail = new ModelRenderer(this, 30, 15);
    this.Tail.addBox(-0.5F, -0.5F, 1.0F, 1, 1, 16);
    this.Tail.setRotationPoint(0.0F, 22.0F, 8.0F);

    this.BodyTail = new ModelRenderer(this, 0, 20);
    this.BodyTail.addBox(-1.8F, -0.5F, -3.2F, 5, 1, 5);
    this.BodyTail.setRotationPoint(0.0F, 22.0F, 7.0F);
    setRotation(this.BodyTail, 0.0F, 1.0F, 0.0F);

    this.RWingA = new ModelRenderer(this, 0, 0);
    this.RWingA.addBox(-3.0F, -0.5F, -5.0F, 3, 1, 10);
    this.RWingA.setRotationPoint(-4.0F, 22.0F, 1.0F);

    this.RWingB = new ModelRenderer(this, 2, 2);
    this.RWingB.addBox(-6.0F, -0.5F, -4.0F, 3, 1, 8);
    this.RWingB.setRotationPoint(-4.0F, 22.0F, 1.0F);

    this.RWingC = new ModelRenderer(this, 5, 4);
    this.RWingC.addBox(-8.0F, -0.5F, -3.0F, 2, 1, 6);
    this.RWingC.setRotationPoint(-4.0F, 22.0F, 1.0F);

    this.RWingD = new ModelRenderer(this, 6, 5);
    this.RWingD.addBox(-10.0F, -0.5F, -2.5F, 2, 1, 5);
    this.RWingD.setRotationPoint(-4.0F, 22.0F, 1.0F);

    this.RWingE = new ModelRenderer(this, 7, 6);
    this.RWingE.addBox(-12.0F, -0.5F, -2.0F, 2, 1, 4);
    this.RWingE.setRotationPoint(-4.0F, 22.0F, 1.0F);

    this.RWingF = new ModelRenderer(this, 8, 7);
    this.RWingF.addBox(-14.0F, -0.5F, -1.5F, 2, 1, 3);
    this.RWingF.setRotationPoint(-4.0F, 22.0F, 1.0F);

    this.RWingG = new ModelRenderer(this, 9, 8);
    this.RWingG.addBox(-16.0F, -0.5F, -1.0F, 2, 1, 2);
    this.RWingG.setRotationPoint(-4.0F, 22.0F, 1.0F);

    this.LWingA = new ModelRenderer(this, 0, 0);
    this.LWingA.addBox(0.0F, -0.5F, -5.0F, 3, 1, 10);
    this.LWingA.setRotationPoint(4.0F, 22.0F, 1.0F);
    this.LWingA.mirror = true;

    this.LWingB = new ModelRenderer(this, 2, 2);
    this.LWingB.addBox(3.0F, -0.5F, -4.0F, 3, 1, 8);
    this.LWingB.setRotationPoint(4.0F, 22.0F, 1.0F);
    this.LWingB.mirror = true;

    this.LWingC = new ModelRenderer(this, 5, 4);
    this.LWingC.addBox(6.0F, -0.5F, -3.0F, 2, 1, 6);
    this.LWingC.setRotationPoint(4.0F, 22.0F, 1.0F);
    this.LWingC.mirror = true;

    this.LWingD = new ModelRenderer(this, 6, 5);
    this.LWingD.addBox(8.0F, -0.5F, -2.5F, 2, 1, 5);
    this.LWingD.setRotationPoint(4.0F, 22.0F, 1.0F);
    this.LWingD.mirror = true;

    this.LWingE = new ModelRenderer(this, 7, 6);
    this.LWingE.addBox(10.0F, -0.5F, -2.0F, 2, 1, 4);
    this.LWingE.setRotationPoint(4.0F, 22.0F, 1.0F);
    this.LWingE.mirror = true;

    this.LWingF = new ModelRenderer(this, 8, 7);
    this.LWingF.addBox(12.0F, -0.5F, -1.5F, 2, 1, 3);
    this.LWingF.setRotationPoint(4.0F, 22.0F, 1.0F);
    this.LWingF.mirror = true;

    this.LWingG = new ModelRenderer(this, 9, 8);
    this.LWingG.addBox(14.0F, -0.5F, -1.0F, 2, 1, 2);
    this.LWingG.setRotationPoint(4.0F, 22.0F, 1.0F);
    this.LWingG.mirror = true;

    this.LEye = new ModelRenderer(this, 0, 0);
    this.LEye.addBox(-3.0F, -2.0F, 1.0F, 1, 1, 2);
    this.LEye.setRotationPoint(0.0F, 21.0F, -4.0F);

    this.REye = new ModelRenderer(this, 0, 3);
    this.REye.addBox(2.0F, -2.0F, 1.0F, 1, 1, 2);
    this.REye.setRotationPoint(0.0F, 21.0F, -4.0F);
  }
  ModelRenderer RWingF; ModelRenderer BodyTail; ModelRenderer RWingG; ModelRenderer LWingA; ModelRenderer LWingB; ModelRenderer LWingC; ModelRenderer LWingD; ModelRenderer LWingE; ModelRenderer LWingF; ModelRenderer LWingG; ModelRenderer LEye; ModelRenderer REye;

  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    super.render(entity, f, f1, f2, f3, f4, f5);
    MoCEntityRay ray = (MoCEntityRay)entity;
    this.attacking = ray.isPoisoning();
    this.isMantaRay = ray.isMantaRay();

    setRotationAngles(f, f1, f2, f3, f4, f5);
    this.Tail.render(f5);
    this.Body.render(f5);
    this.BodyU.render(f5);
    this.BodyTail.render(f5);

    this.RWingA.render(f5);
    this.RWingB.render(f5);

    this.LWingA.render(f5);
    this.LWingB.render(f5);

    if (this.isMantaRay) {
      this.Right.render(f5);
      this.Left.render(f5);
      this.RWingC.render(f5);
      this.LWingC.render(f5);

      this.RWingD.render(f5);
      this.RWingE.render(f5);
      this.RWingF.render(f5);
      this.RWingG.render(f5);
      this.LWingD.render(f5);
      this.LWingE.render(f5);
      this.LWingF.render(f5);
      this.LWingG.render(f5);
    } else {
      this.REye.render(f5);
      this.LEye.render(f5);
    }
  }

  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }


  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
    float rotF = MathHelper.cos(f * 0.6662F) * 1.5F * f1;
    float f6 = 20.0F;
    this.Tail.rotateAngleY = MathHelper.cos(f * 0.6662F) * 0.7F * f1;
    this.RWingA.rotateAngleZ = rotF;
    this.LWingA.rotateAngleZ = -rotF;
    rotF += rotF / f6;
    this.RWingB.rotateAngleZ = rotF;
    this.LWingB.rotateAngleZ = -rotF;
    rotF += rotF / f6;

    this.RWingC.rotateAngleZ = rotF;
    this.LWingC.rotateAngleZ = -rotF;
    rotF += rotF / f6;

    this.RWingD.rotateAngleZ = rotF;
    this.LWingD.rotateAngleZ = -rotF;
    rotF += rotF / f6;

    this.RWingE.rotateAngleZ = rotF;
    this.LWingE.rotateAngleZ = -rotF;
    rotF += rotF / f6;

    this.RWingF.rotateAngleZ = rotF;
    this.LWingF.rotateAngleZ = -rotF;
    rotF += rotF / f6;

    this.RWingG.rotateAngleZ = rotF;
    this.LWingG.rotateAngleZ = -rotF;

    if (this.attacking) {
      this.Tail.rotateAngleX = 0.5F;
    } else {
      this.Tail.rotateAngleX = 0.0F;
    }
  } }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelRay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
