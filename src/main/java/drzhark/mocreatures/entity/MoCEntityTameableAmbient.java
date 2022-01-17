/*     */ package drzhark.mocreatures.entity;
/*     */ 
/*     */ import com.google.common.base.Optional;
/*     */ import drzhark.mocreatures.MoCPetData;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import drzhark.mocreatures.network.MoCMessageHandler;
/*     */ import drzhark.mocreatures.network.message.MoCMessageHealth;
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
/*     */ 
/*     */ public class MoCEntityTameableAmbient
/*     */   extends MoCEntityAmbient
/*     */   implements IMoCTameable {
/*  43 */   protected static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager.createKey(MoCEntityTameableAmbient.class, DataSerializers.OPTIONAL_UNIQUE_ID);
/*  44 */   protected static final DataParameter<Integer> PET_ID = EntityDataManager.createKey(MoCEntityTameableAmbient.class, DataSerializers.VARINT);
/*  45 */   protected static final DataParameter<Boolean> TAMED = EntityDataManager.createKey(MoCEntityTameableAmbient.class, DataSerializers.BOOLEAN);
/*     */   private boolean hasEaten;
/*     */   private int gestationtime;
/*     */   
/*     */   public MoCEntityTameableAmbient(World world) {
/*  50 */     super(world);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  55 */     super.entityInit();
/*  56 */     this.dataManager.register(OWNER_UNIQUE_ID, Optional.absent());
/*  57 */     this.dataManager.register(PET_ID, Integer.valueOf(-1));
/*  58 */     this.dataManager.register(TAMED, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOwnerPetId() {
/*  63 */     return ((Integer)this.dataManager.get(PET_ID)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOwnerPetId(int i) {
/*  68 */     this.dataManager.set(PET_ID, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public UUID getOwnerId() {
/*  73 */     return (UUID)((Optional)this.dataManager.get(OWNER_UNIQUE_ID)).orNull();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOwnerId(@Nullable UUID uniqueId) {
/*  78 */     this.dataManager.set(OWNER_UNIQUE_ID, Optional.fromNullable(uniqueId));
/*     */   }
/*     */ 
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
/*     */     
/* 109 */     if (MoCreatures.proxy.enableOwnership && getOwnerId() != null && entity != null && entity instanceof EntityPlayer && 
/* 110 */       !((EntityPlayer)entity).getUniqueID().equals(getOwnerId()) && 
/* 111 */       !MoCTools.isThisPlayerAnOP((EntityPlayer)entity)) {
/* 112 */       return false;
/*     */     }
/*     */     
/* 115 */     if (!this.world.isRemote && getIsTamed()) {
/* 116 */       MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageHealth(getEntityId(), getHealth()), new NetworkRegistry.TargetPoint(this.world.provider
/* 117 */             .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */     }
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
/* 164 */     if (stack.isEmpty()) {
/* 165 */       return Boolean.valueOf(super.processInteract(player, hand));
/*     */     }
/*     */ 
/*     */     
/* 169 */     if (getIsTamed() && stack.getItem() == MoCItems.scrollOfOwner && MoCreatures.proxy.enableResetOwnership && 
/* 170 */       MoCTools.isThisPlayerAnOP(player)) {
/* 171 */       stack.shrink(1);
/* 172 */       if (stack.isEmpty()) {
/* 173 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 175 */       if (!this.world.isRemote) {
/* 176 */         if (getOwnerPetId() != -1)
/*     */         {
/* 178 */           MoCreatures.instance.mapData.removeOwnerPet(this, getOwnerPetId());
/*     */         }
/* 180 */         setOwnerId((UUID)null);
/*     */       } 
/*     */       
/* 183 */       return Boolean.valueOf(true);
/*     */     } 
/*     */     
/* 186 */     if (MoCreatures.proxy.enableOwnership && getOwnerId() != null && 
/* 187 */       !player.getUniqueID().equals(getOwnerId()) && !MoCTools.isThisPlayerAnOP(player)) {
/* 188 */       return Boolean.valueOf(true);
/*     */     }
/*     */ 
/*     */     
/* 192 */     if (!this.world.isRemote && getIsTamed() && (stack
/* 193 */       .getItem() == MoCItems.medallion || stack.getItem() == Items.BOOK || stack.getItem() == Items.NAME_TAG)) {
/* 194 */       if (MoCTools.tameWithName(player, this)) {
/* 195 */         return Boolean.valueOf(true);
/*     */       }
/* 197 */       return Boolean.valueOf(false);
/*     */     } 
/*     */ 
/*     */     
/* 201 */     if (getIsTamed() && stack.getItem() == MoCItems.scrollFreedom) {
/* 202 */       stack.shrink(1);
/* 203 */       if (stack.isEmpty()) {
/* 204 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 206 */       if (!this.world.isRemote) {
/* 207 */         if (getOwnerPetId() != -1)
/*     */         {
/* 209 */           MoCreatures.instance.mapData.removeOwnerPet(this, getOwnerPetId());
/*     */         }
/* 211 */         setOwnerId((UUID)null);
/* 212 */         setPetName("");
/* 213 */         dropMyStuff();
/* 214 */         setTamed(false);
/*     */       } 
/*     */       
/* 217 */       return Boolean.valueOf(true);
/*     */     } 
/*     */ 
/*     */     
/* 221 */     if (getIsTamed() && stack.getItem() == MoCItems.scrollOfSale) {
/* 222 */       stack.shrink(1);
/* 223 */       if (stack.isEmpty()) {
/* 224 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 226 */       if (!this.world.isRemote) {
/* 227 */         if (getOwnerPetId() != -1)
/*     */         {
/* 229 */           MoCreatures.instance.mapData.removeOwnerPet(this, getOwnerPetId());
/*     */         }
/* 231 */         setOwnerId((UUID)null);
/*     */       } 
/* 233 */       return Boolean.valueOf(true);
/*     */     } 
/*     */     
/* 236 */     if (getIsTamed() && isMyHealFood(stack)) {
/* 237 */       stack.shrink(1);
/* 238 */       if (stack.isEmpty()) {
/* 239 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 241 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_EATING);
/* 242 */       if (!this.world.isRemote) {
/* 243 */         setHealth(getMaxHealth());
/*     */       }
/* 245 */       return Boolean.valueOf(true);
/*     */     } 
/*     */ 
/*     */     
/* 249 */     if (stack.getItem() == MoCItems.fishnet && stack.getItemDamage() == 0 && canBeTrappedInNet()) {
/* 250 */       player.setHeldItem(hand, ItemStack.EMPTY);
/* 251 */       if (!this.world.isRemote) {
/* 252 */         MoCPetData petData = MoCreatures.instance.mapData.getPetData(getOwnerId());
/* 253 */         if (petData != null) {
/* 254 */           petData.setInAmulet(getOwnerPetId(), true);
/*     */         }
/* 256 */         MoCTools.dropAmulet(this, 1, player);
/* 257 */         this.isDead = true;
/*     */       } 
/*     */       
/* 260 */       return Boolean.valueOf(true);
/*     */     } 
/*     */ 
/*     */     
/* 264 */     if (getIsTamed() && isMyHealFood(stack)) {
/* 265 */       stack.shrink(1);
/* 266 */       if (stack.isEmpty()) {
/* 267 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 269 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_EATING);
/* 270 */       if (!this.world.isRemote) {
/* 271 */         setHealth(getMaxHealth());
/*     */       }
/* 273 */       return Boolean.valueOf(true);
/*     */     } 
/*     */     
/* 276 */     if (getIsTamed() && stack.getItem() == Items.SHEARS) {
/* 277 */       if (!this.world.isRemote) {
/* 278 */         dropMyStuff();
/*     */       }
/*     */       
/* 281 */       return Boolean.valueOf(true);
/*     */     } 
/*     */     
/* 284 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDead() {
/* 290 */     if (!this.world.isRemote && getIsTamed() && getHealth() > 0.0F && !this.riderIsDisconnecting) {
/*     */       return;
/*     */     }
/* 293 */     super.setDead();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playTameEffect(boolean par1) {
/* 302 */     EnumParticleTypes particleType = EnumParticleTypes.HEART;
/*     */     
/* 304 */     if (!par1) {
/* 305 */       particleType = EnumParticleTypes.SMOKE_NORMAL;
/*     */     }
/*     */     
/* 308 */     for (int i = 0; i < 7; i++) {
/* 309 */       double d0 = this.rand.nextGaussian() * 0.02D;
/* 310 */       double d1 = this.rand.nextGaussian() * 0.02D;
/* 311 */       double d2 = this.rand.nextGaussian() * 0.02D;
/* 312 */       this.world.spawnParticle(particleType, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, this.posY + 0.5D + (this.rand
/* 313 */           .nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, d0, d1, d2, new int[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
/* 319 */     super.writeEntityToNBT(nbttagcompound);
/* 320 */     nbttagcompound.setBoolean("Tamed", getIsTamed());
/* 321 */     if (getOwnerId() != null) {
/* 322 */       nbttagcompound.setString("OwnerUUID", getOwnerId().toString());
/*     */     }
/* 324 */     if (getOwnerPetId() != -1) {
/* 325 */       nbttagcompound.setInteger("PetId", getOwnerPetId());
/*     */     }
/* 327 */     if (this instanceof IMoCTameable && getIsTamed() && MoCreatures.instance.mapData != null) {
/* 328 */       MoCreatures.instance.mapData.updateOwnerPet(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
/* 334 */     super.readEntityFromNBT(nbttagcompound);
/* 335 */     setTamed(nbttagcompound.getBoolean("Tamed"));
/* 336 */     String s = "";
/* 337 */     if (nbttagcompound.hasKey("OwnerUUID", 8))
/*     */     {
/* 339 */       s = nbttagcompound.getString("OwnerUUID");
/*     */     }
/* 341 */     if (!s.isEmpty())
/*     */     {
/* 343 */       setOwnerId(UUID.fromString(s));
/*     */     }
/* 345 */     if (nbttagcompound.hasKey("PetId")) {
/* 346 */       setOwnerPetId(nbttagcompound.getInteger("PetId"));
/*     */     }
/* 348 */     if (getIsTamed() && nbttagcompound.hasKey("PetId")) {
/* 349 */       MoCPetData petData = MoCreatures.instance.mapData.getPetData(getOwnerId());
/* 350 */       if (petData != null) {
/* 351 */         NBTTagList tag = petData.getOwnerRootNBT().getTagList("TamedList", 10);
/* 352 */         for (int i = 0; i < tag.tagCount(); i++) {
/* 353 */           NBTTagCompound nbt = tag.getCompoundTagAt(i);
/* 354 */           if (nbt.getInteger("PetId") == nbttagcompound.getInteger("PetId")) {
/*     */             
/* 356 */             nbt.setBoolean("InAmulet", false);
/*     */             
/* 358 */             if (nbt.hasKey("Cloned")) {
/*     */               
/* 360 */               nbt.removeTag("Cloned");
/* 361 */               setTamed(false);
/* 362 */               setDead();
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } else {
/*     */         
/* 368 */         setOwnerPetId(-1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float getPetHealth() {
/* 375 */     return getHealth();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRiderDisconnecting() {
/* 380 */     return this.riderIsDisconnecting;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRiderDisconnecting(boolean flag) {
/* 385 */     this.riderIsDisconnecting = flag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void spawnHeart() {
/* 393 */     double var2 = this.rand.nextGaussian() * 0.02D;
/* 394 */     double var4 = this.rand.nextGaussian() * 0.02D;
/* 395 */     double var6 = this.rand.nextGaussian() * 0.02D;
/*     */     
/* 397 */     this.world.spawnParticle(EnumParticleTypes.HEART, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, this.posY + 0.5D + (this.rand
/* 398 */         .nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, var2, var4, var6, new int[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean readytoBreed() {
/* 406 */     return (!isBeingRidden() && getRidingEntity() == null && getIsTamed() && getHasEaten() && getIsAdult());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOffspringClazz(IMoCTameable mate) {
/* 411 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOffspringTypeInt(IMoCTameable mate) {
/* 416 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean compatibleMate(Entity mate) {
/* 421 */     return mate instanceof IMoCTameable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 426 */     super.onLivingUpdate();
/*     */     
/* 428 */     if (!this.world.isRemote && readytoBreed() && this.rand.nextInt(100) == 0) {
/* 429 */       doBreeding();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doBreeding() {
/* 437 */     int i = 0;
/*     */     
/* 439 */     List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(8.0D, 3.0D, 8.0D));
/* 440 */     for (int j = 0; j < list.size(); j++) {
/* 441 */       Entity entity = list.get(j);
/* 442 */       if (compatibleMate(entity)) {
/* 443 */         i++;
/*     */       }
/*     */     } 
/*     */     
/* 447 */     if (i > 1) {
/*     */       return;
/*     */     }
/*     */     
/* 451 */     List<Entity> list1 = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(4.0D, 2.0D, 4.0D));
/* 452 */     for (int k = 0; k < list1.size(); k++) {
/* 453 */       Entity mate = list1.get(k);
/* 454 */       if (compatibleMate(mate) && mate != this) {
/*     */ 
/*     */ 
/*     */         
/* 458 */         if (!readytoBreed()) {
/*     */           return;
/*     */         }
/*     */         
/* 462 */         if (!((IMoCTameable)mate).readytoBreed()) {
/*     */           return;
/*     */         }
/*     */         
/* 466 */         setGestationTime(getGestationTime() + 1);
/* 467 */         if (!this.world.isRemote) {
/* 468 */           MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageHeart(getEntityId()), new NetworkRegistry.TargetPoint(this.world.provider
/* 469 */                 .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */         }
/*     */         
/* 472 */         if (getGestationTime() > 50) {
/*     */ 
/*     */           
/*     */           try {
/*     */ 
/*     */             
/* 478 */             String offspringName = getOffspringClazz((IMoCTameable)mate);
/*     */             
/* 480 */             EntityLiving offspring = (EntityLiving)EntityList.createEntityByIDFromName(new ResourceLocation("mocreatures:" + offspringName.toLowerCase()), this.world);
/* 481 */             if (offspring != null && offspring instanceof IMoCTameable) {
/* 482 */               IMoCTameable baby = (IMoCTameable)offspring;
/* 483 */               ((EntityLiving)baby).setPosition(this.posX, this.posY, this.posZ);
/* 484 */               this.world.spawnEntity((Entity)baby);
/* 485 */               baby.setAdult(false);
/* 486 */               baby.setEdad(35);
/* 487 */               baby.setTamed(true);
/* 488 */               baby.setOwnerId(getOwnerId());
/* 489 */               baby.setType(getOffspringTypeInt((IMoCTameable)mate));
/*     */               
/* 491 */               EntityPlayer entityplayer = this.world.getPlayerEntityByUUID(getOwnerId());
/* 492 */               if (entityplayer != null) {
/* 493 */                 MoCTools.tameWithName(entityplayer, baby);
/*     */               }
/*     */             } 
/* 496 */             MoCTools.playCustomSound((Entity)this, SoundEvents.ENTITY_CHICKEN_EGG);
/*     */           }
/* 498 */           catch (Exception exception) {}
/*     */ 
/*     */           
/* 501 */           setHasEaten(false);
/* 502 */           setGestationTime(0);
/* 503 */           ((IMoCTameable)mate).setHasEaten(false);
/* 504 */           ((IMoCTameable)mate).setGestationTime(0);
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public void setHasEaten(boolean flag) {
/* 511 */     this.hasEaten = flag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getHasEaten() {
/* 519 */     return this.hasEaten;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGestationTime(int time) {
/* 524 */     this.gestationtime = time;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGestationTime() {
/* 532 */     return this.gestationtime;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\MoCEntityTameableAmbient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */