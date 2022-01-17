/*     */ package drzhark.mocreatures.entity.ambient;
/*     */ 
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.MoCEntityInsect;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MoCEntityButterfly extends MoCEntityInsect {
/*     */   public MoCEntityButterfly(World world) {
/*  15 */     super(world);
/*     */   }
/*     */ 
/*     */   
/*     */   private int fCounter;
/*     */   
/*     */   public void onLivingUpdate() {
/*  22 */     super.onLivingUpdate();
/*     */     
/*  24 */     if (!this.world.isRemote && 
/*  25 */       getIsFlying() && this.rand.nextInt(200) == 0) {
/*  26 */       setIsFlying(false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void selectType() {
/*  33 */     if (getType() == 0) {
/*  34 */       setType(this.rand.nextInt(10) + 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  40 */     switch (getType()) {
/*     */       case 1:
/*  42 */         return MoCreatures.proxy.getTexture("bfagalaisurticae.png");
/*     */       case 2:
/*  44 */         return MoCreatures.proxy.getTexture("bfargyreushyperbius.png");
/*     */       case 3:
/*  46 */         return MoCreatures.proxy.getTexture("bfathymanefte.png");
/*     */       case 4:
/*  48 */         return MoCreatures.proxy.getTexture("bfcatopsiliapomona.png");
/*     */       case 5:
/*  50 */         return MoCreatures.proxy.getTexture("bfmorphopeleides.png");
/*     */       case 6:
/*  52 */         return MoCreatures.proxy.getTexture("bfvanessaatalanta.png");
/*     */       case 7:
/*  54 */         return MoCreatures.proxy.getTexture("bfpierisrapae.png");
/*     */       case 8:
/*  56 */         return MoCreatures.proxy.getTexture("mothcamptogrammabilineata.png");
/*     */       case 9:
/*  58 */         return MoCreatures.proxy.getTexture("mothidiaaemula.png");
/*     */       case 10:
/*  60 */         return MoCreatures.proxy.getTexture("moththyatirabatis.png");
/*     */     } 
/*  62 */     return MoCreatures.proxy.getTexture("bfpierisrapae.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public float tFloat() {
/*  67 */     if (!getIsFlying()) {
/*  68 */       return 0.0F;
/*     */     }
/*  70 */     if (++this.fCounter > 1000) {
/*  71 */       this.fCounter = 0;
/*     */     }
/*     */     
/*  74 */     return MathHelper.cos(this.fCounter * 0.1F) * 0.2F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getSizeFactor() {
/*  79 */     if (getType() < 8) {
/*  80 */       return 0.7F;
/*     */     }
/*  82 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMyFavoriteFood(ItemStack stack) {
/*  87 */     return (!stack.isEmpty() && (stack
/*  88 */       .getItem() == Item.getItemFromBlock((Block)Blocks.RED_FLOWER) || stack.getItem() == 
/*  89 */       Item.getItemFromBlock((Block)Blocks.YELLOW_FLOWER)));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAttractedToLight() {
/*  94 */     return (getType() > 7);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFlyer() {
/*  99 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAIMoveSpeed() {
/* 104 */     if (getIsFlying()) {
/* 105 */       return 0.13F;
/*     */     }
/* 107 */     return 0.1F;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ambient\MoCEntityButterfly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */