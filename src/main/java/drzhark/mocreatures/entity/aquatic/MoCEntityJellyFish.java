/*     */ package drzhark.mocreatures.entity.aquatic;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.MoCEntityTameableAquatic;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MoCEntityJellyFish extends MoCEntityTameableAquatic {
/*  20 */   private static final DataParameter<Boolean> GLOWS = EntityDataManager.createKey(MoCEntityJellyFish.class, DataSerializers.BOOLEAN); private int poisoncounter;
/*     */   
/*     */   public MoCEntityJellyFish(World world) {
/*  23 */     super(world);
/*  24 */     setSize(0.3F, 0.5F);
/*  25 */     setEdad(50 + this.rand.nextInt(50));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initEntityAI() {
/*  30 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 0.5D, 120));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  35 */     super.applyEntityAttributes();
/*  36 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
/*  37 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.15D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectType() {
/*  42 */     if (getType() == 0) {
/*  43 */       setType(this.rand.nextInt(5) + 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  49 */     super.entityInit();
/*  50 */     this.dataManager.register(GLOWS, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public void setGlowing(boolean flag) {
/*  54 */     this.dataManager.set(GLOWS, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public boolean isGlowing() {
/*  58 */     return ((Boolean)this.dataManager.get(GLOWS)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAIMoveSpeed() {
/*  63 */     return 0.02F;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  68 */     switch (getType()) {
/*     */       case 1:
/*  70 */         return MoCreatures.proxy.getTexture("jellyfisha.png");
/*     */       case 2:
/*  72 */         return MoCreatures.proxy.getTexture("jellyfishb.png");
/*     */       case 3:
/*  74 */         return MoCreatures.proxy.getTexture("jellyfishc.png");
/*     */       case 4:
/*  76 */         return MoCreatures.proxy.getTexture("jellyfishd.png");
/*     */       case 5:
/*  78 */         return MoCreatures.proxy.getTexture("jellyfishe.png");
/*     */       case 6:
/*  80 */         return MoCreatures.proxy.getTexture("jellyfishf.png");
/*     */       case 7:
/*  82 */         return MoCreatures.proxy.getTexture("jellyfishg.png");
/*     */       case 8:
/*  84 */         return MoCreatures.proxy.getTexture("jellyfishh.png");
/*     */       case 9:
/*  86 */         return MoCreatures.proxy.getTexture("jellyfishi.png");
/*     */       case 10:
/*  88 */         return MoCreatures.proxy.getTexture("jellyfishj.png");
/*     */       case 11:
/*  90 */         return MoCreatures.proxy.getTexture("jellyfishk.png");
/*     */       case 12:
/*  92 */         return MoCreatures.proxy.getTexture("jellyfishl.png");
/*     */     } 
/*     */     
/*  95 */     return MoCreatures.proxy.getTexture("jellyfisha.png");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 101 */     super.onLivingUpdate();
/* 102 */     if (!this.world.isRemote) {
/*     */       
/* 104 */       if (this.rand.nextInt(200) == 0) {
/* 105 */         setGlowing(!this.world.isDaytime());
/*     */       }
/*     */       
/* 108 */       if (!getIsTamed() && ++this.poisoncounter > 250 && shouldAttackPlayers() && this.rand.nextInt(30) == 0 && 
/* 109 */         MoCTools.findNearPlayerAndPoison((Entity)this, true)) {
/* 110 */         this.poisoncounter = 0;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 118 */     boolean flag = (this.rand.nextInt(2) == 0);
/* 119 */     if (flag) {
/* 120 */       return Items.SLIME_BALL;
/*     */     }
/* 122 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public float pitchRotationOffset() {
/* 127 */     if (!isInWater()) {
/* 128 */       return 90.0F;
/*     */     }
/* 130 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public int nameYOffset() {
/* 135 */     int yOff = (int)((getEdad() * -1) / 2.3D);
/* 136 */     return yOff;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getSizeFactor() {
/* 141 */     float myMoveSpeed = MoCTools.getMyMovementSpeed((Entity)this);
/* 142 */     float pulseSpeed = 0.08F;
/* 143 */     if (myMoveSpeed > 0.0F)
/* 144 */       pulseSpeed = 0.5F; 
/* 145 */     float pulseSize = MathHelper.cos(this.ticksExisted * pulseSpeed) * 0.2F;
/* 146 */     return getEdad() * 0.01F + pulseSize / 5.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canBeTrappedInNet() {
/* 151 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxEdad() {
/* 156 */     return 100;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\aquatic\MoCEntityJellyFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */