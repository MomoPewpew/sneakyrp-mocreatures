/*     */ package drzhark.mocreatures.entity.ai;
/*     */ 
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RandomPositionGeneratorMoCFlyer
/*     */ {
/*  19 */   private static Vec3d staticVector = Vec3d.ZERO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static Vec3d findRandomTarget(EntityCreature entitycreatureIn, int xz, int y) {
/*  31 */     return findRandomTargetBlock(entitycreatureIn, xz, y, (Vec3d)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static Vec3d findRandomTargetBlockTowards(EntityCreature entitycreatureIn, int xz, int y, Vec3d targetVec3) {
/*  40 */     staticVector = targetVec3.subtract(entitycreatureIn.posX, entitycreatureIn.posY, entitycreatureIn.posZ);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  45 */     return findRandomTargetBlock(entitycreatureIn, xz, y, staticVector);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static Vec3d findRandomTargetBlockAwayFrom(EntityCreature entitycreatureIn, int xz, int y, Vec3d targetVec3) {
/*  54 */     staticVector = (new Vec3d(entitycreatureIn.posX, entitycreatureIn.posY, entitycreatureIn.posZ)).subtract(targetVec3);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  59 */     return findRandomTargetBlock(entitycreatureIn, xz, y, staticVector);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private static Vec3d findRandomTargetBlock(EntityCreature entitycreatureIn, int xz, int y, @Nullable Vec3d targetVec3) {
/*     */     boolean flag1;
/*  69 */     PathNavigate pathnavigate = entitycreatureIn.getNavigator();
/*  70 */     Random random = entitycreatureIn.getRNG();
/*  71 */     boolean flag = false;
/*  72 */     int i = 0;
/*  73 */     int j = 0;
/*  74 */     int k = 0;
/*  75 */     float f = -99999.0F;
/*     */ 
/*     */     
/*  78 */     if (entitycreatureIn.hasHome()) {
/*     */       
/*  80 */       double d0 = entitycreatureIn.getHomePosition().distanceSq(MathHelper.floor(entitycreatureIn.posX), MathHelper.floor(entitycreatureIn.posY), MathHelper.floor(entitycreatureIn.posZ)) + 4.0D;
/*  81 */       double d1 = (entitycreatureIn.getMaximumHomeDistance() + xz);
/*  82 */       flag1 = (d0 < d1 * d1);
/*     */     }
/*     */     else {
/*     */       
/*  86 */       flag1 = false;
/*     */     } 
/*     */     
/*  89 */     for (int j1 = 0; j1 < 10; j1++) {
/*     */       
/*  91 */       int l = random.nextInt(2 * xz + 1) - xz;
/*  92 */       int k1 = random.nextInt(2 * y + 1) - y;
/*  93 */       int i1 = random.nextInt(2 * xz + 1) - xz;
/*     */       
/*  95 */       if (targetVec3 == null || l * targetVec3.x + i1 * targetVec3.z >= 0.0D) {
/*     */         
/*  97 */         if (entitycreatureIn.hasHome() && xz > 1) {
/*     */           
/*  99 */           BlockPos blockpos = entitycreatureIn.getHomePosition();
/*     */           
/* 101 */           if (entitycreatureIn.posX > blockpos.getX()) {
/*     */             
/* 103 */             l -= random.nextInt(xz / 2);
/*     */           }
/*     */           else {
/*     */             
/* 107 */             l += random.nextInt(xz / 2);
/*     */           } 
/*     */           
/* 110 */           if (entitycreatureIn.posZ > blockpos.getZ()) {
/*     */             
/* 112 */             i1 -= random.nextInt(xz / 2);
/*     */           }
/*     */           else {
/*     */             
/* 116 */             i1 += random.nextInt(xz / 2);
/*     */           } 
/*     */         } 
/*     */         
/* 120 */         BlockPos blockpos1 = new BlockPos(l + entitycreatureIn.posX, k1 + entitycreatureIn.posY, i1 + entitycreatureIn.posZ);
/*     */         
/* 122 */         if (!flag1 || entitycreatureIn.isWithinHomeDistanceFromPosition(blockpos1)) {
/*     */           
/* 124 */           float f1 = entitycreatureIn.getBlockPathWeight(blockpos1);
/*     */           
/* 126 */           if (f1 > f) {
/*     */             
/* 128 */             f = f1;
/* 129 */             i = l;
/* 130 */             j = k1;
/* 131 */             k = i1;
/* 132 */             flag = true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 138 */     if (flag)
/*     */     {
/* 140 */       return new Vec3d(i + entitycreatureIn.posX, j + entitycreatureIn.posY, k + entitycreatureIn.posZ);
/*     */     }
/*     */ 
/*     */     
/* 144 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ai\RandomPositionGeneratorMoCFlyer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */