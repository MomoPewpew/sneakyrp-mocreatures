package drzhark.mocreatures.client.renderer.entity;
import drzhark.mocreatures.client.model.MoCModelTurtle;
import drzhark.mocreatures.entity.passive.MoCEntityTurtle;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class MoCRenderTurtle extends MoCRenderMoC<MoCEntityTurtle> {
  public MoCRenderTurtle(MoCModelTurtle modelbase, float f) {
    super((ModelBase)modelbase, f);
    this.turtly = modelbase;
  }
  public MoCModelTurtle turtly;

  protected void preRenderCallback(MoCEntityTurtle entityturtle, float f) {
    this.turtly.upsidedown = entityturtle.getIsUpsideDown();
    this.turtly.swingProgress = entityturtle.swingProgress;
    this.turtly.isHiding = entityturtle.getIsHiding();

    if (!entityturtle.world.isRemote && entityturtle.getRidingEntity() != null)
    {
      GL11.glTranslatef(0.0F, 1.3F, 0.0F);
    }

    if (entityturtle.getIsHiding()) {
      adjustHeight(entityturtle, 0.15F * entityturtle.getEdad() * 0.01F);
    } else if (!entityturtle.getIsHiding() && !entityturtle.getIsUpsideDown() && !entityturtle.isInsideOfMaterial(Material.WATER)) {
      adjustHeight(entityturtle, 0.05F * entityturtle.getEdad() * 0.01F);
    }
    if (entityturtle.getIsUpsideDown()) {
      rotateAnimal(entityturtle);
    }

    stretch(entityturtle);
  }





  protected void rotateAnimal(MoCEntityTurtle entityturtle) {
    float f = entityturtle.swingProgress * 10.0F * entityturtle.getFlipDirection();
    float f2 = entityturtle.swingProgress / 30.0F * entityturtle.getFlipDirection();
    GL11.glRotatef(180.0F + f, 0.0F, 0.0F, -1.0F);
    GL11.glTranslatef(0.0F - f2, 0.5F * entityturtle.getEdad() * 0.01F, 0.0F);
  }

  protected void adjustHeight(MoCEntityTurtle entityturtle, float height) {
    GL11.glTranslatef(0.0F, height, 0.0F);
  }

  protected void stretch(MoCEntityTurtle entityturtle) {
    float f = entityturtle.getEdad() * 0.01F;
    GL11.glScalef(f, f, f);
  }


  protected ResourceLocation getEntityTexture(MoCEntityTurtle entityturtle) {
    return entityturtle.getTexture();
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderTurtle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
