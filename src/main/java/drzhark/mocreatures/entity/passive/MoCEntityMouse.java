package drzhark.mocreatures.entity.passive;
import drzhark.mocreatures.MoCEntityData;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.MoCEntityAnimal;
import drzhark.mocreatures.entity.ai.EntityAIFleeFromPlayer;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.init.MoCSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class MoCEntityMouse extends MoCEntityAnimal {
  public MoCEntityMouse(World world) {
    super(world);
    setSize(0.3F, 0.3F);
  }


  protected void initEntityAI() {
    this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.tasks.addTask(1, (EntityAIBase)new EntityAIFleeFromPlayer((EntityCreature)this, 1.2D, 4.0D));
    this.tasks.addTask(2, (EntityAIBase)new EntityAIPanic((EntityCreature)this, 1.4D));
    this.tasks.addTask(5, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D));
    this.tasks.addTask(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0F));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
  }


  public void selectType() {
    checkSpawningBiome();

    if (getType() == 0) {
      setType(this.rand.nextInt(3) + 1);
    }
  }


  public ResourceLocation getTexture() {
    switch (getType()) {
      case 1:
        return MoCreatures.proxy.getTexture("miceg.png");
      case 2:
        return MoCreatures.proxy.getTexture("miceb.png");
      case 3:
        return MoCreatures.proxy.getTexture("micew.png");
    }

    return MoCreatures.proxy.getTexture("miceg.png");
  }



  public boolean checkSpawningBiome() {
    BlockPos pos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor((getEntityBoundingBox()).minY), this.posZ);
    Biome currentbiome = MoCTools.Biomekind(this.world, pos);

    try {
      if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.SNOWY)) {
        setType(3);
      }
    } catch (Exception exception) {}

    return true;
  }

  public boolean getIsPicked() {
    return (getRidingEntity() != null);
  }

  public boolean climbing() {
    return (!this.onGround && isOnLadder());
  }


  // public boolean getCanSpawnHere() {
  //   int i = MathHelper.floor(this.posX);
  //   int j = MathHelper.floor((getEntityBoundingBox()).minY);
  //   int k = MathHelper.floor(this.posZ);
  //   BlockPos pos = new BlockPos(i, j, k);
  //   Block block = this.world.getBlockState(pos.down()).getBlock();
  //   return (((MoCEntityData)MoCreatures.entityMap.get(getClass())).getFrequency() > 0 && this.world.checkNoEntityCollision(getEntityBoundingBox()) && this.world
  //     .getCollisionBoxes((Entity)this, getEntityBoundingBox()).size() == 0 &&
  //     !this.world.containsAnyLiquid(getEntityBoundingBox()) && (block == Blocks.COBBLESTONE || block == Blocks.PLANKS || block == Blocks.DIRT || block == Blocks.STONE || block == Blocks.GRASS));
  // }



  protected Item getDropItem() {
    return Items.WHEAT_SEEDS;
  }


  protected SoundEvent getDeathSound() {
    return MoCSoundEvents.ENTITY_MOUSE_DEATH;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    return MoCSoundEvents.ENTITY_MOUSE_HURT;
  }


  protected SoundEvent getAmbientSound() {
    return MoCSoundEvents.ENTITY_MOUSE_AMBIENT;
  }



  public void fall(float f, float f1) {}


  public double getYOffset() {
    if (getRidingEntity() instanceof EntityPlayer && getRidingEntity() == MoCreatures.proxy.getPlayer() && this.world.isRemote) {
      return super.getYOffset() - 0.699999988079071D;
    }

    if (getRidingEntity() instanceof EntityPlayer && this.world.isRemote) {
      return super.getYOffset() - 0.10000000149011612D;
    }
    return super.getYOffset();
  }



  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    if (getRidingEntity() == null) {
      if (startRiding((Entity)player)) {
        this.rotationYaw = player.rotationYaw;
      }

      return true;
    }

    return super.processInteract(player, hand);
  }


  public boolean isOnLadder() {
    return this.collidedHorizontally;
  }


  public void onLivingUpdate() {
    super.onLivingUpdate();
    if (!this.onGround && getRidingEntity() != null) {
      this.rotationYaw = (getRidingEntity()).rotationYaw;
    }
  }


  public boolean upsideDown() {
    return getIsPicked();
  }



  public boolean canRidePlayer() {
    return true;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityMouse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
