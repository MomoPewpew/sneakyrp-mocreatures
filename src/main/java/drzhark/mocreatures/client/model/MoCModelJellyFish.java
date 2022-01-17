/*     */ package drzhark.mocreatures.client.model;@SideOnly(Side.CLIENT)
/*     */ public class MoCModelJellyFish extends ModelBase { ModelRenderer Top;
/*     */   ModelRenderer Head;
/*     */   ModelRenderer HeadSmall;
/*     */   ModelRenderer Body;
/*     */   ModelRenderer BodyCenter;
/*     */   ModelRenderer BodyBottom;
/*     */   ModelRenderer Side1;
/*     */   ModelRenderer Side2;
/*     */   ModelRenderer Side3;
/*     */   ModelRenderer Side4;
/*     */   ModelRenderer LegSmall1;
/*     */   
/*     */   public MoCModelJellyFish() {
/*  15 */     this.textureWidth = 64;
/*  16 */     this.textureHeight = 16;
/*     */     
/*  18 */     this.Top = new ModelRenderer(this, 0, 10);
/*  19 */     this.Top.addBox(-2.5F, 0.0F, -2.5F, 5, 1, 5);
/*  20 */     this.Top.setRotationPoint(0.0F, 11.0F, 0.0F);
/*     */     
/*  22 */     this.Head = new ModelRenderer(this, 0, 0);
/*  23 */     this.Head.addBox(-4.0F, 0.0F, -4.0F, 8, 2, 8);
/*  24 */     this.Head.setRotationPoint(0.0F, 12.0F, 0.0F);
/*     */     
/*  26 */     this.HeadSmall = new ModelRenderer(this, 24, 0);
/*  27 */     this.HeadSmall.addBox(-2.0F, 0.0F, -2.0F, 4, 3, 4);
/*  28 */     this.HeadSmall.setRotationPoint(0.0F, 12.5F, 0.0F);
/*     */     
/*  30 */     this.Body = new ModelRenderer(this, 36, 0);
/*  31 */     this.Body.addBox(-3.5F, 0.0F, -3.5F, 7, 7, 7);
/*  32 */     this.Body.setRotationPoint(0.0F, 13.8F, 0.0F);
/*     */     
/*  34 */     this.BodyCenter = new ModelRenderer(this, 0, 0);
/*  35 */     this.BodyCenter.addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2);
/*  36 */     this.BodyCenter.setRotationPoint(0.0F, 15.5F, 0.0F);
/*     */     
/*  38 */     this.BodyBottom = new ModelRenderer(this, 20, 10);
/*  39 */     this.BodyBottom.addBox(-2.0F, 0.0F, -2.0F, 4, 2, 4);
/*  40 */     this.BodyBottom.setRotationPoint(0.0F, 18.3F, 0.0F);
/*     */     
/*  42 */     this.Side1 = new ModelRenderer(this, 20, 10);
/*  43 */     this.Side1.addBox(-2.0F, 5.0F, 0.0F, 4, 2, 4);
/*  44 */     this.Side1.setRotationPoint(0.0F, 12.5F, 0.0F);
/*  45 */     setRotation(this.Side1, -0.7679449F, 0.0F, 0.0F);
/*     */     
/*  47 */     this.Side2 = new ModelRenderer(this, 20, 10);
/*  48 */     this.Side2.addBox(-4.0F, 5.0F, -2.0F, 4, 2, 4);
/*  49 */     this.Side2.setRotationPoint(0.0F, 12.5F, 0.0F);
/*  50 */     setRotation(this.Side2, 0.0F, 0.0F, -0.7679449F);
/*     */     
/*  52 */     this.Side3 = new ModelRenderer(this, 20, 10);
/*  53 */     this.Side3.addBox(0.0F, 5.0F, -2.0F, 4, 2, 4);
/*  54 */     this.Side3.setRotationPoint(0.0F, 12.5F, 0.0F);
/*  55 */     setRotation(this.Side3, 0.0F, 0.0F, 0.7679449F);
/*     */     
/*  57 */     this.Side4 = new ModelRenderer(this, 20, 10);
/*  58 */     this.Side4.addBox(-2.0F, 5.0F, -4.0F, 4, 2, 4);
/*  59 */     this.Side4.setRotationPoint(0.0F, 12.5F, 0.0F);
/*  60 */     setRotation(this.Side4, 0.7679449F, 0.0F, 0.0F);
/*     */     
/*  62 */     this.LegSmall1 = new ModelRenderer(this, 60, 2);
/*  63 */     this.LegSmall1.addBox(-1.0F, 0.0F, -1.0F, 1, 3, 1);
/*  64 */     this.LegSmall1.setRotationPoint(0.0F, 18.5F, 0.0F);
/*     */     
/*  66 */     this.LegC1 = new ModelRenderer(this, 15, 10);
/*  67 */     this.LegC1.addBox(-1.0F, 0.0F, -1.0F, 1, 4, 1);
/*  68 */     this.LegC1.setRotationPoint(-0.5F, 15.5F, -0.5F);
/*  69 */     setRotation(this.LegC1, -0.2602503F, 0.0F, 0.1487144F);
/*     */     
/*  71 */     this.LegC2 = new ModelRenderer(this, 15, 10);
/*  72 */     this.LegC2.addBox(-1.0F, 0.0F, 0.0F, 1, 4, 1);
/*  73 */     this.LegC2.setRotationPoint(0.5F, 15.5F, -0.5F);
/*  74 */     setRotation(this.LegC2, 0.1487144F, 1.747395F, 0.0F);
/*     */     
/*  76 */     this.LegC3 = new ModelRenderer(this, 15, 10);
/*  77 */     this.LegC3.addBox(-1.0F, 0.0F, 0.0F, 1, 4, 1);
/*  78 */     this.LegC3.setRotationPoint(-0.5F, 15.5F, 0.5F);
/*  79 */     setRotation(this.LegC3, 0.1115358F, 0.3717861F, 0.2230717F);
/*     */     
/*  81 */     this.Leg1 = new ModelRenderer(this, 0, 10);
/*  82 */     this.Leg1.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1);
/*  83 */     this.Leg1.setRotationPoint(0.0F, 20.0F, 2.5F);
/*     */     
/*  85 */     this.Leg2 = new ModelRenderer(this, 0, 10);
/*  86 */     this.Leg2.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1);
/*  87 */     this.Leg2.setRotationPoint(0.0F, 20.0F, -2.5F);
/*     */     
/*  89 */     this.Leg3 = new ModelRenderer(this, 0, 10);
/*  90 */     this.Leg3.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1);
/*  91 */     this.Leg3.setRotationPoint(2.5F, 20.0F, 0.0F);
/*     */     
/*  93 */     this.Leg4 = new ModelRenderer(this, 0, 10);
/*  94 */     this.Leg4.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1);
/*  95 */     this.Leg4.setRotationPoint(-2.5F, 20.0F, 0.0F);
/*     */     
/*  97 */     this.Leg5 = new ModelRenderer(this, 0, 10);
/*  98 */     this.Leg5.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1);
/*  99 */     this.Leg5.setRotationPoint(2.0F, 20.0F, 2.0F);
/* 100 */     setRotation(this.Leg5, 0.0F, 0.7853982F, 0.0F);
/*     */     
/* 102 */     this.Leg6 = new ModelRenderer(this, 0, 10);
/* 103 */     this.Leg6.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1);
/* 104 */     this.Leg6.setRotationPoint(2.0F, 20.0F, -2.0F);
/* 105 */     setRotation(this.Leg6, 0.0F, 0.7853982F, 0.0F);
/*     */     
/* 107 */     this.Leg7 = new ModelRenderer(this, 0, 10);
/* 108 */     this.Leg7.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1);
/* 109 */     this.Leg7.setRotationPoint(-2.0F, 20.0F, -2.0F);
/* 110 */     setRotation(this.Leg7, 0.0F, 0.7853982F, 0.0F);
/*     */     
/* 112 */     this.Leg8 = new ModelRenderer(this, 60, 0);
/* 113 */     this.Leg8.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1);
/* 114 */     this.Leg8.setRotationPoint(0.0F, 18.5F, 0.0F);
/*     */     
/* 116 */     this.Leg9 = new ModelRenderer(this, 0, 10);
/* 117 */     this.Leg9.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1);
/* 118 */     this.Leg9.setRotationPoint(-2.0F, 20.0F, 2.0F);
/* 119 */     setRotation(this.Leg9, 0.0F, 0.7853982F, 0.0F);
/*     */   }
/*     */   ModelRenderer LegC1; ModelRenderer LegC2; ModelRenderer LegC3; ModelRenderer Leg1; ModelRenderer Leg2; ModelRenderer Leg3; ModelRenderer Leg4; ModelRenderer Leg5; ModelRenderer Leg6; ModelRenderer Leg7; ModelRenderer Leg8; ModelRenderer Leg9;
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 124 */     setRotationAngles(f, f1, f2, f3, f4, f5);
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
/* 147 */     this.Top.render(f5);
/* 148 */     this.Head.render(f5);
/* 149 */     this.HeadSmall.render(f5);
/* 150 */     this.Body.render(f5);
/* 151 */     this.BodyCenter.render(f5);
/* 152 */     this.BodyBottom.render(f5);
/* 153 */     this.Side1.render(f5);
/* 154 */     this.Side2.render(f5);
/* 155 */     this.Side3.render(f5);
/* 156 */     this.Side4.render(f5);
/* 157 */     this.LegSmall1.render(f5);
/* 158 */     this.LegC1.render(f5);
/* 159 */     this.LegC2.render(f5);
/* 160 */     this.LegC3.render(f5);
/* 161 */     this.Leg1.render(f5);
/* 162 */     this.Leg2.render(f5);
/* 163 */     this.Leg3.render(f5);
/* 164 */     this.Leg4.render(f5);
/* 165 */     this.Leg5.render(f5);
/* 166 */     this.Leg6.render(f5);
/* 167 */     this.Leg7.render(f5);
/* 168 */     this.Leg8.render(f5);
/* 169 */     this.Leg9.render(f5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 176 */     float f6 = f1 * 2.0F;
/* 177 */     if (f6 > 1.0F) {
/* 178 */       f6 = 1.0F;
/*     */     }
/*     */     
/* 181 */     this.LegSmall1.rotateAngleX = f6;
/* 182 */     this.LegC1.rotateAngleX = f6;
/* 183 */     this.LegC2.rotateAngleX = f6;
/* 184 */     this.LegC3.rotateAngleX = f6;
/* 185 */     this.Leg1.rotateAngleX = f6;
/* 186 */     this.Leg2.rotateAngleX = f6;
/* 187 */     this.Leg3.rotateAngleX = f6;
/* 188 */     this.Leg4.rotateAngleX = f6;
/* 189 */     this.Leg5.rotateAngleX = f6;
/* 190 */     this.Leg6.rotateAngleX = f6;
/* 191 */     this.Leg7.rotateAngleX = f6;
/* 192 */     this.Leg8.rotateAngleX = f6;
/* 193 */     this.Leg9.rotateAngleX = f6;
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 197 */     model.rotateAngleX = x;
/* 198 */     model.rotateAngleY = y;
/* 199 */     model.rotateAngleZ = z;
/*     */   } }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelJellyFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */