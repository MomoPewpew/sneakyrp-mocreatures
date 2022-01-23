package drzhark.mocreatures.entity.passive;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
import drzhark.mocreatures.entity.ai.EntityAIFleeFromPlayer;
import drzhark.mocreatures.entity.ai.EntityAIHunt;
import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
import drzhark.mocreatures.entity.ai.EntityAIPanicMoC;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.init.MoCItems;
import drzhark.mocreatures.init.MoCSoundEvents;
import drzhark.mocreatures.network.MoCMessageHandler;
import drzhark.mocreatures.network.message.MoCMessageAnimation;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;








public class MoCEntitySnake
  extends MoCEntityTameableAnimal
{
  private float fTongue;
  private float fMouth;
  private boolean isBiting;
  private float fRattle;
  private boolean isPissed;
  private int hissCounter;
  private int movInt;
  private boolean isNearPlayer;
  public float bodyswing;
  public static final String[] snakeNames = new String[] { "Dark", "Spotted", "Orange", "Green", "Coral", "Cobra", "Rattle", "Python" };

  public MoCEntitySnake(World world) {
    super(world);
    setSize(1.4F, 0.5F);
    this.bodyswing = 2.0F;
    this.movInt = this.rand.nextInt(10);
    setEdad(50 + this.rand.nextInt(50));
  }


  protected void initEntityAI() {
    this.tasks.addTask(2, (EntityAIBase)new EntityAIPanicMoC((EntityCreature)this, 0.8D));
    this.tasks.addTask(3, (EntityAIBase)new EntityAIFleeFromPlayer((EntityCreature)this, 0.8D, 4.0D));
    this.tasks.addTask(4, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
    this.tasks.addTask(5, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 0.8D, 30));
    this.tasks.addTask(9, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHunt((EntityCreature)this, EntityAnimal.class, true));
    this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
  }


  public void selectType() {
    checkSpawningBiome();










    if (getType() == 0) {
      setType(this.rand.nextInt(8) + 1);
    }
  }


  public ResourceLocation getTexture() {
    switch (getType()) {
      case 1:
        return MoCreatures.proxy.getTexture("snake1.png");
      case 2:
        return MoCreatures.proxy.getTexture("snake2.png");
      case 3:
        return MoCreatures.proxy.getTexture("snake3.png");
      case 4:
        return MoCreatures.proxy.getTexture("snake4.png");
      case 5:
        return MoCreatures.proxy.getTexture("snake5.png");
      case 6:
        return MoCreatures.proxy.getTexture("snake6.png");
      case 7:
        return MoCreatures.proxy.getTexture("snake7.png");
      case 8:
        return MoCreatures.proxy.getTexture("snake8.png");
    }
    return MoCreatures.proxy.getTexture("snake1.png");
  }



  public void fall(float f, float f1) {}



  public boolean isOnLadder() {
    return this.collidedHorizontally;
  }




  protected void jump() {
    if (isInWater()) {
      super.jump();
    }
  }


  protected boolean canDespawn() {
    if (MoCreatures.proxy.forceDespawns) {
      return !getIsTamed();
    }
    return false;
  }


  public boolean pickedUp() {
    return (getRidingEntity() != null);
  }


  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    Boolean tameResult = processTameInteract(player, hand);
    if (tameResult != null) {
      return tameResult.booleanValue();
    }

    if (!getIsTamed()) {
      return false;
    }

    if (getRidingEntity() == null) {
      if (startRiding((Entity)player)) {
        this.rotationYaw = player.rotationYaw;
      }

      return true;
    }

    return super.processInteract(player, hand);
  }


  public boolean isNotScared() {
    if (getType() > 2 && getEdad() > 50) {
      return true;
    }
    return false;
  }






  public boolean isClimbing() {
    return (isOnLadder() && this.motionY > 0.009999999776482582D);
  }

  public boolean isResting() {
    return (!getNearPlayer() && this.onGround && this.motionX < 0.01D && this.motionX > -0.01D && this.motionZ < 0.01D && this.motionZ > -0.01D);
  }

  public boolean getNearPlayer() {
    return (this.isNearPlayer || isBiting());
  }

  public int getMovInt() {
    return this.movInt;
  }


  public boolean swimmerEntity() {
    return false;
  }


  public boolean canBreatheUnderwater() {
    return true;
  }

  public void setNearPlayer(boolean flag) {
    this.isNearPlayer = flag;
  }


  public double getYOffset() {
    if (getRidingEntity() instanceof EntityPlayer) {
      return 0.10000000149011612D;
    }

    return super.getYOffset();
  }

  public float getSizeF() {
    float factor = 1.0F;
    if (getType() == 1 || getType() == 2) {

      factor = 0.8F;
    } else if (getType() == 5) {

      factor = 0.6F;
    }
    if (getType() == 6)
    {
      factor = 1.1F;
    }
    if (getType() == 7)
    {
      factor = 0.9F;
    }
    if (getType() == 8)
    {
      factor = 1.5F;
    }
    return getEdad() * 0.01F * factor;
  }


  public void onUpdate() {
    super.onUpdate();

    if (this.world.isRemote) {
      if (getfTongue() != 0.0F) {
        setfTongue(getfTongue() + 0.2F);
        if (getfTongue() > 8.0F) {
          setfTongue(0.0F);
        }
      }

      if (getfMouth() != 0.0F && this.hissCounter == 0) {

        setfMouth(getfMouth() + 0.1F);
        if (getfMouth() > 0.5F) {
          setfMouth(0.0F);
        }
      }

      if (getType() == 7 && getfRattle() != 0.0F) {

        setfRattle(getfRattle() + 0.2F);
        if (getfRattle() == 1.0F)
        {
          MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_SNAKE_RATTLE);
        }
        if (getfRattle() > 8.0F) {
          setfRattle(0.0F);
        }
      }




      if (this.rand.nextInt(50) == 0 && getfTongue() == 0.0F) {
        setfTongue(0.1F);
      }




      if (this.rand.nextInt(100) == 0 && getfMouth() == 0.0F) {
        setfMouth(0.1F);
      }
      if (getType() == 7) {
        int chance = 0;
        if (getNearPlayer()) {
          chance = 30;
        } else {
          chance = 100;
        }

        if (this.rand.nextInt(chance) == 0) {
          setfRattle(0.1F);
        }
      }



      if (!isResting() && !pickedUp() && this.rand.nextInt(50) == 0) {
        this.movInt = this.rand.nextInt(10);
      }




      if (isBiting()) {
        this.bodyswing -= 0.5F;
        setfMouth(0.3F);

        if (this.bodyswing < 0.0F) {
          MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_SNAKE_SNAP);
          this.bodyswing = 2.5F;
          setfMouth(0.0F);
          setBiting(false);
        }
      }
    }

    if (pickedUp()) {
      this.movInt = 0;
    }

    if (isResting())
    {
      this.prevRenderYawOffset = this.renderYawOffset = this.rotationYaw = this.prevRotationYaw;
    }


    if (!this.onGround && getRidingEntity() != null) {
      this.rotationYaw = (getRidingEntity()).rotationYaw;
    }

    if (this.world.getDifficulty().getDifficultyId()  > 0 && getNearPlayer() && !getIsTamed() && isNotScared()) {

      this.hissCounter++;



      if (this.hissCounter % 25 == 0) {
        setfMouth(0.3F);
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_SNAKE_ANGRY);
      }
      if (this.hissCounter % 35 == 0) {
        setfMouth(0.0F);
      }

      if (this.hissCounter > 100 && this.rand.nextInt(50) == 0) {

        setPissed(true);
        this.hissCounter = 0;
      }
    }
    if (this.hissCounter > 500) {
      this.hissCounter = 0;
    }
  }








  public float getfTongue() {
    return this.fTongue;
  }

  public void setfTongue(float fTongue) {
    this.fTongue = fTongue;
  }

  public float getfMouth() {
    return this.fMouth;
  }

  public void setfMouth(float fMouth) {
    this.fMouth = fMouth;
  }

  public float getfRattle() {
    return this.fRattle;
  }

  public void setfRattle(float fRattle) {
    this.fRattle = fRattle;
  }


  public void onLivingUpdate() {
    super.onLivingUpdate();




    if (getAttackTarget() != null && this.rand.nextInt(300) == 0) {
      setAttackTarget(null);
    }

    EntityPlayer entityplayer1 = this.world.getClosestPlayerToEntity((Entity)this, 12.0D);
    if (entityplayer1 != null) {
      double distP = MoCTools.getSqDistanceTo((Entity)entityplayer1, this.posX, this.posY, this.posZ);
      if (isNotScared()) {
        if (distP < 5.0D) {
          setNearPlayer(true);
        } else {
          setNearPlayer(false);


        }



      }
      else {


        setNearPlayer(false);
      }
    } else {

      setNearPlayer(false);
    }
  }


  public boolean attackEntityAsMob(Entity entityIn) {
    if ((getType() < 3 || getIsTamed()) && entityIn instanceof EntityPlayer) {
      return false;
    }

    if (entityIn instanceof EntityPlayer && !shouldAttackPlayers()) {
      return false;
    }
    setBiting(true);
    return super.attackEntityAsMob(entityIn);
  }


  public void performAnimation(int i) {
    setBiting(true);
  }

  public boolean isBiting() {
    return this.isBiting;
  }

  public void setBiting(boolean flag) {
    if (flag && !this.world.isRemote) {
      MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 0), new NetworkRegistry.TargetPoint(this.world.provider
            .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
    }
    this.isBiting = flag;
  }

  public boolean isPissed() {
    return this.isPissed;
  }

  public void setPissed(boolean isPissed) {
    this.isPissed = isPissed;
  }



  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    if (getType() < 3) {
      return super.attackEntityFrom(damagesource, i);
    }

    if (super.attackEntityFrom(damagesource, i)) {
      Entity entity = damagesource.getTrueSource();
      if (isRidingOrBeingRiddenBy(entity)) {
        return true;
      }
      if (entity != this && entity instanceof EntityLivingBase && super.shouldAttackPlayers()) {
        setPissed(true);
        setAttackTarget((EntityLivingBase)entity);
      }
      return true;
    }
    return false;
  }



  protected void dropFewItems(boolean flag, int x) {
    if (getEdad() > 60) {
      int j = this.rand.nextInt(3);
      for (int l = 0; l < j; l++)
      {
        entityDropItem(new ItemStack((Item)MoCItems.mocegg, 1, getType() + 20), 0.0F);
      }
    }
  }


  public boolean canAttackTarget(EntityLivingBase entity) {
    return (!(entity instanceof MoCEntitySnake) && entity.height < 0.5D && entity.width < 0.5D);
  }


  protected void playStepSound(BlockPos pos, Block par4) {
    if (isInsideOfMaterial(Material.WATER)) {
      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_SNAKE_SWIM);
    }
  }







  protected SoundEvent getDeathSound() {
    return MoCSoundEvents.ENTITY_SNAKE_DEATH;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    return MoCSoundEvents.ENTITY_SNAKE_HURT;
  }


  protected SoundEvent getAmbientSound() {
    return MoCSoundEvents.ENTITY_SNAKE_AMBIENT;
  }


  public boolean getCanSpawnHere() {
    return (getCanSpawnHereCreature() && getCanSpawnHereLiving());
  }


  public boolean checkSpawningBiome() {
    BlockPos pos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor((getEntityBoundingBox()).minY), this.posZ);















    try {
      Biome currentbiome = MoCTools.Biomekind(this.world, pos);
      int l = this.rand.nextInt(10);

      if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.SNOWY)) {
        return false;
      }

      if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.SANDY)) {
        if (l < 5) {
          setType(7);
        } else {
          setType(2);
        }
      }

      if (getType() == 7 && !BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.SANDY)) {
        setType(2);
      }
      if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.HILLS)) {
        if (l < 4) {
          setType(1);
        } else if (l < 7) {
          setType(5);
        } else {
          setType(6);
        }
      }
      if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.SWAMP))
      {
        if (l < 4) {
          setType(8);
        } else if (l < 8) {
          setType(4);
        } else {
          setType(1);
        }
      }
    } catch (Exception exception) {}

    return true;
  }


  public int nameYOffset() {
    return -30;
  }


  public boolean isMyHealFood(ItemStack stack) {
    return (!stack.isEmpty() && stack.getItem() == MoCItems.ratRaw);
  }


  public int getMaxSpawnedInChunk() {
    return 2;
  }


  public boolean isReadyToHunt() {
    return (getIsAdult() && !isMovementCeased());
  }


  protected void applyEnchantments(EntityLivingBase entityLivingBaseIn, Entity entityIn) {
    if (isVenomous()) {
      if (entityIn instanceof EntityPlayer) {
        MoCreatures.poisonPlayer((EntityPlayer)entityIn);
      }
      ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.POISON, 150, 2));
    }
    super.applyEnchantments(entityLivingBaseIn, entityIn);
  }

  private boolean isVenomous() {
    return (getType() == 3 || getType() == 4 || getType() == 5 || getType() == 6 || getType() == 7 || getType() == 9);
  }


  public boolean shouldAttackPlayers() {
    return (isPissed() && super.shouldAttackPlayers());
  }


  public int getTalkInterval() {
    return 400;
  }


  public boolean isAmphibian() {
    return true;
  }



  public boolean canRidePlayer() {
    return true;
  }



  protected double maxDivingDepth() {
    return 1.0D * getEdad() / 100.0D;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntitySnake.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
