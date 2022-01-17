/*     */ package drzhark.mocreatures.entity.ai;
/*     */ 
/*     */ import drzhark.mocreatures.entity.IMoCEntity;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ 
/*     */ public class EntityAIFollowHerd
/*     */   extends EntityAIBase
/*     */ {
/*     */   EntityLiving theAnimal;
/*     */   EntityLiving herdAnimal;
/*     */   double moveSpeed;
/*     */   double maxRange;
/*     */   double minRange;
/*     */   private int delayCounter;
/*     */   private int executionChance;
/*     */   
/*     */   public EntityAIFollowHerd(EntityLiving animal, double speed, double minRangeIn, double maxRangeIn, int chance) {
/*  22 */     this.theAnimal = animal;
/*  23 */     this.moveSpeed = speed;
/*  24 */     this.minRange = minRangeIn;
/*  25 */     this.maxRange = maxRangeIn;
/*  26 */     this.executionChance = chance;
/*     */   }
/*     */   
/*     */   public EntityAIFollowHerd(EntityLiving animal, double speed) {
/*  30 */     this(animal, speed, 4.0D, 20.0D, 120);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  39 */     if (this.theAnimal.getRNG().nextInt(this.executionChance) != 0) {
/*  40 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  44 */     List<EntityLiving> list = this.theAnimal.world.getEntitiesWithinAABB(this.theAnimal.getClass(), this.theAnimal
/*  45 */         .getEntityBoundingBox().expand(this.maxRange, 4.0D, this.maxRange));
/*  46 */     EntityLiving entityliving = null;
/*  47 */     double d0 = Double.MAX_VALUE;
/*  48 */     Iterator<EntityLiving> iterator = list.iterator();
/*     */     
/*  50 */     while (iterator.hasNext()) {
/*  51 */       EntityLiving entityliving1 = iterator.next();
/*  52 */       double d1 = this.theAnimal.getDistanceSq((Entity)entityliving1);
/*  53 */       if (d1 >= this.minRange && this.theAnimal != entityliving1) {
/*  54 */         d0 = d1;
/*  55 */         entityliving = entityliving1;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  60 */     if (entityliving == null)
/*     */     {
/*  62 */       return false;
/*     */     }
/*  64 */     if (d0 < this.maxRange)
/*     */     {
/*     */       
/*  67 */       return false;
/*     */     }
/*  69 */     this.herdAnimal = entityliving;
/*     */     
/*  71 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldContinueExecuting() {
/*  80 */     if (this.theAnimal instanceof IMoCEntity && ((IMoCEntity)this.theAnimal).isMovementCeased())
/*  81 */       return false; 
/*  82 */     if (!this.herdAnimal.isEntityAlive()) {
/*  83 */       return false;
/*     */     }
/*  85 */     double d0 = this.theAnimal.getDistanceSq((Entity)this.herdAnimal);
/*  86 */     return (d0 >= this.minRange && d0 <= this.maxRange);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  95 */     this.delayCounter = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/* 103 */     this.herdAnimal = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/* 111 */     if (--this.delayCounter <= 0) {
/* 112 */       this.delayCounter = 20;
/*     */       
/* 114 */       this.theAnimal.getNavigator().tryMoveToEntityLiving((Entity)this.herdAnimal, this.moveSpeed);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExecutionChance(int newchance) {
/* 122 */     this.executionChance = newchance;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ai\EntityAIFollowHerd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */