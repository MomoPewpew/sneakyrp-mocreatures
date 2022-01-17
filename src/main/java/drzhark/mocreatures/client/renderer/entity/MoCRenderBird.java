/*    */ package drzhark.mocreatures.client.renderer.entity;
/*    */ 
/*    */ import drzhark.mocreatures.entity.passive.MoCEntityBird;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCRenderBird extends MoCRenderMoC<MoCEntityBird> {
/*    */   protected ResourceLocation getEntityTexture(EntityLiving par1Entity) {
/* 17 */     return ((MoCEntityBird)par1Entity).getTexture();
/*    */   }
/*    */   
/*    */   public MoCRenderBird(ModelBase modelbase, float f) {
/* 21 */     super(modelbase, f);
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRender(MoCEntityBird entitybird, double d, double d1, double d2, float f, float f1) {
/* 26 */     super.doRender(entitybird, d, d1, d2, f, f1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected float handleRotationFloat(MoCEntityBird entitybird, float f) {
/* 32 */     float f1 = entitybird.winge + (entitybird.wingb - entitybird.winge) * f;
/* 33 */     float f2 = entitybird.wingd + (entitybird.wingc - entitybird.wingd) * f;
/* 34 */     return (MathHelper.sin(f1) + 1.0F) * f2;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(MoCEntityBird entitybird, float f) {
/* 39 */     if (!entitybird.world.isRemote && entitybird.getRidingEntity() != null)
/* 40 */       GL11.glTranslatef(0.0F, 1.3F, 0.0F); 
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderBird.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */