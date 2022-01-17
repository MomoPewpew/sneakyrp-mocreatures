/*     */ package drzhark.mocreatures.client.model;
/*     */ 
/*     */ import drzhark.mocreatures.entity.monster.MoCEntityGolem;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MoCModelGolem
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer[][] blocks;
/*     */   ModelRenderer head;
/*     */   ModelRenderer headb;
/*     */   ModelRenderer chest;
/*     */   ModelRenderer chestb;
/*     */   byte[] blocksText;
/*  22 */   float radianF = 57.29578F;
/*  23 */   int w = 32;
/*  24 */   int h = 16;
/*     */   
/*     */   public MoCModelGolem() {
/*  27 */     this.blocks = new ModelRenderer[23][28];
/*  28 */     this.blocksText = new byte[23];
/*  29 */     this.textureWidth = 128;
/*  30 */     this.textureHeight = 128;
/*     */     byte i;
/*  32 */     for (i = 0; i < 23; i = (byte)(i + 1)) {
/*  33 */       this.blocksText[i] = 30;
/*     */     }
/*     */ 
/*     */     
/*  37 */     this.head = new ModelRenderer(this, 96, 64);
/*  38 */     this.head.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8);
/*  39 */     this.head.setRotationPoint(0.0F, -10.0F, 0.0F);
/*  40 */     setRotation(this.head, 0.0F, 0.7853982F, 0.0F);
/*     */     
/*  42 */     this.headb = new ModelRenderer(this, 96, 80);
/*  43 */     this.headb.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8);
/*  44 */     this.headb.setRotationPoint(0.0F, -10.0F, 0.0F);
/*  45 */     setRotation(this.headb, 0.0F, 0.7853982F, 0.0F);
/*     */     
/*  47 */     this.chest = new ModelRenderer(this, 96, 96);
/*  48 */     this.chest.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8);
/*  49 */     this.chest.setRotationPoint(0.0F, -3.0F, -7.0F);
/*  50 */     setRotation(this.chest, 0.0F, 0.7853982F, 0.0F);
/*     */     
/*  52 */     this.chestb = new ModelRenderer(this, 96, 112);
/*  53 */     this.chestb.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8);
/*  54 */     this.chestb.setRotationPoint(0.0F, -3.0F, -7.0F);
/*  55 */     setRotation(this.chestb, 0.0F, 0.7853982F, 0.0F);
/*     */     
/*  57 */     for (i = 0; i < 28; i = (byte)(i + 1)) {
/*  58 */       int textX = i / 8 * this.w;
/*  59 */       int textY = i % 8 * this.h;
/*     */ 
/*     */       
/*  62 */       this.blocks[0][i] = new ModelRenderer(this, textX, textY);
/*  63 */       this.blocks[0][i].addBox(-4.0F, 3.0F, -4.0F, 8, 8, 8);
/*  64 */       this.blocks[0][i].setRotationPoint(0.0F, -3.0F, 0.0F);
/*  65 */       setRotationG(this.blocks[0][i], -97.0F, -40.0F, 0.0F);
/*     */ 
/*     */       
/*  68 */       this.blocks[1][i] = new ModelRenderer(this, textX, textY);
/*  69 */       this.blocks[1][i].addBox(-4.0F, 3.0F, -4.0F, 8, 8, 8);
/*  70 */       this.blocks[1][i].setRotationPoint(0.0F, -3.0F, 0.0F);
/*  71 */       setRotationG(this.blocks[1][i], -55.0F, -41.0F, 0.0F);
/*     */ 
/*     */       
/*  74 */       this.blocks[2][i] = new ModelRenderer(this, textX, textY);
/*  75 */       this.blocks[2][i].addBox(-4.0F, 3.0F, -4.0F, 8, 8, 8);
/*  76 */       this.blocks[2][i].setRotationPoint(0.0F, -3.0F, 0.0F);
/*  77 */       setRotationG(this.blocks[2][i], -97.0F, 40.0F, 0.0F);
/*     */ 
/*     */       
/*  80 */       this.blocks[3][i] = new ModelRenderer(this, textX, textY);
/*  81 */       this.blocks[3][i].addBox(-4.0F, 3.0F, -4.0F, 8, 8, 8);
/*  82 */       this.blocks[3][i].setRotationPoint(0.0F, -3.0F, 0.0F);
/*  83 */       setRotationG(this.blocks[3][i], -55.0F, 41.0F, 0.0F);
/*     */ 
/*     */       
/*  86 */       this.blocks[4][i] = new ModelRenderer(this, textX, textY);
/*  87 */       this.blocks[4][i].addBox(-7.0F, -14.0F, -1.0F, 8, 8, 8);
/*  88 */       this.blocks[4][i].setRotationPoint(0.0F, 6.0F, 3.0F);
/*  89 */       setRotation(this.blocks[4][i], 0.0F, 0.7853982F, 0.0F);
/*     */ 
/*     */       
/*  92 */       this.blocks[5][i] = new ModelRenderer(this, textX, textY);
/*  93 */       this.blocks[5][i].addBox(-4.0F, 3.0F, -4.0F, 8, 8, 8);
/*  94 */       this.blocks[5][i].setRotationPoint(0.0F, -3.0F, 0.0F);
/*  95 */       setRotation(this.blocks[5][i], 1.919862F, 0.6981317F, 0.0F);
/*     */ 
/*     */       
/*  98 */       this.blocks[6][i] = new ModelRenderer(this, textX, textY);
/*  99 */       this.blocks[6][i].addBox(-4.0F, 3.0F, -4.0F, 8, 8, 8);
/* 100 */       this.blocks[6][i].setRotationPoint(0.0F, -3.0F, 0.0F);
/* 101 */       setRotation(this.blocks[6][i], 1.183003F, 0.6981317F, 0.0F);
/*     */ 
/*     */       
/* 104 */       this.blocks[7][i] = new ModelRenderer(this, textX, textY);
/* 105 */       this.blocks[7][i].addBox(-4.0F, 3.0F, -4.0F, 8, 8, 8);
/* 106 */       this.blocks[7][i].setRotationPoint(0.0F, -3.0F, 0.0F);
/* 107 */       setRotation(this.blocks[7][i], 1.919862F, -0.6981317F, 0.0F);
/*     */ 
/*     */       
/* 110 */       this.blocks[8][i] = new ModelRenderer(this, textX, textY);
/* 111 */       this.blocks[8][i].addBox(-4.0F, 3.0F, -4.0F, 8, 8, 8);
/* 112 */       this.blocks[8][i].setRotationPoint(0.0F, -3.0F, 0.0F);
/* 113 */       setRotation(this.blocks[8][i], 1.183003F, -0.6981317F, 0.0F);
/*     */ 
/*     */       
/* 116 */       this.blocks[9][i] = new ModelRenderer(this, textX, textY);
/* 117 */       this.blocks[9][i].addBox(0.0F, -2.0F, -4.0F, 8, 8, 8);
/* 118 */       this.blocks[9][i].setRotationPoint(8.0F, -3.0F, 0.0F);
/* 119 */       setRotation(this.blocks[9][i], 0.0F, 0.0F, -0.6981317F);
/*     */ 
/*     */       
/* 122 */       this.blocks[10][i] = new ModelRenderer(this, textX, textY);
/* 123 */       this.blocks[10][i].addBox(2.0F, 4.0F, -4.0F, 8, 8, 8);
/* 124 */       this.blocks[10][i].setRotationPoint(8.0F, -3.0F, 0.0F);
/* 125 */       setRotation(this.blocks[10][i], 0.0F, 0.0F, -0.2094395F);
/*     */ 
/*     */       
/* 128 */       this.blocks[11][i] = new ModelRenderer(this, textX, textY);
/* 129 */       this.blocks[11][i].addBox(4.5F, 11.0F, -4.0F, 8, 8, 8);
/* 130 */       this.blocks[11][i].setRotationPoint(8.0F, -3.0F, 0.0F);
/*     */ 
/*     */       
/* 133 */       this.blocks[12][i] = new ModelRenderer(this, textX, textY);
/* 134 */       this.blocks[12][i].addBox(-8.0F, -2.0F, -4.0F, 8, 8, 8);
/* 135 */       this.blocks[12][i].setRotationPoint(-8.0F, -3.0F, 0.0F);
/* 136 */       setRotation(this.blocks[12][i], 0.0F, 0.0F, 0.6981317F);
/*     */ 
/*     */       
/* 139 */       this.blocks[13][i] = new ModelRenderer(this, textX, textY);
/* 140 */       this.blocks[13][i].addBox(-10.0F, 4.0F, -4.0F, 8, 8, 8);
/* 141 */       this.blocks[13][i].setRotationPoint(-8.0F, -3.0F, 0.0F);
/* 142 */       setRotation(this.blocks[13][i], 0.0F, 0.0F, 0.2094395F);
/*     */ 
/*     */       
/* 145 */       this.blocks[14][i] = new ModelRenderer(this, textX, textY);
/* 146 */       this.blocks[14][i].addBox(-12.5F, 11.0F, -4.0F, 8, 8, 8);
/* 147 */       this.blocks[14][i].setRotationPoint(-8.0F, -3.0F, 0.0F);
/*     */ 
/*     */       
/* 150 */       this.blocks[15][i] = new ModelRenderer(this, textX, textY);
/* 151 */       this.blocks[15][i].addBox(-3.5F, 0.0F, -4.0F, 8, 8, 8);
/* 152 */       this.blocks[15][i].setRotationPoint(5.0F, 4.0F, 0.0F);
/* 153 */       setRotation(this.blocks[15][i], -0.3490659F, 0.0F, 0.0F);
/*     */ 
/*     */       
/* 156 */       this.blocks[16][i] = new ModelRenderer(this, textX, textY);
/* 157 */       this.blocks[16][i].addBox(-4.0F, 6.0F, -7.0F, 8, 8, 8);
/* 158 */       this.blocks[16][i].setRotationPoint(5.0F, 4.0F, 0.0F);
/*     */ 
/*     */       
/* 161 */       this.blocks[17][i] = new ModelRenderer(this, textX, textY);
/* 162 */       this.blocks[17][i].addBox(-3.5F, 12.0F, -5.0F, 8, 8, 8);
/* 163 */       this.blocks[17][i].setRotationPoint(5.0F, 4.0F, 0.0F);
/*     */ 
/*     */       
/* 166 */       this.blocks[18][i] = new ModelRenderer(this, textX, textY);
/* 167 */       this.blocks[18][i].addBox(-4.5F, 0.0F, -4.0F, 8, 8, 8);
/* 168 */       this.blocks[18][i].setRotationPoint(-5.0F, 4.0F, 0.0F);
/* 169 */       setRotation(this.blocks[18][i], -0.3490659F, 0.0F, 0.0F);
/*     */ 
/*     */       
/* 172 */       this.blocks[19][i] = new ModelRenderer(this, textX, textY);
/* 173 */       this.blocks[19][i].addBox(-4.0F, 6.0F, -7.0F, 8, 8, 8);
/* 174 */       this.blocks[19][i].setRotationPoint(-5.0F, 4.0F, 0.0F);
/*     */ 
/*     */       
/* 177 */       this.blocks[20][i] = new ModelRenderer(this, textX, textY);
/* 178 */       this.blocks[20][i].addBox(-4.5F, 12.0F, -5.0F, 8, 8, 8);
/* 179 */       this.blocks[20][i].setRotationPoint(-5.0F, 4.0F, 0.0F);
/*     */ 
/*     */       
/* 182 */       this.blocks[21][i] = new ModelRenderer(this, textX, textY);
/* 183 */       this.blocks[21][i].addBox(0.0F, -4.0F, -8.0F, 8, 8, 8);
/* 184 */       this.blocks[21][i].setRotationPoint(0.0F, 6.0F, 3.0F);
/* 185 */       setRotation(this.blocks[21][i], 0.0F, 0.7853982F, 0.0F);
/*     */ 
/*     */       
/* 188 */       this.blocks[22][i] = new ModelRenderer(this, textX, textY);
/* 189 */       this.blocks[22][i].addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8);
/* 190 */       this.blocks[22][i].setRotationPoint(0.0F, 6.0F, 3.0F);
/* 191 */       setRotation(this.blocks[22][i], -0.7435722F, 0.0F, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 199 */     MoCEntityGolem entityG = (MoCEntityGolem)entity;
/* 200 */     boolean openChest = entityG.openChest();
/* 201 */     boolean isSummoning = entityG.isMissingCubes();
/* 202 */     boolean angry = (entityG.getGolemState() > 1);
/* 203 */     boolean throwing = (entityG.tcounter > 25);
/*     */     
/* 205 */     for (int i = 0; i < 23; i++) {
/* 206 */       this.blocksText[i] = entityG.getBlockText(i);
/*     */     }
/* 208 */     float yOffset = entityG.getAdjustedYOffset();
/* 209 */     setRotationAngles(f, f1, f2, f3, f4, f5, openChest, isSummoning, throwing);
/* 210 */     GL11.glPushMatrix();
/* 211 */     GL11.glTranslatef(0.0F, yOffset, 0.0F);
/* 212 */     for (int j = 0; j < 23; j++) {
/*     */       
/* 214 */       if (this.blocksText[j] != 30) {
/* 215 */         this.blocks[j][this.blocksText[j]].render(f5);
/*     */       }
/*     */     } 
/*     */     
/* 219 */     if (angry) {
/* 220 */       this.headb.render(f5);
/* 221 */       this.chestb.render(f5);
/*     */     } else {
/* 223 */       this.head.render(f5);
/* 224 */       this.chest.render(f5);
/*     */     } 
/* 226 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 231 */     model.rotateAngleX = x;
/* 232 */     model.rotateAngleY = y;
/* 233 */     model.rotateAngleZ = z;
/*     */   }
/*     */   
/*     */   private void setRotationG(ModelRenderer model, float x, float y, float z) {
/* 237 */     model.rotateAngleX = x / this.radianF;
/* 238 */     model.rotateAngleY = y / this.radianF;
/* 239 */     model.rotateAngleZ = z / this.radianF;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, boolean openChest, boolean isSummoning, boolean throwing) {
/* 244 */     float RLegXRot = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.2F * f1;
/* 245 */     float LLegXRot = MathHelper.cos(f * 0.6662F) * 1.2F * f1;
/* 246 */     float RArmZRot = -(MathHelper.cos(f2 * 0.09F) * 0.05F) + 0.05F;
/* 247 */     float LArmZRot = MathHelper.cos(f2 * 0.09F) * 0.05F - 0.05F;
/*     */     
/* 249 */     this.head.rotateAngleY = (45.0F + f3) / this.radianF;
/* 250 */     this.headb.rotateAngleY = (45.0F + f3) / this.radianF;
/*     */     
/* 252 */     if (isSummoning) {
/* 253 */       this.chest.rotateAngleY = 45.0F / this.radianF + f2 / 2.0F;
/* 254 */       this.chestb.rotateAngleY = 45.0F / this.radianF + f2 / 2.0F;
/*     */     } else {
/* 256 */       this.chest.rotateAngleY = 45.0F / this.radianF;
/* 257 */       this.chestb.rotateAngleY = 45.0F / this.radianF;
/*     */     } 
/*     */     
/* 260 */     if (openChest) {
/* 261 */       this.chest.rotationPointZ = -7.0F;
/* 262 */       this.chestb.rotationPointZ = -7.0F;
/*     */       
/* 264 */       if (this.blocksText[0] != 30) {
/* 265 */         (this.blocks[0][this.blocksText[0]]).rotateAngleY = -60.0F / this.radianF;
/*     */       }
/* 267 */       if (this.blocksText[1] != 30) {
/* 268 */         (this.blocks[1][this.blocksText[1]]).rotateAngleY = -55.0F / this.radianF;
/*     */       }
/* 270 */       if (this.blocksText[2] != 30) {
/* 271 */         (this.blocks[2][this.blocksText[2]]).rotateAngleY = 60.0F / this.radianF;
/*     */       }
/* 273 */       if (this.blocksText[3] != 30) {
/* 274 */         (this.blocks[3][this.blocksText[3]]).rotateAngleY = 55.0F / this.radianF;
/*     */       }
/*     */     } else {
/* 277 */       this.chest.rotationPointZ = -4.0F;
/* 278 */       this.chestb.rotationPointZ = -4.0F;
/*     */       
/* 280 */       if (this.blocksText[0] != 30) {
/* 281 */         (this.blocks[0][this.blocksText[0]]).rotateAngleY = -40.0F / this.radianF;
/*     */       }
/* 283 */       if (this.blocksText[1] != 30) {
/* 284 */         (this.blocks[1][this.blocksText[1]]).rotateAngleY = -41.0F / this.radianF;
/*     */       }
/* 286 */       if (this.blocksText[2] != 30) {
/* 287 */         (this.blocks[2][this.blocksText[2]]).rotateAngleY = 40.0F / this.radianF;
/*     */       }
/* 289 */       if (this.blocksText[3] != 30) {
/* 290 */         (this.blocks[3][this.blocksText[3]]).rotateAngleY = 41.0F / this.radianF;
/*     */       }
/*     */     } 
/*     */     
/* 294 */     if (this.blocksText[15] != 30) {
/* 295 */       (this.blocks[15][this.blocksText[15]]).rotateAngleX = -20.0F / this.radianF + LLegXRot;
/*     */     }
/*     */     
/* 298 */     if (this.blocksText[16] != 30) {
/* 299 */       (this.blocks[16][this.blocksText[16]]).rotateAngleX = LLegXRot;
/*     */     }
/*     */     
/* 302 */     if (this.blocksText[17] != 30) {
/* 303 */       (this.blocks[17][this.blocksText[17]]).rotateAngleX = LLegXRot;
/*     */     }
/*     */     
/* 306 */     if (this.blocksText[18] != 30) {
/* 307 */       (this.blocks[18][this.blocksText[18]]).rotateAngleX = -20.0F / this.radianF + RLegXRot;
/*     */     }
/*     */     
/* 310 */     if (this.blocksText[19] != 30) {
/* 311 */       (this.blocks[19][this.blocksText[19]]).rotateAngleX = RLegXRot;
/*     */     }
/*     */     
/* 314 */     if (this.blocksText[20] != 30) {
/* 315 */       (this.blocks[20][this.blocksText[20]]).rotateAngleX = RLegXRot;
/*     */     }
/*     */     
/* 318 */     if (throwing) {
/* 319 */       LLegXRot = -90.0F / this.radianF;
/* 320 */       RLegXRot = -90.0F / this.radianF;
/* 321 */       RArmZRot = 0.0F;
/* 322 */       LArmZRot = 0.0F;
/*     */     } 
/*     */     
/* 325 */     if (this.blocksText[12] != 30) {
/* 326 */       (this.blocks[12][this.blocksText[12]]).rotateAngleZ = 40.0F / this.radianF + RArmZRot;
/* 327 */       (this.blocks[12][this.blocksText[12]]).rotateAngleX = LLegXRot;
/*     */     } 
/*     */     
/* 330 */     if (this.blocksText[13] != 30) {
/* 331 */       (this.blocks[13][this.blocksText[13]]).rotateAngleZ = 12.0F / this.radianF + RArmZRot;
/* 332 */       (this.blocks[13][this.blocksText[13]]).rotateAngleX = LLegXRot;
/*     */     } 
/*     */     
/* 335 */     if (this.blocksText[14] != 30) {
/* 336 */       (this.blocks[14][this.blocksText[14]]).rotateAngleZ = RArmZRot;
/* 337 */       (this.blocks[14][this.blocksText[14]]).rotateAngleX = LLegXRot;
/*     */     } 
/*     */     
/* 340 */     if (this.blocksText[9] != 30) {
/* 341 */       (this.blocks[9][this.blocksText[9]]).rotateAngleZ = -40.0F / this.radianF + LArmZRot;
/* 342 */       (this.blocks[9][this.blocksText[9]]).rotateAngleX = RLegXRot;
/*     */     } 
/*     */     
/* 345 */     if (this.blocksText[10] != 30) {
/* 346 */       (this.blocks[10][this.blocksText[10]]).rotateAngleZ = -12.0F / this.radianF + LArmZRot;
/* 347 */       (this.blocks[10][this.blocksText[10]]).rotateAngleX = RLegXRot;
/*     */     } 
/*     */     
/* 350 */     if (this.blocksText[11] != 30) {
/* 351 */       (this.blocks[11][this.blocksText[11]]).rotateAngleZ = LArmZRot;
/* 352 */       (this.blocks[11][this.blocksText[11]]).rotateAngleX = RLegXRot;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */