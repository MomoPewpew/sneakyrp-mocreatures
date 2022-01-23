package drzhark.mocreatures.entity.monster;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.MoCEntityMob;
import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
import drzhark.mocreatures.init.MoCItems;
import drzhark.mocreatures.init.MoCSoundEvents;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class MoCEntityRat extends MoCEntityMob {
  public MoCEntityRat(World world) {
    super(world);
    setSize(0.5F, 0.5F);
  }


  protected void initEntityAI() {
    this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
    this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
  }


  public void selectType() {
    if (getType() == 0) {
      int i = this.rand.nextInt(100);
      if (i <= 65) {
        setType(1);
      } else if (i <= 98) {
        setType(2);
      } else {
        setType(3);
      }
    }
  }


  protected double getAttackStrenght() {
    return 1.0D;
  }


  public ResourceLocation getTexture() {
    switch (getType()) {
      case 1:
        return MoCreatures.proxy.getTexture("ratb.png");
      case 2:
        return MoCreatures.proxy.getTexture("ratbl.png");
      case 3:
        return MoCreatures.proxy.getTexture("ratw.png");
    }

    return MoCreatures.proxy.getTexture("ratb.png");
  }



  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    Entity entity = damagesource.getTrueSource();

    if (entity != null && entity instanceof EntityLivingBase) {
      setAttackTarget((EntityLivingBase)entity);
      if (!this.world.isRemote) {

        List<MoCEntityRat> list = this.world.getEntitiesWithinAABB(MoCEntityRat.class, (new AxisAlignedBB(this.posX, this.posY, this.posZ, this.posX + 1.0D, this.posY + 1.0D, this.posZ + 1.0D))

            .expand(16.0D, 4.0D, 16.0D));
        Iterator<MoCEntityRat> iterator = list.iterator();

        while (iterator.hasNext()) {


          MoCEntityRat entityrat = iterator.next();
          if (entityrat != null && entityrat.getAttackTarget() == null) {
            entityrat.setAttackTarget((EntityLivingBase)entity);
          }
        }
      }
    }
    return super.attackEntityFrom(damagesource, i);
  }

  public boolean climbing() {
    return (!this.onGround && isOnLadder());
  }


  public void onLivingUpdate() {
    super.onLivingUpdate();

    if (this.rand.nextInt(100) == 0 && getBrightness() > 0.5F) {
      setAttackTarget(null);
      return;
    }
  }


  protected Item getDropItem() {
    return (Item)MoCItems.ratRaw;
  }


  protected SoundEvent getDeathSound() {
    return MoCSoundEvents.ENTITY_RAT_DEATH;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    return MoCSoundEvents.ENTITY_RAT_HURT;
  }


  protected SoundEvent getAmbientSound() {
    return MoCSoundEvents.ENTITY_RAT_AMBIENT;
  }


  public boolean isOnLadder() {
    return this.collidedHorizontally;
  }


  public boolean shouldAttackPlayers() {
    return (getBrightness() < 0.5F && super.shouldAttackPlayers());
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\monster\MoCEntityRat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
