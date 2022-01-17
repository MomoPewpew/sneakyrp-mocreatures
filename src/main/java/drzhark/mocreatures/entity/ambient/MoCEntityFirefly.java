/*    */ package drzhark.mocreatures.entity.ambient;
/*    */ 
/*    */ import drzhark.mocreatures.MoCTools;
/*    */ import drzhark.mocreatures.entity.MoCEntityInsect;
/*    */ import drzhark.mocreatures.init.MoCSoundEvents;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MoCEntityFirefly extends MoCEntityInsect {
/*    */   private int soundCount;
/*    */   
/*    */   public MoCEntityFirefly(World world) {
/* 14 */     super(world);
/* 15 */     this.texture = "firefly.png";
/*    */   }
/*    */ 
/*    */   
/*    */   public void onLivingUpdate() {
/* 20 */     super.onLivingUpdate();
/*    */     
/* 22 */     if (!this.world.isRemote) {
/* 23 */       EntityPlayer ep = this.world.getClosestPlayerToEntity((Entity)this, 5.0D);
/* 24 */       if (ep != null && getIsFlying() && --this.soundCount == -1) {
/* 25 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_CRICKET_FLY);
/* 26 */         this.soundCount = 20;
/*    */       } 
/*    */       
/* 29 */       if (getIsFlying() && this.rand.nextInt(500) == 0) {
/* 30 */         setIsFlying(false);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isFlyer() {
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getAIMoveSpeed() {
/* 42 */     if (getIsFlying()) {
/* 43 */       return 0.12F;
/*    */     }
/* 45 */     return 0.1F;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ambient\MoCEntityFirefly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */