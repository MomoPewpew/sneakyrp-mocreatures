/*     */ package drzhark.mocreatures.client.model;
/*     */ 
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityBear;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MoCModelBear
/*     */   extends ModelBase
/*     */ {
/*     */   public ModelRenderer Saddle;
/*     */   public ModelRenderer SaddleBack;
/*     */   public ModelRenderer SaddleFront;
/*     */   public ModelRenderer Bag;
/*     */   public ModelRenderer SaddleSitted;
/*     */   public ModelRenderer SaddleBackSitted;
/*     */   public ModelRenderer SaddleFrontSitted;
/*     */   public ModelRenderer BagSitted;
/*     */   ModelRenderer Tail;
/*     */   ModelRenderer Mouth;
/*     */   ModelRenderer LegFR1;
/*     */   ModelRenderer Neck;
/*     */   ModelRenderer LEar;
/*     */   ModelRenderer Snout;
/*     */   ModelRenderer Head;
/*     */   ModelRenderer REar;
/*     */   ModelRenderer Abdomen;
/*     */   ModelRenderer Torso;
/*     */   ModelRenderer LegRR3;
/*     */   ModelRenderer LegRR1;
/*     */   ModelRenderer LegRR2;
/*     */   ModelRenderer LegFR2;
/*     */   ModelRenderer LegFR3;
/*     */   ModelRenderer LegFL1;
/*     */   ModelRenderer LegFL3;
/*     */   ModelRenderer LegFL2;
/*     */   ModelRenderer LegRL1;
/*     */   ModelRenderer LegRL2;
/*     */   ModelRenderer LegRL3;
/*     */   ModelRenderer MouthOpen;
/*     */   ModelRenderer BHead;
/*     */   ModelRenderer BSnout;
/*     */   ModelRenderer BMouth;
/*     */   ModelRenderer BMouthOpen;
/*     */   ModelRenderer BNeck;
/*     */   ModelRenderer BLEar;
/*     */   ModelRenderer BREar;
/*     */   ModelRenderer BTorso;
/*     */   ModelRenderer BAbdomen;
/*     */   ModelRenderer BTail;
/*     */   ModelRenderer BLegFL1;
/*     */   ModelRenderer BLegFL2;
/*     */   ModelRenderer BLegFL3;
/*     */   ModelRenderer BLegFR1;
/*     */   ModelRenderer BLegFR2;
/*     */   ModelRenderer BLegFR3;
/*     */   ModelRenderer BLegRL1;
/*     */   ModelRenderer BLegRL2;
/*     */   ModelRenderer BLegRL3;
/*     */   ModelRenderer BLegRR1;
/*     */   ModelRenderer BLegRR2;
/*     */   ModelRenderer BLegRR3;
/*     */   ModelRenderer CHead;
/*     */   ModelRenderer CSnout;
/*     */   ModelRenderer CMouth;
/*     */   ModelRenderer CMouthOpen;
/*     */   ModelRenderer CLEar;
/*     */   ModelRenderer CREar;
/*     */   ModelRenderer CNeck;
/*     */   ModelRenderer CTorso;
/*     */   ModelRenderer CAbdomen;
/*     */   ModelRenderer CTail;
/*     */   ModelRenderer CLegFL1;
/*     */   ModelRenderer CLegFL2;
/*     */   ModelRenderer CLegFL3;
/*     */   ModelRenderer CLegFR1;
/*     */   ModelRenderer CLegFR2;
/*     */   ModelRenderer CLegFR3;
/*     */   ModelRenderer CLegRL1;
/*     */   ModelRenderer CLegRL2;
/*     */   ModelRenderer CLegRL3;
/*     */   ModelRenderer CLegRR1;
/*     */   ModelRenderer CLegRR2;
/*     */   ModelRenderer CLegRR3;
/*     */   private int bearstate;
/*     */   private float attackSwing;
/*     */   
/*     */   public MoCModelBear() {
/*  96 */     this.textureWidth = 128;
/*  97 */     this.textureHeight = 128;
/*     */     
/*  99 */     this.Head = new ModelRenderer(this, 19, 0);
/* 100 */     this.Head.addBox(-4.0F, 0.0F, -4.0F, 8, 8, 5);
/* 101 */     this.Head.setRotationPoint(0.0F, 6.0F, -10.0F);
/* 102 */     setRotation(this.Head, 0.1502636F, 0.0F, 0.0F);
/*     */     
/* 104 */     this.Mouth = new ModelRenderer(this, 24, 21);
/* 105 */     this.Mouth.addBox(-1.5F, 6.0F, -6.8F, 3, 2, 5);
/* 106 */     this.Mouth.setRotationPoint(0.0F, 6.0F, -10.0F);
/* 107 */     setRotation(this.Mouth, -0.0068161F, 0.0F, 0.0F);
/*     */     
/* 109 */     this.MouthOpen = new ModelRenderer(this, 24, 21);
/* 110 */     this.MouthOpen.addBox(-1.5F, 4.0F, -9.5F, 3, 2, 5);
/* 111 */     this.MouthOpen.setRotationPoint(0.0F, 6.0F, -10.0F);
/* 112 */     setRotation(this.MouthOpen, 0.534236F, 0.0F, 0.0F);
/*     */     
/* 114 */     this.LEar = new ModelRenderer(this, 40, 0);
/* 115 */     this.LEar.addBox(2.0F, -2.0F, -2.0F, 3, 3, 1);
/* 116 */     this.LEar.setRotationPoint(0.0F, 6.0F, -10.0F);
/* 117 */     setRotation(this.LEar, 0.1502636F, -0.3490659F, 0.1396263F);
/*     */     
/* 119 */     this.REar = new ModelRenderer(this, 16, 0);
/* 120 */     this.REar.addBox(-5.0F, -2.0F, -2.0F, 3, 3, 1);
/* 121 */     this.REar.setRotationPoint(0.0F, 6.0F, -10.0F);
/* 122 */     setRotation(this.REar, 0.1502636F, 0.3490659F, -0.1396263F);
/*     */     
/* 124 */     this.Snout = new ModelRenderer(this, 23, 13);
/* 125 */     this.Snout.addBox(-2.0F, 3.0F, -8.0F, 4, 3, 5);
/* 126 */     this.Snout.setRotationPoint(0.0F, 6.0F, -10.0F);
/* 127 */     setRotation(this.Snout, 0.1502636F, 0.0F, 0.0F);
/*     */     
/* 129 */     this.Neck = new ModelRenderer(this, 18, 28);
/* 130 */     this.Neck.addBox(-3.5F, 0.0F, -7.0F, 7, 7, 7);
/* 131 */     this.Neck.setRotationPoint(0.0F, 5.0F, -5.0F);
/* 132 */     setRotation(this.Neck, 0.2617994F, 0.0F, 0.0F);
/*     */     
/* 134 */     this.Abdomen = new ModelRenderer(this, 13, 62);
/* 135 */     this.Abdomen.addBox(-4.5F, 0.0F, 0.0F, 9, 11, 10);
/* 136 */     this.Abdomen.setRotationPoint(0.0F, 5.0F, 5.0F);
/* 137 */     setRotation(this.Abdomen, -0.4363323F, 0.0F, 0.0F);
/*     */     
/* 139 */     this.Torso = new ModelRenderer(this, 12, 42);
/* 140 */     this.Torso.addBox(-5.0F, 0.0F, 0.0F, 10, 10, 10);
/* 141 */     this.Torso.setRotationPoint(0.0F, 5.0F, -5.0F);
/*     */     
/* 143 */     this.Tail = new ModelRenderer(this, 26, 83);
/* 144 */     this.Tail.addBox(-1.5F, 0.0F, 0.0F, 3, 3, 3);
/* 145 */     this.Tail.setRotationPoint(0.0F, 8.466666F, 12.0F);
/* 146 */     setRotation(this.Tail, 0.4363323F, 0.0F, 0.0F);
/*     */     
/* 148 */     this.LegFL1 = new ModelRenderer(this, 40, 22);
/* 149 */     this.LegFL1.addBox(-2.5F, 0.0F, -2.5F, 5, 8, 5);
/* 150 */     this.LegFL1.setRotationPoint(4.0F, 10.0F, -4.0F);
/* 151 */     setRotation(this.LegFL1, 0.2617994F, 0.0F, 0.0F);
/*     */     
/* 153 */     this.LegFL2 = new ModelRenderer(this, 46, 35);
/* 154 */     this.LegFL2.addBox(-2.0F, 7.0F, 0.0F, 4, 6, 4);
/* 155 */     this.LegFL2.setRotationPoint(4.0F, 10.0F, -4.0F);
/*     */     
/* 157 */     this.LegFL3 = new ModelRenderer(this, 46, 45);
/* 158 */     this.LegFL3.addBox(-2.0F, 12.0F, -1.0F, 4, 2, 5);
/* 159 */     this.LegFL3.setRotationPoint(4.0F, 10.0F, -4.0F);
/*     */     
/* 161 */     this.LegFR1 = new ModelRenderer(this, 4, 22);
/* 162 */     this.LegFR1.addBox(-2.5F, 0.0F, -2.5F, 5, 8, 5);
/* 163 */     this.LegFR1.setRotationPoint(-4.0F, 10.0F, -4.0F);
/* 164 */     setRotation(this.LegFR1, 0.2617994F, 0.0F, 0.0F);
/*     */     
/* 166 */     this.LegFR2 = new ModelRenderer(this, 2, 35);
/* 167 */     this.LegFR2.addBox(-2.0F, 7.0F, 0.0F, 4, 6, 4);
/* 168 */     this.LegFR2.setRotationPoint(-4.0F, 10.0F, -4.0F);
/*     */     
/* 170 */     this.LegFR3 = new ModelRenderer(this, 0, 45);
/* 171 */     this.LegFR3.addBox(-2.0F, 12.0F, -1.0F, 4, 2, 5);
/* 172 */     this.LegFR3.setRotationPoint(-4.0F, 10.0F, -4.0F);
/*     */     
/* 174 */     this.LegRL1 = new ModelRenderer(this, 34, 83);
/* 175 */     this.LegRL1.addBox(-1.5F, 0.0F, -2.5F, 4, 8, 6);
/* 176 */     this.LegRL1.setRotationPoint(3.5F, 11.0F, 9.0F);
/* 177 */     setRotation(this.LegRL1, -0.1745329F, 0.0F, 0.0F);
/*     */     
/* 179 */     this.LegRL2 = new ModelRenderer(this, 41, 97);
/* 180 */     this.LegRL2.addBox(-2.0F, 6.0F, -1.0F, 4, 6, 4);
/* 181 */     this.LegRL2.setRotationPoint(3.5F, 11.0F, 9.0F);
/*     */     
/* 183 */     this.LegRL3 = new ModelRenderer(this, 44, 107);
/* 184 */     this.LegRL3.addBox(-2.0F, 11.0F, -2.0F, 4, 2, 5);
/* 185 */     this.LegRL3.setRotationPoint(3.5F, 11.0F, 9.0F);
/*     */     
/* 187 */     this.LegRR1 = new ModelRenderer(this, 10, 83);
/* 188 */     this.LegRR1.addBox(-2.5F, 0.0F, -2.5F, 4, 8, 6);
/* 189 */     this.LegRR1.setRotationPoint(-3.5F, 11.0F, 9.0F);
/* 190 */     setRotation(this.LegRR1, -0.1745329F, 0.0F, 0.0F);
/*     */     
/* 192 */     this.LegRR2 = new ModelRenderer(this, 7, 97);
/* 193 */     this.LegRR2.addBox(-2.0F, 6.0F, -1.0F, 4, 6, 4);
/* 194 */     this.LegRR2.setRotationPoint(-3.5F, 11.0F, 9.0F);
/*     */     
/* 196 */     this.LegRR3 = new ModelRenderer(this, 2, 107);
/* 197 */     this.LegRR3.addBox(-2.0F, 11.0F, -2.0F, 4, 2, 5);
/* 198 */     this.LegRR3.setRotationPoint(-3.5F, 11.0F, 9.0F);
/*     */ 
/*     */ 
/*     */     
/* 202 */     this.BHead = new ModelRenderer(this, 19, 0);
/* 203 */     this.BHead.addBox(-4.0F, 0.0F, -4.0F, 8, 8, 5);
/* 204 */     this.BHead.setRotationPoint(0.0F, -12.0F, 5.0F);
/* 205 */     setRotation(this.BHead, -0.0242694F, 0.0F, 0.0F);
/*     */     
/* 207 */     this.BSnout = new ModelRenderer(this, 23, 13);
/* 208 */     this.BSnout.addBox(-2.0F, 2.5F, -8.5F, 4, 3, 5);
/* 209 */     this.BSnout.setRotationPoint(0.0F, -12.0F, 5.0F);
/* 210 */     setRotation(this.BSnout, -0.0242694F, 0.0F, 0.0F);
/*     */     
/* 212 */     this.BMouth = new ModelRenderer(this, 24, 21);
/* 213 */     this.BMouth.addBox(-1.5F, 5.5F, -8.0F, 3, 2, 5);
/* 214 */     this.BMouth.setRotationPoint(0.0F, -12.0F, 5.0F);
/* 215 */     setRotation(this.BMouth, -0.08726F, 0.0F, 0.0F);
/*     */     
/* 217 */     this.BMouthOpen = new ModelRenderer(this, 24, 21);
/* 218 */     this.BMouthOpen.addBox(-1.5F, 3.5F, -11.0F, 3, 2, 5);
/* 219 */     this.BMouthOpen.setRotationPoint(0.0F, -12.0F, 5.0F);
/* 220 */     setRotation(this.BMouthOpen, 0.5235988F, 0.0F, 0.0F);
/*     */     
/* 222 */     this.BNeck = new ModelRenderer(this, 18, 28);
/* 223 */     this.BNeck.addBox(-3.5F, 0.0F, -7.0F, 7, 6, 7);
/* 224 */     this.BNeck.setRotationPoint(0.0F, -3.0F, 11.0F);
/* 225 */     setRotation(this.BNeck, -1.336881F, 0.0F, 0.0F);
/*     */     
/* 227 */     this.BLEar = new ModelRenderer(this, 40, 0);
/* 228 */     this.BLEar.addBox(2.0F, -2.0F, -2.0F, 3, 3, 1);
/* 229 */     this.BLEar.setRotationPoint(0.0F, -12.0F, 5.0F);
/* 230 */     setRotation(this.BLEar, -0.0242694F, -0.3490659F, 0.1396263F);
/*     */     
/* 232 */     this.BREar = new ModelRenderer(this, 16, 0);
/* 233 */     this.BREar.addBox(-5.0F, -2.0F, -2.0F, 3, 3, 1);
/* 234 */     this.BREar.setRotationPoint(0.0F, -12.0F, 5.0F);
/* 235 */     setRotation(this.BREar, -0.0242694F, 0.3490659F, -0.1396263F);
/*     */     
/* 237 */     this.BTorso = new ModelRenderer(this, 12, 42);
/* 238 */     this.BTorso.addBox(-5.0F, 0.0F, 0.0F, 10, 10, 10);
/* 239 */     this.BTorso.setRotationPoint(0.0F, -3.5F, 12.3F);
/* 240 */     setRotation(this.BTorso, -1.396263F, 0.0F, 0.0F);
/*     */     
/* 242 */     this.BAbdomen = new ModelRenderer(this, 13, 62);
/* 243 */     this.BAbdomen.addBox(-4.5F, 0.0F, 0.0F, 9, 11, 10);
/* 244 */     this.BAbdomen.setRotationPoint(0.0F, 6.0F, 14.0F);
/* 245 */     setRotation(this.BAbdomen, -1.570796F, 0.0F, 0.0F);
/*     */     
/* 247 */     this.BTail = new ModelRenderer(this, 26, 83);
/* 248 */     this.BTail.addBox(-1.5F, 0.0F, 0.0F, 3, 3, 3);
/* 249 */     this.BTail.setRotationPoint(0.0F, 12.46667F, 12.6F);
/* 250 */     setRotation(this.BTail, 0.3619751F, 0.0F, 0.0F);
/*     */     
/* 252 */     this.BLegFL1 = new ModelRenderer(this, 40, 22);
/* 253 */     this.BLegFL1.addBox(-2.5F, 0.0F, -2.5F, 5, 8, 5);
/* 254 */     this.BLegFL1.setRotationPoint(5.0F, -1.0F, 6.0F);
/* 255 */     setRotation(this.BLegFL1, 0.2617994F, 0.0F, -0.2617994F);
/*     */     
/* 257 */     this.BLegFL2 = new ModelRenderer(this, 46, 35);
/* 258 */     this.BLegFL2.addBox(0.0F, 5.0F, 3.0F, 4, 6, 4);
/* 259 */     this.BLegFL2.setRotationPoint(5.0F, -1.0F, 6.0F);
/* 260 */     setRotation(this.BLegFL2, -0.5576792F, 0.0F, 0.0F);
/*     */     
/* 262 */     this.BLegFL3 = new ModelRenderer(this, 46, 45);
/* 263 */     this.BLegFL3.addBox(0.1F, -7.0F, -14.0F, 4, 2, 5);
/* 264 */     this.BLegFL3.setRotationPoint(5.0F, -1.0F, 6.0F);
/* 265 */     setRotation(this.BLegFL3, 2.007645F, 0.0F, 0.0F);
/*     */     
/* 267 */     this.BLegFR1 = new ModelRenderer(this, 4, 22);
/* 268 */     this.BLegFR1.addBox(-2.5F, 0.0F, -2.5F, 5, 8, 5);
/* 269 */     this.BLegFR1.setRotationPoint(-5.0F, -1.0F, 6.0F);
/* 270 */     setRotation(this.BLegFR1, 0.2617994F, 0.0F, 0.2617994F);
/*     */     
/* 272 */     this.BLegFR2 = new ModelRenderer(this, 2, 35);
/* 273 */     this.BLegFR2.addBox(-4.0F, 5.0F, 3.0F, 4, 6, 4);
/* 274 */     this.BLegFR2.setRotationPoint(-5.0F, -1.0F, 6.0F);
/* 275 */     setRotation(this.BLegFR2, -0.5576792F, 0.0F, 0.0F);
/*     */     
/* 277 */     this.BLegFR3 = new ModelRenderer(this, 0, 45);
/* 278 */     this.BLegFR3.addBox(-4.1F, -7.0F, -14.0F, 4, 2, 5);
/* 279 */     this.BLegFR3.setRotationPoint(-5.0F, -1.0F, 6.0F);
/* 280 */     setRotation(this.BLegFR3, 2.007129F, 0.0F, 0.0F);
/*     */     
/* 282 */     this.BLegRL1 = new ModelRenderer(this, 34, 83);
/* 283 */     this.BLegRL1.addBox(-1.5F, 0.0F, -2.5F, 4, 8, 6);
/* 284 */     this.BLegRL1.setRotationPoint(3.0F, 11.0F, 9.0F);
/* 285 */     setRotation(this.BLegRL1, -0.5235988F, -0.2617994F, 0.0F);
/*     */     
/* 287 */     this.BLegRL2 = new ModelRenderer(this, 41, 97);
/* 288 */     this.BLegRL2.addBox(-1.3F, 6.0F, -3.0F, 4, 6, 4);
/* 289 */     this.BLegRL2.setRotationPoint(3.0F, 11.0F, 9.0F);
/* 290 */     setRotation(this.BLegRL2, 0.0F, -0.2617994F, 0.0F);
/*     */     
/* 292 */     this.BLegRL3 = new ModelRenderer(this, 44, 107);
/* 293 */     this.BLegRL3.addBox(-1.2F, 11.0F, -4.0F, 4, 2, 5);
/* 294 */     this.BLegRL3.setRotationPoint(3.0F, 11.0F, 9.0F);
/* 295 */     setRotation(this.BLegRL3, 0.0F, -0.2617994F, 0.0F);
/*     */     
/* 297 */     this.BLegRR1 = new ModelRenderer(this, 10, 83);
/* 298 */     this.BLegRR1.addBox(-2.5F, 0.0F, -2.5F, 4, 8, 6);
/* 299 */     this.BLegRR1.setRotationPoint(-3.0F, 11.0F, 9.0F);
/* 300 */     setRotation(this.BLegRR1, -0.1745329F, 0.2617994F, 0.0F);
/*     */     
/* 302 */     this.BLegRR2 = new ModelRenderer(this, 7, 97);
/* 303 */     this.BLegRR2.addBox(-2.4F, 6.0F, -1.0F, 4, 6, 4);
/* 304 */     this.BLegRR2.setRotationPoint(-3.0F, 11.0F, 9.0F);
/* 305 */     setRotation(this.BLegRR2, 0.0F, 0.2617994F, 0.0F);
/*     */     
/* 307 */     this.BLegRR3 = new ModelRenderer(this, 2, 107);
/* 308 */     this.BLegRR3.addBox(-2.5F, 11.0F, -2.0F, 4, 2, 5);
/* 309 */     this.BLegRR3.setRotationPoint(-3.0F, 11.0F, 9.0F);
/* 310 */     setRotation(this.BLegRR3, 0.0F, 0.2617994F, 0.0F);
/*     */ 
/*     */     
/* 313 */     this.CHead = new ModelRenderer(this, 19, 0);
/* 314 */     this.CHead.addBox(-4.0F, 0.0F, -4.0F, 8, 8, 5);
/* 315 */     this.CHead.setRotationPoint(0.0F, 3.0F, -3.5F);
/* 316 */     setRotation(this.CHead, 0.1502636F, 0.0F, 0.0F);
/*     */     
/* 318 */     this.CSnout = new ModelRenderer(this, 23, 13);
/* 319 */     this.CSnout.addBox(-2.0F, 3.0F, -8.5F, 4, 3, 5);
/* 320 */     this.CSnout.setRotationPoint(0.0F, 3.0F, -3.5F);
/* 321 */     setRotation(this.CSnout, 0.1502636F, 0.0F, 0.0F);
/*     */     
/* 323 */     this.CMouth = new ModelRenderer(this, 24, 21);
/* 324 */     this.CMouth.addBox(-1.5F, 6.0F, -7.0F, 3, 2, 5);
/* 325 */     this.CMouth.setRotationPoint(0.0F, 3.0F, -3.5F);
/* 326 */     setRotation(this.CMouth, -0.0068161F, 0.0F, 0.0F);
/*     */     
/* 328 */     this.CMouthOpen = new ModelRenderer(this, 24, 21);
/* 329 */     this.CMouthOpen.addBox(-1.5F, 5.5F, -9.0F, 3, 2, 5);
/* 330 */     this.CMouthOpen.setRotationPoint(0.0F, 3.0F, -3.5F);
/* 331 */     setRotation(this.CMouthOpen, 0.3665191F, 0.0F, 0.0F);
/*     */     
/* 333 */     this.CLEar = new ModelRenderer(this, 40, 0);
/* 334 */     this.CLEar.addBox(2.0F, -2.0F, -2.0F, 3, 3, 1);
/* 335 */     this.CLEar.setRotationPoint(0.0F, 3.0F, -3.5F);
/* 336 */     setRotation(this.CLEar, 0.1502636F, -0.3490659F, 0.1396263F);
/*     */     
/* 338 */     this.CREar = new ModelRenderer(this, 16, 0);
/* 339 */     this.CREar.addBox(-5.0F, -2.0F, -2.0F, 3, 3, 1);
/* 340 */     this.CREar.setRotationPoint(0.0F, 3.0F, -3.5F);
/* 341 */     setRotation(this.CREar, 0.1502636F, 0.3490659F, -0.1396263F);
/*     */     
/* 343 */     this.CNeck = new ModelRenderer(this, 18, 28);
/* 344 */     this.CNeck.addBox(-3.5F, 0.0F, -7.0F, 7, 7, 7);
/* 345 */     this.CNeck.setRotationPoint(0.0F, 5.8F, 3.4F);
/* 346 */     setRotation(this.CNeck, -0.3316126F, 0.0F, 0.0F);
/*     */     
/* 348 */     this.CTorso = new ModelRenderer(this, 12, 42);
/* 349 */     this.CTorso.addBox(-5.0F, 0.0F, 0.0F, 10, 10, 10);
/* 350 */     this.CTorso.setRotationPoint(0.0F, 5.8F, 3.4F);
/* 351 */     setRotation(this.CTorso, -0.9712912F, 0.0F, 0.0F);
/*     */     
/* 353 */     this.CAbdomen = new ModelRenderer(this, 13, 62);
/* 354 */     this.CAbdomen.addBox(-4.5F, 0.0F, 0.0F, 9, 11, 10);
/* 355 */     this.CAbdomen.setRotationPoint(0.0F, 14.0F, 9.0F);
/* 356 */     setRotation(this.CAbdomen, -1.570796F, 0.0F, 0.0F);
/*     */     
/* 358 */     this.CTail = new ModelRenderer(this, 26, 83);
/* 359 */     this.CTail.addBox(-1.5F, 0.0F, 0.0F, 3, 3, 3);
/* 360 */     this.CTail.setRotationPoint(0.0F, 21.46667F, 8.0F);
/* 361 */     setRotation(this.CTail, 0.4363323F, 0.0F, 0.0F);
/*     */     
/* 363 */     this.CLegFL1 = new ModelRenderer(this, 40, 22);
/* 364 */     this.CLegFL1.addBox(-2.5F, 0.0F, -1.5F, 5, 8, 5);
/* 365 */     this.CLegFL1.setRotationPoint(4.0F, 10.0F, 0.0F);
/* 366 */     setRotation(this.CLegFL1, -0.2617994F, 0.0F, 0.0F);
/*     */     
/* 368 */     this.CLegFL2 = new ModelRenderer(this, 46, 35);
/* 369 */     this.CLegFL2.addBox(-2.0F, 0.0F, -1.2F, 4, 6, 4);
/* 370 */     this.CLegFL2.setRotationPoint(4.0F, 17.0F, -2.0F);
/* 371 */     setRotation(this.CLegFL2, -0.3490659F, 0.0F, 0.2617994F);
/*     */     
/* 373 */     this.CLegFL3 = new ModelRenderer(this, 46, 45);
/* 374 */     this.CLegFL3.addBox(-2.0F, 0.0F, -3.0F, 4, 2, 5);
/* 375 */     this.CLegFL3.setRotationPoint(2.5F, 22.0F, -4.0F);
/* 376 */     setRotation(this.CLegFL3, 0.0F, 0.1745329F, 0.0F);
/*     */     
/* 378 */     this.CLegFR1 = new ModelRenderer(this, 4, 22);
/* 379 */     this.CLegFR1.addBox(-2.5F, 0.0F, -1.5F, 5, 8, 5);
/* 380 */     this.CLegFR1.setRotationPoint(-4.0F, 10.0F, 0.0F);
/* 381 */     setRotation(this.CLegFR1, -0.2617994F, 0.0F, 0.0F);
/*     */     
/* 383 */     this.CLegFR2 = new ModelRenderer(this, 2, 35);
/* 384 */     this.CLegFR2.addBox(-2.0F, 0.0F, -1.2F, 4, 6, 4);
/* 385 */     this.CLegFR2.setRotationPoint(-4.0F, 17.0F, -2.0F);
/* 386 */     setRotation(this.CLegFR2, -0.3490659F, 0.0F, -0.2617994F);
/*     */     
/* 388 */     this.CLegFR3 = new ModelRenderer(this, 0, 45);
/* 389 */     this.CLegFR3.addBox(-2.0F, 0.0F, -3.0F, 4, 2, 5);
/* 390 */     this.CLegFR3.setRotationPoint(-2.5F, 22.0F, -4.0F);
/* 391 */     setRotation(this.CLegFR3, 0.0F, -0.1745329F, 0.0F);
/*     */     
/* 393 */     this.CLegRL1 = new ModelRenderer(this, 34, 83);
/* 394 */     this.CLegRL1.addBox(-1.5F, 0.0F, -2.5F, 4, 8, 6);
/* 395 */     this.CLegRL1.setRotationPoint(3.0F, 21.0F, 5.0F);
/* 396 */     setRotation(this.CLegRL1, -1.396263F, -0.3490659F, 0.3490659F);
/*     */     
/* 398 */     this.CLegRL2 = new ModelRenderer(this, 41, 97);
/* 399 */     this.CLegRL2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
/* 400 */     this.CLegRL2.setRotationPoint(5.2F, 22.5F, -1.0F);
/* 401 */     setRotation(this.CLegRL2, -1.570796F, 0.0F, 0.3490659F);
/*     */     
/* 403 */     this.CLegRL3 = new ModelRenderer(this, 44, 107);
/* 404 */     this.CLegRL3.addBox(-2.0F, 0.0F, -3.0F, 4, 2, 5);
/* 405 */     this.CLegRL3.setRotationPoint(5.5F, 22.0F, -6.0F);
/* 406 */     setRotation(this.CLegRL3, -1.375609F, 0.0F, 0.3490659F);
/*     */     
/* 408 */     this.CLegRR1 = new ModelRenderer(this, 10, 83);
/* 409 */     this.CLegRR1.addBox(-2.5F, 0.0F, -2.5F, 4, 8, 6);
/* 410 */     this.CLegRR1.setRotationPoint(-3.0F, 21.0F, 5.0F);
/* 411 */     setRotation(this.CLegRR1, -1.396263F, 0.3490659F, -0.3490659F);
/*     */     
/* 413 */     this.CLegRR2 = new ModelRenderer(this, 7, 97);
/* 414 */     this.CLegRR2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
/* 415 */     this.CLegRR2.setRotationPoint(-5.2F, 22.5F, -1.0F);
/* 416 */     setRotation(this.CLegRR2, -1.570796F, 0.0F, -0.3490659F);
/*     */     
/* 418 */     this.CLegRR3 = new ModelRenderer(this, 2, 107);
/* 419 */     this.CLegRR3.addBox(-2.0F, 0.0F, -3.0F, 4, 2, 5);
/* 420 */     this.CLegRR3.setRotationPoint(-5.5F, 22.0F, -6.0F);
/* 421 */     setRotation(this.CLegRR3, -1.375609F, 0.0F, -0.3490659F);
/*     */     
/* 423 */     this.Saddle = new ModelRenderer(this, 36, 114);
/* 424 */     this.Saddle.addBox(-4.0F, -0.5F, -3.0F, 8, 2, 6, 0.0F);
/* 425 */     this.Saddle.setRotationPoint(0.0F, 4.0F, -2.0F);
/*     */     
/* 427 */     this.SaddleBack = new ModelRenderer(this, 20, 108);
/* 428 */     this.SaddleBack.addBox(-4.0F, -0.2F, 2.9F, 8, 2, 4, 0.0F);
/* 429 */     this.SaddleBack.setRotationPoint(0.0F, 4.0F, -2.0F);
/* 430 */     this.SaddleBack.rotateAngleX = 0.10088F;
/*     */     
/* 432 */     this.SaddleFront = new ModelRenderer(this, 36, 122);
/* 433 */     this.SaddleFront.addBox(-2.5F, -1.0F, -3.0F, 5, 2, 3, 0.0F);
/* 434 */     this.SaddleFront.setRotationPoint(0.0F, 4.0F, -2.0F);
/* 435 */     this.SaddleFront.rotateAngleX = -0.1850049F;
/*     */     
/* 437 */     this.Bag = new ModelRenderer(this, 0, 114);
/* 438 */     this.Bag.addBox(-5.0F, -3.0F, -2.5F, 10, 2, 5, 0.0F);
/* 439 */     this.Bag.setRotationPoint(0.0F, 7.0F, 7.0F);
/* 440 */     this.Bag.rotateAngleX = -0.4363323F;
/*     */     
/* 442 */     this.BagSitted = new ModelRenderer(this, 0, 114);
/* 443 */     this.BagSitted.addBox(-5.0F, -3.0F, -2.5F, 10, 2, 5, 0.0F);
/* 444 */     this.BagSitted.setRotationPoint(0.0F, 17.0F, 8.0F);
/* 445 */     this.BagSitted.rotateAngleX = -1.570796F;
/*     */     
/* 447 */     this.SaddleSitted = new ModelRenderer(this, 36, 114);
/* 448 */     this.SaddleSitted.addBox(-4.0F, -0.5F, -3.0F, 8, 2, 6, 0.0F);
/* 449 */     this.SaddleSitted.setRotationPoint(0.0F, 7.5F, 6.5F);
/* 450 */     this.SaddleSitted.rotateAngleX = -0.9686577F;
/*     */     
/* 452 */     this.SaddleBackSitted = new ModelRenderer(this, 20, 108);
/* 453 */     this.SaddleBackSitted.addBox(-4.0F, -0.3F, 2.9F, 8, 2, 4, 0.0F);
/* 454 */     this.SaddleBackSitted.setRotationPoint(0.0F, 7.5F, 6.5F);
/* 455 */     this.SaddleBackSitted.rotateAngleX = -0.9162979F;
/*     */     
/* 457 */     this.SaddleFrontSitted = new ModelRenderer(this, 36, 122);
/* 458 */     this.SaddleFrontSitted.addBox(-2.5F, -1.0F, -3.0F, 5, 2, 3, 0.0F);
/* 459 */     this.SaddleFrontSitted.setRotationPoint(0.0F, 7.5F, 6.5F);
/* 460 */     this.SaddleFrontSitted.rotateAngleX = -1.151917F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 465 */     MoCEntityBear entitybear = (MoCEntityBear)entity;
/* 466 */     this.bearstate = entitybear.getBearState();
/* 467 */     boolean openMouth = (entitybear.mouthCounter != 0);
/* 468 */     this.attackSwing = entitybear.getAttackSwing();
/* 469 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/* 470 */     boolean chested = entitybear.getIsChested();
/* 471 */     boolean saddled = entitybear.getIsRideable();
/*     */     
/* 473 */     if (this.bearstate == 0) {
/* 474 */       if (openMouth) {
/* 475 */         this.MouthOpen.render(f5);
/*     */       } else {
/* 477 */         this.Mouth.render(f5);
/*     */       } 
/* 479 */       if (saddled) {
/*     */         
/* 481 */         this.Saddle.render(f5);
/* 482 */         this.SaddleBack.render(f5);
/* 483 */         this.SaddleFront.render(f5);
/*     */       } 
/* 485 */       if (chested)
/*     */       {
/* 487 */         this.Bag.render(f5);
/*     */       }
/* 489 */       this.LegFR1.render(f5);
/* 490 */       this.Neck.render(f5);
/* 491 */       this.LEar.render(f5);
/* 492 */       this.Snout.render(f5);
/* 493 */       this.Head.render(f5);
/* 494 */       this.REar.render(f5);
/* 495 */       this.Abdomen.render(f5);
/* 496 */       this.Torso.render(f5);
/* 497 */       this.LegRR3.render(f5);
/* 498 */       this.LegRR1.render(f5);
/* 499 */       this.LegRR2.render(f5);
/* 500 */       this.LegFR2.render(f5);
/* 501 */       this.LegFR3.render(f5);
/* 502 */       this.LegFL1.render(f5);
/* 503 */       this.LegFL3.render(f5);
/* 504 */       this.LegFL2.render(f5);
/* 505 */       this.LegRL1.render(f5);
/* 506 */       this.LegRL2.render(f5);
/* 507 */       this.LegRL3.render(f5);
/* 508 */       this.Tail.render(f5);
/* 509 */     } else if (this.bearstate == 1) {
/* 510 */       this.BHead.render(f5);
/* 511 */       this.BSnout.render(f5);
/* 512 */       if (openMouth) {
/* 513 */         this.BMouthOpen.render(f5);
/*     */       } else {
/* 515 */         this.BMouth.render(f5);
/*     */       } 
/*     */       
/* 518 */       this.BNeck.render(f5);
/* 519 */       this.BLEar.render(f5);
/* 520 */       this.BREar.render(f5);
/* 521 */       this.BTorso.render(f5);
/* 522 */       this.BAbdomen.render(f5);
/* 523 */       this.BTail.render(f5);
/* 524 */       this.BLegFL1.render(f5);
/* 525 */       this.BLegFL2.render(f5);
/* 526 */       this.BLegFL3.render(f5);
/* 527 */       this.BLegFR1.render(f5);
/* 528 */       this.BLegFR2.render(f5);
/* 529 */       this.BLegFR3.render(f5);
/* 530 */       this.BLegRL1.render(f5);
/* 531 */       this.BLegRL2.render(f5);
/* 532 */       this.BLegRL3.render(f5);
/* 533 */       this.BLegRR1.render(f5);
/* 534 */       this.BLegRR2.render(f5);
/* 535 */       this.BLegRR3.render(f5);
/* 536 */     } else if (this.bearstate == 2) {
/* 537 */       if (openMouth) {
/* 538 */         this.CMouthOpen.render(f5);
/*     */       } else {
/* 540 */         this.CMouth.render(f5);
/*     */       } 
/* 542 */       if (saddled) {
/*     */         
/* 544 */         this.SaddleSitted.render(f5);
/* 545 */         this.SaddleBackSitted.render(f5);
/* 546 */         this.SaddleFrontSitted.render(f5);
/*     */       } 
/* 548 */       if (chested)
/*     */       {
/* 550 */         this.BagSitted.render(f5);
/*     */       }
/* 552 */       this.CHead.render(f5);
/* 553 */       this.CSnout.render(f5);
/* 554 */       this.CLEar.render(f5);
/* 555 */       this.CREar.render(f5);
/* 556 */       this.CNeck.render(f5);
/* 557 */       this.CTorso.render(f5);
/* 558 */       this.CAbdomen.render(f5);
/* 559 */       this.CTail.render(f5);
/* 560 */       this.CLegFL1.render(f5);
/* 561 */       this.CLegFL2.render(f5);
/* 562 */       this.CLegFL3.render(f5);
/* 563 */       this.CLegFR1.render(f5);
/* 564 */       this.CLegFR2.render(f5);
/* 565 */       this.CLegFR3.render(f5);
/* 566 */       this.CLegRL1.render(f5);
/* 567 */       this.CLegRL2.render(f5);
/* 568 */       this.CLegRL3.render(f5);
/* 569 */       this.CLegRR1.render(f5);
/* 570 */       this.CLegRR2.render(f5);
/* 571 */       this.CLegRR3.render(f5);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 577 */     model.rotateAngleX = x;
/* 578 */     model.rotateAngleY = y;
/* 579 */     model.rotateAngleZ = z;
/*     */   }
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 583 */     float LLegRotX = MathHelper.cos(f * 0.6662F) * 0.8F * f1;
/* 584 */     float RLegRotX = MathHelper.cos(f * 0.6662F + 3.141593F) * 0.8F * f1;
/* 585 */     float XAngle = f4 / 57.29578F;
/* 586 */     float YAngle = f3 / 57.29578F;
/*     */     
/* 588 */     if (this.bearstate == 0) {
/* 589 */       this.Head.rotateAngleX = 0.1502636F + XAngle;
/* 590 */       this.Head.rotateAngleY = YAngle;
/*     */       
/* 592 */       this.Snout.rotateAngleX = 0.1502636F + XAngle;
/* 593 */       this.Snout.rotateAngleY = YAngle;
/*     */       
/* 595 */       this.Mouth.rotateAngleX = -0.0068161F + XAngle;
/* 596 */       this.Mouth.rotateAngleY = YAngle;
/*     */       
/* 598 */       this.MouthOpen.rotateAngleX = 0.534236F + XAngle;
/* 599 */       this.MouthOpen.rotateAngleY = YAngle;
/*     */       
/* 601 */       this.LEar.rotateAngleX = 0.1502636F + XAngle;
/* 602 */       this.LEar.rotateAngleY = -0.3490659F + YAngle;
/*     */       
/* 604 */       this.REar.rotateAngleX = 0.1502636F + XAngle;
/* 605 */       this.REar.rotateAngleY = 0.3490659F + YAngle;
/*     */       
/* 607 */       this.LegFL1.rotateAngleX = 0.2617994F + LLegRotX;
/* 608 */       this.LegFL2.rotateAngleX = LLegRotX;
/* 609 */       this.LegFL3.rotateAngleX = LLegRotX;
/*     */       
/* 611 */       this.LegRR1.rotateAngleX = -0.1745329F + LLegRotX;
/* 612 */       this.LegRR2.rotateAngleX = LLegRotX;
/* 613 */       this.LegRR3.rotateAngleX = LLegRotX;
/*     */       
/* 615 */       this.LegFR1.rotateAngleX = 0.2617994F + RLegRotX;
/* 616 */       this.LegFR2.rotateAngleX = RLegRotX;
/* 617 */       this.LegFR3.rotateAngleX = RLegRotX;
/*     */       
/* 619 */       this.LegRL1.rotateAngleX = -0.1745329F + RLegRotX;
/* 620 */       this.LegRL2.rotateAngleX = RLegRotX;
/* 621 */       this.LegRL3.rotateAngleX = RLegRotX;
/* 622 */     } else if (this.bearstate == 1) {
/*     */       
/* 624 */       this.BHead.rotateAngleX = -0.0242694F - XAngle;
/* 625 */       this.BHead.rotateAngleY = YAngle;
/*     */       
/* 627 */       this.BSnout.rotateAngleX = -0.0242694F - XAngle;
/* 628 */       this.BSnout.rotateAngleY = YAngle;
/*     */       
/* 630 */       this.BMouth.rotateAngleX = -0.08726F - XAngle;
/* 631 */       this.BMouth.rotateAngleY = YAngle;
/*     */       
/* 633 */       this.BMouthOpen.rotateAngleX = 0.5235988F - XAngle;
/* 634 */       this.BMouthOpen.rotateAngleY = YAngle;
/*     */       
/* 636 */       this.BLEar.rotateAngleX = -0.0242694F - XAngle;
/* 637 */       this.BLEar.rotateAngleY = -0.3490659F + YAngle;
/*     */       
/* 639 */       this.BREar.rotateAngleX = -0.0242694F - XAngle;
/* 640 */       this.BREar.rotateAngleY = 0.3490659F + YAngle;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 645 */       float breathing = MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
/* 646 */       this.BLegFR1.rotateAngleZ = 0.2617994F + breathing;
/* 647 */       this.BLegFR2.rotateAngleZ = breathing;
/* 648 */       this.BLegFR3.rotateAngleZ = breathing;
/* 649 */       this.BLegFL1.rotateAngleZ = -0.2617994F - breathing;
/* 650 */       this.BLegFL2.rotateAngleZ = -breathing;
/* 651 */       this.BLegFL3.rotateAngleZ = -breathing;
/*     */       
/* 653 */       this.BLegFL1.rotateAngleX = 0.2617994F + this.attackSwing;
/* 654 */       this.BLegFL2.rotateAngleX = -0.5576792F + this.attackSwing;
/* 655 */       this.BLegFL3.rotateAngleX = 2.007645F + this.attackSwing;
/*     */       
/* 657 */       this.BLegFR1.rotateAngleX = 0.2617994F + this.attackSwing;
/* 658 */       this.BLegFR2.rotateAngleX = -0.5576792F + this.attackSwing;
/* 659 */       this.BLegFR3.rotateAngleX = 2.007645F + this.attackSwing;
/*     */       
/* 661 */       this.BLegRR1.rotateAngleX = -0.1745329F + LLegRotX;
/* 662 */       this.BLegRR2.rotateAngleX = LLegRotX;
/* 663 */       this.BLegRR3.rotateAngleX = LLegRotX;
/*     */       
/* 665 */       this.BLegRL1.rotateAngleX = -0.5235988F + RLegRotX;
/* 666 */       this.BLegRL2.rotateAngleX = RLegRotX;
/* 667 */       this.BLegRL3.rotateAngleX = RLegRotX;
/* 668 */     } else if (this.bearstate == 2) {
/* 669 */       this.CHead.rotateAngleX = 0.1502636F + XAngle;
/* 670 */       this.CHead.rotateAngleY = YAngle;
/*     */       
/* 672 */       this.CSnout.rotateAngleX = 0.1502636F + XAngle;
/* 673 */       this.CSnout.rotateAngleY = YAngle;
/*     */       
/* 675 */       this.CMouth.rotateAngleX = -0.0068161F + XAngle;
/* 676 */       this.CMouth.rotateAngleY = YAngle;
/*     */       
/* 678 */       this.CMouthOpen.rotateAngleX = 0.3665191F + XAngle;
/* 679 */       this.CMouthOpen.rotateAngleY = YAngle;
/*     */       
/* 681 */       this.CLEar.rotateAngleX = 0.1502636F + XAngle;
/* 682 */       this.CLEar.rotateAngleY = -0.3490659F + YAngle;
/*     */       
/* 684 */       this.CREar.rotateAngleX = 0.1502636F + XAngle;
/* 685 */       this.CREar.rotateAngleY = 0.3490659F + YAngle;
/*     */     } 
/*     */     
/* 688 */     this.Tail.rotateAngleZ = LLegRotX * 0.2F;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelBear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */