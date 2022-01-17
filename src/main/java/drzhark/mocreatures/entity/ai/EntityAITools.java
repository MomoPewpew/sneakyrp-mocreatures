/*    */ package drzhark.mocreatures.entity.ai;
/*    */ 
/*    */ import drzhark.mocreatures.entity.IMoCTameable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ public class EntityAITools {
/*    */   protected static boolean IsNearPlayer(EntityLiving entityliving, double d) {
/* 10 */     EntityPlayer entityplayer1 = entityliving.world.getClosestPlayerToEntity((Entity)entityliving, d);
/* 11 */     if (entityplayer1 != null) {
/* 12 */       return true;
/*    */     }
/* 14 */     return false;
/*    */   }
/*    */   
/*    */   protected static EntityPlayer getIMoCTameableOwner(IMoCTameable pet) {
/* 18 */     if (pet.getOwnerId() == null) {
/* 19 */       return null;
/*    */     }
/*    */     
/* 22 */     for (int i = 0; i < ((EntityLiving)pet).world.playerEntities.size(); i++) {
/* 23 */       EntityPlayer entityplayer = ((EntityLiving)pet).world.playerEntities.get(i);
/*    */       
/* 25 */       if (pet.getOwnerId().equals(entityplayer.getUniqueID())) {
/* 26 */         return entityplayer;
/*    */       }
/*    */     } 
/* 29 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ai\EntityAITools.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */