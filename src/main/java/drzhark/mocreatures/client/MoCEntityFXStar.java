/*    */ package drzhark.mocreatures.client;
/*    */ 
/*    */ import drzhark.mocreatures.MoCProxy;
/*    */ import net.minecraft.client.particle.Particle;
/*    */ import net.minecraft.client.renderer.BufferBuilder;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.client.FMLClientHandler;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCEntityFXStar
/*    */   extends Particle {
/*    */   public MoCEntityFXStar(World world, double posX, double posY, double posZ, float red, float green, float blue) {
/* 17 */     super(world, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
/* 18 */     this.motionX *= 0.8D;
/* 19 */     this.motionY *= 0.8D;
/* 20 */     this.motionZ *= 0.8D;
/* 21 */     this.motionY = (this.rand.nextFloat() * 0.4F + 0.05F);
/*    */     
/* 23 */     this.particleRed = red;
/* 24 */     this.particleGreen = green;
/* 25 */     this.particleBlue = blue;
/*    */     
/* 27 */     setSize(0.01F, 0.01F);
/* 28 */     this.particleGravity = 0.06F;
/* 29 */     this.particleMaxAge = (int)(64.0D / (Math.random() * 0.8D + 0.2D));
/* 30 */     this.particleScale *= 0.6F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFXLayer() {
/* 38 */     return 1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 46 */     this.prevPosX = this.posX;
/* 47 */     this.prevPosY = this.posY;
/* 48 */     this.prevPosZ = this.posZ;
/* 49 */     this.particleScale *= 0.995F;
/*    */     
/* 51 */     this.motionY -= 0.03D;
/* 52 */     move(this.motionX, this.motionY, this.motionZ);
/* 53 */     this.motionX *= 0.9D;
/* 54 */     this.motionY *= 0.2D;
/* 55 */     this.motionZ *= 0.9D;
/*    */     
/* 57 */     if (this.onGround) {
/* 58 */       this.motionX *= 0.7D;
/* 59 */       this.motionZ *= 0.7D;
/*    */     } 
/*    */     
/* 62 */     if (this.particleMaxAge-- <= 0) {
/* 63 */       setExpired();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setParticleTextureIndex(int par1) {}
/*    */ 
/*    */   
/*    */   public void renderParticle(BufferBuilder worldRendererIn, Entity entityIn, float partialTicks, float par3, float par4, float par5, float par6, float par7) {
/* 73 */     (FMLClientHandler.instance().getClient()).renderEngine.bindTexture(new ResourceLocation("mocreatures", MoCProxy.MISC_TEXTURE + "fxstar.png"));
/* 74 */     float sizeFactor = 0.1F * this.particleScale;
/* 75 */     float var13 = (float)(this.prevPosX + (this.posX - this.prevPosX) * partialTicks - interpPosX);
/* 76 */     float var14 = (float)(this.prevPosY + (this.posY - this.prevPosY) * partialTicks - interpPosY);
/* 77 */     float var15 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * partialTicks - interpPosZ);
/* 78 */     float var16 = 1.2F - (float)Math.random() * 0.5F;
/* 79 */     int i = getBrightnessForRender(partialTicks);
/* 80 */     int j = i >> 16 & 0xFFFF;
/* 81 */     int k = i & 0xFFFF;
/* 82 */     worldRendererIn.pos((var13 - par3 * sizeFactor - par6 * sizeFactor), (var14 - par4 * sizeFactor), (var15 - par5 * sizeFactor - par7 * sizeFactor))
/* 83 */       .tex(0.0D, 1.0D).color(this.particleRed * var16, this.particleGreen * var16, this.particleBlue * var16, 1.0F).lightmap(j, k).endVertex();
/* 84 */     worldRendererIn.pos((var13 - par3 * sizeFactor + par6 * sizeFactor), (var14 + par4 * sizeFactor), (var15 - par5 * sizeFactor + par7 * sizeFactor))
/* 85 */       .tex(1.0D, 1.0D).color(this.particleRed * var16, this.particleGreen * var16, this.particleBlue * var16, 1.0F).lightmap(j, k).endVertex();
/* 86 */     worldRendererIn.pos((var13 + par3 * sizeFactor + par6 * sizeFactor), (var14 + par4 * sizeFactor), (var15 + par5 * sizeFactor + par7 * sizeFactor))
/* 87 */       .tex(1.0D, 0.0D).color(this.particleRed * var16, this.particleGreen * var16, this.particleBlue * var16, 1.0F).lightmap(j, k).endVertex();
/* 88 */     worldRendererIn.pos((var13 + par3 * sizeFactor - par6 * sizeFactor), (var14 - par4 * sizeFactor), (var15 + par5 * sizeFactor - par7 * sizeFactor))
/* 89 */       .tex(0.0D, 0.0D).color(this.particleRed * var16, this.particleGreen * var16, this.particleBlue * var16, 1.0F).lightmap(j, k).endVertex();
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\MoCEntityFXStar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */