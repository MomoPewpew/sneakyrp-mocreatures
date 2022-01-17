/*    */ package drzhark.mocreatures.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.pathfinding.PathFinder;
/*    */ import net.minecraft.pathfinding.PathNavigate;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class PathNavigateFlyer extends PathNavigate {
/*    */   public PathNavigateFlyer(EntityLiving entitylivingIn, World worldIn) {
/* 13 */     super(entitylivingIn, worldIn);
/*    */   }
/*    */   
/*    */   protected PathFinder getPathFinder() {
/* 17 */     return new PathFinder(new FlyNodeProcessor());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean canNavigate() {
/* 24 */     return true;
/*    */   }
/*    */   
/*    */   protected Vec3d getEntityPosition() {
/* 28 */     return new Vec3d(this.entity.posX, this.entity.posY + this.entity.height * 0.5D, this.entity.posZ);
/*    */   }
/*    */   
/*    */   protected void pathFollow() {
/* 32 */     Vec3d vec3 = getEntityPosition();
/* 33 */     float f = this.entity.width * this.entity.width;
/* 34 */     byte b0 = 6;
/*    */     
/* 36 */     if (vec3.squareDistanceTo(this.currentPath.getVectorFromIndex((Entity)this.entity, this.currentPath.getCurrentPathIndex())) < f) {
/* 37 */       this.currentPath.incrementPathIndex();
/*    */     }
/*    */     
/* 40 */     int i = Math.min(this.currentPath.getCurrentPathIndex() + b0, this.currentPath.getCurrentPathLength() - 1); for (; i > this.currentPath
/* 41 */       .getCurrentPathIndex(); i--) {
/* 42 */       Vec3d vec31 = this.currentPath.getVectorFromIndex((Entity)this.entity, i);
/*    */       
/* 44 */       if (vec31.squareDistanceTo(vec3) <= 36.0D && isDirectPathBetweenPoints(vec3, vec31, 0, 0, 0)) {
/* 45 */         this.currentPath.setCurrentPathIndex(i);
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 50 */     checkForStuck(vec3);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void removeSunnyPath() {
/* 57 */     super.removeSunnyPath();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean isDirectPathBetweenPoints(Vec3d posVec31, Vec3d posVec32, int sizeX, int sizeY, int sizeZ) {
/* 67 */     RayTraceResult raytraceresult = this.world.rayTraceBlocks(posVec31, new Vec3d(posVec32.x, posVec32.y + this.entity.height * 0.5D, posVec32.z), false, true, false);
/* 68 */     return (raytraceresult == null || raytraceresult.typeOfHit == RayTraceResult.Type.MISS);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ai\PathNavigateFlyer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */