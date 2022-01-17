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
/*     */ public class MoCEntityTiger
/*     */   extends MoCEntityBigCat {
/*     */   public MoCEntityTiger(World world) {
/*  18 */     super(world);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void selectType() {
/*  24 */     if (getType() == 0) {
/*  25 */       if (this.rand.nextInt(20) == 0) {
/*  26 */         setType(2);
/*     */       } else {
/*  28 */         setType(1);
/*     */       } 
/*     */     }
/*  31 */     super.selectType();
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  36 */     switch (getType()) {
/*     */       case 1:
/*  38 */         return MoCreatures.proxy.getTexture("bctiger.png");
/*     */       case 2:
/*  40 */         return MoCreatures.proxy.getTexture("bcwhitetiger.png");
/*     */       case 3:
/*  42 */         return MoCreatures.proxy.getTexture("bcwhitetiger.png");
/*     */     } 
/*     */ 
/*     */     
/*  46 */     return MoCreatures.proxy.getTexture("bctiger.png");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFlyer() {
/*  52 */     return (getType() == 3);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/*  57 */     Boolean tameResult = processTameInteract(player, hand);
/*  58 */     if (tameResult != null) {
/*  59 */       return tameResult.booleanValue();
/*     */     }
/*     */     
/*  62 */     ItemStack stack = player.getHeldItem(hand);
/*  63 */     if (!stack.isEmpty() && getIsTamed() && getType() == 2 && stack.getItem() == MoCItems.essencelight) {
/*  64 */       stack.shrink(1);
/*  65 */       if (stack.isEmpty()) {
/*  66 */         player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
/*     */       } else {
/*  68 */         player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
/*     */       } 
/*  70 */       setType(3);
/*  71 */       return true;
/*     */     } 
/*  73 */     if (getIsRideable() && getIsAdult() && (!getIsChested() || !player.isSneaking()) && !isBeingRidden()) {
/*  74 */       if (!this.world.isRemote && player.startRiding((Entity)this)) {
/*  75 */         player.rotationYaw = this.rotationYaw;
/*  76 */         player.rotationPitch = this.rotationPitch;
/*  77 */         setSitting(false);
/*     */       } 
/*     */       
/*  80 */       return true;
/*     */     } 
/*     */     
/*  83 */     return super.processInteract(player, hand);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOffspringClazz(IMoCTameable mate) {
/*  88 */     if (mate instanceof MoCEntityLion && ((MoCEntityLion)mate).getType() == 2) {
/*  89 */       return "Liger";
/*     */     }
/*  91 */     if (mate instanceof MoCEntityPanther && ((MoCEntityPanther)mate).getType() == 1) {
/*  92 */       return "Panthger";
/*     */     }
/*  94 */     if (mate instanceof MoCEntityLeopard && ((MoCEntityPanther)mate).getType() == 1) {
/*  95 */       return "Leoger";
/*     */     }
/*  97 */     return "Tiger";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOffspringTypeInt(IMoCTameable mate) {
/* 102 */     if (mate instanceof MoCEntityLion && ((MoCEntityLion)mate).getType() == 2) {
/* 103 */       return 1;
/*     */     }
/* 105 */     if (mate instanceof MoCEntityLeopard && ((MoCEntityLeopard)mate).getType() == 1) {
/* 106 */       return 1;
/*     */     }
/* 108 */     if (mate instanceof MoCEntityPanther && ((MoCEntityPanther)mate).getType() == 1) {
/* 109 */       return 1;
/*     */     }
/* 111 */     return getType();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean compatibleMate(Entity mate) {
/* 116 */     return ((mate instanceof MoCEntityTiger && ((MoCEntityTiger)mate).getType() < 3) || (mate instanceof MoCEntityLion && ((MoCEntityLion)mate)
/* 117 */       .getType() == 2) || (mate instanceof MoCEntityLeopard && ((MoCEntityLeopard)mate)
/* 118 */       .getType() == 1) || (mate instanceof MoCEntityPanther && ((MoCEntityPanther)mate)
/* 119 */       .getType() == 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean readytoBreed() {
/* 124 */     return (getType() < 3 && super.readytoBreed());
/*     */   }
/*     */ 
/*     */   
/*     */   public float calculateMaxHealth() {
/* 129 */     if (getType() == 2) {
/* 130 */       return 40.0F;
/*     */     }
/* 132 */     return 35.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public double calculateAttackDmg() {
/* 137 */     if (getType() == 2) {
/* 138 */       return 8.0D;
/*     */     }
/* 140 */     return 7.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getAttackRange() {
/* 145 */     return 8.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxEdad() {
/* 150 */     if (getType() > 2) {
/* 151 */       return 130;
/*     */     }
/* 153 */     return 120;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canAttackTarget(EntityLivingBase entity) {
/* 158 */     if (!getIsAdult() && getEdad() < getMaxEdad() * 0.8D) {
/* 159 */       return false;
/*     */     }
/* 161 */     if (entity instanceof MoCEntityTiger) {
/* 162 */       return false;
/*     */     }
/* 164 */     return (entity.height < 2.0F && entity.width < 2.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getMoveSpeed() {
/* 169 */     return 1.5F;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityTiger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */