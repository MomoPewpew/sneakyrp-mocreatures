package drzhark.mocreatures.entity.passive;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.IMoCTameable;
import drzhark.mocreatures.init.MoCItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MoCEntityPandaBear
  extends MoCEntityBear {
  public MoCEntityPandaBear(World world) {
    super(world);
  }


  public void selectType() {
    if (getType() == 0) {
      setType(1);
    }
    super.selectType();
  }


  public ResourceLocation getTexture() {
    return MoCreatures.proxy.getTexture("bearpanda.png");
  }


  public float getBearSize() {
    return 0.8F;
  }


  public int getMaxEdad() {
    return 80;
  }


  public float calculateMaxHealth() {
    return 20.0F;
  }


  public boolean isReadyToHunt() {
    return false;
  }


  public int getAttackStrength() {
    return 1;
  }


  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    if (super.attackEntityFrom(damagesource, i)) {
      return true;
    }
    return false;
  }



  public boolean shouldAttackPlayers() {
    return false;
  }


  public boolean isMyFavoriteFood(ItemStack stack) {
    return (getType() == 3 && !stack.isEmpty() && stack.getItem() == Items.REEDS);
  }


  public boolean isMyHealFood(ItemStack stack) {
    return (getType() == 3 && !stack.isEmpty() && stack.getItem() == Items.REEDS);
  }


  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    Boolean tameResult = processTameInteract(player, hand);
    if (tameResult != null) {
      return tameResult.booleanValue();
    }

    ItemStack stack = player.getHeldItem(hand);
    if (!stack.isEmpty() && (stack.getItem() == MoCItems.sugarlump || stack.getItem() == Items.REEDS)) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }

      if (!this.world.isRemote) {
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


  public void onLivingUpdate() {
    super.onLivingUpdate();



    if (!this.world.isRemote && !getIsTamed() && this.rand.nextInt(300) == 0) {
      setBearState(2);
    }
  }


  public String getOffspringClazz(IMoCTameable mate) {
    return "PandaBear";
  }


  public int getOffspringTypeInt(IMoCTameable mate) {
    return 1;
  }


  public boolean compatibleMate(Entity mate) {
    return mate instanceof MoCEntityPandaBear;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityPandaBear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
