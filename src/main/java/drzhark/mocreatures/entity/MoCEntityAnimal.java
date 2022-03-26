package drzhark.mocreatures.entity;

import drzhark.mocreatures.MoCEntityData;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.ai.EntityAIMoverHelperMoC;
import drzhark.mocreatures.entity.ai.PathNavigateFlyer;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;






public abstract class MoCEntityAnimal
  extends EntityAnimal
  implements IMoCEntity
{
  protected boolean divePending;
  protected boolean jumpPending;
  protected int temper;
  protected boolean isEntityJumping;
  protected boolean riderIsDisconnecting;
  public float moveSpeed;
  protected String texture;
  private int huntingCounter;
  protected boolean isTameable;
  protected PathNavigate navigatorWater;
  protected PathNavigate navigatorFlyer;
  private double divingDepth;
  private boolean randomAttributesUpdated;
  protected static final DataParameter<Boolean> ADULT = EntityDataManager.createKey(MoCEntityAnimal.class, DataSerializers.BOOLEAN);
  protected static final DataParameter<Integer> TYPE = EntityDataManager.createKey(MoCEntityAnimal.class, DataSerializers.VARINT);
  protected static final DataParameter<Integer> AGE = EntityDataManager.createKey(MoCEntityAnimal.class, DataSerializers.VARINT);
  protected static final DataParameter<String> NAME_STR = EntityDataManager.createKey(MoCEntityAnimal.class, DataSerializers.STRING);

  public MoCEntityAnimal(World world) {
    super(world);
    this.riderIsDisconnecting = false;
    this.isTameable = false;
    this.texture = "blank.jpg";
    this.navigatorWater = (PathNavigate)new PathNavigateSwimmer((EntityLiving)this, world);
    this.moveHelper = (EntityMoveHelper)new EntityAIMoverHelperMoC((EntityLiving)this);
    this.navigatorFlyer = (PathNavigate)new PathNavigateFlyer((EntityLiving)this, world);
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
  }


  public ResourceLocation getTexture() {
    return MoCreatures.proxy.getTexture(this.texture);
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

  public boolean getIsJumping() {
    return this.isEntityJumping;
  }

  public void setIsJumping(boolean flag) {
    this.isEntityJumping = flag;
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

  public EntityLivingBase getClosestTarget(Entity entity, double d) {
    double d1 = -1.0D;
    EntityLivingBase entityliving = null;
    List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(d, d, d));
    for (int i = 0; i < list.size(); i++) {
      Entity entity1 = list.get(i);
      if (entity1 instanceof EntityLivingBase && entity1 != entity && entity1 != entity.getRidingEntity() && entity1 != entity
        .getRidingEntity() && !(entity1 instanceof EntityPlayer) && !(entity1 instanceof EntityMob) && this.height > entity1.height && this.width > entity1.width) {



        double d2 = entity1.getDistanceSq(entity.posY, entity.posZ, entity.motionX);
        if ((d < 0.0D || d2 < d * d) && (d1 == -1.0D || d2 < d1) && ((EntityLivingBase)entity1).canEntityBeSeen(entity)) {
          d1 = d2;
          entityliving = (EntityLivingBase)entity1;
        }
      }
    }
    return entityliving;
  }

  protected EntityLivingBase getClosestSpecificEntity(Entity entity, Class<? extends EntityLiving> myClass, double d) {
    double d1 = -1.0D;
    EntityLivingBase entityliving = null;
    List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(entity, entity.getEntityBoundingBox().expand(d, d, d));
    for (int i = 0; i < list.size(); i++) {
      Entity entity1 = list.get(i);
      if (myClass.isAssignableFrom(entity1.getClass())) {



        double d2 = entity1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
        if ((d < 0.0D || d2 < d * d) && (d1 == -1.0D || d2 < d1)) {

          d1 = d2;
          entityliving = (EntityLivingBase)entity1;
        }
      }
    }
    return entityliving;
  }

  public boolean entitiesToIgnore(Entity entity) {
    return (!(entity instanceof EntityLiving) || entity instanceof EntityMob || entity instanceof EntityPlayer || entity instanceof drzhark.mocreatures.entity.item.MoCEntityKittyBed || entity instanceof drzhark.mocreatures.entity.item.MoCEntityLitterBox || (

      getIsTamed() && entity instanceof IMoCEntity && ((IMoCEntity)entity).getIsTamed()) || (entity instanceof net.minecraft.entity.passive.EntityWolf && !MoCreatures.proxy.attackWolves) || (entity instanceof drzhark.mocreatures.entity.passive.MoCEntityHorse && !MoCreatures.proxy.attackHorses) || entity.width >= this.width || entity.height >= this.height || entity instanceof drzhark.mocreatures.entity.item.MoCEntityEgg || (entity instanceof IMoCEntity && !MoCreatures.proxy.enableHunters));
  }










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
    return (entity.getClass() != getClass() && entity instanceof EntityLivingBase && (entity.width >= 0.5D || entity.height >= 0.5D));
  }


  public void onLivingUpdate() {
    if (!this.world.isRemote) {
      if (rideableEntity() && isBeingRidden()) {
        Riding();
      }

      if (isMovementCeased()) {
        getNavigator().clearPath();
      }
      if (getEdad() == 0) setEdad(getMaxEdad() - 10);
      if (!getIsAdult() && this.rand.nextInt(300) == 0 && getEdad() <= getMaxEdad()) {
        setEdad(getEdad() + 1);
        if (getEdad() >= getMaxEdad()) {
          setAdult(true);
        }
      }

      if (MoCreatures.proxy.enableHunters && isReadyToHunt() && !getIsHunting() && this.rand.nextInt(500) == 0) {
        setIsHunting(true);
      }

      if (getIsHunting() && ++this.huntingCounter > 50) {
        setIsHunting(false);
      }

      getNavigator().onUpdateNavigation();
    }

    if (isInWater() && isAmphibian() && (
      this.rand.nextInt(500) == 0 || !this.randomAttributesUpdated)) {
      setNewDivingDepth();
      this.randomAttributesUpdated = true;
    }



    if (canRidePlayer() && isRiding()) MoCTools.dismountSneakingPlayer((EntityLiving)this);
    resetInLove();
    super.onLivingUpdate();
  }

  public int getMaxEdad() {
    return 100;
  }


  public boolean isNotScared() {
    return false;
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
    if (isAmphibian()) {
      return this.world.handleMaterialAcceleration(getEntityBoundingBox().expand(0.0D, -0.2D, 0.0D), Material.WATER, (Entity)this);
    }
    return super.isInWater();
  }


  public boolean canBreatheUnderwater() {
    return isAmphibian();
  }

  public EntityItem getClosestItem(Entity entity, double d, Item item, Item item1) {
    double d1 = -1.0D;
    EntityItem entityitem = null;
    List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(d, d, d));
    for (int k = 0; k < list.size(); k++) {
      Entity entity1 = list.get(k);
      if (entity1 instanceof EntityItem) {


        EntityItem entityitem1 = (EntityItem)entity1;
        if (entityitem1.getItem().getItem() == item || entityitem1.getItem().getItem() == item1) {


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

  public EntityItem getClosestFood(Entity entity, double d) {
    double d1 = -1.0D;
    EntityItem entityitem = null;
    List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(d, d, d));
    for (int k = 0; k < list.size(); k++) {
      Entity entity1 = list.get(k);
      if (entity1 instanceof EntityItem) {


        EntityItem entityitem1 = (EntityItem)entity1;
        if (MoCTools.isItemEdible(entityitem1.getItem().getItem())) {


          double d2 = entityitem1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
          if ((d < 0.0D || d2 < d * d) && (d1 == -1.0D || d2 < d1)) {
            d1 = d2;
            entityitem = entityitem1;
          }
        }
      }
    }  return entityitem;
  }

  public void faceLocation(int i, int j, int k, float f) {
    double var4 = i + 0.5D - this.posX;
    double var8 = k + 0.5D - this.posZ;
    double var6 = j + 0.5D - this.posY;
    double var14 = MathHelper.sqrt(var4 * var4 + var8 * var8);
    float var12 = (float)(Math.atan2(var8, var4) * 180.0D / Math.PI) - 90.0F;
    float var13 = (float)-(Math.atan2(var6, var14) * 180.0D / Math.PI);
    this.rotationPitch = -updateRotation2(this.rotationPitch, var13, f);
    this.rotationYaw = updateRotation2(this.rotationYaw, var12, f);
  }





  private float updateRotation2(float par1, float par2, float par3) {
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
      if (list != null)
        for (int i = 0; i < list.size(); i++) {
          Entity entity = list.get(i);
          if (!entity.isDead) {


            entity.onCollideWithPlayer(entityplayer);
            if (entity instanceof EntityMob) {


              float f = getDistance(entity);
              if (f < 2.0F && entity instanceof EntityMob && this.rand.nextInt(10) == 0)
                attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)entity),
                    (float)((EntityMob)entity).getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
            }
          }
        }
      if (entityplayer.isSneaking()) {
        makeEntityDive();
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
          BlockPos pos = new BlockPos(i, k, j);
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

  public MoCEntityAnimal spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
    return null;
  }



  // public boolean getCanSpawnHereCreature() {
  //   BlockPos pos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor((getEntityBoundingBox()).minY), MathHelper.floor(this.posZ));
  //   return (getBlockPathWeight(pos) >= 0.0F);
  // }

  // public boolean getCanSpawnHereLiving() {
  //   return (this.world.checkNoEntityCollision(getEntityBoundingBox()) && this.world
  //     .getCollisionBoxes((Entity)this, getEntityBoundingBox()).isEmpty() &&
  //     !this.world.containsAnyLiquid(getEntityBoundingBox()));
  // }


  // public boolean getCanSpawnHere() {
  //   if (((MoCEntityData)MoCreatures.entityMap.get(getClass())).getFrequency() <= 0) {
  //   }
  //   if (this.world.provider.getDimensionType().getId() != 0) {
  //     return (getCanSpawnHereCreature() && getCanSpawnHereLiving());
  //   }

  //   return super.getCanSpawnHere();
  // }


  // public boolean getCanSpawnHereJungle() {
  //   if (this.world.checkNoEntityCollision(getEntityBoundingBox()) && this.world
  //     .getCollisionBoxes((Entity)this, getEntityBoundingBox()).isEmpty()) {
  //     return true;
  //   }
  //   return false;
  // }


  public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
    super.writeEntityToNBT(nbttagcompound);

    nbttagcompound.setBoolean("Adult", getIsAdult());
    nbttagcompound.setInteger("Edad", getEdad());
    nbttagcompound.setString("Name", getPetName());
    nbttagcompound.setInteger("TypeInt", getType());
  }


  public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
    super.readEntityFromNBT(nbttagcompound);

    setAdult(nbttagcompound.getBoolean("Adult"));
    setEdad(nbttagcompound.getInteger("Edad"));
    setPetName(nbttagcompound.getString("Name"));
    setType(nbttagcompound.getInteger("TypeInt"));
  }






  public void travel(float strafe, float vertical, float forward) {
    if (isBeingRidden()) {
      EntityLivingBase passenger = (EntityLivingBase)getControllingPassenger();
      if (passenger != null) moveWithRider(strafe, vertical, forward, passenger);
      return;
    }
    if ((isAmphibian() && isInWater()) || (isFlyer() && getIsFlying())) {
      moveRelative(strafe, vertical, forward, 0.1F);
      move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.8999999761581421D;
      this.motionY *= 0.8999999761581421D;
      this.motionZ *= 0.8999999761581421D;
      if (getAttackTarget() == null) {
        this.motionY -= 0.005D;
      }
    } else {

      super.travel(strafe, vertical, forward);
    }
  }








  public void moveWithRider(float strafe, float vertical, float forward, EntityLivingBase passenger) {
    if (passenger == null) {
      return;
    }

    if (isBeingRidden() && !getIsTamed()) {
      moveEntityWithRiderUntamed(strafe, forward, passenger);
      return;
    }
    boolean flySelfPropelled = (selfPropelledFlyer() && isOnAir());
    boolean flyingMount = (isFlyer() && isBeingRidden() && getIsTamed() && !this.onGround && isOnAir());
    this.rotationYaw = passenger.rotationYaw;
    this.prevRotationYaw = this.rotationYaw;
    this.rotationPitch = passenger.rotationPitch * 0.5F;
    setRotation(this.rotationYaw, this.rotationPitch);
    this.renderYawOffset = this.rotationYaw;
    this.rotationYawHead = this.renderYawOffset;
    if (!selfPropelledFlyer() || (selfPropelledFlyer() && !isOnAir())) {
      strafe = (float)((passenger.moveStrafing * 0.5F) * getCustomSpeed());
      forward = (float)(passenger.moveForward * getCustomSpeed());
    }

    if (this.jumpPending && isFlyer()) {
      this.motionY += flyerThrust();
      this.jumpPending = false;

      if (flySelfPropelled) {
        float velX = MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F);
        float velZ = MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F);

        this.motionX += (-0.5F * velX);
        this.motionZ += (0.5F * velZ);
      }
    } else if (this.jumpPending && !getIsJumping()) {
      this.motionY = getCustomJump() * 2.0D;
      setIsJumping(true);
      this.jumpPending = false;
    }

    if (this.divePending) {
      this.divePending = false;
      this.motionY -= 0.3D;
    }
    if (flyingMount) {
      move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
      moveRelative(strafe, vertical, forward, flyerFriction() / 10.0F);
      this.motionY *= myFallSpeed();
      this.motionY -= 0.055D;
      this.motionZ *= flyerFriction();
      this.motionX *= flyerFriction();
    } else {
      setAIMoveSpeed((float)getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
      super.travel(strafe, vertical, forward);
    }

    if (this.onGround) {
      setIsJumping(false);
      this.divePending = false;
      this.jumpPending = false;
    }
  }


  public void moveEntityWithRiderUntamed(float strafe, float forward, EntityLivingBase passenger) {
    if (isBeingRidden() && !getIsTamed()) {
      if (this.rand.nextInt(5) == 0 && !getIsJumping() && this.jumpPending) {
        this.motionY += getCustomJump();
        setIsJumping(true);
        this.jumpPending = false;
      }
      if (this.rand.nextInt(10) == 0) {
        this.motionX += this.rand.nextDouble() / 30.0D;
        this.motionZ += this.rand.nextDouble() / 10.0D;
      }
      if (!this.world.isRemote) {
        move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
      }
      if (!this.world.isRemote && this.rand.nextInt(50) == 0) {
        passenger.motionY += 0.9D;
        passenger.motionZ -= 0.3D;
        passenger.dismountRidingEntity();
      }
      if (this.onGround) {
        setIsJumping(false);
      }
      if (!this.world.isRemote && this instanceof IMoCTameable && passenger instanceof EntityPlayer) {
        int chance = getMaxTemper() - getTemper();
        if (chance <= 0) {
          chance = 1;
        }
        if (this.rand.nextInt(chance * 8) == 0) {
          MoCTools.tameWithName((EntityPlayer)passenger, (IMoCTameable)this);
        }
      }
    }
  }







  public int maxFlyingHeight() {
    return 5;
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






  public void makeEntityJump() {
    this.jumpPending = true;
  }




  public boolean isFlyer() {
    return false;
  }

  public int getTemper() {
    return this.temper;
  }

  public void setTemper(int i) {
    this.temper = i;
  }





  public int getMaxTemper() {
    return 100;
  }




  public double getCustomSpeed() {
    return 0.6D;
  }




  public double getCustomJump() {
    return 0.4D;
  }




  protected SoundEvent getAngrySound() {
    return null;
  }




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








  public void performAnimation(int attackType) {}







  public boolean isMyFavoriteFood(ItemStack par1ItemStack) {
    return false;
  }






  public void makeEntityDive() {
    this.divePending = true;
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


  public void onDeath(DamageSource damagesource) {
    if (!this.world.isRemote) {
      dropMyStuff();
    }

    super.onDeath(damagesource);
  }


  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    if (isNotScared()) {
      EntityLivingBase entityLivingBase = getAttackTarget();
      boolean flag = super.attackEntityFrom(damagesource, i);
      setAttackTarget(entityLivingBase);
      return flag;
    }

    return super.attackEntityFrom(damagesource, i);
  }

  public boolean getIsRideable() {
    return false;
  }


  public void setRideable(boolean b) {}


  public void setArmorType(int i) {}


  public int getArmorType() {
    return 0;
  }




  public void dropArmor() {}



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










  public boolean canAttackTarget(EntityLivingBase entity) {
    return (this.height >= entity.height && this.width >= entity.width);
  }



  public boolean attackEntityAsMob(Entity entityIn) {
    boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this),
        (int)getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
    if (flag) {
      applyEnchantments((EntityLivingBase)this, entityIn);
    }

    return flag;
  }

  public boolean isReadyToHunt() {
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


  public boolean isMovementCeased() {
    return (getIsSitting() || isBeingRidden());
  }

  public boolean getIsHunting() {
    return (this.huntingCounter != 0);
  }

  public void setIsHunting(boolean flag) {
    if (flag) {
      this.huntingCounter = this.rand.nextInt(30) + 1;
    } else {
      this.huntingCounter = 0;
    }
  }


  public boolean shouldAttackPlayers() {
    return (!getIsTamed() && this.world.getDifficulty() != EnumDifficulty.PEACEFUL);
  }


  public void onKillEntity(EntityLivingBase entityLivingIn) {
    if (!(entityLivingIn instanceof EntityPlayer)) {
      MoCTools.destroyDrops((Entity)this, 3.0D);
    }
  }


  public PathNavigate getNavigator() {
    if (isInWater() && isAmphibian()) {
      return this.navigatorWater;
    }
    if (isFlyer() && getIsFlying()) {
      return this.navigatorFlyer;
    }
    return this.navigator;
  }

  public boolean isAmphibian() {
    return false;
  }


  public boolean isDiving() {
    return false;
  }






  public double getDivingDepth() {
    return (float)this.divingDepth;
  }





  protected void setNewDivingDepth(double setDepth) {
    if (setDepth != 0.0D) {
      if (setDepth > maxDivingDepth()) {
        setDepth = maxDivingDepth();
      }
      if (setDepth < minDivingDepth()) {
        setDepth = minDivingDepth();
      }
      this.divingDepth = setDepth;
    } else {

      this.divingDepth = (float)(this.rand.nextDouble() * (maxDivingDepth() - minDivingDepth()) + minDivingDepth());
    }
  }


  protected void setNewDivingDepth() {
    setNewDivingDepth(0.0D);
  }

  protected double minDivingDepth() {
    return 0.2D;
  }

  protected double maxDivingDepth() {
    return 1.0D;
  }


  public void forceEntityJump() {
    jump();
  }


  public int minFlyingHeight() {
    return 1;
  }


  public boolean getIsFlying() {
    return false;
  }









  public boolean isCreatureType(EnumCreatureType type, boolean forSpawnCount) {
    if (type == EnumCreatureType.CREATURE) {
      return true;
    }
    return false;
  }







  @Nullable
  public Entity getControllingPassenger() {
    return getPassengers().isEmpty() ? null : getPassengers().get(0);
  }






  public boolean canRidePlayer() {
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


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\MoCEntityAnimal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
