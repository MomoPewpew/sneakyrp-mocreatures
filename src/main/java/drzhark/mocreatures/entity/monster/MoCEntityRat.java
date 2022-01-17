/*     */ package drzhark.mocreatures.entity.monster;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.MoCEntityMob;
/*     */ import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
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
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MoCEntityRat extends MoCEntityMob {
/*     */   public MoCEntityRat(World world) {
/*  28 */     super(world);
/*  29 */     setSize(0.5F, 0.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initEntityAI() {
/*  34 */     this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  35 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
/*  36 */     this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  37 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  42 */     super.applyEntityAttributes();
/*  43 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
/*  44 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
/*  45 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectType() {
/*  50 */     if (getType() == 0) {
/*  51 */       int i = this.rand.nextInt(100);
/*  52 */       if (i <= 65) {
/*  53 */         setType(1);
/*  54 */       } else if (i <= 98) {
/*  55 */         setType(2);
/*     */       } else {
/*  57 */         setType(3);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected double getAttackStrenght() {
/*  64 */     return 1.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  69 */     switch (getType()) {
/*     */       case 1:
/*  71 */         return MoCreatures.proxy.getTexture("ratb.png");
/*     */       case 2:
/*  73 */         return MoCreatures.proxy.getTexture("ratbl.png");
/*     */       case 3:
/*  75 */         return MoCreatures.proxy.getTexture("ratw.png");
/*     */     } 
/*     */     
/*  78 */     return MoCreatures.proxy.getTexture("ratb.png");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/*  84 */     Entity entity = damagesource.getTrueSource();
/*     */     
/*  86 */     if (entity != null && entity instanceof EntityLivingBase) {
/*  87 */       setAttackTarget((EntityLivingBase)entity);
/*  88 */       if (!this.world.isRemote) {
/*     */         
/*  90 */         List<MoCEntityRat> list = this.world.getEntitiesWithinAABB(MoCEntityRat.class, (new AxisAlignedBB(this.posX, this.posY, this.posZ, this.posX + 1.0D, this.posY + 1.0D, this.posZ + 1.0D))
/*     */             
/*  92 */             .expand(16.0D, 4.0D, 16.0D));
/*  93 */         Iterator<MoCEntityRat> iterator = list.iterator();
/*     */         
/*  95 */         while (iterator.hasNext()) {
/*     */ 
/*     */           
/*  98 */           MoCEntityRat entityrat = iterator.next();
/*  99 */           if (entityrat != null && entityrat.getAttackTarget() == null) {
/* 100 */             entityrat.setAttackTarget((EntityLivingBase)entity);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 105 */     return super.attackEntityFrom(damagesource, i);
/*     */   }
/*     */   
/*     */   public boolean climbing() {
/* 109 */     return (!this.onGround && isOnLadder());
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 114 */     super.onLivingUpdate();
/*     */     
/* 116 */     if (this.rand.nextInt(100) == 0 && getBrightness() > 0.5F) {
/* 117 */       setAttackTarget(null);
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 124 */     return (Item)MoCItems.ratRaw;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getDeathSound() {
/* 129 */     return MoCSoundEvents.ENTITY_RAT_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 134 */     return MoCSoundEvents.ENTITY_RAT_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getAmbientSound() {
/* 139 */     return MoCSoundEvents.ENTITY_RAT_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOnLadder() {
/* 144 */     return this.collidedHorizontally;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldAttackPlayers() {
/* 149 */     return (getBrightness() < 0.5F && super.shouldAttackPlayers());
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\monster\MoCEntityRat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */