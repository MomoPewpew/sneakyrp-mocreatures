package drzhark.mocreatures.entity.item;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.monster.MoCEntityOgre;
import drzhark.mocreatures.init.MoCItems;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MoCEntityLitterBox extends EntityLiving {
  public int littertime;
  private static final DataParameter<Boolean> PICKED_UP = EntityDataManager.createKey(MoCEntityLitterBox.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Boolean> USED_LITTER = EntityDataManager.createKey(MoCEntityLitterBox.class, DataSerializers.BOOLEAN);

  public MoCEntityLitterBox(World world) {
    super(world);
    setSize(1.0F, 0.3F);
  }

  public ResourceLocation getTexture() {
    return MoCreatures.proxy.getTexture("litterbox.png");
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
  }


  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(PICKED_UP, Boolean.valueOf(false));
    this.dataManager.register(USED_LITTER, Boolean.valueOf(false));
  }

  public boolean getPickedUp() {
    return ((Boolean)this.dataManager.get(PICKED_UP)).booleanValue();
  }

  public boolean getUsedLitter() {
    return ((Boolean)this.dataManager.get(USED_LITTER)).booleanValue();
  }

  public void setPickedUp(boolean flag) {
    this.dataManager.set(PICKED_UP, Boolean.valueOf(flag));
  }

  public void setUsedLitter(boolean flag) {
    this.dataManager.set(USED_LITTER, Boolean.valueOf(flag));
  }

  public boolean attackEntityFrom(Entity entity, int i) {
    return false;
  }


  public boolean canBeCollidedWith() {
    return !this.isDead;
  }


  public boolean canBePushed() {
    return !this.isDead;
  }


  public boolean canBreatheUnderwater() {
    return true;
  }


  protected boolean canDespawn() {
    return false;
  }



  public void fall(float f, float f1) {}


  protected float getSoundVolume() {
    return 0.0F;
  }


  public double getYOffset() {
    if (getRidingEntity() instanceof EntityPlayer)
    {
      return ((EntityPlayer)getRidingEntity()).isSneaking() ? 0.25D : 0.5D;
    }
    return super.getYOffset();
  }



  public void handleStatusUpdate(byte byte0) {}



  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    ItemStack stack = player.getHeldItem(hand);
    if (!stack.isEmpty() && (stack.getItem() == Items.STONE_PICKAXE || stack.getItem() == Items.WOODEN_PICKAXE || stack
      .getItem() == Items.IRON_PICKAXE || stack.getItem() == Items.GOLDEN_PICKAXE || stack.getItem() == Items.DIAMOND_PICKAXE)) {
      player.inventory.addItemStackToInventory(new ItemStack((Item)MoCItems.litterbox));
      playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
      setDead();
      return true;
    }

    if (!stack.isEmpty() && stack.getItem() == Item.getItemFromBlock((Block)Blocks.SAND)) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      setUsedLitter(false);
      this.littertime = 0;
      return true;
    }

    if (getRidingEntity() == null) {
      if (startRiding((Entity)player)) {
        setPickedUp(true);
        this.rotationYaw = player.rotationYaw;
      }

      return true;
    }

    return true;
  }


  public void move(MoverType type, double d, double d1, double d2) {
    if ((getRidingEntity() != null || !this.onGround || !MoCreatures.proxy.staticLitter) &&
      !this.world.isRemote) {
      super.move(type, d, d1, d2);
    }
  }



  public void onUpdate() {
    super.onUpdate();
    if (this.onGround) {
      setPickedUp(false);
    }
    if (getUsedLitter() && !this.world.isRemote) {
      this.littertime++;
      this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
      List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(12.0D, 4.0D, 12.0D));
      for (int i = 0; i < list.size(); i++) {
        Entity entity = list.get(i);
        if (entity instanceof EntityMob) {


          EntityMob entitymob = (EntityMob)entity;
          entitymob.setAttackTarget((EntityLivingBase)this);
          if (entitymob instanceof EntityCreeper) {
            ((EntityCreeper)entitymob).setCreeperState(-1);
          }
          if (entitymob instanceof MoCEntityOgre) {
            ((MoCEntityOgre)entitymob).smashCounter = 0;
          }
        }
      }
    }
    if (this.littertime > 5000 && !this.world.isRemote) {
      setUsedLitter(false);
      this.littertime = 0;
    }

    if (isRiding()) MoCTools.dismountSneakingPlayer(this);

  }

  public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
    nbttagcompound = MoCTools.getEntityData((Entity)this);
    nbttagcompound.setBoolean("UsedLitter", getUsedLitter());
  }


  public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
    nbttagcompound = MoCTools.getEntityData((Entity)this);
    setUsedLitter(nbttagcompound.getBoolean("UsedLitter"));
  }


  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    return false;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\item\MoCEntityLitterBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
