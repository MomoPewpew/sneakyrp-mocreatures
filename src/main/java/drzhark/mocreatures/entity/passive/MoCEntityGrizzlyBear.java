package drzhark.mocreatures.entity.passive;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.IMoCTameable;
import drzhark.mocreatures.init.MoCItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MoCEntityGrizzlyBear
  extends MoCEntityBear {
  public MoCEntityGrizzlyBear(World world) {
    super(world);
  }


  public void selectType() {
    if (getType() == 0) {
      setType(1);
    }
    super.selectType();
  }


  public ResourceLocation getTexture() {
    return MoCreatures.proxy.getTexture("bearbrowm.png");
  }


  public float getBearSize() {
    return 1.2F;
  }


  public int getMaxEdad() {
    return 120;
  }


  public float calculateMaxHealth() {
    return 40.0F;
  }

  public double getAttackRange() {
    int factor = 1;
    if (this.world.getDifficulty().getDifficultyId()  > 1) {
      factor = 2;
    }
    return 6.0D * factor;
  }


  public int getAttackStrength() {
    int factor = this.world.getDifficulty().getDifficultyId() ;
    return 3 * factor;
  }


  public boolean shouldAttackPlayers() {
    return (getBrightness() < 0.4F && super.shouldAttackPlayers());
  }


  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    Boolean tameResult = processTameInteract(player, hand);
    if (tameResult != null) {
      return tameResult.booleanValue();
    }

    ItemStack stack = player.getHeldItem(hand);
    if (!stack.isEmpty() && getEdad() < 80 && MoCTools.isItemEdibleforCarnivores(stack.getItem())) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }

      if (!getIsTamed() && !this.world.isRemote) {
        MoCTools.tameWithName(player, (IMoCTameable)this);
      }

      setHealth(getMaxHealth());
      eatingAnimal();
      if (!this.world.isRemote && !getIsAdult() && getEdad() < 100) {
        setEdad(getEdad() + 1);
      }
      return true;
    }
    if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.whip) {
      if (getBearState() == 0) {
        setBearState(2);
      } else {
        setBearState(0);
      }
      return true;
    }
    if (getIsRideable() && getIsAdult() && (!getIsChested() || !player.isSneaking()) && !isBeingRidden()) {
      if (!this.world.isRemote && player.startRiding((Entity)this)) {
        player.rotationYaw = this.rotationYaw;
        player.rotationPitch = this.rotationPitch;
        setBearState(0);
      }
      return true;
    }

    return super.processInteract(player, hand);
  }


  public String getOffspringClazz(IMoCTameable mate) {
    return "GrizzlyBear";
  }


  public int getOffspringTypeInt(IMoCTameable mate) {
    return 1;
  }


  public boolean compatibleMate(Entity mate) {
    return mate instanceof MoCEntityGrizzlyBear;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityGrizzlyBear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
