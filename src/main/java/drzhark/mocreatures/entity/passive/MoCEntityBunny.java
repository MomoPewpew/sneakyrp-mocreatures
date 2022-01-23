package drzhark.mocreatures.entity.passive;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.IMoCTameable;
import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
import drzhark.mocreatures.entity.ai.EntityAIFleeFromPlayer;
import drzhark.mocreatures.entity.ai.EntityAIFollowAdult;
import drzhark.mocreatures.entity.ai.EntityAIFollowOwnerPlayer;
import drzhark.mocreatures.entity.ai.EntityAIPanicMoC;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.init.MoCSoundEvents;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class MoCEntityBunny extends MoCEntityTameableAnimal {
  private int bunnyReproduceTickerA;
  private int bunnyReproduceTickerB;
  private int jumpTimer;
  private static final DataParameter<Boolean> HAS_EATEN = EntityDataManager.createKey(MoCEntityBunny.class, DataSerializers.BOOLEAN);

  public MoCEntityBunny(World world) {
    super(world);
    setAdult(true);
    setTamed(false);
    setEdad(50 + this.rand.nextInt(15));
    if (this.rand.nextInt(4) == 0) {
      setAdult(false);
    }
    setSize(0.5F, 0.5F);
    this.bunnyReproduceTickerA = this.rand.nextInt(64);
    this.bunnyReproduceTickerB = 0;
  }


  protected void initEntityAI() {
    this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.tasks.addTask(1, (EntityAIBase)new EntityAIFollowOwnerPlayer((EntityLiving)this, 0.8D, 6.0F, 5.0F));
    this.tasks.addTask(2, (EntityAIBase)new EntityAIPanicMoC((EntityCreature)this, 1.0D));
    this.tasks.addTask(3, (EntityAIBase)new EntityAIFleeFromPlayer((EntityCreature)this, 1.0D, 4.0D));
    this.tasks.addTask(4, (EntityAIBase)new EntityAIFollowAdult((EntityLiving)this, 1.0D));
    this.tasks.addTask(5, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 0.8D));
    this.tasks.addTask(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0F));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
  }


  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(HAS_EATEN, Boolean.valueOf(false));
  }

  public boolean getHasEaten() {
    return ((Boolean)this.dataManager.get(HAS_EATEN)).booleanValue();
  }

  public void setHasEaten(boolean flag) {
    this.dataManager.set(HAS_EATEN, Boolean.valueOf(flag));
  }


  public void selectType() {
    checkSpawningBiome();

    if (getType() == 0) {
      setType(this.rand.nextInt(5) + 1);
    }
  }



  public boolean checkSpawningBiome() {
    int i = MathHelper.floor(this.posX);
    int j = MathHelper.floor((getEntityBoundingBox()).minY);
    int k = MathHelper.floor(this.posZ);
    BlockPos pos = new BlockPos(i, j, k);

    Biome currentbiome = MoCTools.Biomekind(this.world, pos);
    try {
      if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.SNOWY)) {
        setType(3);
        return true;
      }
    } catch (Exception exception) {}

    return true;
  }


  public ResourceLocation getTexture() {
    switch (getType()) {
      case 1:
        return MoCreatures.proxy.getTexture("bunny.png");
      case 2:
        return MoCreatures.proxy.getTexture("bunnyb.png");
      case 3:
        return MoCreatures.proxy.getTexture("bunnyc.png");
      case 4:
        return MoCreatures.proxy.getTexture("bunnyd.png");
      case 5:
        return MoCreatures.proxy.getTexture("bunnye.png");
    }

    return MoCreatures.proxy.getTexture("bunny.png");
  }



  public void fall(float f, float f1) {}



  protected SoundEvent getDeathSound() {
    return MoCSoundEvents.ENTITY_RABBIT_DEATH;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    return MoCSoundEvents.ENTITY_RABBIT_HURT;
  }


  protected SoundEvent getAmbientSound() {
    return SoundEvents.ENTITY_RABBIT_AMBIENT;
  }


  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    Boolean tameResult = processTameInteract(player, hand);
    if (tameResult != null) {
      return tameResult.booleanValue();
    }

    ItemStack stack = player.getHeldItem(hand);
    if (!stack.isEmpty() && stack.getItem() == Items.GOLDEN_CARROT && !getHasEaten()) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      setHasEaten(true);
      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_EATING);
      return true;
    }
    if (getRidingEntity() == null) {
      if (startRiding((Entity)player)) {
        this.rotationYaw = player.rotationYaw;
        if (!getIsTamed() && !this.world.isRemote) {
          MoCTools.tameWithName(player, (IMoCTameable)this);
        }
      }

      return true;
    }

    return super.processInteract(player, hand);
  }


  public void onUpdate() {
    super.onUpdate();

    if (getRidingEntity() != null) {
      this.rotationYaw = (getRidingEntity()).rotationYaw;
    }
    if (!this.world.isRemote) {

      if (--this.jumpTimer <= 0 && this.onGround && (this.motionX > 0.05D || this.motionZ > 0.05D || this.motionX < -0.05D || this.motionZ < -0.05D)) {

        this.motionY = 0.3D;
        this.jumpTimer = 15;
      }

      if (!getIsTamed() || !getIsAdult() || !getHasEaten() || getRidingEntity() != null) {
        return;
      }
      if (this.bunnyReproduceTickerA < 1023) {
        this.bunnyReproduceTickerA++;
      } else if (this.bunnyReproduceTickerB < 127) {
        this.bunnyReproduceTickerB++;
      } else {
        List<Entity> list1 = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(4.0D, 4.0D, 4.0D));
        for (int i1 = 0; i1 < list1.size(); i1++) {
          Entity entity1 = list1.get(i1);
          if (entity1 instanceof MoCEntityBunny && entity1 != this) {


            MoCEntityBunny entitybunny = (MoCEntityBunny)entity1;
            if (entitybunny.getRidingEntity() == null && entitybunny.bunnyReproduceTickerA >= 1023 && entitybunny.getIsAdult() && entitybunny
              .getHasEaten()) {


              MoCEntityBunny entitybunny1 = new MoCEntityBunny(this.world);
              entitybunny1.setPosition(this.posX, this.posY, this.posZ);
              entitybunny1.setAdult(false);
              int babytype = getType();
              if (this.rand.nextInt(2) == 0) {
                babytype = entitybunny.getType();
              }
              entitybunny1.setType(babytype);
              this.world.spawnEntity((Entity)entitybunny1);
              MoCTools.playCustomSound((Entity)this, SoundEvents.ENTITY_CHICKEN_EGG);
              proceed();
              entitybunny.proceed();
              break;
            }
          }
        }
      }
    }
  } public void proceed() {
    setHasEaten(false);
    this.bunnyReproduceTickerB = 0;
    this.bunnyReproduceTickerA = this.rand.nextInt(64);
  }


  public int nameYOffset() {
    return -40;
  }


  public boolean isMyHealFood(ItemStack stack) {
    return (!stack.isEmpty() && stack.getItem() == Items.CARROT);
  }





  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    if (getRidingEntity() != null) {
      return false;
    }
    return super.attackEntityFrom(damagesource, i);
  }


  public boolean isNotScared() {
    return getIsTamed();
  }


  public double getYOffset() {
    if (getRidingEntity() instanceof EntityPlayer) {
      return ((EntityPlayer)getRidingEntity()).isSneaking() ? 0.25D : 0.5D;
    }

    return super.getYOffset();
  }


  public float getAdjustedYOffset() {
    return 0.2F;
  }



  public boolean canRidePlayer() {
    return true;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityBunny.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
