/*     */ package drzhark.mocreatures.item;
/*     */ 
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.EnumActionResult;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemOgreHammer
/*     */   extends MoCItem
/*     */ {
/*     */   public ItemOgreHammer(String name) {
/*  25 */     super(name);
/*  26 */     this.maxStackSize = 1;
/*  27 */     setMaxDamage(2048);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFull3D() {
/*  35 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumAction getItemUseAction(ItemStack par1ItemStack) {
/*  44 */     return EnumAction.BLOCK;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
/*  52 */     return 72000;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
/*  61 */     ItemStack stack = player.getHeldItem(hand);
/*  62 */     double coordY = player.posY + player.getEyeHeight();
/*  63 */     double coordZ = player.posZ;
/*  64 */     double coordX = player.posX;
/*     */     
/*  66 */     for (int x = 3; x < 128; x++) {
/*  67 */       double newPosY = coordY - Math.cos(((player.rotationPitch - 90.0F) / 57.29578F)) * x;
/*     */ 
/*     */       
/*  70 */       double newPosX = coordX + Math.cos((MoCTools.realAngle(player.rotationYaw - 90.0F) / 57.29578F)) * Math.sin(((player.rotationPitch - 90.0F) / 57.29578F)) * x;
/*     */ 
/*     */       
/*  73 */       double newPosZ = coordZ + Math.sin((MoCTools.realAngle(player.rotationYaw - 90.0F) / 57.29578F)) * Math.sin(((player.rotationPitch - 90.0F) / 57.29578F)) * x;
/*  74 */       BlockPos pos = new BlockPos(MathHelper.floor(newPosX), MathHelper.floor(newPosY), MathHelper.floor(newPosZ));
/*  75 */       IBlockState blockstate = player.world.getBlockState(pos);
/*     */       
/*  77 */       if (blockstate.getBlock() != Blocks.AIR) {
/*  78 */         newPosY = coordY - Math.cos(((player.rotationPitch - 90.0F) / 57.29578F)) * (x - 1);
/*     */ 
/*     */         
/*  81 */         newPosX = coordX + Math.cos((MoCTools.realAngle(player.rotationYaw - 90.0F) / 57.29578F)) * Math.sin(((player.rotationPitch - 90.0F) / 57.29578F)) * (x - 1);
/*     */ 
/*     */         
/*  84 */         newPosZ = coordZ + Math.sin((MoCTools.realAngle(player.rotationYaw - 90.0F) / 57.29578F)) * Math.sin(((player.rotationPitch - 90.0F) / 57.29578F)) * (x - 1);
/*  85 */         pos = new BlockPos(MathHelper.floor(newPosX), MathHelper.floor(newPosY), MathHelper.floor(newPosZ));
/*  86 */         if (player.world.getBlockState(pos).getBlock() != Blocks.AIR) {
/*  87 */           return new ActionResult(EnumActionResult.PASS, stack);
/*     */         }
/*     */         
/*  90 */         int[] blockInfo = obtainBlockAndMetadataFromBelt(player, true);
/*  91 */         if (blockInfo[0] != 0) {
/*  92 */           if (!world.isRemote) {
/*  93 */             Block block = Block.getBlockById(blockInfo[0]);
/*  94 */             player.world.setBlockState(pos, block.getDefaultState(), 3);
/*  95 */             player.world.playSound(player, ((float)newPosX + 0.5F), ((float)newPosY + 0.5F), ((float)newPosZ + 0.5F), block
/*  96 */                 .getSoundType().getPlaceSound(), SoundCategory.BLOCKS, (block.getSoundType().getVolume() + 1.0F) / 2.0F, block.getSoundType().getPitch() * 0.8F);
/*     */           } 
/*  98 */           MoCreatures.proxy.hammerFX(player);
/*     */         } 
/*     */         
/* 101 */         return new ActionResult(EnumActionResult.SUCCESS, stack);
/*     */       } 
/*     */     } 
/* 104 */     return new ActionResult(EnumActionResult.PASS, stack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int[] obtainBlockAndMetadataFromBelt(EntityPlayer player, boolean remove) {
/* 115 */     for (int y = 0; y < 9; y++) {
/* 116 */       ItemStack slotStack = player.inventory.getStackInSlot(y);
/* 117 */       if (!slotStack.isEmpty()) {
/*     */ 
/*     */         
/* 120 */         Item itemTemp = slotStack.getItem();
/* 121 */         int metadata = slotStack.getItemDamage();
/* 122 */         if (itemTemp instanceof net.minecraft.item.ItemBlock) {
/* 123 */           if (remove && !player.capabilities.isCreativeMode) {
/* 124 */             slotStack.shrink(1);
/* 125 */             if (slotStack.isEmpty()) {
/* 126 */               player.inventory.setInventorySlotContents(y, ItemStack.EMPTY);
/*     */             } else {
/* 128 */               player.inventory.setInventorySlotContents(y, slotStack);
/*     */             } 
/*     */           } 
/* 131 */           return new int[] { Item.getIdFromItem(itemTemp), metadata };
/*     */         } 
/*     */       } 
/* 134 */     }  return new int[] { 0, 0 };
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
/* 139 */     return EnumActionResult.FAIL;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\ItemOgreHammer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */