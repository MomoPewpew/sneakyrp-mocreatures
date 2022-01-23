package drzhark.mocreatures.entity.monster;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.MoCEntityMob;
import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
import drzhark.mocreatures.init.MoCSoundEvents;
import drzhark.mocreatures.network.MoCMessageHandler;
import drzhark.mocreatures.network.message.MoCMessageAnimation;
import drzhark.mocreatures.network.message.MoCMessageExplode;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MoCEntityOgre extends MoCEntityMob {
  public int attackCounterLeft;
  public int attackCounterRight;
  private int movingHead;

  public MoCEntityOgre(World world) {
    super(world);
    setSize(1.9F, 3.0F);
  }
  public int smashCounter; public int armToAnimate; public int attackCounter;

  protected void initEntityAI() {
    this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
    this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(calculateMaxHealth());
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
  }


  public void selectType() {
    if (getType() == 0) {
      setType(this.rand.nextInt(2) + 1);
    }
  }

  public float calculateMaxHealth() {
    return 35.0F;
  }


  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    if (super.attackEntityFrom(damagesource, i)) {
      Entity entity = damagesource.getTrueSource();
      if (isRidingOrBeingRiddenBy(entity)) {
        return true;
      }
      if (entity != this && this.world.getDifficulty().getDifficultyId()  > 0 && entity instanceof EntityLivingBase) {
        setAttackTarget((EntityLivingBase)entity);
        return true;
      }
      return false;
    }

    return false;
  }



  public boolean shouldAttackPlayers() {
    return (getBrightness() < 0.5F && super.shouldAttackPlayers());
  }


  protected SoundEvent getDeathSound() {
    return MoCSoundEvents.ENTITY_OGRE_DEATH;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    return MoCSoundEvents.ENTITY_OGRE_HURT;
  }


  protected SoundEvent getAmbientSound() {
    return MoCSoundEvents.ENTITY_OGRE_AMBIENT;
  }

  public boolean isFireStarter() {
    return false;
  }





  public float getDestroyForce() {
    return MoCreatures.proxy.ogreStrength;
  }

  public int getAttackRange() {
    return MoCreatures.proxy.ogreAttackRange;
  }


  public void onLivingUpdate() {
    if (!this.world.isRemote) {
      if (this.smashCounter > 0 && ++this.smashCounter > 10) {
        this.smashCounter = 0;
        performDestroyBlastAttack();
        MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageExplode(getEntityId()), new NetworkRegistry.TargetPoint(this.world.provider
              .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
      }

      if (getAttackTarget() != null && this.rand.nextInt(40) == 0 && this.smashCounter == 0 && this.attackCounter == 0) {
        startDestroyBlast();
      }
    }

    if (this.attackCounter > 0) {
      if (this.armToAnimate == 3) {
        this.attackCounter++;
      } else {
        this.attackCounter += 2;
      }

      if (this.attackCounter > 10) {
        this.attackCounter = 0;
        this.armToAnimate = 0;
      }
    }
    super.onLivingUpdate();
  }




  protected void startDestroyBlast() {
    this.smashCounter = 1;
    MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 3), new NetworkRegistry.TargetPoint(this.world.provider
          .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
  }




  public void performDestroyBlastAttack() {
    if (this.deathTime > 0) {
      return;
    }
    MoCTools.DestroyBlast((Entity)this, this.posX, this.posY + 1.0D, this.posZ, getDestroyForce(), isFireStarter());
  }


  protected boolean isHarmedByDaylight() {
    return false;
  }




  protected void startArmSwingAttack() {
    if (!this.world.isRemote) {
      if (this.smashCounter != 0) {
        return;
      }
      boolean leftArmW = ((getType() == 2 || getType() == 4 || getType() == 6) && this.rand.nextInt(2) == 0);

      if (leftArmW) {
        this.attackCounter = 1;
        this.armToAnimate = 1;
        MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 1), new NetworkRegistry.TargetPoint(this.world.provider
              .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
      } else {
        this.attackCounter = 1;
        this.armToAnimate = 2;
        MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 2), new NetworkRegistry.TargetPoint(this.world.provider
              .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
      }
    }
  }


  public void performAnimation(int animationType) {
    if (animationType != 0) {
      this.attackCounter = 1;
      this.armToAnimate = animationType;
    }
  }


  public int getMovingHead() {
    if (getType() == 1)
    {
      return 1;
    }

    if (this.rand.nextInt(60) == 0) {
      this.movingHead = this.rand.nextInt(2) + 2;
    }
    return this.movingHead;
  }


  public boolean attackEntityAsMob(Entity entityIn) {
    startArmSwingAttack();
    return super.attackEntityAsMob(entityIn);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\monster\MoCEntityOgre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
