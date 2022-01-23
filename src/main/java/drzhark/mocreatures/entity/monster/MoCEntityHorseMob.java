package drzhark.mocreatures.entity.monster;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.MoCEntityMob;
import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
import drzhark.mocreatures.init.MoCItems;
import drzhark.mocreatures.init.MoCSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class MoCEntityHorseMob
  extends MoCEntityMob {
  public int mouthCounter;
  public int textCounter;
  public int standCounter;

  public MoCEntityHorseMob(World world) {
    super(world);
    setSize(1.4F, 1.6F);
  }
  public int tailCounter; public int eatingCounter; public int wingFlapCounter;

  protected void initEntityAI() {
    this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
    this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
  }


  public void selectType() {
    if (this.world.provider.doesWaterVaporize()) {
      setType(38);
      this.isImmuneToFire = true;
    }
    else if (getType() == 0) {
      int j = this.rand.nextInt(100);
      if (j <= 40) {
        setType(23);
      } else if (j <= 80) {
        setType(26);
      } else {
        setType(32);
      }
    }
  }









  public ResourceLocation getTexture() {
    String baseTex;
    int max;
    String iteratorTex;
    String decayTex;
    String NTA;
    String NTB;
    String NTC;
    switch (getType()) {

      case 23:
        if (!MoCreatures.proxy.getAnimateTextures()) {
          return MoCreatures.proxy.getTexture("horseundead.png");
        }
        baseTex = "horseundead";
        max = 79;

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
        decayTex = "" + (getEdad() / 100);
        decayTex = decayTex.substring(0, 1);
        return MoCreatures.proxy.getTexture(baseTex + decayTex + iteratorTex + ".png");

      case 26:
        return MoCreatures.proxy.getTexture("horseskeleton.png");

      case 32:
        return MoCreatures.proxy.getTexture("horsebat.png");

      case 38:
        if (!MoCreatures.proxy.getAnimateTextures()) {
          return MoCreatures.proxy.getTexture("horsenightmare1.png");
        }
        this.textCounter++;
        if (this.textCounter < 10) {
          this.textCounter = 10;
        }
        if (this.textCounter > 59) {
          this.textCounter = 10;
        }
        NTA = "horsenightmare";
        NTB = "" + this.textCounter;
        NTB = NTB.substring(0, 1);
        NTC = ".png";

        return MoCreatures.proxy.getTexture(NTA + NTB + NTC);
    }

    return MoCreatures.proxy.getTexture("horseundead.png");
  }



  protected SoundEvent getDeathSound() {
    openMouth();
    return MoCSoundEvents.ENTITY_HORSE_DEATH_UNDEAD;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    openMouth();
    stand();
    return MoCSoundEvents.ENTITY_HORSE_HURT_UNDEAD;
  }


  protected SoundEvent getAmbientSound() {
    openMouth();
    if (this.rand.nextInt(10) == 0) {
      stand();
    }
    return MoCSoundEvents.ENTITY_HORSE_AMBIENT_UNDEAD;
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

    if (this.standCounter > 0 && ++this.standCounter > 20) {
      this.standCounter = 0;
    }

    if (this.tailCounter > 0 && ++this.tailCounter > 8) {
      this.tailCounter = 0;
    }

    if (this.eatingCounter > 0 && ++this.eatingCounter > 50) {
      this.eatingCounter = 0;
    }

    if (this.wingFlapCounter > 0 && ++this.wingFlapCounter > 20) {
      this.wingFlapCounter = 0;
    }
  }



  public boolean isFlyer() {
    return (getType() == 25 ||
      getType() == 32 ||
      getType() == 28);
  }






  public boolean isUnicorned() {
    return (getType() == 24 || getType() == 27 || getType() == 32);
  }



  public void onLivingUpdate() {
    super.onLivingUpdate();

    if (isOnAir() && isFlyer() && this.rand.nextInt(5) == 0) {
      this.wingFlapCounter = 1;
    }

    if (this.rand.nextInt(200) == 0) {
      moveTail();
    }

    if (!isOnAir() && !isBeingRidden() && this.rand.nextInt(250) == 0) {
      stand();
    }

    if (this.world.isRemote && getType() == 38 && this.rand.nextInt(50) == 0) {
      LavaFX();
    }

    if (this.world.isRemote && getType() == 23 && this.rand.nextInt(50) == 0) {
      UndeadFX();
    }

    if (!this.world.isRemote) {
      if (isFlyer() && this.rand.nextInt(500) == 0) {
        wingFlap();
      }

      if (!isOnAir() && !isBeingRidden() && this.rand.nextInt(300) == 0) {
        setEating();
      }

      if (!isBeingRidden() && this.rand.nextInt(100) == 0) {
        MoCTools.findMobRider((Entity)this);
      }
    }
  }














  private void openMouth() {
    this.mouthCounter = 1;
  }

  private void moveTail() {
    this.tailCounter = 1;
  }

  private void setEating() {
    this.eatingCounter = 1;
  }

  private void stand() {
    this.standCounter = 1;
  }

  public void wingFlap() {
    this.wingFlapCounter = 1;
  }


  protected Item getDropItem() {
    boolean flag = (this.rand.nextInt(100) < MoCreatures.proxy.rareItemDropChance);
    if (getType() == 32 && MoCreatures.proxy.rareItemDropChance < 25) {
      flag = (this.rand.nextInt(100) < 25);
    }

    if (flag && (getType() == 36 || (getType() >= 50 && getType() < 60)))
    {
      return (Item)MoCItems.unicornhorn;
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


  public boolean attackEntityAsMob(Entity entityIn) {
    if (entityIn instanceof EntityPlayer && !shouldAttackPlayers()) {
      return false;
    }
    if (this.onGround && !isOnAir()) {
      stand();
    }
    openMouth();
    MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_HORSE_MAD);
    return super.attackEntityAsMob(entityIn);
  }


  public void onDeath(DamageSource damagesource) {
    super.onDeath(damagesource);

    if (getType() == 23 || getType() == 24 || getType() == 25) {
      MoCTools.spawnSlimes(this.world, (Entity)this);
    }
  }



  public double getMountedYOffset() {
    return this.height * 0.75D - 0.1D;
  }


  public boolean getCanSpawnHere() {
    if (this.posY < 50.0D && !this.world.provider.doesWaterVaporize()) {
      setType(32);
    }
    return super.getCanSpawnHere();
  }

  public void UndeadFX() {
    MoCreatures.proxy.UndeadFX((Entity)this);
  }

  public void LavaFX() {
    MoCreatures.proxy.LavaFX((Entity)this);
  }





  public EnumCreatureAttribute getCreatureAttribute() {
    if (getType() == 23 || getType() == 24 || getType() == 25) {
      return EnumCreatureAttribute.UNDEAD;
    }
    return super.getCreatureAttribute();
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
    double dist = 0.4D;
    double newPosX = this.posX + dist * Math.sin((this.renderYawOffset / 57.29578F));
    double newPosZ = this.posZ - dist * Math.cos((this.renderYawOffset / 57.29578F));
    passenger.setPosition(newPosX, this.posY + getMountedYOffset() + passenger.getYOffset(), newPosZ);
    passenger.rotationYaw = this.rotationYaw;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\monster\MoCEntityHorseMob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
