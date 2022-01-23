package drzhark.mocreatures.entity.passive;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
import drzhark.mocreatures.init.MoCItems;
import drzhark.mocreatures.init.MoCSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class MoCEntityMole extends MoCEntityTameableAnimal {
  private static final DataParameter<Integer> MOLE_STATE = EntityDataManager.createKey(MoCEntityMole.class, DataSerializers.VARINT);

  public MoCEntityMole(World world) {
    super(world);
    setSize(1.0F, 0.5F);
  }


  protected void initEntityAI() {
    this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.tasks.addTask(2, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D));
    this.tasks.addTask(4, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
  }


  public ResourceLocation getTexture() {
    return MoCreatures.proxy.getTexture("mole.png");
  }


  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(MOLE_STATE, Integer.valueOf(0));
  }





  public boolean isOnDirt() {
    Block block = this.world.getBlockState(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor((getEntityBoundingBox()).minY - 0.5D), MathHelper.floor(this.posZ))).getBlock();
    return isDiggableBlock(Block.getIdFromBlock(block));
  }

  private boolean isDiggableBlock(int i) {
    return ((i == 2)) | ((i == 3)) | ((i == 12) ? true : false);
  }





  private void digForward() {
    double coordY = this.posY;
    double coordZ = this.posZ;
    double coordX = this.posX;
    int x = 1;
    double newPosY = coordY - Math.cos(((this.rotationPitch - 90.0F) / 57.29578F)) * x;

    double newPosX = coordX + Math.cos((MoCTools.realAngle(this.rotationYaw - 90.0F) / 57.29578F)) * Math.sin(((this.rotationPitch - 90.0F) / 57.29578F)) * x;

    double newPosZ = coordZ + Math.sin((MoCTools.realAngle(this.rotationYaw - 90.0F) / 57.29578F)) * Math.sin(((this.rotationPitch - 90.0F) / 57.29578F)) * x;



    Block block = this.world.getBlockState(new BlockPos(MathHelper.floor(newPosX), MathHelper.floor(newPosY), MathHelper.floor(newPosZ))).getBlock();
    if (isDiggableBlock(Block.getIdFromBlock(block))) {
      setPosition(newPosX, newPosY, newPosZ);
    }
  }






  public int getState() {
    return ((Integer)this.dataManager.get(MOLE_STATE)).intValue();
  }






  public void setState(int i) {
    this.dataManager.set(MOLE_STATE, Integer.valueOf(i));
  }



  public float pitchRotationOffset() {
    int i = getState();
    switch (i) {
      case 0:
        return 0.0F;
      case 1:
        return -45.0F;
      case 2:
        return 0.0F;
      case 3:
        return 60.0F;
    }
    return 0.0F;
  }



  public float getAdjustedYOffset() {
    int i = getState();
    switch (i) {
      case 0:
        return 0.0F;
      case 1:
        return 0.3F;
      case 2:
        return 1.0F;
      case 3:
        return 0.1F;
    }
    return 0.0F;
  }



  public void onLivingUpdate() {
    super.onLivingUpdate();

    if (!this.world.isRemote) {
      if (this.rand.nextInt(10) == 0 && getState() == 1) {
        setState(2);
      }

      if (getState() != 2 && getState() != 1 && isOnDirt()) {
        EntityLivingBase entityliving = getBoogey(4.0D);
        if (entityliving != null && canEntityBeSeen((Entity)entityliving)) {
          setState(1);
          getNavigator().clearPath();
        }
      }


      if (this.rand.nextInt(20) == 0 && getState() == 2 && getBoogey(4.0D) == null) {
        setState(3);
        getNavigator().clearPath();
      }


      if (getState() != 0 && !isOnDirt()) {
        setState(0);
      }

      if (this.rand.nextInt(30) == 0 && getState() == 3) {
        setState(2);
      }






      if (getState() == 1 || getState() == 2) {
        setSprinting(true);
      } else {
        setSprinting(false);
      }
    }
  }


  public boolean isMovementCeased() {
    return (getState() == 1 || getState() == 3);
  }


  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    if (getState() != 2) {
      return super.attackEntityFrom(damagesource, i);
    }
    return false;
  }


  public boolean canBeCollidedWith() {
    return (getState() != 2);
  }


  public boolean canBePushed() {
    return (getState() != 2);
  }


  protected void collideWithEntity(Entity par1Entity) {
    if (getState() != 2) {
      super.collideWithEntity(par1Entity);
    }
  }



  public boolean isEntityInsideOpaqueBlock() {
    if (getState() == 2) {
      return false;
    }
    return super.isEntityInsideOpaqueBlock();
  }


  public void onDeath(DamageSource damagesource) {
    super.onDeath(damagesource);
  }


  public boolean isEntityInvulnerable(DamageSource source) {
    if (getState() == 2) {
      return true;
    }
    return super.isEntityInvulnerable(source);
  }


  protected Item getDropItem() {
    return (Item)MoCItems.fur;
  }


  protected SoundEvent getDeathSound() {
    return MoCSoundEvents.ENTITY_RABBIT_DEATH;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    return MoCSoundEvents.ENTITY_RABBIT_HURT;
  }


  protected SoundEvent getAmbientSound() {
    return null;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityMole.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
