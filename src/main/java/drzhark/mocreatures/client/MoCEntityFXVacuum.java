/*    */ package drzhark.mocreatures.client;
/*    */ 
/*    */ import net.minecraft.client.particle.Particle;
/*    */ import net.minecraft.client.renderer.BufferBuilder;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCEntityFXVacuum
/*    */   extends Particle
/*    */ {
/*    */   private final float portalParticleScale;
/*    */   private final double portalPosX;
/*    */   private final double portalPosY;
/*    */   private final double portalPosZ;
/*    */   
/*    */   public MoCEntityFXVacuum(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, float red, float green, float blue, int partTexture) {
/* 20 */     super(par1World, par2, par4, par6, par8, par10, par12);
/*    */     
/* 22 */     this.particleRed = red;
/* 23 */     this.particleGreen = green;
/* 24 */     this.particleBlue = blue;
/*    */     
/* 26 */     this.motionX = par8;
/* 27 */     this.motionY = par10;
/* 28 */     this.motionZ = par12;
/* 29 */     this.portalPosX = this.posX = par2;
/* 30 */     this.portalPosY = this.posY = par4;
/* 31 */     this.portalPosZ = this.posZ = par6;
/* 32 */     this.portalParticleScale = this.particleScale = this.rand.nextFloat() * 0.2F + 0.5F;
/* 33 */     setParticleTextureIndex(partTexture);
/* 34 */     this.particleMaxAge = (int)(Math.random() * 10.0D) + 30;
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderParticle(BufferBuilder worldRendererIn, Entity entityIn, float partialTicks, float par3, float par4, float par5, float par6, float par7) {
/* 39 */     float var8 = (this.particleAge + partialTicks) / this.particleMaxAge;
/* 40 */     var8 = 1.0F - var8;
/* 41 */     var8 *= var8;
/* 42 */     var8 = 1.0F - var8;
/* 43 */     this.particleScale = this.portalParticleScale * var8;
/* 44 */     super.renderParticle(worldRendererIn, entityIn, partialTicks, par3, par4, par5, par6, par7);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBrightnessForRender(float par1) {
/* 49 */     int var2 = super.getBrightnessForRender(par1);
/* 50 */     float var3 = this.particleAge / this.particleMaxAge;
/* 51 */     var3 *= var3;
/* 52 */     var3 *= var3;
/* 53 */     int var4 = var2 & 0xFF;
/* 54 */     int var5 = var2 >> 16 & 0xFF;
/* 55 */     var5 += (int)(var3 * 15.0F * 16.0F);
/*    */     
/* 57 */     if (var5 > 240) {
/* 58 */       var5 = 240;
/*    */     }
/*    */     
/* 61 */     return var4 | var5 << 16;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 77 */     this.prevPosX = this.posX;
/* 78 */     this.prevPosY = this.posY;
/* 79 */     this.prevPosZ = this.posZ;
/* 80 */     float var1 = this.particleAge / this.particleMaxAge;
/* 81 */     float var2 = var1;
/* 82 */     var1 = -var1 + var1 * var1 * 2.0F;
/* 83 */     var1 = 1.0F - var1;
/* 84 */     this.posX = this.portalPosX + this.motionX * var1;
/* 85 */     this.posY = this.portalPosY + this.motionY * var1 + (1.0F - var2);
/* 86 */     this.posZ = this.portalPosZ + this.motionZ * var1;
/*    */     
/* 88 */     if (this.particleAge++ >= this.particleMaxAge)
/* 89 */       setExpired(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\MoCEntityFXVacuum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */