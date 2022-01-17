/*     */ package drzhark.mocreatures.entity.ambient;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.entity.MoCEntityInsect;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MoCEntityAnt extends MoCEntityInsect {
/*  17 */   private static final DataParameter<Boolean> FOUND_FOOD = EntityDataManager.createKey(MoCEntityAnt.class, DataSerializers.BOOLEAN);
/*     */   
/*     */   public MoCEntityAnt(World world) {
/*  20 */     super(world);
/*  21 */     this.texture = "ant.png";
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initEntityAI() {
/*  26 */     this.tasks.addTask(1, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.2D));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  31 */     super.entityInit();
/*  32 */     this.dataManager.register(FOUND_FOOD, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  37 */     super.applyEntityAttributes();
/*  38 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.28D);
/*     */   }
/*     */   
/*     */   public boolean getHasFood() {
/*  42 */     return ((Boolean)this.dataManager.get(FOUND_FOOD)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setHasFood(boolean flag) {
/*  46 */     this.dataManager.set(FOUND_FOOD, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/*  51 */     super.onLivingUpdate();
/*     */     
/*  53 */     if (!this.world.isRemote && 
/*  54 */       !getHasFood()) {
/*  55 */       EntityItem entityitem = MoCTools.getClosestFood((Entity)this, 8.0D);
/*  56 */       if (entityitem == null || entityitem.isDead) {
/*     */         return;
/*     */       }
/*  59 */       if (entityitem.getRidingEntity() == null) {
/*  60 */         float f = entityitem.getDistance((Entity)this);
/*  61 */         if (f > 1.0F) {
/*  62 */           int i = MathHelper.floor(entityitem.posX);
/*  63 */           int j = MathHelper.floor(entityitem.posY);
/*  64 */           int k = MathHelper.floor(entityitem.posZ);
/*  65 */           faceLocation(i, j, k, 30.0F);
/*     */           
/*  67 */           getMyOwnPath((Entity)entityitem, f);
/*     */           return;
/*     */         } 
/*  70 */         if (f < 1.0F) {
/*  71 */           exchangeItem(entityitem);
/*  72 */           setHasFood(true);
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  80 */     if (getHasFood() && 
/*  81 */       !isBeingRidden()) {
/*  82 */       EntityItem entityitem = MoCTools.getClosestFood((Entity)this, 2.0D);
/*  83 */       if (entityitem != null && entityitem.getRidingEntity() == null) {
/*  84 */         entityitem.startRiding((Entity)this);
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/*  89 */       if (!isBeingRidden()) {
/*  90 */         setHasFood(false);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void exchangeItem(EntityItem entityitem) {
/*  97 */     EntityItem cargo = new EntityItem(this.world, this.posX, this.posY + 0.2D, this.posZ, entityitem.getItem());
/*  98 */     entityitem.setDead();
/*  99 */     if (!this.world.isRemote) {
/* 100 */       this.world.spawnEntity((Entity)cargo);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getIsFlying() {
/* 106 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMyFavoriteFood(ItemStack stack) {
/* 111 */     return (!stack.isEmpty() && MoCTools.isItemEdible(stack.getItem()));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFlyer() {
/* 116 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAIMoveSpeed() {
/* 121 */     if (getHasFood()) {
/* 122 */       return 0.05F;
/*     */     }
/* 124 */     return 0.15F;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ambient\MoCEntityAnt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */