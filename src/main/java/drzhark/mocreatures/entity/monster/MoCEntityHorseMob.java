/*     */ package drzhark.mocreatures.entity.monster;
/*     */ 
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.MoCEntityMob;
/*     */ import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MoCEntityHorseMob
/*     */   extends MoCEntityMob {
/*     */   public int mouthCounter;
/*     */   public int textCounter;
/*     */   public int standCounter;
/*     */   
/*     */   public MoCEntityHorseMob(World world) {
/*  35 */     super(world);
/*  36 */     setSize(1.4F, 1.6F);
/*     */   }
/*     */   public int tailCounter; public int eatingCounter; public int wingFlapCounter;
/*     */   
/*     */   protected void initEntityAI() {
/*  41 */     this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  42 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
/*  43 */     this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  44 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  49 */     super.applyEntityAttributes();
/*  50 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
/*  51 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
/*  52 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectType() {
/*  57 */     if (this.world.provider.doesWaterVaporize()) {
/*  58 */       setType(38);
/*  59 */       this.isImmuneToFire = true;
/*     */     }
/*  61 */     else if (getType() == 0) {
/*  62 */       int j = this.rand.nextInt(100);
/*  63 */       if (j <= 40) {
/*  64 */         setType(23);
/*  65 */       } else if (j <= 80) {
/*  66 */         setType(26);
/*     */       } else {
/*  68 */         setType(32);
/*     */       } 
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
/*     */   
/*     */   public ResourceLocation getTexture() {
/*     */     String baseTex;
/*     */     int max;
/*     */     String iteratorTex;
/*     */     String decayTex;
/*     */     String NTA;
/*     */     String NTB;
/*     */     String NTC;
/*  89 */     switch (getType()) {
/*     */       
/*     */       case 23:
/*  92 */         if (!MoCreatures.proxy.getAnimateTextures()) {
/*  93 */           return MoCreatures.proxy.getTexture("horseundead.png");
/*     */         }
/*  95 */         baseTex = "horseundead";
/*  96 */         max = 79;
/*     */         
/*  98 */         if (this.rand.nextInt(3) == 0) {
/*  99 */           this.textCounter++;
/*     */         }
/* 101 */         if (this.textCounter < 10) {
/* 102 */           this.textCounter = 10;
/*     */         }
/* 104 */         if (this.textCounter > max) {
/* 105 */           this.textCounter = 10;
/*     */         }
/*     */         
/* 108 */         iteratorTex = "" + this.textCounter;
/* 109 */         iteratorTex = iteratorTex.substring(0, 1);
/* 110 */         decayTex = "" + (getEdad() / 100);
/* 111 */         decayTex = decayTex.substring(0, 1);
/* 112 */         return MoCreatures.proxy.getTexture(baseTex + decayTex + iteratorTex + ".png");
/*     */       
/*     */       case 26:
/* 115 */         return MoCreatures.proxy.getTexture("horseskeleton.png");
/*     */       
/*     */       case 32:
/* 118 */         return MoCreatures.proxy.getTexture("horsebat.png");
/*     */       
/*     */       case 38:
/* 121 */         if (!MoCreatures.proxy.getAnimateTextures()) {
/* 122 */           return MoCreatures.proxy.getTexture("horsenightmare1.png");
/*     */         }
/* 124 */         this.textCounter++;
/* 125 */         if (this.textCounter < 10) {
/* 126 */           this.textCounter = 10;
/*     */         }
/* 128 */         if (this.textCounter > 59) {
/* 129 */           this.textCounter = 10;
/*     */         }
/* 131 */         NTA = "horsenightmare";
/* 132 */         NTB = "" + this.textCounter;
/* 133 */         NTB = NTB.substring(0, 1);
/* 134 */         NTC = ".png";
/*     */         
/* 136 */         return MoCreatures.proxy.getTexture(NTA + NTB + NTC);
/*     */     } 
/*     */     
/* 139 */     return MoCreatures.proxy.getTexture("horseundead.png");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEvent getDeathSound() {
/* 145 */     openMouth();
/* 146 */     return MoCSoundEvents.ENTITY_HORSE_DEATH_UNDEAD;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 151 */     openMouth();
/* 152 */     stand();
/* 153 */     return MoCSoundEvents.ENTITY_HORSE_HURT_UNDEAD;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getAmbientSound() {
/* 158 */     openMouth();
/* 159 */     if (this.rand.nextInt(10) == 0) {
/* 160 */       stand();
/*     */     }
/* 162 */     return MoCSoundEvents.ENTITY_HORSE_AMBIENT_UNDEAD;
/*     */   }
/*     */   
/*     */   public boolean isOnAir() {
/* 166 */     return this.world.isAirBlock(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.posY - 0.2D), 
/* 167 */           MathHelper.floor(this.posZ)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 172 */     super.onUpdate();
/*     */     
/* 174 */     if (this.mouthCounter > 0 && ++this.mouthCounter > 30) {
/* 175 */       this.mouthCounter = 0;
/*     */     }
/*     */     
/* 178 */     if (this.standCounter > 0 && ++this.standCounter > 20) {
/* 179 */       this.standCounter = 0;
/*     */     }
/*     */     
/* 182 */     if (this.tailCounter > 0 && ++this.tailCounter > 8) {
/* 183 */       this.tailCounter = 0;
/*     */     }
/*     */     
/* 186 */     if (this.eatingCounter > 0 && ++this.eatingCounter > 50) {
/* 187 */       this.eatingCounter = 0;
/*     */     }
/*     */     
/* 190 */     if (this.wingFlapCounter > 0 && ++this.wingFlapCounter > 20) {
/* 191 */       this.wingFlapCounter = 0;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFlyer() {
/* 198 */     return (getType() == 25 || 
/* 199 */       getType() == 32 || 
/* 200 */       getType() == 28);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUnicorned() {
/* 209 */     return (getType() == 24 || getType() == 27 || getType() == 32);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 215 */     super.onLivingUpdate();
/*     */     
/* 217 */     if (isOnAir() && isFlyer() && this.rand.nextInt(5) == 0) {
/* 218 */       this.wingFlapCounter = 1;
/*     */     }
/*     */     
/* 221 */     if (this.rand.nextInt(200) == 0) {
/* 222 */       moveTail();
/*     */     }
/*     */     
/* 225 */     if (!isOnAir() && !isBeingRidden() && this.rand.nextInt(250) == 0) {
/* 226 */       stand();
/*     */     }
/*     */     
/* 229 */     if (this.world.isRemote && getType() == 38 && this.rand.nextInt(50) == 0) {
/* 230 */       LavaFX();
/*     */     }
/*     */     
/* 233 */     if (this.world.isRemote && getType() == 23 && this.rand.nextInt(50) == 0) {
/* 234 */       UndeadFX();
/*     */     }
/*     */     
/* 237 */     if (!this.world.isRemote) {
/* 238 */       if (isFlyer() && this.rand.nextInt(500) == 0) {
/* 239 */         wingFlap();
/*     */       }
/*     */       
/* 242 */       if (!isOnAir() && !isBeingRidden() && this.rand.nextInt(300) == 0) {
/* 243 */         setEating();
/*     */       }
/*     */       
/* 246 */       if (!isBeingRidden() && this.rand.nextInt(100) == 0) {
/* 247 */         MoCTools.findMobRider((Entity)this);
/*     */       }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void openMouth() {
/* 266 */     this.mouthCounter = 1;
/*     */   }
/*     */   
/*     */   private void moveTail() {
/* 270 */     this.tailCounter = 1;
/*     */   }
/*     */   
/*     */   private void setEating() {
/* 274 */     this.eatingCounter = 1;
/*     */   }
/*     */   
/*     */   private void stand() {
/* 278 */     this.standCounter = 1;
/*     */   }
/*     */   
/*     */   public void wingFlap() {
/* 282 */     this.wingFlapCounter = 1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 287 */     boolean flag = (this.rand.nextInt(100) < MoCreatures.proxy.rareItemDropChance);
/* 288 */     if (getType() == 32 && MoCreatures.proxy.rareItemDropChance < 25) {
/* 289 */       flag = (this.rand.nextInt(100) < 25);
/*     */     }
/*     */     
/* 292 */     if (flag && (getType() == 36 || (getType() >= 50 && getType() < 60)))
/*     */     {
/* 294 */       return (Item)MoCItems.unicornhorn;
/*     */     }
/*     */     
/* 297 */     if (getType() == 38 && flag && this.world.provider.doesWaterVaporize())
/*     */     {
/* 299 */       return (Item)MoCItems.heartfire;
/*     */     }
/* 301 */     if (getType() == 32 && flag)
/*     */     {
/* 303 */       return (Item)MoCItems.heartdarkness;
/*     */     }
/* 305 */     if (getType() == 26)
/*     */     {
/* 307 */       return Items.BONE;
/*     */     }
/* 309 */     if (getType() == 23 || getType() == 24 || getType() == 25) {
/* 310 */       if (flag) {
/* 311 */         return (Item)MoCItems.heartundead;
/*     */       }
/* 313 */       return Items.ROTTEN_FLESH;
/*     */     } 
/*     */     
/* 316 */     if (getType() == 21 || getType() == 22) {
/* 317 */       return Items.GHAST_TEAR;
/*     */     }
/*     */     
/* 320 */     return Items.LEATHER;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityAsMob(Entity entityIn) {
/* 325 */     if (entityIn instanceof EntityPlayer && !shouldAttackPlayers()) {
/* 326 */       return false;
/*     */     }
/* 328 */     if (this.onGround && !isOnAir()) {
/* 329 */       stand();
/*     */     }
/* 331 */     openMouth();
/* 332 */     MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_HORSE_MAD);
/* 333 */     return super.attackEntityAsMob(entityIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDeath(DamageSource damagesource) {
/* 338 */     super.onDeath(damagesource);
/*     */     
/* 340 */     if (getType() == 23 || getType() == 24 || getType() == 25) {
/* 341 */       MoCTools.spawnSlimes(this.world, (Entity)this);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getMountedYOffset() {
/* 348 */     return this.height * 0.75D - 0.1D;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getCanSpawnHere() {
/* 353 */     if (this.posY < 50.0D && !this.world.provider.doesWaterVaporize()) {
/* 354 */       setType(32);
/*     */     }
/* 356 */     return super.getCanSpawnHere();
/*     */   }
/*     */   
/*     */   public void UndeadFX() {
/* 360 */     MoCreatures.proxy.UndeadFX((Entity)this);
/*     */   }
/*     */   
/*     */   public void LavaFX() {
/* 364 */     MoCreatures.proxy.LavaFX((Entity)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumCreatureAttribute getCreatureAttribute() {
/* 372 */     if (getType() == 23 || getType() == 24 || getType() == 25) {
/* 373 */       return EnumCreatureAttribute.UNDEAD;
/*     */     }
/* 375 */     return super.getCreatureAttribute();
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isHarmedByDaylight() {
/* 380 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int maxFlyingHeight() {
/* 385 */     return 10;
/*     */   }
/*     */ 
/*     */   
/*     */   public int minFlyingHeight() {
/* 390 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updatePassenger(Entity passenger) {
/* 395 */     double dist = 0.4D;
/* 396 */     double newPosX = this.posX + dist * Math.sin((this.renderYawOffset / 57.29578F));
/* 397 */     double newPosZ = this.posZ - dist * Math.cos((this.renderYawOffset / 57.29578F));
/* 398 */     passenger.setPosition(newPosX, this.posY + getMountedYOffset() + passenger.getYOffset(), newPosZ);
/* 399 */     passenger.rotationYaw = this.rotationYaw;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\monster\MoCEntityHorseMob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */