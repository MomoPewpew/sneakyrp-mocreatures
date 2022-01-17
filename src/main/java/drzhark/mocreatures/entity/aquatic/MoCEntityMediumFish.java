/*     */ package drzhark.mocreatures.entity.aquatic;
/*     */ import com.google.common.base.Predicate;
/*     */ import drzhark.mocreatures.entity.MoCEntityTameableAquatic;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFleeFromEntityMoC;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */
/*     */ public class MoCEntityMediumFish extends MoCEntityTameableAquatic {
/*  19 */   public static final String[] fishNames = new String[] { "Salmon", "Cod", "Bass" };
/*     */
/*     */   public MoCEntityMediumFish(World world) {
/*  22 */     super(world);
/*  23 */     setSize(0.6F, 0.3F);
/*  24 */     setEdad(30 + this.rand.nextInt(70));
/*     */   }
/*     */
/*     */   public static MoCEntityMediumFish createEntity(World world, int type) {
/*  28 */     if (type == 1) {
/*  29 */       return new MoCEntitySalmon(world);
/*     */     }
/*  31 */     if (type == 2) {
/*  32 */       return new MoCEntityCod(world);
/*     */     }
/*  34 */     if (type == 3) {
/*  35 */       return new MoCEntityBass(world);
/*     */     }
/*     */
/*  38 */     return new MoCEntitySalmon(world);
/*     */   }
/*     */
/*     */
/*     */   protected void initEntityAI() {
/*  43 */     this.tasks.addTask(3, (EntityAIBase)new EntityAIFleeFromEntityMoC((EntityCreature)this, new Predicate<Entity>()
/*     */           {
/*     */             public boolean apply(Entity entity) {
/*  46 */               return (entity.height > 0.6F && entity.width > 0.3F);
/*     */             }
/*     */           },  2.0F, 0.6D, 1.5D));
/*  49 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D, 50));
/*     */   }
/*     */
/*     */
/*     */   protected void applyEntityAttributes() {
/*  54 */     super.applyEntityAttributes();
/*  55 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
/*  56 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
/*     */   }
/*     */
/*     */
/*     */   public void selectType() {
/*  61 */     if (getType() == 0) {
/*  62 */       setType(this.rand.nextInt(fishNames.length) + 1);
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   protected void dropFewItems(boolean flag, int x) {
/*  68 */     int i = this.rand.nextInt(100);
/*  69 */     if (i < 70) {
/*  70 */       entityDropItem(new ItemStack(Items.FISH, 1, 0), 0.0F);
/*     */     } else {
/*  72 */       int j = this.rand.nextInt(2);
/*  73 */       for (int k = 0; k < j; k++) {
/*  74 */         entityDropItem(new ItemStack((Item)MoCItems.mocegg, 1, getEggNumber()), 0.0F);
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */   protected int getEggNumber() {
/*  80 */     return 70;
/*     */   }
/*     */
/*     */
/*     */   public void onLivingUpdate() {
/*  85 */     super.onLivingUpdate();
/*     */
/*  87 */     if (!this.world.isRemote &&
/*  88 */       getIsTamed() && this.rand.nextInt(100) == 0 && getHealth() < getMaxHealth()) {
/*  89 */       setHealth(getMaxHealth());
/*     */     }
/*     */
/*  92 */     if (!isInsideOfMaterial(Material.WATER)) {
/*  93 */       this.prevRenderYawOffset = this.renderYawOffset = this.rotationYaw = this.prevRotationYaw;
/*  94 */       this.rotationPitch = this.prevRotationPitch;
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   public float getSizeFactor() {
/* 100 */     return getEdad() * 0.01F;
/*     */   }
/*     */
/*     */
/*     */   public float getAdjustedYOffset() {
/* 105 */     if (!isInsideOfMaterial(Material.WATER)) {
/* 106 */       return 1.0F;
/*     */     }
/* 108 */     return 0.5F;
/*     */   }
/*     */
/*     */
/*     */   protected boolean isFisheable() {
/* 113 */     return !getIsTamed();
/*     */   }
/*     */
/*     */
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float yawRotationOffset() {
/* 119 */     if (!isInsideOfMaterial(Material.WATER)) {
/* 120 */       return 90.0F;
/*     */     }
/* 122 */     return 90.0F + super.yawRotationOffset();
/*     */   }
/*     */
/*     */
/*     */   public float rollRotationOffset() {
/* 127 */     if (!isInWater() && this.onGround) {
/* 128 */       return -90.0F;
/*     */     }
/* 130 */     return 0.0F;
/*     */   }
/*     */
/*     */
/*     */   public int nameYOffset() {
/* 135 */     return -30;
/*     */   }
/*     */
/*     */
/*     */   public float getAdjustedZOffset() {
/* 140 */     if (!isInWater()) {
/* 141 */       return 0.2F;
/*     */     }
/* 143 */     return 0.0F;
/*     */   }
/*     */
/*     */
/*     */   public float getAdjustedXOffset() {
/* 148 */     return 0.0F;
/*     */   }
/*     */
/*     */
/*     */   protected boolean canBeTrappedInNet() {
/* 153 */     return true;
/*     */   }
/*     */
/*     */
/*     */   protected boolean usesNewAI() {
/* 158 */     return true;
/*     */   }
/*     */
/*     */
/*     */   public float getAIMoveSpeed() {
/* 163 */     return 0.15F;
/*     */   }
/*     */
/*     */
/*     */   public boolean isMovementCeased() {
/* 168 */     return !isInWater();
/*     */   }
/*     */
/*     */
/*     */   protected double minDivingDepth() {
/* 173 */     return 0.5D;
/*     */   }
/*     */
/*     */
/*     */   protected double maxDivingDepth() {
/* 178 */     return 4.0D;
/*     */   }
/*     */
/*     */
/*     */   public int getMaxEdad() {
/* 183 */     return 120;
/*     */   }
/*     */
/*     */
/*     */   public boolean isNotScared() {
/* 188 */     return getIsTamed();
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\aquatic\MoCEntityMediumFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
