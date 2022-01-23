package drzhark.mocreatures.entity.monster;

import drzhark.mocreatures.entity.MoCEntityMob;
import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
import drzhark.mocreatures.init.MoCSoundEvents;
import drzhark.mocreatures.network.MoCMessageHandler;
import drzhark.mocreatures.network.message.MoCMessageAnimation;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MoCEntityWraith extends MoCEntityMob {
  public MoCEntityWraith(World world) {
    super(world);
    this.collidedVertically = false;
    this.texture = "wraith.png";
    setSize(1.5F, 1.5F);
    this.isImmuneToFire = false;
  }


  protected void initEntityAI() {
    this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
    this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
  }
  public int attackCounter;

  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE)
      .setBaseValue((this.world.getDifficulty().getDifficultyId()  == 1) ? 2.0D : 3.0D);
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
  }


  protected Item getDropItem() {
    return Items.GUNPOWDER;
  }


  protected SoundEvent getDeathSound() {
    return MoCSoundEvents.ENTITY_WRAITH_DEATH;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    return MoCSoundEvents.ENTITY_WRAITH_HURT;
  }


  protected SoundEvent getAmbientSound() {
    return MoCSoundEvents.ENTITY_WRAITH_AMBIENT;
  }


  public boolean isFlyer() {
    return true;
  }


  public boolean canBePushed() {
    return false;
  }



  protected void collideWithEntity(Entity par1Entity) {}


  public void fall(float f, float f1) {}


  protected void updateFallState(double y, boolean onGroundIn, Block blockIn, BlockPos pos) {}


  public int maxFlyingHeight() {
    return 10;
  }

  public int minFlyingHeight() {
    return 3;
  }


  public boolean attackEntityAsMob(Entity entityIn) {
    startArmSwingAttack();
    return super.attackEntityAsMob(entityIn);
  }




  private void startArmSwingAttack() {
    if (!this.world.isRemote) {
      this.attackCounter = 1;
      MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 1), new NetworkRegistry.TargetPoint(this.world.provider
            .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
    }
  }


  public void onLivingUpdate() {
    if (this.attackCounter > 0) {
      this.attackCounter += 2;
      if (this.attackCounter > 10)
        this.attackCounter = 0;
    }
    super.onLivingUpdate();
  }


  public void performAnimation(int animationType) {
    if (animationType == 1)
      this.attackCounter = 1;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\monster\MoCEntityWraith.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
