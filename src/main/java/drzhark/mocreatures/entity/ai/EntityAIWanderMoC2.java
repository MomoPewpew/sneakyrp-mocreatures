/*     */ package drzhark.mocreatures.entity.ai;
/*     */ 
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.entity.IMoCEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ 
/*     */ public class EntityAIWanderMoC2
/*     */   extends EntityAIBase
/*     */ {
/*     */   private EntityCreature entity;
/*     */   private double xPosition;
/*     */   private double yPosition;
/*     */   private double zPosition;
/*     */   private double speed;
/*     */   private int executionChance;
/*     */   private boolean mustUpdate;
/*     */   
/*     */   public EntityAIWanderMoC2(EntityCreature creatureIn, double speedIn) {
/*  23 */     this(creatureIn, speedIn, 120);
/*     */   }
/*     */   
/*     */   public EntityAIWanderMoC2(EntityCreature creatureIn, double speedIn, int chance) {
/*  27 */     this.entity = creatureIn;
/*  28 */     this.speed = speedIn;
/*  29 */     this.executionChance = chance;
/*  30 */     setMutexBits(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  38 */     if (this.entity instanceof IMoCEntity && ((IMoCEntity)this.entity).isMovementCeased()) {
/*  39 */       return false;
/*     */     }
/*  41 */     if (this.entity.isBeingRidden() && !(this.entity instanceof drzhark.mocreatures.entity.ambient.MoCEntityAnt) && !(this.entity instanceof drzhark.mocreatures.entity.MoCEntityMob)) {
/*  42 */       return false;
/*     */     }
/*     */     
/*  45 */     if (!this.mustUpdate) {
/*  46 */       if (this.entity.getIdleTime() >= 100)
/*     */       {
/*  48 */         return false;
/*     */       }
/*     */       
/*  51 */       if (this.entity.getRNG().nextInt(this.executionChance) != 0)
/*     */       {
/*  53 */         return false;
/*     */       }
/*     */     } 
/*     */     
/*  57 */     Vec3d vec3 = RandomPositionGeneratorMoCFlyer.findRandomTarget(this.entity, 10, 12);
/*     */     
/*  59 */     if (vec3 != null && this.entity instanceof IMoCEntity && this.entity.getNavigator() instanceof PathNavigateFlyer) {
/*  60 */       int distToFloor = MoCTools.distanceToFloor((Entity)this.entity);
/*  61 */       int finalYHeight = distToFloor + MathHelper.floor(vec3.y - this.entity.posY);
/*  62 */       if (finalYHeight < ((IMoCEntity)this.entity).minFlyingHeight())
/*     */       {
/*  64 */         return false;
/*     */       }
/*  66 */       if (finalYHeight > ((IMoCEntity)this.entity).maxFlyingHeight())
/*     */       {
/*  68 */         return false;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  73 */     if (vec3 == null)
/*     */     {
/*  75 */       return false;
/*     */     }
/*     */     
/*  78 */     this.xPosition = vec3.x;
/*  79 */     this.yPosition = vec3.y;
/*  80 */     this.zPosition = vec3.z;
/*  81 */     this.mustUpdate = false;
/*  82 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldContinueExecuting() {
/*  91 */     return (!this.entity.getNavigator().noPath() && !this.entity.isBeingRidden());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/* 100 */     this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void makeUpdate() {
/* 108 */     this.mustUpdate = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExecutionChance(int newchance) {
/* 115 */     this.executionChance = newchance;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ai\EntityAIWanderMoC2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */