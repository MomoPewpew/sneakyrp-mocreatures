package drzhark.mocreatures.entity.aquatic;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.network.MoCMessageHandler;
import drzhark.mocreatures.network.message.MoCMessageAnimation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MoCEntityStingRay extends MoCEntityRay {
  private int poisoncounter;
  private int tailCounter;

  public MoCEntityStingRay(World world) {
    super(world);
    setSize(1.2F, 0.5F);
    setEdad(50 + this.rand.nextInt(40));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
  }


  public ResourceLocation getTexture() {
    return MoCreatures.proxy.getTexture("stingray.png");
  }


  public boolean isPoisoning() {
    return (this.tailCounter != 0);
  }


  public void onLivingUpdate() {
    super.onLivingUpdate();
    if (!this.world.isRemote) {
      if (!getIsTamed() && ++this.poisoncounter > 250 && this.world.getDifficulty().getDifficultyId()  > 0 && this.rand.nextInt(30) == 0 &&
        MoCTools.findNearPlayerAndPoison((Entity)this, true)) {
        MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 1), new NetworkRegistry.TargetPoint(this.world.provider
              .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
        this.poisoncounter = 0;

      }

    }
    else if (this.tailCounter > 0 && ++this.tailCounter > 50) {
      this.tailCounter = 0;
    }
  }



  public void performAnimation(int animationType) {
    if (animationType == 1)
    {
      this.tailCounter = 1;
    }
  }


  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    if (super.attackEntityFrom(damagesource, i)) {
      if (this.world.getDifficulty().getDifficultyId()  == 0) {
        return true;
      }
      Entity entity = damagesource.getTrueSource();
      if (entity instanceof EntityLivingBase) {
        if (entity != this) {
          setAttackTarget((EntityLivingBase)entity);
        }
        return true;
      }
      return false;
    }
    return false;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\aquatic\MoCEntityStingRay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
