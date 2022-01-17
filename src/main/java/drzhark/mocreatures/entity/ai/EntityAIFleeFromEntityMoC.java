/*     */ package drzhark.mocreatures.entity.ai;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.base.Predicates;
/*     */ import drzhark.mocreatures.entity.IMoCEntity;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.RandomPositionGenerator;
/*     */ import net.minecraft.util.EntitySelectors;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ 
/*     */ 
/*     */ public class EntityAIFleeFromEntityMoC
/*     */   extends EntityAIBase
/*     */ {
/*  18 */   public final Predicate<Entity> canBeSeenSelector = new Predicate<Entity>()
/*     */     {
/*     */       public boolean isApplicable(Entity entityIn) {
/*  21 */         return (entityIn.isEntityAlive() && EntityAIFleeFromEntityMoC.this.entity.getEntitySenses().canSee(entityIn));
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean apply(Entity p_apply_1_) {
/*  26 */         return isApplicable(p_apply_1_);
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*     */   protected EntityCreature entity;
/*     */   private double farSpeed;
/*     */   private double nearSpeed;
/*     */   protected Entity closestLivingEntity;
/*     */   private float avoidDistance;
/*     */   private Predicate<Entity> avoidTargetSelector;
/*     */   private double randPosX;
/*     */   private double randPosY;
/*     */   private double randPosZ;
/*     */   
/*     */   public EntityAIFleeFromEntityMoC(EntityCreature creature, Predicate<Entity> targetSelector, float searchDistance, double farSpeedIn, double nearSpeedIn) {
/*  42 */     this.entity = creature;
/*  43 */     this.avoidTargetSelector = targetSelector;
/*  44 */     this.avoidDistance = searchDistance;
/*  45 */     this.farSpeed = farSpeedIn;
/*  46 */     this.nearSpeed = nearSpeedIn;
/*  47 */     setMutexBits(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  56 */     if (this.entity instanceof IMoCEntity && ((IMoCEntity)this.entity).isNotScared()) {
/*  57 */       return false;
/*     */     }
/*     */     
/*  60 */     if (this.entity instanceof drzhark.mocreatures.entity.MoCEntityAquatic && !this.entity.isInWater()) {
/*  61 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  65 */     List<Entity> list = this.entity.world.getEntitiesInAABBexcluding((Entity)this.entity, this.entity
/*  66 */         .getEntityBoundingBox().expand(this.avoidDistance, 3.0D, this.avoidDistance), 
/*  67 */         Predicates.and(new Predicate[] { EntitySelectors.NOT_SPECTATING, this.canBeSeenSelector, this.avoidTargetSelector }));
/*     */     
/*  69 */     if (list.isEmpty()) {
/*  70 */       return false;
/*     */     }
/*  72 */     this.closestLivingEntity = list.get(0);
/*     */     
/*  74 */     Vec3d vec3 = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.entity, 16, 7, new Vec3d(this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));
/*     */ 
/*     */     
/*  77 */     if (vec3 == null)
/*  78 */       return false; 
/*  79 */     if (this.closestLivingEntity.getDistanceSq(vec3.x, vec3.y, vec3.z) < this.closestLivingEntity
/*  80 */       .getDistanceSq((Entity)this.entity)) {
/*  81 */       return false;
/*     */     }
/*  83 */     this.randPosX = vec3.x;
/*  84 */     this.randPosY = vec3.y;
/*  85 */     this.randPosZ = vec3.z;
/*  86 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  96 */     this.entity.getNavigator().tryMoveToXYZ(this.randPosX, this.randPosY, this.randPosZ, this.nearSpeed);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldContinueExecuting() {
/* 104 */     return !this.entity.getNavigator().noPath();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/* 112 */     this.closestLivingEntity = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/* 120 */     if (this.entity.getDistanceSq(this.closestLivingEntity) < 8.0D) {
/* 121 */       this.entity.getNavigator().setSpeed(this.nearSpeed);
/*     */     } else {
/* 123 */       this.entity.getNavigator().setSpeed(this.farSpeed);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ai\EntityAIFleeFromEntityMoC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */