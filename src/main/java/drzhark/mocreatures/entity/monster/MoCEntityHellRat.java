/*    */ package drzhark.mocreatures.entity.monster;
/*    */ 
/*    */ import drzhark.mocreatures.MoCreatures;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MoCEntityHellRat extends MoCEntityRat {
/*    */   private int textCounter;
/*    */   
/*    */   public MoCEntityHellRat(World world) {
/* 16 */     super(world);
/* 17 */     setSize(0.7F, 0.7F);
/* 18 */     this.isImmuneToFire = true;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void applyEntityAttributes() {
/* 23 */     super.applyEntityAttributes();
/* 24 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public void selectType() {
/* 29 */     setType(4);
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTexture() {
/* 34 */     if (this.rand.nextInt(2) == 0) {
/* 35 */       this.textCounter++;
/*    */     }
/* 37 */     if (this.textCounter < 10) {
/* 38 */       this.textCounter = 10;
/*    */     }
/* 40 */     if (this.textCounter > 29) {
/* 41 */       this.textCounter = 10;
/*    */     }
/* 43 */     String textNumber = "" + this.textCounter;
/* 44 */     textNumber = textNumber.substring(0, 1);
/* 45 */     return MoCreatures.proxy.getTexture("hellrat" + textNumber + ".png");
/*    */   }
/*    */ 
/*    */   
/*    */   protected Item getDropItem() {
/* 50 */     boolean flag = (this.rand.nextInt(100) < MoCreatures.proxy.rareItemDropChance);
/* 51 */     if (flag) {
/* 52 */       return Item.getItemFromBlock((Block)Blocks.FIRE);
/*    */     }
/* 54 */     return Items.REDSTONE;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\monster\MoCEntityHellRat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */