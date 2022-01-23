package drzhark.mocreatures.entity;

import drzhark.mocreatures.MoCEntityData;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.ai.EntityAIMoverHelperMoC;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.entity.ai.PathNavigateFlyer;
import drzhark.mocreatures.network.MoCMessageHandler;
import drzhark.mocreatures.network.message.MoCMessageHealth;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;





public abstract class MoCEntityMob
  extends EntityMob
  implements IMoCEntity
{
  protected boolean divePending;
  protected int maxHealth;
  protected float moveSpeed;
  protected String texture;
  protected PathNavigate navigatorWater;
  protected PathNavigate navigatorFlyer;
  protected EntityAIWanderMoC2 wander;
  protected static final DataParameter<Boolean> ADULT = EntityDataManager.createKey(EntityCreature.class, DataSerializers.BOOLEAN);
  protected static final DataParameter<Integer> TYPE = EntityDataManager.createKey(EntityCreature.class, DataSerializers.VARINT);
  protected static final DataParameter<Integer> AGE = EntityDataManager.createKey(EntityCreature.class, DataSerializers.VARINT);
  protected static final DataParameter<String> NAME_STR = EntityDataManager.createKey(EntityCreature.class, DataSerializers.STRING);

  public MoCEntityMob(World world) {
    super(world);
    this.texture = "blank.jpg";
    this.moveHelper = (EntityMoveHelper)new EntityAIMoverHelperMoC((EntityLiving)this);
    this.navigatorWater = (PathNavigate)new PathNavigateSwimmer((EntityLiving)this, world);
    this.navigatorFlyer = (PathNavigate)new PathNavigateFlyer((EntityLiving)this, world);
    this.tasks.addTask(4, (EntityAIBase)(this.wander = new EntityAIWanderMoC2((EntityCreature)this, 1.0D, 80)));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getMoveSpeed());
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(getAttackStrenght());
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
  }


  public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData par1EntityLivingData) {
    selectType();
    return super.onInitialSpawn(difficulty, par1EntityLivingData);
  }


  public ResourceLocation getTexture() {
    return MoCreatures.proxy.getTexture(this.texture);
  }

  protected double getAttackStrenght() {
    return 2.0D;
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


  public boolean getIsAdult() {
    return ((Boolean)this.dataManager.get(ADULT)).booleanValue();
  }


  public void setAdult(boolean flag) {
    this.dataManager.set(ADULT, Boolean.valueOf(flag));
  }


  public boolean getIsTamed() {
    return false;
  }


  public String getPetName() {
    return ((String)this.dataManager.get(NAME_STR)).toString();
  }


  public int getEdad() {
    return ((Integer)this.dataManager.get(AGE)).intValue();
  }


  @Nullable
  public UUID getOwnerId() {
    return null;
  }


  public void setOwnerUniqueId(@Nullable UUID uniqueId) {}


  public int getOwnerPetId() {
    return 0;
  }



  public void setOwnerPetId(int petId) {}


  public void setEdad(int i) {
    this.dataManager.set(AGE, Integer.valueOf(i));
  }


  public void setPetName(String name) {
    this.dataManager.set(NAME_STR, String.valueOf(name));
  }

  public boolean getCanSpawnHereLiving() {
    return (this.world.checkNoEntityCollision(getEntityBoundingBox()) && this.world
      .getCollisionBoxes((Entity)this, getEntityBoundingBox()).size() == 0 &&
      !this.world.containsAnyLiquid(getEntityBoundingBox()));
  }

  public boolean getCanSpawnHereCreature() {
    int i = MathHelper.floor(this.posX);
    int j = MathHelper.floor((getEntityBoundingBox()).minY);
    int k = MathHelper.floor(this.posZ);
    return (getBlockPathWeight(new BlockPos(i, j, k)) >= 0.0F);
  }


  public boolean getCanSpawnHere() {
    return (((MoCEntityData)MoCreatures.entityMap.get(getClass())).getFrequency() > 0 && super.getCanSpawnHere());
  }

  public boolean getCanSpawnHereMob() {
    int i = MathHelper.floor(this.posX);
    int j = MathHelper.floor((getEntityBoundingBox()).minY);
    int k = MathHelper.floor(this.posZ);
    BlockPos pos = new BlockPos(i, j, k);
    if (this.world.getLightFor(EnumSkyBlock.SKY, pos) > this.rand.nextInt(32)) {
      return false;
    }
    int l = this.world.getLightFromNeighbors(pos);
    if (this.world.isThundering()) {
      int i1 = this.world.getSkylightSubtracted();
      this.world.setSkylightSubtracted(10);
      l = this.world.getLightFromNeighbors(pos);
      this.world.setSkylightSubtracted(i1);
    }
    return (l <= this.rand.nextInt(8));
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


  public boolean entitiesToIgnore(Entity entity) {
    return (!(entity instanceof EntityLiving) || entity instanceof EntityMob || entity instanceof drzhark.mocreatures.entity.item.MoCEntityEgg || (entity instanceof net.minecraft.entity.player.EntityPlayer &&
      getIsTamed()) || entity instanceof drzhark.mocreatures.entity.item.MoCEntityKittyBed || entity instanceof drzhark.mocreatures.entity.item.MoCEntityLitterBox || (

      getIsTamed() && entity instanceof MoCEntityAnimal && ((MoCEntityAnimal)entity).getIsTamed()) || (entity instanceof net.minecraft.entity.passive.EntityWolf && !MoCreatures.proxy.attackWolves) || (entity instanceof drzhark.mocreatures.entity.passive.MoCEntityHorse && !MoCreatures.proxy.attackHorses));
  }



  public boolean checkSpawningBiome() {
    return true;
  }


  public void onLivingUpdate() {
    if (!this.world.isRemote) {





      if (getIsTamed() && this.rand.nextInt(200) == 0) {
        MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageHealth(getEntityId(), getHealth()), new NetworkRegistry.TargetPoint(this.world.provider
              .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
      }

      if (isHarmedByDaylight() &&
        this.world.isDaytime()) {
        float var1 = getBrightness();
        if (var1 > 0.5F && this.world
          .canBlockSeeSky(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.posY),
              MathHelper.floor(this.posZ))) && this.rand.nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F) {
          setFire(8);
        }
      }

      if (getEdad() == 0) setEdad(getMaxEdad() - 10);
      if (!getIsAdult() && this.rand.nextInt(300) == 0) {
        setEdad(getEdad() + 1);
        if (getEdad() >= getMaxEdad()) {
          setAdult(true);
        }
      }

      if (getIsFlying() && getNavigator().noPath() && !isMovementCeased() && getAttackTarget() == null && this.rand.nextInt(20) == 0) {
        this.wander.makeUpdate();
      }
    }

    getNavigator().onUpdateNavigation();
    super.onLivingUpdate();
  }

  protected int getMaxEdad() {
    return 100;
  }

  protected boolean isHarmedByDaylight() {
    return false;
  }


  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    if (!this.world.isRemote && getIsTamed()) {
      MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageHealth(getEntityId(), getHealth()), new NetworkRegistry.TargetPoint(this.world.provider
            .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
    }
    return super.attackEntityFrom(damagesource, i);
  }






  public boolean isFlyer() {
    return false;
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



  public void fall(float f, float f1) {
    if (!isFlyer()) {
      super.fall(f, f1);
    }
  }


  public boolean isOnLadder() {
    if (isFlyer()) {
      return false;
    }
    return super.isOnLadder();
  }



  public void travel(float strafe, float vertical, float forward) {
    if (!isFlyer()) {
      super.travel(strafe, vertical, forward);
      return;
    }
    moveEntityWithHeadingFlyer(strafe, vertical, forward);
  }

  public void moveEntityWithHeadingFlyer(float strafe, float vertical, float forward) {
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






  public void performAnimation(int attackType) {}





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












  public void makeEntityJump() {}











  public void makeEntityDive() {
    this.divePending = true;
  }


  protected boolean canDespawn() {
    return !getIsTamed();
  }



  public void setDead() {
    if (!this.world.isRemote && getIsTamed() && getHealth() > 0.0F) {
      return;
    }
    super.setDead();
  }


  public float getSizeFactor() {
    return 1.0F;
  }


  public float getAdjustedYOffset() {
    return 0.0F;
  }














  public void setArmorType(int i) {}













  public int getArmorType() {
    return 0;
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


  public boolean canAttackTarget(EntityLivingBase entity) {
    return false;
  }


  public boolean getIsSitting() {
    return false;
  }


  public boolean isNotScared() {
    return true;
  }


  public boolean isMovementCeased() {
    return false;
  }


  public boolean shouldAttackPlayers() {
    return (this.world.getDifficulty() != EnumDifficulty.PEACEFUL);
  }


  public double getDivingDepth() {
    return 0.0D;
  }


  public boolean isDiving() {
    return false;
  }



  public void forceEntityJump() {}



  public boolean attackEntityAsMob(Entity entityIn) {
    boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this),
        (int)getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
    if (flag) {
      applyEnchantments((EntityLivingBase)this, entityIn);
    }

    return flag;
  }


  public int maxFlyingHeight() {
    return 5;
  }


  public int minFlyingHeight() {
    return 1;
  }


  public PathNavigate getNavigator() {
    if (isInWater() && isAmphibian()) {
      return this.navigatorWater;
    }
    if (isFlyer()) {
      return this.navigatorFlyer;
    }
    return this.navigator;
  }

  public boolean isAmphibian() {
    return false;
  }


  public boolean getIsFlying() {
    return isFlyer();
  }









  public boolean isCreatureType(EnumCreatureType type, boolean forSpawnCount) {
    if (type == EnumCreatureType.MONSTER) {
      return true;
    }
    return false;
  }



  public String getClazzString() {
    return EntityList.getEntityString((Entity)this);
  }


  public boolean getIsGhost() {
    return false;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\MoCEntityMob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
