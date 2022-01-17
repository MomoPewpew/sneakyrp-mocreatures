/*     */ package drzhark.mocreatures.entity.passive;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.MoCEntityAnimal;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFleeFromPlayer;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIHunt;
/*     */ import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIPanicMoC;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import drzhark.mocreatures.network.MoCMessageHandler;
/*     */ import drzhark.mocreatures.network.message.MoCMessageAnimation;
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
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.init.MobEffects;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*     */
/*     */ public class MoCEntityKomodo extends MoCEntityTameableAnimal {
/*     */   private int sitCounter;
/*     */   public int tailCounter;
/*  42 */   private static final DataParameter<Boolean> RIDEABLE = EntityDataManager.createKey(MoCEntityKomodo.class, DataSerializers.BOOLEAN); public int tongueCounter; public int mouthCounter;
/*     */
/*     */   public MoCEntityKomodo(World world) {
/*  45 */     super(world);
/*  46 */     setSize(1.6F, 0.5F);
/*  47 */     this.texture = "komododragon.png";
/*  48 */     setTamed(false);
/*  49 */     setAdult(false);
/*  50 */     this.stepHeight = 1.0F;
/*     */
/*  52 */     if (this.rand.nextInt(6) == 0) {
/*  53 */       setEdad(30 + this.rand.nextInt(40));
/*     */     } else {
/*  55 */       setEdad(90 + this.rand.nextInt(20));
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   protected void initEntityAI() {
/*  61 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIPanicMoC((EntityCreature)this, 1.1D));
/*  62 */     this.tasks.addTask(3, (EntityAIBase)new EntityAIFleeFromPlayer((EntityCreature)this, 1.1D, 4.0D));
/*  63 */     this.tasks.addTask(4, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
/*  64 */     this.tasks.addTask(7, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 0.9D));
/*  65 */     this.tasks.addTask(9, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  66 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHunt((EntityCreature)this, EntityAnimal.class, true));
/*  67 */     this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
/*     */   }
/*     */
/*     */
/*     */   protected void applyEntityAttributes() {
/*  72 */     super.applyEntityAttributes();
/*  73 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
/*  74 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
/*  75 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
/*  76 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.18D);
/*     */   }
/*     */
/*     */
/*     */   protected void entityInit() {
/*  81 */     super.entityInit();
/*  82 */     this.dataManager.register(RIDEABLE, Boolean.valueOf(false));
/*     */   }
/*     */
/*     */
/*     */   public void setRideable(boolean flag) {
/*  87 */     this.dataManager.set(RIDEABLE, Boolean.valueOf(flag));
/*     */   }
/*     */
/*     */
/*     */   public boolean getIsRideable() {
/*  92 */     return ((Boolean)this.dataManager.get(RIDEABLE)).booleanValue();
/*     */   }
/*     */
/*     */
/*     */   public boolean getCanSpawnHere() {
/*  97 */     return (getCanSpawnHereCreature() && getCanSpawnHereLiving());
/*     */   }
/*     */
/*     */
/*     */   protected SoundEvent getDeathSound() {
/* 102 */     openmouth();
/* 103 */     return MoCSoundEvents.ENTITY_SNAKE_DEATH;
/*     */   }
/*     */
/*     */
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 108 */     openmouth();
/* 109 */     return MoCSoundEvents.ENTITY_SNAKE_HURT;
/*     */   }
/*     */
/*     */
/*     */   protected SoundEvent getAmbientSound() {
/* 114 */     openmouth();
/* 115 */     return MoCSoundEvents.ENTITY_SNAKE_AMBIENT;
/*     */   }
/*     */
/*     */
/*     */   public int getTalkInterval() {
/* 120 */     return 500;
/*     */   }
/*     */
/*     */
/*     */   public void onLivingUpdate() {
/* 125 */     super.onLivingUpdate();
/* 126 */     if (this.sitCounter > 0 && (isBeingRidden() || ++this.sitCounter > 150)) {
/* 127 */       this.sitCounter = 0;
/*     */     }
/* 129 */     if (!this.world.isRemote) {
/* 130 */       if (!isSwimming() && !isBeingRidden() && this.sitCounter == 0 && this.rand.nextInt(500) == 0) {
/* 131 */         sit();
/*     */       }
/*     */     }
/*     */     else {
/*     */
/* 136 */       if (this.tailCounter > 0 && ++this.tailCounter > 60) {
/* 137 */         this.tailCounter = 0;
/*     */       }
/*     */
/* 140 */       if (this.rand.nextInt(100) == 0) {
/* 141 */         this.tailCounter = 1;
/*     */       }
/*     */
/* 144 */       if (this.rand.nextInt(100) == 0) {
/* 145 */         this.tongueCounter = 1;
/*     */       }
/*     */
/* 148 */       if (this.mouthCounter > 0 && ++this.mouthCounter > 30) {
/* 149 */         this.mouthCounter = 0;
/*     */       }
/*     */
/* 152 */       if (this.tongueCounter > 0 && ++this.tongueCounter > 20) {
/* 153 */         this.tongueCounter = 0;
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */   private void openmouth() {
/* 159 */     this.mouthCounter = 1;
/*     */   }
/*     */
/*     */   private void sit() {
/* 163 */     this.sitCounter = 1;
/* 164 */     if (!this.world.isRemote) {
/* 165 */       MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 0), new NetworkRegistry.TargetPoint(this.world.provider
/* 166 */             .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */     }
/* 168 */     getNavigator().clearPath();
/*     */   }
/*     */
/*     */
/*     */   public void performAnimation(int animationType) {
/* 173 */     if (animationType == 0) {
/*     */
/* 175 */       this.sitCounter = 1;
/* 176 */       getNavigator().clearPath();
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   protected void dropFewItems(boolean flag, int x) {
/* 182 */     boolean flag2 = (getEdad() > 90 && this.rand.nextInt(5) == 0);
/*     */
/* 184 */     if (flag2) {
/* 185 */       int j = this.rand.nextInt(2) + 1;
/* 186 */       for (int k = 0; k < j; k++) {
/* 187 */         entityDropItem(new ItemStack((Item)MoCItems.mocegg, 1, 33), 0.0F);
/*     */       }
/*     */     } else {
/*     */
/* 191 */       entityDropItem(new ItemStack((Item)MoCItems.hideCroc, 1, 0), 0.0F);
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   public float getSizeFactor() {
/* 197 */     if (!getIsAdult()) {
/* 198 */       return getEdad() * 0.01F;
/*     */     }
/* 200 */     return 1.2F;
/*     */   }
/*     */
/*     */
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/* 205 */     Boolean tameResult = processTameInteract(player, hand);
/* 206 */     if (tameResult != null) {
/* 207 */       return tameResult.booleanValue();
/*     */     }
/*     */
/* 210 */     ItemStack stack = player.getHeldItem(hand);
/* 211 */     if (!stack.isEmpty() && getIsTamed() && (getEdad() > 90 || getIsAdult()) && !getIsRideable() && (stack
/* 212 */       .getItem() == Items.SADDLE || stack.getItem() == MoCItems.horsesaddle)) {
/* 213 */       stack.shrink(1);
/* 214 */       if (stack.isEmpty()) {
/* 215 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 217 */       setRideable(true);
/* 218 */       return true;
/*     */     }
/*     */
/* 221 */     if (getIsRideable() && getIsTamed() && getEdad() > 90 && !isBeingRidden()) {
/* 222 */       if (!this.world.isRemote && player.startRiding((Entity)this)) {
/* 223 */         player.rotationYaw = this.rotationYaw;
/* 224 */         player.rotationPitch = this.rotationPitch;
/*     */       }
/*     */
/* 227 */       return true;
/*     */     }
/*     */
/* 230 */     return super.processInteract(player, hand);
/*     */   }
/*     */
/*     */
/*     */   public boolean isMovementCeased() {
/* 235 */     return (getIsSitting() || isBeingRidden());
/*     */   }
/*     */
/*     */
/*     */   public boolean rideableEntity() {
/* 240 */     return true;
/*     */   }
/*     */
/*     */
/*     */   public int nameYOffset() {
/* 245 */     if (getIsAdult()) {
/* 246 */       return -50;
/*     */     }
/* 248 */     return -50 + getEdad() / 2;
/*     */   }
/*     */
/*     */
/*     */   public boolean canBreatheUnderwater() {
/* 253 */     return true;
/*     */   }
/*     */
/*     */
/*     */   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
/* 258 */     super.writeEntityToNBT(nbttagcompound);
/* 259 */     nbttagcompound.setBoolean("Saddle", getIsRideable());
/*     */   }
/*     */
/*     */
/*     */   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
/* 264 */     super.readEntityFromNBT(nbttagcompound);
/* 265 */     setRideable(nbttagcompound.getBoolean("Saddle"));
/*     */   }
/*     */
/*     */
/*     */   public double getMountedYOffset() {
/* 270 */     double yOff = 0.15000000596046448D;
/* 271 */     boolean sit = (this.sitCounter != 0);
/* 272 */     if (sit);
/*     */
/*     */
/* 275 */     if (getIsAdult()) {
/* 276 */       return yOff + this.height;
/*     */     }
/* 278 */     return (this.height * (120 / getEdad()));
/*     */   }
/*     */
/*     */
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/* 283 */     if (super.attackEntityFrom(damagesource, i)) {
/* 284 */       Entity entity = damagesource.getTrueSource();
/*     */
/* 286 */       if ((entity != null && getIsTamed() && entity instanceof EntityPlayer) || !(entity instanceof EntityLivingBase)) {
/* 287 */         return false;
/*     */       }
/*     */
/* 290 */       if (isBeingRidden() && entity == getRidingEntity()) {
/* 291 */         return false;
/*     */       }
/*     */
/* 294 */       if (entity != this && shouldAttackPlayers()) {
/* 295 */         setAttackTarget((EntityLivingBase)entity);
/*     */       }
/* 297 */       return true;
/*     */     }
/* 299 */     return false;
/*     */   }
/*     */
/*     */
/*     */   public boolean isMyHealFood(ItemStack stack) {
/* 304 */     return (!stack.isEmpty() && (stack.getItem() == MoCItems.ratRaw || stack.getItem() == MoCItems.rawTurkey));
/*     */   }
/*     */
/*     */
/*     */   public boolean canBeCollidedWith() {
/* 309 */     return !isBeingRidden();
/*     */   }
/*     */
/*     */
/*     */   public void dropMyStuff() {
/* 314 */     if (!this.world.isRemote) {
/* 315 */       dropArmor();
/* 316 */       MoCTools.dropSaddle((MoCEntityAnimal)this, this.world);
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   public int getMaxSpawnedInChunk() {
/* 322 */     return 2;
/*     */   }
/*     */
/*     */
/*     */   public boolean canAttackTarget(EntityLivingBase entity) {
/* 327 */     return (!(entity instanceof MoCEntityKomodo) && super.canAttackTarget(entity));
/*     */   }
/*     */
/*     */
/*     */   public int getMaxEdad() {
/* 332 */     return 120;
/*     */   }
/*     */
/*     */
/*     */   public boolean getIsSitting() {
/* 337 */     return (this.sitCounter != 0);
/*     */   }
/*     */
/*     */
/*     */   public boolean isNotScared() {
/* 342 */     return (getEdad() > 70);
/*     */   }
/*     */
/*     */
/*     */   protected void applyEnchantments(EntityLivingBase entityLivingBaseIn, Entity entityIn) {
/* 347 */     if (entityIn instanceof EntityPlayer) {
/* 348 */       MoCreatures.poisonPlayer((EntityPlayer)entityIn);
/*     */     }
/* 350 */     ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.POISON, 150, 0));
/* 351 */     super.applyEnchantments(entityLivingBaseIn, entityIn);
/*     */   }
/*     */
/*     */
/*     */   public boolean isReadyToHunt() {
/* 356 */     return (isNotScared() && !isMovementCeased() && !isBeingRidden());
/*     */   }
/*     */
/*     */
/*     */   public boolean isAmphibian() {
/* 361 */     return true;
/*     */   }
/*     */
/*     */
/*     */   public boolean isSwimming() {
/* 366 */     return isInWater();
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityKomodo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
