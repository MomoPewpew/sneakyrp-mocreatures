/*     */ package drzhark.mocreatures.entity.aquatic;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
/*     */ import drzhark.mocreatures.entity.MoCEntityTameableAquatic;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIPanicMoC;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import drzhark.mocreatures.network.message.MoCMessageHeart;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*     */ 
/*     */ public class MoCEntityDolphin extends MoCEntityTameableAquatic {
/*     */   public int gestationtime;
/*  35 */   private static final DataParameter<Boolean> IS_HUNGRY = EntityDataManager.createKey(MoCEntityDolphin.class, DataSerializers.BOOLEAN);
/*  36 */   private static final DataParameter<Boolean> HAS_EATEN = EntityDataManager.createKey(MoCEntityDolphin.class, DataSerializers.BOOLEAN);
/*     */   
/*     */   public MoCEntityDolphin(World world) {
/*  39 */     super(world);
/*  40 */     setSize(1.5F, 0.8F);
/*  41 */     setEdad(60 + this.rand.nextInt(100));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initEntityAI() {
/*  46 */     this.tasks.addTask(1, (EntityAIBase)new EntityAIPanicMoC((EntityCreature)this, 1.3D));
/*  47 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D, 30));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  52 */     super.applyEntityAttributes();
/*  53 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
/*  54 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
/*  55 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
/*  56 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectType() {
/*  61 */     if (getType() == 0) {
/*  62 */       int i = this.rand.nextInt(100);
/*  63 */       if (i <= 35) {
/*  64 */         setType(1);
/*  65 */       } else if (i <= 60) {
/*  66 */         setType(2);
/*  67 */       } else if (i <= 85) {
/*  68 */         setType(3);
/*  69 */       } else if (i <= 96) {
/*  70 */         setType(4);
/*  71 */       } else if (i <= 98) {
/*  72 */         setType(5);
/*     */       } else {
/*  74 */         setType(6);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  82 */     switch (getType()) {
/*     */       case 1:
/*  84 */         return MoCreatures.proxy.getTexture("dolphin.png");
/*     */       case 2:
/*  86 */         return MoCreatures.proxy.getTexture("dolphin2.png");
/*     */       case 3:
/*  88 */         return MoCreatures.proxy.getTexture("dolphin3.png");
/*     */       case 4:
/*  90 */         return MoCreatures.proxy.getTexture("dolphin4.png");
/*     */       case 5:
/*  92 */         return MoCreatures.proxy.getTexture("dolphin5.png");
/*     */       case 6:
/*  94 */         return MoCreatures.proxy.getTexture("dolphin6.png");
/*     */     } 
/*  96 */     return MoCreatures.proxy.getTexture("dolphin.png");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxTemper() {
/* 103 */     switch (getType()) {
/*     */       case 1:
/* 105 */         return 50;
/*     */       case 2:
/* 107 */         return 100;
/*     */       case 3:
/* 109 */         return 150;
/*     */       case 4:
/* 111 */         return 200;
/*     */       case 5:
/* 113 */         return 250;
/*     */       case 6:
/* 115 */         return 300;
/*     */     } 
/* 117 */     return 100;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInitialTemper() {
/* 122 */     switch (getType()) {
/*     */       case 1:
/* 124 */         return 50;
/*     */       case 2:
/* 126 */         return 100;
/*     */       case 3:
/* 128 */         return 150;
/*     */       case 4:
/* 130 */         return 200;
/*     */       case 5:
/* 132 */         return 250;
/*     */       case 6:
/* 134 */         return 300;
/*     */     } 
/* 136 */     return 50;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getCustomSpeed() {
/* 142 */     switch (getType()) {
/*     */       case 1:
/* 144 */         return 1.5D;
/*     */       case 2:
/* 146 */         return 2.0D;
/*     */       case 3:
/* 148 */         return 2.5D;
/*     */       case 4:
/* 150 */         return 3.0D;
/*     */       case 5:
/* 152 */         return 3.5D;
/*     */       case 6:
/* 154 */         return 4.0D;
/*     */     } 
/* 156 */     return 1.5D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/* 162 */     super.entityInit();
/* 163 */     this.dataManager.register(IS_HUNGRY, Boolean.valueOf(false));
/* 164 */     this.dataManager.register(HAS_EATEN, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public boolean getIsHungry() {
/* 168 */     return ((Boolean)this.dataManager.get(IS_HUNGRY)).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean getHasEaten() {
/* 172 */     return ((Boolean)this.dataManager.get(HAS_EATEN)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setIsHungry(boolean flag) {
/* 176 */     this.dataManager.set(IS_HUNGRY, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public void setHasEaten(boolean flag) {
/* 180 */     this.dataManager.set(HAS_EATEN, Boolean.valueOf(flag));
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
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/* 195 */     if (super.attackEntityFrom(damagesource, i) && this.world.getDifficulty().getId() > 0) {
/* 196 */       Entity entity = damagesource.getTrueSource();
/* 197 */       if (entity instanceof EntityLivingBase) {
/* 198 */         EntityLivingBase entityliving = (EntityLivingBase)entity;
/* 199 */         if (isRidingOrBeingRiddenBy(entity)) {
/* 200 */           return true;
/*     */         }
/* 202 */         if (entity != this && getEdad() >= 100) {
/* 203 */           setAttackTarget(entityliving);
/*     */         }
/* 205 */         return true;
/*     */       } 
/* 207 */       return false;
/*     */     } 
/* 209 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeCollidedWith() {
/* 215 */     return !isBeingRidden();
/*     */   }
/*     */   
/*     */   private int Genetics(MoCEntityDolphin entitydolphin, MoCEntityDolphin entitydolphin1) {
/* 219 */     if (entitydolphin.getType() == entitydolphin1.getType()) {
/* 220 */       return entitydolphin.getType();
/*     */     }
/* 222 */     int i = entitydolphin.getType() + entitydolphin1.getType();
/* 223 */     boolean flag = (this.rand.nextInt(3) == 0);
/* 224 */     boolean flag1 = (this.rand.nextInt(10) == 0);
/* 225 */     if (i < 5 && flag) {
/* 226 */       return i;
/*     */     }
/* 228 */     if ((i == 5 || i == 6) && flag1) {
/* 229 */       return i;
/*     */     }
/* 231 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEvent getDeathSound() {
/* 237 */     return MoCSoundEvents.ENTITY_DOLPHIN_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 242 */     return MoCSoundEvents.ENTITY_DOLPHIN_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getAmbientSound() {
/* 247 */     return MoCSoundEvents.ENTITY_DOLPHIN_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getUpsetSound() {
/* 252 */     return MoCSoundEvents.ENTITY_DOLPHIN_UPSET;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 257 */     return Items.FISH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getSoundVolume() {
/* 262 */     return 0.4F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/* 267 */     Boolean tameResult = processTameInteract(player, hand);
/* 268 */     if (tameResult != null) {
/* 269 */       return tameResult.booleanValue();
/*     */     }
/*     */     
/* 272 */     ItemStack stack = player.getHeldItem(hand);
/* 273 */     if (!stack.isEmpty() && stack.getItem() == Items.FISH) {
/* 274 */       stack.shrink(1);
/* 275 */       if (stack.isEmpty()) {
/* 276 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 278 */       if (!this.world.isRemote) {
/* 279 */         setTemper(getTemper() + 25);
/* 280 */         if (getTemper() > getMaxTemper()) {
/* 281 */           setTemper(getMaxTemper() - 1);
/*     */         }
/*     */         
/* 284 */         if (getHealth() + 15.0F > getMaxHealth()) {
/* 285 */           setHealth(getMaxHealth());
/*     */         }
/*     */         
/* 288 */         if (!getIsAdult()) {
/* 289 */           setEdad(getEdad() + 1);
/*     */         }
/*     */       } 
/*     */       
/* 293 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_EATING);
/*     */       
/* 295 */       return true;
/*     */     } 
/* 297 */     if (!stack.isEmpty() && stack.getItem() == Items.COOKED_FISH && getIsTamed() && getIsAdult()) {
/* 298 */       stack.shrink(1);
/* 299 */       if (stack.isEmpty()) {
/* 300 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 302 */       if (getHealth() + 25.0F > getMaxHealth()) {
/* 303 */         setHealth(getMaxHealth());
/*     */       }
/* 305 */       setHasEaten(true);
/* 306 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_EATING);
/* 307 */       return true;
/*     */     } 
/* 309 */     if (!isBeingRidden()) {
/* 310 */       if (!this.world.isRemote && player.startRiding((Entity)this)) {
/* 311 */         player.rotationYaw = this.rotationYaw;
/* 312 */         player.rotationPitch = this.rotationPitch;
/* 313 */         player.posY = this.posY;
/*     */       } 
/*     */       
/* 316 */       return true;
/*     */     } 
/*     */     
/* 319 */     return super.processInteract(player, hand);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 324 */     super.onLivingUpdate();
/*     */     
/* 326 */     if (!this.world.isRemote) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 333 */       if (!getIsAdult() && this.rand.nextInt(50) == 0) {
/*     */         
/* 335 */         setEdad(getEdad() + 1);
/* 336 */         if (getEdad() >= 150)
/*     */         {
/* 338 */           setAdult(true);
/*     */         }
/*     */       } 
/*     */       
/* 342 */       if (!isBeingRidden() && this.deathTime == 0 && (!getIsTamed() || getIsHungry())) {
/* 343 */         EntityItem entityitem = getClosestFish((Entity)this, 12.0D);
/* 344 */         if (entityitem != null) {
/* 345 */           MoveToNextEntity((Entity)entityitem);
/* 346 */           EntityItem entityitem1 = getClosestFish((Entity)this, 2.0D);
/* 347 */           if (this.rand.nextInt(20) == 0 && entityitem1 != null && this.deathTime == 0) {
/*     */             
/* 349 */             entityitem1.setDead();
/* 350 */             setTemper(getTemper() + 25);
/* 351 */             if (getTemper() > getMaxTemper()) {
/* 352 */               setTemper(getMaxTemper() - 1);
/*     */             }
/* 354 */             setHealth(getMaxHealth());
/*     */           } 
/*     */         } 
/*     */       } 
/* 358 */       if (!ReadyforParenting(this)) {
/*     */         return;
/*     */       }
/* 361 */       int i = 0;
/* 362 */       List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(8.0D, 2.0D, 8.0D));
/* 363 */       for (int j = 0; j < list.size(); j++) {
/* 364 */         Entity entity = list.get(j);
/* 365 */         if (entity instanceof MoCEntityDolphin) {
/* 366 */           i++;
/*     */         }
/*     */       } 
/*     */       
/* 370 */       if (i > 1) {
/*     */         return;
/*     */       }
/* 373 */       List<Entity> list1 = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(4.0D, 2.0D, 4.0D));
/* 374 */       for (int k = 0; k < list1.size(); k++) {
/* 375 */         Entity entity1 = list1.get(k);
/* 376 */         if (entity1 instanceof MoCEntityDolphin && entity1 != this) {
/*     */ 
/*     */           
/* 379 */           MoCEntityDolphin entitydolphin = (MoCEntityDolphin)entity1;
/* 380 */           if (ReadyforParenting(this) && ReadyforParenting(entitydolphin)) {
/*     */ 
/*     */             
/* 383 */             if (this.rand.nextInt(100) == 0) {
/* 384 */               this.gestationtime++;
/*     */             }
/* 386 */             if (this.gestationtime % 3 == 0) {
/* 387 */               MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageHeart(getEntityId()), new NetworkRegistry.TargetPoint(this.world.provider
/* 388 */                     .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */             }
/* 390 */             if (this.gestationtime > 50) {
/*     */ 
/*     */               
/* 393 */               MoCEntityDolphin babydolphin = new MoCEntityDolphin(this.world);
/* 394 */               babydolphin.setPosition(this.posX, this.posY, this.posZ);
/* 395 */               if (this.world.spawnEntity((Entity)babydolphin)) {
/* 396 */                 MoCTools.playCustomSound((Entity)this, SoundEvents.ENTITY_CHICKEN_EGG);
/* 397 */                 setHasEaten(false);
/* 398 */                 entitydolphin.setHasEaten(false);
/* 399 */                 this.gestationtime = 0;
/* 400 */                 entitydolphin.gestationtime = 0;
/* 401 */                 int l = Genetics(this, entitydolphin);
/* 402 */                 babydolphin.setEdad(35);
/* 403 */                 babydolphin.setAdult(false);
/* 404 */                 babydolphin.setOwnerId(getOwnerId());
/* 405 */                 babydolphin.setTamed(true);
/* 406 */                 EntityPlayer entityplayer = this.world.getPlayerEntityByUUID(getOwnerId());
/* 407 */                 if (entityplayer != null) {
/* 408 */                   MoCTools.tameWithName(entityplayer, (IMoCTameable)babydolphin);
/*     */                 }
/* 410 */                 babydolphin.setTypeInt(l);
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }  } public boolean ReadyforParenting(MoCEntityDolphin entitydolphin) {
/* 418 */     EntityLivingBase passenger = (EntityLivingBase)getControllingPassenger();
/* 419 */     return (entitydolphin.getRidingEntity() == null && passenger == null && entitydolphin.getIsTamed() && entitydolphin
/* 420 */       .getHasEaten() && entitydolphin.getIsAdult());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDead() {
/* 425 */     if (!this.world.isRemote && getIsTamed() && getHealth() > 0.0F) {
/*     */       return;
/*     */     }
/* 428 */     super.setDead();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxSpawnedInChunk() {
/* 435 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean usesNewAI() {
/* 440 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAIMoveSpeed() {
/* 445 */     return 0.15F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMovementCeased() {
/* 450 */     return !isInWater();
/*     */   }
/*     */ 
/*     */   
/*     */   protected double minDivingDepth() {
/* 455 */     return 0.4D;
/*     */   }
/*     */ 
/*     */   
/*     */   protected double maxDivingDepth() {
/* 460 */     return 4.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxEdad() {
/* 465 */     return 160;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updatePassenger(Entity passenger) {
/* 470 */     double dist = 0.8D;
/* 471 */     double newPosX = this.posX + dist * Math.sin((this.renderYawOffset / 57.29578F));
/* 472 */     double newPosZ = this.posZ - dist * Math.cos((this.renderYawOffset / 57.29578F));
/* 473 */     passenger.setPosition(newPosX, this.posY + getMountedYOffset() + passenger.getYOffset(), newPosZ);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMountedYOffset() {
/* 478 */     return (getEdad() * 0.01F) * this.height * 0.3D;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\aquatic\MoCEntityDolphin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */