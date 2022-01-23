package drzhark.mocreatures.entity.passive;

import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.MoCEntityAnimal;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.init.MoCSoundEvents;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class MoCEntityDuck extends MoCEntityAnimal {
  public float wingRotation = 0.0F; public boolean field_70885_d = false;
  public float destPos = 0.0F;
  public float oFlapSpeed;
  public float oFlap;
  public float wingRotDelta = 1.0F;

  public MoCEntityDuck(World world) {
    super(world);
    this.texture = "duck.png";
    setSize(0.4F, 0.7F);
  }


  protected void initEntityAI() {
    this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.tasks.addTask(1, (EntityAIBase)new EntityAIPanic((EntityCreature)this, 1.4D));
    this.tasks.addTask(5, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D));
    this.tasks.addTask(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0F));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
  }


  protected SoundEvent getDeathSound() {
    return MoCSoundEvents.ENTITY_DUCK_DEATH;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    return MoCSoundEvents.ENTITY_DUCK_HURT;
  }


  protected SoundEvent getAmbientSound() {
    return MoCSoundEvents.ENTITY_DUCK_AMBIENT;
  }


  protected boolean canDespawn() {
    if (MoCreatures.proxy.forceDespawns) {
      return !getIsTamed();
    }
    return false;
  }



  public void onLivingUpdate() {
    super.onLivingUpdate();
    this.oFlap = this.wingRotation;
    this.oFlapSpeed = this.destPos;
    this.destPos = (float)(this.destPos + (this.onGround ? -1 : 4) * 0.3D);

    if (this.destPos < 0.0F) {
      this.destPos = 0.0F;
    }

    if (this.destPos > 1.0F) {
      this.destPos = 1.0F;
    }

    if (!this.onGround && this.wingRotDelta < 1.0F) {
      this.wingRotDelta = 1.0F;
    }

    this.wingRotDelta = (float)(this.wingRotDelta * 0.9D);

    if (!this.onGround && this.motionY < 0.0D) {
      this.motionY *= 0.6D;
    }

    this.wingRotation += this.wingRotDelta * 2.0F;
  }



  public void fall(float f, float f1) {}


  protected Item getDropItem() {
    return Items.FEATHER;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityDuck.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
