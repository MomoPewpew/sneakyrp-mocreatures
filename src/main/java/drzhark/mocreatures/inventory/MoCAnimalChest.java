package drzhark.mocreatures.inventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.LockCode;

public class MoCAnimalChest extends InventoryBasic implements ILockableContainer {
  private LockCode lockCode = LockCode.EMPTY_CODE;

  public MoCAnimalChest(String name, int size) {
    super(name, true, size);
  }


  public void loadInventoryFromNBT(NBTTagList par1NBTTagList) {
    int var2;
    for (var2 = 0; var2 < getSizeInventory(); var2++) {
      setInventorySlotContents(var2, ItemStack.EMPTY);
    }

    for (var2 = 0; var2 < par1NBTTagList.tagCount(); var2++) {
      NBTTagCompound var3 = par1NBTTagList.getCompoundTagAt(var2);
      int var4 = var3.getByte("Slot") & 0xFF;

      if (var4 >= 0 && var4 < getSizeInventory()) {
        setInventorySlotContents(var4, new ItemStack(var3));
      }
    }
  }

  public NBTTagList saveInventoryToNBT() {
    NBTTagList var1 = new NBTTagList();

    for (int var2 = 0; var2 < getSizeInventory(); var2++) {
      ItemStack var3 = getStackInSlot(var2);

      if (var3 != null) {
        NBTTagCompound var4 = new NBTTagCompound();
        var4.setByte("Slot", (byte)var2);
        var3.writeToNBT(var4);
        var1.appendTag((NBTBase)var4);
      }
    }

    return var1;
  }


  public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
    return (Container)new ContainerChest((IInventory)playerInventory, (IInventory)this, playerIn);
  }


  public String getGuiID() {
    return "";
  }


  public boolean isLocked() {
    return (this.lockCode != null && !this.lockCode.isEmpty());
  }


  public void setLockCode(LockCode code) {
    this.lockCode = code;
  }


  public LockCode getLockCode() {
    return this.lockCode;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\inventory\MoCAnimalChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
