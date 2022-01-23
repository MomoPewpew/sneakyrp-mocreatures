package drzhark.mocreatures.client.model;

import drzhark.mocreatures.entity.monster.MoCEntityWraith;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MoCModelWraith
  extends ModelBiped {
  private int attackCounter;

  public MoCModelWraith() {
    super(12.0F, 0.0F, 64, 32);
    this.leftArmPose = ModelBiped.ArmPose.EMPTY;
    this.rightArmPose = ModelBiped.ArmPose.EMPTY;
    this.isSneak = false;
    this.bipedHead = new ModelRenderer((ModelBase)this, 0, 40);
    this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 1, 1, 1, 0.0F);
    this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.bipedHeadwear = new ModelRenderer((ModelBase)this, 0, 0);
    this.bipedHeadwear.addBox(-5.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
    this.bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.bipedBody = new ModelRenderer((ModelBase)this, 36, 0);
    this.bipedBody.addBox(-6.0F, 0.0F, -2.0F, 10, 20, 4, 0.0F);
    this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.bipedRightArm = new ModelRenderer((ModelBase)this, 16, 16);
    this.bipedRightArm.addBox(-5.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
    this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
    this.bipedLeftArm = new ModelRenderer((ModelBase)this, 16, 16);
    this.bipedLeftArm.mirror = true;
    this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
    this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
    this.bipedRightLeg = new ModelRenderer((ModelBase)this, 0, 16);
    this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 2, 2, 2, 0.0F);
    this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
    this.bipedLeftLeg = new ModelRenderer((ModelBase)this, 0, 16);
    this.bipedLeftLeg.mirror = true;
    this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 2, 2, 2, 0.0F);
    this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
  }



  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity) {
    if (par7Entity instanceof MoCEntityWraith) {
      this.attackCounter = ((MoCEntityWraith)par7Entity).attackCounter;
    }

    float f6 = MathHelper.sin(this.swingProgress * 3.141593F);
    float f7 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * 3.141593F);
    this.bipedRightArm.rotateAngleZ = 0.0F;
    this.bipedLeftArm.rotateAngleZ = 0.0F;
    this.bipedRightArm.rotateAngleY = -(0.1F - f6 * 0.6F);
    this.bipedLeftArm.rotateAngleY = 0.1F - f6 * 0.6F;
    if (this.attackCounter != 0) {
      float armMov = MathHelper.cos(this.attackCounter * 0.12F) * 4.0F;

      this.bipedRightArm.rotateAngleX = -armMov;
      this.bipedLeftArm.rotateAngleX = -armMov;
    } else {
      this.bipedRightArm.rotateAngleX = -1.570796F;
      this.bipedLeftArm.rotateAngleX = -1.570796F;
      this.bipedRightArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
      this.bipedLeftArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
      this.bipedRightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
      this.bipedLeftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
    }

    this.bipedRightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
    this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelWraith.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
