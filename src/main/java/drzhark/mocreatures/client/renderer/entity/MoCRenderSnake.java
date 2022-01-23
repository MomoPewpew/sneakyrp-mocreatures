package drzhark.mocreatures.client.renderer.entity;
import drzhark.mocreatures.entity.passive.MoCEntitySnake;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import net.minecraft.block.material.Material;

@SideOnly(Side.CLIENT)
public class MoCRenderSnake extends MoCRenderMoC<MoCEntitySnake> {
  public MoCRenderSnake(ModelBase modelbase, float f) {
    super(modelbase, 0.0F);
  }


  protected ResourceLocation getEntityTexture(MoCEntitySnake par1Entity) {
    return par1Entity.getTexture();
  }

  protected void adjustHeight(MoCEntitySnake entitysnake, float FHeight) {
    GL11.glTranslatef(0.0F, FHeight, 0.0F);
  }


  protected void preRenderCallback(MoCEntitySnake entitysnake, float f) {
    stretch(entitysnake);






    if (entitysnake.pickedUp()) {

      float xOff = entitysnake.getSizeF() - 1.0F;
      if (xOff > 0.0F) {
        xOff = 0.0F;
      }
      if (entitysnake.world.isRemote) {
        GL11.glTranslatef(xOff, 0.0F, 0.0F);
      } else {
        GL11.glTranslatef(xOff, 0.0F, 0.0F);
      }
    }








    if (entitysnake.isInsideOfMaterial(Material.WATER)) {
      adjustHeight(entitysnake, -0.25F);
    }

    super.preRenderCallback(entitysnake, f);
  }

  protected void stretch(MoCEntitySnake entitysnake) {
    float f = entitysnake.getSizeF();
    GL11.glScalef(f, f, f);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderSnake.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
