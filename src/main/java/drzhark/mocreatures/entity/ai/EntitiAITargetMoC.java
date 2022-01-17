/*     */ package drzhark.mocreatures.entity.ai;
/*     */ 
/*     */ import drzhark.mocreatures.entity.MoCEntityAnimal;
/*     */ import drzhark.mocreatures.entity.monster.MoCEntityOgre;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityOwnable;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.pathfinding.Path;
/*     */ import net.minecraft.pathfinding.PathPoint;
/*     */ import net.minecraft.scoreboard.Team;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class EntitiAITargetMoC
/*     */   extends EntityAIBase
/*     */ {
/*     */   protected final EntityCreature taskOwner;
/*     */   protected boolean shouldCheckSight;
/*     */   private boolean nearbyOnly;
/*     */   private int targetSearchStatus;
/*     */   private int targetSearchDelay;
/*     */   private int targetUnseenTicks;
/*     */   
/*     */   public EntitiAITargetMoC(EntityCreature creature, boolean checkSight, boolean onlyNearby) {
/*  38 */     this.taskOwner = creature;
/*  39 */     this.shouldCheckSight = checkSight;
/*  40 */     this.nearbyOnly = onlyNearby;
/*     */   }
/*     */   
/*     */   public EntitiAITargetMoC(EntityCreature creature, boolean checkSight) {
/*  44 */     this(creature, checkSight, false);
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
/*     */   public static boolean isSuitableTarget(EntityLiving attacker, EntityLivingBase target, boolean includeInvincibles, boolean checkSight) {
/*  57 */     if (target == null)
/*  58 */       return false; 
/*  59 */     if (target == attacker)
/*  60 */       return false; 
/*  61 */     if (!target.isEntityAlive())
/*  62 */       return false; 
/*  63 */     if (!attacker.canAttackClass(target.getClass()))
/*  64 */       return false; 
/*  65 */     if (attacker instanceof MoCEntityAnimal && !(target instanceof EntityPlayer)) {
/*  66 */       MoCEntityAnimal mocattacker = (MoCEntityAnimal)attacker;
/*  67 */       if (!mocattacker.canAttackTarget(target)) {
/*  68 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  72 */       if (mocattacker.getIsTamed() && target instanceof MoCEntityAnimal && ((MoCEntityAnimal)target).getIsTamed()) {
/*  73 */         return false;
/*     */       }
/*     */     } 
/*  76 */     Team team = attacker.getTeam();
/*  77 */     Team team1 = target.getTeam();
/*     */     
/*  79 */     if (team != null && team1 == team) {
/*  80 */       return false;
/*     */     }
/*  82 */     if (attacker instanceof IEntityOwnable && ((IEntityOwnable)attacker).getOwnerId() != null) {
/*  83 */       if (target instanceof IEntityOwnable && ((IEntityOwnable)attacker).getOwnerId().equals(((IEntityOwnable)target).getOwnerId())) {
/*  84 */         return false;
/*     */       }
/*     */       
/*  87 */       if (target == ((IEntityOwnable)attacker).getOwner()) {
/*  88 */         return false;
/*     */       }
/*  90 */     } else if (target instanceof EntityPlayer && includeInvincibles && ((EntityPlayer)target).capabilities.disableDamage) {
/*  91 */       return false;
/*     */     } 
/*     */     
/*  94 */     return (!checkSight || attacker.getEntitySenses().canSee((Entity)target));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldContinueExecuting() {
/* 104 */     EntityLivingBase entitylivingbase = this.taskOwner.getAttackTarget();
/*     */     
/* 106 */     if (entitylivingbase == null)
/* 107 */       return false; 
/* 108 */     if (!entitylivingbase.isEntityAlive()) {
/* 109 */       return false;
/*     */     }
/* 111 */     Team team = this.taskOwner.getTeam();
/* 112 */     Team team1 = entitylivingbase.getTeam();
/*     */     
/* 114 */     if (team != null && team1 == team) {
/* 115 */       return false;
/*     */     }
/* 117 */     double d0 = getTargetDistance();
/*     */     
/* 119 */     if (this.taskOwner.getDistanceSq((Entity)entitylivingbase) > d0 * d0) {
/* 120 */       return false;
/*     */     }
/* 122 */     if (this.shouldCheckSight) {
/* 123 */       if (this.taskOwner.getEntitySenses().canSee((Entity)entitylivingbase)) {
/* 124 */         this.targetUnseenTicks = 0;
/* 125 */       } else if (++this.targetUnseenTicks > 60) {
/* 126 */         return false;
/*     */       } 
/*     */     }
/*     */     
/* 130 */     return (!(entitylivingbase instanceof EntityPlayer) || !((EntityPlayer)entitylivingbase).capabilities.disableDamage);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected double getTargetDistance() {
/* 137 */     if (this.taskOwner instanceof MoCEntityOgre) {
/* 138 */       return ((MoCEntityOgre)this.taskOwner).getAttackRange();
/*     */     }
/* 140 */     IAttributeInstance iattributeinstance = this.taskOwner.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
/* 141 */     return (iattributeinstance == null) ? 16.0D : iattributeinstance.getAttributeValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/* 149 */     this.targetSearchStatus = 0;
/* 150 */     this.targetSearchDelay = 0;
/* 151 */     this.targetUnseenTicks = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/* 159 */     this.taskOwner.setAttackTarget((EntityLivingBase)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isSuitableTarget(EntityLivingBase target, boolean includeInvincibles) {
/* 167 */     if (!isSuitableTarget((EntityLiving)this.taskOwner, target, includeInvincibles, this.shouldCheckSight))
/*     */     {
/* 169 */       return false; } 
/* 170 */     if (!this.taskOwner.isWithinHomeDistanceFromPosition(new BlockPos((Entity)target)))
/*     */     {
/* 172 */       return false;
/*     */     }
/* 174 */     if (this.nearbyOnly) {
/* 175 */       if (--this.targetSearchDelay <= 0) {
/* 176 */         this.targetSearchStatus = 0;
/*     */       }
/*     */       
/* 179 */       if (this.targetSearchStatus == 0) {
/* 180 */         this.targetSearchStatus = canEasilyReach(target) ? 1 : 2;
/*     */       }
/*     */       
/* 183 */       if (this.targetSearchStatus == 2) {
/* 184 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 188 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean canEasilyReach(EntityLivingBase p_75295_1_) {
/* 196 */     this.targetSearchDelay = 10 + this.taskOwner.getRNG().nextInt(5);
/* 197 */     Path path = this.taskOwner.getNavigator().getPathToEntityLiving((Entity)p_75295_1_);
/*     */     
/* 199 */     if (path == null)
/*     */     {
/* 201 */       return false;
/*     */     }
/* 203 */     PathPoint pathpoint = path.getFinalPathPoint();
/*     */     
/* 205 */     if (pathpoint == null) {
/* 206 */       return false;
/*     */     }
/* 208 */     int i = pathpoint.x - MathHelper.floor(p_75295_1_.posX);
/* 209 */     int j = pathpoint.z - MathHelper.floor(p_75295_1_.posZ);
/* 210 */     return ((i * i + j * j) <= 2.25D);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ai\EntitiAITargetMoC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */