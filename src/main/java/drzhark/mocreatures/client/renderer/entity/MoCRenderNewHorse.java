/*    */ package drzhark.mocreatures.client.renderer.entity;
/*    */ import drzhark.mocreatures.client.model.MoCModelNewHorse;
/*    */ import drzhark.mocreatures.entity.passive.MoCEntityHorse;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCRenderNewHorse extends MoCRenderMoC<MoCEntityHorse> {
/*    */   public MoCRenderNewHorse(MoCModelNewHorse modelbase) {
/* 14 */     super((ModelBase)modelbase, 0.5F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(MoCEntityHorse entityhorse) {
/* 19 */     return entityhorse.getTexture();
/*    */   }
/*    */   
/*    */   protected void adjustHeight(MoCEntityHorse entityhorse, float FHeight) {
/* 23 */     GL11.glTranslatef(0.0F, FHeight, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(MoCEntityHorse entityhorse, float f) {
/* 28 */     if (!entityhorse.getIsAdult() || entityhorse.getType() > 64) {
/* 29 */       stretch(entityhorse);
/*    */     }
/* 31 */     if (entityhorse.getIsGhost()) {
/* 32 */       adjustHeight(entityhorse, -0.3F + entityhorse.tFloat() / 5.0F);
/*    */     }
/* 34 */     super.preRenderCallback(entityhorse, f);
/*    */   }
/*    */   
/*    */   protected void stretch(MoCEntityHorse entityhorse) {
/* 38 */     float sizeFactor = entityhorse.getEdad() * 0.01F;
/* 39 */     if (entityhorse.getIsAdult()) {
/* 40 */       sizeFactor = 1.0F;
/*    */     }
/* 42 */     if (entityhorse.getType() > 64)
/*    */     {
/* 44 */       sizeFactor *= 0.9F;
/*    */     }
/* 46 */     GL11.glScalef(sizeFactor, sizeFactor, sizeFactor);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderNewHorse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */