/*    */ package drzhark.mocreatures.client.model;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.entity.Entity;
/*    */
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCModelDeer extends ModelBase {
/*    */   public ModelRenderer Body;
/*    */   public ModelRenderer Neck;
/*    */   public ModelRenderer Head;
/*    */   public ModelRenderer Leg1;
/*    */   public ModelRenderer Leg2;
/*    */   public ModelRenderer Leg3;
/*    */
/*    */   public MoCModelDeer() {
/* 14 */     this.Head = new ModelRenderer(this, 0, 0);
/* 15 */     this.Head.addBox(-1.5F, -6.0F, -9.5F, 3, 3, 6, 0.0F);
/* 16 */     this.Head.setRotationPoint(1.0F, 11.5F, -4.5F);
/* 17 */     this.Neck = new ModelRenderer(this, 0, 9);
/* 18 */     this.Neck.addBox(-2.0F, -2.0F, -6.0F, 4, 4, 6, 0.0F);
/* 19 */     this.Neck.setRotationPoint(1.0F, 11.5F, -4.5F);
/* 20 */     this.Neck.rotateAngleX = -0.7853981F;
/* 21 */     this.LEar = new ModelRenderer(this, 0, 0);
/* 22 */     this.LEar.addBox(-4.0F, -7.5F, -5.0F, 2, 3, 1, 0.0F);
/* 23 */     this.LEar.setRotationPoint(1.0F, 11.5F, -4.5F);
/* 24 */     this.LEar.rotateAngleZ = 0.7853981F;
/* 25 */     this.REar = new ModelRenderer(this, 0, 0);
/* 26 */     this.REar.addBox(2.0F, -7.5F, -5.0F, 2, 3, 1, 0.0F);
/* 27 */     this.REar.setRotationPoint(1.0F, 11.5F, -4.5F);
/* 28 */     this.REar.rotateAngleZ = -0.7853981F;
/* 29 */     this.LeftAntler = new ModelRenderer(this, 54, 0);
/* 30 */     this.LeftAntler.addBox(0.0F, -14.0F, -7.0F, 1, 8, 4, 0.0F);
/* 31 */     this.LeftAntler.setRotationPoint(1.0F, 11.5F, -4.5F);
/* 32 */     this.LeftAntler.rotateAngleZ = 0.2094395F;
/* 33 */     this.RightAntler = new ModelRenderer(this, 54, 0);
/* 34 */     this.RightAntler.addBox(0.0F, -14.0F, -7.0F, 1, 8, 4, 0.0F);
/* 35 */     this.RightAntler.setRotationPoint(1.0F, 11.5F, -4.5F);
/* 36 */     this.RightAntler.rotateAngleZ = -0.2094395F;
/* 37 */     this.Body = new ModelRenderer(this, 24, 12);
/* 38 */     this.Body.addBox(-2.0F, -3.0F, -6.0F, 6, 6, 14, 0.0F);
/* 39 */     this.Body.setRotationPoint(0.0F, 13.0F, 0.0F);
/* 40 */     this.Leg1 = new ModelRenderer(this, 9, 20);
/* 41 */     this.Leg1.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
/* 42 */     this.Leg1.setRotationPoint(3.0F, 16.0F, -4.0F);
/* 43 */     this.Leg2 = new ModelRenderer(this, 0, 20);
/* 44 */     this.Leg2.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
/* 45 */     this.Leg2.setRotationPoint(-1.0F, 16.0F, -4.0F);
/* 46 */     this.Leg3 = new ModelRenderer(this, 9, 20);
/* 47 */     this.Leg3.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
/* 48 */     this.Leg3.setRotationPoint(3.0F, 16.0F, 6.0F);
/* 49 */     this.Leg4 = new ModelRenderer(this, 0, 20);
/* 50 */     this.Leg4.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
/* 51 */     this.Leg4.setRotationPoint(-1.0F, 16.0F, 6.0F);
/* 52 */     this.Tail = new ModelRenderer(this, 50, 20);
/* 53 */     this.Tail.addBox(-1.5F, -1.0F, 0.0F, 3, 2, 4, 0.0F);
/* 54 */     this.Tail.setRotationPoint(1.0F, 11.0F, 7.0F);
/* 55 */     this.Tail.rotateAngleX = 0.7854F;
/*    */   }
/*    */   public ModelRenderer Leg4; public ModelRenderer Tail; public ModelRenderer LEar; public ModelRenderer REar; public ModelRenderer LeftAntler; public ModelRenderer RightAntler;
/*    */
/*    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 60 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/* 61 */     this.Body.render(f5);
/* 62 */     this.Neck.render(f5);
/* 63 */     this.Head.render(f5);
/* 64 */     this.Leg1.render(f5);
/* 65 */     this.Leg2.render(f5);
/* 66 */     this.Leg3.render(f5);
/* 67 */     this.Leg4.render(f5);
/* 68 */     this.Tail.render(f5);
/* 69 */     this.LEar.render(f5);
/* 70 */     this.REar.render(f5);
/* 71 */     this.LeftAntler.render(f5);
/* 72 */     this.RightAntler.render(f5);
/*    */   }
/*    */
/*    */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 76 */     this.Leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
/* 77 */     this.Leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
/* 78 */     this.Leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
/* 79 */     this.Leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelDeer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
