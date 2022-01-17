/*     */ package drzhark.mocreatures.entity.passive;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.MoCEntityAnimal;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFleeFromPlayer;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFollowAdult;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIHunt;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import drzhark.mocreatures.init.MoCItems;
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
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MoCEntityBoar extends MoCEntityAnimal {
/*     */   public MoCEntityBoar(World world) {
/*  29 */     super(world);
/*  30 */     setSize(0.9F, 0.8F);
/*  31 */     setEdad(this.rand.nextInt(15) + 45);
/*  32 */     if (this.rand.nextInt(4) == 0) {
/*  33 */       setAdult(false);
/*     */     } else {
/*     */       
/*  36 */       setAdult(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initEntityAI() {
/*  42 */     this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  43 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIFleeFromPlayer((EntityCreature)this, 1.0D, 4.0D));
/*  44 */     this.tasks.addTask(3, (EntityAIBase)new EntityAIFollowAdult((EntityLiving)this, 1.0D));
/*  45 */     this.tasks.addTask(4, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
/*  46 */     this.tasks.addTask(7, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D));
/*  47 */     this.tasks.addTask(9, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  48 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHunt((EntityCreature)this, EntityAnimal.class, true));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  53 */     super.applyEntityAttributes();
/*  54 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
/*  55 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
/*  56 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
/*  57 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  62 */     if (getIsAdult()) {
/*  63 */       return MoCreatures.proxy.getTexture("boara.png");
/*     */     }
/*  65 */     return MoCreatures.proxy.getTexture("boarb.png");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canDespawn() {
/*  71 */     if (MoCreatures.proxy.forceDespawns) {
/*  72 */       return !getIsTamed();
/*     */     }
/*  74 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/*  80 */     if (super.attackEntityFrom(damagesource, i)) {
/*  81 */       Entity entity = damagesource.getTrueSource();
/*  82 */       if (isRidingOrBeingRiddenBy(entity)) {
/*  83 */         return true;
/*     */       }
/*  85 */       if (entity != this && entity instanceof EntityLivingBase && shouldAttackPlayers() && getIsAdult()) {
/*  86 */         setAttackTarget((EntityLivingBase)entity);
/*     */       }
/*  88 */       return true;
/*     */     } 
/*  90 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNotScared() {
/*  96 */     return getIsAdult();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 102 */     if (this.rand.nextInt(2) == 0) {
/* 103 */       return Items.PORKCHOP;
/*     */     }
/*     */     
/* 106 */     return (Item)MoCItems.animalHide;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getAmbientSound() {
/* 111 */     return SoundEvents.ENTITY_PIG_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 116 */     return SoundEvents.ENTITY_PIG_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getDeathSound() {
/* 121 */     return SoundEvents.ENTITY_PIG_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canAttackTarget(EntityLivingBase entity) {
/* 126 */     return (!(entity instanceof MoCEntityBoar) && super.canAttackTarget(entity));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isReadyToHunt() {
/* 131 */     return (getIsAdult() && !isMovementCeased());
/*     */   }
/*     */ 
/*     */   
/*     */   public float getSizeFactor() {
/* 136 */     if (getIsAdult()) {
/* 137 */       return 1.0F;
/*     */     }
/* 139 */     return getEdad() * 0.01F;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityBoar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */