/*     */ package drzhark.mocreatures.client.model;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityTurtle;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MoCModelTurtle extends ModelBase {
/*     */   ModelRenderer Shell;
/*     */   ModelRenderer ShellUp;
/*     */   ModelRenderer ShellTop;
/*     */   ModelRenderer Belly;
/*     */
/*     */   public MoCModelTurtle() {
/*  15 */     this.Shell = new ModelRenderer(this, 28, 0);
/*  16 */     this.Shell.addBox(0.0F, 0.0F, 0.0F, 9, 1, 9);
/*  17 */     this.Shell.setRotationPoint(-4.5F, 19.0F, -4.5F);
/*     */
/*  19 */     this.ShellUp = new ModelRenderer(this, 0, 22);
/*  20 */     this.ShellUp.addBox(0.0F, 0.0F, 0.0F, 8, 2, 8);
/*  21 */     this.ShellUp.setRotationPoint(-4.0F, 17.0F, -4.0F);
/*     */
/*  23 */     this.ShellTop = new ModelRenderer(this, 40, 10);
/*  24 */     this.ShellTop.addBox(0.0F, 0.0F, 0.0F, 6, 1, 6);
/*  25 */     this.ShellTop.setRotationPoint(-3.0F, 16.0F, -3.0F);
/*     */
/*  27 */     this.Belly = new ModelRenderer(this, 0, 12);
/*  28 */     this.Belly.addBox(0.0F, 0.0F, 0.0F, 8, 1, 8);
/*  29 */     this.Belly.setRotationPoint(-4.0F, 20.0F, -4.0F);
/*     */
/*  31 */     this.Leg1 = new ModelRenderer(this, 0, 0);
/*  32 */     this.Leg1.addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2);
/*  33 */     this.Leg1.setRotationPoint(3.5F, 20.0F, -3.5F);
/*     */
/*  35 */     this.Leg2 = new ModelRenderer(this, 0, 9);
/*  36 */     this.Leg2.addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2);
/*  37 */     this.Leg2.setRotationPoint(-3.5F, 20.0F, -3.5F);
/*     */
/*  39 */     this.Leg3 = new ModelRenderer(this, 0, 0);
/*  40 */     this.Leg3.addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2);
/*  41 */     this.Leg3.setRotationPoint(3.5F, 20.0F, 3.5F);
/*     */
/*  43 */     this.Leg4 = new ModelRenderer(this, 0, 9);
/*  44 */     this.Leg4.addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2);
/*  45 */     this.Leg4.setRotationPoint(-3.5F, 20.0F, 3.5F);
/*     */
/*  47 */     this.Head = new ModelRenderer(this, 10, 0);
/*  48 */     this.Head.addBox(-1.5F, -1.0F, -4.0F, 3, 2, 4);
/*  49 */     this.Head.setRotationPoint(0.0F, 20.0F, -4.5F);
/*     */
/*  51 */     this.Tail = new ModelRenderer(this, 0, 5);
/*  52 */     this.Tail.addBox(-1.0F, -1.0F, 0.0F, 2, 1, 3);
/*  53 */     this.Tail.setRotationPoint(0.0F, 21.0F, 4.0F);
/*     */   }
/*     */   ModelRenderer Leg1; ModelRenderer Leg2; ModelRenderer Leg3; ModelRenderer Leg4; ModelRenderer Head; ModelRenderer Tail; public boolean isHiding; public boolean upsidedown; private boolean turtleHat; private boolean TMNT;
/*     */   private boolean isSwimming;
/*     */   public float swingProgress;
/*     */
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/*  60 */     MoCEntityTurtle entityturtle = (MoCEntityTurtle)entity;
/*  61 */     this.TMNT = entityturtle.isTMNT();
/*  62 */     this.turtleHat = (entityturtle.getRidingEntity() != null);
/*  63 */     this.isSwimming = entityturtle.isInWater();
/*  64 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/*  65 */     this.Shell.render(f5);
/*  66 */     this.ShellUp.render(f5);
/*  67 */     if (!this.TMNT) {
/*  68 */       this.ShellTop.render(f5);
/*     */     }
/*  70 */     this.Belly.render(f5);
/*  71 */     this.Leg1.render(f5);
/*  72 */     this.Leg2.render(f5);
/*  73 */     this.Leg3.render(f5);
/*  74 */     this.Leg4.render(f5);
/*  75 */     this.Head.render(f5);
/*  76 */     this.Tail.render(f5);
/*     */   }
/*     */
/*     */
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/*  81 */     if (this.upsidedown) {
/*  82 */       float f25 = this.swingProgress;
/*  83 */       float f26 = f25;
/*  84 */       if (f25 >= 0.8F) {
/*  85 */         f26 = 0.6F - f25 - 0.8F;
/*     */       }
/*  87 */       if (f26 > 1.6F) {
/*  88 */         f26 = 1.6F;
/*     */       }
/*  90 */       if (f26 < -1.6F) {
/*  91 */         f26 = -1.6F;
/*     */       }
/*  93 */       this.Leg1.rotateAngleX = 0.0F - f26;
/*  94 */       this.Leg2.rotateAngleX = 0.0F + f26;
/*  95 */       this.Leg3.rotateAngleX = 0.0F + f26;
/*  96 */       this.Leg4.rotateAngleX = 0.0F - f26;
/*  97 */       this.Tail.rotateAngleY = 0.0F - f26;
/*     */     }
/*  99 */     else if (this.turtleHat) {
/* 100 */       this.Leg1.rotateAngleX = 0.0F;
/* 101 */       this.Leg2.rotateAngleX = 0.0F;
/* 102 */       this.Leg3.rotateAngleX = 0.0F;
/* 103 */       this.Leg4.rotateAngleX = 0.0F;
/* 104 */       this.Tail.rotateAngleY = 0.0F;
/* 105 */     } else if (this.isSwimming) {
/* 106 */       float swimmLegs = MathHelper.cos(f * 0.5F) * 6.0F * f1;
/* 107 */       this.Leg1.rotateAngleX = -1.2F;
/* 108 */       this.Leg1.rotateAngleY = -1.2F + swimmLegs;
/* 109 */       this.Leg2.rotateAngleX = -1.2F;
/* 110 */       this.Leg2.rotateAngleY = 1.2F - swimmLegs;
/* 111 */       this.Leg3.rotateAngleX = 1.2F;
/* 112 */       this.Leg4.rotateAngleX = 1.2F;
/* 113 */       this.Tail.rotateAngleY = 0.0F;
/*     */     } else {
/* 115 */       this.Leg1.rotateAngleX = MathHelper.cos(f * 2.0F) * 2.0F * f1;
/* 116 */       this.Leg2.rotateAngleX = MathHelper.cos(f * 2.0F + 3.141593F) * 2.0F * f1;
/* 117 */       this.Leg3.rotateAngleX = MathHelper.cos(f * 2.0F + 3.141593F) * 2.0F * f1;
/* 118 */       this.Leg4.rotateAngleX = MathHelper.cos(f * 2.0F) * 2.0F * f1;
/* 119 */       this.Tail.rotateAngleY = MathHelper.cos(f * 0.6662F) * 0.7F * f1;
/*     */
/* 121 */       this.Leg1.rotateAngleY = 0.0F;
/* 122 */       this.Leg2.rotateAngleY = 0.0F;
/*     */     }
/*     */
/* 125 */     if (this.isHiding && !this.isSwimming) {
/* 126 */       this.Head.rotateAngleX = 0.0F;
/* 127 */       this.Head.rotateAngleY = 0.0F;
/*     */
/*     */
/* 130 */       this.Leg1.rotationPointX = 2.9F;
/* 131 */       this.Leg1.rotationPointY = 18.5F;
/* 132 */       this.Leg1.rotationPointZ = -2.9F;
/*     */
/*     */
/* 135 */       this.Leg2.rotationPointX = -2.9F;
/* 136 */       this.Leg2.rotationPointY = 18.5F;
/* 137 */       this.Leg2.rotationPointZ = -2.9F;
/*     */
/*     */
/* 140 */       this.Leg3.rotationPointX = 2.9F;
/* 141 */       this.Leg3.rotationPointY = 18.5F;
/* 142 */       this.Leg3.rotationPointZ = 2.9F;
/*     */
/*     */
/* 145 */       this.Leg4.rotationPointX = -2.9F;
/* 146 */       this.Leg4.rotationPointY = 18.5F;
/* 147 */       this.Leg4.rotationPointZ = 2.9F;
/*     */
/*     */
/*     */
/* 151 */       this.Head.rotationPointY = 19.5F;
/* 152 */       this.Head.rotationPointZ = -1.0F;
/*     */
/*     */
/*     */
/*     */
/* 157 */       this.Tail.rotationPointZ = 2.0F;
/*     */     } else {
/* 159 */       this.Head.rotateAngleX = f4 / 57.29578F;
/* 160 */       this.Head.rotateAngleY = f3 / 57.29578F;
/*     */
/*     */
/* 163 */       this.Leg1.rotationPointX = 3.5F;
/* 164 */       this.Leg1.rotationPointY = 20.0F;
/* 165 */       this.Leg1.rotationPointZ = -3.5F;
/*     */
/*     */
/* 168 */       this.Leg2.rotationPointX = -3.5F;
/* 169 */       this.Leg2.rotationPointY = 20.0F;
/* 170 */       this.Leg2.rotationPointZ = -3.5F;
/*     */
/*     */
/* 173 */       this.Leg3.rotationPointX = 3.5F;
/* 174 */       this.Leg3.rotationPointY = 20.0F;
/* 175 */       this.Leg3.rotationPointZ = 3.5F;
/*     */
/*     */
/* 178 */       this.Leg4.rotationPointX = -3.5F;
/* 179 */       this.Leg4.rotationPointY = 20.0F;
/* 180 */       this.Leg4.rotationPointZ = 3.5F;
/*     */
/*     */
/*     */
/* 184 */       this.Head.rotationPointY = 20.0F;
/* 185 */       this.Head.rotationPointZ = -4.5F;
/*     */
/*     */
/*     */
/*     */
/* 190 */       this.Tail.rotationPointZ = 4.0F;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelTurtle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
