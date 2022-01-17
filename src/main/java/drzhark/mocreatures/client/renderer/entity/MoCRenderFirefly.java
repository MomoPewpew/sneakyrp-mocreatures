/*    */ package drzhark.mocreatures.client.renderer.entity;
/*    */ import drzhark.mocreatures.entity.MoCEntityInsect;
/*    */ import drzhark.mocreatures.entity.ambient.MoCEntityFirefly;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCRenderFirefly extends MoCRenderInsect<MoCEntityFirefly> {
/*    */   public MoCRenderFirefly(ModelBase modelbase) {
/* 14 */     super(modelbase);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(MoCEntityFirefly entityfirefly, float par2) {
/* 20 */     if (entityfirefly.getIsFlying()) {
/* 21 */       rotateFirefly(entityfirefly);
/* 22 */     } else if (entityfirefly.climbing()) {
/* 23 */       rotateAnimal(entityfirefly);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void rotateFirefly(MoCEntityFirefly entityfirefly) {
/* 29 */     GL11.glRotatef(40.0F, -1.0F, 0.0F, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(MoCEntityFirefly entityfirefly) {
/* 35 */     return entityfirefly.getTexture();
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderFirefly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */