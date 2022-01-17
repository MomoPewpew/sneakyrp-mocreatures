/*     */ package drzhark.mocreatures.client.model;
/*     */ 
/*     */ import drzhark.mocreatures.entity.monster.MoCEntityWWolf;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MoCModelWolf
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer Head;
/*     */   ModelRenderer MouthB;
/*     */   ModelRenderer Nose2;
/*     */   ModelRenderer Neck;
/*     */   ModelRenderer Neck2;
/*     */   ModelRenderer LSide;
/*     */   ModelRenderer RSide;
/*     */   ModelRenderer REar2;
/*     */   ModelRenderer Nose;
/*     */   ModelRenderer Mouth;
/*     */   ModelRenderer MouthOpen;
/*     */   ModelRenderer REar;
/*     */   ModelRenderer LEar2;
/*     */   ModelRenderer LEar;
/*     */   ModelRenderer UTeeth;
/*     */   ModelRenderer LTeeth;
/*     */   ModelRenderer Chest;
/*     */   ModelRenderer Body;
/*     */   ModelRenderer TailA;
/*     */   ModelRenderer TailB;
/*     */   ModelRenderer TailC;
/*     */   ModelRenderer TailD;
/*     */   ModelRenderer Leg4A;
/*     */   ModelRenderer Leg4D;
/*     */   ModelRenderer Leg4B;
/*     */   ModelRenderer Leg4C;
/*     */   ModelRenderer Leg3B;
/*     */   ModelRenderer Leg2A;
/*     */   ModelRenderer Leg2B;
/*     */   ModelRenderer Leg2C;
/*     */   ModelRenderer Leg3D;
/*     */   ModelRenderer Leg3C;
/*     */   ModelRenderer Leg3A;
/*     */   ModelRenderer Leg1A;
/*     */   ModelRenderer Leg1B;
/*     */   ModelRenderer Leg1C;
/*     */   
/*     */   public MoCModelWolf() {
/*  55 */     this.textureWidth = 64;
/*  56 */     this.textureHeight = 128;
/*     */     
/*  58 */     this.Head = new ModelRenderer(this, 0, 0);
/*  59 */     this.Head.addBox(-4.0F, -3.0F, -6.0F, 8, 8, 6);
/*  60 */     this.Head.setRotationPoint(0.0F, 7.0F, -10.0F);
/*     */     
/*  62 */     this.MouthB = new ModelRenderer(this, 16, 33);
/*  63 */     this.MouthB.addBox(-2.0F, 4.0F, -7.0F, 4, 1, 2);
/*  64 */     this.MouthB.setRotationPoint(0.0F, 7.0F, -10.0F);
/*     */     
/*  66 */     this.Nose2 = new ModelRenderer(this, 0, 25);
/*  67 */     this.Nose2.addBox(-2.0F, 2.0F, -12.0F, 4, 2, 6);
/*  68 */     this.Nose2.setRotationPoint(0.0F, 7.0F, -10.0F);
/*     */     
/*  70 */     this.Neck = new ModelRenderer(this, 28, 0);
/*  71 */     this.Neck.addBox(-3.5F, -3.0F, -7.0F, 7, 8, 7);
/*  72 */     this.Neck.setRotationPoint(0.0F, 10.0F, -6.0F);
/*  73 */     setRotation(this.Neck, -0.4537856F, 0.0F, 0.0F);
/*     */     
/*  75 */     this.Neck2 = new ModelRenderer(this, 0, 14);
/*  76 */     this.Neck2.addBox(-1.5F, -2.0F, -5.0F, 3, 4, 7);
/*  77 */     this.Neck2.setRotationPoint(0.0F, 14.0F, -10.0F);
/*  78 */     setRotation(this.Neck2, -0.4537856F, 0.0F, 0.0F);
/*     */     
/*  80 */     this.LSide = new ModelRenderer(this, 28, 33);
/*  81 */     this.LSide.addBox(3.0F, -0.5F, -2.0F, 2, 6, 6);
/*  82 */     this.LSide.setRotationPoint(0.0F, 7.0F, -10.0F);
/*  83 */     setRotation(this.LSide, -0.2094395F, 0.418879F, -0.0872665F);
/*     */     
/*  85 */     this.RSide = new ModelRenderer(this, 28, 45);
/*  86 */     this.RSide.addBox(-5.0F, -0.5F, -2.0F, 2, 6, 6);
/*  87 */     this.RSide.setRotationPoint(0.0F, 7.0F, -10.0F);
/*  88 */     setRotation(this.RSide, -0.2094395F, -0.418879F, 0.0872665F);
/*     */     
/*  90 */     this.Nose = new ModelRenderer(this, 44, 33);
/*  91 */     this.Nose.addBox(-1.5F, -1.8F, -12.4F, 3, 2, 7);
/*  92 */     this.Nose.setRotationPoint(0.0F, 7.0F, -10.0F);
/*  93 */     setRotation(this.Nose, 0.2792527F, 0.0F, 0.0F);
/*     */     
/*  95 */     this.Mouth = new ModelRenderer(this, 1, 34);
/*  96 */     this.Mouth.addBox(-2.0F, 4.0F, -11.5F, 4, 1, 5);
/*  97 */     this.Mouth.setRotationPoint(0.0F, 7.0F, -10.0F);
/*     */     
/*  99 */     this.UTeeth = new ModelRenderer(this, 46, 18);
/* 100 */     this.UTeeth.addBox(-2.0F, 4.0F, -12.0F, 4, 2, 5);
/* 101 */     this.UTeeth.setRotationPoint(0.0F, 7.0F, -10.0F);
/*     */     
/* 103 */     this.LTeeth = new ModelRenderer(this, 20, 109);
/* 104 */     this.LTeeth.addBox(-1.5F, -12.9F, 1.2F, 3, 5, 2);
/* 105 */     this.LTeeth.setRotationPoint(0.0F, 7.0F, -10.0F);
/* 106 */     setRotation(this.LTeeth, 2.5307274F, 0.0F, 0.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 113 */     this.MouthOpen = new ModelRenderer(this, 42, 69);
/* 114 */     this.MouthOpen.addBox(-1.5F, -12.9F, -0.81F, 3, 9, 2);
/* 115 */     this.MouthOpen.setRotationPoint(0.0F, 7.0F, -10.0F);
/* 116 */     setRotation(this.MouthOpen, 2.5307274F, 0.0F, 0.0F);
/*     */     
/* 118 */     this.REar = new ModelRenderer(this, 22, 0);
/* 119 */     this.REar.addBox(-3.5F, -7.0F, -1.5F, 3, 5, 1);
/* 120 */     this.REar.setRotationPoint(0.0F, 7.0F, -10.0F);
/* 121 */     setRotation(this.REar, 0.0F, 0.0F, -0.1745329F);
/*     */     
/* 123 */     this.LEar = new ModelRenderer(this, 13, 14);
/* 124 */     this.LEar.addBox(0.5F, -7.0F, -1.5F, 3, 5, 1);
/* 125 */     this.LEar.setRotationPoint(0.0F, 7.0F, -10.0F);
/* 126 */     setRotation(this.LEar, 0.0F, 0.0F, 0.1745329F);
/*     */     
/* 128 */     this.Chest = new ModelRenderer(this, 20, 15);
/* 129 */     this.Chest.addBox(-4.0F, -11.0F, -12.0F, 8, 8, 10);
/* 130 */     this.Chest.setRotationPoint(0.0F, 5.0F, 2.0F);
/* 131 */     setRotation(this.Chest, 1.570796F, 0.0F, 0.0F);
/*     */     
/* 133 */     this.Body = new ModelRenderer(this, 0, 40);
/* 134 */     this.Body.addBox(-3.0F, -8.0F, -9.0F, 6, 16, 8);
/* 135 */     this.Body.setRotationPoint(0.0F, 6.5F, 2.0F);
/* 136 */     setRotation(this.Body, 1.570796F, 0.0F, 0.0F);
/*     */     
/* 138 */     this.TailA = new ModelRenderer(this, 52, 42);
/* 139 */     this.TailA.addBox(-1.5F, 0.0F, -1.5F, 3, 4, 3);
/* 140 */     this.TailA.setRotationPoint(0.0F, 8.5F, 9.0F);
/* 141 */     setRotation(this.TailA, 1.064651F, 0.0F, 0.0F);
/*     */     
/* 143 */     this.TailB = new ModelRenderer(this, 48, 49);
/* 144 */     this.TailB.addBox(-2.0F, 3.0F, -1.0F, 4, 6, 4);
/* 145 */     this.TailB.setRotationPoint(0.0F, 8.5F, 9.0F);
/* 146 */     setRotation(this.TailB, 0.7504916F, 0.0F, 0.0F);
/*     */     
/* 148 */     this.TailC = new ModelRenderer(this, 48, 59);
/* 149 */     this.TailC.addBox(-2.0F, 7.8F, -4.1F, 4, 6, 4);
/* 150 */     this.TailC.setRotationPoint(0.0F, 8.5F, 9.0F);
/* 151 */     setRotation(this.TailC, 1.099557F, 0.0F, 0.0F);
/*     */     
/* 153 */     this.TailD = new ModelRenderer(this, 52, 69);
/* 154 */     this.TailD.addBox(-1.5F, 9.8F, -3.6F, 3, 5, 3);
/* 155 */     this.TailD.setRotationPoint(0.0F, 8.5F, 9.0F);
/* 156 */     setRotation(this.TailD, 1.099557F, 0.0F, 0.0F);
/*     */     
/* 158 */     this.Leg1A = new ModelRenderer(this, 28, 57);
/* 159 */     this.Leg1A.addBox(0.01F, -4.0F, -2.5F, 2, 8, 4);
/* 160 */     this.Leg1A.setRotationPoint(4.0F, 12.5F, -5.5F);
/* 161 */     setRotation(this.Leg1A, 0.2617994F, 0.0F, 0.0F);
/*     */     
/* 163 */     this.Leg1B = new ModelRenderer(this, 28, 69);
/* 164 */     this.Leg1B.addBox(0.0F, 3.2F, 0.5F, 2, 8, 2);
/* 165 */     this.Leg1B.setRotationPoint(4.0F, 12.5F, -5.5F);
/* 166 */     setRotation(this.Leg1B, -0.1745329F, 0.0F, 0.0F);
/*     */     
/* 168 */     this.Leg1C = new ModelRenderer(this, 28, 79);
/* 169 */     this.Leg1C.addBox(-0.5066667F, 9.5F, -2.5F, 3, 2, 3);
/* 170 */     this.Leg1C.setRotationPoint(4.0F, 12.5F, -5.5F);
/*     */     
/* 172 */     this.Leg2A = new ModelRenderer(this, 28, 84);
/* 173 */     this.Leg2A.addBox(-2.01F, -4.0F, -2.5F, 2, 8, 4);
/* 174 */     this.Leg2A.setRotationPoint(-4.0F, 12.5F, -5.5F);
/* 175 */     setRotation(this.Leg2A, 0.2617994F, 0.0F, 0.0F);
/*     */     
/* 177 */     this.Leg2B = new ModelRenderer(this, 28, 96);
/* 178 */     this.Leg2B.addBox(-2.0F, 3.2F, 0.5F, 2, 8, 2);
/* 179 */     this.Leg2B.setRotationPoint(-4.0F, 12.5F, -5.5F);
/* 180 */     setRotation(this.Leg2B, -0.1745329F, 0.0F, 0.0F);
/*     */     
/* 182 */     this.Leg2C = new ModelRenderer(this, 28, 106);
/* 183 */     this.Leg2C.addBox(-2.506667F, 9.5F, -2.5F, 3, 2, 3);
/* 184 */     this.Leg2C.setRotationPoint(-4.0F, 12.5F, -5.5F);
/*     */     
/* 186 */     this.Leg3A = new ModelRenderer(this, 0, 64);
/* 187 */     this.Leg3A.addBox(0.0F, -3.8F, -3.5F, 2, 7, 5);
/* 188 */     this.Leg3A.setRotationPoint(3.0F, 12.5F, 7.0F);
/* 189 */     setRotation(this.Leg3A, -0.3665191F, 0.0F, 0.0F);
/*     */     
/* 191 */     this.Leg3B = new ModelRenderer(this, 0, 76);
/* 192 */     this.Leg3B.addBox(-0.1F, 1.9F, -1.8F, 2, 2, 5);
/* 193 */     this.Leg3B.setRotationPoint(3.0F, 12.5F, 7.0F);
/* 194 */     setRotation(this.Leg3B, -0.7330383F, 0.0F, 0.0F);
/*     */     
/* 196 */     this.Leg3C = new ModelRenderer(this, 0, 83);
/* 197 */     this.Leg3C.addBox(0.0F, 3.2F, 0.0F, 2, 8, 2);
/* 198 */     this.Leg3C.setRotationPoint(3.0F, 12.5F, 7.0F);
/* 199 */     setRotation(this.Leg3C, -0.1745329F, 0.0F, 0.0F);
/*     */     
/* 201 */     this.Leg3D = new ModelRenderer(this, 0, 93);
/* 202 */     this.Leg3D.addBox(-0.5066667F, 9.5F, -3.0F, 3, 2, 3);
/* 203 */     this.Leg3D.setRotationPoint(3.0F, 12.5F, 7.0F);
/*     */     
/* 205 */     this.Leg4A = new ModelRenderer(this, 14, 64);
/* 206 */     this.Leg4A.addBox(-2.0F, -3.8F, -3.5F, 2, 7, 5);
/* 207 */     this.Leg4A.setRotationPoint(-3.0F, 12.5F, 7.0F);
/* 208 */     setRotation(this.Leg4A, -0.3665191F, 0.0F, 0.0F);
/*     */     
/* 210 */     this.Leg4B = new ModelRenderer(this, 14, 76);
/* 211 */     this.Leg4B.addBox(-1.9F, 1.9F, -1.8F, 2, 2, 5);
/* 212 */     this.Leg4B.setRotationPoint(-3.0F, 12.5F, 7.0F);
/* 213 */     setRotation(this.Leg4B, -0.7330383F, 0.0F, 0.0F);
/*     */     
/* 215 */     this.Leg4C = new ModelRenderer(this, 14, 83);
/* 216 */     this.Leg4C.addBox(-2.0F, 3.2F, 0.0F, 2, 8, 2);
/* 217 */     this.Leg4C.setRotationPoint(-3.0F, 12.5F, 7.0F);
/* 218 */     setRotation(this.Leg4C, -0.1745329F, 0.0F, 0.0F);
/*     */     
/* 220 */     this.Leg4D = new ModelRenderer(this, 14, 93);
/* 221 */     this.Leg4D.addBox(-2.506667F, 9.5F, -3.0F, 3, 2, 3);
/* 222 */     this.Leg4D.setRotationPoint(-3.0F, 12.5F, 7.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 228 */     MoCEntityWWolf entitywolf = (MoCEntityWWolf)entity;
/* 229 */     boolean openMouth = (entitywolf.mouthCounter != 0);
/* 230 */     boolean moveTail = (entitywolf.tailCounter != 0);
/*     */     
/* 232 */     setRotationAngles(f, f1, f2, f3, f4, f5, moveTail);
/* 233 */     this.Head.render(f5);
/*     */     
/* 235 */     this.Nose2.render(f5);
/* 236 */     this.Neck.render(f5);
/* 237 */     this.Neck2.render(f5);
/* 238 */     this.LSide.render(f5);
/* 239 */     this.RSide.render(f5);
/* 240 */     this.Nose.render(f5);
/*     */     
/* 242 */     if (openMouth) {
/* 243 */       this.MouthOpen.render(f5);
/* 244 */       this.UTeeth.render(f5);
/* 245 */       this.LTeeth.render(f5);
/*     */     } else {
/*     */       
/* 248 */       this.Mouth.render(f5);
/* 249 */       this.MouthB.render(f5);
/*     */     } 
/* 251 */     this.REar.render(f5);
/* 252 */     this.LEar.render(f5);
/* 253 */     this.Chest.render(f5);
/* 254 */     this.Body.render(f5);
/* 255 */     this.TailA.render(f5);
/* 256 */     this.TailB.render(f5);
/* 257 */     this.TailC.render(f5);
/* 258 */     this.TailD.render(f5);
/* 259 */     this.Leg4A.render(f5);
/* 260 */     this.Leg4D.render(f5);
/* 261 */     this.Leg4B.render(f5);
/* 262 */     this.Leg4C.render(f5);
/* 263 */     this.Leg3B.render(f5);
/* 264 */     this.Leg2A.render(f5);
/* 265 */     this.Leg2B.render(f5);
/* 266 */     this.Leg2C.render(f5);
/* 267 */     this.Leg3D.render(f5);
/* 268 */     this.Leg3C.render(f5);
/* 269 */     this.Leg3A.render(f5);
/* 270 */     this.Leg1A.render(f5);
/* 271 */     this.Leg1B.render(f5);
/* 272 */     this.Leg1C.render(f5);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 277 */     model.rotateAngleX = x;
/* 278 */     model.rotateAngleY = y;
/* 279 */     model.rotateAngleZ = z;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, boolean tail) {
/* 284 */     this.Head.rotateAngleX = f4 / 57.29578F;
/* 285 */     this.Head.rotateAngleY = f3 / 57.29578F;
/* 286 */     float LLegX = MathHelper.cos(f * 0.6662F) * 0.8F * f1;
/* 287 */     float RLegX = MathHelper.cos(f * 0.6662F + 3.141593F) * 0.8F * f1;
/*     */     
/* 289 */     this.Mouth.rotateAngleX = this.Head.rotateAngleX;
/* 290 */     this.Mouth.rotateAngleY = this.Head.rotateAngleY;
/* 291 */     this.MouthB.rotateAngleX = this.Head.rotateAngleX;
/* 292 */     this.MouthB.rotateAngleY = this.Head.rotateAngleY;
/* 293 */     this.UTeeth.rotateAngleX = this.Head.rotateAngleX;
/* 294 */     this.UTeeth.rotateAngleY = this.Head.rotateAngleY;
/* 295 */     this.MouthOpen.rotateAngleX = 2.5307274F + this.Head.rotateAngleX;
/* 296 */     this.MouthOpen.rotateAngleY = this.Head.rotateAngleY;
/* 297 */     this.LTeeth.rotateAngleX = 2.5307274F + this.Head.rotateAngleX;
/* 298 */     this.LTeeth.rotateAngleY = this.Head.rotateAngleY;
/* 299 */     this.Nose.rotateAngleX = 0.27925268F + this.Head.rotateAngleX;
/* 300 */     this.Nose.rotateAngleY = this.Head.rotateAngleY;
/* 301 */     this.Nose2.rotateAngleX = this.Head.rotateAngleX;
/* 302 */     this.Nose2.rotateAngleY = this.Head.rotateAngleY;
/*     */     
/* 304 */     this.LSide.rotateAngleX = -0.2094395F + this.Head.rotateAngleX;
/* 305 */     this.LSide.rotateAngleY = 0.418879F + this.Head.rotateAngleY;
/* 306 */     this.RSide.rotateAngleX = -0.2094395F + this.Head.rotateAngleX;
/* 307 */     this.RSide.rotateAngleY = -0.418879F + this.Head.rotateAngleY;
/*     */     
/* 309 */     this.REar.rotateAngleX = this.Head.rotateAngleX;
/* 310 */     this.REar.rotateAngleY = this.Head.rotateAngleY;
/* 311 */     this.LEar.rotateAngleX = this.Head.rotateAngleX;
/* 312 */     this.LEar.rotateAngleY = this.Head.rotateAngleY;
/*     */     
/* 314 */     this.Leg1A.rotateAngleX = 0.2617994F + LLegX;
/* 315 */     this.Leg1B.rotateAngleX = -0.17453292F + LLegX;
/* 316 */     this.Leg1C.rotateAngleX = LLegX;
/*     */     
/* 318 */     this.Leg2A.rotateAngleX = 0.2617994F + RLegX;
/* 319 */     this.Leg2B.rotateAngleX = -0.17453292F + RLegX;
/* 320 */     this.Leg2C.rotateAngleX = RLegX;
/*     */     
/* 322 */     this.Leg3A.rotateAngleX = -0.36651915F + RLegX;
/* 323 */     this.Leg3B.rotateAngleX = -0.7330383F + RLegX;
/* 324 */     this.Leg3C.rotateAngleX = -0.17453292F + RLegX;
/* 325 */     this.Leg3D.rotateAngleX = RLegX;
/*     */     
/* 327 */     this.Leg4A.rotateAngleX = -0.36651915F + LLegX;
/* 328 */     this.Leg4B.rotateAngleX = -0.7330383F + LLegX;
/* 329 */     this.Leg4C.rotateAngleX = -0.17453292F + LLegX;
/* 330 */     this.Leg4D.rotateAngleX = LLegX;
/*     */     
/* 332 */     float tailMov = -1.3089F + f1 * 1.5F;
/*     */     
/* 334 */     if (tail) {
/* 335 */       this.TailA.rotateAngleY = MathHelper.cos(f2 * 0.5F);
/* 336 */       tailMov = 0.0F;
/*     */     } else {
/* 338 */       this.TailA.rotateAngleY = 0.0F;
/*     */     } 
/*     */     
/* 341 */     this.TailA.rotateAngleX = 1.0647582F - tailMov;
/* 342 */     this.TailB.rotateAngleX = 0.75056726F - tailMov;
/* 343 */     this.TailC.rotateAngleX = 1.0996684F - tailMov;
/* 344 */     this.TailD.rotateAngleX = 1.0996684F - tailMov;
/*     */     
/* 346 */     this.TailB.rotateAngleY = this.TailA.rotateAngleY;
/* 347 */     this.TailC.rotateAngleY = this.TailA.rotateAngleY;
/* 348 */     this.TailD.rotateAngleY = this.TailA.rotateAngleY;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelWolf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */