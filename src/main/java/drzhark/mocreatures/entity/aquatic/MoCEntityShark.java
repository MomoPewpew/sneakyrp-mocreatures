package drzhark.mocreatures.entity.aquatic;
import drzhark.mocreatures.entity.MoCEntityTameableAquatic;
import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.init.MoCItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class MoCEntityShark extends MoCEntityTameableAquatic {
  public MoCEntityShark(World world) {
    super(world);
    this.texture = "shark.png";
    setSize(1.7F, 0.8F);
    setEdad(60 + this.rand.nextInt(100));
  }


  protected void initEntityAI() {
    this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
    this.tasks.addTask(5, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D, 30));
    this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
    getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
  }


  protected void entityInit() {
    super.entityInit();
  }


  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    if (super.attackEntityFrom(damagesource, i) && this.world.getDifficulty().getDifficultyId()  > 0) {
      Entity entity = damagesource.getTrueSource();
      if (isRidingOrBeingRiddenBy(entity)) {
        return true;
      }
      if (entity != this && entity instanceof EntityLivingBase) {
        setAttackTarget((EntityLivingBase)entity);
        return true;
      }
      return false;
    }

    return false;
  }



  protected void dropFewItems(boolean flag, int x) {
    int i = this.rand.nextInt(100);
    if (i < 90) {
      int j = this.rand.nextInt(3) + 1;
      for (int l = 0; l < j; l++) {
        entityDropItem(new ItemStack((Item)MoCItems.sharkteeth, 1, 0), 0.0F);
      }
    } else if (this.world.getDifficulty().getDifficultyId()  > 0 && getEdad() > 150) {
      int k = this.rand.nextInt(3);
      for (int i1 = 0; i1 < k; i1++) {
        entityDropItem(new ItemStack((Item)MoCItems.mocegg, 1, 11), 0.0F);
      }
    }
  }

































  public void onLivingUpdate() {
    super.onLivingUpdate();
    if (!this.world.isRemote &&
      !getIsAdult() && this.rand.nextInt(50) == 0) {
      setEdad(getEdad() + 1);
      if (getEdad() >= 200) {
        setAdult(true);
      }
    }
  }



  public void setDead() {
    if (!this.world.isRemote && getIsTamed() && getHealth() > 0.0F) {
      return;
    }
    super.setDead();
  }



  public boolean isMyHealFood(Item item1) {
    return false;
  }


  protected boolean usesNewAI() {
    return true;
  }


  public float getAIMoveSpeed() {
    return 0.12F;
  }


  public boolean isMovementCeased() {
    return !isInWater();
  }


  protected double minDivingDepth() {
    return 1.0D;
  }


  protected double maxDivingDepth() {
    return 6.0D;
  }


  public int getMaxEdad() {
    return 200;
  }


  public boolean isNotScared() {
    return true;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\aquatic\MoCEntityShark.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
