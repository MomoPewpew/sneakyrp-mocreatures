/*     */ package drzhark.mocreatures.entity;
/*     */
/*     */ import drzhark.mocreatures.MoCEntityData;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.ai.PathNavigateFlyer;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityAgeable;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.pathfinding.Path;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */
/*     */
/*     */
/*     */ public abstract class MoCEntityAmbient
/*     */   extends EntityAnimal
/*     */   implements IMoCEntity
/*     */ {
/*  48 */   protected static final DataParameter<Boolean> ADULT = EntityDataManager.createKey(MoCEntityAmbient.class, DataSerializers.BOOLEAN);
/*  49 */   protected static final DataParameter<Integer> TYPE = EntityDataManager.createKey(MoCEntityAmbient.class, DataSerializers.VARINT);
/*  50 */   protected static final DataParameter<Integer> AGE = EntityDataManager.createKey(MoCEntityAmbient.class, DataSerializers.VARINT);
/*  51 */   protected static final DataParameter<String> NAME_STR = EntityDataManager.createKey(MoCEntityAmbient.class, DataSerializers.STRING);
/*     */
/*     */   protected String texture;
/*     */   protected boolean riderIsDisconnecting;
/*     */   protected PathNavigate navigatorFlyer;
/*     */
/*     */   public MoCEntityAmbient(World world) {
/*  58 */     super(world);
/*  59 */     this.navigatorFlyer = (PathNavigate)new PathNavigateFlyer((EntityLiving)this, world);
/*     */   }
/*     */
/*     */
/*     */   public ResourceLocation getTexture() {
/*  64 */     return MoCreatures.proxy.getTexture(this.texture);
/*     */   }
/*     */
/*     */
/*     */   protected void applyEntityAttributes() {
/*  69 */     super.applyEntityAttributes();
/*     */   }
/*     */
/*     */
/*     */   public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData par1EntityLivingData) {
/*  74 */     selectType();
/*  75 */     return super.onInitialSpawn(difficulty, par1EntityLivingData);
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public void selectType() {
/*  84 */     setType(1);
/*     */   }
/*     */
/*     */
/*     */   public EntityAgeable createChild(EntityAgeable var1) {
/*  89 */     return null;
/*     */   }
/*     */
/*     */
/*     */   protected void entityInit() {
/*  94 */     super.entityInit();
/*  95 */     this.dataManager.register(ADULT, Boolean.valueOf(false));
/*  96 */     this.dataManager.register(TYPE, Integer.valueOf(0));
/*  97 */     this.dataManager.register(AGE, Integer.valueOf(45));
/*  98 */     this.dataManager.register(NAME_STR, "");
/*     */   }
/*     */
/*     */
/*     */   public void setType(int i) {
/* 103 */     this.dataManager.set(TYPE, Integer.valueOf(i));
/*     */   }
/*     */
/*     */
/*     */   public int getType() {
/* 108 */     return ((Integer)this.dataManager.get(TYPE)).intValue();
/*     */   }
/*     */
/*     */
/*     */   public void setDisplayName(boolean flag) {}
/*     */
/*     */
/*     */   public boolean renderName() {
/* 116 */     return (MoCreatures.proxy.getDisplayPetName() &&
/* 117 */       getPetName() != null && !getPetName().equals("") && !isBeingRidden() && getRidingEntity() == null);
/*     */   }
/*     */
/*     */
/*     */   public boolean getIsAdult() {
/* 122 */     return ((Boolean)this.dataManager.get(ADULT)).booleanValue();
/*     */   }
/*     */
/*     */
/*     */   public void setAdult(boolean flag) {
/* 127 */     this.dataManager.set(ADULT, Boolean.valueOf(flag));
/*     */   }
/*     */
/*     */
/*     */   public String getPetName() {
/* 132 */     return (String)this.dataManager.get(NAME_STR);
/*     */   }
/*     */
/*     */
/*     */   public void setPetName(String name) {
/* 137 */     this.dataManager.set(NAME_STR, name);
/*     */   }
/*     */
/*     */
/*     */   public int getEdad() {
/* 142 */     return ((Integer)this.dataManager.get(AGE)).intValue();
/*     */   }
/*     */
/*     */
/*     */   public void setEdad(int i) {
/* 147 */     this.dataManager.set(AGE, Integer.valueOf(i));
/*     */   }
/*     */
/*     */
/*     */   public boolean getIsTamed() {
/* 152 */     return false;
/*     */   }
/*     */
/*     */
/*     */   public int getOwnerPetId() {
/* 157 */     return 0;
/*     */   }
/*     */
/*     */
/*     */
/*     */   public void setOwnerPetId(int petId) {}
/*     */
/*     */
/*     */   public UUID getOwnerId() {
/* 166 */     return null;
/*     */   }
/*     */
/*     */
/*     */   protected boolean canDespawn() {
/* 171 */     if (MoCreatures.proxy.forceDespawns) {
/* 172 */       return !getIsTamed();
/*     */     }
/* 174 */     return false;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public boolean checkSpawningBiome() {
/* 185 */     return true;
/*     */   }
/*     */
/*     */   protected EntityLivingBase getClosestEntityLiving(Entity entity, double d) {
/* 189 */     double d1 = -1.0D;
/* 190 */     EntityLivingBase entityliving = null;
/* 191 */     List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(d, d, d));
/* 192 */     for (int i = 0; i < list.size(); i++) {
/* 193 */       Entity entity1 = list.get(i);
/*     */
/* 195 */       if (!entitiesToIgnore(entity1)) {
/*     */
/*     */
/* 198 */         double d2 = entity1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
/* 199 */         if ((d < 0.0D || d2 < d * d) && (d1 == -1.0D || d2 < d1) && ((EntityLivingBase)entity1).canEntityBeSeen(entity)) {
/* 200 */           d1 = d2;
/* 201 */           entityliving = (EntityLivingBase)entity1;
/*     */         }
/*     */       }
/*     */     }
/* 205 */     return entityliving;
/*     */   }
/*     */
/*     */
/*     */   public void onLivingUpdate() {
/* 210 */     if (!this.world.isRemote) {
/* 211 */       if (isMovementCeased()) {
/* 212 */         getNavigator().clearPath();
/*     */       }
/* 214 */       getNavigator().onUpdateNavigation();
/*     */     }
/* 216 */     super.onLivingUpdate();
/*     */   }
/*     */
/*     */   public boolean swimmerEntity() {
/* 220 */     return false;
/*     */   }
/*     */
/*     */   public boolean isSwimming() {
/* 224 */     return isInsideOfMaterial(Material.WATER);
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public boolean isMyAphrodisiac(Item item1) {
/* 234 */     return false;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public void dropMyStuff() {}
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   protected boolean isMyHealFood(ItemStack itemstack) {
/* 248 */     return false;
/*     */   }
/*     */
/*     */
/*     */   public boolean isInWater() {
/* 253 */     if (swimmerEntity()) {
/* 254 */       return false;
/*     */     }
/* 256 */     return super.isInWater();
/*     */   }
/*     */
/*     */
/*     */   public boolean canBreatheUnderwater() {
/* 261 */     return swimmerEntity();
/*     */   }
/*     */
/*     */   public EntityItem getClosestItem(Entity entity, double d, ItemStack item, ItemStack item1) {
/* 265 */     double d1 = -1.0D;
/* 266 */     EntityItem entityitem = null;
/* 267 */     List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(d, d, d));
/* 268 */     for (int k = 0; k < list.size(); k++) {
/* 269 */       Entity entity1 = list.get(k);
/* 270 */       if (entity1 instanceof EntityItem) {
/*     */
/*     */
/* 273 */         EntityItem entityitem1 = (EntityItem)entity1;
/* 274 */         if (entityitem1.getItem() == item || entityitem1.getItem() == item1) {
/*     */
/*     */
/* 277 */           double d2 = entityitem1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
/* 278 */           if ((d < 0.0D || d2 < d * d) && (d1 == -1.0D || d2 < d1)) {
/* 279 */             d1 = d2;
/* 280 */             entityitem = entityitem1;
/*     */           }
/*     */         }
/*     */       }
/* 284 */     }  return entityitem;
/*     */   }
/*     */
/*     */   public EntityItem getClosestEntityItem(Entity entity, double d) {
/* 288 */     double d1 = -1.0D;
/* 289 */     EntityItem entityitem = null;
/* 290 */     List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(d, d, d));
/* 291 */     for (int k = 0; k < list.size(); k++) {
/* 292 */       Entity entity1 = list.get(k);
/* 293 */       if (entity1 instanceof EntityItem) {
/*     */
/*     */
/* 296 */         EntityItem entityitem1 = (EntityItem)entity1;
/* 297 */         double d2 = entityitem1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
/* 298 */         if ((d < 0.0D || d2 < d * d) && (d1 == -1.0D || d2 < d1)) {
/* 299 */           d1 = d2;
/* 300 */           entityitem = entityitem1;
/*     */         }
/*     */       }
/*     */     }
/* 304 */     return entityitem;
/*     */   }
/*     */
/*     */   public void faceLocation(int i, int j, int k, float f) {
/* 308 */     double var4 = i + 0.5D - this.posX;
/* 309 */     double var8 = k + 0.5D - this.posZ;
/* 310 */     double var6 = j + 0.5D - this.posY;
/* 311 */     double var14 = MathHelper.sqrt(var4 * var4 + var8 * var8);
/* 312 */     float var12 = (float)(Math.atan2(var8, var4) * 180.0D / Math.PI) - 90.0F;
/* 313 */     float var13 = (float)-(Math.atan2(var6, var14) * 180.0D / Math.PI);
/* 314 */     this.rotationPitch = -updateRotation(this.rotationPitch, var13, f);
/* 315 */     this.rotationYaw = updateRotation(this.rotationYaw, var12, f);
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   private float updateRotation(float par1, float par2, float par3) {
/*     */     float var4;
/* 324 */     for (var4 = par2 - par1; var4 < -180.0F; var4 += 360.0F);
/*     */
/*     */
/*     */
/* 328 */     while (var4 >= 180.0F) {
/* 329 */       var4 -= 360.0F;
/*     */     }
/*     */
/* 332 */     if (var4 > par3) {
/* 333 */       var4 = par3;
/*     */     }
/*     */
/* 336 */     if (var4 < -par3) {
/* 337 */       var4 = -par3;
/*     */     }
/*     */
/* 340 */     return par1 + var4;
/*     */   }
/*     */
/*     */   public void getMyOwnPath(Entity entity, float f) {
/* 344 */     Path pathentity = getNavigator().getPathToEntityLiving(entity);
/* 345 */     if (pathentity != null) {
/* 346 */       getNavigator().setPath(pathentity, 1.0D);
/*     */     }
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */   public void Riding() {
/* 354 */     if (isBeingRidden() && getRidingEntity() instanceof EntityPlayer) {
/* 355 */       EntityPlayer entityplayer = (EntityPlayer)getRidingEntity();
/* 356 */       List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(1.0D, 0.0D, 1.0D));
/* 357 */       if (list != null) {
/* 358 */         for (int i = 0; i < list.size(); i++) {
/* 359 */           Entity entity = list.get(i);
/* 360 */           if (!entity.isDead) {
/*     */
/*     */
/* 363 */             entity.onCollideWithPlayer(entityplayer);
/* 364 */             if (entity instanceof EntityMob) {
/*     */
/*     */
/* 367 */               float f = getDistance(entity);
/* 368 */               if (f >= 2.0F || this.rand.nextInt(10) == 0);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 373 */       if (entityplayer.isSneaking() &&
/* 374 */         !this.world.isRemote) {
/* 375 */         entityplayer.dismountRidingEntity();
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   protected void getPathOrWalkableBlock(Entity entity, float f) {
/* 382 */     Path pathentity = this.navigator.getPathToPos(entity.getPosition());
/* 383 */     if (pathentity == null && f > 8.0F) {
/* 384 */       int i = MathHelper.floor(entity.posX) - 2;
/* 385 */       int j = MathHelper.floor(entity.posZ) - 2;
/* 386 */       int k = MathHelper.floor((entity.getEntityBoundingBox()).minY);
/* 387 */       for (int l = 0; l <= 4; l++) {
/* 388 */         for (int i1 = 0; i1 <= 4; i1++) {
/* 389 */           BlockPos pos = new BlockPos(i, j, k);
/* 390 */           if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && this.world.getBlockState(pos.add(l, -1, i1)).isNormalCube() &&
/* 391 */             !this.world.getBlockState(pos.add(l, 0, i1)).isNormalCube() &&
/* 392 */             !this.world.getBlockState(pos.add(l, 1, i1)).isNormalCube()) {
/* 393 */             setLocationAndAngles(((i + l) + 0.5F), k, ((j + i1) + 0.5F), this.rotationYaw, this.rotationPitch);
/*     */             return;
/*     */           }
/*     */         }
/*     */       }
/*     */     } else {
/* 399 */       this.navigator.setPath(pathentity, 16.0D);
/*     */     }
/*     */   }
/*     */
/*     */   public boolean getCanSpawnHereAnimal() {
/* 404 */     int i = MathHelper.floor(this.posX);
/* 405 */     int j = MathHelper.floor((getEntityBoundingBox()).minY);
/* 406 */     int k = MathHelper.floor(this.posZ);
/* 407 */     BlockPos pos = new BlockPos(i, j, k);
/* 408 */     return (this.world.getBlockState(pos.down()).getBlock() == Blocks.GRASS && this.world.getLight(pos) > 8);
/*     */   }
/*     */
/*     */   public boolean getCanSpawnHereCreature() {
/* 412 */     int i = MathHelper.floor(this.posX);
/* 413 */     int j = MathHelper.floor((getEntityBoundingBox()).minY);
/* 414 */     int k = MathHelper.floor(this.posZ);
/* 415 */     return (getBlockPathWeight(new BlockPos(i, j, k)) >= 0.0F);
/*     */   }
/*     */
/*     */   public boolean getCanSpawnHereLiving() {
/* 419 */     return (this.world.checkNoEntityCollision(getEntityBoundingBox()) && this.world
/* 420 */       .getCollisionBoxes((Entity)this, getEntityBoundingBox()).size() == 0 &&
/* 421 */       !this.world.containsAnyLiquid(getEntityBoundingBox()));
/*     */   }
/*     */
/*     */   public boolean getCanSpawnHereAquatic() {
/* 425 */     return this.world.checkNoEntityCollision(getEntityBoundingBox());
/*     */   }
/*     */
/*     */
/*     */   public boolean getCanSpawnHere() {
/* 430 */     if (((MoCEntityData)MoCreatures.entityMap.get(getClass())).getFrequency() <= 0) {
/* 431 */       return false;
/*     */     }
/* 433 */     BlockPos pos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor((getEntityBoundingBox()).minY), this.posZ);
/*     */
/* 435 */     String s = MoCTools.biomeName(this.world, pos);
/*     */
/* 437 */     if (s.equals("Jungle") || s.equals("JungleHills")) {
/* 438 */       return getCanSpawnHereJungle();
/*     */     }
/*     */
/* 441 */     return super.getCanSpawnHere();
/*     */   }
/*     */
/*     */   public boolean getCanSpawnHereJungle() {
/* 445 */     if (this.world.checkNoEntityCollision(getEntityBoundingBox()) && this.world
/* 446 */       .getCollisionBoxes((Entity)this, getEntityBoundingBox()).isEmpty() &&
/* 447 */       !this.world.containsAnyLiquid(getEntityBoundingBox())) {
/* 448 */       int var1 = MathHelper.floor(this.posX);
/* 449 */       int var2 = MathHelper.floor((getEntityBoundingBox()).minY);
/* 450 */       int var3 = MathHelper.floor(this.posZ);
/*     */
/* 452 */       if (var2 < 63) {
/* 453 */         return false;
/*     */       }
/*     */
/* 456 */       BlockPos pos = new BlockPos(var1, var2, var3);
/* 457 */       IBlockState blockstate = this.world.getBlockState(pos.down());
/* 458 */       Block block = blockstate.getBlock();
/*     */
/* 460 */       if (block == Blocks.GRASS || block == Blocks.LEAVES || block.isLeaves(blockstate, (IBlockAccess)this.world, pos.down())) {
/* 461 */         return true;
/*     */       }
/*     */     }
/*     */
/* 465 */     return false;
/*     */   }
/*     */
/*     */
/*     */   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
/* 470 */     super.writeEntityToNBT(nbttagcompound);
/* 471 */     nbttagcompound = MoCTools.getEntityData((Entity)this);
/* 472 */     nbttagcompound.setBoolean("Adult", getIsAdult());
/* 473 */     nbttagcompound.setInteger("Edad", getEdad());
/* 474 */     nbttagcompound.setString("Name", getPetName());
/* 475 */     nbttagcompound.setInteger("TypeInt", getType());
/*     */   }
/*     */
/*     */
/*     */   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
/* 480 */     super.readEntityFromNBT(nbttagcompound);
/* 481 */     nbttagcompound = MoCTools.getEntityData((Entity)this);
/* 482 */     setAdult(nbttagcompound.getBoolean("Adult"));
/* 483 */     setEdad(nbttagcompound.getInteger("Edad"));
/* 484 */     setPetName(nbttagcompound.getString("Name"));
/* 485 */     setType(nbttagcompound.getInteger("TypeInt"));
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   protected double myFallSpeed() {
/* 494 */     return 0.6D;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   protected double flyerThrust() {
/* 503 */     return 0.3D;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   protected float flyerFriction() {
/* 512 */     return 0.91F;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   protected boolean selfPropelledFlyer() {
/* 522 */     return false;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public void makeEntityJump() {}
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public boolean isFlyer() {
/* 537 */     return false;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public int getMaxTemper() {
/* 545 */     return 100;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */   public double getCustomSpeed() {
/* 552 */     return 0.8D;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */   public double getCustomJump() {
/* 559 */     return 0.4D;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */   protected SoundEvent getAngrySound() {
/* 566 */     return null;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */   public void makeEntityDive() {}
/*     */
/*     */
/*     */
/*     */   public boolean rideableEntity() {
/* 577 */     return false;
/*     */   }
/*     */
/*     */
/*     */   public int nameYOffset() {
/* 582 */     return -80;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */   protected Entity findPlayerToAttack() {
/* 589 */     return null;
/*     */   }
/*     */
/*     */   public void repelMobs(Entity entity1, Double dist, World world) {
/* 593 */     List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(entity1, entity1.getEntityBoundingBox().expand(dist.doubleValue(), 4.0D, dist.doubleValue()));
/* 594 */     for (int i = 0; i < list.size(); i++) {
/* 595 */       Entity entity = list.get(i);
/* 596 */       if (entity instanceof EntityMob) {
/*     */
/*     */
/* 599 */         EntityMob entitymob = (EntityMob)entity;
/* 600 */         entitymob.setAttackTarget(null);
/* 601 */         entitymob.getNavigator().clearPath();
/*     */       }
/*     */     }
/*     */   }
/*     */   public void faceItem(int i, int j, int k, float f) {
/* 606 */     double d = i - this.posX;
/* 607 */     double d1 = k - this.posZ;
/* 608 */     double d2 = j - this.posY;
/* 609 */     double d3 = MathHelper.sqrt(d * d + d1 * d1);
/* 610 */     float f1 = (float)(Math.atan2(d1, d) * 180.0D / 3.141592741012573D) - 90.0F;
/* 611 */     float f2 = (float)(Math.atan2(d2, d3) * 180.0D / 3.141592741012573D);
/* 612 */     this.rotationPitch = -adjustRotation(this.rotationPitch, f2, f);
/* 613 */     this.rotationYaw = adjustRotation(this.rotationYaw, f1, f);
/*     */   }
/*     */
/*     */   public float adjustRotation(float f, float f1, float f2) {
/* 617 */     float f3 = f1;
/* 618 */     for (f3 = f1 - f; f3 < -180.0F; f3 += 360.0F);
/*     */
/* 620 */     for (; f3 >= 180.0F; f3 -= 360.0F);
/*     */
/* 622 */     if (f3 > f2) {
/* 623 */       f3 = f2;
/*     */     }
/* 625 */     if (f3 < -f2) {
/* 626 */       f3 = -f2;
/*     */     }
/* 628 */     return f + f3;
/*     */   }
/*     */
/*     */   public boolean isFlyingAlone() {
/* 632 */     return false;
/*     */   }
/*     */
/*     */   public float getMoveSpeed() {
/* 636 */     return 0.7F;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public void performAnimation(int attackType) {}
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public boolean isMyFavoriteFood(ItemStack par1ItemStack) {
/* 655 */     return false;
/*     */   }
/*     */
/*     */
/*     */   private void followPlayer() {
/* 660 */     EntityPlayer entityplayer1 = this.world.getClosestPlayerToEntity((Entity)this, 24.0D);
/* 661 */     if (entityplayer1 == null) {
/*     */       return;
/*     */     }
/*     */
/* 665 */     ItemStack itemstack1 = entityplayer1.inventory.getCurrentItem();
/* 666 */     if (itemstack1 != null && isMyFavoriteFood(itemstack1)) {
/* 667 */       getNavigator().tryMoveToEntityLiving((Entity)entityplayer1, 1.0D);
/*     */     }
/*     */   }
/*     */
/*     */   public boolean isOnAir() {
/* 672 */     return (this.world.isAirBlock(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.posY - 0.2D),
/* 673 */           MathHelper.floor(this.posZ))) && this.world.isAirBlock(new BlockPos(MathHelper.floor(this.posX),
/* 674 */           MathHelper.floor(this.posY - 1.2D), MathHelper.floor(this.posZ))));
/*     */   }
/*     */
/*     */
/*     */   public float getSizeFactor() {
/* 679 */     return 1.0F;
/*     */   }
/*     */
/*     */
/*     */   public float getAdjustedYOffset() {
/* 684 */     return 0.0F;
/*     */   }
/*     */
/*     */   public boolean getIsRideable() {
/* 688 */     return false;
/*     */   }
/*     */
/*     */
/*     */   public void setRideable(boolean b) {}
/*     */
/*     */   public boolean entitiesToIgnore(Entity entity) {
/* 695 */     return (!(entity instanceof EntityLiving) || entity instanceof EntityMob || (entity instanceof EntityPlayer && getIsTamed()) || entity instanceof drzhark.mocreatures.entity.item.MoCEntityKittyBed || entity instanceof drzhark.mocreatures.entity.item.MoCEntityLitterBox || (
/*     */
/* 697 */       getIsTamed() && entity instanceof MoCEntityAnimal && ((MoCEntityAnimal)entity).getIsTamed()) || (entity instanceof net.minecraft.entity.passive.EntityWolf && !MoCreatures.proxy.attackWolves) || (entity instanceof drzhark.mocreatures.entity.passive.MoCEntityHorse && !MoCreatures.proxy.attackHorses) || (entity.width > this.width && entity.height > this.height) || entity instanceof drzhark.mocreatures.entity.item.MoCEntityEgg);
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public void setArmorType(int i) {}
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   protected EntityLivingBase getBoogey(double d) {
/* 714 */     EntityLivingBase entityliving = null;
/* 715 */     List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(d, 4.0D, d));
/* 716 */     for (int i = 0; i < list.size(); i++) {
/* 717 */       Entity entity = list.get(i);
/* 718 */       if (entitiesToInclude(entity)) {
/* 719 */         entityliving = (EntityLivingBase)entity;
/*     */       }
/*     */     }
/* 722 */     return entityliving;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public boolean entitiesToInclude(Entity entity) {
/* 732 */     return (entity instanceof EntityLivingBase && (entity.width >= 0.5D || entity.height >= 0.5D));
/*     */   }
/*     */
/*     */
/*     */   public float pitchRotationOffset() {
/* 737 */     return 0.0F;
/*     */   }
/*     */
/*     */
/*     */   public float rollRotationOffset() {
/* 742 */     return 0.0F;
/*     */   }
/*     */
/*     */
/*     */   public float yawRotationOffset() {
/* 747 */     return 0.0F;
/*     */   }
/*     */
/*     */
/*     */   public float getAdjustedZOffset() {
/* 752 */     return 0.0F;
/*     */   }
/*     */
/*     */
/*     */   public float getAdjustedXOffset() {
/* 757 */     return 0.0F;
/*     */   }
/*     */
/*     */   protected boolean canBeTrappedInNet() {
/* 761 */     return (this instanceof IMoCTameable && getIsTamed());
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
/*     */   public boolean isCreatureType(EnumCreatureType type, boolean forSpawnCount) {
/* 773 */     if (type == EnumCreatureType.AMBIENT) {
/* 774 */       return true;
/*     */     }
/* 776 */     return false;
/*     */   }
/*     */
/*     */
/*     */
/*     */   public boolean canAttackTarget(EntityLivingBase entity) {
/* 782 */     return false;
/*     */   }
/*     */
/*     */
/*     */   public boolean canBeLeashedTo(EntityPlayer player) {
/* 787 */     if (!this.world.isRemote && !MoCTools.isThisPlayerAnOP(player) && getIsTamed() && !player.getUniqueID().equals(getOwnerId())) {
/* 788 */       return false;
/*     */     }
/* 790 */     return super.canBeLeashedTo(player);
/*     */   }
/*     */
/*     */
/*     */   public boolean getIsSitting() {
/* 795 */     return false;
/*     */   }
/*     */
/*     */
/*     */   public boolean isNotScared() {
/* 800 */     return false;
/*     */   }
/*     */
/*     */
/*     */   public boolean isMovementCeased() {
/* 805 */     return getIsSitting();
/*     */   }
/*     */
/*     */
/*     */   public boolean shouldAttackPlayers() {
/* 810 */     return (this.world.getDifficulty() != EnumDifficulty.PEACEFUL);
/*     */   }
/*     */
/*     */
/*     */   protected boolean canTriggerWalking() {
/* 815 */     return false;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public double getDivingDepth() {
/* 824 */     return 0.5D;
/*     */   }
/*     */
/*     */
/*     */   public boolean isDiving() {
/* 829 */     return false;
/*     */   }
/*     */
/*     */
/*     */   public void forceEntityJump() {
/* 834 */     jump();
/*     */   }
/*     */
/*     */
/*     */
/*     */   public void fall(float f, float f1) {}
/*     */
/*     */
/*     */   public int minFlyingHeight() {
/* 843 */     return 2;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public int maxFlyingHeight() {
/* 852 */     return 4;
/*     */   }
/*     */
/*     */
/*     */   public void travel(float strafe, float vertical, float forward) {
/* 857 */     if (!getIsFlying()) {
/* 858 */       super.travel(strafe, vertical, forward);
/*     */       return;
/*     */     }
/* 861 */     moveEntityWithHeadingFlying(strafe, vertical, forward);
/*     */   }
/*     */
/*     */   public void moveEntityWithHeadingFlying(float strafe, float vertical, float forward) {
/* 865 */     if (isServerWorld()) {
/*     */
/* 867 */       moveRelative(strafe, vertical, forward, 0.1F);
/* 868 */       move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
/* 869 */       this.motionX *= 0.8999999761581421D;
/* 870 */       this.motionY *= 0.8999999761581421D;
/* 871 */       this.motionZ *= 0.8999999761581421D;
/*     */     } else {
/* 873 */       super.travel(strafe, vertical, forward);
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   public boolean getIsFlying() {
/* 879 */     return false;
/*     */   }
/*     */
/*     */
/*     */   public String getClazzString() {
/* 884 */     return EntityList.getEntityString((Entity)this);
/*     */   }
/*     */
/*     */
/*     */   public boolean getIsGhost() {
/* 889 */     return false;
/*     */   }
/*     */
/*     */
/*     */   public void setLeashHolder(Entity entityIn, boolean sendAttachNotification) {
/* 894 */     if (getIsTamed() && entityIn instanceof EntityPlayer) {
/* 895 */       EntityPlayer entityplayer = (EntityPlayer)entityIn;
/* 896 */       if (MoCreatures.proxy.enableOwnership && getOwnerId() != null &&
/* 897 */         !entityplayer.getUniqueID().equals(getOwnerId()) && !MoCTools.isThisPlayerAnOP(entityplayer)) {
/*     */         return;
/*     */       }
/*     */     }
/* 901 */     super.setLeashHolder(entityIn, sendAttachNotification);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\MoCEntityAmbient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
