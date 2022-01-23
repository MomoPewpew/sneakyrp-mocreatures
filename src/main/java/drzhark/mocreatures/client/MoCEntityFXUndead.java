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
public class MoCEntityFXUndead
  extends Particle {
  public MoCEntityFXUndead(World par1World, double par2, double par4, double par6) {
    super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
    this.motionX *= 0.8D;
    this.motionY *= 0.8D;
    this.motionZ *= 0.8D;
    this.motionY = (this.rand.nextFloat() * 0.4F + 0.05F);

    setSize(0.01F, 0.01F);
    this.particleGravity = 0.06F;
    this.particleMaxAge = (int)(32.0D / (Math.random() * 0.8D + 0.2D));
    this.particleScale *= 0.8F;
  }





  public int getFXLayer() {
    if (this.onGround) {
      return 1;
    }
    return 2;
  }





  public void onUpdate() {
    this.prevPosX = this.posX;
    this.prevPosY = this.posY;
    this.prevPosZ = this.posZ;

    this.motionY -= 0.03D;
    move(this.motionX, this.motionY, this.motionZ);

    this.motionX *= 0.8D;
    this.motionY *= 0.5D;
    this.motionZ *= 0.8D;

    if (this.onGround) {
      this.motionX *= 0.7D;
      this.motionZ *= 0.7D;
    }

    if (this.particleMaxAge-- <= 0) {
      setExpired();
    }
  }

  private String getCurrentTexture() {
    if (this.onGround) {
      return "fxundead1.png";
    }
    return "fxundead2.png";
  }


  public void renderParticle(BufferBuilder worldRendererIn, Entity entityIn, float partialTicks, float par3, float par4, float par5, float par6, float par7) {
    (FMLClientHandler.instance().getClient()).renderEngine.bindTexture(new ResourceLocation("mocreatures", MoCProxy.MISC_TEXTURE +
          getCurrentTexture()));
    float sizeFactor = 0.1F * this.particleScale;
    float var13 = (float)(this.prevPosX + (this.posX - this.prevPosX) * partialTicks - interpPosX);
    float var14 = (float)(this.prevPosY + (this.posY - this.prevPosY) * partialTicks - interpPosY);
    float var15 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * partialTicks - interpPosZ);
    float var16 = 1.0F;
    int i = getBrightnessForRender(partialTicks);
    int j = i >> 16 & 0xFFFF;
    int k = i & 0xFFFF;
    worldRendererIn.pos((var13 - par3 * sizeFactor - par6 * sizeFactor), (var14 - par4 * sizeFactor), (var15 - par5 * sizeFactor - par7 * sizeFactor))
      .tex(0.0D, 1.0D).color(this.particleRed * var16, this.particleGreen * var16, this.particleBlue * var16, 1.0F).lightmap(j, k).endVertex();
    worldRendererIn.pos((var13 - par3 * sizeFactor + par6 * sizeFactor), (var14 + par4 * sizeFactor), (var15 - par5 * sizeFactor + par7 * sizeFactor))
      .tex(1.0D, 1.0D).color(this.particleRed * var16, this.particleGreen * var16, this.particleBlue * var16, 1.0F).lightmap(j, k).endVertex();
    worldRendererIn.pos((var13 + par3 * sizeFactor + par6 * sizeFactor), (var14 + par4 * sizeFactor), (var15 + par5 * sizeFactor + par7 * sizeFactor))
      .tex(1.0D, 0.0D).color(this.particleRed * var16, this.particleGreen * var16, this.particleBlue * var16, 1.0F).lightmap(j, k).endVertex();
    worldRendererIn.pos((var13 + par3 * sizeFactor - par6 * sizeFactor), (var14 - par4 * sizeFactor), (var15 + par5 * sizeFactor - par7 * sizeFactor))
      .tex(0.0D, 0.0D).color(this.particleRed * var16, this.particleGreen * var16, this.particleBlue * var16, 1.0F).lightmap(j, k).endVertex();
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\MoCEntityFXUndead.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
