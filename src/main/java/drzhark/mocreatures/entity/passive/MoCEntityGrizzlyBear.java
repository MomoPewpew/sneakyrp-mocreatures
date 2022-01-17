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
/*     */ public class MoCEntityGrizzlyBear
/*     */   extends MoCEntityBear {
/*     */   public MoCEntityGrizzlyBear(World world) {
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
/*  30 */     return MoCreatures.proxy.getTexture("bearbrowm.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public float getBearSize() {
/*  35 */     return 1.2F;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxEdad() {
/*  40 */     return 120;
/*     */   }
/*     */ 
/*     */   
/*     */   public float calculateMaxHealth() {
/*  45 */     return 40.0F;
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
/*  59 */     return 3 * factor;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldAttackPlayers() {
/*  64 */     return (getBrightness() < 0.4F && super.shouldAttackPlayers());
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
/*  81 */       if (!getIsTamed() && !this.world.isRemote) {
/*  82 */         MoCTools.tameWithName(player, (IMoCTameable)this);
/*     */       }
/*     */       
/*  85 */       setHealth(getMaxHealth());
/*  86 */       eatingAnimal();
/*  87 */       if (!this.world.isRemote && !getIsAdult() && getEdad() < 100) {
/*  88 */         setEdad(getEdad() + 1);
/*     */       }
/*  90 */       return true;
/*     */     } 
/*  92 */     if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.whip) {
/*  93 */       if (getBearState() == 0) {
/*  94 */         setBearState(2);
/*     */       } else {
/*  96 */         setBearState(0);
/*     */       } 
/*  98 */       return true;
/*     */     } 
/* 100 */     if (getIsRideable() && getIsAdult() && (!getIsChested() || !player.isSneaking()) && !isBeingRidden()) {
/* 101 */       if (!this.world.isRemote && player.startRiding((Entity)this)) {
/* 102 */         player.rotationYaw = this.rotationYaw;
/* 103 */         player.rotationPitch = this.rotationPitch;
/* 104 */         setBearState(0);
/*     */       } 
/* 106 */       return true;
/*     */     } 
/*     */     
/* 109 */     return super.processInteract(player, hand);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOffspringClazz(IMoCTameable mate) {
/* 114 */     return "GrizzlyBear";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOffspringTypeInt(IMoCTameable mate) {
/* 119 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean compatibleMate(Entity mate) {
/* 124 */     return mate instanceof MoCEntityGrizzlyBear;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityGrizzlyBear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */