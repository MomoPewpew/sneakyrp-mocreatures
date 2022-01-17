/*    */ package drzhark.mocreatures.client.model;
/*    */ 
/*    */ import drzhark.mocreatures.entity.monster.MoCEntityWraith;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelBiped;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCModelWraith
/*    */   extends ModelBiped {
/*    */   private int attackCounter;
/*    */   
/*    */   public MoCModelWraith() {
/* 18 */     super(12.0F, 0.0F, 64, 32);
/* 19 */     this.leftArmPose = ModelBiped.ArmPose.EMPTY;
/* 20 */     this.rightArmPose = ModelBiped.ArmPose.EMPTY;
/* 21 */     this.isSneak = false;
/* 22 */     this.bipedHead = new ModelRenderer((ModelBase)this, 0, 40);
/* 23 */     this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 1, 1, 1, 0.0F);
/* 24 */     this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 25 */     this.bipedHeadwear = new ModelRenderer((ModelBase)this, 0, 0);
/* 26 */     this.bipedHeadwear.addBox(-5.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
/* 27 */     this.bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 28 */     this.bipedBody = new ModelRenderer((ModelBase)this, 36, 0);
/* 29 */     this.bipedBody.addBox(-6.0F, 0.0F, -2.0F, 10, 20, 4, 0.0F);
/* 30 */     this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 31 */     this.bipedRightArm = new ModelRenderer((ModelBase)this, 16, 16);
/* 32 */     this.bipedRightArm.addBox(-5.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
/* 33 */     this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
/* 34 */     this.bipedLeftArm = new ModelRenderer((ModelBase)this, 16, 16);
/* 35 */     this.bipedLeftArm.mirror = true;
/* 36 */     this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
/* 37 */     this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
/* 38 */     this.bipedRightLeg = new ModelRenderer((ModelBase)this, 0, 16);
/* 39 */     this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 2, 2, 2, 0.0F);
/* 40 */     this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
/* 41 */     this.bipedLeftLeg = new ModelRenderer((ModelBase)this, 0, 16);
/* 42 */     this.bipedLeftLeg.mirror = true;
/* 43 */     this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 2, 2, 2, 0.0F);
/* 44 */     this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity) {
/* 50 */     if (par7Entity instanceof MoCEntityWraith) {
/* 51 */       this.attackCounter = ((MoCEntityWraith)par7Entity).attackCounter;
/*    */     }
/*    */     
/* 54 */     float f6 = MathHelper.sin(this.swingProgress * 3.141593F);
/* 55 */     float f7 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * 3.141593F);
/* 56 */     this.bipedRightArm.rotateAngleZ = 0.0F;
/* 57 */     this.bipedLeftArm.rotateAngleZ = 0.0F;
/* 58 */     this.bipedRightArm.rotateAngleY = -(0.1F - f6 * 0.6F);
/* 59 */     this.bipedLeftArm.rotateAngleY = 0.1F - f6 * 0.6F;
/* 60 */     if (this.attackCounter != 0) {
/* 61 */       float armMov = MathHelper.cos(this.attackCounter * 0.12F) * 4.0F;
/*    */       
/* 63 */       this.bipedRightArm.rotateAngleX = -armMov;
/* 64 */       this.bipedLeftArm.rotateAngleX = -armMov;
/*    */     } else {
/* 66 */       this.bipedRightArm.rotateAngleX = -1.570796F;
/* 67 */       this.bipedLeftArm.rotateAngleX = -1.570796F;
/* 68 */       this.bipedRightArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
/* 69 */       this.bipedLeftArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
/* 70 */       this.bipedRightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
/* 71 */       this.bipedLeftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
/*    */     } 
/*    */     
/* 74 */     this.bipedRightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
/* 75 */     this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelWraith.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */