package drzhark.mocreatures.client;

import drzhark.mocreatures.MoCProxy;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MoCEntityFXVanish
  extends Particle
{
  private final double portalPosX;
  private final double portalPosY;
  private final double portalPosZ;
  private final boolean implode;

  public MoCEntityFXVanish(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, float red, float green, float blue, boolean flag) {
    super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);

    this.particleRed = red;
    this.particleGreen = green;
    this.particleBlue = blue;
    this.motionX = par8;
    this.motionY = par10 * 5.0D;
    this.motionZ = par12;
    this.portalPosX = this.posX = par2;
    this.portalPosY = this.posY = par4;
    this.portalPosZ = this.posZ = par6;
    this.implode = flag;
    this.particleMaxAge = (int)(Math.random() * 10.0D) + 70;
  }





  public int getFXLayer() {
    return 1;
  }





  public void onUpdate() {
    this.prevPosX = this.posX;
    this.prevPosY = this.posY;
    this.prevPosZ = this.posZ;

    int speeder = 0;
    float sizeExp = 2.0F;
    if (this.implode) {
      speeder = this.particleMaxAge / 2;
      sizeExp = 5.0F;
    }

    float var1 = (this.particleAge + speeder) / this.particleMaxAge;
    float var2 = var1;
    var1 = -var1 + var1 * var1 * sizeExp;
    var1 = 1.0F - var1;
    this.posX = this.portalPosX + this.motionX * var1;
    this.posY = this.portalPosY + this.motionY * var1 + (1.0F - var2);
    this.posZ = this.portalPosZ + this.motionZ * var1;

    if (this.particleAge++ >= this.particleMaxAge) {
      setExpired();
    }
  }


  public void renderParticle(BufferBuilder worldRendererIn, Entity entityIn, float partialTicks, float par3, float par4, float par5, float par6, float par7) {
    (FMLClientHandler.instance().getClient()).renderEngine.bindTexture(new ResourceLocation("mocreatures", MoCProxy.MISC_TEXTURE + "fxvanish.png"));
    float scale = 0.1F * this.particleScale;
    float xPos = (float)(this.prevPosX + (this.posX - this.prevPosX) * partialTicks - interpPosX);
    float yPos = (float)(this.prevPosY + (this.posY - this.prevPosY) * partialTicks - interpPosY);
    float zPos = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * partialTicks - interpPosZ);
    float colorIntensity = 1.0F;
    int i = getBrightnessForRender(partialTicks);
    int j = i >> 16 & 0xFFFF;
    int k = i & 0xFFFF;
    worldRendererIn.pos((xPos - par3 * scale - par6 * scale), (yPos - par4 * scale), (zPos - par5 * scale - par7 * scale)).tex(0.0D, 1.0D).color(this.particleRed * colorIntensity, this.particleGreen * colorIntensity, this.particleBlue * colorIntensity, 1.0F).lightmap(j, k).endVertex();
    worldRendererIn.pos((xPos - par3 * scale + par6 * scale), (yPos + par4 * scale), (zPos - par5 * scale + par7 * scale)).tex(1.0D, 1.0D).color(this.particleRed * colorIntensity, this.particleGreen * colorIntensity, this.particleBlue * colorIntensity, 1.0F).lightmap(j, k).endVertex();
    worldRendererIn.pos((xPos + par3 * scale + par6 * scale), (yPos + par4 * scale), (zPos + par5 * scale + par7 * scale)).tex(1.0D, 0.0D).color(this.particleRed * colorIntensity, this.particleGreen * colorIntensity, this.particleBlue * colorIntensity, 1.0F).lightmap(j, k).endVertex();
    worldRendererIn.pos((xPos + par3 * scale - par6 * scale), (yPos - par4 * scale), (zPos + par5 * scale - par7 * scale)).tex(0.0D, 0.0D).color(this.particleRed * colorIntensity, this.particleGreen * colorIntensity, this.particleBlue * colorIntensity, 1.0F).lightmap(j, k).endVertex();
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\MoCEntityFXVanish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
