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

public class MoCEntityLion
  extends MoCEntityBigCat {
  public MoCEntityLion(World world) {
    super(world);
  }



  public void selectType() {
    if (getType() == 0) {
      if (this.rand.nextInt(20) == 0) {

        setType(this.rand.nextInt(2) + 6);
      } else {

        setType(this.rand.nextInt(2) + 1);
      }
    }
    super.selectType();
  }


  public ResourceLocation getTexture() {
    switch (getType()) {
      case 1:
        return MoCreatures.proxy.getTexture("bcfemalelion.png");
      case 2:
        return MoCreatures.proxy.getTexture("bcmalelion.png");
      case 3:
        return MoCreatures.proxy.getTexture("bcmalelion.png");




      case 6:
        return MoCreatures.proxy.getTexture("bcwhitelion.png");
      case 7:
        return MoCreatures.proxy.getTexture("bcwhitelion.png");
      case 8:
        return MoCreatures.proxy.getTexture("bcwhitelion.png");
    }


    return MoCreatures.proxy.getTexture("bcfemalelion.png");
  }



  public boolean hasMane() {
    return (getIsAdult() && ((getType() >= 2 && getType() < 4) || getType() == 7));
  }


  public boolean isFlyer() {
    return (getType() == 3 || getType() == 5 || getType() == 8);
  }


  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    Boolean tameResult = processTameInteract(player, hand);
    if (tameResult != null) {
      return tameResult.booleanValue();
    }

    ItemStack stack = player.getHeldItem(hand);
    if (!stack.isEmpty() && getIsTamed() && (getType() == 2 || getType() == 7) && stack
      .getItem() == MoCItems.essencelight) {
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
    if (mate instanceof MoCEntityTiger && ((MoCEntityTiger)mate).getType() < 3) {
      return "Liger";
    }
    if (getType() == 2 && mate instanceof MoCEntityLeopard && ((MoCEntityLeopard)mate).getType() == 1) {
      return "Liard";
    }
    if (getType() == 2 && mate instanceof MoCEntityPanther && ((MoCEntityPanther)mate).getType() == 1) {
      return "Lither";
    }
    return "Lion";
  }


  public int getOffspringTypeInt(IMoCTameable mate) {
    int x = 0;
    if (mate instanceof MoCEntityTiger && ((MoCEntityTiger)mate).getType() < 3) {
      return 1;
    }
    if (getType() == 2 && mate instanceof MoCEntityLeopard && ((MoCEntityLeopard)mate).getType() == 1) {
      return 1;
    }
    if (getType() == 2 && mate instanceof MoCEntityPanther && ((MoCEntityPanther)mate).getType() == 1) {
      return 1;
    }
    if (mate instanceof MoCEntityLion) {
      int lionMateType = ((MoCEntityLion)mate).getType();
      if (getType() == 1 && lionMateType == 2) {
        x = this.rand.nextInt(2) + 1;
      }
      if (getType() == 2 && lionMateType == 1) {
        x = this.rand.nextInt(2) + 1;
      }
      if (getType() == 6 && lionMateType == 7) {
        x = this.rand.nextInt(2) + 6;
      }
      if (getType() == 7 && lionMateType == 6) {
        x = this.rand.nextInt(2) + 6;
      }
      if (getType() == 7 && lionMateType == 1) {
        x = this.rand.nextInt(2) + 1;
      }
      if (getType() == 6 && lionMateType == 2) {
        x = this.rand.nextInt(2) + 1;
      }
      if (getType() == 1 && lionMateType == 7) {
        x = this.rand.nextInt(2) + 1;
      }
      if (getType() == 2 && lionMateType == 6) {
        x = this.rand.nextInt(2) + 1;
      }
    }
    return x;
  }


  public boolean compatibleMate(Entity mate) {
    if (getType() == 2 && mate instanceof MoCEntityTiger && ((MoCEntityTiger)mate).getType() < 3) {
      return true;
    }
    if (getType() == 2 && mate instanceof MoCEntityLeopard && ((MoCEntityLeopard)mate).getType() == 1) {
      return true;
    }
    if (getType() == 2 && mate instanceof MoCEntityPanther && ((MoCEntityPanther)mate).getType() == 1) {
      return true;
    }
    if (mate instanceof MoCEntityLion) {
      return (getOffspringTypeInt((IMoCTameable)mate) != 0);
    }
    return false;
  }


  public boolean readytoBreed() {
    return ((getType() < 3 || getType() == 6 || getType() == 7) && super.readytoBreed());
  }


  public float calculateMaxHealth() {
    if (getType() == 2 || getType() == 7) {
      return 35.0F;
    }
    if (getType() == 4) {
      return 40.0F;
    }
    return 30.0F;
  }


  public double calculateAttackDmg() {
    return 7.0D;
  }


  public double getAttackRange() {
    if (getType() == 1 || getType() == 6) {
      return 12.0D;
    }
    return 8.0D;
  }


  public int getMaxEdad() {
    if (getType() == 1 || getType() == 6) {
      return 110;
    }
    if (getType() == 9) {
      return 100;
    }
    return 120;
  }


  public boolean canAttackTarget(EntityLivingBase entity) {
    if (!getIsAdult() && getEdad() < getMaxEdad() * 0.8D) {
      return false;
    }
    if (entity instanceof MoCEntityLion) {
      return false;
    }
    return (entity.height < 2.0F && entity.width < 2.0F);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityLion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
