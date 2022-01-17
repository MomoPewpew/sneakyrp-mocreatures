/*    */ package drzhark.mocreatures.client.renderer.entity;
/*    */ import drzhark.mocreatures.entity.passive.MoCEntityMouse;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import org.lwjgl.opengl.GL11;
/*    */
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCRenderMouse extends MoCRenderMoC<MoCEntityMouse> {
/*    */   public MoCRenderMouse(ModelBase modelbase, float f) {
/* 14 */     super(modelbase, f);
/*    */   }
/*    */
/*    */
/*    */   public void doRender(MoCEntityMouse entitymouse, double d, double d1, double d2, float f, float f1) {
/* 19 */     super.doRender(entitymouse, d, d1, d2, f, f1);
/*    */   }
/*    */
/*    */
/*    */   protected float handleRotationFloat(MoCEntityMouse entitymouse, float f) {
/* 24 */     stretch(entitymouse);
/* 25 */     return entitymouse.ticksExisted + f;
/*    */   }
/*    */
/*    */
/*    */   protected void preRenderCallback(MoCEntityMouse entitymouse, float f) {
/* 30 */     if (entitymouse.upsideDown()) {
/* 31 */       upsideDown(entitymouse);
/*    */     }
/*    */
/* 34 */     if (entitymouse.climbing()) {
/* 35 */       rotateAnimal(entitymouse);
/*    */     }
/*    */   }
/*    */
/*    */   protected void rotateAnimal(MoCEntityMouse entitymouse) {
/* 40 */     GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
/*    */   }
/*    */
/*    */   protected void stretch(MoCEntityMouse entitymouse) {
/* 44 */     float f = 0.6F;
/* 45 */     GL11.glScalef(f, f, f);
/*    */   }
/*    */
/*    */   protected void upsideDown(MoCEntityMouse entitymouse) {
/* 49 */     GL11.glRotatef(-90.0F, -1.0F, 0.0F, 0.0F);
/*    */
/* 51 */     GL11.glTranslatef(-0.55F, 0.0F, 0.0F);
/*    */   }
/*    */
/*    */
/*    */   protected ResourceLocation getEntityTexture(MoCEntityMouse entitymouse) {
/* 56 */     return entitymouse.getTexture();
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderMouse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
