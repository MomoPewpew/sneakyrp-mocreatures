/*    */ package drzhark.mocreatures.client.renderer.entity;
/*    */ import drzhark.mocreatures.client.MoCClientProxy;
/*    */ import drzhark.mocreatures.client.model.MoCModelCrocodile;
/*    */ import drzhark.mocreatures.entity.passive.MoCEntityCrocodile;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
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
/*    */ public class MoCRenderCrocodile extends RenderLiving<MoCEntityCrocodile> {
/*    */   public MoCRenderCrocodile(MoCModelCrocodile modelbase, float f) {
/* 19 */     super(MoCClientProxy.mc.getRenderManager(), (ModelBase)modelbase, f);
/* 20 */     this.croc = modelbase;
/*    */   }
/*    */   public MoCModelCrocodile croc;
/*    */   
/*    */   protected ResourceLocation getEntityTexture(MoCEntityCrocodile entitycrocodile) {
/* 25 */     return entitycrocodile.getTexture();
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRender(MoCEntityCrocodile entitycrocodile, double d, double d1, double d2, float f, float f1) {
/* 30 */     super.doRender((EntityLiving)entitycrocodile, d, d1, d2, f, f1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(MoCEntityCrocodile entitycrocodile, float f) {
/* 35 */     this.croc.biteProgress = entitycrocodile.biteProgress;
/* 36 */     this.croc.swimming = entitycrocodile.isSwimming();
/* 37 */     this.croc.resting = entitycrocodile.getIsSitting();
/* 38 */     if (entitycrocodile.isSpinning()) {
/* 39 */       spinCroc(entitycrocodile, (EntityLiving)entitycrocodile.getRidingEntity());
/*    */     }
/* 41 */     stretch(entitycrocodile);
/* 42 */     if (entitycrocodile.getIsSitting() && 
/* 43 */       !entitycrocodile.isInsideOfMaterial(Material.WATER)) {
/* 44 */       adjustHeight(entitycrocodile, 0.2F);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void rotateAnimal(MoCEntityCrocodile entitycrocodile) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void adjustHeight(MoCEntityCrocodile entitycrocodile, float FHeight) {
/* 62 */     GL11.glTranslatef(0.0F, FHeight, 0.0F);
/*    */   }
/*    */   
/*    */   protected void spinCroc(MoCEntityCrocodile entitycrocodile, EntityLiving prey) {
/* 66 */     int intSpin = entitycrocodile.spinInt;
/* 67 */     int direction = 1;
/* 68 */     if (intSpin > 40) {
/* 69 */       intSpin -= 40;
/* 70 */       direction = -1;
/*    */     } 
/* 72 */     int intEndSpin = intSpin;
/* 73 */     if (intSpin >= 20) {
/* 74 */       intEndSpin = 20 - intSpin - 20;
/*    */     }
/* 76 */     if (intEndSpin == 0) {
/* 77 */       intEndSpin = 1;
/*    */     }
/* 79 */     float f3 = (intEndSpin - 1.0F) / 20.0F * 1.6F;
/* 80 */     f3 = MathHelper.sqrt(f3);
/* 81 */     if (f3 > 1.0F) {
/* 82 */       f3 = 1.0F;
/*    */     }
/* 84 */     f3 *= direction;
/* 85 */     GL11.glRotatef(f3 * 90.0F, 0.0F, 0.0F, 1.0F);
/*    */     
/* 87 */     if (prey != null) {
/* 88 */       prey.deathTime = intEndSpin;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected void stretch(MoCEntityCrocodile entitycrocodile) {
/* 94 */     float f = entitycrocodile.getEdad() * 0.01F;
/*    */     
/* 96 */     GL11.glScalef(f, f, f);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderCrocodile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */