/*     */ package drzhark.mocreatures.entity.aquatic;
/*     */ import com.google.common.base.Predicate;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.MoCEntityTameableAquatic;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFleeFromEntityMoC;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIPanicMoC;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */
/*     */ public class MoCEntitySmallFish extends MoCEntityTameableAquatic {
/*  21 */   public static final String[] fishNames = new String[] { "Anchovy", "Angelfish", "Angler", "Clownfish", "Goldfish", "Hippotang", "Manderin" };
/*     */
/*     */   public MoCEntitySmallFish(World world) {
/*  24 */     super(world);
/*  25 */     setSize(0.3F, 0.3F);
/*  26 */     setEdad(70 + this.rand.nextInt(30));
/*     */   }
/*     */
/*     */   public static MoCEntitySmallFish createEntity(World world, int type) {
/*  30 */     if (type == 1) {
/*  31 */       return new MoCEntityAnchovy(world);
/*     */     }
/*  33 */     if (type == 2) {
/*  34 */       return new MoCEntityAngelFish(world);
/*     */     }
/*  36 */     if (type == 3) {
/*  37 */       return new MoCEntityAngler(world);
/*     */     }
/*  39 */     if (type == 4) {
/*  40 */       return new MoCEntityClownFish(world);
/*     */     }
/*  42 */     if (type == 5) {
/*  43 */       return new MoCEntityGoldFish(world);
/*     */     }
/*  45 */     if (type == 6) {
/*  46 */       return new MoCEntityHippoTang(world);
/*     */     }
/*  48 */     if (type == 7) {
/*  49 */       return new MoCEntityManderin(world);
/*     */     }
/*     */
/*  52 */     return new MoCEntityClownFish(world);
/*     */   }
/*     */
/*     */
/*     */   protected void initEntityAI() {
/*  57 */     this.tasks.addTask(1, (EntityAIBase)new EntityAIPanicMoC((EntityCreature)this, 1.3D));
/*  58 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIFleeFromEntityMoC((EntityCreature)this, new Predicate<Entity>()
/*     */           {
/*     */             public boolean apply(Entity entity) {
/*  61 */               return (entity.height > 0.3F || entity.width > 0.3F);
/*     */             }
/*     */           },  2.0F, 0.6D, 1.5D));
/*  64 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D, 80));
/*     */   }
/*     */
/*     */
/*     */   protected void applyEntityAttributes() {
/*  69 */     super.applyEntityAttributes();
/*  70 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
/*  71 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
/*     */   }
/*     */
/*     */
/*     */   public void selectType() {
/*  76 */     if (getType() == 0) {
/*  77 */       setType(this.rand.nextInt(fishNames.length) + 1);
/*     */     }
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */   public ResourceLocation getTexture() {
/*  85 */     switch (getType()) {
/*     */       case 1:
/*  87 */         return MoCreatures.proxy.getTexture("smallfish_anchovy.png");
/*     */       case 2:
/*  89 */         return MoCreatures.proxy.getTexture("smallfish_angelfish.png");
/*     */       case 3:
/*  91 */         return MoCreatures.proxy.getTexture("smallfish_angler.png");
/*     */       case 4:
/*  93 */         return MoCreatures.proxy.getTexture("smallfish_clownfish.png");
/*     */       case 5:
/*  95 */         return MoCreatures.proxy.getTexture("smallfish_goldfish.png");
/*     */       case 6:
/*  97 */         return MoCreatures.proxy.getTexture("smallfish_hippotang.png");
/*     */       case 7:
/*  99 */         return MoCreatures.proxy.getTexture("smallfish_manderin.png");
/*     */     }
/* 101 */     return MoCreatures.proxy.getTexture("smallfish_clownfish.png");
/*     */   }
/*     */
/*     */
/*     */
/*     */   protected boolean canBeTrappedInNet() {
/* 107 */     return true;
/*     */   }
/*     */
/*     */
/*     */   protected void dropFewItems(boolean flag, int x) {
/* 112 */     int i = this.rand.nextInt(100);
/* 113 */     if (i < 70) {
/* 114 */       entityDropItem(new ItemStack(Items.FISH, 1, 0), 0.0F);
/*     */     } else {
/* 116 */       int j = this.rand.nextInt(2);
/* 117 */       for (int k = 0; k < j; k++) {
/* 118 */         entityDropItem(new ItemStack((Item)MoCItems.mocegg, 1, getEggNumber()), 0.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   public void onLivingUpdate() {
/* 125 */     super.onLivingUpdate();
/*     */
/* 127 */     if (!this.world.isRemote)
/*     */     {
/* 129 */       if (getIsTamed() && this.rand.nextInt(100) == 0 && getHealth() < getMaxHealth()) {
/* 130 */         setHealth(getMaxHealth());
/*     */       }
/*     */     }
/* 133 */     if (!isInWater()) {
/* 134 */       this.prevRenderYawOffset = this.renderYawOffset = this.rotationYaw = this.prevRotationYaw;
/* 135 */       this.rotationPitch = this.prevRotationPitch;
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   public float getSizeFactor() {
/* 141 */     return getEdad() * 0.01F;
/*     */   }
/*     */
/*     */
/*     */   public float getAdjustedYOffset() {
/* 146 */     if (!isInWater()) {
/* 147 */       return 0.5F;
/*     */     }
/* 149 */     return 0.3F;
/*     */   }
/*     */
/*     */
/*     */   protected boolean isFisheable() {
/* 154 */     return !getIsTamed();
/*     */   }
/*     */
/*     */
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float yawRotationOffset() {
/* 160 */     if (!isInWater()) {
/* 161 */       return 90.0F;
/*     */     }
/* 163 */     return 90.0F + super.yawRotationOffset();
/*     */   }
/*     */
/*     */
/*     */   public float rollRotationOffset() {
/* 168 */     if (!isInWater()) {
/* 169 */       return -90.0F;
/*     */     }
/* 171 */     return 0.0F;
/*     */   }
/*     */
/*     */
/*     */   public int nameYOffset() {
/* 176 */     return -25;
/*     */   }
/*     */
/*     */
/*     */   public float getAdjustedXOffset() {
/* 181 */     return 0.0F;
/*     */   }
/*     */
/*     */
/*     */   protected boolean usesNewAI() {
/* 186 */     return true;
/*     */   }
/*     */
/*     */
/*     */   public float getAIMoveSpeed() {
/* 191 */     return 0.1F;
/*     */   }
/*     */
/*     */
/*     */   public boolean isMovementCeased() {
/* 196 */     return !isInWater();
/*     */   }
/*     */
/*     */
/*     */   protected double minDivingDepth() {
/* 201 */     return 0.2D;
/*     */   }
/*     */
/*     */
/*     */   protected double maxDivingDepth() {
/* 206 */     return 2.0D;
/*     */   }
/*     */
/*     */
/*     */   public int getMaxEdad() {
/* 211 */     return 120;
/*     */   }
/*     */
/*     */
/*     */   public boolean isNotScared() {
/* 216 */     return getIsTamed();
/*     */   }
/*     */
/*     */
/*     */   public float getAdjustedZOffset() {
/* 221 */     if (!isInWater()) {
/* 222 */       return 0.1F;
/*     */     }
/* 224 */     return 0.0F;
/*     */   }
/*     */
/*     */   protected int getEggNumber() {
/* 228 */     return 80;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\aquatic\MoCEntitySmallFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
