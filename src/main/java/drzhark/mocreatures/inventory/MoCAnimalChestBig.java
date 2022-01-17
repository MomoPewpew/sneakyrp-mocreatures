/*    */ package drzhark.mocreatures.inventory;
/*    */ 
/*    */ import net.minecraft.inventory.InventoryLargeChest;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTBase;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.nbt.NBTTagList;
/*    */ import net.minecraft.world.ILockableContainer;
/*    */ 
/*    */ public class MoCAnimalChestBig extends InventoryLargeChest {
/*    */   private final int mySize;
/*    */   
/*    */   public MoCAnimalChestBig(String name, ILockableContainer p_i45905_2_, ILockableContainer p_i45905_3_, int size) {
/* 14 */     super(name, p_i45905_2_, p_i45905_3_);
/* 15 */     this.mySize = size;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSizeInventory() {
/* 20 */     return this.mySize;
/*    */   }
/*    */ 
/*    */   
/*    */   public void loadInventoryFromNBT(NBTTagList par1NBTTagList) {
/*    */     int var2;
/* 26 */     for (var2 = 0; var2 < getSizeInventory(); var2++) {
/* 27 */       setInventorySlotContents(var2, (ItemStack)null);
/*    */     }
/*    */     
/* 30 */     for (var2 = 0; var2 < par1NBTTagList.tagCount(); var2++) {
/* 31 */       NBTTagCompound var3 = par1NBTTagList.getCompoundTagAt(var2);
/* 32 */       int var4 = var3.getByte("Slot") & 0xFF;
/*    */       
/* 34 */       if (var4 >= 0 && var4 < getSizeInventory()) {
/* 35 */         setInventorySlotContents(var4, new ItemStack(var3));
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public NBTTagList saveInventoryToNBT() {
/* 41 */     NBTTagList var1 = new NBTTagList();
/*    */     
/* 43 */     for (int var2 = 0; var2 < getSizeInventory(); var2++) {
/* 44 */       ItemStack var3 = getStackInSlot(var2);
/*    */       
/* 46 */       if (var3 != null) {
/* 47 */         NBTTagCompound var4 = new NBTTagCompound();
/* 48 */         var4.setByte("Slot", (byte)var2);
/* 49 */         var3.writeToNBT(var4);
/* 50 */         var1.appendTag((NBTBase)var4);
/*    */       } 
/*    */     } 
/*    */     
/* 54 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\inventory\MoCAnimalChestBig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */