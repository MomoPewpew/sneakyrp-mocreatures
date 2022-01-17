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
/*     */ public class MoCEntityPanther
/*     */   extends MoCEntityBigCat {
/*     */   public MoCEntityPanther(World world) {
/*  18 */     super(world);
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  23 */     switch (getType()) {
/*     */       case 1:
/*  25 */         return MoCreatures.proxy.getTexture("bcpuma.png");
/*     */       case 2:
/*  27 */         return MoCreatures.proxy.getTexture("bcpuma.png");
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  35 */     return MoCreatures.proxy.getTexture("bcpuma.png");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void selectType() {
/*  42 */     if (getType() == 0) {
/*  43 */       setType(1);
/*     */     }
/*  45 */     super.selectType();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFlyer() {
/*  50 */     return (getType() == 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/*  55 */     Boolean tameResult = processTameInteract(player, hand);
/*  56 */     if (tameResult != null) {
/*  57 */       return tameResult.booleanValue();
/*     */     }
/*     */     
/*  60 */     ItemStack stack = player.getHeldItem(hand);
/*  61 */     if (!stack.isEmpty() && getIsTamed() && getType() == 1 && stack.getItem() == MoCItems.essencedarkness) {
/*  62 */       stack.shrink(1);
/*  63 */       if (stack.isEmpty()) {
/*  64 */         player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
/*     */       } else {
/*  66 */         player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
/*     */       } 
/*  68 */       setType(2);
/*  69 */       return true;
/*     */     } 
/*  71 */     if (getIsRideable() && getIsAdult() && (!getIsChested() || !player.isSneaking()) && !isBeingRidden()) {
/*  72 */       if (!this.world.isRemote && player.startRiding((Entity)this)) {
/*  73 */         player.rotationYaw = this.rotationYaw;
/*  74 */         player.rotationPitch = this.rotationPitch;
/*  75 */         setSitting(false);
/*     */       } 
/*     */       
/*  78 */       return true;
/*     */     } 
/*     */     
/*  81 */     return super.processInteract(player, hand);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOffspringClazz(IMoCTameable mate) {
/*  86 */     if (mate instanceof MoCEntityLeopard && ((MoCEntityLeopard)mate).getType() == 1) {
/*  87 */       return "Panthard";
/*     */     }
/*  89 */     if (mate instanceof MoCEntityTiger && ((MoCEntityTiger)mate).getType() == 1) {
/*  90 */       return "Panthger";
/*     */     }
/*  92 */     if (mate instanceof MoCEntityLion && ((MoCEntityLion)mate).getType() == 2) {
/*  93 */       return "Lither";
/*     */     }
/*     */     
/*  96 */     return "Panther";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOffspringTypeInt(IMoCTameable mate) {
/* 102 */     if (mate instanceof MoCEntityLeopard && ((MoCEntityLeopard)mate).getType() == 1) {
/* 103 */       return 1;
/*     */     }
/* 105 */     if (mate instanceof MoCEntityTiger && ((MoCEntityTiger)mate).getType() == 1) {
/* 106 */       return 1;
/*     */     }
/* 108 */     if (mate instanceof MoCEntityLion && ((MoCEntityLion)mate).getType() == 2) {
/* 109 */       return 1;
/*     */     }
/* 111 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean compatibleMate(Entity mate) {
/* 116 */     return ((mate instanceof MoCEntityLeopard && ((MoCEntityLeopard)mate).getType() == 1) || (mate instanceof MoCEntityPanther && ((MoCEntityPanther)mate)
/* 117 */       .getType() == 1) || (mate instanceof MoCEntityTiger && ((MoCEntityTiger)mate)
/* 118 */       .getType() == 1) || (mate instanceof MoCEntityLion && ((MoCEntityLion)mate)
/* 119 */       .getType() == 2));
/*     */   }
/*     */ 
/*     */   
/*     */   public float calculateMaxHealth() {
/* 124 */     return 25.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public double calculateAttackDmg() {
/* 129 */     return 6.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getAttackRange() {
/* 134 */     return 8.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxEdad() {
/* 139 */     if (getType() >= 4) return 110; 
/* 140 */     return 100;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canAttackTarget(EntityLivingBase entity) {
/* 145 */     if (!getIsAdult() && getEdad() < getMaxEdad() * 0.8D) {
/* 146 */       return false;
/*     */     }
/* 148 */     if (entity instanceof MoCEntityPanther) {
/* 149 */       return false;
/*     */     }
/* 151 */     return (entity.height < 1.5F && entity.width < 1.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getMoveSpeed() {
/* 156 */     return 1.6F;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityPanther.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */