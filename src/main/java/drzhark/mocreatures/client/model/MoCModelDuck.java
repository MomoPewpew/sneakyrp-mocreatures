/*    */ package drzhark.mocreatures.client.model;
/*    */ 
/*    */ import drzhark.mocreatures.entity.passive.MoCEntityDuck;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCModelDuck
/*    */   extends ModelBase {
/*    */   public ModelRenderer head;
/*    */   public ModelRenderer body;
/*    */   public ModelRenderer rightLeg;
/*    */   public ModelRenderer leftLeg;
/*    */   public ModelRenderer rightWing;
/*    */   public ModelRenderer leftWing;
/*    */   public ModelRenderer bill;
/*    */   public ModelRenderer chin;
/*    */   
/*    */   public MoCModelDuck() {
/* 24 */     byte var1 = 16;
/* 25 */     this.head = new ModelRenderer(this, 0, 0);
/* 26 */     this.head.addBox(-2.0F, -6.0F, -2.0F, 4, 6, 3, 0.0F);
/* 27 */     this.head.setRotationPoint(0.0F, (-1 + var1), -4.0F);
/* 28 */     this.bill = new ModelRenderer(this, 14, 0);
/* 29 */     this.bill.addBox(-2.0F, -4.0F, -4.0F, 4, 2, 2, 0.0F);
/* 30 */     this.bill.setRotationPoint(0.0F, (-1 + var1), -4.0F);
/* 31 */     this.chin = new ModelRenderer(this, 14, 4);
/* 32 */     this.chin.addBox(-1.0F, -2.0F, -3.0F, 2, 2, 2, 0.0F);
/* 33 */     this.chin.setRotationPoint(0.0F, (-1 + var1), -4.0F);
/* 34 */     this.body = new ModelRenderer(this, 0, 9);
/* 35 */     this.body.addBox(-3.0F, -4.0F, -3.0F, 6, 8, 6, 0.0F);
/* 36 */     this.body.setRotationPoint(0.0F, var1, 0.0F);
/* 37 */     this.rightLeg = new ModelRenderer(this, 26, 0);
/* 38 */     this.rightLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3);
/* 39 */     this.rightLeg.setRotationPoint(-2.0F, (3 + var1), 1.0F);
/* 40 */     this.leftLeg = new ModelRenderer(this, 26, 0);
/* 41 */     this.leftLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3);
/* 42 */     this.leftLeg.setRotationPoint(1.0F, (3 + var1), 1.0F);
/* 43 */     this.rightWing = new ModelRenderer(this, 24, 13);
/* 44 */     this.rightWing.addBox(0.0F, 0.0F, -3.0F, 1, 4, 6);
/* 45 */     this.rightWing.setRotationPoint(-4.0F, (-3 + var1), 0.0F);
/* 46 */     this.leftWing = new ModelRenderer(this, 24, 13);
/* 47 */     this.leftWing.addBox(-1.0F, 0.0F, -3.0F, 1, 4, 6);
/* 48 */     this.leftWing.setRotationPoint(4.0F, (-3 + var1), 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
/* 56 */     MoCEntityDuck entityDuck = (MoCEntityDuck)par1Entity;
/* 57 */     boolean fly = !entityDuck.onGround;
/* 58 */     setRotationAngles(par2, par3, par4, par5, par6, par7, fly);
/*    */     
/* 60 */     this.head.render(par7);
/* 61 */     this.bill.render(par7);
/* 62 */     this.chin.render(par7);
/* 63 */     this.body.render(par7);
/* 64 */     this.rightLeg.render(par7);
/* 65 */     this.leftLeg.render(par7);
/* 66 */     this.rightWing.render(par7);
/* 67 */     this.leftWing.render(par7);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, boolean fly) {
/* 78 */     this.head.rotateAngleX = -(par5 / 57.295776F);
/* 79 */     this.head.rotateAngleY = par4 / 57.295776F;
/* 80 */     this.bill.rotateAngleX = this.head.rotateAngleX;
/* 81 */     this.bill.rotateAngleY = this.head.rotateAngleY;
/* 82 */     this.chin.rotateAngleX = this.head.rotateAngleX;
/* 83 */     this.chin.rotateAngleY = this.head.rotateAngleY;
/* 84 */     this.body.rotateAngleX = 1.5707964F;
/* 85 */     this.rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
/* 86 */     this.leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
/* 87 */     if (fly) {
/* 88 */       Float WingRot = Float.valueOf(MathHelper.cos(par3 * 1.4F + 3.141593F) * 0.6F);
/* 89 */       this.rightWing.rotateAngleZ = 0.5F + WingRot.floatValue();
/* 90 */       this.leftWing.rotateAngleZ = -0.5F - WingRot.floatValue();
/*    */     } else {
/* 92 */       this.rightWing.rotateAngleZ = 0.0F;
/* 93 */       this.leftWing.rotateAngleZ = 0.0F;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelDuck.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */