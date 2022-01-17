/*     */ package drzhark.mocreatures.client.model;
/*     */ 
/*     */ import drzhark.mocreatures.entity.ambient.MoCEntityCrab;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ 
/*     */ public class MoCModelCrab
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer Shell;
/*     */   ModelRenderer ShellRight;
/*     */   ModelRenderer ShellLeft;
/*     */   ModelRenderer ShellBack;
/*     */   ModelRenderer LeftEye;
/*     */   ModelRenderer LeftEyeBase;
/*     */   ModelRenderer RightEyeBase;
/*     */   ModelRenderer RightEye;
/*     */   ModelRenderer RightArmA;
/*     */   ModelRenderer RightArmB;
/*     */   ModelRenderer RightArmC;
/*     */   ModelRenderer RightArmD;
/*     */   ModelRenderer LeftArmA;
/*     */   ModelRenderer LeftArmB;
/*     */   ModelRenderer LeftArmC;
/*     */   ModelRenderer LeftArmD;
/*     */   ModelRenderer LeftLeg1A;
/*     */   ModelRenderer LeftLeg1B;
/*     */   ModelRenderer LeftLeg2A;
/*     */   ModelRenderer LeftLeg2B;
/*     */   ModelRenderer LeftLeg3A;
/*     */   ModelRenderer LeftLeg3B;
/*     */   ModelRenderer LeftLeg4A;
/*     */   ModelRenderer LeftLeg4B;
/*     */   ModelRenderer LeftLeg4C;
/*     */   ModelRenderer RightLeg1A;
/*     */   ModelRenderer RightLeg1B;
/*     */   ModelRenderer RightLeg2A;
/*     */   ModelRenderer RightLeg2B;
/*     */   ModelRenderer RightLeg3A;
/*     */   ModelRenderer RightLeg3B;
/*     */   ModelRenderer RightLeg4A;
/*     */   ModelRenderer RightLeg4B;
/*     */   ModelRenderer RightLeg4C;
/*  46 */   private float radianF = 57.29578F;
/*     */   private boolean fleeing;
/*     */   
/*     */   public MoCModelCrab() {
/*  50 */     this.textureWidth = 64;
/*  51 */     this.textureHeight = 64;
/*     */     
/*  53 */     this.Shell = new ModelRenderer(this, 0, 0);
/*  54 */     this.Shell.addBox(-5.0F, 0.0F, -4.0F, 10, 4, 8);
/*  55 */     this.Shell.setRotationPoint(0.0F, 16.0F, 0.0F);
/*     */     
/*  57 */     this.ShellRight = new ModelRenderer(this, 0, 23);
/*  58 */     this.ShellRight.addBox(4.6F, -2.0F, -4.0F, 3, 3, 8);
/*  59 */     this.ShellRight.setRotationPoint(0.0F, 16.0F, 0.0F);
/*  60 */     setRotation(this.ShellRight, 0.0F, 0.0F, 0.418879F);
/*     */     
/*  62 */     this.ShellLeft = new ModelRenderer(this, 0, 12);
/*  63 */     this.ShellLeft.addBox(-7.6F, -2.0F, -4.0F, 3, 3, 8);
/*  64 */     this.ShellLeft.setRotationPoint(0.0F, 16.0F, 0.0F);
/*  65 */     setRotation(this.ShellLeft, 0.0F, 0.0F, -0.418879F);
/*     */     
/*  67 */     this.ShellBack = new ModelRenderer(this, 10, 42);
/*  68 */     this.ShellBack.addBox(-5.0F, -1.6F, 3.6F, 10, 3, 3);
/*  69 */     this.ShellBack.setRotationPoint(0.0F, 16.0F, 0.0F);
/*  70 */     setRotation(this.ShellBack, -0.418879F, 0.0F, 0.0F);
/*     */     
/*  72 */     this.LeftEye = new ModelRenderer(this, 0, 4);
/*  73 */     this.LeftEye.addBox(1.0F, -2.0F, -4.5F, 1, 3, 1);
/*  74 */     this.LeftEye.setRotationPoint(0.0F, 16.0F, 0.0F);
/*  75 */     setRotation(this.LeftEye, 0.0F, 0.0F, 0.1745329F);
/*     */     
/*  77 */     this.LeftEyeBase = new ModelRenderer(this, 0, 16);
/*  78 */     this.LeftEyeBase.addBox(1.0F, 1.0F, -5.0F, 2, 3, 1);
/*  79 */     this.LeftEyeBase.setRotationPoint(0.0F, 16.0F, 0.0F);
/*  80 */     setRotation(this.LeftEyeBase, 0.0F, 0.0F, 0.2094395F);
/*     */     
/*  82 */     this.RightEyeBase = new ModelRenderer(this, 0, 12);
/*  83 */     this.RightEyeBase.addBox(-3.0F, 1.0F, -5.0F, 2, 3, 1);
/*  84 */     this.RightEyeBase.setRotationPoint(0.0F, 16.0F, 0.0F);
/*  85 */     setRotation(this.RightEyeBase, 0.0F, 0.0F, -0.2094395F);
/*     */     
/*  87 */     this.RightEye = new ModelRenderer(this, 0, 0);
/*  88 */     this.RightEye.addBox(-2.0F, -2.0F, -4.5F, 1, 3, 1);
/*  89 */     this.RightEye.setRotationPoint(0.0F, 16.0F, 0.0F);
/*  90 */     setRotation(this.RightEye, 0.0F, 0.0F, -0.1745329F);
/*     */     
/*  92 */     this.RightArmA = new ModelRenderer(this, 0, 34);
/*  93 */     this.RightArmA.addBox(-4.0F, -1.0F, -1.0F, 4, 2, 2);
/*  94 */     this.RightArmA.setRotationPoint(-4.0F, 19.0F, -4.0F);
/*  95 */     setRotation(this.RightArmA, 0.0F, -0.5235988F, 0.0F);
/*     */     
/*  97 */     this.RightArmB = new ModelRenderer(this, 22, 12);
/*  98 */     this.RightArmB.addBox(-4.0F, -1.5F, -1.0F, 4, 3, 2);
/*  99 */     this.RightArmB.setRotationPoint(-4.0F, 0.0F, 0.0F);
/* 100 */     setRotation(this.RightArmB, 0.0F, -2.094395F, 0.0F);
/* 101 */     this.RightArmA.addChild(this.RightArmB);
/*     */     
/* 103 */     this.RightArmC = new ModelRenderer(this, 22, 17);
/* 104 */     this.RightArmC.addBox(-3.0F, -1.5F, -1.0F, 3, 1, 2);
/* 105 */     this.RightArmC.setRotationPoint(-4.0F, 0.0F, 0.0F);
/* 106 */     this.RightArmB.addChild(this.RightArmC);
/*     */     
/* 108 */     this.RightArmD = new ModelRenderer(this, 16, 12);
/* 109 */     this.RightArmD.addBox(-2.0F, 0.5F, -0.5F, 2, 1, 1);
/* 110 */     this.RightArmD.setRotationPoint(-4.0F, 0.0F, 0.0F);
/* 111 */     this.RightArmB.addChild(this.RightArmD);
/*     */     
/* 113 */     this.LeftArmA = new ModelRenderer(this, 0, 38);
/* 114 */     this.LeftArmA.addBox(0.0F, -1.0F, -1.0F, 4, 2, 2);
/* 115 */     this.LeftArmA.setRotationPoint(4.0F, 19.0F, -4.0F);
/* 116 */     setRotation(this.LeftArmA, 0.0F, 0.5235988F, 0.0F);
/*     */     
/* 118 */     this.LeftArmB = new ModelRenderer(this, 22, 20);
/* 119 */     this.LeftArmB.addBox(0.0F, -1.5F, -1.0F, 4, 3, 2);
/* 120 */     this.LeftArmB.setRotationPoint(4.0F, 0.0F, 0.0F);
/* 121 */     setRotation(this.LeftArmB, 0.0F, 2.094395F, 0.0F);
/* 122 */     this.LeftArmA.addChild(this.LeftArmB);
/*     */     
/* 124 */     this.LeftArmC = new ModelRenderer(this, 22, 25);
/* 125 */     this.LeftArmC.addBox(0.0F, -1.5F, -1.0F, 3, 1, 2);
/* 126 */     this.LeftArmC.setRotationPoint(4.0F, 0.0F, 0.0F);
/* 127 */     this.LeftArmB.addChild(this.LeftArmC);
/*     */     
/* 129 */     this.LeftArmD = new ModelRenderer(this, 16, 23);
/* 130 */     this.LeftArmD.addBox(0.0F, 0.5F, -0.5F, 2, 1, 1);
/* 131 */     this.LeftArmD.setRotationPoint(4.0F, 0.0F, 0.0F);
/* 132 */     this.LeftArmB.addChild(this.LeftArmD);
/*     */     
/* 134 */     this.RightLeg1A = new ModelRenderer(this, 0, 42);
/* 135 */     this.RightLeg1A.addBox(-4.0F, -0.5F, -0.5F, 4, 1, 1);
/* 136 */     this.RightLeg1A.setRotationPoint(-5.0F, 19.5F, -2.5F);
/* 137 */     setRotation(this.RightLeg1A, 0.0F, -0.1745329F, -0.418879F);
/*     */     
/* 139 */     this.RightLeg1B = new ModelRenderer(this, 0, 48);
/* 140 */     this.RightLeg1B.addBox(-4.0F, -0.5F, -0.5F, 4, 1, 1);
/* 141 */     this.RightLeg1B.setRotationPoint(-4.0F, 0.0F, 0.0F);
/* 142 */     setRotation(this.RightLeg1B, 0.0F, 0.0F, -0.5235988F);
/* 143 */     this.RightLeg1A.addChild(this.RightLeg1B);
/*     */     
/* 145 */     this.RightLeg2A = new ModelRenderer(this, 0, 44);
/* 146 */     this.RightLeg2A.addBox(-4.0F, -0.5F, -0.5F, 4, 1, 1);
/* 147 */     this.RightLeg2A.setRotationPoint(-5.0F, 19.5F, 0.0F);
/* 148 */     setRotation(this.RightLeg2A, 0.0F, 0.0872665F, -0.418879F);
/*     */     
/* 150 */     this.RightLeg2B = new ModelRenderer(this, 0, 50);
/* 151 */     this.RightLeg2B.addBox(-4.0F, -0.5F, -0.5F, 4, 1, 1);
/* 152 */     this.RightLeg2B.setRotationPoint(-4.0F, 0.0F, 0.0F);
/* 153 */     setRotation(this.RightLeg2B, 0.0F, 0.0F, -0.5235988F);
/* 154 */     this.RightLeg2A.addChild(this.RightLeg2B);
/*     */     
/* 156 */     this.RightLeg3A = new ModelRenderer(this, 0, 46);
/* 157 */     this.RightLeg3A.addBox(-4.0F, -0.5F, -0.5F, 4, 1, 1);
/* 158 */     this.RightLeg3A.setRotationPoint(-5.0F, 19.5F, 2.5F);
/* 159 */     setRotation(this.RightLeg3A, 0.0F, 0.6981317F, -0.418879F);
/*     */     
/* 161 */     this.RightLeg3B = new ModelRenderer(this, 0, 52);
/* 162 */     this.RightLeg3B.addBox(-4.0F, -0.5F, -0.5F, 4, 1, 1);
/* 163 */     this.RightLeg3B.setRotationPoint(-4.0F, 0.0F, 0.0F);
/* 164 */     setRotation(this.RightLeg3B, 0.0F, 0.0F, -0.5235988F);
/* 165 */     this.RightLeg3A.addChild(this.RightLeg3B);
/*     */     
/* 167 */     this.RightLeg4A = new ModelRenderer(this, 12, 34);
/* 168 */     this.RightLeg4A.addBox(-4.0F, -0.5F, -0.5F, 4, 1, 1);
/* 169 */     this.RightLeg4A.setRotationPoint(-3.0F, 19.5F, 3.5F);
/* 170 */     setRotation(this.RightLeg4A, 0.0F, 0.6108652F, -0.418879F);
/*     */     
/* 172 */     this.RightLeg4B = new ModelRenderer(this, 12, 36);
/* 173 */     this.RightLeg4B.addBox(-3.0F, -0.5F, -1.0F, 3, 1, 2);
/* 174 */     this.RightLeg4B.setRotationPoint(-4.0F, 0.0F, 0.0F);
/* 175 */     setRotation(this.RightLeg4B, 0.0F, 1.308997F, -0.418879F);
/* 176 */     this.RightLeg4A.addChild(this.RightLeg4B);
/*     */     
/* 178 */     this.RightLeg4C = new ModelRenderer(this, 12, 39);
/* 179 */     this.RightLeg4C.addBox(-3.0F, -0.5F, -1.0F, 3, 1, 2);
/* 180 */     this.RightLeg4C.setRotationPoint(-3.0F, 0.0F, 0.0F);
/* 181 */     setRotation(this.RightLeg4C, 0.0F, 0.8726646F, -0.418879F);
/* 182 */     this.RightLeg4C.mirror = true;
/* 183 */     this.RightLeg4B.addChild(this.RightLeg4C);
/*     */     
/* 185 */     this.LeftLeg1A = new ModelRenderer(this, 0, 54);
/* 186 */     this.LeftLeg1A.addBox(0.0F, -0.5F, -0.5F, 4, 1, 1);
/* 187 */     this.LeftLeg1A.setRotationPoint(5.0F, 19.5F, -2.5F);
/* 188 */     setRotation(this.LeftLeg1A, 0.0F, 0.1745329F, 0.418879F);
/*     */     
/* 190 */     this.LeftLeg1B = new ModelRenderer(this, 0, 56);
/* 191 */     this.LeftLeg1B.addBox(0.0F, -0.5F, -0.5F, 4, 1, 1);
/* 192 */     this.LeftLeg1B.setRotationPoint(4.0F, 0.0F, 0.0F);
/* 193 */     setRotation(this.LeftLeg1B, 0.0F, 0.0F, 0.5235988F);
/* 194 */     this.LeftLeg1A.addChild(this.LeftLeg1B);
/*     */     
/* 196 */     this.LeftLeg2A = new ModelRenderer(this, 0, 62);
/* 197 */     this.LeftLeg2A.addBox(0.0F, -0.5F, -0.5F, 4, 1, 1);
/* 198 */     this.LeftLeg2A.setRotationPoint(5.0F, 19.5F, 0.0F);
/* 199 */     setRotation(this.LeftLeg2A, 0.0F, -0.0872665F, 0.418879F);
/*     */     
/* 201 */     this.LeftLeg2B = new ModelRenderer(this, 10, 62);
/* 202 */     this.LeftLeg2B.addBox(0.0F, -0.5F, -0.5F, 4, 1, 1);
/* 203 */     this.LeftLeg2B.setRotationPoint(4.0F, 0.0F, 0.0F);
/* 204 */     setRotation(this.LeftLeg2B, 0.0F, 0.0F, 0.5235988F);
/* 205 */     this.LeftLeg2A.addChild(this.LeftLeg2B);
/*     */     
/* 207 */     this.LeftLeg3A = new ModelRenderer(this, 0, 58);
/* 208 */     this.LeftLeg3A.addBox(0.0F, -0.5F, -0.5F, 4, 1, 1);
/* 209 */     this.LeftLeg3A.setRotationPoint(5.0F, 19.5F, 2.5F);
/* 210 */     setRotation(this.LeftLeg3A, 0.0F, -0.6981317F, 0.418879F);
/*     */     
/* 212 */     this.LeftLeg3B = new ModelRenderer(this, 0, 60);
/* 213 */     this.LeftLeg3B.addBox(0.0F, -0.5F, -0.5F, 4, 1, 1);
/* 214 */     this.LeftLeg3B.setRotationPoint(4.0F, 0.0F, 0.0F);
/* 215 */     setRotation(this.LeftLeg3B, 0.0F, 0.0F, 0.5235988F);
/* 216 */     this.LeftLeg3A.addChild(this.LeftLeg3B);
/*     */     
/* 218 */     this.LeftLeg4A = new ModelRenderer(this, 22, 34);
/* 219 */     this.LeftLeg4A.addBox(0.0F, -0.5F, -0.5F, 4, 1, 1);
/* 220 */     this.LeftLeg4A.setRotationPoint(2.0F, 19.5F, 3.5F);
/* 221 */     setRotation(this.LeftLeg4A, 0.0F, -0.6108652F, 0.418879F);
/*     */     
/* 223 */     this.LeftLeg4B = new ModelRenderer(this, 22, 36);
/* 224 */     this.LeftLeg4B.addBox(0.0F, -0.5F, -1.0F, 3, 1, 2);
/* 225 */     this.LeftLeg4B.setRotationPoint(4.0F, 0.0F, 0.0F);
/* 226 */     setRotation(this.LeftLeg4B, 0.0F, -1.308997F, 0.418879F);
/* 227 */     this.LeftLeg4A.addChild(this.LeftLeg4B);
/*     */     
/* 229 */     this.LeftLeg4C = new ModelRenderer(this, 22, 39);
/* 230 */     this.LeftLeg4C.addBox(0.0F, -0.5F, -1.0F, 3, 1, 2);
/* 231 */     this.LeftLeg4C.setRotationPoint(3.0F, 0.0F, 0.0F);
/* 232 */     setRotation(this.LeftLeg4C, 0.0F, -0.8726646F, 0.418879F);
/* 233 */     this.LeftLeg4B.addChild(this.LeftLeg4C);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 240 */     MoCEntityCrab crab = (MoCEntityCrab)entity;
/* 241 */     this.fleeing = crab.isFleeing();
/* 242 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/*     */     
/* 244 */     this.Shell.render(f5);
/* 245 */     this.ShellRight.render(f5);
/* 246 */     this.ShellLeft.render(f5);
/* 247 */     this.ShellBack.render(f5);
/* 248 */     this.LeftEye.render(f5);
/* 249 */     this.LeftEyeBase.render(f5);
/* 250 */     this.RightEyeBase.render(f5);
/* 251 */     this.RightEye.render(f5);
/* 252 */     this.RightArmA.render(f5);
/* 253 */     this.LeftArmA.render(f5);
/* 254 */     this.LeftLeg1A.render(f5);
/* 255 */     this.LeftLeg2A.render(f5);
/* 256 */     this.LeftLeg3A.render(f5);
/* 257 */     this.LeftLeg4A.render(f5);
/* 258 */     this.RightLeg1A.render(f5);
/* 259 */     this.RightLeg2A.render(f5);
/* 260 */     this.RightLeg3A.render(f5);
/* 261 */     this.RightLeg4A.render(f5);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 265 */     model.rotateAngleX = x;
/* 266 */     model.rotateAngleY = y;
/* 267 */     model.rotateAngleZ = z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 274 */     if (this.fleeing) {
/*     */       
/* 276 */       this.LeftArmA.rotateAngleX = -90.0F / this.radianF;
/* 277 */       this.RightArmA.rotateAngleX = -90.0F / this.radianF;
/*     */     } else {
/*     */       
/* 280 */       this.LeftArmA.rotateAngleX = 0.0F;
/* 281 */       this.RightArmA.rotateAngleX = 0.0F;
/*     */     } 
/*     */     
/* 284 */     if (f1 < 0.1F) {
/* 285 */       this.RightArmA.rotateAngleY = -30.0F / this.radianF;
/* 286 */       this.RightArmB.rotateAngleY = -120.0F / this.radianF;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 293 */       float lHand = 0.0F;
/*     */       
/* 295 */       float f2a = f2 % 100.0F;
/* 296 */       if ((((f2a > 0.0F) ? 1 : 0) & ((f2a < 10.0F) ? 1 : 0)) != 0) {
/* 297 */         lHand = f2a * 2.0F / this.radianF;
/*     */       }
/*     */       
/* 300 */       this.LeftArmA.rotateAngleY = 30.0F / this.radianF + lHand;
/* 301 */       this.LeftArmB.rotateAngleY = 120.0F / this.radianF + lHand;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 306 */       float RHand = 0.0F;
/* 307 */       float f2b = f2 % 75.0F;
/* 308 */       if ((((f2b > 30.0F) ? 1 : 0) & ((f2b < 40.0F) ? 1 : 0)) != 0) {
/* 309 */         RHand = (f2b - 29.0F) * 2.0F / this.radianF;
/*     */       }
/* 311 */       this.RightArmA.rotateAngleY = -30.0F / this.radianF - RHand;
/* 312 */       this.RightArmB.rotateAngleY = -120.0F / this.radianF - RHand;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 318 */     float f9 = -MathHelper.cos(f * 5.0F) * f1 * 2.0F;
/* 319 */     float f10 = -MathHelper.cos(f * 5.0F + 3.141593F) * f1 * 2.0F;
/* 320 */     float f11 = -MathHelper.cos(f * 5.0F + 1.570796F) * f1 * 2.0F;
/* 321 */     float f12 = -MathHelper.cos(f * 5.0F + 4.712389F) * f1 * 2.0F;
/* 322 */     float f13 = Math.abs(MathHelper.sin(f * 0.6662F + 0.0F) * 0.4F) * f1 * 5.0F;
/* 323 */     float f14 = Math.abs(MathHelper.sin(f * 0.6662F + 3.141593F) * 0.4F) * f1;
/* 324 */     float f15 = Math.abs(MathHelper.sin(f * 0.6662F + 1.570796F) * 0.4F) * f1;
/* 325 */     float f16 = Math.abs(MathHelper.sin(f * 0.6662F + 4.712389F) * 0.4F) * f1;
/*     */     
/* 327 */     this.RightLeg1A.rotateAngleY = -0.1745329F;
/* 328 */     this.RightLeg1A.rotateAngleZ = -0.418879F;
/* 329 */     this.RightLeg1A.rotateAngleY += f9;
/* 330 */     this.RightLeg1A.rotateAngleZ += f13;
/*     */     
/* 332 */     this.RightLeg1B.rotateAngleZ = -0.5235988F;
/* 333 */     this.RightLeg1B.rotateAngleZ -= f13;
/*     */     
/* 335 */     this.RightLeg2A.rotateAngleY = 0.0872665F;
/* 336 */     this.RightLeg2A.rotateAngleZ = -0.418879F;
/* 337 */     this.RightLeg2A.rotateAngleY += f10;
/* 338 */     this.RightLeg2A.rotateAngleZ += f14;
/*     */     
/* 340 */     this.RightLeg2B.rotateAngleZ = -0.5235988F;
/* 341 */     this.RightLeg2B.rotateAngleZ -= f14;
/*     */     
/* 343 */     this.RightLeg3A.rotateAngleY = 0.6981317F;
/* 344 */     this.RightLeg3A.rotateAngleZ = -0.418879F;
/* 345 */     this.RightLeg3A.rotateAngleY += f11;
/* 346 */     this.RightLeg3A.rotateAngleZ += f15;
/*     */     
/* 348 */     this.RightLeg3B.rotateAngleZ = -0.5235988F;
/* 349 */     this.RightLeg3B.rotateAngleZ -= f15;
/*     */     
/* 351 */     this.RightLeg4A.rotateAngleY = 0.6108652F;
/* 352 */     this.RightLeg4A.rotateAngleZ = -0.418879F;
/* 353 */     this.RightLeg4A.rotateAngleY += f12;
/* 354 */     this.RightLeg4A.rotateAngleZ += f16;
/*     */     
/* 356 */     this.LeftLeg1A.rotateAngleY = 0.1745329F;
/* 357 */     this.LeftLeg1A.rotateAngleZ = 0.418879F;
/* 358 */     this.LeftLeg1A.rotateAngleY -= f9;
/* 359 */     this.LeftLeg1A.rotateAngleZ -= f13;
/*     */     
/* 361 */     this.LeftLeg1B.rotateAngleZ = 0.5235988F;
/* 362 */     this.LeftLeg1B.rotateAngleZ += f13;
/*     */     
/* 364 */     this.LeftLeg2A.rotateAngleY = -0.0872665F;
/* 365 */     this.LeftLeg2A.rotateAngleZ = 0.418879F;
/* 366 */     this.LeftLeg2A.rotateAngleY -= f10;
/* 367 */     this.LeftLeg2A.rotateAngleZ -= f14;
/*     */     
/* 369 */     this.LeftLeg2B.rotateAngleZ = 0.5235988F;
/* 370 */     this.LeftLeg2B.rotateAngleZ += f14;
/*     */     
/* 372 */     this.LeftLeg3A.rotateAngleY = -0.6981317F;
/* 373 */     this.LeftLeg3A.rotateAngleZ = 0.418879F;
/* 374 */     this.LeftLeg3A.rotateAngleY -= f11;
/* 375 */     this.LeftLeg3A.rotateAngleZ -= f15;
/*     */     
/* 377 */     this.LeftLeg3B.rotateAngleZ = 0.5235988F;
/* 378 */     this.LeftLeg3B.rotateAngleZ += f15;
/*     */     
/* 380 */     this.LeftLeg4A.rotateAngleY = -0.6108652F;
/* 381 */     this.LeftLeg4A.rotateAngleZ = 0.418879F;
/* 382 */     this.LeftLeg4A.rotateAngleY -= f12;
/* 383 */     this.LeftLeg4A.rotateAngleZ -= f16;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelCrab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */