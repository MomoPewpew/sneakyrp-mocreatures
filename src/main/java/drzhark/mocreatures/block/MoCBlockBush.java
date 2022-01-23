package drzhark.mocreatures.block;

import drzhark.mocreatures.MoCreatures;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MoCBlockBush
  extends BlockBush {
  public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

  public MoCBlockBush(String name, Material material) {
    super(material);
    setCreativeTab(MoCreatures.tabMoC);
    setRegistryName("mocreatures", name);
    setUnlocalizedName(name);
    setDefaultState(this.blockState.getBaseState().withProperty((IProperty)VARIANT, EnumType.WYVERN_LAIR));
  }


  public int damageDropped(IBlockState state) {
    return ((EnumType)state.getValue((IProperty)VARIANT)).getMetadata();
  }


  @SideOnly(Side.CLIENT)
  public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
    for (int j = 0; j < (EnumType.values()).length; j++) {
      items.add(new ItemStack((Block)this, 1, j));
    }
  }


  public IBlockState getStateFromMeta(int meta) {
    return getDefaultState().withProperty((IProperty)VARIANT, EnumType.byMetadata(meta));
  }


  public int getMetaFromState(IBlockState state) {
    return ((EnumType)state.getValue((IProperty)VARIANT)).getMetadata();
  }


  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer((Block)this, new IProperty[] { (IProperty)VARIANT });
  }

  public enum EnumType implements IStringSerializable {
    WYVERN_LAIR(0, "wyvern_lair"), OGRE_LAIR(1, "ogre_lair");
    private final String unlocalizedName;
    private static final EnumType[] META_LOOKUP = new EnumType[(values()).length];













    private final String name;












    private final int meta;













    static {
      EnumType[] var0 = values();
      int var1 = var0.length;

      for (int var2 = 0; var2 < var1; var2++) {
        EnumType var3 = var0[var2];
        META_LOOKUP[var3.getMetadata()] = var3;
      }
    }

    EnumType(int meta, String name) {
      this.meta = meta;
      this.name = name;
      this.unlocalizedName = name;
    }

    public int getMetadata() {
      return this.meta;
    }

    public String toString() {
      return this.name;
    }

    public static EnumType byMetadata(int meta) {
      if (meta < 0 || meta >= META_LOOKUP.length)
        meta = 0;
      return META_LOOKUP[meta];
    }

    public String getName() {
      return this.name;
    }

    public String getUnlocalizedName() {
      return this.unlocalizedName;
    }
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\block\MoCBlockBush.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
