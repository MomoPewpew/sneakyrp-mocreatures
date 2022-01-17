/*    */ package drzhark.mocreatures.client.model;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class MoCModelMaggot
/*    */   extends ModelBase {
/*    */   ModelRenderer Head;
/*    */   ModelRenderer Body;
/*    */   ModelRenderer Tail;
/*    */   ModelRenderer Tailtip;
/*    */   
/*    */   public MoCModelMaggot() {
/* 17 */     this.textureWidth = 32;
/* 18 */     this.textureHeight = 32;
/*    */     
/* 20 */     this.Head = new ModelRenderer(this, 0, 11);
/* 21 */     this.Head.addBox(-1.0F, -1.0F, -2.0F, 2, 2, 2);
/* 22 */     this.Head.setRotationPoint(0.0F, 23.0F, -2.0F);
/*    */     
/* 24 */     this.Body = new ModelRenderer(this, 0, 0);
/* 25 */     this.Body.addBox(-1.5F, -2.0F, 0.0F, 3, 3, 4);
/* 26 */     this.Body.setRotationPoint(0.0F, 23.0F, -2.0F);
/*    */     
/* 28 */     this.Tail = new ModelRenderer(this, 0, 7);
/* 29 */     this.Tail.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 2);
/* 30 */     this.Tail.setRotationPoint(0.0F, 23.0F, 2.0F);
/*    */     
/* 32 */     this.Tailtip = new ModelRenderer(this, 8, 7);
/* 33 */     this.Tailtip.addBox(-0.5F, 0.0F, 0.0F, 1, 1, 1);
/* 34 */     this.Tailtip.setRotationPoint(0.0F, 23.0F, 4.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 40 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 46 */     GL11.glPushMatrix();
/* 47 */     GL11.glEnable(3042);
/*    */     
/* 49 */     GL11.glBlendFunc(770, 771);
/*    */     
/* 51 */     float f9 = -MathHelper.cos(f * 3.0F) * f1 * 2.0F;
/*    */     
/* 53 */     GL11.glScalef(1.0F, 1.0F, 1.0F + f9);
/*    */     
/* 55 */     this.Head.render(f5);
/* 56 */     this.Body.render(f5);
/* 57 */     this.Tail.render(f5);
/* 58 */     this.Tailtip.render(f5);
/* 59 */     GL11.glDisable(3042);
/* 60 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 66 */     model.rotateAngleX = x;
/* 67 */     model.rotateAngleY = y;
/* 68 */     model.rotateAngleZ = z;
/*    */   }
/*    */   
/*    */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {}
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelMaggot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */