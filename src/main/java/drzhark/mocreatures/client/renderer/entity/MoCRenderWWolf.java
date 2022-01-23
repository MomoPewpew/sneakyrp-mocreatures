package drzhark.mocreatures.client.renderer.entity;

import drzhark.mocreatures.client.MoCClientProxy;
import drzhark.mocreatures.entity.monster.MoCEntityWWolf;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MoCRenderWWolf extends RenderLiving<EntityLiving> {
  public MoCRenderWWolf(ModelBase modelbase, float f) {
    super(MoCClientProxy.mc.getRenderManager(), modelbase, f);
  }


  protected ResourceLocation getEntityTexture(EntityLiving par1Entity) {
    return ((MoCEntityWWolf)par1Entity).getTexture();
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderWWolf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
