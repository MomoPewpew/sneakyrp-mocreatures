/*     */ package drzhark.mocreatures.entity.ai;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.base.Predicates;
/*     */ import drzhark.mocreatures.entity.IMoCEntity;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.EntitySelectors;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityAINearestAttackableTargetMoC
/*     */   extends EntitiAITargetMoC
/*     */ {
/*     */   protected final Class<? extends EntityLivingBase> targetClass;
/*     */   private final int targetChance;
/*     */   protected final Sorter theNearestAttackableTargetSorter;
/*     */   protected Predicate<EntityLivingBase> targetEntitySelector;
/*     */   protected EntityLivingBase targetEntity;
/*     */   private IMoCEntity theAttacker;
/*     */   
/*     */   public EntityAINearestAttackableTargetMoC(EntityCreature creature, Class<? extends EntityLivingBase> classTarget, boolean checkSight) {
/*  31 */     this(creature, classTarget, checkSight, false);
/*     */   }
/*     */   
/*     */   public EntityAINearestAttackableTargetMoC(EntityCreature creature, Class<? extends EntityLivingBase> classTarget, boolean checkSight, boolean onlyNearby) {
/*  35 */     this(creature, classTarget, 10, checkSight, onlyNearby, (Predicate<EntityLivingBase>)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityAINearestAttackableTargetMoC(EntityCreature creature, Class<? extends EntityLivingBase> classTarget, int chance, boolean checkSight, boolean onlyNearby, final Predicate<EntityLivingBase> targetSelector) {
/*  40 */     super(creature, checkSight, onlyNearby);
/*  41 */     if (creature instanceof IMoCEntity) {
/*  42 */       this.theAttacker = (IMoCEntity)creature;
/*     */     }
/*  44 */     this.targetClass = classTarget;
/*  45 */     this.targetChance = chance;
/*  46 */     this.theNearestAttackableTargetSorter = new Sorter((Entity)creature);
/*  47 */     setMutexBits(1);
/*  48 */     this.targetEntitySelector = new Predicate<EntityLivingBase>()
/*     */       {
/*     */         public boolean apply(EntityLivingBase entitylivingbaseIn)
/*     */         {
/*  52 */           if (targetSelector != null && !targetSelector.apply(entitylivingbaseIn)) {
/*  53 */             return false;
/*     */           }
/*  55 */           if (entitylivingbaseIn instanceof EntityPlayer) {
/*  56 */             double d0 = EntityAINearestAttackableTargetMoC.this.getTargetDistance();
/*     */             
/*  58 */             if (entitylivingbaseIn.isSneaking()) {
/*  59 */               d0 *= 0.800000011920929D;
/*     */             }
/*     */             
/*  62 */             if (entitylivingbaseIn.isInvisible()) {
/*  63 */               float f = ((EntityPlayer)entitylivingbaseIn).getArmorVisibility();
/*     */               
/*  65 */               if (f < 0.1F) {
/*  66 */                 f = 0.1F;
/*     */               }
/*     */               
/*  69 */               d0 *= (0.7F * f);
/*     */             } 
/*     */             
/*  72 */             if (entitylivingbaseIn.getDistance((Entity)EntityAINearestAttackableTargetMoC.this.taskOwner) > d0) {
/*  73 */               return false;
/*     */             }
/*     */           } 
/*     */           
/*  77 */           return EntityAINearestAttackableTargetMoC.this.isSuitableTarget(entitylivingbaseIn, false);
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  88 */     if (this.theAttacker != null && (this.theAttacker.isMovementCeased() || !this.theAttacker.isNotScared())) {
/*  89 */       return false;
/*     */     }
/*  91 */     if (this.targetChance > 0 && this.taskOwner.getRNG().nextInt(this.targetChance) != 0) {
/*  92 */       return false;
/*     */     }
/*  94 */     double d0 = getTargetDistance();
/*     */     
/*  96 */     List<EntityLivingBase> list = this.taskOwner.world.getEntitiesWithinAABB(this.targetClass, this.taskOwner.getEntityBoundingBox().expand(d0, 4.0D, d0), 
/*  97 */         Predicates.and(this.targetEntitySelector, EntitySelectors.NOT_SPECTATING));
/*  98 */     Collections.sort(list, this.theNearestAttackableTargetSorter);
/*     */     
/* 100 */     if (list.isEmpty()) {
/* 101 */       return false;
/*     */     }
/* 103 */     this.targetEntity = list.get(0);
/* 104 */     if (this.targetEntity instanceof EntityPlayer && !this.theAttacker.shouldAttackPlayers()) {
/* 105 */       return false;
/*     */     }
/* 107 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/* 117 */     this.taskOwner.setAttackTarget(this.targetEntity);
/* 118 */     super.startExecuting();
/*     */   }
/*     */   
/*     */   public static class Sorter
/*     */     implements Comparator<Entity> {
/*     */     private final Entity entity;
/*     */     
/*     */     public Sorter(Entity entityIn) {
/* 126 */       this.entity = entityIn;
/*     */     }
/*     */     
/*     */     public int compare(Entity p_compare_1_, Entity p_compare_2_) {
/* 130 */       double d0 = this.entity.getDistanceSq(p_compare_1_);
/* 131 */       double d1 = this.entity.getDistanceSq(p_compare_2_);
/* 132 */       return (d0 < d1) ? -1 : ((d0 > d1) ? 1 : 0);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ai\EntityAINearestAttackableTargetMoC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */