/*    */ package drzhark.mocreatures.client.model;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ 
/*    */ public class MoCModelAnt
/*    */   extends ModelBase {
/*    */   ModelRenderer Head;
/*    */   ModelRenderer Mouth;
/*    */   ModelRenderer RightAntenna;
/*    */   ModelRenderer LeftAntenna;
/*    */   ModelRenderer Thorax;
/*    */   ModelRenderer Abdomen;
/*    */   ModelRenderer MidLegs;
/*    */   ModelRenderer FrontLegs;
/*    */   ModelRenderer RearLegs;
/*    */   
/*    */   public MoCModelAnt() {
/* 21 */     this.textureWidth = 32;
/* 22 */     this.textureHeight = 32;
/*    */     
/* 24 */     this.Head = new ModelRenderer(this, 0, 11);
/* 25 */     this.Head.addBox(-0.5F, 0.0F, 0.0F, 1, 1, 1);
/* 26 */     this.Head.setRotationPoint(0.0F, 21.9F, -1.3F);
/* 27 */     setRotation(this.Head, -2.171231F, 0.0F, 0.0F);
/*    */     
/* 29 */     this.Mouth = new ModelRenderer(this, 8, 10);
/* 30 */     this.Mouth.addBox(0.0F, 0.0F, 0.0F, 2, 1, 0);
/* 31 */     this.Mouth.setRotationPoint(-1.0F, 22.3F, -1.9F);
/* 32 */     setRotation(this.Mouth, -0.8286699F, 0.0F, 0.0F);
/*    */     
/* 34 */     this.RightAntenna = new ModelRenderer(this, 0, 6);
/* 35 */     this.RightAntenna.addBox(-0.5F, 0.0F, -1.0F, 1, 0, 1);
/* 36 */     this.RightAntenna.setRotationPoint(-0.5F, 21.7F, -2.3F);
/* 37 */     setRotation(this.RightAntenna, -1.041001F, 0.7853982F, 0.0F);
/*    */     
/* 39 */     this.LeftAntenna = new ModelRenderer(this, 4, 6);
/* 40 */     this.LeftAntenna.addBox(-0.5F, 0.0F, -1.0F, 1, 0, 1);
/* 41 */     this.LeftAntenna.setRotationPoint(0.5F, 21.7F, -2.3F);
/* 42 */     setRotation(this.LeftAntenna, -1.041001F, -0.7853982F, 0.0F);
/*    */     
/* 44 */     this.Thorax = new ModelRenderer(this, 0, 0);
/* 45 */     this.Thorax.addBox(-0.5F, 1.5F, -1.0F, 1, 1, 2);
/* 46 */     this.Thorax.setRotationPoint(0.0F, 20.0F, -0.5F);
/*    */     
/* 48 */     this.Abdomen = new ModelRenderer(this, 8, 1);
/* 49 */     this.Abdomen.addBox(-0.5F, -0.2F, -1.0F, 1, 2, 1);
/* 50 */     this.Abdomen.setRotationPoint(0.0F, 21.5F, 0.3F);
/* 51 */     setRotation(this.Abdomen, 1.706911F, 0.0F, 0.0F);
/*    */     
/* 53 */     this.MidLegs = new ModelRenderer(this, 4, 8);
/* 54 */     this.MidLegs.addBox(-1.0F, 0.0F, 0.0F, 2, 3, 0);
/* 55 */     this.MidLegs.setRotationPoint(0.0F, 22.0F, -0.7F);
/* 56 */     setRotation(this.MidLegs, 0.5948578F, 0.0F, 0.0F);
/*    */     
/* 58 */     this.FrontLegs = new ModelRenderer(this, 0, 8);
/* 59 */     this.FrontLegs.addBox(-1.0F, 0.0F, 0.0F, 2, 3, 0);
/* 60 */     this.FrontLegs.setRotationPoint(0.0F, 22.0F, -0.8F);
/* 61 */     setRotation(this.FrontLegs, -0.6192304F, 0.0F, 0.0F);
/*    */     
/* 63 */     this.RearLegs = new ModelRenderer(this, 0, 8);
/* 64 */     this.RearLegs.addBox(-1.0F, 0.0F, 0.0F, 2, 3, 0);
/* 65 */     this.RearLegs.setRotationPoint(0.0F, 22.0F, 0.0F);
/* 66 */     setRotation(this.RearLegs, 0.9136644F, 0.0F, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 71 */     super.render(entity, f, f1, f2, f3, f4, f5);
/* 72 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/* 73 */     this.Head.render(f5);
/* 74 */     this.Mouth.render(f5);
/* 75 */     this.RightAntenna.render(f5);
/* 76 */     this.LeftAntenna.render(f5);
/* 77 */     this.Thorax.render(f5);
/* 78 */     this.Abdomen.render(f5);
/* 79 */     this.MidLegs.render(f5);
/* 80 */     this.FrontLegs.render(f5);
/* 81 */     this.RearLegs.render(f5);
/*    */   }
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 85 */     model.rotateAngleX = x;
/* 86 */     model.rotateAngleY = y;
/* 87 */     model.rotateAngleZ = z;
/*    */   }
/*    */   
/*    */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 91 */     float legMov = MathHelper.cos(f + 3.141593F) * f1;
/* 92 */     float legMovB = MathHelper.cos(f) * f1;
/* 93 */     this.FrontLegs.rotateAngleX = -0.6192304F + legMov;
/* 94 */     this.MidLegs.rotateAngleX = 0.5948578F + legMovB;
/* 95 */     this.RearLegs.rotateAngleX = 0.9136644F + legMov;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelAnt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */