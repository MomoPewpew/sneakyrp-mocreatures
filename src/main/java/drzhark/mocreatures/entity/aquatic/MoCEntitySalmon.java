/*    */ package drzhark.mocreatures.entity.aquatic;
/*    */ 
/*    */ import drzhark.mocreatures.MoCreatures;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MoCEntitySalmon
/*    */   extends MoCEntityMediumFish {
/*    */   public MoCEntitySalmon(World world) {
/* 10 */     super(world);
/* 11 */     setType(1);
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTexture() {
/* 16 */     return MoCreatures.proxy.getTexture("mediumfish_salmon.png");
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getEggNumber() {
/* 21 */     return 70;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\aquatic\MoCEntitySalmon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */