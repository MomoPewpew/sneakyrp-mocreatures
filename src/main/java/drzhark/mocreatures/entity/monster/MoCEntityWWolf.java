package drzhark.mocreatures.entity.monster;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.MoCEntityMob;
import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
import drzhark.mocreatures.init.MoCItems;
import drzhark.mocreatures.init.MoCSoundEvents;
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
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;






public class MoCEntityWWolf
  extends MoCEntityMob
{
  public int mouthCounter;
  public int tailCounter;

  public MoCEntityWWolf(World world) {
    super(world);
    setSize(0.9F, 1.3F);
  }


  protected void initEntityAI() {
    this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
    this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
  }


  public void selectType() {
    if (getType() == 0) {
      setType(this.rand.nextInt(4) + 1);
    }
  }


  public ResourceLocation getTexture() {
    switch (getType()) {
      case 1:
        return MoCreatures.proxy.getTexture("wolfblack.png");
      case 2:
        return MoCreatures.proxy.getTexture("wolfwild.png");
      case 3:
        return MoCreatures.proxy.getTexture("wolftimber.png");
      case 4:
        return MoCreatures.proxy.getTexture("wolfdark.png");
      case 5:
        return MoCreatures.proxy.getTexture("wolfbright.png");
    }

    return MoCreatures.proxy.getTexture("wolfwild.png");
  }


  private void openMouth() {
    this.mouthCounter = 1;
  }

  private void moveTail() {
    this.tailCounter = 1;
  }


  public void onUpdate() {
    super.onUpdate();

    if (this.rand.nextInt(200) == 0) {
      moveTail();
    }

    if (this.mouthCounter > 0 && ++this.mouthCounter > 15) {
      this.mouthCounter = 0;
    }

    if (this.tailCounter > 0 && ++this.tailCounter > 8) {
      this.tailCounter = 0;
    }
  }


  public boolean checkSpawningBiome() {
    int i = MathHelper.floor(this.posX);
    int j = MathHelper.floor((getEntityBoundingBox()).minY);
    int k = MathHelper.floor(this.posZ);

    Biome biome = MoCTools.Biomekind(this.world, new BlockPos(i, j, k));
    if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.SNOWY)) {
      setType(3);
    }
    selectType();
    return true;
  }


  public boolean getCanSpawnHere() {
    return (checkSpawningBiome() && this.world
      .canBlockSeeSky(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.posY),
          MathHelper.floor(this.posZ))) && super.getCanSpawnHere());
  }


  public EntityLivingBase getClosestTarget(Entity entity, double d) {
    double d1 = -1.0D;
    EntityLivingBase entityliving = null;
    List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(d, d, d));
    for (int i = 0; i < list.size(); i++) {
      Entity entity1 = list.get(i);
      if (entity1 instanceof EntityLivingBase && entity1 != entity && entity1 != entity.getRidingEntity() && entity1 != entity
        .getRidingEntity() && !(entity1 instanceof EntityPlayer) && !(entity1 instanceof EntityMob) && !(entity1 instanceof drzhark.mocreatures.entity.passive.MoCEntityBigCat) && !(entity1 instanceof drzhark.mocreatures.entity.passive.MoCEntityBear) && !(entity1 instanceof net.minecraft.entity.passive.EntityCow) && (!(entity1 instanceof net.minecraft.entity.passive.EntityWolf) || MoCreatures.proxy.attackWolves) && (!(entity1 instanceof drzhark.mocreatures.entity.passive.MoCEntityHorse) || MoCreatures.proxy.attackHorses)) {





        double d2 = entity1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
        if ((d < 0.0D || d2 < d * d) && (d1 == -1.0D || d2 < d1) && ((EntityLivingBase)entity1).canEntityBeSeen(entity)) {
          d1 = d2;
          entityliving = (EntityLivingBase)entity1;
        }
      }
    }
    return entityliving;
  }


  protected Item getDropItem() {
    return (Item)MoCItems.fur;
  }


  protected SoundEvent getDeathSound() {
    return MoCSoundEvents.ENTITY_WOLF_DEATH;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    openMouth();
    return MoCSoundEvents.ENTITY_WOLF_HURT;
  }


  protected SoundEvent getAmbientSound() {
    openMouth();
    return MoCSoundEvents.ENTITY_WOLF_AMBIENT;
  }


  public void updatePassenger(Entity passenger) {
    double dist = 0.1D;
    double newPosX = this.posX + dist * Math.sin((this.renderYawOffset / 57.29578F));
    double newPosZ = this.posZ - dist * Math.cos((this.renderYawOffset / 57.29578F));
    passenger.setPosition(newPosX, this.posY + getMountedYOffset() + passenger.getYOffset(), newPosZ);
    passenger.rotationYaw = this.rotationYaw;
  }


  public double getMountedYOffset() {
    return this.height * 0.75D - 0.1D;
  }


  public void onLivingUpdate() {
    super.onLivingUpdate();
    if (!this.world.isRemote && !isBeingRidden() && this.rand.nextInt(100) == 0) {
      List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(4.0D, 2.0D, 4.0D));
      for (int i = 0; i < list.size(); i++) {
        Entity entity = list.get(i);
        if (entity instanceof EntityMob) {


          EntityMob entitymob = (EntityMob)entity;
          if (entitymob.getRidingEntity() == null && (entitymob instanceof net.minecraft.entity.monster.EntitySkeleton || entitymob instanceof net.minecraft.entity.monster.EntityZombie || entitymob instanceof MoCEntitySilverSkeleton)) {

            entitymob.startRiding((Entity)this);
            break;
          }
        }
      }
    }
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\monster\MoCEntityWWolf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
