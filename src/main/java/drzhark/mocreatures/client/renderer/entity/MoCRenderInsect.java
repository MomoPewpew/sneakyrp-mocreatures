/*    */ package drzhark.mocreatures.client.renderer.entity;
/*    */ import drzhark.mocreatures.entity.MoCEntityInsect;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCRenderInsect<T extends MoCEntityInsect> extends MoCRenderMoC<T> {
/*    */   public MoCRenderInsect(ModelBase modelbase) {
/* 13 */     super(modelbase, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(T entityinsect, float par2) {
/* 19 */     if (entityinsect.climbing()) {
/* 20 */       rotateAnimal(entityinsect);
/*    */     }
/*    */     
/* 23 */     stretch(entityinsect);
/*    */   }
/*    */   
/*    */   protected void rotateAnimal(T entityinsect) {
/* 27 */     GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
/*    */   }
/*    */   
/*    */   protected void stretch(T entityinsect) {
/* 31 */     float sizeFactor = entityinsect.getSizeFactor();
/* 32 */     GL11.glScalef(sizeFactor, sizeFactor, sizeFactor);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderInsect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */