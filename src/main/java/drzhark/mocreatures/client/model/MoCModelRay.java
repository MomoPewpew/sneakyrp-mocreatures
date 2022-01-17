/*     */ package drzhark.mocreatures.client.model;
/*     */ import drzhark.mocreatures.entity.aquatic.MoCEntityRay;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MoCModelRay extends ModelBase { public boolean isMantaRay; public boolean attacking;
/*     */   ModelRenderer Tail;
/*     */   ModelRenderer Body;
/*     */   ModelRenderer Right;
/*     */   ModelRenderer Left;
/*     */   ModelRenderer BodyU;
/*     */   ModelRenderer RWingA;
/*     */   ModelRenderer RWingB;
/*     */   ModelRenderer RWingC;
/*     */   ModelRenderer RWingD;
/*     */   ModelRenderer RWingE;
/*     */
/*     */   public MoCModelRay() {
/*  15 */     this.textureWidth = 64;
/*  16 */     this.textureHeight = 32;
/*     */
/*  18 */     this.Body = new ModelRenderer(this, 26, 0);
/*  19 */     this.Body.addBox(-4.0F, -1.0F, 0.0F, 8, 2, 11);
/*  20 */     this.Body.setRotationPoint(0.0F, 22.0F, -5.0F);
/*     */
/*  22 */     this.Right = new ModelRenderer(this, 10, 26);
/*  23 */     this.Right.addBox(-0.5F, -1.0F, -4.0F, 1, 2, 4);
/*  24 */     this.Right.setRotationPoint(-3.0F, 22.0F, -4.8F);
/*     */
/*  26 */     this.Left = new ModelRenderer(this, 0, 26);
/*  27 */     this.Left.addBox(-0.5F, -1.0F, -4.0F, 1, 2, 4);
/*  28 */     this.Left.setRotationPoint(3.0F, 22.0F, -4.8F);
/*     */
/*  30 */     this.BodyU = new ModelRenderer(this, 0, 11);
/*  31 */     this.BodyU.addBox(-3.0F, -1.0F, 0.0F, 6, 1, 8);
/*  32 */     this.BodyU.setRotationPoint(0.0F, 21.0F, -4.0F);
/*     */
/*  34 */     this.Tail = new ModelRenderer(this, 30, 15);
/*  35 */     this.Tail.addBox(-0.5F, -0.5F, 1.0F, 1, 1, 16);
/*  36 */     this.Tail.setRotationPoint(0.0F, 22.0F, 8.0F);
/*     */
/*  38 */     this.BodyTail = new ModelRenderer(this, 0, 20);
/*  39 */     this.BodyTail.addBox(-1.8F, -0.5F, -3.2F, 5, 1, 5);
/*  40 */     this.BodyTail.setRotationPoint(0.0F, 22.0F, 7.0F);
/*  41 */     setRotation(this.BodyTail, 0.0F, 1.0F, 0.0F);
/*     */
/*  43 */     this.RWingA = new ModelRenderer(this, 0, 0);
/*  44 */     this.RWingA.addBox(-3.0F, -0.5F, -5.0F, 3, 1, 10);
/*  45 */     this.RWingA.setRotationPoint(-4.0F, 22.0F, 1.0F);
/*     */
/*  47 */     this.RWingB = new ModelRenderer(this, 2, 2);
/*  48 */     this.RWingB.addBox(-6.0F, -0.5F, -4.0F, 3, 1, 8);
/*  49 */     this.RWingB.setRotationPoint(-4.0F, 22.0F, 1.0F);
/*     */
/*  51 */     this.RWingC = new ModelRenderer(this, 5, 4);
/*  52 */     this.RWingC.addBox(-8.0F, -0.5F, -3.0F, 2, 1, 6);
/*  53 */     this.RWingC.setRotationPoint(-4.0F, 22.0F, 1.0F);
/*     */
/*  55 */     this.RWingD = new ModelRenderer(this, 6, 5);
/*  56 */     this.RWingD.addBox(-10.0F, -0.5F, -2.5F, 2, 1, 5);
/*  57 */     this.RWingD.setRotationPoint(-4.0F, 22.0F, 1.0F);
/*     */
/*  59 */     this.RWingE = new ModelRenderer(this, 7, 6);
/*  60 */     this.RWingE.addBox(-12.0F, -0.5F, -2.0F, 2, 1, 4);
/*  61 */     this.RWingE.setRotationPoint(-4.0F, 22.0F, 1.0F);
/*     */
/*  63 */     this.RWingF = new ModelRenderer(this, 8, 7);
/*  64 */     this.RWingF.addBox(-14.0F, -0.5F, -1.5F, 2, 1, 3);
/*  65 */     this.RWingF.setRotationPoint(-4.0F, 22.0F, 1.0F);
/*     */
/*  67 */     this.RWingG = new ModelRenderer(this, 9, 8);
/*  68 */     this.RWingG.addBox(-16.0F, -0.5F, -1.0F, 2, 1, 2);
/*  69 */     this.RWingG.setRotationPoint(-4.0F, 22.0F, 1.0F);
/*     */
/*  71 */     this.LWingA = new ModelRenderer(this, 0, 0);
/*  72 */     this.LWingA.addBox(0.0F, -0.5F, -5.0F, 3, 1, 10);
/*  73 */     this.LWingA.setRotationPoint(4.0F, 22.0F, 1.0F);
/*  74 */     this.LWingA.mirror = true;
/*     */
/*  76 */     this.LWingB = new ModelRenderer(this, 2, 2);
/*  77 */     this.LWingB.addBox(3.0F, -0.5F, -4.0F, 3, 1, 8);
/*  78 */     this.LWingB.setRotationPoint(4.0F, 22.0F, 1.0F);
/*  79 */     this.LWingB.mirror = true;
/*     */
/*  81 */     this.LWingC = new ModelRenderer(this, 5, 4);
/*  82 */     this.LWingC.addBox(6.0F, -0.5F, -3.0F, 2, 1, 6);
/*  83 */     this.LWingC.setRotationPoint(4.0F, 22.0F, 1.0F);
/*  84 */     this.LWingC.mirror = true;
/*     */
/*  86 */     this.LWingD = new ModelRenderer(this, 6, 5);
/*  87 */     this.LWingD.addBox(8.0F, -0.5F, -2.5F, 2, 1, 5);
/*  88 */     this.LWingD.setRotationPoint(4.0F, 22.0F, 1.0F);
/*  89 */     this.LWingD.mirror = true;
/*     */
/*  91 */     this.LWingE = new ModelRenderer(this, 7, 6);
/*  92 */     this.LWingE.addBox(10.0F, -0.5F, -2.0F, 2, 1, 4);
/*  93 */     this.LWingE.setRotationPoint(4.0F, 22.0F, 1.0F);
/*  94 */     this.LWingE.mirror = true;
/*     */
/*  96 */     this.LWingF = new ModelRenderer(this, 8, 7);
/*  97 */     this.LWingF.addBox(12.0F, -0.5F, -1.5F, 2, 1, 3);
/*  98 */     this.LWingF.setRotationPoint(4.0F, 22.0F, 1.0F);
/*  99 */     this.LWingF.mirror = true;
/*     */
/* 101 */     this.LWingG = new ModelRenderer(this, 9, 8);
/* 102 */     this.LWingG.addBox(14.0F, -0.5F, -1.0F, 2, 1, 2);
/* 103 */     this.LWingG.setRotationPoint(4.0F, 22.0F, 1.0F);
/* 104 */     this.LWingG.mirror = true;
/*     */
/* 106 */     this.LEye = new ModelRenderer(this, 0, 0);
/* 107 */     this.LEye.addBox(-3.0F, -2.0F, 1.0F, 1, 1, 2);
/* 108 */     this.LEye.setRotationPoint(0.0F, 21.0F, -4.0F);
/*     */
/* 110 */     this.REye = new ModelRenderer(this, 0, 3);
/* 111 */     this.REye.addBox(2.0F, -2.0F, 1.0F, 1, 1, 2);
/* 112 */     this.REye.setRotationPoint(0.0F, 21.0F, -4.0F);
/*     */   }
/*     */   ModelRenderer RWingF; ModelRenderer BodyTail; ModelRenderer RWingG; ModelRenderer LWingA; ModelRenderer LWingB; ModelRenderer LWingC; ModelRenderer LWingD; ModelRenderer LWingE; ModelRenderer LWingF; ModelRenderer LWingG; ModelRenderer LEye; ModelRenderer REye;
/*     */
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 117 */     super.render(entity, f, f1, f2, f3, f4, f5);
/* 118 */     MoCEntityRay ray = (MoCEntityRay)entity;
/* 119 */     this.attacking = ray.isPoisoning();
/* 120 */     this.isMantaRay = ray.isMantaRay();
/*     */
/* 122 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/* 123 */     this.Tail.render(f5);
/* 124 */     this.Body.render(f5);
/* 125 */     this.BodyU.render(f5);
/* 126 */     this.BodyTail.render(f5);
/*     */
/* 128 */     this.RWingA.render(f5);
/* 129 */     this.RWingB.render(f5);
/*     */
/* 131 */     this.LWingA.render(f5);
/* 132 */     this.LWingB.render(f5);
/*     */
/* 134 */     if (this.isMantaRay) {
/* 135 */       this.Right.render(f5);
/* 136 */       this.Left.render(f5);
/* 137 */       this.RWingC.render(f5);
/* 138 */       this.LWingC.render(f5);
/*     */
/* 140 */       this.RWingD.render(f5);
/* 141 */       this.RWingE.render(f5);
/* 142 */       this.RWingF.render(f5);
/* 143 */       this.RWingG.render(f5);
/* 144 */       this.LWingD.render(f5);
/* 145 */       this.LWingE.render(f5);
/* 146 */       this.LWingF.render(f5);
/* 147 */       this.LWingG.render(f5);
/*     */     } else {
/* 149 */       this.REye.render(f5);
/* 150 */       this.LEye.render(f5);
/*     */     }
/*     */   }
/*     */
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 155 */     model.rotateAngleX = x;
/* 156 */     model.rotateAngleY = y;
/* 157 */     model.rotateAngleZ = z;
/*     */   }
/*     */
/*     */
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 162 */     float rotF = MathHelper.cos(f * 0.6662F) * 1.5F * f1;
/* 163 */     float f6 = 20.0F;
/* 164 */     this.Tail.rotateAngleY = MathHelper.cos(f * 0.6662F) * 0.7F * f1;
/* 165 */     this.RWingA.rotateAngleZ = rotF;
/* 166 */     this.LWingA.rotateAngleZ = -rotF;
/* 167 */     rotF += rotF / f6;
/* 168 */     this.RWingB.rotateAngleZ = rotF;
/* 169 */     this.LWingB.rotateAngleZ = -rotF;
/* 170 */     rotF += rotF / f6;
/*     */
/* 172 */     this.RWingC.rotateAngleZ = rotF;
/* 173 */     this.LWingC.rotateAngleZ = -rotF;
/* 174 */     rotF += rotF / f6;
/*     */
/* 176 */     this.RWingD.rotateAngleZ = rotF;
/* 177 */     this.LWingD.rotateAngleZ = -rotF;
/* 178 */     rotF += rotF / f6;
/*     */
/* 180 */     this.RWingE.rotateAngleZ = rotF;
/* 181 */     this.LWingE.rotateAngleZ = -rotF;
/* 182 */     rotF += rotF / f6;
/*     */
/* 184 */     this.RWingF.rotateAngleZ = rotF;
/* 185 */     this.LWingF.rotateAngleZ = -rotF;
/* 186 */     rotF += rotF / f6;
/*     */
/* 188 */     this.RWingG.rotateAngleZ = rotF;
/* 189 */     this.LWingG.rotateAngleZ = -rotF;
/*     */
/* 191 */     if (this.attacking) {
/* 192 */       this.Tail.rotateAngleX = 0.5F;
/*     */     } else {
/* 194 */       this.Tail.rotateAngleX = 0.0F;
/*     */     }
/*     */   } }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelRay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
