/*    */ package drzhark.mocreatures.client.model;
/*    */ 
/*    */ import drzhark.mocreatures.entity.monster.MoCEntityManticore;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ public class MoCModelManticore
/*    */   extends MoCModelNewBigCat
/*    */ {
/*    */   public void updateAnimationModifiers(Entity entity) {
/* 10 */     MoCEntityManticore bigcat = (MoCEntityManticore)entity;
/* 11 */     this.isFlyer = true;
/* 12 */     this.isSaddled = bigcat.getIsRideable();
/* 13 */     this.flapwings = (bigcat.wingFlapCounter != 0);
/* 14 */     this.floating = (this.isFlyer && bigcat.isOnAir());
/* 15 */     this.poisoning = bigcat.swingingTail();
/* 16 */     this.isRidden = bigcat.isBeingRidden();
/* 17 */     this.hasMane = true;
/* 18 */     this.hasSaberTeeth = true;
/* 19 */     this.onAir = bigcat.isOnAir();
/* 20 */     this.hasStinger = true;
/* 21 */     this.isMovingVertically = (bigcat.motionY != 0.0D);
/* 22 */     this.hasChest = false;
/* 23 */     this.isTamed = false;
/* 24 */     this.hasChest = false;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelManticore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */