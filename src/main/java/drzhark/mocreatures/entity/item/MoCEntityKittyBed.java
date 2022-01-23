package drzhark.mocreatures.entity.item;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.init.MoCItems;
import drzhark.mocreatures.init.MoCSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class MoCEntityKittyBed extends EntityLiving {
  public float milklevel;
  private static final DataParameter<Boolean> HAS_MILK = EntityDataManager.createKey(MoCEntityKittyBed.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Boolean> HAS_FOOD = EntityDataManager.createKey(MoCEntityKittyBed.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Boolean> PICKED_UP = EntityDataManager.createKey(MoCEntityKittyBed.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Integer> SHEET_COLOR = EntityDataManager.createKey(MoCEntityKittyBed.class, DataSerializers.VARINT);

  public MoCEntityKittyBed(World world) {
    super(world);
    setSize(1.0F, 0.3F);
    this.milklevel = 0.0F;
  }

  public MoCEntityKittyBed(World world, double d, double d1, double d2) {
    super(world);
    setSize(1.0F, 0.3F);
    this.milklevel = 0.0F;
  }

  public MoCEntityKittyBed(World world, int i) {
    this(world);
    setSheetColor(i);
  }

  public ResourceLocation getTexture() {
    return MoCreatures.proxy.getTexture("fullkittybed.png");
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
  }


  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(HAS_MILK, Boolean.valueOf(false));
    this.dataManager.register(HAS_FOOD, Boolean.valueOf(false));
    this.dataManager.register(PICKED_UP, Boolean.valueOf(false));
    this.dataManager.register(SHEET_COLOR, Integer.valueOf(0));
  }

  public boolean getHasFood() {
    return ((Boolean)this.dataManager.get(HAS_FOOD)).booleanValue();
  }

  public boolean getHasMilk() {
    return ((Boolean)this.dataManager.get(HAS_MILK)).booleanValue();
  }

  public boolean getPickedUp() {
    return ((Boolean)this.dataManager.get(PICKED_UP)).booleanValue();
  }

  public int getSheetColor() {
    return ((Integer)this.dataManager.get(SHEET_COLOR)).intValue();
  }

  public void setHasFood(boolean flag) {
    this.dataManager.set(HAS_FOOD, Boolean.valueOf(flag));
  }

  public void setHasMilk(boolean flag) {
    this.dataManager.set(HAS_MILK, Boolean.valueOf(flag));
  }

  public void setPickedUp(boolean flag) {
    this.dataManager.set(PICKED_UP, Boolean.valueOf(flag));
  }

  public void setSheetColor(int i) {
    this.dataManager.set(SHEET_COLOR, Integer.valueOf(i));
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


  public boolean canEntityBeSeen(Entity entity) {
    return (this.world.rayTraceBlocks(new Vec3d(this.posX, this.posY + getEyeHeight(), this.posZ), new Vec3d(entity.posX, entity.posY + entity
          .getEyeHeight(), entity.posZ)) == null);
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
    if (!stack.isEmpty() && stack.getItem() == Items.MILK_BUCKET) {
      player.setHeldItem(hand, new ItemStack(Items.BUCKET, 1));
      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_KITTYBED_POURINGMILK);
      setHasMilk(true);
      setHasFood(false);
      return true;
    }
    if (!stack.isEmpty() && !getHasFood() && stack.getItem() == MoCItems.petfood) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_KITTYBED_POURINGFOOD);
      setHasMilk(false);
      setHasFood(true);
      return true;
    }
    if (!stack.isEmpty() && (stack.getItem() == Items.STONE_PICKAXE || stack.getItem() == Items.WOODEN_PICKAXE || stack
      .getItem() == Items.IRON_PICKAXE || stack.getItem() == Items.GOLDEN_PICKAXE || stack.getItem() == Items.DIAMOND_PICKAXE)) {
      int color = getSheetColor();
      player.inventory.addItemStackToInventory(new ItemStack((Item)MoCItems.kittybed[color], 1));
      playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
      setDead();
      return true;
    }
    if (getRidingEntity() == null) {
      if (startRiding((Entity)player)) {
        this.rotationYaw = player.rotationYaw;
        setPickedUp(true);
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
    if ((getHasMilk() || getHasFood()) && isBeingRidden() && !this.world.isRemote) {
      this.milklevel += 0.003F;
      if (this.milklevel > 2.0F) {
        this.milklevel = 0.0F;
        setHasMilk(false);
        setHasFood(false);
      }
    }
    if (isRiding()) MoCTools.dismountSneakingPlayer(this);

  }

  public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
    setHasMilk(nbttagcompound.getBoolean("HasMilk"));
    setSheetColor(nbttagcompound.getInteger("SheetColour"));
    setHasFood(nbttagcompound.getBoolean("HasFood"));
    this.milklevel = nbttagcompound.getFloat("MilkLevel");
  }


  public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
    nbttagcompound.setBoolean("HasMilk", getHasMilk());
    nbttagcompound.setInteger("SheetColour", getSheetColor());
    nbttagcompound.setBoolean("HasFood", getHasFood());
    nbttagcompound.setFloat("MilkLevel", this.milklevel);
  }


  public void onLivingUpdate() {
    this.moveStrafing = 0.0F;
    this.moveForward = 0.0F;
    this.randomYawVelocity = 0.0F;
    super.onLivingUpdate();
  }


  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    return false;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\item\MoCEntityKittyBed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
