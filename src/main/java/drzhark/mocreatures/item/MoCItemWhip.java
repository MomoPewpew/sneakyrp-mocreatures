package drzhark.mocreatures.item;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.MoCEntityAnimal;
import drzhark.mocreatures.entity.passive.MoCEntityBear;
import drzhark.mocreatures.entity.passive.MoCEntityBigCat;
import drzhark.mocreatures.entity.passive.MoCEntityElephant;
import drzhark.mocreatures.entity.passive.MoCEntityHorse;
import drzhark.mocreatures.entity.passive.MoCEntityKitty;
import drzhark.mocreatures.entity.passive.MoCEntityOstrich;
import drzhark.mocreatures.entity.passive.MoCEntityPetScorpion;
import drzhark.mocreatures.entity.passive.MoCEntityWyvern;
import drzhark.mocreatures.init.MoCSoundEvents;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MoCItemWhip
  extends MoCItem {
  public MoCItemWhip(String name) {
    super(name);
    this.maxStackSize = 1;
    setMaxDamage(24);
  }


  public boolean isFull3D() {
    return true;
  }

  public ItemStack onItemRightClick2(ItemStack itemstack, World world, EntityPlayer entityplayer) {
    return itemstack;
  }



  public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
    ItemStack stack = player.getHeldItem(hand);
    int i1 = 0;
    Block block = worldIn.getBlockState(pos).getBlock();
    Block block1 = worldIn.getBlockState(pos.up()).getBlock();
    if (side != EnumFacing.DOWN && block1 == Blocks.AIR && block != Blocks.AIR && block != Blocks.STANDING_SIGN) {
      whipFX(worldIn, pos);
      worldIn.playSound(player, pos, MoCSoundEvents.ENTITY_GENERIC_WHIP, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
      stack.damageItem(1, (EntityLivingBase)player);
      List<Entity> list = worldIn.getEntitiesWithinAABBExcludingEntity((Entity)player, player.getEntityBoundingBox().expand(12.0D, 12.0D, 12.0D));
      for (int l1 = 0; l1 < list.size(); l1++) {
        Entity entity = list.get(l1);

        if (entity instanceof MoCEntityAnimal) {
          MoCEntityAnimal animal = (MoCEntityAnimal)entity;
          if (MoCreatures.proxy.enableOwnership && animal.getOwnerId() != null &&
            !player.getName().equals(animal.getOwnerId()) && !MoCTools.isThisPlayerAnOP(player)) {
            continue;
          }
        }

        if (entity instanceof MoCEntityBigCat) {
          MoCEntityBigCat entitybigcat = (MoCEntityBigCat)entity;
          if (entitybigcat.getIsTamed()) {
            entitybigcat.setSitting(!entitybigcat.getIsSitting());
            i1++;
          } else if (worldIn.getDifficulty().getDifficultyId()  > 0 && entitybigcat.getIsAdult()) {
            entitybigcat.setAttackTarget((EntityLivingBase)player);
          }
        }
        if (entity instanceof MoCEntityHorse) {
          MoCEntityHorse entityhorse = (MoCEntityHorse)entity;
          if (entityhorse.getIsTamed()) {
            if (entityhorse.getRidingEntity() == null) {
              entityhorse.setSitting(!entityhorse.getIsSitting());
            } else if (entityhorse.isNightmare()) {
              entityhorse.setNightmareInt(100);
            } else if (entityhorse.sprintCounter == 0) {
              entityhorse.sprintCounter = 1;
            }
          }
        }

        if (entity instanceof MoCEntityKitty) {
          MoCEntityKitty entitykitty = (MoCEntityKitty)entity;
          if (entitykitty.getKittyState() > 2 && entitykitty.whipeable()) {
            entitykitty.setSitting(!entitykitty.getIsSitting());
          }
        }

        if (entity instanceof MoCEntityWyvern) {
          MoCEntityWyvern entitywyvern = (MoCEntityWyvern)entity;
          if (entitywyvern.getIsTamed() && entitywyvern.getRidingEntity() == null && !entitywyvern.isOnAir()) {
            entitywyvern.setSitting(!entitywyvern.getIsSitting());
          }
        }

        if (entity instanceof MoCEntityPetScorpion) {
          MoCEntityPetScorpion petscorpion = (MoCEntityPetScorpion)entity;
          if (petscorpion.getIsTamed() && petscorpion.getRidingEntity() == null) {
            petscorpion.setSitting(!petscorpion.getIsSitting());
          }
        }

        if (entity instanceof MoCEntityOstrich) {
          MoCEntityOstrich entityostrich = (MoCEntityOstrich)entity;


          if (entityostrich.isBeingRidden() && entityostrich.sprintCounter == 0) {
            entityostrich.sprintCounter = 1;
          }


          if (entityostrich.getIsTamed() && entityostrich.getRidingEntity() == null) {
            entityostrich.setHiding(!entityostrich.getHiding());
          }
        }
        if (entity instanceof MoCEntityElephant) {
          MoCEntityElephant entityelephant = (MoCEntityElephant)entity;


          if (entityelephant.isBeingRidden() && entityelephant.sprintCounter == 0) {
            entityelephant.sprintCounter = 1;
          }
        }

        if (entity instanceof MoCEntityBear) {
          MoCEntityBear entitybear = (MoCEntityBear)entity;

          if (entitybear.getIsTamed()) {
            if (entitybear.getBearState() == 0) {
              entitybear.setBearState(2);
            } else {
              entitybear.setBearState(0);
            }
          }
        }
        continue;
      }
      if (i1 > 6);


      return EnumActionResult.SUCCESS;
    }
    return EnumActionResult.FAIL;
  }

  public void whipFX(World world, BlockPos pos) {
    double d = (pos.getX() + 0.5F);
    double d1 = (pos.getY() + 1.0F);
    double d2 = (pos.getZ() + 0.5F);
    double d3 = 0.2199999988079071D;
    double d4 = 0.27000001072883606D;
    world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D, new int[0]);
    world.spawnParticle(EnumParticleTypes.FLAME, d - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D, new int[0]);
    world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D, new int[0]);
    world.spawnParticle(EnumParticleTypes.FLAME, d + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D, new int[0]);
    world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D, new int[0]);
    world.spawnParticle(EnumParticleTypes.FLAME, d, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D, new int[0]);
    world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
    world.spawnParticle(EnumParticleTypes.FLAME, d, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
    world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
    world.spawnParticle(EnumParticleTypes.FLAME, d, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\MoCItemWhip.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
