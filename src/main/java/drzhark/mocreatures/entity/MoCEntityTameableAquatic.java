package drzhark.mocreatures.entity;

import com.google.common.base.Optional;
import drzhark.mocreatures.MoCPetData;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.init.MoCItems;
import drzhark.mocreatures.init.MoCSoundEvents;
import drzhark.mocreatures.network.MoCMessageHandler;
import drzhark.mocreatures.network.message.MoCMessageHeart;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MoCEntityTameableAquatic
  extends MoCEntityAquatic
  implements IMoCTameable {
  protected static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager.createKey(MoCEntityTameableAquatic.class, DataSerializers.OPTIONAL_UNIQUE_ID);
  protected static final DataParameter<Integer> PET_ID = EntityDataManager.createKey(MoCEntityTameableAquatic.class, DataSerializers.VARINT);
  protected static final DataParameter<Boolean> TAMED = EntityDataManager.createKey(MoCEntityTameableAquatic.class, DataSerializers.BOOLEAN);

  private boolean hasEaten;
  private int gestationtime;

  public MoCEntityTameableAquatic(World world) {
    super(world);
  }


  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(OWNER_UNIQUE_ID, Optional.absent());
    this.dataManager.register(PET_ID, Integer.valueOf(-1));
    this.dataManager.register(TAMED, Boolean.valueOf(false));
  }


  public int getOwnerPetId() {
    return ((Integer)this.dataManager.get(PET_ID)).intValue();
  }


  public void setOwnerPetId(int i) {
    this.dataManager.set(PET_ID, Integer.valueOf(i));
  }

  @Nullable
  public UUID getOwnerId() {
    return (UUID)((Optional)this.dataManager.get(OWNER_UNIQUE_ID)).orNull();
  }

  public void setOwnerId(@Nullable UUID uniqueId) {
    this.dataManager.set(OWNER_UNIQUE_ID, Optional.fromNullable(uniqueId));
  }


  public void setTamed(boolean flag) {
    this.dataManager.set(TAMED, Boolean.valueOf(flag));
  }


  public boolean getIsTamed() {
    return ((Boolean)this.dataManager.get(TAMED)).booleanValue();
  }


  @Nullable
  public EntityLivingBase getOwner() {
    try {
      UUID uuid = getOwnerId();
      return (uuid == null) ? null : (EntityLivingBase)this.world.getPlayerEntityByUUID(uuid);
    }
    catch (IllegalArgumentException var2) {

      return null;
    }
  }


  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    Entity entity = damagesource.getTrueSource();
    if ((isBeingRidden() && entity == getRidingEntity()) || (getRidingEntity() != null && entity == getRidingEntity())) {
      return false;
    }
    if (usesNewAI()) {
      return super.attackEntityFrom(damagesource, i);
    }


    if (MoCreatures.proxy.enableOwnership && getOwnerId() != null && entity != null && entity instanceof EntityPlayer &&
      !((EntityPlayer)entity).getUniqueID().equals(getOwnerId()) &&
      !MoCTools.isThisPlayerAnOP((EntityPlayer)entity)) {
      return false;
    }

    return super.attackEntityFrom(damagesource, i);
  }

  private boolean checkOwnership(EntityPlayer player, EnumHand hand) {
    ItemStack stack = player.getHeldItem(hand);
    if (!getIsTamed() || MoCTools.isThisPlayerAnOP(player)) {
      return true;
    }

    if (getIsGhost() && !stack.isEmpty() && stack.getItem() == MoCItems.petamulet) {
      if (!this.world.isRemote) {

        ((EntityPlayerMP)player).sendAllContents(player.openContainer, player.openContainer.getInventory());
        player.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.RED + "This pet does not belong to you.", new Object[0]));
      }
      return false;
    }


    if (MoCreatures.proxy.enableOwnership && getOwnerId() != null &&
      !player.getUniqueID().equals(getOwnerId())) {
      player.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.RED + "This pet does not belong to you.", new Object[0]));
      return false;
    }

    return true;
  }


  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    Boolean tameResult = processTameInteract(player, hand);
    if (tameResult != null) {
      return tameResult.booleanValue();
    }

    return super.processInteract(player, hand);
  }


  public Boolean processTameInteract(EntityPlayer player, EnumHand hand) {
    if (!checkOwnership(player, hand)) {
      return Boolean.valueOf(false);
    }

    ItemStack stack = player.getHeldItem(hand);

    if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.scrollOfOwner && MoCreatures.proxy.enableResetOwnership &&
      MoCTools.isThisPlayerAnOP(player)) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      if (!this.world.isRemote) {
        if (getOwnerPetId() != -1)
        {
          MoCreatures.instance.mapData.removeOwnerPet(this, getOwnerPetId());
        }
        setOwnerId((UUID)null);
      }

      return Boolean.valueOf(true);
    }

    if (MoCreatures.proxy.enableOwnership && getOwnerId() != null &&
      !player.getUniqueID().equals(getOwnerId()) && !MoCTools.isThisPlayerAnOP(player)) {
      return Boolean.valueOf(true);
    }


    if (!this.world.isRemote && !stack.isEmpty() && getIsTamed() && (stack
      .getItem() == MoCItems.medallion || stack.getItem() == Items.BOOK || stack.getItem() == Items.NAME_TAG)) {
      if (MoCTools.tameWithName(player, this)) {
        return Boolean.valueOf(true);
      }
      return Boolean.valueOf(false);
    }


    if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.scrollFreedom) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      if (!this.world.isRemote) {
        if (getOwnerPetId() != -1)
        {
          MoCreatures.instance.mapData.removeOwnerPet(this, getOwnerPetId());
        }
        setOwnerId((UUID)null);
        setPetName("");
        dropMyStuff();
        setTamed(false);
      }

      return Boolean.valueOf(true);
    }


    if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.scrollOfSale) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      if (!this.world.isRemote) {
        if (getOwnerPetId() != -1)
        {
          MoCreatures.instance.mapData.removeOwnerPet(this, getOwnerPetId());
        }
        setOwnerId((UUID)null);
      }
      return Boolean.valueOf(true);
    }

    if (!stack.isEmpty() && getIsTamed() && isMyHealFood(stack)) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_EATING);
      if (!this.world.isRemote) {
        setHealth(getMaxHealth());
      }
      return Boolean.valueOf(true);
    }


    if (!stack.isEmpty() && stack.getItem() == MoCItems.fishnet && stack.getItemDamage() == 0 && canBeTrappedInNet()) {
      if (!this.world.isRemote) {
        MoCPetData petData = MoCreatures.instance.mapData.getPetData(getOwnerId());
        if (petData != null) {
          petData.setInAmulet(getOwnerPetId(), true);
        }
      }
      player.setHeldItem(hand, ItemStack.EMPTY);
      if (!this.world.isRemote) {
        MoCTools.dropAmulet(this, 1, player);
        this.isDead = true;
      }

      return Boolean.valueOf(true);
    }

    return null;
  }



  public void setDead() {
    if (!this.world.isRemote && getIsTamed() && getHealth() > 0.0F && !this.riderIsDisconnecting) {
      return;
    }
    super.setDead();
  }






  public void playTameEffect(boolean par1) {
    EnumParticleTypes particleType = EnumParticleTypes.HEART;

    if (!par1) {
      particleType = EnumParticleTypes.SMOKE_NORMAL;
    }

    for (int i = 0; i < 7; i++) {
      double d0 = this.rand.nextGaussian() * 0.02D;
      double d1 = this.rand.nextGaussian() * 0.02D;
      double d2 = this.rand.nextGaussian() * 0.02D;
      this.world.spawnParticle(particleType, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, this.posY + 0.5D + (this.rand
          .nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, d0, d1, d2, new int[0]);
    }
  }


  public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
    super.writeEntityToNBT(nbttagcompound);
    nbttagcompound.setBoolean("Tamed", getIsTamed());
    if (getOwnerId() != null) {
      nbttagcompound.setString("OwnerUUID", getOwnerId().toString());
    }
    if (getOwnerPetId() != -1) {
      nbttagcompound.setInteger("PetId", getOwnerPetId());
    }
    if (this instanceof IMoCTameable && getIsTamed() && MoCreatures.instance.mapData != null) {
      MoCreatures.instance.mapData.updateOwnerPet(this);
    }
  }


  public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
    super.readEntityFromNBT(nbttagcompound);
    setTamed(nbttagcompound.getBoolean("Tamed"));
    String s = "";
    if (nbttagcompound.hasKey("OwnerUUID", 8))
    {
      s = nbttagcompound.getString("OwnerUUID");
    }
    if (!s.isEmpty())
    {
      setOwnerId(UUID.fromString(s));
    }
    if (nbttagcompound.hasKey("PetId")) {
      setOwnerPetId(nbttagcompound.getInteger("PetId"));
    }
    if (getIsTamed() && nbttagcompound.hasKey("PetId")) {
      MoCPetData petData = MoCreatures.instance.mapData.getPetData(getOwnerId());
      if (petData != null) {
        NBTTagList tag = petData.getOwnerRootNBT().getTagList("TamedList", 10);
        for (int i = 0; i < tag.tagCount(); i++) {
          NBTTagCompound nbt = tag.getCompoundTagAt(i);
          if (nbt.getInteger("PetId") == nbttagcompound.getInteger("PetId")) {

            nbt.setBoolean("InAmulet", false);

            if (nbt.hasKey("Cloned")) {

              nbt.removeTag("Cloned");
              setTamed(false);
              setDead();
            }
          }
        }
      } else {

        setOwnerPetId(-1);
      }
    }
  }









  public boolean shouldDismountInWater(Entity rider) {
    return !getIsTamed();
  }

  public boolean isBreedingItem(ItemStack par1ItemStack) {
    return false;
  }



  @SideOnly(Side.CLIENT)
  public void handleStatusUpdate(byte par1) {
    if (par1 == 2) {
      this.limbSwingAmount = 1.5F;
      this.hurtResistantTime = this.maxHurtResistantTime;
      this.hurtTime = this.maxHurtTime = 10;
      this.attackedAtYaw = 0.0F;
      playSound(getHurtSound(DamageSource.GENERIC), getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
      attackEntityFrom(DamageSource.GENERIC, 0.0F);
    } else if (par1 == 3) {
      playSound(getDeathSound(), getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
      setHealth(0.0F);
      onDeath(DamageSource.GENERIC);
    }
  }


  public float getPetHealth() {
    return getHealth();
  }


  public boolean isRiderDisconnecting() {
    return this.riderIsDisconnecting;
  }


  public void setRiderDisconnecting(boolean flag) {
    this.riderIsDisconnecting = flag;
  }





  public void spawnHeart() {
    double var2 = this.rand.nextGaussian() * 0.02D;
    double var4 = this.rand.nextGaussian() * 0.02D;
    double var6 = this.rand.nextGaussian() * 0.02D;

    this.world.spawnParticle(EnumParticleTypes.HEART, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, this.posY + 0.5D + (this.rand
        .nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, var2, var4, var6, new int[0]);
  }





  public boolean readytoBreed() {
    return (!isBeingRidden() && getRidingEntity() == null && getIsTamed() && getHasEaten() && getIsAdult());
  }


  public String getOffspringClazz(IMoCTameable mate) {
    return "";
  }


  public int getOffspringTypeInt(IMoCTameable mate) {
    return 0;
  }


  public boolean compatibleMate(Entity mate) {
    return mate instanceof IMoCTameable;
  }


  public void onLivingUpdate() {
    super.onLivingUpdate();

    if (!this.world.isRemote && readytoBreed() && this.rand.nextInt(100) == 0) {
      doBreeding();
    }
  }





  public boolean isEntityInsideOpaqueBlock() {
    if (getIsTamed()) {
      return false;
    }

    return super.isEntityInsideOpaqueBlock();
  }




  protected void doBreeding() {
    int i = 0;

    List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(8.0D, 3.0D, 8.0D));
    for (int j = 0; j < list.size(); j++) {
      Entity entity = list.get(j);
      if (compatibleMate(entity)) {
        i++;
      }
    }

    if (i > 1) {
      return;
    }

    List<Entity> list1 = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(4.0D, 2.0D, 4.0D));
    for (int k = 0; k < list1.size(); k++) {
      Entity mate = list1.get(k);
      if (compatibleMate(mate) && mate != this) {



        if (!readytoBreed()) {
          return;
        }

        if (!((IMoCTameable)mate).readytoBreed()) {
          return;
        }

        setGestationTime(getGestationTime() + 1);
        if (!this.world.isRemote) {
          MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageHeart(getEntityId()), new NetworkRegistry.TargetPoint(this.world.provider
                .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
        }

        if (getGestationTime() > 50) {


          try {


            String offspringName = getOffspringClazz((IMoCTameable)mate);

            EntityLiving offspring = (EntityLiving)EntityList.createEntityByIDFromName(new ResourceLocation("mocreatures:" + offspringName.toLowerCase()), this.world);
            if (offspring != null && offspring instanceof IMoCTameable) {
              IMoCTameable baby = (IMoCTameable)offspring;
              ((EntityLiving)baby).setPosition(this.posX, this.posY, this.posZ);
              this.world.spawnEntity((Entity)baby);
              baby.setAdult(false);
              baby.setEdad(35);
              baby.setTamed(true);
              baby.setOwnerId(getOwnerId());
              baby.setType(getOffspringTypeInt((IMoCTameable)mate));

              EntityPlayer entityplayer = this.world.getPlayerEntityByUUID(getOwnerId());
              if (entityplayer != null) {
                MoCTools.tameWithName(entityplayer, baby);
              }
            }
            MoCTools.playCustomSound((Entity)this, SoundEvents.ENTITY_CHICKEN_EGG);
          }
          catch (Exception exception) {}


          setHasEaten(false);
          setGestationTime(0);
          ((IMoCTameable)mate).setHasEaten(false);
          ((IMoCTameable)mate).setGestationTime(0);
          break;
        }
      }
    }
  }
  public void setHasEaten(boolean flag) {
    this.hasEaten = flag;
  }





  public boolean getHasEaten() {
    return this.hasEaten;
  }


  public void setGestationTime(int time) {
    this.gestationtime = time;
  }





  public int getGestationTime() {
    return this.gestationtime;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\MoCEntityTameableAquatic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
