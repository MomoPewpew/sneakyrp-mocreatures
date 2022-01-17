/*     */ package drzhark.mocreatures.entity;
/*     */ 
/*     */ import drzhark.mocreatures.MoCEntityData;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIMoverHelperMoC;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import drzhark.mocreatures.entity.ai.PathNavigateFlyer;
/*     */ import drzhark.mocreatures.network.MoCMessageHandler;
/*     */ import drzhark.mocreatures.network.message.MoCMessageHealth;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityMoveHelper;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.pathfinding.PathNavigateSwimmer;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.EnumSkyBlock;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class MoCEntityMob
/*     */   extends EntityMob
/*     */   implements IMoCEntity
/*     */ {
/*     */   protected boolean divePending;
/*     */   protected int maxHealth;
/*     */   protected float moveSpeed;
/*     */   protected String texture;
/*     */   protected PathNavigate navigatorWater;
/*     */   protected PathNavigate navigatorFlyer;
/*     */   protected EntityAIWanderMoC2 wander;
/*  57 */   protected static final DataParameter<Boolean> ADULT = EntityDataManager.createKey(EntityCreature.class, DataSerializers.BOOLEAN);
/*  58 */   protected static final DataParameter<Integer> TYPE = EntityDataManager.createKey(EntityCreature.class, DataSerializers.VARINT);
/*  59 */   protected static final DataParameter<Integer> AGE = EntityDataManager.createKey(EntityCreature.class, DataSerializers.VARINT);
/*  60 */   protected static final DataParameter<String> NAME_STR = EntityDataManager.createKey(EntityCreature.class, DataSerializers.STRING);
/*     */   
/*     */   public MoCEntityMob(World world) {
/*  63 */     super(world);
/*  64 */     this.texture = "blank.jpg";
/*  65 */     this.moveHelper = (EntityMoveHelper)new EntityAIMoverHelperMoC((EntityLiving)this);
/*  66 */     this.navigatorWater = (PathNavigate)new PathNavigateSwimmer((EntityLiving)this, world);
/*  67 */     this.navigatorFlyer = (PathNavigate)new PathNavigateFlyer((EntityLiving)this, world);
/*  68 */     this.tasks.addTask(4, (EntityAIBase)(this.wander = new EntityAIWanderMoC2((EntityCreature)this, 1.0D, 80)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  73 */     super.applyEntityAttributes();
/*  74 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getMoveSpeed());
/*  75 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(getAttackStrenght());
/*  76 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData par1EntityLivingData) {
/*  81 */     selectType();
/*  82 */     return super.onInitialSpawn(difficulty, par1EntityLivingData);
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  87 */     return MoCreatures.proxy.getTexture(this.texture);
/*     */   }
/*     */   
/*     */   protected double getAttackStrenght() {
/*  91 */     return 2.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void selectType() {
/* 100 */     setType(1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/* 105 */     super.entityInit();
/* 106 */     this.dataManager.register(ADULT, Boolean.valueOf(false));
/* 107 */     this.dataManager.register(TYPE, Integer.valueOf(0));
/* 108 */     this.dataManager.register(AGE, Integer.valueOf(45));
/* 109 */     this.dataManager.register(NAME_STR, "");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setType(int i) {
/* 114 */     this.dataManager.set(TYPE, Integer.valueOf(i));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getType() {
/* 119 */     return ((Integer)this.dataManager.get(TYPE)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getIsAdult() {
/* 124 */     return ((Boolean)this.dataManager.get(ADULT)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAdult(boolean flag) {
/* 129 */     this.dataManager.set(ADULT, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getIsTamed() {
/* 134 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPetName() {
/* 139 */     return ((String)this.dataManager.get(NAME_STR)).toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEdad() {
/* 144 */     return ((Integer)this.dataManager.get(AGE)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public UUID getOwnerId() {
/* 150 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOwnerUniqueId(@Nullable UUID uniqueId) {}
/*     */ 
/*     */   
/*     */   public int getOwnerPetId() {
/* 158 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOwnerPetId(int petId) {}
/*     */ 
/*     */   
/*     */   public void setEdad(int i) {
/* 167 */     this.dataManager.set(AGE, Integer.valueOf(i));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPetName(String name) {
/* 172 */     this.dataManager.set(NAME_STR, String.valueOf(name));
/*     */   }
/*     */   
/*     */   public boolean getCanSpawnHereLiving() {
/* 176 */     return (this.world.checkNoEntityCollision(getEntityBoundingBox()) && this.world
/* 177 */       .getCollisionBoxes((Entity)this, getEntityBoundingBox()).size() == 0 && 
/* 178 */       !this.world.containsAnyLiquid(getEntityBoundingBox()));
/*     */   }
/*     */   
/*     */   public boolean getCanSpawnHereCreature() {
/* 182 */     int i = MathHelper.floor(this.posX);
/* 183 */     int j = MathHelper.floor((getEntityBoundingBox()).minY);
/* 184 */     int k = MathHelper.floor(this.posZ);
/* 185 */     return (getBlockPathWeight(new BlockPos(i, j, k)) >= 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getCanSpawnHere() {
/* 190 */     return (((MoCEntityData)MoCreatures.entityMap.get(getClass())).getFrequency() > 0 && super.getCanSpawnHere());
/*     */   }
/*     */   
/*     */   public boolean getCanSpawnHereMob() {
/* 194 */     int i = MathHelper.floor(this.posX);
/* 195 */     int j = MathHelper.floor((getEntityBoundingBox()).minY);
/* 196 */     int k = MathHelper.floor(this.posZ);
/* 197 */     BlockPos pos = new BlockPos(i, j, k);
/* 198 */     if (this.world.getLightFor(EnumSkyBlock.SKY, pos) > this.rand.nextInt(32)) {
/* 199 */       return false;
/*     */     }
/* 201 */     int l = this.world.getLightFromNeighbors(pos);
/* 202 */     if (this.world.isThundering()) {
/* 203 */       int i1 = this.world.getSkylightSubtracted();
/* 204 */       this.world.setSkylightSubtracted(10);
/* 205 */       l = this.world.getLightFromNeighbors(pos);
/* 206 */       this.world.setSkylightSubtracted(i1);
/*     */     } 
/* 208 */     return (l <= this.rand.nextInt(8));
/*     */   }
/*     */ 
/*     */   
/*     */   protected EntityLivingBase getClosestEntityLiving(Entity entity, double d) {
/* 213 */     double d1 = -1.0D;
/* 214 */     EntityLivingBase entityliving = null;
/* 215 */     List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(d, d, d));
/* 216 */     for (int i = 0; i < list.size(); i++) {
/* 217 */       Entity entity1 = list.get(i);
/*     */       
/* 219 */       if (!entitiesToIgnore(entity1)) {
/*     */ 
/*     */         
/* 222 */         double d2 = entity1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
/* 223 */         if ((d < 0.0D || d2 < d * d) && (d1 == -1.0D || d2 < d1) && ((EntityLivingBase)entity1).canEntityBeSeen(entity)) {
/* 224 */           d1 = d2;
/* 225 */           entityliving = (EntityLivingBase)entity1;
/*     */         } 
/*     */       } 
/*     */     } 
/* 229 */     return entityliving;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean entitiesToIgnore(Entity entity) {
/* 234 */     return (!(entity instanceof EntityLiving) || entity instanceof EntityMob || entity instanceof drzhark.mocreatures.entity.item.MoCEntityEgg || (entity instanceof net.minecraft.entity.player.EntityPlayer && 
/* 235 */       getIsTamed()) || entity instanceof drzhark.mocreatures.entity.item.MoCEntityKittyBed || entity instanceof drzhark.mocreatures.entity.item.MoCEntityLitterBox || (
/*     */       
/* 237 */       getIsTamed() && entity instanceof MoCEntityAnimal && ((MoCEntityAnimal)entity).getIsTamed()) || (entity instanceof net.minecraft.entity.passive.EntityWolf && !MoCreatures.proxy.attackWolves) || (entity instanceof drzhark.mocreatures.entity.passive.MoCEntityHorse && !MoCreatures.proxy.attackHorses));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkSpawningBiome() {
/* 243 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 248 */     if (!this.world.isRemote) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 254 */       if (getIsTamed() && this.rand.nextInt(200) == 0) {
/* 255 */         MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageHealth(getEntityId(), getHealth()), new NetworkRegistry.TargetPoint(this.world.provider
/* 256 */               .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */       }
/*     */       
/* 259 */       if (isHarmedByDaylight() && 
/* 260 */         this.world.isDaytime()) {
/* 261 */         float var1 = getBrightness();
/* 262 */         if (var1 > 0.5F && this.world
/* 263 */           .canBlockSeeSky(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.posY), 
/* 264 */               MathHelper.floor(this.posZ))) && this.rand.nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F) {
/* 265 */           setFire(8);
/*     */         }
/*     */       } 
/*     */       
/* 269 */       if (getEdad() == 0) setEdad(getMaxEdad() - 10); 
/* 270 */       if (!getIsAdult() && this.rand.nextInt(300) == 0) {
/* 271 */         setEdad(getEdad() + 1);
/* 272 */         if (getEdad() >= getMaxEdad()) {
/* 273 */           setAdult(true);
/*     */         }
/*     */       } 
/*     */       
/* 277 */       if (getIsFlying() && getNavigator().noPath() && !isMovementCeased() && getAttackTarget() == null && this.rand.nextInt(20) == 0) {
/* 278 */         this.wander.makeUpdate();
/*     */       }
/*     */     } 
/*     */     
/* 282 */     getNavigator().onUpdateNavigation();
/* 283 */     super.onLivingUpdate();
/*     */   }
/*     */   
/*     */   protected int getMaxEdad() {
/* 287 */     return 100;
/*     */   }
/*     */   
/*     */   protected boolean isHarmedByDaylight() {
/* 291 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/* 296 */     if (!this.world.isRemote && getIsTamed()) {
/* 297 */       MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageHealth(getEntityId(), getHealth()), new NetworkRegistry.TargetPoint(this.world.provider
/* 298 */             .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */     }
/* 300 */     return super.attackEntityFrom(damagesource, i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFlyer() {
/* 309 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
/* 314 */     super.writeEntityToNBT(nbttagcompound);
/*     */     
/* 316 */     nbttagcompound.setBoolean("Adult", getIsAdult());
/* 317 */     nbttagcompound.setInteger("Edad", getEdad());
/* 318 */     nbttagcompound.setString("Name", getPetName());
/* 319 */     nbttagcompound.setInteger("TypeInt", getType());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
/* 325 */     super.readEntityFromNBT(nbttagcompound);
/*     */     
/* 327 */     setAdult(nbttagcompound.getBoolean("Adult"));
/* 328 */     setEdad(nbttagcompound.getInteger("Edad"));
/* 329 */     setPetName(nbttagcompound.getString("Name"));
/* 330 */     setType(nbttagcompound.getInteger("TypeInt"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fall(float f, float f1) {
/* 336 */     if (!isFlyer()) {
/* 337 */       super.fall(f, f1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOnLadder() {
/* 343 */     if (isFlyer()) {
/* 344 */       return false;
/*     */     }
/* 346 */     return super.isOnLadder();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void travel(float strafe, float vertical, float forward) {
/* 352 */     if (!isFlyer()) {
/* 353 */       super.travel(strafe, vertical, forward);
/*     */       return;
/*     */     } 
/* 356 */     moveEntityWithHeadingFlyer(strafe, vertical, forward);
/*     */   }
/*     */   
/*     */   public void moveEntityWithHeadingFlyer(float strafe, float vertical, float forward) {
/* 360 */     if (isServerWorld()) {
/*     */       
/* 362 */       moveRelative(strafe, vertical, forward, 0.1F);
/* 363 */       move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
/* 364 */       this.motionX *= 0.8999999761581421D;
/* 365 */       this.motionY *= 0.8999999761581421D;
/* 366 */       this.motionZ *= 0.8999999761581421D;
/*     */     } else {
/* 368 */       super.travel(strafe, vertical, forward);
/*     */     } 
/*     */   }
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
/*     */   public float getMoveSpeed() {
/* 384 */     return 0.7F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int nameYOffset() {
/* 390 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean renderName() {
/* 395 */     return (MoCreatures.proxy.getDisplayPetName() && 
/* 396 */       getPetName() != null && !getPetName().equals("") && !isBeingRidden() && getRidingEntity() == null);
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
/*     */   public void makeEntityJump() {}
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
/*     */   public void makeEntityDive() {
/* 423 */     this.divePending = true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canDespawn() {
/* 428 */     return !getIsTamed();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDead() {
/* 434 */     if (!this.world.isRemote && getIsTamed() && getHealth() > 0.0F) {
/*     */       return;
/*     */     }
/* 437 */     super.setDead();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getSizeFactor() {
/* 442 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAdjustedYOffset() {
/* 447 */     return 0.0F;
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
/*     */   
/*     */   public void setArmorType(int i) {}
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
/*     */   public int getArmorType() {
/* 478 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public float pitchRotationOffset() {
/* 483 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float rollRotationOffset() {
/* 488 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float yawRotationOffset() {
/* 493 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAdjustedZOffset() {
/* 498 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAdjustedXOffset() {
/* 503 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canAttackTarget(EntityLivingBase entity) {
/* 508 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getIsSitting() {
/* 513 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNotScared() {
/* 518 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMovementCeased() {
/* 523 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldAttackPlayers() {
/* 528 */     return (this.world.getDifficulty() != EnumDifficulty.PEACEFUL);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDivingDepth() {
/* 533 */     return 0.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDiving() {
/* 538 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void forceEntityJump() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityAsMob(Entity entityIn) {
/* 548 */     boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), 
/* 549 */         (int)getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
/* 550 */     if (flag) {
/* 551 */       applyEnchantments((EntityLivingBase)this, entityIn);
/*     */     }
/*     */     
/* 554 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public int maxFlyingHeight() {
/* 559 */     return 5;
/*     */   }
/*     */ 
/*     */   
/*     */   public int minFlyingHeight() {
/* 564 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public PathNavigate getNavigator() {
/* 569 */     if (isInWater() && isAmphibian()) {
/* 570 */       return this.navigatorWater;
/*     */     }
/* 572 */     if (isFlyer()) {
/* 573 */       return this.navigatorFlyer;
/*     */     }
/* 575 */     return this.navigator;
/*     */   }
/*     */   
/*     */   public boolean isAmphibian() {
/* 579 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getIsFlying() {
/* 584 */     return isFlyer();
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
/* 596 */     if (type == EnumCreatureType.MONSTER) {
/* 597 */       return true;
/*     */     }
/* 599 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getClazzString() {
/* 605 */     return EntityList.getEntityString((Entity)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getIsGhost() {
/* 610 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\MoCEntityMob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */