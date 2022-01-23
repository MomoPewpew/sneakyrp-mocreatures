package drzhark.mocreatures.entity.passive;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.IMoCTameable;
import drzhark.mocreatures.entity.MoCEntityAnimal;
import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
import drzhark.mocreatures.entity.ai.EntityAIFollowAdult;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.init.MoCItems;
import drzhark.mocreatures.init.MoCSoundEvents;
import drzhark.mocreatures.inventory.MoCAnimalChest;
import drzhark.mocreatures.item.MoCItemRecord;
import drzhark.mocreatures.network.MoCMessageHandler;
import drzhark.mocreatures.network.message.MoCMessageAnimation;
import drzhark.mocreatures.network.message.MoCMessageHeart;
import drzhark.mocreatures.network.message.MoCMessageVanish;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockJukebox;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MoCEntityHorse extends MoCEntityTameableAnimal {
  private int gestationtime;
  private int countEating;
  private int textCounter;
  private float transFloat = 0.2F;

  private int fCounter;

  public int shuffleCounter;

  public int wingFlapCounter;

  public MoCAnimalChest localchest;

  public boolean eatenpumpkin;

  private boolean hasReproduced;
  private int nightmareInt;
  public ItemStack localstack;
  private static final DataParameter<Boolean> RIDEABLE = EntityDataManager.createKey(MoCEntityHorse.class, DataSerializers.BOOLEAN); public int mouthCounter; public int standCounter; public int tailCounter; public int vanishCounter; public int sprintCounter; public int transformType; public int transformCounter; protected EntityAIWanderMoC2 wander;
  private static final DataParameter<Boolean> CHESTED = EntityDataManager.createKey(MoCEntityHorse.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Boolean> SITTING = EntityDataManager.createKey(MoCEntityHorse.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Boolean> BRED = EntityDataManager.createKey(MoCEntityHorse.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Integer> ARMOR_TYPE = EntityDataManager.createKey(MoCEntityHorse.class, DataSerializers.VARINT);

  public MoCEntityHorse(World world) {
    super(world);
    setSize(1.4F, 1.6F);
    this.gestationtime = 0;
    this.eatenpumpkin = false;
    this.nightmareInt = 0;
    this.isImmuneToFire = false;
    setEdad(50);
    setIsChested(false);
    this.stepHeight = 1.0F;

    if (!this.world.isRemote) {
      if (this.rand.nextInt(5) == 0) {
        setAdult(false);
      } else {
        setAdult(true);
      }
    }
  }


  protected void initEntityAI() {
    this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.tasks.addTask(3, (EntityAIBase)new EntityAIFollowAdult((EntityLiving)this, 1.0D));
    this.tasks.addTask(4, (EntityAIBase)(this.wander = new EntityAIWanderMoC2((EntityCreature)this, 1.0D, 80)));
    this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
  }


  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(RIDEABLE, Boolean.valueOf(false));
    this.dataManager.register(SITTING, Boolean.valueOf(false));
    this.dataManager.register(CHESTED, Boolean.valueOf(false));
    this.dataManager.register(BRED, Boolean.valueOf(false));
    this.dataManager.register(ARMOR_TYPE, Integer.valueOf(0));
  }


  public int getArmorType() {
    return ((Integer)this.dataManager.get(ARMOR_TYPE)).intValue();
  }

  public boolean getIsChested() {
    return ((Boolean)this.dataManager.get(CHESTED)).booleanValue();
  }


  public boolean getIsSitting() {
    return ((Boolean)this.dataManager.get(SITTING)).booleanValue();
  }

  public boolean getHasBred() {
    return ((Boolean)this.dataManager.get(BRED)).booleanValue();
  }

  public void setBred(boolean flag) {
    this.dataManager.set(BRED, Boolean.valueOf(flag));
  }


  public boolean getIsRideable() {
    return ((Boolean)this.dataManager.get(RIDEABLE)).booleanValue();
  }

  public void setRideable(boolean flag) {
    this.dataManager.set(RIDEABLE, Boolean.valueOf(flag));
  }


  public void setArmorType(int i) {
    this.dataManager.set(ARMOR_TYPE, Integer.valueOf(i));
  }

  public void setIsChested(boolean flag) {
    this.dataManager.set(CHESTED, Boolean.valueOf(flag));
  }

  public void setSitting(boolean flag) {
    this.dataManager.set(SITTING, Boolean.valueOf(flag));
  }


  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    Entity entity = damagesource.getTrueSource();
    if (isBeingRidden() && entity == getRidingEntity()) {
      return false;
    }
    if (entity instanceof net.minecraft.entity.passive.EntityWolf) {
      EntityCreature entitycreature = (EntityCreature)entity;
      entitycreature.setAttackTarget(null);
      return false;
    }
    i -= (getArmorType() + 2);
    if (i < 0.0F) {
      i = 0.0F;
    }
    return super.attackEntityFrom(damagesource, i);
  }



  public boolean canBeCollidedWith() {
    return !isBeingRidden();
  }


  public boolean checkSpawningBiome() {
    BlockPos pos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor((getEntityBoundingBox()).minY), this.posZ);

    Biome currentbiome = MoCTools.Biomekind(this.world, pos);
    String s = MoCTools.biomeName(this.world, pos);
    try {
      if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.PLAINS) &&
        this.rand.nextInt(3) == 0) {
        setType(60);
      }

      if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.SANDY)) {
        setType(60);
      }

      if (s.toLowerCase().contains("prairie"))
      {
        setType(this.rand.nextInt(5) + 1);
      }
    } catch (Exception exception) {}

    return true;
  }










  public float colorFX(int sColor, int typeInt) {
    if (typeInt == 48) {

      if (sColor == 1) {
        return 0.69921875F;
      }
      if (sColor == 2) {
        return 0.625F;
      }
      return 0.0859375F;
    }

    if (typeInt == 49) {

      if (sColor == 1) {
        return 0.57421875F;
      }
      if (sColor == 2) {
        return 0.3515625F;
      }
      return 0.76171875F;
    }

    if (typeInt == 51) {

      if (sColor == 1) {
        return 0.1171875F;
      }
      if (sColor == 2) {
        return 0.5625F;
      }
      return 0.99609375F;
    }
    if (typeInt == 52) {

      if (sColor == 1) {
        return 0.99609375F;
      }
      if (sColor == 2) {
        return 0.41015625F;
      }
      return 0.703125F;
    }

    if (typeInt == 53) {

      if (sColor == 1) {
        return 0.734375F;
      }
      if (sColor == 2) {
        return 0.9296875F;
      }
      return 0.40625F;
    }

    if (typeInt == 54) {

      if (sColor == 1) {
        return 0.4296875F;
      }
      if (sColor == 2) {
        return 0.48046875F;
      }
      return 0.54296875F;
    }

    if (typeInt == 55) {

      if (sColor == 1) {
        return 0.7578125F;
      }
      if (sColor == 2) {
        return 0.11328125F;
      }
      return 0.1328125F;
    }

    if (typeInt == 56) {

      if (sColor == 1) {
        return 0.24609375F;
      }
      if (sColor == 2) {
        return 0.17578125F;
      }
      return 0.99609375F;
    }

    if (typeInt == 57) {

      if (sColor == 1) {
        return 0.26953125F;
      }
      if (sColor == 2) {
        return 0.5703125F;
      }
      return 0.56640625F;
    }

    if (typeInt == 58) {

      if (sColor == 1) {
        return 0.3515625F;
      }
      if (sColor == 2) {
        return 0.53125F;
      }
      return 0.16796875F;
    }

    if (typeInt == 59) {

      if (sColor == 1) {
        return 0.8515625F;
      }
      if (sColor == 2) {
        return 0.15625F;
      }
      return 0.0F;
    }

    if (typeInt > 22 && typeInt < 26) {

      if (sColor == 1) {
        return 0.234375F;
      }
      if (sColor == 2) {
        return 0.69921875F;
      }
      return 0.4375F;
    }

    if (typeInt == 40) {

      if (sColor == 1) {
        return 0.54296875F;
      }
      if (sColor == 2) {
        return 0.0F;
      }
      return 0.0F;
    }



    if (sColor == 1) {
      return 0.99609375F;
    }
    if (sColor == 2) {
      return 0.921875F;
    }
    return 0.54296875F;
  }




  public void dissapearHorse() {
    this.isDead = true;
  }

  private void drinkingHorse() {
    openMouth();
    MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_DRINKING);
  }





  public void dropArmor() {
    if (!this.world.isRemote) {
      int i = getArmorType();
      if (i != 0) {
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_OFF);
      }

      if (i == 1) {
        EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(Items.IRON_HORSE_ARMOR, 1));
        entityitem.setPickupDelay(10);
        this.world.spawnEntity((Entity)entityitem);
      }
      if (i == 2) {
        EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(Items.GOLDEN_HORSE_ARMOR, 1));
        entityitem.setPickupDelay(10);
        this.world.spawnEntity((Entity)entityitem);
      }
      if (i == 3) {
        EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(Items.DIAMOND_HORSE_ARMOR, 1));
        entityitem.setPickupDelay(10);
        this.world.spawnEntity((Entity)entityitem);
      }
      if (i == 4) {
        EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack((Item)MoCItems.horsearmorcrystal, 1));

        entityitem.setPickupDelay(10);
        this.world.spawnEntity((Entity)entityitem);
      }
      setArmorType(0);
    }
  }




  public void dropBags() {
    if (!isBagger() || !getIsChested() || this.world.isRemote) {
      return;
    }

    EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack((Block)Blocks.CHEST, 1));
    float f3 = 0.05F;
    entityitem.motionX = ((float)this.world.rand.nextGaussian() * f3);
    entityitem.motionY = ((float)this.world.rand.nextGaussian() * f3 + 0.2F);
    entityitem.motionZ = ((float)this.world.rand.nextGaussian() * f3);
    this.world.spawnEntity((Entity)entityitem);
    setIsChested(false);
  }

  private void eatingHorse() {
    openMouth();
    MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_EATING);
  }


  public void fall(float f, float f1) {
    if (isFlyer() || isFloater()) {
      return;
    }

    float i = (float)(Math.ceil((f - 3.0F)) / 2.0D);
    if (!this.world.isRemote && i > 0.0F) {
      if (getType() >= 10) {
        i /= 2.0F;
      }
      if (i > 1.0F) {
        attackEntityFrom(DamageSource.FALL, i);
      }
      if (isBeingRidden() && i > 1.0F) {
        for (Entity entity : getRecursivePassengers())
        {
          entity.attackEntityFrom(DamageSource.FALL, i);
        }
      }
      IBlockState iblockstate = this.world.getBlockState(new BlockPos(this.posX, this.posY - 0.2D - this.prevRotationYaw, this.posZ));
      Block block = iblockstate.getBlock();

      if (iblockstate.getMaterial() != Material.AIR && !isSilent()) {

        SoundType soundtype = block.getSoundType(iblockstate, this.world, new BlockPos(this.posX, this.posY - 0.2D - this.prevRotationYaw, this.posZ), (Entity)this);
        this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, soundtype.getStepSound(), getSoundCategory(), soundtype.getVolume() * 0.5F, soundtype.getPitch() * 0.75F);
      }
    }
  }

  public int getInventorySize() {
    if (getType() == 40)
      return 18;
    if (getType() > 64) {
      return 27;
    }
    return 9;
  }


  public double getCustomJump() {
    double HorseJump = 0.35D;
    if (getType() < 6) {

      HorseJump = 0.35D;
    } else if (getType() > 5 && getType() < 11) {

      HorseJump = 0.4D;
    } else if (getType() > 10 && getType() < 16) {

      HorseJump = 0.45D;
    } else if (getType() > 15 && getType() < 21) {

      HorseJump = 0.5D;

    }
    else if (getType() > 20 && getType() < 26) {

      HorseJump = 0.45D;
    } else if (getType() > 25 && getType() < 30) {

      HorseJump = 0.5D;
    } else if (getType() >= 30 && getType() < 40) {

      HorseJump = 0.55D;
    } else if (getType() >= 40 && getType() < 60) {

      HorseJump = 0.6D;
    } else if (getType() >= 60) {

      HorseJump = 0.4D;
    }
    return HorseJump;
  }


  public double getCustomSpeed() {
    double HorseSpeed = 0.8D;
    if (getType() < 6) {

      HorseSpeed = 0.9D;
    } else if (getType() > 5 && getType() < 11) {

      HorseSpeed = 1.0D;
    } else if (getType() > 10 && getType() < 16) {

      HorseSpeed = 1.1D;
    } else if (getType() > 15 && getType() < 21) {

      HorseSpeed = 1.2D;

    }
    else if (getType() > 20 && getType() < 26) {

      HorseSpeed = 0.8D;
    } else if (getType() > 25 && getType() < 30) {

      HorseSpeed = 1.0D;
    } else if (getType() > 30 && getType() < 40) {

      HorseSpeed = 1.2D;
    } else if (getType() >= 40 && getType() < 60) {


      HorseSpeed = 1.3D;
    } else if (getType() == 60 || getType() == 61) {

      HorseSpeed = 1.1D;
    } else if (getType() == 65) {

      HorseSpeed = 0.7D;
    } else if (getType() > 65) {

      HorseSpeed = 0.9D;
    }
    if (this.sprintCounter > 0 && this.sprintCounter < 150) {
      HorseSpeed *= 1.5D;
    }
    if (this.sprintCounter > 150) {
      HorseSpeed *= 0.5D;
    }
    return HorseSpeed;
  }


  protected SoundEvent getDeathSound() {
    openMouth();
    if (isUndead()) {
      return MoCSoundEvents.ENTITY_HORSE_DEATH_UNDEAD;
    }
    if (getIsGhost()) {
      return MoCSoundEvents.ENTITY_HORSE_DEATH_GHOST;
    }
    if (getType() == 60 || getType() == 61) {
      return MoCSoundEvents.ENTITY_HORSE_HURT_ZEBRA;
    }
    if (getType() >= 65 && getType() <= 67) {
      return MoCSoundEvents.ENTITY_HORSE_DEATH_DONKEY;
    }
    return MoCSoundEvents.ENTITY_HORSE_DEATH;
  }


  public boolean renderName() {
    if (getIsGhost() && getEdad() < 10) {
      return false;
    }

    return super.renderName();
  }


  protected Item getDropItem() {
    boolean flag = (this.rand.nextInt(100) < MoCreatures.proxy.rareItemDropChance);

    if (flag && (getType() == 36 || (getType() >= 50 && getType() < 60)))
    {
      return (Item)MoCItems.unicornhorn;
    }
    if (getType() == 39)
    {
      return Items.FEATHER;
    }
    if (getType() == 40)
    {
      return Items.FEATHER;
    }
    if (getType() == 38 && flag && this.world.provider.doesWaterVaporize())
    {
      return (Item)MoCItems.heartfire;
    }
    if (getType() == 32 && flag)
    {
      return (Item)MoCItems.heartdarkness;
    }
    if (getType() == 26)
    {
      return Items.BONE;
    }
    if (getType() == 23 || getType() == 24 || getType() == 25) {
      if (flag) {
        return (Item)MoCItems.heartundead;
      }
      return Items.ROTTEN_FLESH;
    }
    if (getType() == 21 || getType() == 22) {
      return Items.GHAST_TEAR;
    }

    return Items.LEATHER;
  }

  public boolean getHasReproduced() {
    return this.hasReproduced;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    openMouth();
    if (isFlyer() && !isBeingRidden()) {
      wingFlap();
    }
    else if (this.rand.nextInt(3) == 0) {
      stand();
    }

    if (isUndead()) {
      return MoCSoundEvents.ENTITY_HORSE_HURT_UNDEAD;
    }
    if (getIsGhost()) {
      return MoCSoundEvents.ENTITY_HORSE_HURT_GHOST;
    }
    if (getType() == 60 || getType() == 61) {
      return MoCSoundEvents.ENTITY_HORSE_HURT_ZEBRA;
    }
    if (getType() >= 65 && getType() <= 67) {
      return MoCSoundEvents.ENTITY_HORSE_HURT_DONKEY;
    }
    return MoCSoundEvents.ENTITY_HORSE_HURT;
  }


  protected SoundEvent getAmbientSound() {
    openMouth();
    if (this.rand.nextInt(10) == 0 && !isMovementCeased()) {
      stand();
    }
    if (isUndead()) {
      return MoCSoundEvents.ENTITY_HORSE_AMBIENT_UNDEAD;
    }
    if (getIsGhost()) {
      return MoCSoundEvents.ENTITY_HORSE_AMBIENT_GHOST;
    }
    if (getType() == 60 || getType() == 61) {
      return MoCSoundEvents.ENTITY_HORSE_AMBIENT_ZEBRA;
    }
    if (getType() >= 65 && getType() <= 67) {
      return MoCSoundEvents.ENTITY_HORSE_HURT_DONKEY;
    }
    return MoCSoundEvents.ENTITY_HORSE_AMBIENT;
  }





  protected SoundEvent getAngrySound() {
    openMouth();
    stand();
    if (isUndead()) {
      return MoCSoundEvents.ENTITY_HORSE_ANGRY_UNDEAD;
    }
    if (getIsGhost()) {
      return MoCSoundEvents.ENTITY_HORSE_ANGRY_GHOST;
    }
    if (getType() == 60 || getType() == 61) {
      return MoCSoundEvents.ENTITY_HORSE_HURT_ZEBRA;
    }
    if (getType() >= 65 && getType() <= 67) {
      return MoCSoundEvents.ENTITY_HORSE_HURT_DONKEY;
    }
    return MoCSoundEvents.ENTITY_HORSE_MAD;
  }

  public float calculateMaxHealth() {
    int maximumHealth = 30;
    if (getType() < 6) {

      maximumHealth = 25;
    } else if (getType() > 5 && getType() < 11) {

      maximumHealth = 30;
    } else if (getType() > 10 && getType() < 16) {

      maximumHealth = 35;
    } else if (getType() > 15 && getType() < 21) {

      maximumHealth = 40;

    }
    else if (getType() > 20 && getType() < 26) {

      maximumHealth = 35;
    } else if (getType() > 25 && getType() < 30) {

      maximumHealth = 35;
    } else if (getType() >= 30 && getType() < 40) {

      maximumHealth = 50;
    } else if (getType() == 40) {

      maximumHealth = 50;
    } else if (getType() > 40 && getType() < 60) {

      maximumHealth = 40;
    } else if (getType() >= 60) {

      maximumHealth = 30;
    }

    return maximumHealth;
  }







  public int getMaxTemper() {
    if (getType() == 60) {
      return 200;
    }
    return 100;
  }

  public int getNightmareInt() {
    return this.nightmareInt;
  }


  protected float getSoundVolume() {
    return 0.8F;
  }


  public int getTalkInterval() {
    return 400;
  }






  public ResourceLocation getTexture() {
    String tempTexture;
    switch (getType()) {
      case 1:
        tempTexture = "horsewhite.png";
        break;
      case 2:
        tempTexture = "horsecreamy.png";
        break;
      case 3:
        tempTexture = "horsebrown.png";
        break;
      case 4:
        tempTexture = "horsedarkbrown.png";
        break;
      case 5:
        tempTexture = "horseblack.png";
        break;
      case 6:
        tempTexture = "horsebrightcreamy.png";
        break;
      case 7:
        tempTexture = "horsespeckled.png";
        break;
      case 8:
        tempTexture = "horsepalebrown.png";
        break;
      case 9:
        tempTexture = "horsegrey.png";
        break;
      case 11:
        tempTexture = "horsepinto.png";
        break;
      case 12:
        tempTexture = "horsebrightpinto.png";
        break;
      case 13:
        tempTexture = "horsepalespeckles.png";
        break;
      case 16:
        tempTexture = "horsespotted.png";
        break;
      case 17:
        tempTexture = "horsecow.png";
        break;

      case 21:
        tempTexture = "horseghost.png";
        break;
      case 22:
        tempTexture = "horseghostb.png";
        break;
      case 23:
        tempTexture = "horseundead.png";
        break;
      case 24:
        tempTexture = "horseundeadunicorn.png";
        break;
      case 25:
        tempTexture = "horseundeadpegasus.png";
        break;
      case 26:
        tempTexture = "horseskeleton.png";
        break;
      case 27:
        tempTexture = "horseunicornskeleton.png";
        break;
      case 28:
        tempTexture = "horsepegasusskeleton.png";
        break;
      case 30:
        tempTexture = "horsebug.png";
        break;
      case 32:
        tempTexture = "horsebat.png";
        break;
      case 36:
        tempTexture = "horseunicorn.png";
        break;

      case 38:
        tempTexture = "horsenightmare.png";
        break;
      case 39:
        tempTexture = "horsepegasus.png";
        break;

      case 40:
        tempTexture = "horsedarkpegasus.png";
        break;






      case 48:
        tempTexture = "horsefairyyellow.png";
        break;
      case 49:
        tempTexture = "horsefairypurple.png";
        break;
      case 50:
        tempTexture = "horsefairywhite.png";
        break;
      case 51:
        tempTexture = "horsefairyblue.png";
        break;
      case 52:
        tempTexture = "horsefairypink.png";
        break;
      case 53:
        tempTexture = "horsefairylightgreen.png";
        break;
      case 54:
        tempTexture = "horsefairyblack.png";
        break;
      case 55:
        tempTexture = "horsefairyred.png";
        break;
      case 56:
        tempTexture = "horsefairydarkblue.png";
        break;
      case 57:
        tempTexture = "horsefairycyan.png";
        break;
      case 58:
        tempTexture = "horsefairygreen.png";
        break;
      case 59:
        tempTexture = "horsefairyorange.png";
        break;

      case 60:
        tempTexture = "horsezebra.png";
        break;
      case 61:
        tempTexture = "horsezorse.png";
        break;
      case 65:
        tempTexture = "horsedonkey.png";
        break;
      case 66:
        tempTexture = "horsemule.png";
        break;
      case 67:
        tempTexture = "horsezonky.png";
        break;

      default:
        tempTexture = "horsebug.png";
        break;
    }
    if ((isArmored() || isMagicHorse()) && getArmorType() > 0) {
      String armorTex = "";
      if (getArmorType() == 1) {
        armorTex = "metal.png";
      }
      if (getArmorType() == 2) {
        armorTex = "gold.png";
      }
      if (getArmorType() == 3) {
        armorTex = "diamond.png";
      }
      if (getArmorType() == 4) {
        armorTex = "crystaline.png";
      }
      return MoCreatures.proxy.getTexture(tempTexture.replace(".png", armorTex));
    }

    if (isUndead() && getType() < 26) {
      String baseTex = "horseundead";
      int max = 79;
      if (getType() == 25)
      {
        baseTex = "horseundeadpegasus";
      }


      if (getType() == 24) {

        baseTex = "horseundeadunicorn";
        max = 69;
      }

      String iteratorTex = "1";
      if (MoCreatures.proxy.getAnimateTextures()) {
        if (this.rand.nextInt(3) == 0) {
          this.textCounter++;
        }
        if (this.textCounter < 10) {
          this.textCounter = 10;
        }
        if (this.textCounter > max) {
          this.textCounter = 10;
        }
        iteratorTex = "" + this.textCounter;
        iteratorTex = iteratorTex.substring(0, 1);
      }

      String decayTex = "" + (getEdad() / 100);
      decayTex = decayTex.substring(0, 1);
      return MoCreatures.proxy.getTexture(baseTex + decayTex + iteratorTex + ".png");
    }


    if (!MoCreatures.proxy.getAnimateTextures()) {
      return MoCreatures.proxy.getTexture(tempTexture);
    }

    if (isNightmare()) {
      if (this.rand.nextInt(1) == 0) {
        this.textCounter++;
      }
      if (this.textCounter < 10) {
        this.textCounter = 10;
      }
      if (this.textCounter > 59) {
        this.textCounter = 10;
      }
      String NTA = "horsenightmare";
      String NTB = "" + this.textCounter;
      NTB = NTB.substring(0, 1);
      String NTC = ".png";

      return MoCreatures.proxy.getTexture(NTA + NTB + NTC);
    }

    if (this.transformCounter != 0 && this.transformType != 0) {
      String newText = "horseundead.png";
      if (this.transformType == 23) {
        newText = "horseundead.png";
      }
      if (this.transformType == 24) {
        newText = "horseundeadunicorn.png";
      }
      if (this.transformType == 25) {
        newText = "horseundeadpegasus.png";
      }
      if (this.transformType == 36) {
        newText = "horseunicorn.png";
      }
      if (this.transformType == 39) {
        newText = "horsepegasus.png";
      }
      if (this.transformType == 40) {
        newText = "horseblackpegasus.png";
      }

      if (this.transformType == 48) {
        newText = "horsefairyyellow.png";
      }
      if (this.transformType == 49) {
        newText = "horsefairypurple.png";
      }
      if (this.transformType == 50) {
        newText = "horsefairywhite.png";
      }
      if (this.transformType == 51) {
        newText = "horsefairyblue.png";
      }
      if (this.transformType == 52) {
        newText = "horsefairypink.png";
      }
      if (this.transformType == 53) {
        newText = "horsefairylightgreen.png";
      }
      if (this.transformType == 54) {
        newText = "horsefairyblack.png";
      }
      if (this.transformType == 55) {
        newText = "horsefairyred.png";
      }
      if (this.transformType == 56) {
        newText = "horsefairydarkblue.png";
      }

      if (this.transformType == 57) {
        newText = "horsefairycyan.png";
      }

      if (this.transformType == 58) {
        newText = "horsefairygreen.png";
      }

      if (this.transformType == 59) {
        newText = "horsefairyorange.png";
      }

      if (this.transformType == 32) {
        newText = "horsebat.png";
      }
      if (this.transformType == 38) {
        newText = "horsenightmare1.png";
      }
      if (this.transformCounter % 5 == 0) {
        return MoCreatures.proxy.getTexture(newText);
      }
      if (this.transformCounter > 50 && this.transformCounter % 3 == 0) {
        return MoCreatures.proxy.getTexture(newText);
      }

      if (this.transformCounter > 75 && this.transformCounter % 4 == 0) {
        return MoCreatures.proxy.getTexture(newText);
      }
    }

    return MoCreatures.proxy.getTexture(tempTexture);
  }







  public byte getVanishC() {
    return (byte)this.vanishCounter;
  }









  private int HorseGenetics(int typeA, int typeB) {
    boolean flag = MoCreatures.proxy.easyBreeding;




    if (typeA == typeB) {
      return typeA;
    }


    if ((typeA == 60 && typeB < 21) || (typeB == 60 && typeA < 21)) {
      return 61;
    }


    if ((typeA == 65 && typeB < 21) || (typeB == 65 && typeA < 21)) {
      return 66;
    }


    if ((typeA == 60 && typeB == 65) || (typeB == 60 && typeA == 65)) {
      return 67;
    }

    if ((typeA > 20 && typeB < 21) || (typeB > 20 && typeA < 21)) {

      if (typeA < typeB) {
        return typeA;
      }
      return typeB;
    }


    if ((typeA == 36 && typeB == 39) || (typeB == 36 && typeA == 39)) {
      return 50;
    }


    if ((typeA == 36 && typeB == 40) || (typeB == 36 && typeA == 40)) {
      return 54;
    }


    if (typeA > 20 && typeB > 20 && typeA != typeB) {
      return this.rand.nextInt(5) + 1;
    }


    int chanceInt = this.rand.nextInt(4) + 1;
    if (!flag) {
      if (chanceInt == 1)
      {
        return typeA; }
      if (chanceInt == 2)
      {
        return typeB;
      }
    }

    if ((typeA == 1 && typeB == 2) || (typeA == 2 && typeB == 1)) {
      return 6;
    }

    if ((typeA == 1 && typeB == 3) || (typeA == 3 && typeB == 1)) {
      return 2;
    }

    if ((typeA == 1 && typeB == 4) || (typeA == 4 && typeB == 1)) {
      return 7;
    }

    if ((typeA == 1 && typeB == 5) || (typeA == 5 && typeB == 1)) {
      return 9;
    }

    if ((typeA == 1 && typeB == 7) || (typeA == 7 && typeB == 1)) {
      return 12;
    }

    if ((typeA == 1 && typeB == 8) || (typeA == 8 && typeB == 1)) {
      return 7;
    }

    if ((typeA == 1 && typeB == 9) || (typeA == 9 && typeB == 1)) {
      return 13;
    }

    if ((typeA == 1 && typeB == 11) || (typeA == 11 && typeB == 1)) {
      return 12;
    }

    if ((typeA == 1 && typeB == 12) || (typeA == 12 && typeB == 1)) {
      return 13;
    }

    if ((typeA == 1 && typeB == 17) || (typeA == 17 && typeB == 1)) {
      return 16;
    }

    if ((typeA == 2 && typeB == 4) || (typeA == 4 && typeB == 2)) {
      return 3;
    }

    if ((typeA == 2 && typeB == 5) || (typeA == 5 && typeB == 2)) {
      return 4;
    }

    if ((typeA == 2 && typeB == 7) || (typeA == 7 && typeB == 2)) {
      return 8;
    }

    if ((typeA == 2 && typeB == 8) || (typeA == 8 && typeB == 2)) {
      return 3;
    }

    if ((typeA == 2 && typeB == 12) || (typeA == 12 && typeB == 2)) {
      return 6;
    }

    if ((typeA == 2 && typeB == 16) || (typeA == 16 && typeB == 2)) {
      return 13;
    }

    if ((typeA == 2 && typeB == 17) || (typeA == 17 && typeB == 2)) {
      return 12;
    }

    if ((typeA == 3 && typeB == 4) || (typeA == 4 && typeB == 3)) {
      return 8;
    }

    if ((typeA == 3 && typeB == 5) || (typeA == 5 && typeB == 3)) {
      return 8;
    }

    if ((typeA == 3 && typeB == 6) || (typeA == 6 && typeB == 3)) {
      return 2;
    }

    if ((typeA == 3 && typeB == 7) || (typeA == 7 && typeB == 3)) {
      return 11;
    }

    if ((typeA == 3 && typeB == 9) || (typeA == 9 && typeB == 3)) {
      return 8;
    }

    if ((typeA == 3 && typeB == 12) || (typeA == 12 && typeB == 3)) {
      return 11;
    }

    if ((typeA == 3 && typeB == 16) || (typeA == 16 && typeB == 3)) {
      return 11;
    }

    if ((typeA == 3 && typeB == 17) || (typeA == 17 && typeB == 3)) {
      return 11;
    }

    if ((typeA == 4 && typeB == 6) || (typeA == 6 && typeB == 4)) {
      return 3;
    }

    if ((typeA == 4 && typeB == 7) || (typeA == 7 && typeB == 4)) {
      return 8;
    }

    if ((typeA == 4 && typeB == 9) || (typeA == 9 && typeB == 4)) {
      return 7;
    }

    if ((typeA == 4 && typeB == 11) || (typeA == 11 && typeB == 4)) {
      return 7;
    }

    if ((typeA == 4 && typeB == 12) || (typeA == 12 && typeB == 4)) {
      return 7;
    }

    if ((typeA == 4 && typeB == 13) || (typeA == 13 && typeB == 4)) {
      return 7;
    }

    if ((typeA == 4 && typeB == 16) || (typeA == 16 && typeB == 4)) {
      return 13;
    }

    if ((typeA == 4 && typeB == 17) || (typeA == 17 && typeB == 4)) {
      return 5;
    }

    if ((typeA == 5 && typeB == 6) || (typeA == 6 && typeB == 5)) {
      return 4;
    }

    if ((typeA == 5 && typeB == 7) || (typeA == 7 && typeB == 5)) {
      return 4;
    }

    if ((typeA == 5 && typeB == 8) || (typeA == 8 && typeB == 5)) {
      return 4;
    }

    if ((typeA == 5 && typeB == 11) || (typeA == 11 && typeB == 5)) {
      return 17;
    }

    if ((typeA == 5 && typeB == 12) || (typeA == 12 && typeB == 5)) {
      return 13;
    }

    if ((typeA == 5 && typeB == 13) || (typeA == 13 && typeB == 5)) {
      return 16;
    }

    if ((typeA == 5 && typeB == 16) || (typeA == 16 && typeB == 5)) {
      return 17;
    }

    if ((typeA == 6 && typeB == 8) || (typeA == 8 && typeB == 6)) {
      return 2;
    }

    if ((typeA == 6 && typeB == 17) || (typeA == 17 && typeB == 6)) {
      return 7;
    }

    if ((typeA == 7 && typeB == 16) || (typeA == 16 && typeB == 7)) {
      return 13;
    }

    if ((typeA == 8 && typeB == 11) || (typeA == 11 && typeB == 8)) {
      return 7;
    }

    if ((typeA == 8 && typeB == 12) || (typeA == 12 && typeB == 8)) {
      return 7;
    }

    if ((typeA == 8 && typeB == 13) || (typeA == 13 && typeB == 8)) {
      return 7;
    }

    if ((typeA == 8 && typeB == 16) || (typeA == 16 && typeB == 8)) {
      return 7;
    }

    if ((typeA == 8 && typeB == 17) || (typeA == 17 && typeB == 8)) {
      return 7;
    }

    if ((typeA == 9 && typeB == 16) || (typeA == 16 && typeB == 9)) {
      return 13;
    }

    if ((typeA == 11 && typeB == 16) || (typeA == 16 && typeB == 11)) {
      return 13;
    }

    if ((typeA == 11 && typeB == 17) || (typeA == 17 && typeB == 11)) {
      return 7;
    }

    if ((typeA == 12 && typeB == 16) || (typeA == 16 && typeB == 12)) {
      return 13;
    }

    if ((typeA == 13 && typeB == 17) || (typeA == 17 && typeB == 13)) {
      return 9;
    }

    return typeA;
  }



  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    Boolean tameResult = processTameInteract(player, hand);
    if (tameResult != null) {
      return tameResult.booleanValue();
    }
    if (getType() == 60 && !getIsTamed() && isZebraRunning()) {
      return false;
    }

    ItemStack stack = player.getHeldItem(hand);
    if (!stack.isEmpty() && !getIsRideable() && stack.getItem() == Items.SADDLE) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      setRideable(true);
      return true;
    }

    if (!stack.isEmpty() && getIsTamed() && stack.getItem() == Items.IRON_HORSE_ARMOR && isArmored()) {
      if (getArmorType() == 0) {
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_ON);
      }
      dropArmor();
      setArmorType(1);
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }

      return true;
    }

    if (!stack.isEmpty() && getIsTamed() && stack.getItem() == Items.GOLDEN_HORSE_ARMOR && isArmored()) {
      if (getArmorType() == 0) {
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_ON);
      }
      dropArmor();
      setArmorType(2);
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      return true;
    }

    if (!stack.isEmpty() && getIsTamed() && stack.getItem() == Items.DIAMOND_HORSE_ARMOR && isArmored()) {
      if (getArmorType() == 0) {
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_ON);
      }
      dropArmor();
      setArmorType(3);
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      return true;
    }

    if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.horsearmorcrystal && isMagicHorse()) {
      if (getArmorType() == 0) {
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_ON);
      }
      dropArmor();
      setArmorType(4);
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      return true;
    }


    if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.essenceundead) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
      } else {
        player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
      }

      if (isUndead() || getIsGhost()) {
        setHealth(getMaxHealth());
      }



      if (getType() == 39 || getType() == 32 || getType() == 40) {


        transform(25);
      }
      else if (getType() == 36 || (getType() > 47 && getType() < 60)) {



        transform(24);
      } else if (getType() < 21 || getType() == 60 || getType() == 61) {



        transform(23);
      }

      drinkingHorse();
      return true;
    }


    if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.essencefire) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
      } else {
        player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
      }

      if (isNightmare()) {
        if (getIsAdult() && getHealth() == getMaxHealth()) {
          this.eatenpumpkin = true;
        }
        setHealth(getMaxHealth());
      }

      if (getType() == 61)
      {
        transform(38);
      }
      drinkingHorse();
      return true;
    }


    if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.essencedarkness) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
      } else {
        player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
      }

      if (getType() == 32) {
        if (getIsAdult() && getHealth() == getMaxHealth()) {
          this.eatenpumpkin = true;
        }
        setHealth(getMaxHealth());
      }

      if (getType() == 61) {
        transform(32);
      }

      if (getType() == 39)
      {

        transform(40);
      }
      drinkingHorse();
      return true;
    }

    if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.essencelight) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
      } else {
        player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
      }

      if (isMagicHorse()) {
        if (getIsAdult() && getHealth() == getMaxHealth()) {
          this.eatenpumpkin = true;
        }
        setHealth(getMaxHealth());
      }

      if (isNightmare())
      {
        transform(36);
      }
      if (getType() == 32 && this.posY > 128.0D)
      {

        transform(39);
      }

      if (isUndead() && getIsAdult() && !this.world.isRemote) {
        setEdad(10);
        if (getType() >= 26) {
          setType(getType() - 3);
        }
      }
      drinkingHorse();
      return true;
    }

    if (!stack.isEmpty() && isAmuletHorse() && getIsTamed()) {
      if ((getType() == 26 || getType() == 27 || getType() == 28) && stack.getItem() == MoCItems.amuletbone) {
        player.setHeldItem(hand, ItemStack.EMPTY);
        vanishHorse();
        return true;
      }

      if (getType() > 47 && getType() < 60 && stack.getItem() == MoCItems.amuletfairy) {
        player.setHeldItem(hand, ItemStack.EMPTY);
        vanishHorse();
        return true;
      }

      if ((getType() == 39 || getType() == 40) && stack.getItem() == MoCItems.amuletpegasus) {
        player.setHeldItem(hand, ItemStack.EMPTY);
        vanishHorse();
        return true;
      }

      if ((getType() == 21 || getType() == 22) && stack.getItem() == MoCItems.amuletghost) {
        player.setHeldItem(hand, ItemStack.EMPTY);
        vanishHorse();
        return true;
      }
    }


    if (!stack.isEmpty() && stack.getItem() == Items.DYE && getType() == 50) {

      int colorInt = stack.getItemDamage();
      switch (colorInt) {
        case 14:
          transform(59);
          break;



        case 12:
          transform(51);
          break;
        case 11:
          transform(48);
          break;
        case 10:
          transform(53);
          break;
        case 9:
          transform(52);
          break;






        case 6:
          transform(57);
          break;
        case 5:
          transform(49);
          break;
        case 4:
          transform(56);
          break;



        case 2:
          transform(58);
          break;
        case 1:
          transform(55);
          break;
        case 0:
          transform(54);
          break;
      }


      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      eatingHorse();
      return true;
    }


    if (!stack.isEmpty() &&
      getType() == 60 && (stack
      .getItem() == Items.RECORD_11 || stack.getItem() == Items.RECORD_13 || stack.getItem() == Items.RECORD_CAT || stack
      .getItem() == Items.RECORD_CHIRP || stack.getItem() == Items.RECORD_FAR || stack
      .getItem() == Items.RECORD_MALL || stack.getItem() == Items.RECORD_MELLOHI || stack
      .getItem() == Items.RECORD_STAL || stack.getItem() == Items.RECORD_STRAD || stack.getItem() == Items.RECORD_WARD)) {
      player.setHeldItem(hand, ItemStack.EMPTY);
      if (!this.world.isRemote) {
        EntityItem entityitem1 = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack((Item)MoCItems.recordshuffle, 1));
        entityitem1.setPickupDelay(20);
        this.world.spawnEntity((Entity)entityitem1);
      }
      eatingHorse();
      return true;
    }
    if (!stack.isEmpty() && stack.getItem() == Items.WHEAT && !isMagicHorse() && !isUndead()) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      if (!this.world.isRemote) {
        setTemper(getTemper() + 25);
        if (getTemper() > getMaxTemper()) {
          setTemper(getMaxTemper() - 5);
        }
      }
      if (getHealth() + 5.0F > getMaxHealth()) {
        setHealth(getMaxHealth());
      }
      eatingHorse();
      if (!getIsAdult() && getEdad() < getMaxEdad()) {
        setEdad(getEdad() + 1);
      }
      return true;
    }

    if (!stack.isEmpty() && stack.getItem() == MoCItems.sugarlump && !isMagicHorse() && !isUndead()) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      if (!this.world.isRemote) {
        setTemper(getTemper() + 25);
        if (getTemper() > getMaxTemper()) {
          setTemper(getMaxTemper() - 5);
        }
      }
      if (getHealth() + 10.0F > getMaxHealth()) {
        setHealth(getMaxHealth());
      }
      eatingHorse();
      if (!getIsAdult() && getEdad() < getMaxEdad()) {
        setEdad(getEdad() + 2);
      }
      return true;
    }

    if (!stack.isEmpty() && stack.getItem() == Items.BREAD && !isMagicHorse() && !isUndead()) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      if (!this.world.isRemote) {
        setTemper(getTemper() + 100);
        if (getTemper() > getMaxTemper()) {
          setTemper(getMaxTemper() - 5);
        }
      }
      if (getHealth() + 20.0F > getMaxHealth()) {
        setHealth(getMaxHealth());
      }
      eatingHorse();
      if (!getIsAdult() && getEdad() < getMaxEdad()) {
        setEdad(getEdad() + 3);
      }
      return true;
    }

    if (!stack.isEmpty() && (stack.getItem() == Items.APPLE || stack.getItem() == Items.GOLDEN_APPLE) && !isMagicHorse() &&
      !isUndead()) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }

      if (!this.world.isRemote) {
        MoCTools.tameWithName(player, (IMoCTameable)this);
      }

      setHealth(getMaxHealth());
      eatingHorse();
      if (!getIsAdult() && getEdad() < getMaxEdad() && !this.world.isRemote) {
        setEdad(getEdad() + 1);
      }

      return true;
    }

    if (!stack.isEmpty() && getIsTamed() && stack.getItem() == Item.getItemFromBlock((Block)Blocks.CHEST) && isBagger()) {
      if (getIsChested()) {
        return false;
      }
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }


      setIsChested(true);
      MoCTools.playCustomSound((Entity)this, SoundEvents.ENTITY_CHICKEN_EGG);
      return true;
    }
    if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.haystack) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }

      setSitting(true);
      eatingHorse();
      if (!isMagicHorse() && !isUndead()) {
        setHealth(getMaxHealth());
      }

      return true;
    }
    if (getIsChested() && player.isSneaking()) {

      if (this.localchest == null) {
        this.localchest = new MoCAnimalChest("HorseChest", getInventorySize());
      }

      if (!this.world.isRemote) {
        player.displayGUIChest((IInventory)this.localchest);
      }
      return true;
    }


    if (!stack.isEmpty() && (stack
      .getItem() == Item.getItemFromBlock(Blocks.PUMPKIN) || stack.getItem() == Items.MUSHROOM_STEW || stack
      .getItem() == Items.CAKE || stack.getItem() == Items.GOLDEN_CARROT)) {
      if (!getIsAdult() || isMagicHorse() || isUndead()) {
        return false;
      }
      stack.shrink(1);
      if (stack.getItem() == Items.MUSHROOM_STEW) {
        if (stack.isEmpty()) {
          player.setHeldItem(hand, new ItemStack(Items.BOWL));
        } else {
          player.inventory.addItemStackToInventory(new ItemStack(Items.BOWL));
        }
      } else if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      this.eatenpumpkin = true;
      setHealth(getMaxHealth());
      eatingHorse();
      return true;
    }

    if (!stack.isEmpty() && stack.getItem() == MoCItems.whip && getIsTamed() && !isBeingRidden()) {
      setSitting(!getIsSitting());
      return true;
    }

    if (getIsRideable() && getIsAdult() && !isBeingRidden()) {
      if (!this.world.isRemote && player.startRiding((Entity)this)) {
        player.rotationYaw = this.rotationYaw;
        player.rotationPitch = this.rotationPitch;
        setSitting(false);
        this.gestationtime = 0;
      }

      return true;
    }

    return super.processInteract(player, hand);
  }





  public boolean isAmuletHorse() {
    return ((getType() >= 48 && getType() < 60) || getType() == 40 || getType() == 39 || getType() == 21 ||
      getType() == 22 || getType() == 26 || getType() == 27 || getType() == 28);
  }




  public boolean isArmored() {
    return (getType() < 21);
  }






  public boolean isBagger() {
    return (getType() == 66 ||
      getType() == 65 ||
      getType() == 67 ||
      getType() == 39 ||
      getType() == 40 ||
      getType() == 25 ||
      getType() == 28 || (
      getType() >= 45 && getType() < 60));
  }





  public boolean isFloater() {
    return (getType() == 36 ||
      getType() == 27 ||
      getType() == 24 ||
      getType() == 22);
  }



  public boolean isFlyer() {
    return (getType() == 39 ||
      getType() == 40 || (
      getType() >= 45 && getType() < 60) ||
      getType() == 32 ||
      getType() == 21 ||
      getType() == 25 ||
      getType() == 28);
  }








  public boolean getIsGhost() {
    return (getType() == 21 || getType() == 22);
  }




  public boolean isMagicHorse() {
    return (

      getType() == 39 || getType() == 36 || getType() == 32 || getType() == 40 || (getType() >= 45 && getType() < 60) ||
      getType() == 21 || getType() == 22);
  }


  public boolean isMovementCeased() {
    return (getIsSitting() || isBeingRidden() || this.standCounter != 0 || this.shuffleCounter != 0 || getVanishC() != 0);
  }





  public boolean isNightmare() {
    return (getType() == 38);
  }






  public boolean isPureBreed() {
    return (getType() > 10 && getType() < 21);
  }







  public boolean isUndead() {
    return (getType() == 23 || getType() == 24 || getType() == 25 || getType() == 26 ||
      getType() == 27 ||
      getType() == 28);
  }







  public boolean isUnicorned() {
    return (getType() == 36 || (getType() >= 45 && getType() < 60) || getType() == 27 || getType() == 24);
  }

  public boolean isZebraRunning() {
    boolean flag = false;
    EntityPlayer ep1 = this.world.getClosestPlayerToEntity((Entity)this, 8.0D);
    if (ep1 != null) {
      flag = true;
      if (ep1.getRidingEntity() != null && ep1.getRidingEntity() instanceof MoCEntityHorse) {
        MoCEntityHorse playerHorse = (MoCEntityHorse)ep1.getRidingEntity();
        if (playerHorse.getType() == 16 || playerHorse.getType() == 17 || playerHorse.getType() == 60 || playerHorse.getType() == 61) {
          flag = false;
        }
      }
    }

    if (flag) {
      MoCTools.runLikeHell((EntityLiving)this, (Entity)ep1);
    }
    return flag;
  }

  public void LavaFX() {
    MoCreatures.proxy.LavaFX((Entity)this);
  }

  public void MaterializeFX() {
    MoCreatures.proxy.MaterializeFX(this);
  }

  private void moveTail() {
    this.tailCounter = 1;
  }


  public int nameYOffset() {
    if (getIsAdult()) {
      return -80;
    }
    return -5 - getEdad();
  }



  private boolean nearMusicBox() {
    if (this.world.isRemote) {
      return false;
    }

    boolean flag = false;
    BlockJukebox.TileEntityJukebox jukebox = MoCTools.nearJukeBoxRecord((Entity)this, Double.valueOf(6.0D));
    if (jukebox != null && jukebox.getRecord() != null) {
      Item record = jukebox.getRecord().getItem();
      MoCItemRecord moCItemRecord = MoCItems.recordshuffle;
      if (record == moCItemRecord) {
        flag = true;
        if (this.shuffleCounter > 1000) {
          this.shuffleCounter = 0;
          MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 102), new NetworkRegistry.TargetPoint(this.world.provider
                .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
          flag = false;
        }
      }
    }
    return flag;
  }


  public void NightmareEffect() {
    if (!MoCTools.mobGriefing(this.world)) {
      setNightmareInt(getNightmareInt() - 1);
      return;
    }
    int i = MathHelper.floor(this.posX);
    int j = MathHelper.floor((getEntityBoundingBox()).minY);
    int k = MathHelper.floor(this.posZ);
    BlockPos pos = new BlockPos(i, j, k);
    IBlockState blockstate = this.world.getBlockState(pos.add(-1, 0, -1));

    BlockEvent.BreakEvent event = null;
    if (!this.world.isRemote) {

      try {
        event = new BlockEvent.BreakEvent(this.world, pos, blockstate, (EntityPlayer)FakePlayerFactory.get(
              DimensionManager.getWorld(this.world.provider.getDimensionType().getId()), MoCreatures.MOCFAKEPLAYER));
      } catch (Throwable throwable) {}
    }


    if (event != null && !event.isCanceled()) {
      this.world.setBlockState(pos, Blocks.FIRE.getDefaultState(), 3);
      EntityPlayer entityplayer = (EntityPlayer)getRidingEntity();
      if (entityplayer != null && entityplayer.isBurning()) {
        entityplayer.extinguish();
      }
      setNightmareInt(getNightmareInt() - 1);
    }
  }


  public void onDeath(DamageSource damagesource) {
    super.onDeath(damagesource);
    if (!this.world.isRemote) {
      if ((this.rand.nextInt(10) == 0 && getType() == 23) || getType() == 24 || getType() == 25) {
        MoCTools.spawnMaggots(this.world, (Entity)this);
      }

      if (getIsTamed() && (isMagicHorse() || isPureBreed()) && !getIsGhost() && this.rand.nextInt(4) == 0) {
        MoCEntityHorse entityhorse1 = new MoCEntityHorse(this.world);
        entityhorse1.setPosition(this.posX, this.posY, this.posZ);
        this.world.spawnEntity((Entity)entityhorse1);
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_MAGIC_APPEAR);

        entityhorse1.setOwnerId(getOwnerId());
        entityhorse1.setTamed(true);
        EntityPlayer entityplayer = this.world.getClosestPlayerToEntity((Entity)this, 24.0D);
        if (entityplayer != null) {
          MoCTools.tameWithName(entityplayer, (IMoCTameable)entityhorse1);
        }

        entityhorse1.setAdult(false);
        entityhorse1.setEdad(1);
        int l = 22;
        if (isFlyer()) {
          l = 21;
        }
        entityhorse1.setType(l);
      }
    }
  }






  public void onLivingUpdate() {
    if ((isFlyer() || isFloater()) &&
      !this.onGround && this.motionY < 0.0D) {
      this.motionY *= 0.6D;
    }


    if (this.rand.nextInt(200) == 0) {
      moveTail();
    }

    if (getType() == 38 && this.rand.nextInt(50) == 0 && this.world.isRemote) {
      LavaFX();
    }

    if (getType() == 36 && isOnAir() && this.world.isRemote) {
      StarFX();
    }

    if (!this.world.isRemote && isFlyer() && isOnAir()) {
      float myFlyingSpeed = MoCTools.getMyMovementSpeed((Entity)this);
      int wingFlapFreq = (int)(25.0F - myFlyingSpeed * 10.0F);
      if (!isBeingRidden() || wingFlapFreq < 5) {
        wingFlapFreq = 5;
      }
      if (this.rand.nextInt(wingFlapFreq) == 0) {
        wingFlap();
      }
    }

    if (isFlyer()) {
      if (this.wingFlapCounter > 0 && ++this.wingFlapCounter > 20) {
        this.wingFlapCounter = 0;
      }
      if (this.wingFlapCounter != 0 && this.wingFlapCounter % 5 == 0 && this.world.isRemote) {
        StarFX();
      }
      if (this.wingFlapCounter == 5 && !this.world.isRemote) {
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_WINGFLAP);
      }
    }

    if (isUndead() && getType() < 26 && getIsAdult() && this.rand.nextInt(20) == 0) {
      if (!this.world.isRemote) {
        if (this.rand.nextInt(16) == 0) {
          setEdad(getEdad() + 1);
        }
        if (getEdad() >= 399) {
          setType(getType() + 3);
        }
      } else {
        UndeadFX();
      }
    }


    super.onLivingUpdate();

    if (!this.world.isRemote) {



      if (getType() == 60 && getIsTamed() && this.rand.nextInt(50) == 0 && nearMusicBox() && this.shuffleCounter == 0) {
        this.shuffleCounter = 1;
        MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 101), new NetworkRegistry.TargetPoint(this.world.provider
              .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
      }

      if (this.rand.nextInt(300) == 0 && this.deathTime == 0) {
        setHealth(getHealth() + 1.0F);
        if (getHealth() > getMaxHealth()) {
          setHealth(getMaxHealth());
        }
      }

      if (!getIsSitting() && !getIsTamed() && this.rand.nextInt(300) == 0) {
        setSitting(true);
      }

      if (getIsSitting() && ++this.countEating > 50 && !getIsTamed()) {
        this.countEating = 0;
        setSitting(false);
      }

      if (getType() == 38 && isBeingRidden() && getNightmareInt() > 0 && this.rand.nextInt(2) == 0) {
        NightmareEffect();
      }



























      if (this.sprintCounter > 0 && this.sprintCounter < 150 && isUnicorned() && isBeingRidden()) {
        MoCTools.buckleMobs((EntityLiving)this, Double.valueOf(2.0D), this.world);
      }

      if (isFlyer() && !getIsTamed() && this.rand.nextInt(100) == 0 && !isMovementCeased() && !getIsSitting()) {
        wingFlap();
      }

      if (!ReadyforParenting(this)) {
        return;
      }

      int i = 0;

      List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(8.0D, 3.0D, 8.0D));
      for (int j = 0; j < list.size(); j++) {
        Entity entity = list.get(j);
        if (entity instanceof MoCEntityHorse || entity instanceof EntityHorse) {
          i++;
        }
      }

      if (i > 1) {
        return;
      }
      List<Entity> list1 = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(4.0D, 2.0D, 4.0D));
      for (int k = 0; k < list1.size(); k++) {
        Entity horsemate = list1.get(k);
        boolean flag = horsemate instanceof EntityHorse;
        if ((horsemate instanceof MoCEntityHorse || flag) && horsemate != this) {



          if (!flag &&
            !ReadyforParenting((MoCEntityHorse)horsemate)) {
            return;
          }


          if (this.rand.nextInt(100) == 0) {
            this.gestationtime++;
          }

          if (this.gestationtime % 3 == 0) {
            MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageHeart(getEntityId()), new NetworkRegistry.TargetPoint(this.world.provider
                  .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
          }

          if (this.gestationtime > 50) {
            int horsemateType;

            MoCEntityHorse baby = new MoCEntityHorse(this.world);
            baby.setPosition(this.posX, this.posY, this.posZ);
            this.world.spawnEntity((Entity)baby);
            MoCTools.playCustomSound((Entity)this, SoundEvents.ENTITY_CHICKEN_EGG);
            this.eatenpumpkin = false;
            this.gestationtime = 0;



            if (flag) {
              horsemateType = TranslateVanillaHorseType((AbstractHorse)horsemate);
              if (horsemateType == -1) {
                return;
              }
            } else {
              horsemateType = ((MoCEntityHorse)horsemate).getType();
              ((MoCEntityHorse)horsemate).eatenpumpkin = false;
              ((MoCEntityHorse)horsemate).gestationtime = 0;
            }
            int l = HorseGenetics(getType(), horsemateType);

            if (l == 50 || l == 54) {

              MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_MAGIC_APPEAR);
              if (!flag) {
                ((MoCEntityHorse)horsemate).dissapearHorse();
              }
              dissapearHorse();
            }
            baby.setOwnerId(getOwnerId());
            baby.setTamed(true);

            baby.setAdult(false);
            EntityPlayer entityplayer = this.world.getPlayerEntityByUUID(getOwnerId());
            if (entityplayer != null) {
              MoCTools.tameWithName(entityplayer, (IMoCTameable)baby);
            }
            baby.setType(l);
            break;
          }
        }
      }
    }
  }






  private int TranslateVanillaHorseType(AbstractHorse horse) {
    if (horse instanceof net.minecraft.entity.passive.EntityDonkey) {
      return 65;
    }
    if (horse instanceof EntityHorse) {
      switch ((byte)((EntityHorse)horse).getHorseVariant()) {
        case 0:
          return 1;
        case 1:
          return 2;
        case 3:
          return 3;
        case 4:
          return 5;
        case 5:
          return 9;
        case 6:
          return 4;
      }
      return 3;
    }


    return -1;
  }


  public void onUpdate() {
    super.onUpdate();

    if (this.shuffleCounter > 0) {
      this.shuffleCounter++;
      if (this.world.isRemote && this.shuffleCounter % 20 == 0) {
        double var2 = this.rand.nextGaussian() * 0.5D;
        double var4 = this.rand.nextGaussian() * -0.1D;
        double var6 = this.rand.nextGaussian() * 0.02D;
        this.world.spawnParticle(EnumParticleTypes.NOTE, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, this.posY + 0.5D + (this.rand
            .nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, var2, var4, var6, new int[0]);
      }


      if (!this.world.isRemote && !nearMusicBox()) {
        this.shuffleCounter = 0;
        MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 102), new NetworkRegistry.TargetPoint(this.world.provider
              .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
      }
    }

    if (this.mouthCounter > 0 && ++this.mouthCounter > 30) {
      this.mouthCounter = 0;
    }

    if (this.standCounter > 0 && ++this.standCounter > 20) {
      this.standCounter = 0;
    }

    if (this.tailCounter > 0 && ++this.tailCounter > 8) {
      this.tailCounter = 0;
    }

    if (getVanishC() > 0) {

      setVanishC((byte)(getVanishC() + 1));

      if (getVanishC() < 15 && this.world.isRemote) {
        VanishFX();
      }


      if (getVanishC() > 100) {
        setVanishC((byte)101);
        MoCTools.dropHorseAmulet(this);
        dissapearHorse();
      }

      if (getVanishC() == 1) {
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_VANISH);
      }

      if (getVanishC() == 70) {
        stand();
      }
    }

    if (this.sprintCounter > 0) {
      this.sprintCounter++;
      if (this.sprintCounter < 150 && this.sprintCounter % 2 == 0 && this.world.isRemote) {
        StarFX();
      }

      if (this.sprintCounter > 300) {
        this.sprintCounter = 0;
      }
    }












    if (this.transformCounter > 0) {
      if (this.transformCounter == 40) {
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_TRANSFORM);
      }

      if (++this.transformCounter > 100) {
        this.transformCounter = 0;
        if (this.transformType != 0) {
          dropArmor();
          setType(this.transformType);
        }
      }
    }

    if (getIsGhost() && getEdad() < 10 && this.rand.nextInt(7) == 0) {
      setEdad(getEdad() + 1);
    }

    if (getIsGhost() && getEdad() == 9) {
      setEdad(100);
      setAdult(true);
    }
  }

  private void openMouth() {
    this.mouthCounter = 1;
  }

  public boolean ReadyforParenting(MoCEntityHorse entityhorse) {
    int i = entityhorse.getType();
    return (!entityhorse.isBeingRidden() && entityhorse.getRidingEntity() == null && entityhorse.getIsTamed() && entityhorse.eatenpumpkin && entityhorse
      .getIsAdult() && !entityhorse.isUndead() && !entityhorse.getIsGhost() && i != 61 && i < 66);
  }


  public boolean rideableEntity() {
    return true;
  }




































  public void selectType() {
    checkSpawningBiome();
    if (getType() == 0) {
      if (this.rand.nextInt(5) == 0) {
        setAdult(false);
      }
      int j = this.rand.nextInt(100);
      int i = MoCreatures.proxy.zebraChance;
      if (j <= 33 - i) {
        setType(6);
      } else if (j <= 66 - i) {
        setType(7);
      } else if (j <= 99 - i) {
        setType(8);
      } else {
        setType(60);
      }
    }
  }

  public void setNightmareInt(int i) {
    this.nightmareInt = i;
  }

  public void setReproduced(boolean var1) {
    this.hasReproduced = var1;
  }






  public void setVanishC(byte i) {
    this.vanishCounter = i;
  }

  private void stand() {
    if (!isBeingRidden() && !isOnAir()) {
      this.standCounter = 1;
    }
  }

  public void StarFX() {
    MoCreatures.proxy.StarFX(this);
  }






  public float tFloat() {
    if (++this.fCounter > 60) {
      this.fCounter = 0;
      this.transFloat = this.rand.nextFloat() * 0.3F + 0.3F;
    }

    if (getIsGhost() && getEdad() < 10) {
      this.transFloat = 0.0F;
    }
    return this.transFloat;
  }

  public void transform(int tType) {
    if (!this.world.isRemote) {
      MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), tType), new NetworkRegistry.TargetPoint(this.world.provider
            .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
    }

    this.transformType = tType;
    if (!isBeingRidden() && this.transformType != 0) {
      dropArmor();
      this.transformCounter = 1;
    }
  }

  public void UndeadFX() {
    MoCreatures.proxy.UndeadFX((Entity)this);
  }

  public void VanishFX() {
    MoCreatures.proxy.VanishFX(this);
  }





  public void vanishHorse() {
    getNavigator().clearPath();
    this.motionX = 0.0D;
    this.motionZ = 0.0D;

    if (isBagger()) {
      MoCTools.dropInventory((Entity)this, this.localchest);
      dropBags();
    }
    if (!this.world.isRemote) {
      MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageVanish(getEntityId()), new NetworkRegistry.TargetPoint(this.world.provider
            .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
      setVanishC((byte)1);
    }
    MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_VANISH);
  }


  public void dropMyStuff() {
    dropArmor();
    MoCTools.dropSaddle((MoCEntityAnimal)this, this.world);
    if (isBagger()) {
      MoCTools.dropInventory((Entity)this, this.localchest);
      dropBags();
    }
  }

  public void wingFlap() {
    if (isFlyer() && this.wingFlapCounter == 0) {
      this.wingFlapCounter = 1;
      if (!this.world.isRemote) {
        MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 3), new NetworkRegistry.TargetPoint(this.world.provider
              .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
      }
    }
  }


  public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
    super.writeEntityToNBT(nbttagcompound);
    nbttagcompound.setBoolean("Saddle", getIsRideable());
    nbttagcompound.setBoolean("EatingHaystack", getIsSitting());
    nbttagcompound.setBoolean("ChestedHorse", getIsChested());
    nbttagcompound.setBoolean("HasReproduced", getHasReproduced());
    nbttagcompound.setBoolean("Bred", getHasBred());
    nbttagcompound.setInteger("ArmorType", getArmorType());

    if (getIsChested() && this.localchest != null) {
      NBTTagList nbttaglist = new NBTTagList();
      for (int i = 0; i < this.localchest.getSizeInventory(); i++) {

        this.localstack = this.localchest.getStackInSlot(i);
        if (this.localstack != null && !this.localstack.isEmpty()) {
          NBTTagCompound nbttagcompound1 = new NBTTagCompound();
          nbttagcompound1.setByte("Slot", (byte)i);
          this.localstack.writeToNBT(nbttagcompound1);
          nbttaglist.appendTag((NBTBase)nbttagcompound1);
        }
      }
      nbttagcompound.setTag("Items", (NBTBase)nbttaglist);
    }
  }


  public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
    super.readEntityFromNBT(nbttagcompound);
    setRideable(nbttagcompound.getBoolean("Saddle"));
    setSitting(nbttagcompound.getBoolean("EatingHaystack"));
    setBred(nbttagcompound.getBoolean("Bred"));
    setIsChested(nbttagcompound.getBoolean("ChestedHorse"));
    setReproduced(nbttagcompound.getBoolean("HasReproduced"));
    setArmorType((byte)nbttagcompound.getInteger("ArmorType"));
    if (getIsChested()) {
      NBTTagList nbttaglist = nbttagcompound.getTagList("Items", 10);
      this.localchest = new MoCAnimalChest("HorseChest", getInventorySize());
      for (int i = 0; i < nbttaglist.tagCount(); i++) {
        NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
        int j = nbttagcompound1.getByte("Slot") & 0xFF;
        if (j >= 0 && j < this.localchest.getSizeInventory()) {
          this.localchest.setInventorySlotContents(j, new ItemStack(nbttagcompound1));
        }
      }
    }
  }



  public void performAnimation(int animationType) {
    if (animationType >= 23 && animationType < 60) {

      this.transformType = animationType;
      this.transformCounter = 1;
    }
    if (animationType == 3)
    {
      this.wingFlapCounter = 1;
    }
    if (animationType == 101)
    {
      this.shuffleCounter = 1;
    }
    if (animationType == 102)
    {
      this.shuffleCounter = 0;
    }
  }


  public EnumCreatureAttribute getCreatureAttribute() {
    if (isUndead()) {
      return EnumCreatureAttribute.UNDEAD;
    }
    return super.getCreatureAttribute();
  }


  protected boolean canBeTrappedInNet() {
    return (getIsTamed() && !isAmuletHorse());
  }


  public int getMaxSpawnedInChunk() {
    return 4;
  }


  public void setType(int i) {
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(calculateMaxHealth());
    setHealth(getMaxHealth());
    if (getType() == 38 || getType() == 40) {
      this.isImmuneToFire = true;
    }
    super.setType(i);
  }


  public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
    if (getType() == 38 || getType() == 40) {
      this.isImmuneToFire = true;
    }
    return super.onInitialSpawn(difficulty, livingdata);
  }


  public void updatePassenger(Entity passenger) {
    double dist = getSizeFactor() * 0.25D;
    double newPosX = this.posX + dist * Math.sin((this.renderYawOffset / 57.29578F));
    double newPosZ = this.posZ - dist * Math.cos((this.renderYawOffset / 57.29578F));
    passenger.setPosition(newPosX, this.posY + getMountedYOffset() + passenger.getYOffset(), newPosZ);
  }


  public void makeEntityJump() {
    wingFlap();
    super.makeEntityJump();
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityHorse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
