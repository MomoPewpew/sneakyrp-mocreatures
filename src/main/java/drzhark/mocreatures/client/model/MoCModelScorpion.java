/*     */ package drzhark.mocreatures.client.model;
/*     */ 
/*     */ import drzhark.mocreatures.entity.monster.MoCEntityScorpion;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MoCModelScorpion
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer Head;
/*     */   ModelRenderer MouthL;
/*     */   ModelRenderer MouthR;
/*     */   ModelRenderer Body;
/*     */   ModelRenderer Tail1;
/*     */   ModelRenderer Tail2;
/*     */   ModelRenderer Tail3;
/*     */   ModelRenderer Tail4;
/*     */   ModelRenderer Tail5;
/*     */   ModelRenderer Sting1;
/*     */   ModelRenderer Sting2;
/*     */   ModelRenderer LArm1;
/*     */   ModelRenderer LArm2;
/*     */   ModelRenderer LArm3;
/*     */   ModelRenderer LArm4;
/*     */   ModelRenderer RArm1;
/*     */   ModelRenderer RArm2;
/*     */   ModelRenderer RArm3;
/*     */   ModelRenderer RArm4;
/*     */   ModelRenderer Leg1A;
/*     */   ModelRenderer Leg1B;
/*     */   ModelRenderer Leg1C;
/*     */   ModelRenderer Leg2A;
/*     */   ModelRenderer Leg2B;
/*     */   ModelRenderer Leg2C;
/*     */   ModelRenderer Leg3A;
/*     */   ModelRenderer Leg3B;
/*     */   ModelRenderer Leg3C;
/*     */   ModelRenderer Leg4A;
/*     */   ModelRenderer Leg4B;
/*     */   ModelRenderer Leg4C;
/*     */   ModelRenderer Leg5A;
/*     */   ModelRenderer Leg5B;
/*     */   ModelRenderer Leg5C;
/*     */   ModelRenderer Leg6A;
/*     */   ModelRenderer Leg6B;
/*     */   ModelRenderer Leg6C;
/*     */   ModelRenderer Leg7A;
/*     */   ModelRenderer Leg7B;
/*     */   ModelRenderer Leg7C;
/*     */   ModelRenderer Leg8A;
/*     */   ModelRenderer Leg8B;
/*     */   ModelRenderer Leg8C;
/*     */   ModelRenderer baby1;
/*     */   ModelRenderer baby2;
/*     */   ModelRenderer baby3;
/*     */   ModelRenderer baby4;
/*     */   ModelRenderer baby5;
/*     */   protected boolean poisoning;
/*     */   protected boolean isTalking;
/*     */   protected boolean babies;
/*     */   protected int attacking;
/*     */   protected boolean sitting;
/*  68 */   float radianF = 57.29578F;
/*     */   
/*     */   public MoCModelScorpion() {
/*  71 */     this.textureWidth = 64;
/*  72 */     this.textureHeight = 64;
/*     */     
/*  74 */     this.Head = new ModelRenderer(this, 0, 0);
/*  75 */     this.Head.addBox(-5.0F, 0.0F, 0.0F, 10, 5, 13);
/*  76 */     this.Head.setRotationPoint(0.0F, 14.0F, -9.0F);
/*     */     
/*  78 */     this.MouthL = new ModelRenderer(this, 18, 58);
/*  79 */     this.MouthL.addBox(-3.0F, -2.0F, -1.0F, 4, 4, 2);
/*  80 */     this.MouthL.setRotationPoint(3.0F, 17.0F, -9.0F);
/*  81 */     setRotation(this.MouthL, 0.0F, -0.3839724F, 0.0F);
/*     */     
/*  83 */     this.MouthR = new ModelRenderer(this, 30, 58);
/*  84 */     this.MouthR.addBox(-1.0F, -2.0F, -1.0F, 4, 4, 2);
/*  85 */     this.MouthR.setRotationPoint(-3.0F, 17.0F, -9.0F);
/*  86 */     setRotation(this.MouthR, 0.0F, 0.3839724F, 0.0F);
/*     */     
/*  88 */     this.Body = new ModelRenderer(this, 0, 18);
/*  89 */     this.Body.addBox(-4.0F, -2.0F, 0.0F, 8, 4, 10);
/*  90 */     this.Body.setRotationPoint(0.0F, 17.0F, 3.0F);
/*  91 */     setRotation(this.Body, 0.0872665F, 0.0F, 0.0F);
/*     */     
/*  93 */     this.Tail1 = new ModelRenderer(this, 0, 32);
/*  94 */     this.Tail1.addBox(-3.0F, -2.0F, 0.0F, 6, 4, 6);
/*  95 */     this.Tail1.setRotationPoint(0.0F, 16.0F, 12.0F);
/*  96 */     setRotation(this.Tail1, 0.6108652F, 0.0F, 0.0F);
/*     */     
/*  98 */     this.Tail2 = new ModelRenderer(this, 0, 42);
/*  99 */     this.Tail2.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 6);
/* 100 */     this.Tail2.setRotationPoint(0.0F, 13.0F, 16.5F);
/* 101 */     setRotation(this.Tail2, 1.134464F, 0.0F, 0.0F);
/*     */     
/* 103 */     this.Tail3 = new ModelRenderer(this, 0, 52);
/* 104 */     this.Tail3.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 6);
/* 105 */     this.Tail3.setRotationPoint(0.0F, 8.0F, 18.5F);
/* 106 */     setRotation(this.Tail3, 1.692143F, 0.0F, 0.0F);
/*     */     
/* 108 */     this.Tail4 = new ModelRenderer(this, 24, 32);
/* 109 */     this.Tail4.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 6);
/* 110 */     this.Tail4.setRotationPoint(0.0F, 3.0F, 18.0F);
/* 111 */     setRotation(this.Tail4, 2.510073F, 0.0F, 0.0F);
/*     */     
/* 113 */     this.Tail5 = new ModelRenderer(this, 24, 41);
/* 114 */     this.Tail5.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 6);
/* 115 */     this.Tail5.setRotationPoint(0.0F, -0.2F, 14.0F);
/* 116 */     setRotation(this.Tail5, 3.067752F, 0.0F, 0.0F);
/*     */     
/* 118 */     this.Sting1 = new ModelRenderer(this, 30, 50);
/* 119 */     this.Sting1.addBox(-1.5F, 0.0F, -1.5F, 3, 5, 3);
/* 120 */     this.Sting1.setRotationPoint(0.0F, -1.0F, 7.0F);
/* 121 */     setRotation(this.Sting1, 0.4089647F, 0.0F, 0.0F);
/*     */     
/* 123 */     this.Sting2 = new ModelRenderer(this, 26, 50);
/* 124 */     this.Sting2.addBox(-0.5F, 0.0F, 0.5F, 1, 4, 1);
/* 125 */     this.Sting2.setRotationPoint(0.0F, 2.6F, 8.8F);
/* 126 */     setRotation(this.Sting2, -0.2230717F, 0.0F, 0.0F);
/*     */     
/* 128 */     this.LArm1 = new ModelRenderer(this, 26, 18);
/* 129 */     this.LArm1.addBox(-1.0F, -7.0F, -1.0F, 2, 7, 2);
/* 130 */     this.LArm1.setRotationPoint(5.0F, 18.0F, -8.0F);
/* 131 */     setRotation(this.LArm1, -0.3490659F, 0.0F, 0.8726646F);
/*     */     
/* 133 */     this.LArm2 = new ModelRenderer(this, 42, 55);
/* 134 */     this.LArm2.addBox(-1.5F, -1.5F, -6.0F, 3, 3, 6);
/* 135 */     this.LArm2.setRotationPoint(10.0F, 14.0F, -6.0F);
/* 136 */     setRotation(this.LArm2, 0.1745329F, -0.3490659F, -0.2617994F);
/*     */     
/* 138 */     this.LArm3 = new ModelRenderer(this, 42, 39);
/* 139 */     this.LArm3.addBox(-0.5F, -0.5F, -7.0F, 2, 1, 7);
/* 140 */     this.LArm3.setRotationPoint(12.0F, 15.0F, -11.0F);
/* 141 */     setRotation(this.LArm3, 0.2617994F, 0.1570796F, -0.1570796F);
/*     */     
/* 143 */     this.LArm4 = new ModelRenderer(this, 42, 31);
/* 144 */     this.LArm4.addBox(-1.5F, -0.5F, -6.0F, 1, 1, 7);
/* 145 */     this.LArm4.setRotationPoint(11.0F, 15.0F, -11.0F);
/* 146 */     setRotation(this.LArm4, 0.2617994F, 0.0F, -0.1570796F);
/*     */     
/* 148 */     this.RArm1 = new ModelRenderer(this, 0, 18);
/* 149 */     this.RArm1.addBox(-1.0F, -7.0F, -1.0F, 2, 7, 2);
/* 150 */     this.RArm1.setRotationPoint(-5.0F, 18.0F, -8.0F);
/* 151 */     setRotation(this.RArm1, -0.3490659F, 0.0F, -0.8726646F);
/*     */     
/* 153 */     this.RArm2 = new ModelRenderer(this, 42, 55);
/* 154 */     this.RArm2.addBox(-1.5F, -1.5F, -6.0F, 3, 3, 6);
/* 155 */     this.RArm2.setRotationPoint(-10.0F, 14.0F, -6.0F);
/* 156 */     setRotation(this.RArm2, 0.1745329F, 0.3490659F, 0.2617994F);
/*     */     
/* 158 */     this.RArm3 = new ModelRenderer(this, 42, 47);
/* 159 */     this.RArm3.addBox(-1.5F, -0.5F, -7.0F, 2, 1, 7);
/* 160 */     this.RArm3.setRotationPoint(-12.0F, 15.0F, -11.0F);
/* 161 */     setRotation(this.RArm3, 0.2617994F, -0.1570796F, 0.1570796F);
/*     */     
/* 163 */     this.RArm4 = new ModelRenderer(this, 42, 31);
/* 164 */     this.RArm4.addBox(0.5F, -0.5F, -6.0F, 1, 1, 7);
/* 165 */     this.RArm4.setRotationPoint(-11.0F, 15.0F, -11.0F);
/* 166 */     setRotation(this.RArm4, 0.2617994F, 0.0F, 0.1570796F);
/*     */     
/* 168 */     this.Leg1A = new ModelRenderer(this, 38, 0);
/* 169 */     this.Leg1A.addBox(-1.0F, -7.0F, -1.0F, 2, 7, 2);
/* 170 */     this.Leg1A.setRotationPoint(5.0F, 18.0F, -5.0F);
/* 171 */     setRotationG(this.Leg1A, -10.0F, 0.0F, 75.0F);
/*     */     
/* 173 */     this.Leg1B = new ModelRenderer(this, 50, 0);
/* 174 */     this.Leg1B.addBox(2.0F, -8.0F, -1.0F, 5, 2, 2);
/* 175 */     this.Leg1B.setRotationPoint(5.0F, 18.0F, -5.0F);
/* 176 */     setRotationG(this.Leg1B, -10.0F, 0.0F, 60.0F);
/*     */     
/* 178 */     this.Leg1C = new ModelRenderer(this, 52, 16);
/* 179 */     this.Leg1C.addBox(4.5F, -9.0F, -0.7F, 5, 1, 1);
/* 180 */     this.Leg1C.setRotationPoint(5.0F, 18.0F, -5.0F);
/* 181 */     setRotationG(this.Leg1C, -10.0F, 0.0F, 75.0F);
/*     */     
/* 183 */     this.Leg2A = new ModelRenderer(this, 38, 0);
/* 184 */     this.Leg2A.addBox(-1.0F, -7.0F, -1.0F, 2, 7, 2);
/* 185 */     this.Leg2A.setRotationPoint(5.0F, 18.0F, -2.0F);
/* 186 */     setRotationG(this.Leg2A, -30.0F, 0.0F, 70.0F);
/*     */     
/* 188 */     this.Leg2B = new ModelRenderer(this, 50, 4);
/* 189 */     this.Leg2B.addBox(1.0F, -8.0F, -1.0F, 5, 2, 2);
/* 190 */     this.Leg2B.setRotationPoint(5.0F, 18.0F, -2.0F);
/* 191 */     setRotationG(this.Leg2B, -30.0F, 0.0F, 60.0F);
/*     */     
/* 193 */     this.Leg2C = new ModelRenderer(this, 50, 18);
/* 194 */     this.Leg2C.addBox(4.0F, -8.5F, -1.0F, 6, 1, 1);
/* 195 */     this.Leg2C.setRotationPoint(5.0F, 18.0F, -2.0F);
/* 196 */     setRotationG(this.Leg2C, -30.0F, 0.0F, 70.0F);
/*     */     
/* 198 */     this.Leg3A = new ModelRenderer(this, 38, 0);
/* 199 */     this.Leg3A.addBox(-1.0F, -7.0F, -1.0F, 2, 7, 2);
/* 200 */     this.Leg3A.setRotationPoint(5.0F, 17.5F, 1.0F);
/* 201 */     setRotationG(this.Leg3A, -45.0F, 0.0F, 70.0F);
/*     */     
/* 203 */     this.Leg3B = new ModelRenderer(this, 48, 8);
/* 204 */     this.Leg3B.addBox(1.0F, -8.0F, -1.0F, 6, 2, 2);
/* 205 */     this.Leg3B.setRotationPoint(5.0F, 17.5F, 1.0F);
/* 206 */     setRotationG(this.Leg3B, -45.0F, 0.0F, 60.0F);
/*     */     
/* 208 */     this.Leg3C = new ModelRenderer(this, 50, 20);
/* 209 */     this.Leg3C.addBox(4.5F, -8.2F, -1.3F, 6, 1, 1);
/* 210 */     this.Leg3C.setRotationPoint(5.0F, 17.5F, 1.0F);
/* 211 */     setRotationG(this.Leg3C, -45.0F, 0.0F, 70.0F);
/*     */     
/* 213 */     this.Leg4A = new ModelRenderer(this, 38, 0);
/* 214 */     this.Leg4A.addBox(-1.0F, -7.0F, -1.0F, 2, 7, 2);
/* 215 */     this.Leg4A.setRotationPoint(5.0F, 17.0F, 4.0F);
/* 216 */     setRotationG(this.Leg4A, -60.0F, 0.0F, 70.0F);
/*     */     
/* 218 */     this.Leg4B = new ModelRenderer(this, 46, 12);
/* 219 */     this.Leg4B.addBox(0.5F, -8.5F, -1.0F, 7, 2, 2);
/* 220 */     this.Leg4B.setRotationPoint(5.0F, 17.0F, 4.0F);
/* 221 */     setRotationG(this.Leg4B, -60.0F, 0.0F, 60.0F);
/*     */     
/* 223 */     this.Leg4C = new ModelRenderer(this, 48, 22);
/* 224 */     this.Leg4C.addBox(3.5F, -8.5F, -1.5F, 7, 1, 1);
/* 225 */     this.Leg4C.setRotationPoint(5.0F, 17.0F, 4.0F);
/* 226 */     setRotationG(this.Leg4C, -60.0F, 0.0F, 70.0F);
/*     */     
/* 228 */     this.Leg5A = new ModelRenderer(this, 0, 0);
/* 229 */     this.Leg5A.addBox(-1.0F, -7.0F, -1.0F, 2, 7, 2);
/* 230 */     this.Leg5A.setRotationPoint(-5.0F, 18.0F, -5.0F);
/* 231 */     setRotationG(this.Leg5A, -10.0F, 0.0F, -75.0F);
/*     */     
/* 233 */     this.Leg5B = new ModelRenderer(this, 50, 0);
/* 234 */     this.Leg5B.addBox(-7.0F, -8.0F, -1.0F, 5, 2, 2);
/* 235 */     this.Leg5B.setRotationPoint(-5.0F, 18.0F, -5.0F);
/* 236 */     setRotationG(this.Leg5B, -10.0F, 0.0F, -60.0F);
/*     */     
/* 238 */     this.Leg5C = new ModelRenderer(this, 52, 16);
/* 239 */     this.Leg5C.addBox(-9.5F, -9.0F, -0.7F, 5, 1, 1);
/* 240 */     this.Leg5C.setRotationPoint(-5.0F, 18.0F, -5.0F);
/* 241 */     setRotationG(this.Leg5C, -10.0F, 0.0F, -75.0F);
/*     */     
/* 243 */     this.Leg6A = new ModelRenderer(this, 0, 0);
/* 244 */     this.Leg6A.addBox(-1.0F, -7.0F, -1.0F, 2, 7, 2);
/* 245 */     this.Leg6A.setRotationPoint(-5.0F, 18.0F, -2.0F);
/* 246 */     setRotationG(this.Leg6A, -30.0F, 0.0F, -70.0F);
/*     */     
/* 248 */     this.Leg6B = new ModelRenderer(this, 50, 4);
/* 249 */     this.Leg6B.addBox(-6.0F, -8.0F, -1.0F, 5, 2, 2);
/* 250 */     this.Leg6B.setRotationPoint(-5.0F, 18.0F, -2.0F);
/* 251 */     setRotationG(this.Leg6B, -30.0F, 0.0F, -60.0F);
/*     */     
/* 253 */     this.Leg6C = new ModelRenderer(this, 50, 18);
/* 254 */     this.Leg6C.addBox(-10.0F, -8.5F, -1.0F, 6, 1, 1);
/* 255 */     this.Leg6C.setRotationPoint(-5.0F, 18.0F, -2.0F);
/* 256 */     setRotationG(this.Leg6C, -30.0F, 0.0F, -60.0F);
/*     */     
/* 258 */     this.Leg7A = new ModelRenderer(this, 0, 0);
/* 259 */     this.Leg7A.addBox(-1.0F, -7.0F, -1.0F, 2, 7, 2);
/* 260 */     this.Leg7A.setRotationPoint(-5.0F, 17.5F, 1.0F);
/* 261 */     setRotationG(this.Leg7A, -45.0F, 0.0F, -70.0F);
/*     */     
/* 263 */     this.Leg7B = new ModelRenderer(this, 48, 8);
/* 264 */     this.Leg7B.addBox(-7.0F, -8.5F, -1.0F, 6, 2, 2);
/* 265 */     this.Leg7B.setRotationPoint(-5.0F, 17.5F, 1.0F);
/* 266 */     setRotationG(this.Leg7B, -45.0F, 0.0F, -60.0F);
/*     */     
/* 268 */     this.Leg7C = new ModelRenderer(this, 50, 20);
/* 269 */     this.Leg7C.addBox(-10.5F, -8.7F, -1.3F, 6, 1, 1);
/* 270 */     this.Leg7C.setRotationPoint(-5.0F, 17.5F, 1.0F);
/* 271 */     setRotationG(this.Leg7C, -45.0F, 0.0F, -70.0F);
/*     */     
/* 273 */     this.Leg8A = new ModelRenderer(this, 0, 0);
/* 274 */     this.Leg8A.addBox(-1.0F, -7.0F, -1.0F, 2, 7, 2);
/* 275 */     this.Leg8A.setRotationPoint(-5.0F, 17.0F, 4.0F);
/* 276 */     setRotationG(this.Leg8A, -60.0F, 0.0F, -70.0F);
/*     */     
/* 278 */     this.Leg8B = new ModelRenderer(this, 46, 12);
/* 279 */     this.Leg8B.addBox(-7.5F, -8.5F, -1.0F, 7, 2, 2);
/* 280 */     this.Leg8B.setRotationPoint(-5.0F, 17.0F, 4.0F);
/* 281 */     setRotationG(this.Leg8B, -60.0F, 0.0F, -60.0F);
/*     */     
/* 283 */     this.Leg8C = new ModelRenderer(this, 48, 22);
/* 284 */     this.Leg8C.addBox(-10.5F, -8.5F, -1.5F, 7, 1, 1);
/* 285 */     this.Leg8C.setRotationPoint(-5.0F, 17.0F, 4.0F);
/* 286 */     setRotationG(this.Leg8C, -60.0F, 0.0F, -70.0F);
/*     */     
/* 288 */     this.baby1 = new ModelRenderer(this, 48, 24);
/* 289 */     this.baby1.addBox(-1.5F, 0.0F, -2.5F, 3, 2, 5);
/* 290 */     this.baby1.setRotationPoint(0.0F, 12.0F, 0.0F);
/*     */     
/* 292 */     this.baby2 = new ModelRenderer(this, 48, 24);
/* 293 */     this.baby2.addBox(-1.5F, 0.0F, -2.5F, 3, 2, 5);
/* 294 */     this.baby2.setRotationPoint(-5.0F, 13.4F, -1.0F);
/* 295 */     setRotation(this.baby2, 0.4461433F, 2.490967F, 0.5205006F);
/*     */     
/* 297 */     this.baby3 = new ModelRenderer(this, 48, 24);
/* 298 */     this.baby3.addBox(-1.5F, 0.0F, -2.5F, 3, 2, 5);
/* 299 */     this.baby3.setRotationPoint(-2.0F, 13.0F, 4.0F);
/* 300 */     setRotation(this.baby3, 0.0F, 0.8551081F, 0.0F);
/*     */     
/* 302 */     this.baby4 = new ModelRenderer(this, 48, 24);
/* 303 */     this.baby4.addBox(-1.5F, 0.0F, -2.5F, 3, 2, 5);
/* 304 */     this.baby4.setRotationPoint(4.0F, 13.0F, 2.0F);
/* 305 */     setRotation(this.baby4, 0.0F, 2.714039F, -0.3717861F);
/*     */     
/* 307 */     this.baby5 = new ModelRenderer(this, 48, 24);
/* 308 */     this.baby5.addBox(-1.5F, 0.0F, -2.5F, 3, 2, 5);
/* 309 */     this.baby5.setRotationPoint(1.0F, 13.0F, 8.0F);
/* 310 */     setRotation(this.baby5, 0.0F, -1.189716F, 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 315 */     MoCEntityScorpion scorpy = (MoCEntityScorpion)entity;
/* 316 */     this.poisoning = scorpy.swingingTail();
/* 317 */     this.isTalking = (scorpy.mouthCounter != 0);
/* 318 */     this.babies = scorpy.getHasBabies();
/* 319 */     this.attacking = scorpy.armCounter;
/* 320 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/* 321 */     renderParts(f5);
/*     */   }
/*     */   
/*     */   protected void renderParts(float f5) {
/* 325 */     this.Head.render(f5);
/* 326 */     this.MouthL.render(f5);
/* 327 */     this.MouthR.render(f5);
/* 328 */     this.Body.render(f5);
/* 329 */     this.Tail1.render(f5);
/* 330 */     this.Tail2.render(f5);
/* 331 */     this.Tail3.render(f5);
/* 332 */     this.Tail4.render(f5);
/* 333 */     this.Tail5.render(f5);
/* 334 */     this.Sting1.render(f5);
/* 335 */     this.Sting2.render(f5);
/* 336 */     this.LArm1.render(f5);
/* 337 */     this.LArm2.render(f5);
/* 338 */     this.LArm3.render(f5);
/* 339 */     this.LArm4.render(f5);
/* 340 */     this.RArm1.render(f5);
/* 341 */     this.RArm2.render(f5);
/* 342 */     this.RArm3.render(f5);
/* 343 */     this.RArm4.render(f5);
/* 344 */     this.Leg1A.render(f5);
/* 345 */     this.Leg1B.render(f5);
/* 346 */     this.Leg1C.render(f5);
/* 347 */     this.Leg2A.render(f5);
/* 348 */     this.Leg2B.render(f5);
/* 349 */     this.Leg2C.render(f5);
/* 350 */     this.Leg3A.render(f5);
/* 351 */     this.Leg3B.render(f5);
/* 352 */     this.Leg3C.render(f5);
/* 353 */     this.Leg4A.render(f5);
/* 354 */     this.Leg4B.render(f5);
/* 355 */     this.Leg4C.render(f5);
/* 356 */     this.Leg5A.render(f5);
/* 357 */     this.Leg5B.render(f5);
/* 358 */     this.Leg5C.render(f5);
/* 359 */     this.Leg6A.render(f5);
/* 360 */     this.Leg6B.render(f5);
/* 361 */     this.Leg6C.render(f5);
/* 362 */     this.Leg7A.render(f5);
/* 363 */     this.Leg7B.render(f5);
/* 364 */     this.Leg7C.render(f5);
/* 365 */     this.Leg8A.render(f5);
/* 366 */     this.Leg8B.render(f5);
/* 367 */     this.Leg8C.render(f5);
/* 368 */     if (this.babies) {
/* 369 */       this.baby1.render(f5);
/* 370 */       this.baby2.render(f5);
/* 371 */       this.baby3.render(f5);
/* 372 */       this.baby4.render(f5);
/* 373 */       this.baby5.render(f5);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 378 */     model.rotateAngleX = x;
/* 379 */     model.rotateAngleY = y;
/* 380 */     model.rotateAngleZ = z;
/*     */   }
/*     */   
/*     */   private void setRotationG(ModelRenderer model, float x, float y, float z) {
/* 384 */     model.rotateAngleX = x / this.radianF;
/* 385 */     model.rotateAngleY = y / this.radianF;
/* 386 */     model.rotateAngleZ = z / this.radianF;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 392 */     if (!this.poisoning) {
/* 393 */       this.Body.rotateAngleX = 5.0F / this.radianF;
/* 394 */       this.Tail1.rotateAngleX = 35.0F / this.radianF;
/* 395 */       this.Tail1.rotationPointY = 16.0F;
/* 396 */       this.Tail1.rotationPointZ = 12.0F;
/*     */       
/* 398 */       this.Tail2.rotateAngleX = 65.0F / this.radianF;
/* 399 */       this.Tail2.rotationPointY = 13.0F;
/* 400 */       this.Tail2.rotationPointZ = 16.5F;
/*     */       
/* 402 */       this.Tail3.rotateAngleX = 90.0F / this.radianF;
/* 403 */       this.Tail3.rotationPointY = 8.0F;
/* 404 */       this.Tail3.rotationPointZ = 18.5F;
/*     */       
/* 406 */       this.Tail4.rotateAngleX = 143.0F / this.radianF;
/* 407 */       this.Tail4.rotationPointY = 3.0F;
/* 408 */       this.Tail4.rotationPointZ = 18.0F;
/*     */       
/* 410 */       this.Tail5.rotateAngleX = 175.0F / this.radianF;
/* 411 */       this.Tail5.rotationPointY = -0.2F;
/* 412 */       this.Tail5.rotationPointZ = 14.0F;
/*     */       
/* 414 */       this.Sting1.rotateAngleX = 24.0F / this.radianF;
/* 415 */       this.Sting1.rotationPointY = -1.0F;
/* 416 */       this.Sting1.rotationPointZ = 7.0F;
/*     */       
/* 418 */       this.Sting2.rotateAngleX = -12.0F / this.radianF;
/* 419 */       this.Sting2.rotationPointY = 2.6F;
/* 420 */       this.Sting2.rotationPointZ = 8.8F;
/*     */     } else {
/*     */       
/* 423 */       this.Body.rotateAngleX = 50.0F / this.radianF;
/* 424 */       this.Tail1.rotateAngleX = 100.0F / this.radianF;
/* 425 */       this.Tail1.rotationPointY = 9.0F;
/* 426 */       this.Tail1.rotationPointZ = 10.0F;
/*     */       
/* 428 */       this.Tail2.rotateAngleX = 160.0F / this.radianF;
/* 429 */       this.Tail2.rotationPointY = 3.0F;
/* 430 */       this.Tail2.rotationPointZ = 9.5F;
/*     */       
/* 432 */       this.Tail3.rotateAngleX = -170.0F / this.radianF;
/* 433 */       this.Tail3.rotationPointY = 1.0F;
/* 434 */       this.Tail3.rotationPointZ = 3.5F;
/*     */       
/* 436 */       this.Tail4.rotateAngleX = -156.0F / this.radianF;
/* 437 */       this.Tail4.rotationPointY = 1.8F;
/* 438 */       this.Tail4.rotationPointZ = -2.0F;
/*     */       
/* 440 */       this.Tail5.rotateAngleX = -154.0F / this.radianF;
/* 441 */       this.Tail5.rotationPointY = 3.8F;
/* 442 */       this.Tail5.rotationPointZ = -7.0F;
/*     */       
/* 444 */       this.Sting1.rotateAngleX = -57.0F / this.radianF;
/* 445 */       this.Sting1.rotationPointY = 6.0F;
/* 446 */       this.Sting1.rotationPointZ = -12.0F;
/*     */       
/* 448 */       this.Sting2.rotateAngleX = -93.7F / this.radianF;
/* 449 */       this.Sting2.rotationPointY = 8.0F;
/* 450 */       this.Sting2.rotationPointZ = -15.2F;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 456 */     float MouthRot = 0.0F;
/* 457 */     if (this.isTalking) {
/* 458 */       MouthRot = MathHelper.cos(f2 * 1.1F) * 0.2F;
/*     */     }
/* 460 */     this.MouthR.rotateAngleY = 22.0F / this.radianF + MouthRot;
/* 461 */     this.MouthL.rotateAngleY = -22.0F / this.radianF - MouthRot;
/*     */ 
/*     */     
/* 464 */     this.LArm1.rotateAngleX = -20.0F / this.radianF;
/* 465 */     this.LArm2.rotationPointX = 10.0F;
/* 466 */     this.LArm2.rotationPointY = 14.0F;
/* 467 */     this.LArm2.rotationPointZ = -6.0F;
/* 468 */     this.LArm3.rotationPointX = 12.0F;
/* 469 */     this.LArm3.rotationPointY = 15.0F;
/* 470 */     this.LArm3.rotationPointZ = -11.0F;
/* 471 */     this.LArm4.rotationPointX = 11.0F;
/* 472 */     this.LArm4.rotationPointY = 15.0F;
/* 473 */     this.LArm4.rotationPointZ = -11.0F;
/* 474 */     this.LArm4.rotateAngleY = 0.0F;
/*     */ 
/*     */     
/* 477 */     this.RArm1.rotateAngleX = -20.0F / this.radianF;
/* 478 */     this.RArm2.rotationPointX = -10.0F;
/* 479 */     this.RArm2.rotationPointY = 14.0F;
/* 480 */     this.RArm2.rotationPointZ = -6.0F;
/* 481 */     this.RArm3.rotationPointX = -12.0F;
/* 482 */     this.RArm3.rotationPointY = 15.0F;
/* 483 */     this.RArm3.rotationPointZ = -11.0F;
/* 484 */     this.RArm4.rotationPointX = -11.0F;
/* 485 */     this.RArm4.rotationPointY = 15.0F;
/* 486 */     this.RArm4.rotationPointZ = -11.0F;
/* 487 */     this.RArm4.rotateAngleY = 0.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 492 */     if (this.attacking == 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 497 */       float lHand = 0.0F;
/*     */       
/* 499 */       float f2a = f2 % 100.0F;
/* 500 */       if ((((f2a > 0.0F) ? 1 : 0) & ((f2a < 20.0F) ? 1 : 0)) != 0) {
/* 501 */         lHand = f2a / this.radianF;
/*     */       }
/* 503 */       this.LArm3.rotateAngleY = 9.0F / this.radianF - lHand;
/* 504 */       this.LArm4.rotateAngleY = lHand;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 509 */       float RHand = 0.0F;
/* 510 */       float f2b = f2 % 75.0F;
/* 511 */       if ((((f2b > 30.0F) ? 1 : 0) & ((f2b < 50.0F) ? 1 : 0)) != 0) {
/* 512 */         RHand = (f2b - 29.0F) / this.radianF;
/*     */       }
/* 514 */       this.RArm3.rotateAngleY = -9.0F / this.radianF + RHand;
/* 515 */       this.RArm4.rotateAngleY = -RHand;
/*     */     }
/*     */     else {
/*     */       
/* 519 */       if (this.attacking > 0 && this.attacking < 5) {
/*     */         
/* 521 */         this.LArm1.rotateAngleX = 50.0F / this.radianF;
/* 522 */         this.LArm2.rotationPointX = 8.0F;
/* 523 */         this.LArm2.rotationPointY = 15.0F;
/* 524 */         this.LArm2.rotationPointZ = -13.0F;
/* 525 */         this.LArm3.rotationPointX = 10.0F;
/* 526 */         this.LArm3.rotationPointY = 16.0F;
/* 527 */         this.LArm3.rotationPointZ = -18.0F;
/* 528 */         this.LArm4.rotationPointX = 9.0F;
/* 529 */         this.LArm4.rotationPointY = 16.0F;
/* 530 */         this.LArm4.rotationPointZ = -18.0F;
/* 531 */         this.LArm4.rotateAngleY = 40.0F / this.radianF;
/*     */       } 
/*     */       
/* 534 */       if (this.attacking >= 5 && this.attacking < 10) {
/*     */         
/* 536 */         this.LArm1.rotateAngleX = 70.0F / this.radianF;
/* 537 */         this.LArm2.rotationPointX = 7.0F;
/* 538 */         this.LArm2.rotationPointY = 16.0F;
/* 539 */         this.LArm2.rotationPointZ = -14.0F;
/* 540 */         this.LArm3.rotationPointX = 9.0F;
/* 541 */         this.LArm3.rotationPointY = 17.0F;
/* 542 */         this.LArm3.rotationPointZ = -19.0F;
/* 543 */         this.LArm4.rotationPointX = 8.0F;
/* 544 */         this.LArm4.rotationPointY = 17.0F;
/* 545 */         this.LArm4.rotationPointZ = -19.0F;
/* 546 */         this.LArm4.rotateAngleY = 0.0F;
/*     */       } 
/* 548 */       if (this.attacking >= 10 && this.attacking < 15) {
/*     */         
/* 550 */         this.RArm1.rotateAngleX = 50.0F / this.radianF;
/* 551 */         this.RArm2.rotationPointX = -8.0F;
/* 552 */         this.RArm2.rotationPointY = 15.0F;
/* 553 */         this.RArm2.rotationPointZ = -13.0F;
/* 554 */         this.RArm3.rotationPointX = -10.0F;
/* 555 */         this.RArm3.rotationPointY = 16.0F;
/* 556 */         this.RArm3.rotationPointZ = -18.0F;
/* 557 */         this.RArm4.rotationPointX = -9.0F;
/* 558 */         this.RArm4.rotationPointY = 16.0F;
/* 559 */         this.RArm4.rotationPointZ = -18.0F;
/* 560 */         this.RArm4.rotateAngleY = -40.0F / this.radianF;
/*     */       } 
/* 562 */       if (this.attacking >= 15 && this.attacking < 20) {
/*     */         
/* 564 */         this.RArm1.rotateAngleX = 70.0F / this.radianF;
/* 565 */         this.RArm2.rotationPointX = -7.0F;
/* 566 */         this.RArm2.rotationPointY = 16.0F;
/* 567 */         this.RArm2.rotationPointZ = -14.0F;
/* 568 */         this.RArm3.rotationPointX = -9.0F;
/* 569 */         this.RArm3.rotationPointY = 17.0F;
/* 570 */         this.RArm3.rotationPointZ = -19.0F;
/* 571 */         this.RArm4.rotationPointX = -8.0F;
/* 572 */         this.RArm4.rotationPointY = 17.0F;
/* 573 */         this.RArm4.rotationPointZ = -19.0F;
/* 574 */         this.RArm4.rotateAngleY = 0.0F;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 581 */     if (this.babies) {
/* 582 */       float fmov = f2 % 100.0F;
/* 583 */       float fb1 = 0.0F;
/* 584 */       float fb2 = 142.0F / this.radianF;
/* 585 */       float fb3 = 49.0F / this.radianF;
/* 586 */       float fb4 = 155.0F / this.radianF;
/* 587 */       float fb5 = -68.0F / this.radianF;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 595 */       if (fmov > 0.0F && fmov < 20.0F) {
/* 596 */         fb2 -= MathHelper.cos(f2 * 0.8F) * 0.3F;
/* 597 */         fb3 -= MathHelper.cos(f2 * 0.6F) * 0.2F;
/* 598 */         fb1 += MathHelper.cos(f2 * 0.4F) * 0.4F;
/* 599 */         fb5 += MathHelper.cos(f2 * 0.7F) * 0.5F;
/*     */       } 
/*     */       
/* 602 */       if (fmov > 30.0F && fmov < 50.0F) {
/* 603 */         fb4 -= MathHelper.cos(f2 * 0.8F) * 0.4F;
/* 604 */         fb1 += MathHelper.cos(f2 * 0.7F) * 0.1F;
/* 605 */         fb3 -= MathHelper.cos(f2 * 0.6F) * 0.2F;
/*     */       } 
/* 607 */       if (fmov > 80.0F) {
/* 608 */         fb5 += MathHelper.cos(f2 * 0.2F) * 0.4F;
/* 609 */         fb2 -= MathHelper.cos(f2 * 0.6F) * 0.3F;
/* 610 */         fb4 -= MathHelper.cos(f2 * 0.4F) * 0.2F;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 618 */       this.baby1.rotateAngleY = fb1;
/* 619 */       this.baby2.rotateAngleY = fb2;
/* 620 */       this.baby3.rotateAngleY = fb3;
/* 621 */       this.baby4.rotateAngleY = fb4;
/* 622 */       this.baby5.rotateAngleY = fb5;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 628 */     float f9 = -(MathHelper.cos(f * 0.6662F * 2.0F + 0.0F) * 0.4F) * f1;
/* 629 */     float f10 = -(MathHelper.cos(f * 0.6662F * 2.0F + 3.141593F) * 0.4F) * f1;
/* 630 */     float f11 = -(MathHelper.cos(f * 0.6662F * 2.0F + 1.570796F) * 0.4F) * f1;
/* 631 */     float f12 = -(MathHelper.cos(f * 0.6662F * 2.0F + 4.712389F) * 0.4F) * f1;
/* 632 */     float f13 = Math.abs(MathHelper.sin(f * 0.6662F + 0.0F) * 0.4F) * f1;
/* 633 */     float f14 = Math.abs(MathHelper.sin(f * 0.6662F + 3.141593F) * 0.4F) * f1;
/* 634 */     float f15 = Math.abs(MathHelper.sin(f * 0.6662F + 1.570796F) * 0.4F) * f1;
/* 635 */     float f16 = Math.abs(MathHelper.sin(f * 0.6662F + 4.712389F) * 0.4F) * f1;
/*     */     
/* 637 */     if (this.sitting) {
/* 638 */       this.Leg1A.rotateAngleX = -10.0F / this.radianF;
/* 639 */       this.Leg1A.rotateAngleZ = 35.0F / this.radianF;
/* 640 */       this.Leg1B.rotateAngleZ = 20.0F / this.radianF;
/* 641 */       this.Leg1C.rotateAngleZ = 35.0F / this.radianF;
/*     */       
/* 643 */       this.Leg2A.rotateAngleX = -30.0F / this.radianF;
/* 644 */       this.Leg2A.rotateAngleZ = 35.0F / this.radianF;
/* 645 */       this.Leg2B.rotateAngleZ = 20.0F / this.radianF;
/* 646 */       this.Leg2C.rotateAngleZ = 35.0F / this.radianF;
/*     */       
/* 648 */       this.Leg3A.rotateAngleX = -45.0F / this.radianF;
/* 649 */       this.Leg3A.rotateAngleZ = 35.0F / this.radianF;
/* 650 */       this.Leg3B.rotateAngleZ = 20.0F / this.radianF;
/* 651 */       this.Leg3C.rotateAngleZ = 35.0F / this.radianF;
/*     */       
/* 653 */       this.Leg4A.rotateAngleX = -60.0F / this.radianF;
/* 654 */       this.Leg4A.rotateAngleZ = 35.0F / this.radianF;
/* 655 */       this.Leg4B.rotateAngleZ = 20.0F / this.radianF;
/* 656 */       this.Leg4C.rotateAngleZ = 35.0F / this.radianF;
/*     */       
/* 658 */       this.Leg5A.rotateAngleX = -10.0F / this.radianF;
/* 659 */       this.Leg5A.rotateAngleZ = -35.0F / this.radianF;
/* 660 */       this.Leg5B.rotateAngleZ = -20.0F / this.radianF;
/* 661 */       this.Leg5C.rotateAngleZ = -35.0F / this.radianF;
/*     */       
/* 663 */       this.Leg6A.rotateAngleX = -30.0F / this.radianF;
/* 664 */       this.Leg6A.rotateAngleZ = -35.0F / this.radianF;
/* 665 */       this.Leg6B.rotateAngleZ = -20.0F / this.radianF;
/* 666 */       this.Leg6C.rotateAngleZ = -35.0F / this.radianF;
/*     */       
/* 668 */       this.Leg7A.rotateAngleX = -45.0F / this.radianF;
/* 669 */       this.Leg7A.rotateAngleZ = -35.0F / this.radianF;
/* 670 */       this.Leg7B.rotateAngleZ = -20.0F / this.radianF;
/* 671 */       this.Leg7C.rotateAngleZ = -35.0F / this.radianF;
/*     */       
/* 673 */       this.Leg8A.rotateAngleX = -60.0F / this.radianF;
/* 674 */       this.Leg8A.rotateAngleZ = -35.0F / this.radianF;
/* 675 */       this.Leg8B.rotateAngleZ = -20.0F / this.radianF;
/* 676 */       this.Leg8C.rotateAngleZ = -35.0F / this.radianF;
/*     */     } else {
/*     */       
/* 679 */       this.Leg1A.rotateAngleX = -10.0F / this.radianF;
/* 680 */       this.Leg1A.rotateAngleZ = 75.0F / this.radianF;
/* 681 */       this.Leg1B.rotateAngleZ = 60.0F / this.radianF;
/* 682 */       this.Leg1C.rotateAngleZ = 75.0F / this.radianF;
/*     */       
/* 684 */       this.Leg1A.rotateAngleX += f9;
/* 685 */       this.Leg1B.rotateAngleX = this.Leg1A.rotateAngleX;
/* 686 */       this.Leg1C.rotateAngleX = this.Leg1A.rotateAngleX;
/*     */       
/* 688 */       this.Leg1A.rotateAngleZ += f13;
/* 689 */       this.Leg1B.rotateAngleZ += f13;
/* 690 */       this.Leg1C.rotateAngleZ += f13;
/*     */       
/* 692 */       this.Leg2A.rotateAngleX = -30.0F / this.radianF;
/* 693 */       this.Leg2A.rotateAngleZ = 70.0F / this.radianF;
/* 694 */       this.Leg2B.rotateAngleZ = 60.0F / this.radianF;
/* 695 */       this.Leg2C.rotateAngleZ = 70.0F / this.radianF;
/*     */       
/* 697 */       this.Leg2A.rotateAngleX += f10;
/* 698 */       this.Leg2B.rotateAngleX = this.Leg2A.rotateAngleX;
/* 699 */       this.Leg2C.rotateAngleX = this.Leg2A.rotateAngleX;
/*     */       
/* 701 */       this.Leg2A.rotateAngleZ += f14;
/* 702 */       this.Leg2B.rotateAngleZ += f14;
/* 703 */       this.Leg2C.rotateAngleZ += f14;
/*     */       
/* 705 */       this.Leg3A.rotateAngleX = -45.0F / this.radianF;
/* 706 */       this.Leg3A.rotateAngleZ = 70.0F / this.radianF;
/* 707 */       this.Leg3B.rotateAngleZ = 60.0F / this.radianF;
/* 708 */       this.Leg3C.rotateAngleZ = 70.0F / this.radianF;
/*     */       
/* 710 */       this.Leg3A.rotateAngleX += f11;
/* 711 */       this.Leg3B.rotateAngleX = this.Leg3A.rotateAngleX;
/* 712 */       this.Leg3C.rotateAngleX = this.Leg3A.rotateAngleX;
/*     */       
/* 714 */       this.Leg3A.rotateAngleZ += f15;
/* 715 */       this.Leg3B.rotateAngleZ += f15;
/* 716 */       this.Leg3C.rotateAngleZ += f15;
/*     */       
/* 718 */       this.Leg4A.rotateAngleX = -60.0F / this.radianF;
/* 719 */       this.Leg4A.rotateAngleZ = 70.0F / this.radianF;
/* 720 */       this.Leg4B.rotateAngleZ = 60.0F / this.radianF;
/* 721 */       this.Leg4C.rotateAngleZ = 70.0F / this.radianF;
/*     */       
/* 723 */       this.Leg4A.rotateAngleX += f12;
/* 724 */       this.Leg4B.rotateAngleX = this.Leg4A.rotateAngleX;
/* 725 */       this.Leg4C.rotateAngleX = this.Leg4A.rotateAngleX;
/*     */       
/* 727 */       this.Leg4A.rotateAngleZ += f16;
/* 728 */       this.Leg4B.rotateAngleZ += f16;
/* 729 */       this.Leg4C.rotateAngleZ += f16;
/*     */       
/* 731 */       this.Leg5A.rotateAngleX = -10.0F / this.radianF;
/* 732 */       this.Leg5A.rotateAngleZ = -75.0F / this.radianF;
/* 733 */       this.Leg5B.rotateAngleZ = -60.0F / this.radianF;
/* 734 */       this.Leg5C.rotateAngleZ = -75.0F / this.radianF;
/*     */       
/* 736 */       this.Leg5A.rotateAngleX -= f9;
/* 737 */       this.Leg5B.rotateAngleX = this.Leg5A.rotateAngleX;
/* 738 */       this.Leg5C.rotateAngleX = this.Leg5A.rotateAngleX;
/*     */       
/* 740 */       this.Leg5A.rotateAngleZ -= f13;
/* 741 */       this.Leg5B.rotateAngleZ -= f13;
/* 742 */       this.Leg5C.rotateAngleZ -= f13;
/*     */       
/* 744 */       this.Leg6A.rotateAngleX = -30.0F / this.radianF;
/* 745 */       this.Leg6A.rotateAngleZ = -70.0F / this.radianF;
/* 746 */       this.Leg6B.rotateAngleZ = -60.0F / this.radianF;
/* 747 */       this.Leg6C.rotateAngleZ = -70.0F / this.radianF;
/*     */       
/* 749 */       this.Leg6A.rotateAngleX -= f10;
/* 750 */       this.Leg6B.rotateAngleX = this.Leg6A.rotateAngleX;
/* 751 */       this.Leg6C.rotateAngleX = this.Leg6A.rotateAngleX;
/*     */       
/* 753 */       this.Leg6A.rotateAngleZ -= f14;
/* 754 */       this.Leg6B.rotateAngleZ -= f14;
/* 755 */       this.Leg6C.rotateAngleZ -= f14;
/*     */       
/* 757 */       this.Leg7A.rotateAngleX = -45.0F / this.radianF;
/* 758 */       this.Leg7A.rotateAngleZ = -70.0F / this.radianF;
/* 759 */       this.Leg7B.rotateAngleZ = -60.0F / this.radianF;
/* 760 */       this.Leg7C.rotateAngleZ = -70.0F / this.radianF;
/*     */       
/* 762 */       this.Leg7A.rotateAngleX -= f11;
/* 763 */       this.Leg7B.rotateAngleX = this.Leg7A.rotateAngleX;
/* 764 */       this.Leg7C.rotateAngleX = this.Leg7A.rotateAngleX;
/*     */       
/* 766 */       this.Leg7A.rotateAngleZ -= f15;
/* 767 */       this.Leg7B.rotateAngleZ -= f15;
/* 768 */       this.Leg7C.rotateAngleZ -= f15;
/*     */       
/* 770 */       this.Leg8A.rotateAngleX = -60.0F / this.radianF;
/* 771 */       this.Leg8A.rotateAngleZ = -70.0F / this.radianF;
/* 772 */       this.Leg8B.rotateAngleZ = -60.0F / this.radianF;
/* 773 */       this.Leg8C.rotateAngleZ = -70.0F / this.radianF;
/*     */       
/* 775 */       this.Leg8A.rotateAngleX -= f12;
/* 776 */       this.Leg8B.rotateAngleX = this.Leg8A.rotateAngleX;
/* 777 */       this.Leg8C.rotateAngleX = this.Leg8A.rotateAngleX;
/*     */       
/* 779 */       this.Leg8A.rotateAngleZ -= f16;
/* 780 */       this.Leg8B.rotateAngleZ -= f16;
/* 781 */       this.Leg8C.rotateAngleZ -= f16;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelScorpion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */