package drzhark.mocreatures.entity.passive;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.entity.IMoCTameable;
import drzhark.mocreatures.entity.ai.EntityAIFleeFromPlayer;
import drzhark.mocreatures.entity.ai.EntityAIFollowAdult;
import drzhark.mocreatures.entity.ai.EntityAIHunt;
import drzhark.mocreatures.entity.ai.EntityAIPanicMoC;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.entity.ai.EntityAIFollowOwnerPlayer;
import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
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
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class MoCEntityRaccoon extends MoCEntityTameableAnimal {
  public MoCEntityRaccoon(World world) {
    super(world);
    setSize(0.5F, 0.6F);
    this.texture = "raccoon.png";
    setEdad(50 + this.rand.nextInt(15));

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
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
    getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
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
    if (!stack.isEmpty() && MoCTools.isItemEdible(stack.getItem())) {

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


  protected Item getDropItem() {
    return (Item)MoCItems.fur;
  }


  protected SoundEvent getDeathSound() {
    return MoCSoundEvents.ENTITY_RACCOON_DEATH;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    return MoCSoundEvents.ENTITY_RACCOON_HURT;
  }


  protected SoundEvent getAmbientSound() {
    return MoCSoundEvents.ENTITY_RACCOON_AMBIENT;
  }


  public int nameYOffset() {
    return -30;
  }


  public float getSizeFactor() {
    if (getIsAdult()) {
      return 0.85F;
    }
    return 0.85F * getEdad() * 0.01F;
  }


  public int getTalkInterval() {
    return 400;
  }


  public int getMaxSpawnedInChunk() {
    return 2;
  }


  public boolean isNotScared() {
    return getIsAdult();
  }


  public boolean canAttackTarget(EntityLivingBase entity) {
    return (!(entity instanceof MoCEntityRaccoon) && super.canAttackTarget(entity));
  }


  public boolean isReadyToHunt() {
    return (getIsAdult() && !isMovementCeased());
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityRaccoon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
