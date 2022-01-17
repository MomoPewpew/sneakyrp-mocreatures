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
/*     */ public class ItemBuilderHammer
/*     */   extends MoCItem
/*     */ {
/*     */   public ItemBuilderHammer(String name) {
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
/*     */   public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {
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
/*     */         
/*  79 */         newPosY = coordY - Math.cos(((player.rotationPitch - 90.0F) / 57.29578F)) * (x - 1);
/*     */ 
/*     */         
/*  82 */         newPosX = coordX + Math.cos((MoCTools.realAngle(player.rotationYaw - 90.0F) / 57.29578F)) * Math.sin(((player.rotationPitch - 90.0F) / 57.29578F)) * (x - 1);
/*     */ 
/*     */         
/*  85 */         newPosZ = coordZ + Math.sin((MoCTools.realAngle(player.rotationYaw - 90.0F) / 57.29578F)) * Math.sin(((player.rotationPitch - 90.0F) / 57.29578F)) * (x - 1);
/*  86 */         pos = new BlockPos(MathHelper.floor(newPosX), MathHelper.floor(newPosY), MathHelper.floor(newPosZ));
/*  87 */         if (!player.world.isAirBlock(pos)) {
/*  88 */           return new ActionResult(EnumActionResult.FAIL, stack);
/*     */         }
/*     */         
/*  91 */         int[] blockInfo = obtainBlockAndMetadataFromBelt(player, true);
/*  92 */         if (blockInfo[0] != 0) {
/*  93 */           if (!worldIn.isRemote) {
/*  94 */             Block block = Block.getBlockById(blockInfo[0]);
/*  95 */             player.world.setBlockState(pos, block.getDefaultState(), 3);
/*  96 */             player.world.playSound(player, ((float)newPosX + 0.5F), ((float)newPosY + 0.5F), ((float)newPosZ + 0.5F), block
/*  97 */                 .getSoundType().getPlaceSound(), SoundCategory.BLOCKS, (block.getSoundType().getVolume() + 1.0F) / 2.0F, block.getSoundType().getPitch() * 0.8F);
/*     */           } 
/*  99 */           MoCreatures.proxy.hammerFX(player);
/*     */         } 
/*     */         
/* 102 */         return new ActionResult(EnumActionResult.SUCCESS, stack);
/*     */       } 
/*     */     } 
/* 105 */     return new ActionResult(EnumActionResult.SUCCESS, stack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int[] obtainBlockAndMetadataFromBelt(EntityPlayer entityplayer, boolean remove) {
/* 116 */     for (int y = 0; y < 9; y++) {
/* 117 */       ItemStack slotStack = entityplayer.inventory.getStackInSlot(y);
/* 118 */       if (!slotStack.isEmpty()) {
/*     */ 
/*     */         
/* 121 */         Item itemTemp = slotStack.getItem();
/* 122 */         int metadata = slotStack.getItemDamage();
/* 123 */         if (itemTemp instanceof net.minecraft.item.ItemBlock) {
/* 124 */           if (remove && !entityplayer.capabilities.isCreativeMode) {
/* 125 */             slotStack.shrink(1);
/* 126 */             if (slotStack.isEmpty()) {
/* 127 */               entityplayer.inventory.setInventorySlotContents(y, ItemStack.EMPTY);
/*     */             } else {
/* 129 */               entityplayer.inventory.setInventorySlotContents(y, slotStack);
/*     */             } 
/*     */           } 
/* 132 */           return new int[] { Item.getIdFromItem(itemTemp), metadata };
/*     */         } 
/*     */       } 
/* 135 */     }  return new int[] { 0, 0 };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 141 */     return EnumActionResult.FAIL;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\ItemBuilderHammer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */