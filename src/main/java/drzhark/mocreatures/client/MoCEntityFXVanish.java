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
/*    */ public class MoCEntityFXVanish
/*    */   extends Particle
/*    */ {
/*    */   private final double portalPosX;
/*    */   private final double portalPosY;
/*    */   private final double portalPosZ;
/*    */   private final boolean implode;
/*    */   
/*    */   public MoCEntityFXVanish(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, float red, float green, float blue, boolean flag) {
/* 23 */     super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
/*    */     
/* 25 */     this.particleRed = red;
/* 26 */     this.particleGreen = green;
/* 27 */     this.particleBlue = blue;
/* 28 */     this.motionX = par8;
/* 29 */     this.motionY = par10 * 5.0D;
/* 30 */     this.motionZ = par12;
/* 31 */     this.portalPosX = this.posX = par2;
/* 32 */     this.portalPosY = this.posY = par4;
/* 33 */     this.portalPosZ = this.posZ = par6;
/* 34 */     this.implode = flag;
/* 35 */     this.particleMaxAge = (int)(Math.random() * 10.0D) + 70;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFXLayer() {
/* 43 */     return 1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 51 */     this.prevPosX = this.posX;
/* 52 */     this.prevPosY = this.posY;
/* 53 */     this.prevPosZ = this.posZ;
/*    */     
/* 55 */     int speeder = 0;
/* 56 */     float sizeExp = 2.0F;
/* 57 */     if (this.implode) {
/* 58 */       speeder = this.particleMaxAge / 2;
/* 59 */       sizeExp = 5.0F;
/*    */     } 
/*    */     
/* 62 */     float var1 = (this.particleAge + speeder) / this.particleMaxAge;
/* 63 */     float var2 = var1;
/* 64 */     var1 = -var1 + var1 * var1 * sizeExp;
/* 65 */     var1 = 1.0F - var1;
/* 66 */     this.posX = this.portalPosX + this.motionX * var1;
/* 67 */     this.posY = this.portalPosY + this.motionY * var1 + (1.0F - var2);
/* 68 */     this.posZ = this.portalPosZ + this.motionZ * var1;
/*    */     
/* 70 */     if (this.particleAge++ >= this.particleMaxAge) {
/* 71 */       setExpired();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderParticle(BufferBuilder worldRendererIn, Entity entityIn, float partialTicks, float par3, float par4, float par5, float par6, float par7) {
/* 77 */     (FMLClientHandler.instance().getClient()).renderEngine.bindTexture(new ResourceLocation("mocreatures", MoCProxy.MISC_TEXTURE + "fxvanish.png"));
/* 78 */     float scale = 0.1F * this.particleScale;
/* 79 */     float xPos = (float)(this.prevPosX + (this.posX - this.prevPosX) * partialTicks - interpPosX);
/* 80 */     float yPos = (float)(this.prevPosY + (this.posY - this.prevPosY) * partialTicks - interpPosY);
/* 81 */     float zPos = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * partialTicks - interpPosZ);
/* 82 */     float colorIntensity = 1.0F;
/* 83 */     int i = getBrightnessForRender(partialTicks);
/* 84 */     int j = i >> 16 & 0xFFFF;
/* 85 */     int k = i & 0xFFFF;
/* 86 */     worldRendererIn.pos((xPos - par3 * scale - par6 * scale), (yPos - par4 * scale), (zPos - par5 * scale - par7 * scale)).tex(0.0D, 1.0D).color(this.particleRed * colorIntensity, this.particleGreen * colorIntensity, this.particleBlue * colorIntensity, 1.0F).lightmap(j, k).endVertex();
/* 87 */     worldRendererIn.pos((xPos - par3 * scale + par6 * scale), (yPos + par4 * scale), (zPos - par5 * scale + par7 * scale)).tex(1.0D, 1.0D).color(this.particleRed * colorIntensity, this.particleGreen * colorIntensity, this.particleBlue * colorIntensity, 1.0F).lightmap(j, k).endVertex();
/* 88 */     worldRendererIn.pos((xPos + par3 * scale + par6 * scale), (yPos + par4 * scale), (zPos + par5 * scale + par7 * scale)).tex(1.0D, 0.0D).color(this.particleRed * colorIntensity, this.particleGreen * colorIntensity, this.particleBlue * colorIntensity, 1.0F).lightmap(j, k).endVertex();
/* 89 */     worldRendererIn.pos((xPos + par3 * scale - par6 * scale), (yPos - par4 * scale), (zPos + par5 * scale - par7 * scale)).tex(0.0D, 0.0D).color(this.particleRed * colorIntensity, this.particleGreen * colorIntensity, this.particleBlue * colorIntensity, 1.0F).lightmap(j, k).endVertex();
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\MoCEntityFXVanish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */