/*      */ package drzhark.mocreatures.entity;
/*      */ 
/*      */ import drzhark.mocreatures.MoCEntityData;
/*      */ import drzhark.mocreatures.MoCTools;
/*      */ import drzhark.mocreatures.MoCreatures;
/*      */ import drzhark.mocreatures.entity.ai.EntityAIMoverHelperMoC;
/*      */ import drzhark.mocreatures.entity.ai.PathNavigateFlyer;
/*      */ import drzhark.mocreatures.init.MoCBlocks;
/*      */ import java.util.List;
/*      */ import java.util.UUID;
/*      */ import javax.annotation.Nullable;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.block.state.IBlockState;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityAgeable;
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
/*      */ import net.minecraft.entity.passive.EntityAnimal;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.network.datasync.DataParameter;
/*      */ import net.minecraft.network.datasync.DataSerializers;
/*      */ import net.minecraft.network.datasync.EntityDataManager;
/*      */ import net.minecraft.pathfinding.Path;
/*      */ import net.minecraft.pathfinding.PathNavigate;
/*      */ import net.minecraft.pathfinding.PathNavigateSwimmer;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.SoundEvent;
/*      */ import net.minecraft.util.math.BlockPos;
/*      */ import net.minecraft.util.math.MathHelper;
/*      */ import net.minecraft.world.DifficultyInstance;
/*      */ import net.minecraft.world.EnumDifficulty;
/*      */ import net.minecraft.world.IBlockAccess;
/*      */ import net.minecraft.world.World;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class MoCEntityAnimal
/*      */   extends EntityAnimal
/*      */   implements IMoCEntity
/*      */ {
/*      */   protected boolean divePending;
/*      */   protected boolean jumpPending;
/*      */   protected int temper;
/*      */   protected boolean isEntityJumping;
/*      */   protected boolean riderIsDisconnecting;
/*      */   public float moveSpeed;
/*      */   protected String texture;
/*      */   private int huntingCounter;
/*      */   protected boolean isTameable;
/*      */   protected PathNavigate navigatorWater;
/*      */   protected PathNavigate navigatorFlyer;
/*      */   private double divingDepth;
/*      */   private boolean randomAttributesUpdated;
/*   70 */   protected static final DataParameter<Boolean> ADULT = EntityDataManager.createKey(MoCEntityAnimal.class, DataSerializers.BOOLEAN);
/*   71 */   protected static final DataParameter<Integer> TYPE = EntityDataManager.createKey(MoCEntityAnimal.class, DataSerializers.VARINT);
/*   72 */   protected static final DataParameter<Integer> AGE = EntityDataManager.createKey(MoCEntityAnimal.class, DataSerializers.VARINT);
/*   73 */   protected static final DataParameter<String> NAME_STR = EntityDataManager.createKey(MoCEntityAnimal.class, DataSerializers.STRING);
/*      */   
/*      */   public MoCEntityAnimal(World world) {
/*   76 */     super(world);
/*   77 */     this.riderIsDisconnecting = false;
/*   78 */     this.isTameable = false;
/*   79 */     this.texture = "blank.jpg";
/*   80 */     this.navigatorWater = (PathNavigate)new PathNavigateSwimmer((EntityLiving)this, world);
/*   81 */     this.moveHelper = (EntityMoveHelper)new EntityAIMoverHelperMoC((EntityLiving)this);
/*   82 */     this.navigatorFlyer = (PathNavigate)new PathNavigateFlyer((EntityLiving)this, world);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void applyEntityAttributes() {
/*   87 */     super.applyEntityAttributes();
/*   88 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
/*   89 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
/*      */   }
/*      */ 
/*      */   
/*      */   public ResourceLocation getTexture() {
/*   94 */     return MoCreatures.proxy.getTexture(this.texture);
/*      */   }
/*      */ 
/*      */   
/*      */   public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData par1EntityLivingData) {
/*   99 */     selectType();
/*  100 */     return super.onInitialSpawn(difficulty, par1EntityLivingData);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void selectType() {
/*  109 */     setType(1);
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityAgeable createChild(EntityAgeable var1) {
/*  114 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void entityInit() {
/*  119 */     super.entityInit();
/*  120 */     this.dataManager.register(ADULT, Boolean.valueOf(false));
/*  121 */     this.dataManager.register(TYPE, Integer.valueOf(0));
/*  122 */     this.dataManager.register(AGE, Integer.valueOf(45));
/*  123 */     this.dataManager.register(NAME_STR, "");
/*      */   }
/*      */ 
/*      */   
/*      */   public void setType(int i) {
/*  128 */     this.dataManager.set(TYPE, Integer.valueOf(i));
/*      */   }
/*      */ 
/*      */   
/*      */   public int getType() {
/*  133 */     return ((Integer)this.dataManager.get(TYPE)).intValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDisplayName(boolean flag) {}
/*      */ 
/*      */   
/*      */   public boolean renderName() {
/*  141 */     return (MoCreatures.proxy.getDisplayPetName() && 
/*  142 */       getPetName() != null && !getPetName().equals("") && !isBeingRidden() && getRidingEntity() == null);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getIsAdult() {
/*  147 */     return ((Boolean)this.dataManager.get(ADULT)).booleanValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAdult(boolean flag) {
/*  152 */     this.dataManager.set(ADULT, Boolean.valueOf(flag));
/*      */   }
/*      */ 
/*      */   
/*      */   public String getPetName() {
/*  157 */     return (String)this.dataManager.get(NAME_STR);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPetName(String name) {
/*  162 */     this.dataManager.set(NAME_STR, name);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getEdad() {
/*  167 */     return ((Integer)this.dataManager.get(AGE)).intValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setEdad(int i) {
/*  172 */     this.dataManager.set(AGE, Integer.valueOf(i));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getIsTamed() {
/*  177 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getOwnerPetId() {
/*  182 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOwnerPetId(int petId) {}
/*      */ 
/*      */   
/*      */   public UUID getOwnerId() {
/*  191 */     return null;
/*      */   }
/*      */   
/*      */   public boolean getIsJumping() {
/*  195 */     return this.isEntityJumping;
/*      */   }
/*      */   
/*      */   public void setIsJumping(boolean flag) {
/*  199 */     this.isEntityJumping = flag;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean canDespawn() {
/*  204 */     if (MoCreatures.proxy.forceDespawns) {
/*  205 */       return !getIsTamed();
/*      */     }
/*  207 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean checkSpawningBiome() {
/*  218 */     return true;
/*      */   }
/*      */   
/*      */   protected EntityLivingBase getClosestEntityLiving(Entity entity, double d) {
/*  222 */     double d1 = -1.0D;
/*  223 */     EntityLivingBase entityliving = null;
/*  224 */     List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(d, d, d));
/*  225 */     for (int i = 0; i < list.size(); i++) {
/*  226 */       Entity entity1 = list.get(i);
/*      */       
/*  228 */       if (!entitiesToIgnore(entity1)) {
/*      */ 
/*      */         
/*  231 */         double d2 = entity1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
/*  232 */         if ((d < 0.0D || d2 < d * d) && (d1 == -1.0D || d2 < d1) && ((EntityLivingBase)entity1).canEntityBeSeen(entity)) {
/*  233 */           d1 = d2;
/*  234 */           entityliving = (EntityLivingBase)entity1;
/*      */         } 
/*      */       } 
/*      */     } 
/*  238 */     return entityliving;
/*      */   }
/*      */   
/*      */   public EntityLivingBase getClosestTarget(Entity entity, double d) {
/*  242 */     double d1 = -1.0D;
/*  243 */     EntityLivingBase entityliving = null;
/*  244 */     List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(d, d, d));
/*  245 */     for (int i = 0; i < list.size(); i++) {
/*  246 */       Entity entity1 = list.get(i);
/*  247 */       if (entity1 instanceof EntityLivingBase && entity1 != entity && entity1 != entity.getRidingEntity() && entity1 != entity
/*  248 */         .getRidingEntity() && !(entity1 instanceof EntityPlayer) && !(entity1 instanceof EntityMob) && this.height > entity1.height && this.width > entity1.width) {
/*      */ 
/*      */ 
/*      */         
/*  252 */         double d2 = entity1.getDistanceSq(entity.posY, entity.posZ, entity.motionX);
/*  253 */         if ((d < 0.0D || d2 < d * d) && (d1 == -1.0D || d2 < d1) && ((EntityLivingBase)entity1).canEntityBeSeen(entity)) {
/*  254 */           d1 = d2;
/*  255 */           entityliving = (EntityLivingBase)entity1;
/*      */         } 
/*      */       } 
/*      */     } 
/*  259 */     return entityliving;
/*      */   }
/*      */   
/*      */   protected EntityLivingBase getClosestSpecificEntity(Entity entity, Class<? extends EntityLiving> myClass, double d) {
/*  263 */     double d1 = -1.0D;
/*  264 */     EntityLivingBase entityliving = null;
/*  265 */     List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(entity, entity.getEntityBoundingBox().expand(d, d, d));
/*  266 */     for (int i = 0; i < list.size(); i++) {
/*  267 */       Entity entity1 = list.get(i);
/*  268 */       if (myClass.isAssignableFrom(entity1.getClass())) {
/*      */ 
/*      */ 
/*      */         
/*  272 */         double d2 = entity1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
/*  273 */         if ((d < 0.0D || d2 < d * d) && (d1 == -1.0D || d2 < d1)) {
/*      */           
/*  275 */           d1 = d2;
/*  276 */           entityliving = (EntityLivingBase)entity1;
/*      */         } 
/*      */       } 
/*      */     } 
/*  280 */     return entityliving;
/*      */   }
/*      */   
/*      */   public boolean entitiesToIgnore(Entity entity) {
/*  284 */     return (!(entity instanceof EntityLiving) || entity instanceof EntityMob || entity instanceof EntityPlayer || entity instanceof drzhark.mocreatures.entity.item.MoCEntityKittyBed || entity instanceof drzhark.mocreatures.entity.item.MoCEntityLitterBox || (
/*      */       
/*  286 */       getIsTamed() && entity instanceof IMoCEntity && ((IMoCEntity)entity).getIsTamed()) || (entity instanceof net.minecraft.entity.passive.EntityWolf && !MoCreatures.proxy.attackWolves) || (entity instanceof drzhark.mocreatures.entity.passive.MoCEntityHorse && !MoCreatures.proxy.attackHorses) || entity.width >= this.width || entity.height >= this.height || entity instanceof drzhark.mocreatures.entity.item.MoCEntityEgg || (entity instanceof IMoCEntity && !MoCreatures.proxy.enableHunters));
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
/*      */   protected EntityLivingBase getBoogey(double d) {
/*  299 */     EntityLivingBase entityliving = null;
/*  300 */     List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(d, 4.0D, d));
/*  301 */     for (int i = 0; i < list.size(); i++) {
/*  302 */       Entity entity = list.get(i);
/*  303 */       if (entitiesToInclude(entity)) {
/*  304 */         entityliving = (EntityLivingBase)entity;
/*      */       }
/*      */     } 
/*  307 */     return entityliving;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean entitiesToInclude(Entity entity) {
/*  317 */     return (entity.getClass() != getClass() && entity instanceof EntityLivingBase && (entity.width >= 0.5D || entity.height >= 0.5D));
/*      */   }
/*      */ 
/*      */   
/*      */   public void onLivingUpdate() {
/*  322 */     if (!this.world.isRemote) {
/*  323 */       if (rideableEntity() && isBeingRidden()) {
/*  324 */         Riding();
/*      */       }
/*      */       
/*  327 */       if (isMovementCeased()) {
/*  328 */         getNavigator().clearPath();
/*      */       }
/*  330 */       if (getEdad() == 0) setEdad(getMaxEdad() - 10); 
/*  331 */       if (!getIsAdult() && this.rand.nextInt(300) == 0 && getEdad() <= getMaxEdad()) {
/*  332 */         setEdad(getEdad() + 1);
/*  333 */         if (getEdad() >= getMaxEdad()) {
/*  334 */           setAdult(true);
/*      */         }
/*      */       } 
/*      */       
/*  338 */       if (MoCreatures.proxy.enableHunters && isReadyToHunt() && !getIsHunting() && this.rand.nextInt(500) == 0) {
/*  339 */         setIsHunting(true);
/*      */       }
/*      */       
/*  342 */       if (getIsHunting() && ++this.huntingCounter > 50) {
/*  343 */         setIsHunting(false);
/*      */       }
/*      */       
/*  346 */       getNavigator().onUpdateNavigation();
/*      */     } 
/*      */     
/*  349 */     if (isInWater() && isAmphibian() && (
/*  350 */       this.rand.nextInt(500) == 0 || !this.randomAttributesUpdated)) {
/*  351 */       setNewDivingDepth();
/*  352 */       this.randomAttributesUpdated = true;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  357 */     if (canRidePlayer() && isRiding()) MoCTools.dismountSneakingPlayer((EntityLiving)this); 
/*  358 */     resetInLove();
/*  359 */     super.onLivingUpdate();
/*      */   }
/*      */   
/*      */   public int getMaxEdad() {
/*  363 */     return 100;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isNotScared() {
/*  368 */     return false;
/*      */   }
/*      */   
/*      */   public boolean swimmerEntity() {
/*  372 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isSwimming() {
/*  376 */     return isInsideOfMaterial(Material.WATER);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isMyAphrodisiac(Item item1) {
/*  386 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dropMyStuff() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isMyHealFood(ItemStack itemstack) {
/*  400 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isInWater() {
/*  405 */     if (isAmphibian()) {
/*  406 */       return this.world.handleMaterialAcceleration(getEntityBoundingBox().expand(0.0D, -0.2D, 0.0D), Material.WATER, (Entity)this);
/*      */     }
/*  408 */     return super.isInWater();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canBreatheUnderwater() {
/*  413 */     return isAmphibian();
/*      */   }
/*      */   
/*      */   public EntityItem getClosestItem(Entity entity, double d, Item item, Item item1) {
/*  417 */     double d1 = -1.0D;
/*  418 */     EntityItem entityitem = null;
/*  419 */     List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(d, d, d));
/*  420 */     for (int k = 0; k < list.size(); k++) {
/*  421 */       Entity entity1 = list.get(k);
/*  422 */       if (entity1 instanceof EntityItem) {
/*      */ 
/*      */         
/*  425 */         EntityItem entityitem1 = (EntityItem)entity1;
/*  426 */         if (entityitem1.getItem().getItem() == item || entityitem1.getItem().getItem() == item1) {
/*      */ 
/*      */           
/*  429 */           double d2 = entityitem1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
/*  430 */           if ((d < 0.0D || d2 < d * d) && (d1 == -1.0D || d2 < d1)) {
/*  431 */             d1 = d2;
/*  432 */             entityitem = entityitem1;
/*      */           } 
/*      */         } 
/*      */       } 
/*  436 */     }  return entityitem;
/*      */   }
/*      */   
/*      */   public EntityItem getClosestEntityItem(Entity entity, double d) {
/*  440 */     double d1 = -1.0D;
/*  441 */     EntityItem entityitem = null;
/*  442 */     List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(d, d, d));
/*  443 */     for (int k = 0; k < list.size(); k++) {
/*  444 */       Entity entity1 = list.get(k);
/*  445 */       if (entity1 instanceof EntityItem) {
/*      */ 
/*      */         
/*  448 */         EntityItem entityitem1 = (EntityItem)entity1;
/*  449 */         double d2 = entityitem1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
/*  450 */         if ((d < 0.0D || d2 < d * d) && (d1 == -1.0D || d2 < d1)) {
/*  451 */           d1 = d2;
/*  452 */           entityitem = entityitem1;
/*      */         } 
/*      */       } 
/*      */     } 
/*  456 */     return entityitem;
/*      */   }
/*      */   
/*      */   public EntityItem getClosestFood(Entity entity, double d) {
/*  460 */     double d1 = -1.0D;
/*  461 */     EntityItem entityitem = null;
/*  462 */     List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(d, d, d));
/*  463 */     for (int k = 0; k < list.size(); k++) {
/*  464 */       Entity entity1 = list.get(k);
/*  465 */       if (entity1 instanceof EntityItem) {
/*      */ 
/*      */         
/*  468 */         EntityItem entityitem1 = (EntityItem)entity1;
/*  469 */         if (MoCTools.isItemEdible(entityitem1.getItem().getItem())) {
/*      */ 
/*      */           
/*  472 */           double d2 = entityitem1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
/*  473 */           if ((d < 0.0D || d2 < d * d) && (d1 == -1.0D || d2 < d1)) {
/*  474 */             d1 = d2;
/*  475 */             entityitem = entityitem1;
/*      */           } 
/*      */         } 
/*      */       } 
/*  479 */     }  return entityitem;
/*      */   }
/*      */   
/*      */   public void faceLocation(int i, int j, int k, float f) {
/*  483 */     double var4 = i + 0.5D - this.posX;
/*  484 */     double var8 = k + 0.5D - this.posZ;
/*  485 */     double var6 = j + 0.5D - this.posY;
/*  486 */     double var14 = MathHelper.sqrt(var4 * var4 + var8 * var8);
/*  487 */     float var12 = (float)(Math.atan2(var8, var4) * 180.0D / Math.PI) - 90.0F;
/*  488 */     float var13 = (float)-(Math.atan2(var6, var14) * 180.0D / Math.PI);
/*  489 */     this.rotationPitch = -updateRotation2(this.rotationPitch, var13, f);
/*  490 */     this.rotationYaw = updateRotation2(this.rotationYaw, var12, f);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private float updateRotation2(float par1, float par2, float par3) {
/*      */     float var4;
/*  499 */     for (var4 = par2 - par1; var4 < -180.0F; var4 += 360.0F);
/*      */ 
/*      */ 
/*      */     
/*  503 */     while (var4 >= 180.0F) {
/*  504 */       var4 -= 360.0F;
/*      */     }
/*      */     
/*  507 */     if (var4 > par3) {
/*  508 */       var4 = par3;
/*      */     }
/*      */     
/*  511 */     if (var4 < -par3) {
/*  512 */       var4 = -par3;
/*      */     }
/*      */     
/*  515 */     return par1 + var4;
/*      */   }
/*      */   
/*      */   public void getMyOwnPath(Entity entity, float f) {
/*  519 */     Path pathentity = getNavigator().getPathToEntityLiving(entity);
/*  520 */     if (pathentity != null) {
/*  521 */       getNavigator().setPath(pathentity, 1.0D);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void Riding() {
/*  529 */     if (isBeingRidden() && getRidingEntity() instanceof EntityPlayer) {
/*  530 */       EntityPlayer entityplayer = (EntityPlayer)getRidingEntity();
/*  531 */       List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(1.0D, 0.0D, 1.0D));
/*  532 */       if (list != null)
/*  533 */         for (int i = 0; i < list.size(); i++) {
/*  534 */           Entity entity = list.get(i);
/*  535 */           if (!entity.isDead) {
/*      */ 
/*      */             
/*  538 */             entity.onCollideWithPlayer(entityplayer);
/*  539 */             if (entity instanceof EntityMob) {
/*      */ 
/*      */               
/*  542 */               float f = getDistance(entity);
/*  543 */               if (f < 2.0F && entity instanceof EntityMob && this.rand.nextInt(10) == 0)
/*  544 */                 attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)entity), 
/*  545 */                     (float)((EntityMob)entity).getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()); 
/*      */             } 
/*      */           } 
/*      */         }  
/*  549 */       if (entityplayer.isSneaking()) {
/*  550 */         makeEntityDive();
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void getPathOrWalkableBlock(Entity entity, float f) {
/*  556 */     Path pathentity = this.navigator.getPathToPos(entity.getPosition());
/*  557 */     if (pathentity == null && f > 8.0F) {
/*  558 */       int i = MathHelper.floor(entity.posX) - 2;
/*  559 */       int j = MathHelper.floor(entity.posZ) - 2;
/*  560 */       int k = MathHelper.floor((entity.getEntityBoundingBox()).minY);
/*  561 */       for (int l = 0; l <= 4; l++) {
/*  562 */         for (int i1 = 0; i1 <= 4; i1++) {
/*  563 */           BlockPos pos = new BlockPos(i, k, j);
/*  564 */           if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && this.world.getBlockState(pos.add(l, -1, i1)).isNormalCube() && 
/*  565 */             !this.world.getBlockState(pos.add(l, 0, i1)).isNormalCube() && 
/*  566 */             !this.world.getBlockState(pos.add(l, 1, i1)).isNormalCube()) {
/*  567 */             setLocationAndAngles(((i + l) + 0.5F), k, ((j + i1) + 0.5F), this.rotationYaw, this.rotationPitch);
/*      */             return;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/*  573 */       this.navigator.setPath(pathentity, 16.0D);
/*      */     } 
/*      */   }
/*      */   
/*      */   public MoCEntityAnimal spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
/*  578 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getCanSpawnHereCreature() {
/*  584 */     BlockPos pos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor((getEntityBoundingBox()).minY), MathHelper.floor(this.posZ));
/*  585 */     return (getBlockPathWeight(pos) >= 0.0F);
/*      */   }
/*      */   
/*      */   public boolean getCanSpawnHereLiving() {
/*  589 */     return (this.world.checkNoEntityCollision(getEntityBoundingBox()) && this.world
/*  590 */       .getCollisionBoxes((Entity)this, getEntityBoundingBox()).isEmpty() && 
/*  591 */       !this.world.containsAnyLiquid(getEntityBoundingBox()));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getCanSpawnHere() {
/*  596 */     if (((MoCEntityData)MoCreatures.entityMap.get(getClass())).getFrequency() <= 0) {
/*  597 */       return false;
/*      */     }
/*  599 */     if (this.world.provider.getDimensionType().getId() != 0) {
/*  600 */       return (getCanSpawnHereCreature() && getCanSpawnHereLiving());
/*      */     }
/*  602 */     BlockPos pos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor((getEntityBoundingBox()).minY), this.posZ);
/*      */     
/*  604 */     String s = MoCTools.biomeName(this.world, pos);
/*      */     
/*  606 */     if (s.toLowerCase().contains("jungle")) {
/*  607 */       return getCanSpawnHereJungle();
/*      */     }
/*  609 */     if (s.equals("WyvernBiome")) {
/*  610 */       return getCanSpawnHereMoCBiome();
/*      */     }
/*  612 */     return super.getCanSpawnHere();
/*      */   }
/*      */   
/*      */   private boolean getCanSpawnHereMoCBiome() {
/*  616 */     if (this.world.checkNoEntityCollision(getEntityBoundingBox()) && this.world
/*  617 */       .getCollisionBoxes((Entity)this, getEntityBoundingBox()).isEmpty() && 
/*  618 */       !this.world.containsAnyLiquid(getEntityBoundingBox())) {
/*      */ 
/*      */       
/*  621 */       BlockPos pos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor((getEntityBoundingBox()).minY), MathHelper.floor(this.posZ));
/*      */       
/*  623 */       if (pos.getY() < 50) {
/*  624 */         return false;
/*      */       }
/*      */       
/*  627 */       IBlockState blockstate = this.world.getBlockState(pos.down());
/*  628 */       Block block = blockstate.getBlock();
/*      */       
/*  630 */       if (block == MoCBlocks.mocDirt || block == MoCBlocks.mocGrass || (block != null && block
/*  631 */         .isLeaves(blockstate, (IBlockAccess)this.world, pos.down()))) {
/*  632 */         return true;
/*      */       }
/*      */     } 
/*  635 */     return false;
/*      */   }
/*      */   
/*      */   public boolean getCanSpawnHereJungle() {
/*  639 */     if (this.world.checkNoEntityCollision(getEntityBoundingBox()) && this.world
/*  640 */       .getCollisionBoxes((Entity)this, getEntityBoundingBox()).isEmpty()) {
/*  641 */       return true;
/*      */     }
/*  643 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
/*  648 */     super.writeEntityToNBT(nbttagcompound);
/*      */     
/*  650 */     nbttagcompound.setBoolean("Adult", getIsAdult());
/*  651 */     nbttagcompound.setInteger("Edad", getEdad());
/*  652 */     nbttagcompound.setString("Name", getPetName());
/*  653 */     nbttagcompound.setInteger("TypeInt", getType());
/*      */   }
/*      */ 
/*      */   
/*      */   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
/*  658 */     super.readEntityFromNBT(nbttagcompound);
/*      */     
/*  660 */     setAdult(nbttagcompound.getBoolean("Adult"));
/*  661 */     setEdad(nbttagcompound.getInteger("Edad"));
/*  662 */     setPetName(nbttagcompound.getString("Name"));
/*  663 */     setType(nbttagcompound.getInteger("TypeInt"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void travel(float strafe, float vertical, float forward) {
/*  672 */     if (isBeingRidden()) {
/*  673 */       EntityLivingBase passenger = (EntityLivingBase)getControllingPassenger();
/*  674 */       if (passenger != null) moveWithRider(strafe, vertical, forward, passenger); 
/*      */       return;
/*      */     } 
/*  677 */     if ((isAmphibian() && isInWater()) || (isFlyer() && getIsFlying())) {
/*  678 */       moveRelative(strafe, vertical, forward, 0.1F);
/*  679 */       move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
/*  680 */       this.motionX *= 0.8999999761581421D;
/*  681 */       this.motionY *= 0.8999999761581421D;
/*  682 */       this.motionZ *= 0.8999999761581421D;
/*  683 */       if (getAttackTarget() == null) {
/*  684 */         this.motionY -= 0.005D;
/*      */       }
/*      */     } else {
/*      */       
/*  688 */       super.travel(strafe, vertical, forward);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void moveWithRider(float strafe, float vertical, float forward, EntityLivingBase passenger) {
/*  700 */     if (passenger == null) {
/*      */       return;
/*      */     }
/*      */     
/*  704 */     if (isBeingRidden() && !getIsTamed()) {
/*  705 */       moveEntityWithRiderUntamed(strafe, forward, passenger);
/*      */       return;
/*      */     } 
/*  708 */     boolean flySelfPropelled = (selfPropelledFlyer() && isOnAir());
/*  709 */     boolean flyingMount = (isFlyer() && isBeingRidden() && getIsTamed() && !this.onGround && isOnAir());
/*  710 */     this.rotationYaw = passenger.rotationYaw;
/*  711 */     this.prevRotationYaw = this.rotationYaw;
/*  712 */     this.rotationPitch = passenger.rotationPitch * 0.5F;
/*  713 */     setRotation(this.rotationYaw, this.rotationPitch);
/*  714 */     this.renderYawOffset = this.rotationYaw;
/*  715 */     this.rotationYawHead = this.renderYawOffset;
/*  716 */     if (!selfPropelledFlyer() || (selfPropelledFlyer() && !isOnAir())) {
/*  717 */       strafe = (float)((passenger.moveStrafing * 0.5F) * getCustomSpeed());
/*  718 */       forward = (float)(passenger.moveForward * getCustomSpeed());
/*      */     } 
/*      */     
/*  721 */     if (this.jumpPending && isFlyer()) {
/*  722 */       this.motionY += flyerThrust();
/*  723 */       this.jumpPending = false;
/*      */       
/*  725 */       if (flySelfPropelled) {
/*  726 */         float velX = MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F);
/*  727 */         float velZ = MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F);
/*      */         
/*  729 */         this.motionX += (-0.5F * velX);
/*  730 */         this.motionZ += (0.5F * velZ);
/*      */       } 
/*  732 */     } else if (this.jumpPending && !getIsJumping()) {
/*  733 */       this.motionY = getCustomJump() * 2.0D;
/*  734 */       setIsJumping(true);
/*  735 */       this.jumpPending = false;
/*      */     } 
/*      */     
/*  738 */     if (this.divePending) {
/*  739 */       this.divePending = false;
/*  740 */       this.motionY -= 0.3D;
/*      */     } 
/*  742 */     if (flyingMount) {
/*  743 */       move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
/*  744 */       moveRelative(strafe, vertical, forward, flyerFriction() / 10.0F);
/*  745 */       this.motionY *= myFallSpeed();
/*  746 */       this.motionY -= 0.055D;
/*  747 */       this.motionZ *= flyerFriction();
/*  748 */       this.motionX *= flyerFriction();
/*      */     } else {
/*  750 */       setAIMoveSpeed((float)getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
/*  751 */       super.travel(strafe, vertical, forward);
/*      */     } 
/*      */     
/*  754 */     if (this.onGround) {
/*  755 */       setIsJumping(false);
/*  756 */       this.divePending = false;
/*  757 */       this.jumpPending = false;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void moveEntityWithRiderUntamed(float strafe, float forward, EntityLivingBase passenger) {
/*  763 */     if (isBeingRidden() && !getIsTamed()) {
/*  764 */       if (this.rand.nextInt(5) == 0 && !getIsJumping() && this.jumpPending) {
/*  765 */         this.motionY += getCustomJump();
/*  766 */         setIsJumping(true);
/*  767 */         this.jumpPending = false;
/*      */       } 
/*  769 */       if (this.rand.nextInt(10) == 0) {
/*  770 */         this.motionX += this.rand.nextDouble() / 30.0D;
/*  771 */         this.motionZ += this.rand.nextDouble() / 10.0D;
/*      */       } 
/*  773 */       if (!this.world.isRemote) {
/*  774 */         move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
/*      */       }
/*  776 */       if (!this.world.isRemote && this.rand.nextInt(50) == 0) {
/*  777 */         passenger.motionY += 0.9D;
/*  778 */         passenger.motionZ -= 0.3D;
/*  779 */         passenger.dismountRidingEntity();
/*      */       } 
/*  781 */       if (this.onGround) {
/*  782 */         setIsJumping(false);
/*      */       }
/*  784 */       if (!this.world.isRemote && this instanceof IMoCTameable && passenger instanceof EntityPlayer) {
/*  785 */         int chance = getMaxTemper() - getTemper();
/*  786 */         if (chance <= 0) {
/*  787 */           chance = 1;
/*      */         }
/*  789 */         if (this.rand.nextInt(chance * 8) == 0) {
/*  790 */           MoCTools.tameWithName((EntityPlayer)passenger, (IMoCTameable)this);
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
/*      */   
/*      */   public int maxFlyingHeight() {
/*  803 */     return 5;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected double myFallSpeed() {
/*  812 */     return 0.6D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected double flyerThrust() {
/*  821 */     return 0.3D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float flyerFriction() {
/*  830 */     return 0.91F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean selfPropelledFlyer() {
/*  840 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void makeEntityJump() {
/*  849 */     this.jumpPending = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isFlyer() {
/*  856 */     return false;
/*      */   }
/*      */   
/*      */   public int getTemper() {
/*  860 */     return this.temper;
/*      */   }
/*      */   
/*      */   public void setTemper(int i) {
/*  864 */     this.temper = i;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxTemper() {
/*  872 */     return 100;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getCustomSpeed() {
/*  879 */     return 0.6D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getCustomJump() {
/*  886 */     return 0.4D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected SoundEvent getAngrySound() {
/*  893 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean rideableEntity() {
/*  900 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public int nameYOffset() {
/*  905 */     return -80;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Entity findPlayerToAttack() {
/*  913 */     return null;
/*      */   }
/*      */   
/*      */   public void repelMobs(Entity entity1, Double dist, World world) {
/*  917 */     List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(entity1, entity1.getEntityBoundingBox().expand(dist.doubleValue(), 4.0D, dist.doubleValue()));
/*  918 */     for (int i = 0; i < list.size(); i++) {
/*  919 */       Entity entity = list.get(i);
/*  920 */       if (entity instanceof EntityMob) {
/*      */ 
/*      */         
/*  923 */         EntityMob entitymob = (EntityMob)entity;
/*  924 */         entitymob.setAttackTarget(null);
/*  925 */         entitymob.getNavigator().clearPath();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   public void faceItem(int i, int j, int k, float f) {
/*  930 */     double d = i - this.posX;
/*  931 */     double d1 = k - this.posZ;
/*  932 */     double d2 = j - this.posY;
/*  933 */     double d3 = MathHelper.sqrt(d * d + d1 * d1);
/*  934 */     float f1 = (float)(Math.atan2(d1, d) * 180.0D / 3.141592741012573D) - 90.0F;
/*  935 */     float f2 = (float)(Math.atan2(d2, d3) * 180.0D / 3.141592741012573D);
/*  936 */     this.rotationPitch = -adjustRotation(this.rotationPitch, f2, f);
/*  937 */     this.rotationYaw = adjustRotation(this.rotationYaw, f1, f);
/*      */   }
/*      */   
/*      */   public float adjustRotation(float f, float f1, float f2) {
/*  941 */     float f3 = f1;
/*  942 */     for (f3 = f1 - f; f3 < -180.0F; f3 += 360.0F);
/*      */     
/*  944 */     for (; f3 >= 180.0F; f3 -= 360.0F);
/*      */     
/*  946 */     if (f3 > f2) {
/*  947 */       f3 = f2;
/*      */     }
/*  949 */     if (f3 < -f2) {
/*  950 */       f3 = -f2;
/*      */     }
/*  952 */     return f + f3;
/*      */   }
/*      */   
/*      */   public boolean isFlyingAlone() {
/*  956 */     return false;
/*      */   }
/*      */ 
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
/*      */   public boolean isMyFavoriteFood(ItemStack par1ItemStack) {
/*  975 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private void followPlayer() {
/*  980 */     EntityPlayer entityplayer1 = this.world.getClosestPlayerToEntity((Entity)this, 24.0D);
/*  981 */     if (entityplayer1 == null) {
/*      */       return;
/*      */     }
/*      */     
/*  985 */     ItemStack itemstack1 = entityplayer1.inventory.getCurrentItem();
/*  986 */     if (itemstack1 != null && isMyFavoriteFood(itemstack1)) {
/*  987 */       getNavigator().tryMoveToEntityLiving((Entity)entityplayer1, 1.0D);
/*      */     }
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
/*      */   public void makeEntityDive() {
/* 1017 */     this.divePending = true;
/*      */   }
/*      */   
/*      */   public boolean isOnAir() {
/* 1021 */     return (this.world.isAirBlock(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.posY - 0.2D), 
/* 1022 */           MathHelper.floor(this.posZ))) && this.world.isAirBlock(new BlockPos(MathHelper.floor(this.posX), 
/* 1023 */           MathHelper.floor(this.posY - 1.2D), MathHelper.floor(this.posZ))));
/*      */   }
/*      */ 
/*      */   
/*      */   public float getSizeFactor() {
/* 1028 */     return 1.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getAdjustedYOffset() {
/* 1033 */     return 0.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   public void onDeath(DamageSource damagesource) {
/* 1038 */     if (!this.world.isRemote) {
/* 1039 */       dropMyStuff();
/*      */     }
/*      */     
/* 1042 */     super.onDeath(damagesource);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/* 1047 */     if (isNotScared()) {
/* 1048 */       EntityLivingBase entityLivingBase = getAttackTarget();
/* 1049 */       boolean flag = super.attackEntityFrom(damagesource, i);
/* 1050 */       setAttackTarget(entityLivingBase);
/* 1051 */       return flag;
/*      */     } 
/*      */     
/* 1054 */     return super.attackEntityFrom(damagesource, i);
/*      */   }
/*      */   
/*      */   public boolean getIsRideable() {
/* 1058 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRideable(boolean b) {}
/*      */ 
/*      */   
/*      */   public void setArmorType(int i) {}
/*      */ 
/*      */   
/*      */   public int getArmorType() {
/* 1069 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dropArmor() {}
/*      */ 
/*      */ 
/*      */   
/*      */   public float pitchRotationOffset() {
/* 1080 */     return 0.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   public float rollRotationOffset() {
/* 1085 */     return 0.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   public float yawRotationOffset() {
/* 1090 */     return 0.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getAdjustedZOffset() {
/* 1095 */     return 0.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getAdjustedXOffset() {
/* 1100 */     return 0.0F;
/*      */   }
/*      */   
/*      */   protected boolean canBeTrappedInNet() {
/* 1104 */     return (this instanceof IMoCTameable && getIsTamed());
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
/*      */   public boolean canAttackTarget(EntityLivingBase entity) {
/* 1117 */     return (this.height >= entity.height && this.width >= entity.width);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean attackEntityAsMob(Entity entityIn) {
/* 1123 */     boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), 
/* 1124 */         (int)getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
/* 1125 */     if (flag) {
/* 1126 */       applyEnchantments((EntityLivingBase)this, entityIn);
/*      */     }
/*      */     
/* 1129 */     return flag;
/*      */   }
/*      */   
/*      */   public boolean isReadyToHunt() {
/* 1133 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canBeLeashedTo(EntityPlayer player) {
/* 1138 */     if (!this.world.isRemote && !MoCTools.isThisPlayerAnOP(player) && getIsTamed() && !player.getUniqueID().equals(getOwnerId())) {
/* 1139 */       return false;
/*      */     }
/* 1141 */     return super.canBeLeashedTo(player);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getIsSitting() {
/* 1146 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isMovementCeased() {
/* 1151 */     return (getIsSitting() || isBeingRidden());
/*      */   }
/*      */   
/*      */   public boolean getIsHunting() {
/* 1155 */     return (this.huntingCounter != 0);
/*      */   }
/*      */   
/*      */   public void setIsHunting(boolean flag) {
/* 1159 */     if (flag) {
/* 1160 */       this.huntingCounter = this.rand.nextInt(30) + 1;
/*      */     } else {
/* 1162 */       this.huntingCounter = 0;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean shouldAttackPlayers() {
/* 1168 */     return (!getIsTamed() && this.world.getDifficulty() != EnumDifficulty.PEACEFUL);
/*      */   }
/*      */ 
/*      */   
/*      */   public void onKillEntity(EntityLivingBase entityLivingIn) {
/* 1173 */     if (!(entityLivingIn instanceof EntityPlayer)) {
/* 1174 */       MoCTools.destroyDrops((Entity)this, 3.0D);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public PathNavigate getNavigator() {
/* 1180 */     if (isInWater() && isAmphibian()) {
/* 1181 */       return this.navigatorWater;
/*      */     }
/* 1183 */     if (isFlyer() && getIsFlying()) {
/* 1184 */       return this.navigatorFlyer;
/*      */     }
/* 1186 */     return this.navigator;
/*      */   }
/*      */   
/*      */   public boolean isAmphibian() {
/* 1190 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isDiving() {
/* 1195 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getDivingDepth() {
/* 1204 */     return (float)this.divingDepth;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setNewDivingDepth(double setDepth) {
/* 1212 */     if (setDepth != 0.0D) {
/* 1213 */       if (setDepth > maxDivingDepth()) {
/* 1214 */         setDepth = maxDivingDepth();
/*      */       }
/* 1216 */       if (setDepth < minDivingDepth()) {
/* 1217 */         setDepth = minDivingDepth();
/*      */       }
/* 1219 */       this.divingDepth = setDepth;
/*      */     } else {
/*      */       
/* 1222 */       this.divingDepth = (float)(this.rand.nextDouble() * (maxDivingDepth() - minDivingDepth()) + minDivingDepth());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void setNewDivingDepth() {
/* 1228 */     setNewDivingDepth(0.0D);
/*      */   }
/*      */   
/*      */   protected double minDivingDepth() {
/* 1232 */     return 0.2D;
/*      */   }
/*      */   
/*      */   protected double maxDivingDepth() {
/* 1236 */     return 1.0D;
/*      */   }
/*      */ 
/*      */   
/*      */   public void forceEntityJump() {
/* 1241 */     jump();
/*      */   }
/*      */ 
/*      */   
/*      */   public int minFlyingHeight() {
/* 1246 */     return 1;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getIsFlying() {
/* 1251 */     return false;
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
/* 1263 */     if (type == EnumCreatureType.CREATURE) {
/* 1264 */       return true;
/*      */     }
/* 1266 */     return false;
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
/* 1277 */     return getPassengers().isEmpty() ? null : getPassengers().get(0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canRidePlayer() {
/* 1286 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getClazzString() {
/* 1291 */     return EntityList.getEntityString((Entity)this);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getIsGhost() {
/* 1296 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLeashHolder(Entity entityIn, boolean sendAttachNotification) {
/* 1301 */     if (getIsTamed() && entityIn instanceof EntityPlayer) {
/* 1302 */       EntityPlayer entityplayer = (EntityPlayer)entityIn;
/* 1303 */       if (MoCreatures.proxy.enableOwnership && getOwnerId() != null && 
/* 1304 */         !entityplayer.getUniqueID().equals(getOwnerId()) && !MoCTools.isThisPlayerAnOP(entityplayer)) {
/*      */         return;
/*      */       }
/*      */     } 
/* 1308 */     super.setLeashHolder(entityIn, sendAttachNotification);
/*      */   }
/*      */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\MoCEntityAnimal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */