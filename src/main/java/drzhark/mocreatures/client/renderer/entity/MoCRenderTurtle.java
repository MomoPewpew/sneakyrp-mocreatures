/*    */ package drzhark.mocreatures.client.renderer.entity;
/*    */ import drzhark.mocreatures.client.model.MoCModelTurtle;
/*    */ import drzhark.mocreatures.entity.passive.MoCEntityTurtle;
/*    */ import net.minecraft.block.material.Material;
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
/*    */ public class MoCRenderTurtle extends MoCRenderMoC<MoCEntityTurtle> {
/*    */   public MoCRenderTurtle(MoCModelTurtle modelbase, float f) {
/* 17 */     super((ModelBase)modelbase, f);
/* 18 */     this.turtly = modelbase;
/*    */   }
/*    */   public MoCModelTurtle turtly;
/*    */   
/*    */   protected void preRenderCallback(MoCEntityTurtle entityturtle, float f) {
/* 23 */     this.turtly.upsidedown = entityturtle.getIsUpsideDown();
/* 24 */     this.turtly.swingProgress = entityturtle.swingProgress;
/* 25 */     this.turtly.isHiding = entityturtle.getIsHiding();
/*    */     
/* 27 */     if (!entityturtle.world.isRemote && entityturtle.getRidingEntity() != null)
/*    */     {
/* 29 */       GL11.glTranslatef(0.0F, 1.3F, 0.0F);
/*    */     }
/*    */     
/* 32 */     if (entityturtle.getIsHiding()) {
/* 33 */       adjustHeight(entityturtle, 0.15F * entityturtle.getEdad() * 0.01F);
/* 34 */     } else if (!entityturtle.getIsHiding() && !entityturtle.getIsUpsideDown() && !entityturtle.isInsideOfMaterial(Material.WATER)) {
/* 35 */       adjustHeight(entityturtle, 0.05F * entityturtle.getEdad() * 0.01F);
/*    */     } 
/* 37 */     if (entityturtle.getIsUpsideDown()) {
/* 38 */       rotateAnimal(entityturtle);
/*    */     }
/*    */     
/* 41 */     stretch(entityturtle);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void rotateAnimal(MoCEntityTurtle entityturtle) {
/* 49 */     float f = entityturtle.swingProgress * 10.0F * entityturtle.getFlipDirection();
/* 50 */     float f2 = entityturtle.swingProgress / 30.0F * entityturtle.getFlipDirection();
/* 51 */     GL11.glRotatef(180.0F + f, 0.0F, 0.0F, -1.0F);
/* 52 */     GL11.glTranslatef(0.0F - f2, 0.5F * entityturtle.getEdad() * 0.01F, 0.0F);
/*    */   }
/*    */   
/*    */   protected void adjustHeight(MoCEntityTurtle entityturtle, float height) {
/* 56 */     GL11.glTranslatef(0.0F, height, 0.0F);
/*    */   }
/*    */   
/*    */   protected void stretch(MoCEntityTurtle entityturtle) {
/* 60 */     float f = entityturtle.getEdad() * 0.01F;
/* 61 */     GL11.glScalef(f, f, f);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(MoCEntityTurtle entityturtle) {
/* 66 */     return entityturtle.getTexture();
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderTurtle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */