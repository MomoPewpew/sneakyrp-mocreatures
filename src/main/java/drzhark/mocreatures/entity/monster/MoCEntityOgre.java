/*     */ package drzhark.mocreatures.entity.monster;
/*     */
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.MoCEntityMob;
/*     */ import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import drzhark.mocreatures.network.MoCMessageHandler;
/*     */ import drzhark.mocreatures.network.message.MoCMessageAnimation;
/*     */ import drzhark.mocreatures.network.message.MoCMessageExplode;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*     */
/*     */ public class MoCEntityOgre extends MoCEntityMob {
/*     */   public int attackCounterLeft;
/*     */   public int attackCounterRight;
/*     */   private int movingHead;
/*     */
/*     */   public MoCEntityOgre(World world) {
/*  33 */     super(world);
/*  34 */     setSize(1.9F, 3.0F);
/*     */   }
/*     */   public int smashCounter; public int armToAnimate; public int attackCounter;
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
/*  48 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(calculateMaxHealth());
/*  49 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
/*  50 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
/*     */   }
/*     */
/*     */
/*     */   public void selectType() {
/*  55 */     if (getType() == 0) {
/*  56 */       setType(this.rand.nextInt(2) + 1);
/*     */     }
/*     */   }
/*     */
/*     */   public float calculateMaxHealth() {
/*  61 */     return 35.0F;
/*     */   }
/*     */
/*     */
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/*  66 */     if (super.attackEntityFrom(damagesource, i)) {
/*  67 */       Entity entity = damagesource.getTrueSource();
/*  68 */       if (isRidingOrBeingRiddenBy(entity)) {
/*  69 */         return true;
/*     */       }
/*  71 */       if (entity != this && this.world.getDifficulty().getDifficultyId()  > 0 && entity instanceof EntityLivingBase) {
/*  72 */         setAttackTarget((EntityLivingBase)entity);
/*  73 */         return true;
/*     */       }
/*  75 */       return false;
/*     */     }
/*     */
/*  78 */     return false;
/*     */   }
/*     */
/*     */
/*     */
/*     */   public boolean shouldAttackPlayers() {
/*  84 */     return (getBrightness() < 0.5F && super.shouldAttackPlayers());
/*     */   }
/*     */
/*     */
/*     */   protected SoundEvent getDeathSound() {
/*  89 */     return MoCSoundEvents.ENTITY_OGRE_DEATH;
/*     */   }
/*     */
/*     */
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/*  94 */     return MoCSoundEvents.ENTITY_OGRE_HURT;
/*     */   }
/*     */
/*     */
/*     */   protected SoundEvent getAmbientSound() {
/*  99 */     return MoCSoundEvents.ENTITY_OGRE_AMBIENT;
/*     */   }
/*     */
/*     */   public boolean isFireStarter() {
/* 103 */     return false;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public float getDestroyForce() {
/* 111 */     return MoCreatures.proxy.ogreStrength;
/*     */   }
/*     */
/*     */   public int getAttackRange() {
/* 115 */     return MoCreatures.proxy.ogreAttackRange;
/*     */   }
/*     */
/*     */
/*     */   public void onLivingUpdate() {
/* 120 */     if (!this.world.isRemote) {
/* 121 */       if (this.smashCounter > 0 && ++this.smashCounter > 10) {
/* 122 */         this.smashCounter = 0;
/* 123 */         performDestroyBlastAttack();
/* 124 */         MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageExplode(getEntityId()), new NetworkRegistry.TargetPoint(this.world.provider
/* 125 */               .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */       }
/*     */
/* 128 */       if (getAttackTarget() != null && this.rand.nextInt(40) == 0 && this.smashCounter == 0 && this.attackCounter == 0) {
/* 129 */         startDestroyBlast();
/*     */       }
/*     */     }
/*     */
/* 133 */     if (this.attackCounter > 0) {
/* 134 */       if (this.armToAnimate == 3) {
/* 135 */         this.attackCounter++;
/*     */       } else {
/* 137 */         this.attackCounter += 2;
/*     */       }
/*     */
/* 140 */       if (this.attackCounter > 10) {
/* 141 */         this.attackCounter = 0;
/* 142 */         this.armToAnimate = 0;
/*     */       }
/*     */     }
/* 145 */     super.onLivingUpdate();
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */   protected void startDestroyBlast() {
/* 152 */     this.smashCounter = 1;
/* 153 */     MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 3), new NetworkRegistry.TargetPoint(this.world.provider
/* 154 */           .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */   public void performDestroyBlastAttack() {
/* 161 */     if (this.deathTime > 0) {
/*     */       return;
/*     */     }
/* 164 */     MoCTools.DestroyBlast((Entity)this, this.posX, this.posY + 1.0D, this.posZ, getDestroyForce(), isFireStarter());
/*     */   }
/*     */
/*     */
/*     */   protected boolean isHarmedByDaylight() {
/* 169 */     return false;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */   protected void startArmSwingAttack() {
/* 176 */     if (!this.world.isRemote) {
/* 177 */       if (this.smashCounter != 0) {
/*     */         return;
/*     */       }
/* 180 */       boolean leftArmW = ((getType() == 2 || getType() == 4 || getType() == 6) && this.rand.nextInt(2) == 0);
/*     */
/* 182 */       if (leftArmW) {
/* 183 */         this.attackCounter = 1;
/* 184 */         this.armToAnimate = 1;
/* 185 */         MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 1), new NetworkRegistry.TargetPoint(this.world.provider
/* 186 */               .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */       } else {
/* 188 */         this.attackCounter = 1;
/* 189 */         this.armToAnimate = 2;
/* 190 */         MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 2), new NetworkRegistry.TargetPoint(this.world.provider
/* 191 */               .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   public void performAnimation(int animationType) {
/* 198 */     if (animationType != 0) {
/* 199 */       this.attackCounter = 1;
/* 200 */       this.armToAnimate = animationType;
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   public int getMovingHead() {
/* 206 */     if (getType() == 1)
/*     */     {
/* 208 */       return 1;
/*     */     }
/*     */
/* 211 */     if (this.rand.nextInt(60) == 0) {
/* 212 */       this.movingHead = this.rand.nextInt(2) + 2;
/*     */     }
/* 214 */     return this.movingHead;
/*     */   }
/*     */
/*     */
/*     */   public boolean attackEntityAsMob(Entity entityIn) {
/* 219 */     startArmSwingAttack();
/* 220 */     return super.attackEntityAsMob(entityIn);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\monster\MoCEntityOgre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
