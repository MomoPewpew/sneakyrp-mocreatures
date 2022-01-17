/*     */ package drzhark.mocreatures.entity.passive;
/*     */ 
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MoCEntityPandaBear
/*     */   extends MoCEntityBear {
/*     */   public MoCEntityPandaBear(World world) {
/*  19 */     super(world);
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectType() {
/*  24 */     if (getType() == 0) {
/*  25 */       setType(1);
/*     */     }
/*  27 */     super.selectType();
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  32 */     return MoCreatures.proxy.getTexture("bearpanda.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public float getBearSize() {
/*  37 */     return 0.8F;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxEdad() {
/*  42 */     return 80;
/*     */   }
/*     */ 
/*     */   
/*     */   public float calculateMaxHealth() {
/*  47 */     return 20.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isReadyToHunt() {
/*  52 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAttackStrength() {
/*  57 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/*  62 */     if (super.attackEntityFrom(damagesource, i)) {
/*  63 */       return true;
/*     */     }
/*  65 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldAttackPlayers() {
/*  71 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMyFavoriteFood(ItemStack stack) {
/*  76 */     return (getType() == 3 && !stack.isEmpty() && stack.getItem() == Items.REEDS);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMyHealFood(ItemStack stack) {
/*  81 */     return (getType() == 3 && !stack.isEmpty() && stack.getItem() == Items.REEDS);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/*  86 */     Boolean tameResult = processTameInteract(player, hand);
/*  87 */     if (tameResult != null) {
/*  88 */       return tameResult.booleanValue();
/*     */     }
/*     */     
/*  91 */     ItemStack stack = player.getHeldItem(hand);
/*  92 */     if (!stack.isEmpty() && (stack.getItem() == MoCItems.sugarlump || stack.getItem() == Items.REEDS)) {
/*  93 */       stack.shrink(1);
/*  94 */       if (stack.isEmpty()) {
/*  95 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/*     */       
/*  98 */       if (!this.world.isRemote) {
/*  99 */         MoCTools.tameWithName(player, (IMoCTameable)this);
/*     */       }
/*     */       
/* 102 */       setHealth(getMaxHealth());
/* 103 */       eatingAnimal();
/* 104 */       if (!this.world.isRemote && !getIsAdult() && getEdad() < 100) {
/* 105 */         setEdad(getEdad() + 1);
/*     */       }
/*     */       
/* 108 */       return true;
/*     */     } 
/* 110 */     if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.whip) {
/* 111 */       if (getBearState() == 0) {
/* 112 */         setBearState(2);
/*     */       } else {
/* 114 */         setBearState(0);
/*     */       } 
/* 116 */       return true;
/*     */     } 
/* 118 */     if (getIsRideable() && getIsAdult() && (!getIsChested() || !player.isSneaking()) && !isBeingRidden()) {
/* 119 */       if (!this.world.isRemote && player.startRiding((Entity)this)) {
/* 120 */         player.rotationYaw = this.rotationYaw;
/* 121 */         player.rotationPitch = this.rotationPitch;
/* 122 */         setBearState(0);
/*     */       } 
/*     */       
/* 125 */       return true;
/*     */     } 
/*     */     
/* 128 */     return super.processInteract(player, hand);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 133 */     super.onLivingUpdate();
/*     */ 
/*     */ 
/*     */     
/* 137 */     if (!this.world.isRemote && !getIsTamed() && this.rand.nextInt(300) == 0) {
/* 138 */       setBearState(2);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOffspringClazz(IMoCTameable mate) {
/* 144 */     return "PandaBear";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOffspringTypeInt(IMoCTameable mate) {
/* 149 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean compatibleMate(Entity mate) {
/* 154 */     return mate instanceof MoCEntityPandaBear;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityPandaBear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */