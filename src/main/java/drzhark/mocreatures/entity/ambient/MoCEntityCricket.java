/*    */ package drzhark.mocreatures.entity.ambient;
/*    */ 
/*    */ import drzhark.mocreatures.MoCTools;
/*    */ import drzhark.mocreatures.MoCreatures;
/*    */ import drzhark.mocreatures.entity.MoCEntityInsect;
/*    */ import drzhark.mocreatures.init.MoCSoundEvents;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.DimensionManager;
/*    */ 
/*    */ public class MoCEntityCricket
/*    */   extends MoCEntityInsect
/*    */ {
/*    */   private int jumpCounter;
/*    */   private int soundCounter;
/*    */   
/*    */   public MoCEntityCricket(World world) {
/* 20 */     super(world);
/* 21 */     this.texture = "cricketa.png";
/*    */   }
/*    */ 
/*    */   
/*    */   public void selectType() {
/* 26 */     if (getType() == 0) {
/* 27 */       int i = this.rand.nextInt(100);
/* 28 */       if (i <= 50) {
/* 29 */         setType(1);
/*    */       } else {
/* 31 */         setType(2);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTexture() {
/* 38 */     if (getType() == 1) {
/* 39 */       return MoCreatures.proxy.getTexture("cricketa.png");
/*    */     }
/* 41 */     return MoCreatures.proxy.getTexture("cricketb.png");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onLivingUpdate() {
/* 47 */     super.onLivingUpdate();
/* 48 */     if (!this.world.isRemote) {
/* 49 */       if (getIsFlying() && this.rand.nextInt(50) == 0) {
/* 50 */         setIsFlying(false);
/*    */       }
/*    */       
/* 53 */       if (getIsFlying() || !this.onGround) {
/* 54 */         EntityPlayer ep = this.world.getClosestPlayerToEntity((Entity)this, 5.0D);
/* 55 */         if (ep != null && --this.soundCounter == -1) {
/* 56 */           MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_CRICKET_FLY);
/* 57 */           this.soundCounter = 10;
/*    */         } 
/* 59 */       } else if (!DimensionManager.getWorld(0).isDaytime()) {
/* 60 */         EntityPlayer ep = this.world.getClosestPlayerToEntity((Entity)this, 12.0D);
/* 61 */         if (ep != null && --this.soundCounter == -1) {
/* 62 */           MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_CRICKET_AMBIENT);
/* 63 */           this.soundCounter = 20;
/*    */         } 
/*    */       } 
/*    */       
/* 67 */       if (this.jumpCounter > 0 && ++this.jumpCounter > 30) {
/* 68 */         this.jumpCounter = 0;
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 75 */     super.onUpdate();
/* 76 */     if (!this.world.isRemote && 
/* 77 */       this.onGround && (this.motionX > 0.05D || this.motionZ > 0.05D || this.motionX < -0.05D || this.motionZ < -0.05D) && 
/* 78 */       this.jumpCounter == 0 && this.onGround && (this.motionX > 0.05D || this.motionZ > 0.05D || this.motionX < -0.05D || this.motionZ < -0.05D)) {
/*    */       
/* 80 */       this.motionY = 0.45D;
/* 81 */       this.motionX *= 5.0D;
/* 82 */       this.motionZ *= 5.0D;
/* 83 */       this.jumpCounter = 1;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getAIMoveSpeed() {
/* 90 */     if (getIsFlying()) {
/* 91 */       return 0.12F;
/*    */     }
/* 93 */     return 0.15F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isFlyer() {
/* 98 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ambient\MoCEntityCricket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */