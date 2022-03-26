package drzhark.mocreatures.entity;

import drzhark.mocreatures.MoCEntityData;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.ai.PathNavigateFlyer;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;



public abstract class MoCEntityAmbient
  extends EntityAnimal
  implements IMoCEntity
{
  protected static final DataParameter<Boolean> ADULT = EntityDataManager.createKey(MoCEntityAmbient.class, DataSerializers.BOOLEAN);
  protected static final DataParameter<Integer> TYPE = EntityDataManager.createKey(MoCEntityAmbient.class, DataSerializers.VARINT);
  protected static final DataParameter<Integer> AGE = EntityDataManager.createKey(MoCEntityAmbient.class, DataSerializers.VARINT);
  protected static final DataParameter<String> NAME_STR = EntityDataManager.createKey(MoCEntityAmbient.class, DataSerializers.STRING);

  protected String texture;
  protected boolean riderIsDisconnecting;
  protected PathNavigate navigatorFlyer;

  public MoCEntityAmbient(World world) {
    super(world);
    this.navigatorFlyer = (PathNavigate)new PathNavigateFlyer((EntityLiving)this, world);
  }


  public ResourceLocation getTexture() {
    return MoCreatures.proxy.getTexture(this.texture);
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
  }


  public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData par1EntityLivingData) {
    selectType();
    return super.onInitialSpawn(difficulty, par1EntityLivingData);
  }






  public void selectType() {
    setType(1);
  }


  public EntityAgeable createChild(EntityAgeable var1) {
    return null;
  }


  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(ADULT, Boolean.valueOf(false));
    this.dataManager.register(TYPE, Integer.valueOf(0));
    this.dataManager.register(AGE, Integer.valueOf(45));
    this.dataManager.register(NAME_STR, "");
  }


  public void setType(int i) {
    this.dataManager.set(TYPE, Integer.valueOf(i));
  }


  public int getType() {
    return ((Integer)this.dataManager.get(TYPE)).intValue();
  }


  public void setDisplayName(boolean flag) {}


  public boolean renderName() {
    return (MoCreatures.proxy.getDisplayPetName() &&
      getPetName() != null && !getPetName().equals("") && !isBeingRidden() && getRidingEntity() == null);
  }


  public boolean getIsAdult() {
    return ((Boolean)this.dataManager.get(ADULT)).booleanValue();
  }


  public void setAdult(boolean flag) {
    this.dataManager.set(ADULT, Boolean.valueOf(flag));
  }


  public String getPetName() {
    return (String)this.dataManager.get(NAME_STR);
  }


  public void setPetName(String name) {
    this.dataManager.set(NAME_STR, name);
  }


  public int getEdad() {
    return ((Integer)this.dataManager.get(AGE)).intValue();
  }


  public void setEdad(int i) {
    this.dataManager.set(AGE, Integer.valueOf(i));
  }


  public boolean getIsTamed() {
    return false;
  }


  public int getOwnerPetId() {
    return 0;
  }



  public void setOwnerPetId(int petId) {}


  public UUID getOwnerId() {
    return null;
  }


  protected boolean canDespawn() {
    if (MoCreatures.proxy.forceDespawns) {
      return !getIsTamed();
    }
    return false;
  }








  public boolean checkSpawningBiome() {
    return true;
  }

  protected EntityLivingBase getClosestEntityLiving(Entity entity, double d) {
    double d1 = -1.0D;
    EntityLivingBase entityliving = null;
    List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(d, d, d));
    for (int i = 0; i < list.size(); i++) {
      Entity entity1 = list.get(i);

      if (!entitiesToIgnore(entity1)) {


        double d2 = entity1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
        if ((d < 0.0D || d2 < d * d) && (d1 == -1.0D || d2 < d1) && ((EntityLivingBase)entity1).canEntityBeSeen(entity)) {
          d1 = d2;
          entityliving = (EntityLivingBase)entity1;
        }
      }
    }
    return entityliving;
  }


  public void onLivingUpdate() {
    if (!this.world.isRemote) {
      if (isMovementCeased()) {
        getNavigator().clearPath();
      }
      getNavigator().onUpdateNavigation();
    }
    super.onLivingUpdate();
  }

  public boolean swimmerEntity() {
    return false;
  }

  public boolean isSwimming() {
    return isInsideOfMaterial(Material.WATER);
  }







  public boolean isMyAphrodisiac(Item item1) {
    return false;
  }





  public void dropMyStuff() {}





  protected boolean isMyHealFood(ItemStack itemstack) {
    return false;
  }


  public boolean isInWater() {
    if (swimmerEntity()) {
      return false;
    }
    return super.isInWater();
  }


  public boolean canBreatheUnderwater() {
    return swimmerEntity();
  }

  public EntityItem getClosestItem(Entity entity, double d, ItemStack item, ItemStack item1) {
    double d1 = -1.0D;
    EntityItem entityitem = null;
    List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(d, d, d));
    for (int k = 0; k < list.size(); k++) {
      Entity entity1 = list.get(k);
      if (entity1 instanceof EntityItem) {


        EntityItem entityitem1 = (EntityItem)entity1;
        if (entityitem1.getItem() == item || entityitem1.getItem() == item1) {


          double d2 = entityitem1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
          if ((d < 0.0D || d2 < d * d) && (d1 == -1.0D || d2 < d1)) {
            d1 = d2;
            entityitem = entityitem1;
          }
        }
      }
    }  return entityitem;
  }

  public EntityItem getClosestEntityItem(Entity entity, double d) {
    double d1 = -1.0D;
    EntityItem entityitem = null;
    List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(d, d, d));
    for (int k = 0; k < list.size(); k++) {
      Entity entity1 = list.get(k);
      if (entity1 instanceof EntityItem) {


        EntityItem entityitem1 = (EntityItem)entity1;
        double d2 = entityitem1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
        if ((d < 0.0D || d2 < d * d) && (d1 == -1.0D || d2 < d1)) {
          d1 = d2;
          entityitem = entityitem1;
        }
      }
    }
    return entityitem;
  }

  public void faceLocation(int i, int j, int k, float f) {
    double var4 = i + 0.5D - this.posX;
    double var8 = k + 0.5D - this.posZ;
    double var6 = j + 0.5D - this.posY;
    double var14 = MathHelper.sqrt(var4 * var4 + var8 * var8);
    float var12 = (float)(Math.atan2(var8, var4) * 180.0D / Math.PI) - 90.0F;
    float var13 = (float)-(Math.atan2(var6, var14) * 180.0D / Math.PI);
    this.rotationPitch = -updateRotation(this.rotationPitch, var13, f);
    this.rotationYaw = updateRotation(this.rotationYaw, var12, f);
  }





  private float updateRotation(float par1, float par2, float par3) {
    float var4;
    for (var4 = par2 - par1; var4 < -180.0F; var4 += 360.0F);



    while (var4 >= 180.0F) {
      var4 -= 360.0F;
    }

    if (var4 > par3) {
      var4 = par3;
    }

    if (var4 < -par3) {
      var4 = -par3;
    }

    return par1 + var4;
  }

  public void getMyOwnPath(Entity entity, float f) {
    Path pathentity = getNavigator().getPathToEntityLiving(entity);
    if (pathentity != null) {
      getNavigator().setPath(pathentity, 1.0D);
    }
  }




  public void Riding() {
    if (isBeingRidden() && getRidingEntity() instanceof EntityPlayer) {
      EntityPlayer entityplayer = (EntityPlayer)getRidingEntity();
      List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(1.0D, 0.0D, 1.0D));
      if (list != null) {
        for (int i = 0; i < list.size(); i++) {
          Entity entity = list.get(i);
          if (!entity.isDead) {


            entity.onCollideWithPlayer(entityplayer);
            if (entity instanceof EntityMob) {


              float f = getDistance(entity);
              if (f >= 2.0F || this.rand.nextInt(10) == 0);
            }
          }
        }
      }
      if (entityplayer.isSneaking() &&
        !this.world.isRemote) {
        entityplayer.dismountRidingEntity();
      }
    }
  }


  protected void getPathOrWalkableBlock(Entity entity, float f) {
    Path pathentity = this.navigator.getPathToPos(entity.getPosition());
    if (pathentity == null && f > 8.0F) {
      int i = MathHelper.floor(entity.posX) - 2;
      int j = MathHelper.floor(entity.posZ) - 2;
      int k = MathHelper.floor((entity.getEntityBoundingBox()).minY);
      for (int l = 0; l <= 4; l++) {
        for (int i1 = 0; i1 <= 4; i1++) {
          BlockPos pos = new BlockPos(i, j, k);
          if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && this.world.getBlockState(pos.add(l, -1, i1)).isNormalCube() &&
            !this.world.getBlockState(pos.add(l, 0, i1)).isNormalCube() &&
            !this.world.getBlockState(pos.add(l, 1, i1)).isNormalCube()) {
            setLocationAndAngles(((i + l) + 0.5F), k, ((j + i1) + 0.5F), this.rotationYaw, this.rotationPitch);
            return;
          }
        }
      }
    } else {
      this.navigator.setPath(pathentity, 16.0D);
    }
  }

  // public boolean getCanSpawnHereAnimal() {
  //   int i = MathHelper.floor(this.posX);
  //   int j = MathHelper.floor((getEntityBoundingBox()).minY);
  //   int k = MathHelper.floor(this.posZ);
  //   BlockPos pos = new BlockPos(i, j, k);
  //   return (this.world.getBlockState(pos.down()).getBlock() == Blocks.GRASS && this.world.getLight(pos) > 8);
  // }

  // public boolean getCanSpawnHereCreature() {
  //   int i = MathHelper.floor(this.posX);
  //   int j = MathHelper.floor((getEntityBoundingBox()).minY);
  //   int k = MathHelper.floor(this.posZ);
  //   return (getBlockPathWeight(new BlockPos(i, j, k)) >= 0.0F);
  // }

  // public boolean getCanSpawnHereLiving() {
  //   return (this.world.checkNoEntityCollision(getEntityBoundingBox()) && this.world
  //     .getCollisionBoxes((Entity)this, getEntityBoundingBox()).size() == 0 &&
  //     !this.world.containsAnyLiquid(getEntityBoundingBox()));
  // }

  // public boolean getCanSpawnHereAquatic() {
  //   return this.world.checkNoEntityCollision(getEntityBoundingBox());
  // }


  // public boolean getCanSpawnHere() {
  //   if (((MoCEntityData)MoCreatures.entityMap.get(getClass())).getFrequency() <= 0) {
  //     return false;
  //   }

  //   return super.getCanSpawnHere();
  // }

  // public boolean getCanSpawnHereJungle() {
  //   if (this.world.checkNoEntityCollision(getEntityBoundingBox()) && this.world
  //     .getCollisionBoxes((Entity)this, getEntityBoundingBox()).isEmpty() &&
  //     !this.world.containsAnyLiquid(getEntityBoundingBox())) {
  //     int var1 = MathHelper.floor(this.posX);
  //     int var2 = MathHelper.floor((getEntityBoundingBox()).minY);
  //     int var3 = MathHelper.floor(this.posZ);

  //     if (var2 < 63) {
  //       return false;
  //     }

  //     BlockPos pos = new BlockPos(var1, var2, var3);
  //     IBlockState blockstate = this.world.getBlockState(pos.down());
  //     Block block = blockstate.getBlock();

  //     if (block == Blocks.GRASS || block == Blocks.LEAVES || block.isLeaves(blockstate, (IBlockAccess)this.world, pos.down())) {
  //       return true;
  //     }
  //   }

  //   return false;
  // }


  public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
    super.writeEntityToNBT(nbttagcompound);
    nbttagcompound = MoCTools.getEntityData((Entity)this);
    nbttagcompound.setBoolean("Adult", getIsAdult());
    nbttagcompound.setInteger("Edad", getEdad());
    nbttagcompound.setString("Name", getPetName());
    nbttagcompound.setInteger("TypeInt", getType());
  }


  public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
    super.readEntityFromNBT(nbttagcompound);
    nbttagcompound = MoCTools.getEntityData((Entity)this);
    setAdult(nbttagcompound.getBoolean("Adult"));
    setEdad(nbttagcompound.getInteger("Edad"));
    setPetName(nbttagcompound.getString("Name"));
    setType(nbttagcompound.getInteger("TypeInt"));
  }






  protected double myFallSpeed() {
    return 0.6D;
  }






  protected double flyerThrust() {
    return 0.3D;
  }






  protected float flyerFriction() {
    return 0.91F;
  }







  protected boolean selfPropelledFlyer() {
    return false;
  }






  public void makeEntityJump() {}





  public boolean isFlyer() {
    return false;
  }





  public int getMaxTemper() {
    return 100;
  }




  public double getCustomSpeed() {
    return 0.8D;
  }




  public double getCustomJump() {
    return 0.4D;
  }




  protected SoundEvent getAngrySound() {
    return null;
  }




  public void makeEntityDive() {}



  public boolean rideableEntity() {
    return false;
  }


  public int nameYOffset() {
    return -80;
  }




  protected Entity findPlayerToAttack() {
    return null;
  }

  public void repelMobs(Entity entity1, Double dist, World world) {
    List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(entity1, entity1.getEntityBoundingBox().expand(dist.doubleValue(), 4.0D, dist.doubleValue()));
    for (int i = 0; i < list.size(); i++) {
      Entity entity = list.get(i);
      if (entity instanceof EntityMob) {


        EntityMob entitymob = (EntityMob)entity;
        entitymob.setAttackTarget(null);
        entitymob.getNavigator().clearPath();
      }
    }
  }
  public void faceItem(int i, int j, int k, float f) {
    double d = i - this.posX;
    double d1 = k - this.posZ;
    double d2 = j - this.posY;
    double d3 = MathHelper.sqrt(d * d + d1 * d1);
    float f1 = (float)(Math.atan2(d1, d) * 180.0D / 3.141592741012573D) - 90.0F;
    float f2 = (float)(Math.atan2(d2, d3) * 180.0D / 3.141592741012573D);
    this.rotationPitch = -adjustRotation(this.rotationPitch, f2, f);
    this.rotationYaw = adjustRotation(this.rotationYaw, f1, f);
  }

  public float adjustRotation(float f, float f1, float f2) {
    float f3 = f1;
    for (f3 = f1 - f; f3 < -180.0F; f3 += 360.0F);

    for (; f3 >= 180.0F; f3 -= 360.0F);

    if (f3 > f2) {
      f3 = f2;
    }
    if (f3 < -f2) {
      f3 = -f2;
    }
    return f + f3;
  }

  public boolean isFlyingAlone() {
    return false;
  }

  public float getMoveSpeed() {
    return 0.7F;
  }








  public void performAnimation(int attackType) {}







  public boolean isMyFavoriteFood(ItemStack par1ItemStack) {
    return false;
  }


  public boolean isOnAir() {
    return (this.world.isAirBlock(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.posY - 0.2D),
          MathHelper.floor(this.posZ))) && this.world.isAirBlock(new BlockPos(MathHelper.floor(this.posX),
          MathHelper.floor(this.posY - 1.2D), MathHelper.floor(this.posZ))));
  }


  public float getSizeFactor() {
    return 1.0F;
  }


  public float getAdjustedYOffset() {
    return 0.0F;
  }

  public boolean getIsRideable() {
    return false;
  }


  public void setRideable(boolean b) {}

  public boolean entitiesToIgnore(Entity entity) {
    return (!(entity instanceof EntityLiving) || entity instanceof EntityMob || (entity instanceof EntityPlayer && getIsTamed()) || entity instanceof drzhark.mocreatures.entity.item.MoCEntityKittyBed || entity instanceof drzhark.mocreatures.entity.item.MoCEntityLitterBox || (

      getIsTamed() && entity instanceof MoCEntityAnimal && ((MoCEntityAnimal)entity).getIsTamed()) || (entity instanceof net.minecraft.entity.passive.EntityWolf && !MoCreatures.proxy.attackWolves) || (entity instanceof drzhark.mocreatures.entity.passive.MoCEntityHorse && !MoCreatures.proxy.attackHorses) || (entity.width > this.width && entity.height > this.height) || entity instanceof drzhark.mocreatures.entity.item.MoCEntityEgg);
  }







  public void setArmorType(int i) {}






  protected EntityLivingBase getBoogey(double d) {
    EntityLivingBase entityliving = null;
    List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(d, 4.0D, d));
    for (int i = 0; i < list.size(); i++) {
      Entity entity = list.get(i);
      if (entitiesToInclude(entity)) {
        entityliving = (EntityLivingBase)entity;
      }
    }
    return entityliving;
  }







  public boolean entitiesToInclude(Entity entity) {
    return (entity instanceof EntityLivingBase && (entity.width >= 0.5D || entity.height >= 0.5D));
  }


  public float pitchRotationOffset() {
    return 0.0F;
  }


  public float rollRotationOffset() {
    return 0.0F;
  }


  public float yawRotationOffset() {
    return 0.0F;
  }


  public float getAdjustedZOffset() {
    return 0.0F;
  }


  public float getAdjustedXOffset() {
    return 0.0F;
  }

  protected boolean canBeTrappedInNet() {
    return (this instanceof IMoCTameable && getIsTamed());
  }









  public boolean isCreatureType(EnumCreatureType type, boolean forSpawnCount) {
    if (type == EnumCreatureType.AMBIENT) {
      return true;
    }
    return false;
  }



  public boolean canAttackTarget(EntityLivingBase entity) {
    return false;
  }


  public boolean canBeLeashedTo(EntityPlayer player) {
    if (!this.world.isRemote && !MoCTools.isThisPlayerAnOP(player) && getIsTamed() && !player.getUniqueID().equals(getOwnerId())) {
      return false;
    }
    return super.canBeLeashedTo(player);
  }


  public boolean getIsSitting() {
    return false;
  }


  public boolean isNotScared() {
    return false;
  }


  public boolean isMovementCeased() {
    return getIsSitting();
  }


  public boolean shouldAttackPlayers() {
    return (this.world.getDifficulty() != EnumDifficulty.PEACEFUL);
  }


  protected boolean canTriggerWalking() {
    return false;
  }






  public double getDivingDepth() {
    return 0.5D;
  }


  public boolean isDiving() {
    return false;
  }


  public void forceEntityJump() {
    jump();
  }



  public void fall(float f, float f1) {}


  public int minFlyingHeight() {
    return 2;
  }






  public int maxFlyingHeight() {
    return 4;
  }


  public void travel(float strafe, float vertical, float forward) {
    if (!getIsFlying()) {
      super.travel(strafe, vertical, forward);
      return;
    }
    moveEntityWithHeadingFlying(strafe, vertical, forward);
  }

  public void moveEntityWithHeadingFlying(float strafe, float vertical, float forward) {
    if (isServerWorld()) {

      moveRelative(strafe, vertical, forward, 0.1F);
      move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.8999999761581421D;
      this.motionY *= 0.8999999761581421D;
      this.motionZ *= 0.8999999761581421D;
    } else {
      super.travel(strafe, vertical, forward);
    }
  }


  public boolean getIsFlying() {
    return false;
  }


  public String getClazzString() {
    return EntityList.getEntityString((Entity)this);
  }


  public boolean getIsGhost() {
    return false;
  }


  public void setLeashHolder(Entity entityIn, boolean sendAttachNotification) {
    if (getIsTamed() && entityIn instanceof EntityPlayer) {
      EntityPlayer entityplayer = (EntityPlayer)entityIn;
      if (MoCreatures.proxy.enableOwnership && getOwnerId() != null &&
        !entityplayer.getUniqueID().equals(getOwnerId()) && !MoCTools.isThisPlayerAnOP(entityplayer)) {
        return;
      }
    }
    super.setLeashHolder(entityIn, sendAttachNotification);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\MoCEntityAmbient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
