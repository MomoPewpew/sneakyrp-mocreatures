/*     */ package drzhark.mocreatures.client.model;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MoCModelWere
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer Head;
/*     */   ModelRenderer Nose;
/*     */   ModelRenderer Snout;
/*     */   ModelRenderer TeethU;
/*     */   ModelRenderer TeethL;
/*     */   ModelRenderer Mouth;
/*     */   ModelRenderer LEar;
/*     */   ModelRenderer REar;
/*     */   ModelRenderer Neck;
/*     */   ModelRenderer Neck2;
/*     */   ModelRenderer SideburnL;
/*     */   ModelRenderer SideburnR;
/*     */   ModelRenderer Chest;
/*     */   ModelRenderer Abdomen;
/*     */   ModelRenderer TailA;
/*     */   ModelRenderer TailC;
/*     */   ModelRenderer TailB;
/*     */   ModelRenderer TailD;
/*     */   ModelRenderer RLegA;
/*     */   ModelRenderer RFoot;
/*     */   ModelRenderer RLegB;
/*     */   ModelRenderer RLegC;
/*     */   ModelRenderer LLegB;
/*     */   ModelRenderer LFoot;
/*     */   ModelRenderer LLegC;
/*     */   ModelRenderer LLegA;
/*     */   ModelRenderer RArmB;
/*     */   ModelRenderer RArmC;
/*     */   ModelRenderer LArmB;
/*     */   ModelRenderer RHand;
/*     */   ModelRenderer RArmA;
/*     */   ModelRenderer LArmA;
/*     */   ModelRenderer LArmC;
/*     */   ModelRenderer LHand;
/*     */   ModelRenderer RFinger1;
/*     */   ModelRenderer RFinger2;
/*     */   ModelRenderer RFinger3;
/*     */   ModelRenderer RFinger4;
/*     */   ModelRenderer RFinger5;
/*     */   ModelRenderer LFinger1;
/*     */   ModelRenderer LFinger2;
/*     */   ModelRenderer LFinger3;
/*     */   ModelRenderer LFinger4;
/*     */   ModelRenderer LFinger5;
/*     */   public boolean hunched;
/*     */   
/*     */   public MoCModelWere() {
/*  61 */     this.textureWidth = 64;
/*  62 */     this.textureHeight = 128;
/*     */     
/*  64 */     this.Head = new ModelRenderer(this, 0, 0);
/*  65 */     this.Head.addBox(-4.0F, -3.0F, -6.0F, 8, 8, 6);
/*  66 */     this.Head.setRotationPoint(0.0F, -8.0F, -6.0F);
/*  67 */     this.Head.setTextureSize(64, 128);
/*     */     
/*  69 */     this.Nose = new ModelRenderer(this, 44, 33);
/*  70 */     this.Nose.addBox(-1.5F, -1.7F, -12.3F, 3, 2, 7);
/*  71 */     this.Nose.setRotationPoint(0.0F, -8.0F, -6.0F);
/*  72 */     setRotation(this.Nose, 0.2792527F, 0.0F, 0.0F);
/*     */     
/*  74 */     this.Snout = new ModelRenderer(this, 0, 25);
/*  75 */     this.Snout.addBox(-2.0F, 2.0F, -12.0F, 4, 2, 6);
/*  76 */     this.Snout.setRotationPoint(0.0F, -8.0F, -6.0F);
/*     */     
/*  78 */     this.TeethU = new ModelRenderer(this, 46, 18);
/*  79 */     this.TeethU.addBox(-2.0F, 4.01F, -12.0F, 4, 2, 5);
/*  80 */     this.TeethU.setRotationPoint(0.0F, -8.0F, -6.0F);
/*     */     
/*  82 */     this.TeethL = new ModelRenderer(this, 20, 109);
/*  83 */     this.TeethL.addBox(-1.5F, -12.5F, 2.01F, 3, 5, 2);
/*  84 */     this.TeethL.setRotationPoint(0.0F, -8.0F, -6.0F);
/*  85 */     setRotation(this.TeethL, 2.530727F, 0.0F, 0.0F);
/*     */     
/*  87 */     this.Mouth = new ModelRenderer(this, 42, 69);
/*  88 */     this.Mouth.addBox(-1.5F, -12.5F, 0.0F, 3, 9, 2);
/*  89 */     this.Mouth.setRotationPoint(0.0F, -8.0F, -6.0F);
/*  90 */     setRotation(this.Mouth, 2.530727F, 0.0F, 0.0F);
/*     */     
/*  92 */     this.LEar = new ModelRenderer(this, 13, 14);
/*  93 */     this.LEar.addBox(0.5F, -7.5F, -1.0F, 3, 5, 1);
/*  94 */     this.LEar.setRotationPoint(0.0F, -8.0F, -6.0F);
/*  95 */     setRotation(this.LEar, 0.0F, 0.0F, 0.1745329F);
/*     */     
/*  97 */     this.REar = new ModelRenderer(this, 22, 0);
/*  98 */     this.REar.addBox(-3.5F, -7.5F, -1.0F, 3, 5, 1);
/*  99 */     this.REar.setRotationPoint(0.0F, -8.0F, -6.0F);
/* 100 */     setRotation(this.REar, 0.0F, 0.0F, -0.1745329F);
/*     */     
/* 102 */     this.Neck = new ModelRenderer(this, 28, 0);
/* 103 */     this.Neck.addBox(-3.5F, -3.0F, -7.0F, 7, 8, 7);
/* 104 */     this.Neck.setRotationPoint(0.0F, -5.0F, -2.0F);
/* 105 */     setRotation(this.Neck, -0.6025001F, 0.0F, 0.0F);
/*     */     
/* 107 */     this.Neck2 = new ModelRenderer(this, 0, 14);
/* 108 */     this.Neck2.addBox(-1.5F, -2.0F, -5.0F, 3, 4, 7);
/* 109 */     this.Neck2.setRotationPoint(0.0F, -1.0F, -6.0F);
/* 110 */     setRotation(this.Neck2, -0.4537856F, 0.0F, 0.0F);
/*     */     
/* 112 */     this.SideburnL = new ModelRenderer(this, 28, 33);
/* 113 */     this.SideburnL.addBox(3.0F, 0.0F, -2.0F, 2, 6, 6);
/* 114 */     this.SideburnL.setRotationPoint(0.0F, -8.0F, -6.0F);
/* 115 */     setRotation(this.SideburnL, -0.2094395F, 0.418879F, -0.0872665F);
/*     */     
/* 117 */     this.SideburnR = new ModelRenderer(this, 28, 45);
/* 118 */     this.SideburnR.addBox(-5.0F, 0.0F, -2.0F, 2, 6, 6);
/* 119 */     this.SideburnR.setRotationPoint(0.0F, -8.0F, -6.0F);
/* 120 */     setRotation(this.SideburnR, -0.2094395F, -0.418879F, 0.0872665F);
/*     */     
/* 122 */     this.Chest = new ModelRenderer(this, 20, 15);
/* 123 */     this.Chest.addBox(-4.0F, 0.0F, -7.0F, 8, 8, 10);
/* 124 */     this.Chest.setRotationPoint(0.0F, -6.0F, -2.5F);
/* 125 */     setRotation(this.Chest, 0.641331F, 0.0F, 0.0F);
/*     */     
/* 127 */     this.Abdomen = new ModelRenderer(this, 0, 40);
/* 128 */     this.Abdomen.addBox(-3.0F, -8.0F, -8.0F, 6, 14, 8);
/* 129 */     this.Abdomen.setRotationPoint(0.0F, 4.5F, 5.0F);
/* 130 */     setRotation(this.Abdomen, 0.2695449F, 0.0F, 0.0F);
/*     */     
/* 132 */     this.TailA = new ModelRenderer(this, 52, 42);
/* 133 */     this.TailA.addBox(-1.5F, -1.0F, -2.0F, 3, 4, 3);
/* 134 */     this.TailA.setRotationPoint(0.0F, 9.5F, 6.0F);
/* 135 */     setRotation(this.TailA, 1.064651F, 0.0F, 0.0F);
/*     */     
/* 137 */     this.TailC = new ModelRenderer(this, 48, 59);
/* 138 */     this.TailC.addBox(-2.0F, 6.8F, -4.6F, 4, 6, 4);
/* 139 */     this.TailC.setRotationPoint(0.0F, 9.5F, 6.0F);
/* 140 */     setRotation(this.TailC, 1.099557F, 0.0F, 0.0F);
/*     */     
/* 142 */     this.TailB = new ModelRenderer(this, 48, 49);
/* 143 */     this.TailB.addBox(-2.0F, 2.0F, -2.0F, 4, 6, 4);
/* 144 */     this.TailB.setRotationPoint(0.0F, 9.5F, 6.0F);
/* 145 */     setRotation(this.TailB, 0.7504916F, 0.0F, 0.0F);
/*     */     
/* 147 */     this.TailD = new ModelRenderer(this, 52, 69);
/* 148 */     this.TailD.addBox(-1.5F, 9.8F, -4.1F, 3, 5, 3);
/* 149 */     this.TailD.setRotationPoint(0.0F, 9.5F, 6.0F);
/* 150 */     setRotation(this.TailD, 1.099557F, 0.0F, 0.0F);
/*     */     
/* 152 */     this.RLegA = new ModelRenderer(this, 12, 64);
/* 153 */     this.RLegA.addBox(-2.5F, -1.5F, -3.5F, 3, 8, 5);
/* 154 */     this.RLegA.setRotationPoint(-3.0F, 9.5F, 3.0F);
/* 155 */     setRotation(this.RLegA, -0.8126625F, 0.0F, 0.0F);
/*     */     
/* 157 */     this.RFoot = new ModelRenderer(this, 14, 93);
/* 158 */     this.RFoot.addBox(-2.506667F, 12.5F, -5.0F, 3, 2, 3);
/* 159 */     this.RFoot.setRotationPoint(-3.0F, 9.5F, 3.0F);
/*     */     
/* 161 */     this.RLegB = new ModelRenderer(this, 14, 76);
/* 162 */     this.RLegB.addBox(-1.9F, 4.2F, 0.5F, 2, 2, 5);
/* 163 */     this.RLegB.setRotationPoint(-3.0F, 9.5F, 3.0F);
/* 164 */     setRotation(this.RLegB, -0.8445741F, 0.0F, 0.0F);
/*     */     
/* 166 */     this.RLegC = new ModelRenderer(this, 14, 83);
/* 167 */     this.RLegC.addBox(-2.0F, 6.2F, 0.5F, 2, 8, 2);
/* 168 */     this.RLegC.setRotationPoint(-3.0F, 9.5F, 3.0F);
/* 169 */     setRotation(this.RLegC, -0.2860688F, 0.0F, 0.0F);
/*     */     
/* 171 */     this.LLegB = new ModelRenderer(this, 0, 76);
/* 172 */     this.LLegB.addBox(-0.1F, 4.2F, 0.5F, 2, 2, 5);
/* 173 */     this.LLegB.setRotationPoint(3.0F, 9.5F, 3.0F);
/* 174 */     setRotation(this.LLegB, -0.8445741F, 0.0F, 0.0F);
/*     */     
/* 176 */     this.LFoot = new ModelRenderer(this, 0, 93);
/* 177 */     this.LFoot.addBox(-0.5066667F, 12.5F, -5.0F, 3, 2, 3);
/* 178 */     this.LFoot.setRotationPoint(3.0F, 9.5F, 3.0F);
/*     */     
/* 180 */     this.LLegC = new ModelRenderer(this, 0, 83);
/* 181 */     this.LLegC.addBox(0.0F, 6.2F, 0.5F, 2, 8, 2);
/* 182 */     this.LLegC.setRotationPoint(3.0F, 9.5F, 3.0F);
/* 183 */     setRotation(this.LLegC, -0.2860688F, 0.0F, 0.0F);
/*     */     
/* 185 */     this.LLegA = new ModelRenderer(this, 0, 64);
/* 186 */     this.LLegA.addBox(-0.5F, -1.5F, -3.5F, 3, 8, 5);
/* 187 */     this.LLegA.setRotationPoint(3.0F, 9.5F, 3.0F);
/* 188 */     setRotation(this.LLegA, -0.8126625F, 0.0F, 0.0F);
/*     */     
/* 190 */     this.RArmB = new ModelRenderer(this, 48, 77);
/* 191 */     this.RArmB.addBox(-3.5F, 1.0F, -1.5F, 4, 8, 4);
/* 192 */     this.RArmB.setRotationPoint(-4.0F, -4.0F, -2.0F);
/* 193 */     setRotation(this.RArmB, 0.2617994F, 0.0F, 0.3490659F);
/*     */     
/* 195 */     this.RArmC = new ModelRenderer(this, 48, 112);
/* 196 */     this.RArmC.addBox(-6.0F, 5.0F, 3.0F, 4, 7, 4);
/* 197 */     this.RArmC.setRotationPoint(-4.0F, -4.0F, -2.0F);
/* 198 */     setRotation(this.RArmC, -0.3490659F, 0.0F, 0.0F);
/*     */     
/* 200 */     this.LArmB = new ModelRenderer(this, 48, 89);
/* 201 */     this.LArmB.addBox(-0.5F, 1.0F, -1.5F, 4, 8, 4);
/* 202 */     this.LArmB.setRotationPoint(4.0F, -4.0F, -2.0F);
/* 203 */     setRotation(this.LArmB, 0.2617994F, 0.0F, -0.3490659F);
/*     */     
/* 205 */     this.RHand = new ModelRenderer(this, 32, 118);
/* 206 */     this.RHand.addBox(-6.0F, 12.5F, -1.5F, 4, 3, 4);
/* 207 */     this.RHand.setRotationPoint(-4.0F, -4.0F, -2.0F);
/*     */     
/* 209 */     this.RArmA = new ModelRenderer(this, 0, 108);
/* 210 */     this.RArmA.addBox(-5.0F, -3.0F, -2.0F, 5, 5, 5);
/* 211 */     this.RArmA.setRotationPoint(-4.0F, -4.0F, -2.0F);
/* 212 */     setRotation(this.RArmA, 0.6320364F, 0.0F, 0.0F);
/*     */     
/* 214 */     this.LArmA = new ModelRenderer(this, 0, 98);
/* 215 */     this.LArmA.addBox(0.0F, -3.0F, -2.0F, 5, 5, 5);
/* 216 */     this.LArmA.setRotationPoint(4.0F, -4.0F, -2.0F);
/* 217 */     setRotation(this.LArmA, 0.6320364F, 0.0F, 0.0F);
/*     */     
/* 219 */     this.LArmC = new ModelRenderer(this, 48, 101);
/* 220 */     this.LArmC.addBox(2.0F, 5.0F, 3.0F, 4, 7, 4);
/* 221 */     this.LArmC.setRotationPoint(4.0F, -4.0F, -2.0F);
/* 222 */     setRotation(this.LArmC, -0.3490659F, 0.0F, 0.0F);
/*     */     
/* 224 */     this.LHand = new ModelRenderer(this, 32, 111);
/* 225 */     this.LHand.addBox(2.0F, 12.5F, -1.5F, 4, 3, 4);
/* 226 */     this.LHand.setRotationPoint(4.0F, -4.0F, -2.0F);
/*     */     
/* 228 */     this.RFinger1 = new ModelRenderer(this, 8, 120);
/* 229 */     this.RFinger1.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
/* 230 */     this.RFinger1.setRotationPoint(-6.5F, 11.5F, -0.5F);
/*     */     
/* 232 */     this.RFinger1 = new ModelRenderer(this, 8, 120);
/* 233 */     this.RFinger1.addBox(-3.0F, 15.5F, 1.0F, 1, 3, 1);
/* 234 */     this.RFinger1.setRotationPoint(-4.0F, -4.0F, -2.0F);
/*     */     
/* 236 */     this.RFinger2 = new ModelRenderer(this, 12, 124);
/* 237 */     this.RFinger2.addBox(-3.5F, 15.5F, -1.5F, 1, 3, 1);
/* 238 */     this.RFinger2.setRotationPoint(-4.0F, -4.0F, -2.0F);
/*     */     
/* 240 */     this.RFinger3 = new ModelRenderer(this, 12, 119);
/* 241 */     this.RFinger3.addBox(-4.8F, 15.5F, -1.5F, 1, 4, 1);
/* 242 */     this.RFinger3.setRotationPoint(-4.0F, -4.0F, -2.0F);
/*     */     
/* 244 */     this.RFinger4 = new ModelRenderer(this, 16, 119);
/* 245 */     this.RFinger4.addBox(-6.0F, 15.5F, -0.5F, 1, 4, 1);
/* 246 */     this.RFinger4.setRotationPoint(-4.0F, -4.0F, -2.0F);
/*     */     
/* 248 */     this.RFinger5 = new ModelRenderer(this, 16, 124);
/* 249 */     this.RFinger5.addBox(-6.0F, 15.5F, 1.0F, 1, 3, 1);
/* 250 */     this.RFinger5.setRotationPoint(-4.0F, -4.0F, -2.0F);
/*     */     
/* 252 */     this.LFinger1 = new ModelRenderer(this, 8, 124);
/* 253 */     this.LFinger1.addBox(2.0F, 15.5F, 1.0F, 1, 3, 1);
/* 254 */     this.LFinger1.setRotationPoint(4.0F, -4.0F, -2.0F);
/*     */     
/* 256 */     this.LFinger2 = new ModelRenderer(this, 0, 124);
/* 257 */     this.LFinger2.addBox(2.5F, 15.5F, -1.5F, 1, 3, 1);
/* 258 */     this.LFinger2.setRotationPoint(4.0F, -4.0F, -2.0F);
/*     */     
/* 260 */     this.LFinger3 = new ModelRenderer(this, 0, 119);
/* 261 */     this.LFinger3.addBox(3.8F, 15.5F, -1.5F, 1, 4, 1);
/* 262 */     this.LFinger3.setRotationPoint(4.0F, -4.0F, -2.0F);
/*     */     
/* 264 */     this.LFinger4 = new ModelRenderer(this, 4, 119);
/* 265 */     this.LFinger4.addBox(5.0F, 15.5F, -0.5F, 1, 4, 1);
/* 266 */     this.LFinger4.setRotationPoint(4.0F, -4.0F, -2.0F);
/*     */     
/* 268 */     this.LFinger5 = new ModelRenderer(this, 4, 124);
/* 269 */     this.LFinger5.addBox(5.0F, 15.5F, 1.0F, 1, 3, 1);
/* 270 */     this.LFinger5.setRotationPoint(4.0F, -4.0F, -2.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 277 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/* 278 */     this.Head.render(f5);
/* 279 */     this.Nose.render(f5);
/* 280 */     this.Snout.render(f5);
/* 281 */     this.TeethU.render(f5);
/* 282 */     this.TeethL.render(f5);
/* 283 */     this.Mouth.render(f5);
/* 284 */     this.LEar.render(f5);
/* 285 */     this.REar.render(f5);
/* 286 */     this.Neck.render(f5);
/* 287 */     this.Neck2.render(f5);
/* 288 */     this.SideburnL.render(f5);
/* 289 */     this.SideburnR.render(f5);
/* 290 */     this.Chest.render(f5);
/* 291 */     this.Abdomen.render(f5);
/* 292 */     this.TailA.render(f5);
/* 293 */     this.TailC.render(f5);
/* 294 */     this.TailB.render(f5);
/* 295 */     this.TailD.render(f5);
/* 296 */     this.RLegA.render(f5);
/* 297 */     this.RFoot.render(f5);
/* 298 */     this.RLegB.render(f5);
/* 299 */     this.RLegC.render(f5);
/* 300 */     this.LLegB.render(f5);
/* 301 */     this.LFoot.render(f5);
/* 302 */     this.LLegC.render(f5);
/* 303 */     this.LLegA.render(f5);
/* 304 */     this.RArmB.render(f5);
/* 305 */     this.RArmC.render(f5);
/* 306 */     this.LArmB.render(f5);
/* 307 */     this.RHand.render(f5);
/* 308 */     this.RArmA.render(f5);
/* 309 */     this.LArmA.render(f5);
/* 310 */     this.LArmC.render(f5);
/* 311 */     this.LHand.render(f5);
/* 312 */     this.RFinger1.render(f5);
/* 313 */     this.RFinger2.render(f5);
/* 314 */     this.RFinger3.render(f5);
/* 315 */     this.RFinger4.render(f5);
/* 316 */     this.RFinger5.render(f5);
/* 317 */     this.LFinger1.render(f5);
/* 318 */     this.LFinger2.render(f5);
/* 319 */     this.LFinger3.render(f5);
/* 320 */     this.LFinger4.render(f5);
/* 321 */     this.LFinger5.render(f5);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 325 */     model.rotateAngleX = x;
/* 326 */     model.rotateAngleY = y;
/* 327 */     model.rotateAngleZ = z;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 332 */     float radianF = 57.29578F;
/* 333 */     float RLegXRot = MathHelper.cos(f * 0.6662F + 3.141593F) * 0.8F * f1;
/* 334 */     float LLegXRot = MathHelper.cos(f * 0.6662F) * 0.8F * f1;
/*     */     
/* 336 */     this.Head.rotateAngleY = f3 / radianF;
/*     */ 
/*     */     
/* 339 */     if (!this.hunched) {
/* 340 */       this.Head.rotationPointY = -8.0F;
/* 341 */       this.Head.rotationPointZ = -6.0F;
/* 342 */       this.Head.rotateAngleX = f4 / radianF;
/* 343 */       this.Neck.rotateAngleX = -34.0F / radianF;
/* 344 */       this.Neck.rotationPointY = -5.0F;
/* 345 */       this.Neck.rotationPointZ = -2.0F;
/* 346 */       this.Neck2.rotationPointY = -1.0F;
/* 347 */       this.Neck2.rotationPointZ = -6.0F;
/* 348 */       this.Chest.rotationPointY = -6.0F;
/* 349 */       this.Chest.rotationPointZ = -2.5F;
/* 350 */       this.Chest.rotateAngleX = 36.0F / radianF;
/* 351 */       this.Abdomen.rotateAngleX = 15.0F / radianF;
/* 352 */       this.LLegA.rotationPointZ = 3.0F;
/*     */       
/* 354 */       this.LArmA.rotationPointY = -4.0F;
/* 355 */       this.LArmA.rotationPointZ = -2.0F;
/*     */       
/* 357 */       this.TailA.rotationPointY = 9.5F;
/* 358 */       this.TailA.rotationPointZ = 6.0F;
/*     */     } else {
/*     */       
/* 361 */       this.Head.rotationPointY = 0.0F;
/* 362 */       this.Head.rotationPointZ = -11.0F;
/* 363 */       this.Head.rotateAngleX = (15.0F + f4) / radianF;
/*     */       
/* 365 */       this.Neck.rotateAngleX = -10.0F / radianF;
/* 366 */       this.Neck.rotationPointY = 2.0F;
/* 367 */       this.Neck.rotationPointZ = -6.0F;
/* 368 */       this.Neck2.rotationPointY = 9.0F;
/* 369 */       this.Neck2.rotationPointZ = -9.0F;
/* 370 */       this.Chest.rotationPointY = 1.0F;
/* 371 */       this.Chest.rotationPointZ = -7.5F;
/* 372 */       this.Chest.rotateAngleX = 60.0F / radianF;
/* 373 */       this.Abdomen.rotateAngleX = 75.0F / radianF;
/* 374 */       this.LLegA.rotationPointZ = 7.0F;
/* 375 */       this.LArmA.rotationPointY = 4.5F;
/* 376 */       this.LArmA.rotationPointZ = -6.0F;
/* 377 */       this.TailA.rotationPointY = 7.5F;
/* 378 */       this.TailA.rotationPointZ = 10.0F;
/*     */     } 
/*     */ 
/*     */     
/* 382 */     this.Nose.rotationPointY = this.Head.rotationPointY;
/* 383 */     this.Snout.rotationPointY = this.Head.rotationPointY;
/* 384 */     this.TeethU.rotationPointY = this.Head.rotationPointY;
/* 385 */     this.LEar.rotationPointY = this.Head.rotationPointY;
/* 386 */     this.REar.rotationPointY = this.Head.rotationPointY;
/* 387 */     this.TeethL.rotationPointY = this.Head.rotationPointY;
/* 388 */     this.Mouth.rotationPointY = this.Head.rotationPointY;
/* 389 */     this.SideburnL.rotationPointY = this.Head.rotationPointY;
/* 390 */     this.SideburnR.rotationPointY = this.Head.rotationPointY;
/*     */     
/* 392 */     this.Nose.rotationPointZ = this.Head.rotationPointZ;
/* 393 */     this.Snout.rotationPointZ = this.Head.rotationPointZ;
/* 394 */     this.TeethU.rotationPointZ = this.Head.rotationPointZ;
/* 395 */     this.LEar.rotationPointZ = this.Head.rotationPointZ;
/* 396 */     this.REar.rotationPointZ = this.Head.rotationPointZ;
/* 397 */     this.TeethL.rotationPointZ = this.Head.rotationPointZ;
/* 398 */     this.Mouth.rotationPointZ = this.Head.rotationPointZ;
/* 399 */     this.SideburnL.rotationPointZ = this.Head.rotationPointZ;
/* 400 */     this.SideburnR.rotationPointZ = this.Head.rotationPointZ;
/*     */     
/* 402 */     this.LArmB.rotationPointY = this.LArmA.rotationPointY;
/* 403 */     this.LArmC.rotationPointY = this.LArmA.rotationPointY;
/* 404 */     this.LHand.rotationPointY = this.LArmA.rotationPointY;
/* 405 */     this.LFinger1.rotationPointY = this.LArmA.rotationPointY;
/* 406 */     this.LFinger2.rotationPointY = this.LArmA.rotationPointY;
/* 407 */     this.LFinger3.rotationPointY = this.LArmA.rotationPointY;
/* 408 */     this.LFinger4.rotationPointY = this.LArmA.rotationPointY;
/* 409 */     this.LFinger5.rotationPointY = this.LArmA.rotationPointY;
/* 410 */     this.RArmA.rotationPointY = this.LArmA.rotationPointY;
/* 411 */     this.RArmB.rotationPointY = this.LArmA.rotationPointY;
/* 412 */     this.RArmC.rotationPointY = this.LArmA.rotationPointY;
/* 413 */     this.RHand.rotationPointY = this.LArmA.rotationPointY;
/* 414 */     this.RFinger1.rotationPointY = this.LArmA.rotationPointY;
/* 415 */     this.RFinger2.rotationPointY = this.LArmA.rotationPointY;
/* 416 */     this.RFinger3.rotationPointY = this.LArmA.rotationPointY;
/* 417 */     this.RFinger4.rotationPointY = this.LArmA.rotationPointY;
/* 418 */     this.RFinger5.rotationPointY = this.LArmA.rotationPointY;
/*     */     
/* 420 */     this.LArmB.rotationPointZ = this.LArmA.rotationPointZ;
/* 421 */     this.LArmC.rotationPointZ = this.LArmA.rotationPointZ;
/* 422 */     this.LHand.rotationPointZ = this.LArmA.rotationPointZ;
/* 423 */     this.LFinger1.rotationPointZ = this.LArmA.rotationPointZ;
/* 424 */     this.LFinger2.rotationPointZ = this.LArmA.rotationPointZ;
/* 425 */     this.LFinger3.rotationPointZ = this.LArmA.rotationPointZ;
/* 426 */     this.LFinger4.rotationPointZ = this.LArmA.rotationPointZ;
/* 427 */     this.LFinger5.rotationPointZ = this.LArmA.rotationPointZ;
/* 428 */     this.RArmA.rotationPointZ = this.LArmA.rotationPointZ;
/* 429 */     this.RArmB.rotationPointZ = this.LArmA.rotationPointZ;
/* 430 */     this.RArmC.rotationPointZ = this.LArmA.rotationPointZ;
/* 431 */     this.RHand.rotationPointZ = this.LArmA.rotationPointZ;
/* 432 */     this.RFinger1.rotationPointZ = this.LArmA.rotationPointZ;
/* 433 */     this.RFinger2.rotationPointZ = this.LArmA.rotationPointZ;
/* 434 */     this.RFinger3.rotationPointZ = this.LArmA.rotationPointZ;
/* 435 */     this.RFinger4.rotationPointZ = this.LArmA.rotationPointZ;
/* 436 */     this.RFinger5.rotationPointZ = this.LArmA.rotationPointZ;
/*     */     
/* 438 */     this.RLegA.rotationPointZ = this.LLegA.rotationPointZ;
/* 439 */     this.RLegB.rotationPointZ = this.LLegA.rotationPointZ;
/* 440 */     this.RLegC.rotationPointZ = this.LLegA.rotationPointZ;
/* 441 */     this.RFoot.rotationPointZ = this.LLegA.rotationPointZ;
/* 442 */     this.LLegB.rotationPointZ = this.LLegA.rotationPointZ;
/* 443 */     this.LLegC.rotationPointZ = this.LLegA.rotationPointZ;
/* 444 */     this.LFoot.rotationPointZ = this.LLegA.rotationPointZ;
/*     */     
/* 446 */     this.TailB.rotationPointY = this.TailA.rotationPointY;
/* 447 */     this.TailB.rotationPointZ = this.TailA.rotationPointZ;
/* 448 */     this.TailC.rotationPointY = this.TailA.rotationPointY;
/* 449 */     this.TailC.rotationPointZ = this.TailA.rotationPointZ;
/* 450 */     this.TailD.rotationPointY = this.TailA.rotationPointY;
/* 451 */     this.TailD.rotationPointZ = this.TailA.rotationPointZ;
/*     */     
/* 453 */     this.Nose.rotateAngleY = this.Head.rotateAngleY;
/* 454 */     this.Snout.rotateAngleY = this.Head.rotateAngleY;
/* 455 */     this.TeethU.rotateAngleY = this.Head.rotateAngleY;
/* 456 */     this.LEar.rotateAngleY = this.Head.rotateAngleY;
/* 457 */     this.REar.rotateAngleY = this.Head.rotateAngleY;
/* 458 */     this.TeethL.rotateAngleY = this.Head.rotateAngleY;
/* 459 */     this.Mouth.rotateAngleY = this.Head.rotateAngleY;
/*     */     
/* 461 */     this.Head.rotateAngleX += 2.530727F;
/* 462 */     this.Head.rotateAngleX += 2.530727F;
/*     */     
/* 464 */     this.SideburnL.rotateAngleX = -0.2094395F + this.Head.rotateAngleX;
/* 465 */     this.SideburnL.rotateAngleY = 0.418879F + this.Head.rotateAngleY;
/* 466 */     this.SideburnR.rotateAngleX = -0.2094395F + this.Head.rotateAngleX;
/* 467 */     this.SideburnR.rotateAngleY = -0.418879F + this.Head.rotateAngleY;
/*     */     
/* 469 */     this.Nose.rotateAngleX = 0.2792527F + this.Head.rotateAngleX;
/* 470 */     this.Snout.rotateAngleX = this.Head.rotateAngleX;
/* 471 */     this.TeethU.rotateAngleX = this.Head.rotateAngleX;
/*     */     
/* 473 */     this.LEar.rotateAngleX = this.Head.rotateAngleX;
/* 474 */     this.REar.rotateAngleX = this.Head.rotateAngleX;
/*     */     
/* 476 */     this.RLegA.rotateAngleX = -0.8126625F + RLegXRot;
/* 477 */     this.RLegB.rotateAngleX = -0.8445741F + RLegXRot;
/* 478 */     this.RLegC.rotateAngleX = -0.2860688F + RLegXRot;
/* 479 */     this.RFoot.rotateAngleX = RLegXRot;
/*     */     
/* 481 */     this.LLegA.rotateAngleX = -0.8126625F + LLegXRot;
/* 482 */     this.LLegB.rotateAngleX = -0.8445741F + LLegXRot;
/* 483 */     this.LLegC.rotateAngleX = -0.2860688F + LLegXRot;
/* 484 */     this.LFoot.rotateAngleX = LLegXRot;
/*     */     
/* 486 */     this.RArmA.rotateAngleZ = -(MathHelper.cos(f2 * 0.09F) * 0.05F) + 0.05F;
/* 487 */     this.LArmA.rotateAngleZ = MathHelper.cos(f2 * 0.09F) * 0.05F - 0.05F;
/* 488 */     this.RArmA.rotateAngleX = LLegXRot;
/* 489 */     this.LArmA.rotateAngleX = RLegXRot;
/*     */     
/* 491 */     this.RArmB.rotateAngleZ = 0.3490659F + this.RArmA.rotateAngleZ;
/* 492 */     this.LArmB.rotateAngleZ = -0.3490659F + this.LArmA.rotateAngleZ;
/* 493 */     this.RArmB.rotateAngleX = 0.2617994F + this.RArmA.rotateAngleX;
/* 494 */     this.LArmB.rotateAngleX = 0.2617994F + this.LArmA.rotateAngleX;
/*     */     
/* 496 */     this.RArmC.rotateAngleZ = this.RArmA.rotateAngleZ;
/* 497 */     this.LArmC.rotateAngleZ = this.LArmA.rotateAngleZ;
/* 498 */     this.RArmC.rotateAngleX = -0.3490659F + this.RArmA.rotateAngleX;
/* 499 */     this.LArmC.rotateAngleX = -0.3490659F + this.LArmA.rotateAngleX;
/*     */     
/* 501 */     this.RHand.rotateAngleZ = this.RArmA.rotateAngleZ;
/* 502 */     this.LHand.rotateAngleZ = this.LArmA.rotateAngleZ;
/* 503 */     this.RHand.rotateAngleX = this.RArmA.rotateAngleX;
/* 504 */     this.LHand.rotateAngleX = this.LArmA.rotateAngleX;
/*     */     
/* 506 */     this.RFinger1.rotateAngleX = this.RArmA.rotateAngleX;
/* 507 */     this.RFinger2.rotateAngleX = this.RArmA.rotateAngleX;
/* 508 */     this.RFinger3.rotateAngleX = this.RArmA.rotateAngleX;
/* 509 */     this.RFinger4.rotateAngleX = this.RArmA.rotateAngleX;
/* 510 */     this.RFinger5.rotateAngleX = this.RArmA.rotateAngleX;
/*     */     
/* 512 */     this.LFinger1.rotateAngleX = this.LArmA.rotateAngleX;
/* 513 */     this.LFinger2.rotateAngleX = this.LArmA.rotateAngleX;
/* 514 */     this.LFinger3.rotateAngleX = this.LArmA.rotateAngleX;
/* 515 */     this.LFinger4.rotateAngleX = this.LArmA.rotateAngleX;
/* 516 */     this.LFinger5.rotateAngleX = this.LArmA.rotateAngleX;
/*     */     
/* 518 */     this.RFinger1.rotateAngleZ = this.RArmA.rotateAngleZ;
/* 519 */     this.RFinger2.rotateAngleZ = this.RArmA.rotateAngleZ;
/* 520 */     this.RFinger3.rotateAngleZ = this.RArmA.rotateAngleZ;
/* 521 */     this.RFinger4.rotateAngleZ = this.RArmA.rotateAngleZ;
/* 522 */     this.RFinger5.rotateAngleZ = this.RArmA.rotateAngleZ;
/*     */     
/* 524 */     this.LFinger1.rotateAngleZ = this.LArmA.rotateAngleZ;
/* 525 */     this.LFinger2.rotateAngleZ = this.LArmA.rotateAngleZ;
/* 526 */     this.LFinger3.rotateAngleZ = this.LArmA.rotateAngleZ;
/* 527 */     this.LFinger4.rotateAngleZ = this.LArmA.rotateAngleZ;
/* 528 */     this.LFinger5.rotateAngleZ = this.LArmA.rotateAngleZ;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelWere.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */