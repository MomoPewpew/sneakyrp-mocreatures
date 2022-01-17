/*    */ package drzhark.mocreatures.client.model;
/*    */ 
/*    */ import drzhark.mocreatures.entity.passive.MoCEntityBird;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCModelBird
/*    */   extends ModelBase {
/*    */   public ModelRenderer head;
/*    */   public ModelRenderer body;
/*    */   public ModelRenderer leftleg;
/*    */   public ModelRenderer rightleg;
/*    */   public ModelRenderer rwing;
/*    */   public ModelRenderer lwing;
/*    */   public ModelRenderer beak;
/*    */   public ModelRenderer tail;
/*    */   private boolean isOnAir;
/*    */   
/*    */   public MoCModelBird() {
/* 25 */     byte byte0 = 16;
/* 26 */     this.head = new ModelRenderer(this, 0, 0);
/* 27 */     this.head.addBox(-1.5F, -3.0F, -2.0F, 3, 3, 3, 0.0F);
/* 28 */     this.head.setRotationPoint(0.0F, (-1 + byte0), -4.0F);
/* 29 */     this.beak = new ModelRenderer(this, 14, 0);
/* 30 */     this.beak.addBox(-0.5F, -1.5F, -3.0F, 1, 1, 2, 0.0F);
/* 31 */     this.beak.setRotationPoint(0.0F, (-1 + byte0), -4.0F);
/* 32 */     this.body = new ModelRenderer(this, 0, 9);
/* 33 */     this.body.addBox(-2.0F, -4.0F, -3.0F, 4, 8, 4, 0.0F);
/* 34 */     this.body.setRotationPoint(0.0F, (0 + byte0), 0.0F);
/* 35 */     this.body.rotateAngleX = 1.047198F;
/* 36 */     this.leftleg = new ModelRenderer(this, 26, 0);
/* 37 */     this.leftleg.addBox(-1.0F, 0.0F, -4.0F, 3, 4, 3);
/* 38 */     this.leftleg.setRotationPoint(-2.0F, (3 + byte0), 1.0F);
/* 39 */     this.rightleg = new ModelRenderer(this, 26, 0);
/* 40 */     this.rightleg.addBox(-1.0F, 0.0F, -4.0F, 3, 4, 3);
/* 41 */     this.rightleg.setRotationPoint(1.0F, (3 + byte0), 1.0F);
/* 42 */     this.rwing = new ModelRenderer(this, 24, 13);
/* 43 */     this.rwing.addBox(-1.0F, 0.0F, -3.0F, 1, 5, 5);
/* 44 */     this.rwing.setRotationPoint(-2.0F, (-2 + byte0), 0.0F);
/* 45 */     this.lwing = new ModelRenderer(this, 24, 13);
/* 46 */     this.lwing.addBox(0.0F, 0.0F, -3.0F, 1, 5, 5);
/* 47 */     this.lwing.setRotationPoint(2.0F, (-2 + byte0), 0.0F);
/* 48 */     this.tail = new ModelRenderer(this, 0, 23);
/* 49 */     this.tail.addBox(-6.0F, 5.0F, 2.0F, 4, 1, 4, 0.0F);
/* 50 */     this.tail.setRotationPoint(4.0F, (-3 + byte0), 0.0F);
/* 51 */     this.tail.rotateAngleX = 0.261799F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 56 */     MoCEntityBird bird = (MoCEntityBird)entity;
/* 57 */     this.isOnAir = (bird.isOnAir() && bird.getRidingEntity() == null);
/* 58 */     setRotationAngles(f, f1, f2, f3, f4, f5, entity);
/* 59 */     this.head.render(f5);
/* 60 */     this.beak.render(f5);
/* 61 */     this.body.render(f5);
/* 62 */     this.leftleg.render(f5);
/* 63 */     this.rightleg.render(f5);
/* 64 */     this.rwing.render(f5);
/* 65 */     this.lwing.render(f5);
/* 66 */     this.tail.render(f5);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
/* 71 */     this.head.rotateAngleX = -(f4 / 2.0F / 57.29578F);
/*    */     
/* 73 */     this.head.rotateAngleY = f3 / 57.29578F;
/* 74 */     this.beak.rotateAngleY = this.head.rotateAngleY;
/*    */     
/* 76 */     if (this.isOnAir) {
/* 77 */       this.leftleg.rotateAngleX = 1.4F;
/* 78 */       this.rightleg.rotateAngleX = 1.4F;
/*    */     } else {
/* 80 */       this.leftleg.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
/* 81 */       this.rightleg.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * f1;
/*    */     } 
/* 83 */     this.rwing.rotateAngleZ = f2;
/* 84 */     this.lwing.rotateAngleZ = -f2;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelBird.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */