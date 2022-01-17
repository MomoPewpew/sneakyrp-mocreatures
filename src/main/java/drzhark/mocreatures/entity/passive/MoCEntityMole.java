/*     */ package drzhark.mocreatures.entity.passive;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */
/*     */ public class MoCEntityMole extends MoCEntityTameableAnimal {
/*  29 */   private static final DataParameter<Integer> MOLE_STATE = EntityDataManager.createKey(MoCEntityMole.class, DataSerializers.VARINT);
/*     */
/*     */   public MoCEntityMole(World world) {
/*  32 */     super(world);
/*  33 */     setSize(1.0F, 0.5F);
/*     */   }
/*     */
/*     */
/*     */   protected void initEntityAI() {
/*  38 */     this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  39 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D));
/*  40 */     this.tasks.addTask(4, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*     */   }
/*     */
/*     */
/*     */   protected void applyEntityAttributes() {
/*  45 */     super.applyEntityAttributes();
/*  46 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
/*  47 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
/*     */   }
/*     */
/*     */
/*     */   public ResourceLocation getTexture() {
/*  52 */     return MoCreatures.proxy.getTexture("mole.png");
/*     */   }
/*     */
/*     */
/*     */   protected void entityInit() {
/*  57 */     super.entityInit();
/*  58 */     this.dataManager.register(MOLE_STATE, Integer.valueOf(0));
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public boolean isOnDirt() {
/*  66 */     Block block = this.world.getBlockState(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor((getEntityBoundingBox()).minY - 0.5D), MathHelper.floor(this.posZ))).getBlock();
/*  67 */     return isDiggableBlock(Block.getIdFromBlock(block));
/*     */   }
/*     */
/*     */   private boolean isDiggableBlock(int i) {
/*  71 */     return ((i == 2)) | ((i == 3)) | ((i == 12) ? true : false);
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   private void digForward() {
/*  79 */     double coordY = this.posY;
/*  80 */     double coordZ = this.posZ;
/*  81 */     double coordX = this.posX;
/*  82 */     int x = 1;
/*  83 */     double newPosY = coordY - Math.cos(((this.rotationPitch - 90.0F) / 57.29578F)) * x;
/*     */
/*  85 */     double newPosX = coordX + Math.cos((MoCTools.realAngle(this.rotationYaw - 90.0F) / 57.29578F)) * Math.sin(((this.rotationPitch - 90.0F) / 57.29578F)) * x;
/*     */
/*  87 */     double newPosZ = coordZ + Math.sin((MoCTools.realAngle(this.rotationYaw - 90.0F) / 57.29578F)) * Math.sin(((this.rotationPitch - 90.0F) / 57.29578F)) * x;
/*     */
/*     */
/*     */
/*  91 */     Block block = this.world.getBlockState(new BlockPos(MathHelper.floor(newPosX), MathHelper.floor(newPosY), MathHelper.floor(newPosZ))).getBlock();
/*  92 */     if (isDiggableBlock(Block.getIdFromBlock(block))) {
/*  93 */       setPosition(newPosX, newPosY, newPosZ);
/*     */     }
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public int getState() {
/* 103 */     return ((Integer)this.dataManager.get(MOLE_STATE)).intValue();
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public void setState(int i) {
/* 112 */     this.dataManager.set(MOLE_STATE, Integer.valueOf(i));
/*     */   }
/*     */
/*     */
/*     */
/*     */   public float pitchRotationOffset() {
/* 118 */     int i = getState();
/* 119 */     switch (i) {
/*     */       case 0:
/* 121 */         return 0.0F;
/*     */       case 1:
/* 123 */         return -45.0F;
/*     */       case 2:
/* 125 */         return 0.0F;
/*     */       case 3:
/* 127 */         return 60.0F;
/*     */     }
/* 129 */     return 0.0F;
/*     */   }
/*     */
/*     */
/*     */
/*     */   public float getAdjustedYOffset() {
/* 135 */     int i = getState();
/* 136 */     switch (i) {
/*     */       case 0:
/* 138 */         return 0.0F;
/*     */       case 1:
/* 140 */         return 0.3F;
/*     */       case 2:
/* 142 */         return 1.0F;
/*     */       case 3:
/* 144 */         return 0.1F;
/*     */     }
/* 146 */     return 0.0F;
/*     */   }
/*     */
/*     */
/*     */
/*     */   public void onLivingUpdate() {
/* 152 */     super.onLivingUpdate();
/*     */
/* 154 */     if (!this.world.isRemote) {
/* 155 */       if (this.rand.nextInt(10) == 0 && getState() == 1) {
/* 156 */         setState(2);
/*     */       }
/*     */
/* 159 */       if (getState() != 2 && getState() != 1 && isOnDirt()) {
/* 160 */         EntityLivingBase entityliving = getBoogey(4.0D);
/* 161 */         if (entityliving != null && canEntityBeSeen((Entity)entityliving)) {
/* 162 */           setState(1);
/* 163 */           getNavigator().clearPath();
/*     */         }
/*     */       }
/*     */
/*     */
/* 168 */       if (this.rand.nextInt(20) == 0 && getState() == 2 && getBoogey(4.0D) == null) {
/* 169 */         setState(3);
/* 170 */         getNavigator().clearPath();
/*     */       }
/*     */
/*     */
/* 174 */       if (getState() != 0 && !isOnDirt()) {
/* 175 */         setState(0);
/*     */       }
/*     */
/* 178 */       if (this.rand.nextInt(30) == 0 && getState() == 3) {
/* 179 */         setState(2);
/*     */       }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/* 187 */       if (getState() == 1 || getState() == 2) {
/* 188 */         setSprinting(true);
/*     */       } else {
/* 190 */         setSprinting(false);
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   public boolean isMovementCeased() {
/* 197 */     return (getState() == 1 || getState() == 3);
/*     */   }
/*     */
/*     */
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/* 202 */     if (getState() != 2) {
/* 203 */       return super.attackEntityFrom(damagesource, i);
/*     */     }
/* 205 */     return false;
/*     */   }
/*     */
/*     */
/*     */   public boolean canBeCollidedWith() {
/* 210 */     return (getState() != 2);
/*     */   }
/*     */
/*     */
/*     */   public boolean canBePushed() {
/* 215 */     return (getState() != 2);
/*     */   }
/*     */
/*     */
/*     */   protected void collideWithEntity(Entity par1Entity) {
/* 220 */     if (getState() != 2) {
/* 221 */       super.collideWithEntity(par1Entity);
/*     */     }
/*     */   }
/*     */
/*     */
/*     */
/*     */   public boolean isEntityInsideOpaqueBlock() {
/* 228 */     if (getState() == 2) {
/* 229 */       return false;
/*     */     }
/* 231 */     return super.isEntityInsideOpaqueBlock();
/*     */   }
/*     */
/*     */
/*     */   public void onDeath(DamageSource damagesource) {
/* 236 */     super.onDeath(damagesource);
/*     */   }
/*     */
/*     */
/*     */   public boolean isEntityInvulnerable(DamageSource source) {
/* 241 */     if (getState() == 2) {
/* 242 */       return true;
/*     */     }
/* 244 */     return super.isEntityInvulnerable(source);
/*     */   }
/*     */
/*     */
/*     */   protected Item getDropItem() {
/* 249 */     return (Item)MoCItems.fur;
/*     */   }
/*     */
/*     */
/*     */   protected SoundEvent getDeathSound() {
/* 254 */     return MoCSoundEvents.ENTITY_RABBIT_DEATH;
/*     */   }
/*     */
/*     */
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 259 */     return MoCSoundEvents.ENTITY_RABBIT_HURT;
/*     */   }
/*     */
/*     */
/*     */   protected SoundEvent getAmbientSound() {
/* 264 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityMole.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
