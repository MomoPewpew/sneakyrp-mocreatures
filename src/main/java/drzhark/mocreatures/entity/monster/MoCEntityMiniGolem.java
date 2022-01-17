/*     */ package drzhark.mocreatures.entity.monster;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
/*     */ import drzhark.mocreatures.entity.item.MoCEntityThrowableRock;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MoCEntityMiniGolem extends MoCEntityMob {
/*     */   public int tcounter;
/*  27 */   private static final DataParameter<Boolean> ANGRY = EntityDataManager.createKey(MoCEntityMiniGolem.class, DataSerializers.BOOLEAN); public MoCEntityThrowableRock tempRock;
/*  28 */   private static final DataParameter<Boolean> HAS_ROCK = EntityDataManager.createKey(MoCEntityMiniGolem.class, DataSerializers.BOOLEAN);
/*     */ 
/*     */   
/*     */   public MoCEntityMiniGolem(World world) {
/*  32 */     super(world);
/*  33 */     this.texture = "minigolem.png";
/*  34 */     setSize(1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initEntityAI() {
/*  39 */     this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  40 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
/*  41 */     this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  42 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  47 */     super.applyEntityAttributes();
/*  48 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
/*  49 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
/*  50 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  55 */     super.entityInit();
/*  56 */     this.dataManager.register(ANGRY, Boolean.valueOf(false));
/*  57 */     this.dataManager.register(HAS_ROCK, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public boolean getIsAngry() {
/*  61 */     return ((Boolean)this.dataManager.get(ANGRY)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setIsAngry(boolean flag) {
/*  65 */     this.dataManager.set(ANGRY, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public boolean getHasRock() {
/*  69 */     return ((Boolean)this.dataManager.get(HAS_ROCK)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setHasRock(boolean flag) {
/*  73 */     this.dataManager.set(HAS_ROCK, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/*  78 */     super.onLivingUpdate();
/*     */     
/*  80 */     if (!this.world.isRemote) {
/*  81 */       if (getAttackTarget() == null) {
/*  82 */         setIsAngry(false);
/*     */       } else {
/*     */         
/*  85 */         setIsAngry(true);
/*     */       } 
/*     */       
/*  88 */       if (getIsAngry() && getAttackTarget() != null) {
/*  89 */         if (!getHasRock() && this.rand.nextInt(30) == 0) {
/*  90 */           acquireTRock();
/*     */         }
/*     */         
/*  93 */         if (getHasRock()) {
/*  94 */           getNavigator().clearPath();
/*  95 */           attackWithTRock();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void acquireTRock() {
/* 102 */     IBlockState tRockState = MoCTools.destroyRandomBlockWithIBlockState((Entity)this, 3.0D);
/* 103 */     if (tRockState == null) {
/* 104 */       this.tcounter = 1;
/* 105 */       setHasRock(false);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 110 */     MoCEntityThrowableRock trock = new MoCEntityThrowableRock(this.world, (Entity)this, this.posX, this.posY + 1.5D, this.posZ);
/* 111 */     this.world.spawnEntity((Entity)trock);
/* 112 */     trock.setState(tRockState);
/* 113 */     trock.setBehavior(1);
/* 114 */     this.tempRock = trock;
/* 115 */     setHasRock(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMovementCeased() {
/* 120 */     return (getHasRock() && getAttackTarget() != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void attackWithTRock() {
/* 127 */     this.tcounter++;
/*     */     
/* 129 */     if (this.tcounter < 50) {
/*     */       
/* 131 */       this.tempRock.posX = this.posX;
/* 132 */       this.tempRock.posY = this.posY + 1.0D;
/* 133 */       this.tempRock.posZ = this.posZ;
/*     */     } 
/*     */     
/* 136 */     if (this.tcounter >= 50) {
/*     */       
/* 138 */       if (getAttackTarget() != null && getDistance((Entity)getAttackTarget()) < 48.0F) {
/* 139 */         MoCTools.ThrowStone((Entity)this, (Entity)getAttackTarget(), this.tempRock.getState(), 10.0D, 0.25D);
/*     */       }
/*     */       
/* 142 */       this.tempRock.setDead();
/* 143 */       setHasRock(false);
/* 144 */       this.tcounter = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSizeFactor() {
/* 153 */     return 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void playStepSound(BlockPos pos, Block block) {
/* 161 */     MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GOLEM_WALK);
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getDeathSound() {
/* 166 */     return MoCSoundEvents.ENTITY_GOLEM_DYING;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 171 */     return MoCSoundEvents.ENTITY_GOLEM_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getAmbientSound() {
/* 176 */     return MoCSoundEvents.ENTITY_GOLEM_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isHarmedByDaylight() {
/* 181 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\monster\MoCEntityMiniGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */