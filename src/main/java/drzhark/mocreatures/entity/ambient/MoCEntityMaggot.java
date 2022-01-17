/*    */ package drzhark.mocreatures.entity.ambient;
/*    */ import drzhark.mocreatures.entity.MoCEntityAmbient;
/*    */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.EntityAIBase;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MoCEntityMaggot extends MoCEntityAmbient {
/*    */   public MoCEntityMaggot(World world) {
/* 13 */     super(world);
/* 14 */     setSize(0.2F, 0.2F);
/* 15 */     this.texture = "maggot.png";
/*    */   }
/*    */ 
/*    */   
/*    */   protected void initEntityAI() {
/* 20 */     this.tasks.addTask(1, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 0.8D));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void applyEntityAttributes() {
/* 25 */     super.applyEntityAttributes();
/* 26 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
/* 27 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void fall(float f, float f1) {}
/*    */ 
/*    */   
/*    */   protected Item getDropItem() {
/* 36 */     return Items.SLIME_BALL;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isOnLadder() {
/* 41 */     return this.collidedHorizontally;
/*    */   }
/*    */   
/*    */   public boolean climbing() {
/* 45 */     return (!this.onGround && isOnLadder());
/*    */   }
/*    */   
/*    */   public void jump() {}
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ambient\MoCEntityMaggot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */