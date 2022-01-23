package drzhark.mocreatures.entity.ambient;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.entity.MoCEntityInsect;
import drzhark.mocreatures.init.MoCSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;




public class MoCEntityBee
  extends MoCEntityInsect
{
  private int soundCount;

  public MoCEntityBee(World world) {
    super(world);
    this.texture = "bee.png";
  }


  public void onLivingUpdate() {
    super.onLivingUpdate();

    if (!this.world.isRemote) {
      if (getIsFlying() && --this.soundCount == -1) {
        EntityPlayer ep = this.world.getClosestPlayerToEntity((Entity)this, 5.0D);
        if (ep != null) {
          MoCTools.playCustomSound((Entity)this, getMySound());
          this.soundCount = 20;
        }
      }

      if (getIsFlying() && this.rand.nextInt(500) == 0) {
        setIsFlying(false);
      }
    }
  }

  private SoundEvent getMySound() {
    if (getAttackTarget() != null) {
      return MoCSoundEvents.ENTITY_BEE_UPSET;
    }
    return MoCSoundEvents.ENTITY_BEE_AMBIENT;
  }


  public int getTalkInterval() {
    return 2000;
  }


  protected float getSoundVolume() {
    return 0.1F;
  }


  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    if (super.attackEntityFrom(damagesource, i)) {
      Entity entity = damagesource.getTrueSource();
      if (entity instanceof EntityLivingBase) {
        EntityLivingBase entityliving = (EntityLivingBase)entity;
        if (entity != this && this.world.getDifficulty().getDifficultyId()  > 0) {
          setAttackTarget(entityliving);
        }
        return true;
      }
      return false;
    }
    return false;
  }



  public boolean isMyFavoriteFood(ItemStack stack) {
    return (!stack.isEmpty() && (stack
      .getItem() == Item.getItemFromBlock((Block)Blocks.RED_FLOWER) || stack.getItem() ==
      Item.getItemFromBlock((Block)Blocks.YELLOW_FLOWER)));
  }


  public float getAIMoveSpeed() {
    if (getIsFlying()) {
      return 0.15F;
    }
    return 0.12F;
  }


  public boolean isFlyer() {
    return true;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ambient\MoCEntityBee.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
