/*    */ package drzhark.mocreatures.entity.ai;
/*    */ 
/*    */ import drzhark.mocreatures.entity.IMoCEntity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.ai.EntityAIBase;
/*    */ import net.minecraft.entity.ai.RandomPositionGenerator;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ 
/*    */ public class EntityAIFleeFromPlayer extends EntityAIBase {
/*    */   private EntityCreature entityCreature;
/*    */   protected double speed;
/*    */   protected double distance;
/*    */   private double randPosX;
/*    */   private double randPosY;
/*    */   private double randPosZ;
/*    */   
/*    */   public EntityAIFleeFromPlayer(EntityCreature creature, double speedIn, double distanceToCheck) {
/* 20 */     this.entityCreature = creature;
/* 21 */     this.distance = distanceToCheck;
/* 22 */     this.speed = speedIn;
/* 23 */     setMutexBits(1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 32 */     if (this.entityCreature instanceof IMoCEntity && (
/* 33 */       (IMoCEntity)this.entityCreature).isNotScared()) {
/* 34 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 38 */     if (!IsNearPlayer(this.distance)) {
/* 39 */       return false;
/*    */     }
/* 41 */     Vec3d vec3 = RandomPositionGenerator.findRandomTarget(this.entityCreature, 5, 4);
/*    */     
/* 43 */     if (vec3 == null) {
/* 44 */       return false;
/*    */     }
/* 46 */     this.randPosX = vec3.x;
/* 47 */     this.randPosY = vec3.y;
/* 48 */     this.randPosZ = vec3.z;
/* 49 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean IsNearPlayer(double d) {
/* 55 */     EntityPlayer entityplayer1 = this.entityCreature.world.getClosestPlayerToEntity((Entity)this.entityCreature, d);
/* 56 */     if (entityplayer1 != null) {
/* 57 */       return true;
/*    */     }
/* 59 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 67 */     this.entityCreature.getNavigator().tryMoveToXYZ(this.randPosX, this.randPosY, this.randPosZ, this.speed);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldContinueExecuting() {
/* 75 */     return !this.entityCreature.getNavigator().noPath();
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ai\EntityAIFleeFromPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */