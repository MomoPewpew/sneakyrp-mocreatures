/*    */ package drzhark.mocreatures.client.renderer.entity;
/*    */ import drzhark.mocreatures.entity.passive.MoCEntitySnake;
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
/*    */ public class MoCRenderSnake extends MoCRenderMoC<MoCEntitySnake> {
/*    */   public MoCRenderSnake(ModelBase modelbase, float f) {
/* 15 */     super(modelbase, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(MoCEntitySnake par1Entity) {
/* 20 */     return par1Entity.getTexture();
/*    */   }
/*    */   
/*    */   protected void adjustHeight(MoCEntitySnake entitysnake, float FHeight) {
/* 24 */     GL11.glTranslatef(0.0F, FHeight, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(MoCEntitySnake entitysnake, float f) {
/* 29 */     stretch(entitysnake);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 36 */     if (entitysnake.pickedUp()) {
/*    */       
/* 38 */       float xOff = entitysnake.getSizeF() - 1.0F;
/* 39 */       if (xOff > 0.0F) {
/* 40 */         xOff = 0.0F;
/*    */       }
/* 42 */       if (entitysnake.world.isRemote) {
/* 43 */         GL11.glTranslatef(xOff, 0.0F, 0.0F);
/*    */       } else {
/* 45 */         GL11.glTranslatef(xOff, 0.0F, 0.0F);
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 56 */     if (entitysnake.isInsideOfMaterial(Material.WATER)) {
/* 57 */       adjustHeight(entitysnake, -0.25F);
/*    */     }
/*    */     
/* 60 */     super.preRenderCallback(entitysnake, f);
/*    */   }
/*    */   
/*    */   protected void stretch(MoCEntitySnake entitysnake) {
/* 64 */     float f = entitysnake.getSizeF();
/* 65 */     GL11.glScalef(f, f, f);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderSnake.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */