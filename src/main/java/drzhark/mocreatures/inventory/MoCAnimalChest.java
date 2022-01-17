/*    */ package drzhark.mocreatures.inventory;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.inventory.ContainerChest;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.InventoryBasic;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTBase;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.nbt.NBTTagList;
/*    */ import net.minecraft.world.ILockableContainer;
/*    */ import net.minecraft.world.LockCode;
/*    */ 
/*    */ public class MoCAnimalChest extends InventoryBasic implements ILockableContainer {
/* 16 */   private LockCode lockCode = LockCode.EMPTY_CODE;
/*    */   
/*    */   public MoCAnimalChest(String name, int size) {
/* 19 */     super(name, true, size);
/*    */   }
/*    */ 
/*    */   
/*    */   public void loadInventoryFromNBT(NBTTagList par1NBTTagList) {
/*    */     int var2;
/* 25 */     for (var2 = 0; var2 < getSizeInventory(); var2++) {
/* 26 */       setInventorySlotContents(var2, ItemStack.EMPTY);
/*    */     }
/*    */     
/* 29 */     for (var2 = 0; var2 < par1NBTTagList.tagCount(); var2++) {
/* 30 */       NBTTagCompound var3 = par1NBTTagList.getCompoundTagAt(var2);
/* 31 */       int var4 = var3.getByte("Slot") & 0xFF;
/*    */       
/* 33 */       if (var4 >= 0 && var4 < getSizeInventory()) {
/* 34 */         setInventorySlotContents(var4, new ItemStack(var3));
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public NBTTagList saveInventoryToNBT() {
/* 40 */     NBTTagList var1 = new NBTTagList();
/*    */     
/* 42 */     for (int var2 = 0; var2 < getSizeInventory(); var2++) {
/* 43 */       ItemStack var3 = getStackInSlot(var2);
/*    */       
/* 45 */       if (var3 != null) {
/* 46 */         NBTTagCompound var4 = new NBTTagCompound();
/* 47 */         var4.setByte("Slot", (byte)var2);
/* 48 */         var3.writeToNBT(var4);
/* 49 */         var1.appendTag((NBTBase)var4);
/*    */       } 
/*    */     } 
/*    */     
/* 53 */     return var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
/* 58 */     return (Container)new ContainerChest((IInventory)playerInventory, (IInventory)this, playerIn);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getGuiID() {
/* 63 */     return "";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isLocked() {
/* 68 */     return (this.lockCode != null && !this.lockCode.isEmpty());
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLockCode(LockCode code) {
/* 73 */     this.lockCode = code;
/*    */   }
/*    */ 
/*    */   
/*    */   public LockCode getLockCode() {
/* 78 */     return this.lockCode;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\inventory\MoCAnimalChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */