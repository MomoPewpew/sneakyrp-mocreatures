/*    */ package drzhark.mocreatures.entity.passive;
/*    */ 
/*    */ import drzhark.mocreatures.MoCreatures;
/*    */ import drzhark.mocreatures.entity.IMoCTameable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MoCEntityLither
/*    */   extends MoCEntityBigCat {
/*    */   public MoCEntityLither(World world) {
/* 15 */     super(world);
/*    */   }
/*    */ 
/*    */   
/*    */   public void selectType() {
/* 20 */     if (getType() == 0) {
/* 21 */       setType(1);
/*    */     }
/* 23 */     super.selectType();
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTexture() {
/* 28 */     return MoCreatures.proxy.getTexture("bclither.png");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/* 33 */     Boolean tameResult = processTameInteract(player, hand);
/* 34 */     if (tameResult != null) {
/* 35 */       return tameResult.booleanValue();
/*    */     }
/*    */     
/* 38 */     if (getIsRideable() && getIsAdult() && (!getIsChested() || !player.isSneaking()) && !isBeingRidden()) {
/* 39 */       if (!this.world.isRemote && player.startRiding((Entity)this)) {
/* 40 */         player.rotationYaw = this.rotationYaw;
/* 41 */         player.rotationPitch = this.rotationPitch;
/* 42 */         setSitting(false);
/*    */       } 
/*    */       
/* 45 */       return true;
/*    */     } 
/*    */     
/* 48 */     return super.processInteract(player, hand);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getOffspringClazz(IMoCTameable mate) {
/* 53 */     return "Lither";
/*    */   }
/*    */ 
/*    */   
/*    */   public int getOffspringTypeInt(IMoCTameable mate) {
/* 58 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean compatibleMate(Entity mate) {
/* 63 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public float calculateMaxHealth() {
/* 68 */     return 25.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public double calculateAttackDmg() {
/* 73 */     return 6.0D;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getAttackRange() {
/* 78 */     return 8.0D;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxEdad() {
/* 83 */     return 110;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canAttackTarget(EntityLivingBase entity) {
/* 88 */     if (!getIsAdult() && getEdad() < getMaxEdad() * 0.8D) {
/* 89 */       return false;
/*    */     }
/* 91 */     if (entity instanceof MoCEntityLither) {
/* 92 */       return false;
/*    */     }
/* 94 */     return (entity.height < 1.5F && entity.width < 1.5F);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getMoveSpeed() {
/* 99 */     return 1.6F;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityLither.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */