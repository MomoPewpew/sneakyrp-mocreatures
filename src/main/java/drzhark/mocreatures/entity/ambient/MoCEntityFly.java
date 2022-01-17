/*    */ package drzhark.mocreatures.entity.ambient;
/*    */ 
/*    */ import drzhark.mocreatures.MoCTools;
/*    */ import drzhark.mocreatures.entity.MoCEntityInsect;
/*    */ import drzhark.mocreatures.init.MoCSoundEvents;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MoCEntityFly extends MoCEntityInsect {
/*    */   public MoCEntityFly(World world) {
/* 14 */     super(world);
/* 15 */     this.texture = "fly.png";
/*    */   }
/*    */ 
/*    */   
/*    */   private int soundCount;
/*    */   
/*    */   public void onLivingUpdate() {
/* 22 */     super.onLivingUpdate();
/*    */     
/* 24 */     if (!this.world.isRemote) {
/*    */       
/* 26 */       if (getIsFlying() && this.rand.nextInt(200) == 0) {
/* 27 */         setIsFlying(false);
/*    */       }
/* 29 */       if (getIsFlying() && --this.soundCount == -1) {
/* 30 */         EntityPlayer ep = this.world.getClosestPlayerToEntity((Entity)this, 5.0D);
/* 31 */         if (ep != null) {
/* 32 */           MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_FLY_AMBIENT);
/* 33 */           this.soundCount = 55;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isMyFavoriteFood(ItemStack stack) {
/* 41 */     return (!stack.isEmpty() && stack.getItem() == Items.ROTTEN_FLESH);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isFlyer() {
/* 46 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getAIMoveSpeed() {
/* 51 */     if (getIsFlying()) {
/* 52 */       return 0.2F;
/*    */     }
/* 54 */     return 0.12F;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ambient\MoCEntityFly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */