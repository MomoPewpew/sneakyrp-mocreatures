/*    */ package drzhark.mocreatures.block;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.item.ItemMultiTexture;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class MultiItemBlock
/*    */   extends ItemMultiTexture {
/*    */   public MultiItemBlock(Block block) {
/* 10 */     super(block, block, new ItemMultiTexture.Mapper()
/*    */         {
/*    */           public String apply(ItemStack stack) {
/* 13 */             return MoCBlock.EnumType.byMetadata(stack.getMetadata()).getUnlocalizedName();
/*    */           }
/*    */         });
/* 16 */     setHasSubtypes(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMetadata(int damageValue) {
/* 21 */     return damageValue;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\block\MultiItemBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */