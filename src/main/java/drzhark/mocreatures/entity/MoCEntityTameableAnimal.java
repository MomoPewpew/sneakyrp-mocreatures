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
/*     */ import net.minecraft.entity.IEntityOwnable;
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
/*     */ public class MoCEntityTameableAnimal
/*     */   extends MoCEntityAnimal
/*     */   implements IMoCTameable, IEntityOwnable {
/*  45 */   protected static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager.createKey(MoCEntityTameableAnimal.class, DataSerializers.OPTIONAL_UNIQUE_ID);
/*  46 */   protected static final DataParameter<Integer> PET_ID = EntityDataManager.createKey(MoCEntityTameableAnimal.class, DataSerializers.VARINT);
/*  47 */   protected static final DataParameter<Boolean> TAMED = EntityDataManager.createKey(MoCEntityTameableAnimal.class, DataSerializers.BOOLEAN);
/*     */   private boolean hasEaten;
/*     */   private int gestationtime;
/*     */   
/*     */   public MoCEntityTameableAnimal(World world) {
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
/*     */   public void setTamed(boolean tamed) {
/*  84 */     this.dataManager.set(TAMED, Boolean.valueOf(tamed));
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
/*     */ 
/*     */     
/* 113 */     if (MoCreatures.proxy.enableOwnership && getOwnerId() != null && entity != null && entity instanceof EntityPlayer && 
/* 114 */       !((EntityPlayer)entity).getUniqueID().equals(getOwnerId()) && 
/* 115 */       !MoCTools.isThisPlayerAnOP((EntityPlayer)entity)) {
/* 116 */       return false;
/*     */     }
/*     */     
/* 119 */     return super.attackEntityFrom(damagesource, i);
/*     */   }
/*     */   
/*     */   private boolean checkOwnership(EntityPlayer player, EnumHand hand) {
/* 123 */     ItemStack stack = player.getHeldItem(hand);
/* 124 */     if (!getIsTamed() || MoCTools.isThisPlayerAnOP(player)) {
/* 125 */       return true;
/*     */     }
/*     */     
/* 128 */     if (getIsGhost() && !stack.isEmpty() && stack.getItem() == MoCItems.petamulet) {
/* 129 */       if (!this.world.isRemote) {
/*     */         
/* 131 */         ((EntityPlayerMP)player).sendAllContents(player.openContainer, player.openContainer.getInventory());
/* 132 */         player.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.RED + "This pet does not belong to you.", new Object[0]));
/*     */       } 
/* 134 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 138 */     if (MoCreatures.proxy.enableOwnership && getOwnerId() != null && 
/* 139 */       !player.getUniqueID().equals(getOwnerId())) {
/* 140 */       player.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.RED + "This pet does not belong to you.", new Object[0]));
/* 141 */       return false;
/*     */     } 
/*     */     
/* 144 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/* 149 */     Boolean tameResult = processTameInteract(player, hand);
/* 150 */     if (tameResult != null) {
/* 151 */       return tameResult.booleanValue();
/*     */     }
/*     */     
/* 154 */     return super.processInteract(player, hand);
/*     */   }
/*     */ 
/*     */   
/*     */   public Boolean processTameInteract(EntityPlayer player, EnumHand hand) {
/* 159 */     if (!checkOwnership(player, hand)) {
/* 160 */       return Boolean.valueOf(false);
/*     */     }
/*     */     
/* 163 */     ItemStack stack = player.getHeldItem(hand);
/*     */     
/* 165 */     if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.scrollOfOwner && MoCreatures.proxy.enableResetOwnership && 
/* 166 */       MoCTools.isThisPlayerAnOP(player)) {
/* 167 */       stack.shrink(1);
/* 168 */       if (stack.isEmpty()) {
/* 169 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 171 */       if (!this.world.isRemote) {
/* 172 */         if (getOwnerPetId() != -1)
/*     */         {
/* 174 */           MoCreatures.instance.mapData.removeOwnerPet(this, getOwnerPetId());
/*     */         }
/* 176 */         setOwnerId((UUID)null);
/*     */       } 
/* 178 */       return Boolean.valueOf(true);
/*     */     } 
/*     */     
/* 181 */     if (!this.world.isRemote && !stack.isEmpty() && getIsTamed() && (stack
/* 182 */       .getItem() == MoCItems.medallion || stack.getItem() == Items.BOOK || stack.getItem() == Items.NAME_TAG)) {
/* 183 */       if (MoCTools.tameWithName(player, this)) {
/* 184 */         return Boolean.valueOf(true);
/*     */       }
/* 186 */       return Boolean.valueOf(false);
/*     */     } 
/*     */     
/* 189 */     if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.scrollFreedom) {
/* 190 */       stack.shrink(1);
/* 191 */       if (stack.isEmpty()) {
/* 192 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 194 */       if (!this.world.isRemote) {
/* 195 */         if (getOwnerPetId() != -1)
/*     */         {
/* 197 */           MoCreatures.instance.mapData.removeOwnerPet(this, getOwnerPetId());
/*     */         }
/* 199 */         setOwnerId((UUID)null);
/* 200 */         setPetName("");
/* 201 */         dropMyStuff();
/* 202 */         setTamed(false);
/*     */       } 
/*     */       
/* 205 */       return Boolean.valueOf(true);
/*     */     } 
/*     */ 
/*     */     
/* 209 */     if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.scrollOfSale) {
/* 210 */       stack.shrink(1);
/* 211 */       if (stack.isEmpty()) {
/* 212 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 214 */       if (!this.world.isRemote) {
/* 215 */         if (getOwnerPetId() != -1)
/*     */         {
/* 217 */           MoCreatures.instance.mapData.removeOwnerPet(this, getOwnerPetId());
/*     */         }
/* 219 */         setOwnerId((UUID)null);
/*     */       } 
/* 221 */       return Boolean.valueOf(true);
/*     */     } 
/*     */ 
/*     */     
/* 225 */     if (!stack.isEmpty() && stack.getItem() == MoCItems.petamulet && stack.getItemDamage() == 0 && canBeTrappedInNet()) {
/* 226 */       player.setHeldItem(hand, ItemStack.EMPTY);
/* 227 */       if (!this.world.isRemote) {
/* 228 */         MoCPetData petData = MoCreatures.instance.mapData.getPetData(getOwnerId());
/* 229 */         if (petData != null) {
/* 230 */           petData.setInAmulet(getOwnerPetId(), true);
/*     */         }
/* 232 */         dropMyStuff();
/* 233 */         MoCTools.dropAmulet(this, 2, player);
/* 234 */         this.isDead = true;
/*     */       } 
/*     */       
/* 237 */       return Boolean.valueOf(true);
/*     */     } 
/*     */     
/* 240 */     if (!stack.isEmpty() && getIsTamed() && stack.getItem() == Items.SHEARS) {
/* 241 */       if (!this.world.isRemote) {
/* 242 */         dropMyStuff();
/*     */       }
/*     */       
/* 245 */       return Boolean.valueOf(true);
/*     */     } 
/*     */ 
/*     */     
/* 249 */     if (!stack.isEmpty() && getIsTamed() && getHealth() != getMaxHealth() && isMyHealFood(stack)) {
/* 250 */       stack.shrink(1);
/* 251 */       if (stack.isEmpty()) {
/* 252 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 254 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_EATING);
/* 255 */       if (!this.world.isRemote) {
/* 256 */         setHealth(getMaxHealth());
/*     */       }
/* 258 */       return Boolean.valueOf(true);
/*     */     } 
/*     */     
/* 261 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDead() {
/* 267 */     if (!this.world.isRemote && getIsTamed() && getHealth() > 0.0F) {
/*     */       return;
/*     */     }
/* 270 */     super.setDead();
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canDespawn() {
/* 275 */     if (MoCreatures.proxy.forceDespawns) {
/* 276 */       return !getIsTamed();
/*     */     }
/* 278 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playTameEffect(boolean par1) {
/* 288 */     EnumParticleTypes particleType = EnumParticleTypes.HEART;
/*     */     
/* 290 */     if (!par1) {
/* 291 */       particleType = EnumParticleTypes.SMOKE_NORMAL;
/*     */     }
/*     */     
/* 294 */     for (int i = 0; i < 7; i++) {
/* 295 */       double d0 = this.rand.nextGaussian() * 0.02D;
/* 296 */       double d1 = this.rand.nextGaussian() * 0.02D;
/* 297 */       double d2 = this.rand.nextGaussian() * 0.02D;
/* 298 */       this.world.spawnParticle(particleType, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, this.posY + 0.5D + (this.rand
/* 299 */           .nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, d0, d1, d2, new int[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
/* 305 */     super.writeEntityToNBT(nbttagcompound);
/* 306 */     nbttagcompound.setBoolean("Tamed", getIsTamed());
/* 307 */     if (getOwnerId() != null) {
/* 308 */       nbttagcompound.setString("OwnerUUID", getOwnerId().toString());
/*     */     }
/* 310 */     if (getOwnerPetId() != -1) {
/* 311 */       nbttagcompound.setInteger("PetId", getOwnerPetId());
/*     */     }
/* 313 */     if (this instanceof IMoCTameable && getIsTamed() && MoCreatures.instance.mapData != null) {
/* 314 */       MoCreatures.instance.mapData.updateOwnerPet(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
/* 320 */     super.readEntityFromNBT(nbttagcompound);
/* 321 */     setTamed(nbttagcompound.getBoolean("Tamed"));
/* 322 */     String s = "";
/* 323 */     if (nbttagcompound.hasKey("OwnerUUID", 8))
/*     */     {
/* 325 */       s = nbttagcompound.getString("OwnerUUID");
/*     */     }
/* 327 */     if (!s.isEmpty())
/*     */     {
/* 329 */       setOwnerId(UUID.fromString(s));
/*     */     }
/* 331 */     if (nbttagcompound.hasKey("PetId")) {
/* 332 */       setOwnerPetId(nbttagcompound.getInteger("PetId"));
/*     */     }
/* 334 */     if (getIsTamed() && nbttagcompound.hasKey("PetId")) {
/* 335 */       MoCPetData petData = MoCreatures.instance.mapData.getPetData(getOwnerId());
/* 336 */       if (petData != null) {
/* 337 */         NBTTagList tag = petData.getOwnerRootNBT().getTagList("TamedList", 10);
/* 338 */         for (int i = 0; i < tag.tagCount(); i++) {
/* 339 */           NBTTagCompound nbt = tag.getCompoundTagAt(i);
/* 340 */           if (nbt.getInteger("PetId") == nbttagcompound.getInteger("PetId")) {
/*     */             
/* 342 */             nbt.setBoolean("InAmulet", false);
/*     */             
/* 344 */             if (nbt.hasKey("Cloned")) {
/*     */               
/* 346 */               nbt.removeTag("Cloned");
/* 347 */               setTamed(false);
/* 348 */               setDead();
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } else {
/*     */         
/* 354 */         setOwnerPetId(-1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBreedingItem(ItemStack par1ItemStack) {
/* 361 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void handleStatusUpdate(byte par1) {
/* 368 */     if (par1 == 2) {
/* 369 */       this.limbSwingAmount = 1.5F;
/* 370 */       this.hurtResistantTime = this.maxHurtResistantTime;
/* 371 */       this.hurtTime = this.maxHurtTime = 10;
/* 372 */       this.attackedAtYaw = 0.0F;
/* 373 */       playSound(getHurtSound(DamageSource.GENERIC), getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
/* 374 */       attackEntityFrom(DamageSource.GENERIC, 0.0F);
/* 375 */     } else if (par1 == 3) {
/* 376 */       playSound(getDeathSound(), getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
/* 377 */       setHealth(0.0F);
/* 378 */       onDeath(DamageSource.GENERIC);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float getPetHealth() {
/* 384 */     return getHealth();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRiderDisconnecting() {
/* 389 */     return this.riderIsDisconnecting;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRiderDisconnecting(boolean flag) {
/* 394 */     this.riderIsDisconnecting = flag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void spawnHeart() {
/* 402 */     double var2 = this.rand.nextGaussian() * 0.02D;
/* 403 */     double var4 = this.rand.nextGaussian() * 0.02D;
/* 404 */     double var6 = this.rand.nextGaussian() * 0.02D;
/*     */     
/* 406 */     this.world.spawnParticle(EnumParticleTypes.HEART, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, this.posY + 0.5D + (this.rand
/* 407 */         .nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, var2, var4, var6, new int[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean readytoBreed() {
/* 415 */     return (!isBeingRidden() && getRidingEntity() == null && getIsTamed() && getHasEaten() && getIsAdult());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOffspringClazz(IMoCTameable mate) {
/* 420 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOffspringTypeInt(IMoCTameable mate) {
/* 425 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean compatibleMate(Entity mate) {
/* 430 */     return mate instanceof IMoCTameable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 435 */     super.onLivingUpdate();
/*     */     
/* 437 */     if (!this.world.isRemote && readytoBreed() && this.rand.nextInt(100) == 0) {
/* 438 */       doBreeding();
/*     */     }
/*     */     
/* 441 */     if (getIsFlying()) {
/*     */       
/* 443 */       if (this.motionX > 0.5D) {
/* 444 */         this.motionX = 0.5D;
/*     */       }
/* 446 */       if (this.motionY > 0.5D) {
/* 447 */         this.motionY = 0.5D;
/*     */       }
/* 449 */       if (this.motionZ > 2.5D) {
/* 450 */         this.motionZ = 2.5D;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doBreeding() {
/* 459 */     int i = 0;
/*     */     
/* 461 */     List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(8.0D, 3.0D, 8.0D));
/* 462 */     for (int j = 0; j < list.size(); j++) {
/* 463 */       Entity entity = list.get(j);
/* 464 */       if (compatibleMate(entity)) {
/* 465 */         i++;
/*     */       }
/*     */     } 
/*     */     
/* 469 */     if (i > 1) {
/*     */       return;
/*     */     }
/*     */     
/* 473 */     List<Entity> list1 = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(4.0D, 2.0D, 4.0D));
/* 474 */     for (int k = 0; k < list1.size(); k++) {
/* 475 */       Entity mate = list1.get(k);
/* 476 */       if (compatibleMate(mate) && mate != this) {
/*     */ 
/*     */ 
/*     */         
/* 480 */         if (!readytoBreed()) {
/*     */           return;
/*     */         }
/*     */         
/* 484 */         if (!((IMoCTameable)mate).readytoBreed()) {
/*     */           return;
/*     */         }
/*     */         
/* 488 */         setGestationTime(getGestationTime() + 1);
/* 489 */         if (!this.world.isRemote) {
/* 490 */           MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageHeart(getEntityId()), new NetworkRegistry.TargetPoint(this.world.provider
/* 491 */                 .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */         }
/*     */         
/* 494 */         if (getGestationTime() > 50) {
/*     */ 
/*     */           
/*     */           try {
/*     */ 
/*     */             
/* 500 */             String offspringName = getOffspringClazz((IMoCTameable)mate);
/*     */             
/* 502 */             EntityLiving offspring = (EntityLiving)EntityList.createEntityByIDFromName(new ResourceLocation("mocreatures:" + offspringName.toLowerCase()), this.world);
/* 503 */             if (offspring != null && offspring instanceof IMoCTameable) {
/* 504 */               IMoCTameable baby = (IMoCTameable)offspring;
/* 505 */               ((EntityLiving)baby).setPosition(this.posX, this.posY, this.posZ);
/* 506 */               this.world.spawnEntity((Entity)baby);
/* 507 */               baby.setAdult(false);
/* 508 */               baby.setEdad(35);
/* 509 */               baby.setTamed(true);
/* 510 */               baby.setOwnerId(getOwnerId());
/* 511 */               baby.setType(getOffspringTypeInt((IMoCTameable)mate));
/*     */               
/* 513 */               EntityPlayer entityplayer = this.world.getPlayerEntityByUUID(getOwnerId());
/* 514 */               if (entityplayer != null) {
/* 515 */                 MoCTools.tameWithName(entityplayer, baby);
/*     */               }
/*     */             } 
/* 518 */             MoCTools.playCustomSound((Entity)this, SoundEvents.ENTITY_CHICKEN_EGG);
/*     */           }
/* 520 */           catch (Exception exception) {}
/*     */ 
/*     */           
/* 523 */           setHasEaten(false);
/* 524 */           setGestationTime(0);
/* 525 */           ((IMoCTameable)mate).setHasEaten(false);
/* 526 */           ((IMoCTameable)mate).setGestationTime(0);
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public void setHasEaten(boolean flag) {
/* 533 */     this.hasEaten = flag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getHasEaten() {
/* 541 */     return this.hasEaten;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGestationTime(int time) {
/* 546 */     this.gestationtime = time;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGestationTime() {
/* 554 */     return this.gestationtime;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getIsGhost() {
/* 559 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\MoCEntityTameableAnimal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */