/*    */ package drzhark.mocreatures.block;
/*    */ 
/*    */ import drzhark.mocreatures.init.MoCBlocks;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ 
/*    */ public class MoCBlockLog
/*    */   extends MoCBlock
/*    */ {
/*    */   public MoCBlockLog(String name) {
/* 16 */     super(name, Material.WOOD);
/* 17 */     setSoundType(SoundType.WOOD);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int quantityDropped(Random par1Random) {
/* 25 */     return 1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Item getItemDropped(int par1, Random par2Random, int par3) {
/* 32 */     return Item.getItemFromBlock(MoCBlocks.mocLog);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isWood(IBlockAccess world, BlockPos pos) {
/* 42 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\block\MoCBlockLog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */