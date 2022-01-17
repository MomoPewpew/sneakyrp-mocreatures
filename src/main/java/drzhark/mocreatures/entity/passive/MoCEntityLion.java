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
/*     */ public class MoCEntityLion
/*     */   extends MoCEntityBigCat {
/*     */   public MoCEntityLion(World world) {
/*  18 */     super(world);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void selectType() {
/*  24 */     if (getType() == 0) {
/*  25 */       if (this.rand.nextInt(20) == 0) {
/*     */         
/*  27 */         setType(this.rand.nextInt(2) + 6);
/*     */       } else {
/*     */         
/*  30 */         setType(this.rand.nextInt(2) + 1);
/*     */       } 
/*     */     }
/*  33 */     super.selectType();
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  38 */     switch (getType()) {
/*     */       case 1:
/*  40 */         return MoCreatures.proxy.getTexture("bcfemalelion.png");
/*     */       case 2:
/*  42 */         return MoCreatures.proxy.getTexture("bcmalelion.png");
/*     */       case 3:
/*  44 */         return MoCreatures.proxy.getTexture("bcmalelion.png");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 6:
/*  50 */         return MoCreatures.proxy.getTexture("bcwhitelion.png");
/*     */       case 7:
/*  52 */         return MoCreatures.proxy.getTexture("bcwhitelion.png");
/*     */       case 8:
/*  54 */         return MoCreatures.proxy.getTexture("bcwhitelion.png");
/*     */     } 
/*     */ 
/*     */     
/*  58 */     return MoCreatures.proxy.getTexture("bcfemalelion.png");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasMane() {
/*  64 */     return (getIsAdult() && ((getType() >= 2 && getType() < 4) || getType() == 7));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFlyer() {
/*  69 */     return (getType() == 3 || getType() == 5 || getType() == 8);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/*  74 */     Boolean tameResult = processTameInteract(player, hand);
/*  75 */     if (tameResult != null) {
/*  76 */       return tameResult.booleanValue();
/*     */     }
/*     */     
/*  79 */     ItemStack stack = player.getHeldItem(hand);
/*  80 */     if (!stack.isEmpty() && getIsTamed() && (getType() == 2 || getType() == 7) && stack
/*  81 */       .getItem() == MoCItems.essencelight) {
/*  82 */       stack.shrink(1);
/*  83 */       if (stack.isEmpty()) {
/*  84 */         player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
/*     */       } else {
/*  86 */         player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
/*     */       } 
/*  88 */       setType(getType() + 1);
/*  89 */       return true;
/*     */     } 
/*     */     
/*  92 */     if (getIsRideable() && getIsAdult() && (!getIsChested() || !player.isSneaking()) && !isBeingRidden()) {
/*  93 */       if (!this.world.isRemote && player.startRiding((Entity)this)) {
/*  94 */         player.rotationYaw = this.rotationYaw;
/*  95 */         player.rotationPitch = this.rotationPitch;
/*  96 */         setSitting(false);
/*     */       } 
/*     */       
/*  99 */       return true;
/*     */     } 
/*     */     
/* 102 */     return super.processInteract(player, hand);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOffspringClazz(IMoCTameable mate) {
/* 107 */     if (mate instanceof MoCEntityTiger && ((MoCEntityTiger)mate).getType() < 3) {
/* 108 */       return "Liger";
/*     */     }
/* 110 */     if (getType() == 2 && mate instanceof MoCEntityLeopard && ((MoCEntityLeopard)mate).getType() == 1) {
/* 111 */       return "Liard";
/*     */     }
/* 113 */     if (getType() == 2 && mate instanceof MoCEntityPanther && ((MoCEntityPanther)mate).getType() == 1) {
/* 114 */       return "Lither";
/*     */     }
/* 116 */     return "Lion";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOffspringTypeInt(IMoCTameable mate) {
/* 121 */     int x = 0;
/* 122 */     if (mate instanceof MoCEntityTiger && ((MoCEntityTiger)mate).getType() < 3) {
/* 123 */       return 1;
/*     */     }
/* 125 */     if (getType() == 2 && mate instanceof MoCEntityLeopard && ((MoCEntityLeopard)mate).getType() == 1) {
/* 126 */       return 1;
/*     */     }
/* 128 */     if (getType() == 2 && mate instanceof MoCEntityPanther && ((MoCEntityPanther)mate).getType() == 1) {
/* 129 */       return 1;
/*     */     }
/* 131 */     if (mate instanceof MoCEntityLion) {
/* 132 */       int lionMateType = ((MoCEntityLion)mate).getType();
/* 133 */       if (getType() == 1 && lionMateType == 2) {
/* 134 */         x = this.rand.nextInt(2) + 1;
/*     */       }
/* 136 */       if (getType() == 2 && lionMateType == 1) {
/* 137 */         x = this.rand.nextInt(2) + 1;
/*     */       }
/* 139 */       if (getType() == 6 && lionMateType == 7) {
/* 140 */         x = this.rand.nextInt(2) + 6;
/*     */       }
/* 142 */       if (getType() == 7 && lionMateType == 6) {
/* 143 */         x = this.rand.nextInt(2) + 6;
/*     */       }
/* 145 */       if (getType() == 7 && lionMateType == 1) {
/* 146 */         x = this.rand.nextInt(2) + 1;
/*     */       }
/* 148 */       if (getType() == 6 && lionMateType == 2) {
/* 149 */         x = this.rand.nextInt(2) + 1;
/*     */       }
/* 151 */       if (getType() == 1 && lionMateType == 7) {
/* 152 */         x = this.rand.nextInt(2) + 1;
/*     */       }
/* 154 */       if (getType() == 2 && lionMateType == 6) {
/* 155 */         x = this.rand.nextInt(2) + 1;
/*     */       }
/*     */     } 
/* 158 */     return x;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean compatibleMate(Entity mate) {
/* 163 */     if (getType() == 2 && mate instanceof MoCEntityTiger && ((MoCEntityTiger)mate).getType() < 3) {
/* 164 */       return true;
/*     */     }
/* 166 */     if (getType() == 2 && mate instanceof MoCEntityLeopard && ((MoCEntityLeopard)mate).getType() == 1) {
/* 167 */       return true;
/*     */     }
/* 169 */     if (getType() == 2 && mate instanceof MoCEntityPanther && ((MoCEntityPanther)mate).getType() == 1) {
/* 170 */       return true;
/*     */     }
/* 172 */     if (mate instanceof MoCEntityLion) {
/* 173 */       return (getOffspringTypeInt((IMoCTameable)mate) != 0);
/*     */     }
/* 175 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean readytoBreed() {
/* 180 */     return ((getType() < 3 || getType() == 6 || getType() == 7) && super.readytoBreed());
/*     */   }
/*     */ 
/*     */   
/*     */   public float calculateMaxHealth() {
/* 185 */     if (getType() == 2 || getType() == 7) {
/* 186 */       return 35.0F;
/*     */     }
/* 188 */     if (getType() == 4) {
/* 189 */       return 40.0F;
/*     */     }
/* 191 */     return 30.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public double calculateAttackDmg() {
/* 196 */     return 7.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getAttackRange() {
/* 201 */     if (getType() == 1 || getType() == 6) {
/* 202 */       return 12.0D;
/*     */     }
/* 204 */     return 8.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxEdad() {
/* 209 */     if (getType() == 1 || getType() == 6) {
/* 210 */       return 110;
/*     */     }
/* 212 */     if (getType() == 9) {
/* 213 */       return 100;
/*     */     }
/* 215 */     return 120;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canAttackTarget(EntityLivingBase entity) {
/* 220 */     if (!getIsAdult() && getEdad() < getMaxEdad() * 0.8D) {
/* 221 */       return false;
/*     */     }
/* 223 */     if (entity instanceof MoCEntityLion) {
/* 224 */       return false;
/*     */     }
/* 226 */     return (entity.height < 2.0F && entity.width < 2.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityLion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */