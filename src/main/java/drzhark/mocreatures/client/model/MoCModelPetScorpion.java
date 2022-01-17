/*    */ package drzhark.mocreatures.client.model;
/*    */ 
/*    */ import drzhark.mocreatures.entity.passive.MoCEntityPetScorpion;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCModelPetScorpion
/*    */   extends MoCModelScorpion
/*    */ {
/*    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 13 */     MoCEntityPetScorpion scorpy = (MoCEntityPetScorpion)entity;
/* 14 */     this.poisoning = scorpy.swingingTail();
/* 15 */     this.isTalking = (scorpy.mouthCounter != 0);
/* 16 */     this.babies = scorpy.getHasBabies();
/* 17 */     this.attacking = scorpy.armCounter;
/* 18 */     this.sitting = scorpy.getIsSitting();
/* 19 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/* 20 */     renderParts(f5);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelPetScorpion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */