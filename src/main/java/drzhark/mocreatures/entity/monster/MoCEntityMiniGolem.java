package drzhark.mocreatures.entity.monster;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
import drzhark.mocreatures.entity.item.MoCEntityThrowableRock;
import drzhark.mocreatures.entity.MoCEntityMob;
import drzhark.mocreatures.init.MoCSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MoCEntityMiniGolem extends MoCEntityMob {
  public int tcounter;
  private static final DataParameter<Boolean> ANGRY = EntityDataManager.createKey(MoCEntityMiniGolem.class, DataSerializers.BOOLEAN); public MoCEntityThrowableRock tempRock;
  private static final DataParameter<Boolean> HAS_ROCK = EntityDataManager.createKey(MoCEntityMiniGolem.class, DataSerializers.BOOLEAN);


  public MoCEntityMiniGolem(World world) {
    super(world);
    this.texture = "minigolem.png";
    setSize(1.0F, 1.0F);
  }


  protected void initEntityAI() {
    this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
    this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
  }


  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(ANGRY, Boolean.valueOf(false));
    this.dataManager.register(HAS_ROCK, Boolean.valueOf(false));
  }

  public boolean getIsAngry() {
    return ((Boolean)this.dataManager.get(ANGRY)).booleanValue();
  }

  public void setIsAngry(boolean flag) {
    this.dataManager.set(ANGRY, Boolean.valueOf(flag));
  }

  public boolean getHasRock() {
    return ((Boolean)this.dataManager.get(HAS_ROCK)).booleanValue();
  }

  public void setHasRock(boolean flag) {
    this.dataManager.set(HAS_ROCK, Boolean.valueOf(flag));
  }


  public void onLivingUpdate() {
    super.onLivingUpdate();

    if (!this.world.isRemote) {
      if (getAttackTarget() == null) {
        setIsAngry(false);
      } else {

        setIsAngry(true);
      }

      if (getIsAngry() && getAttackTarget() != null) {
        if (!getHasRock() && this.rand.nextInt(30) == 0) {
          acquireTRock();
        }

        if (getHasRock()) {
          getNavigator().clearPath();
          attackWithTRock();
        }
      }
    }
  }

  protected void acquireTRock() {
    IBlockState tRockState = MoCTools.destroyRandomBlockWithIBlockState((Entity)this, 3.0D);
    if (tRockState == null) {
      this.tcounter = 1;
      setHasRock(false);

      return;
    }

    MoCEntityThrowableRock trock = new MoCEntityThrowableRock(this.world, (Entity)this, this.posX, this.posY + 1.5D, this.posZ);
    this.world.spawnEntity((Entity)trock);
    trock.setState(tRockState);
    trock.setBehavior(1);
    this.tempRock = trock;
    setHasRock(true);
  }


  public boolean isMovementCeased() {
    return (getHasRock() && getAttackTarget() != null);
  }




  protected void attackWithTRock() {
    this.tcounter++;

    if (this.tcounter < 50) {

      this.tempRock.posX = this.posX;
      this.tempRock.posY = this.posY + 1.0D;
      this.tempRock.posZ = this.posZ;
    }

    if (this.tcounter >= 50) {

      if (getAttackTarget() != null && getDistance((Entity)getAttackTarget()) < 48.0F) {
        MoCTools.ThrowStone((Entity)this, (Entity)getAttackTarget(), this.tempRock.getState(), 10.0D, 0.25D);
      }

      this.tempRock.setDead();
      setHasRock(false);
      this.tcounter = 0;
    }
  }





  public float getSizeFactor() {
    return 1.0F;
  }





  protected void playStepSound(BlockPos pos, Block block) {
    MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GOLEM_WALK);
  }


  protected SoundEvent getDeathSound() {
    return MoCSoundEvents.ENTITY_GOLEM_DYING;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    return MoCSoundEvents.ENTITY_GOLEM_HURT;
  }


  protected SoundEvent getAmbientSound() {
    return MoCSoundEvents.ENTITY_GOLEM_AMBIENT;
  }


  protected boolean isHarmedByDaylight() {
    return true;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\monster\MoCEntityMiniGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
