/*     */ package drzhark.mocreatures.entity.ambient;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.MoCEntityAmbient;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */
/*     */ public class MoCEntitySnail extends MoCEntityAmbient {
/*  18 */   private static final DataParameter<Boolean> IS_HIDDING = EntityDataManager.createKey(MoCEntitySnail.class, DataSerializers.BOOLEAN);
/*     */
/*     */   public MoCEntitySnail(World world) {
/*  21 */     super(world);
/*  22 */     setSize(0.2F, 0.2F);
/*     */   }
/*     */
/*     */
/*     */   protected void initEntityAI() {
/*  27 */     this.tasks.addTask(1, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 0.8D));
/*     */   }
/*     */
/*     */
/*     */   protected void entityInit() {
/*  32 */     super.entityInit();
/*  33 */     this.dataManager.register(IS_HIDDING, Boolean.valueOf(false));
/*     */   }
/*     */
/*     */
/*     */   protected void applyEntityAttributes() {
/*  38 */     super.applyEntityAttributes();
/*  39 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0D);
/*  40 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1D);
/*     */   }
/*     */
/*     */
/*     */   public boolean isMovementCeased() {
/*  45 */     return getIsHiding();
/*     */   }
/*     */
/*     */
/*     */   public void selectType() {
/*  50 */     if (getType() == 0) {
/*  51 */       setType(this.rand.nextInt(6) + 1);
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   public ResourceLocation getTexture() {
/*  57 */     switch (getType()) {
/*     */       case 1:
/*  59 */         return MoCreatures.proxy.getTexture("snaila.png");
/*     */       case 2:
/*  61 */         return MoCreatures.proxy.getTexture("snailb.png");
/*     */       case 3:
/*  63 */         return MoCreatures.proxy.getTexture("snailc.png");
/*     */       case 4:
/*  65 */         return MoCreatures.proxy.getTexture("snaild.png");
/*     */       case 5:
/*  67 */         return MoCreatures.proxy.getTexture("snaile.png");
/*     */       case 6:
/*  69 */         return MoCreatures.proxy.getTexture("snailf.png");
/*     */     }
/*  71 */     return MoCreatures.proxy.getTexture("snaila.png");
/*     */   }
/*     */
/*     */
/*     */   public boolean getIsHiding() {
/*  76 */     return ((Boolean)this.dataManager.get(IS_HIDDING)).booleanValue();
/*     */   }
/*     */
/*     */   public void setIsHiding(boolean flag) {
/*  80 */     this.dataManager.set(IS_HIDDING, Boolean.valueOf(flag));
/*     */   }
/*     */
/*     */
/*     */   public void onLivingUpdate() {
/*  85 */     super.onLivingUpdate();
/*     */
/*  87 */     if (!this.world.isRemote) {
/*  88 */       EntityLivingBase entityliving = getBoogey(3.0D);
/*  89 */       if (entityliving != null && entityliving.height > 0.5F && entityliving.width > 0.5F && canEntityBeSeen((Entity)entityliving)) {
/*  90 */         if (!getIsHiding()) {
/*  91 */           setIsHiding(true);
/*     */         }
/*  93 */         getNavigator().clearPath();
/*     */       } else {
/*  95 */         setIsHiding(false);
/*     */       }
/*     */
/*     */
/*     */
/* 100 */       if (getIsHiding() && getType() > 4) {
/* 101 */         setIsHiding(false);
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */
/*     */
/*     */   public void fall(float f, float f1) {}
/*     */
/*     */
/*     */   public void onUpdate() {
/* 112 */     super.onUpdate();
/*     */
/* 114 */     if (getIsHiding()) {
/* 115 */       this.prevRenderYawOffset = this.renderYawOffset = this.rotationYaw = this.prevRotationYaw;
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   protected Item getDropItem() {
/* 121 */     return Items.SLIME_BALL;
/*     */   }
/*     */
/*     */
/*     */   public boolean isOnLadder() {
/* 126 */     return this.collidedHorizontally;
/*     */   }
/*     */
/*     */   public boolean climbing() {
/* 130 */     return (!this.onGround && isOnLadder());
/*     */   }
/*     */
/*     */   public void jump() {}
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ambient\MoCEntitySnail.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
