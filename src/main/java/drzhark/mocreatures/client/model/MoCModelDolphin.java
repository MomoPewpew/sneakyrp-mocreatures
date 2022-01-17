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
/*    */ public class MoCModelDolphin
/*    */   extends ModelBase {
/*    */   public ModelRenderer UHead;
/*    */   public ModelRenderer DHead;
/*    */   public ModelRenderer RTail;
/*    */   public ModelRenderer LTail;
/*    */   public ModelRenderer PTail;
/*    */   public ModelRenderer Body;
/*    */   public ModelRenderer UpperFin;
/*    */   public ModelRenderer RTailFin;
/*    */   public ModelRenderer LTailFin;
/*    */   public ModelRenderer LowerFin;
/*    */   public ModelRenderer RightFin;
/*    */   public ModelRenderer LeftFin;
/*    */   
/*    */   public MoCModelDolphin() {
/* 27 */     this.Body = new ModelRenderer(this, 4, 6);
/* 28 */     this.Body.addBox(0.0F, 0.0F, 0.0F, 6, 8, 18, 0.0F);
/* 29 */     this.Body.setRotationPoint(-4.0F, 17.0F, -10.0F);
/* 30 */     this.UHead = new ModelRenderer(this, 0, 0);
/* 31 */     this.UHead.addBox(0.0F, 0.0F, 0.0F, 5, 7, 8, 0.0F);
/* 32 */     this.UHead.setRotationPoint(-3.5F, 18.0F, -16.5F);
/* 33 */     this.DHead = new ModelRenderer(this, 50, 0);
/* 34 */     this.DHead.addBox(0.0F, 0.0F, 0.0F, 3, 3, 4, 0.0F);
/* 35 */     this.DHead.setRotationPoint(-2.5F, 21.5F, -20.5F);
/* 36 */     this.PTail = new ModelRenderer(this, 34, 9);
/* 37 */     this.PTail.addBox(0.0F, 0.0F, 0.0F, 5, 5, 10, 0.0F);
/* 38 */     this.PTail.setRotationPoint(-3.5F, 19.0F, 8.0F);
/* 39 */     this.UpperFin = new ModelRenderer(this, 4, 12);
/* 40 */     this.UpperFin.addBox(0.0F, 0.0F, 0.0F, 1, 4, 8, 0.0F);
/* 41 */     this.UpperFin.setRotationPoint(-1.5F, 18.0F, -4.0F);
/* 42 */     this.UpperFin.rotateAngleX = 0.7853981F;
/* 43 */     this.LTailFin = new ModelRenderer(this, 34, 0);
/* 44 */     this.LTailFin.addBox(0.0F, 0.0F, 0.0F, 4, 1, 8, 0.3F);
/* 45 */     this.LTailFin.setRotationPoint(-2.0F, 21.5F, 18.0F);
/* 46 */     this.LTailFin.rotateAngleY = 0.7853981F;
/* 47 */     this.RTailFin = new ModelRenderer(this, 34, 0);
/* 48 */     this.RTailFin.addBox(0.0F, 0.0F, 0.0F, 4, 1, 8, 0.3F);
/* 49 */     this.RTailFin.setRotationPoint(-3.0F, 21.5F, 15.0F);
/* 50 */     this.RTailFin.rotateAngleY = -0.7853981F;
/* 51 */     this.LeftFin = new ModelRenderer(this, 14, 0);
/* 52 */     this.LeftFin.addBox(0.0F, 0.0F, 0.0F, 8, 1, 4, 0.0F);
/* 53 */     this.LeftFin.setRotationPoint(2.0F, 24.0F, -7.0F);
/* 54 */     this.LeftFin.rotateAngleY = -0.5235988F;
/* 55 */     this.LeftFin.rotateAngleZ = 0.5235988F;
/* 56 */     this.RightFin = new ModelRenderer(this, 14, 0);
/* 57 */     this.RightFin.addBox(0.0F, 0.0F, 0.0F, 8, 1, 4, 0.0F);
/* 58 */     this.RightFin.setRotationPoint(-10.0F, 27.5F, -3.0F);
/* 59 */     this.RightFin.rotateAngleY = 0.5235988F;
/* 60 */     this.RightFin.rotateAngleZ = -0.5235988F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 65 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/* 66 */     this.Body.render(f5);
/* 67 */     this.PTail.render(f5);
/* 68 */     this.UHead.render(f5);
/* 69 */     this.DHead.render(f5);
/* 70 */     this.UpperFin.render(f5);
/* 71 */     this.LTailFin.render(f5);
/* 72 */     this.RTailFin.render(f5);
/* 73 */     this.LeftFin.render(f5);
/* 74 */     this.RightFin.render(f5);
/*    */   }
/*    */   
/*    */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 78 */     this.RTailFin.rotateAngleX = MathHelper.cos(f * 0.4F) * f1;
/* 79 */     this.LTailFin.rotateAngleX = MathHelper.cos(f * 0.4F) * f1;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelDolphin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */