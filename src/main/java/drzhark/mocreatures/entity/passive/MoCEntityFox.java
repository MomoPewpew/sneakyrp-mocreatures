package drzhark.mocreatures.entity.passive;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.IMoCTameable;
import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
import drzhark.mocreatures.entity.ai.EntityAIFleeFromPlayer;
import drzhark.mocreatures.entity.ai.EntityAIFollowAdult;
import drzhark.mocreatures.entity.ai.EntityAIHunt;
import drzhark.mocreatures.entity.ai.EntityAIPanicMoC;
import drzhark.mocreatures.entity.ai.EntityAIFollowOwnerPlayer;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.init.MoCItems;
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
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class MoCEntityFox extends MoCEntityTameableAnimal {
  public MoCEntityFox(World world) {
    super(world);
    setSize(0.6F, 0.7F);
    setEdad(this.rand.nextInt(15) + 50);
    if (this.rand.nextInt(3) == 0) {
      setAdult(false);
    } else {

      setAdult(true);
    }
  }


  protected void initEntityAI() {
    this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.tasks.addTask(2, (EntityAIBase)new EntityAIPanicMoC((EntityCreature)this, 1.0D));
    this.tasks.addTask(3, (EntityAIBase)new EntityAIFleeFromPlayer((EntityCreature)this, 1.0D, 4.0D));
    this.tasks.addTask(3, (EntityAIBase)new EntityAIFollowOwnerPlayer((EntityLiving)this, 0.8D, 2.0F, 10.0F));
    this.tasks.addTask(4, (EntityAIBase)new EntityAIFollowAdult((EntityLiving)this, 1.0D));
    this.tasks.addTask(5, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
    this.tasks.addTask(6, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D));
    this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHunt((EntityCreature)this, EntityAnimal.class, true));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
    getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
  }


  public void selectType() {
    checkSpawningBiome();

    if (getType() == 0) {
      setType(1);
    }
  }



  public ResourceLocation getTexture() {
    if (!getIsAdult()) {
      if (getType() == 2) {
        return MoCreatures.proxy.getTexture("foxsnow.png");
      }
      return MoCreatures.proxy.getTexture("foxcub.png");
    }
    switch (getType()) {
      case 1:
        return MoCreatures.proxy.getTexture("fox.png");
      case 2:
        return MoCreatures.proxy.getTexture("foxsnow.png");
    }

    return MoCreatures.proxy.getTexture("fox.png");
  }



  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    if (super.attackEntityFrom(damagesource, i)) {
      Entity entity = damagesource.getTrueSource();
      if (isRidingOrBeingRiddenBy(entity)) {
        return true;
      }
      if (entity != this && isNotScared() && entity instanceof EntityLivingBase && shouldAttackPlayers()) {
        setAttackTarget((EntityLivingBase)entity);
        setRevengeTarget((EntityLivingBase)entity);
        return true;
      }
    }

    return false;
  }


  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    Boolean tameResult = processTameInteract(player, hand);
    if (tameResult != null) {
      return tameResult.booleanValue();
    }

    ItemStack stack = player.getHeldItem(hand);
    if (!stack.isEmpty() && stack.getItem() == MoCItems.rawTurkey) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }

      if (!this.world.isRemote) {
        MoCTools.tameWithName(player, (IMoCTameable)this);
      }
      setHealth(getMaxHealth());

      if (!this.world.isRemote && !getIsAdult() && getEdad() < 100) {
        setEdad(getEdad() + 1);
      }

      return true;
    }

    return super.processInteract(player, hand);
  }


  public boolean isNotScared() {
    return getIsAdult();
  }




  public boolean checkSpawningBiome() {
    BlockPos pos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor((getEntityBoundingBox()).minY), MathHelper.floor(this.posZ));
    Biome currentbiome = MoCTools.Biomekind(this.world, pos);
    try {
      if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.SNOWY)) {
        setType(2);
      }
    } catch (Exception exception) {}

    return true;
  }


  protected Item getDropItem() {
    return (Item)MoCItems.fur;
  }


  protected SoundEvent getDeathSound() {
    return MoCSoundEvents.ENTITY_FOX_DEATH;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    return MoCSoundEvents.ENTITY_FOX_HURT;
  }


  protected SoundEvent getAmbientSound() {
    return MoCSoundEvents.ENTITY_FOX_AMBIENT;
  }


  protected float getSoundVolume() {
    return 0.3F;
  }


  public boolean isMyHealFood(ItemStack stack) {
    return (!stack.isEmpty() && stack.getItem() == MoCItems.ratRaw);
  }


  public int nameYOffset() {
    return -50;
  }


  public boolean canAttackTarget(EntityLivingBase entity) {
    return (!(entity instanceof MoCEntityFox) && entity.height <= 0.7D && entity.width <= 0.7D);
  }


  public boolean isReadyToHunt() {
    return (getIsAdult() && !isMovementCeased());
  }


  public float getSizeFactor() {
    if (getIsAdult()) {
      return 0.9F;
    }
    return 0.9F * getEdad() * 0.01F;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityFox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
