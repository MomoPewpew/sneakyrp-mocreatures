/*     */ package drzhark.mocreatures.entity.passive;
/*     */ 
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFleeFromPlayer;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIHunt;
/*     */ import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIPanicMoC;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import drzhark.mocreatures.network.MoCMessageHandler;
/*     */ import drzhark.mocreatures.network.message.MoCMessageAnimation;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.MobEffects;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MoCEntitySnake
/*     */   extends MoCEntityTameableAnimal
/*     */ {
/*     */   private float fTongue;
/*     */   private float fMouth;
/*     */   private boolean isBiting;
/*     */   private float fRattle;
/*     */   private boolean isPissed;
/*     */   private int hissCounter;
/*     */   private int movInt;
/*     */   private boolean isNearPlayer;
/*     */   public float bodyswing;
/*  62 */   public static final String[] snakeNames = new String[] { "Dark", "Spotted", "Orange", "Green", "Coral", "Cobra", "Rattle", "Python" };
/*     */   
/*     */   public MoCEntitySnake(World world) {
/*  65 */     super(world);
/*  66 */     setSize(1.4F, 0.5F);
/*  67 */     this.bodyswing = 2.0F;
/*  68 */     this.movInt = this.rand.nextInt(10);
/*  69 */     setEdad(50 + this.rand.nextInt(50));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initEntityAI() {
/*  74 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIPanicMoC((EntityCreature)this, 0.8D));
/*  75 */     this.tasks.addTask(3, (EntityAIBase)new EntityAIFleeFromPlayer((EntityCreature)this, 0.8D, 4.0D));
/*  76 */     this.tasks.addTask(4, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
/*  77 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 0.8D, 30));
/*  78 */     this.tasks.addTask(9, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  79 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHunt((EntityCreature)this, EntityAnimal.class, true));
/*  80 */     this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  85 */     super.applyEntityAttributes();
/*  86 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
/*  87 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
/*  88 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
/*  89 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectType() {
/*  94 */     checkSpawningBiome();
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
/* 105 */     if (getType() == 0) {
/* 106 */       setType(this.rand.nextInt(8) + 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/* 112 */     switch (getType()) {
/*     */       case 1:
/* 114 */         return MoCreatures.proxy.getTexture("snake1.png");
/*     */       case 2:
/* 116 */         return MoCreatures.proxy.getTexture("snake2.png");
/*     */       case 3:
/* 118 */         return MoCreatures.proxy.getTexture("snake3.png");
/*     */       case 4:
/* 120 */         return MoCreatures.proxy.getTexture("snake4.png");
/*     */       case 5:
/* 122 */         return MoCreatures.proxy.getTexture("snake5.png");
/*     */       case 6:
/* 124 */         return MoCreatures.proxy.getTexture("snake6.png");
/*     */       case 7:
/* 126 */         return MoCreatures.proxy.getTexture("snake7.png");
/*     */       case 8:
/* 128 */         return MoCreatures.proxy.getTexture("snake8.png");
/*     */     } 
/* 130 */     return MoCreatures.proxy.getTexture("snake1.png");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fall(float f, float f1) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOnLadder() {
/* 140 */     return this.collidedHorizontally;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void jump() {
/* 147 */     if (isInWater()) {
/* 148 */       super.jump();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canDespawn() {
/* 154 */     if (MoCreatures.proxy.forceDespawns) {
/* 155 */       return !getIsTamed();
/*     */     }
/* 157 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean pickedUp() {
/* 162 */     return (getRidingEntity() != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/* 167 */     Boolean tameResult = processTameInteract(player, hand);
/* 168 */     if (tameResult != null) {
/* 169 */       return tameResult.booleanValue();
/*     */     }
/*     */     
/* 172 */     if (!getIsTamed()) {
/* 173 */       return false;
/*     */     }
/*     */     
/* 176 */     if (getRidingEntity() == null) {
/* 177 */       if (startRiding((Entity)player)) {
/* 178 */         this.rotationYaw = player.rotationYaw;
/*     */       }
/*     */       
/* 181 */       return true;
/*     */     } 
/*     */     
/* 184 */     return super.processInteract(player, hand);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNotScared() {
/* 189 */     if (getType() > 2 && getEdad() > 50) {
/* 190 */       return true;
/*     */     }
/* 192 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isClimbing() {
/* 201 */     return (isOnLadder() && this.motionY > 0.009999999776482582D);
/*     */   }
/*     */   
/*     */   public boolean isResting() {
/* 205 */     return (!getNearPlayer() && this.onGround && this.motionX < 0.01D && this.motionX > -0.01D && this.motionZ < 0.01D && this.motionZ > -0.01D);
/*     */   }
/*     */   
/*     */   public boolean getNearPlayer() {
/* 209 */     return (this.isNearPlayer || isBiting());
/*     */   }
/*     */   
/*     */   public int getMovInt() {
/* 213 */     return this.movInt;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean swimmerEntity() {
/* 218 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBreatheUnderwater() {
/* 223 */     return true;
/*     */   }
/*     */   
/*     */   public void setNearPlayer(boolean flag) {
/* 227 */     this.isNearPlayer = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getYOffset() {
/* 232 */     if (getRidingEntity() instanceof EntityPlayer) {
/* 233 */       return 0.10000000149011612D;
/*     */     }
/*     */     
/* 236 */     return super.getYOffset();
/*     */   }
/*     */   
/*     */   public float getSizeF() {
/* 240 */     float factor = 1.0F;
/* 241 */     if (getType() == 1 || getType() == 2) {
/*     */       
/* 243 */       factor = 0.8F;
/* 244 */     } else if (getType() == 5) {
/*     */       
/* 246 */       factor = 0.6F;
/*     */     } 
/* 248 */     if (getType() == 6)
/*     */     {
/* 250 */       factor = 1.1F;
/*     */     }
/* 252 */     if (getType() == 7)
/*     */     {
/* 254 */       factor = 0.9F;
/*     */     }
/* 256 */     if (getType() == 8)
/*     */     {
/* 258 */       factor = 1.5F;
/*     */     }
/* 260 */     return getEdad() * 0.01F * factor;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 265 */     super.onUpdate();
/*     */     
/* 267 */     if (this.world.isRemote) {
/* 268 */       if (getfTongue() != 0.0F) {
/* 269 */         setfTongue(getfTongue() + 0.2F);
/* 270 */         if (getfTongue() > 8.0F) {
/* 271 */           setfTongue(0.0F);
/*     */         }
/*     */       } 
/*     */       
/* 275 */       if (getfMouth() != 0.0F && this.hissCounter == 0) {
/*     */         
/* 277 */         setfMouth(getfMouth() + 0.1F);
/* 278 */         if (getfMouth() > 0.5F) {
/* 279 */           setfMouth(0.0F);
/*     */         }
/*     */       } 
/*     */       
/* 283 */       if (getType() == 7 && getfRattle() != 0.0F) {
/*     */         
/* 285 */         setfRattle(getfRattle() + 0.2F);
/* 286 */         if (getfRattle() == 1.0F)
/*     */         {
/* 288 */           MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_SNAKE_RATTLE);
/*     */         }
/* 290 */         if (getfRattle() > 8.0F) {
/* 291 */           setfRattle(0.0F);
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 298 */       if (this.rand.nextInt(50) == 0 && getfTongue() == 0.0F) {
/* 299 */         setfTongue(0.1F);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 305 */       if (this.rand.nextInt(100) == 0 && getfMouth() == 0.0F) {
/* 306 */         setfMouth(0.1F);
/*     */       }
/* 308 */       if (getType() == 7) {
/* 309 */         int chance = 0;
/* 310 */         if (getNearPlayer()) {
/* 311 */           chance = 30;
/*     */         } else {
/* 313 */           chance = 100;
/*     */         } 
/*     */         
/* 316 */         if (this.rand.nextInt(chance) == 0) {
/* 317 */           setfRattle(0.1F);
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 323 */       if (!isResting() && !pickedUp() && this.rand.nextInt(50) == 0) {
/* 324 */         this.movInt = this.rand.nextInt(10);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 330 */       if (isBiting()) {
/* 331 */         this.bodyswing -= 0.5F;
/* 332 */         setfMouth(0.3F);
/*     */         
/* 334 */         if (this.bodyswing < 0.0F) {
/* 335 */           MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_SNAKE_SNAP);
/* 336 */           this.bodyswing = 2.5F;
/* 337 */           setfMouth(0.0F);
/* 338 */           setBiting(false);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 343 */     if (pickedUp()) {
/* 344 */       this.movInt = 0;
/*     */     }
/*     */     
/* 347 */     if (isResting())
/*     */     {
/* 349 */       this.prevRenderYawOffset = this.renderYawOffset = this.rotationYaw = this.prevRotationYaw;
/*     */     }
/*     */ 
/*     */     
/* 353 */     if (!this.onGround && getRidingEntity() != null) {
/* 354 */       this.rotationYaw = (getRidingEntity()).rotationYaw;
/*     */     }
/*     */     
/* 357 */     if (this.world.getDifficulty().getId() > 0 && getNearPlayer() && !getIsTamed() && isNotScared()) {
/*     */       
/* 359 */       this.hissCounter++;
/*     */ 
/*     */ 
/*     */       
/* 363 */       if (this.hissCounter % 25 == 0) {
/* 364 */         setfMouth(0.3F);
/* 365 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_SNAKE_ANGRY);
/*     */       } 
/* 367 */       if (this.hissCounter % 35 == 0) {
/* 368 */         setfMouth(0.0F);
/*     */       }
/*     */       
/* 371 */       if (this.hissCounter > 100 && this.rand.nextInt(50) == 0) {
/*     */         
/* 373 */         setPissed(true);
/* 374 */         this.hissCounter = 0;
/*     */       } 
/*     */     } 
/* 377 */     if (this.hissCounter > 500) {
/* 378 */       this.hissCounter = 0;
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
/*     */   public float getfTongue() {
/* 390 */     return this.fTongue;
/*     */   }
/*     */   
/*     */   public void setfTongue(float fTongue) {
/* 394 */     this.fTongue = fTongue;
/*     */   }
/*     */   
/*     */   public float getfMouth() {
/* 398 */     return this.fMouth;
/*     */   }
/*     */   
/*     */   public void setfMouth(float fMouth) {
/* 402 */     this.fMouth = fMouth;
/*     */   }
/*     */   
/*     */   public float getfRattle() {
/* 406 */     return this.fRattle;
/*     */   }
/*     */   
/*     */   public void setfRattle(float fRattle) {
/* 410 */     this.fRattle = fRattle;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 415 */     super.onLivingUpdate();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 420 */     if (getAttackTarget() != null && this.rand.nextInt(300) == 0) {
/* 421 */       setAttackTarget(null);
/*     */     }
/*     */     
/* 424 */     EntityPlayer entityplayer1 = this.world.getClosestPlayerToEntity((Entity)this, 12.0D);
/* 425 */     if (entityplayer1 != null) {
/* 426 */       double distP = MoCTools.getSqDistanceTo((Entity)entityplayer1, this.posX, this.posY, this.posZ);
/* 427 */       if (isNotScared()) {
/* 428 */         if (distP < 5.0D) {
/* 429 */           setNearPlayer(true);
/*     */         } else {
/* 431 */           setNearPlayer(false);
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 442 */         setNearPlayer(false);
/*     */       } 
/*     */     } else {
/*     */       
/* 446 */       setNearPlayer(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityAsMob(Entity entityIn) {
/* 452 */     if ((getType() < 3 || getIsTamed()) && entityIn instanceof EntityPlayer) {
/* 453 */       return false;
/*     */     }
/*     */     
/* 456 */     if (entityIn instanceof EntityPlayer && !shouldAttackPlayers()) {
/* 457 */       return false;
/*     */     }
/* 459 */     setBiting(true);
/* 460 */     return super.attackEntityAsMob(entityIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void performAnimation(int i) {
/* 465 */     setBiting(true);
/*     */   }
/*     */   
/*     */   public boolean isBiting() {
/* 469 */     return this.isBiting;
/*     */   }
/*     */   
/*     */   public void setBiting(boolean flag) {
/* 473 */     if (flag && !this.world.isRemote) {
/* 474 */       MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 0), new NetworkRegistry.TargetPoint(this.world.provider
/* 475 */             .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */     }
/* 477 */     this.isBiting = flag;
/*     */   }
/*     */   
/*     */   public boolean isPissed() {
/* 481 */     return this.isPissed;
/*     */   }
/*     */   
/*     */   public void setPissed(boolean isPissed) {
/* 485 */     this.isPissed = isPissed;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/* 491 */     if (getType() < 3) {
/* 492 */       return super.attackEntityFrom(damagesource, i);
/*     */     }
/*     */     
/* 495 */     if (super.attackEntityFrom(damagesource, i)) {
/* 496 */       Entity entity = damagesource.getTrueSource();
/* 497 */       if (isRidingOrBeingRiddenBy(entity)) {
/* 498 */         return true;
/*     */       }
/* 500 */       if (entity != this && entity instanceof EntityLivingBase && super.shouldAttackPlayers()) {
/* 501 */         setPissed(true);
/* 502 */         setAttackTarget((EntityLivingBase)entity);
/*     */       } 
/* 504 */       return true;
/*     */     } 
/* 506 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dropFewItems(boolean flag, int x) {
/* 512 */     if (getEdad() > 60) {
/* 513 */       int j = this.rand.nextInt(3);
/* 514 */       for (int l = 0; l < j; l++)
/*     */       {
/* 516 */         entityDropItem(new ItemStack((Item)MoCItems.mocegg, 1, getType() + 20), 0.0F);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canAttackTarget(EntityLivingBase entity) {
/* 523 */     return (!(entity instanceof MoCEntitySnake) && entity.height < 0.5D && entity.width < 0.5D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void playStepSound(BlockPos pos, Block par4) {
/* 528 */     if (isInsideOfMaterial(Material.WATER)) {
/* 529 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_SNAKE_SWIM);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEvent getDeathSound() {
/* 540 */     return MoCSoundEvents.ENTITY_SNAKE_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 545 */     return MoCSoundEvents.ENTITY_SNAKE_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getAmbientSound() {
/* 550 */     return MoCSoundEvents.ENTITY_SNAKE_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getCanSpawnHere() {
/* 555 */     return (getCanSpawnHereCreature() && getCanSpawnHereLiving());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean checkSpawningBiome() {
/* 560 */     BlockPos pos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor((getEntityBoundingBox()).minY), this.posZ);
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
/*     */ 
/*     */     
/*     */     try {
/* 577 */       Biome currentbiome = MoCTools.Biomekind(this.world, pos);
/* 578 */       int l = this.rand.nextInt(10);
/*     */       
/* 580 */       if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.SNOWY)) {
/* 581 */         return false;
/*     */       }
/*     */       
/* 584 */       if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.SANDY)) {
/* 585 */         if (l < 5) {
/* 586 */           setType(7);
/*     */         } else {
/* 588 */           setType(2);
/*     */         } 
/*     */       }
/*     */       
/* 592 */       if (getType() == 7 && !BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.SANDY)) {
/* 593 */         setType(2);
/*     */       }
/* 595 */       if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.HILLS)) {
/* 596 */         if (l < 4) {
/* 597 */           setType(1);
/* 598 */         } else if (l < 7) {
/* 599 */           setType(5);
/*     */         } else {
/* 601 */           setType(6);
/*     */         } 
/*     */       }
/* 604 */       if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.SWAMP))
/*     */       {
/* 606 */         if (l < 4) {
/* 607 */           setType(8);
/* 608 */         } else if (l < 8) {
/* 609 */           setType(4);
/*     */         } else {
/* 611 */           setType(1);
/*     */         } 
/*     */       }
/* 614 */     } catch (Exception exception) {}
/*     */     
/* 616 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int nameYOffset() {
/* 621 */     return -30;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMyHealFood(ItemStack stack) {
/* 626 */     return (!stack.isEmpty() && stack.getItem() == MoCItems.ratRaw);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxSpawnedInChunk() {
/* 631 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isReadyToHunt() {
/* 636 */     return (getIsAdult() && !isMovementCeased());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEnchantments(EntityLivingBase entityLivingBaseIn, Entity entityIn) {
/* 641 */     if (isVenomous()) {
/* 642 */       if (entityIn instanceof EntityPlayer) {
/* 643 */         MoCreatures.poisonPlayer((EntityPlayer)entityIn);
/*     */       }
/* 645 */       ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.POISON, 150, 2));
/*     */     } 
/* 647 */     super.applyEnchantments(entityLivingBaseIn, entityIn);
/*     */   }
/*     */   
/*     */   private boolean isVenomous() {
/* 651 */     return (getType() == 3 || getType() == 4 || getType() == 5 || getType() == 6 || getType() == 7 || getType() == 9);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldAttackPlayers() {
/* 656 */     return (isPissed() && super.shouldAttackPlayers());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTalkInterval() {
/* 661 */     return 400;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAmphibian() {
/* 666 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canRidePlayer() {
/* 672 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected double maxDivingDepth() {
/* 678 */     return 1.0D * getEdad() / 100.0D;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntitySnake.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */