/*    */ package drzhark.mocreatures.client.renderer.entity;
/*    */ import drzhark.mocreatures.client.model.MoCModelBear;
/*    */ import drzhark.mocreatures.entity.passive.MoCEntityBear;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCRenderBear extends MoCRenderMoC<MoCEntityBear> {
/*    */   public MoCRenderBear(MoCModelBear modelbase, float f) {
/* 14 */     super(modelbase, f);
/*    */   }
/*    */
/*    */
/*    */   protected void preRenderCallback(MoCEntityBear entitybear, float f) {
/* 19 */     stretch(entitybear);
/* 20 */     super.preRenderCallback(entitybear, f);
/*    */   }
/*    */
/*    */
/*    */   protected void stretch(MoCEntityBear entitybear) {
/* 25 */     float sizeFactor = entitybear.getEdad() * 0.01F;
/* 26 */     if (entitybear.getIsAdult()) {
/* 27 */       sizeFactor = 1.0F;
/*    */     }
/* 29 */     sizeFactor *= entitybear.getBearSize();
/* 30 */     GL11.glScalef(sizeFactor, sizeFactor, sizeFactor);
/*    */   }
/*    */
/*    */
/*    */   protected ResourceLocation getEntityTexture(MoCEntityBear entitybear) {
/* 35 */     return entitybear.getTexture();
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderBear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
