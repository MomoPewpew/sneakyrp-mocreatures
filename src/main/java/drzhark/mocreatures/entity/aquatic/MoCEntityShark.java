/*     */ package drzhark.mocreatures.entity.aquatic;
/*     */ import drzhark.mocreatures.entity.MoCEntityTameableAquatic;
/*     */ import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */
/*     */ public class MoCEntityShark extends MoCEntityTameableAquatic {
/*     */   public MoCEntityShark(World world) {
/*  20 */     super(world);
/*  21 */     this.texture = "shark.png";
/*  22 */     setSize(1.7F, 0.8F);
/*  23 */     setEdad(60 + this.rand.nextInt(100));
/*     */   }
/*     */
/*     */
/*     */   protected void initEntityAI() {
/*  28 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
/*  29 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D, 30));
/*  30 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
/*     */   }
/*     */
/*     */
/*     */   protected void applyEntityAttributes() {
/*  35 */     super.applyEntityAttributes();
/*  36 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
/*  37 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
/*  38 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
/*  39 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
/*     */   }
/*     */
/*     */
/*     */   protected void entityInit() {
/*  44 */     super.entityInit();
/*     */   }
/*     */
/*     */
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/*  49 */     if (super.attackEntityFrom(damagesource, i) && this.world.getDifficulty().getDifficultyId()  > 0) {
/*  50 */       Entity entity = damagesource.getTrueSource();
/*  51 */       if (isRidingOrBeingRiddenBy(entity)) {
/*  52 */         return true;
/*     */       }
/*  54 */       if (entity != this && entity instanceof EntityLivingBase) {
/*  55 */         setAttackTarget((EntityLivingBase)entity);
/*  56 */         return true;
/*     */       }
/*  58 */       return false;
/*     */     }
/*     */
/*  61 */     return false;
/*     */   }
/*     */
/*     */
/*     */
/*     */   protected void dropFewItems(boolean flag, int x) {
/*  67 */     int i = this.rand.nextInt(100);
/*  68 */     if (i < 90) {
/*  69 */       int j = this.rand.nextInt(3) + 1;
/*  70 */       for (int l = 0; l < j; l++) {
/*  71 */         entityDropItem(new ItemStack((Item)MoCItems.sharkteeth, 1, 0), 0.0F);
/*     */       }
/*  73 */     } else if (this.world.getDifficulty().getDifficultyId()  > 0 && getEdad() > 150) {
/*  74 */       int k = this.rand.nextInt(3);
/*  75 */       for (int i1 = 0; i1 < k; i1++) {
/*  76 */         entityDropItem(new ItemStack((Item)MoCItems.mocegg, 1, 11), 0.0F);
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
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public void onLivingUpdate() {
/* 114 */     super.onLivingUpdate();
/* 115 */     if (!this.world.isRemote &&
/* 116 */       !getIsAdult() && this.rand.nextInt(50) == 0) {
/* 117 */       setEdad(getEdad() + 1);
/* 118 */       if (getEdad() >= 200) {
/* 119 */         setAdult(true);
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */
/*     */
/*     */   public void setDead() {
/* 127 */     if (!this.world.isRemote && getIsTamed() && getHealth() > 0.0F) {
/*     */       return;
/*     */     }
/* 130 */     super.setDead();
/*     */   }
/*     */
/*     */
/*     */
/*     */   public boolean isMyHealFood(Item item1) {
/* 136 */     return false;
/*     */   }
/*     */
/*     */
/*     */   protected boolean usesNewAI() {
/* 141 */     return true;
/*     */   }
/*     */
/*     */
/*     */   public float getAIMoveSpeed() {
/* 146 */     return 0.12F;
/*     */   }
/*     */
/*     */
/*     */   public boolean isMovementCeased() {
/* 151 */     return !isInWater();
/*     */   }
/*     */
/*     */
/*     */   protected double minDivingDepth() {
/* 156 */     return 1.0D;
/*     */   }
/*     */
/*     */
/*     */   protected double maxDivingDepth() {
/* 161 */     return 6.0D;
/*     */   }
/*     */
/*     */
/*     */   public int getMaxEdad() {
/* 166 */     return 200;
/*     */   }
/*     */
/*     */
/*     */   public boolean isNotScared() {
/* 171 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\aquatic\MoCEntityShark.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
