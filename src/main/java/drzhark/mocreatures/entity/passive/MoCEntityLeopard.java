package drzhark.mocreatures.entity.passive;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.IMoCTameable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class MoCEntityLeopard
  extends MoCEntityBigCat
{
  public MoCEntityLeopard(World world) {
    super(world);
  }



  public void selectType() {
    if (getType() == 0) {
      checkSpawningBiome();
    }
    super.selectType();
  }


  public boolean checkSpawningBiome() {
    int i = MathHelper.floor(this.posX);
    int j = MathHelper.floor((getEntityBoundingBox()).minY);
    int k = MathHelper.floor(this.posZ);
    BlockPos pos = new BlockPos(i, j, k);

    Biome currentbiome = MoCTools.Biomekind(this.world, pos);
    try {
      if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.SNOWY)) {
        setType(2);
        return true;
      }
    } catch (Exception exception) {}

    setType(1);
    return true;
  }


  public ResourceLocation getTexture() {
    switch (getType()) {
      case 1:
        return MoCreatures.proxy.getTexture("bcleopard.png");
      case 2:
        return MoCreatures.proxy.getTexture("bcsnowleopard.png");
    }
    return MoCreatures.proxy.getTexture("bcleopard.png");
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
    if (mate instanceof MoCEntityPanther && ((MoCEntityPanther)mate).getType() == 1) {
      return "Pantard";
    }
    if (mate instanceof MoCEntityTiger && ((MoCEntityTiger)mate).getType() == 1) {
      return "Leoger";
    }
    if (mate instanceof MoCEntityLion && ((MoCEntityLion)mate).getType() == 2) {
      return "Liard";
    }
    return "Leopard";
  }


  public int getOffspringTypeInt(IMoCTameable mate) {
    if (mate instanceof MoCEntityPanther && ((MoCEntityPanther)mate).getType() == 1) {
      return 1;
    }
    if (mate instanceof MoCEntityTiger && ((MoCEntityTiger)mate).getType() == 1) {
      return 1;
    }
    if (mate instanceof MoCEntityLion && ((MoCEntityLion)mate).getType() == 2) {
      return 1;
    }
    return getType();
  }


  public boolean compatibleMate(Entity mate) {
    return ((mate instanceof MoCEntityLeopard && ((MoCEntityLeopard)mate).getType() == getType()) || (mate instanceof MoCEntityPanther && ((MoCEntityPanther)mate)
      .getType() == 1) || (mate instanceof MoCEntityTiger && ((MoCEntityTiger)mate)
      .getType() == 1) || (mate instanceof MoCEntityLion && ((MoCEntityLion)mate)
      .getType() == 2));
  }


  public float calculateMaxHealth() {
    return 25.0F;
  }


  public double calculateAttackDmg() {
    return 5.0D;
  }


  public double getAttackRange() {
    return 6.0D;
  }


  public int getMaxEdad() {
    return 95;
  }


  public boolean canAttackTarget(EntityLivingBase entity) {
    if (!getIsAdult() && getEdad() < getMaxEdad() * 0.8D) {
      return false;
    }
    if (entity instanceof MoCEntityLeopard) {
      return false;
    }
    return (entity.height < 1.3F && entity.width < 1.3F);
  }


  public float getMoveSpeed() {
    return 1.6F;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityLeopard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
