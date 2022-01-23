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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class MoCEntityWerewolf extends MoCEntityMob {
  private boolean transforming;
  private static final DataParameter<Boolean> IS_HUMAN = EntityDataManager.createKey(MoCEntityWerewolf.class, DataSerializers.BOOLEAN); private int tcounter; private int textCounter;
  private static final DataParameter<Boolean> IS_HUNCHED = EntityDataManager.createKey(MoCEntityWerewolf.class, DataSerializers.BOOLEAN);

  public MoCEntityWerewolf(World world) {
    super(world);
    setSize(0.9F, 1.6F);
    this.transforming = false;
    this.tcounter = 0;
    setHumanForm(true);
  }


  protected void initEntityAI() {
    this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
    this.tasks.addTask(3, (EntityAIBase)new EntityAILeapAtTarget((EntityLiving)this, 0.4F));
    this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
  }


  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(IS_HUMAN, Boolean.valueOf(false));
    this.dataManager.register(IS_HUNCHED, Boolean.valueOf(false));
  }


  public void setHealth(float par1) {
    if (getIsHumanForm() && par1 > 15.0F) {
      par1 = 15.0F;
    }
    super.setHealth(par1);
  }


  public void selectType() {
    if (getType() == 0) {
      int k = this.rand.nextInt(100);
      if (k <= 28) {
        setType(1);
      } else if (k <= 56) {
        setType(2);
      } else if (k <= 85) {
        setType(3);
      } else {
        setType(4);
        this.isImmuneToFire = true;
      }
    }
  } public ResourceLocation getTexture() {
    String NTA;
    String NTB;
    String NTC;
    if (getIsHumanForm()) {
      return MoCreatures.proxy.getTexture("wereblank.png");
    }

    switch (getType()) {
      case 1:
        return MoCreatures.proxy.getTexture("wolfblack.png");
      case 2:
        return MoCreatures.proxy.getTexture("wolfbrown.png");
      case 3:
        return MoCreatures.proxy.getTexture("wolftimber.png");
      case 4:
        if (!MoCreatures.proxy.getAnimateTextures()) {
          return MoCreatures.proxy.getTexture("wolffire1.png");
        }
        this.textCounter++;
        if (this.textCounter < 10) {
          this.textCounter = 10;
        }
        if (this.textCounter > 39) {
          this.textCounter = 10;
        }
        NTA = "wolffire";
        NTB = "" + this.textCounter;
        NTB = NTB.substring(0, 1);
        NTC = ".png";

        return MoCreatures.proxy.getTexture(NTA + NTB + NTC);
    }
    return MoCreatures.proxy.getTexture("wolfbrown.png");
  }


  public boolean getIsHumanForm() {
    return ((Boolean)this.dataManager.get(IS_HUMAN)).booleanValue();
  }

  public void setHumanForm(boolean flag) {
    this.dataManager.set(IS_HUMAN, Boolean.valueOf(flag));
  }

  public boolean getIsHunched() {
    return ((Boolean)this.dataManager.get(IS_HUNCHED)).booleanValue();
  }

  public void setHunched(boolean flag) {
    this.dataManager.set(IS_HUNCHED, Boolean.valueOf(flag));
  }


  public boolean attackEntityAsMob(Entity entityIn) {
    if (getIsHumanForm()) {
      setAttackTarget(null);
      return false;
    }
    if (getType() == 4 && entityIn instanceof EntityLivingBase) {
      ((EntityLivingBase)entityIn).setFire(10);
    }
    return super.attackEntityAsMob(entityIn);
  }


  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    Entity entity = damagesource.getTrueSource();
    if (!getIsHumanForm() && entity != null && entity instanceof EntityPlayer) {
      EntityPlayer entityplayer = (EntityPlayer)entity;
      ItemStack stack = entityplayer.getHeldItemMainhand();
      if (!stack.isEmpty()) {
        i = 1.0F;
        if (stack.getItem() == MoCItems.silversword) {
          i = 10.0F;
        }
        if (stack.getItem() instanceof ItemSword) {
          String swordMaterial = ((ItemSword)stack.getItem()).getToolMaterialName();
          String swordName = ((ItemSword)stack.getItem()).getUnlocalizedName();
          if (swordMaterial.toLowerCase().contains("silver") || swordName.toLowerCase().contains("silver")) {
            i = ((ItemSword)stack.getItem()).getAttackDamage() * 3.0F;
          }
        } else if (stack.getItem() instanceof ItemTool) {
          String toolMaterial = ((ItemTool)stack.getItem()).getToolMaterialName();
          String toolName = ((ItemTool)stack.getItem()).getUnlocalizedName();
          if (toolMaterial.toLowerCase().contains("silver") || toolName.toLowerCase().contains("silver")) {
            i = ((ItemSword)stack.getItem()).getAttackDamage() * 2.0F;
          }
        } else if (stack.getItem().getUnlocalizedName().toLowerCase().contains("silver")) {
          i = 6.0F;
        }
      }
    }
    return super.attackEntityFrom(damagesource, i);
  }


  public boolean shouldAttackPlayers() {
    return (!getIsHumanForm() && super.shouldAttackPlayers());
  }


  protected Item getDropItem() {
    int i = this.rand.nextInt(12);
    if (getIsHumanForm()) {
      switch (i) {
        case 0:
          return Items.WOODEN_SHOVEL;

        case 1:
          return Items.WOODEN_AXE;

        case 2:
          return Items.WOODEN_SWORD;

        case 3:
          return Items.WOODEN_HOE;

        case 4:
          return Items.WOODEN_PICKAXE;
      }
      return Items.STICK;
    }
    switch (i) {
      case 0:
        return Items.IRON_HOE;

      case 1:
        return Items.IRON_SHOVEL;

      case 2:
        return Items.IRON_AXE;

      case 3:
        return Items.IRON_PICKAXE;

      case 4:
        return Items.IRON_SWORD;

      case 5:
        return Items.STONE_HOE;

      case 6:
        return Items.STONE_SHOVEL;

      case 7:
        return Items.STONE_AXE;

      case 8:
        return Items.STONE_PICKAXE;

      case 9:
        return Items.STONE_SWORD;
    }
    return Items.GOLDEN_APPLE;
  }


  protected SoundEvent getDeathSound() {
    if (getIsHumanForm()) {
      return MoCSoundEvents.ENTITY_WEREWOLF_DEATH_HUMAN;
    }
    return MoCSoundEvents.ENTITY_WEREWOLF_DEATH;
  }



  protected SoundEvent getHurtSound(DamageSource source) {
    if (getIsHumanForm()) {
      if (!this.transforming)
        return MoCSoundEvents.ENTITY_WEREWOLF_HURT_HUMAN;
      return null;
    }
    return MoCSoundEvents.ENTITY_WEREWOLF_HURT;
  }



  protected SoundEvent getAmbientSound() {
    if (getIsHumanForm()) {
      return MoCSoundEvents.ENTITY_WEREWOLF_AMBIENT_HUMAN;
    }
    return MoCSoundEvents.ENTITY_WEREWOLF_AMBIENT;
  }


  public boolean IsNight() {
    return !this.world.isDaytime();
  }


  public void onDeath(DamageSource damagesource) {
    Entity entity = damagesource.getTrueSource();
    if (this.scoreValue > 0 && entity != null) {
      entity.awardKillScore((Entity)this, this.scoreValue, damagesource);
    }
    if (entity != null) {
      entity.onKillEntity((EntityLivingBase)this);
    }

    if (!this.world.isRemote) {
      for (int i = 0; i < 2; i++) {
        Item item = getDropItem();
        if (item != null) {
          dropItem(item, 1);
        }
      }
    }
  }



  public void onLivingUpdate() {
    super.onLivingUpdate();
    if (!this.world.isRemote) {
      if (((IsNight() && getIsHumanForm()) || (!IsNight() && !getIsHumanForm())) && this.rand.nextInt(250) == 0) {
        this.transforming = true;
      }
      if (getIsHumanForm() && getAttackTarget() != null) {
        setAttackTarget(null);
      }
      if (getAttackTarget() != null && !getIsHumanForm()) {
        boolean hunch = (getDistanceSq((Entity)getAttackTarget()) > 12.0D);
        setHunched(hunch);
      }

      if (this.transforming && this.rand.nextInt(3) == 0) {
        this.tcounter++;
        if (this.tcounter % 2 == 0) {
          this.posX += 0.3D;
          this.posY += (this.tcounter / 30);
          attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), 1.0F);
        }
        if (this.tcounter % 2 != 0) {
          this.posX -= 0.3D;
        }
        if (this.tcounter == 10) {
          MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_WEREWOLF_TRANSFORM);
        }
        if (this.tcounter > 30) {
          Transform();
          this.tcounter = 0;
          this.transforming = false;
        }
      }

      if (this.rand.nextInt(300) == 0) {
        this.idleTime -= 100 * this.world.getDifficulty().getDifficultyId() ;
        if (this.idleTime < 0) {
          this.idleTime = 0;
        }
      }
    }
  }

  private void Transform() {
    if (this.deathTime > 0) {
      return;
    }
    int i = MathHelper.floor(this.posX);
    int j = MathHelper.floor((getEntityBoundingBox()).minY) + 1;
    int k = MathHelper.floor(this.posZ);
    float f = 0.1F;
    for (int l = 0; l < 30; l++) {
      double d = (i + this.world.rand.nextFloat());
      double d1 = (j + this.world.rand.nextFloat());
      double d2 = (k + this.world.rand.nextFloat());
      double d3 = d - i;
      double d4 = d1 - j;
      double d5 = d2 - k;
      double d6 = MathHelper.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
      d3 /= d6;
      d4 /= d6;
      d5 /= d6;
      double d7 = 0.5D / (d6 / f + 0.1D);
      d7 *= (this.world.rand.nextFloat() * this.world.rand.nextFloat() + 0.3F);
      d3 *= d7;
      d4 *= d7;
      d5 *= d7;
      this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (d + i * 1.0D) / 2.0D, (d1 + j * 1.0D) / 2.0D, (d2 + k * 1.0D) / 2.0D, d3, d4, d5, new int[0]);
    }


    if (getIsHumanForm()) {
      setHumanForm(false);
      setHealth(40.0F);
      this.transforming = false;
      getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
    } else {
      setHumanForm(true);
      setHealth(15.0F);
      this.transforming = false;
      getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }
  }


  public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
    super.readEntityFromNBT(nbttagcompound);
    setHumanForm(nbttagcompound.getBoolean("HumanForm"));
  }


  public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
    super.writeEntityToNBT(nbttagcompound);
    nbttagcompound.setBoolean("HumanForm", getIsHumanForm());
  }


  public float getAIMoveSpeed() {
    if (getIsHumanForm()) {
      return 0.1F;
    }
    if (getIsHunched()) {
      return 0.35F;
    }
    return 0.2F;
  }


  public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
    if (getType() == 4) {
      this.isImmuneToFire = true;
    }
    return super.onInitialSpawn(difficulty, livingdata);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\monster\MoCEntityWerewolf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
