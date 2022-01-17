/*     */ package drzhark.mocreatures.entity;
/*     */ 
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import drzhark.mocreatures.network.MoCMessageHandler;
/*     */ import drzhark.mocreatures.network.message.MoCMessageAnimation;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*     */ 
/*     */ public class MoCEntityInsect extends MoCEntityAmbient {
/*     */   private int climbCounter;
/*     */   protected EntityAIWanderMoC2 wander;
/*  26 */   private static final DataParameter<Boolean> IS_FLYING = EntityDataManager.createKey(MoCEntityInsect.class, DataSerializers.BOOLEAN);
/*     */   
/*     */   public MoCEntityInsect(World world) {
/*  29 */     super(world);
/*  30 */     setSize(0.2F, 0.2F);
/*  31 */     this.tasks.addTask(2, (EntityAIBase)(this.wander = new EntityAIWanderMoC2((EntityCreature)this, 1.0D, 80)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  36 */     super.applyEntityAttributes();
/*  37 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
/*  38 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  43 */     super.entityInit();
/*  44 */     this.dataManager.register(IS_FLYING, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFlyer() {
/*  49 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFlyingAlone() {
/*  54 */     return getIsFlying();
/*     */   }
/*     */   
/*     */   public boolean getIsFlying() {
/*  58 */     return ((Boolean)this.dataManager.get(IS_FLYING)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setIsFlying(boolean flag) {
/*  62 */     this.dataManager.set(IS_FLYING, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/*  67 */     super.onLivingUpdate();
/*     */     
/*  69 */     if (!this.world.isRemote) {
/*  70 */       if (!getIsFlying() && isOnLadder() && !this.onGround) {
/*  71 */         MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 1), new NetworkRegistry.TargetPoint(this.world.provider
/*  72 */               .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */       }
/*     */       
/*  75 */       if (isFlyer() && !getIsFlying() && this.rand.nextInt(getFlyingFreq()) == 0) {
/*  76 */         List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(4.0D, 4.0D, 4.0D));
/*  77 */         for (int i = 0; i < list.size(); i++) {
/*  78 */           Entity entity1 = list.get(i);
/*  79 */           if (entity1 instanceof EntityLivingBase)
/*     */           {
/*     */             
/*  82 */             if (((EntityLivingBase)entity1).width >= 0.4F && ((EntityLivingBase)entity1).height >= 0.4F && canEntityBeSeen(entity1)) {
/*  83 */               setIsFlying(true);
/*  84 */               this.wander.makeUpdate();
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*  89 */       if (isFlyer() && !getIsFlying() && this.rand.nextInt(200) == 0) {
/*  90 */         setIsFlying(true);
/*  91 */         this.wander.makeUpdate();
/*     */       } 
/*     */       
/*  94 */       if (isAttractedToLight() && this.rand.nextInt(50) == 0) {
/*  95 */         int[] ai = MoCTools.ReturnNearestBlockCoord((Entity)this, Blocks.TORCH, Double.valueOf(8.0D));
/*  96 */         if (ai[0] > -1000) {
/*  97 */           getNavigator().tryMoveToXYZ(ai[0], ai[1], ai[2], 1.0D);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 102 */       if (getIsFlying() && getNavigator().noPath() && !isMovementCeased() && getAttackTarget() == null) {
/* 103 */         this.wander.makeUpdate();
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 108 */     else if (this.climbCounter > 0 && ++this.climbCounter > 8) {
/* 109 */       this.climbCounter = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAttractedToLight() {
/* 120 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void performAnimation(int animationType) {
/* 125 */     if (animationType == 1)
/*     */     {
/* 127 */       this.climbCounter = 1;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fall(float f, float f1) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCanSpawnHere() {
/* 138 */     return (getCanSpawnHereAnimal() && getCanSpawnHereCreature());
/*     */   }
/*     */ 
/*     */   
/*     */   public float getSizeFactor() {
/* 143 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxSpawnedInChunk() {
/* 148 */     return 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOnLadder() {
/* 153 */     return this.collidedHorizontally;
/*     */   }
/*     */   
/*     */   public boolean climbing() {
/* 157 */     return (this.climbCounter != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void jump() {}
/*     */ 
/*     */   
/*     */   protected boolean canTriggerWalking() {
/* 166 */     return false;
/*     */   }
/*     */   
/*     */   protected int getFlyingFreq() {
/* 170 */     return 20;
/*     */   }
/*     */ 
/*     */   
/*     */   public float rollRotationOffset() {
/* 175 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumCreatureAttribute getCreatureAttribute() {
/* 183 */     return EnumCreatureAttribute.ARTHROPOD;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PathNavigate getNavigator() {
/* 191 */     if (getIsFlying()) {
/* 192 */       return this.navigatorFlyer;
/*     */     }
/* 194 */     return this.navigator;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\MoCEntityInsect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */