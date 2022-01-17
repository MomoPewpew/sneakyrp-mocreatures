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
/*    */ public class MoCModelMouse
/*    */   extends ModelBase {
/*    */   public ModelRenderer Head;
/*    */   public ModelRenderer EarR;
/*    */   public ModelRenderer EarL;
/*    */   public ModelRenderer WhiskerR;
/*    */   public ModelRenderer WhiskerL;
/*    */   public ModelRenderer Tail;
/*    */   public ModelRenderer FrontL;
/*    */   public ModelRenderer FrontR;
/*    */   public ModelRenderer RearL;
/*    */   public ModelRenderer RearR;
/*    */   public ModelRenderer BodyF;
/*    */   
/*    */   public MoCModelMouse() {
/* 26 */     this.Head = new ModelRenderer(this, 0, 0);
/* 27 */     this.Head.addBox(-1.5F, -1.0F, -6.0F, 3, 4, 6, 0.0F);
/* 28 */     this.Head.setRotationPoint(0.0F, 19.0F, -9.0F);
/* 29 */     this.EarR = new ModelRenderer(this, 16, 26);
/* 30 */     this.EarR.addBox(-3.5F, -3.0F, -2.0F, 3, 3, 1, 0.0F);
/* 31 */     this.EarR.setRotationPoint(0.0F, 19.0F, -9.0F);
/* 32 */     this.EarL = new ModelRenderer(this, 24, 26);
/* 33 */     this.EarL.addBox(0.5F, -3.0F, -1.0F, 3, 3, 1, 0.0F);
/* 34 */     this.EarL.setRotationPoint(0.0F, 19.0F, -10.0F);
/* 35 */     this.WhiskerR = new ModelRenderer(this, 20, 20);
/* 36 */     this.WhiskerR.addBox(-4.5F, -1.0F, -7.0F, 3, 3, 1, 0.0F);
/* 37 */     this.WhiskerR.setRotationPoint(0.0F, 19.0F, -9.0F);
/* 38 */     this.WhiskerL = new ModelRenderer(this, 24, 20);
/* 39 */     this.WhiskerL.addBox(1.5F, -1.0F, -6.0F, 3, 3, 1, 0.0F);
/* 40 */     this.WhiskerL.setRotationPoint(0.0F, 19.0F, -9.0F);
/* 41 */     this.Tail = new ModelRenderer(this, 56, 0);
/* 42 */     this.Tail.addBox(-0.5F, 0.0F, -1.0F, 1, 14, 1, 0.0F);
/* 43 */     this.Tail.setRotationPoint(0.0F, 20.0F, 3.0F);
/* 44 */     this.Tail.rotateAngleX = 1.570796F;
/* 45 */     this.FrontL = new ModelRenderer(this, 0, 18);
/* 46 */     this.FrontL.addBox(-2.0F, 0.0F, -3.0F, 2, 1, 4, 0.0F);
/* 47 */     this.FrontL.setRotationPoint(3.0F, 23.0F, -7.0F);
/* 48 */     this.FrontR = new ModelRenderer(this, 0, 18);
/* 49 */     this.FrontR.addBox(0.0F, 0.0F, -3.0F, 2, 1, 4, 0.0F);
/* 50 */     this.FrontR.setRotationPoint(-3.0F, 23.0F, -7.0F);
/* 51 */     this.RearL = new ModelRenderer(this, 0, 18);
/* 52 */     this.RearL.addBox(-2.0F, 0.0F, -4.0F, 2, 1, 4, 0.0F);
/* 53 */     this.RearL.setRotationPoint(4.0F, 23.0F, 2.0F);
/* 54 */     this.RearR = new ModelRenderer(this, 0, 18);
/* 55 */     this.RearR.addBox(0.0F, 0.0F, -4.0F, 2, 1, 4, 0.0F);
/* 56 */     this.RearR.setRotationPoint(-4.0F, 23.0F, 2.0F);
/* 57 */     this.BodyF = new ModelRenderer(this, 20, 0);
/* 58 */     this.BodyF.addBox(-3.0F, -3.0F, -7.0F, 6, 6, 12, 0.0F);
/* 59 */     this.BodyF.setRotationPoint(0.0F, 20.0F, -2.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 64 */     super.render(entity, f, f1, f2, f3, f4, f5);
/* 65 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/* 66 */     this.Head.render(f5);
/* 67 */     this.EarR.render(f5);
/* 68 */     this.EarL.render(f5);
/* 69 */     this.WhiskerR.render(f5);
/* 70 */     this.WhiskerL.render(f5);
/* 71 */     this.Tail.render(f5);
/* 72 */     this.FrontL.render(f5);
/* 73 */     this.FrontR.render(f5);
/* 74 */     this.RearL.render(f5);
/* 75 */     this.RearR.render(f5);
/* 76 */     this.BodyF.render(f5);
/*    */   }
/*    */   
/*    */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 80 */     this.Head.rotateAngleX = -(f4 / 57.29578F);
/* 81 */     this.Head.rotateAngleY = f3 / 57.29578F;
/* 82 */     this.EarR.rotateAngleX = this.Head.rotateAngleX;
/* 83 */     this.EarR.rotateAngleY = this.Head.rotateAngleY;
/* 84 */     this.EarL.rotateAngleX = this.Head.rotateAngleX;
/* 85 */     this.EarL.rotateAngleY = this.Head.rotateAngleY;
/* 86 */     this.WhiskerR.rotateAngleX = this.Head.rotateAngleX;
/* 87 */     this.WhiskerR.rotateAngleY = this.Head.rotateAngleY;
/* 88 */     this.WhiskerL.rotateAngleX = this.Head.rotateAngleX;
/* 89 */     this.WhiskerL.rotateAngleY = this.Head.rotateAngleY;
/* 90 */     this.FrontL.rotateAngleX = MathHelper.cos(f * 0.6662F) * 0.6F * f1;
/* 91 */     this.RearL.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 0.8F * f1;
/* 92 */     this.RearR.rotateAngleX = MathHelper.cos(f * 0.6662F) * 0.6F * f1;
/* 93 */     this.FrontR.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 0.8F * f1;
/* 94 */     this.Tail.rotateAngleY = this.FrontL.rotateAngleX * 0.625F;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelMouse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */