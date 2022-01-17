/*    */ package drzhark.mocreatures.entity.monster;
/*    */ 
/*    */ import drzhark.mocreatures.MoCreatures;
/*    */ import drzhark.mocreatures.init.MoCItems;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MoCEntityFireOgre extends MoCEntityOgre {
/*    */   public MoCEntityFireOgre(World world) {
/* 13 */     super(world);
/* 14 */     this.isImmuneToFire = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTexture() {
/* 19 */     return MoCreatures.proxy.getTexture("ogrered.png");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isFireStarter() {
/* 24 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getDestroyForce() {
/* 29 */     return MoCreatures.proxy.fireOgreStrength;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean isHarmedByDaylight() {
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Item getDropItem() {
/* 39 */     boolean flag = (this.rand.nextInt(100) < MoCreatures.proxy.rareItemDropChance);
/* 40 */     if (!flag) {
/* 41 */       return Item.getItemFromBlock((Block)Blocks.FIRE);
/*    */     }
/* 43 */     return (Item)MoCItems.heartfire;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\monster\MoCEntityFireOgre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */