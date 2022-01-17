/*     */ package drzhark.mocreatures.client.model;
/*     */ 
/*     */ import drzhark.mocreatures.entity.ambient.MoCEntityRoach;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class MoCModelRoach
/*     */   extends ModelBase {
/*     */   ModelRenderer Head;
/*     */   ModelRenderer LAnthenna;
/*     */   ModelRenderer LAnthennaB;
/*     */   ModelRenderer RAnthenna;
/*     */   ModelRenderer RAnthennaB;
/*     */   ModelRenderer Thorax;
/*     */   ModelRenderer FrontLegs;
/*     */   ModelRenderer MidLegs;
/*     */   ModelRenderer RearLegs;
/*     */   ModelRenderer Abdomen;
/*     */   ModelRenderer TailL;
/*     */   ModelRenderer TailR;
/*     */   ModelRenderer LShellClosed;
/*     */   ModelRenderer RShellClosed;
/*     */   ModelRenderer LShellOpen;
/*     */   ModelRenderer RShellOpen;
/*     */   ModelRenderer LeftWing;
/*     */   ModelRenderer RightWing;
/*  30 */   private float radianF = 57.29578F;
/*     */   
/*     */   public MoCModelRoach() {
/*  33 */     this.textureWidth = 32;
/*  34 */     this.textureHeight = 32;
/*     */     
/*  36 */     this.Head = new ModelRenderer(this, 0, 0);
/*  37 */     this.Head.addBox(-0.5F, 0.0F, -1.0F, 1, 1, 2);
/*  38 */     this.Head.setRotationPoint(0.0F, 23.0F, -2.0F);
/*  39 */     setRotation(this.Head, -2.171231F, 0.0F, 0.0F);
/*     */     
/*  41 */     this.LAnthenna = new ModelRenderer(this, 3, 21);
/*     */ 
/*     */     
/*  44 */     this.LAnthenna.addBox(0.0F, 0.0F, 0.0F, 4, 0, 1);
/*  45 */     this.LAnthenna.setRotationPoint(0.5F, 0.0F, 0.0F);
/*     */ 
/*     */ 
/*     */     
/*  49 */     setRotation(this.LAnthenna, -90.0F / this.radianF, 0.4363323F, 0.0F);
/*  50 */     this.Head.addChild(this.LAnthenna);
/*     */     
/*  52 */     this.LAnthennaB = new ModelRenderer(this, 4, 21);
/*     */ 
/*     */ 
/*     */     
/*  56 */     this.LAnthennaB.addBox(0.0F, 0.0F, 1.0F, 3, 0, 1);
/*  57 */     this.LAnthennaB.setRotationPoint(2.5F, 0.0F, -0.5F);
/*     */ 
/*     */ 
/*     */     
/*  61 */     setRotation(this.LAnthennaB, 0.0F, 45.0F / this.radianF, 0.0F);
/*  62 */     this.LAnthenna.addChild(this.LAnthennaB);
/*     */     
/*  64 */     this.RAnthenna = new ModelRenderer(this, 3, 19);
/*  65 */     this.RAnthenna.addBox(-4.5F, 0.0F, 0.0F, 4, 0, 1);
/*     */ 
/*     */     
/*  68 */     this.RAnthenna.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  69 */     setRotation(this.RAnthenna, -90.0F / this.radianF, -0.4363323F, 0.0F);
/*  70 */     this.Head.addChild(this.RAnthenna);
/*     */     
/*  72 */     this.RAnthennaB = new ModelRenderer(this, 4, 19);
/*  73 */     this.RAnthennaB.addBox(-4.0F, 0.0F, 1.0F, 3, 0, 1);
/*  74 */     this.RAnthennaB.setRotationPoint(-2.5F, 0.0F, 0.5F);
/*  75 */     setRotation(this.RAnthennaB, 0.0F, -45.0F / this.radianF, 0.0F);
/*  76 */     this.RAnthenna.addChild(this.RAnthennaB);
/*     */     
/*  78 */     this.Thorax = new ModelRenderer(this, 0, 3);
/*  79 */     this.Thorax.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2);
/*  80 */     this.Thorax.setRotationPoint(0.0F, 22.0F, -1.0F);
/*     */     
/*  82 */     this.FrontLegs = new ModelRenderer(this, 0, 11);
/*  83 */     this.FrontLegs.addBox(-2.0F, 0.0F, 0.0F, 4, 2, 0);
/*  84 */     this.FrontLegs.setRotationPoint(0.0F, 23.0F, -1.8F);
/*  85 */     setRotation(this.FrontLegs, -1.115358F, 0.0F, 0.0F);
/*     */     
/*  87 */     this.MidLegs = new ModelRenderer(this, 0, 13);
/*  88 */     this.MidLegs.addBox(-2.5F, 0.0F, 0.0F, 5, 2, 0);
/*  89 */     this.MidLegs.setRotationPoint(0.0F, 23.0F, -1.2F);
/*  90 */     setRotation(this.MidLegs, 1.264073F, 0.0F, 0.0F);
/*     */     
/*  92 */     this.RearLegs = new ModelRenderer(this, 0, 15);
/*  93 */     this.RearLegs.addBox(-2.0F, 0.0F, 0.0F, 4, 4, 0);
/*  94 */     this.RearLegs.setRotationPoint(0.0F, 23.0F, -0.4F);
/*  95 */     setRotation(this.RearLegs, 1.368173F, 0.0F, 0.0F);
/*     */     
/*  97 */     this.Abdomen = new ModelRenderer(this, 0, 6);
/*  98 */     this.Abdomen.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 1);
/*  99 */     this.Abdomen.setRotationPoint(0.0F, 22.0F, 0.0F);
/* 100 */     setRotation(this.Abdomen, 1.427659F, 0.0F, 0.0F);
/*     */     
/* 102 */     this.TailL = new ModelRenderer(this, 2, 29);
/* 103 */     this.TailL.addBox(-0.5F, 0.0F, 0.0F, 1, 2, 0);
/* 104 */     this.TailL.setRotationPoint(0.0F, 23.0F, 3.6F);
/* 105 */     setRotation(this.TailL, 1.554066F, 0.6457718F, 0.0F);
/*     */     
/* 107 */     this.TailR = new ModelRenderer(this, 0, 29);
/* 108 */     this.TailR.addBox(-0.5F, 0.0F, 0.0F, 1, 2, 0);
/* 109 */     this.TailR.setRotationPoint(0.0F, 23.0F, 3.6F);
/* 110 */     setRotation(this.TailR, 1.554066F, -0.6457718F, 0.0F);
/*     */     
/* 112 */     this.LShellClosed = new ModelRenderer(this, 4, 23);
/* 113 */     this.LShellClosed.addBox(0.0F, 0.0F, 0.0F, 2, 0, 6);
/* 114 */     this.LShellClosed.setRotationPoint(0.0F, 21.5F, -1.5F);
/* 115 */     setRotation(this.LShellClosed, -0.1487144F, -0.0872665F, 0.1919862F);
/*     */     
/* 117 */     this.RShellClosed = new ModelRenderer(this, 0, 23);
/* 118 */     this.RShellClosed.addBox(-2.0F, 0.0F, 0.0F, 2, 0, 6);
/* 119 */     this.RShellClosed.setRotationPoint(0.0F, 21.5F, -1.5F);
/* 120 */     setRotation(this.RShellClosed, -0.1487144F, 0.0872665F, -0.1919862F);
/*     */     
/* 122 */     this.LShellOpen = new ModelRenderer(this, 4, 23);
/* 123 */     this.LShellOpen.addBox(0.0F, 0.0F, 0.0F, 2, 0, 6);
/* 124 */     this.LShellOpen.setRotationPoint(0.0F, 21.5F, -1.5F);
/* 125 */     setRotation(this.LShellOpen, 1.117011F, -0.0872665F, 1.047198F);
/*     */     
/* 127 */     this.RShellOpen = new ModelRenderer(this, 0, 23);
/* 128 */     this.RShellOpen.addBox(-2.0F, 0.0F, 0.0F, 2, 0, 6);
/* 129 */     this.RShellOpen.setRotationPoint(0.0F, 21.5F, -1.5F);
/* 130 */     setRotation(this.RShellOpen, 1.117011F, 0.0872665F, -1.047198F);
/*     */     
/* 132 */     this.LeftWing = new ModelRenderer(this, 11, 21);
/* 133 */     this.LeftWing.addBox(0.0F, 1.0F, -1.0F, 6, 0, 2);
/* 134 */     this.LeftWing.setRotationPoint(0.0F, 21.5F, -1.5F);
/* 135 */     this.LeftWing.setTextureSize(32, 32);
/* 136 */     this.LeftWing.mirror = true;
/* 137 */     setRotation(this.LeftWing, 0.0F, -1.047198F, -0.4363323F);
/* 138 */     this.RightWing = new ModelRenderer(this, 11, 19);
/* 139 */     this.RightWing.addBox(-6.0F, 1.0F, -1.0F, 6, 0, 2);
/* 140 */     this.RightWing.setRotationPoint(0.0F, 21.5F, -1.5F);
/* 141 */     this.RightWing.setTextureSize(32, 32);
/* 142 */     this.RightWing.mirror = true;
/* 143 */     setRotation(this.RightWing, 0.0F, 1.047198F, 0.4363323F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 148 */     MoCEntityRoach entityroach = (MoCEntityRoach)entity;
/* 149 */     boolean isFlying = (entityroach.getIsFlying() || entityroach.motionY < -0.1D);
/*     */     
/* 151 */     setRotationAngles(f, f1, f2, f3, f4, f5, isFlying);
/* 152 */     this.Head.render(f5);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 157 */     this.Thorax.render(f5);
/* 158 */     this.FrontLegs.render(f5);
/* 159 */     this.MidLegs.render(f5);
/* 160 */     this.RearLegs.render(f5);
/* 161 */     this.Abdomen.render(f5);
/* 162 */     this.TailL.render(f5);
/* 163 */     this.TailR.render(f5);
/*     */     
/* 165 */     if (!isFlying) {
/* 166 */       this.LShellClosed.render(f5);
/* 167 */       this.RShellClosed.render(f5);
/*     */     } else {
/* 169 */       this.LShellOpen.render(f5);
/* 170 */       this.RShellOpen.render(f5);
/* 171 */       GL11.glPushMatrix();
/* 172 */       GL11.glEnable(3042);
/* 173 */       float transparency = 0.6F;
/* 174 */       GL11.glBlendFunc(770, 771);
/* 175 */       GL11.glColor4f(0.8F, 0.8F, 0.8F, transparency);
/* 176 */       this.LeftWing.render(f5);
/* 177 */       this.RightWing.render(f5);
/* 178 */       GL11.glDisable(3042);
/* 179 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 184 */     model.rotateAngleX = x;
/* 185 */     model.rotateAngleY = y;
/* 186 */     model.rotateAngleZ = z;
/*     */   }
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, boolean isFlying) {
/* 190 */     this.Head.rotateAngleX = -2.171231F + f4 / 57.29578F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 196 */     float antMov = 5.0F / this.radianF + f1 * 1.5F;
/*     */     
/* 198 */     this.LAnthenna.rotateAngleZ = -antMov;
/* 199 */     this.RAnthenna.rotateAngleZ = antMov;
/*     */     
/* 201 */     float legMov = 0.0F;
/* 202 */     float legMovB = 0.0F;
/*     */     
/* 204 */     float frontLegAdj = 0.0F;
/*     */     
/* 206 */     if (isFlying) {
/* 207 */       float WingRot = MathHelper.cos(f2 * 2.0F) * 0.7F;
/* 208 */       this.RightWing.rotateAngleY = 1.047198F + WingRot;
/* 209 */       this.LeftWing.rotateAngleY = -1.047198F - WingRot;
/* 210 */       legMov = f1 * 1.5F;
/* 211 */       legMovB = legMov;
/* 212 */       frontLegAdj = 1.4F;
/*     */     } else {
/*     */       
/* 215 */       legMov = MathHelper.cos(f * 1.5F + 3.141593F) * 0.6F * f1;
/* 216 */       legMovB = MathHelper.cos(f * 1.5F) * 0.8F * f1;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 221 */     this.FrontLegs.rotateAngleX = -1.115358F + frontLegAdj + legMov;
/* 222 */     this.MidLegs.rotateAngleX = 1.264073F + legMovB;
/* 223 */     this.RearLegs.rotateAngleX = 1.368173F - frontLegAdj + legMov;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelRoach.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */