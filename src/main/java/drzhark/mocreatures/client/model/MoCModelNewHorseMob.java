/*    */ package drzhark.mocreatures.client.model;
/*    */ 
/*    */ import drzhark.mocreatures.entity.monster.MoCEntityHorseMob;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCModelNewHorseMob
/*    */   extends MoCModelNewHorse
/*    */ {
/*    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 13 */     MoCEntityHorseMob entityhorse = (MoCEntityHorseMob)entity;
/*    */     
/* 15 */     int type = entityhorse.getType();
/* 16 */     boolean wings = entityhorse.isFlyer();
/* 17 */     boolean eating = (entityhorse.eatingCounter != 0);
/* 18 */     boolean standing = (entityhorse.standCounter != 0 && entityhorse.getRidingEntity() == null);
/* 19 */     boolean openMouth = (entityhorse.mouthCounter != 0);
/* 20 */     boolean moveTail = (entityhorse.tailCounter != 0);
/* 21 */     boolean flapwings = (entityhorse.wingFlapCounter != 0);
/* 22 */     boolean rider = entityhorse.isBeingRidden();
/* 23 */     boolean floating = (entityhorse.isFlyer() && entityhorse.isOnAir());
/*    */     
/* 25 */     setRotationAngles(f, f1, f2, f3, f4, f5, eating, rider, floating, standing, false, moveTail, wings, flapwings, false, 0);
/* 26 */     this.Ear1.render(f5);
/* 27 */     this.Ear2.render(f5);
/* 28 */     this.Neck.render(f5);
/* 29 */     this.Head.render(f5);
/* 30 */     if (openMouth) {
/* 31 */       this.UMouth2.render(f5);
/* 32 */       this.LMouth2.render(f5);
/*    */     } else {
/* 34 */       this.UMouth.render(f5);
/* 35 */       this.LMouth.render(f5);
/*    */     } 
/* 37 */     this.Mane.render(f5);
/* 38 */     this.Body.render(f5);
/* 39 */     this.TailA.render(f5);
/* 40 */     this.TailB.render(f5);
/* 41 */     this.TailC.render(f5);
/*    */     
/* 43 */     this.Leg1A.render(f5);
/* 44 */     this.Leg1B.render(f5);
/* 45 */     this.Leg1C.render(f5);
/*    */     
/* 47 */     this.Leg2A.render(f5);
/* 48 */     this.Leg2B.render(f5);
/* 49 */     this.Leg2C.render(f5);
/*    */     
/* 51 */     this.Leg3A.render(f5);
/* 52 */     this.Leg3B.render(f5);
/* 53 */     this.Leg3C.render(f5);
/*    */     
/* 55 */     this.Leg4A.render(f5);
/* 56 */     this.Leg4B.render(f5);
/* 57 */     this.Leg4C.render(f5);
/*    */     
/* 59 */     if (entityhorse.isUnicorned()) {
/* 60 */       this.Unicorn.render(f5);
/*    */     }
/* 62 */     if (entityhorse.isFlyer() && type != 34 && type != 36) {
/*    */       
/* 64 */       this.MidWing.render(f5);
/* 65 */       this.InnerWing.render(f5);
/* 66 */       this.OuterWing.render(f5);
/* 67 */       this.InnerWingR.render(f5);
/* 68 */       this.MidWingR.render(f5);
/* 69 */       this.OuterWingR.render(f5);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelNewHorseMob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */