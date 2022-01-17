/*      */ package drzhark.mocreatures.entity.passive;
/*      */
/*      */ import drzhark.mocreatures.MoCTools;
/*      */ import drzhark.mocreatures.MoCreatures;
/*      */ import drzhark.mocreatures.entity.IMoCTameable;
/*      */ import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
/*      */ import drzhark.mocreatures.entity.ai.EntityAIFollowAdult;
/*      */ import drzhark.mocreatures.entity.ai.EntityAIPanicMoC;
/*      */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*      */ import drzhark.mocreatures.entity.item.MoCEntityKittyBed;
/*      */ import drzhark.mocreatures.entity.item.MoCEntityLitterBox;
/*      */ import drzhark.mocreatures.init.MoCItems;
/*      */ import drzhark.mocreatures.init.MoCSoundEvents;
/*      */ import drzhark.mocreatures.network.MoCMessageHandler;
/*      */ import drzhark.mocreatures.network.message.MoCMessageAnimation;
/*      */ import java.util.List;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityCreature;
/*      */ import net.minecraft.entity.EntityLiving;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.SharedMonsterAttributes;
/*      */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*      */ import net.minecraft.entity.ai.EntityAIBase;
/*      */ import net.minecraft.entity.ai.EntityAISwimming;
/*      */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*      */ import net.minecraft.entity.item.EntityItem;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.network.datasync.DataParameter;
/*      */ import net.minecraft.network.datasync.DataSerializers;
/*      */ import net.minecraft.network.datasync.EntityDataManager;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.EnumHand;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.SoundEvent;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*      */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */ public class MoCEntityKitty
/*      */   extends MoCEntityTameableAnimal
/*      */ {
/*      */   private int kittytimer;
/*      */   private int madtimer;
/*      */   private boolean foundTree;
/*   55 */   private final int[] treeCoord = new int[] { -1, -1, -1 };
/*      */
/*      */   private boolean isSwinging;
/*      */   private boolean onTree;
/*      */   private EntityItem itemAttackTarget;
/*   60 */   private static final DataParameter<Boolean> SITTING = EntityDataManager.createKey(MoCEntityKitty.class, DataSerializers.BOOLEAN);
/*   61 */   private static final DataParameter<Boolean> HUNGRY = EntityDataManager.createKey(MoCEntityKitty.class, DataSerializers.BOOLEAN);
/*   62 */   private static final DataParameter<Boolean> EMO = EntityDataManager.createKey(MoCEntityKitty.class, DataSerializers.BOOLEAN);
/*   63 */   private static final DataParameter<Integer> KITTY_STATE = EntityDataManager.createKey(MoCEntityKitty.class, DataSerializers.VARINT);
/*      */
/*      */   public MoCEntityKitty(World world) {
/*   66 */     super(world);
/*   67 */     setSize(0.7F, 0.5F);
/*   68 */     setAdult(true);
/*   69 */     setEdad(40);
/*   70 */     setKittyState(1);
/*   71 */     this.kittytimer = 0;
/*   72 */     this.madtimer = this.rand.nextInt(5);
/*   73 */     this.foundTree = false;
/*      */   }
/*      */
/*      */
/*      */   protected void initEntityAI() {
/*   78 */     this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*   79 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIPanicMoC((EntityCreature)this, 1.0D));
/*   80 */     this.tasks.addTask(4, (EntityAIBase)new EntityAIFollowAdult((EntityLiving)this, 1.0D));
/*   81 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
/*   82 */     this.tasks.addTask(6, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D));
/*   83 */     this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*      */   }
/*      */
/*      */
/*      */   protected void applyEntityAttributes() {
/*   88 */     super.applyEntityAttributes();
/*   89 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
/*   90 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
/*   91 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
/*   92 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
/*      */   }
/*      */
/*      */
/*      */   public void selectType() {
/*   97 */     if (getType() == 0) {
/*   98 */       setType(this.rand.nextInt(8) + 1);
/*      */     }
/*      */   }
/*      */
/*      */
/*      */
/*      */   public ResourceLocation getTexture() {
/*  105 */     switch (getType()) {
/*      */       case 1:
/*  107 */         return MoCreatures.proxy.getTexture("pussycata.png");
/*      */       case 2:
/*  109 */         return MoCreatures.proxy.getTexture("pussycatb.png");
/*      */       case 3:
/*  111 */         return MoCreatures.proxy.getTexture("pussycatc.png");
/*      */       case 4:
/*  113 */         return MoCreatures.proxy.getTexture("pussycatd.png");
/*      */       case 5:
/*  115 */         return MoCreatures.proxy.getTexture("pussycate.png");
/*      */       case 6:
/*  117 */         return MoCreatures.proxy.getTexture("pussycatf.png");
/*      */       case 7:
/*  119 */         return MoCreatures.proxy.getTexture("pussycatg.png");
/*      */       case 8:
/*  121 */         return MoCreatures.proxy.getTexture("pussycath.png");
/*      */     }
/*      */
/*  124 */     return MoCreatures.proxy.getTexture("pussycata.png");
/*      */   }
/*      */
/*      */
/*      */
/*      */   protected void entityInit() {
/*  130 */     super.entityInit();
/*  131 */     this.dataManager.register(SITTING, Boolean.valueOf(false));
/*  132 */     this.dataManager.register(HUNGRY, Boolean.valueOf(false));
/*  133 */     this.dataManager.register(EMO, Boolean.valueOf(false));
/*  134 */     this.dataManager.register(KITTY_STATE, Integer.valueOf(0));
/*      */   }
/*      */
/*      */   public int getKittyState() {
/*  138 */     return ((Integer)this.dataManager.get(KITTY_STATE)).intValue();
/*      */   }
/*      */
/*      */
/*      */   public boolean getIsSitting() {
/*  143 */     return ((Boolean)this.dataManager.get(SITTING)).booleanValue();
/*      */   }
/*      */
/*      */   public boolean getIsHungry() {
/*  147 */     return ((Boolean)this.dataManager.get(HUNGRY)).booleanValue();
/*      */   }
/*      */
/*      */   public boolean getIsEmo() {
/*  151 */     return ((Boolean)this.dataManager.get(EMO)).booleanValue();
/*      */   }
/*      */
/*      */   public boolean getIsSwinging() {
/*  155 */     return this.isSwinging;
/*      */   }
/*      */
/*      */   public boolean getOnTree() {
/*  159 */     return this.onTree;
/*      */   }
/*      */
/*      */   public void setKittyState(int i) {
/*  163 */     this.dataManager.set(KITTY_STATE, Integer.valueOf(i));
/*      */   }
/*      */
/*      */   public void setSitting(boolean flag) {
/*  167 */     this.dataManager.set(SITTING, Boolean.valueOf(flag));
/*      */   }
/*      */
/*      */   public void setHungry(boolean flag) {
/*  171 */     this.dataManager.set(HUNGRY, Boolean.valueOf(flag));
/*      */   }
/*      */
/*      */   public void setIsEmo(boolean flag) {
/*  175 */     this.dataManager.set(EMO, Boolean.valueOf(flag));
/*      */   }
/*      */
/*      */   public void setOnTree(boolean var1) {
/*  179 */     this.onTree = var1;
/*      */   }
/*      */
/*      */   public void setSwinging(boolean var1) {
/*  183 */     this.isSwinging = var1;
/*      */   }
/*      */
/*      */
/*      */   public boolean attackEntityAsMob(Entity entityIn) {
/*  188 */     if (getKittyState() != 18 && getKittyState() != 10) {
/*  189 */       swingArm();
/*      */     }
/*  191 */     if ((getKittyState() == 13 && entityIn instanceof EntityPlayer) || (getKittyState() == 8 && entityIn instanceof EntityItem) || (
/*  192 */       getKittyState() == 18 && entityIn instanceof MoCEntityKitty) || getKittyState() == 10) {
/*  193 */       return false;
/*      */     }
/*  195 */     return super.attackEntityAsMob(entityIn);
/*      */   }
/*      */
/*      */
/*      */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/*  200 */     if (super.attackEntityFrom(damagesource, i)) {
/*  201 */       Entity entity = damagesource.getTrueSource();
/*  202 */       if (entity != this && entity instanceof EntityLivingBase) {
/*  203 */         EntityLivingBase entityliving = (EntityLivingBase)entity;
/*  204 */         if (getKittyState() == 10) {
/*  205 */           List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(16.0D, 6.0D, 16.0D));
/*  206 */           for (int j = 0; j < list.size(); j++) {
/*  207 */             Entity entity1 = list.get(j);
/*  208 */             if (entity1 instanceof MoCEntityKitty && ((MoCEntityKitty)entity1).getKittyState() == 21) {
/*  209 */               ((MoCEntityKitty)entity1).setAttackTarget(entityliving);
/*  210 */               return true;
/*      */             }
/*      */           }
/*      */
/*  214 */           return true;
/*      */         }
/*  216 */         if (entityliving instanceof EntityPlayer && shouldAttackPlayers()) {
/*  217 */           if (getKittyState() < 2) {
/*  218 */             setAttackTarget(entityliving);
/*  219 */             setKittyState(-1);
/*      */           }
/*  221 */           if (getKittyState() == 19 || getKittyState() == 20 || getKittyState() == 21) {
/*  222 */             setAttackTarget(entityliving);
/*  223 */             setSitting(false);
/*  224 */             return true;
/*      */           }
/*  226 */           if (getKittyState() > 1 && getKittyState() != 10 && getKittyState() != 19 && getKittyState() != 20 &&
/*  227 */             getKittyState() != 21) {
/*  228 */             setKittyState(13);
/*  229 */             setSitting(false);
/*      */           }
/*  231 */           return true;
/*      */         }
/*  233 */         setAttackTarget(entityliving);
/*      */       }
/*  235 */       return true;
/*      */     }
/*  237 */     return false;
/*      */   }
/*      */
/*      */
/*      */
/*      */   protected boolean canDespawn() {
/*  243 */     if (MoCreatures.proxy.forceDespawns) {
/*  244 */       return (getKittyState() < 3);
/*      */     }
/*  246 */     return false;
/*      */   }
/*      */
/*      */
/*      */   private void changeKittyState(int i) {
/*  251 */     setKittyState(i);
/*  252 */     setSitting(false);
/*  253 */     this.kittytimer = 0;
/*  254 */     setOnTree(false);
/*  255 */     this.foundTree = false;
/*  256 */     setAttackTarget(null);
/*  257 */     this.itemAttackTarget = null;
/*      */   }
/*      */
/*      */   public boolean climbingTree() {
/*  261 */     return (getKittyState() == 16 && isOnLadder());
/*      */   }
/*      */
/*      */
/*      */
/*      */   public void fall(float f, float f1) {}
/*      */
/*      */
/*      */   protected Entity findPlayerToAttack() {
/*  270 */     if (this.world.getDifficulty().getDifficultyId()  > 0 && getKittyState() != 8 && getKittyState() != 10 && getKittyState() != 15 &&
/*  271 */       getKittyState() != 18 && getKittyState() != 19 && !isMovementCeased() && getIsHungry()) {
/*  272 */       return (Entity)getClosestTarget((Entity)this, 10.0D);
/*      */     }
/*      */
/*  275 */     return null;
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */   public EntityLivingBase getBoogey(double d, boolean flag) {
/*  282 */     EntityLivingBase entityliving = null;
/*  283 */     List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(d, 4.0D, d));
/*  284 */     for (int i = 0; i < list.size(); i++) {
/*  285 */       Entity entity = list.get(i);
/*  286 */       if (entity instanceof EntityLivingBase && !(entity instanceof MoCEntityDeer) && !(entity instanceof MoCEntityHorse) && (entity.width >= 0.5D || entity.height >= 0.5D) && (flag || !(entity instanceof EntityPlayer)))
/*      */       {
/*  288 */         entityliving = (EntityLivingBase)entity;
/*      */       }
/*      */     }
/*      */
/*  292 */     return entityliving;
/*      */   }
/*      */
/*      */
/*      */
/*      */   public EntityLiving getClosestTarget(Entity entity, double d) {
/*  298 */     double d1 = -1.0D;
/*  299 */     EntityLiving entityliving = null;
/*  300 */     List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(d, d, d));
/*  301 */     for (int i = 0; i < list.size(); i++) {
/*  302 */       Entity entity1 = list.get(i);
/*  303 */       if (entity1 instanceof EntityLiving && !(entity1 instanceof MoCEntityKitty) && !(entity1 instanceof EntityPlayer) && !(entity1 instanceof net.minecraft.entity.monster.EntityMob) && !(entity1 instanceof MoCEntityKittyBed) && !(entity1 instanceof MoCEntityLitterBox) && (entity1.width <= 0.5D || entity1.height <= 0.5D) && (!(entity instanceof drzhark.mocreatures.entity.IMoCEntity) || MoCreatures.proxy.enableHunters)) {
/*      */
/*      */
/*      */
/*      */
/*  308 */         double d2 = entity1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
/*  309 */         if ((d < 0.0D || d2 < d * d) && (d1 == -1.0D || d2 < d1) && ((EntityLiving)entity1).canEntityBeSeen(entity)) {
/*  310 */           d1 = d2;
/*  311 */           entityliving = (EntityLiving)entity1;
/*      */         }
/*      */       }
/*      */     }
/*  315 */     return entityliving;
/*      */   }
/*      */
/*      */
/*      */   protected Item getDropItem() {
/*  320 */     return null;
/*      */   }
/*      */
/*      */   public ResourceLocation getEmoteIcon() {
/*  324 */     switch (getKittyState()) {
/*      */       case -1:
/*  326 */         return MoCreatures.proxy.getTexture("emoticon2.png");
/*      */
/*      */       case 3:
/*  329 */         return MoCreatures.proxy.getTexture("emoticon3.png");
/*      */
/*      */       case 4:
/*  332 */         return MoCreatures.proxy.getTexture("emoticon4.png");
/*      */
/*      */       case 5:
/*  335 */         return MoCreatures.proxy.getTexture("emoticon5.png");
/*      */
/*      */       case 7:
/*  338 */         return MoCreatures.proxy.getTexture("emoticon7.png");
/*      */
/*      */       case 8:
/*  341 */         return MoCreatures.proxy.getTexture("emoticon8.png");
/*      */
/*      */       case 9:
/*  344 */         return MoCreatures.proxy.getTexture("emoticon9.png");
/*      */
/*      */       case 10:
/*  347 */         return MoCreatures.proxy.getTexture("emoticon10.png");
/*      */
/*      */       case 11:
/*  350 */         return MoCreatures.proxy.getTexture("emoticon11.png");
/*      */
/*      */       case 12:
/*  353 */         return MoCreatures.proxy.getTexture("emoticon12.png");
/*      */
/*      */       case 13:
/*  356 */         return MoCreatures.proxy.getTexture("emoticon13.png");
/*      */
/*      */       case 16:
/*  359 */         return MoCreatures.proxy.getTexture("emoticon16.png");
/*      */
/*      */       case 17:
/*  362 */         return MoCreatures.proxy.getTexture("emoticon17.png");
/*      */
/*      */       case 18:
/*  365 */         return MoCreatures.proxy.getTexture("emoticon9.png");
/*      */
/*      */       case 19:
/*  368 */         return MoCreatures.proxy.getTexture("emoticon19.png");
/*      */
/*      */       case 20:
/*  371 */         return MoCreatures.proxy.getTexture("emoticon19.png");
/*      */
/*      */       case 21:
/*  374 */         return MoCreatures.proxy.getTexture("emoticon10.png");
/*      */     }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*  383 */     return MoCreatures.proxy.getTexture("emoticon1.png");
/*      */   }
/*      */
/*      */
/*      */
/*      */   protected SoundEvent getDeathSound() {
/*  389 */     if (getKittyState() == 10) {
/*  390 */       return MoCSoundEvents.ENTITY_KITTY_DEATH_BABY;
/*      */     }
/*  392 */     return MoCSoundEvents.ENTITY_KITTY_DEATH;
/*      */   }
/*      */
/*      */
/*      */
/*      */   protected SoundEvent getHurtSound(DamageSource source) {
/*  398 */     if (getKittyState() == 10) {
/*  399 */       return MoCSoundEvents.ENTITY_KITTY_HURT_BABY;
/*      */     }
/*  401 */     return MoCSoundEvents.ENTITY_KITTY_HURT;
/*      */   }
/*      */
/*      */
/*      */
/*      */   protected SoundEvent getAmbientSound() {
/*  407 */     if (getKittyState() == 4) {
/*  408 */       if (getRidingEntity() != null) {
/*  409 */         MoCEntityKittyBed entitykittybed = (MoCEntityKittyBed)getRidingEntity();
/*  410 */         if (entitykittybed != null && !entitykittybed.getHasMilk()) {
/*  411 */           return MoCSoundEvents.ENTITY_KITTY_DRINKING;
/*      */         }
/*  413 */         if (entitykittybed != null && !entitykittybed.getHasFood()) {
/*  414 */           return MoCSoundEvents.ENTITY_KITTY_EATING;
/*      */         }
/*      */       }
/*  417 */       return null;
/*      */     }
/*  419 */     if (getKittyState() == 6) {
/*  420 */       return MoCSoundEvents.ENTITY_KITTY_LITTER;
/*      */     }
/*  422 */     if (getKittyState() == 3) {
/*  423 */       return MoCSoundEvents.ENTITY_KITTY_HUNGRY;
/*      */     }
/*  425 */     if (getKittyState() == 10) {
/*  426 */       return MoCSoundEvents.ENTITY_KITTY_AMBIENT_BABY;
/*      */     }
/*  428 */     if (getKittyState() == 13) {
/*  429 */       return MoCSoundEvents.ENTITY_KITTY_ANGRY;
/*      */     }
/*  431 */     if (getKittyState() == 17) {
/*  432 */       return MoCSoundEvents.ENTITY_KITTY_TRAPPED;
/*      */     }
/*  434 */     if (getKittyState() == 18 || getKittyState() == 12) {
/*  435 */       return MoCSoundEvents.ENTITY_KITTY_PURR;
/*      */     }
/*  437 */     return MoCSoundEvents.ENTITY_KITTY_AMBIENT;
/*      */   }
/*      */
/*      */
/*      */   public EntityLiving getKittyStuff(Entity entity, double d, boolean flag) {
/*  442 */     double d1 = -1.0D;
/*  443 */     Object obj = null;
/*  444 */     List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(entity, getEntityBoundingBox().expand(d, d, d));
/*  445 */     for (int i = 0; i < list.size(); i++) {
/*  446 */       Entity entity1 = list.get(i);
/*  447 */       if (flag) {
/*  448 */         if (entity1 instanceof MoCEntityLitterBox) {
/*      */
/*      */
/*  451 */           MoCEntityLitterBox entitylitterbox = (MoCEntityLitterBox)entity1;
/*  452 */           if (!entitylitterbox.getUsedLitter()) {
/*      */
/*      */
/*  455 */             double d2 = entity1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
/*  456 */             if ((d < 0.0D || d2 < d * d) && (d1 == -1.0D || d2 < d1) && entitylitterbox.canEntityBeSeen(entity)) {
/*  457 */               d1 = d2;
/*  458 */               obj = entitylitterbox;
/*      */             }
/*      */           }
/*      */         }
/*  462 */       } else if (entity1 instanceof MoCEntityKittyBed) {
/*      */
/*      */
/*  465 */         MoCEntityKittyBed entitykittybed = (MoCEntityKittyBed)entity1;
/*  466 */         double d3 = entity1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
/*  467 */         if ((d < 0.0D || d3 < d * d) && (d1 == -1.0D || d3 < d1) && entitykittybed.canEntityBeSeen(entity)) {
/*  468 */           d1 = d3;
/*  469 */           obj = entitykittybed;
/*      */         }
/*      */       }
/*      */     }
/*  473 */     return (EntityLiving)obj;
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
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/*  508 */     Boolean tameResult = processTameInteract(player, hand);
/*  509 */     if (tameResult != null) {
/*  510 */       return tameResult.booleanValue();
/*      */     }
/*      */
/*  513 */     ItemStack stack = player.getHeldItem(hand);
/*  514 */     if (getKittyState() == 2 && !stack.isEmpty() && stack.getItem() == MoCItems.medallion) {
/*  515 */       if (!this.world.isRemote) {
/*  516 */         MoCTools.tameWithName(player, (IMoCTameable)this);
/*      */       }
/*  518 */       if (getIsTamed()) {
/*  519 */         stack.shrink(1);
/*  520 */         if (stack.isEmpty()) {
/*  521 */           player.setHeldItem(hand, ItemStack.EMPTY);
/*      */         }
/*  523 */         changeKittyState(3);
/*  524 */         setHealth(getMaxHealth());
/*  525 */         return true;
/*      */       }
/*  527 */       return false;
/*      */     }
/*  529 */     if (getKittyState() == 7 && !stack.isEmpty() && (stack
/*  530 */       .getItem() == Items.CAKE || stack.getItem() == Items.FISH || stack.getItem() == Items.COOKED_FISH)) {
/*  531 */       stack.shrink(1);
/*  532 */       if (stack.isEmpty()) {
/*  533 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/*  535 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_KITTY_EATING);
/*  536 */       setHealth(getMaxHealth());
/*  537 */       changeKittyState(9);
/*  538 */       return true;
/*      */     }
/*  540 */     if (getKittyState() == 11 && !stack.isEmpty() && stack.getItem() == MoCItems.woolball) {
/*  541 */       stack.shrink(1);
/*  542 */       if (stack.isEmpty()) {
/*  543 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/*  545 */       setKittyState(8);
/*  546 */       if (!this.world.isRemote) {
/*  547 */         EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY + 1.0D, this.posZ, new ItemStack((Item)MoCItems.woolball, 1));
/*  548 */         entityitem.setPickupDelay(30);
/*  549 */         entityitem.setNoDespawn();
/*  550 */         this.world.spawnEntity((Entity)entityitem);
/*  551 */         entityitem.motionY += (this.world.rand.nextFloat() * 0.05F);
/*  552 */         entityitem.motionX += ((this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.3F);
/*  553 */         entityitem.motionZ += ((this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.3F);
/*  554 */         this.itemAttackTarget = entityitem;
/*      */       }
/*  556 */       return true;
/*      */     }
/*  558 */     if (getKittyState() == 13 && !stack.isEmpty() && (stack.getItem() == Items.FISH || stack.getItem() == Items.COOKED_FISH)) {
/*  559 */       stack.shrink(1);
/*  560 */       if (stack.isEmpty()) {
/*  561 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/*  563 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_KITTY_EATING);
/*  564 */       setHealth(getMaxHealth());
/*  565 */       changeKittyState(7);
/*  566 */       return true;
/*      */     }
/*  568 */     if (!stack.isEmpty() && getKittyState() > 2 && (stack.getItem() == MoCItems.medallion || stack.getItem() == Items.BOOK)) {
/*  569 */       if (!this.world.isRemote) {
/*  570 */         MoCTools.tameWithName(player, (IMoCTameable)this);
/*      */       }
/*      */
/*  573 */       return true;
/*      */     }
/*  575 */     if (!stack.isEmpty() && getKittyState() > 2 && pickable() && stack.getItem() == Items.LEAD) {
/*  576 */       if (startRiding((Entity)player)) {
/*  577 */         changeKittyState(14);
/*      */       }
/*  579 */       return true;
/*      */     }
/*  581 */     if (!stack.isEmpty() && getKittyState() > 2 && whipeable() && stack.getItem() == MoCItems.whip) {
/*  582 */       setSitting(!getIsSitting());
/*  583 */       return true;
/*      */     }
/*      */
/*  586 */     if (stack.isEmpty() && getKittyState() > 2 && pickable()) {
/*  587 */       if (startRiding((Entity)player)) {
/*  588 */         changeKittyState(15);
/*      */       }
/*  590 */       return true;
/*      */     }
/*  592 */     if (stack.isEmpty() && getKittyState() == 15) {
/*  593 */       changeKittyState(7);
/*  594 */       return true;
/*      */     }
/*  596 */     if (getKittyState() == 14 && getRidingEntity() != null) {
/*  597 */       changeKittyState(7);
/*  598 */       return true;
/*      */     }
/*      */
/*  601 */     return super.processInteract(player, hand);
/*      */   }
/*      */
/*      */
/*      */   public boolean isMovementCeased() {
/*  606 */     return (getIsSitting() || getKittyState() == 6 || (getKittyState() == 16 && getOnTree()) || getKittyState() == 12 ||
/*  607 */       getKittyState() == 17 || getKittyState() == 14 || getKittyState() == 20 || getKittyState() == 23);
/*      */   }
/*      */
/*      */
/*      */   public boolean isOnLadder() {
/*  612 */     if (getKittyState() == 16) {
/*  613 */       return (this.collidedHorizontally && getOnTree());
/*      */     }
/*  615 */     return super.isOnLadder();
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
/*      */   public void onLivingUpdate() {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield world : Lnet/minecraft/world/World;
/*      */     //   4: getfield isRemote : Z
/*      */     //   7: ifne -> 3560
/*      */     //   10: aload_0
/*      */     //   11: invokevirtual getIsAdult : ()Z
/*      */     //   14: ifne -> 32
/*      */     //   17: aload_0
/*      */     //   18: invokevirtual getKittyState : ()I
/*      */     //   21: bipush #10
/*      */     //   23: if_icmpeq -> 32
/*      */     //   26: aload_0
/*      */     //   27: bipush #10
/*      */     //   29: invokevirtual setKittyState : (I)V
/*      */     //   32: aload_0
/*      */     //   33: invokevirtual getKittyState : ()I
/*      */     //   36: bipush #12
/*      */     //   38: if_icmpeq -> 45
/*      */     //   41: aload_0
/*      */     //   42: invokespecial onLivingUpdate : ()V
/*      */     //   45: aload_0
/*      */     //   46: getfield rand : Ljava/util/Random;
/*      */     //   49: sipush #200
/*      */     //   52: invokevirtual nextInt : (I)I
/*      */     //   55: ifne -> 74
/*      */     //   58: aload_0
/*      */     //   59: aload_0
/*      */     //   60: invokevirtual getIsEmo : ()Z
/*      */     //   63: ifne -> 70
/*      */     //   66: iconst_1
/*      */     //   67: goto -> 71
/*      */     //   70: iconst_0
/*      */     //   71: invokevirtual setIsEmo : (Z)V
/*      */     //   74: aload_0
/*      */     //   75: invokevirtual getIsAdult : ()Z
/*      */     //   78: ifne -> 118
/*      */     //   81: aload_0
/*      */     //   82: getfield rand : Ljava/util/Random;
/*      */     //   85: sipush #200
/*      */     //   88: invokevirtual nextInt : (I)I
/*      */     //   91: ifne -> 118
/*      */     //   94: aload_0
/*      */     //   95: aload_0
/*      */     //   96: invokevirtual getEdad : ()I
/*      */     //   99: iconst_1
/*      */     //   100: iadd
/*      */     //   101: invokevirtual setEdad : (I)V
/*      */     //   104: aload_0
/*      */     //   105: invokevirtual getEdad : ()I
/*      */     //   108: bipush #100
/*      */     //   110: if_icmplt -> 118
/*      */     //   113: aload_0
/*      */     //   114: iconst_1
/*      */     //   115: invokevirtual setAdult : (Z)V
/*      */     //   118: aload_0
/*      */     //   119: invokevirtual getIsHungry : ()Z
/*      */     //   122: ifne -> 149
/*      */     //   125: aload_0
/*      */     //   126: invokevirtual getIsSitting : ()Z
/*      */     //   129: ifne -> 149
/*      */     //   132: aload_0
/*      */     //   133: getfield rand : Ljava/util/Random;
/*      */     //   136: bipush #100
/*      */     //   138: invokevirtual nextInt : (I)I
/*      */     //   141: ifne -> 149
/*      */     //   144: aload_0
/*      */     //   145: iconst_1
/*      */     //   146: invokevirtual setHungry : (Z)V
/*      */     //   149: aload_0
/*      */     //   150: invokevirtual isRiding : ()Z
/*      */     //   153: ifeq -> 161
/*      */     //   156: aload_0
/*      */     //   157: invokestatic dismountSneakingPlayer : (Lnet/minecraft/entity/EntityLiving;)Z
/*      */     //   160: pop
/*      */     //   161: aload_0
/*      */     //   162: invokevirtual getKittyState : ()I
/*      */     //   165: tableswitch default -> 3551, -1 -> 280, 0 -> 3543, 1 -> 286, 2 -> 422, 3 -> 443, 4 -> 620, 5 -> 716, 6 -> 859, 7 -> 924, 8 -> 1106, 9 -> 1234, 10 -> 1397, 11 -> 1825, 12 -> 1926, 13 -> 2009, 14 -> 2151, 15 -> 2271, 16 -> 2309, 17 -> 2930, 18 -> 2957, 19 -> 3102, 20 -> 3195, 21 -> 3375, 22 -> 3551, 23 -> 283
/*      */     //   280: goto -> 3564
/*      */     //   283: goto -> 3564
/*      */     //   286: aload_0
/*      */     //   287: getfield rand : Ljava/util/Random;
/*      */     //   290: bipush #10
/*      */     //   292: invokevirtual nextInt : (I)I
/*      */     //   295: ifne -> 319
/*      */     //   298: aload_0
/*      */     //   299: ldc2_w 6.0
/*      */     //   302: iconst_1
/*      */     //   303: invokevirtual getBoogey : (DZ)Lnet/minecraft/entity/EntityLivingBase;
/*      */     //   306: astore_1
/*      */     //   307: aload_1
/*      */     //   308: ifnull -> 3564
/*      */     //   311: aload_0
/*      */     //   312: aload_1
/*      */     //   313: invokestatic runLikeHell : (Lnet/minecraft/entity/EntityLiving;Lnet/minecraft/entity/Entity;)V
/*      */     //   316: goto -> 3564
/*      */     //   319: aload_0
/*      */     //   320: invokevirtual getIsHungry : ()Z
/*      */     //   323: ifeq -> 3564
/*      */     //   326: aload_0
/*      */     //   327: getfield rand : Ljava/util/Random;
/*      */     //   330: bipush #10
/*      */     //   332: invokevirtual nextInt : (I)I
/*      */     //   335: ifeq -> 341
/*      */     //   338: goto -> 3564
/*      */     //   341: aload_0
/*      */     //   342: aload_0
/*      */     //   343: ldc2_w 10.0
/*      */     //   346: getstatic net/minecraft/init/Items.COOKED_FISH : Lnet/minecraft/item/Item;
/*      */     //   349: getstatic net/minecraft/init/Items.COOKED_FISH : Lnet/minecraft/item/Item;
/*      */     //   352: invokevirtual getClosestItem : (Lnet/minecraft/entity/Entity;DLnet/minecraft/item/Item;Lnet/minecraft/item/Item;)Lnet/minecraft/entity/item/EntityItem;
/*      */     //   355: astore_1
/*      */     //   356: aload_1
/*      */     //   357: ifnonnull -> 363
/*      */     //   360: goto -> 3564
/*      */     //   363: aload_1
/*      */     //   364: aload_0
/*      */     //   365: invokevirtual getDistance : (Lnet/minecraft/entity/Entity;)F
/*      */     //   368: fstore_2
/*      */     //   369: fload_2
/*      */     //   370: fconst_2
/*      */     //   371: fcmpl
/*      */     //   372: ifle -> 381
/*      */     //   375: aload_0
/*      */     //   376: aload_1
/*      */     //   377: fload_2
/*      */     //   378: invokevirtual getMyOwnPath : (Lnet/minecraft/entity/Entity;F)V
/*      */     //   381: fload_2
/*      */     //   382: fconst_2
/*      */     //   383: fcmpg
/*      */     //   384: ifge -> 3564
/*      */     //   387: aload_1
/*      */     //   388: ifnull -> 3564
/*      */     //   391: aload_0
/*      */     //   392: getfield deathTime : I
/*      */     //   395: ifne -> 3564
/*      */     //   398: aload_1
/*      */     //   399: invokevirtual setDead : ()V
/*      */     //   402: aload_0
/*      */     //   403: getstatic drzhark/mocreatures/init/MoCSoundEvents.ENTITY_KITTY_EATING : Lnet/minecraft/util/SoundEvent;
/*      */     //   406: invokestatic playCustomSound : (Lnet/minecraft/entity/Entity;Lnet/minecraft/util/SoundEvent;)V
/*      */     //   409: aload_0
/*      */     //   410: iconst_0
/*      */     //   411: invokevirtual setHungry : (Z)V
/*      */     //   414: aload_0
/*      */     //   415: iconst_2
/*      */     //   416: invokevirtual setKittyState : (I)V
/*      */     //   419: goto -> 3564
/*      */     //   422: aload_0
/*      */     //   423: ldc2_w 6.0
/*      */     //   426: iconst_0
/*      */     //   427: invokevirtual getBoogey : (DZ)Lnet/minecraft/entity/EntityLivingBase;
/*      */     //   430: astore_3
/*      */     //   431: aload_3
/*      */     //   432: ifnull -> 3564
/*      */     //   435: aload_0
/*      */     //   436: aload_3
/*      */     //   437: invokestatic runLikeHell : (Lnet/minecraft/entity/EntityLiving;Lnet/minecraft/entity/Entity;)V
/*      */     //   440: goto -> 3564
/*      */     //   443: aload_0
/*      */     //   444: dup
/*      */     //   445: getfield kittytimer : I
/*      */     //   448: iconst_1
/*      */     //   449: iadd
/*      */     //   450: putfield kittytimer : I
/*      */     //   453: aload_0
/*      */     //   454: getfield kittytimer : I
/*      */     //   457: sipush #500
/*      */     //   460: if_icmple -> 507
/*      */     //   463: aload_0
/*      */     //   464: getfield rand : Ljava/util/Random;
/*      */     //   467: sipush #200
/*      */     //   470: invokevirtual nextInt : (I)I
/*      */     //   473: ifne -> 485
/*      */     //   476: aload_0
/*      */     //   477: bipush #13
/*      */     //   479: invokespecial changeKittyState : (I)V
/*      */     //   482: goto -> 3564
/*      */     //   485: aload_0
/*      */     //   486: getfield rand : Ljava/util/Random;
/*      */     //   489: sipush #500
/*      */     //   492: invokevirtual nextInt : (I)I
/*      */     //   495: ifne -> 507
/*      */     //   498: aload_0
/*      */     //   499: bipush #7
/*      */     //   501: invokespecial changeKittyState : (I)V
/*      */     //   504: goto -> 3564
/*      */     //   507: aload_0
/*      */     //   508: getfield rand : Ljava/util/Random;
/*      */     //   511: bipush #20
/*      */     //   513: invokevirtual nextInt : (I)I
/*      */     //   516: ifeq -> 522
/*      */     //   519: goto -> 3564
/*      */     //   522: aload_0
/*      */     //   523: aload_0
/*      */     //   524: ldc2_w 18.0
/*      */     //   527: iconst_0
/*      */     //   528: invokevirtual getKittyStuff : (Lnet/minecraft/entity/Entity;DZ)Lnet/minecraft/entity/EntityLiving;
/*      */     //   531: checkcast drzhark/mocreatures/entity/item/MoCEntityKittyBed
/*      */     //   534: astore #4
/*      */     //   536: aload #4
/*      */     //   538: ifnull -> 3564
/*      */     //   541: aload #4
/*      */     //   543: invokevirtual isBeingRidden : ()Z
/*      */     //   546: ifne -> 3564
/*      */     //   549: aload #4
/*      */     //   551: invokevirtual getHasMilk : ()Z
/*      */     //   554: ifne -> 568
/*      */     //   557: aload #4
/*      */     //   559: invokevirtual getHasFood : ()Z
/*      */     //   562: ifne -> 568
/*      */     //   565: goto -> 3564
/*      */     //   568: aload #4
/*      */     //   570: aload_0
/*      */     //   571: invokevirtual getDistance : (Lnet/minecraft/entity/Entity;)F
/*      */     //   574: fstore #5
/*      */     //   576: fload #5
/*      */     //   578: fconst_2
/*      */     //   579: fcmpl
/*      */     //   580: ifle -> 591
/*      */     //   583: aload_0
/*      */     //   584: aload #4
/*      */     //   586: fload #5
/*      */     //   588: invokevirtual getMyOwnPath : (Lnet/minecraft/entity/Entity;F)V
/*      */     //   591: fload #5
/*      */     //   593: fconst_2
/*      */     //   594: fcmpg
/*      */     //   595: ifge -> 3564
/*      */     //   598: aload_0
/*      */     //   599: aload #4
/*      */     //   601: invokevirtual startRiding : (Lnet/minecraft/entity/Entity;)Z
/*      */     //   604: ifeq -> 3564
/*      */     //   607: aload_0
/*      */     //   608: iconst_4
/*      */     //   609: invokespecial changeKittyState : (I)V
/*      */     //   612: aload_0
/*      */     //   613: iconst_1
/*      */     //   614: invokevirtual setSitting : (Z)V
/*      */     //   617: goto -> 3564
/*      */     //   620: aload_0
/*      */     //   621: invokevirtual getRidingEntity : ()Lnet/minecraft/entity/Entity;
/*      */     //   624: ifnull -> 673
/*      */     //   627: aload_0
/*      */     //   628: invokevirtual getRidingEntity : ()Lnet/minecraft/entity/Entity;
/*      */     //   631: checkcast drzhark/mocreatures/entity/item/MoCEntityKittyBed
/*      */     //   634: astore #6
/*      */     //   636: aload #6
/*      */     //   638: ifnull -> 670
/*      */     //   641: aload #6
/*      */     //   643: invokevirtual getHasMilk : ()Z
/*      */     //   646: ifne -> 670
/*      */     //   649: aload #6
/*      */     //   651: invokevirtual getHasFood : ()Z
/*      */     //   654: ifne -> 670
/*      */     //   657: aload_0
/*      */     //   658: aload_0
/*      */     //   659: invokevirtual getMaxHealth : ()F
/*      */     //   662: invokevirtual setHealth : (F)V
/*      */     //   665: aload_0
/*      */     //   666: iconst_5
/*      */     //   667: invokespecial changeKittyState : (I)V
/*      */     //   670: goto -> 686
/*      */     //   673: aload_0
/*      */     //   674: aload_0
/*      */     //   675: invokevirtual getMaxHealth : ()F
/*      */     //   678: invokevirtual setHealth : (F)V
/*      */     //   681: aload_0
/*      */     //   682: iconst_5
/*      */     //   683: invokespecial changeKittyState : (I)V
/*      */     //   686: aload_0
/*      */     //   687: getfield rand : Ljava/util/Random;
/*      */     //   690: sipush #2500
/*      */     //   693: invokevirtual nextInt : (I)I
/*      */     //   696: ifne -> 3564
/*      */     //   699: aload_0
/*      */     //   700: aload_0
/*      */     //   701: invokevirtual getMaxHealth : ()F
/*      */     //   704: invokevirtual setHealth : (F)V
/*      */     //   707: aload_0
/*      */     //   708: bipush #7
/*      */     //   710: invokespecial changeKittyState : (I)V
/*      */     //   713: goto -> 3564
/*      */     //   716: aload_0
/*      */     //   717: dup
/*      */     //   718: getfield kittytimer : I
/*      */     //   721: iconst_1
/*      */     //   722: iadd
/*      */     //   723: putfield kittytimer : I
/*      */     //   726: aload_0
/*      */     //   727: getfield kittytimer : I
/*      */     //   730: sipush #2000
/*      */     //   733: if_icmple -> 758
/*      */     //   736: aload_0
/*      */     //   737: getfield rand : Ljava/util/Random;
/*      */     //   740: sipush #1000
/*      */     //   743: invokevirtual nextInt : (I)I
/*      */     //   746: ifne -> 758
/*      */     //   749: aload_0
/*      */     //   750: bipush #13
/*      */     //   752: invokespecial changeKittyState : (I)V
/*      */     //   755: goto -> 3564
/*      */     //   758: aload_0
/*      */     //   759: getfield rand : Ljava/util/Random;
/*      */     //   762: bipush #20
/*      */     //   764: invokevirtual nextInt : (I)I
/*      */     //   767: ifeq -> 773
/*      */     //   770: goto -> 3564
/*      */     //   773: aload_0
/*      */     //   774: aload_0
/*      */     //   775: ldc2_w 18.0
/*      */     //   778: iconst_1
/*      */     //   779: invokevirtual getKittyStuff : (Lnet/minecraft/entity/Entity;DZ)Lnet/minecraft/entity/EntityLiving;
/*      */     //   782: checkcast drzhark/mocreatures/entity/item/MoCEntityLitterBox
/*      */     //   785: astore #6
/*      */     //   787: aload #6
/*      */     //   789: ifnull -> 3564
/*      */     //   792: aload #6
/*      */     //   794: invokevirtual isBeingRidden : ()Z
/*      */     //   797: ifne -> 3564
/*      */     //   800: aload #6
/*      */     //   802: invokevirtual getUsedLitter : ()Z
/*      */     //   805: ifeq -> 811
/*      */     //   808: goto -> 3564
/*      */     //   811: aload #6
/*      */     //   813: aload_0
/*      */     //   814: invokevirtual getDistance : (Lnet/minecraft/entity/Entity;)F
/*      */     //   817: fstore #7
/*      */     //   819: fload #7
/*      */     //   821: fconst_2
/*      */     //   822: fcmpl
/*      */     //   823: ifle -> 834
/*      */     //   826: aload_0
/*      */     //   827: aload #6
/*      */     //   829: fload #7
/*      */     //   831: invokevirtual getMyOwnPath : (Lnet/minecraft/entity/Entity;F)V
/*      */     //   834: fload #7
/*      */     //   836: fconst_2
/*      */     //   837: fcmpg
/*      */     //   838: ifge -> 3564
/*      */     //   841: aload_0
/*      */     //   842: aload #6
/*      */     //   844: invokevirtual startRiding : (Lnet/minecraft/entity/Entity;)Z
/*      */     //   847: ifeq -> 3564
/*      */     //   850: aload_0
/*      */     //   851: bipush #6
/*      */     //   853: invokespecial changeKittyState : (I)V
/*      */     //   856: goto -> 3564
/*      */     //   859: aload_0
/*      */     //   860: dup
/*      */     //   861: getfield kittytimer : I
/*      */     //   864: iconst_1
/*      */     //   865: iadd
/*      */     //   866: putfield kittytimer : I
/*      */     //   869: aload_0
/*      */     //   870: getfield kittytimer : I
/*      */     //   873: sipush #300
/*      */     //   876: if_icmpgt -> 882
/*      */     //   879: goto -> 3564
/*      */     //   882: aload_0
/*      */     //   883: getstatic drzhark/mocreatures/init/MoCSoundEvents.ENTITY_KITTY_LITTER : Lnet/minecraft/util/SoundEvent;
/*      */     //   886: invokestatic playCustomSound : (Lnet/minecraft/entity/Entity;Lnet/minecraft/util/SoundEvent;)V
/*      */     //   889: aload_0
/*      */     //   890: invokevirtual getRidingEntity : ()Lnet/minecraft/entity/Entity;
/*      */     //   893: checkcast drzhark/mocreatures/entity/item/MoCEntityLitterBox
/*      */     //   896: astore #8
/*      */     //   898: aload #8
/*      */     //   900: ifnull -> 915
/*      */     //   903: aload #8
/*      */     //   905: iconst_1
/*      */     //   906: invokevirtual setUsedLitter : (Z)V
/*      */     //   909: aload #8
/*      */     //   911: iconst_0
/*      */     //   912: putfield littertime : I
/*      */     //   915: aload_0
/*      */     //   916: bipush #7
/*      */     //   918: invokespecial changeKittyState : (I)V
/*      */     //   921: goto -> 3564
/*      */     //   924: aload_0
/*      */     //   925: invokevirtual getIsSitting : ()Z
/*      */     //   928: ifeq -> 934
/*      */     //   931: goto -> 3564
/*      */     //   934: aload_0
/*      */     //   935: getfield rand : Ljava/util/Random;
/*      */     //   938: bipush #20
/*      */     //   940: invokevirtual nextInt : (I)I
/*      */     //   943: ifne -> 1002
/*      */     //   946: aload_0
/*      */     //   947: getfield world : Lnet/minecraft/world/World;
/*      */     //   950: aload_0
/*      */     //   951: ldc2_w 12.0
/*      */     //   954: invokevirtual getClosestPlayerToEntity : (Lnet/minecraft/entity/Entity;D)Lnet/minecraft/entity/player/EntityPlayer;
/*      */     //   957: astore #9
/*      */     //   959: aload #9
/*      */     //   961: ifnull -> 1002
/*      */     //   964: aload #9
/*      */     //   966: getfield inventory : Lnet/minecraft/entity/player/InventoryPlayer;
/*      */     //   969: invokevirtual getCurrentItem : ()Lnet/minecraft/item/ItemStack;
/*      */     //   972: astore #10
/*      */     //   974: aload #10
/*      */     //   976: invokevirtual isEmpty : ()Z
/*      */     //   979: ifne -> 1002
/*      */     //   982: aload #10
/*      */     //   984: invokevirtual getItem : ()Lnet/minecraft/item/Item;
/*      */     //   987: getstatic drzhark/mocreatures/init/MoCItems.woolball : Ldrzhark/mocreatures/item/MoCItem;
/*      */     //   990: if_acmpne -> 1002
/*      */     //   993: aload_0
/*      */     //   994: bipush #11
/*      */     //   996: invokespecial changeKittyState : (I)V
/*      */     //   999: goto -> 3564
/*      */     //   1002: aload_0
/*      */     //   1003: getfield inWater : Z
/*      */     //   1006: ifeq -> 1031
/*      */     //   1009: aload_0
/*      */     //   1010: getfield rand : Ljava/util/Random;
/*      */     //   1013: sipush #500
/*      */     //   1016: invokevirtual nextInt : (I)I
/*      */     //   1019: ifne -> 1031
/*      */     //   1022: aload_0
/*      */     //   1023: bipush #13
/*      */     //   1025: invokespecial changeKittyState : (I)V
/*      */     //   1028: goto -> 3564
/*      */     //   1031: aload_0
/*      */     //   1032: getfield rand : Ljava/util/Random;
/*      */     //   1035: sipush #500
/*      */     //   1038: invokevirtual nextInt : (I)I
/*      */     //   1041: ifne -> 1063
/*      */     //   1044: aload_0
/*      */     //   1045: getfield world : Lnet/minecraft/world/World;
/*      */     //   1048: invokevirtual isDaytime : ()Z
/*      */     //   1051: ifne -> 1063
/*      */     //   1054: aload_0
/*      */     //   1055: bipush #12
/*      */     //   1057: invokespecial changeKittyState : (I)V
/*      */     //   1060: goto -> 3564
/*      */     //   1063: aload_0
/*      */     //   1064: getfield rand : Ljava/util/Random;
/*      */     //   1067: sipush #2000
/*      */     //   1070: invokevirtual nextInt : (I)I
/*      */     //   1073: ifne -> 1084
/*      */     //   1076: aload_0
/*      */     //   1077: iconst_3
/*      */     //   1078: invokespecial changeKittyState : (I)V
/*      */     //   1081: goto -> 3564
/*      */     //   1084: aload_0
/*      */     //   1085: getfield rand : Ljava/util/Random;
/*      */     //   1088: sipush #4000
/*      */     //   1091: invokevirtual nextInt : (I)I
/*      */     //   1094: ifne -> 3564
/*      */     //   1097: aload_0
/*      */     //   1098: bipush #16
/*      */     //   1100: invokespecial changeKittyState : (I)V
/*      */     //   1103: goto -> 3564
/*      */     //   1106: aload_0
/*      */     //   1107: getfield inWater : Z
/*      */     //   1110: ifeq -> 1135
/*      */     //   1113: aload_0
/*      */     //   1114: getfield rand : Ljava/util/Random;
/*      */     //   1117: sipush #200
/*      */     //   1120: invokevirtual nextInt : (I)I
/*      */     //   1123: ifne -> 1135
/*      */     //   1126: aload_0
/*      */     //   1127: bipush #13
/*      */     //   1129: invokespecial changeKittyState : (I)V
/*      */     //   1132: goto -> 3564
/*      */     //   1135: aload_0
/*      */     //   1136: getfield itemAttackTarget : Lnet/minecraft/entity/item/EntityItem;
/*      */     //   1139: ifnull -> 1205
/*      */     //   1142: aload_0
/*      */     //   1143: getfield itemAttackTarget : Lnet/minecraft/entity/item/EntityItem;
/*      */     //   1146: instanceof net/minecraft/entity/item/EntityItem
/*      */     //   1149: ifeq -> 1205
/*      */     //   1152: aload_0
/*      */     //   1153: invokevirtual getAttackTarget : ()Lnet/minecraft/entity/EntityLivingBase;
/*      */     //   1156: ifnull -> 1205
/*      */     //   1159: aload_0
/*      */     //   1160: aload_0
/*      */     //   1161: invokevirtual getAttackTarget : ()Lnet/minecraft/entity/EntityLivingBase;
/*      */     //   1164: invokevirtual getDistance : (Lnet/minecraft/entity/Entity;)F
/*      */     //   1167: fstore #9
/*      */     //   1169: fload #9
/*      */     //   1171: ldc_w 1.5
/*      */     //   1174: fcmpg
/*      */     //   1175: ifge -> 1205
/*      */     //   1178: aload_0
/*      */     //   1179: invokevirtual swingArm : ()V
/*      */     //   1182: aload_0
/*      */     //   1183: getfield rand : Ljava/util/Random;
/*      */     //   1186: bipush #10
/*      */     //   1188: invokevirtual nextInt : (I)I
/*      */     //   1191: ifne -> 1205
/*      */     //   1194: aload_0
/*      */     //   1195: aload_0
/*      */     //   1196: getfield itemAttackTarget : Lnet/minecraft/entity/item/EntityItem;
/*      */     //   1199: ldc_w 0.3
/*      */     //   1202: invokestatic bigsmack : (Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/Entity;F)V
/*      */     //   1205: aload_0
/*      */     //   1206: invokevirtual getAttackTarget : ()Lnet/minecraft/entity/EntityLivingBase;
/*      */     //   1209: ifnull -> 1225
/*      */     //   1212: aload_0
/*      */     //   1213: getfield rand : Ljava/util/Random;
/*      */     //   1216: sipush #1000
/*      */     //   1219: invokevirtual nextInt : (I)I
/*      */     //   1222: ifne -> 3564
/*      */     //   1225: aload_0
/*      */     //   1226: bipush #7
/*      */     //   1228: invokespecial changeKittyState : (I)V
/*      */     //   1231: goto -> 3564
/*      */     //   1234: aload_0
/*      */     //   1235: dup
/*      */     //   1236: getfield kittytimer : I
/*      */     //   1239: iconst_1
/*      */     //   1240: iadd
/*      */     //   1241: putfield kittytimer : I
/*      */     //   1244: aload_0
/*      */     //   1245: getfield rand : Ljava/util/Random;
/*      */     //   1248: bipush #50
/*      */     //   1250: invokevirtual nextInt : (I)I
/*      */     //   1253: ifne -> 1378
/*      */     //   1256: aload_0
/*      */     //   1257: getfield world : Lnet/minecraft/world/World;
/*      */     //   1260: aload_0
/*      */     //   1261: aload_0
/*      */     //   1262: invokevirtual getEntityBoundingBox : ()Lnet/minecraft/util/math/AxisAlignedBB;
/*      */     //   1265: ldc2_w 16.0
/*      */     //   1268: ldc2_w 6.0
/*      */     //   1271: ldc2_w 16.0
/*      */     //   1274: invokevirtual expand : (DDD)Lnet/minecraft/util/math/AxisAlignedBB;
/*      */     //   1277: invokevirtual getEntitiesWithinAABBExcludingEntity : (Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/AxisAlignedBB;)Ljava/util/List;
/*      */     //   1280: astore #9
/*      */     //   1282: iconst_0
/*      */     //   1283: istore #10
/*      */     //   1285: iload #10
/*      */     //   1287: aload #9
/*      */     //   1289: invokeinterface size : ()I
/*      */     //   1294: if_icmplt -> 1300
/*      */     //   1297: goto -> 1378
/*      */     //   1300: aload #9
/*      */     //   1302: iload #10
/*      */     //   1304: invokeinterface get : (I)Ljava/lang/Object;
/*      */     //   1309: checkcast net/minecraft/entity/Entity
/*      */     //   1312: astore #11
/*      */     //   1314: aload #11
/*      */     //   1316: instanceof drzhark/mocreatures/entity/passive/MoCEntityKitty
/*      */     //   1319: ifeq -> 1372
/*      */     //   1322: aload #11
/*      */     //   1324: checkcast drzhark/mocreatures/entity/passive/MoCEntityKitty
/*      */     //   1327: invokevirtual getKittyState : ()I
/*      */     //   1330: bipush #9
/*      */     //   1332: if_icmpne -> 1372
/*      */     //   1335: aload_0
/*      */     //   1336: bipush #18
/*      */     //   1338: invokespecial changeKittyState : (I)V
/*      */     //   1341: aload_0
/*      */     //   1342: aload #11
/*      */     //   1344: checkcast net/minecraft/entity/EntityLivingBase
/*      */     //   1347: invokevirtual setAttackTarget : (Lnet/minecraft/entity/EntityLivingBase;)V
/*      */     //   1350: aload #11
/*      */     //   1352: checkcast drzhark/mocreatures/entity/passive/MoCEntityKitty
/*      */     //   1355: bipush #18
/*      */     //   1357: invokespecial changeKittyState : (I)V
/*      */     //   1360: aload #11
/*      */     //   1362: checkcast drzhark/mocreatures/entity/passive/MoCEntityKitty
/*      */     //   1365: aload_0
/*      */     //   1366: invokevirtual setAttackTarget : (Lnet/minecraft/entity/EntityLivingBase;)V
/*      */     //   1369: goto -> 1378
/*      */     //   1372: iinc #10, 1
/*      */     //   1375: goto -> 1285
/*      */     //   1378: aload_0
/*      */     //   1379: getfield kittytimer : I
/*      */     //   1382: sipush #2000
/*      */     //   1385: if_icmple -> 3564
/*      */     //   1388: aload_0
/*      */     //   1389: bipush #7
/*      */     //   1391: invokespecial changeKittyState : (I)V
/*      */     //   1394: goto -> 3564
/*      */     //   1397: aload_0
/*      */     //   1398: invokevirtual getIsAdult : ()Z
/*      */     //   1401: ifeq -> 1413
/*      */     //   1404: aload_0
/*      */     //   1405: bipush #7
/*      */     //   1407: invokespecial changeKittyState : (I)V
/*      */     //   1410: goto -> 3564
/*      */     //   1413: aload_0
/*      */     //   1414: getfield rand : Ljava/util/Random;
/*      */     //   1417: bipush #50
/*      */     //   1419: invokevirtual nextInt : (I)I
/*      */     //   1422: ifne -> 1536
/*      */     //   1425: aload_0
/*      */     //   1426: getfield world : Lnet/minecraft/world/World;
/*      */     //   1429: aload_0
/*      */     //   1430: aload_0
/*      */     //   1431: invokevirtual getEntityBoundingBox : ()Lnet/minecraft/util/math/AxisAlignedBB;
/*      */     //   1434: ldc2_w 16.0
/*      */     //   1437: ldc2_w 6.0
/*      */     //   1440: ldc2_w 16.0
/*      */     //   1443: invokevirtual expand : (DDD)Lnet/minecraft/util/math/AxisAlignedBB;
/*      */     //   1446: invokevirtual getEntitiesWithinAABBExcludingEntity : (Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/AxisAlignedBB;)Ljava/util/List;
/*      */     //   1449: astore #9
/*      */     //   1451: iconst_0
/*      */     //   1452: istore #10
/*      */     //   1454: iload #10
/*      */     //   1456: aload #9
/*      */     //   1458: invokeinterface size : ()I
/*      */     //   1463: if_icmpge -> 1536
/*      */     //   1466: aload #9
/*      */     //   1468: iload #10
/*      */     //   1470: invokeinterface get : (I)Ljava/lang/Object;
/*      */     //   1475: checkcast net/minecraft/entity/Entity
/*      */     //   1478: astore #11
/*      */     //   1480: aload #11
/*      */     //   1482: instanceof drzhark/mocreatures/entity/passive/MoCEntityKitty
/*      */     //   1485: ifeq -> 1530
/*      */     //   1488: aload #11
/*      */     //   1490: checkcast drzhark/mocreatures/entity/passive/MoCEntityKitty
/*      */     //   1493: invokevirtual getKittyState : ()I
/*      */     //   1496: bipush #21
/*      */     //   1498: if_icmpeq -> 1504
/*      */     //   1501: goto -> 1530
/*      */     //   1504: aload_0
/*      */     //   1505: aload #11
/*      */     //   1507: invokevirtual getDistance : (Lnet/minecraft/entity/Entity;)F
/*      */     //   1510: fstore #12
/*      */     //   1512: fload #12
/*      */     //   1514: ldc_w 12.0
/*      */     //   1517: fcmpl
/*      */     //   1518: ifle -> 1530
/*      */     //   1521: aload_0
/*      */     //   1522: aload #11
/*      */     //   1524: checkcast net/minecraft/entity/EntityLivingBase
/*      */     //   1527: invokevirtual setAttackTarget : (Lnet/minecraft/entity/EntityLivingBase;)V
/*      */     //   1530: iinc #10, 1
/*      */     //   1533: goto -> 1454
/*      */     //   1536: aload_0
/*      */     //   1537: getfield itemAttackTarget : Lnet/minecraft/entity/item/EntityItem;
/*      */     //   1540: ifnull -> 1550
/*      */     //   1543: aload_0
/*      */     //   1544: invokevirtual getAttackTarget : ()Lnet/minecraft/entity/EntityLivingBase;
/*      */     //   1547: ifnonnull -> 1612
/*      */     //   1550: aload_0
/*      */     //   1551: getfield rand : Ljava/util/Random;
/*      */     //   1554: bipush #100
/*      */     //   1556: invokevirtual nextInt : (I)I
/*      */     //   1559: ifne -> 1612
/*      */     //   1562: aload_0
/*      */     //   1563: getfield rand : Ljava/util/Random;
/*      */     //   1566: bipush #10
/*      */     //   1568: invokevirtual nextInt : (I)I
/*      */     //   1571: istore #9
/*      */     //   1573: iload #9
/*      */     //   1575: bipush #7
/*      */     //   1577: if_icmpge -> 1597
/*      */     //   1580: aload_0
/*      */     //   1581: aload_0
/*      */     //   1582: aload_0
/*      */     //   1583: ldc2_w 10.0
/*      */     //   1586: aconst_null
/*      */     //   1587: aconst_null
/*      */     //   1588: invokevirtual getClosestItem : (Lnet/minecraft/entity/Entity;DLnet/minecraft/item/Item;Lnet/minecraft/item/Item;)Lnet/minecraft/entity/item/EntityItem;
/*      */     //   1591: putfield itemAttackTarget : Lnet/minecraft/entity/item/EntityItem;
/*      */     //   1594: goto -> 1612
/*      */     //   1597: aload_0
/*      */     //   1598: aload_0
/*      */     //   1599: getfield world : Lnet/minecraft/world/World;
/*      */     //   1602: aload_0
/*      */     //   1603: ldc2_w 18.0
/*      */     //   1606: invokevirtual getClosestPlayerToEntity : (Lnet/minecraft/entity/Entity;D)Lnet/minecraft/entity/player/EntityPlayer;
/*      */     //   1609: invokevirtual setAttackTarget : (Lnet/minecraft/entity/EntityLivingBase;)V
/*      */     //   1612: aload_0
/*      */     //   1613: invokevirtual getAttackTarget : ()Lnet/minecraft/entity/EntityLivingBase;
/*      */     //   1616: ifnonnull -> 1626
/*      */     //   1619: aload_0
/*      */     //   1620: getfield itemAttackTarget : Lnet/minecraft/entity/item/EntityItem;
/*      */     //   1623: ifnull -> 1649
/*      */     //   1626: aload_0
/*      */     //   1627: getfield rand : Ljava/util/Random;
/*      */     //   1630: sipush #400
/*      */     //   1633: invokevirtual nextInt : (I)I
/*      */     //   1636: ifne -> 1649
/*      */     //   1639: aload_0
/*      */     //   1640: aconst_null
/*      */     //   1641: invokevirtual setAttackTarget : (Lnet/minecraft/entity/EntityLivingBase;)V
/*      */     //   1644: aload_0
/*      */     //   1645: aconst_null
/*      */     //   1646: putfield itemAttackTarget : Lnet/minecraft/entity/item/EntityItem;
/*      */     //   1649: aload_0
/*      */     //   1650: getfield itemAttackTarget : Lnet/minecraft/entity/item/EntityItem;
/*      */     //   1653: ifnull -> 1712
/*      */     //   1656: aload_0
/*      */     //   1657: getfield itemAttackTarget : Lnet/minecraft/entity/item/EntityItem;
/*      */     //   1660: instanceof net/minecraft/entity/item/EntityItem
/*      */     //   1663: ifeq -> 1712
/*      */     //   1666: aload_0
/*      */     //   1667: aload_0
/*      */     //   1668: getfield itemAttackTarget : Lnet/minecraft/entity/item/EntityItem;
/*      */     //   1671: invokevirtual getDistance : (Lnet/minecraft/entity/Entity;)F
/*      */     //   1674: fstore #9
/*      */     //   1676: fload #9
/*      */     //   1678: ldc_w 1.5
/*      */     //   1681: fcmpg
/*      */     //   1682: ifge -> 1712
/*      */     //   1685: aload_0
/*      */     //   1686: invokevirtual swingArm : ()V
/*      */     //   1689: aload_0
/*      */     //   1690: getfield rand : Ljava/util/Random;
/*      */     //   1693: bipush #10
/*      */     //   1695: invokevirtual nextInt : (I)I
/*      */     //   1698: ifne -> 1712
/*      */     //   1701: aload_0
/*      */     //   1702: aload_0
/*      */     //   1703: getfield itemAttackTarget : Lnet/minecraft/entity/item/EntityItem;
/*      */     //   1706: ldc_w 0.2
/*      */     //   1709: invokestatic bigsmack : (Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/Entity;F)V
/*      */     //   1712: aload_0
/*      */     //   1713: invokevirtual getAttackTarget : ()Lnet/minecraft/entity/EntityLivingBase;
/*      */     //   1716: ifnull -> 1769
/*      */     //   1719: aload_0
/*      */     //   1720: invokevirtual getAttackTarget : ()Lnet/minecraft/entity/EntityLivingBase;
/*      */     //   1723: instanceof drzhark/mocreatures/entity/passive/MoCEntityKitty
/*      */     //   1726: ifeq -> 1769
/*      */     //   1729: aload_0
/*      */     //   1730: getfield rand : Ljava/util/Random;
/*      */     //   1733: bipush #20
/*      */     //   1735: invokevirtual nextInt : (I)I
/*      */     //   1738: ifne -> 1769
/*      */     //   1741: aload_0
/*      */     //   1742: aload_0
/*      */     //   1743: invokevirtual getAttackTarget : ()Lnet/minecraft/entity/EntityLivingBase;
/*      */     //   1746: invokevirtual getDistance : (Lnet/minecraft/entity/Entity;)F
/*      */     //   1749: fstore #9
/*      */     //   1751: fload #9
/*      */     //   1753: fconst_2
/*      */     //   1754: fcmpg
/*      */     //   1755: ifge -> 1769
/*      */     //   1758: aload_0
/*      */     //   1759: invokevirtual swingArm : ()V
/*      */     //   1762: aload_0
/*      */     //   1763: invokevirtual getNavigator : ()Lnet/minecraft/pathfinding/PathNavigate;
/*      */     //   1766: invokevirtual clearPath : ()V
/*      */     //   1769: aload_0
/*      */     //   1770: invokevirtual getAttackTarget : ()Lnet/minecraft/entity/EntityLivingBase;
/*      */     //   1773: ifnull -> 3564
/*      */     //   1776: aload_0
/*      */     //   1777: invokevirtual getAttackTarget : ()Lnet/minecraft/entity/EntityLivingBase;
/*      */     //   1780: instanceof net/minecraft/entity/player/EntityPlayer
/*      */     //   1783: ifne -> 1789
/*      */     //   1786: goto -> 3564
/*      */     //   1789: aload_0
/*      */     //   1790: aload_0
/*      */     //   1791: invokevirtual getAttackTarget : ()Lnet/minecraft/entity/EntityLivingBase;
/*      */     //   1794: invokevirtual getDistance : (Lnet/minecraft/entity/Entity;)F
/*      */     //   1797: fstore #9
/*      */     //   1799: fload #9
/*      */     //   1801: fconst_2
/*      */     //   1802: fcmpg
/*      */     //   1803: ifge -> 3564
/*      */     //   1806: aload_0
/*      */     //   1807: getfield rand : Ljava/util/Random;
/*      */     //   1810: bipush #20
/*      */     //   1812: invokevirtual nextInt : (I)I
/*      */     //   1815: ifne -> 3564
/*      */     //   1818: aload_0
/*      */     //   1819: invokevirtual swingArm : ()V
/*      */     //   1822: goto -> 3564
/*      */     //   1825: aload_0
/*      */     //   1826: getfield world : Lnet/minecraft/world/World;
/*      */     //   1829: aload_0
/*      */     //   1830: ldc2_w 18.0
/*      */     //   1833: invokevirtual getClosestPlayerToEntity : (Lnet/minecraft/entity/Entity;D)Lnet/minecraft/entity/player/EntityPlayer;
/*      */     //   1836: astore #10
/*      */     //   1838: aload #10
/*      */     //   1840: ifnull -> 3564
/*      */     //   1843: aload_0
/*      */     //   1844: getfield rand : Ljava/util/Random;
/*      */     //   1847: bipush #10
/*      */     //   1849: invokevirtual nextInt : (I)I
/*      */     //   1852: ifeq -> 1858
/*      */     //   1855: goto -> 3564
/*      */     //   1858: aload #10
/*      */     //   1860: getfield inventory : Lnet/minecraft/entity/player/InventoryPlayer;
/*      */     //   1863: invokevirtual getCurrentItem : ()Lnet/minecraft/item/ItemStack;
/*      */     //   1866: astore #11
/*      */     //   1868: aload #11
/*      */     //   1870: ifnull -> 1889
/*      */     //   1873: aload #11
/*      */     //   1875: ifnull -> 1898
/*      */     //   1878: aload #11
/*      */     //   1880: invokevirtual getItem : ()Lnet/minecraft/item/Item;
/*      */     //   1883: getstatic drzhark/mocreatures/init/MoCItems.woolball : Ldrzhark/mocreatures/item/MoCItem;
/*      */     //   1886: if_acmpeq -> 1898
/*      */     //   1889: aload_0
/*      */     //   1890: bipush #7
/*      */     //   1892: invokespecial changeKittyState : (I)V
/*      */     //   1895: goto -> 3564
/*      */     //   1898: aload #10
/*      */     //   1900: aload_0
/*      */     //   1901: invokevirtual getDistance : (Lnet/minecraft/entity/Entity;)F
/*      */     //   1904: fstore #12
/*      */     //   1906: fload #12
/*      */     //   1908: ldc_w 5.0
/*      */     //   1911: fcmpl
/*      */     //   1912: ifle -> 3564
/*      */     //   1915: aload_0
/*      */     //   1916: aload #10
/*      */     //   1918: fload #12
/*      */     //   1920: invokevirtual getPathOrWalkableBlock : (Lnet/minecraft/entity/Entity;F)V
/*      */     //   1923: goto -> 3564
/*      */     //   1926: aload_0
/*      */     //   1927: dup
/*      */     //   1928: getfield kittytimer : I
/*      */     //   1931: iconst_1
/*      */     //   1932: iadd
/*      */     //   1933: putfield kittytimer : I
/*      */     //   1936: aload_0
/*      */     //   1937: getfield world : Lnet/minecraft/world/World;
/*      */     //   1940: invokevirtual isDaytime : ()Z
/*      */     //   1943: ifne -> 1969
/*      */     //   1946: aload_0
/*      */     //   1947: getfield kittytimer : I
/*      */     //   1950: sipush #500
/*      */     //   1953: if_icmple -> 1978
/*      */     //   1956: aload_0
/*      */     //   1957: getfield rand : Ljava/util/Random;
/*      */     //   1960: sipush #500
/*      */     //   1963: invokevirtual nextInt : (I)I
/*      */     //   1966: ifne -> 1978
/*      */     //   1969: aload_0
/*      */     //   1970: bipush #7
/*      */     //   1972: invokespecial changeKittyState : (I)V
/*      */     //   1975: goto -> 3564
/*      */     //   1978: aload_0
/*      */     //   1979: iconst_1
/*      */     //   1980: invokevirtual setSitting : (Z)V
/*      */     //   1983: aload_0
/*      */     //   1984: getfield rand : Ljava/util/Random;
/*      */     //   1987: bipush #80
/*      */     //   1989: invokevirtual nextInt : (I)I
/*      */     //   1992: ifeq -> 2002
/*      */     //   1995: aload_0
/*      */     //   1996: getfield onGround : Z
/*      */     //   1999: ifne -> 3564
/*      */     //   2002: aload_0
/*      */     //   2003: invokespecial onLivingUpdate : ()V
/*      */     //   2006: goto -> 3564
/*      */     //   2009: aload_0
/*      */     //   2010: iconst_0
/*      */     //   2011: invokevirtual setHungry : (Z)V
/*      */     //   2014: aload_0
/*      */     //   2015: aload_0
/*      */     //   2016: getfield world : Lnet/minecraft/world/World;
/*      */     //   2019: aload_0
/*      */     //   2020: ldc2_w 18.0
/*      */     //   2023: invokevirtual getClosestPlayerToEntity : (Lnet/minecraft/entity/Entity;D)Lnet/minecraft/entity/player/EntityPlayer;
/*      */     //   2026: invokevirtual setAttackTarget : (Lnet/minecraft/entity/EntityLivingBase;)V
/*      */     //   2029: aload_0
/*      */     //   2030: invokevirtual getAttackTarget : ()Lnet/minecraft/entity/EntityLivingBase;
/*      */     //   2033: ifnull -> 2142
/*      */     //   2036: aload_0
/*      */     //   2037: aload_0
/*      */     //   2038: invokevirtual getAttackTarget : ()Lnet/minecraft/entity/EntityLivingBase;
/*      */     //   2041: invokevirtual getDistance : (Lnet/minecraft/entity/Entity;)F
/*      */     //   2044: fstore #13
/*      */     //   2046: fload #13
/*      */     //   2048: ldc_w 1.5
/*      */     //   2051: fcmpg
/*      */     //   2052: ifge -> 2120
/*      */     //   2055: aload_0
/*      */     //   2056: invokevirtual swingArm : ()V
/*      */     //   2059: aload_0
/*      */     //   2060: getfield rand : Ljava/util/Random;
/*      */     //   2063: bipush #20
/*      */     //   2065: invokevirtual nextInt : (I)I
/*      */     //   2068: ifne -> 2120
/*      */     //   2071: aload_0
/*      */     //   2072: dup
/*      */     //   2073: getfield madtimer : I
/*      */     //   2076: iconst_1
/*      */     //   2077: isub
/*      */     //   2078: putfield madtimer : I
/*      */     //   2081: aload_0
/*      */     //   2082: invokevirtual getAttackTarget : ()Lnet/minecraft/entity/EntityLivingBase;
/*      */     //   2085: aload_0
/*      */     //   2086: invokestatic causeMobDamage : (Lnet/minecraft/entity/EntityLivingBase;)Lnet/minecraft/util/DamageSource;
/*      */     //   2089: fconst_1
/*      */     //   2090: invokevirtual attackEntityFrom : (Lnet/minecraft/util/DamageSource;F)Z
/*      */     //   2093: pop
/*      */     //   2094: aload_0
/*      */     //   2095: getfield madtimer : I
/*      */     //   2098: iconst_1
/*      */     //   2099: if_icmpge -> 2120
/*      */     //   2102: aload_0
/*      */     //   2103: bipush #7
/*      */     //   2105: invokespecial changeKittyState : (I)V
/*      */     //   2108: aload_0
/*      */     //   2109: aload_0
/*      */     //   2110: getfield rand : Ljava/util/Random;
/*      */     //   2113: iconst_5
/*      */     //   2114: invokevirtual nextInt : (I)I
/*      */     //   2117: putfield madtimer : I
/*      */     //   2120: aload_0
/*      */     //   2121: getfield rand : Ljava/util/Random;
/*      */     //   2124: sipush #500
/*      */     //   2127: invokevirtual nextInt : (I)I
/*      */     //   2130: ifne -> 2139
/*      */     //   2133: aload_0
/*      */     //   2134: bipush #7
/*      */     //   2136: invokespecial changeKittyState : (I)V
/*      */     //   2139: goto -> 3564
/*      */     //   2142: aload_0
/*      */     //   2143: bipush #7
/*      */     //   2145: invokespecial changeKittyState : (I)V
/*      */     //   2148: goto -> 3564
/*      */     //   2151: aload_0
/*      */     //   2152: getfield onGround : Z
/*      */     //   2155: ifeq -> 2167
/*      */     //   2158: aload_0
/*      */     //   2159: bipush #13
/*      */     //   2161: invokespecial changeKittyState : (I)V
/*      */     //   2164: goto -> 3564
/*      */     //   2167: aload_0
/*      */     //   2168: getfield rand : Ljava/util/Random;
/*      */     //   2171: bipush #50
/*      */     //   2173: invokevirtual nextInt : (I)I
/*      */     //   2176: ifne -> 2183
/*      */     //   2179: aload_0
/*      */     //   2180: invokevirtual swingArm : ()V
/*      */     //   2183: aload_0
/*      */     //   2184: invokevirtual getRidingEntity : ()Lnet/minecraft/entity/Entity;
/*      */     //   2187: ifnonnull -> 2193
/*      */     //   2190: goto -> 3564
/*      */     //   2193: aload_0
/*      */     //   2194: aload_0
/*      */     //   2195: invokevirtual getRidingEntity : ()Lnet/minecraft/entity/Entity;
/*      */     //   2198: getfield rotationYaw : F
/*      */     //   2201: ldc_w 90.0
/*      */     //   2204: fadd
/*      */     //   2205: putfield rotationYaw : F
/*      */     //   2208: aload_0
/*      */     //   2209: invokevirtual getRidingEntity : ()Lnet/minecraft/entity/Entity;
/*      */     //   2212: checkcast net/minecraft/entity/player/EntityPlayer
/*      */     //   2215: astore #13
/*      */     //   2217: aload #13
/*      */     //   2219: ifnonnull -> 2231
/*      */     //   2222: aload_0
/*      */     //   2223: bipush #13
/*      */     //   2225: invokespecial changeKittyState : (I)V
/*      */     //   2228: goto -> 3564
/*      */     //   2231: aload #13
/*      */     //   2233: getfield inventory : Lnet/minecraft/entity/player/InventoryPlayer;
/*      */     //   2236: invokevirtual getCurrentItem : ()Lnet/minecraft/item/ItemStack;
/*      */     //   2239: astore #14
/*      */     //   2241: aload #14
/*      */     //   2243: ifnull -> 2262
/*      */     //   2246: aload #14
/*      */     //   2248: ifnull -> 3564
/*      */     //   2251: aload #14
/*      */     //   2253: invokevirtual getItem : ()Lnet/minecraft/item/Item;
/*      */     //   2256: getstatic net/minecraft/init/Items.LEAD : Lnet/minecraft/item/Item;
/*      */     //   2259: if_acmpeq -> 3564
/*      */     //   2262: aload_0
/*      */     //   2263: bipush #13
/*      */     //   2265: invokespecial changeKittyState : (I)V
/*      */     //   2268: goto -> 3564
/*      */     //   2271: aload_0
/*      */     //   2272: getfield onGround : Z
/*      */     //   2275: ifeq -> 2284
/*      */     //   2278: aload_0
/*      */     //   2279: bipush #7
/*      */     //   2281: invokespecial changeKittyState : (I)V
/*      */     //   2284: aload_0
/*      */     //   2285: invokevirtual getRidingEntity : ()Lnet/minecraft/entity/Entity;
/*      */     //   2288: ifnull -> 3564
/*      */     //   2291: aload_0
/*      */     //   2292: aload_0
/*      */     //   2293: invokevirtual getRidingEntity : ()Lnet/minecraft/entity/Entity;
/*      */     //   2296: getfield rotationYaw : F
/*      */     //   2299: ldc_w 90.0
/*      */     //   2302: fadd
/*      */     //   2303: putfield rotationYaw : F
/*      */     //   2306: goto -> 3564
/*      */     //   2309: aload_0
/*      */     //   2310: dup
/*      */     //   2311: getfield kittytimer : I
/*      */     //   2314: iconst_1
/*      */     //   2315: iadd
/*      */     //   2316: putfield kittytimer : I
/*      */     //   2319: aload_0
/*      */     //   2320: getfield kittytimer : I
/*      */     //   2323: sipush #500
/*      */     //   2326: if_icmple -> 2342
/*      */     //   2329: aload_0
/*      */     //   2330: invokevirtual getOnTree : ()Z
/*      */     //   2333: ifne -> 2342
/*      */     //   2336: aload_0
/*      */     //   2337: bipush #7
/*      */     //   2339: invokespecial changeKittyState : (I)V
/*      */     //   2342: aload_0
/*      */     //   2343: invokevirtual getOnTree : ()Z
/*      */     //   2346: ifne -> 2618
/*      */     //   2349: aload_0
/*      */     //   2350: getfield foundTree : Z
/*      */     //   2353: ifne -> 2498
/*      */     //   2356: aload_0
/*      */     //   2357: getfield rand : Ljava/util/Random;
/*      */     //   2360: bipush #50
/*      */     //   2362: invokevirtual nextInt : (I)I
/*      */     //   2365: ifne -> 2498
/*      */     //   2368: aload_0
/*      */     //   2369: getstatic net/minecraft/block/material/Material.WOOD : Lnet/minecraft/block/material/Material;
/*      */     //   2372: ldc2_w 18.0
/*      */     //   2375: invokestatic valueOf : (D)Ljava/lang/Double;
/*      */     //   2378: ldc2_w 4.0
/*      */     //   2381: invokestatic valueOf : (D)Ljava/lang/Double;
/*      */     //   2384: invokestatic ReturnNearestMaterialCoord : (Lnet/minecraft/entity/Entity;Lnet/minecraft/block/material/Material;Ljava/lang/Double;Ljava/lang/Double;)[I
/*      */     //   2387: astore #15
/*      */     //   2389: aload #15
/*      */     //   2391: iconst_0
/*      */     //   2392: iaload
/*      */     //   2393: iconst_m1
/*      */     //   2394: if_icmpeq -> 2498
/*      */     //   2397: iconst_0
/*      */     //   2398: istore #16
/*      */     //   2400: iload #16
/*      */     //   2402: bipush #20
/*      */     //   2404: if_icmplt -> 2410
/*      */     //   2407: goto -> 2498
/*      */     //   2410: aload_0
/*      */     //   2411: getfield world : Lnet/minecraft/world/World;
/*      */     //   2414: new net/minecraft/util/math/BlockPos
/*      */     //   2417: dup
/*      */     //   2418: aload #15
/*      */     //   2420: iconst_0
/*      */     //   2421: iaload
/*      */     //   2422: aload #15
/*      */     //   2424: iconst_1
/*      */     //   2425: iaload
/*      */     //   2426: iload #16
/*      */     //   2428: iadd
/*      */     //   2429: aload #15
/*      */     //   2431: iconst_2
/*      */     //   2432: iaload
/*      */     //   2433: invokespecial <init> : (III)V
/*      */     //   2436: invokevirtual getBlockState : (Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
/*      */     //   2439: astore #17
/*      */     //   2441: aload #17
/*      */     //   2443: invokeinterface getMaterial : ()Lnet/minecraft/block/material/Material;
/*      */     //   2448: getstatic net/minecraft/block/material/Material.LEAVES : Lnet/minecraft/block/material/Material;
/*      */     //   2451: if_acmpne -> 2492
/*      */     //   2454: aload_0
/*      */     //   2455: iconst_1
/*      */     //   2456: putfield foundTree : Z
/*      */     //   2459: aload_0
/*      */     //   2460: getfield treeCoord : [I
/*      */     //   2463: iconst_0
/*      */     //   2464: aload #15
/*      */     //   2466: iconst_0
/*      */     //   2467: iaload
/*      */     //   2468: iastore
/*      */     //   2469: aload_0
/*      */     //   2470: getfield treeCoord : [I
/*      */     //   2473: iconst_1
/*      */     //   2474: aload #15
/*      */     //   2476: iconst_1
/*      */     //   2477: iaload
/*      */     //   2478: iastore
/*      */     //   2479: aload_0
/*      */     //   2480: getfield treeCoord : [I
/*      */     //   2483: iconst_2
/*      */     //   2484: aload #15
/*      */     //   2486: iconst_2
/*      */     //   2487: iaload
/*      */     //   2488: iastore
/*      */     //   2489: goto -> 2498
/*      */     //   2492: iinc #16, 1
/*      */     //   2495: goto -> 2400
/*      */     //   2498: aload_0
/*      */     //   2499: getfield foundTree : Z
/*      */     //   2502: ifeq -> 3564
/*      */     //   2505: aload_0
/*      */     //   2506: getfield rand : Ljava/util/Random;
/*      */     //   2509: bipush #10
/*      */     //   2511: invokevirtual nextInt : (I)I
/*      */     //   2514: ifeq -> 2520
/*      */     //   2517: goto -> 3564
/*      */     //   2520: aload_0
/*      */     //   2521: getfield navigator : Lnet/minecraft/pathfinding/PathNavigate;
/*      */     //   2524: aload_0
/*      */     //   2525: getfield treeCoord : [I
/*      */     //   2528: iconst_0
/*      */     //   2529: iaload
/*      */     //   2530: i2d
/*      */     //   2531: aload_0
/*      */     //   2532: getfield treeCoord : [I
/*      */     //   2535: iconst_1
/*      */     //   2536: iaload
/*      */     //   2537: i2d
/*      */     //   2538: aload_0
/*      */     //   2539: getfield treeCoord : [I
/*      */     //   2542: iconst_2
/*      */     //   2543: iaload
/*      */     //   2544: i2d
/*      */     //   2545: invokevirtual getPathToXYZ : (DDD)Lnet/minecraft/pathfinding/Path;
/*      */     //   2548: astore #15
/*      */     //   2550: aload #15
/*      */     //   2552: ifnull -> 2568
/*      */     //   2555: aload_0
/*      */     //   2556: getfield navigator : Lnet/minecraft/pathfinding/PathNavigate;
/*      */     //   2559: aload #15
/*      */     //   2561: ldc2_w 24.0
/*      */     //   2564: invokevirtual setPath : (Lnet/minecraft/pathfinding/Path;D)Z
/*      */     //   2567: pop
/*      */     //   2568: aload_0
/*      */     //   2569: aload_0
/*      */     //   2570: getfield treeCoord : [I
/*      */     //   2573: iconst_0
/*      */     //   2574: iaload
/*      */     //   2575: i2d
/*      */     //   2576: aload_0
/*      */     //   2577: getfield treeCoord : [I
/*      */     //   2580: iconst_1
/*      */     //   2581: iaload
/*      */     //   2582: i2d
/*      */     //   2583: aload_0
/*      */     //   2584: getfield treeCoord : [I
/*      */     //   2587: iconst_2
/*      */     //   2588: iaload
/*      */     //   2589: i2d
/*      */     //   2590: invokevirtual getDistanceSq : (DDD)D
/*      */     //   2593: invokestatic valueOf : (D)Ljava/lang/Double;
/*      */     //   2596: astore #16
/*      */     //   2598: aload #16
/*      */     //   2600: invokevirtual doubleValue : ()D
/*      */     //   2603: ldc2_w 7.0
/*      */     //   2606: dcmpg
/*      */     //   2607: ifge -> 3564
/*      */     //   2610: aload_0
/*      */     //   2611: iconst_1
/*      */     //   2612: invokevirtual setOnTree : (Z)V
/*      */     //   2615: goto -> 3564
/*      */     //   2618: aload_0
/*      */     //   2619: invokevirtual getOnTree : ()Z
/*      */     //   2622: ifne -> 2628
/*      */     //   2625: goto -> 3564
/*      */     //   2628: aload_0
/*      */     //   2629: getfield treeCoord : [I
/*      */     //   2632: iconst_0
/*      */     //   2633: iaload
/*      */     //   2634: istore #15
/*      */     //   2636: aload_0
/*      */     //   2637: getfield treeCoord : [I
/*      */     //   2640: iconst_1
/*      */     //   2641: iaload
/*      */     //   2642: istore #16
/*      */     //   2644: aload_0
/*      */     //   2645: getfield treeCoord : [I
/*      */     //   2648: iconst_2
/*      */     //   2649: iaload
/*      */     //   2650: istore #17
/*      */     //   2652: aload_0
/*      */     //   2653: iload #15
/*      */     //   2655: iload #16
/*      */     //   2657: iload #17
/*      */     //   2659: ldc_w 30.0
/*      */     //   2662: invokevirtual faceItem : (IIIF)V
/*      */     //   2665: iload #16
/*      */     //   2667: aload_0
/*      */     //   2668: getfield posY : D
/*      */     //   2671: invokestatic floor : (D)I
/*      */     //   2674: isub
/*      */     //   2675: iconst_2
/*      */     //   2676: if_icmple -> 2691
/*      */     //   2679: aload_0
/*      */     //   2680: dup
/*      */     //   2681: getfield motionY : D
/*      */     //   2684: ldc2_w 0.03
/*      */     //   2687: dadd
/*      */     //   2688: putfield motionY : D
/*      */     //   2691: aload_0
/*      */     //   2692: getfield posX : D
/*      */     //   2695: iload #15
/*      */     //   2697: i2d
/*      */     //   2698: dcmpg
/*      */     //   2699: ifge -> 2717
/*      */     //   2702: aload_0
/*      */     //   2703: dup
/*      */     //   2704: getfield motionX : D
/*      */     //   2707: ldc2_w 0.01
/*      */     //   2710: dadd
/*      */     //   2711: putfield motionX : D
/*      */     //   2714: goto -> 2729
/*      */     //   2717: aload_0
/*      */     //   2718: dup
/*      */     //   2719: getfield motionX : D
/*      */     //   2722: ldc2_w 0.01
/*      */     //   2725: dsub
/*      */     //   2726: putfield motionX : D
/*      */     //   2729: aload_0
/*      */     //   2730: getfield posZ : D
/*      */     //   2733: iload #17
/*      */     //   2735: i2d
/*      */     //   2736: dcmpg
/*      */     //   2737: ifge -> 2755
/*      */     //   2740: aload_0
/*      */     //   2741: dup
/*      */     //   2742: getfield motionZ : D
/*      */     //   2745: ldc2_w 0.01
/*      */     //   2748: dadd
/*      */     //   2749: putfield motionZ : D
/*      */     //   2752: goto -> 2767
/*      */     //   2755: aload_0
/*      */     //   2756: dup
/*      */     //   2757: getfield motionZ : D
/*      */     //   2760: ldc2_w 0.01
/*      */     //   2763: dsub
/*      */     //   2764: putfield motionZ : D
/*      */     //   2767: aload_0
/*      */     //   2768: getfield onGround : Z
/*      */     //   2771: ifne -> 3564
/*      */     //   2774: aload_0
/*      */     //   2775: getfield collidedHorizontally : Z
/*      */     //   2778: ifeq -> 3564
/*      */     //   2781: aload_0
/*      */     //   2782: getfield collidedVertically : Z
/*      */     //   2785: ifne -> 2791
/*      */     //   2788: goto -> 3564
/*      */     //   2791: iconst_0
/*      */     //   2792: istore #18
/*      */     //   2794: iload #18
/*      */     //   2796: bipush #30
/*      */     //   2798: if_icmplt -> 2804
/*      */     //   2801: goto -> 3564
/*      */     //   2804: new net/minecraft/util/math/BlockPos
/*      */     //   2807: dup
/*      */     //   2808: aload_0
/*      */     //   2809: getfield treeCoord : [I
/*      */     //   2812: iconst_0
/*      */     //   2813: iaload
/*      */     //   2814: aload_0
/*      */     //   2815: getfield treeCoord : [I
/*      */     //   2818: iconst_1
/*      */     //   2819: iaload
/*      */     //   2820: iload #18
/*      */     //   2822: iadd
/*      */     //   2823: aload_0
/*      */     //   2824: getfield treeCoord : [I
/*      */     //   2827: iconst_2
/*      */     //   2828: iaload
/*      */     //   2829: invokespecial <init> : (III)V
/*      */     //   2832: astore #19
/*      */     //   2834: aload_0
/*      */     //   2835: getfield world : Lnet/minecraft/world/World;
/*      */     //   2838: aload #19
/*      */     //   2840: invokevirtual getBlockState : (Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
/*      */     //   2843: invokeinterface getBlock : ()Lnet/minecraft/block/Block;
/*      */     //   2848: astore #20
/*      */     //   2850: aload #20
/*      */     //   2852: getstatic net/minecraft/init/Blocks.AIR : Lnet/minecraft/block/Block;
/*      */     //   2855: if_acmpne -> 2924
/*      */     //   2858: aload_0
/*      */     //   2859: aload_0
/*      */     //   2860: getfield treeCoord : [I
/*      */     //   2863: iconst_0
/*      */     //   2864: iaload
/*      */     //   2865: i2d
/*      */     //   2866: aload_0
/*      */     //   2867: getfield treeCoord : [I
/*      */     //   2870: iconst_1
/*      */     //   2871: iaload
/*      */     //   2872: iload #18
/*      */     //   2874: iadd
/*      */     //   2875: i2d
/*      */     //   2876: aload_0
/*      */     //   2877: getfield treeCoord : [I
/*      */     //   2880: iconst_2
/*      */     //   2881: iaload
/*      */     //   2882: i2d
/*      */     //   2883: aload_0
/*      */     //   2884: getfield rotationYaw : F
/*      */     //   2887: aload_0
/*      */     //   2888: getfield rotationPitch : F
/*      */     //   2891: invokevirtual setLocationAndAngles : (DDDFF)V
/*      */     //   2894: aload_0
/*      */     //   2895: bipush #17
/*      */     //   2897: invokespecial changeKittyState : (I)V
/*      */     //   2900: aload_0
/*      */     //   2901: getfield treeCoord : [I
/*      */     //   2904: iconst_0
/*      */     //   2905: iconst_m1
/*      */     //   2906: iastore
/*      */     //   2907: aload_0
/*      */     //   2908: getfield treeCoord : [I
/*      */     //   2911: iconst_1
/*      */     //   2912: iconst_m1
/*      */     //   2913: iastore
/*      */     //   2914: aload_0
/*      */     //   2915: getfield treeCoord : [I
/*      */     //   2918: iconst_2
/*      */     //   2919: iconst_m1
/*      */     //   2920: iastore
/*      */     //   2921: goto -> 3564
/*      */     //   2924: iinc #18, 1
/*      */     //   2927: goto -> 2794
/*      */     //   2930: aload_0
/*      */     //   2931: getfield world : Lnet/minecraft/world/World;
/*      */     //   2934: aload_0
/*      */     //   2935: ldc2_w 2.0
/*      */     //   2938: invokevirtual getClosestPlayerToEntity : (Lnet/minecraft/entity/Entity;D)Lnet/minecraft/entity/player/EntityPlayer;
/*      */     //   2941: astore #19
/*      */     //   2943: aload #19
/*      */     //   2945: ifnull -> 3564
/*      */     //   2948: aload_0
/*      */     //   2949: bipush #7
/*      */     //   2951: invokespecial changeKittyState : (I)V
/*      */     //   2954: goto -> 3564
/*      */     //   2957: aload_0
/*      */     //   2958: invokevirtual getAttackTarget : ()Lnet/minecraft/entity/EntityLivingBase;
/*      */     //   2961: ifnull -> 2974
/*      */     //   2964: aload_0
/*      */     //   2965: invokevirtual getAttackTarget : ()Lnet/minecraft/entity/EntityLivingBase;
/*      */     //   2968: instanceof drzhark/mocreatures/entity/passive/MoCEntityKitty
/*      */     //   2971: ifne -> 2983
/*      */     //   2974: aload_0
/*      */     //   2975: bipush #9
/*      */     //   2977: invokespecial changeKittyState : (I)V
/*      */     //   2980: goto -> 3564
/*      */     //   2983: aload_0
/*      */     //   2984: invokevirtual getAttackTarget : ()Lnet/minecraft/entity/EntityLivingBase;
/*      */     //   2987: checkcast drzhark/mocreatures/entity/passive/MoCEntityKitty
/*      */     //   2990: astore #20
/*      */     //   2992: aload #20
/*      */     //   2994: ifnull -> 3093
/*      */     //   2997: aload #20
/*      */     //   2999: invokevirtual getKittyState : ()I
/*      */     //   3002: bipush #18
/*      */     //   3004: if_icmpne -> 3093
/*      */     //   3007: aload_0
/*      */     //   3008: getfield rand : Ljava/util/Random;
/*      */     //   3011: bipush #50
/*      */     //   3013: invokevirtual nextInt : (I)I
/*      */     //   3016: ifne -> 3023
/*      */     //   3019: aload_0
/*      */     //   3020: invokevirtual swingArm : ()V
/*      */     //   3023: aload_0
/*      */     //   3024: aload #20
/*      */     //   3026: invokevirtual getDistance : (Lnet/minecraft/entity/Entity;)F
/*      */     //   3029: fstore #21
/*      */     //   3031: fload #21
/*      */     //   3033: ldc_w 5.0
/*      */     //   3036: fcmpg
/*      */     //   3037: ifge -> 3050
/*      */     //   3040: aload_0
/*      */     //   3041: dup
/*      */     //   3042: getfield kittytimer : I
/*      */     //   3045: iconst_1
/*      */     //   3046: iadd
/*      */     //   3047: putfield kittytimer : I
/*      */     //   3050: aload_0
/*      */     //   3051: getfield kittytimer : I
/*      */     //   3054: sipush #500
/*      */     //   3057: if_icmple -> 3090
/*      */     //   3060: aload_0
/*      */     //   3061: getfield rand : Ljava/util/Random;
/*      */     //   3064: bipush #50
/*      */     //   3066: invokevirtual nextInt : (I)I
/*      */     //   3069: ifne -> 3090
/*      */     //   3072: aload_0
/*      */     //   3073: invokevirtual getAttackTarget : ()Lnet/minecraft/entity/EntityLivingBase;
/*      */     //   3076: checkcast drzhark/mocreatures/entity/passive/MoCEntityKitty
/*      */     //   3079: bipush #7
/*      */     //   3081: invokespecial changeKittyState : (I)V
/*      */     //   3084: aload_0
/*      */     //   3085: bipush #19
/*      */     //   3087: invokespecial changeKittyState : (I)V
/*      */     //   3090: goto -> 3564
/*      */     //   3093: aload_0
/*      */     //   3094: bipush #9
/*      */     //   3096: invokespecial changeKittyState : (I)V
/*      */     //   3099: goto -> 3564
/*      */     //   3102: aload_0
/*      */     //   3103: getfield rand : Ljava/util/Random;
/*      */     //   3106: bipush #20
/*      */     //   3108: invokevirtual nextInt : (I)I
/*      */     //   3111: ifeq -> 3117
/*      */     //   3114: goto -> 3564
/*      */     //   3117: aload_0
/*      */     //   3118: aload_0
/*      */     //   3119: ldc2_w 18.0
/*      */     //   3122: iconst_0
/*      */     //   3123: invokevirtual getKittyStuff : (Lnet/minecraft/entity/Entity;DZ)Lnet/minecraft/entity/EntityLiving;
/*      */     //   3126: checkcast drzhark/mocreatures/entity/item/MoCEntityKittyBed
/*      */     //   3129: astore #21
/*      */     //   3131: aload #21
/*      */     //   3133: ifnull -> 3564
/*      */     //   3136: aload #21
/*      */     //   3138: invokevirtual isBeingRidden : ()Z
/*      */     //   3141: ifeq -> 3147
/*      */     //   3144: goto -> 3564
/*      */     //   3147: aload #21
/*      */     //   3149: aload_0
/*      */     //   3150: invokevirtual getDistance : (Lnet/minecraft/entity/Entity;)F
/*      */     //   3153: fstore #22
/*      */     //   3155: fload #22
/*      */     //   3157: fconst_2
/*      */     //   3158: fcmpl
/*      */     //   3159: ifle -> 3170
/*      */     //   3162: aload_0
/*      */     //   3163: aload #21
/*      */     //   3165: fload #22
/*      */     //   3167: invokevirtual getMyOwnPath : (Lnet/minecraft/entity/Entity;F)V
/*      */     //   3170: fload #22
/*      */     //   3172: fconst_2
/*      */     //   3173: fcmpg
/*      */     //   3174: ifge -> 3564
/*      */     //   3177: aload_0
/*      */     //   3178: aload #21
/*      */     //   3180: invokevirtual startRiding : (Lnet/minecraft/entity/Entity;)Z
/*      */     //   3183: ifeq -> 3564
/*      */     //   3186: aload_0
/*      */     //   3187: bipush #20
/*      */     //   3189: invokespecial changeKittyState : (I)V
/*      */     //   3192: goto -> 3564
/*      */     //   3195: aload_0
/*      */     //   3196: invokevirtual getRidingEntity : ()Lnet/minecraft/entity/Entity;
/*      */     //   3199: ifnonnull -> 3211
/*      */     //   3202: aload_0
/*      */     //   3203: bipush #19
/*      */     //   3205: invokespecial changeKittyState : (I)V
/*      */     //   3208: goto -> 3564
/*      */     //   3211: aload_0
/*      */     //   3212: ldc_w 180.0
/*      */     //   3215: putfield rotationYaw : F
/*      */     //   3218: aload_0
/*      */     //   3219: dup
/*      */     //   3220: getfield kittytimer : I
/*      */     //   3223: iconst_1
/*      */     //   3224: iadd
/*      */     //   3225: putfield kittytimer : I
/*      */     //   3228: aload_0
/*      */     //   3229: getfield kittytimer : I
/*      */     //   3232: sipush #1000
/*      */     //   3235: if_icmpgt -> 3241
/*      */     //   3238: goto -> 3564
/*      */     //   3241: aload_0
/*      */     //   3242: getfield rand : Ljava/util/Random;
/*      */     //   3245: iconst_3
/*      */     //   3246: invokevirtual nextInt : (I)I
/*      */     //   3249: iconst_1
/*      */     //   3250: iadd
/*      */     //   3251: istore #23
/*      */     //   3253: iconst_0
/*      */     //   3254: istore #24
/*      */     //   3256: iload #24
/*      */     //   3258: iload #23
/*      */     //   3260: if_icmpge -> 3366
/*      */     //   3263: new drzhark/mocreatures/entity/passive/MoCEntityKitty
/*      */     //   3266: dup
/*      */     //   3267: aload_0
/*      */     //   3268: getfield world : Lnet/minecraft/world/World;
/*      */     //   3271: invokespecial <init> : (Lnet/minecraft/world/World;)V
/*      */     //   3274: astore #25
/*      */     //   3276: aload_0
/*      */     //   3277: invokevirtual getType : ()I
/*      */     //   3280: istore #26
/*      */     //   3282: aload_0
/*      */     //   3283: getfield rand : Ljava/util/Random;
/*      */     //   3286: iconst_2
/*      */     //   3287: invokevirtual nextInt : (I)I
/*      */     //   3290: ifne -> 3306
/*      */     //   3293: aload_0
/*      */     //   3294: getfield rand : Ljava/util/Random;
/*      */     //   3297: bipush #8
/*      */     //   3299: invokevirtual nextInt : (I)I
/*      */     //   3302: iconst_1
/*      */     //   3303: iadd
/*      */     //   3304: istore #26
/*      */     //   3306: aload #25
/*      */     //   3308: iload #26
/*      */     //   3310: invokevirtual setType : (I)V
/*      */     //   3313: aload #25
/*      */     //   3315: aload_0
/*      */     //   3316: getfield posX : D
/*      */     //   3319: aload_0
/*      */     //   3320: getfield posY : D
/*      */     //   3323: aload_0
/*      */     //   3324: getfield posZ : D
/*      */     //   3327: invokevirtual setPosition : (DDD)V
/*      */     //   3330: aload_0
/*      */     //   3331: getfield world : Lnet/minecraft/world/World;
/*      */     //   3334: aload #25
/*      */     //   3336: invokevirtual spawnEntity : (Lnet/minecraft/entity/Entity;)Z
/*      */     //   3339: pop
/*      */     //   3340: aload_0
/*      */     //   3341: getstatic net/minecraft/init/SoundEvents.ENTITY_CHICKEN_EGG : Lnet/minecraft/util/SoundEvent;
/*      */     //   3344: invokestatic playCustomSound : (Lnet/minecraft/entity/Entity;Lnet/minecraft/util/SoundEvent;)V
/*      */     //   3347: aload #25
/*      */     //   3349: iconst_0
/*      */     //   3350: invokevirtual setAdult : (Z)V
/*      */     //   3353: aload #25
/*      */     //   3355: bipush #10
/*      */     //   3357: invokespecial changeKittyState : (I)V
/*      */     //   3360: iinc #24, 1
/*      */     //   3363: goto -> 3256
/*      */     //   3366: aload_0
/*      */     //   3367: bipush #21
/*      */     //   3369: invokespecial changeKittyState : (I)V
/*      */     //   3372: goto -> 3564
/*      */     //   3375: aload_0
/*      */     //   3376: dup
/*      */     //   3377: getfield kittytimer : I
/*      */     //   3380: iconst_1
/*      */     //   3381: iadd
/*      */     //   3382: putfield kittytimer : I
/*      */     //   3385: aload_0
/*      */     //   3386: getfield kittytimer : I
/*      */     //   3389: sipush #2000
/*      */     //   3392: if_icmple -> 3505
/*      */     //   3395: aload_0
/*      */     //   3396: getfield world : Lnet/minecraft/world/World;
/*      */     //   3399: aload_0
/*      */     //   3400: aload_0
/*      */     //   3401: invokevirtual getEntityBoundingBox : ()Lnet/minecraft/util/math/AxisAlignedBB;
/*      */     //   3404: ldc2_w 24.0
/*      */     //   3407: ldc2_w 8.0
/*      */     //   3410: ldc2_w 24.0
/*      */     //   3413: invokevirtual expand : (DDD)Lnet/minecraft/util/math/AxisAlignedBB;
/*      */     //   3416: invokevirtual getEntitiesWithinAABBExcludingEntity : (Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/AxisAlignedBB;)Ljava/util/List;
/*      */     //   3419: astore #24
/*      */     //   3421: iconst_0
/*      */     //   3422: istore #25
/*      */     //   3424: iconst_0
/*      */     //   3425: istore #26
/*      */     //   3427: iload #26
/*      */     //   3429: aload #24
/*      */     //   3431: invokeinterface size : ()I
/*      */     //   3436: if_icmpge -> 3483
/*      */     //   3439: aload #24
/*      */     //   3441: iload #26
/*      */     //   3443: invokeinterface get : (I)Ljava/lang/Object;
/*      */     //   3448: checkcast net/minecraft/entity/Entity
/*      */     //   3451: astore #27
/*      */     //   3453: aload #27
/*      */     //   3455: instanceof drzhark/mocreatures/entity/passive/MoCEntityKitty
/*      */     //   3458: ifeq -> 3477
/*      */     //   3461: aload #27
/*      */     //   3463: checkcast drzhark/mocreatures/entity/passive/MoCEntityKitty
/*      */     //   3466: invokevirtual getKittyState : ()I
/*      */     //   3469: bipush #10
/*      */     //   3471: if_icmpne -> 3477
/*      */     //   3474: iinc #25, 1
/*      */     //   3477: iinc #26, 1
/*      */     //   3480: goto -> 3427
/*      */     //   3483: iload #25
/*      */     //   3485: iconst_1
/*      */     //   3486: if_icmpge -> 3498
/*      */     //   3489: aload_0
/*      */     //   3490: bipush #7
/*      */     //   3492: invokespecial changeKittyState : (I)V
/*      */     //   3495: goto -> 3564
/*      */     //   3498: aload_0
/*      */     //   3499: sipush #1000
/*      */     //   3502: putfield kittytimer : I
/*      */     //   3505: aload_0
/*      */     //   3506: invokevirtual getAttackTarget : ()Lnet/minecraft/entity/EntityLivingBase;
/*      */     //   3509: ifnull -> 3564
/*      */     //   3512: aload_0
/*      */     //   3513: invokevirtual getAttackTarget : ()Lnet/minecraft/entity/EntityLivingBase;
/*      */     //   3516: instanceof net/minecraft/entity/player/EntityPlayer
/*      */     //   3519: ifeq -> 3564
/*      */     //   3522: aload_0
/*      */     //   3523: getfield rand : Ljava/util/Random;
/*      */     //   3526: sipush #300
/*      */     //   3529: invokevirtual nextInt : (I)I
/*      */     //   3532: ifne -> 3564
/*      */     //   3535: aload_0
/*      */     //   3536: aconst_null
/*      */     //   3537: invokevirtual setAttackTarget : (Lnet/minecraft/entity/EntityLivingBase;)V
/*      */     //   3540: goto -> 3564
/*      */     //   3543: aload_0
/*      */     //   3544: iconst_1
/*      */     //   3545: invokespecial changeKittyState : (I)V
/*      */     //   3548: goto -> 3564
/*      */     //   3551: aload_0
/*      */     //   3552: bipush #7
/*      */     //   3554: invokespecial changeKittyState : (I)V
/*      */     //   3557: goto -> 3564
/*      */     //   3560: aload_0
/*      */     //   3561: invokespecial onLivingUpdate : ()V
/*      */     //   3564: return
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #621	-> 0
/*      */     //   #622	-> 10
/*      */     //   #623	-> 26
/*      */     //   #625	-> 32
/*      */     //   #626	-> 41
/*      */     //   #628	-> 45
/*      */     //   #629	-> 58
/*      */     //   #631	-> 74
/*      */     //   #632	-> 94
/*      */     //   #633	-> 104
/*      */     //   #634	-> 113
/*      */     //   #637	-> 118
/*      */     //   #638	-> 144
/*      */     //   #641	-> 149
/*      */     //   #643	-> 161
/*      */     //   #645	-> 280
/*      */     //   #647	-> 283
/*      */     //   #650	-> 286
/*      */     //   #651	-> 298
/*      */     //   #652	-> 307
/*      */     //   #653	-> 311
/*      */     //   #657	-> 319
/*      */     //   #658	-> 338
/*      */     //   #660	-> 341
/*      */     //   #661	-> 356
/*      */     //   #662	-> 360
/*      */     //   #664	-> 363
/*      */     //   #665	-> 369
/*      */     //   #666	-> 375
/*      */     //   #668	-> 381
/*      */     //   #669	-> 398
/*      */     //   #670	-> 402
/*      */     //   #671	-> 409
/*      */     //   #672	-> 414
/*      */     //   #677	-> 422
/*      */     //   #678	-> 431
/*      */     //   #679	-> 435
/*      */     //   #684	-> 443
/*      */     //   #685	-> 453
/*      */     //   #686	-> 463
/*      */     //   #687	-> 476
/*      */     //   #688	-> 482
/*      */     //   #690	-> 485
/*      */     //   #691	-> 498
/*      */     //   #692	-> 504
/*      */     //   #695	-> 507
/*      */     //   #696	-> 519
/*      */     //   #698	-> 522
/*      */     //   #699	-> 536
/*      */     //   #700	-> 551
/*      */     //   #701	-> 565
/*      */     //   #703	-> 568
/*      */     //   #704	-> 576
/*      */     //   #705	-> 583
/*      */     //   #707	-> 591
/*      */     //   #708	-> 598
/*      */     //   #709	-> 607
/*      */     //   #710	-> 612
/*      */     //   #716	-> 620
/*      */     //   #717	-> 627
/*      */     //   #718	-> 636
/*      */     //   #719	-> 657
/*      */     //   #720	-> 665
/*      */     //   #722	-> 670
/*      */     //   #723	-> 673
/*      */     //   #724	-> 681
/*      */     //   #726	-> 686
/*      */     //   #727	-> 699
/*      */     //   #728	-> 707
/*      */     //   #733	-> 716
/*      */     //   #734	-> 726
/*      */     //   #735	-> 749
/*      */     //   #736	-> 755
/*      */     //   #738	-> 758
/*      */     //   #739	-> 770
/*      */     //   #741	-> 773
/*      */     //   #742	-> 787
/*      */     //   #743	-> 808
/*      */     //   #745	-> 811
/*      */     //   #746	-> 819
/*      */     //   #747	-> 826
/*      */     //   #749	-> 834
/*      */     //   #750	-> 841
/*      */     //   #751	-> 850
/*      */     //   #757	-> 859
/*      */     //   #758	-> 869
/*      */     //   #759	-> 879
/*      */     //   #761	-> 882
/*      */     //   #762	-> 889
/*      */     //   #763	-> 898
/*      */     //   #764	-> 903
/*      */     //   #765	-> 909
/*      */     //   #767	-> 915
/*      */     //   #768	-> 921
/*      */     //   #771	-> 924
/*      */     //   #772	-> 931
/*      */     //   #774	-> 934
/*      */     //   #775	-> 946
/*      */     //   #776	-> 959
/*      */     //   #777	-> 964
/*      */     //   #778	-> 974
/*      */     //   #779	-> 993
/*      */     //   #780	-> 999
/*      */     //   #784	-> 1002
/*      */     //   #785	-> 1022
/*      */     //   #786	-> 1028
/*      */     //   #788	-> 1031
/*      */     //   #789	-> 1054
/*      */     //   #790	-> 1060
/*      */     //   #792	-> 1063
/*      */     //   #793	-> 1076
/*      */     //   #794	-> 1081
/*      */     //   #796	-> 1084
/*      */     //   #797	-> 1097
/*      */     //   #802	-> 1106
/*      */     //   #803	-> 1126
/*      */     //   #804	-> 1132
/*      */     //   #807	-> 1135
/*      */     //   #808	-> 1152
/*      */     //   #809	-> 1159
/*      */     //   #810	-> 1169
/*      */     //   #811	-> 1178
/*      */     //   #812	-> 1182
/*      */     //   #815	-> 1194
/*      */     //   #821	-> 1205
/*      */     //   #822	-> 1225
/*      */     //   #827	-> 1234
/*      */     //   #828	-> 1244
/*      */     //   #829	-> 1256
/*      */     //   #830	-> 1282
/*      */     //   #832	-> 1285
/*      */     //   #833	-> 1297
/*      */     //   #835	-> 1300
/*      */     //   #836	-> 1314
/*      */     //   #837	-> 1335
/*      */     //   #838	-> 1341
/*      */     //   #839	-> 1350
/*      */     //   #840	-> 1360
/*      */     //   #841	-> 1369
/*      */     //   #843	-> 1372
/*      */     //   #844	-> 1375
/*      */     //   #846	-> 1378
/*      */     //   #847	-> 1388
/*      */     //   #852	-> 1397
/*      */     //   #853	-> 1404
/*      */     //   #854	-> 1410
/*      */     //   #856	-> 1413
/*      */     //   #857	-> 1425
/*      */     //   #858	-> 1451
/*      */     //   #859	-> 1466
/*      */     //   #860	-> 1480
/*      */     //   #861	-> 1501
/*      */     //   #863	-> 1504
/*      */     //   #864	-> 1512
/*      */     //   #865	-> 1521
/*      */     //   #858	-> 1530
/*      */     //   #870	-> 1536
/*      */     //   #871	-> 1562
/*      */     //   #872	-> 1573
/*      */     //   #873	-> 1580
/*      */     //   #875	-> 1597
/*      */     //   #878	-> 1612
/*      */     //   #879	-> 1639
/*      */     //   #880	-> 1644
/*      */     //   #882	-> 1649
/*      */     //   #883	-> 1666
/*      */     //   #884	-> 1676
/*      */     //   #885	-> 1685
/*      */     //   #886	-> 1689
/*      */     //   #887	-> 1701
/*      */     //   #892	-> 1712
/*      */     //   #893	-> 1741
/*      */     //   #894	-> 1751
/*      */     //   #895	-> 1758
/*      */     //   #896	-> 1762
/*      */     //   #899	-> 1769
/*      */     //   #900	-> 1786
/*      */     //   #902	-> 1789
/*      */     //   #903	-> 1799
/*      */     //   #904	-> 1818
/*      */     //   #909	-> 1825
/*      */     //   #910	-> 1838
/*      */     //   #911	-> 1855
/*      */     //   #913	-> 1858
/*      */     //   #914	-> 1868
/*      */     //   #915	-> 1889
/*      */     //   #916	-> 1895
/*      */     //   #918	-> 1898
/*      */     //   #919	-> 1906
/*      */     //   #920	-> 1915
/*      */     //   #925	-> 1926
/*      */     //   #926	-> 1936
/*      */     //   #927	-> 1969
/*      */     //   #928	-> 1975
/*      */     //   #930	-> 1978
/*      */     //   #931	-> 1983
/*      */     //   #932	-> 2002
/*      */     //   #937	-> 2009
/*      */     //   #938	-> 2014
/*      */     //   #939	-> 2029
/*      */     //   #940	-> 2036
/*      */     //   #941	-> 2046
/*      */     //   #942	-> 2055
/*      */     //   #943	-> 2059
/*      */     //   #944	-> 2071
/*      */     //   #945	-> 2081
/*      */     //   #946	-> 2094
/*      */     //   #947	-> 2102
/*      */     //   #948	-> 2108
/*      */     //   #952	-> 2120
/*      */     //   #953	-> 2133
/*      */     //   #955	-> 2139
/*      */     //   #956	-> 2142
/*      */     //   #958	-> 2148
/*      */     //   #961	-> 2151
/*      */     //   #962	-> 2158
/*      */     //   #963	-> 2164
/*      */     //   #965	-> 2167
/*      */     //   #966	-> 2179
/*      */     //   #968	-> 2183
/*      */     //   #969	-> 2190
/*      */     //   #971	-> 2193
/*      */     //   #972	-> 2208
/*      */     //   #973	-> 2217
/*      */     //   #974	-> 2222
/*      */     //   #975	-> 2228
/*      */     //   #977	-> 2231
/*      */     //   #978	-> 2241
/*      */     //   #979	-> 2262
/*      */     //   #984	-> 2271
/*      */     //   #985	-> 2278
/*      */     //   #987	-> 2284
/*      */     //   #988	-> 2291
/*      */     //   #993	-> 2309
/*      */     //   #994	-> 2319
/*      */     //   #995	-> 2336
/*      */     //   #997	-> 2342
/*      */     //   #998	-> 2349
/*      */     //   #999	-> 2368
/*      */     //   #1000	-> 2389
/*      */     //   #1001	-> 2397
/*      */     //   #1003	-> 2400
/*      */     //   #1004	-> 2407
/*      */     //   #1006	-> 2410
/*      */     //   #1007	-> 2441
/*      */     //   #1008	-> 2454
/*      */     //   #1009	-> 2459
/*      */     //   #1010	-> 2469
/*      */     //   #1011	-> 2479
/*      */     //   #1012	-> 2489
/*      */     //   #1014	-> 2492
/*      */     //   #1015	-> 2495
/*      */     //   #1018	-> 2498
/*      */     //   #1019	-> 2517
/*      */     //   #1021	-> 2520
/*      */     //   #1023	-> 2550
/*      */     //   #1024	-> 2555
/*      */     //   #1026	-> 2568
/*      */     //   #1027	-> 2598
/*      */     //   #1028	-> 2610
/*      */     //   #1032	-> 2618
/*      */     //   #1033	-> 2625
/*      */     //   #1035	-> 2628
/*      */     //   #1036	-> 2636
/*      */     //   #1037	-> 2644
/*      */     //   #1038	-> 2652
/*      */     //   #1039	-> 2665
/*      */     //   #1040	-> 2679
/*      */     //   #1043	-> 2691
/*      */     //   #1044	-> 2702
/*      */     //   #1046	-> 2717
/*      */     //   #1048	-> 2729
/*      */     //   #1049	-> 2740
/*      */     //   #1051	-> 2755
/*      */     //   #1053	-> 2767
/*      */     //   #1054	-> 2788
/*      */     //   #1056	-> 2791
/*      */     //   #1058	-> 2794
/*      */     //   #1059	-> 2801
/*      */     //   #1061	-> 2804
/*      */     //   #1062	-> 2834
/*      */     //   #1063	-> 2850
/*      */     //   #1064	-> 2858
/*      */     //   #1065	-> 2894
/*      */     //   #1066	-> 2900
/*      */     //   #1067	-> 2907
/*      */     //   #1068	-> 2914
/*      */     //   #1069	-> 2921
/*      */     //   #1071	-> 2924
/*      */     //   #1072	-> 2927
/*      */     //   #1075	-> 2930
/*      */     //   #1076	-> 2943
/*      */     //   #1077	-> 2948
/*      */     //   #1082	-> 2957
/*      */     //   #1083	-> 2974
/*      */     //   #1084	-> 2980
/*      */     //   #1086	-> 2983
/*      */     //   #1087	-> 2992
/*      */     //   #1088	-> 3007
/*      */     //   #1089	-> 3019
/*      */     //   #1091	-> 3023
/*      */     //   #1092	-> 3031
/*      */     //   #1093	-> 3040
/*      */     //   #1095	-> 3050
/*      */     //   #1096	-> 3072
/*      */     //   #1097	-> 3084
/*      */     //   #1099	-> 3090
/*      */     //   #1100	-> 3093
/*      */     //   #1102	-> 3099
/*      */     //   #1105	-> 3102
/*      */     //   #1106	-> 3114
/*      */     //   #1108	-> 3117
/*      */     //   #1109	-> 3131
/*      */     //   #1110	-> 3144
/*      */     //   #1112	-> 3147
/*      */     //   #1113	-> 3155
/*      */     //   #1114	-> 3162
/*      */     //   #1116	-> 3170
/*      */     //   #1117	-> 3177
/*      */     //   #1118	-> 3186
/*      */     //   #1124	-> 3195
/*      */     //   #1125	-> 3202
/*      */     //   #1126	-> 3208
/*      */     //   #1128	-> 3211
/*      */     //   #1129	-> 3218
/*      */     //   #1130	-> 3228
/*      */     //   #1131	-> 3238
/*      */     //   #1133	-> 3241
/*      */     //   #1134	-> 3253
/*      */     //   #1135	-> 3263
/*      */     //   #1136	-> 3276
/*      */     //   #1137	-> 3282
/*      */     //   #1138	-> 3293
/*      */     //   #1140	-> 3306
/*      */     //   #1141	-> 3313
/*      */     //   #1142	-> 3330
/*      */     //   #1143	-> 3340
/*      */     //   #1144	-> 3347
/*      */     //   #1145	-> 3353
/*      */     //   #1134	-> 3360
/*      */     //   #1150	-> 3366
/*      */     //   #1151	-> 3372
/*      */     //   #1154	-> 3375
/*      */     //   #1155	-> 3385
/*      */     //   #1156	-> 3395
/*      */     //   #1157	-> 3421
/*      */     //   #1158	-> 3424
/*      */     //   #1159	-> 3439
/*      */     //   #1160	-> 3453
/*      */     //   #1161	-> 3474
/*      */     //   #1158	-> 3477
/*      */     //   #1165	-> 3483
/*      */     //   #1166	-> 3489
/*      */     //   #1167	-> 3495
/*      */     //   #1169	-> 3498
/*      */     //   #1171	-> 3505
/*      */     //   #1172	-> 3535
/*      */     //   #1177	-> 3543
/*      */     //   #1178	-> 3548
/*      */     //   #1181	-> 3551
/*      */     //   #1182	-> 3557
/*      */     //   #1185	-> 3560
/*      */     //   #1187	-> 3564
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   307	12	1	entityliving	Lnet/minecraft/entity/EntityLivingBase;
/*      */     //   356	66	1	entityitem	Lnet/minecraft/entity/item/EntityItem;
/*      */     //   369	53	2	f	F
/*      */     //   431	12	3	entityliving1	Lnet/minecraft/entity/EntityLivingBase;
/*      */     //   536	84	4	entitykittybed	Ldrzhark/mocreatures/entity/item/MoCEntityKittyBed;
/*      */     //   576	44	5	f5	F
/*      */     //   636	34	6	entitykittybed1	Ldrzhark/mocreatures/entity/item/MoCEntityKittyBed;
/*      */     //   787	72	6	entitylitterbox	Ldrzhark/mocreatures/entity/item/MoCEntityLitterBox;
/*      */     //   819	40	7	f6	F
/*      */     //   898	26	8	entitylitterbox1	Ldrzhark/mocreatures/entity/item/MoCEntityLitterBox;
/*      */     //   974	28	10	stack	Lnet/minecraft/item/ItemStack;
/*      */     //   959	43	9	entityplayer	Lnet/minecraft/entity/player/EntityPlayer;
/*      */     //   1169	36	9	f1	F
/*      */     //   1314	61	11	entity	Lnet/minecraft/entity/Entity;
/*      */     //   1282	96	9	list	Ljava/util/List;
/*      */     //   1285	93	10	j	I
/*      */     //   1480	50	11	entity1	Lnet/minecraft/entity/Entity;
/*      */     //   1512	18	12	f9	F
/*      */     //   1454	82	10	k	I
/*      */     //   1451	85	9	list1	Ljava/util/List;
/*      */     //   1573	39	9	i	I
/*      */     //   1676	36	9	f2	F
/*      */     //   1751	18	9	f3	F
/*      */     //   1799	26	9	f4	F
/*      */     //   1838	88	10	entityplayer1	Lnet/minecraft/entity/player/EntityPlayer;
/*      */     //   1868	58	11	itemstack1	Lnet/minecraft/item/ItemStack;
/*      */     //   1906	20	12	f8	F
/*      */     //   2046	93	13	f7	F
/*      */     //   2217	54	13	entityplayer2	Lnet/minecraft/entity/player/EntityPlayer;
/*      */     //   2241	30	14	itemstack2	Lnet/minecraft/item/ItemStack;
/*      */     //   2441	54	17	blockstate	Lnet/minecraft/block/state/IBlockState;
/*      */     //   2400	98	16	i1	I
/*      */     //   2389	109	15	ai	[I
/*      */     //   2550	68	15	pathentity	Lnet/minecraft/pathfinding/Path;
/*      */     //   2598	20	16	double1	Ljava/lang/Double;
/*      */     //   2834	93	19	pos	Lnet/minecraft/util/math/BlockPos;
/*      */     //   2850	77	20	block	Lnet/minecraft/block/Block;
/*      */     //   2636	294	15	l	I
/*      */     //   2644	286	16	j1	I
/*      */     //   2652	278	17	l1	I
/*      */     //   2794	136	18	i4	I
/*      */     //   2943	14	19	entityplayer3	Lnet/minecraft/entity/player/EntityPlayer;
/*      */     //   3031	59	21	f10	F
/*      */     //   2992	110	20	entitykitty	Ldrzhark/mocreatures/entity/passive/MoCEntityKitty;
/*      */     //   3131	64	21	entitykittybed2	Ldrzhark/mocreatures/entity/item/MoCEntityKittyBed;
/*      */     //   3155	40	22	f11	F
/*      */     //   3276	84	25	entitykitty1	Ldrzhark/mocreatures/entity/passive/MoCEntityKitty;
/*      */     //   3282	78	26	babytype	I
/*      */     //   3256	110	24	l2	I
/*      */     //   3253	122	23	i2	I
/*      */     //   3453	24	27	entity2	Lnet/minecraft/entity/Entity;
/*      */     //   3427	56	26	l3	I
/*      */     //   3421	84	24	list2	Ljava/util/List;
/*      */     //   3424	81	25	i3	I
/*      */     //   0	3565	0	this	Ldrzhark/mocreatures/entity/passive/MoCEntityKitty;
/*      */     // Local variable type table:
/*      */     //   start	length	slot	name	signature
/*      */     //   1282	96	9	list	Ljava/util/List<Lnet/minecraft/entity/Entity;>;
/*      */     //   1451	85	9	list1	Ljava/util/List<Lnet/minecraft/entity/Entity;>;
/*      */     //   3421	84	24	list2	Ljava/util/List<Lnet/minecraft/entity/Entity;>;
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
/*      */   public boolean onMaBack() {
/* 1190 */     return (getKittyState() == 15);
/*      */   }
/*      */
/*      */
/*      */   public void onUpdate() {
/* 1195 */     super.onUpdate();
/* 1196 */     if (getIsSwinging()) {
/* 1197 */       this.swingProgress += 0.2F;
/* 1198 */       if (this.swingProgress > 2.0F) {
/* 1199 */         setSwinging(false);
/* 1200 */         this.swingProgress = 0.0F;
/*      */       }
/*      */     }
/*      */   }
/*      */
/*      */   private boolean pickable() {
/* 1206 */     return (getKittyState() != 13 && getKittyState() != 14 && getKittyState() != 15 && getKittyState() != 19 && getKittyState() != 20 &&
/* 1207 */       getKittyState() != 21);
/*      */   }
/*      */
/*      */
/*      */   public boolean renderName() {
/* 1212 */     return (getKittyState() != 14 && getKittyState() != 15 && getKittyState() > 1 && super.renderName());
/*      */   }
/*      */
/*      */
/*      */   public void setDead() {
/* 1217 */     if (!this.world.isRemote && getKittyState() > 2 && getHealth() > 0.0F) {
/*      */       return;
/*      */     }
/* 1220 */     super.setDead();
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */   public void swingArm() {
/* 1227 */     if (!this.world.isRemote) {
/* 1228 */       MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 0), new NetworkRegistry.TargetPoint(this.world.provider
/* 1229 */             .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*      */     }
/*      */
/* 1232 */     if (!getIsSwinging()) {
/* 1233 */       setSwinging(true);
/* 1234 */       this.swingProgress = 0.0F;
/*      */     }
/*      */   }
/*      */
/*      */
/*      */   public void performAnimation(int i) {
/* 1240 */     swingArm();
/*      */   }
/*      */
/*      */   public boolean upsideDown() {
/* 1244 */     return (getKittyState() == 14);
/*      */   }
/*      */
/*      */   public boolean whipeable() {
/* 1248 */     return (getKittyState() != 13);
/*      */   }
/*      */
/*      */
/*      */   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
/* 1253 */     super.readEntityFromNBT(nbttagcompound);
/* 1254 */     setSitting(nbttagcompound.getBoolean("Sitting"));
/* 1255 */     setKittyState(nbttagcompound.getInteger("KittyState"));
/*      */   }
/*      */
/*      */
/*      */   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
/* 1260 */     super.writeEntityToNBT(nbttagcompound);
/* 1261 */     nbttagcompound.setBoolean("Sitting", getIsSitting());
/* 1262 */     nbttagcompound.setInteger("KittyState", getKittyState());
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
/*      */   public void onDeath(DamageSource damagesource) {
/* 1278 */     if (!this.world.isRemote &&
/* 1279 */       getIsTamed()) {
/* 1280 */       MoCTools.dropCustomItem((Entity)this, this.world, new ItemStack((Item)MoCItems.medallion, 1));
/*      */     }
/*      */
/* 1283 */     super.onDeath(damagesource);
/*      */   }
/*      */
/*      */
/*      */   public boolean swimmerEntity() {
/* 1288 */     return true;
/*      */   }
/*      */
/*      */
/*      */   public int nameYOffset() {
/* 1293 */     if (getIsSitting())
/* 1294 */       return -30;
/* 1295 */     return -40;
/*      */   }
/*      */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityKitty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
