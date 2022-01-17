/*    */ package drzhark.mocreatures.client.model;
/*    */ 
/*    */ import drzhark.mocreatures.entity.ambient.MoCEntitySnail;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCModelSnail
/*    */   extends ModelBase {
/*    */   ModelRenderer Head;
/*    */   ModelRenderer Antenna;
/*    */   ModelRenderer Body;
/*    */   ModelRenderer ShellUp;
/*    */   ModelRenderer ShellDown;
/*    */   ModelRenderer Tail;
/*    */   
/*    */   public MoCModelSnail() {
/* 22 */     this.textureWidth = 32;
/* 23 */     this.textureHeight = 32;
/*    */     
/* 25 */     this.Head = new ModelRenderer(this, 0, 6);
/* 26 */     this.Head.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);
/* 27 */     this.Head.setRotationPoint(0.0F, 21.8F, -1.0F);
/* 28 */     setRotation(this.Head, -0.4537856F, 0.0F, 0.0F);
/*    */     
/* 30 */     this.Antenna = new ModelRenderer(this, 8, 0);
/* 31 */     this.Antenna.addBox(-1.5F, 0.0F, -1.0F, 3, 2, 0);
/* 32 */     this.Antenna.setRotationPoint(0.0F, 19.4F, -1.0F);
/* 33 */     setRotation(this.Antenna, 0.0523599F, 0.0F, 0.0F);
/*    */     
/* 35 */     this.Body = new ModelRenderer(this, 0, 0);
/* 36 */     this.Body.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 4);
/* 37 */     this.Body.setRotationPoint(0.0F, 22.0F, 0.0F);
/*    */     
/* 39 */     this.ShellUp = new ModelRenderer(this, 12, 0);
/* 40 */     this.ShellUp.addBox(-1.0F, -3.0F, 0.0F, 2, 3, 3);
/* 41 */     this.ShellUp.setRotationPoint(0.0F, 22.3F, -0.2F);
/* 42 */     setRotation(this.ShellUp, 0.2268928F, 0.0F, 0.0F);
/*    */     
/* 44 */     this.ShellDown = new ModelRenderer(this, 12, 0);
/* 45 */     this.ShellDown.addBox(-1.0F, 0.0F, 0.0F, 2, 3, 3);
/* 46 */     this.ShellDown.setRotationPoint(0.0F, 21.0F, 0.0F);
/*    */     
/* 48 */     this.Tail = new ModelRenderer(this, 1, 2);
/* 49 */     this.Tail.addBox(-1.0F, 0.0F, 0.0F, 2, 1, 3);
/* 50 */     this.Tail.setRotationPoint(0.0F, 23.0F, 3.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 57 */     MoCEntitySnail snail = (MoCEntitySnail)entity;
/* 58 */     boolean isHiding = snail.getIsHiding();
/* 59 */     int type = snail.getType();
/*    */     
/* 61 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/*    */     
/* 63 */     if (isHiding && type < 5) {
/* 64 */       this.ShellDown.render(f5);
/*    */     } else {
/* 66 */       this.Head.render(f5);
/* 67 */       this.Antenna.render(f5);
/* 68 */       this.Body.render(f5);
/* 69 */       this.ShellUp.render(f5);
/* 70 */       this.Tail.render(f5);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 76 */     model.rotateAngleX = x;
/* 77 */     model.rotateAngleY = y;
/* 78 */     model.rotateAngleZ = z;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 83 */     float tailMov = MathHelper.cos(f2 * 0.3F) * 0.8F;
/* 84 */     if (f1 < 0.1F) {
/* 85 */       tailMov = 0.0F;
/*    */     }
/* 87 */     this.Tail.rotationPointZ = 2.0F + tailMov;
/* 88 */     this.ShellUp.rotateAngleX = 0.2268928F + tailMov / 10.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelSnail.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */