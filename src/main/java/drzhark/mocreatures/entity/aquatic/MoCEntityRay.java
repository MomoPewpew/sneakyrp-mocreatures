/*     */ package drzhark.mocreatures.entity.aquatic;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import drzhark.mocreatures.entity.MoCEntityTameableAquatic;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.world.World;
/*     */
/*     */ public class MoCEntityRay extends MoCEntityTameableAquatic {
/*     */   public MoCEntityRay(World world) {
/*  12 */     super(world);
/*     */   }
/*     */
/*     */
/*     */   protected void initEntityAI() {
/*  17 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D, 80));
/*     */   }
/*     */
/*     */   public boolean isPoisoning() {
/*  21 */     return false;
/*     */   }
/*     */
/*     */
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/*  26 */     Boolean tameResult = processTameInteract(player, hand);
/*  27 */     if (tameResult != null) {
/*  28 */       return tameResult.booleanValue();
/*     */     }
/*     */
/*  31 */     if (!isBeingRidden() && getType() == 1) {
/*  32 */       if (!this.world.isRemote && player.startRiding((Entity)this)) {
/*  33 */         player.rotationYaw = this.rotationYaw;
/*  34 */         player.rotationPitch = this.rotationPitch;
/*  35 */         player.posY = this.posY;
/*     */       }
/*     */
/*  38 */       return true;
/*     */     }
/*     */
/*  41 */     return super.processInteract(player, hand);
/*     */   }
/*     */
/*     */
/*     */   public float getAdjustedYOffset() {
/*  46 */     if (!isInWater()) {
/*  47 */       return 0.09F;
/*     */     }
/*  49 */     return 0.15F;
/*     */   }
/*     */
/*     */
/*     */   public int nameYOffset() {
/*  54 */     return -25;
/*     */   }
/*     */
/*     */
/*     */   public boolean canBeTrappedInNet() {
/*  59 */     return true;
/*     */   }
/*     */
/*     */
/*     */   public double getMountedYOffset() {
/*  64 */     return this.height * 0.15D * getSizeFactor();
/*     */   }
/*     */
/*     */
/*     */   public float getSizeFactor() {
/*  69 */     float f = getEdad() * 0.01F;
/*  70 */     if (f > 1.5F) {
/*  71 */       f = 1.5F;
/*     */     }
/*  73 */     return f;
/*     */   }
/*     */
/*     */
/*     */   protected boolean usesNewAI() {
/*  78 */     return true;
/*     */   }
/*     */
/*     */
/*     */   public float getAIMoveSpeed() {
/*  83 */     return 0.06F;
/*     */   }
/*     */
/*     */
/*     */   public boolean isMovementCeased() {
/*  88 */     return !isInWater();
/*     */   }
/*     */
/*     */
/*     */   protected double minDivingDepth() {
/*  93 */     return 3.0D;
/*     */   }
/*     */
/*     */
/*     */   protected double maxDivingDepth() {
/*  98 */     return 6.0D;
/*     */   }
/*     */
/*     */
/*     */   public int getMaxEdad() {
/* 103 */     return 90;
/*     */   }
/*     */
/*     */   public boolean isMantaRay() {
/* 107 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\aquatic\MoCEntityRay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
