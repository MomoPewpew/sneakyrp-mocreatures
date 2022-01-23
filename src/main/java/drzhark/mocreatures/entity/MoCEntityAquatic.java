package drzhark.mocreatures.entity;

import drzhark.mocreatures.MoCEntityData;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.ai.EntityAIMoverHelperMoC;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class MoCEntityAquatic
  extends EntityCreature
  implements IMoCEntity {
  protected boolean divePending;
  protected boolean jumpPending;
  protected boolean isEntityJumping;
  protected int outOfWater;
  private boolean diving;
  private int divingCount;
  private int mountCount;
  public boolean fishHooked;
  protected boolean riderIsDisconnecting;
  protected float moveSpeed;
  protected String texture;
  protected PathNavigate navigatorWater;
  private boolean updateDivingDepth = false;
  private double divingDepth;
  protected int temper;
  protected static final DataParameter<Boolean> ADULT = EntityDataManager.createKey(MoCEntityAquatic.class, DataSerializers.BOOLEAN);
  protected static final DataParameter<Integer> TYPE = EntityDataManager.createKey(MoCEntityAquatic.class, DataSerializers.VARINT);
  protected static final DataParameter<Integer> AGE = EntityDataManager.createKey(MoCEntityAquatic.class, DataSerializers.VARINT);
  protected static final DataParameter<String> NAME_STR = EntityDataManager.createKey(MoCEntityAquatic.class, DataSerializers.STRING);

  public MoCEntityAquatic(World world) {
    super(world);
    this.outOfWater = 0;
    setTemper(50);
    setNewDivingDepth();
    this.riderIsDisconnecting = false;
    this.texture = "blank.jpg";
    this.navigatorWater = (PathNavigate)new PathNavigateSwimmer((EntityLiving)this, world);
    this.moveHelper = (EntityMoveHelper)new EntityAIMoverHelperMoC((EntityLiving)this);
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getMoveSpeed());
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
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


  public int getOwnerPetId() {
    return -1;
  }


  public void setOwnerPetId(int i) {}


  @Nullable
  public UUID getOwnerId() {
    return null;
  }


  public boolean getIsTamed() {
    return false;
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


  public int getEdad() {
    return ((Integer)this.dataManager.get(AGE)).intValue();
  }


  public void setEdad(int i) {
    this.dataManager.set(AGE, Integer.valueOf(i));
  }


  public void setPetName(String name) {
    this.dataManager.set(NAME_STR, name);
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

  public float b(float f, float f1, float f2) {
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

  public void faceItem(int i, int j, int k, float f) {
    double d = i - this.posX;
    double d1 = k - this.posZ;
    double d2 = j - this.posY;
    double d3 = MathHelper.sqrt(d * d + d1 * d1);
    float f1 = (float)(Math.atan2(d1, d) * 180.0D / 3.141592741012573D) - 90.0F;
    float f2 = (float)(Math.atan2(d2, d3) * 180.0D / 3.141592741012573D);
    this.rotationPitch = -b(this.rotationPitch, f2, f);
    this.rotationYaw = b(this.rotationYaw, f1, f);
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



  protected void playStepSound(BlockPos pos, Block par4) {}


  public void fall(float f, float f1) {}


  public EntityItem getClosestFish(Entity entity, double d) {
    double d1 = -1.0D;
    EntityItem entityitem = null;
    List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(d, d, d));
    for (int i = 0; i < list.size(); i++) {
      Entity entity1 = list.get(i);
      if (entity1 instanceof EntityItem) {


        EntityItem entityitem1 = (EntityItem)entity1;
        if (entityitem1.getItem().getItem() == Items.FISH && entityitem1.isInWater()) {


          double d2 = entityitem1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
          if ((d < 0.0D || d2 < d * d) && (d1 == -1.0D || d2 < d1)) {
            d1 = d2;
            entityitem = entityitem1;
          }
        }
      }
    }  return entityitem;
  }


  protected float getSoundVolume() {
    return 0.4F;
  }

  public boolean gettingOutOfWater() {
    int i = (int)this.posX;
    int j = (int)this.posY;
    int k = (int)this.posZ;
    return this.world.isAirBlock(new BlockPos(i, j + 1, k));
  }




  public double getCustomJump() {
    return 0.4D;
  }

  public void setIsJumping(boolean flag) {
    this.isEntityJumping = flag;
  }

  public boolean getIsJumping() {
    return this.isEntityJumping;
  }






  public void makeEntityJump() {
    this.jumpPending = true;
  }






  protected boolean MoveToNextEntity(Entity entity) {
    if (entity != null) {
      int i = MathHelper.floor(entity.posX);
      int j = MathHelper.floor(entity.posY);
      int k = MathHelper.floor(entity.posZ);
      faceItem(i, j, k, 30.0F);
      if (this.posX < i) {
        double d = entity.posX - this.posX;
        if (d > 0.5D) {
          this.motionX += 0.05D;
        }
      } else {
        double d1 = this.posX - entity.posX;
        if (d1 > 0.5D) {
          this.motionX -= 0.05D;
        }
      }
      if (this.posZ < k) {
        double d2 = entity.posZ - this.posZ;
        if (d2 > 0.5D) {
          this.motionZ += 0.05D;
        }
      } else {
        double d3 = this.posZ - entity.posZ;
        if (d3 > 0.5D) {
          this.motionZ -= 0.05D;
        }
      }
      return true;
    }
    return false;
  }







  public double getCustomSpeed() {
    return 1.5D;
  }


  public boolean isInWater() {
    return this.world.handleMaterialAcceleration(getEntityBoundingBox().expand(0.0D, -0.2D, 0.0D), Material.WATER, (Entity)this);
  }


  public boolean canBreatheUnderwater() {
    return true;
  }


  public boolean isDiving() {
    return this.diving;
  }



  protected void jump() {}



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
              if (f < 2.0F && entity instanceof EntityMob && this.rand.nextInt(10) == 0) {
                attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)entity),
                    (float)((EntityMob)entity).getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
              }
            }
          }
        }
      }
    }
  }





  public boolean isMovementCeased() {
    return ((!isSwimming() && !isBeingRidden()) || isBeingRidden() || getIsSitting());
  }


  public void onLivingUpdate() {
    if (!this.world.isRemote) {
      if (isBeingRidden()) {
        Riding();
        this.mountCount = 1;
      }

      if (this.mountCount > 0 &&
        ++this.mountCount > 50) {
        this.mountCount = 0;
      }

      if (getEdad() == 0) setEdad(getMaxEdad() - 10);
      if (!getIsAdult() && this.rand.nextInt(300) == 0) {
        setEdad(getEdad() + 1);
        if (getEdad() >= getMaxEdad()) {
          setAdult(true);
        }
      }

      getNavigator().onUpdateNavigation();


      if (!getNavigator().noPath()) {

        if (!this.updateDivingDepth) {

          float targetDepth = MoCTools.distanceToSurface(this.moveHelper.getX(), this.moveHelper.getY(), this.moveHelper.getZ(), this.world);
          setNewDivingDepth(targetDepth);
          this.updateDivingDepth = true;
        }
      } else {
        this.updateDivingDepth = false;
      }

      if (isMovementCeased() || this.rand.nextInt(200) == 0) {
        getNavigator().clearPath();
      }

















      if (isFisheable() && !this.fishHooked && this.rand.nextInt(30) == 0) {
        getFished();
      }

      if (this.fishHooked && this.rand.nextInt(200) == 0) {
        this.fishHooked = false;

        List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(2.0D, 2.0D, 2.0D));
        for (int i = 0; i < list.size(); i++) {
          Entity entity1 = list.get(i);

          if (entity1 instanceof EntityFishHook &&
            ((EntityFishHook)entity1).caughtEntity == this) {
            ((EntityFishHook)entity1).caughtEntity = null;
          }
        }
      }
    }


    this.moveSpeed = getMoveSpeed();

    if (isSwimming()) {

      this.outOfWater = 0;
      setAir(800);
    } else {
      this.outOfWater++;
      this.motionY -= 0.1D;
      if (this.outOfWater > 20) {
        getNavigator().clearPath();
      }
      if (this.outOfWater > 300 && this.outOfWater % 40 == 0) {
        this.motionY += 0.3D;
        this.motionX = (float)(Math.random() * 0.2D - 0.1D);
        this.motionZ = (float)(Math.random() * 0.2D - 0.1D);
        attackEntityFrom(DamageSource.DROWN, 1.0F);
      }
    }
    if (!this.diving) {
      if (!isBeingRidden() && getAttackTarget() == null && !this.navigator.noPath() && this.rand.nextInt(500) == 0) {
        this.diving = true;
      }
    } else {
      this.divingCount++;
      if (this.divingCount > 100 || isBeingRidden()) {
        this.diving = false;
        this.divingCount = 0;
      }
    }
    super.onLivingUpdate();
  }

  public boolean isSwimming() {
    return isInsideOfMaterial(Material.WATER);
  }


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


  public void setDisplayName(boolean flag) {}

  public void setTypeInt(int i) {
    setType(i);
    selectType();
  }







  public void performAnimation(int attackType) {}







  protected void despawnEntity() {
    EntityPlayer var1 = this.world.getClosestPlayerToEntity((Entity)this, -1.0D);
    if (var1 != null) {
      double var2 = var1.posX - this.posX;
      double var4 = var1.posY - this.posY;
      double var6 = var1.posZ - this.posZ;
      double var8 = var2 * var2 + var4 * var4 + var6 * var6;

      if (canDespawn() && var8 > 16384.0D) {
        setDead();
      }

      if (this.idleTime > 1800 && this.rand.nextInt(800) == 0 && var8 > 1024.0D && canDespawn()) {
        setDead();
      } else if (var8 < 1024.0D) {
        this.idleTime = 0;
      }
    }
  }

  public float getMoveSpeed() {
    return 0.7F;
  }


  public int nameYOffset() {
    return 0;
  }


  public boolean renderName() {
    return (MoCreatures.proxy.getDisplayPetName() &&
      getPetName() != null && !getPetName().equals("") && !isBeingRidden() && getRidingEntity() == null);
  }












  public void makeEntityDive() {
    this.divePending = true;
  }


  public float getSizeFactor() {
    return 1.0F;
  }


  public float getAdjustedYOffset() {
    return 0.0F;
  }






  public boolean getCanSpawnHere() {
    return (((MoCEntityData)MoCreatures.entityMap.get(getClass())).getFrequency() > 0 && this.world.checkNoEntityCollision(getEntityBoundingBox()));
  }




  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    if (usesNewAI()) {
      return super.attackEntityFrom(damagesource, i);
    }

    if (isNotScared()) {
      EntityLivingBase entityLivingBase = getAttackTarget();
      setAttackTarget(entityLivingBase);
      return super.attackEntityFrom(damagesource, i);
    }

    return super.attackEntityFrom(damagesource, i);
  }

  protected boolean canBeTrappedInNet() {
    return (this instanceof IMoCTameable && getIsTamed());
  }





  protected void dropMyStuff() {}




  protected boolean isMyHealFood(ItemStack itemstack) {
    return false;
  }



  public void setArmorType(int i) {}


  public float pitchRotationOffset() {
    return 0.0F;
  }


  public float rollRotationOffset() {
    return 0.0F;
  }




  private void getFished() {
    EntityPlayer entityplayer1 = this.world.getClosestPlayerToEntity((Entity)this, 18.0D);
    if (entityplayer1 != null) {
      EntityFishHook fishHook = entityplayer1.fishEntity;
      if (fishHook != null && fishHook.caughtEntity == null) {
        float f = fishHook.getDistance((Entity)this);
        if (f > 1.0F) {
          MoCTools.getPathToEntity((EntityLiving)this, (Entity)fishHook, f);
        } else {
          fishHook.caughtEntity = (Entity)this;
          this.fishHooked = true;
        }
      }
    }
  }






  protected boolean isFisheable() {
    return false;
  }


  public float getAdjustedZOffset() {
    return 0.0F;
  }


  public float getAdjustedXOffset() {
    return 0.0F;
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


  public boolean isNotScared() {
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


  public boolean shouldAttackPlayers() {
    return (!getIsTamed() && this.world.getDifficulty() != EnumDifficulty.PEACEFUL);
  }





  public void travel(float strafe, float vertical, float forward) {
    if (isInWater()) {
      if (isBeingRidden()) {
        EntityLivingBase passenger = (EntityLivingBase)getControllingPassenger();
        if (passenger != null) moveWithRider(strafe, vertical, forward, passenger);
        return;
      }
      moveRelative(strafe, vertical, forward, 0.1F);
      move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.8999999761581421D;
      this.motionY *= 0.8999999761581421D;
      this.motionZ *= 0.8999999761581421D;

      if (getAttackTarget() == null) {
        this.motionY -= 0.005D;
      }
      this.prevLimbSwingAmount = this.limbSwingAmount;
      double d2 = this.posX - this.prevPosX;
      double d3 = this.posZ - this.prevPosZ;
      float f7 = MathHelper.sqrt(d2 * d2 + d3 * d3) * 4.0F;

      if (f7 > 1.0F)
      {
        f7 = 1.0F;
      }

      this.limbSwingAmount += (f7 - this.limbSwingAmount) * 0.4F;
      this.limbSwing += this.limbSwingAmount;
    } else {
      super.travel(strafe, vertical, forward);
    }
  }






  public void moveWithRider(float strafe, float vertical, float forward, EntityLivingBase passenger) {
    if (passenger == null) {
      return;
    }


    if (isBeingRidden() && !getIsTamed() && !isSwimming()) {
      removePassengers();

      return;
    }
    if (isBeingRidden() && !getIsTamed() && passenger instanceof EntityLivingBase) {
      moveWithRiderUntamed(strafe, vertical, forward, passenger);

      return;
    }
    if (isBeingRidden() && getIsTamed() && passenger instanceof EntityLivingBase) {
      this.prevRotationYaw = this.rotationYaw = passenger.rotationYaw;
      this.rotationPitch = passenger.rotationPitch * 0.5F;
      setRotation(this.rotationYaw, this.rotationPitch);
      this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
      strafe = passenger.moveStrafing * 0.35F;
      forward = passenger.moveForward * (float)(getCustomSpeed() / 5.0D);
      if (this.jumpPending) {
        if (isSwimming()) {
          this.motionY += getCustomJump();
        }
        this.jumpPending = false;
      }

      if (this.motionY < 0.0D && isSwimming()) {
        this.motionY = 0.0D;
      }
      if (this.divePending) {
        this.divePending = false;
        this.motionY -= 0.3D;
      }
      setAIMoveSpeed((float)getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
      super.travel(strafe, vertical, forward);
      moveRelative(strafe, vertical, forward, 0.1F);
    }
  }


  public void moveWithRiderUntamed(float strafe, float vertical, float forward, EntityLivingBase passenger) {
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

      move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);

      if (!this.world.isRemote && this.rand.nextInt(100) == 0) {
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






  public boolean isNotColliding() {
    return this.world.checkNoEntityCollision(getEntityBoundingBox(), (Entity)this);
  }





  public int getTalkInterval() {
    return 300;
  }





  protected int getExperiencePoints(EntityPlayer player) {
    return 1 + this.world.rand.nextInt(3);
  }





  public void onEntityUpdate() {
    int i = getAir();
    super.onEntityUpdate();

    if (isEntityAlive() && !isInWater()) {
      i--;
      setAir(i);

      if (getAir() == -30) {
        setAir(0);
        attackEntityFrom(DamageSource.DROWN, 1.0F);
        this.motionX += this.rand.nextDouble() / 10.0D;
        this.motionZ += this.rand.nextDouble() / 10.0D;
      }
    } else {
      setAir(300);
    }
  }


  public boolean isPushedByWater() {
    return false;
  }

  protected boolean usesNewAI() {
    return false;
  }


  public PathNavigate getNavigator() {
    if (isInWater()) {
      return this.navigatorWater;
    }
    return this.navigator;
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
    return 3.0D;
  }


  public void forceEntityJump() {
    jump();
  }


  @SideOnly(Side.CLIENT)
  public float yawRotationOffset() {
    double d4 = 0.0D;
    if (this.motionX != 0.0D || this.motionZ != 0.0D) {
      d4 = Math.sin(this.ticksExisted * 0.5D) * 8.0D;
    }
    return (float)d4;
  }

  public int getMaxEdad() {
    return 100;
  }


  public boolean attackEntityAsMob(Entity entityIn) {
    if (!entityIn.isInWater()) {
      return false;
    }

    boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this),
        (int)getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
    if (flag) {
      applyEnchantments((EntityLivingBase)this, entityIn);
    }
    return flag;
  }


  public int maxFlyingHeight() {
    return 1;
  }


  public int minFlyingHeight() {
    return 1;
  }




  public boolean isFlyer() {
    return false;
  }


  public boolean getIsFlying() {
    return false;
  }

  protected SoundEvent getUpsetSound() {
    return SoundEvents.ENTITY_GENERIC_HURT;
  }









  public boolean isCreatureType(EnumCreatureType type, boolean forSpawnCount) {
    if (type == EnumCreatureType.WATER_CREATURE) {
      return true;
    }
    return false;
  }







  @Nullable
  public Entity getControllingPassenger() {
    return getPassengers().isEmpty() ? null : getPassengers().get(0);
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


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\MoCEntityAquatic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
