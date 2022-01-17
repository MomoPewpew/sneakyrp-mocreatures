/*    */ package drzhark.mocreatures.entity.monster;
/*    */ 
/*    */ import drzhark.mocreatures.MoCreatures;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MoCEntityGreenOgre
/*    */   extends MoCEntityOgre {
/*    */   public MoCEntityGreenOgre(World world) {
/* 12 */     super(world);
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTexture() {
/* 17 */     return MoCreatures.proxy.getTexture("ogregreen.png");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getDestroyForce() {
/* 26 */     return MoCreatures.proxy.ogreStrength;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Item getDropItem() {
/* 31 */     return Item.getItemFromBlock(Blocks.OBSIDIAN);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\monster\MoCEntityGreenOgre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */