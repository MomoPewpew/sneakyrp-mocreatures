/*    */ package drzhark.mocreatures.client.model;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCModelLitterBox
/*    */   extends ModelBase
/*    */ {
/*    */   ModelRenderer Table1;
/*    */   ModelRenderer Table3;
/*    */   ModelRenderer Table2;
/*    */   ModelRenderer Litter;
/*    */   ModelRenderer Table4;
/*    */   ModelRenderer Bottom;
/*    */   ModelRenderer LitterUsed;
/*    */   public boolean usedlitter;
/*    */   
/*    */   public MoCModelLitterBox() {
/* 29 */     float f = 0.0F;
/* 30 */     this.Table1 = new ModelRenderer(this, 30, 0);
/* 31 */     this.Table1.addBox(-8.0F, 0.0F, 7.0F, 16, 6, 1, f);
/* 32 */     this.Table1.setRotationPoint(0.0F, 18.0F, 0.0F);
/* 33 */     this.Table3 = new ModelRenderer(this, 30, 0);
/* 34 */     this.Table3.addBox(-8.0F, 18.0F, -8.0F, 16, 6, 1, f);
/* 35 */     this.Table3.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 36 */     this.Table2 = new ModelRenderer(this, 30, 0);
/* 37 */     this.Table2.addBox(-8.0F, -3.0F, 0.0F, 16, 6, 1, f);
/* 38 */     this.Table2.setRotationPoint(8.0F, 21.0F, 0.0F);
/* 39 */     this.Table2.rotateAngleY = 1.5708F;
/* 40 */     this.Litter = new ModelRenderer(this, 0, 15);
/* 41 */     this.Litter.addBox(0.0F, 0.0F, 0.0F, 16, 2, 14, f);
/* 42 */     this.Litter.setRotationPoint(-8.0F, 21.0F, -7.0F);
/* 43 */     this.Table4 = new ModelRenderer(this, 30, 0);
/* 44 */     this.Table4.addBox(-8.0F, -3.0F, 0.0F, 16, 6, 1, f);
/* 45 */     this.Table4.setRotationPoint(-9.0F, 21.0F, 0.0F);
/* 46 */     this.Table4.rotateAngleY = 1.5708F;
/* 47 */     this.LitterUsed = new ModelRenderer(this, 16, 15);
/* 48 */     this.LitterUsed.addBox(0.0F, 0.0F, 0.0F, 16, 2, 14, f);
/* 49 */     this.LitterUsed.setRotationPoint(-8.0F, 21.0F, -7.0F);
/* 50 */     this.Bottom = new ModelRenderer(this, 16, 15);
/* 51 */     this.Bottom.addBox(-10.0F, 0.0F, -7.0F, 16, 1, 14, f);
/* 52 */     this.Bottom.setRotationPoint(2.0F, 23.0F, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 57 */     this.Table1.render(f5);
/* 58 */     this.Table3.render(f5);
/* 59 */     this.Table2.render(f5);
/* 60 */     this.Table4.render(f5);
/* 61 */     this.Bottom.render(f5);
/* 62 */     if (this.usedlitter) {
/* 63 */       this.LitterUsed.render(f5);
/*    */     } else {
/* 65 */       this.Litter.render(f5);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelLitterBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */