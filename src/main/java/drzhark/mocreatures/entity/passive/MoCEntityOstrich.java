/*     */ package drzhark.mocreatures.entity.passive;
/*     */ 
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.MoCEntityAnimal;
/*     */ import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFollowAdult;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import drzhark.mocreatures.entity.item.MoCEntityEgg;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import drzhark.mocreatures.inventory.MoCAnimalChest;
/*     */ import drzhark.mocreatures.network.MoCMessageHandler;
/*     */ import drzhark.mocreatures.network.message.MoCMessageAnimation;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.inventory.EntityEquipmentSlot;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.pathfinding.Path;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*     */ 
/*     */ public class MoCEntityOstrich
/*     */   extends MoCEntityTameableAnimal {
/*     */   private int eggCounter;
/*     */   private int hidingCounter;
/*     */   public int mouthCounter;
/*     */   public int wingCounter;
/*     */   public int sprintCounter;
/*  60 */   private static final DataParameter<Boolean> RIDEABLE = EntityDataManager.createKey(MoCEntityOstrich.class, DataSerializers.BOOLEAN); public int jumpCounter; public int transformCounter; public int transformType; public boolean canLayEggs; public MoCAnimalChest localchest; public ItemStack localstack;
/*  61 */   private static final DataParameter<Boolean> EGG_WATCH = EntityDataManager.createKey(MoCEntityOstrich.class, DataSerializers.BOOLEAN);
/*  62 */   private static final DataParameter<Boolean> CHESTED = EntityDataManager.createKey(MoCEntityOstrich.class, DataSerializers.BOOLEAN);
/*  63 */   private static final DataParameter<Boolean> IS_HIDING = EntityDataManager.createKey(MoCEntityOstrich.class, DataSerializers.BOOLEAN);
/*  64 */   private static final DataParameter<Integer> HELMET_TYPE = EntityDataManager.createKey(MoCEntityOstrich.class, DataSerializers.VARINT);
/*  65 */   private static final DataParameter<Integer> FLAG_COLOR = EntityDataManager.createKey(MoCEntityOstrich.class, DataSerializers.VARINT);
/*     */ 
/*     */   
/*     */   public MoCEntityOstrich(World world) {
/*  69 */     super(world);
/*  70 */     setSize(1.0F, 1.6F);
/*  71 */     setEdad(35);
/*  72 */     this.eggCounter = this.rand.nextInt(1000) + 1000;
/*  73 */     this.stepHeight = 1.0F;
/*  74 */     this.canLayEggs = false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initEntityAI() {
/*  79 */     this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  80 */     this.tasks.addTask(4, (EntityAIBase)new EntityAIFollowAdult((EntityLiving)this, 1.0D));
/*  81 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
/*  82 */     this.tasks.addTask(6, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D));
/*  83 */     this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  88 */     super.applyEntityAttributes();
/*  89 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
/*  90 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
/*  91 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
/*  92 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  97 */     super.entityInit();
/*  98 */     this.dataManager.register(EGG_WATCH, Boolean.valueOf(false));
/*  99 */     this.dataManager.register(CHESTED, Boolean.valueOf(false));
/* 100 */     this.dataManager.register(RIDEABLE, Boolean.valueOf(false));
/* 101 */     this.dataManager.register(IS_HIDING, Boolean.valueOf(false));
/* 102 */     this.dataManager.register(HELMET_TYPE, Integer.valueOf(0));
/* 103 */     this.dataManager.register(FLAG_COLOR, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getIsRideable() {
/* 108 */     return ((Boolean)this.dataManager.get(RIDEABLE)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRideable(boolean flag) {
/* 113 */     this.dataManager.set(RIDEABLE, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public boolean getEggWatching() {
/* 117 */     return ((Boolean)this.dataManager.get(EGG_WATCH)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setEggWatching(boolean flag) {
/* 121 */     this.dataManager.set(EGG_WATCH, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public boolean getHiding() {
/* 125 */     return ((Boolean)this.dataManager.get(IS_HIDING)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setHiding(boolean flag) {
/* 129 */     this.dataManager.set(IS_HIDING, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public int getHelmet() {
/* 133 */     return ((Integer)this.dataManager.get(HELMET_TYPE)).intValue();
/*     */   }
/*     */   
/*     */   public void setHelmet(int i) {
/* 137 */     this.dataManager.set(HELMET_TYPE, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public int getFlagColor() {
/* 141 */     return ((Integer)this.dataManager.get(FLAG_COLOR)).intValue();
/*     */   }
/*     */   
/*     */   public void setFlagColor(int i) {
/* 145 */     this.dataManager.set(FLAG_COLOR, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public boolean getIsChested() {
/* 149 */     return ((Boolean)this.dataManager.get(CHESTED)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setIsChested(boolean flag) {
/* 153 */     this.dataManager.set(CHESTED, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMovementCeased() {
/* 158 */     return (getHiding() || isBeingRidden());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNotScared() {
/* 163 */     return ((getType() == 2 && getAttackTarget() != null) || getType() > 2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/* 169 */     if (getIsTamed() && getHelmet() != 0) {
/* 170 */       int j = 0;
/* 171 */       switch (getHelmet()) {
/*     */         case 1:
/* 173 */           j = 1;
/*     */           break;
/*     */         case 2:
/*     */         case 5:
/*     */         case 6:
/* 178 */           j = 2;
/*     */           break;
/*     */         case 3:
/*     */         case 7:
/* 182 */           j = 3;
/*     */           break;
/*     */         case 4:
/*     */         case 9:
/*     */         case 10:
/*     */         case 11:
/*     */         case 12:
/* 189 */           j = 4;
/*     */           break;
/*     */       } 
/* 192 */       i -= j;
/* 193 */       if (i <= 0.0F) {
/* 194 */         i = 1.0F;
/*     */       }
/*     */     } 
/*     */     
/* 198 */     if (super.attackEntityFrom(damagesource, i)) {
/* 199 */       Entity entity = damagesource.getTrueSource();
/*     */       
/* 201 */       if (!(entity instanceof EntityLivingBase) || (isBeingRidden() && entity == getRidingEntity()) || (entity instanceof EntityPlayer && 
/* 202 */         getIsTamed())) {
/* 203 */         return false;
/*     */       }
/*     */       
/* 206 */       if (entity != this && shouldAttackPlayers() && getType() > 2) {
/* 207 */         setAttackTarget((EntityLivingBase)entity);
/* 208 */         flapWings();
/*     */       } 
/* 210 */       return true;
/*     */     } 
/* 212 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDeath(DamageSource damagesource) {
/* 218 */     super.onDeath(damagesource);
/* 219 */     dropMyStuff();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityAsMob(Entity entityIn) {
/* 224 */     if (entityIn instanceof EntityPlayer && !shouldAttackPlayers()) {
/* 225 */       return false;
/*     */     }
/* 227 */     openMouth();
/* 228 */     flapWings();
/* 229 */     return super.attackEntityAsMob(entityIn);
/*     */   }
/*     */   
/*     */   public float calculateMaxHealth() {
/* 233 */     switch (getType()) {
/*     */       case 1:
/* 235 */         return 10.0F;
/*     */       case 2:
/* 237 */         return 20.0F;
/*     */       case 3:
/* 239 */         return 25.0F;
/*     */       case 4:
/* 241 */         return 25.0F;
/*     */       case 5:
/* 243 */         return 35.0F;
/*     */       case 6:
/* 245 */         return 35.0F;
/*     */     } 
/* 247 */     return 20.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeCollidedWith() {
/* 253 */     return !isBeingRidden();
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectType() {
/* 258 */     if (getType() == 0) {
/*     */ 
/*     */ 
/*     */       
/* 262 */       int j = this.rand.nextInt(100);
/* 263 */       if (j <= 20) {
/* 264 */         setType(1);
/* 265 */       } else if (j <= 65) {
/* 266 */         setType(2);
/* 267 */       } else if (j <= 95) {
/* 268 */         setType(3);
/*     */       } else {
/* 270 */         setType(4);
/*     */       } 
/*     */     } 
/* 273 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(calculateMaxHealth());
/* 274 */     setHealth(getMaxHealth());
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/* 279 */     if (this.transformCounter != 0 && this.transformType > 4) {
/* 280 */       String newText = "ostricha.png";
/* 281 */       if (this.transformType == 5) {
/* 282 */         newText = "ostriche.png";
/*     */       }
/* 284 */       if (this.transformType == 6) {
/* 285 */         newText = "ostrichf.png";
/*     */       }
/* 287 */       if (this.transformType == 7) {
/* 288 */         newText = "ostrichg.png";
/*     */       }
/* 290 */       if (this.transformType == 8) {
/* 291 */         newText = "ostrichh.png";
/*     */       }
/*     */       
/* 294 */       if (this.transformCounter % 5 == 0) {
/* 295 */         return MoCreatures.proxy.getTexture(newText);
/*     */       }
/* 297 */       if (this.transformCounter > 50 && this.transformCounter % 3 == 0) {
/* 298 */         return MoCreatures.proxy.getTexture(newText);
/*     */       }
/*     */       
/* 301 */       if (this.transformCounter > 75 && this.transformCounter % 4 == 0) {
/* 302 */         return MoCreatures.proxy.getTexture(newText);
/*     */       }
/*     */     } 
/*     */     
/* 306 */     switch (getType()) {
/*     */       case 1:
/* 308 */         return MoCreatures.proxy.getTexture("ostrichc.png");
/*     */       case 2:
/* 310 */         return MoCreatures.proxy.getTexture("ostrichb.png");
/*     */       case 3:
/* 312 */         return MoCreatures.proxy.getTexture("ostricha.png");
/*     */       case 4:
/* 314 */         return MoCreatures.proxy.getTexture("ostrichd.png");
/*     */       case 5:
/* 316 */         return MoCreatures.proxy.getTexture("ostriche.png");
/*     */       case 6:
/* 318 */         return MoCreatures.proxy.getTexture("ostrichf.png");
/*     */       case 7:
/* 320 */         return MoCreatures.proxy.getTexture("ostrichg.png");
/*     */       case 8:
/* 322 */         return MoCreatures.proxy.getTexture("ostrichh.png");
/*     */     } 
/* 324 */     return MoCreatures.proxy.getTexture("ostricha.png");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getCustomSpeed() {
/* 330 */     double OstrichSpeed = 0.8D;
/* 331 */     if (getType() == 1) {
/* 332 */       OstrichSpeed = 0.8D;
/* 333 */     } else if (getType() == 2) {
/* 334 */       OstrichSpeed = 0.8D;
/* 335 */     } else if (getType() == 3) {
/* 336 */       OstrichSpeed = 1.1D;
/* 337 */     } else if (getType() == 4) {
/* 338 */       OstrichSpeed = 1.3D;
/* 339 */     } else if (getType() == 5) {
/* 340 */       OstrichSpeed = 1.4D;
/* 341 */       this.isImmuneToFire = true;
/*     */     } 
/* 343 */     if (this.sprintCounter > 0 && this.sprintCounter < 200) {
/* 344 */       OstrichSpeed *= 1.5D;
/*     */     }
/* 346 */     if (this.sprintCounter > 200) {
/* 347 */       OstrichSpeed *= 0.5D;
/*     */     }
/* 349 */     return OstrichSpeed;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean rideableEntity() {
/* 354 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 359 */     super.onUpdate();
/*     */     
/* 361 */     if (getHiding()) {
/* 362 */       this.prevRenderYawOffset = this.renderYawOffset = this.rotationYaw = this.prevRotationYaw;
/*     */     }
/*     */     
/* 365 */     if (this.mouthCounter > 0 && ++this.mouthCounter > 20) {
/* 366 */       this.mouthCounter = 0;
/*     */     }
/*     */     
/* 369 */     if (this.wingCounter > 0 && ++this.wingCounter > 80) {
/* 370 */       this.wingCounter = 0;
/*     */     }
/*     */     
/* 373 */     if (this.jumpCounter > 0 && ++this.jumpCounter > 8) {
/* 374 */       this.jumpCounter = 0;
/*     */     }
/*     */     
/* 377 */     if (this.sprintCounter > 0 && ++this.sprintCounter > 300) {
/* 378 */       this.sprintCounter = 0;
/*     */     }
/*     */     
/* 381 */     if (this.transformCounter > 0) {
/* 382 */       if (this.transformCounter == 40) {
/* 383 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_TRANSFORM);
/*     */       }
/*     */       
/* 386 */       if (++this.transformCounter > 100) {
/* 387 */         this.transformCounter = 0;
/* 388 */         if (this.transformType != 0) {
/* 389 */           dropArmor();
/* 390 */           setType(this.transformType);
/* 391 */           selectType();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void transform(int tType) {
/* 398 */     if (!this.world.isRemote) {
/* 399 */       MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), tType), new NetworkRegistry.TargetPoint(this.world.provider
/* 400 */             .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */     }
/* 402 */     this.transformType = tType;
/* 403 */     if (!isBeingRidden() && this.transformType != 0) {
/* 404 */       dropArmor();
/* 405 */       this.transformCounter = 1;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void performAnimation(int animationType) {
/* 411 */     if (animationType >= 5 && animationType < 9) {
/*     */       
/* 413 */       this.transformType = animationType;
/* 414 */       this.transformCounter = 1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 421 */     super.onLivingUpdate();
/*     */     
/* 423 */     if (getIsTamed() && !this.world.isRemote && this.rand.nextInt(300) == 0 && getHealth() <= getMaxHealth() && this.deathTime == 0) {
/* 424 */       setHealth(getHealth() + 1.0F);
/*     */     }
/*     */     
/* 427 */     if (!this.world.isRemote) {
/*     */       
/* 429 */       if (getType() == 8 && this.sprintCounter > 0 && this.sprintCounter < 150 && isBeingRidden() && this.rand.nextInt(15) == 0) {
/* 430 */         MoCTools.buckleMobs((EntityLiving)this, Double.valueOf(2.0D), this.world);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 440 */       if (getHiding())
/*     */       {
/*     */         
/* 443 */         if (++this.hidingCounter > 500 && !getIsTamed()) {
/* 444 */           setHiding(false);
/* 445 */           this.hidingCounter = 0;
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 450 */       if (getType() == 1 && this.rand.nextInt(200) == 0) {
/*     */         
/* 452 */         setEdad(getEdad() + 1);
/* 453 */         if (getEdad() >= 100) {
/* 454 */           setAdult(true);
/* 455 */           setType(0);
/* 456 */           selectType();
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 461 */       if (this.canLayEggs && getType() == 2 && !getEggWatching() && --this.eggCounter <= 0 && this.rand.nextInt(5) == 0) {
/*     */         
/* 463 */         EntityPlayer entityplayer1 = this.world.getClosestPlayerToEntity((Entity)this, 12.0D);
/* 464 */         if (entityplayer1 != null) {
/* 465 */           double distP = MoCTools.getSqDistanceTo((Entity)entityplayer1, this.posX, this.posY, this.posZ);
/* 466 */           if (distP < 10.0D) {
/* 467 */             int OstrichEggType = 30;
/* 468 */             MoCEntityOstrich maleOstrich = getClosestMaleOstrich((Entity)this, 8.0D);
/* 469 */             if (maleOstrich != null && this.rand.nextInt(100) < MoCreatures.proxy.ostrichEggDropChance) {
/* 470 */               MoCEntityEgg entityegg = new MoCEntityEgg(this.world, OstrichEggType);
/* 471 */               entityegg.setPosition(this.posX, this.posY, this.posZ);
/* 472 */               this.world.spawnEntity((Entity)entityegg);
/*     */               
/* 474 */               if (!getIsTamed()) {
/* 475 */                 setEggWatching(true);
/* 476 */                 if (maleOstrich != null) {
/* 477 */                   maleOstrich.setEggWatching(true);
/*     */                 }
/* 479 */                 openMouth();
/*     */               } 
/*     */ 
/*     */               
/* 483 */               MoCTools.playCustomSound((Entity)this, SoundEvents.ENTITY_CHICKEN_EGG);
/*     */ 
/*     */               
/* 486 */               this.eggCounter = this.rand.nextInt(2000) + 2000;
/* 487 */               this.canLayEggs = false;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 494 */       if (getEggWatching()) {
/*     */         
/* 496 */         MoCEntityEgg myEgg = (MoCEntityEgg)getBoogey(8.0D);
/* 497 */         if (myEgg != null && MoCTools.getSqDistanceTo((Entity)myEgg, this.posX, this.posY, this.posZ) > 4.0D) {
/* 498 */           Path pathentity = this.navigator.getPathToPos(myEgg.getPosition());
/* 499 */           this.navigator.setPath(pathentity, 16.0D);
/*     */         } 
/* 501 */         if (myEgg == null) {
/*     */           
/* 503 */           setEggWatching(false);
/*     */           
/* 505 */           EntityPlayer eggStealer = this.world.getClosestPlayerToEntity((Entity)this, 10.0D);
/* 506 */           if (eggStealer != null) {
/* 507 */             this.world.getDifficulty();
/* 508 */             if (!getIsTamed() && this.world.getDifficulty() != EnumDifficulty.PEACEFUL) {
/* 509 */               setAttackTarget((EntityLivingBase)eggStealer);
/* 510 */               flapWings();
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected MoCEntityOstrich getClosestMaleOstrich(Entity entity, double d) {
/* 519 */     double d1 = -1.0D;
/* 520 */     MoCEntityOstrich entityliving = null;
/* 521 */     List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(entity, entity.getEntityBoundingBox().expand(d, d, d));
/* 522 */     for (int i = 0; i < list.size(); i++) {
/* 523 */       Entity entity1 = list.get(i);
/* 524 */       if (entity1 instanceof MoCEntityOstrich && (!(entity1 instanceof MoCEntityOstrich) || ((MoCEntityOstrich)entity1).getType() >= 3)) {
/*     */ 
/*     */ 
/*     */         
/* 528 */         double d2 = entity1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
/* 529 */         if ((d < 0.0D || d2 < d * d) && (d1 == -1.0D || d2 < d1)) {
/* 530 */           d1 = d2;
/* 531 */           entityliving = (MoCEntityOstrich)entity1;
/*     */         } 
/*     */       } 
/*     */     } 
/* 535 */     return entityliving;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean entitiesToInclude(Entity entity) {
/* 540 */     return (entity instanceof MoCEntityEgg && ((MoCEntityEgg)entity).eggType == 30);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/* 547 */     Boolean tameResult = processTameInteract(player, hand);
/* 548 */     if (tameResult != null) {
/* 549 */       return tameResult.booleanValue();
/*     */     }
/*     */     
/* 552 */     ItemStack stack = player.getHeldItem(hand);
/* 553 */     if (getIsTamed() && getType() > 1 && !stack.isEmpty() && !getIsRideable() && (stack
/* 554 */       .getItem() == MoCItems.horsesaddle || stack.getItem() == Items.SADDLE)) {
/* 555 */       stack.shrink(1);
/* 556 */       if (stack.isEmpty()) {
/* 557 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 559 */       MoCTools.playCustomSound((Entity)this, SoundEvents.ENTITY_CHICKEN_EGG);
/* 560 */       setRideable(true);
/* 561 */       return true;
/*     */     } 
/*     */     
/* 564 */     if (!getIsTamed() && !stack.isEmpty() && getType() == 2 && stack.getItem() == Items.MELON_SEEDS) {
/* 565 */       stack.shrink(1);
/* 566 */       if (stack.isEmpty()) {
/* 567 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/*     */       
/* 570 */       openMouth();
/* 571 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_EATING);
/* 572 */       this.canLayEggs = true;
/* 573 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 577 */     if (!stack.isEmpty() && stack.getItem() == MoCItems.whip && getIsTamed() && !isBeingRidden()) {
/* 578 */       setHiding(!getHiding());
/* 579 */       return true;
/*     */     } 
/*     */     
/* 582 */     if (!stack.isEmpty() && getIsTamed() && getType() > 1 && stack.getItem() == MoCItems.essencedarkness) {
/* 583 */       stack.shrink(1);
/* 584 */       if (stack.isEmpty()) {
/* 585 */         player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
/*     */       } else {
/* 587 */         player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
/*     */       } 
/* 589 */       if (getType() == 6) {
/* 590 */         setHealth(getMaxHealth());
/*     */       } else {
/* 592 */         transform(6);
/*     */       } 
/* 594 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_DRINKING);
/* 595 */       return true;
/*     */     } 
/*     */     
/* 598 */     if (!stack.isEmpty() && getIsTamed() && getType() > 1 && stack.getItem() == MoCItems.essenceundead) {
/* 599 */       stack.shrink(1);
/* 600 */       if (stack.isEmpty()) {
/* 601 */         player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
/*     */       } else {
/* 603 */         player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
/*     */       } 
/* 605 */       if (getType() == 7) {
/* 606 */         setHealth(getMaxHealth());
/*     */       } else {
/* 608 */         transform(7);
/*     */       } 
/* 610 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_DRINKING);
/* 611 */       return true;
/*     */     } 
/*     */     
/* 614 */     if (!stack.isEmpty() && getIsTamed() && getType() > 1 && stack.getItem() == MoCItems.essencelight) {
/* 615 */       stack.shrink(1);
/* 616 */       if (stack.isEmpty()) {
/* 617 */         player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
/*     */       } else {
/* 619 */         player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
/*     */       } 
/* 621 */       if (getType() == 8) {
/* 622 */         setHealth(getMaxHealth());
/*     */       } else {
/* 624 */         transform(8);
/*     */       } 
/* 626 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_DRINKING);
/* 627 */       return true;
/*     */     } 
/*     */     
/* 630 */     if (!stack.isEmpty() && getIsTamed() && getType() > 1 && stack.getItem() == MoCItems.essencefire) {
/* 631 */       stack.shrink(1);
/* 632 */       if (stack.isEmpty()) {
/* 633 */         player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
/*     */       } else {
/* 635 */         player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
/*     */       } 
/* 637 */       if (getType() == 5) {
/* 638 */         setHealth(getMaxHealth());
/*     */       } else {
/* 640 */         transform(5);
/*     */       } 
/* 642 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_DRINKING);
/* 643 */       return true;
/*     */     } 
/* 645 */     if (getIsTamed() && getIsChested() && getType() > 1 && !stack.isEmpty() && stack.getItem() == Item.getItemFromBlock(Blocks.WOOL)) {
/* 646 */       int colorInt = stack.getItemDamage();
/* 647 */       if (colorInt == 0) {
/* 648 */         colorInt = 16;
/*     */       }
/* 650 */       stack.shrink(1);
/* 651 */       if (stack.isEmpty()) {
/* 652 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 654 */       MoCTools.playCustomSound((Entity)this, SoundEvents.ENTITY_CHICKEN_EGG);
/* 655 */       dropFlag();
/* 656 */       setFlagColor((byte)colorInt);
/* 657 */       return true;
/*     */     } 
/*     */     
/* 660 */     if (!stack.isEmpty() && getType() > 1 && getIsTamed() && !getIsChested() && stack.getItem() == Item.getItemFromBlock((Block)Blocks.CHEST)) {
/* 661 */       stack.shrink(1);
/* 662 */       if (stack.isEmpty()) {
/* 663 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/*     */ 
/*     */       
/* 667 */       setIsChested(true);
/* 668 */       MoCTools.playCustomSound((Entity)this, SoundEvents.ENTITY_CHICKEN_EGG);
/* 669 */       return true;
/*     */     } 
/*     */     
/* 672 */     if (player.isSneaking() && getIsChested()) {
/*     */       
/* 674 */       if (this.localchest == null) {
/* 675 */         this.localchest = new MoCAnimalChest("OstrichChest", 9);
/*     */       }
/* 677 */       if (!this.world.isRemote) {
/* 678 */         player.displayGUIChest((IInventory)this.localchest);
/*     */       }
/* 680 */       return true;
/*     */     } 
/*     */     
/* 683 */     if (getIsTamed() && getType() > 1 && !stack.isEmpty()) {
/*     */       
/* 685 */       Item item = stack.getItem();
/* 686 */       if (item instanceof ItemArmor && ((ItemArmor)item).armorType == EntityEquipmentSlot.HEAD) {
/* 687 */         ItemArmor itemArmor = (ItemArmor)stack.getItem();
/* 688 */         byte helmetType = 0;
/* 689 */         if (stack.getItem() == Items.LEATHER_HELMET) {
/* 690 */           helmetType = 1;
/* 691 */         } else if (stack.getItem() == Items.IRON_HELMET) {
/* 692 */           helmetType = 2;
/* 693 */         } else if (stack.getItem() == Items.GOLDEN_HELMET) {
/* 694 */           helmetType = 3;
/* 695 */         } else if (stack.getItem() == Items.DIAMOND_HELMET) {
/* 696 */           helmetType = 4;
/* 697 */         } else if (stack.getItem() == MoCItems.helmetHide) {
/* 698 */           helmetType = 5;
/* 699 */         } else if (stack.getItem() == MoCItems.helmetFur) {
/* 700 */           helmetType = 6;
/* 701 */         } else if (stack.getItem() == MoCItems.helmetCroc) {
/* 702 */           helmetType = 7;
/* 703 */         } else if (stack.getItem() == MoCItems.scorpHelmetDirt) {
/* 704 */           helmetType = 9;
/* 705 */         } else if (stack.getItem() == MoCItems.scorpHelmetFrost) {
/* 706 */           helmetType = 10;
/* 707 */         } else if (stack.getItem() == MoCItems.scorpHelmetCave) {
/* 708 */           helmetType = 11;
/* 709 */         } else if (stack.getItem() == MoCItems.scorpHelmetNether) {
/* 710 */           helmetType = 12;
/*     */         } 
/*     */         
/* 713 */         if (helmetType != 0) {
/* 714 */           player.setHeldItem(hand, ItemStack.EMPTY);
/* 715 */           dropArmor();
/* 716 */           setItemStackToSlot(itemArmor.armorType, stack);
/* 717 */           MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_OFF);
/* 718 */           setHelmet(helmetType);
/* 719 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/* 723 */     if (getIsRideable() && getIsAdult() && (!getIsChested() || !player.isSneaking()) && !isBeingRidden()) {
/* 724 */       if (!this.world.isRemote && player.startRiding((Entity)this)) {
/* 725 */         player.rotationYaw = this.rotationYaw;
/* 726 */         player.rotationPitch = this.rotationPitch;
/* 727 */         setHiding(false);
/*     */       } 
/*     */       
/* 730 */       return true;
/*     */     } 
/*     */     
/* 733 */     return super.processInteract(player, hand);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void dropFlag() {
/* 740 */     if (!this.world.isRemote && getFlagColor() != 0) {
/* 741 */       int color = getFlagColor();
/* 742 */       if (color == 16) {
/* 743 */         color = 0;
/*     */       }
/* 745 */       EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(Blocks.WOOL, 1, color));
/* 746 */       entityitem.setPickupDelay(10);
/* 747 */       this.world.spawnEntity((Entity)entityitem);
/* 748 */       setFlagColor(0);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void openMouth() {
/* 753 */     this.mouthCounter = 1;
/*     */   }
/*     */   
/*     */   private void flapWings() {
/* 757 */     this.wingCounter = 1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 762 */     openMouth();
/* 763 */     return MoCSoundEvents.ENTITY_OSTRICH_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getAmbientSound() {
/* 768 */     openMouth();
/* 769 */     if (getType() == 1) {
/* 770 */       return MoCSoundEvents.ENTITY_OSTRICH_AMBIENT_BABY;
/*     */     }
/*     */     
/* 773 */     return MoCSoundEvents.ENTITY_OSTRICH_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getDeathSound() {
/* 778 */     openMouth();
/* 779 */     return MoCSoundEvents.ENTITY_OSTRICH_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 784 */     boolean flag = (this.rand.nextInt(100) < MoCreatures.proxy.rareItemDropChance);
/* 785 */     if (flag && getType() == 8)
/*     */     {
/* 787 */       return (Item)MoCItems.unicornhorn;
/*     */     }
/* 789 */     if (getType() == 5 && flag) {
/* 790 */       return (Item)MoCItems.heartfire;
/*     */     }
/* 792 */     if (getType() == 6 && flag)
/*     */     {
/* 794 */       return (Item)MoCItems.heartdarkness;
/*     */     }
/* 796 */     if (getType() == 7) {
/* 797 */       if (flag) {
/* 798 */         return (Item)MoCItems.heartundead;
/*     */       }
/* 800 */       return Items.ROTTEN_FLESH;
/*     */     } 
/* 802 */     return (Item)MoCItems.ostrichraw;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
/* 807 */     super.readEntityFromNBT(nbttagcompound);
/* 808 */     setRideable(nbttagcompound.getBoolean("Saddle"));
/* 809 */     setEggWatching(nbttagcompound.getBoolean("EggWatch"));
/* 810 */     setHiding(nbttagcompound.getBoolean("Hiding"));
/* 811 */     setHelmet(nbttagcompound.getInteger("Helmet"));
/* 812 */     setFlagColor(nbttagcompound.getInteger("FlagColor"));
/* 813 */     setIsChested(nbttagcompound.getBoolean("Bagged"));
/* 814 */     if (getIsChested()) {
/* 815 */       NBTTagList nbttaglist = nbttagcompound.getTagList("Items", 10);
/* 816 */       this.localchest = new MoCAnimalChest("OstrichChest", 18);
/* 817 */       for (int i = 0; i < nbttaglist.tagCount(); i++) {
/* 818 */         NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
/* 819 */         int j = nbttagcompound1.getByte("Slot") & 0xFF;
/* 820 */         if (j >= 0 && j < this.localchest.getSizeInventory()) {
/* 821 */           this.localchest.setInventorySlotContents(j, new ItemStack(nbttagcompound1));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
/* 829 */     super.writeEntityToNBT(nbttagcompound);
/* 830 */     nbttagcompound.setBoolean("Saddle", getIsRideable());
/* 831 */     nbttagcompound.setBoolean("EggWatch", getEggWatching());
/* 832 */     nbttagcompound.setBoolean("Hiding", getHiding());
/* 833 */     nbttagcompound.setInteger("Helmet", getHelmet());
/* 834 */     nbttagcompound.setInteger("FlagColor", getFlagColor());
/* 835 */     nbttagcompound.setBoolean("Bagged", getIsChested());
/*     */     
/* 837 */     if (getIsChested() && this.localchest != null) {
/* 838 */       NBTTagList nbttaglist = new NBTTagList();
/* 839 */       for (int i = 0; i < this.localchest.getSizeInventory(); i++) {
/* 840 */         this.localstack = this.localchest.getStackInSlot(i);
/* 841 */         if (this.localstack != null && !this.localstack.isEmpty()) {
/* 842 */           NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 843 */           nbttagcompound1.setByte("Slot", (byte)i);
/* 844 */           this.localstack.writeToNBT(nbttagcompound1);
/* 845 */           nbttaglist.appendTag((NBTBase)nbttagcompound1);
/*     */         } 
/*     */       } 
/* 848 */       nbttagcompound.setTag("Items", (NBTBase)nbttaglist);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCanSpawnHere() {
/* 855 */     return (getCanSpawnHereCreature() && getCanSpawnHereLiving());
/*     */   }
/*     */ 
/*     */   
/*     */   public int nameYOffset() {
/* 860 */     if (getType() > 1) {
/* 861 */       return -105;
/*     */     }
/* 863 */     return -5 - getEdad();
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMyHealFood(ItemStack par1ItemStack) {
/* 879 */     return MoCTools.isItemEdible(par1ItemStack.getItem());
/*     */   }
/*     */ 
/*     */   
/*     */   public void dropMyStuff() {
/* 884 */     if (!this.world.isRemote) {
/* 885 */       dropArmor();
/* 886 */       MoCTools.dropSaddle((MoCEntityAnimal)this, this.world);
/*     */       
/* 888 */       if (getIsChested()) {
/* 889 */         MoCTools.dropInventory((Entity)this, this.localchest);
/* 890 */         MoCTools.dropCustomItem((Entity)this, this.world, new ItemStack((Block)Blocks.CHEST, 1));
/* 891 */         setIsChested(false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dropArmor() {
/* 902 */     if (!this.world.isRemote) {
/* 903 */       ItemStack itemStack = getItemStackFromSlot(EntityEquipmentSlot.HEAD);
/* 904 */       if (!itemStack.isEmpty() && itemStack.getItem() instanceof ItemArmor) {
/* 905 */         EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, itemStack.copy());
/* 906 */         if (entityitem != null) {
/* 907 */           entityitem.setPickupDelay(10);
/* 908 */           this.world.spawnEntity((Entity)entityitem);
/*     */         } 
/*     */       } 
/* 911 */       setHelmet(0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFlyer() {
/* 917 */     return (isBeingRidden() && (getType() == 5 || getType() == 6));
/*     */   }
/*     */ 
/*     */   
/*     */   public void fall(float f, float f1) {
/* 922 */     if (isFlyer()) {
/*     */       return;
/*     */     }
/* 925 */     super.fall(f, f1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected double myFallSpeed() {
/* 930 */     return 0.89D;
/*     */   }
/*     */ 
/*     */   
/*     */   protected double flyerThrust() {
/* 935 */     return 0.8D;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float flyerFriction() {
/* 940 */     return 0.96F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean selfPropelledFlyer() {
/* 945 */     return (getType() == 6);
/*     */   }
/*     */ 
/*     */   
/*     */   public void makeEntityJump() {
/* 950 */     if (this.jumpCounter > 5) {
/* 951 */       this.jumpCounter = 1;
/*     */     }
/* 953 */     if (this.jumpCounter == 0) {
/* 954 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_WINGFLAP);
/* 955 */       this.jumpPending = true;
/* 956 */       this.jumpCounter = 1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumCreatureAttribute getCreatureAttribute() {
/* 963 */     if (getType() == 7) {
/* 964 */       return EnumCreatureAttribute.UNDEAD;
/*     */     }
/* 966 */     return super.getCreatureAttribute();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxSpawnedInChunk() {
/* 971 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxEdad() {
/* 982 */     return 20;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityOstrich.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */