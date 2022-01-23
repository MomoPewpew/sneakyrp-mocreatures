package drzhark.mocreatures.entity.passive;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.init.MoCItems;
import drzhark.mocreatures.entity.IMoCTameable;
import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.init.MoCSoundEvents;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class MoCEntityTurkey extends MoCEntityTameableAnimal {
  public MoCEntityTurkey(World world) {
    super(world);
    setSize(0.8F, 1.0F);
    this.texture = "turkey.png";
  }


  protected void initEntityAI() {
    this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.tasks.addTask(1, (EntityAIBase)new EntityAIPanic((EntityCreature)this, 1.4D));
    this.tasks.addTask(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.0D, Items.MELON_SEEDS, false));
    this.tasks.addTask(5, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D));
    this.tasks.addTask(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0F));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
  }


  public void selectType() {
    if (getType() == 0) {
      setType(this.rand.nextInt(2) + 1);
    }
  }


  public ResourceLocation getTexture() {
    if (getType() == 1) {
      return MoCreatures.proxy.getTexture("turkey.png");
    }
    return MoCreatures.proxy.getTexture("turkeyfemale.png");
  }



  protected SoundEvent getDeathSound() {
    return MoCSoundEvents.ENTITY_TURKEY_HURT;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    return MoCSoundEvents.ENTITY_TURKEY_HURT;
  }


  protected SoundEvent getAmbientSound() {
    return MoCSoundEvents.ENTITY_TURKEY_AMBIENT;
  }


  protected Item getDropItem() {
    boolean flag = (this.rand.nextInt(2) == 0);
    if (flag) {
      return (Item)MoCItems.rawTurkey;
    }
    return Items.FEATHER;
  }


  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    Boolean tameResult = processTameInteract(player, hand);
    if (tameResult != null) {
      return tameResult.booleanValue();
    }

    ItemStack stack = player.getHeldItem(hand);
    if (!getIsTamed() && !stack.isEmpty() && stack.getItem() == Items.MELON_SEEDS) {
      if (!this.world.isRemote) {
        MoCTools.tameWithName(player, (IMoCTameable)this);
      }

      return true;
    }

    return super.processInteract(player, hand);
  }


  public void onLivingUpdate() {
    super.onLivingUpdate();
    if (!this.onGround && this.motionY < 0.0D) {
      this.motionY *= 0.8D;
    }
  }


  public boolean isMyHealFood(ItemStack stack) {
    return (!stack.isEmpty() && stack.getItem() == Items.PUMPKIN_SEEDS);
  }


  public int nameYOffset() {
    return -50;
  }


  public int getTalkInterval() {
    return 400;
  }


  public int getMaxSpawnedInChunk() {
    return 2;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityTurkey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
