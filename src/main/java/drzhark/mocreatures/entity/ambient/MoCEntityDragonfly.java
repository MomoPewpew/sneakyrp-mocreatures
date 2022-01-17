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
/*    */ 
/*    */ public class MoCEntityDragonfly extends MoCEntityInsect {
/*    */   private int soundCount;
/*    */   
/*    */   public MoCEntityDragonfly(World world) {
/* 16 */     super(world);
/* 17 */     this.texture = "dragonflya.png";
/*    */   }
/*    */ 
/*    */   
/*    */   public void selectType() {
/* 22 */     if (getType() == 0) {
/* 23 */       setType(this.rand.nextInt(4) + 1);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTexture() {
/* 29 */     switch (getType()) {
/*    */       case 1:
/* 31 */         return MoCreatures.proxy.getTexture("dragonflya.png");
/*    */       case 2:
/* 33 */         return MoCreatures.proxy.getTexture("dragonflyb.png");
/*    */       case 3:
/* 35 */         return MoCreatures.proxy.getTexture("dragonflyc.png");
/*    */       case 4:
/* 37 */         return MoCreatures.proxy.getTexture("dragonflyd.png");
/*    */     } 
/*    */     
/* 40 */     return MoCreatures.proxy.getTexture("dragonflyd.png");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onLivingUpdate() {
/* 46 */     super.onLivingUpdate();
/*    */     
/* 48 */     if (!this.world.isRemote) {
/* 49 */       EntityPlayer ep = this.world.getClosestPlayerToEntity((Entity)this, 5.0D);
/* 50 */       if (ep != null && getIsFlying() && --this.soundCount == -1) {
/* 51 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_DRAGONFLY_AMBIENT);
/* 52 */         this.soundCount = 20;
/*    */       } 
/*    */       
/* 55 */       if (getIsFlying() && this.rand.nextInt(200) == 0) {
/* 56 */         setIsFlying(false);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isFlyer() {
/* 63 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getAIMoveSpeed() {
/* 68 */     if (getIsFlying()) {
/* 69 */       return 0.25F;
/*    */     }
/* 71 */     return 0.12F;
/*    */   }
/*    */   
/*    */   public int maxFlyingHeight() {
/* 75 */     return 4;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ambient\MoCEntityDragonfly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */