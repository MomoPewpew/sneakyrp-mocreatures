/*     */ package drzhark.mocreatures.item;
/*     */ 
/*     */ import com.google.common.collect.Multimap;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.MobEffects;
/*     */ import net.minecraft.inventory.EntityEquipmentSlot;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.EnumActionResult;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ 
/*     */ public class MoCItemWeapon extends MoCItem {
/*     */   private float attackDamage;
/*     */   private final Item.ToolMaterial material;
/*  30 */   private int specialWeaponType = 0;
/*     */   private boolean breakable = false;
/*     */   
/*     */   public MoCItemWeapon(String name, Item.ToolMaterial par2ToolMaterial) {
/*  34 */     super(name);
/*  35 */     this.material = par2ToolMaterial;
/*  36 */     this.maxStackSize = 1;
/*  37 */     setMaxDamage(par2ToolMaterial.getMaxUses());
/*  38 */     this.attackDamage = 4.0F + par2ToolMaterial.getAttackDamage();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MoCItemWeapon(String name, Item.ToolMaterial par2ToolMaterial, int damageType, boolean fragile) {
/*  49 */     this(name, par2ToolMaterial);
/*  50 */     this.specialWeaponType = damageType;
/*  51 */     this.breakable = fragile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getAttackDamage() {
/*  58 */     return this.material.getAttackDamage();
/*     */   }
/*     */   
/*     */   public float getStrVsBlock(ItemStack stack, IBlockState state) {
/*  62 */     if (state.getBlock() == Blocks.WEB) {
/*  63 */       return 15.0F;
/*     */     }
/*  65 */     Material material = state.getMaterial();
/*  66 */     return (material != Material.PLANTS && material != Material.VINE && material != Material.CORAL && material != Material.LEAVES && material != Material.GOURD) ? 1.0F : 1.5F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase target, EntityLivingBase attacker) {
/*  80 */     int i = 1;
/*  81 */     if (this.breakable) {
/*  82 */       i = 10;
/*     */     }
/*  84 */     par1ItemStack.damageItem(i, attacker);
/*  85 */     int potionTime = 100;
/*  86 */     switch (this.specialWeaponType) {
/*     */       case 1:
/*  88 */         target.addPotionEffect(new PotionEffect(MobEffects.POISON, potionTime, 0));
/*     */         break;
/*     */       case 2:
/*  91 */         target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, potionTime, 0));
/*     */         break;
/*     */       case 3:
/*  94 */         target.setFire(10);
/*     */         break;
/*     */       case 4:
/*  97 */         target.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, potionTime, 0));
/*     */         break;
/*     */       case 5:
/* 100 */         target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, potionTime, 0));
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 106 */     return true;
/*     */   }
/*     */   
/*     */   public boolean onBlockDestroyed(ItemStack par1ItemStack, int par2, int par3, int par4, int par5, EntityLiving par6EntityLiving) {
/* 110 */     par1ItemStack.damageItem(2, (EntityLivingBase)par6EntityLiving);
/* 111 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean isFull3D() {
/* 120 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumAction getItemUseAction(ItemStack par1ItemStack) {
/* 129 */     return EnumAction.BLOCK;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
/* 137 */     return 72000;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {
/* 146 */     ItemStack stack = player.getHeldItem(hand);
/* 147 */     player.setActiveHand(hand);
/* 148 */     return new ActionResult(EnumActionResult.SUCCESS, stack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canHarvestBlock(IBlockState state) {
/* 156 */     return (state.getBlock() == Blocks.WEB);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getItemEnchantability() {
/* 165 */     return this.material.getEnchantability();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase playerIn) {
/* 172 */     if (state.getBlockHardness(worldIn, pos) != 0.0D) {
/* 173 */       stack.damageItem(2, playerIn);
/*     */     }
/*     */     
/* 176 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getToolMaterialName() {
/* 183 */     return this.material.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
/* 193 */     ItemStack mat = this.material.getRepairItemStack();
/* 194 */     if (mat != null && OreDictionary.itemMatches(mat, repair, false))
/* 195 */       return true; 
/* 196 */     return super.getIsRepairable(toRepair, repair);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
/* 204 */     Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);
/* 205 */     if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
/* 206 */       multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.attackDamage, 0));
/*     */     }
/*     */     
/* 209 */     return multimap;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\MoCItemWeapon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */