/*    */ package drzhark.mocreatures.client.renderer.entity;
/*    */ import drzhark.mocreatures.client.MoCClientProxy;
/*    */ import drzhark.mocreatures.entity.monster.MoCEntityWraith;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelBiped;
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
/*    */ public class MoCRenderWraith extends RenderLiving<MoCEntityWraith> {
/*    */   public MoCRenderWraith(ModelBiped modelbiped, float f) {
/* 17 */     super(MoCClientProxy.mc.getRenderManager(), (ModelBase)modelbiped, f);
/*    */   }
/*    */
/*    */
/*    */
/*    */
/*    */   public void doRender(MoCEntityWraith entitywraith, double d, double d1, double d2, float f, float f1) {
/* 24 */     boolean flag = false;
/*    */
/* 26 */     GL11.glPushMatrix();
/* 27 */     GL11.glEnable(3042);
/* 28 */     if (!flag) {
/* 29 */       float transparency = 0.6F;
/* 30 */       GL11.glBlendFunc(770, 771);
/* 31 */       GL11.glColor4f(0.8F, 0.8F, 0.8F, transparency);
/*    */     } else {
/* 33 */       GL11.glBlendFunc(770, 1);
/*    */     }
/* 35 */     super.doRender(entitywraith, d, d1, d2, f, f1);
/* 36 */     GL11.glDisable(3042);
/* 37 */     GL11.glPopMatrix();
/*    */   }
/*    */
/*    */
/*    */
/*    */   protected ResourceLocation getEntityTexture(MoCEntityWraith entitywraith) {
/* 43 */     return entitywraith.getTexture();
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderWraith.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
