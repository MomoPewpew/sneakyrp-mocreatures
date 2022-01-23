package drzhark.mocreatures.entity.passive;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.MoCEntityAnimal;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.init.MoCSoundEvents;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.state.IBlockState;
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
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.world.BlockEvent;

public class MoCEntityEnt extends MoCEntityAnimal {
  public MoCEntityEnt(World world) {
    super(world);
    setSize(1.4F, 7.0F);
    this.stepHeight = 2.0F;
  }


  protected void initEntityAI() {
    this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.tasks.addTask(5, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
    this.tasks.addTask(6, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D));
    this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
    getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
  }


  public void selectType() {
    if (getType() == 0) {
      setType(this.rand.nextInt(2) + 1);
    }
  }


  public ResourceLocation getTexture() {
    switch (getType()) {
      case 1:
        return MoCreatures.proxy.getTexture("ent_oak.png");
      case 2:
        return MoCreatures.proxy.getTexture("ent_birch.png");
    }
    return MoCreatures.proxy.getTexture("ent_oak.png");
  }



  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    if (damagesource.getTrueSource() != null && damagesource.getTrueSource() instanceof EntityPlayer) {
      EntityPlayer ep = (EntityPlayer)damagesource.getTrueSource();
      ItemStack currentItem = ep.inventory.getCurrentItem();
      if (currentItem != null) {
        Item itemheld = currentItem.getItem();
        if (itemheld != null && itemheld instanceof net.minecraft.item.ItemAxe) {
          this.world.getDifficulty();
          if (shouldAttackPlayers()) {
            setAttackTarget((EntityLivingBase)ep);
          }

          return super.attackEntityFrom(damagesource, i);
        }
      }
    }
    if (damagesource.isFireDamage()) {
      return super.attackEntityFrom(damagesource, i);
    }
    return false;
  }


  protected void dropFewItems(boolean flag, int x) {
    int i = this.rand.nextInt(3);
    int qty = this.rand.nextInt(12) + 4;
    int typ = 0;
    if (getType() == 2) {
      typ = 2;
    }
    if (i == 0) {
      entityDropItem(new ItemStack(Blocks.LOG, qty, typ), 0.0F);
      return;
    }
    if (i == 1) {
      entityDropItem(new ItemStack(Items.STICK, qty, 0), 0.0F);

      return;
    }
    entityDropItem(new ItemStack(Blocks.SAPLING, qty, typ), 0.0F);
  }


  protected SoundEvent getDeathSound() {
    return MoCSoundEvents.ENTITY_ENT_DEATH;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    return MoCSoundEvents.ENTITY_ENT_HURT;
  }


  protected SoundEvent getAmbientSound() {
    return MoCSoundEvents.ENTITY_ENT_AMBIENT;
  }


  public void onLivingUpdate() {
    super.onLivingUpdate();
    if (!this.world.isRemote) {

      if (getAttackTarget() == null && this.rand.nextInt(500) == 0) {
        plantOnFertileGround();
      }

      if (this.rand.nextInt(100) == 0) {
        atractCritter();
      }
    }
  }




  private void atractCritter() {
    List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(8.0D, 3.0D, 8.0D));
    int n = this.rand.nextInt(3) + 1;
    int j = 0;
    for (int k = 0; k < list.size(); k++) {
      Entity entity = list.get(k);
      if (entity instanceof EntityAnimal && entity.width < 0.6F && entity.height < 0.6F) {
        EntityAnimal entityanimal = (EntityAnimal)entity;
        if (entityanimal.getAttackTarget() == null && !MoCTools.isTamed((Entity)entityanimal)) {
          Path pathentity = entityanimal.getNavigator().getPathToEntityLiving((Entity)this);
          entityanimal.setAttackTarget((EntityLivingBase)this);
          entityanimal.getNavigator().setPath(pathentity, 1.0D);
          j++;
          if (j > n) {
            return;
          }
        }
      }
    }
  }


  private boolean plantOnFertileGround() {
    BlockPos pos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.posY), MathHelper.floor(this.posZ));
    Block blockUnderFeet = this.world.getBlockState(pos.down()).getBlock();
    Block blockOnFeet = this.world.getBlockState(pos).getBlock();

    if (blockUnderFeet == Blocks.DIRT) {
      BlockGrass blockGrass = Blocks.GRASS;
      BlockEvent.BreakEvent event = null;
      if (!this.world.isRemote)
      {
        event = new BlockEvent.BreakEvent(this.world, pos, blockGrass.getDefaultState(), (EntityPlayer)FakePlayerFactory.get((WorldServer)this.world, MoCreatures.MOCFAKEPLAYER));
      }

      if (event != null && !event.isCanceled()) {
        this.world.setBlockState(pos.down(), blockGrass.getDefaultState(), 3);
        return true;
      }
      return false;
    }

    if (blockUnderFeet == Blocks.GRASS && blockOnFeet == Blocks.AIR) {
      IBlockState iblockstate = getBlockStateToBePlanted();
      int plantChance = 3;
      if (iblockstate.getBlock() == Blocks.SAPLING) {
        plantChance = 10;
      }


      for (int x = -1; x < 2; x++) {
        for (int z = -1; z < 2; z++) {
          BlockPos pos1 = new BlockPos(MathHelper.floor(this.posX + x), MathHelper.floor(this.posY), MathHelper.floor(this.posZ + z));







          Block blockToPlant = this.world.getBlockState(pos1).getBlock();

          if (this.rand.nextInt(plantChance) == 0 && blockToPlant == Blocks.AIR) {
            this.world.setBlockState(pos1, iblockstate, 3);
          }
        }
      }
      return true;
    }

    return false;
  }






  private IBlockState getBlockStateToBePlanted() {
    int blockID = 0;
    int metaData = 0;
    switch (this.rand.nextInt(20))
    { case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
        blockID = 31;
        metaData = this.rand.nextInt(2) + 1;



































        IBlockState iblockstate = Block.getBlockById(blockID).getStateFromMeta(metaData);
        return iblockstate;case 8: case 9: case 10: blockID = 175; metaData = this.rand.nextInt(6); iblockstate = Block.getBlockById(blockID).getStateFromMeta(metaData); return iblockstate;case 11: case 12: case 13: blockID = 37; iblockstate = Block.getBlockById(blockID).getStateFromMeta(metaData); return iblockstate;case 14: case 15: case 16: blockID = 38; metaData = this.rand.nextInt(9); iblockstate = Block.getBlockById(blockID).getStateFromMeta(metaData); return iblockstate;case 17: blockID = 39; iblockstate = Block.getBlockById(blockID).getStateFromMeta(metaData); return iblockstate;case 18: blockID = 40; iblockstate = Block.getBlockById(blockID).getStateFromMeta(metaData); return iblockstate;case 19: blockID = 6; if (getType() == 2) metaData = 2;  iblockstate = Block.getBlockById(blockID).getStateFromMeta(metaData); return iblockstate; }  blockID = 31; IBlockState iblockstate = Block.getBlockById(blockID).getStateFromMeta(metaData); return iblockstate;
  }



  public boolean canBePushed() {
    return false;
  }













  protected void applyEnchantments(EntityLivingBase entityLivingBaseIn, Entity entityIn) {
    MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GOAT_SMACK);
    MoCTools.bigsmack((Entity)this, entityIn, 1.0F);
    super.applyEnchantments(entityLivingBaseIn, entityIn);
  }


  public boolean isNotScared() {
    return true;
  }


  protected boolean canTriggerWalking() {
    return false;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityEnt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
