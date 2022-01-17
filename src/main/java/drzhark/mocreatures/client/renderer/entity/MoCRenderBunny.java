/*    */ package drzhark.mocreatures.client.renderer.entity;
/*    */ import drzhark.mocreatures.entity.passive.MoCEntityBunny;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCRenderBunny extends MoCRenderMoC<MoCEntityBunny> {
/*    */   protected ResourceLocation getEntityTexture(MoCEntityBunny entitybunny) {
/* 15 */     return entitybunny.getTexture();
/*    */   }
/*    */   
/*    */   public MoCRenderBunny(ModelBase modelbase, float f) {
/* 19 */     super(modelbase, f);
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRender(MoCEntityBunny entitybunny, double d, double d1, double d2, float f, float f1) {
/* 24 */     super.doRender(entitybunny, d, d1, d2, f, f1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected float handleRotationFloat(MoCEntityBunny entitybunny, float f) {
/* 29 */     if (!entitybunny.getIsAdult()) {
/* 30 */       stretch(entitybunny);
/*    */     }
/* 32 */     return entitybunny.ticksExisted + f;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(MoCEntityBunny entitybunny, float f) {
/* 37 */     rotBunny(entitybunny);
/* 38 */     adjustOffsets(entitybunny.getAdjustedXOffset(), entitybunny.getAdjustedYOffset(), entitybunny.getAdjustedZOffset());
/*    */   }
/*    */   
/*    */   protected void rotBunny(MoCEntityBunny entitybunny) {
/* 42 */     if (!entitybunny.onGround && entitybunny.getRidingEntity() == null) {
/* 43 */       if (entitybunny.motionY > 0.5D) {
/* 44 */         GL11.glRotatef(35.0F, -1.0F, 0.0F, 0.0F);
/* 45 */       } else if (entitybunny.motionY < -0.5D) {
/* 46 */         GL11.glRotatef(-35.0F, -1.0F, 0.0F, 0.0F);
/*    */       } else {
/* 48 */         GL11.glRotatef((float)(entitybunny.motionY * 70.0D), -1.0F, 0.0F, 0.0F);
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   protected void stretch(MoCEntityBunny entitybunny) {
/* 54 */     float f = entitybunny.getEdad() * 0.01F;
/* 55 */     GL11.glScalef(f, f, f);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderBunny.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */