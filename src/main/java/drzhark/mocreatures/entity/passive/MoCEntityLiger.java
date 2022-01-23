package drzhark.mocreatures.entity.passive;

import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.IMoCTameable;
import drzhark.mocreatures.init.MoCItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MoCEntityLiger
  extends MoCEntityBigCat {
  public MoCEntityLiger(World world) {
    super(world);
  }


  public void selectType() {
    if (getType() == 0) {
      setType(1);
    }
    super.selectType();
  }


  public ResourceLocation getTexture() {
    return MoCreatures.proxy.getTexture("bcliger.png");
  }


  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    Boolean tameResult = processTameInteract(player, hand);
    if (tameResult != null) {
      return tameResult.booleanValue();
    }

    ItemStack stack = player.getHeldItem(hand);
    if (!stack.isEmpty() && getIsTamed() && getType() == 1 && stack.getItem() == MoCItems.essencelight) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
      } else {
        player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
      }
      setType(getType() + 1);
      return true;
    }

    if (getIsRideable() && getIsAdult() && (!getIsChested() || !player.isSneaking()) && !isBeingRidden()) {
      if (!this.world.isRemote && player.startRiding((Entity)this)) {
        player.rotationYaw = this.rotationYaw;
        player.rotationPitch = this.rotationPitch;
        setSitting(false);
      }

      return true;
    }

    return super.processInteract(player, hand);
  }

  public String getOffspringClazz(IMoCTameable mate) {
    return "Liger";
  }


  public int getOffspringTypeInt(IMoCTameable mate) {
    return 1;
  }


  public boolean compatibleMate(Entity mate) {
    return false;
  }


  public float calculateMaxHealth() {
    return 35.0F;
  }


  public double calculateAttackDmg() {
    return 8.0D;
  }


  public double getAttackRange() {
    return 10.0D;
  }


  public int getMaxEdad() {
    return 135;
  }


  public boolean canAttackTarget(EntityLivingBase entity) {
    if (!getIsAdult() && getEdad() < getMaxEdad() * 0.8D) {
      return false;
    }
    if (entity instanceof MoCEntityLiger) {
      return false;
    }
    return (entity.height < 2.0F && entity.width < 2.0F);
  }


  public boolean isFlyer() {
    return (getType() == 2);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityLiger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
