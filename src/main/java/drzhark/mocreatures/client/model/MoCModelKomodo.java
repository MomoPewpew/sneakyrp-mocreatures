/*     */ package drzhark.mocreatures.client.model;
/*     */ 
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityKomodo;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MoCModelKomodo
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer Tail;
/*     */   ModelRenderer Tail4;
/*     */   ModelRenderer Tail1;
/*     */   ModelRenderer Tail2;
/*     */   ModelRenderer Tail3;
/*     */   ModelRenderer LegBackLeft1;
/*     */   ModelRenderer Neck;
/*     */   ModelRenderer Head;
/*     */   ModelRenderer Chest;
/*     */   ModelRenderer LegBackRight1;
/*     */   ModelRenderer LegFrontLeft1;
/*     */   ModelRenderer LegFrontLeft3;
/*     */   ModelRenderer LegFrontRight1;
/*     */   ModelRenderer LegBackLeft2;
/*     */   ModelRenderer LegFrontRight2;
/*     */   ModelRenderer LegFrontLeft2;
/*     */   ModelRenderer LegBackRight2;
/*     */   ModelRenderer LegFrontRight3;
/*     */   ModelRenderer LegBackLeft3;
/*     */   ModelRenderer LegBackRight3;
/*     */   ModelRenderer Abdomen;
/*     */   ModelRenderer Mouth;
/*     */   ModelRenderer LegFrontLeft;
/*     */   ModelRenderer LegBackLeft;
/*     */   ModelRenderer LegFrontRight;
/*     */   ModelRenderer LegBackRight;
/*     */   ModelRenderer Nose;
/*     */   ModelRenderer Tongue;
/*     */   ModelRenderer SaddleA;
/*     */   ModelRenderer SaddleC;
/*     */   ModelRenderer SaddleB;
/*  45 */   private float radianF = 57.29578F;
/*     */   
/*     */   public MoCModelKomodo() {
/*  48 */     this.textureWidth = 64;
/*  49 */     this.textureHeight = 64;
/*     */     
/*  51 */     this.Head = new ModelRenderer(this);
/*  52 */     this.Head.setRotationPoint(0.0F, 13.0F, -8.0F);
/*     */     
/*  54 */     this.Neck = new ModelRenderer(this, 22, 34);
/*  55 */     this.Neck.addBox(-2.0F, 0.0F, -6.0F, 4, 5, 6);
/*  56 */     this.Neck.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  57 */     this.Head.addChild(this.Neck);
/*     */ 
/*     */     
/*  60 */     this.Nose = new ModelRenderer(this, 24, 45);
/*  61 */     this.Nose.addBox(-1.5F, -1.0F, -6.5F, 3, 2, 6);
/*  62 */     this.Nose.setRotationPoint(0.0F, 1.0F, -5.0F);
/*  63 */     this.Neck.addChild(this.Nose);
/*     */ 
/*     */     
/*  66 */     this.Mouth = new ModelRenderer(this, 0, 12);
/*  67 */     this.Mouth.addBox(-1.0F, -0.3F, -5.0F, 2, 1, 6);
/*  68 */     this.Mouth.setRotationPoint(0.0F, 3.0F, -5.8F);
/*  69 */     this.Neck.addChild(this.Mouth);
/*     */ 
/*     */     
/*  72 */     this.Tongue = new ModelRenderer(this, 48, 44);
/*  73 */     this.Tongue.addBox(-1.5F, 0.0F, -5.0F, 3, 0, 5);
/*  74 */     this.Tongue.setRotationPoint(0.0F, -0.4F, -4.7F);
/*  75 */     this.Mouth.addChild(this.Tongue);
/*     */     
/*  77 */     this.Chest = new ModelRenderer(this, 36, 2);
/*  78 */     this.Chest.addBox(-3.0F, 0.0F, -8.0F, 6, 6, 7);
/*  79 */     this.Chest.setRotationPoint(0.0F, 13.0F, 0.0F);
/*     */     
/*  81 */     this.Abdomen = new ModelRenderer(this, 36, 49);
/*  82 */     this.Abdomen.addBox(-3.0F, 0.0F, -1.0F, 6, 7, 8);
/*  83 */     this.Abdomen.setRotationPoint(0.0F, 13.0F, 0.0F);
/*     */     
/*  85 */     this.Tail = new ModelRenderer(this);
/*  86 */     this.Tail.setRotationPoint(0.0F, 13.0F, 7.0F);
/*     */     
/*  88 */     this.Tail1 = new ModelRenderer(this, 0, 21);
/*  89 */     this.Tail1.addBox(-2.0F, 0.0F, 0.0F, 4, 5, 8);
/*  90 */     this.Tail1.setRotationPoint(0.0F, 0.0F, 0.0F);
/*     */     
/*  92 */     this.Tail.addChild(this.Tail1);
/*     */     
/*  94 */     this.Tail2 = new ModelRenderer(this, 0, 34);
/*  95 */     this.Tail2.addBox(-1.5F, 0.0F, 0.0F, 3, 4, 8);
/*  96 */     this.Tail2.setRotationPoint(0.0F, 0.1F, 7.7F);
/*     */     
/*  98 */     this.Tail1.addChild(this.Tail2);
/*     */     
/* 100 */     this.Tail3 = new ModelRenderer(this, 0, 46);
/* 101 */     this.Tail3.addBox(-1.0F, 0.0F, 0.0F, 2, 3, 8);
/* 102 */     this.Tail3.setRotationPoint(0.0F, 0.1F, 7.3F);
/*     */     
/* 104 */     this.Tail2.addChild(this.Tail3);
/*     */     
/* 106 */     this.Tail4 = new ModelRenderer(this, 24, 21);
/* 107 */     this.Tail4.addBox(-0.5F, 0.0F, 0.0F, 1, 2, 8);
/* 108 */     this.Tail4.setRotationPoint(0.0F, 0.1F, 7.0F);
/*     */     
/* 110 */     this.Tail3.addChild(this.Tail4);
/*     */     
/* 112 */     this.LegFrontLeft = new ModelRenderer(this);
/* 113 */     this.LegFrontLeft.setRotationPoint(2.0F, 17.0F, -7.0F);
/*     */     
/* 115 */     this.LegFrontLeft1 = new ModelRenderer(this, 0, 0);
/* 116 */     this.LegFrontLeft1.addBox(0.0F, -1.0F, -1.5F, 4, 3, 3);
/* 117 */     this.LegFrontLeft1.setRotationPoint(0.0F, 0.0F, 0.0F);
/*     */     
/* 119 */     this.LegFrontLeft.addChild(this.LegFrontLeft1);
/*     */     
/* 121 */     this.LegFrontLeft2 = new ModelRenderer(this, 22, 0);
/* 122 */     this.LegFrontLeft2.addBox(-1.5F, 0.0F, -1.5F, 3, 4, 3);
/* 123 */     this.LegFrontLeft2.setRotationPoint(3.0F, 0.5F, 0.0F);
/* 124 */     this.LegFrontLeft1.addChild(this.LegFrontLeft2);
/*     */     
/* 126 */     this.LegFrontLeft3 = new ModelRenderer(this, 16, 58);
/* 127 */     this.LegFrontLeft3.addBox(-1.5F, 0.0F, -3.5F, 3, 1, 5);
/* 128 */     this.LegFrontLeft3.setRotationPoint(0.0F, 4.0F, 0.0F);
/* 129 */     setRotation(this.LegFrontLeft3, 0.0F, -10.0F / this.radianF, 0.0F);
/* 130 */     this.LegFrontLeft2.addChild(this.LegFrontLeft3);
/*     */     
/* 132 */     this.LegBackLeft = new ModelRenderer(this);
/* 133 */     this.LegBackLeft.setRotationPoint(2.0F, 17.0F, 6.0F);
/*     */     
/* 135 */     this.LegBackLeft1 = new ModelRenderer(this, 0, 0);
/* 136 */     this.LegBackLeft1.addBox(0.0F, -1.0F, -1.5F, 4, 3, 3);
/* 137 */     this.LegBackLeft1.setRotationPoint(0.0F, 0.0F, 0.0F);
/*     */     
/* 139 */     this.LegBackLeft.addChild(this.LegBackLeft1);
/*     */     
/* 141 */     this.LegBackLeft2 = new ModelRenderer(this, 22, 0);
/* 142 */     this.LegBackLeft2.addBox(-1.5F, 0.0F, -1.5F, 3, 4, 3);
/* 143 */     this.LegBackLeft2.setRotationPoint(3.0F, 0.5F, 0.0F);
/* 144 */     this.LegBackLeft1.addChild(this.LegBackLeft2);
/*     */     
/* 146 */     this.LegBackLeft3 = new ModelRenderer(this, 16, 58);
/* 147 */     this.LegBackLeft3.addBox(-1.5F, 0.0F, -3.5F, 3, 1, 5);
/* 148 */     this.LegBackLeft3.setRotationPoint(0.0F, 4.0F, 0.0F);
/* 149 */     setRotation(this.LegBackLeft3, 0.0F, -10.0F / this.radianF, 0.0F);
/* 150 */     this.LegBackLeft2.addChild(this.LegBackLeft3);
/*     */     
/* 152 */     this.LegFrontRight = new ModelRenderer(this);
/* 153 */     this.LegFrontRight.setRotationPoint(-2.0F, 17.0F, -7.0F);
/*     */     
/* 155 */     this.LegFrontRight1 = new ModelRenderer(this, 0, 6);
/* 156 */     this.LegFrontRight1.addBox(-4.0F, -1.0F, -1.5F, 4, 3, 3);
/* 157 */     this.LegFrontRight1.setRotationPoint(0.0F, 0.0F, 0.0F);
/*     */     
/* 159 */     this.LegFrontRight.addChild(this.LegFrontRight1);
/*     */     
/* 161 */     this.LegFrontRight2 = new ModelRenderer(this, 22, 7);
/* 162 */     this.LegFrontRight2.addBox(-1.5F, 0.0F, -1.5F, 3, 4, 3);
/* 163 */     this.LegFrontRight2.setRotationPoint(-3.0F, 0.5F, 0.0F);
/* 164 */     this.LegFrontRight1.addChild(this.LegFrontRight2);
/*     */     
/* 166 */     this.LegFrontRight3 = new ModelRenderer(this, 0, 58);
/* 167 */     this.LegFrontRight3.addBox(-1.5F, 0.0F, -3.5F, 3, 1, 5);
/* 168 */     this.LegFrontRight3.setRotationPoint(0.0F, 4.0F, 0.0F);
/* 169 */     setRotation(this.LegFrontRight3, 0.0F, 10.0F / this.radianF, 0.0F);
/* 170 */     this.LegFrontRight2.addChild(this.LegFrontRight3);
/*     */     
/* 172 */     this.LegBackRight = new ModelRenderer(this);
/* 173 */     this.LegBackRight.setRotationPoint(-2.0F, 17.0F, 6.0F);
/*     */     
/* 175 */     this.LegBackRight1 = new ModelRenderer(this, 0, 6);
/* 176 */     this.LegBackRight1.addBox(-4.0F, -1.0F, -1.5F, 4, 3, 3);
/* 177 */     this.LegBackRight1.setRotationPoint(0.0F, 0.0F, 0.0F);
/*     */     
/* 179 */     this.LegBackRight.addChild(this.LegBackRight1);
/*     */     
/* 181 */     this.LegBackRight2 = new ModelRenderer(this, 22, 7);
/* 182 */     this.LegBackRight2.addBox(-1.5F, 0.0F, -1.5F, 3, 4, 3);
/* 183 */     this.LegBackRight2.setRotationPoint(-3.0F, 0.5F, 0.0F);
/* 184 */     this.LegBackRight1.addChild(this.LegBackRight2);
/*     */     
/* 186 */     this.LegBackRight3 = new ModelRenderer(this, 0, 58);
/* 187 */     this.LegBackRight3.addBox(-1.5F, 0.0F, -3.5F, 3, 1, 5);
/* 188 */     this.LegBackRight3.setRotationPoint(0.0F, 4.0F, 0.0F);
/* 189 */     setRotation(this.LegBackRight3, 0.0F, 10.0F / this.radianF, 0.0F);
/* 190 */     this.LegBackRight2.addChild(this.LegBackRight3);
/*     */     
/* 192 */     this.SaddleA = new ModelRenderer(this, 36, 28);
/* 193 */     this.SaddleA.addBox(-2.5F, 0.5F, -4.0F, 5, 1, 8);
/* 194 */     this.SaddleA.setRotationPoint(0.0F, 12.0F, 0.0F);
/* 195 */     this.SaddleA.setTextureSize(64, 64);
/* 196 */     this.SaddleA.mirror = true;
/* 197 */     setRotation(this.SaddleA, 0.0F, 0.0F, 0.0F);
/* 198 */     this.SaddleC = new ModelRenderer(this, 36, 37);
/* 199 */     this.SaddleC.addBox(-2.5F, 0.0F, 2.0F, 5, 1, 2);
/* 200 */     this.SaddleC.setRotationPoint(0.0F, 12.0F, 0.0F);
/* 201 */     this.SaddleC.setTextureSize(64, 64);
/* 202 */     this.SaddleC.mirror = true;
/* 203 */     setRotation(this.SaddleC, 0.0F, 0.0F, 0.0F);
/* 204 */     this.SaddleB = new ModelRenderer(this, 54, 37);
/* 205 */     this.SaddleB.addBox(-1.5F, 0.0F, -4.0F, 3, 1, 2);
/* 206 */     this.SaddleB.setRotationPoint(0.0F, 12.0F, 0.0F);
/* 207 */     this.SaddleB.setTextureSize(64, 64);
/* 208 */     this.SaddleB.mirror = true;
/* 209 */     setRotation(this.SaddleB, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 214 */     MoCEntityKomodo komodo = (MoCEntityKomodo)entity;
/*     */ 
/*     */ 
/*     */     
/* 218 */     boolean mouth = (komodo.mouthCounter != 0);
/* 219 */     boolean sitting = komodo.getIsSitting();
/* 220 */     boolean swimming = komodo.isSwimming();
/* 221 */     boolean moveTail = (komodo.tailCounter != 0);
/* 222 */     boolean tongue = (komodo.tongueCounter != 0);
/* 223 */     setRotationAngles(f, f1, f2, f3, f4, f5, sitting, moveTail, tongue, mouth, swimming);
/*     */     
/* 225 */     this.Tail.render(f5);
/* 226 */     this.Head.render(f5);
/* 227 */     this.Chest.render(f5);
/* 228 */     this.LegFrontLeft.render(f5);
/* 229 */     this.LegBackLeft.render(f5);
/* 230 */     this.LegFrontRight.render(f5);
/* 231 */     this.LegBackRight.render(f5);
/* 232 */     this.Abdomen.render(f5);
/*     */     
/* 234 */     if (komodo.getIsRideable()) {
/* 235 */       this.SaddleA.render(f5);
/* 236 */       this.SaddleC.render(f5);
/* 237 */       this.SaddleB.render(f5);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 243 */     model.rotateAngleX = x;
/* 244 */     model.rotateAngleY = y;
/* 245 */     model.rotateAngleZ = z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void AdjustY(float f) {
/* 252 */     float yOff = f;
/* 253 */     this.Tail.rotationPointY = yOff + 13.0F;
/* 254 */     this.Head.rotationPointY = yOff + 13.0F;
/* 255 */     this.Chest.rotationPointY = yOff + 13.0F;
/* 256 */     this.LegFrontLeft.rotationPointY = yOff + 17.0F;
/* 257 */     this.LegBackLeft.rotationPointY = yOff + 17.0F;
/* 258 */     this.LegFrontRight.rotationPointY = yOff + 17.0F;
/* 259 */     this.LegBackRight.rotationPointY = yOff + 17.0F;
/* 260 */     this.Abdomen.rotationPointY = yOff + 13.0F;
/* 261 */     this.SaddleA.rotationPointY = yOff + 12.0F;
/* 262 */     this.SaddleB.rotationPointY = yOff + 12.0F;
/* 263 */     this.SaddleC.rotationPointY = yOff + 12.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, boolean sitting, boolean movetail, boolean tongue, boolean mouth, boolean swimming) {
/* 269 */     float TailXRot = MathHelper.cos(f * 0.4F) * 0.2F * f1;
/* 270 */     float LLegXRot = MathHelper.cos(f * 1.2F) * 1.2F * f1;
/* 271 */     float RLegXRot = MathHelper.cos(f * 1.2F + 3.141593F) * 1.2F * f1;
/*     */     
/* 273 */     if (f3 > 60.0F) {
/* 274 */       f3 = 60.0F;
/*     */     }
/* 276 */     if (f3 < -60.0F) {
/* 277 */       f3 = -60.0F;
/*     */     }
/*     */     
/* 280 */     float f10 = 0.0F;
/* 281 */     if (swimming) {
/* 282 */       f10 = 4.0F;
/* 283 */       this.Tail1.rotateAngleX = 0.0F / this.radianF - TailXRot;
/* 284 */       this.LegFrontLeft1.rotateAngleZ = 0.0F / this.radianF;
/* 285 */       this.LegFrontLeft2.rotateAngleZ = -65.0F / this.radianF;
/* 286 */       this.LegFrontLeft1.rotateAngleY = -80.0F / this.radianF;
/*     */       
/* 288 */       this.LegBackLeft1.rotateAngleZ = 0.0F / this.radianF;
/* 289 */       this.LegBackLeft2.rotateAngleZ = -65.0F / this.radianF;
/* 290 */       this.LegBackLeft1.rotateAngleY = -80.0F / this.radianF;
/*     */       
/* 292 */       this.LegFrontRight1.rotateAngleZ = 0.0F / this.radianF;
/* 293 */       this.LegFrontRight2.rotateAngleZ = 65.0F / this.radianF;
/* 294 */       this.LegFrontRight1.rotateAngleY = 80.0F / this.radianF;
/*     */       
/* 296 */       this.LegBackRight1.rotateAngleZ = 0.0F / this.radianF;
/* 297 */       this.LegBackRight2.rotateAngleZ = 65.0F / this.radianF;
/* 298 */       this.LegBackRight1.rotateAngleY = 80.0F / this.radianF;
/* 299 */     } else if (sitting) {
/* 300 */       f10 = 4.0F;
/* 301 */       this.Tail1.rotateAngleX = -5.0F / this.radianF - TailXRot;
/* 302 */       this.LegFrontLeft1.rotateAngleZ = -30.0F / this.radianF;
/* 303 */       this.LegFrontLeft2.rotateAngleZ = 0.0F / this.radianF;
/* 304 */       this.LegFrontLeft1.rotateAngleY = 0.0F;
/*     */       
/* 306 */       this.LegBackLeft1.rotateAngleZ = 0.0F / this.radianF;
/* 307 */       this.LegBackLeft2.rotateAngleZ = -65.0F / this.radianF;
/* 308 */       this.LegBackLeft1.rotateAngleY = -40.0F / this.radianF;
/*     */       
/* 310 */       this.LegFrontRight1.rotateAngleZ = 30.0F / this.radianF;
/* 311 */       this.LegFrontRight2.rotateAngleZ = 0.0F / this.radianF;
/* 312 */       this.LegFrontRight1.rotateAngleY = 0.0F;
/*     */       
/* 314 */       this.LegBackRight1.rotateAngleZ = 0.0F / this.radianF;
/* 315 */       this.LegBackRight2.rotateAngleZ = 65.0F / this.radianF;
/* 316 */       this.LegBackRight1.rotateAngleY = 40.0F / this.radianF;
/*     */     } else {
/* 318 */       this.Tail1.rotateAngleX = -15.0F / this.radianF - TailXRot;
/* 319 */       this.LegFrontLeft1.rotateAngleZ = 30.0F / this.radianF;
/* 320 */       this.LegFrontLeft2.rotateAngleZ = -30.0F / this.radianF;
/* 321 */       this.LegFrontLeft1.rotateAngleY = LLegXRot;
/* 322 */       this.LegFrontLeft2.rotateAngleX = -LLegXRot;
/*     */       
/* 324 */       this.LegBackLeft1.rotateAngleZ = 30.0F / this.radianF;
/* 325 */       this.LegBackLeft2.rotateAngleZ = -30.0F / this.radianF;
/* 326 */       this.LegBackLeft1.rotateAngleY = RLegXRot;
/* 327 */       this.LegBackLeft2.rotateAngleX = -RLegXRot;
/*     */       
/* 329 */       this.LegFrontRight1.rotateAngleZ = -30.0F / this.radianF;
/* 330 */       this.LegFrontRight2.rotateAngleZ = 30.0F / this.radianF;
/* 331 */       this.LegFrontRight1.rotateAngleY = -RLegXRot;
/* 332 */       this.LegFrontRight2.rotateAngleX = -RLegXRot;
/*     */       
/* 334 */       this.LegBackRight1.rotateAngleZ = -30.0F / this.radianF;
/* 335 */       this.LegBackRight2.rotateAngleZ = 30.0F / this.radianF;
/* 336 */       this.LegBackRight1.rotateAngleY = -LLegXRot;
/* 337 */       this.LegBackRight2.rotateAngleX = -LLegXRot;
/*     */     } 
/* 339 */     AdjustY(f10);
/*     */     
/* 341 */     float tongueF = 0.0F;
/* 342 */     if (!mouth && tongue) {
/* 343 */       tongueF = MathHelper.cos(f2 * 3.0F) / 10.0F;
/* 344 */       this.Tongue.rotationPointZ = -4.7F;
/*     */     } else {
/* 346 */       this.Tongue.rotationPointZ = 0.3F;
/*     */     } 
/*     */     
/* 349 */     float mouthF = 0.0F;
/* 350 */     if (mouth) {
/* 351 */       mouthF = 35.0F / this.radianF;
/* 352 */       this.Tongue.rotationPointZ = -0.8F;
/*     */     } 
/*     */     
/* 355 */     this.Neck.rotateAngleX = 11.0F / this.radianF + f4 * 0.33F / this.radianF;
/* 356 */     this.Nose.rotateAngleX = 10.6F / this.radianF + f4 * 0.66F / this.radianF;
/* 357 */     this.Mouth.rotateAngleX = mouthF + -3.0F / this.radianF + f4 * 0.66F / this.radianF;
/* 358 */     this.Tongue.rotateAngleX = tongueF;
/*     */     
/* 360 */     this.Neck.rotateAngleY = f3 * 0.33F / this.radianF;
/* 361 */     this.Nose.rotateAngleY = f3 * 0.66F / this.radianF;
/* 362 */     this.Mouth.rotateAngleY = f3 * 0.66F / this.radianF;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 372 */     this.Tail2.rotateAngleX = -17.0F / this.radianF + TailXRot;
/* 373 */     this.Tail3.rotateAngleX = 13.0F / this.radianF + TailXRot;
/* 374 */     this.Tail4.rotateAngleX = 11.0F / this.radianF + TailXRot;
/*     */     
/* 376 */     float t = f / 2.0F;
/*     */     
/* 378 */     if (movetail) {
/* 379 */       t = f2 / 4.0F;
/*     */     }
/* 381 */     float A = 0.35F;
/* 382 */     float w = 0.6F;
/* 383 */     float k = 0.6F;
/*     */     
/* 385 */     int i = 0;
/* 386 */     float tailLat = 0.0F;
/* 387 */     tailLat = A * MathHelper.sin(w * t - k * i++);
/* 388 */     this.Tail1.rotateAngleY = tailLat;
/* 389 */     tailLat = A * MathHelper.sin(w * t - k * i++);
/* 390 */     this.Tail2.rotateAngleY = tailLat;
/* 391 */     tailLat = A * MathHelper.sin(w * t - k * i++);
/* 392 */     this.Tail3.rotateAngleY = tailLat;
/* 393 */     tailLat = A * MathHelper.sin(w * t - k * i++);
/* 394 */     this.Tail4.rotateAngleY = tailLat;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelKomodo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */