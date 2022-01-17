/*    */ package drzhark.mocreatures.client.renderer.entity;
/*    */ import drzhark.mocreatures.entity.MoCEntityInsect;
/*    */ import drzhark.mocreatures.entity.ambient.MoCEntityButterfly;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCRenderButterfly extends MoCRenderInsect<MoCEntityButterfly> {
/*    */   public MoCRenderButterfly(ModelBase modelbase) {
/* 14 */     super(modelbase);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(MoCEntityButterfly entitybutterfly, float par2) {
/* 20 */     if (entitybutterfly.isOnAir() || !entitybutterfly.onGround) {
/* 21 */       adjustHeight(entitybutterfly, entitybutterfly.tFloat());
/*    */     }
/* 23 */     if (entitybutterfly.climbing()) {
/* 24 */       rotateAnimal(entitybutterfly);
/*    */     }
/* 26 */     stretch(entitybutterfly);
/*    */   }
/*    */   
/*    */   protected void adjustHeight(MoCEntityButterfly entitybutterfly, float FHeight) {
/* 30 */     GL11.glTranslatef(0.0F, FHeight, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(MoCEntityButterfly entitybutterfly) {
/* 35 */     return entitybutterfly.getTexture();
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderButterfly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */