/*     */ package drzhark.mocreatures.entity.ai;
/*     */ 
/*     */ import drzhark.mocreatures.entity.IMoCEntity;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ 
/*     */ public class EntityAIFollowAdult
/*     */   extends EntityAIBase
/*     */ {
/*     */   EntityLiving childAnimal;
/*     */   EntityLiving parentAnimal;
/*     */   double moveSpeed;
/*     */   private int delayCounter;
/*     */   
/*     */   public EntityAIFollowAdult(EntityLiving animal, double speed) {
/*  19 */     this.childAnimal = animal;
/*  20 */     this.moveSpeed = speed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  28 */     if (!(this.childAnimal instanceof IMoCEntity) || ((IMoCEntity)this.childAnimal).getIsAdult()) {
/*  29 */       return false;
/*     */     }
/*     */     
/*  32 */     List<EntityLiving> list = this.childAnimal.world.getEntitiesWithinAABB(this.childAnimal.getClass(), this.childAnimal
/*  33 */         .getEntityBoundingBox().expand(8.0D, 4.0D, 8.0D));
/*  34 */     EntityLiving entityliving = null;
/*  35 */     double d0 = Double.MAX_VALUE;
/*  36 */     Iterator<EntityLiving> iterator = list.iterator();
/*     */     
/*  38 */     while (iterator.hasNext()) {
/*  39 */       EntityLiving entityliving1 = iterator.next();
/*     */       
/*  41 */       if (((IMoCEntity)entityliving1).getIsAdult()) {
/*  42 */         double d1 = this.childAnimal.getDistanceSq((Entity)entityliving1);
/*     */         
/*  44 */         if (d1 <= d0) {
/*  45 */           d0 = d1;
/*  46 */           entityliving = entityliving1;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  51 */     if (entityliving == null)
/*  52 */       return false; 
/*  53 */     if (d0 < 9.0D) {
/*  54 */       return false;
/*     */     }
/*  56 */     this.parentAnimal = entityliving;
/*  57 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldContinueExecuting() {
/*  67 */     if (((IMoCEntity)this.childAnimal).getIsAdult())
/*  68 */       return false; 
/*  69 */     if (!this.parentAnimal.isEntityAlive()) {
/*  70 */       return false;
/*     */     }
/*  72 */     double d0 = this.childAnimal.getDistanceSq((Entity)this.parentAnimal);
/*  73 */     return (d0 >= 9.0D && d0 <= 256.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  82 */     this.delayCounter = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/*  90 */     this.parentAnimal = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/*  98 */     if (--this.delayCounter <= 0) {
/*  99 */       this.delayCounter = 10;
/* 100 */       this.childAnimal.getNavigator().tryMoveToEntityLiving((Entity)this.parentAnimal, this.moveSpeed);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ai\EntityAIFollowAdult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */