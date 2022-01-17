/*    */ package drzhark.mocreatures.item;
/*    */ import drzhark.mocreatures.MoCTools;
/*    */ import drzhark.mocreatures.MoCreatures;
/*    */ import drzhark.mocreatures.init.MoCSoundEvents;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.item.EnumAction;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.EnumActionResult;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemStaffTeleport extends MoCItem {
/*    */   public ItemStaffTeleport(String name) {
/* 22 */     super(name);
/* 23 */     this.maxStackSize = 1;
/* 24 */     setMaxDamage(128);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isFull3D() {
/* 32 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumAction getItemUseAction(ItemStack par1ItemStack) {
/* 41 */     return EnumAction.BLOCK;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {
/* 50 */     ItemStack stack = player.getHeldItem(hand);
/* 51 */     if (player.getRidingEntity() != null || player.isBeingRidden()) {
/* 52 */       return ActionResult.newResult(EnumActionResult.PASS, stack);
/*    */     }
/*    */     
/* 55 */     double coordY = player.posY + player.getEyeHeight();
/* 56 */     double coordZ = player.posZ;
/* 57 */     double coordX = player.posX;
/* 58 */     for (int x = 4; x < 128; x++) {
/* 59 */       double newPosY = coordY - Math.cos(((player.rotationPitch - 90.0F) / 57.29578F)) * x;
/*    */ 
/*    */       
/* 62 */       double newPosX = coordX + Math.cos((MoCTools.realAngle(player.rotationYaw - 90.0F) / 57.29578F)) * Math.sin(((player.rotationPitch - 90.0F) / 57.29578F)) * x;
/*    */ 
/*    */       
/* 65 */       double newPosZ = coordZ + Math.sin((MoCTools.realAngle(player.rotationYaw - 90.0F) / 57.29578F)) * Math.sin(((player.rotationPitch - 90.0F) / 57.29578F)) * x;
/* 66 */       BlockPos pos = new BlockPos(MathHelper.floor(newPosX), MathHelper.floor(newPosY), MathHelper.floor(newPosZ));
/* 67 */       IBlockState blockstate = player.world.getBlockState(pos);
/* 68 */       if (blockstate.getBlock() != Blocks.AIR) {
/* 69 */         newPosY = coordY - Math.cos(((player.rotationPitch - 90.0F) / 57.29578F)) * (x - 1);
/*    */ 
/*    */         
/* 72 */         newPosX = coordX + Math.cos((MoCTools.realAngle(player.rotationYaw - 90.0F) / 57.29578F)) * Math.sin(((player.rotationPitch - 90.0F) / 57.29578F)) * (x - 1);
/*    */ 
/*    */         
/* 75 */         newPosZ = coordZ + Math.sin((MoCTools.realAngle(player.rotationYaw - 90.0F) / 57.29578F)) * Math.sin(((player.rotationPitch - 90.0F) / 57.29578F)) * (x - 1);
/*    */         
/* 77 */         if (!worldIn.isRemote) {
/* 78 */           EntityPlayerMP playerMP = (EntityPlayerMP)player;
/* 79 */           playerMP.connection.setPlayerLocation(newPosX, newPosY, newPosZ, player.rotationYaw, player.rotationPitch);
/*    */           
/* 81 */           MoCTools.playCustomSound((Entity)player, MoCSoundEvents.ENTITY_GENERIC_MAGIC_APPEAR);
/*    */         } 
/* 83 */         MoCreatures.proxy.teleportFX(player);
/*    */         
/* 85 */         stack.damageItem(1, (EntityLivingBase)player);
/*    */         
/* 87 */         return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 92 */     return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxItemUseDuration(ItemStack stack) {
/* 97 */     return 200;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\ItemStaffTeleport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */