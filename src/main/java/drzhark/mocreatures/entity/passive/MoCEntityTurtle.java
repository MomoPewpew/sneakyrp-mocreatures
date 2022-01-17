/*     */ package drzhark.mocreatures.entity.passive;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
/*     */ import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFollowOwnerPlayer;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
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
/*     */ public class MoCEntityTurtle extends MoCEntityTameableAnimal {
/*     */   private boolean isSwinging;
/*  37 */   private static final DataParameter<Boolean> IS_UPSIDE_DOWN = EntityDataManager.createKey(MoCEntityTurtle.class, DataSerializers.BOOLEAN); private boolean twistright; private int flopcounter;
/*  38 */   private static final DataParameter<Boolean> IS_HIDING = EntityDataManager.createKey(MoCEntityTurtle.class, DataSerializers.BOOLEAN);
/*     */   
/*     */   public MoCEntityTurtle(World world) {
/*  41 */     super(world);
/*  42 */     setSize(0.6F, 0.4F);
/*  43 */     setAdult(false);
/*  44 */     setEdad(60 + this.rand.nextInt(50));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initEntityAI() {
/*  49 */     this.tasks.addTask(1, (EntityAIBase)new EntityAIFollowOwnerPlayer((EntityLiving)this, 0.8D, 2.0F, 10.0F));
/*  50 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 0.8D, 50));
/*  51 */     this.tasks.addTask(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  56 */     super.applyEntityAttributes();
/*  57 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
/*  58 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.15D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  63 */     super.entityInit();
/*  64 */     this.dataManager.register(IS_UPSIDE_DOWN, Boolean.valueOf(false));
/*  65 */     this.dataManager.register(IS_HIDING, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  70 */     String tempText = "turtle.png";
/*     */     
/*  72 */     if (getPetName().equals("Donatello") || getPetName().equals("donatello")) {
/*  73 */       tempText = "turtled.png";
/*     */     }
/*     */     
/*  76 */     if (getPetName().equals("Leonardo") || getPetName().equals("leonardo")) {
/*  77 */       tempText = "turtlel.png";
/*     */     }
/*     */     
/*  80 */     if (getPetName().equals("Rafael") || getPetName().equals("rafael") || getPetName().equals("raphael") || getPetName().equals("Raphael")) {
/*  81 */       tempText = "turtler.png";
/*     */     }
/*     */     
/*  84 */     if (getPetName().equals("Michelangelo") || getPetName().equals("michelangelo") || getPetName().equals("Michaelangelo") || 
/*  85 */       getPetName().equals("michaelangelo")) {
/*  86 */       tempText = "turtlem.png";
/*     */     }
/*     */     
/*  89 */     return MoCreatures.proxy.getTexture(tempText);
/*     */   }
/*     */   
/*     */   public boolean getIsHiding() {
/*  93 */     return ((Boolean)this.dataManager.get(IS_HIDING)).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean getIsUpsideDown() {
/*  97 */     return ((Boolean)this.dataManager.get(IS_UPSIDE_DOWN)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setIsHiding(boolean flag) {
/* 101 */     this.dataManager.set(IS_HIDING, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public void setIsUpsideDown(boolean flag) {
/* 105 */     this.flopcounter = 0;
/* 106 */     this.swingProgress = 0.0F;
/* 107 */     this.dataManager.set(IS_UPSIDE_DOWN, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   public double getYOffset() {
/* 112 */     if (getRidingEntity() instanceof EntityPlayer) {
/* 113 */       if (((EntityPlayer)getRidingEntity()).isSneaking()) {
/* 114 */         return -0.25D + (300.0D - getEdad()) / 500.0D;
/*     */       }
/* 116 */       return (300.0D - getEdad()) / 500.0D;
/*     */     } 
/*     */     
/* 119 */     return super.getYOffset();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/* 124 */     Boolean tameResult = processTameInteract(player, hand);
/* 125 */     if (tameResult != null) {
/* 126 */       return tameResult.booleanValue();
/*     */     }
/*     */     
/* 129 */     if (getIsTamed()) {
/* 130 */       if (getIsUpsideDown()) {
/* 131 */         flipflop(false);
/* 132 */         return true;
/*     */       } 
/* 134 */       if (getRidingEntity() == null && 
/* 135 */         startRiding((Entity)player)) {
/* 136 */         this.rotationYaw = player.rotationYaw;
/*     */       }
/*     */       
/* 139 */       return true;
/*     */     } 
/*     */     
/* 142 */     flipflop(!getIsUpsideDown());
/*     */     
/* 144 */     return super.processInteract(player, hand);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void jump() {
/* 149 */     if (isInsideOfMaterial(Material.WATER)) {
/* 150 */       this.motionY = 0.3D;
/* 151 */       if (isSprinting()) {
/* 152 */         float f = this.rotationYaw * 0.01745329F;
/* 153 */         this.motionX -= (MathHelper.sin(f) * 0.2F);
/* 154 */         this.motionZ += (MathHelper.cos(f) * 0.2F);
/*     */       } 
/* 156 */       this.isAirBorne = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNotScared() {
/* 162 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 167 */     super.onLivingUpdate();
/* 168 */     if (!this.world.isRemote && 
/* 169 */       !getIsUpsideDown() && !getIsTamed()) {
/* 170 */       EntityLivingBase entityliving = getBoogey(4.0D);
/* 171 */       if (entityliving != null && canEntityBeSeen((Entity)entityliving)) {
/* 172 */         if (!getIsHiding() && !isInWater()) {
/* 173 */           MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_TURTLE_ANGRY);
/* 174 */           setIsHiding(true);
/*     */         } 
/*     */         
/* 177 */         getNavigator().clearPath();
/*     */       } else {
/*     */         
/* 180 */         setIsHiding(false);
/* 181 */         if (!hasPath() && this.rand.nextInt(50) == 0) {
/* 182 */           EntityItem entityitem = getClosestItem((Entity)this, 10.0D, Items.MELON, Items.REEDS);
/* 183 */           if (entityitem != null) {
/* 184 */             float f = entityitem.getDistance((Entity)this);
/* 185 */             if (f > 2.0F) {
/* 186 */               getMyOwnPath((Entity)entityitem, f);
/*     */             }
/* 188 */             if (f < 2.0F && entityitem != null && this.deathTime == 0) {
/* 189 */               entityitem.setDead();
/* 190 */               MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_TURTLE_EATING);
/* 191 */               EntityPlayer entityplayer = this.world.getClosestPlayerToEntity((Entity)this, 24.0D);
/* 192 */               if (entityplayer != null) {
/* 193 */                 MoCTools.tameWithName(entityplayer, (IMoCTameable)this);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean swimmerEntity() {
/* 205 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBreatheUnderwater() {
/* 210 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/* 215 */     Entity entity = damagesource.getTrueSource();
/* 216 */     if (getRidingEntity() != null) {
/* 217 */       return false;
/*     */     }
/* 219 */     if (entity == null) {
/* 220 */       return super.attackEntityFrom(damagesource, i);
/*     */     }
/* 222 */     if (getIsHiding()) {
/* 223 */       if (this.rand.nextInt(10) == 0) {
/* 224 */         flipflop(true);
/*     */       }
/* 226 */       return false;
/*     */     } 
/* 228 */     boolean flag = super.attackEntityFrom(damagesource, i);
/* 229 */     if (this.rand.nextInt(3) == 0) {
/* 230 */       flipflop(true);
/*     */     }
/* 232 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void flipflop(boolean flip) {
/* 237 */     setIsUpsideDown(flip);
/* 238 */     setIsHiding(false);
/* 239 */     getNavigator().clearPath();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean entitiesToIgnore(Entity entity) {
/* 244 */     return (entity instanceof MoCEntityTurtle || (entity.height <= this.height && entity.width <= this.width) || super
/* 245 */       .entitiesToIgnore(entity));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 250 */     super.onUpdate();
/*     */     
/* 252 */     if (getRidingEntity() != null && getRidingEntity() instanceof EntityPlayer) {
/* 253 */       EntityPlayer entityplayer = (EntityPlayer)getRidingEntity();
/* 254 */       if (entityplayer != null) {
/* 255 */         this.rotationYaw = entityplayer.rotationYaw;
/*     */       }
/*     */     } 
/*     */     
/* 259 */     if (getIsTamed() && getEdad() < 300 && this.rand.nextInt(900) == 0) {
/* 260 */       setEdad(getEdad() + 1);
/*     */     }
/* 262 */     if (getIsUpsideDown() && isInWater()) {
/* 263 */       setIsUpsideDown(false);
/*     */     }
/* 265 */     if (getIsUpsideDown() && getRidingEntity() == null && this.rand.nextInt(20) == 0) {
/* 266 */       setSwinging(true);
/* 267 */       this.flopcounter++;
/*     */     } 
/*     */     
/* 270 */     if (getIsSwinging()) {
/* 271 */       this.swingProgress += 0.2F;
/*     */       
/* 273 */       boolean flag = (this.flopcounter > this.rand.nextInt(3) + 8);
/*     */       
/* 275 */       if (this.swingProgress > 2.0F && (!flag || this.rand.nextInt(20) == 0)) {
/* 276 */         setSwinging(false);
/* 277 */         this.swingProgress = 0.0F;
/* 278 */         if (this.rand.nextInt(2) == 0) {
/* 279 */           this.twistright = !this.twistright;
/*     */         }
/*     */       }
/* 282 */       else if (this.swingProgress > 9.0F && flag) {
/* 283 */         setSwinging(false);
/* 284 */         MoCTools.playCustomSound((Entity)this, SoundEvents.ENTITY_CHICKEN_EGG);
/* 285 */         setIsUpsideDown(false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean getIsSwinging() {
/* 291 */     return this.isSwinging;
/*     */   }
/*     */   
/*     */   public void setSwinging(boolean flag) {
/* 295 */     this.isSwinging = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMovementCeased() {
/* 300 */     return (getIsUpsideDown() || getIsHiding());
/*     */   }
/*     */   
/*     */   public int getFlipDirection() {
/* 304 */     if (this.twistright) {
/* 305 */       return 1;
/*     */     }
/* 307 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
/* 312 */     super.readEntityFromNBT(nbttagcompound);
/* 313 */     setIsUpsideDown(nbttagcompound.getBoolean("UpsideDown"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
/* 318 */     super.writeEntityToNBT(nbttagcompound);
/* 319 */     nbttagcompound.setBoolean("UpsideDown", getIsUpsideDown());
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 324 */     return MoCSoundEvents.ENTITY_TURTLE_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getAmbientSound() {
/* 329 */     return MoCSoundEvents.ENTITY_TURTLE_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getDeathSound() {
/* 334 */     return MoCSoundEvents.ENTITY_TURTLE_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 339 */     if (getPetName().equals("Donatello") || getPetName().equals("donatello")) {
/* 340 */       return (Item)MoCItems.bo;
/*     */     }
/*     */     
/* 343 */     if (getPetName().equals("Leonardo") || getPetName().equals("leonardo")) {
/* 344 */       return (Item)MoCItems.katana;
/*     */     }
/*     */     
/* 347 */     if (getPetName().equals("Rafael") || getPetName().equals("rafael") || getPetName().equals("raphael") || getPetName().equals("Raphael")) {
/* 348 */       return (Item)MoCItems.sai;
/*     */     }
/*     */     
/* 351 */     if (getPetName().equals("Michelangelo") || getPetName().equals("michelangelo") || getPetName().equals("Michaelangelo") || 
/* 352 */       getPetName().equals("michaelangelo")) {
/* 353 */       return (Item)MoCItems.nunchaku;
/*     */     }
/* 355 */     return (Item)MoCItems.turtleraw;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTMNT() {
/* 364 */     if (getPetName().equals("Donatello") || getPetName().equals("donatello") || getPetName().equals("Leonardo") || getPetName().equals("leonardo") || 
/* 365 */       getPetName().equals("Rafael") || getPetName().equals("rafael") || getPetName().equals("raphael") || getPetName().equals("Raphael") || 
/* 366 */       getPetName().equals("Michelangelo") || getPetName().equals("michelangelo") || getPetName().equals("Michaelangelo") || 
/* 367 */       getPetName().equals("michaelangelo")) {
/* 368 */       return true;
/*     */     }
/* 370 */     return false;
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
/*     */   public boolean isMyHealFood(ItemStack stack) {
/* 385 */     return (!stack.isEmpty() && (stack.getItem() == Items.REEDS || stack.getItem() == Items.MELON));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxSpawnedInChunk() {
/* 390 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public int nameYOffset() {
/* 395 */     return -10 - getEdad() / 5;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPushedByWater() {
/* 400 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAmphibian() {
/* 405 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAIMoveSpeed() {
/* 410 */     if (isInWater()) {
/* 411 */       return 0.08F;
/*     */     }
/* 413 */     return 0.12F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected double minDivingDepth() {
/* 418 */     return (getEdad() + 8.0D) / 340.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   protected double maxDivingDepth() {
/* 423 */     return 1.0D * getEdad() / 100.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxEdad() {
/* 428 */     return 120;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canRidePlayer() {
/* 434 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityTurtle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */