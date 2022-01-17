/*    */ package drzhark.mocreatures.item;
/*    */ 
/*    */ import drzhark.mocreatures.entity.passive.MoCEntityHorse;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumHand;
/*    */ 
/*    */ public class MoCItemHorseSaddle
/*    */   extends MoCItem {
/*    */   public MoCItemHorseSaddle(String name) {
/* 12 */     super(name);
/* 13 */     this.maxStackSize = 32;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
/* 18 */     if (target instanceof MoCEntityHorse) {
/* 19 */       MoCEntityHorse entityhorse = (MoCEntityHorse)target;
/* 20 */       if (!entityhorse.getIsRideable() && entityhorse.getIsAdult()) {
/* 21 */         entityhorse.setRideable(true);
/* 22 */         stack.shrink(1);
/* 23 */         return true;
/*    */       } 
/*    */     } 
/* 26 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\MoCItemHorseSaddle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */