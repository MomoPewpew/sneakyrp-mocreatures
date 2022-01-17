/*    */ package drzhark.mocreatures.client.model;
/*    */ 
/*    */ import drzhark.mocreatures.entity.aquatic.MoCEntityFishy;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCModelFishy
/*    */   extends ModelBase {
/*    */   public ModelRenderer Body;
/*    */   public ModelRenderer UpperFin;
/*    */   public ModelRenderer LowerFin;
/*    */   public ModelRenderer RightFin;
/*    */   public ModelRenderer LeftFin;
/*    */   public ModelRenderer Tail;
/*    */   
/*    */   public MoCModelFishy() {
/* 23 */     this.Body = new ModelRenderer(this, 0, 0);
/* 24 */     this.Body.addBox(0.0F, 0.0F, -3.5F, 1, 5, 5, 0.0F);
/* 25 */     this.Body.setRotationPoint(0.0F, 18.0F, -1.0F);
/* 26 */     this.Body.rotateAngleX = 0.7853981F;
/* 27 */     this.Tail = new ModelRenderer(this, 12, 0);
/* 28 */     this.Tail.addBox(0.0F, 0.0F, 0.0F, 1, 3, 3, 0.0F);
/* 29 */     this.Tail.setRotationPoint(0.0F, 20.5F, 3.0F);
/* 30 */     this.Tail.rotateAngleX = 0.7853981F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 35 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/* 36 */     MoCEntityFishy smallFish = (MoCEntityFishy)entity;
/* 37 */     float yOffset = smallFish.getAdjustedYOffset();
/* 38 */     float xOffset = smallFish.getAdjustedXOffset();
/* 39 */     float zOffset = smallFish.getAdjustedZOffset();
/* 40 */     GL11.glPushMatrix();
/* 41 */     GL11.glTranslatef(xOffset, yOffset, zOffset);
/* 42 */     this.Body.render(f5);
/* 43 */     this.Tail.render(f5);
/* 44 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 48 */     this.Tail.rotateAngleY = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelFishy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */