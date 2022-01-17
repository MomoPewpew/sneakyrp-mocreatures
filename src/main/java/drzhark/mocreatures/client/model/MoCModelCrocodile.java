/*     */ package drzhark.mocreatures.client.model;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MoCModelCrocodile extends ModelBase { ModelRenderer LJaw; ModelRenderer TailA; ModelRenderer TailB;
/*     */   ModelRenderer TailC;
/*     */   ModelRenderer UJaw;
/*     */   ModelRenderer Head;
/*     */   ModelRenderer Body;
/*     */   ModelRenderer Leg1;
/*     */   ModelRenderer Leg3;
/*     */   ModelRenderer Leg2;
/*     */   ModelRenderer Leg4;
/*     */   ModelRenderer TailD;
/*     */
/*     */   public MoCModelCrocodile() {
/*  14 */     this.LJaw = new ModelRenderer(this, 42, 0);
/*  15 */     this.LJaw.addBox(-2.5F, 1.0F, -12.0F, 5, 2, 6);
/*  16 */     this.LJaw.setRotationPoint(0.0F, 18.0F, -8.0F);
/*  17 */     this.LJaw.rotateAngleX = 0.0F;
/*  18 */     this.LJaw.rotateAngleY = 0.0F;
/*  19 */     this.LJaw.rotateAngleZ = 0.0F;
/*     */
/*  21 */     this.TailA = new ModelRenderer(this, 0, 0);
/*  22 */     this.TailA.addBox(-4.0F, -0.5F, 0.0F, 8, 4, 8);
/*  23 */     this.TailA.setRotationPoint(0.0F, 17.0F, 12.0F);
/*  24 */     this.TailA.rotateAngleX = 0.0F;
/*  25 */     this.TailA.rotateAngleY = 0.0F;
/*  26 */     this.TailA.rotateAngleZ = 0.0F;
/*     */
/*  28 */     this.TailB = new ModelRenderer(this, 2, 0);
/*  29 */     this.TailB.addBox(-3.0F, 0.0F, 8.0F, 6, 3, 8);
/*  30 */     this.TailB.setRotationPoint(0.0F, 17.0F, 12.0F);
/*  31 */     this.TailB.rotateAngleX = 0.0F;
/*  32 */     this.TailB.rotateAngleY = 0.0F;
/*  33 */     this.TailB.rotateAngleZ = 0.0F;
/*     */
/*  35 */     this.TailC = new ModelRenderer(this, 6, 2);
/*  36 */     this.TailC.addBox(-2.0F, 0.5F, 16.0F, 4, 2, 6);
/*  37 */     this.TailC.setRotationPoint(0.0F, 17.0F, 12.0F);
/*  38 */     this.TailC.rotateAngleX = 0.0F;
/*  39 */     this.TailC.rotateAngleY = 0.0F;
/*  40 */     this.TailC.rotateAngleZ = 0.0F;
/*     */
/*  42 */     this.TailD = new ModelRenderer(this, 7, 2);
/*  43 */     this.TailD.addBox(-1.5F, 1.0F, 22.0F, 3, 1, 6);
/*  44 */     this.TailD.setRotationPoint(0.0F, 17.0F, 12.0F);
/*  45 */     this.TailD.rotateAngleX = 0.0F;
/*  46 */     this.TailD.rotateAngleY = 0.0F;
/*  47 */     this.TailD.rotateAngleZ = 0.0F;
/*     */
/*  49 */     this.UJaw = new ModelRenderer(this, 44, 8);
/*  50 */     this.UJaw.addBox(-2.0F, -1.0F, -12.0F, 4, 2, 6);
/*  51 */     this.UJaw.setRotationPoint(0.0F, 18.0F, -8.0F);
/*  52 */     this.UJaw.rotateAngleX = 0.0F;
/*  53 */     this.UJaw.rotateAngleY = 0.0F;
/*  54 */     this.UJaw.rotateAngleZ = 0.0F;
/*     */
/*  56 */     this.Head = new ModelRenderer(this, 0, 16);
/*  57 */     this.Head.addBox(-3.0F, -2.0F, -6.0F, 6, 5, 6);
/*  58 */     this.Head.setRotationPoint(0.0F, 18.0F, -8.0F);
/*  59 */     this.Head.rotateAngleX = 0.0F;
/*  60 */     this.Head.rotateAngleY = 0.0F;
/*  61 */     this.Head.rotateAngleZ = 0.0F;
/*     */
/*  63 */     this.Body = new ModelRenderer(this, 4, 7);
/*  64 */     this.Body.addBox(0.0F, 0.0F, 0.0F, 10, 5, 20);
/*  65 */     this.Body.setRotationPoint(-5.0F, 16.0F, -8.0F);
/*  66 */     this.Body.rotateAngleX = 0.0F;
/*  67 */     this.Body.rotateAngleY = 0.0F;
/*  68 */     this.Body.rotateAngleZ = 0.0F;
/*     */
/*  70 */     this.Leg1 = new ModelRenderer(this, 49, 21);
/*  71 */     this.Leg1.addBox(1.0F, 2.0F, -3.0F, 3, 2, 4);
/*  72 */     this.Leg1.setRotationPoint(5.0F, 19.0F, -3.0F);
/*     */
/*  74 */     this.Leg1.rotateAngleX = 0.0F;
/*  75 */     this.Leg1.rotateAngleY = 0.0F;
/*  76 */     this.Leg1.rotateAngleZ = 0.0F;
/*     */
/*  78 */     this.Leg3 = new ModelRenderer(this, 48, 20);
/*  79 */     this.Leg3.addBox(1.0F, 2.0F, -3.0F, 3, 2, 5);
/*  80 */     this.Leg3.setRotationPoint(5.0F, 19.0F, 9.0F);
/*     */
/*  82 */     this.Leg3.rotateAngleX = 0.0F;
/*  83 */     this.Leg3.rotateAngleY = 0.0F;
/*  84 */     this.Leg3.rotateAngleZ = 0.0F;
/*     */
/*  86 */     this.Leg2 = new ModelRenderer(this, 49, 21);
/*  87 */     this.Leg2.addBox(-4.0F, 2.0F, -3.0F, 3, 2, 4);
/*  88 */     this.Leg2.setRotationPoint(-5.0F, 19.0F, -3.0F);
/*     */
/*  90 */     this.Leg2.rotateAngleX = 0.0F;
/*  91 */     this.Leg2.rotateAngleY = 0.0F;
/*  92 */     this.Leg2.rotateAngleZ = 0.0F;
/*     */
/*  94 */     this.Leg4 = new ModelRenderer(this, 48, 20);
/*  95 */     this.Leg4.addBox(-4.0F, 2.0F, -3.0F, 3, 2, 5);
/*  96 */     this.Leg4.setRotationPoint(-5.0F, 19.0F, 9.0F);
/*     */
/*  98 */     this.Leg4.rotateAngleX = 0.0F;
/*  99 */     this.Leg4.rotateAngleY = 0.0F;
/* 100 */     this.Leg4.rotateAngleZ = 0.0F;
/*     */
/* 102 */     this.Leg1A = new ModelRenderer(this, 7, 9);
/* 103 */     this.Leg1A.addBox(0.0F, -1.0F, -2.0F, 3, 3, 3);
/* 104 */     this.Leg1A.setRotationPoint(5.0F, 19.0F, -3.0F);
/*     */
/* 106 */     this.Leg1A.rotateAngleX = 0.0F;
/* 107 */     this.Leg1A.rotateAngleY = 0.0F;
/* 108 */     this.Leg1A.rotateAngleZ = 0.0F;
/*     */
/* 110 */     this.Leg2A = new ModelRenderer(this, 7, 9);
/* 111 */     this.Leg2A.addBox(-3.0F, -1.0F, -2.0F, 3, 3, 3);
/* 112 */     this.Leg2A.setRotationPoint(-5.0F, 19.0F, -3.0F);
/*     */
/* 114 */     this.Leg2A.rotateAngleX = 0.0F;
/* 115 */     this.Leg2A.rotateAngleY = 0.0F;
/* 116 */     this.Leg2A.rotateAngleZ = 0.0F;
/*     */
/* 118 */     this.Leg3A = new ModelRenderer(this, 6, 8);
/* 119 */     this.Leg3A.addBox(0.0F, -1.0F, -2.0F, 3, 3, 4);
/* 120 */     this.Leg3A.setRotationPoint(5.0F, 19.0F, 9.0F);
/*     */
/* 122 */     this.Leg3A.rotateAngleX = 0.0F;
/* 123 */     this.Leg3A.rotateAngleY = 0.0F;
/* 124 */     this.Leg3A.rotateAngleZ = 0.0F;
/*     */
/* 126 */     this.Leg4A = new ModelRenderer(this, 6, 8);
/* 127 */     this.Leg4A.addBox(-3.0F, -1.0F, -2.0F, 3, 3, 4);
/* 128 */     this.Leg4A.setRotationPoint(-5.0F, 19.0F, 9.0F);
/*     */
/* 130 */     this.Leg4A.rotateAngleX = 0.0F;
/* 131 */     this.Leg4A.rotateAngleY = 0.0F;
/* 132 */     this.Leg4A.rotateAngleZ = 0.0F;
/*     */
/* 134 */     this.UJaw2 = new ModelRenderer(this, 37, 0);
/* 135 */     this.UJaw2.addBox(-1.5F, -1.0F, -16.0F, 3, 2, 4);
/* 136 */     this.UJaw2.setRotationPoint(0.0F, 18.0F, -8.0F);
/* 137 */     this.UJaw2.rotateAngleX = 0.0F;
/* 138 */     this.UJaw2.rotateAngleY = 0.0F;
/* 139 */     this.UJaw2.rotateAngleZ = 0.0F;
/*     */
/* 141 */     this.LJaw2 = new ModelRenderer(this, 24, 1);
/* 142 */     this.LJaw2.addBox(-2.0F, 1.0F, -16.0F, 4, 2, 4);
/* 143 */     this.LJaw2.setRotationPoint(0.0F, 18.0F, -8.0F);
/* 144 */     this.LJaw2.rotateAngleX = 0.0F;
/* 145 */     this.LJaw2.rotateAngleY = 0.0F;
/* 146 */     this.LJaw2.rotateAngleZ = 0.0F;
/*     */
/* 148 */     this.TeethA = new ModelRenderer(this, 8, 11);
/* 149 */     this.TeethA.addBox(1.6F, 0.0F, -16.0F, 0, 1, 4);
/* 150 */     this.TeethA.setRotationPoint(0.0F, 18.0F, -8.0F);
/* 151 */     this.TeethA.rotateAngleX = 0.0F;
/* 152 */     this.TeethA.rotateAngleY = 0.0F;
/* 153 */     this.TeethA.rotateAngleZ = 0.0F;
/*     */
/* 155 */     this.TeethB = new ModelRenderer(this, 8, 11);
/* 156 */     this.TeethB.addBox(-1.6F, 0.0F, -16.0F, 0, 1, 4);
/* 157 */     this.TeethB.setRotationPoint(0.0F, 18.0F, -8.0F);
/* 158 */     this.TeethB.rotateAngleX = 0.0F;
/* 159 */     this.TeethB.rotateAngleY = 0.0F;
/* 160 */     this.TeethB.rotateAngleZ = 0.0F;
/*     */
/* 162 */     this.TeethC = new ModelRenderer(this, 6, 9);
/* 163 */     this.TeethC.addBox(2.1F, 0.0F, -12.0F, 0, 1, 6);
/* 164 */     this.TeethC.setRotationPoint(0.0F, 18.0F, -8.0F);
/* 165 */     this.TeethC.rotateAngleX = 0.0F;
/* 166 */     this.TeethC.rotateAngleY = 0.0F;
/* 167 */     this.TeethC.rotateAngleZ = 0.0F;
/*     */
/* 169 */     this.TeethD = new ModelRenderer(this, 6, 9);
/* 170 */     this.TeethD.addBox(-2.1F, 0.0F, -12.0F, 0, 1, 6);
/* 171 */     this.TeethD.setRotationPoint(0.0F, 18.0F, -8.0F);
/* 172 */     this.TeethD.rotateAngleX = 0.0F;
/* 173 */     this.TeethD.rotateAngleY = 0.0F;
/* 174 */     this.TeethD.rotateAngleZ = 0.0F;
/*     */
/* 176 */     this.Leg1A.rotateAngleX = 0.0F;
/* 177 */     this.Leg1A.rotateAngleY = 0.0F;
/* 178 */     this.Leg1A.rotateAngleZ = 0.0F;
/*     */
/* 180 */     this.Leg2A.rotateAngleX = 0.0F;
/* 181 */     this.Leg2A.rotateAngleY = 0.0F;
/* 182 */     this.Leg2A.rotateAngleZ = 0.0F;
/*     */
/* 184 */     this.Leg3A.rotateAngleX = 0.0F;
/* 185 */     this.Leg3A.rotateAngleY = 0.0F;
/* 186 */     this.Leg3A.rotateAngleZ = 0.0F;
/*     */
/* 188 */     this.Leg4A.rotateAngleX = 0.0F;
/* 189 */     this.Leg4A.rotateAngleY = 0.0F;
/* 190 */     this.Leg4A.rotateAngleZ = 0.0F;
/*     */
/* 192 */     this.TeethF = new ModelRenderer(this, 19, 21);
/* 193 */     this.TeethF.addBox(-1.0F, 0.0F, -16.1F, 2, 1, 0);
/* 194 */     this.TeethF.setRotationPoint(0.0F, 18.0F, -8.0F);
/*     */
/* 196 */     this.Spike0 = new ModelRenderer(this, 44, 16);
/* 197 */     this.Spike0.addBox(-1.0F, -1.0F, 23.0F, 0, 2, 4);
/* 198 */     this.Spike0.setRotationPoint(0.0F, 17.0F, 12.0F);
/*     */
/* 200 */     this.Spike1 = new ModelRenderer(this, 44, 16);
/* 201 */     this.Spike1.addBox(1.0F, -1.0F, 23.0F, 0, 2, 4);
/* 202 */     this.Spike1.setRotationPoint(0.0F, 17.0F, 12.0F);
/*     */
/* 204 */     this.Spike2 = new ModelRenderer(this, 44, 16);
/* 205 */     this.Spike2.addBox(-1.5F, -1.5F, 17.0F, 0, 2, 4);
/* 206 */     this.Spike2.setRotationPoint(0.0F, 17.0F, 12.0F);
/*     */
/* 208 */     this.Spike3 = new ModelRenderer(this, 44, 16);
/* 209 */     this.Spike3.addBox(1.5F, -1.5F, 17.0F, 0, 2, 4);
/* 210 */     this.Spike3.setRotationPoint(0.0F, 17.0F, 12.0F);
/*     */
/* 212 */     this.Spike4 = new ModelRenderer(this, 44, 16);
/* 213 */     this.Spike4.addBox(-2.0F, -2.0F, 12.0F, 0, 2, 4);
/* 214 */     this.Spike4.setRotationPoint(0.0F, 17.0F, 12.0F);
/*     */
/* 216 */     this.Spike5 = new ModelRenderer(this, 44, 16);
/* 217 */     this.Spike5.addBox(2.0F, -2.0F, 12.0F, 0, 2, 4);
/* 218 */     this.Spike5.setRotationPoint(0.0F, 17.0F, 12.0F);
/*     */
/* 220 */     this.Spike6 = new ModelRenderer(this, 44, 16);
/* 221 */     this.Spike6.addBox(-2.5F, -2.0F, 8.0F, 0, 2, 4);
/* 222 */     this.Spike6.setRotationPoint(0.0F, 17.0F, 12.0F);
/*     */
/* 224 */     this.Spike7 = new ModelRenderer(this, 44, 16);
/* 225 */     this.Spike7.addBox(2.5F, -2.0F, 8.0F, 0, 2, 4);
/* 226 */     this.Spike7.setRotationPoint(0.0F, 17.0F, 12.0F);
/*     */
/* 228 */     this.Spike8 = new ModelRenderer(this, 44, 16);
/* 229 */     this.Spike8.addBox(-3.0F, -2.5F, 4.0F, 0, 2, 4);
/* 230 */     this.Spike8.setRotationPoint(0.0F, 17.0F, 12.0F);
/*     */
/* 232 */     this.Spike9 = new ModelRenderer(this, 44, 16);
/* 233 */     this.Spike9.addBox(3.0F, -2.5F, 4.0F, 0, 2, 4);
/* 234 */     this.Spike9.setRotationPoint(0.0F, 17.0F, 12.0F);
/*     */
/* 236 */     this.Spike10 = new ModelRenderer(this, 44, 16);
/* 237 */     this.Spike10.addBox(3.5F, -2.5F, 0.0F, 0, 2, 4);
/* 238 */     this.Spike10.setRotationPoint(0.0F, 17.0F, 12.0F);
/*     */
/* 240 */     this.Spike11 = new ModelRenderer(this, 44, 16);
/* 241 */     this.Spike11.addBox(-3.5F, -2.5F, 0.0F, 0, 2, 4);
/* 242 */     this.Spike11.setRotationPoint(0.0F, 17.0F, 12.0F);
/*     */
/* 244 */     this.SpikeBack0 = new ModelRenderer(this, 44, 10);
/* 245 */     this.SpikeBack0.addBox(0.0F, 0.0F, 0.0F, 0, 2, 8);
/* 246 */     this.SpikeBack0.setRotationPoint(0.0F, 14.0F, 3.0F);
/*     */
/* 248 */     this.SpikeBack1 = new ModelRenderer(this, 44, 10);
/* 249 */     this.SpikeBack1.addBox(0.0F, 0.0F, 0.0F, 0, 2, 8);
/* 250 */     this.SpikeBack1.setRotationPoint(0.0F, 14.0F, -6.0F);
/*     */
/* 252 */     this.SpikeBack2 = new ModelRenderer(this, 44, 10);
/* 253 */     this.SpikeBack2.addBox(0.0F, 0.0F, 0.0F, 0, 2, 8);
/* 254 */     this.SpikeBack2.setRotationPoint(4.0F, 14.0F, -8.0F);
/*     */
/* 256 */     this.SpikeBack3 = new ModelRenderer(this, 44, 10);
/* 257 */     this.SpikeBack3.addBox(0.0F, 0.0F, 0.0F, 0, 2, 8);
/* 258 */     this.SpikeBack3.setRotationPoint(-4.0F, 14.0F, -8.0F);
/*     */
/* 260 */     this.SpikeBack4 = new ModelRenderer(this, 44, 10);
/* 261 */     this.SpikeBack4.addBox(0.0F, 0.0F, 0.0F, 0, 2, 8);
/* 262 */     this.SpikeBack4.setRotationPoint(-4.0F, 14.0F, 1.0F);
/*     */
/* 264 */     this.SpikeBack5 = new ModelRenderer(this, 44, 10);
/* 265 */     this.SpikeBack5.addBox(0.0F, 0.0F, 0.0F, 0, 2, 8);
/* 266 */     this.SpikeBack5.setRotationPoint(4.0F, 14.0F, 1.0F);
/*     */
/* 268 */     this.SpikeEye = new ModelRenderer(this, 44, 14);
/* 269 */     this.SpikeEye.addBox(-3.0F, -3.0F, -6.0F, 0, 1, 2);
/* 270 */     this.SpikeEye.setRotationPoint(0.0F, 18.0F, -8.0F);
/*     */
/* 272 */     this.SpikeEye1 = new ModelRenderer(this, 44, 14);
/* 273 */     this.SpikeEye1.addBox(3.0F, -3.0F, -6.0F, 0, 1, 2);
/* 274 */     this.SpikeEye1.setRotationPoint(0.0F, 18.0F, -8.0F);
/*     */
/* 276 */     this.TeethA1 = new ModelRenderer(this, 52, 12);
/* 277 */     this.TeethA1.addBox(1.4F, 1.0F, -16.4F, 0, 1, 4);
/* 278 */     this.TeethA1.setRotationPoint(0.0F, 18.0F, -8.0F);
/*     */
/* 280 */     this.TeethB1 = new ModelRenderer(this, 52, 12);
/* 281 */     this.TeethB1.addBox(-1.4F, 1.0F, -16.4F, 0, 1, 4);
/* 282 */     this.TeethB1.setRotationPoint(0.0F, 18.0F, -8.0F);
/*     */
/* 284 */     this.TeethC1 = new ModelRenderer(this, 50, 10);
/* 285 */     this.TeethC1.addBox(1.9F, 1.0F, -12.5F, 0, 1, 6);
/* 286 */     this.TeethC1.setRotationPoint(0.0F, 18.0F, -8.0F);
/*     */
/* 288 */     this.TeethD1 = new ModelRenderer(this, 50, 10);
/* 289 */     this.TeethD1.addBox(-1.9F, 1.0F, -12.5F, 0, 1, 6);
/* 290 */     this.TeethD1.setRotationPoint(0.0F, 18.0F, -8.0F);
/*     */   }
/*     */   ModelRenderer Leg1A; ModelRenderer Leg2A; ModelRenderer Leg3A; ModelRenderer Leg4A; ModelRenderer UJaw2; ModelRenderer LJaw2; ModelRenderer TeethA; ModelRenderer TeethB; ModelRenderer TeethC; ModelRenderer TeethD; public float biteProgress; public boolean swimming; public boolean resting; ModelRenderer TeethF; ModelRenderer Spike0; ModelRenderer Spike1; ModelRenderer Spike2; ModelRenderer Spike3; ModelRenderer Spike4; ModelRenderer Spike5; ModelRenderer Spike6; ModelRenderer Spike7; ModelRenderer Spike8; ModelRenderer Spike9; ModelRenderer Spike10; ModelRenderer Spike11; ModelRenderer SpikeBack0; ModelRenderer SpikeBack1; ModelRenderer SpikeBack2; ModelRenderer SpikeBack3; ModelRenderer SpikeBack4; ModelRenderer SpikeBack5; ModelRenderer SpikeEye; ModelRenderer SpikeEye1; ModelRenderer TeethA1; ModelRenderer TeethB1;
/*     */   ModelRenderer TeethC1;
/*     */   ModelRenderer TeethD1;
/*     */
/*     */   public void model2() {}
/*     */
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 299 */     super.render(entity, f, f1, f2, f3, f4, f5);
/* 300 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/* 301 */     this.LJaw.render(f5);
/* 302 */     this.TailA.render(f5);
/* 303 */     this.TailB.render(f5);
/* 304 */     this.TailC.render(f5);
/* 305 */     this.UJaw.render(f5);
/* 306 */     this.Head.render(f5);
/* 307 */     this.Body.render(f5);
/* 308 */     this.Leg1.render(f5);
/* 309 */     this.Leg3.render(f5);
/* 310 */     this.Leg2.render(f5);
/* 311 */     this.Leg4.render(f5);
/* 312 */     this.TailD.render(f5);
/* 313 */     this.Leg1A.render(f5);
/* 314 */     this.Leg2A.render(f5);
/* 315 */     this.Leg3A.render(f5);
/* 316 */     this.Leg4A.render(f5);
/* 317 */     this.UJaw2.render(f5);
/* 318 */     this.LJaw2.render(f5);
/* 319 */     this.TeethA.render(f5);
/* 320 */     this.TeethB.render(f5);
/* 321 */     this.TeethC.render(f5);
/* 322 */     this.TeethD.render(f5);
/* 323 */     this.TeethF.render(f5);
/* 324 */     this.Spike0.render(f5);
/* 325 */     this.Spike1.render(f5);
/* 326 */     this.Spike2.render(f5);
/* 327 */     this.Spike3.render(f5);
/* 328 */     this.Spike4.render(f5);
/* 329 */     this.Spike5.render(f5);
/* 330 */     this.Spike6.render(f5);
/* 331 */     this.Spike7.render(f5);
/* 332 */     this.Spike8.render(f5);
/* 333 */     this.Spike9.render(f5);
/* 334 */     this.Spike10.render(f5);
/* 335 */     this.Spike11.render(f5);
/* 336 */     this.SpikeBack0.render(f5);
/* 337 */     this.SpikeBack1.render(f5);
/* 338 */     this.SpikeBack2.render(f5);
/* 339 */     this.SpikeBack3.render(f5);
/* 340 */     this.SpikeBack4.render(f5);
/* 341 */     this.SpikeBack5.render(f5);
/* 342 */     this.SpikeEye.render(f5);
/* 343 */     this.SpikeEye1.render(f5);
/* 344 */     this.TeethA1.render(f5);
/* 345 */     this.TeethB1.render(f5);
/* 346 */     this.TeethC1.render(f5);
/* 347 */     this.TeethD1.render(f5);
/*     */   }
/*     */
/*     */
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 352 */     this.Head.rotateAngleX = f4 / 57.29578F;
/* 353 */     this.Head.rotateAngleY = f3 / 57.29578F;
/* 354 */     this.SpikeEye.rotateAngleX = this.Head.rotateAngleX;
/* 355 */     this.SpikeEye.rotateAngleY = this.Head.rotateAngleY;
/* 356 */     this.SpikeEye1.rotateAngleX = this.Head.rotateAngleX;
/* 357 */     this.SpikeEye1.rotateAngleY = this.Head.rotateAngleY;
/*     */
/*     */
/* 360 */     this.LJaw.rotateAngleY = this.Head.rotateAngleY;
/* 361 */     this.LJaw2.rotateAngleY = this.Head.rotateAngleY;
/*     */
/* 363 */     this.UJaw.rotateAngleY = this.Head.rotateAngleY;
/* 364 */     this.UJaw2.rotateAngleY = this.Head.rotateAngleY;
/* 365 */     if (this.swimming) {
/*     */
/* 367 */       this.Leg1.rotationPointX = 9.0F;
/* 368 */       this.Leg1.rotationPointY = 18.0F;
/* 369 */       this.Leg1.rotationPointZ = 0.0F;
/* 370 */       this.Leg1.rotateAngleX = 0.0F;
/* 371 */       this.Leg1.rotateAngleY = -3.14159F;
/*     */
/* 373 */       this.Leg2.rotationPointX = -9.0F;
/* 374 */       this.Leg2.rotationPointY = 18.0F;
/* 375 */       this.Leg2.rotationPointZ = 0.0F;
/* 376 */       this.Leg2.rotateAngleX = 0.0F;
/* 377 */       this.Leg2.rotateAngleY = -3.14159F;
/*     */
/* 379 */       this.Leg3.rotationPointX = 8.0F;
/* 380 */       this.Leg3.rotationPointY = 18.0F;
/* 381 */       this.Leg3.rotationPointZ = 12.0F;
/* 382 */       this.Leg3.rotateAngleX = 0.0F;
/* 383 */       this.Leg3.rotateAngleY = -3.14159F;
/*     */
/* 385 */       this.Leg4.rotationPointX = -8.0F;
/* 386 */       this.Leg4.rotationPointY = 18.0F;
/* 387 */       this.Leg4.rotationPointZ = 12.0F;
/* 388 */       this.Leg4.rotateAngleX = 0.0F;
/* 389 */       this.Leg4.rotateAngleY = -3.14159F;
/*     */
/*     */
/* 392 */       this.Leg1A.rotateAngleX = 1.5708F;
/* 393 */       this.Leg1A.rotationPointX = 5.0F;
/* 394 */       this.Leg1A.rotationPointY = 19.0F;
/* 395 */       this.Leg1A.rotationPointZ = -3.0F;
/*     */
/*     */
/* 398 */       this.Leg2A.rotateAngleX = 1.5708F;
/* 399 */       this.Leg2A.rotationPointX = -5.0F;
/* 400 */       this.Leg2A.rotationPointY = 19.0F;
/* 401 */       this.Leg2A.rotationPointZ = -3.0F;
/*     */
/*     */
/* 404 */       this.Leg3A.rotateAngleX = 1.5708F;
/* 405 */       this.Leg3A.rotationPointX = 5.0F;
/* 406 */       this.Leg3A.rotationPointY = 19.0F;
/* 407 */       this.Leg3A.rotationPointZ = 9.0F;
/*     */
/*     */
/* 410 */       this.Leg4A.rotateAngleX = 1.5708F;
/* 411 */       this.Leg4A.rotationPointX = -5.0F;
/* 412 */       this.Leg4A.rotationPointY = 19.0F;
/* 413 */       this.Leg4A.rotationPointZ = 9.0F;
/*     */
/* 415 */       this.Leg1.rotateAngleZ = 0.0F;
/* 416 */       this.Leg1A.rotateAngleZ = 0.0F;
/* 417 */       this.Leg3.rotateAngleZ = 0.0F;
/* 418 */       this.Leg3A.rotateAngleZ = 0.0F;
/*     */
/* 420 */       this.Leg2.rotateAngleZ = 0.0F;
/* 421 */       this.Leg2A.rotateAngleZ = 0.0F;
/* 422 */       this.Leg4.rotateAngleZ = 0.0F;
/* 423 */       this.Leg4A.rotateAngleZ = 0.0F;
/*     */     }
/* 425 */     else if (this.resting) {
/*     */
/* 427 */       this.Leg1.rotationPointX = 6.0F;
/* 428 */       this.Leg1.rotationPointY = 17.0F;
/* 429 */       this.Leg1.rotationPointZ = -6.0F;
/* 430 */       this.Leg1.rotateAngleX = 0.0F;
/* 431 */       this.Leg1.rotateAngleY = -0.7854F;
/*     */
/*     */
/* 434 */       this.Leg2.rotateAngleY = 0.7854F;
/* 435 */       this.Leg2.rotationPointX = -6.0F;
/* 436 */       this.Leg2.rotationPointY = 17.0F;
/* 437 */       this.Leg2.rotationPointZ = -6.0F;
/* 438 */       this.Leg2.rotateAngleX = 0.0F;
/*     */
/*     */
/* 441 */       this.Leg3.rotateAngleY = -0.7854F;
/* 442 */       this.Leg3.rotationPointX = 7.0F;
/* 443 */       this.Leg3.rotationPointY = 17.0F;
/* 444 */       this.Leg3.rotationPointZ = 7.0F;
/* 445 */       this.Leg3.rotateAngleX = 0.0F;
/*     */
/* 447 */       this.Leg4.setRotationPoint(-7.0F, 17.0F, 7.0F);
/* 448 */       this.Leg4.rotateAngleY = 0.7854F;
/* 449 */       this.Leg4.rotationPointX = -7.0F;
/* 450 */       this.Leg4.rotationPointY = 17.0F;
/* 451 */       this.Leg4.rotationPointZ = 7.0F;
/* 452 */       this.Leg4.rotateAngleX = 0.0F;
/*     */
/*     */
/* 455 */       this.Leg1A.rotationPointX = 5.0F;
/* 456 */       this.Leg1A.rotationPointY = 17.0F;
/* 457 */       this.Leg1A.rotationPointZ = -3.0F;
/* 458 */       this.Leg1A.rotateAngleX = 0.0F;
/*     */
/*     */
/* 461 */       this.Leg2A.rotationPointX = -5.0F;
/* 462 */       this.Leg2A.rotationPointY = 17.0F;
/* 463 */       this.Leg2A.rotationPointZ = -3.0F;
/* 464 */       this.Leg2A.rotateAngleX = 0.0F;
/*     */
/*     */
/* 467 */       this.Leg3A.rotationPointX = 5.0F;
/* 468 */       this.Leg3A.rotationPointY = 17.0F;
/* 469 */       this.Leg3A.rotationPointZ = 9.0F;
/* 470 */       this.Leg3A.rotateAngleX = 0.0F;
/*     */
/*     */
/* 473 */       this.Leg4A.rotationPointX = -5.0F;
/* 474 */       this.Leg4A.rotationPointY = 17.0F;
/* 475 */       this.Leg4A.rotationPointZ = 9.0F;
/* 476 */       this.Leg4A.rotateAngleX = 0.0F;
/*     */
/* 478 */       this.Leg1.rotateAngleZ = 0.0F;
/* 479 */       this.Leg1A.rotateAngleZ = 0.0F;
/* 480 */       this.Leg3.rotateAngleZ = 0.0F;
/* 481 */       this.Leg3A.rotateAngleZ = 0.0F;
/*     */
/* 483 */       this.Leg2.rotateAngleZ = 0.0F;
/* 484 */       this.Leg2A.rotateAngleZ = 0.0F;
/* 485 */       this.Leg4.rotateAngleZ = 0.0F;
/* 486 */       this.Leg4A.rotateAngleZ = 0.0F;
/*     */     } else {
/*     */
/* 489 */       this.Leg1.rotationPointX = 5.0F;
/* 490 */       this.Leg1.rotationPointY = 19.0F;
/* 491 */       this.Leg1.rotationPointZ = -3.0F;
/*     */
/* 493 */       this.Leg2.rotationPointX = -5.0F;
/* 494 */       this.Leg2.rotationPointY = 19.0F;
/* 495 */       this.Leg2.rotationPointZ = -3.0F;
/*     */
/* 497 */       this.Leg3.rotationPointX = 5.0F;
/* 498 */       this.Leg3.rotationPointY = 19.0F;
/* 499 */       this.Leg3.rotationPointZ = 9.0F;
/*     */
/* 501 */       this.Leg4.rotationPointX = -5.0F;
/* 502 */       this.Leg4.rotationPointY = 19.0F;
/* 503 */       this.Leg4.rotationPointZ = 9.0F;
/*     */
/*     */
/* 506 */       this.Leg1A.rotationPointX = 5.0F;
/* 507 */       this.Leg1A.rotationPointY = 19.0F;
/* 508 */       this.Leg1A.rotationPointZ = -3.0F;
/*     */
/*     */
/* 511 */       this.Leg2A.rotationPointX = -5.0F;
/* 512 */       this.Leg2A.rotationPointY = 19.0F;
/* 513 */       this.Leg2A.rotationPointZ = -3.0F;
/*     */
/*     */
/* 516 */       this.Leg3A.rotationPointX = 5.0F;
/* 517 */       this.Leg3A.rotationPointY = 19.0F;
/* 518 */       this.Leg3A.rotationPointZ = 9.0F;
/*     */
/*     */
/* 521 */       this.Leg4A.rotationPointX = -5.0F;
/* 522 */       this.Leg4A.rotationPointY = 19.0F;
/* 523 */       this.Leg4A.rotationPointZ = 9.0F;
/*     */
/* 525 */       this.Leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
/* 526 */       this.Leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
/* 527 */       this.Leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
/* 528 */       this.Leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
/*     */
/* 530 */       this.Leg1.rotateAngleY = 0.0F;
/* 531 */       this.Leg2.rotateAngleY = 0.0F;
/* 532 */       this.Leg3.rotateAngleY = 0.0F;
/* 533 */       this.Leg4.rotateAngleY = 0.0F;
/*     */
/* 535 */       this.Leg1A.rotateAngleX = this.Leg1.rotateAngleX;
/* 536 */       this.Leg2A.rotateAngleX = this.Leg2.rotateAngleX;
/* 537 */       this.Leg3A.rotateAngleX = this.Leg3.rotateAngleX;
/* 538 */       this.Leg4A.rotateAngleX = this.Leg4.rotateAngleX;
/*     */
/* 540 */       float latrot = MathHelper.cos(f / 1.9191077F) * 0.2617994F * f1 * 5.0F;
/* 541 */       this.Leg1.rotateAngleZ = latrot;
/* 542 */       this.Leg1A.rotateAngleZ = latrot;
/* 543 */       this.Leg4.rotateAngleZ = -latrot;
/* 544 */       this.Leg4A.rotateAngleZ = -latrot;
/*     */
/* 546 */       this.Leg3.rotateAngleZ = latrot;
/* 547 */       this.Leg3A.rotateAngleZ = latrot;
/*     */
/* 549 */       this.Leg2.rotateAngleZ = -latrot;
/* 550 */       this.Leg2A.rotateAngleZ = -latrot;
/*     */     }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/* 558 */     this.TailA.rotateAngleY = MathHelper.cos(f * 0.6662F) * 0.7F * f1;
/* 559 */     this.TailB.rotateAngleY = this.TailA.rotateAngleY;
/* 560 */     this.TailC.rotateAngleY = this.TailA.rotateAngleY;
/* 561 */     this.TailD.rotateAngleY = this.TailA.rotateAngleY;
/* 562 */     this.Spike0.rotateAngleY = this.TailA.rotateAngleY;
/* 563 */     this.Spike1.rotateAngleY = this.TailA.rotateAngleY;
/* 564 */     this.Spike2.rotateAngleY = this.TailA.rotateAngleY;
/* 565 */     this.Spike3.rotateAngleY = this.TailA.rotateAngleY;
/* 566 */     this.Spike4.rotateAngleY = this.TailA.rotateAngleY;
/* 567 */     this.Spike5.rotateAngleY = this.TailA.rotateAngleY;
/* 568 */     this.Spike6.rotateAngleY = this.TailA.rotateAngleY;
/* 569 */     this.Spike7.rotateAngleY = this.TailA.rotateAngleY;
/* 570 */     this.Spike8.rotateAngleY = this.TailA.rotateAngleY;
/* 571 */     this.Spike9.rotateAngleY = this.TailA.rotateAngleY;
/* 572 */     this.Spike10.rotateAngleY = this.TailA.rotateAngleY;
/* 573 */     this.Spike11.rotateAngleY = this.TailA.rotateAngleY;
/* 574 */     float f25 = this.biteProgress;
/* 575 */     float f26 = f25;
/* 576 */     if (f25 >= 0.5F) {
/* 577 */       f26 = 0.5F - f25 - 0.5F;
/*     */     }
/* 579 */     this.Head.rotateAngleX -= f26;
/* 580 */     this.UJaw2.rotateAngleX = this.UJaw.rotateAngleX;
/* 581 */     this.Head.rotateAngleX += f26 / 2.0F;
/* 582 */     this.LJaw2.rotateAngleX = this.LJaw.rotateAngleX;
/* 583 */     this.TeethA.rotateAngleX = this.LJaw.rotateAngleX;
/* 584 */     this.TeethB.rotateAngleX = this.LJaw.rotateAngleX;
/* 585 */     this.TeethC.rotateAngleX = this.LJaw.rotateAngleX;
/* 586 */     this.TeethD.rotateAngleX = this.LJaw.rotateAngleX;
/* 587 */     this.TeethF.rotateAngleX = this.LJaw.rotateAngleX;
/* 588 */     this.TeethA.rotateAngleY = this.LJaw.rotateAngleY;
/* 589 */     this.TeethB.rotateAngleY = this.LJaw.rotateAngleY;
/* 590 */     this.TeethC.rotateAngleY = this.LJaw.rotateAngleY;
/* 591 */     this.TeethD.rotateAngleY = this.LJaw.rotateAngleY;
/* 592 */     this.TeethF.rotateAngleY = this.LJaw.rotateAngleY;
/* 593 */     this.TeethA1.rotateAngleX = this.UJaw.rotateAngleX;
/* 594 */     this.TeethB1.rotateAngleX = this.UJaw.rotateAngleX;
/* 595 */     this.TeethC1.rotateAngleX = this.UJaw.rotateAngleX;
/* 596 */     this.TeethD1.rotateAngleX = this.UJaw.rotateAngleX;
/* 597 */     this.TeethA1.rotateAngleY = this.UJaw.rotateAngleY;
/* 598 */     this.TeethB1.rotateAngleY = this.UJaw.rotateAngleY;
/* 599 */     this.TeethC1.rotateAngleY = this.UJaw.rotateAngleY;
/* 600 */     this.TeethD1.rotateAngleY = this.UJaw.rotateAngleY;
/*     */   } }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelCrocodile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
