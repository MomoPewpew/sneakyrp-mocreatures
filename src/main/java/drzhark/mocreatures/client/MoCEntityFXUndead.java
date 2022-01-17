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
/*    */ public class MoCEntityFXUndead
/*    */   extends Particle {
/*    */   public MoCEntityFXUndead(World par1World, double par2, double par4, double par6) {
/* 17 */     super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
/* 18 */     this.motionX *= 0.8D;
/* 19 */     this.motionY *= 0.8D;
/* 20 */     this.motionZ *= 0.8D;
/* 21 */     this.motionY = (this.rand.nextFloat() * 0.4F + 0.05F);
/*    */     
/* 23 */     setSize(0.01F, 0.01F);
/* 24 */     this.particleGravity = 0.06F;
/* 25 */     this.particleMaxAge = (int)(32.0D / (Math.random() * 0.8D + 0.2D));
/* 26 */     this.particleScale *= 0.8F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFXLayer() {
/* 34 */     if (this.onGround) {
/* 35 */       return 1;
/*    */     }
/* 37 */     return 2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 45 */     this.prevPosX = this.posX;
/* 46 */     this.prevPosY = this.posY;
/* 47 */     this.prevPosZ = this.posZ;
/*    */     
/* 49 */     this.motionY -= 0.03D;
/* 50 */     move(this.motionX, this.motionY, this.motionZ);
/*    */     
/* 52 */     this.motionX *= 0.8D;
/* 53 */     this.motionY *= 0.5D;
/* 54 */     this.motionZ *= 0.8D;
/*    */     
/* 56 */     if (this.onGround) {
/* 57 */       this.motionX *= 0.7D;
/* 58 */       this.motionZ *= 0.7D;
/*    */     } 
/*    */     
/* 61 */     if (this.particleMaxAge-- <= 0) {
/* 62 */       setExpired();
/*    */     }
/*    */   }
/*    */   
/*    */   private String getCurrentTexture() {
/* 67 */     if (this.onGround) {
/* 68 */       return "fxundead1.png";
/*    */     }
/* 70 */     return "fxundead2.png";
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderParticle(BufferBuilder worldRendererIn, Entity entityIn, float partialTicks, float par3, float par4, float par5, float par6, float par7) {
/* 75 */     (FMLClientHandler.instance().getClient()).renderEngine.bindTexture(new ResourceLocation("mocreatures", MoCProxy.MISC_TEXTURE + 
/* 76 */           getCurrentTexture()));
/* 77 */     float sizeFactor = 0.1F * this.particleScale;
/* 78 */     float var13 = (float)(this.prevPosX + (this.posX - this.prevPosX) * partialTicks - interpPosX);
/* 79 */     float var14 = (float)(this.prevPosY + (this.posY - this.prevPosY) * partialTicks - interpPosY);
/* 80 */     float var15 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * partialTicks - interpPosZ);
/* 81 */     float var16 = 1.0F;
/* 82 */     int i = getBrightnessForRender(partialTicks);
/* 83 */     int j = i >> 16 & 0xFFFF;
/* 84 */     int k = i & 0xFFFF;
/* 85 */     worldRendererIn.pos((var13 - par3 * sizeFactor - par6 * sizeFactor), (var14 - par4 * sizeFactor), (var15 - par5 * sizeFactor - par7 * sizeFactor))
/* 86 */       .tex(0.0D, 1.0D).color(this.particleRed * var16, this.particleGreen * var16, this.particleBlue * var16, 1.0F).lightmap(j, k).endVertex();
/* 87 */     worldRendererIn.pos((var13 - par3 * sizeFactor + par6 * sizeFactor), (var14 + par4 * sizeFactor), (var15 - par5 * sizeFactor + par7 * sizeFactor))
/* 88 */       .tex(1.0D, 1.0D).color(this.particleRed * var16, this.particleGreen * var16, this.particleBlue * var16, 1.0F).lightmap(j, k).endVertex();
/* 89 */     worldRendererIn.pos((var13 + par3 * sizeFactor + par6 * sizeFactor), (var14 + par4 * sizeFactor), (var15 + par5 * sizeFactor + par7 * sizeFactor))
/* 90 */       .tex(1.0D, 0.0D).color(this.particleRed * var16, this.particleGreen * var16, this.particleBlue * var16, 1.0F).lightmap(j, k).endVertex();
/* 91 */     worldRendererIn.pos((var13 + par3 * sizeFactor - par6 * sizeFactor), (var14 - par4 * sizeFactor), (var15 + par5 * sizeFactor - par7 * sizeFactor))
/* 92 */       .tex(0.0D, 0.0D).color(this.particleRed * var16, this.particleGreen * var16, this.particleBlue * var16, 1.0F).lightmap(j, k).endVertex();
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\MoCEntityFXUndead.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */