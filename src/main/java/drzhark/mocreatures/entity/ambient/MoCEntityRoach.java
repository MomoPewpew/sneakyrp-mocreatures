/*    */ package drzhark.mocreatures.entity.ambient;
/*    */ 
/*    */ import com.google.common.base.Predicate;
/*    */ import drzhark.mocreatures.entity.MoCEntityInsect;
/*    */ import drzhark.mocreatures.entity.ai.EntityAIFleeFromEntityMoC;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.ai.EntityAIBase;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MoCEntityRoach
/*    */   extends MoCEntityInsect {
/*    */   public MoCEntityRoach(World world) {
/* 16 */     super(world);
/* 17 */     this.texture = "roach.png";
/*    */   }
/*    */ 
/*    */   
/*    */   protected void initEntityAI() {
/* 22 */     this.tasks.addTask(3, (EntityAIBase)new EntityAIFleeFromEntityMoC((EntityCreature)this, new Predicate<Entity>()
/*    */           {
/*    */             public boolean apply(Entity entity) {
/* 25 */               return (!(entity instanceof MoCEntityCrab) && (entity.height > 0.3F || entity.width > 0.3F));
/*    */             }
/*    */           },  6.0F, 0.8D, 1.3D));
/*    */   }
/*    */ 
/*    */   
/*    */   public void onLivingUpdate() {
/* 32 */     super.onLivingUpdate();
/*    */     
/* 34 */     if (!this.world.isRemote)
/*    */     {
/* 36 */       if (getIsFlying() && this.rand.nextInt(50) == 0) {
/* 37 */         setIsFlying(false);
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean entitiesToInclude(Entity entity) {
/* 51 */     return (!(entity instanceof MoCEntityInsect) && super.entitiesToInclude(entity));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isFlyer() {
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isMyFavoriteFood(ItemStack stack) {
/* 61 */     return (!stack.isEmpty() && stack.getItem() == Items.ROTTEN_FLESH);
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getFlyingFreq() {
/* 66 */     return 500;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getAIMoveSpeed() {
/* 71 */     if (getIsFlying()) {
/* 72 */       return 0.1F;
/*    */     }
/* 74 */     return 0.25F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isNotScared() {
/* 79 */     return getIsFlying();
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ambient\MoCEntityRoach.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */