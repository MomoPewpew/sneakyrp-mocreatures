/*    */ package drzhark.mocreatures.client.renderer.entity;
/*    */ import drzhark.mocreatures.client.MoCClientProxy;
/*    */ import drzhark.mocreatures.entity.monster.MoCEntityRat;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import org.lwjgl.opengl.GL11;
/*    */
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCRenderRat<T extends MoCEntityRat> extends RenderLiving<T> {
/*    */   public MoCRenderRat(ModelBase modelbase, float f) {
/* 16 */     super(MoCClientProxy.mc.getRenderManager(), modelbase, f);
/*    */   }
/*    */
/*    */
/*    */   public void doRender(T entityrat, double d, double d1, double d2, float f, float f1) {
/* 21 */     super.doRender(entityrat, d, d1, d2, f, f1);
/*    */   }
/*    */
/*    */
/*    */   protected float handleRotationFloat(T entityrat, float f) {
/* 26 */     stretch(entityrat);
/* 27 */     return ((MoCEntityRat)entityrat).ticksExisted + f;
/*    */   }
/*    */
/*    */
/*    */   protected void preRenderCallback(T entityrat, float f) {
/* 32 */     if (entityrat.climbing()) {
/* 33 */       rotateAnimal(entityrat);
/*    */     }
/*    */   }
/*    */
/*    */   protected void rotateAnimal(T entityrat) {
/* 38 */     GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
/*    */   }
/*    */
/*    */   protected void stretch(T entityrat) {
/* 42 */     float f = 0.8F;
/* 43 */     GL11.glScalef(f, f, f);
/*    */   }
/*    */
/*    */
/*    */   protected ResourceLocation getEntityTexture(T entityrat) {
/* 48 */     return entityrat.getTexture();
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderRat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
