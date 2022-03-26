package drzhark.mocreatures.entity.passive;

import com.google.common.base.Predicate;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.IMoCTameable;
import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
import drzhark.mocreatures.entity.ai.EntityAIFleeFromEntityMoC;
import drzhark.mocreatures.entity.ai.EntityAIFollowOwnerPlayer;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.init.MoCSoundEvents;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MoCEntityBird
  extends MoCEntityTameableAnimal
{
  private boolean fleeing;
  public float wingb;
  public float wingc;
  public float wingd;
  public static final String[] birdNames = new String[] { "Dove", "Crow", "Parrot", "Blue", "Canary", "Red" }; public float winge; public float wingh; public boolean textureSet; private int jumpTimer; protected EntityAIWanderMoC2 wander;
  private static final DataParameter<Boolean> PRE_TAMED = EntityDataManager.createKey(MoCEntityBird.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Boolean> IS_FLYING = EntityDataManager.createKey(MoCEntityBird.class, DataSerializers.BOOLEAN);

  public MoCEntityBird(World world) {
    super(world);
    setSize(0.4F, 0.3F);
    this.collidedVertically = true;
    this.wingb = 0.0F;
    this.wingc = 0.0F;
    this.wingh = 1.0F;
    this.fleeing = false;
    this.textureSet = false;
    setTamed(false);
    this.stepHeight = 1.0F;
  }


  protected void initEntityAI() {
    this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.tasks.addTask(2, (EntityAIBase)new EntityAIFleeFromEntityMoC((EntityCreature)this, new Predicate<Entity>()
          {
            public boolean apply(Entity entity) {
              return (!(entity instanceof MoCEntityBird) && (entity.height > 0.4F || entity.width > 0.4F));
            }
          },  6.0F, 1.0D, 1.3D));
    this.tasks.addTask(3, (EntityAIBase)new EntityAIFollowOwnerPlayer((EntityLiving)this, 0.8D, 2.0F, 10.0F));
    this.tasks.addTask(4, (EntityAIBase)(this.wander = new EntityAIWanderMoC2((EntityCreature)this, 1.0D, 80)));
    this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
  }


  public void selectType() {
    if (getType() == 0) {
      setType(this.rand.nextInt(6) + 1);
    }
  }



  public ResourceLocation getTexture() {
    switch (getType()) {
      case 1:
        return MoCreatures.proxy.getTexture("birdwhite.png");
      case 2:
        return MoCreatures.proxy.getTexture("birdblack.png");
      case 3:
        return MoCreatures.proxy.getTexture("birdgreen.png");
      case 4:
        return MoCreatures.proxy.getTexture("birdblue.png");
      case 5:
        return MoCreatures.proxy.getTexture("birdyellow.png");
      case 6:
        return MoCreatures.proxy.getTexture("birdred.png");
    }

    return MoCreatures.proxy.getTexture("birdblue.png");
  }



  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(PRE_TAMED, Boolean.valueOf(false));
    this.dataManager.register(IS_FLYING, Boolean.valueOf(false));
  }

  public boolean getPreTamed() {
    return ((Boolean)this.dataManager.get(PRE_TAMED)).booleanValue();
  }

  public void setPreTamed(boolean flag) {
    this.dataManager.set(PRE_TAMED, Boolean.valueOf(flag));
  }

  public boolean getIsFlying() {
    return ((Boolean)this.dataManager.get(IS_FLYING)).booleanValue();
  }

  public void setIsFlying(boolean flag) {
    this.dataManager.set(IS_FLYING, Boolean.valueOf(flag));
  }


  public void fall(float f, float f1) {}



  private boolean FlyToNextEntity(Entity entity) {
    if (entity != null) {
      int i = MathHelper.floor(entity.posX);
      int j = MathHelper.floor(entity.posY);
      int k = MathHelper.floor(entity.posZ);
      faceLocation(i, j, k, 30.0F);
      if (MathHelper.floor(this.posY) < j) {
        this.motionY += 0.15D;
      }
      if (this.posX < entity.posX) {
        double d = entity.posX - this.posX;
        if (d > 0.5D) {
          this.motionX += 0.05D;
        }
      } else {
        double d1 = this.posX - entity.posX;
        if (d1 > 0.5D) {
          this.motionX -= 0.05D;
        }
      }
      if (this.posZ < entity.posZ) {
        double d2 = entity.posZ - this.posZ;
        if (d2 > 0.5D) {
          this.motionZ += 0.05D;
        }
      } else {
        double d3 = this.posZ - entity.posZ;
        if (d3 > 0.5D) {
          this.motionZ -= 0.05D;
        }
      }
      return true;
    }
    return false;
  }



  protected Item getDropItem() {
    return Items.FEATHER;
  }


  protected SoundEvent getDeathSound() {
    return MoCSoundEvents.ENTITY_BIRD_DEATH;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    return MoCSoundEvents.ENTITY_BIRD_HURT;
  }


  protected SoundEvent getAmbientSound() {
    if (getType() == 1) {
      return MoCSoundEvents.ENTITY_BIRD_AMBIENT_WHITE;
    }
    if (getType() == 2) {
      return MoCSoundEvents.ENTITY_BIRD_AMBIENT_BLACK;
    }
    if (getType() == 3) {
      return MoCSoundEvents.ENTITY_BIRD_AMBIENT_GREEN;
    }
    if (getType() == 4) {
      return MoCSoundEvents.ENTITY_BIRD_AMBIENT_BLUE;
    }
    if (getType() == 5) {
      return MoCSoundEvents.ENTITY_BIRD_AMBIENT_YELLOW;
    }
    return MoCSoundEvents.ENTITY_BIRD_AMBIENT_RED;
  }



  public double getYOffset() {
    if (getRidingEntity() instanceof EntityPlayer) {
      return ((EntityPlayer)getRidingEntity()).isSneaking() ? 0.2D : 0.44999998807907104D;
    }

    return super.getYOffset();
  }


  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    Boolean tameResult = processTameInteract(player, hand);
    if (tameResult != null) {
      return tameResult.booleanValue();
    }

    ItemStack stack = player.getHeldItem(hand);
    if (!stack.isEmpty() && getPreTamed() && !getIsTamed() && stack.getItem() == Items.WHEAT_SEEDS) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      if (!this.world.isRemote) {
        MoCTools.tameWithName(player, (IMoCTameable)this);
      }
      return true;
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


  public void onLivingUpdate() {
    super.onLivingUpdate();

    this.winge = this.wingb;
    this.wingd = this.wingc;
    this.wingc = (float)(this.wingc + (this.onGround ? -1 : 4) * 0.3D);
    if (this.wingc < 0.0F) {
      this.wingc = 0.0F;
    }
    if (this.wingc > 1.0F) {
      this.wingc = 1.0F;
    }
    if (!this.onGround && this.wingh < 1.0F) {
      this.wingh = 1.0F;
    }
    this.wingh = (float)(this.wingh * 0.9D);
    if (!this.onGround && this.motionY < 0.0D) {
      this.motionY *= 0.8D;
    }
    this.wingb += this.wingh * 2.0F;


    if (!this.world.isRemote) {

      if (isMovementCeased() && getIsFlying()) {
        setIsFlying(false);
      }

      if (getIsFlying() && getNavigator().noPath() && !isMovementCeased() && getAttackTarget() == null && this.rand.nextInt(30) == 0) {
        this.wander.makeUpdate();
      }

      if (!getIsFlying() && !getIsTamed() && this.rand.nextInt(10) == 0) {
        List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(4.0D, 4.0D, 4.0D));
        for (int i = 0; i < list.size(); i++) {
          Entity entity1 = list.get(i);
          if (entity1 instanceof EntityLivingBase && !(entity1 instanceof MoCEntityBird))
          {

            if (((EntityLivingBase)entity1).width >= 0.4F && ((EntityLivingBase)entity1).height >= 0.4F && canEntityBeSeen(entity1)) {
              setIsFlying(true);
              this.fleeing = true;
              this.wander.makeUpdate();
            }
          }
        }
      }
      if (!isMovementCeased() && !getIsFlying() && this.rand.nextInt(getIsTamed() ? 1000 : 400) == 0) {
        setIsFlying(true);
        this.wander.makeUpdate();
      }

      if (getIsFlying() && this.rand.nextInt(200) == 0) {
        setIsFlying(false);
      }

      if (this.fleeing && this.rand.nextInt(50) == 0) {
        this.fleeing = false;
      }


      if (!this.fleeing) {
        EntityItem entityitem = getClosestItem((Entity)this, 12.0D, Items.WHEAT_SEEDS, Items.MELON_SEEDS);
        if (entityitem != null) {
          FlyToNextEntity((Entity)entityitem);
          EntityItem entityitem1 = getClosestItem((Entity)this, 1.0D, Items.WHEAT_SEEDS, Items.MELON_SEEDS);
          if (this.rand.nextInt(50) == 0 && entityitem1 != null) {
            entityitem1.setDead();
            setPreTamed(true);
          }
        }
      }
      if (this.rand.nextInt(10) == 0 && isInsideOfMaterial(Material.WATER)) {
        WingFlap();
      }
    }
  }


  public void onUpdate() {
    super.onUpdate();

    if (getRidingEntity() != null) {
      this.rotationYaw = (getRidingEntity()).rotationYaw;
    }

    if (getRidingEntity() != null && getRidingEntity() instanceof EntityPlayer) {
      EntityPlayer entityplayer = (EntityPlayer)getRidingEntity();
      this.rotationYaw = entityplayer.rotationYaw;
      entityplayer.fallDistance = 0.0F;
      if (entityplayer.motionY < -0.1D) {
        entityplayer.motionY *= 0.6D;
      }
    }
    if (--this.jumpTimer <= 0 && this.onGround && (this.motionX > 0.05D || this.motionZ > 0.05D || this.motionX < -0.05D || this.motionZ < -0.05D)) {

      this.motionY = 0.25D;
      float velX = MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F);
      float velZ = MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F);

      this.motionX += (-0.2F * velX);
      this.motionZ += (0.2F * velZ);
      this.jumpTimer = 15;
    }
  }

  public int[] ReturnNearestMaterialCoord(Entity entity, Material material, Double double1) {
    AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().expand(double1.doubleValue(), double1.doubleValue(), double1.doubleValue());
    int i = MathHelper.floor(axisalignedbb.minX);
    int j = MathHelper.floor(axisalignedbb.maxX + 1.0D);
    int k = MathHelper.floor(axisalignedbb.minY);
    int l = MathHelper.floor(axisalignedbb.maxY + 1.0D);
    int i1 = MathHelper.floor(axisalignedbb.minZ);
    int j1 = MathHelper.floor(axisalignedbb.maxZ + 1.0D);
    for (int k1 = i; k1 < j; k1++) {
      for (int l1 = k; l1 < l; l1++) {
        for (int i2 = i1; i2 < j1; i2++) {
          BlockPos pos = new BlockPos(k1, l1, i2);
          IBlockState blockstate = this.world.getBlockState(pos);
          if (blockstate.getBlock() != null && !blockstate.getBlock().isAir(blockstate, (IBlockAccess)this.world, pos) && blockstate
            .getMaterial() == material) {
            return new int[] { k1, l1, i2 };
          }
        }
      }
    }



    return new int[] { -1, 0, 0 };
  }


  public void setDead() {
    if (!this.world.isRemote && getIsTamed() && getHealth() > 0.0F) {
      return;
    }
    super.setDead();
  }



  private void WingFlap() {
    this.motionY += 0.05D;
    if (this.rand.nextInt(30) == 0) {
      this.motionX += 0.2D;
    }
    if (this.rand.nextInt(30) == 0) {
      this.motionX -= 0.2D;
    }
    if (this.rand.nextInt(30) == 0) {
      this.motionZ += 0.2D;
    }
    if (this.rand.nextInt(30) == 0) {
      this.motionZ -= 0.2D;
    }
  }


  public int nameYOffset() {
    return -40;
  }


  public boolean isMyHealFood(ItemStack stack) {
    return (!stack.isEmpty() && (stack.getItem() == Items.WHEAT_SEEDS || stack.getItem() == Items.MELON_SEEDS));
  }


  public boolean isNotScared() {
    return getIsTamed();
  }


  public boolean isFlyer() {
    return true;
  }


  public int maxFlyingHeight() {
    if (getIsTamed())
      return 4;
    return 6;
  }


  public int minFlyingHeight() {
    return 2;
  }



  public boolean canRidePlayer() {
    return true;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityBird.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
