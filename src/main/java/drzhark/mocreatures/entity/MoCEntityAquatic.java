/*      */ package drzhark.mocreatures.entity;
/*      */ 
/*      */ import drzhark.mocreatures.MoCEntityData;
/*      */ import drzhark.mocreatures.MoCTools;
/*      */ import drzhark.mocreatures.MoCreatures;
/*      */ import drzhark.mocreatures.entity.ai.EntityAIMoverHelperMoC;
/*      */ import java.util.List;
/*      */ import java.util.UUID;
/*      */ import javax.annotation.Nullable;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityCreature;
/*      */ import net.minecraft.entity.EntityList;
/*      */ import net.minecraft.entity.EntityLiving;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.EnumCreatureType;
/*      */ import net.minecraft.entity.IEntityLivingData;
/*      */ import net.minecraft.entity.MoverType;
/*      */ import net.minecraft.entity.SharedMonsterAttributes;
/*      */ import net.minecraft.entity.ai.EntityMoveHelper;
/*      */ import net.minecraft.entity.item.EntityItem;
/*      */ import net.minecraft.entity.monster.EntityMob;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.projectile.EntityFishHook;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.init.SoundEvents;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.network.datasync.DataParameter;
/*      */ import net.minecraft.network.datasync.DataSerializers;
/*      */ import net.minecraft.network.datasync.EntityDataManager;
/*      */ import net.minecraft.pathfinding.PathNavigate;
/*      */ import net.minecraft.pathfinding.PathNavigateSwimmer;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.SoundEvent;
/*      */ import net.minecraft.util.math.BlockPos;
/*      */ import net.minecraft.util.math.MathHelper;
/*      */ import net.minecraft.world.DifficultyInstance;
/*      */ import net.minecraft.world.EnumDifficulty;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraftforge.fml.relauncher.Side;
/*      */ import net.minecraftforge.fml.relauncher.SideOnly;
/*      */ 
/*      */ public abstract class MoCEntityAquatic
/*      */   extends EntityCreature
/*      */   implements IMoCEntity {
/*      */   protected boolean divePending;
/*      */   protected boolean jumpPending;
/*      */   protected boolean isEntityJumping;
/*      */   protected int outOfWater;
/*      */   private boolean diving;
/*      */   private int divingCount;
/*      */   private int mountCount;
/*      */   public boolean fishHooked;
/*      */   protected boolean riderIsDisconnecting;
/*      */   protected float moveSpeed;
/*      */   protected String texture;
/*      */   protected PathNavigate navigatorWater;
/*      */   private boolean updateDivingDepth = false;
/*      */   private double divingDepth;
/*      */   protected int temper;
/*   64 */   protected static final DataParameter<Boolean> ADULT = EntityDataManager.createKey(MoCEntityAquatic.class, DataSerializers.BOOLEAN);
/*   65 */   protected static final DataParameter<Integer> TYPE = EntityDataManager.createKey(MoCEntityAquatic.class, DataSerializers.VARINT);
/*   66 */   protected static final DataParameter<Integer> AGE = EntityDataManager.createKey(MoCEntityAquatic.class, DataSerializers.VARINT);
/*   67 */   protected static final DataParameter<String> NAME_STR = EntityDataManager.createKey(MoCEntityAquatic.class, DataSerializers.STRING);
/*      */   
/*      */   public MoCEntityAquatic(World world) {
/*   70 */     super(world);
/*   71 */     this.outOfWater = 0;
/*   72 */     setTemper(50);
/*   73 */     setNewDivingDepth();
/*   74 */     this.riderIsDisconnecting = false;
/*   75 */     this.texture = "blank.jpg";
/*   76 */     this.navigatorWater = (PathNavigate)new PathNavigateSwimmer((EntityLiving)this, world);
/*   77 */     this.moveHelper = (EntityMoveHelper)new EntityAIMoverHelperMoC((EntityLiving)this);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void applyEntityAttributes() {
/*   82 */     super.applyEntityAttributes();
/*   83 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getMoveSpeed());
/*   84 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
/*      */   }
/*      */ 
/*      */   
/*      */   public ResourceLocation getTexture() {
/*   89 */     return MoCreatures.proxy.getTexture(this.texture);
/*      */   }
/*      */ 
/*      */   
/*      */   public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData par1EntityLivingData) {
/*   94 */     selectType();
/*   95 */     return super.onInitialSpawn(difficulty, par1EntityLivingData);
/*      */   }
/*      */ 
/*      */   
/*      */   public void selectType() {
/*  100 */     setType(1);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void entityInit() {
/*  105 */     super.entityInit();
/*  106 */     this.dataManager.register(ADULT, Boolean.valueOf(false));
/*  107 */     this.dataManager.register(TYPE, Integer.valueOf(0));
/*  108 */     this.dataManager.register(AGE, Integer.valueOf(45));
/*  109 */     this.dataManager.register(NAME_STR, "");
/*      */   }
/*      */ 
/*      */   
/*      */   public void setType(int i) {
/*  114 */     this.dataManager.set(TYPE, Integer.valueOf(i));
/*      */   }
/*      */ 
/*      */   
/*      */   public int getType() {
/*  119 */     return ((Integer)this.dataManager.get(TYPE)).intValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getOwnerPetId() {
/*  124 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setOwnerPetId(int i) {}
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public UUID getOwnerId() {
/*  133 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getIsTamed() {
/*  138 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getIsAdult() {
/*  143 */     return ((Boolean)this.dataManager.get(ADULT)).booleanValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAdult(boolean flag) {
/*  148 */     this.dataManager.set(ADULT, Boolean.valueOf(flag));
/*      */   }
/*      */ 
/*      */   
/*      */   public String getPetName() {
/*  153 */     return (String)this.dataManager.get(NAME_STR);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getEdad() {
/*  158 */     return ((Integer)this.dataManager.get(AGE)).intValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setEdad(int i) {
/*  163 */     this.dataManager.set(AGE, Integer.valueOf(i));
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPetName(String name) {
/*  168 */     this.dataManager.set(NAME_STR, name);
/*      */   }
/*      */   
/*      */   public int getTemper() {
/*  172 */     return this.temper;
/*      */   }
/*      */   
/*      */   public void setTemper(int i) {
/*  176 */     this.temper = i;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxTemper() {
/*  184 */     return 100;
/*      */   }
/*      */   
/*      */   public float b(float f, float f1, float f2) {
/*  188 */     float f3 = f1;
/*  189 */     for (f3 = f1 - f; f3 < -180.0F; f3 += 360.0F);
/*      */     
/*  191 */     for (; f3 >= 180.0F; f3 -= 360.0F);
/*      */     
/*  193 */     if (f3 > f2) {
/*  194 */       f3 = f2;
/*      */     }
/*  196 */     if (f3 < -f2) {
/*  197 */       f3 = -f2;
/*      */     }
/*  199 */     return f + f3;
/*      */   }
/*      */   
/*      */   public void faceItem(int i, int j, int k, float f) {
/*  203 */     double d = i - this.posX;
/*  204 */     double d1 = k - this.posZ;
/*  205 */     double d2 = j - this.posY;
/*  206 */     double d3 = MathHelper.sqrt(d * d + d1 * d1);
/*  207 */     float f1 = (float)(Math.atan2(d1, d) * 180.0D / 3.141592741012573D) - 90.0F;
/*  208 */     float f2 = (float)(Math.atan2(d2, d3) * 180.0D / 3.141592741012573D);
/*  209 */     this.rotationPitch = -b(this.rotationPitch, f2, f);
/*  210 */     this.rotationYaw = b(this.rotationYaw, f1, f);
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean canDespawn() {
/*  215 */     if (MoCreatures.proxy.forceDespawns) {
/*  216 */       return !getIsTamed();
/*      */     }
/*  218 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean checkSpawningBiome() {
/*  224 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void playStepSound(BlockPos pos, Block par4) {}
/*      */ 
/*      */   
/*      */   public void fall(float f, float f1) {}
/*      */ 
/*      */   
/*      */   public EntityItem getClosestFish(Entity entity, double d) {
/*  236 */     double d1 = -1.0D;
/*  237 */     EntityItem entityitem = null;
/*  238 */     List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(d, d, d));
/*  239 */     for (int i = 0; i < list.size(); i++) {
/*  240 */       Entity entity1 = list.get(i);
/*  241 */       if (entity1 instanceof EntityItem) {
/*      */ 
/*      */         
/*  244 */         EntityItem entityitem1 = (EntityItem)entity1;
/*  245 */         if (entityitem1.getItem().getItem() == Items.FISH && entityitem1.isInWater()) {
/*      */ 
/*      */           
/*  248 */           double d2 = entityitem1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
/*  249 */           if ((d < 0.0D || d2 < d * d) && (d1 == -1.0D || d2 < d1)) {
/*  250 */             d1 = d2;
/*  251 */             entityitem = entityitem1;
/*      */           } 
/*      */         } 
/*      */       } 
/*  255 */     }  return entityitem;
/*      */   }
/*      */ 
/*      */   
/*      */   protected float getSoundVolume() {
/*  260 */     return 0.4F;
/*      */   }
/*      */   
/*      */   public boolean gettingOutOfWater() {
/*  264 */     int i = (int)this.posX;
/*  265 */     int j = (int)this.posY;
/*  266 */     int k = (int)this.posZ;
/*  267 */     return this.world.isAirBlock(new BlockPos(i, j + 1, k));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getCustomJump() {
/*  274 */     return 0.4D;
/*      */   }
/*      */   
/*      */   public void setIsJumping(boolean flag) {
/*  278 */     this.isEntityJumping = flag;
/*      */   }
/*      */   
/*      */   public boolean getIsJumping() {
/*  282 */     return this.isEntityJumping;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void makeEntityJump() {
/*  291 */     this.jumpPending = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean MoveToNextEntity(Entity entity) {
/*  300 */     if (entity != null) {
/*  301 */       int i = MathHelper.floor(entity.posX);
/*  302 */       int j = MathHelper.floor(entity.posY);
/*  303 */       int k = MathHelper.floor(entity.posZ);
/*  304 */       faceItem(i, j, k, 30.0F);
/*  305 */       if (this.posX < i) {
/*  306 */         double d = entity.posX - this.posX;
/*  307 */         if (d > 0.5D) {
/*  308 */           this.motionX += 0.05D;
/*      */         }
/*      */       } else {
/*  311 */         double d1 = this.posX - entity.posX;
/*  312 */         if (d1 > 0.5D) {
/*  313 */           this.motionX -= 0.05D;
/*      */         }
/*      */       } 
/*  316 */       if (this.posZ < k) {
/*  317 */         double d2 = entity.posZ - this.posZ;
/*  318 */         if (d2 > 0.5D) {
/*  319 */           this.motionZ += 0.05D;
/*      */         }
/*      */       } else {
/*  322 */         double d3 = this.posZ - entity.posZ;
/*  323 */         if (d3 > 0.5D) {
/*  324 */           this.motionZ -= 0.05D;
/*      */         }
/*      */       } 
/*  327 */       return true;
/*      */     } 
/*  329 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getCustomSpeed() {
/*  339 */     return 1.5D;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isInWater() {
/*  344 */     return this.world.handleMaterialAcceleration(getEntityBoundingBox().expand(0.0D, -0.2D, 0.0D), Material.WATER, (Entity)this);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canBreatheUnderwater() {
/*  349 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isDiving() {
/*  354 */     return this.diving;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void jump() {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void Riding() {
/*  364 */     if (isBeingRidden() && getRidingEntity() instanceof EntityPlayer) {
/*  365 */       EntityPlayer entityplayer = (EntityPlayer)getRidingEntity();
/*  366 */       List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(1.0D, 0.0D, 1.0D));
/*  367 */       if (list != null) {
/*  368 */         for (int i = 0; i < list.size(); i++) {
/*  369 */           Entity entity = list.get(i);
/*  370 */           if (!entity.isDead) {
/*      */ 
/*      */             
/*  373 */             entity.onCollideWithPlayer(entityplayer);
/*  374 */             if (entity instanceof EntityMob) {
/*      */ 
/*      */               
/*  377 */               float f = getDistance(entity);
/*  378 */               if (f < 2.0F && entity instanceof EntityMob && this.rand.nextInt(10) == 0) {
/*  379 */                 attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)entity), 
/*  380 */                     (float)((EntityMob)entity).getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isMovementCeased() {
/*  394 */     return ((!isSwimming() && !isBeingRidden()) || isBeingRidden() || getIsSitting());
/*      */   }
/*      */ 
/*      */   
/*      */   public void onLivingUpdate() {
/*  399 */     if (!this.world.isRemote) {
/*  400 */       if (isBeingRidden()) {
/*  401 */         Riding();
/*  402 */         this.mountCount = 1;
/*      */       } 
/*      */       
/*  405 */       if (this.mountCount > 0 && 
/*  406 */         ++this.mountCount > 50) {
/*  407 */         this.mountCount = 0;
/*      */       }
/*      */       
/*  410 */       if (getEdad() == 0) setEdad(getMaxEdad() - 10); 
/*  411 */       if (!getIsAdult() && this.rand.nextInt(300) == 0) {
/*  412 */         setEdad(getEdad() + 1);
/*  413 */         if (getEdad() >= getMaxEdad()) {
/*  414 */           setAdult(true);
/*      */         }
/*      */       } 
/*      */       
/*  418 */       getNavigator().onUpdateNavigation();
/*      */ 
/*      */       
/*  421 */       if (!getNavigator().noPath()) {
/*      */         
/*  423 */         if (!this.updateDivingDepth) {
/*      */           
/*  425 */           float targetDepth = MoCTools.distanceToSurface(this.moveHelper.getX(), this.moveHelper.getY(), this.moveHelper.getZ(), this.world);
/*  426 */           setNewDivingDepth(targetDepth);
/*  427 */           this.updateDivingDepth = true;
/*      */         } 
/*      */       } else {
/*  430 */         this.updateDivingDepth = false;
/*      */       } 
/*      */       
/*  433 */       if (isMovementCeased() || this.rand.nextInt(200) == 0) {
/*  434 */         getNavigator().clearPath();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  453 */       if (isFisheable() && !this.fishHooked && this.rand.nextInt(30) == 0) {
/*  454 */         getFished();
/*      */       }
/*      */       
/*  457 */       if (this.fishHooked && this.rand.nextInt(200) == 0) {
/*  458 */         this.fishHooked = false;
/*      */         
/*  460 */         List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(2.0D, 2.0D, 2.0D));
/*  461 */         for (int i = 0; i < list.size(); i++) {
/*  462 */           Entity entity1 = list.get(i);
/*      */           
/*  464 */           if (entity1 instanceof EntityFishHook && 
/*  465 */             ((EntityFishHook)entity1).caughtEntity == this) {
/*  466 */             ((EntityFishHook)entity1).caughtEntity = null;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  473 */     this.moveSpeed = getMoveSpeed();
/*      */     
/*  475 */     if (isSwimming()) {
/*      */       
/*  477 */       this.outOfWater = 0;
/*  478 */       setAir(800);
/*      */     } else {
/*  480 */       this.outOfWater++;
/*  481 */       this.motionY -= 0.1D;
/*  482 */       if (this.outOfWater > 20) {
/*  483 */         getNavigator().clearPath();
/*      */       }
/*  485 */       if (this.outOfWater > 300 && this.outOfWater % 40 == 0) {
/*  486 */         this.motionY += 0.3D;
/*  487 */         this.motionX = (float)(Math.random() * 0.2D - 0.1D);
/*  488 */         this.motionZ = (float)(Math.random() * 0.2D - 0.1D);
/*  489 */         attackEntityFrom(DamageSource.DROWN, 1.0F);
/*      */       } 
/*      */     } 
/*  492 */     if (!this.diving) {
/*  493 */       if (!isBeingRidden() && getAttackTarget() == null && !this.navigator.noPath() && this.rand.nextInt(500) == 0) {
/*  494 */         this.diving = true;
/*      */       }
/*      */     } else {
/*  497 */       this.divingCount++;
/*  498 */       if (this.divingCount > 100 || isBeingRidden()) {
/*  499 */         this.diving = false;
/*  500 */         this.divingCount = 0;
/*      */       } 
/*      */     } 
/*  503 */     super.onLivingUpdate();
/*      */   }
/*      */   
/*      */   public boolean isSwimming() {
/*  507 */     return isInsideOfMaterial(Material.WATER);
/*      */   }
/*      */ 
/*      */   
/*      */   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
/*  512 */     super.writeEntityToNBT(nbttagcompound);
/*      */     
/*  514 */     nbttagcompound.setBoolean("Adult", getIsAdult());
/*  515 */     nbttagcompound.setInteger("Edad", getEdad());
/*  516 */     nbttagcompound.setString("Name", getPetName());
/*  517 */     nbttagcompound.setInteger("TypeInt", getType());
/*      */   }
/*      */ 
/*      */   
/*      */   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
/*  522 */     super.readEntityFromNBT(nbttagcompound);
/*      */     
/*  524 */     setAdult(nbttagcompound.getBoolean("Adult"));
/*  525 */     setEdad(nbttagcompound.getInteger("Edad"));
/*  526 */     setPetName(nbttagcompound.getString("Name"));
/*  527 */     setType(nbttagcompound.getInteger("TypeInt"));
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDisplayName(boolean flag) {}
/*      */   
/*      */   public void setTypeInt(int i) {
/*  534 */     setType(i);
/*  535 */     selectType();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void performAnimation(int attackType) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void despawnEntity() {
/*  553 */     EntityPlayer var1 = this.world.getClosestPlayerToEntity((Entity)this, -1.0D);
/*  554 */     if (var1 != null) {
/*  555 */       double var2 = var1.posX - this.posX;
/*  556 */       double var4 = var1.posY - this.posY;
/*  557 */       double var6 = var1.posZ - this.posZ;
/*  558 */       double var8 = var2 * var2 + var4 * var4 + var6 * var6;
/*      */       
/*  560 */       if (canDespawn() && var8 > 16384.0D) {
/*  561 */         setDead();
/*      */       }
/*      */       
/*  564 */       if (this.idleTime > 1800 && this.rand.nextInt(800) == 0 && var8 > 1024.0D && canDespawn()) {
/*  565 */         setDead();
/*  566 */       } else if (var8 < 1024.0D) {
/*  567 */         this.idleTime = 0;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public float getMoveSpeed() {
/*  573 */     return 0.7F;
/*      */   }
/*      */ 
/*      */   
/*      */   public int nameYOffset() {
/*  578 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean renderName() {
/*  583 */     return (MoCreatures.proxy.getDisplayPetName() && 
/*  584 */       getPetName() != null && !getPetName().equals("") && !isBeingRidden() && getRidingEntity() == null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void makeEntityDive() {
/*  599 */     this.divePending = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getSizeFactor() {
/*  604 */     return 1.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getAdjustedYOffset() {
/*  609 */     return 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getCanSpawnHere() {
/*  618 */     return (((MoCEntityData)MoCreatures.entityMap.get(getClass())).getFrequency() > 0 && this.world.checkNoEntityCollision(getEntityBoundingBox()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/*  625 */     if (usesNewAI()) {
/*  626 */       return super.attackEntityFrom(damagesource, i);
/*      */     }
/*      */     
/*  629 */     if (isNotScared()) {
/*  630 */       EntityLivingBase entityLivingBase = getAttackTarget();
/*  631 */       setAttackTarget(entityLivingBase);
/*  632 */       return super.attackEntityFrom(damagesource, i);
/*      */     } 
/*      */     
/*  635 */     return super.attackEntityFrom(damagesource, i);
/*      */   }
/*      */   
/*      */   protected boolean canBeTrappedInNet() {
/*  639 */     return (this instanceof IMoCTameable && getIsTamed());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void dropMyStuff() {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isMyHealFood(ItemStack itemstack) {
/*  652 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setArmorType(int i) {}
/*      */ 
/*      */   
/*      */   public float pitchRotationOffset() {
/*  661 */     return 0.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   public float rollRotationOffset() {
/*  666 */     return 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void getFished() {
/*  673 */     EntityPlayer entityplayer1 = this.world.getClosestPlayerToEntity((Entity)this, 18.0D);
/*  674 */     if (entityplayer1 != null) {
/*  675 */       EntityFishHook fishHook = entityplayer1.fishEntity;
/*  676 */       if (fishHook != null && fishHook.caughtEntity == null) {
/*  677 */         float f = fishHook.getDistance((Entity)this);
/*  678 */         if (f > 1.0F) {
/*  679 */           MoCTools.getPathToEntity((EntityLiving)this, (Entity)fishHook, f);
/*      */         } else {
/*  681 */           fishHook.caughtEntity = (Entity)this;
/*  682 */           this.fishHooked = true;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isFisheable() {
/*  694 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getAdjustedZOffset() {
/*  699 */     return 0.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getAdjustedXOffset() {
/*  704 */     return 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EntityLivingBase getBoogey(double d) {
/*  714 */     EntityLivingBase entityliving = null;
/*  715 */     List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(d, 4.0D, d));
/*  716 */     for (int i = 0; i < list.size(); i++) {
/*  717 */       Entity entity = list.get(i);
/*  718 */       if (entitiesToInclude(entity)) {
/*  719 */         entityliving = (EntityLivingBase)entity;
/*      */       }
/*      */     } 
/*  722 */     return entityliving;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean entitiesToInclude(Entity entity) {
/*  732 */     return (entity.getClass() != getClass() && entity instanceof EntityLivingBase && (entity.width >= 0.5D || entity.height >= 0.5D));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isNotScared() {
/*  737 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canAttackTarget(EntityLivingBase entity) {
/*  742 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canBeLeashedTo(EntityPlayer player) {
/*  747 */     if (!this.world.isRemote && !MoCTools.isThisPlayerAnOP(player) && getIsTamed() && !player.getUniqueID().equals(getOwnerId())) {
/*  748 */       return false;
/*      */     }
/*  750 */     return super.canBeLeashedTo(player);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getIsSitting() {
/*  755 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean shouldAttackPlayers() {
/*  760 */     return (!getIsTamed() && this.world.getDifficulty() != EnumDifficulty.PEACEFUL);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void travel(float strafe, float vertical, float forward) {
/*  768 */     if (isInWater()) {
/*  769 */       if (isBeingRidden()) {
/*  770 */         EntityLivingBase passenger = (EntityLivingBase)getControllingPassenger();
/*  771 */         if (passenger != null) moveWithRider(strafe, vertical, forward, passenger); 
/*      */         return;
/*      */       } 
/*  774 */       moveRelative(strafe, vertical, forward, 0.1F);
/*  775 */       move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
/*  776 */       this.motionX *= 0.8999999761581421D;
/*  777 */       this.motionY *= 0.8999999761581421D;
/*  778 */       this.motionZ *= 0.8999999761581421D;
/*      */       
/*  780 */       if (getAttackTarget() == null) {
/*  781 */         this.motionY -= 0.005D;
/*      */       }
/*  783 */       this.prevLimbSwingAmount = this.limbSwingAmount;
/*  784 */       double d2 = this.posX - this.prevPosX;
/*  785 */       double d3 = this.posZ - this.prevPosZ;
/*  786 */       float f7 = MathHelper.sqrt(d2 * d2 + d3 * d3) * 4.0F;
/*      */       
/*  788 */       if (f7 > 1.0F)
/*      */       {
/*  790 */         f7 = 1.0F;
/*      */       }
/*      */       
/*  793 */       this.limbSwingAmount += (f7 - this.limbSwingAmount) * 0.4F;
/*  794 */       this.limbSwing += this.limbSwingAmount;
/*      */     } else {
/*  796 */       super.travel(strafe, vertical, forward);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void moveWithRider(float strafe, float vertical, float forward, EntityLivingBase passenger) {
/*  806 */     if (passenger == null) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  811 */     if (isBeingRidden() && !getIsTamed() && !isSwimming()) {
/*  812 */       removePassengers();
/*      */       
/*      */       return;
/*      */     } 
/*  816 */     if (isBeingRidden() && !getIsTamed() && passenger instanceof EntityLivingBase) {
/*  817 */       moveWithRiderUntamed(strafe, vertical, forward, passenger);
/*      */       
/*      */       return;
/*      */     } 
/*  821 */     if (isBeingRidden() && getIsTamed() && passenger instanceof EntityLivingBase) {
/*  822 */       this.prevRotationYaw = this.rotationYaw = passenger.rotationYaw;
/*  823 */       this.rotationPitch = passenger.rotationPitch * 0.5F;
/*  824 */       setRotation(this.rotationYaw, this.rotationPitch);
/*  825 */       this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
/*  826 */       strafe = passenger.moveStrafing * 0.35F;
/*  827 */       forward = passenger.moveForward * (float)(getCustomSpeed() / 5.0D);
/*  828 */       if (this.jumpPending) {
/*  829 */         if (isSwimming()) {
/*  830 */           this.motionY += getCustomJump();
/*      */         }
/*  832 */         this.jumpPending = false;
/*      */       } 
/*      */       
/*  835 */       if (this.motionY < 0.0D && isSwimming()) {
/*  836 */         this.motionY = 0.0D;
/*      */       }
/*  838 */       if (this.divePending) {
/*  839 */         this.divePending = false;
/*  840 */         this.motionY -= 0.3D;
/*      */       } 
/*  842 */       setAIMoveSpeed((float)getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
/*  843 */       super.travel(strafe, vertical, forward);
/*  844 */       moveRelative(strafe, vertical, forward, 0.1F);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void moveWithRiderUntamed(float strafe, float vertical, float forward, EntityLivingBase passenger) {
/*  850 */     if (isBeingRidden() && !getIsTamed()) {
/*  851 */       if (this.rand.nextInt(5) == 0 && !getIsJumping() && this.jumpPending) {
/*  852 */         this.motionY += getCustomJump();
/*  853 */         setIsJumping(true);
/*  854 */         this.jumpPending = false;
/*      */       } 
/*  856 */       if (this.rand.nextInt(10) == 0) {
/*  857 */         this.motionX += this.rand.nextDouble() / 30.0D;
/*  858 */         this.motionZ += this.rand.nextDouble() / 10.0D;
/*      */       } 
/*      */       
/*  861 */       move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
/*      */       
/*  863 */       if (!this.world.isRemote && this.rand.nextInt(100) == 0) {
/*  864 */         passenger.motionY += 0.9D;
/*  865 */         passenger.motionZ -= 0.3D;
/*  866 */         passenger.dismountRidingEntity();
/*      */       } 
/*  868 */       if (this.onGround) {
/*  869 */         setIsJumping(false);
/*      */       }
/*  871 */       if (!this.world.isRemote && this instanceof IMoCTameable && passenger instanceof EntityPlayer) {
/*  872 */         int chance = getMaxTemper() - getTemper();
/*  873 */         if (chance <= 0) {
/*  874 */           chance = 1;
/*      */         }
/*  876 */         if (this.rand.nextInt(chance * 8) == 0) {
/*  877 */           MoCTools.tameWithName((EntityPlayer)passenger, (IMoCTameable)this);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isNotColliding() {
/*  889 */     return this.world.checkNoEntityCollision(getEntityBoundingBox(), (Entity)this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTalkInterval() {
/*  897 */     return 300;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getExperiencePoints(EntityPlayer player) {
/*  905 */     return 1 + this.world.rand.nextInt(3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onEntityUpdate() {
/*  913 */     int i = getAir();
/*  914 */     super.onEntityUpdate();
/*      */     
/*  916 */     if (isEntityAlive() && !isInWater()) {
/*  917 */       i--;
/*  918 */       setAir(i);
/*      */       
/*  920 */       if (getAir() == -30) {
/*  921 */         setAir(0);
/*  922 */         attackEntityFrom(DamageSource.DROWN, 1.0F);
/*  923 */         this.motionX += this.rand.nextDouble() / 10.0D;
/*  924 */         this.motionZ += this.rand.nextDouble() / 10.0D;
/*      */       } 
/*      */     } else {
/*  927 */       setAir(300);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isPushedByWater() {
/*  933 */     return false;
/*      */   }
/*      */   
/*      */   protected boolean usesNewAI() {
/*  937 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public PathNavigate getNavigator() {
/*  942 */     if (isInWater()) {
/*  943 */       return this.navigatorWater;
/*      */     }
/*  945 */     return this.navigator;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getDivingDepth() {
/*  954 */     return (float)this.divingDepth;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setNewDivingDepth(double setDepth) {
/*  962 */     if (setDepth != 0.0D) {
/*  963 */       if (setDepth > maxDivingDepth()) {
/*  964 */         setDepth = maxDivingDepth();
/*      */       }
/*  966 */       if (setDepth < minDivingDepth()) {
/*  967 */         setDepth = minDivingDepth();
/*      */       }
/*  969 */       this.divingDepth = setDepth;
/*      */     } else {
/*  971 */       this.divingDepth = (float)(this.rand.nextDouble() * (maxDivingDepth() - minDivingDepth()) + minDivingDepth());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void setNewDivingDepth() {
/*  977 */     setNewDivingDepth(0.0D);
/*      */   }
/*      */   
/*      */   protected double minDivingDepth() {
/*  981 */     return 0.2D;
/*      */   }
/*      */   
/*      */   protected double maxDivingDepth() {
/*  985 */     return 3.0D;
/*      */   }
/*      */ 
/*      */   
/*      */   public void forceEntityJump() {
/*  990 */     jump();
/*      */   }
/*      */ 
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public float yawRotationOffset() {
/*  996 */     double d4 = 0.0D;
/*  997 */     if (this.motionX != 0.0D || this.motionZ != 0.0D) {
/*  998 */       d4 = Math.sin(this.ticksExisted * 0.5D) * 8.0D;
/*      */     }
/* 1000 */     return (float)d4;
/*      */   }
/*      */   
/*      */   public int getMaxEdad() {
/* 1004 */     return 100;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean attackEntityAsMob(Entity entityIn) {
/* 1009 */     if (!entityIn.isInWater()) {
/* 1010 */       return false;
/*      */     }
/*      */     
/* 1013 */     boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), 
/* 1014 */         (int)getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
/* 1015 */     if (flag) {
/* 1016 */       applyEnchantments((EntityLivingBase)this, entityIn);
/*      */     }
/* 1018 */     return flag;
/*      */   }
/*      */ 
/*      */   
/*      */   public int maxFlyingHeight() {
/* 1023 */     return 1;
/*      */   }
/*      */ 
/*      */   
/*      */   public int minFlyingHeight() {
/* 1028 */     return 1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isFlyer() {
/* 1035 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getIsFlying() {
/* 1040 */     return false;
/*      */   }
/*      */   
/*      */   protected SoundEvent getUpsetSound() {
/* 1044 */     return SoundEvents.ENTITY_GENERIC_HURT;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isCreatureType(EnumCreatureType type, boolean forSpawnCount) {
/* 1056 */     if (type == EnumCreatureType.WATER_CREATURE) {
/* 1057 */       return true;
/*      */     }
/* 1059 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public Entity getControllingPassenger() {
/* 1070 */     return getPassengers().isEmpty() ? null : getPassengers().get(0);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getClazzString() {
/* 1075 */     return EntityList.getEntityString((Entity)this);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getIsGhost() {
/* 1080 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLeashHolder(Entity entityIn, boolean sendAttachNotification) {
/* 1085 */     if (getIsTamed() && entityIn instanceof EntityPlayer) {
/* 1086 */       EntityPlayer entityplayer = (EntityPlayer)entityIn;
/* 1087 */       if (MoCreatures.proxy.enableOwnership && getOwnerId() != null && 
/* 1088 */         !entityplayer.getUniqueID().equals(getOwnerId()) && !MoCTools.isThisPlayerAnOP(entityplayer)) {
/*      */         return;
/*      */       }
/*      */     } 
/* 1092 */     super.setLeashHolder(entityIn, sendAttachNotification);
/*      */   }
/*      */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\MoCEntityAquatic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */