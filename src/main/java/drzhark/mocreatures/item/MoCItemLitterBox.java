/*    */ package drzhark.mocreatures.item;
/*    */ 
/*    */ import drzhark.mocreatures.entity.item.MoCEntityLitterBox;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.EnumActionResult;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MoCItemLitterBox extends MoCItem {
/*    */   public MoCItemLitterBox(String name) {
/* 14 */     super(name);
/* 15 */     this.maxStackSize = 16;
/*    */   }
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
/* 20 */     ItemStack stack = player.getHeldItem(hand);
/* 21 */     if (!world.isRemote) {
/* 22 */       stack.shrink(1);
/* 23 */       MoCEntityLitterBox entitylitterbox = new MoCEntityLitterBox(world);
/* 24 */       entitylitterbox.setPosition(player.posX, player.posY, player.posZ);
/* 25 */       player.world.spawnEntity((Entity)entitylitterbox);
/* 26 */       entitylitterbox.motionY += (world.rand.nextFloat() * 0.05F);
/* 27 */       entitylitterbox.motionX += ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F);
/* 28 */       entitylitterbox.motionZ += ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F);
/*    */     } 
/* 30 */     return new ActionResult(EnumActionResult.SUCCESS, stack);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\MoCItemLitterBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */