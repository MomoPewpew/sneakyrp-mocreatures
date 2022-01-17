/*     */ package drzhark.mocreatures.entity.ambient;
/*     */
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.entity.MoCEntityInsect;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.world.World;
/*     */
/*     */
/*     */
/*     */
/*     */ public class MoCEntityBee
/*     */   extends MoCEntityInsect
/*     */ {
/*     */   private int soundCount;
/*     */
/*     */   public MoCEntityBee(World world) {
/*  26 */     super(world);
/*  27 */     this.texture = "bee.png";
/*     */   }
/*     */
/*     */
/*     */   public void onLivingUpdate() {
/*  32 */     super.onLivingUpdate();
/*     */
/*  34 */     if (!this.world.isRemote) {
/*  35 */       if (getIsFlying() && --this.soundCount == -1) {
/*  36 */         EntityPlayer ep = this.world.getClosestPlayerToEntity((Entity)this, 5.0D);
/*  37 */         if (ep != null) {
/*  38 */           MoCTools.playCustomSound((Entity)this, getMySound());
/*  39 */           this.soundCount = 20;
/*     */         }
/*     */       }
/*     */
/*  43 */       if (getIsFlying() && this.rand.nextInt(500) == 0) {
/*  44 */         setIsFlying(false);
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */   private SoundEvent getMySound() {
/*  50 */     if (getAttackTarget() != null) {
/*  51 */       return MoCSoundEvents.ENTITY_BEE_UPSET;
/*     */     }
/*  53 */     return MoCSoundEvents.ENTITY_BEE_AMBIENT;
/*     */   }
/*     */
/*     */
/*     */   public int getTalkInterval() {
/*  58 */     return 2000;
/*     */   }
/*     */
/*     */
/*     */   protected float getSoundVolume() {
/*  63 */     return 0.1F;
/*     */   }
/*     */
/*     */
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/*  68 */     if (super.attackEntityFrom(damagesource, i)) {
/*  69 */       Entity entity = damagesource.getTrueSource();
/*  70 */       if (entity instanceof EntityLivingBase) {
/*  71 */         EntityLivingBase entityliving = (EntityLivingBase)entity;
/*  72 */         if (entity != this && this.world.getDifficulty().getDifficultyId()  > 0) {
/*  73 */           setAttackTarget(entityliving);
/*     */         }
/*  75 */         return true;
/*     */       }
/*  77 */       return false;
/*     */     }
/*  79 */     return false;
/*     */   }
/*     */
/*     */
/*     */
/*     */   public boolean isMyFavoriteFood(ItemStack stack) {
/*  85 */     return (!stack.isEmpty() && (stack
/*  86 */       .getItem() == Item.getItemFromBlock((Block)Blocks.RED_FLOWER) || stack.getItem() ==
/*  87 */       Item.getItemFromBlock((Block)Blocks.YELLOW_FLOWER)));
/*     */   }
/*     */
/*     */
/*     */   public float getAIMoveSpeed() {
/*  92 */     if (getIsFlying()) {
/*  93 */       return 0.15F;
/*     */     }
/*  95 */     return 0.12F;
/*     */   }
/*     */
/*     */
/*     */   public boolean isFlyer() {
/* 100 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ambient\MoCEntityBee.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
