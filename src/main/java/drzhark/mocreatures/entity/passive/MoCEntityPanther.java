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

public class MoCEntityPanther
  extends MoCEntityBigCat {
  public MoCEntityPanther(World world) {
    super(world);
  }


  public ResourceLocation getTexture() {
    switch (getType()) {
      case 1:
        return MoCreatures.proxy.getTexture("bcpuma.png");
      case 2:
        return MoCreatures.proxy.getTexture("bcpuma.png");
    }






    return MoCreatures.proxy.getTexture("bcpuma.png");
  }




  public void selectType() {
    if (getType() == 0) {
      setType(1);
    }
    super.selectType();
  }


  public boolean isFlyer() {
    return (getType() == 2);
  }


  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    Boolean tameResult = processTameInteract(player, hand);
    if (tameResult != null) {
      return tameResult.booleanValue();
    }

    ItemStack stack = player.getHeldItem(hand);
    if (!stack.isEmpty() && getIsTamed() && getType() == 1 && stack.getItem() == MoCItems.essencedarkness) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
      } else {
        player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
      }
      setType(2);
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
    if (mate instanceof MoCEntityLeopard && ((MoCEntityLeopard)mate).getType() == 1) {
      return "Panthard";
    }
    if (mate instanceof MoCEntityTiger && ((MoCEntityTiger)mate).getType() == 1) {
      return "Panthger";
    }
    if (mate instanceof MoCEntityLion && ((MoCEntityLion)mate).getType() == 2) {
      return "Lither";
    }

    return "Panther";
  }



  public int getOffspringTypeInt(IMoCTameable mate) {
    if (mate instanceof MoCEntityLeopard && ((MoCEntityLeopard)mate).getType() == 1) {
      return 1;
    }
    if (mate instanceof MoCEntityTiger && ((MoCEntityTiger)mate).getType() == 1) {
      return 1;
    }
    if (mate instanceof MoCEntityLion && ((MoCEntityLion)mate).getType() == 2) {
      return 1;
    }
    return 1;
  }


  public boolean compatibleMate(Entity mate) {
    return ((mate instanceof MoCEntityLeopard && ((MoCEntityLeopard)mate).getType() == 1) || (mate instanceof MoCEntityPanther && ((MoCEntityPanther)mate)
      .getType() == 1) || (mate instanceof MoCEntityTiger && ((MoCEntityTiger)mate)
      .getType() == 1) || (mate instanceof MoCEntityLion && ((MoCEntityLion)mate)
      .getType() == 2));
  }


  public float calculateMaxHealth() {
    return 25.0F;
  }


  public double calculateAttackDmg() {
    return 6.0D;
  }


  public double getAttackRange() {
    return 8.0D;
  }


  public int getMaxEdad() {
    if (getType() >= 4) return 110;
    return 100;
  }


  public boolean canAttackTarget(EntityLivingBase entity) {
    if (!getIsAdult() && getEdad() < getMaxEdad() * 0.8D) {
      return false;
    }
    if (entity instanceof MoCEntityPanther) {
      return false;
    }
    return (entity.height < 1.5F && entity.width < 1.5F);
  }


  public float getMoveSpeed() {
    return 1.6F;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityPanther.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
