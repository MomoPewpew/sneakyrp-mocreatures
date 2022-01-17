/*    */ package drzhark.mocreatures.client.renderer.entity;
/*    */ import drzhark.mocreatures.client.model.MoCModelScorpion;
/*    */ import drzhark.mocreatures.entity.monster.MoCEntityScorpion;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCRenderScorpion extends MoCRenderMoC<MoCEntityScorpion> {
/*    */   public MoCRenderScorpion(MoCModelScorpion modelbase, float f) {
/* 14 */     super((ModelBase)modelbase, f);
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRender(MoCEntityScorpion entityscorpion, double d, double d1, double d2, float f, float f1) {
/* 19 */     super.doRender(entityscorpion, d, d1, d2, f, f1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(MoCEntityScorpion entityscorpion, float f) {
/* 24 */     if (entityscorpion.climbing()) {
/* 25 */       rotateAnimal(entityscorpion);
/*    */     }
/*    */     
/* 28 */     if (!entityscorpion.getIsAdult()) {
/* 29 */       stretch(entityscorpion);
/* 30 */       if (entityscorpion.getIsPicked()) {
/* 31 */         upsideDown(entityscorpion);
/*    */       }
/*    */     } else {
/* 34 */       adjustHeight(entityscorpion);
/*    */     } 
/*    */   }
/*    */   
/*    */   protected void upsideDown(MoCEntityScorpion entityscorpion) {
/* 39 */     GL11.glRotatef(-90.0F, -1.0F, 0.0F, 0.0F);
/* 40 */     GL11.glTranslatef(-1.5F, -0.5F, -2.5F);
/*    */   }
/*    */   
/*    */   protected void adjustHeight(MoCEntityScorpion entityscorpion) {
/* 44 */     GL11.glTranslatef(0.0F, -0.1F, 0.0F);
/*    */   }
/*    */   
/*    */   protected void rotateAnimal(MoCEntityScorpion entityscorpion) {
/* 48 */     GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void stretch(MoCEntityScorpion entityscorpion) {
/* 53 */     float f = 1.1F;
/* 54 */     if (!entityscorpion.getIsAdult()) {
/* 55 */       f = entityscorpion.getEdad() * 0.01F;
/*    */     }
/* 57 */     GL11.glScalef(f, f, f);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(MoCEntityScorpion entityscorpion) {
/* 62 */     return entityscorpion.getTexture();
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderScorpion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */