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
/*    */ public class MoCModelFox
/*    */   extends ModelBase {
/*    */   public ModelRenderer Body;
/*    */   public ModelRenderer Leg1;
/*    */   public ModelRenderer Leg2;
/*    */   public ModelRenderer Leg3;
/*    */   public ModelRenderer Leg4;
/*    */   public ModelRenderer Snout;
/*    */   public ModelRenderer Head;
/*    */   public ModelRenderer Tail;
/*    */   public ModelRenderer Ears;
/*    */   
/*    */   public MoCModelFox() {
/* 24 */     byte byte0 = 8;
/* 25 */     this.Body = new ModelRenderer(this, 0, 0);
/* 26 */     this.Body.addBox(0.0F, 0.0F, 0.0F, 6, 6, 12, 0.0F);
/* 27 */     this.Body.setRotationPoint(-4.0F, 10.0F, -6.0F);
/* 28 */     this.Head = new ModelRenderer(this, 0, 20);
/* 29 */     this.Head.addBox(-3.0F, -3.0F, -4.0F, 6, 6, 4, 0.0F);
/* 30 */     this.Head.setRotationPoint(-1.0F, 11.0F, -6.0F);
/* 31 */     this.Snout = new ModelRenderer(this, 20, 20);
/* 32 */     this.Snout.addBox(-1.0F, 1.0F, -7.0F, 2, 2, 4, 0.0F);
/* 33 */     this.Snout.setRotationPoint(-1.0F, 11.0F, -6.0F);
/* 34 */     this.Ears = new ModelRenderer(this, 50, 20);
/* 35 */     this.Ears.addBox(-3.0F, -6.0F, -2.0F, 6, 4, 1, 0.0F);
/* 36 */     this.Ears.setRotationPoint(-1.0F, 11.0F, -6.0F);
/* 37 */     this.Tail = new ModelRenderer(this, 32, 20);
/* 38 */     this.Tail.addBox(-5.0F, -5.0F, -2.0F, 3, 3, 8, 0.0F);
/* 39 */     this.Tail.setRotationPoint(2.5F, 15.0F, 5.0F);
/* 40 */     this.Tail.rotateAngleX = -0.5235988F;
/* 41 */     this.Leg1 = new ModelRenderer(this, 0, 0);
/* 42 */     this.Leg1.addBox(-2.0F, 0.0F, -2.0F, 3, byte0, 3, 0.0F);
/* 43 */     this.Leg1.setRotationPoint(-2.0F, (24 - byte0), 5.0F);
/* 44 */     this.Leg2 = new ModelRenderer(this, 0, 0);
/* 45 */     this.Leg2.addBox(-2.0F, 0.0F, -2.0F, 3, byte0, 3, 0.0F);
/* 46 */     this.Leg2.setRotationPoint(1.0F, (24 - byte0), 5.0F);
/* 47 */     this.Leg3 = new ModelRenderer(this, 0, 0);
/* 48 */     this.Leg3.addBox(-2.0F, 0.0F, -2.0F, 3, byte0, 3, 0.0F);
/* 49 */     this.Leg3.setRotationPoint(-2.0F, (24 - byte0), -4.0F);
/* 50 */     this.Leg4 = new ModelRenderer(this, 0, 0);
/* 51 */     this.Leg4.addBox(-2.0F, 0.0F, -2.0F, 3, byte0, 3, 0.0F);
/* 52 */     this.Leg4.setRotationPoint(1.0F, (24 - byte0), -4.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 57 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/* 58 */     this.Body.render(f5);
/* 59 */     this.Leg1.render(f5);
/* 60 */     this.Leg2.render(f5);
/* 61 */     this.Leg3.render(f5);
/* 62 */     this.Leg4.render(f5);
/* 63 */     this.Head.render(f5);
/* 64 */     this.Snout.render(f5);
/* 65 */     this.Tail.render(f5);
/* 66 */     this.Ears.render(f5);
/*    */   }
/*    */   
/*    */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 70 */     this.Head.rotateAngleY = f3 / 57.29578F;
/* 71 */     this.Head.rotateAngleX = f4 / 57.29578F;
/* 72 */     this.Snout.rotateAngleY = this.Head.rotateAngleY;
/* 73 */     this.Snout.rotateAngleX = this.Head.rotateAngleX;
/*    */     
/* 75 */     this.Ears.rotateAngleY = this.Head.rotateAngleY;
/* 76 */     this.Ears.rotateAngleX = this.Head.rotateAngleX;
/*    */     
/* 78 */     this.Leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
/* 79 */     this.Leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
/* 80 */     this.Leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
/* 81 */     this.Leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelFox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */