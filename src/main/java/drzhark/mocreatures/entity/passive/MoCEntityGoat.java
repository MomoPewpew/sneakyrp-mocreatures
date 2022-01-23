package drzhark.mocreatures.entity.passive;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.IMoCTameable;
import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
import drzhark.mocreatures.entity.ai.EntityAIFollowAdult;
import drzhark.mocreatures.entity.ai.EntityAIPanicMoC;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.init.MoCSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;



public class MoCEntityGoat
  extends MoCEntityTameableAnimal
{
  private boolean hungry;
  private boolean swingLeg;
  private boolean swingEar;
  private boolean swingTail;
  private boolean bleat;
  private boolean eating;
  private static final DataParameter<Boolean> IS_CHARGING = EntityDataManager.createKey(MoCEntityGoat.class, DataSerializers.BOOLEAN); private int bleatcount; private int attacking; public int movecount; private int chargecount; private int tailcount; private int earcount; private int eatcount;
  private static final DataParameter<Boolean> IS_UPSET = EntityDataManager.createKey(MoCEntityGoat.class, DataSerializers.BOOLEAN);

  public MoCEntityGoat(World world) {
    super(world);
    setSize(0.8F, 1.0F);
    setEdad(70);
  }


  protected void initEntityAI() {
    this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.tasks.addTask(2, (EntityAIBase)new EntityAIPanicMoC((EntityCreature)this, 1.0D));
    this.tasks.addTask(4, (EntityAIBase)new EntityAIFollowAdult((EntityLiving)this, 1.0D));
    this.tasks.addTask(5, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
    this.tasks.addTask(6, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D));
    this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12.0D);
    getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
  }


  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(IS_CHARGING, Boolean.valueOf(false));
    this.dataManager.register(IS_UPSET, Boolean.valueOf(false));
  }

  public boolean getUpset() {
    return ((Boolean)this.dataManager.get(IS_UPSET)).booleanValue();
  }

  public boolean getCharging() {
    return ((Boolean)this.dataManager.get(IS_CHARGING)).booleanValue();
  }

  public void setUpset(boolean flag) {
    this.dataManager.set(IS_UPSET, Boolean.valueOf(flag));
  }

  public void setCharging(boolean flag) {
    this.dataManager.set(IS_CHARGING, Boolean.valueOf(flag));
  }






  public void selectType() {
    if (getType() == 0) {
      int i = this.rand.nextInt(100);
      if (i <= 15) {
        setType(1);
        setEdad(50);
      } else if (i <= 30) {
        setType(2);
        setEdad(70);
      } else if (i <= 45) {
        setType(3);
        setEdad(70);
      } else if (i <= 60) {
        setType(4);
        setEdad(70);
      } else if (i <= 75) {
        setType(5);
        setEdad(90);
      } else if (i <= 90) {
        setType(6);
        setEdad(90);
      } else {
        setType(7);
        setEdad(90);
      }
    }
  }



  public ResourceLocation getTexture() {
    switch (getType()) {
      case 1:
        return MoCreatures.proxy.getTexture("goat1.png");
      case 2:
        return MoCreatures.proxy.getTexture("goat2.png");
      case 3:
        return MoCreatures.proxy.getTexture("goat3.png");
      case 4:
        return MoCreatures.proxy.getTexture("goat4.png");
      case 5:
        return MoCreatures.proxy.getTexture("goat5.png");
      case 6:
        return MoCreatures.proxy.getTexture("goat6.png");
      case 7:
        return MoCreatures.proxy.getTexture("goat1.png");
    }

    return MoCreatures.proxy.getTexture("goat1.png");
  }


  public void calm() {
    setAttackTarget(null);
    setUpset(false);
    setCharging(false);
    this.attacking = 0;
    this.chargecount = 0;
  }


  protected void jump() {
    if (getType() == 1) {
      this.motionY = 0.41D;
    } else if (getType() < 5) {
      this.motionY = 0.45D;
    } else {
      this.motionY = 0.5D;
    }

    if (isPotionActive(MobEffects.JUMP_BOOST)) {
      this.motionY += ((getActivePotionEffect(MobEffects.JUMP_BOOST).getAmplifier() + 1) * 0.1F);
    }
    if (isSprinting()) {
      float f = this.rotationYaw * 0.01745329F;
      this.motionX -= (MathHelper.sin(f) * 0.2F);
      this.motionZ += (MathHelper.cos(f) * 0.2F);
    }
    this.isAirBorne = true;
  }


  public void onLivingUpdate() {
    super.onLivingUpdate();
    if (this.world.isRemote) {
      if (this.rand.nextInt(100) == 0) {
        setSwingEar(true);
      }

      if (this.rand.nextInt(80) == 0) {
        setSwingTail(true);
      }

      if (this.rand.nextInt(50) == 0) {
        setEating(true);
      }
    }
    if (getBleating()) {
      this.bleatcount++;
      if (this.bleatcount > 15) {
        this.bleatcount = 0;
        setBleating(false);
      }
    }


    if (this.hungry && this.rand.nextInt(20) == 0) {
      this.hungry = false;
    }

    if (!this.world.isRemote && (getEdad() < 90 || (getType() > 4 && getEdad() < 100)) && this.rand.nextInt(500) == 0) {
      setEdad(getEdad() + 1);
      if (getType() == 1 && getEdad() > 70) {
        int i = this.rand.nextInt(6) + 2;
        setType(i);
      }
    }


    if (getUpset()) {
      this.attacking += this.rand.nextInt(4) + 2;
      if (this.attacking > 75) {
        this.attacking = 75;
      }

      if (this.rand.nextInt(200) == 0 || getAttackTarget() == null) {
        calm();
      }

      if (!getCharging() && this.rand.nextInt(35) == 0) {
        swingLeg();
      }

      if (!getCharging()) {
        getNavigator().clearPath();
      }

      if (getAttackTarget() != null) {

        faceEntity((Entity)getAttackTarget(), 10.0F, 10.0F);
        if (this.rand.nextInt(80) == 0) {
          setCharging(true);
        }
      }
    }

    if (getCharging()) {
      this.chargecount++;
      if (this.chargecount > 120) {
        this.chargecount = 0;
      }
      if (getAttackTarget() == null) {
        calm();
      }
    }

    if (!getUpset() && !getCharging()) {
      EntityPlayer entityplayer1 = this.world.getClosestPlayerToEntity((Entity)this, 24.0D);
      if (entityplayer1 != null) {


        EntityItem entityitem = getClosestEntityItem((Entity)this, 10.0D);
        if (entityitem != null) {
          float f = entityitem.getDistance((Entity)this);
          if (f > 2.0F) {
            int i = MathHelper.floor(entityitem.posX);
            int j = MathHelper.floor(entityitem.posY);
            int k = MathHelper.floor(entityitem.posZ);
            faceLocation(i, j, k, 30.0F);

            getMyOwnPath((Entity)entityitem, f);
            return;
          }
          if (f < 2.0F && entityitem != null && this.deathTime == 0 && this.rand.nextInt(50) == 0) {
            MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GOAT_EATING);
            setEating(true);

            entityitem.setDead();

            return;
          }
        }

        if (getType() > 4 && this.rand.nextInt(200) == 0) {
          MoCEntityGoat entitytarget = (MoCEntityGoat)getClosestEntityLiving((Entity)this, 14.0D);
          if (entitytarget != null) {
            setUpset(true);
            setAttackTarget((EntityLivingBase)entitytarget);
            entitytarget.setUpset(true);
            entitytarget.setAttackTarget((EntityLivingBase)this);
          }
        }
      }
    }
  }



  public boolean isMyFavoriteFood(ItemStack stack) {
    return (!stack.isEmpty() && MoCTools.isItemEdible(stack.getItem()));
  }


  public int getTalkInterval() {
    if (this.hungry) {
      return 80;
    }

    return 200;
  }


  public boolean entitiesToIgnore(Entity entity) {
    return (!(entity instanceof MoCEntityGoat) || ((MoCEntityGoat)entity).getType() < 5);
  }


  public boolean isMovementCeased() {
    return (getUpset() && !getCharging());
  }


  public boolean attackEntityAsMob(Entity entityIn) {
    this.attacking = 30;
    if (entityIn instanceof MoCEntityGoat) {
      MoCTools.bigsmack((Entity)this, entityIn, 0.4F);
      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GOAT_SMACK);
      if (this.rand.nextInt(3) == 0) {
        calm();
        ((MoCEntityGoat)entityIn).calm();
      }
      return false;
    }
    MoCTools.bigsmack((Entity)this, entityIn, 0.8F);
    if (this.rand.nextInt(3) == 0) {
      calm();
    }
    return super.attackEntityAsMob(entityIn);
  }


  public boolean isNotScared() {
    return (getType() > 4);
  }

  private void swingLeg() {
    if (!getSwingLeg()) {
      setSwingLeg(true);
      this.movecount = 0;
    }
  }

  public boolean getSwingLeg() {
    return this.swingLeg;
  }

  public void setSwingLeg(boolean flag) {
    this.swingLeg = flag;
  }

  public boolean getSwingEar() {
    return this.swingEar;
  }

  public void setSwingEar(boolean flag) {
    this.swingEar = flag;
  }

  public boolean getSwingTail() {
    return this.swingTail;
  }

  public void setSwingTail(boolean flag) {
    this.swingTail = flag;
  }

  public boolean getEating() {
    return this.eating;
  }

  public void setEating(boolean flag) {
    this.eating = flag;
  }


  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    if (super.attackEntityFrom(damagesource, i)) {
      Entity entity = damagesource.getTrueSource();

      if (entity != this && entity instanceof EntityLivingBase && shouldAttackPlayers() && getType() > 4) {
        setAttackTarget((EntityLivingBase)entity);
        setUpset(true);
      }
      return true;
    }
    return false;
  }




  public void onUpdate() {
    if (getSwingLeg()) {
      this.movecount += 5;
      if (this.movecount == 30) {
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GOAT_DIGG);
      }

      if (this.movecount > 100) {
        setSwingLeg(false);
        this.movecount = 0;
      }
    }

    if (getSwingEar()) {
      this.earcount += 5;
      if (this.earcount > 40) {
        setSwingEar(false);
        this.earcount = 0;
      }
    }

    if (getSwingTail()) {
      this.tailcount += 15;
      if (this.tailcount > 135) {
        setSwingTail(false);
        this.tailcount = 0;
      }
    }

    if (getEating()) {
      this.eatcount++;
      if (this.eatcount == 2) {
        EntityPlayer entityplayer1 = this.world.getClosestPlayerToEntity((Entity)this, 3.0D);
        if (entityplayer1 != null) {
          MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GOAT_EATING);
        }
      }
      if (this.eatcount > 25) {
        setEating(false);
        this.eatcount = 0;
      }
    }

    super.onUpdate();
  }

  public int legMovement() {
    if (!getSwingLeg()) {
      return 0;
    }

    if (this.movecount < 21) {
      return this.movecount * -1;
    }
    if (this.movecount < 70) {
      return this.movecount - 40;
    }
    return -this.movecount + 100;
  }


  public int earMovement() {
    if (!getSwingEar()) {
      return 0;
    }
    if (this.earcount < 11) {
      return this.earcount + 30;
    }
    if (this.earcount < 31) {
      return -this.earcount + 50;
    }
    return this.earcount - 10;
  }


  public int tailMovement() {
    if (!getSwingTail()) {
      return 90;
    }

    return this.tailcount - 45;
  }

  public int mouthMovement() {
    if (!getEating()) {
      return 0;
    }
    if (this.eatcount < 6) {
      return this.eatcount;
    }
    if (this.eatcount < 16) {
      return -this.eatcount + 10;
    }
    return this.eatcount - 20;
  }



  public void fall(float f, float f1) {}


  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    Boolean tameResult = processTameInteract(player, hand);
    if (tameResult != null) {
      return tameResult.booleanValue();
    }

    ItemStack stack = player.getHeldItem(hand);
    if (!stack.isEmpty() && stack.getItem() == Items.BUCKET) {
      if (getType() > 4) {
        setUpset(true);
        setAttackTarget((EntityLivingBase)player);
        return false;
      }
      if (getType() == 1) {
        return false;
      }

      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      player.addItemStackToInventory(new ItemStack(Items.MILK_BUCKET));
      return true;
    }

    if (getIsTamed() && !stack.isEmpty() && MoCTools.isItemEdible(stack.getItem())) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      setHealth(getMaxHealth());
      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GOAT_EATING);
      return true;
    }

    if (!getIsTamed() && !stack.isEmpty() && MoCTools.isItemEdible(stack.getItem())) {
      if (!this.world.isRemote) {
        MoCTools.tameWithName(player, (IMoCTameable)this);
      }

      return true;
    }

    return super.processInteract(player, hand);
  }


  public boolean getBleating() {
    return (this.bleat && getAttacking() == 0);
  }

  public void setBleating(boolean flag) {
    this.bleat = flag;
  }

  public int getAttacking() {
    return this.attacking;
  }

  public void setAttacking(int flag) {
    this.attacking = flag;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    return MoCSoundEvents.ENTITY_GOAT_HURT;
  }


  protected SoundEvent getAmbientSound() {
    setBleating(true);
    if (getType() == 1) {
      return MoCSoundEvents.ENTITY_GOAT_AMBIENT_BABY;
    }
    if (getType() > 2 && getType() < 5) {
      return MoCSoundEvents.ENTITY_GOAT_AMBIENT_FEMALE;
    }

    return MoCSoundEvents.ENTITY_GOAT_AMBIENT;
  }


  protected SoundEvent getDeathSound() {
    return MoCSoundEvents.ENTITY_GOAT_DEATH;
  }


  protected Item getDropItem() {
    return Items.LEATHER;
  }


  public int getMaxEdad() {
    return 50;
  }


  public float getAIMoveSpeed() {
    return 0.15F;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityGoat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
