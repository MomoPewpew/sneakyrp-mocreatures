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

public class MoCEntityTiger
  extends MoCEntityBigCat {
  public MoCEntityTiger(World world) {
    super(world);
  }



  public void selectType() {
    if (getType() == 0) {
      if (this.rand.nextInt(20) == 0) {
        setType(2);
      } else {
        setType(1);
      }
    }
    super.selectType();
  }


  public ResourceLocation getTexture() {
    switch (getType()) {
      case 1:
        return MoCreatures.proxy.getTexture("bctiger.png");
      case 2:
        return MoCreatures.proxy.getTexture("bcwhitetiger.png");
      case 3:
        return MoCreatures.proxy.getTexture("bcwhitetiger.png");
    }


    return MoCreatures.proxy.getTexture("bctiger.png");
  }



  public boolean isFlyer() {
    return (getType() == 3);
  }


  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    Boolean tameResult = processTameInteract(player, hand);
    if (tameResult != null) {
      return tameResult.booleanValue();
    }

    ItemStack stack = player.getHeldItem(hand);
    if (!stack.isEmpty() && getIsTamed() && getType() == 2 && stack.getItem() == MoCItems.essencelight) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
      } else {
        player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
      }
      setType(3);
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
    if (mate instanceof MoCEntityLion && ((MoCEntityLion)mate).getType() == 2) {
      return "Liger";
    }
    if (mate instanceof MoCEntityPanther && ((MoCEntityPanther)mate).getType() == 1) {
      return "Panthger";
    }
    if (mate instanceof MoCEntityLeopard && ((MoCEntityPanther)mate).getType() == 1) {
      return "Leoger";
    }
    return "Tiger";
  }


  public int getOffspringTypeInt(IMoCTameable mate) {
    if (mate instanceof MoCEntityLion && ((MoCEntityLion)mate).getType() == 2) {
      return 1;
    }
    if (mate instanceof MoCEntityLeopard && ((MoCEntityLeopard)mate).getType() == 1) {
      return 1;
    }
    if (mate instanceof MoCEntityPanther && ((MoCEntityPanther)mate).getType() == 1) {
      return 1;
    }
    return getType();
  }


  public boolean compatibleMate(Entity mate) {
    return ((mate instanceof MoCEntityTiger && ((MoCEntityTiger)mate).getType() < 3) || (mate instanceof MoCEntityLion && ((MoCEntityLion)mate)
      .getType() == 2) || (mate instanceof MoCEntityLeopard && ((MoCEntityLeopard)mate)
      .getType() == 1) || (mate instanceof MoCEntityPanther && ((MoCEntityPanther)mate)
      .getType() == 1));
  }


  public boolean readytoBreed() {
    return (getType() < 3 && super.readytoBreed());
  }


  public float calculateMaxHealth() {
    if (getType() == 2) {
      return 40.0F;
    }
    return 35.0F;
  }


  public double calculateAttackDmg() {
    if (getType() == 2) {
      return 8.0D;
    }
    return 7.0D;
  }


  public double getAttackRange() {
    return 8.0D;
  }


  public int getMaxEdad() {
    if (getType() > 2) {
      return 130;
    }
    return 120;
  }


  public boolean canAttackTarget(EntityLivingBase entity) {
    if (!getIsAdult() && getEdad() < getMaxEdad() * 0.8D) {
      return false;
    }
    if (entity instanceof MoCEntityTiger) {
      return false;
    }
    return (entity.height < 2.0F && entity.width < 2.0F);
  }


  public float getMoveSpeed() {
    return 1.5F;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityTiger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
