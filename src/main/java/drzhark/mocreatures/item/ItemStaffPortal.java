/*     */ package drzhark.mocreatures.item;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.dimension.MoCDirectTeleporter;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Enchantments;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.EnumActionResult;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TextComponentTranslation;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.world.Teleporter;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemStaffPortal extends MoCItem {
/*     */   public ItemStaffPortal(String name) {
/*  26 */     super(name);
/*  27 */     this.maxStackSize = 1;
/*  28 */     setMaxDamage(3);
/*     */   }
/*     */ 
/*     */   
/*     */   private int portalPosX;
/*     */   
/*     */   private int portalPosY;
/*     */   private int portalPosZ;
/*     */   private int portalDimension;
/*     */   
/*     */   public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  39 */     ItemStack stack = player.getHeldItem(hand);
/*  40 */     if (worldIn.isRemote) {
/*  41 */       return EnumActionResult.FAIL;
/*     */     }
/*  43 */     boolean hasMending = (EnchantmentHelper.getEnchantmentLevel(Enchantments.MENDING, stack) > 0);
/*  44 */     boolean hasUnbreaking = (EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, stack) > 0);
/*  45 */     if (hasMending || hasUnbreaking) {
/*  46 */       String enchantments = "unbreaking";
/*  47 */       if (hasMending && hasUnbreaking) {
/*  48 */         enchantments = "mending, unbreaking";
/*  49 */       } else if (hasMending) {
/*  50 */         enchantments = "mending";
/*     */       } 
/*  52 */       player.sendMessage((ITextComponent)new TextComponentTranslation(MoCreatures.MOC_LOGO + TextFormatting.RED + " Detected illegal enchantment(s) '" + TextFormatting.GREEN + enchantments + TextFormatting.RED + "' on Staff Portal!\nThe item has been removed from your inventory.", new Object[0]));
/*     */ 
/*     */       
/*  55 */       player.inventory.deleteStack(stack);
/*  56 */       return EnumActionResult.SUCCESS;
/*     */     } 
/*     */     
/*  59 */     if (stack.getTagCompound() == null) {
/*  60 */       stack.setTagCompound(new NBTTagCompound());
/*     */     }
/*     */     
/*  63 */     NBTTagCompound nbtcompound = stack.getTagCompound();
/*     */     
/*  65 */     EntityPlayerMP playerMP = (EntityPlayerMP)player;
/*  66 */     if (player.getRidingEntity() != null || player.isBeingRidden()) {
/*  67 */       return EnumActionResult.FAIL;
/*     */     }
/*  69 */     if (player.dimension != MoCreatures.WyvernLairDimensionID) {
/*  70 */       this.portalDimension = player.dimension;
/*  71 */       this.portalPosX = (int)player.posX;
/*  72 */       this.portalPosY = (int)player.posY;
/*  73 */       this.portalPosZ = (int)player.posZ;
/*  74 */       writeToNBT(nbtcompound);
/*     */       
/*  76 */       BlockPos var2 = playerMP.server.getWorld(MoCreatures.WyvernLairDimensionID).getSpawnCoordinate();
/*     */       
/*  78 */       if (var2 != null) {
/*  79 */         playerMP.connection.setPlayerLocation(var2.getX(), var2.getY(), var2.getZ(), 0.0F, 0.0F);
/*     */       }
/*  81 */       playerMP.server.getPlayerList().transferPlayerToDimension(playerMP, MoCreatures.WyvernLairDimensionID, (Teleporter)new MoCDirectTeleporter(playerMP.server
/*  82 */             .getWorld(MoCreatures.WyvernLairDimensionID)));
/*  83 */       stack.damageItem(1, (EntityLivingBase)player);
/*  84 */       return EnumActionResult.SUCCESS;
/*     */     } 
/*     */     
/*  87 */     if (player.posX > 1.5D || player.posX < -1.5D || player.posZ > 2.5D || player.posZ < -2.5D) {
/*  88 */       return EnumActionResult.FAIL;
/*     */     }
/*  90 */     readFromNBT(nbtcompound);
/*     */     
/*  92 */     boolean foundSpawn = false;
/*  93 */     if (this.portalPosX == 0 && this.portalPosY == 0 && this.portalPosZ == 0) {
/*     */       
/*  95 */       BlockPos var2 = playerMP.server.getWorld(0).getSpawnPoint();
/*     */       
/*  97 */       if (var2 != null) {
/*  98 */         for (int i1 = 0; i1 < 60; i1++) {
/*  99 */           IBlockState blockstate = playerMP.server.getWorld(0).getBlockState(pos.add(0, i1, 0));
/* 100 */           IBlockState blockstate1 = playerMP.server.getWorld(0).getBlockState(pos.add(0, i1 + 1, 0));
/*     */           
/* 102 */           if (blockstate.getBlock() == Blocks.AIR && blockstate1.getBlock() == Blocks.AIR) {
/* 103 */             playerMP.connection.setPlayerLocation(var2.getX(), var2.getY() + i1 + 1.0D, var2.getZ(), 0.0F, 0.0F);
/*     */             
/* 105 */             if (MoCreatures.proxy.debug) {
/* 106 */               System.out.println("MoC Staff teleporter found location at spawn");
/*     */             }
/* 108 */             foundSpawn = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 113 */         if (!foundSpawn) {
/* 114 */           if (MoCreatures.proxy.debug) {
/* 115 */             System.out.println("MoC Staff teleporter couldn't find an adequate teleport location at spawn");
/*     */           }
/* 117 */           return EnumActionResult.FAIL;
/*     */         } 
/*     */       } else {
/* 120 */         if (MoCreatures.proxy.debug) {
/* 121 */           System.out.println("MoC Staff teleporter couldn't find spawn point");
/*     */         }
/* 123 */         return EnumActionResult.FAIL;
/*     */       } 
/*     */     } else {
/* 126 */       playerMP.connection.setPlayerLocation(this.portalPosX, this.portalPosY + 1.0D, this.portalPosZ, 0.0F, 0.0F);
/*     */     } 
/*     */     
/* 129 */     stack.damageItem(1, (EntityLivingBase)player);
/* 130 */     playerMP.server.getPlayerList().transferPlayerToDimension(playerMP, this.portalDimension, (Teleporter)new MoCDirectTeleporter(playerMP.server
/* 131 */           .getWorld(0)));
/* 132 */     return EnumActionResult.SUCCESS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFull3D() {
/* 142 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumAction getItemUseAction(ItemStack par1ItemStack) {
/* 151 */     return EnumAction.BLOCK;
/*     */   }
/*     */   
/*     */   public void readFromNBT(NBTTagCompound nbt) {
/* 155 */     this.portalPosX = nbt.getInteger("portalPosX");
/* 156 */     this.portalPosY = nbt.getInteger("portalPosY");
/* 157 */     this.portalPosZ = nbt.getInteger("portalPosZ");
/* 158 */     this.portalDimension = nbt.getInteger("portalDimension");
/*     */   }
/*     */   
/*     */   public void writeToNBT(NBTTagCompound nbt) {
/* 162 */     nbt.setInteger("portalPosX", this.portalPosX);
/* 163 */     nbt.setInteger("portalPosY", this.portalPosY);
/* 164 */     nbt.setInteger("portalPosZ", this.portalPosZ);
/* 165 */     nbt.setInteger("portalDimension", this.portalDimension);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
/* 170 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\ItemStaffPortal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */