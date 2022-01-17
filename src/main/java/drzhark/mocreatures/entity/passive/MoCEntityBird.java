/*     */ package drzhark.mocreatures.entity.passive;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
/*     */ import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFleeFromEntityMoC;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFollowOwnerPlayer;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MoCEntityBird
/*     */   extends MoCEntityTameableAnimal
/*     */ {
/*     */   private boolean fleeing;
/*     */   public float wingb;
/*     */   public float wingc;
/*     */   public float wingd;
/*  48 */   public static final String[] birdNames = new String[] { "Dove", "Crow", "Parrot", "Blue", "Canary", "Red" }; public float winge; public float wingh; public boolean textureSet; private int jumpTimer; protected EntityAIWanderMoC2 wander;
/*  49 */   private static final DataParameter<Boolean> PRE_TAMED = EntityDataManager.createKey(MoCEntityBird.class, DataSerializers.BOOLEAN);
/*  50 */   private static final DataParameter<Boolean> IS_FLYING = EntityDataManager.createKey(MoCEntityBird.class, DataSerializers.BOOLEAN);
/*     */   
/*     */   public MoCEntityBird(World world) {
/*  53 */     super(world);
/*  54 */     setSize(0.4F, 0.3F);
/*  55 */     this.collidedVertically = true;
/*  56 */     this.wingb = 0.0F;
/*  57 */     this.wingc = 0.0F;
/*  58 */     this.wingh = 1.0F;
/*  59 */     this.fleeing = false;
/*  60 */     this.textureSet = false;
/*  61 */     setTamed(false);
/*  62 */     this.stepHeight = 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initEntityAI() {
/*  67 */     this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  68 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIFleeFromEntityMoC((EntityCreature)this, new Predicate<Entity>()
/*     */           {
/*     */             public boolean apply(Entity entity) {
/*  71 */               return (!(entity instanceof MoCEntityBird) && (entity.height > 0.4F || entity.width > 0.4F));
/*     */             }
/*     */           },  6.0F, 1.0D, 1.3D));
/*  74 */     this.tasks.addTask(3, (EntityAIBase)new EntityAIFollowOwnerPlayer((EntityLiving)this, 0.8D, 2.0F, 10.0F));
/*  75 */     this.tasks.addTask(4, (EntityAIBase)(this.wander = new EntityAIWanderMoC2((EntityCreature)this, 1.0D, 80)));
/*  76 */     this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  81 */     super.applyEntityAttributes();
/*  82 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
/*  83 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectType() {
/*  88 */     if (getType() == 0) {
/*  89 */       setType(this.rand.nextInt(6) + 1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  96 */     switch (getType()) {
/*     */       case 1:
/*  98 */         return MoCreatures.proxy.getTexture("birdwhite.png");
/*     */       case 2:
/* 100 */         return MoCreatures.proxy.getTexture("birdblack.png");
/*     */       case 3:
/* 102 */         return MoCreatures.proxy.getTexture("birdgreen.png");
/*     */       case 4:
/* 104 */         return MoCreatures.proxy.getTexture("birdblue.png");
/*     */       case 5:
/* 106 */         return MoCreatures.proxy.getTexture("birdyellow.png");
/*     */       case 6:
/* 108 */         return MoCreatures.proxy.getTexture("birdred.png");
/*     */     } 
/*     */     
/* 111 */     return MoCreatures.proxy.getTexture("birdblue.png");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/* 117 */     super.entityInit();
/* 118 */     this.dataManager.register(PRE_TAMED, Boolean.valueOf(false));
/* 119 */     this.dataManager.register(IS_FLYING, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public boolean getPreTamed() {
/* 123 */     return ((Boolean)this.dataManager.get(PRE_TAMED)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setPreTamed(boolean flag) {
/* 127 */     this.dataManager.set(PRE_TAMED, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public boolean getIsFlying() {
/* 131 */     return ((Boolean)this.dataManager.get(IS_FLYING)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setIsFlying(boolean flag) {
/* 135 */     this.dataManager.set(IS_FLYING, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   public void fall(float f, float f1) {}
/*     */ 
/*     */   
/*     */   private int[] FindTreeTop(int i, int j, int k) {
/* 143 */     int l = i - 5;
/* 144 */     int i1 = k - 5;
/* 145 */     int j1 = i + 5;
/* 146 */     int k1 = j + 7;
/* 147 */     int l1 = k + 5;
/* 148 */     for (int i2 = l; i2 < j1; i2++) {
/* 149 */       for (int j2 = i1; j2 < l1; j2++) {
/* 150 */         BlockPos pos = new BlockPos(i2, j, j2);
/* 151 */         IBlockState blockstate = this.world.getBlockState(pos);
/* 152 */         if (!blockstate.getBlock().isAir(blockstate, (IBlockAccess)this.world, pos) && blockstate.getMaterial() == Material.WOOD) {
/*     */ 
/*     */           
/* 155 */           int l2 = j;
/*     */           
/* 157 */           while (l2 < k1) {
/*     */ 
/*     */             
/* 160 */             BlockPos pos1 = new BlockPos(i2, l2, j2);
/* 161 */             IBlockState blockstate1 = this.world.getBlockState(pos1);
/* 162 */             if (blockstate1.getBlock().isAir(blockstate1, (IBlockAccess)this.world, pos1)) {
/* 163 */               return new int[] { i2, l2 + 2, j2 };
/*     */             }
/* 165 */             l2++;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 171 */     return new int[] { 0, 0, 0 };
/*     */   }
/*     */   
/*     */   private boolean FlyToNextEntity(Entity entity) {
/* 175 */     if (entity != null) {
/* 176 */       int i = MathHelper.floor(entity.posX);
/* 177 */       int j = MathHelper.floor(entity.posY);
/* 178 */       int k = MathHelper.floor(entity.posZ);
/* 179 */       faceLocation(i, j, k, 30.0F);
/* 180 */       if (MathHelper.floor(this.posY) < j) {
/* 181 */         this.motionY += 0.15D;
/*     */       }
/* 183 */       if (this.posX < entity.posX) {
/* 184 */         double d = entity.posX - this.posX;
/* 185 */         if (d > 0.5D) {
/* 186 */           this.motionX += 0.05D;
/*     */         }
/*     */       } else {
/* 189 */         double d1 = this.posX - entity.posX;
/* 190 */         if (d1 > 0.5D) {
/* 191 */           this.motionX -= 0.05D;
/*     */         }
/*     */       } 
/* 194 */       if (this.posZ < entity.posZ) {
/* 195 */         double d2 = entity.posZ - this.posZ;
/* 196 */         if (d2 > 0.5D) {
/* 197 */           this.motionZ += 0.05D;
/*     */         }
/*     */       } else {
/* 200 */         double d3 = this.posZ - entity.posZ;
/* 201 */         if (d3 > 0.5D) {
/* 202 */           this.motionZ -= 0.05D;
/*     */         }
/*     */       } 
/* 205 */       return true;
/*     */     } 
/* 207 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean FlyToNextTree() {
/* 213 */     int[] ai = ReturnNearestMaterialCoord((Entity)this, Material.LEAVES, Double.valueOf(20.0D));
/* 214 */     int[] ai1 = FindTreeTop(ai[0], ai[1], ai[2]);
/* 215 */     if (ai1[1] != 0) {
/* 216 */       int i = ai1[0];
/* 217 */       int j = ai1[1];
/* 218 */       int k = ai1[2];
/* 219 */       faceLocation(i, j, k, 30.0F);
/* 220 */       if (j - MathHelper.floor(this.posY) > 2) {
/* 221 */         this.motionY += 0.15D;
/*     */       }
/* 223 */       int l = 0;
/* 224 */       int i1 = 0;
/* 225 */       if (this.posX < i) {
/* 226 */         l = i - MathHelper.floor(this.posX);
/* 227 */         this.motionX += 0.05D;
/*     */       } else {
/* 229 */         l = MathHelper.floor(this.posX) - i;
/* 230 */         this.motionX -= 0.05D;
/*     */       } 
/* 232 */       if (this.posZ < k) {
/* 233 */         i1 = k - MathHelper.floor(this.posZ);
/* 234 */         this.motionZ += 0.05D;
/*     */       } else {
/* 236 */         i1 = MathHelper.floor(this.posX) - k;
/* 237 */         this.motionZ -= 0.05D;
/*     */       } 
/* 239 */       double d = (l + i1);
/* 240 */       if (d < 3.0D) {
/* 241 */         return true;
/*     */       }
/*     */     } 
/* 244 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 249 */     return Items.FEATHER;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getDeathSound() {
/* 254 */     return MoCSoundEvents.ENTITY_BIRD_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 259 */     return MoCSoundEvents.ENTITY_BIRD_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getAmbientSound() {
/* 264 */     if (getType() == 1) {
/* 265 */       return MoCSoundEvents.ENTITY_BIRD_AMBIENT_WHITE;
/*     */     }
/* 267 */     if (getType() == 2) {
/* 268 */       return MoCSoundEvents.ENTITY_BIRD_AMBIENT_BLACK;
/*     */     }
/* 270 */     if (getType() == 3) {
/* 271 */       return MoCSoundEvents.ENTITY_BIRD_AMBIENT_GREEN;
/*     */     }
/* 273 */     if (getType() == 4) {
/* 274 */       return MoCSoundEvents.ENTITY_BIRD_AMBIENT_BLUE;
/*     */     }
/* 276 */     if (getType() == 5) {
/* 277 */       return MoCSoundEvents.ENTITY_BIRD_AMBIENT_YELLOW;
/*     */     }
/* 279 */     return MoCSoundEvents.ENTITY_BIRD_AMBIENT_RED;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getYOffset() {
/* 285 */     if (getRidingEntity() instanceof EntityPlayer) {
/* 286 */       return ((EntityPlayer)getRidingEntity()).isSneaking() ? 0.2D : 0.44999998807907104D;
/*     */     }
/*     */     
/* 289 */     return super.getYOffset();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/* 294 */     Boolean tameResult = processTameInteract(player, hand);
/* 295 */     if (tameResult != null) {
/* 296 */       return tameResult.booleanValue();
/*     */     }
/*     */     
/* 299 */     ItemStack stack = player.getHeldItem(hand);
/* 300 */     if (!stack.isEmpty() && getPreTamed() && !getIsTamed() && stack.getItem() == Items.WHEAT_SEEDS) {
/* 301 */       stack.shrink(1);
/* 302 */       if (stack.isEmpty()) {
/* 303 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 305 */       if (!this.world.isRemote) {
/* 306 */         MoCTools.tameWithName(player, (IMoCTameable)this);
/*     */       }
/* 308 */       return true;
/*     */     } 
/*     */     
/* 311 */     if (!getIsTamed()) {
/* 312 */       return false;
/*     */     }
/* 314 */     if (getRidingEntity() == null) {
/* 315 */       if (startRiding((Entity)player)) {
/* 316 */         this.rotationYaw = player.rotationYaw;
/*     */       }
/*     */       
/* 319 */       return true;
/*     */     } 
/*     */     
/* 322 */     return super.processInteract(player, hand);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 327 */     super.onLivingUpdate();
/*     */     
/* 329 */     this.winge = this.wingb;
/* 330 */     this.wingd = this.wingc;
/* 331 */     this.wingc = (float)(this.wingc + (this.onGround ? -1 : 4) * 0.3D);
/* 332 */     if (this.wingc < 0.0F) {
/* 333 */       this.wingc = 0.0F;
/*     */     }
/* 335 */     if (this.wingc > 1.0F) {
/* 336 */       this.wingc = 1.0F;
/*     */     }
/* 338 */     if (!this.onGround && this.wingh < 1.0F) {
/* 339 */       this.wingh = 1.0F;
/*     */     }
/* 341 */     this.wingh = (float)(this.wingh * 0.9D);
/* 342 */     if (!this.onGround && this.motionY < 0.0D) {
/* 343 */       this.motionY *= 0.8D;
/*     */     }
/* 345 */     this.wingb += this.wingh * 2.0F;
/*     */ 
/*     */     
/* 348 */     if (!this.world.isRemote) {
/*     */       
/* 350 */       if (isMovementCeased() && getIsFlying()) {
/* 351 */         setIsFlying(false);
/*     */       }
/*     */       
/* 354 */       if (getIsFlying() && getNavigator().noPath() && !isMovementCeased() && getAttackTarget() == null && this.rand.nextInt(30) == 0) {
/* 355 */         this.wander.makeUpdate();
/*     */       }
/*     */       
/* 358 */       if (!getIsFlying() && !getIsTamed() && this.rand.nextInt(10) == 0) {
/* 359 */         List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(4.0D, 4.0D, 4.0D));
/* 360 */         for (int i = 0; i < list.size(); i++) {
/* 361 */           Entity entity1 = list.get(i);
/* 362 */           if (entity1 instanceof EntityLivingBase && !(entity1 instanceof MoCEntityBird))
/*     */           {
/*     */             
/* 365 */             if (((EntityLivingBase)entity1).width >= 0.4F && ((EntityLivingBase)entity1).height >= 0.4F && canEntityBeSeen(entity1)) {
/* 366 */               setIsFlying(true);
/* 367 */               this.fleeing = true;
/* 368 */               this.wander.makeUpdate();
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/* 373 */       if (!isMovementCeased() && !getIsFlying() && this.rand.nextInt(getIsTamed() ? 1000 : 400) == 0) {
/* 374 */         setIsFlying(true);
/* 375 */         this.wander.makeUpdate();
/*     */       } 
/*     */       
/* 378 */       if (getIsFlying() && this.rand.nextInt(200) == 0) {
/* 379 */         setIsFlying(false);
/*     */       }
/*     */       
/* 382 */       if (this.fleeing && this.rand.nextInt(50) == 0) {
/* 383 */         this.fleeing = false;
/*     */       }
/*     */ 
/*     */       
/* 387 */       if (!this.fleeing) {
/* 388 */         EntityItem entityitem = getClosestItem((Entity)this, 12.0D, Items.WHEAT_SEEDS, Items.MELON_SEEDS);
/* 389 */         if (entityitem != null) {
/* 390 */           FlyToNextEntity((Entity)entityitem);
/* 391 */           EntityItem entityitem1 = getClosestItem((Entity)this, 1.0D, Items.WHEAT_SEEDS, Items.MELON_SEEDS);
/* 392 */           if (this.rand.nextInt(50) == 0 && entityitem1 != null) {
/* 393 */             entityitem1.setDead();
/* 394 */             setPreTamed(true);
/*     */           } 
/*     */         } 
/*     */       } 
/* 398 */       if (this.rand.nextInt(10) == 0 && isInsideOfMaterial(Material.WATER)) {
/* 399 */         WingFlap();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 406 */     super.onUpdate();
/*     */     
/* 408 */     if (getRidingEntity() != null) {
/* 409 */       this.rotationYaw = (getRidingEntity()).rotationYaw;
/*     */     }
/*     */     
/* 412 */     if (getRidingEntity() != null && getRidingEntity() instanceof EntityPlayer) {
/* 413 */       EntityPlayer entityplayer = (EntityPlayer)getRidingEntity();
/* 414 */       this.rotationYaw = entityplayer.rotationYaw;
/* 415 */       entityplayer.fallDistance = 0.0F;
/* 416 */       if (entityplayer.motionY < -0.1D) {
/* 417 */         entityplayer.motionY *= 0.6D;
/*     */       }
/*     */     } 
/* 420 */     if (--this.jumpTimer <= 0 && this.onGround && (this.motionX > 0.05D || this.motionZ > 0.05D || this.motionX < -0.05D || this.motionZ < -0.05D)) {
/*     */       
/* 422 */       this.motionY = 0.25D;
/* 423 */       float velX = MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F);
/* 424 */       float velZ = MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F);
/*     */       
/* 426 */       this.motionX += (-0.2F * velX);
/* 427 */       this.motionZ += (0.2F * velZ);
/* 428 */       this.jumpTimer = 15;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int[] ReturnNearestMaterialCoord(Entity entity, Material material, Double double1) {
/* 433 */     AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().expand(double1.doubleValue(), double1.doubleValue(), double1.doubleValue());
/* 434 */     int i = MathHelper.floor(axisalignedbb.minX);
/* 435 */     int j = MathHelper.floor(axisalignedbb.maxX + 1.0D);
/* 436 */     int k = MathHelper.floor(axisalignedbb.minY);
/* 437 */     int l = MathHelper.floor(axisalignedbb.maxY + 1.0D);
/* 438 */     int i1 = MathHelper.floor(axisalignedbb.minZ);
/* 439 */     int j1 = MathHelper.floor(axisalignedbb.maxZ + 1.0D);
/* 440 */     for (int k1 = i; k1 < j; k1++) {
/* 441 */       for (int l1 = k; l1 < l; l1++) {
/* 442 */         for (int i2 = i1; i2 < j1; i2++) {
/* 443 */           BlockPos pos = new BlockPos(k1, l1, i2);
/* 444 */           IBlockState blockstate = this.world.getBlockState(pos);
/* 445 */           if (blockstate.getBlock() != null && !blockstate.getBlock().isAir(blockstate, (IBlockAccess)this.world, pos) && blockstate
/* 446 */             .getMaterial() == material) {
/* 447 */             return new int[] { k1, l1, i2 };
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 455 */     return new int[] { -1, 0, 0 };
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDead() {
/* 460 */     if (!this.world.isRemote && getIsTamed() && getHealth() > 0.0F) {
/*     */       return;
/*     */     }
/* 463 */     super.setDead();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void WingFlap() {
/* 469 */     this.motionY += 0.05D;
/* 470 */     if (this.rand.nextInt(30) == 0) {
/* 471 */       this.motionX += 0.2D;
/*     */     }
/* 473 */     if (this.rand.nextInt(30) == 0) {
/* 474 */       this.motionX -= 0.2D;
/*     */     }
/* 476 */     if (this.rand.nextInt(30) == 0) {
/* 477 */       this.motionZ += 0.2D;
/*     */     }
/* 479 */     if (this.rand.nextInt(30) == 0) {
/* 480 */       this.motionZ -= 0.2D;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int nameYOffset() {
/* 486 */     return -40;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMyHealFood(ItemStack stack) {
/* 491 */     return (!stack.isEmpty() && (stack.getItem() == Items.WHEAT_SEEDS || stack.getItem() == Items.MELON_SEEDS));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNotScared() {
/* 496 */     return getIsTamed();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFlyer() {
/* 501 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int maxFlyingHeight() {
/* 506 */     if (getIsTamed())
/* 507 */       return 4; 
/* 508 */     return 6;
/*     */   }
/*     */ 
/*     */   
/*     */   public int minFlyingHeight() {
/* 513 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canRidePlayer() {
/* 519 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityBird.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */