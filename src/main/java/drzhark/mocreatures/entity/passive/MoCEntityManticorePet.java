package drzhark.mocreatures.entity.passive;

import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.IMoCTameable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MoCEntityManticorePet
  extends MoCEntityBigCat {
  public MoCEntityManticorePet(World world) {
    super(world);
    this.chestName = "ManticoreChest";
  }



  public void selectType() {
    if (getType() == 0) {
      setType(this.rand.nextInt(4) + 1);
    }
    super.selectType();
  }


  public ResourceLocation getTexture() {
    switch (getType()) {
      case 1:
        return MoCreatures.proxy.getTexture("bcmanticore.png");
      case 2:
        return MoCreatures.proxy.getTexture("bcmanticoredark.png");
      case 3:
        return MoCreatures.proxy.getTexture("bcmanticoreblue.png");
      case 4:
        return MoCreatures.proxy.getTexture("bcmanticoregreen.png");
    }
    return MoCreatures.proxy.getTexture("bcmanticore.png");
  }



  public boolean hasMane() {
    return true;
  }


  public boolean isFlyer() {
    return true;
  }


  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    Boolean tameResult = processTameInteract(player, hand);
    if (tameResult != null) {
      return tameResult.booleanValue();
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
    return "";
  }


  public int getOffspringTypeInt(IMoCTameable mate) {
    return 0;
  }


  public boolean compatibleMate(Entity mate) {
    return false;
  }


  public boolean readytoBreed() {
    return false;
  }


  public float calculateMaxHealth() {
    return 40.0F;
  }


  public double calculateAttackDmg() {
    return 7.0D;
  }


  public double getAttackRange() {
    return 8.0D;
  }


  public int getMaxEdad() {
    return 130;
  }


  public boolean getHasStinger() {
    return true;
  }


  public boolean hasSaberTeeth() {
    return true;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityManticorePet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
