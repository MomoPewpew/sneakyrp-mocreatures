package drzhark.mocreatures.client.renderer.entity;
import drzhark.mocreatures.client.model.MoCModelNewHorse;
import drzhark.mocreatures.entity.passive.MoCEntityHorse;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class MoCRenderNewHorse extends MoCRenderMoC<MoCEntityHorse> {
  public MoCRenderNewHorse(MoCModelNewHorse modelbase) {
    super(modelbase, 0.5F);
  }


  protected ResourceLocation getEntityTexture(MoCEntityHorse entityhorse) {
    return entityhorse.getTexture();
  }

  protected void adjustHeight(MoCEntityHorse entityhorse, float FHeight) {
    GL11.glTranslatef(0.0F, FHeight, 0.0F);
  }


  protected void preRenderCallback(MoCEntityHorse entityhorse, float f) {
    if (!entityhorse.getIsAdult() || entityhorse.getType() > 64) {
      stretch(entityhorse);
    }
    if (entityhorse.getIsGhost()) {
      adjustHeight(entityhorse, -0.3F + entityhorse.tFloat() / 5.0F);
    }
    super.preRenderCallback(entityhorse, f);
  }

  protected void stretch(MoCEntityHorse entityhorse) {
    float sizeFactor = entityhorse.getEdad() * 0.01F;
    if (entityhorse.getIsAdult()) {
      sizeFactor = 1.0F;
    }
    if (entityhorse.getType() > 64)
    {
      sizeFactor *= 0.9F;
    }
    GL11.glScalef(sizeFactor, sizeFactor, sizeFactor);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderNewHorse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
