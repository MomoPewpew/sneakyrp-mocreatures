/*    */ package drzhark.mocreatures.client.renderer.entity;
/*    */ import drzhark.mocreatures.client.MoCClientProxy;
/*    */ import drzhark.mocreatures.entity.item.MoCEntityEgg;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCRenderEgg extends RenderLiving<MoCEntityEgg> {
/*    */   public MoCRenderEgg(ModelBase modelbase, float f) {
/* 16 */     super(MoCClientProxy.mc.getRenderManager(), modelbase, f);
/*    */   }
/*    */
/*    */
/*    */   protected void preRenderCallback(MoCEntityEgg entityegg, float f) {
/* 21 */     stretch(entityegg);
/* 22 */     super.preRenderCallback(entityegg, f);
/*    */   }
/*    */
/*    */
/*    */   protected void stretch(MoCEntityEgg entityegg) {
/* 27 */     float f = entityegg.getSize() * 0.01F;
/* 28 */     GL11.glScalef(f, f, f);
/*    */   }
/*    */
/*    */
/*    */   protected ResourceLocation getEntityTexture(MoCEntityEgg entityegg) {
/* 33 */     return entityegg.getTexture();
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
