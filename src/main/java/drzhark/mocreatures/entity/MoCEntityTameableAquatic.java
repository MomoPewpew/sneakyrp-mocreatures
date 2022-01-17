/*     */ package drzhark.mocreatures.entity;
/*     */ 
/*     */ import com.google.common.base.Optional;
/*     */ import drzhark.mocreatures.MoCPetData;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import drzhark.mocreatures.network.MoCMessageHandler;
/*     */ import drzhark.mocreatures.network.message.MoCMessageHeart;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TextComponentTranslation;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ 
/*     */ public class MoCEntityTameableAquatic
/*     */   extends MoCEntityAquatic
/*     */   implements IMoCTameable {
/*  44 */   protected static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager.createKey(MoCEntityTameableAquatic.class, DataSerializers.OPTIONAL_UNIQUE_ID);
/*  45 */   protected static final DataParameter<Integer> PET_ID = EntityDataManager.createKey(MoCEntityTameableAquatic.class, DataSerializers.VARINT);
/*  46 */   protected static final DataParameter<Boolean> TAMED = EntityDataManager.createKey(MoCEntityTameableAquatic.class, DataSerializers.BOOLEAN);
/*     */   
/*     */   private boolean hasEaten;
/*     */   private int gestationtime;
/*     */   
/*     */   public MoCEntityTameableAquatic(World world) {
/*  52 */     super(world);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  57 */     super.entityInit();
/*  58 */     this.dataManager.register(OWNER_UNIQUE_ID, Optional.absent());
/*  59 */     this.dataManager.register(PET_ID, Integer.valueOf(-1));
/*  60 */     this.dataManager.register(TAMED, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOwnerPetId() {
/*  65 */     return ((Integer)this.dataManager.get(PET_ID)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOwnerPetId(int i) {
/*  70 */     this.dataManager.set(PET_ID, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public UUID getOwnerId() {
/*  75 */     return (UUID)((Optional)this.dataManager.get(OWNER_UNIQUE_ID)).orNull();
/*     */   }
/*     */   
/*     */   public void setOwnerId(@Nullable UUID uniqueId) {
/*  79 */     this.dataManager.set(OWNER_UNIQUE_ID, Optional.fromNullable(uniqueId));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTamed(boolean flag) {
/*  84 */     this.dataManager.set(TAMED, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getIsTamed() {
/*  89 */     return ((Boolean)this.dataManager.get(TAMED)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public EntityLivingBase getOwner() {
/*     */     try {
/*  96 */       UUID uuid = getOwnerId();
/*  97 */       return (uuid == null) ? null : (EntityLivingBase)this.world.getPlayerEntityByUUID(uuid);
/*     */     }
/*  99 */     catch (IllegalArgumentException var2) {
/*     */       
/* 101 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/* 107 */     Entity entity = damagesource.getTrueSource();
/* 108 */     if ((isBeingRidden() && entity == getRidingEntity()) || (getRidingEntity() != null && entity == getRidingEntity())) {
/* 109 */       return false;
/*     */     }
/* 111 */     if (usesNewAI()) {
/* 112 */       return super.attackEntityFrom(damagesource, i);
/*     */     }
/*     */ 
/*     */     
/* 116 */     if (MoCreatures.proxy.enableOwnership && getOwnerId() != null && entity != null && entity instanceof EntityPlayer && 
/* 117 */       !((EntityPlayer)entity).getUniqueID().equals(getOwnerId()) && 
/* 118 */       !MoCTools.isThisPlayerAnOP((EntityPlayer)entity)) {
/* 119 */       return false;
/*     */     }
/*     */     
/* 122 */     return super.attackEntityFrom(damagesource, i);
/*     */   }
/*     */   
/*     */   private boolean checkOwnership(EntityPlayer player, EnumHand hand) {
/* 126 */     ItemStack stack = player.getHeldItem(hand);
/* 127 */     if (!getIsTamed() || MoCTools.isThisPlayerAnOP(player)) {
/* 128 */       return true;
/*     */     }
/*     */     
/* 131 */     if (getIsGhost() && !stack.isEmpty() && stack.getItem() == MoCItems.petamulet) {
/* 132 */       if (!this.world.isRemote) {
/*     */         
/* 134 */         ((EntityPlayerMP)player).sendAllContents(player.openContainer, player.openContainer.getInventory());
/* 135 */         player.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.RED + "This pet does not belong to you.", new Object[0]));
/*     */       } 
/* 137 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 141 */     if (MoCreatures.proxy.enableOwnership && getOwnerId() != null && 
/* 142 */       !player.getUniqueID().equals(getOwnerId())) {
/* 143 */       player.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.RED + "This pet does not belong to you.", new Object[0]));
/* 144 */       return false;
/*     */     } 
/*     */     
/* 147 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/* 152 */     Boolean tameResult = processTameInteract(player, hand);
/* 153 */     if (tameResult != null) {
/* 154 */       return tameResult.booleanValue();
/*     */     }
/*     */     
/* 157 */     return super.processInteract(player, hand);
/*     */   }
/*     */ 
/*     */   
/*     */   public Boolean processTameInteract(EntityPlayer player, EnumHand hand) {
/* 162 */     if (!checkOwnership(player, hand)) {
/* 163 */       return Boolean.valueOf(false);
/*     */     }
/*     */     
/* 166 */     ItemStack stack = player.getHeldItem(hand);
/*     */     
/* 168 */     if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.scrollOfOwner && MoCreatures.proxy.enableResetOwnership && 
/* 169 */       MoCTools.isThisPlayerAnOP(player)) {
/* 170 */       stack.shrink(1);
/* 171 */       if (stack.isEmpty()) {
/* 172 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 174 */       if (!this.world.isRemote) {
/* 175 */         if (getOwnerPetId() != -1)
/*     */         {
/* 177 */           MoCreatures.instance.mapData.removeOwnerPet(this, getOwnerPetId());
/*     */         }
/* 179 */         setOwnerId((UUID)null);
/*     */       } 
/*     */       
/* 182 */       return Boolean.valueOf(true);
/*     */     } 
/*     */     
/* 185 */     if (MoCreatures.proxy.enableOwnership && getOwnerId() != null && 
/* 186 */       !player.getUniqueID().equals(getOwnerId()) && !MoCTools.isThisPlayerAnOP(player)) {
/* 187 */       return Boolean.valueOf(true);
/*     */     }
/*     */ 
/*     */     
/* 191 */     if (!this.world.isRemote && !stack.isEmpty() && getIsTamed() && (stack
/* 192 */       .getItem() == MoCItems.medallion || stack.getItem() == Items.BOOK || stack.getItem() == Items.NAME_TAG)) {
/* 193 */       if (MoCTools.tameWithName(player, this)) {
/* 194 */         return Boolean.valueOf(true);
/*     */       }
/* 196 */       return Boolean.valueOf(false);
/*     */     } 
/*     */ 
/*     */     
/* 200 */     if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.scrollFreedom) {
/* 201 */       stack.shrink(1);
/* 202 */       if (stack.isEmpty()) {
/* 203 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 205 */       if (!this.world.isRemote) {
/* 206 */         if (getOwnerPetId() != -1)
/*     */         {
/* 208 */           MoCreatures.instance.mapData.removeOwnerPet(this, getOwnerPetId());
/*     */         }
/* 210 */         setOwnerId((UUID)null);
/* 211 */         setPetName("");
/* 212 */         dropMyStuff();
/* 213 */         setTamed(false);
/*     */       } 
/*     */       
/* 216 */       return Boolean.valueOf(true);
/*     */     } 
/*     */ 
/*     */     
/* 220 */     if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.scrollOfSale) {
/* 221 */       stack.shrink(1);
/* 222 */       if (stack.isEmpty()) {
/* 223 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 225 */       if (!this.world.isRemote) {
/* 226 */         if (getOwnerPetId() != -1)
/*     */         {
/* 228 */           MoCreatures.instance.mapData.removeOwnerPet(this, getOwnerPetId());
/*     */         }
/* 230 */         setOwnerId((UUID)null);
/*     */       } 
/* 232 */       return Boolean.valueOf(true);
/*     */     } 
/*     */     
/* 235 */     if (!stack.isEmpty() && getIsTamed() && isMyHealFood(stack)) {
/* 236 */       stack.shrink(1);
/* 237 */       if (stack.isEmpty()) {
/* 238 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 240 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_EATING);
/* 241 */       if (!this.world.isRemote) {
/* 242 */         setHealth(getMaxHealth());
/*     */       }
/* 244 */       return Boolean.valueOf(true);
/*     */     } 
/*     */ 
/*     */     
/* 248 */     if (!stack.isEmpty() && stack.getItem() == MoCItems.fishnet && stack.getItemDamage() == 0 && canBeTrappedInNet()) {
/* 249 */       if (!this.world.isRemote) {
/* 250 */         MoCPetData petData = MoCreatures.instance.mapData.getPetData(getOwnerId());
/* 251 */         if (petData != null) {
/* 252 */           petData.setInAmulet(getOwnerPetId(), true);
/*     */         }
/*     */       } 
/* 255 */       player.setHeldItem(hand, ItemStack.EMPTY);
/* 256 */       if (!this.world.isRemote) {
/* 257 */         MoCTools.dropAmulet(this, 1, player);
/* 258 */         this.isDead = true;
/*     */       } 
/*     */       
/* 261 */       return Boolean.valueOf(true);
/*     */     } 
/*     */     
/* 264 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDead() {
/* 270 */     if (!this.world.isRemote && getIsTamed() && getHealth() > 0.0F && !this.riderIsDisconnecting) {
/*     */       return;
/*     */     }
/* 273 */     super.setDead();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playTameEffect(boolean par1) {
/* 282 */     EnumParticleTypes particleType = EnumParticleTypes.HEART;
/*     */     
/* 284 */     if (!par1) {
/* 285 */       particleType = EnumParticleTypes.SMOKE_NORMAL;
/*     */     }
/*     */     
/* 288 */     for (int i = 0; i < 7; i++) {
/* 289 */       double d0 = this.rand.nextGaussian() * 0.02D;
/* 290 */       double d1 = this.rand.nextGaussian() * 0.02D;
/* 291 */       double d2 = this.rand.nextGaussian() * 0.02D;
/* 292 */       this.world.spawnParticle(particleType, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, this.posY + 0.5D + (this.rand
/* 293 */           .nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, d0, d1, d2, new int[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
/* 299 */     super.writeEntityToNBT(nbttagcompound);
/* 300 */     nbttagcompound.setBoolean("Tamed", getIsTamed());
/* 301 */     if (getOwnerId() != null) {
/* 302 */       nbttagcompound.setString("OwnerUUID", getOwnerId().toString());
/*     */     }
/* 304 */     if (getOwnerPetId() != -1) {
/* 305 */       nbttagcompound.setInteger("PetId", getOwnerPetId());
/*     */     }
/* 307 */     if (this instanceof IMoCTameable && getIsTamed() && MoCreatures.instance.mapData != null) {
/* 308 */       MoCreatures.instance.mapData.updateOwnerPet(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
/* 314 */     super.readEntityFromNBT(nbttagcompound);
/* 315 */     setTamed(nbttagcompound.getBoolean("Tamed"));
/* 316 */     String s = "";
/* 317 */     if (nbttagcompound.hasKey("OwnerUUID", 8))
/*     */     {
/* 319 */       s = nbttagcompound.getString("OwnerUUID");
/*     */     }
/* 321 */     if (!s.isEmpty())
/*     */     {
/* 323 */       setOwnerId(UUID.fromString(s));
/*     */     }
/* 325 */     if (nbttagcompound.hasKey("PetId")) {
/* 326 */       setOwnerPetId(nbttagcompound.getInteger("PetId"));
/*     */     }
/* 328 */     if (getIsTamed() && nbttagcompound.hasKey("PetId")) {
/* 329 */       MoCPetData petData = MoCreatures.instance.mapData.getPetData(getOwnerId());
/* 330 */       if (petData != null) {
/* 331 */         NBTTagList tag = petData.getOwnerRootNBT().getTagList("TamedList", 10);
/* 332 */         for (int i = 0; i < tag.tagCount(); i++) {
/* 333 */           NBTTagCompound nbt = tag.getCompoundTagAt(i);
/* 334 */           if (nbt.getInteger("PetId") == nbttagcompound.getInteger("PetId")) {
/*     */             
/* 336 */             nbt.setBoolean("InAmulet", false);
/*     */             
/* 338 */             if (nbt.hasKey("Cloned")) {
/*     */               
/* 340 */               nbt.removeTag("Cloned");
/* 341 */               setTamed(false);
/* 342 */               setDead();
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } else {
/*     */         
/* 348 */         setOwnerPetId(-1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldDismountInWater(Entity rider) {
/* 362 */     return !getIsTamed();
/*     */   }
/*     */   
/*     */   public boolean isBreedingItem(ItemStack par1ItemStack) {
/* 366 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void handleStatusUpdate(byte par1) {
/* 373 */     if (par1 == 2) {
/* 374 */       this.limbSwingAmount = 1.5F;
/* 375 */       this.hurtResistantTime = this.maxHurtResistantTime;
/* 376 */       this.hurtTime = this.maxHurtTime = 10;
/* 377 */       this.attackedAtYaw = 0.0F;
/* 378 */       playSound(getHurtSound(DamageSource.GENERIC), getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
/* 379 */       attackEntityFrom(DamageSource.GENERIC, 0.0F);
/* 380 */     } else if (par1 == 3) {
/* 381 */       playSound(getDeathSound(), getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
/* 382 */       setHealth(0.0F);
/* 383 */       onDeath(DamageSource.GENERIC);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float getPetHealth() {
/* 389 */     return getHealth();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRiderDisconnecting() {
/* 394 */     return this.riderIsDisconnecting;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRiderDisconnecting(boolean flag) {
/* 399 */     this.riderIsDisconnecting = flag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void spawnHeart() {
/* 407 */     double var2 = this.rand.nextGaussian() * 0.02D;
/* 408 */     double var4 = this.rand.nextGaussian() * 0.02D;
/* 409 */     double var6 = this.rand.nextGaussian() * 0.02D;
/*     */     
/* 411 */     this.world.spawnParticle(EnumParticleTypes.HEART, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, this.posY + 0.5D + (this.rand
/* 412 */         .nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, var2, var4, var6, new int[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean readytoBreed() {
/* 420 */     return (!isBeingRidden() && getRidingEntity() == null && getIsTamed() && getHasEaten() && getIsAdult());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOffspringClazz(IMoCTameable mate) {
/* 425 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOffspringTypeInt(IMoCTameable mate) {
/* 430 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean compatibleMate(Entity mate) {
/* 435 */     return mate instanceof IMoCTameable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 440 */     super.onLivingUpdate();
/*     */     
/* 442 */     if (!this.world.isRemote && readytoBreed() && this.rand.nextInt(100) == 0) {
/* 443 */       doBreeding();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEntityInsideOpaqueBlock() {
/* 452 */     if (getIsTamed()) {
/* 453 */       return false;
/*     */     }
/*     */     
/* 456 */     return super.isEntityInsideOpaqueBlock();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doBreeding() {
/* 463 */     int i = 0;
/*     */     
/* 465 */     List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(8.0D, 3.0D, 8.0D));
/* 466 */     for (int j = 0; j < list.size(); j++) {
/* 467 */       Entity entity = list.get(j);
/* 468 */       if (compatibleMate(entity)) {
/* 469 */         i++;
/*     */       }
/*     */     } 
/*     */     
/* 473 */     if (i > 1) {
/*     */       return;
/*     */     }
/*     */     
/* 477 */     List<Entity> list1 = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(4.0D, 2.0D, 4.0D));
/* 478 */     for (int k = 0; k < list1.size(); k++) {
/* 479 */       Entity mate = list1.get(k);
/* 480 */       if (compatibleMate(mate) && mate != this) {
/*     */ 
/*     */ 
/*     */         
/* 484 */         if (!readytoBreed()) {
/*     */           return;
/*     */         }
/*     */         
/* 488 */         if (!((IMoCTameable)mate).readytoBreed()) {
/*     */           return;
/*     */         }
/*     */         
/* 492 */         setGestationTime(getGestationTime() + 1);
/* 493 */         if (!this.world.isRemote) {
/* 494 */           MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageHeart(getEntityId()), new NetworkRegistry.TargetPoint(this.world.provider
/* 495 */                 .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */         }
/*     */         
/* 498 */         if (getGestationTime() > 50) {
/*     */ 
/*     */           
/*     */           try {
/*     */ 
/*     */             
/* 504 */             String offspringName = getOffspringClazz((IMoCTameable)mate);
/*     */             
/* 506 */             EntityLiving offspring = (EntityLiving)EntityList.createEntityByIDFromName(new ResourceLocation("mocreatures:" + offspringName.toLowerCase()), this.world);
/* 507 */             if (offspring != null && offspring instanceof IMoCTameable) {
/* 508 */               IMoCTameable baby = (IMoCTameable)offspring;
/* 509 */               ((EntityLiving)baby).setPosition(this.posX, this.posY, this.posZ);
/* 510 */               this.world.spawnEntity((Entity)baby);
/* 511 */               baby.setAdult(false);
/* 512 */               baby.setEdad(35);
/* 513 */               baby.setTamed(true);
/* 514 */               baby.setOwnerId(getOwnerId());
/* 515 */               baby.setType(getOffspringTypeInt((IMoCTameable)mate));
/*     */               
/* 517 */               EntityPlayer entityplayer = this.world.getPlayerEntityByUUID(getOwnerId());
/* 518 */               if (entityplayer != null) {
/* 519 */                 MoCTools.tameWithName(entityplayer, baby);
/*     */               }
/*     */             } 
/* 522 */             MoCTools.playCustomSound((Entity)this, SoundEvents.ENTITY_CHICKEN_EGG);
/*     */           }
/* 524 */           catch (Exception exception) {}
/*     */ 
/*     */           
/* 527 */           setHasEaten(false);
/* 528 */           setGestationTime(0);
/* 529 */           ((IMoCTameable)mate).setHasEaten(false);
/* 530 */           ((IMoCTameable)mate).setGestationTime(0);
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public void setHasEaten(boolean flag) {
/* 537 */     this.hasEaten = flag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getHasEaten() {
/* 545 */     return this.hasEaten;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGestationTime(int time) {
/* 550 */     this.gestationtime = time;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGestationTime() {
/* 558 */     return this.gestationtime;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\MoCEntityTameableAquatic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */