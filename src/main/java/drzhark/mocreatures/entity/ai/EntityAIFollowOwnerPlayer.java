/*     */ package drzhark.mocreatures.entity.ai;
/*     */ 
/*     */ import drzhark.mocreatures.entity.IMoCEntity;
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityAIFollowOwnerPlayer extends EntityAIBase {
/*     */   private EntityLiving thePet;
/*     */   private EntityPlayer theOwner;
/*     */   World world;
/*     */   private double speed;
/*     */   private PathNavigate petPathfinder;
/*     */   private int delayCounter;
/*     */   float maxDist;
/*     */   float minDist;
/*     */   
/*     */   public EntityAIFollowOwnerPlayer(EntityLiving thePetIn, double speedIn, float minDistIn, float maxDistIn) {
/*  30 */     this.thePet = thePetIn;
/*  31 */     this.world = thePetIn.world;
/*  32 */     this.speed = speedIn;
/*  33 */     this.petPathfinder = thePetIn.getNavigator();
/*  34 */     this.minDist = minDistIn;
/*  35 */     this.maxDist = maxDistIn;
/*  36 */     setMutexBits(3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  49 */     if (((IMoCEntity)this.thePet).getIsSitting()) {
/*  50 */       return false;
/*     */     }
/*     */     
/*  53 */     UUID ownerUniqueId = ((IMoCTameable)this.thePet).getOwnerId();
/*  54 */     if (ownerUniqueId == null) {
/*  55 */       return false;
/*     */     }
/*     */     
/*  58 */     EntityPlayer entityplayer = EntityAITools.getIMoCTameableOwner((IMoCTameable)this.thePet);
/*     */     
/*  60 */     if (entityplayer == null) {
/*  61 */       return false;
/*     */     }
/*     */     
/*  64 */     if (this.thePet.getDistanceSq((Entity)entityplayer) < (this.minDist * this.minDist) || this.thePet
/*  65 */       .getDistanceSq((Entity)entityplayer) > (this.maxDist * this.maxDist)) {
/*  66 */       return false;
/*     */     }
/*  68 */     this.theOwner = entityplayer;
/*  69 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldContinueExecuting() {
/*  78 */     return (!this.petPathfinder.noPath() && this.thePet.getDistanceSq((Entity)this.theOwner) > (this.maxDist * this.maxDist) && 
/*  79 */       !((IMoCEntity)this.thePet).getIsSitting());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  87 */     this.delayCounter = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/*  97 */     this.theOwner = null;
/*  98 */     this.petPathfinder.clearPath();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isEmptyBlock(BlockPos pos) {
/* 104 */     IBlockState iblockstate = this.world.getBlockState(pos);
/* 105 */     return (iblockstate.getMaterial() == Material.AIR) ? true : (!iblockstate.isFullCube());
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTask() {
/* 110 */     this.thePet.getLookHelper().setLookPositionWithEntity((Entity)this.theOwner, 10.0F, this.thePet.getVerticalFaceSpeed());
/*     */     
/* 112 */     if (!((IMoCEntity)this.thePet).getIsSitting() && 
/* 113 */       --this.delayCounter <= 0) {
/*     */       
/* 115 */       this.delayCounter = 10;
/*     */       
/* 117 */       if (!this.petPathfinder.tryMoveToEntityLiving((Entity)this.theOwner, this.speed))
/*     */       {
/* 119 */         if (!this.thePet.getLeashed())
/*     */         {
/* 121 */           if (this.thePet.getDistanceSq((Entity)this.theOwner) >= 144.0D) {
/*     */             
/* 123 */             int i = MathHelper.floor(this.theOwner.posX) - 2;
/* 124 */             int j = MathHelper.floor(this.theOwner.posZ) - 2;
/* 125 */             int k = MathHelper.floor((this.theOwner.getEntityBoundingBox()).minY);
/*     */             
/* 127 */             for (int l = 0; l <= 4; l++) {
/*     */               
/* 129 */               for (int i1 = 0; i1 <= 4; i1++) {
/*     */                 
/* 131 */                 BlockPos pos = new BlockPos(i + l, k - 1, j + i1);
/* 132 */                 if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && this.world.getBlockState(pos).isSideSolid((IBlockAccess)this.world, pos, EnumFacing.DOWN) && isEmptyBlock(new BlockPos(i + l, k, j + i1)) && isEmptyBlock(new BlockPos(i + l, k + 1, j + i1))) {
/*     */                   
/* 134 */                   this.thePet.setLocationAndAngles(((i + l) + 0.5F), k, ((j + i1) + 0.5F), this.thePet.rotationYaw, this.thePet.rotationPitch);
/* 135 */                   this.petPathfinder.clearPath();
/*     */                   return;
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ai\EntityAIFollowOwnerPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */