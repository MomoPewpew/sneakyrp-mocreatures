/*     */ package drzhark.mocreatures.client.model;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ 
/*     */ public class MoCModelRaccoon
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer Head;
/*     */   ModelRenderer Snout;
/*     */   ModelRenderer RightEar;
/*     */   ModelRenderer LeftEar;
/*     */   ModelRenderer LeftSideburn;
/*     */   ModelRenderer RightSideburn;
/*     */   ModelRenderer RightRearFoot;
/*     */   ModelRenderer Neck;
/*     */   ModelRenderer Body;
/*     */   ModelRenderer TailA;
/*     */   ModelRenderer TailB;
/*     */   ModelRenderer RightFrontLegA;
/*     */   ModelRenderer RightFrontLegB;
/*     */   ModelRenderer RightFrontFoot;
/*     */   ModelRenderer LeftFrontLegA;
/*     */   ModelRenderer LeftFrontLegB;
/*     */   ModelRenderer LeftFrontFoot;
/*     */   ModelRenderer RightRearLegA;
/*     */   ModelRenderer RightRearLegB;
/*     */   ModelRenderer LeftRearLegB;
/*     */   ModelRenderer LeftRearLegA;
/*     */   ModelRenderer LeftRearFoot;
/*  33 */   private float radianF = 57.29578F;
/*     */   
/*     */   public MoCModelRaccoon() {
/*  36 */     this.textureWidth = 64;
/*  37 */     this.textureHeight = 64;
/*     */     
/*  39 */     this.Head = new ModelRenderer(this, 38, 21);
/*  40 */     this.Head.addBox(-4.0F, -3.5F, -6.5F, 8, 6, 5);
/*  41 */     this.Head.setRotationPoint(0.0F, 17.0F, -4.0F);
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
/*  53 */     this.RightSideburn = new ModelRenderer(this, 0, 32);
/*  54 */     this.RightSideburn.addBox(-3.0F, -2.0F, -2.0F, 3, 4, 4);
/*  55 */     this.RightSideburn.setRotationPoint(-2.5F, 0.5F, -3.2F);
/*  56 */     setRotation(this.RightSideburn, 0.0F, -0.5235988F, 0.0F);
/*  57 */     this.Head.addChild(this.RightSideburn);
/*     */     
/*  59 */     this.LeftSideburn = new ModelRenderer(this, 0, 40);
/*  60 */     this.LeftSideburn.addBox(0.0F, -2.0F, -2.0F, 3, 4, 4);
/*  61 */     this.LeftSideburn.setRotationPoint(2.5F, 0.5F, -3.2F);
/*  62 */     setRotation(this.LeftSideburn, 0.0F, 0.5235988F, 0.0F);
/*  63 */     this.Head.addChild(this.LeftSideburn);
/*     */     
/*  65 */     this.Snout = new ModelRenderer(this, 24, 25);
/*  66 */     this.Snout.addBox(-1.5F, -0.5F, -10.5F, 3, 3, 4);
/*  67 */     this.Snout.setRotationPoint(0.0F, 17.0F, -4.0F);
/*     */     
/*  69 */     this.RightEar = new ModelRenderer(this, 24, 22);
/*  70 */     this.RightEar.addBox(-4.0F, -5.5F, -3.5F, 3, 2, 1);
/*  71 */     this.RightEar.setRotationPoint(0.0F, 17.0F, -4.0F);
/*     */     
/*  73 */     this.LeftEar = new ModelRenderer(this, 24, 18);
/*  74 */     this.LeftEar.addBox(1.0F, -5.5F, -3.5F, 3, 2, 1);
/*  75 */     this.LeftEar.setRotationPoint(0.0F, 17.0F, -4.0F);
/*     */     
/*  77 */     this.RightRearFoot = new ModelRenderer(this, 46, 0);
/*  78 */     this.RightRearFoot.addBox(-5.0F, 5.0F, -2.0F, 3, 1, 3);
/*  79 */     this.RightRearFoot.setRotationPoint(0.0F, 18.0F, 4.0F);
/*     */     
/*  81 */     this.Neck = new ModelRenderer(this, 46, 4);
/*  82 */     this.Neck.addBox(-2.5F, -2.0F, -3.0F, 5, 4, 3);
/*  83 */     this.Neck.setRotationPoint(0.0F, 17.0F, -4.0F);
/*  84 */     setRotation(this.Neck, -0.4461433F, 0.0F, 0.0F);
/*     */     
/*  86 */     this.Body = new ModelRenderer(this, 0, 0);
/*  87 */     this.Body.addBox(-3.0F, 0.0F, -3.0F, 6, 6, 12);
/*  88 */     this.Body.setRotationPoint(0.0F, 15.0F, -2.0F);
/*     */     
/*  90 */     this.TailA = new ModelRenderer(this, 0, 3);
/*  91 */     this.TailA.addBox(-1.5F, -6.0F, -1.5F, 3, 6, 3);
/*  92 */     this.TailA.setRotationPoint(0.0F, 16.5F, 6.5F);
/*  93 */     setRotation(this.TailA, -2.024582F, 0.0F, 0.0F);
/*     */     
/*  95 */     this.TailB = new ModelRenderer(this, 24, 3);
/*  96 */     this.TailB.addBox(-1.5F, -11.0F, 0.3F, 3, 6, 3);
/*  97 */     this.TailB.setRotationPoint(0.0F, 16.5F, 6.5F);
/*  98 */     setRotation(this.TailB, -1.689974F, 0.0F, 0.0F);
/*     */     
/* 100 */     this.RightFrontLegA = new ModelRenderer(this, 36, 0);
/* 101 */     this.RightFrontLegA.addBox(-4.0F, -1.0F, -1.0F, 2, 5, 3);
/* 102 */     this.RightFrontLegA.setRotationPoint(0.0F, 18.0F, -4.0F);
/* 103 */     setRotation(this.RightFrontLegA, 0.5205006F, 0.0F, 0.0F);
/*     */     
/* 105 */     this.RightFrontLegB = new ModelRenderer(this, 46, 11);
/* 106 */     this.RightFrontLegB.addBox(-3.5F, 1.0F, 2.0F, 2, 4, 2);
/* 107 */     this.RightFrontLegB.setRotationPoint(0.0F, 18.0F, -4.0F);
/* 108 */     setRotation(this.RightFrontLegB, -0.3717861F, 0.0F, 0.0F);
/*     */     
/* 110 */     this.RightFrontFoot = new ModelRenderer(this, 46, 0);
/* 111 */     this.RightFrontFoot.addBox(-4.0F, 5.0F, -1.0F, 3, 1, 3);
/* 112 */     this.RightFrontFoot.setRotationPoint(0.0F, 18.0F, -4.0F);
/*     */     
/* 114 */     this.LeftFrontLegA = new ModelRenderer(this, 36, 8);
/* 115 */     this.LeftFrontLegA.addBox(2.0F, -1.0F, -1.0F, 2, 5, 3);
/* 116 */     this.LeftFrontLegA.setRotationPoint(0.0F, 18.0F, -4.0F);
/* 117 */     setRotation(this.LeftFrontLegA, 0.5205006F, 0.0F, 0.0F);
/*     */     
/* 119 */     this.LeftFrontLegB = new ModelRenderer(this, 54, 11);
/* 120 */     this.LeftFrontLegB.addBox(1.5F, 1.0F, 2.0F, 2, 4, 2);
/* 121 */     this.LeftFrontLegB.setRotationPoint(0.0F, 18.0F, -4.0F);
/* 122 */     setRotation(this.LeftFrontLegB, -0.3717861F, 0.0F, 0.0F);
/*     */     
/* 124 */     this.LeftFrontFoot = new ModelRenderer(this, 46, 0);
/* 125 */     this.LeftFrontFoot.addBox(1.0F, 5.0F, -1.0F, 3, 1, 3);
/* 126 */     this.LeftFrontFoot.setRotationPoint(0.0F, 18.0F, -4.0F);
/*     */     
/* 128 */     this.RightRearLegA = new ModelRenderer(this, 12, 18);
/* 129 */     this.RightRearLegA.addBox(-5.0F, -2.0F, -3.0F, 2, 5, 4);
/* 130 */     this.RightRearLegA.setRotationPoint(0.0F, 18.0F, 4.0F);
/* 131 */     setRotation(this.RightRearLegA, 0.9294653F, 0.0F, 0.0F);
/*     */     
/* 133 */     this.RightRearLegB = new ModelRenderer(this, 0, 27);
/* 134 */     this.RightRearLegB.addBox(-4.5F, 2.0F, -5.0F, 2, 2, 3);
/* 135 */     this.RightRearLegB.setRotationPoint(0.0F, 18.0F, 4.0F);
/* 136 */     setRotation(this.RightRearLegB, 0.9294653F, 0.0F, 0.0F);
/*     */     
/* 138 */     this.LeftRearLegB = new ModelRenderer(this, 10, 27);
/* 139 */     this.LeftRearLegB.addBox(2.5F, 2.0F, -5.0F, 2, 2, 3);
/* 140 */     this.LeftRearLegB.setRotationPoint(0.0F, 18.0F, 4.0F);
/* 141 */     setRotation(this.LeftRearLegB, 0.9294653F, 0.0F, 0.0F);
/*     */     
/* 143 */     this.LeftRearLegA = new ModelRenderer(this, 0, 18);
/* 144 */     this.LeftRearLegA.addBox(3.0F, -2.0F, -3.0F, 2, 5, 4);
/* 145 */     this.LeftRearLegA.setRotationPoint(0.0F, 18.0F, 4.0F);
/* 146 */     setRotation(this.LeftRearLegA, 0.9294653F, 0.0F, 0.0F);
/*     */     
/* 148 */     this.LeftRearFoot = new ModelRenderer(this, 46, 0);
/* 149 */     this.LeftRearFoot.addBox(2.0F, 5.0F, -2.0F, 3, 1, 3);
/* 150 */     this.LeftRearFoot.setRotationPoint(0.0F, 18.0F, 4.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 156 */     super.render(entity, f, f1, f2, f3, f4, f5);
/* 157 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/* 158 */     this.Head.render(f5);
/* 159 */     this.Snout.render(f5);
/* 160 */     this.RightEar.render(f5);
/* 161 */     this.LeftEar.render(f5);
/*     */ 
/*     */     
/* 164 */     this.RightRearFoot.render(f5);
/* 165 */     this.Neck.render(f5);
/* 166 */     this.Body.render(f5);
/* 167 */     this.TailA.render(f5);
/* 168 */     this.TailB.render(f5);
/* 169 */     this.RightFrontLegA.render(f5);
/* 170 */     this.RightFrontLegB.render(f5);
/* 171 */     this.RightFrontFoot.render(f5);
/* 172 */     this.LeftFrontLegA.render(f5);
/* 173 */     this.LeftFrontLegB.render(f5);
/* 174 */     this.LeftFrontFoot.render(f5);
/* 175 */     this.RightRearLegA.render(f5);
/* 176 */     this.RightRearLegB.render(f5);
/* 177 */     this.LeftRearLegB.render(f5);
/* 178 */     this.LeftRearLegA.render(f5);
/* 179 */     this.LeftRearFoot.render(f5);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 183 */     model.rotateAngleX = x;
/* 184 */     model.rotateAngleY = y;
/* 185 */     model.rotateAngleZ = z;
/*     */   }
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 189 */     this.Head.rotateAngleY = f3 / 57.29578F;
/* 190 */     this.Head.rotateAngleX = f4 / 57.29578F;
/* 191 */     this.Snout.rotateAngleY = this.Head.rotateAngleY;
/* 192 */     this.Snout.rotateAngleX = this.Head.rotateAngleX;
/* 193 */     this.RightEar.rotateAngleX = this.Head.rotateAngleX;
/* 194 */     this.RightEar.rotateAngleY = this.Head.rotateAngleY;
/* 195 */     this.LeftEar.rotateAngleX = this.Head.rotateAngleX;
/* 196 */     this.LeftEar.rotateAngleY = this.Head.rotateAngleY;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 202 */     float RLegXRot = MathHelper.cos(f * 1.0F + 3.141593F) * 0.8F * f1;
/* 203 */     float LLegXRot = MathHelper.cos(f * 1.0F) * 0.8F * f1;
/*     */     
/* 205 */     this.RightFrontLegA.rotateAngleX = 30.0F / this.radianF + RLegXRot;
/* 206 */     this.LeftFrontLegA.rotateAngleX = 30.0F / this.radianF + LLegXRot;
/* 207 */     this.RightRearLegA.rotateAngleX = 53.0F / this.radianF + LLegXRot;
/* 208 */     this.LeftRearLegA.rotateAngleX = 53.0F / this.radianF + RLegXRot;
/*     */     
/* 210 */     this.RightFrontLegB.rotateAngleX = -21.0F / this.radianF + RLegXRot;
/* 211 */     this.RightFrontFoot.rotateAngleX = RLegXRot;
/* 212 */     this.LeftFrontLegB.rotateAngleX = -21.0F / this.radianF + LLegXRot;
/* 213 */     this.LeftFrontFoot.rotateAngleX = LLegXRot;
/*     */     
/* 215 */     this.RightRearLegB.rotateAngleX = 53.0F / this.radianF + LLegXRot;
/* 216 */     this.RightRearFoot.rotateAngleX = LLegXRot;
/* 217 */     this.LeftRearLegB.rotateAngleX = 53.0F / this.radianF + RLegXRot;
/* 218 */     this.LeftRearFoot.rotateAngleX = RLegXRot;
/*     */     
/* 220 */     this.TailA.rotateAngleY = MathHelper.cos(f * 0.6662F) * 0.7F * f1;
/* 221 */     this.TailB.rotateAngleY = this.TailA.rotateAngleY;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelRaccoon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */