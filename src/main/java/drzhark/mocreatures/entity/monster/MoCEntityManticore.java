package drzhark.mocreatures.entity.monster;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.MoCEntityMob;
import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
import drzhark.mocreatures.init.MoCItems;
import drzhark.mocreatures.init.MoCSoundEvents;
import drzhark.mocreatures.network.MoCMessageHandler;
import drzhark.mocreatures.network.message.MoCMessageAnimation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

public class MoCEntityManticore
  extends MoCEntityMob {
  public int mouthCounter;
  public int tailCounter;

  public MoCEntityManticore(World world) {
    super(world);
    setSize(1.4F, 1.6F);
    this.isImmuneToFire = true;
  }
  public int wingFlapCounter; private boolean isPoisoning; private int poisontimer;

  protected void initEntityAI() {
    this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
    this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
  }


  public void selectType() {
    checkSpawningBiome();

    if (getType() == 0) {
      setType(this.rand.nextInt(2) * 2 + 2);
    }
  }


  public boolean checkSpawningBiome() {
    if (this.world.provider.doesWaterVaporize()) {
      setType(1);
      this.isImmuneToFire = true;
      return true;
    }

    int i = MathHelper.floor(this.posX);
    int j = MathHelper.floor((getEntityBoundingBox()).minY);
    int k = MathHelper.floor(this.posZ);
    BlockPos pos = new BlockPos(i, j, k);

    Biome currentbiome = MoCTools.Biomekind(this.world, pos);

    if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.SNOWY)) {
      setType(3);
    }

    return true;
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
    return MoCreatures.proxy.getTexture("bcmanticoregreen.png");
  }



  public boolean isFlyer() {
    return true;
  }


  public float getMoveSpeed() {
    return 0.9F;
  }





  public void fall(float f, float f1) {}




  public boolean attackEntityAsMob(Entity entityIn) {
    return super.attackEntityAsMob(entityIn);
  }

  public boolean getIsRideable() {
    return false;
  }


  protected boolean isHarmedByDaylight() {
    return true;
  }


  public int maxFlyingHeight() {
    return 10;
  }


  public int minFlyingHeight() {
    return 1;
  }


  public void updatePassenger(Entity passenger) {
    double dist = -0.1D;
    double newPosX = this.posX + dist * Math.sin((this.renderYawOffset / 57.29578F));
    double newPosZ = this.posZ - dist * Math.cos((this.renderYawOffset / 57.29578F));
    passenger.setPosition(newPosX, this.posY + getMountedYOffset() + passenger.getYOffset(), newPosZ);
    passenger.rotationYaw = this.rotationYaw;
  }


  public double getMountedYOffset() {
    return this.height * 0.75D - 0.1D;
  }














  private void openMouth() {
    this.mouthCounter = 1;
  }

  private void moveTail() {
    this.tailCounter = 1;
  }

  public boolean isOnAir() {
    return this.world.isAirBlock(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.posY - 0.2D),
          MathHelper.floor(this.posZ)));
  }


  public void onUpdate() {
    super.onUpdate();

    if (this.mouthCounter > 0 && ++this.mouthCounter > 30) {
      this.mouthCounter = 0;
    }

    if (this.tailCounter > 0 && ++this.tailCounter > 8) {
      this.tailCounter = 0;
    }

    if (this.wingFlapCounter > 0 && ++this.wingFlapCounter > 20) {
      this.wingFlapCounter = 0;
    }
  }



  public void onLivingUpdate() {
    super.onLivingUpdate();








    if (isOnAir() && isFlyer() && this.rand.nextInt(5) == 0) {
      this.wingFlapCounter = 1;
    }

    if (this.rand.nextInt(200) == 0) {
      moveTail();
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



      if (this.wingFlapCounter == 5 && !this.world.isRemote)
      {
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_WINGFLAP);
      }
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
    if (!this.world.isRemote) {
      if (isFlyer() && this.rand.nextInt(500) == 0) {
        wingFlap();
      }

      if (!isBeingRidden() && this.rand.nextInt(200) == 0) {
        MoCTools.findMobRider((Entity)this);
      }
    }
  }


  public void makeEntityJump() {
    wingFlap();
    super.makeEntityJump();
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







  public void performAnimation(int animationType) {
    if (animationType == 0) {

      setPoisoning(true);
    } else if (animationType == 3) {

      this.wingFlapCounter = 1;
    }
  }

  public boolean getIsPoisoning() {
    return this.isPoisoning;
  }

  public void setPoisoning(boolean flag) {
    if (flag && !this.world.isRemote) {
      MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 0), new NetworkRegistry.TargetPoint(this.world.provider
            .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
    }
    this.isPoisoning = flag;
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



  protected void applyEnchantments(EntityLivingBase entityLivingBaseIn, Entity entityIn) {
    boolean flag = entityIn instanceof EntityPlayer;
    if (!getIsPoisoning() && this.rand.nextInt(5) == 0 && entityIn instanceof EntityLivingBase) {
      setPoisoning(true);
      if (getType() == 4 || getType() == 2) {

        if (flag) {
          MoCreatures.poisonPlayer((EntityPlayer)entityIn);
        }
        ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.POISON, 70, 0));
      } else if (getType() == 3) {

        if (flag) {
          MoCreatures.freezePlayer((EntityPlayer)entityIn);
        }
        ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 70, 0));
      }
      else if (getType() == 1) {

        if (flag && !this.world.isRemote && !this.world.provider.doesWaterVaporize()) {
          MoCreatures.burnPlayer((EntityPlayer)entityIn);
          ((EntityLivingBase)entityIn).setFire(15);
        }
      }
    } else {
      openMouth();
    }
    super.applyEnchantments(entityLivingBaseIn, entityIn);
  }

  public boolean swingingTail() {
    return (getIsPoisoning() && this.poisontimer < 15);
  }


  protected SoundEvent getDeathSound() {
    openMouth();
    return MoCSoundEvents.ENTITY_LION_DEATH;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    openMouth();
    return MoCSoundEvents.ENTITY_LION_HURT;
  }


  protected SoundEvent getAmbientSound() {
    openMouth();
    return MoCSoundEvents.ENTITY_LION_AMBIENT;
  }

















  public float getSizeFactor() {
    return 1.4F;
  }


  protected Item getDropItem() {
    boolean flag = (this.rand.nextInt(100) < MoCreatures.proxy.rareItemDropChance);

    switch (getType()) {
      case 1:
        if (flag) {
          return (Item)MoCItems.scorpStingNether;
        }
        return (Item)MoCItems.chitinNether;
      case 2:
        if (flag) {
          return (Item)MoCItems.scorpStingCave;
        }
        return (Item)MoCItems.chitinCave;

      case 3:
        if (flag) {
          return (Item)MoCItems.scorpStingFrost;
        }
        return (Item)MoCItems.chitinFrost;
      case 4:
        if (flag) {
          return (Item)MoCItems.scorpStingDirt;
        }
        return (Item)MoCItems.chitin;
    }

    return (Item)MoCItems.chitin;
  }



  protected void dropFewItems(boolean flag, int x) {
    BlockPos pos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor((getEntityBoundingBox()).minY), this.posZ);
    int chance = MoCreatures.proxy.rareItemDropChance;
    if (this.rand.nextInt(100) < chance) {
      entityDropItem(new ItemStack((Item)MoCItems.mocegg, 1, getType() + 61), 0.0F);
    } else {
      super.dropFewItems(flag, x);
    }
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\monster\MoCEntityManticore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
