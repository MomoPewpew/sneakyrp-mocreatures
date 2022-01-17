/*    */ package drzhark.mocreatures.item;
/*    */ 
/*    */ import drzhark.mocreatures.entity.item.MoCEntityKittyBed;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.EnumActionResult;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MoCItemKittyBed extends MoCItem {
/*    */   private int sheetType;
/*    */   
/*    */   public MoCItemKittyBed(String name) {
/* 16 */     super(name);
/*    */   }
/*    */   
/*    */   public MoCItemKittyBed(String name, int type) {
/* 20 */     this(name);
/* 21 */     this.sheetType = type;
/*    */   }
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
/* 26 */     ItemStack stack = player.getHeldItem(hand);
/* 27 */     if (!world.isRemote) {
/* 28 */       MoCEntityKittyBed entitykittybed = new MoCEntityKittyBed(world, this.sheetType);
/* 29 */       entitykittybed.setPosition(player.posX, player.posY, player.posZ);
/* 30 */       world.spawnEntity((Entity)entitykittybed);
/* 31 */       entitykittybed.motionY += (world.rand.nextFloat() * 0.05F);
/* 32 */       entitykittybed.motionX += ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F);
/* 33 */       entitykittybed.motionZ += ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F);
/* 34 */       stack.shrink(1);
/* 35 */       if (stack.isEmpty()) {
/* 36 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*    */       }
/*    */     } 
/* 39 */     return new ActionResult(EnumActionResult.SUCCESS, stack);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\MoCItemKittyBed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */