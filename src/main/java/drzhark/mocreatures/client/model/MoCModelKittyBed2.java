/*    */ package drzhark.mocreatures.client.model;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCModelKittyBed2
/*    */   extends ModelBase {
/*    */   ModelRenderer Sheet;
/*    */   
/*    */   public MoCModelKittyBed2() {
/* 15 */     float f = 0.0F;
/* 16 */     this.Sheet = new ModelRenderer(this, 0, 15);
/* 17 */     this.Sheet.addBox(0.0F, 0.0F, 0.0F, 16, 3, 14, f);
/* 18 */     this.Sheet.setRotationPoint(-8.0F, 21.0F, -7.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 23 */     this.Sheet.render(f5);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelKittyBed2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */