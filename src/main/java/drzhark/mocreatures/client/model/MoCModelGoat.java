/*     */ package drzhark.mocreatures.client.model;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MoCModelGoat
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer Leg1;
/*     */   ModelRenderer Leg2;
/*     */   ModelRenderer Leg3;
/*     */   ModelRenderer Leg4;
/*     */   ModelRenderer Body;
/*     */   ModelRenderer Tail;
/*     */   ModelRenderer LEar;
/*     */   ModelRenderer REar;
/*     */   ModelRenderer Head;
/*     */   ModelRenderer Nose;
/*     */   ModelRenderer Tongue;
/*     */   ModelRenderer Mouth;
/*     */   ModelRenderer RHorn1;
/*     */   ModelRenderer RHorn2;
/*     */   ModelRenderer RHorn3;
/*     */   ModelRenderer RHorn4;
/*     */   ModelRenderer RHorn5;
/*     */   ModelRenderer LHorn1;
/*     */   ModelRenderer LHorn2;
/*     */   ModelRenderer LHorn3;
/*     */   ModelRenderer LHorn4;
/*     */   ModelRenderer LHorn5;
/*     */   ModelRenderer Goatie;
/*     */   ModelRenderer Neck;
/*     */   ModelRenderer Tits;
/*     */   public int typeInt;
/*     */   public int attacking;
/*     */   public float edad;
/*     */   public boolean bleat;
/*     */   public int legMov;
/*     */   public int earMov;
/*     */   public int tailMov;
/*     */   public int eatMov;
/*     */   
/*     */   public MoCModelGoat() {
/*  50 */     this.textureWidth = 64;
/*  51 */     this.textureHeight = 32;
/*     */     
/*  53 */     this.Leg1 = new ModelRenderer(this, 0, 23);
/*  54 */     this.Leg1.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2);
/*  55 */     this.Leg1.setRotationPoint(2.0F, 17.0F, -6.0F);
/*     */ 
/*     */ 
/*     */     
/*  59 */     this.Leg2 = new ModelRenderer(this, 0, 23);
/*  60 */     this.Leg2.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2);
/*  61 */     this.Leg2.setRotationPoint(-2.0F, 17.0F, -6.0F);
/*     */ 
/*     */ 
/*     */     
/*  65 */     this.Leg3 = new ModelRenderer(this, 0, 23);
/*  66 */     this.Leg3.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2);
/*  67 */     this.Leg3.setRotationPoint(-2.0F, 17.0F, 6.0F);
/*     */ 
/*     */ 
/*     */     
/*  71 */     this.Leg4 = new ModelRenderer(this, 0, 23);
/*  72 */     this.Leg4.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2);
/*  73 */     this.Leg4.setRotationPoint(2.0F, 17.0F, 6.0F);
/*     */ 
/*     */ 
/*     */     
/*  77 */     this.Body = new ModelRenderer(this, 20, 8);
/*  78 */     this.Body.addBox(-3.0F, -4.0F, -8.0F, 6, 8, 16);
/*  79 */     this.Body.setRotationPoint(0.0F, 13.0F, 0.0F);
/*     */     
/*  81 */     this.Tail = new ModelRenderer(this, 22, 8);
/*  82 */     this.Tail.addBox(-1.5F, -1.0F, 0.0F, 3, 2, 4);
/*  83 */     this.Tail.setRotationPoint(0.0F, 10.0F, 8.0F);
/*     */     
/*  85 */     this.LEar = new ModelRenderer(this, 52, 8);
/*  86 */     this.LEar.addBox(1.5F, -2.0F, 0.0F, 2, 1, 1);
/*  87 */     this.LEar.setRotationPoint(0.0F, 8.0F, -12.0F);
/*     */ 
/*     */ 
/*     */     
/*  91 */     this.REar = new ModelRenderer(this, 52, 8);
/*  92 */     this.REar.addBox(-3.5F, -2.0F, 0.0F, 2, 1, 1);
/*  93 */     this.REar.setRotationPoint(0.0F, 8.0F, -12.0F);
/*     */ 
/*     */ 
/*     */     
/*  97 */     this.Head = new ModelRenderer(this, 52, 16);
/*  98 */     this.Head.addBox(-1.5F, -2.0F, -2.0F, 3, 5, 3);
/*  99 */     this.Head.setRotationPoint(0.0F, 8.0F, -12.0F);
/*     */     
/* 101 */     this.Nose = new ModelRenderer(this, 52, 10);
/* 102 */     this.Nose.addBox(-1.5F, -1.0F, -5.0F, 3, 3, 3);
/* 103 */     this.Nose.setRotationPoint(0.0F, 8.0F, -12.0F);
/*     */     
/* 105 */     this.Tongue = new ModelRenderer(this, 56, 5);
/* 106 */     this.Tongue.addBox(-0.5F, 2.0F, -5.0F, 1, 0, 3);
/* 107 */     this.Tongue.setRotationPoint(0.0F, 8.0F, -12.0F);
/*     */     
/* 109 */     this.Mouth = new ModelRenderer(this, 54, 0);
/* 110 */     this.Mouth.addBox(-1.0F, 2.0F, -5.0F, 2, 1, 3);
/* 111 */     this.Mouth.setRotationPoint(0.0F, 8.0F, -12.0F);
/*     */     
/* 113 */     this.RHorn1 = new ModelRenderer(this, 0, 0);
/* 114 */     this.RHorn1.addBox(-1.5F, -3.0F, -0.7F, 1, 1, 1, 0.1F);
/* 115 */     this.RHorn1.setRotationPoint(0.0F, 8.0F, -12.0F);
/*     */     
/* 117 */     this.RHorn2 = new ModelRenderer(this, 0, 0);
/* 118 */     this.RHorn2.addBox(-1.9F, -4.0F, -0.2F, 1, 1, 1);
/* 119 */     this.RHorn2.setRotationPoint(0.0F, 8.0F, -12.0F);
/*     */     
/* 121 */     this.RHorn3 = new ModelRenderer(this, 0, 0);
/* 122 */     this.RHorn3.addBox(-2.1F, -4.8F, 0.5F, 1, 1, 1, -0.05F);
/* 123 */     this.RHorn3.setRotationPoint(0.0F, 8.0F, -12.0F);
/*     */     
/* 125 */     this.RHorn4 = new ModelRenderer(this, 0, 0);
/* 126 */     this.RHorn4.addBox(-2.3F, -5.2F, 1.4F, 1, 1, 1, -0.1F);
/* 127 */     this.RHorn4.setRotationPoint(0.0F, 8.0F, -12.0F);
/*     */     
/* 129 */     this.RHorn5 = new ModelRenderer(this, 0, 0);
/* 130 */     this.RHorn5.addBox(-2.6F, -4.9F, 2.0F, 1, 1, 1, -0.15F);
/* 131 */     this.RHorn5.setRotationPoint(0.0F, 8.0F, -12.0F);
/*     */     
/* 133 */     this.LHorn1 = new ModelRenderer(this, 0, 0);
/* 134 */     this.LHorn1.addBox(0.5F, -3.0F, -0.7F, 1, 1, 1, 0.1F);
/* 135 */     this.LHorn1.setRotationPoint(0.0F, 8.0F, -12.0F);
/*     */     
/* 137 */     this.LHorn2 = new ModelRenderer(this, 0, 0);
/* 138 */     this.LHorn2.addBox(0.9F, -4.0F, -0.2F, 1, 1, 1);
/* 139 */     this.LHorn2.setRotationPoint(0.0F, 8.0F, -12.0F);
/*     */     
/* 141 */     this.LHorn3 = new ModelRenderer(this, 0, 0);
/* 142 */     this.LHorn3.addBox(1.2F, -4.9F, 0.5F, 1, 1, 1, -0.05F);
/* 143 */     this.LHorn3.setRotationPoint(0.0F, 8.0F, -12.0F);
/*     */     
/* 145 */     this.LHorn4 = new ModelRenderer(this, 0, 0);
/* 146 */     this.LHorn4.addBox(1.4F, -5.3F, 1.4F, 1, 1, 1, -0.1F);
/* 147 */     this.LHorn4.setRotationPoint(0.0F, 8.0F, -12.0F);
/*     */     
/* 149 */     this.LHorn5 = new ModelRenderer(this, 0, 0);
/* 150 */     this.LHorn5.addBox(1.7F, -4.9F, 2.1F, 1, 1, 1, -0.15F);
/* 151 */     this.LHorn5.setRotationPoint(0.0F, 8.0F, -12.0F);
/*     */     
/* 153 */     this.Goatie = new ModelRenderer(this, 52, 5);
/* 154 */     this.Goatie.addBox(-0.5F, 3.0F, -4.0F, 1, 2, 1);
/* 155 */     this.Goatie.setRotationPoint(0.0F, 8.0F, -12.0F);
/*     */     
/* 157 */     this.Neck = new ModelRenderer(this, 18, 14);
/* 158 */     this.Neck.addBox(-1.5F, -2.0F, -5.0F, 3, 4, 6, -0.2F);
/* 159 */     this.Neck.setRotationPoint(0.0F, 11.0F, -8.0F);
/* 160 */     this.Neck.rotateAngleX = -0.418879F;
/*     */     
/* 162 */     this.Tits = new ModelRenderer(this, 18, 0);
/* 163 */     this.Tits.addBox(-2.5F, 0.0F, -2.0F, 5, 1, 4);
/* 164 */     this.Tits.setRotationPoint(0.0F, 17.0F, 3.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 170 */     super.render(entity, f, f1, f2, f3, f4, f5);
/* 171 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/* 172 */     this.Leg1.render(f5);
/* 173 */     this.Leg2.render(f5);
/* 174 */     this.Leg3.render(f5);
/* 175 */     this.Leg4.render(f5);
/* 176 */     this.Body.render(f5);
/* 177 */     this.Tail.render(f5);
/* 178 */     this.Neck.render(f5);
/* 179 */     if (this.typeInt > 1 && this.typeInt < 5) {
/* 180 */       this.Tits.render(f5);
/*     */     }
/* 182 */     GL11.glPushMatrix();
/*     */     
/* 184 */     if (this.attacking != 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 191 */       float yOff = this.attacking / 150.0F - 0.2F;
/*     */       
/* 193 */       float zOff = this.attacking / 450.0F - 0.06666667F;
/* 194 */       GL11.glTranslatef(0.0F, yOff, -zOff);
/*     */     } 
/* 196 */     this.LEar.render(f5);
/* 197 */     this.REar.render(f5);
/* 198 */     this.Head.render(f5);
/* 199 */     this.Nose.render(f5);
/*     */     
/* 201 */     if (this.typeInt > 1) {
/* 202 */       if (this.edad > 0.7D) {
/* 203 */         this.RHorn1.render(f5);
/* 204 */         this.LHorn1.render(f5);
/*     */       } 
/* 206 */       if (this.edad > 0.8D) {
/* 207 */         this.RHorn2.render(f5);
/* 208 */         this.LHorn2.render(f5);
/*     */       } 
/*     */     } 
/* 211 */     if (this.typeInt > 4) {
/* 212 */       if (this.edad > 0.8D) {
/* 213 */         this.RHorn3.render(f5);
/* 214 */         this.LHorn3.render(f5);
/*     */       } 
/* 216 */       if (this.edad > 0.85D) {
/* 217 */         this.RHorn4.render(f5);
/* 218 */         this.LHorn4.render(f5);
/*     */       } 
/* 220 */       if (this.edad > 0.9D) {
/* 221 */         this.RHorn5.render(f5);
/* 222 */         this.LHorn5.render(f5);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 229 */     if (this.eatMov != 0 && !this.bleat) {
/* 230 */       GL11.glTranslatef(this.eatMov / 100.0F, 0.0F, 0.0F);
/*     */     }
/* 232 */     if (this.typeInt > 4 && this.edad > 0.9D) {
/* 233 */       this.Goatie.render(f5);
/*     */     }
/* 235 */     this.Tongue.render(f5);
/* 236 */     this.Mouth.render(f5);
/*     */     
/* 238 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 244 */     this.Leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
/* 245 */     this.Leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
/* 246 */     this.Leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
/* 247 */     this.Leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
/* 248 */     float baseAngle = 0.5235988F + f4 / 57.29578F;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 253 */     if (f3 > 20.0F) {
/* 254 */       f3 = 20.0F;
/*     */     }
/* 256 */     if (f3 < -20.0F) {
/* 257 */       f3 = -20.0F;
/*     */     }
/* 259 */     this.Head.rotateAngleY = f3 / 57.29578F;
/* 260 */     this.Neck.rotateAngleX = -0.5235988F;
/* 261 */     this.Tail.rotateAngleX = this.tailMov / 57.29578F;
/*     */     
/* 263 */     this.Head.rotateAngleX = baseAngle;
/* 264 */     if (this.bleat) {
/* 265 */       this.Head.rotateAngleX = -0.2617994F;
/*     */     }
/* 267 */     if (this.attacking != 0) {
/* 268 */       this.Head.rotateAngleX = this.attacking / 57.29578F;
/* 269 */       this.Neck.rotateAngleX = (1.33F * this.attacking - 70.0F) / 57.29578F;
/* 270 */       if (this.legMov != 0) {
/* 271 */         this.Leg1.rotateAngleX = this.legMov / 57.29578F;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 278 */     this.LEar.rotateAngleX = this.Head.rotateAngleX;
/* 279 */     this.REar.rotateAngleX = this.Head.rotateAngleX;
/* 280 */     if (!this.bleat && this.attacking == 0) {
/* 281 */       this.LEar.rotateAngleX = baseAngle + this.earMov / 57.29578F;
/* 282 */       this.REar.rotateAngleX = baseAngle + this.earMov / 57.29578F;
/*     */     } 
/*     */     
/* 285 */     this.Nose.rotateAngleX = this.Head.rotateAngleX;
/* 286 */     this.Mouth.rotateAngleX = this.Head.rotateAngleX;
/* 287 */     this.Tongue.rotateAngleX = this.Head.rotateAngleX;
/* 288 */     this.Goatie.rotateAngleX = this.Head.rotateAngleX;
/* 289 */     this.RHorn1.rotateAngleX = this.Head.rotateAngleX;
/* 290 */     this.LHorn1.rotateAngleX = this.Head.rotateAngleX;
/* 291 */     this.RHorn2.rotateAngleX = this.Head.rotateAngleX;
/* 292 */     this.LHorn2.rotateAngleX = this.Head.rotateAngleX;
/* 293 */     this.RHorn3.rotateAngleX = this.Head.rotateAngleX;
/* 294 */     this.LHorn3.rotateAngleX = this.Head.rotateAngleX;
/* 295 */     this.RHorn4.rotateAngleX = this.Head.rotateAngleX;
/* 296 */     this.LHorn4.rotateAngleX = this.Head.rotateAngleX;
/* 297 */     this.RHorn5.rotateAngleX = this.Head.rotateAngleX;
/* 298 */     this.LHorn5.rotateAngleX = this.Head.rotateAngleX;
/* 299 */     if (this.bleat) {
/*     */       
/* 301 */       this.Mouth.rotateAngleX = 0.0F;
/* 302 */       this.Tongue.rotateAngleX = -0.08726646F;
/* 303 */       this.Goatie.rotateAngleX = 0.0F;
/*     */     } 
/*     */     
/* 306 */     this.Nose.rotateAngleY = this.Head.rotateAngleY;
/* 307 */     this.Mouth.rotateAngleY = this.Head.rotateAngleY;
/* 308 */     this.Tongue.rotateAngleY = this.Head.rotateAngleY;
/* 309 */     this.LEar.rotateAngleY = this.Head.rotateAngleY;
/* 310 */     this.REar.rotateAngleY = this.Head.rotateAngleY;
/* 311 */     this.Goatie.rotateAngleY = this.Head.rotateAngleY;
/* 312 */     this.RHorn1.rotateAngleY = this.Head.rotateAngleY;
/* 313 */     this.LHorn1.rotateAngleY = this.Head.rotateAngleY;
/* 314 */     this.RHorn2.rotateAngleY = this.Head.rotateAngleY;
/* 315 */     this.LHorn2.rotateAngleY = this.Head.rotateAngleY;
/* 316 */     this.RHorn3.rotateAngleY = this.Head.rotateAngleY;
/* 317 */     this.LHorn3.rotateAngleY = this.Head.rotateAngleY;
/* 318 */     this.RHorn4.rotateAngleY = this.Head.rotateAngleY;
/* 319 */     this.LHorn4.rotateAngleY = this.Head.rotateAngleY;
/* 320 */     this.RHorn5.rotateAngleY = this.Head.rotateAngleY;
/* 321 */     this.LHorn5.rotateAngleY = this.Head.rotateAngleY;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelGoat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */