package drzhark.mocreatures.entity.monster;

import drzhark.mocreatures.MoCEntityData;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.MoCEntityMob;
import drzhark.mocreatures.entity.ai.EntityAIFleeFromPlayer;
import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
import drzhark.mocreatures.entity.passive.MoCEntityPetScorpion;
import drzhark.mocreatures.init.MoCItems;
import drzhark.mocreatures.init.MoCSoundEvents;
import drzhark.mocreatures.network.MoCMessageHandler;
import drzhark.mocreatures.network.message.MoCMessageAnimation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MoCEntityScorpion extends MoCEntityMob {
  private boolean isPoisoning;
  private int poisontimer;
  private static final DataParameter<Boolean> IS_PICKED = EntityDataManager.createKey(MoCEntityScorpion.class, DataSerializers.BOOLEAN); public int mouthCounter; public int armCounter;
  private static final DataParameter<Boolean> HAS_BABIES = EntityDataManager.createKey(MoCEntityScorpion.class, DataSerializers.BOOLEAN);

  public MoCEntityScorpion(World world) {
    super(world);
    setSize(1.4F, 0.9F);
    this.poisontimer = 0;
    setAdult(true);
    setEdad(20);

    if (!this.world.isRemote) {
      if (this.rand.nextInt(4) == 0) {
        setHasBabies(true);
      } else {
        setHasBabies(false);
      }
    }
  }


  protected void initEntityAI() {
    this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.tasks.addTask(1, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
    this.tasks.addTask(2, (EntityAIBase)new EntityAIRestrictSun((EntityCreature)this));
    this.tasks.addTask(7, (EntityAIBase)new EntityAIFleeSun((EntityCreature)this, 1.0D));
    this.tasks.addTask(5, (EntityAIBase)new EntityAIFleeFromPlayer((EntityCreature)this, 1.2D, 4.0D));
    this.tasks.addTask(6, (EntityAIBase)new EntityAILeapAtTarget((EntityLiving)this, 0.4F));
    this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(18.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
  }


  public void selectType() {
    checkSpawningBiome();

    if (getType() == 0) {
      setType(1);
    }
  }


  public ResourceLocation getTexture() {
    switch (getType()) {
      case 1:
        return MoCreatures.proxy.getTexture("scorpiondirt.png");
      case 2:
        return MoCreatures.proxy.getTexture("scorpioncave.png");
      case 3:
        return MoCreatures.proxy.getTexture("scorpionnether.png");
      case 4:
        return MoCreatures.proxy.getTexture("scorpionfrost.png");
    }
    return MoCreatures.proxy.getTexture("scorpiondirt.png");
  }



  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(IS_PICKED, Boolean.valueOf(false));
    this.dataManager.register(HAS_BABIES, Boolean.valueOf(false));
  }

  public boolean getHasBabies() {
    return ((Boolean)this.dataManager.get(HAS_BABIES)).booleanValue();
  }

  public boolean getIsPicked() {
    return ((Boolean)this.dataManager.get(IS_PICKED)).booleanValue();
  }

  public boolean getIsPoisoning() {
    return this.isPoisoning;
  }

  public void setHasBabies(boolean flag) {
    this.dataManager.set(HAS_BABIES, Boolean.valueOf(flag));
  }

  public void setPicked(boolean flag) {
    this.dataManager.set(IS_PICKED, Boolean.valueOf(flag));
  }

  public void setPoisoning(boolean flag) {
    if (flag && !this.world.isRemote) {
      MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 0), new NetworkRegistry.TargetPoint(this.world.provider
            .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
    }
    this.isPoisoning = flag;
  }


  public void performAnimation(int animationType) {
    if (animationType == 0) {

      setPoisoning(true);
    } else if (animationType == 1) {

      this.armCounter = 1;
    }
    else if (animationType == 3) {

      this.mouthCounter = 1;
    }
  }


  public float getMoveSpeed() {
    return 0.8F;
  }


  public boolean isOnLadder() {
    return this.collidedHorizontally;
  }

  public boolean climbing() {
    return (!this.onGround && isOnLadder());
  }



  public void onLivingUpdate() {
    if (!this.onGround && getRidingEntity() != null) {
      this.rotationYaw = (getRidingEntity()).rotationYaw;
    }

    if (this.mouthCounter != 0 && this.mouthCounter++ > 50) {
      this.mouthCounter = 0;
    }

    if (!this.world.isRemote && (this.armCounter == 10 || this.armCounter == 40)) {
      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_SCORPION_CLAW);
    }

    if (this.armCounter != 0 && this.armCounter++ > 24) {
      this.armCounter = 0;
    }

    if (!this.world.isRemote && !isBeingRidden() && getIsAdult() && !getHasBabies() && this.rand.nextInt(100) == 0) {
      MoCTools.findMobRider((Entity)this);
    }














    if (getIsPoisoning()) {
      this.poisontimer++;
      if (this.poisontimer == 1) {
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_SCORPION_STING);
      }
      if (this.poisontimer > 50) {
        this.poisontimer = 0;
        setPoisoning(false);
      }
    }
    super.onLivingUpdate();
  }


  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    if (super.attackEntityFrom(damagesource, i)) {
      Entity entity = damagesource.getTrueSource();

      if (entity != null && entity != this && entity instanceof EntityLivingBase && shouldAttackPlayers() && getIsAdult()) {
        setAttackTarget((EntityLivingBase)entity);
      }
      return true;
    }
    return false;
  }



  public boolean entitiesToIgnore(Entity entity) {
    return (super.entitiesToIgnore(entity) || (getIsTamed() && entity instanceof MoCEntityScorpion && ((MoCEntityScorpion)entity)
      .getIsTamed()));
  }


  protected void applyEnchantments(EntityLivingBase entityLivingBaseIn, Entity entityIn) {
    boolean flag = entityIn instanceof EntityPlayer;
    if (!getIsPoisoning() && this.rand.nextInt(5) == 0 && entityIn instanceof EntityLivingBase) {
      setPoisoning(true);
      if (getType() <= 2) {

        if (flag) {
          MoCreatures.poisonPlayer((EntityPlayer)entityIn);
        }
        ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.POISON, 70, 0));
      } else if (getType() == 4) {

        if (flag) {
          MoCreatures.freezePlayer((EntityPlayer)entityIn);
        }
        ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 70, 0));
      }
      else if (getType() == 3) {

        if (!this.world.isRemote && flag && !this.world.provider.doesWaterVaporize()) {
          MoCreatures.burnPlayer((EntityPlayer)entityIn);
          ((EntityLivingBase)entityIn).setFire(15);
        }
      }
    } else {
      swingArm();
    }
    super.applyEnchantments(entityLivingBaseIn, entityIn);
  }

  public void swingArm() {
    if (!this.world.isRemote) {
      MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 1), new NetworkRegistry.TargetPoint(this.world.provider
            .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
    }
  }


  public void onUpdate() {
    super.onUpdate();
  }

  public boolean swingingTail() {
    return (getIsPoisoning() && this.poisontimer < 15);
  }


  public void onDeath(DamageSource damagesource) {
    super.onDeath(damagesource);
    if (!this.world.isRemote && getIsAdult() && getHasBabies()) {
      int k = this.rand.nextInt(5);
      for (int i = 0; i < k; i++) {
        MoCEntityPetScorpion entityscorpy = new MoCEntityPetScorpion(this.world);
        entityscorpy.setPosition(this.posX, this.posY, this.posZ);
        entityscorpy.setAdult(false);
        entityscorpy.setEdad(20);
        entityscorpy.setType(getType());
        this.world.spawnEntity((Entity)entityscorpy);
        MoCTools.playCustomSound((Entity)entityscorpy, SoundEvents.ENTITY_CHICKEN_EGG);
      }
    }
  }


  protected SoundEvent getDeathSound() {
    return MoCSoundEvents.ENTITY_SCORPION_DEATH;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    return MoCSoundEvents.ENTITY_SCORPION_HURT;
  }


  protected SoundEvent getAmbientSound() {
    if (!this.world.isRemote) {
      MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 3), new NetworkRegistry.TargetPoint(this.world.provider
            .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
    }
    return MoCSoundEvents.ENTITY_SCORPION_AMBIENT;
  }


  protected Item getDropItem() {
    if (!getIsAdult()) {
      return Items.STRING;
    }

    boolean flag = (this.rand.nextInt(100) < MoCreatures.proxy.rareItemDropChance);

    switch (getType()) {
      case 1:
        if (flag) {
          return (Item)MoCItems.scorpStingDirt;
        }
        return (Item)MoCItems.chitin;
      case 2:
        if (flag) {
          return (Item)MoCItems.scorpStingCave;
        }
        return (Item)MoCItems.chitinCave;
      case 3:
        if (flag) {
          return (Item)MoCItems.scorpStingNether;
        }
        return (Item)MoCItems.chitinNether;
      case 4:
        if (flag) {
          return (Item)MoCItems.scorpStingFrost;
        }
        return (Item)MoCItems.chitinFrost;
    }
    return Items.STRING;
  }



  protected void dropFewItems(boolean flag, int x) {
    if (!flag) {
      return;
    }
    Item item = getDropItem();

    if (item != null &&
      this.rand.nextInt(3) == 0) {
      dropItem(item, 1);
    }
  }




  public boolean getCanSpawnHere() {
    return (isValidLightLevel() && ((MoCEntityData)MoCreatures.entityMap.get(getClass())).getFrequency() > 0 && getCanSpawnHereLiving() &&
      getCanSpawnHereCreature());
  }


  public boolean checkSpawningBiome() {
    if (this.world.provider.doesWaterVaporize()) {
      setType(3);
      this.isImmuneToFire = true;
      return true;
    }

    int i = MathHelper.floor(this.posX);
    int j = MathHelper.floor((getEntityBoundingBox()).minY);
    int k = MathHelper.floor(this.posZ);
    BlockPos pos = new BlockPos(i, j, k);

    Biome currentbiome = MoCTools.Biomekind(this.world, pos);

    if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.SNOWY)) {
      setType(4);
    } else if (!this.world.canBlockSeeSky(pos) && this.posY < 50.0D) {
      setType(2);
      return true;
    }

    return true;
  }


  public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
    super.readEntityFromNBT(nbttagcompound);
    setHasBabies(nbttagcompound.getBoolean("Babies"));
  }


  public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
    super.writeEntityToNBT(nbttagcompound);
    nbttagcompound.setBoolean("Babies", getHasBabies());
  }


  public int getTalkInterval() {
    return 300;
  }





  public EnumCreatureAttribute getCreatureAttribute() {
    return EnumCreatureAttribute.ARTHROPOD;
  }


  public float getAdjustedYOffset() {
    return 30.0F;
  }


  protected int getMaxEdad() {
    return 120;
  }


  public boolean isNotScared() {
    return (getIsAdult() || getEdad() > 70);
  }


  public double getMountedYOffset() {
    return this.height * 0.75D - 0.15D;
  }


  public void updatePassenger(Entity passenger) {
    double dist = 0.2D;
    double newPosX = this.posX + dist * Math.sin((this.renderYawOffset / 57.29578F));
    double newPosZ = this.posZ - dist * Math.cos((this.renderYawOffset / 57.29578F));
    passenger.setPosition(newPosX, this.posY + getMountedYOffset() + passenger.getYOffset(), newPosZ);
    passenger.rotationYaw = this.rotationYaw;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\monster\MoCEntityScorpion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
