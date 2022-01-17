/*     */ package drzhark.mocreatures.entity.passive;
/*     */ 
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MoCEntityLiger
/*     */   extends MoCEntityBigCat {
/*     */   public MoCEntityLiger(World world) {
/*  18 */     super(world);
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectType() {
/*  23 */     if (getType() == 0) {
/*  24 */       setType(1);
/*     */     }
/*  26 */     super.selectType();
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  31 */     return MoCreatures.proxy.getTexture("bcliger.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/*  36 */     Boolean tameResult = processTameInteract(player, hand);
/*  37 */     if (tameResult != null) {
/*  38 */       return tameResult.booleanValue();
/*     */     }
/*     */     
/*  41 */     ItemStack stack = player.getHeldItem(hand);
/*  42 */     if (!stack.isEmpty() && getIsTamed() && getType() == 1 && stack.getItem() == MoCItems.essencelight) {
/*  43 */       stack.shrink(1);
/*  44 */       if (stack.isEmpty()) {
/*  45 */         player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
/*     */       } else {
/*  47 */         player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
/*     */       } 
/*  49 */       setType(getType() + 1);
/*  50 */       return true;
/*     */     } 
/*     */     
/*  53 */     if (getIsRideable() && getIsAdult() && (!getIsChested() || !player.isSneaking()) && !isBeingRidden()) {
/*  54 */       if (!this.world.isRemote && player.startRiding((Entity)this)) {
/*  55 */         player.rotationYaw = this.rotationYaw;
/*  56 */         player.rotationPitch = this.rotationPitch;
/*  57 */         setSitting(false);
/*     */       } 
/*     */       
/*  60 */       return true;
/*     */     } 
/*     */     
/*  63 */     return super.processInteract(player, hand);
/*     */   }
/*     */   
/*     */   public String getOffspringClazz(IMoCTameable mate) {
/*  67 */     return "Liger";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOffspringTypeInt(IMoCTameable mate) {
/*  72 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean compatibleMate(Entity mate) {
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public float calculateMaxHealth() {
/*  82 */     return 35.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public double calculateAttackDmg() {
/*  87 */     return 8.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getAttackRange() {
/*  92 */     return 10.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxEdad() {
/*  97 */     return 135;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canAttackTarget(EntityLivingBase entity) {
/* 102 */     if (!getIsAdult() && getEdad() < getMaxEdad() * 0.8D) {
/* 103 */       return false;
/*     */     }
/* 105 */     if (entity instanceof MoCEntityLiger) {
/* 106 */       return false;
/*     */     }
/* 108 */     return (entity.height < 2.0F && entity.width < 2.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFlyer() {
/* 113 */     return (getType() == 2);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityLiger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */