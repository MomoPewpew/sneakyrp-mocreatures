package drzhark.mocreatures.entity.monster;
import drzhark.mocreatures.entity.MoCEntityMob;
import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
import drzhark.mocreatures.init.MoCItems;
import drzhark.mocreatures.network.MoCMessageHandler;
import drzhark.mocreatures.network.message.MoCMessageAnimation;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MoCEntitySilverSkeleton extends MoCEntityMob {
  public int attackCounterLeft;

  public MoCEntitySilverSkeleton(World world) {
    super(world);
    this.texture = "silverskeleton.png";
    setSize(0.9F, 1.4F);
  }
  public int attackCounterRight;

  protected void initEntityAI() {
    this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
    this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
  }


  public void onLivingUpdate() {
    if (!this.world.isRemote) {
      if (getAttackTarget() == null) {
        setSprinting(false);
      } else {
        setSprinting(true);
      }
    }

    if (this.attackCounterLeft > 0 && ++this.attackCounterLeft > 10) {
      this.attackCounterLeft = 0;
    }

    if (this.attackCounterRight > 0 && ++this.attackCounterRight > 10) {
      this.attackCounterRight = 0;
    }

    super.onLivingUpdate();
  }


  protected Item getDropItem() {
    if (this.rand.nextInt(10) == 0) {
      return (Item)MoCItems.silversword;
    }
    return Items.BONE;
  }




  public void performAnimation(int animationType) {
    if (animationType == 1)
    {
      this.attackCounterLeft = 1;
    }
    if (animationType == 2)
    {
      this.attackCounterRight = 1;
    }
  }




  private void startAttackAnimation() {
    if (!this.world.isRemote) {
      boolean leftArmW = (this.rand.nextInt(2) == 0);

      if (leftArmW) {
        this.attackCounterLeft = 1;
        MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 1), new NetworkRegistry.TargetPoint(this.world.provider
              .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
      } else {
        this.attackCounterRight = 1;
        MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 2), new NetworkRegistry.TargetPoint(this.world.provider
              .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
      }
    }
  }


  public boolean attackEntityAsMob(Entity entityIn) {
    startAttackAnimation();
    return super.attackEntityAsMob(entityIn);
  }


  public float getAIMoveSpeed() {
    if (isSprinting()) {
      return 0.35F;
    }
    return 0.2F;
  }


  protected SoundEvent getDeathSound() {
    return SoundEvents.ENTITY_SKELETON_DEATH;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    return SoundEvents.ENTITY_SKELETON_HURT;
  }


  protected SoundEvent getAmbientSound() {
    return SoundEvents.ENTITY_SKELETON_AMBIENT;
  }





  public EnumCreatureAttribute getCreatureAttribute() {
    return EnumCreatureAttribute.UNDEAD;
  }


  protected void playStepSound(BlockPos pos, Block block) {
    playSound(SoundEvents.ENTITY_SKELETON_STEP, 0.15F, 1.0F);
  }


  protected boolean isHarmedByDaylight() {
    return true;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\monster\MoCEntitySilverSkeleton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
