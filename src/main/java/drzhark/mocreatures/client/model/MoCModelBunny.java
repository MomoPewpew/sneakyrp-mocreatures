/*    */ package drzhark.mocreatures.client.model;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCModelBunny
/*    */   extends ModelBase {
/*    */   public ModelRenderer part1;
/*    */   public ModelRenderer part2;
/*    */   public ModelRenderer part3;
/*    */   public ModelRenderer part4;
/*    */   public ModelRenderer part5;
/*    */   public ModelRenderer part6;
/*    */   public ModelRenderer part7;
/*    */   public ModelRenderer part8;
/*    */   public ModelRenderer part9;
/*    */   public ModelRenderer part10;
/*    */   public ModelRenderer part11;
/*    */   private boolean bunnyHat;
/*    */   
/*    */   public MoCModelBunny() {
/* 27 */     byte byte0 = 16;
/* 28 */     this.part1 = new ModelRenderer(this, 0, 0);
/* 29 */     this.part1.addBox(-2.0F, -1.0F, -4.0F, 4, 4, 6, 0.0F);
/* 30 */     this.part1.setRotationPoint(0.0F, (-1 + byte0), -4.0F);
/* 31 */     this.part8 = new ModelRenderer(this, 14, 0);
/* 32 */     this.part8.addBox(-2.0F, -5.0F, -3.0F, 1, 4, 2, 0.0F);
/* 33 */     this.part8.setRotationPoint(0.0F, (-1 + byte0), -4.0F);
/* 34 */     this.part9 = new ModelRenderer(this, 14, 0);
/* 35 */     this.part9.addBox(1.0F, -5.0F, -3.0F, 1, 4, 2, 0.0F);
/* 36 */     this.part9.setRotationPoint(0.0F, (-1 + byte0), -4.0F);
/* 37 */     this.part10 = new ModelRenderer(this, 20, 0);
/* 38 */     this.part10.addBox(-4.0F, 0.0F, -3.0F, 2, 3, 2, 0.0F);
/* 39 */     this.part10.setRotationPoint(0.0F, (-1 + byte0), -4.0F);
/* 40 */     this.part11 = new ModelRenderer(this, 20, 0);
/* 41 */     this.part11.addBox(2.0F, 0.0F, -3.0F, 2, 3, 2, 0.0F);
/* 42 */     this.part11.setRotationPoint(0.0F, (-1 + byte0), -4.0F);
/* 43 */     this.part2 = new ModelRenderer(this, 0, 10);
/* 44 */     this.part2.addBox(-3.0F, -4.0F, -3.0F, 6, 8, 6, 0.0F);
/* 45 */     this.part2.setRotationPoint(0.0F, (0 + byte0), 0.0F);
/* 46 */     this.part3 = new ModelRenderer(this, 0, 24);
/* 47 */     this.part3.addBox(-2.0F, 4.0F, -2.0F, 4, 3, 4, 0.0F);
/* 48 */     this.part3.setRotationPoint(0.0F, (0 + byte0), 0.0F);
/* 49 */     this.part4 = new ModelRenderer(this, 24, 16);
/* 50 */     this.part4.addBox(-2.0F, 0.0F, -1.0F, 2, 2, 2);
/* 51 */     this.part4.setRotationPoint(3.0F, (3 + byte0), -3.0F);
/* 52 */     this.part5 = new ModelRenderer(this, 24, 16);
/* 53 */     this.part5.addBox(0.0F, 0.0F, -1.0F, 2, 2, 2);
/* 54 */     this.part5.setRotationPoint(-3.0F, (3 + byte0), -3.0F);
/* 55 */     this.part6 = new ModelRenderer(this, 16, 24);
/* 56 */     this.part6.addBox(-2.0F, 0.0F, -4.0F, 2, 2, 4);
/* 57 */     this.part6.setRotationPoint(3.0F, (3 + byte0), 4.0F);
/* 58 */     this.part7 = new ModelRenderer(this, 16, 24);
/* 59 */     this.part7.addBox(0.0F, 0.0F, -4.0F, 2, 2, 4);
/* 60 */     this.part7.setRotationPoint(-3.0F, (3 + byte0), 4.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 65 */     this.bunnyHat = (entity.getRidingEntity() != null);
/* 66 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/* 67 */     this.part1.render(f5);
/* 68 */     this.part8.render(f5);
/* 69 */     this.part9.render(f5);
/* 70 */     this.part10.render(f5);
/* 71 */     this.part11.render(f5);
/* 72 */     this.part2.render(f5);
/* 73 */     this.part3.render(f5);
/* 74 */     this.part4.render(f5);
/* 75 */     this.part5.render(f5);
/* 76 */     this.part6.render(f5);
/* 77 */     this.part7.render(f5);
/*    */   }
/*    */   
/*    */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 81 */     this.part1.rotateAngleX = -(f4 / 57.29578F);
/* 82 */     this.part1.rotateAngleY = f3 / 57.29578F;
/*    */     
/* 84 */     this.part8.rotateAngleX = this.part1.rotateAngleX;
/* 85 */     this.part8.rotateAngleY = this.part1.rotateAngleY;
/* 86 */     this.part9.rotateAngleX = this.part1.rotateAngleX;
/* 87 */     this.part9.rotateAngleY = this.part1.rotateAngleY;
/* 88 */     this.part10.rotateAngleX = this.part1.rotateAngleX;
/* 89 */     this.part10.rotateAngleY = this.part1.rotateAngleY;
/* 90 */     this.part11.rotateAngleX = this.part1.rotateAngleX;
/* 91 */     this.part11.rotateAngleY = this.part1.rotateAngleY;
/* 92 */     this.part2.rotateAngleX = 1.570796F;
/* 93 */     this.part3.rotateAngleX = 1.570796F;
/* 94 */     if (!this.bunnyHat) {
/* 95 */       this.part4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.0F * f1;
/* 96 */       this.part6.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.2F * f1;
/* 97 */       this.part5.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.0F * f1;
/* 98 */       this.part7.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.2F * f1;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelBunny.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */