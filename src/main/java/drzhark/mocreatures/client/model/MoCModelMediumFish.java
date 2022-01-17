/*     */ package drzhark.mocreatures.client.model;
/*     */ 
/*     */ import drzhark.mocreatures.entity.aquatic.MoCEntityMediumFish;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class MoCModelMediumFish
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer Head;
/*     */   ModelRenderer LowerHead;
/*     */   ModelRenderer Nose;
/*     */   ModelRenderer MouthBottom;
/*     */   ModelRenderer MouthBottomB;
/*     */   ModelRenderer Body;
/*     */   ModelRenderer BackUp;
/*     */   ModelRenderer BackDown;
/*     */   ModelRenderer Tail;
/*     */   ModelRenderer TailFin;
/*     */   ModelRenderer RightPectoralFin;
/*     */   ModelRenderer LeftPectoralFin;
/*     */   ModelRenderer UpperFin;
/*     */   ModelRenderer LowerFin;
/*     */   ModelRenderer RightLowerFin;
/*     */   ModelRenderer LeftLowerFin;
/*     */   
/*     */   public MoCModelMediumFish() {
/*  31 */     this.textureWidth = 64;
/*  32 */     this.textureHeight = 32;
/*     */     
/*  34 */     this.Head = new ModelRenderer(this, 0, 10);
/*  35 */     this.Head.addBox(-5.0F, 0.0F, -1.5F, 5, 3, 3);
/*  36 */     this.Head.setRotationPoint(-8.0F, 6.0F, 0.0F);
/*  37 */     setRotation(this.Head, 0.0F, 0.0F, -0.4461433F);
/*     */     
/*  39 */     this.LowerHead = new ModelRenderer(this, 0, 16);
/*  40 */     this.LowerHead.addBox(-4.0F, -3.0F, -1.5F, 4, 3, 3);
/*  41 */     this.LowerHead.setRotationPoint(-8.0F, 12.0F, 0.0F);
/*  42 */     setRotation(this.LowerHead, 0.0F, 0.0F, 0.3346075F);
/*     */     
/*  44 */     this.Nose = new ModelRenderer(this, 14, 17);
/*  45 */     this.Nose.addBox(-1.0F, -1.0F, -1.0F, 1, 3, 2);
/*  46 */     this.Nose.setRotationPoint(-11.0F, 8.2F, 0.0F);
/*  47 */     setRotation(this.Nose, 0.0F, 0.0F, 1.412787F);
/*     */     
/*  49 */     this.MouthBottom = new ModelRenderer(this, 16, 10);
/*  50 */     this.MouthBottom.addBox(-2.0F, -0.4F, -1.0F, 2, 1, 2);
/*  51 */     this.MouthBottom.setRotationPoint(-11.5F, 10.0F, 0.0F);
/*  52 */     setRotation(this.MouthBottom, 0.0F, 0.0F, 0.3346075F);
/*     */     
/*  54 */     this.MouthBottomB = new ModelRenderer(this, 16, 13);
/*  55 */     this.MouthBottomB.addBox(-1.5F, -2.4F, -0.5F, 1, 1, 1);
/*  56 */     this.MouthBottomB.setRotationPoint(-11.5F, 10.0F, 0.0F);
/*  57 */     setRotation(this.MouthBottomB, 0.0F, 0.0F, -0.7132579F);
/*     */     
/*  59 */     this.Body = new ModelRenderer(this, 0, 0);
/*  60 */     this.Body.addBox(0.0F, -3.0F, -2.0F, 9, 6, 4);
/*  61 */     this.Body.setRotationPoint(-8.0F, 9.0F, 0.0F);
/*     */     
/*  63 */     this.BackUp = new ModelRenderer(this, 26, 0);
/*  64 */     this.BackUp.addBox(0.0F, 0.0F, -1.5F, 8, 3, 3);
/*  65 */     this.BackUp.setRotationPoint(1.0F, 6.0F, 0.0F);
/*  66 */     setRotation(this.BackUp, 0.0F, 0.0F, 0.1858931F);
/*     */     
/*  68 */     this.BackDown = new ModelRenderer(this, 26, 6);
/*  69 */     this.BackDown.addBox(0.0F, -3.0F, -1.5F, 8, 3, 3);
/*  70 */     this.BackDown.setRotationPoint(1.0F, 12.0F, 0.0F);
/*  71 */     setRotation(this.BackDown, 0.0F, 0.0F, -0.1919862F);
/*     */     
/*  73 */     this.Tail = new ModelRenderer(this, 48, 0);
/*  74 */     this.Tail.addBox(0.0F, -1.5F, -1.0F, 4, 3, 2);
/*  75 */     this.Tail.setRotationPoint(8.0F, 9.0F, 0.0F);
/*     */     
/*  77 */     this.TailFin = new ModelRenderer(this, 48, 5);
/*  78 */     this.TailFin.addBox(3.0F, -5.3F, 0.0F, 5, 11, 0);
/*  79 */     this.TailFin.setRotationPoint(8.0F, 9.0F, 0.0F);
/*     */     
/*  81 */     this.RightPectoralFin = new ModelRenderer(this, 28, 12);
/*  82 */     this.RightPectoralFin.addBox(0.0F, -2.0F, 0.0F, 5, 4, 0);
/*  83 */     this.RightPectoralFin.setRotationPoint(-6.5F, 10.0F, 2.0F);
/*  84 */     setRotation(this.RightPectoralFin, 0.0F, -0.8726646F, 0.185895F);
/*     */     
/*  86 */     this.LeftPectoralFin = new ModelRenderer(this, 38, 12);
/*  87 */     this.LeftPectoralFin.addBox(0.0F, -2.0F, 0.0F, 5, 4, 0);
/*  88 */     this.LeftPectoralFin.setRotationPoint(-6.5F, 10.0F, -2.0F);
/*  89 */     setRotation(this.LeftPectoralFin, 0.0F, 0.8726646F, 0.1858931F);
/*     */     
/*  91 */     this.UpperFin = new ModelRenderer(this, 0, 22);
/*  92 */     this.UpperFin.addBox(0.0F, -4.0F, 0.0F, 15, 4, 0);
/*  93 */     this.UpperFin.setRotationPoint(-7.0F, 6.0F, 0.0F);
/*  94 */     setRotation(this.UpperFin, 0.0F, 0.0F, 0.1047198F);
/*     */     
/*  96 */     this.LowerFin = new ModelRenderer(this, 46, 20);
/*  97 */     this.LowerFin.addBox(0.0F, 0.0F, 0.0F, 9, 4, 0);
/*  98 */     this.LowerFin.setRotationPoint(0.0F, 12.0F, 0.0F);
/*  99 */     setRotation(this.LowerFin, 0.0F, 0.0F, -0.1858931F);
/*     */     
/* 101 */     this.RightLowerFin = new ModelRenderer(this, 28, 16);
/* 102 */     this.RightLowerFin.addBox(0.0F, 0.0F, 0.0F, 9, 4, 0);
/* 103 */     this.RightLowerFin.setRotationPoint(-7.0F, 12.0F, 1.0F);
/* 104 */     setRotation(this.RightLowerFin, 0.5235988F, 0.0F, 0.0F);
/*     */     
/* 106 */     this.LeftLowerFin = new ModelRenderer(this, 46, 16);
/* 107 */     this.LeftLowerFin.addBox(0.0F, 0.0F, 0.0F, 9, 4, 0);
/* 108 */     this.LeftLowerFin.setRotationPoint(-7.0F, 12.0F, -1.0F);
/* 109 */     setRotation(this.LeftLowerFin, -0.5235988F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 114 */     super.render(entity, f, f1, f2, f3, f4, f5);
/* 115 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/* 116 */     MoCEntityMediumFish mediumFish = (MoCEntityMediumFish)entity;
/* 117 */     float yOffset = mediumFish.getAdjustedYOffset();
/* 118 */     float xOffset = mediumFish.getAdjustedXOffset();
/* 119 */     float zOffset = mediumFish.getAdjustedZOffset();
/* 120 */     GL11.glPushMatrix();
/* 121 */     GL11.glTranslatef(xOffset, yOffset, zOffset);
/* 122 */     this.Head.render(f5);
/* 123 */     this.LowerHead.render(f5);
/* 124 */     this.Nose.render(f5);
/* 125 */     this.MouthBottom.render(f5);
/* 126 */     this.MouthBottomB.render(f5);
/* 127 */     this.Body.render(f5);
/* 128 */     this.BackUp.render(f5);
/* 129 */     this.BackDown.render(f5);
/* 130 */     this.Tail.render(f5);
/* 131 */     this.TailFin.render(f5);
/* 132 */     this.RightPectoralFin.render(f5);
/* 133 */     this.LeftPectoralFin.render(f5);
/* 134 */     this.UpperFin.render(f5);
/* 135 */     this.LowerFin.render(f5);
/* 136 */     this.RightLowerFin.render(f5);
/* 137 */     this.LeftLowerFin.render(f5);
/* 138 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 142 */     model.rotateAngleX = x;
/* 143 */     model.rotateAngleY = y;
/* 144 */     model.rotateAngleZ = z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 152 */     float tailMov = MathHelper.cos(f * 0.6662F) * f1 * 0.6F;
/* 153 */     float finMov = MathHelper.cos(f2 * 0.2F) * 0.4F;
/* 154 */     float mouthMov = MathHelper.cos(f2 * 0.3F) * 0.2F;
/*     */     
/* 156 */     this.Tail.rotateAngleY = tailMov;
/* 157 */     this.TailFin.rotateAngleY = tailMov;
/*     */     
/* 159 */     this.LeftPectoralFin.rotateAngleY = 0.8726646F + finMov;
/* 160 */     this.RightPectoralFin.rotateAngleY = -0.8726646F - finMov;
/*     */     
/* 162 */     this.MouthBottom.rotateAngleZ = 0.3346075F + mouthMov;
/* 163 */     this.MouthBottomB.rotateAngleZ = -0.7132579F + mouthMov;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelMediumFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */