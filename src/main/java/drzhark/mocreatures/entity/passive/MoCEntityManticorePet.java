/*     */ package drzhark.mocreatures.entity.passive;
/*     */ 
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MoCEntityManticorePet
/*     */   extends MoCEntityBigCat {
/*     */   public MoCEntityManticorePet(World world) {
/*  14 */     super(world);
/*  15 */     this.chestName = "ManticoreChest";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void selectType() {
/*  21 */     if (getType() == 0) {
/*  22 */       setType(this.rand.nextInt(4) + 1);
/*     */     }
/*  24 */     super.selectType();
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  29 */     switch (getType()) {
/*     */       case 1:
/*  31 */         return MoCreatures.proxy.getTexture("bcmanticore.png");
/*     */       case 2:
/*  33 */         return MoCreatures.proxy.getTexture("bcmanticoredark.png");
/*     */       case 3:
/*  35 */         return MoCreatures.proxy.getTexture("bcmanticoreblue.png");
/*     */       case 4:
/*  37 */         return MoCreatures.proxy.getTexture("bcmanticoregreen.png");
/*     */     } 
/*  39 */     return MoCreatures.proxy.getTexture("bcmanticore.png");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasMane() {
/*  45 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFlyer() {
/*  50 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/*  55 */     Boolean tameResult = processTameInteract(player, hand);
/*  56 */     if (tameResult != null) {
/*  57 */       return tameResult.booleanValue();
/*     */     }
/*     */     
/*  60 */     if (getIsRideable() && getIsAdult() && (!getIsChested() || !player.isSneaking()) && !isBeingRidden()) {
/*  61 */       if (!this.world.isRemote && player.startRiding((Entity)this)) {
/*  62 */         player.rotationYaw = this.rotationYaw;
/*  63 */         player.rotationPitch = this.rotationPitch;
/*  64 */         setSitting(false);
/*     */       } 
/*     */       
/*  67 */       return true;
/*     */     } 
/*     */     
/*  70 */     return super.processInteract(player, hand);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOffspringClazz(IMoCTameable mate) {
/*  75 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOffspringTypeInt(IMoCTameable mate) {
/*  80 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean compatibleMate(Entity mate) {
/*  85 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean readytoBreed() {
/*  90 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public float calculateMaxHealth() {
/*  95 */     return 40.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public double calculateAttackDmg() {
/* 100 */     return 7.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getAttackRange() {
/* 105 */     return 8.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxEdad() {
/* 110 */     return 130;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getHasStinger() {
/* 115 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasSaberTeeth() {
/* 120 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityManticorePet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */