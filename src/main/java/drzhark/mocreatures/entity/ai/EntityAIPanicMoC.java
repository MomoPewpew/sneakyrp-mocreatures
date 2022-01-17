/*    */ package drzhark.mocreatures.entity.ai;
/*    */ 
/*    */ import drzhark.mocreatures.entity.IMoCEntity;
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.ai.EntityAIPanic;
/*    */ 
/*    */ public class EntityAIPanicMoC
/*    */   extends EntityAIPanic {
/*    */   private EntityCreature entityCreature;
/*    */   
/*    */   public EntityAIPanicMoC(EntityCreature creature, double speedIn) {
/* 12 */     super(creature, speedIn);
/* 13 */     this.entityCreature = creature;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 21 */     if (this.entityCreature instanceof IMoCEntity && ((IMoCEntity)this.entityCreature).isNotScared()) {
/* 22 */       return false;
/*    */     }
/* 24 */     return super.shouldExecute();
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ai\EntityAIPanicMoC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */