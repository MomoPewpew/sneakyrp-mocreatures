/*    */ package drzhark.mocreatures.item;
/*    */ 
/*    */ import drzhark.mocreatures.entity.aquatic.MoCEntityFishy;
/*    */ import drzhark.mocreatures.entity.aquatic.MoCEntityMediumFish;
/*    */ import drzhark.mocreatures.entity.aquatic.MoCEntitySmallFish;
/*    */ import drzhark.mocreatures.entity.item.MoCEntityEgg;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.EnumActionResult;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.util.NonNullList;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ public class MoCItemEgg extends MoCItem {
/*    */   public MoCItemEgg(String name) {
/* 21 */     super(name);
/* 22 */     this.maxStackSize = 16;
/* 23 */     setHasSubtypes(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
/* 28 */     ItemStack stack = player.getHeldItem(hand);
/* 29 */     stack.shrink(1);
/* 30 */     if (!world.isRemote && player.onGround) {
/* 31 */       int i = stack.getItemDamage();
/* 32 */       if (i == 30) {
/* 33 */         i = 31;
/*    */       }
/* 35 */       MoCEntityEgg entityegg = new MoCEntityEgg(world, i);
/* 36 */       entityegg.setPosition(player.posX, player.posY, player.posZ);
/* 37 */       player.world.spawnEntity((Entity)entityegg);
/* 38 */       entityegg.motionY += (world.rand.nextFloat() * 0.05F);
/* 39 */       entityegg.motionX += ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F);
/* 40 */       entityegg.motionZ += ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F);
/*    */     } 
/* 42 */     return new ActionResult(EnumActionResult.SUCCESS, stack);
/*    */   }
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
/* 48 */     if (!isInCreativeTab(tab)) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 53 */     int length = MoCEntityFishy.fishNames.length; int i;
/* 54 */     for (i = 0; i < length; i++) {
/* 55 */       items.add(new ItemStack(this, 1, i));
/*    */     }
/* 57 */     length = 80 + MoCEntitySmallFish.fishNames.length;
/* 58 */     for (i = 80; i < length; i++) {
/* 59 */       items.add(new ItemStack(this, 1, i));
/*    */     }
/* 61 */     length = 70 + MoCEntityMediumFish.fishNames.length;
/* 62 */     for (i = 70; i < length; i++) {
/* 63 */       items.add(new ItemStack(this, 1, i));
/*    */     }
/* 65 */     items.add(new ItemStack(this, 1, 90));
/* 66 */     items.add(new ItemStack(this, 1, 11));
/* 67 */     for (i = 21; i < 28; i++) {
/* 68 */       items.add(new ItemStack(this, 1, i));
/*    */     }
/* 70 */     items.add(new ItemStack(this, 1, 30));
/* 71 */     items.add(new ItemStack(this, 1, 31));
/* 72 */     items.add(new ItemStack(this, 1, 33));
/* 73 */     for (i = 41; i < 46; i++) {
/* 74 */       items.add(new ItemStack(this, 1, i));
/*    */     }
/* 76 */     for (i = 50; i < 65; i++) {
/* 77 */       items.add(new ItemStack(this, 1, i));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTranslationKey(ItemStack itemstack) {
/* 83 */     return getTranslationKey() + "." + itemstack.getItemDamage();
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\MoCItemEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */