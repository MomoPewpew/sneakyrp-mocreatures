/*     */ package drzhark.mocreatures.entity.passive;
/*     */ 
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MoCEntityBlackBear
/*     */   extends MoCEntityBear {
/*     */   public MoCEntityBlackBear(World world) {
/*  17 */     super(world);
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectType() {
/*  22 */     if (getType() == 0) {
/*  23 */       setType(1);
/*     */     }
/*  25 */     super.selectType();
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  30 */     return MoCreatures.proxy.getTexture("bearblack.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public float getBearSize() {
/*  35 */     return 0.9F;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxEdad() {
/*  40 */     return 90;
/*     */   }
/*     */ 
/*     */   
/*     */   public float calculateMaxHealth() {
/*  45 */     return 30.0F;
/*     */   }
/*     */   
/*     */   public double getAttackRange() {
/*  49 */     int factor = 1;
/*  50 */     if (this.world.getDifficulty().getId() > 1) {
/*  51 */       factor = 2;
/*     */     }
/*  53 */     return 6.0D * factor;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAttackStrength() {
/*  58 */     int factor = this.world.getDifficulty().getId();
/*  59 */     return 2 * factor;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldAttackPlayers() {
/*  64 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/*  69 */     Boolean tameResult = processTameInteract(player, hand);
/*  70 */     if (tameResult != null) {
/*  71 */       return tameResult.booleanValue();
/*     */     }
/*     */     
/*  74 */     ItemStack stack = player.getHeldItem(hand);
/*  75 */     if (!stack.isEmpty() && getEdad() < 80 && MoCTools.isItemEdibleforCarnivores(stack.getItem())) {
/*  76 */       stack.shrink(1);
/*  77 */       if (stack.isEmpty()) {
/*  78 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/*     */       
/*  81 */       if (!this.world.isRemote && !getIsTamed()) {
/*  82 */         MoCTools.tameWithName(player, (IMoCTameable)this);
/*     */       }
/*     */       
/*  85 */       setHealth(getMaxHealth());
/*  86 */       eatingAnimal();
/*  87 */       if (!this.world.isRemote && !getIsAdult() && getEdad() < 100) {
/*  88 */         setEdad(getEdad() + 1);
/*     */       }
/*     */       
/*  91 */       return true;
/*     */     } 
/*  93 */     if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.whip) {
/*  94 */       if (getBearState() == 0) {
/*  95 */         setBearState(2);
/*     */       } else {
/*  97 */         setBearState(0);
/*     */       } 
/*  99 */       return true;
/*     */     } 
/* 101 */     if (getIsRideable() && getIsAdult() && (!getIsChested() || !player.isSneaking()) && !isBeingRidden()) {
/* 102 */       if (player.startRiding((Entity)this)) {
/* 103 */         player.rotationYaw = this.rotationYaw;
/* 104 */         player.rotationPitch = this.rotationPitch;
/* 105 */         setBearState(0);
/*     */       } 
/*     */       
/* 108 */       return true;
/*     */     } 
/*     */     
/* 111 */     return super.processInteract(player, hand);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOffspringClazz(IMoCTameable mate) {
/* 116 */     return "BlackBear";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOffspringTypeInt(IMoCTameable mate) {
/* 121 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean compatibleMate(Entity mate) {
/* 126 */     return mate instanceof MoCEntityPandaBear;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityBlackBear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */