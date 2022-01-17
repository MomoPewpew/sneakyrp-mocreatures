/*     */ package drzhark.mocreatures.entity.ai;
/*     */ 
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.entity.IMoCEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityMoveHelper;
/*     */ import net.minecraft.pathfinding.NodeProcessor;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.pathfinding.PathNodeType;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ 
/*     */ public class EntityAIMoverHelperMoC extends EntityMoveHelper {
/*     */   EntityCreature theCreature;
/*  18 */   protected EntityMoveHelper.Action action = EntityMoveHelper.Action.WAIT;
/*     */ 
/*     */   
/*     */   public boolean isUpdating() {
/*  22 */     return (this.action == EntityMoveHelper.Action.MOVE_TO);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getSpeed() {
/*  27 */     return this.speed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMoveTo(double x, double y, double z, double speedIn) {
/*  35 */     this.posX = x;
/*  36 */     this.posY = y;
/*  37 */     this.posZ = z;
/*  38 */     this.speed = speedIn;
/*  39 */     this.action = EntityMoveHelper.Action.MOVE_TO;
/*     */   }
/*     */ 
/*     */   
/*     */   public void strafe(float forward, float strafe) {
/*  44 */     this.action = EntityMoveHelper.Action.STRAFE;
/*  45 */     this.moveForward = forward;
/*  46 */     this.moveStrafe = strafe;
/*  47 */     this.speed = 0.25D;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdateMoveOnGround() {
/*  63 */     if (this.action == EntityMoveHelper.Action.STRAFE) {
/*     */       
/*  65 */       float f = (float)this.entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
/*  66 */       float f1 = (float)this.speed * f;
/*  67 */       float f2 = this.moveForward;
/*  68 */       float f3 = this.moveStrafe;
/*  69 */       float f4 = MathHelper.sqrt(f2 * f2 + f3 * f3);
/*     */       
/*  71 */       if (f4 < 1.0F)
/*     */       {
/*  73 */         f4 = 1.0F;
/*     */       }
/*     */       
/*  76 */       f4 = f1 / f4;
/*  77 */       f2 *= f4;
/*  78 */       f3 *= f4;
/*  79 */       float f5 = MathHelper.sin(this.entity.rotationYaw * 0.017453292F);
/*  80 */       float f6 = MathHelper.cos(this.entity.rotationYaw * 0.017453292F);
/*  81 */       float f7 = f2 * f6 - f3 * f5;
/*  82 */       float f8 = f3 * f6 + f2 * f5;
/*  83 */       PathNavigate pathnavigate = this.entity.getNavigator();
/*     */       
/*  85 */       if (pathnavigate != null) {
/*     */         
/*  87 */         NodeProcessor nodeprocessor = pathnavigate.getNodeProcessor();
/*     */         
/*  89 */         if (nodeprocessor != null && nodeprocessor.getPathNodeType((IBlockAccess)this.entity.world, MathHelper.floor(this.entity.posX + f7), MathHelper.floor(this.entity.posY), MathHelper.floor(this.entity.posZ + f8)) != PathNodeType.WALKABLE) {
/*     */           
/*  91 */           this.moveForward = 1.0F;
/*  92 */           this.moveStrafe = 0.0F;
/*  93 */           f1 = f;
/*     */         } 
/*     */       } 
/*     */       
/*  97 */       this.entity.setAIMoveSpeed(f1);
/*  98 */       this.entity.setMoveForward(this.moveForward);
/*  99 */       this.entity.setMoveStrafing(this.moveStrafe);
/* 100 */       this.action = EntityMoveHelper.Action.WAIT;
/*     */     }
/* 102 */     else if (this.action == EntityMoveHelper.Action.MOVE_TO) {
/*     */       
/* 104 */       this.action = EntityMoveHelper.Action.WAIT;
/* 105 */       double d0 = this.posX - this.entity.posX;
/* 106 */       double d1 = this.posZ - this.entity.posZ;
/* 107 */       double d2 = this.posY - this.entity.posY;
/* 108 */       double d3 = d0 * d0 + d2 * d2 + d1 * d1;
/*     */       
/* 110 */       if (d3 < 2.500000277905201E-7D) {
/*     */         
/* 112 */         this.entity.setMoveForward(0.0F);
/*     */         
/*     */         return;
/*     */       } 
/* 116 */       float f9 = (float)(MathHelper.atan2(d1, d0) * 57.29577951308232D) - 90.0F;
/* 117 */       this.entity.rotationYaw = limitAngle(this.entity.rotationYaw, f9, 20.0F);
/* 118 */       this.entity.setAIMoveSpeed((float)(this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));
/*     */       
/* 120 */       if (d2 > this.entity.stepHeight && d0 * d0 + d1 * d1 < Math.max(1.0F, this.entity.width))
/*     */       {
/* 122 */         this.entity.getJumpHelper().setJumping();
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 127 */       this.entity.setMoveForward(0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float limitAngle(float p_75639_1_, float p_75639_2_, float p_75639_3_) {
/* 136 */     float f = MathHelper.wrapDegrees(p_75639_2_ - p_75639_1_);
/*     */     
/* 138 */     if (f > p_75639_3_)
/*     */     {
/* 140 */       f = p_75639_3_;
/*     */     }
/*     */     
/* 143 */     if (f < -p_75639_3_)
/*     */     {
/* 145 */       f = -p_75639_3_;
/*     */     }
/*     */     
/* 148 */     float f1 = p_75639_1_ + f;
/*     */     
/* 150 */     if (f1 < 0.0F) {
/*     */       
/* 152 */       f1 += 360.0F;
/*     */     }
/* 154 */     else if (f1 > 360.0F) {
/*     */       
/* 156 */       f1 -= 360.0F;
/*     */     } 
/*     */     
/* 159 */     return f1;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getX() {
/* 164 */     return this.posX;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getY() {
/* 169 */     return this.posY;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getZ() {
/* 174 */     return this.posZ;
/*     */   }
/*     */   
/*     */   public enum Action
/*     */   {
/* 179 */     WAIT,
/* 180 */     MOVE_TO,
/* 181 */     STRAFE;
/*     */   }
/*     */   
/*     */   public EntityAIMoverHelperMoC(EntityLiving entityliving) {
/* 185 */     super(entityliving);
/* 186 */     this.theCreature = (EntityCreature)entityliving;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdateMoveHelper() {
/* 191 */     boolean isFlyer = ((IMoCEntity)this.theCreature).isFlyer();
/* 192 */     boolean isSwimmer = this.theCreature.isInWater();
/* 193 */     float fLimitAngle = 90.0F;
/* 194 */     if (!isFlyer && !isSwimmer) {
/* 195 */       onUpdateMoveOnGround();
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/* 202 */     if (isFlyer && !this.theCreature.isBeingRidden()) {
/* 203 */       flyingMovementUpdate();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 209 */     if (isSwimmer) {
/* 210 */       swimmerMovementUpdate();
/* 211 */       fLimitAngle = 30.0F;
/*     */     } 
/* 213 */     if (this.action == EntityMoveHelper.Action.MOVE_TO && !this.theCreature.getNavigator().noPath()) {
/* 214 */       double d0 = this.posX - this.theCreature.posX;
/* 215 */       double d1 = this.posY - this.theCreature.posY;
/* 216 */       double d2 = this.posZ - this.theCreature.posZ;
/* 217 */       double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/* 218 */       d3 = MathHelper.sqrt(d3);
/* 219 */       if (d3 < 0.5D) {
/* 220 */         this.entity.setMoveForward(0.0F);
/* 221 */         this.theCreature.getNavigator().clearPath();
/*     */         
/*     */         return;
/*     */       } 
/* 225 */       d1 /= d3;
/* 226 */       float f = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
/* 227 */       this.theCreature.rotationYaw = limitAngle(this.theCreature.rotationYaw, f, fLimitAngle);
/* 228 */       this.theCreature.renderYawOffset = this.theCreature.rotationYaw;
/* 229 */       float f1 = (float)(this.speed * this.theCreature.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
/* 230 */       this.theCreature.setAIMoveSpeed(this.theCreature.getAIMoveSpeed() + (f1 - this.theCreature.getAIMoveSpeed()) * 0.125F);
/* 231 */       double d4 = Math.sin((this.theCreature.ticksExisted + this.theCreature.getEntityId()) * 0.75D) * 0.01D;
/* 232 */       double d5 = Math.cos((this.theCreature.rotationYaw * 3.1415927F / 180.0F));
/* 233 */       double d6 = Math.sin((this.theCreature.rotationYaw * 3.1415927F / 180.0F));
/* 234 */       this.theCreature.motionX += d4 * d5;
/* 235 */       this.theCreature.motionZ += d4 * d6;
/*     */       
/* 237 */       this.theCreature.motionY += d4 * (d6 + d5) * 0.25D;
/* 238 */       this.theCreature.motionY += this.theCreature.getAIMoveSpeed() * d1 * 1.5D;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void flyingMovementUpdate() {
/* 248 */     if (((IMoCEntity)this.theCreature).getIsFlying()) {
/* 249 */       int distY = MoCTools.distanceToFloor((Entity)this.theCreature);
/* 250 */       if (distY <= ((IMoCEntity)this.theCreature).minFlyingHeight() && (this.theCreature.collidedHorizontally || this.theCreature.world.rand
/* 251 */         .nextInt(100) == 0)) {
/* 252 */         this.theCreature.motionY += 0.02D;
/*     */       }
/* 254 */       if (distY > ((IMoCEntity)this.theCreature).maxFlyingHeight() || this.theCreature.world.rand.nextInt(150) == 0) {
/* 255 */         this.theCreature.motionY -= 0.02D;
/*     */       
/*     */       }
/*     */     }
/* 259 */     else if (this.theCreature.motionY < 0.0D) {
/* 260 */       this.theCreature.motionY *= 0.6D;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void swimmerMovementUpdate() {
/* 270 */     if (this.theCreature.isBeingRidden()) {
/*     */       return;
/*     */     }
/*     */     
/* 274 */     double distToSurface = MoCTools.waterSurfaceAtGivenEntity((Entity)this.theCreature) - this.theCreature.posY;
/* 275 */     if (distToSurface > ((IMoCEntity)this.theCreature).getDivingDepth()) {
/* 276 */       if (this.theCreature.motionY < 0.0D) {
/* 277 */         this.theCreature.motionY = 0.0D;
/*     */       }
/* 279 */       this.theCreature.motionY += 0.001D;
/* 280 */       this.theCreature.motionY += distToSurface * 0.01D;
/*     */     } 
/*     */     
/* 283 */     if (!this.theCreature.getNavigator().noPath() && this.theCreature.collidedHorizontally) {
/* 284 */       if (this.theCreature instanceof drzhark.mocreatures.entity.MoCEntityAquatic) {
/* 285 */         this.theCreature.motionY = 0.05D;
/*     */       } else {
/* 287 */         ((IMoCEntity)this.theCreature).forceEntityJump();
/*     */       } 
/*     */     }
/*     */     
/* 291 */     if (this.theCreature.getAttackTarget() != null && (this.theCreature.getAttackTarget()).posY < this.posY - 0.5D && this.theCreature
/* 292 */       .getDistance((Entity)this.theCreature.getAttackTarget()) < 10.0F) {
/* 293 */       if (this.theCreature.motionY < -0.1D)
/* 294 */         this.theCreature.motionY = -0.1D; 
/*     */       return;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ai\EntityAIMoverHelperMoC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */