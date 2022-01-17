/*     */ package drzhark.mocreatures.entity.passive;
/*     */ 
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
/*     */ import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFollowAdult;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIPanicMoC;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.init.MobEffects;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MoCEntityGoat
/*     */   extends MoCEntityTameableAnimal
/*     */ {
/*     */   private boolean hungry;
/*     */   private boolean swingLeg;
/*     */   private boolean swingEar;
/*     */   private boolean swingTail;
/*     */   private boolean bleat;
/*     */   private boolean eating;
/*  47 */   private static final DataParameter<Boolean> IS_CHARGING = EntityDataManager.createKey(MoCEntityGoat.class, DataSerializers.BOOLEAN); private int bleatcount; private int attacking; public int movecount; private int chargecount; private int tailcount; private int earcount; private int eatcount;
/*  48 */   private static final DataParameter<Boolean> IS_UPSET = EntityDataManager.createKey(MoCEntityGoat.class, DataSerializers.BOOLEAN);
/*     */   
/*     */   public MoCEntityGoat(World world) {
/*  51 */     super(world);
/*  52 */     setSize(0.8F, 1.0F);
/*  53 */     setEdad(70);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initEntityAI() {
/*  58 */     this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  59 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIPanicMoC((EntityCreature)this, 1.0D));
/*  60 */     this.tasks.addTask(4, (EntityAIBase)new EntityAIFollowAdult((EntityLiving)this, 1.0D));
/*  61 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
/*  62 */     this.tasks.addTask(6, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D));
/*  63 */     this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  68 */     super.applyEntityAttributes();
/*  69 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12.0D);
/*  70 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
/*  71 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
/*  72 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  77 */     super.entityInit();
/*  78 */     this.dataManager.register(IS_CHARGING, Boolean.valueOf(false));
/*  79 */     this.dataManager.register(IS_UPSET, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public boolean getUpset() {
/*  83 */     return ((Boolean)this.dataManager.get(IS_UPSET)).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean getCharging() {
/*  87 */     return ((Boolean)this.dataManager.get(IS_CHARGING)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setUpset(boolean flag) {
/*  91 */     this.dataManager.set(IS_UPSET, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public void setCharging(boolean flag) {
/*  95 */     this.dataManager.set(IS_CHARGING, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void selectType() {
/* 104 */     if (getType() == 0) {
/* 105 */       int i = this.rand.nextInt(100);
/* 106 */       if (i <= 15) {
/* 107 */         setType(1);
/* 108 */         setEdad(50);
/* 109 */       } else if (i <= 30) {
/* 110 */         setType(2);
/* 111 */         setEdad(70);
/* 112 */       } else if (i <= 45) {
/* 113 */         setType(3);
/* 114 */         setEdad(70);
/* 115 */       } else if (i <= 60) {
/* 116 */         setType(4);
/* 117 */         setEdad(70);
/* 118 */       } else if (i <= 75) {
/* 119 */         setType(5);
/* 120 */         setEdad(90);
/* 121 */       } else if (i <= 90) {
/* 122 */         setType(6);
/* 123 */         setEdad(90);
/*     */       } else {
/* 125 */         setType(7);
/* 126 */         setEdad(90);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/* 134 */     switch (getType()) {
/*     */       case 1:
/* 136 */         return MoCreatures.proxy.getTexture("goat1.png");
/*     */       case 2:
/* 138 */         return MoCreatures.proxy.getTexture("goat2.png");
/*     */       case 3:
/* 140 */         return MoCreatures.proxy.getTexture("goat3.png");
/*     */       case 4:
/* 142 */         return MoCreatures.proxy.getTexture("goat4.png");
/*     */       case 5:
/* 144 */         return MoCreatures.proxy.getTexture("goat5.png");
/*     */       case 6:
/* 146 */         return MoCreatures.proxy.getTexture("goat6.png");
/*     */       case 7:
/* 148 */         return MoCreatures.proxy.getTexture("goat1.png");
/*     */     } 
/*     */     
/* 151 */     return MoCreatures.proxy.getTexture("goat1.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public void calm() {
/* 156 */     setAttackTarget(null);
/* 157 */     setUpset(false);
/* 158 */     setCharging(false);
/* 159 */     this.attacking = 0;
/* 160 */     this.chargecount = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void jump() {
/* 165 */     if (getType() == 1) {
/* 166 */       this.motionY = 0.41D;
/* 167 */     } else if (getType() < 5) {
/* 168 */       this.motionY = 0.45D;
/*     */     } else {
/* 170 */       this.motionY = 0.5D;
/*     */     } 
/*     */     
/* 173 */     if (isPotionActive(MobEffects.JUMP_BOOST)) {
/* 174 */       this.motionY += ((getActivePotionEffect(MobEffects.JUMP_BOOST).getAmplifier() + 1) * 0.1F);
/*     */     }
/* 176 */     if (isSprinting()) {
/* 177 */       float f = this.rotationYaw * 0.01745329F;
/* 178 */       this.motionX -= (MathHelper.sin(f) * 0.2F);
/* 179 */       this.motionZ += (MathHelper.cos(f) * 0.2F);
/*     */     } 
/* 181 */     this.isAirBorne = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 186 */     super.onLivingUpdate();
/* 187 */     if (this.world.isRemote) {
/* 188 */       if (this.rand.nextInt(100) == 0) {
/* 189 */         setSwingEar(true);
/*     */       }
/*     */       
/* 192 */       if (this.rand.nextInt(80) == 0) {
/* 193 */         setSwingTail(true);
/*     */       }
/*     */       
/* 196 */       if (this.rand.nextInt(50) == 0) {
/* 197 */         setEating(true);
/*     */       }
/*     */     } 
/* 200 */     if (getBleating()) {
/* 201 */       this.bleatcount++;
/* 202 */       if (this.bleatcount > 15) {
/* 203 */         this.bleatcount = 0;
/* 204 */         setBleating(false);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 209 */     if (this.hungry && this.rand.nextInt(20) == 0) {
/* 210 */       this.hungry = false;
/*     */     }
/*     */     
/* 213 */     if (!this.world.isRemote && (getEdad() < 90 || (getType() > 4 && getEdad() < 100)) && this.rand.nextInt(500) == 0) {
/* 214 */       setEdad(getEdad() + 1);
/* 215 */       if (getType() == 1 && getEdad() > 70) {
/* 216 */         int i = this.rand.nextInt(6) + 2;
/* 217 */         setType(i);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 222 */     if (getUpset()) {
/* 223 */       this.attacking += this.rand.nextInt(4) + 2;
/* 224 */       if (this.attacking > 75) {
/* 225 */         this.attacking = 75;
/*     */       }
/*     */       
/* 228 */       if (this.rand.nextInt(200) == 0 || getAttackTarget() == null) {
/* 229 */         calm();
/*     */       }
/*     */       
/* 232 */       if (!getCharging() && this.rand.nextInt(35) == 0) {
/* 233 */         swingLeg();
/*     */       }
/*     */       
/* 236 */       if (!getCharging()) {
/* 237 */         getNavigator().clearPath();
/*     */       }
/*     */       
/* 240 */       if (getAttackTarget() != null) {
/*     */         
/* 242 */         faceEntity((Entity)getAttackTarget(), 10.0F, 10.0F);
/* 243 */         if (this.rand.nextInt(80) == 0) {
/* 244 */           setCharging(true);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 249 */     if (getCharging()) {
/* 250 */       this.chargecount++;
/* 251 */       if (this.chargecount > 120) {
/* 252 */         this.chargecount = 0;
/*     */       }
/* 254 */       if (getAttackTarget() == null) {
/* 255 */         calm();
/*     */       }
/*     */     } 
/*     */     
/* 259 */     if (!getUpset() && !getCharging()) {
/* 260 */       EntityPlayer entityplayer1 = this.world.getClosestPlayerToEntity((Entity)this, 24.0D);
/* 261 */       if (entityplayer1 != null) {
/*     */ 
/*     */         
/* 264 */         EntityItem entityitem = getClosestEntityItem((Entity)this, 10.0D);
/* 265 */         if (entityitem != null) {
/* 266 */           float f = entityitem.getDistance((Entity)this);
/* 267 */           if (f > 2.0F) {
/* 268 */             int i = MathHelper.floor(entityitem.posX);
/* 269 */             int j = MathHelper.floor(entityitem.posY);
/* 270 */             int k = MathHelper.floor(entityitem.posZ);
/* 271 */             faceLocation(i, j, k, 30.0F);
/*     */             
/* 273 */             getMyOwnPath((Entity)entityitem, f);
/*     */             return;
/*     */           } 
/* 276 */           if (f < 2.0F && entityitem != null && this.deathTime == 0 && this.rand.nextInt(50) == 0) {
/* 277 */             MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GOAT_EATING);
/* 278 */             setEating(true);
/*     */             
/* 280 */             entityitem.setDead();
/*     */             
/*     */             return;
/*     */           } 
/*     */         } 
/*     */         
/* 286 */         if (getType() > 4 && this.rand.nextInt(200) == 0) {
/* 287 */           MoCEntityGoat entitytarget = (MoCEntityGoat)getClosestEntityLiving((Entity)this, 14.0D);
/* 288 */           if (entitytarget != null) {
/* 289 */             setUpset(true);
/* 290 */             setAttackTarget((EntityLivingBase)entitytarget);
/* 291 */             entitytarget.setUpset(true);
/* 292 */             entitytarget.setAttackTarget((EntityLivingBase)this);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMyFavoriteFood(ItemStack stack) {
/* 302 */     return (!stack.isEmpty() && MoCTools.isItemEdible(stack.getItem()));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTalkInterval() {
/* 307 */     if (this.hungry) {
/* 308 */       return 80;
/*     */     }
/*     */     
/* 311 */     return 200;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean entitiesToIgnore(Entity entity) {
/* 316 */     return (!(entity instanceof MoCEntityGoat) || ((MoCEntityGoat)entity).getType() < 5);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMovementCeased() {
/* 321 */     return (getUpset() && !getCharging());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityAsMob(Entity entityIn) {
/* 326 */     this.attacking = 30;
/* 327 */     if (entityIn instanceof MoCEntityGoat) {
/* 328 */       MoCTools.bigsmack((Entity)this, entityIn, 0.4F);
/* 329 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GOAT_SMACK);
/* 330 */       if (this.rand.nextInt(3) == 0) {
/* 331 */         calm();
/* 332 */         ((MoCEntityGoat)entityIn).calm();
/*     */       } 
/* 334 */       return false;
/*     */     } 
/* 336 */     MoCTools.bigsmack((Entity)this, entityIn, 0.8F);
/* 337 */     if (this.rand.nextInt(3) == 0) {
/* 338 */       calm();
/*     */     }
/* 340 */     return super.attackEntityAsMob(entityIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNotScared() {
/* 345 */     return (getType() > 4);
/*     */   }
/*     */   
/*     */   private void swingLeg() {
/* 349 */     if (!getSwingLeg()) {
/* 350 */       setSwingLeg(true);
/* 351 */       this.movecount = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean getSwingLeg() {
/* 356 */     return this.swingLeg;
/*     */   }
/*     */   
/*     */   public void setSwingLeg(boolean flag) {
/* 360 */     this.swingLeg = flag;
/*     */   }
/*     */   
/*     */   public boolean getSwingEar() {
/* 364 */     return this.swingEar;
/*     */   }
/*     */   
/*     */   public void setSwingEar(boolean flag) {
/* 368 */     this.swingEar = flag;
/*     */   }
/*     */   
/*     */   public boolean getSwingTail() {
/* 372 */     return this.swingTail;
/*     */   }
/*     */   
/*     */   public void setSwingTail(boolean flag) {
/* 376 */     this.swingTail = flag;
/*     */   }
/*     */   
/*     */   public boolean getEating() {
/* 380 */     return this.eating;
/*     */   }
/*     */   
/*     */   public void setEating(boolean flag) {
/* 384 */     this.eating = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/* 389 */     if (super.attackEntityFrom(damagesource, i)) {
/* 390 */       Entity entity = damagesource.getTrueSource();
/*     */       
/* 392 */       if (entity != this && entity instanceof EntityLivingBase && shouldAttackPlayers() && getType() > 4) {
/* 393 */         setAttackTarget((EntityLivingBase)entity);
/* 394 */         setUpset(true);
/*     */       } 
/* 396 */       return true;
/*     */     } 
/* 398 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 405 */     if (getSwingLeg()) {
/* 406 */       this.movecount += 5;
/* 407 */       if (this.movecount == 30) {
/* 408 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GOAT_DIGG);
/*     */       }
/*     */       
/* 411 */       if (this.movecount > 100) {
/* 412 */         setSwingLeg(false);
/* 413 */         this.movecount = 0;
/*     */       } 
/*     */     } 
/*     */     
/* 417 */     if (getSwingEar()) {
/* 418 */       this.earcount += 5;
/* 419 */       if (this.earcount > 40) {
/* 420 */         setSwingEar(false);
/* 421 */         this.earcount = 0;
/*     */       } 
/*     */     } 
/*     */     
/* 425 */     if (getSwingTail()) {
/* 426 */       this.tailcount += 15;
/* 427 */       if (this.tailcount > 135) {
/* 428 */         setSwingTail(false);
/* 429 */         this.tailcount = 0;
/*     */       } 
/*     */     } 
/*     */     
/* 433 */     if (getEating()) {
/* 434 */       this.eatcount++;
/* 435 */       if (this.eatcount == 2) {
/* 436 */         EntityPlayer entityplayer1 = this.world.getClosestPlayerToEntity((Entity)this, 3.0D);
/* 437 */         if (entityplayer1 != null) {
/* 438 */           MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GOAT_EATING);
/*     */         }
/*     */       } 
/* 441 */       if (this.eatcount > 25) {
/* 442 */         setEating(false);
/* 443 */         this.eatcount = 0;
/*     */       } 
/*     */     } 
/*     */     
/* 447 */     super.onUpdate();
/*     */   }
/*     */   
/*     */   public int legMovement() {
/* 451 */     if (!getSwingLeg()) {
/* 452 */       return 0;
/*     */     }
/*     */     
/* 455 */     if (this.movecount < 21) {
/* 456 */       return this.movecount * -1;
/*     */     }
/* 458 */     if (this.movecount < 70) {
/* 459 */       return this.movecount - 40;
/*     */     }
/* 461 */     return -this.movecount + 100;
/*     */   }
/*     */ 
/*     */   
/*     */   public int earMovement() {
/* 466 */     if (!getSwingEar()) {
/* 467 */       return 0;
/*     */     }
/* 469 */     if (this.earcount < 11) {
/* 470 */       return this.earcount + 30;
/*     */     }
/* 472 */     if (this.earcount < 31) {
/* 473 */       return -this.earcount + 50;
/*     */     }
/* 475 */     return this.earcount - 10;
/*     */   }
/*     */ 
/*     */   
/*     */   public int tailMovement() {
/* 480 */     if (!getSwingTail()) {
/* 481 */       return 90;
/*     */     }
/*     */     
/* 484 */     return this.tailcount - 45;
/*     */   }
/*     */   
/*     */   public int mouthMovement() {
/* 488 */     if (!getEating()) {
/* 489 */       return 0;
/*     */     }
/* 491 */     if (this.eatcount < 6) {
/* 492 */       return this.eatcount;
/*     */     }
/* 494 */     if (this.eatcount < 16) {
/* 495 */       return -this.eatcount + 10;
/*     */     }
/* 497 */     return this.eatcount - 20;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fall(float f, float f1) {}
/*     */ 
/*     */   
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/* 506 */     Boolean tameResult = processTameInteract(player, hand);
/* 507 */     if (tameResult != null) {
/* 508 */       return tameResult.booleanValue();
/*     */     }
/*     */     
/* 511 */     ItemStack stack = player.getHeldItem(hand);
/* 512 */     if (!stack.isEmpty() && stack.getItem() == Items.BUCKET) {
/* 513 */       if (getType() > 4) {
/* 514 */         setUpset(true);
/* 515 */         setAttackTarget((EntityLivingBase)player);
/* 516 */         return false;
/*     */       } 
/* 518 */       if (getType() == 1) {
/* 519 */         return false;
/*     */       }
/*     */       
/* 522 */       stack.shrink(1);
/* 523 */       if (stack.isEmpty()) {
/* 524 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 526 */       player.addItemStackToInventory(new ItemStack(Items.MILK_BUCKET));
/* 527 */       return true;
/*     */     } 
/*     */     
/* 530 */     if (getIsTamed() && !stack.isEmpty() && MoCTools.isItemEdible(stack.getItem())) {
/* 531 */       stack.shrink(1);
/* 532 */       if (stack.isEmpty()) {
/* 533 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 535 */       setHealth(getMaxHealth());
/* 536 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GOAT_EATING);
/* 537 */       return true;
/*     */     } 
/*     */     
/* 540 */     if (!getIsTamed() && !stack.isEmpty() && MoCTools.isItemEdible(stack.getItem())) {
/* 541 */       if (!this.world.isRemote) {
/* 542 */         MoCTools.tameWithName(player, (IMoCTameable)this);
/*     */       }
/*     */       
/* 545 */       return true;
/*     */     } 
/*     */     
/* 548 */     return super.processInteract(player, hand);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getBleating() {
/* 553 */     return (this.bleat && getAttacking() == 0);
/*     */   }
/*     */   
/*     */   public void setBleating(boolean flag) {
/* 557 */     this.bleat = flag;
/*     */   }
/*     */   
/*     */   public int getAttacking() {
/* 561 */     return this.attacking;
/*     */   }
/*     */   
/*     */   public void setAttacking(int flag) {
/* 565 */     this.attacking = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 570 */     return MoCSoundEvents.ENTITY_GOAT_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getAmbientSound() {
/* 575 */     setBleating(true);
/* 576 */     if (getType() == 1) {
/* 577 */       return MoCSoundEvents.ENTITY_GOAT_AMBIENT_BABY;
/*     */     }
/* 579 */     if (getType() > 2 && getType() < 5) {
/* 580 */       return MoCSoundEvents.ENTITY_GOAT_AMBIENT_FEMALE;
/*     */     }
/*     */     
/* 583 */     return MoCSoundEvents.ENTITY_GOAT_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getDeathSound() {
/* 588 */     return MoCSoundEvents.ENTITY_GOAT_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 593 */     return Items.LEATHER;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxEdad() {
/* 598 */     return 50;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAIMoveSpeed() {
/* 603 */     return 0.15F;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityGoat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */