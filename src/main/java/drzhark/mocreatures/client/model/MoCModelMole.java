/*     */ package drzhark.mocreatures.client.model;
/*     */ 
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityMole;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class MoCModelMole
/*     */   extends ModelBase {
/*     */   ModelRenderer Nose;
/*     */   ModelRenderer Head;
/*     */   ModelRenderer Body;
/*     */   ModelRenderer Back;
/*     */   ModelRenderer Tail;
/*     */   ModelRenderer LLeg;
/*     */   ModelRenderer LFingers;
/*     */   ModelRenderer RLeg;
/*     */   ModelRenderer RFingers;
/*     */   ModelRenderer LRearLeg;
/*     */   ModelRenderer RRearLeg;
/*     */   
/*     */   public MoCModelMole() {
/*  25 */     this.textureWidth = 64;
/*  26 */     this.textureHeight = 32;
/*     */     
/*  28 */     this.Nose = new ModelRenderer(this, 0, 25);
/*  29 */     this.Nose.addBox(-1.0F, 0.0F, -4.0F, 2, 2, 3);
/*  30 */     this.Nose.setRotationPoint(0.0F, 20.0F, -6.0F);
/*  31 */     setRotation(this.Nose, 0.2617994F, 0.0F, 0.0F);
/*     */     
/*  33 */     this.Head = new ModelRenderer(this, 0, 18);
/*  34 */     this.Head.addBox(-3.0F, -2.0F, -2.0F, 6, 4, 3);
/*  35 */     this.Head.setRotationPoint(0.0F, 20.0F, -6.0F);
/*     */     
/*  37 */     this.Body = new ModelRenderer(this, 0, 0);
/*  38 */     this.Body.addBox(-5.0F, 0.0F, 0.0F, 10, 6, 10);
/*  39 */     this.Body.setRotationPoint(0.0F, 17.0F, -6.0F);
/*     */     
/*  41 */     this.Back = new ModelRenderer(this, 18, 16);
/*  42 */     this.Back.addBox(-4.0F, -3.0F, 0.0F, 8, 5, 4);
/*  43 */     this.Back.setRotationPoint(0.0F, 21.0F, 4.0F);
/*     */     
/*  45 */     this.Tail = new ModelRenderer(this, 52, 8);
/*  46 */     this.Tail.addBox(-0.5F, 0.0F, 1.0F, 1, 1, 5);
/*  47 */     this.Tail.setRotationPoint(0.0F, 21.0F, 6.0F);
/*  48 */     setRotation(this.Tail, -0.3490659F, 0.0F, 0.0F);
/*     */     
/*  50 */     this.LLeg = new ModelRenderer(this, 10, 25);
/*  51 */     this.LLeg.addBox(0.0F, -2.0F, -1.0F, 6, 4, 2);
/*  52 */     this.LLeg.setRotationPoint(4.0F, 21.0F, -4.0F);
/*  53 */     setRotation(this.LLeg, 0.0F, 0.0F, 0.2268928F);
/*     */     
/*  55 */     this.LFingers = new ModelRenderer(this, 44, 8);
/*  56 */     this.LFingers.addBox(5.0F, -2.0F, 1.0F, 1, 4, 1);
/*  57 */     this.LFingers.setRotationPoint(4.0F, 21.0F, -4.0F);
/*  58 */     setRotation(this.LFingers, 0.0F, 0.0F, 0.2268928F);
/*     */     
/*  60 */     this.RLeg = new ModelRenderer(this, 26, 25);
/*  61 */     this.RLeg.addBox(-6.0F, -2.0F, -1.0F, 6, 4, 2);
/*  62 */     this.RLeg.setRotationPoint(-4.0F, 21.0F, -4.0F);
/*  63 */     setRotation(this.RLeg, 0.0F, 0.0F, -0.2268928F);
/*     */     
/*  65 */     this.RFingers = new ModelRenderer(this, 48, 8);
/*  66 */     this.RFingers.addBox(-6.0F, -2.0F, 1.0F, 1, 4, 1);
/*  67 */     this.RFingers.setRotationPoint(-4.0F, 21.0F, -4.0F);
/*  68 */     setRotation(this.RFingers, 0.0F, 0.0F, -0.2268928F);
/*     */     
/*  70 */     this.LRearLeg = new ModelRenderer(this, 36, 0);
/*  71 */     this.LRearLeg.addBox(0.0F, -2.0F, -1.0F, 2, 3, 5);
/*  72 */     this.LRearLeg.setRotationPoint(3.0F, 22.0F, 5.0F);
/*  73 */     setRotation(this.LRearLeg, -0.2792527F, 0.5235988F, 0.0F);
/*     */     
/*  75 */     this.RRearLeg = new ModelRenderer(this, 50, 0);
/*  76 */     this.RRearLeg.addBox(-2.0F, -2.0F, -1.0F, 2, 3, 5);
/*  77 */     this.RRearLeg.setRotationPoint(-3.0F, 22.0F, 5.0F);
/*  78 */     setRotation(this.RRearLeg, -0.2792527F, -0.5235988F, 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/*  83 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/*  84 */     MoCEntityMole mole = (MoCEntityMole)entity;
/*  85 */     float yOffset = mole.getAdjustedYOffset();
/*  86 */     GL11.glPushMatrix();
/*  87 */     GL11.glTranslatef(0.0F, yOffset, 0.0F);
/*  88 */     this.Nose.render(f5);
/*  89 */     this.Head.render(f5);
/*  90 */     this.Body.render(f5);
/*  91 */     this.Back.render(f5);
/*  92 */     this.Tail.render(f5);
/*  93 */     this.LLeg.render(f5);
/*  94 */     this.LFingers.render(f5);
/*  95 */     this.RLeg.render(f5);
/*  96 */     this.RFingers.render(f5);
/*  97 */     this.LRearLeg.render(f5);
/*  98 */     this.RRearLeg.render(f5);
/*  99 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 103 */     model.rotateAngleX = x;
/* 104 */     model.rotateAngleY = y;
/* 105 */     model.rotateAngleZ = z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 111 */     this.Head.rotateAngleY = f3 / 57.29578F;
/* 112 */     this.Head.rotateAngleX = f4 / 57.29578F;
/* 113 */     this.Nose.rotateAngleX = 0.2617994F + this.Head.rotateAngleX;
/* 114 */     this.Nose.rotateAngleY = this.Head.rotateAngleY;
/*     */     
/* 116 */     float RLegXRot = MathHelper.cos(f * 1.0F + 3.141593F) * 0.8F * f1;
/* 117 */     float LLegXRot = MathHelper.cos(f * 1.0F) * 0.8F * f1;
/*     */     
/* 119 */     this.RLeg.rotateAngleY = RLegXRot;
/* 120 */     this.RFingers.rotateAngleY = this.RLeg.rotateAngleY;
/* 121 */     this.LLeg.rotateAngleY = LLegXRot;
/* 122 */     this.LFingers.rotateAngleY = this.LLeg.rotateAngleY;
/* 123 */     this.RRearLeg.rotateAngleY = -0.5235988F + LLegXRot;
/* 124 */     this.LRearLeg.rotateAngleY = 0.5235988F + RLegXRot;
/*     */     
/* 126 */     this.Tail.rotateAngleZ = this.LLeg.rotateAngleX * 0.625F;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelMole.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */