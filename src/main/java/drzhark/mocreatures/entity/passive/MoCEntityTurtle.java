package drzhark.mocreatures.entity.passive;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.IMoCTameable;
import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
import drzhark.mocreatures.entity.ai.EntityAIFollowOwnerPlayer;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.init.MoCItems;
import drzhark.mocreatures.init.MoCSoundEvents;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class MoCEntityTurtle extends MoCEntityTameableAnimal {
  private boolean isSwinging;
  private static final DataParameter<Boolean> IS_UPSIDE_DOWN = EntityDataManager.createKey(MoCEntityTurtle.class, DataSerializers.BOOLEAN); private boolean twistright; private int flopcounter;
  private static final DataParameter<Boolean> IS_HIDING = EntityDataManager.createKey(MoCEntityTurtle.class, DataSerializers.BOOLEAN);

  public MoCEntityTurtle(World world) {
    super(world);
    setSize(0.6F, 0.4F);
    setAdult(false);
    setEdad(60 + this.rand.nextInt(50));
  }


  protected void initEntityAI() {
    this.tasks.addTask(1, (EntityAIBase)new EntityAIFollowOwnerPlayer((EntityLiving)this, 0.8D, 2.0F, 10.0F));
    this.tasks.addTask(5, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 0.8D, 50));
    this.tasks.addTask(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0F));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.15D);
  }


  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(IS_UPSIDE_DOWN, Boolean.valueOf(false));
    this.dataManager.register(IS_HIDING, Boolean.valueOf(false));
  }


  public ResourceLocation getTexture() {
    String tempText = "turtle.png";

    if (getPetName().equals("Donatello") || getPetName().equals("donatello")) {
      tempText = "turtled.png";
    }

    if (getPetName().equals("Leonardo") || getPetName().equals("leonardo")) {
      tempText = "turtlel.png";
    }

    if (getPetName().equals("Rafael") || getPetName().equals("rafael") || getPetName().equals("raphael") || getPetName().equals("Raphael")) {
      tempText = "turtler.png";
    }

    if (getPetName().equals("Michelangelo") || getPetName().equals("michelangelo") || getPetName().equals("Michaelangelo") ||
      getPetName().equals("michaelangelo")) {
      tempText = "turtlem.png";
    }

    return MoCreatures.proxy.getTexture(tempText);
  }

  public boolean getIsHiding() {
    return ((Boolean)this.dataManager.get(IS_HIDING)).booleanValue();
  }

  public boolean getIsUpsideDown() {
    return ((Boolean)this.dataManager.get(IS_UPSIDE_DOWN)).booleanValue();
  }

  public void setIsHiding(boolean flag) {
    this.dataManager.set(IS_HIDING, Boolean.valueOf(flag));
  }

  public void setIsUpsideDown(boolean flag) {
    this.flopcounter = 0;
    this.swingProgress = 0.0F;
    this.dataManager.set(IS_UPSIDE_DOWN, Boolean.valueOf(flag));
  }


  public double getYOffset() {
    if (getRidingEntity() instanceof EntityPlayer) {
      if (((EntityPlayer)getRidingEntity()).isSneaking()) {
        return -0.25D + (300.0D - getEdad()) / 500.0D;
      }
      return (300.0D - getEdad()) / 500.0D;
    }

    return super.getYOffset();
  }


  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    Boolean tameResult = processTameInteract(player, hand);
    if (tameResult != null) {
      return tameResult.booleanValue();
    }

    if (getIsTamed()) {
      if (getIsUpsideDown()) {
        flipflop(false);
        return true;
      }
      if (getRidingEntity() == null &&
        startRiding((Entity)player)) {
        this.rotationYaw = player.rotationYaw;
      }

      return true;
    }

    flipflop(!getIsUpsideDown());

    return super.processInteract(player, hand);
  }


  protected void jump() {
    if (isInsideOfMaterial(Material.WATER)) {
      this.motionY = 0.3D;
      if (isSprinting()) {
        float f = this.rotationYaw * 0.01745329F;
        this.motionX -= (MathHelper.sin(f) * 0.2F);
        this.motionZ += (MathHelper.cos(f) * 0.2F);
      }
      this.isAirBorne = true;
    }
  }


  public boolean isNotScared() {
    return true;
  }


  public void onLivingUpdate() {
    super.onLivingUpdate();
    if (!this.world.isRemote &&
      !getIsUpsideDown() && !getIsTamed()) {
      EntityLivingBase entityliving = getBoogey(4.0D);
      if (entityliving != null && canEntityBeSeen((Entity)entityliving)) {
        if (!getIsHiding() && !isInWater()) {
          MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_TURTLE_ANGRY);
          setIsHiding(true);
        }

        getNavigator().clearPath();
      } else {

        setIsHiding(false);
        if (!hasPath() && this.rand.nextInt(50) == 0) {
          EntityItem entityitem = getClosestItem((Entity)this, 10.0D, Items.MELON, Items.REEDS);
          if (entityitem != null) {
            float f = entityitem.getDistance((Entity)this);
            if (f > 2.0F) {
              getMyOwnPath((Entity)entityitem, f);
            }
            if (f < 2.0F && entityitem != null && this.deathTime == 0) {
              entityitem.setDead();
              MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_TURTLE_EATING);
              EntityPlayer entityplayer = this.world.getClosestPlayerToEntity((Entity)this, 24.0D);
              if (entityplayer != null) {
                MoCTools.tameWithName(entityplayer, (IMoCTameable)this);
              }
            }
          }
        }
      }
    }
  }



  public boolean swimmerEntity() {
    return false;
  }


  public boolean canBreatheUnderwater() {
    return true;
  }


  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    Entity entity = damagesource.getTrueSource();
    if (getRidingEntity() != null) {
      return false;
    }
    if (entity == null) {
      return super.attackEntityFrom(damagesource, i);
    }
    if (getIsHiding()) {
      if (this.rand.nextInt(10) == 0) {
        flipflop(true);
      }
      return false;
    }
    boolean flag = super.attackEntityFrom(damagesource, i);
    if (this.rand.nextInt(3) == 0) {
      flipflop(true);
    }
    return flag;
  }


  public void flipflop(boolean flip) {
    setIsUpsideDown(flip);
    setIsHiding(false);
    getNavigator().clearPath();
  }


  public boolean entitiesToIgnore(Entity entity) {
    return (entity instanceof MoCEntityTurtle || (entity.height <= this.height && entity.width <= this.width) || super
      .entitiesToIgnore(entity));
  }


  public void onUpdate() {
    super.onUpdate();

    if (getRidingEntity() != null && getRidingEntity() instanceof EntityPlayer) {
      EntityPlayer entityplayer = (EntityPlayer)getRidingEntity();
      if (entityplayer != null) {
        this.rotationYaw = entityplayer.rotationYaw;
      }
    }

    if (getIsTamed() && getEdad() < 300 && this.rand.nextInt(900) == 0) {
      setEdad(getEdad() + 1);
    }
    if (getIsUpsideDown() && isInWater()) {
      setIsUpsideDown(false);
    }
    if (getIsUpsideDown() && getRidingEntity() == null && this.rand.nextInt(20) == 0) {
      setSwinging(true);
      this.flopcounter++;
    }

    if (getIsSwinging()) {
      this.swingProgress += 0.2F;

      boolean flag = (this.flopcounter > this.rand.nextInt(3) + 8);

      if (this.swingProgress > 2.0F && (!flag || this.rand.nextInt(20) == 0)) {
        setSwinging(false);
        this.swingProgress = 0.0F;
        if (this.rand.nextInt(2) == 0) {
          this.twistright = !this.twistright;
        }
      }
      else if (this.swingProgress > 9.0F && flag) {
        setSwinging(false);
        MoCTools.playCustomSound((Entity)this, SoundEvents.ENTITY_CHICKEN_EGG);
        setIsUpsideDown(false);
      }
    }
  }

  public boolean getIsSwinging() {
    return this.isSwinging;
  }

  public void setSwinging(boolean flag) {
    this.isSwinging = flag;
  }


  public boolean isMovementCeased() {
    return (getIsUpsideDown() || getIsHiding());
  }

  public int getFlipDirection() {
    if (this.twistright) {
      return 1;
    }
    return -1;
  }


  public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
    super.readEntityFromNBT(nbttagcompound);
    setIsUpsideDown(nbttagcompound.getBoolean("UpsideDown"));
  }


  public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
    super.writeEntityToNBT(nbttagcompound);
    nbttagcompound.setBoolean("UpsideDown", getIsUpsideDown());
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    return MoCSoundEvents.ENTITY_TURTLE_HURT;
  }


  protected SoundEvent getAmbientSound() {
    return MoCSoundEvents.ENTITY_TURTLE_AMBIENT;
  }


  protected SoundEvent getDeathSound() {
    return MoCSoundEvents.ENTITY_TURTLE_DEATH;
  }


  protected Item getDropItem() {
    if (getPetName().equals("Donatello") || getPetName().equals("donatello")) {
      return (Item)MoCItems.bo;
    }

    if (getPetName().equals("Leonardo") || getPetName().equals("leonardo")) {
      return (Item)MoCItems.katana;
    }

    if (getPetName().equals("Rafael") || getPetName().equals("rafael") || getPetName().equals("raphael") || getPetName().equals("Raphael")) {
      return (Item)MoCItems.sai;
    }

    if (getPetName().equals("Michelangelo") || getPetName().equals("michelangelo") || getPetName().equals("Michaelangelo") ||
      getPetName().equals("michaelangelo")) {
      return (Item)MoCItems.nunchaku;
    }
    return (Item)MoCItems.turtleraw;
  }






  public boolean isTMNT() {
    if (getPetName().equals("Donatello") || getPetName().equals("donatello") || getPetName().equals("Leonardo") || getPetName().equals("leonardo") ||
      getPetName().equals("Rafael") || getPetName().equals("rafael") || getPetName().equals("raphael") || getPetName().equals("Raphael") ||
      getPetName().equals("Michelangelo") || getPetName().equals("michelangelo") || getPetName().equals("Michaelangelo") ||
      getPetName().equals("michaelangelo")) {
      return true;
    }
    return false;
  }












  public boolean isMyHealFood(ItemStack stack) {
    return (!stack.isEmpty() && (stack.getItem() == Items.REEDS || stack.getItem() == Items.MELON));
  }


  public int getMaxSpawnedInChunk() {
    return 2;
  }


  public int nameYOffset() {
    return -10 - getEdad() / 5;
  }


  public boolean isPushedByWater() {
    return true;
  }


  public boolean isAmphibian() {
    return true;
  }


  public float getAIMoveSpeed() {
    if (isInWater()) {
      return 0.08F;
    }
    return 0.12F;
  }


  protected double minDivingDepth() {
    return (getEdad() + 8.0D) / 340.0D;
  }


  protected double maxDivingDepth() {
    return 1.0D * getEdad() / 100.0D;
  }


  public int getMaxEdad() {
    return 120;
  }



  public boolean canRidePlayer() {
    return true;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityTurtle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
