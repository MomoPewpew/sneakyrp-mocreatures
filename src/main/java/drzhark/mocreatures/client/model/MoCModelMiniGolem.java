/*     */ package drzhark.mocreatures.client.model;
/*     */ 
/*     */ import drzhark.mocreatures.entity.monster.MoCEntityMiniGolem;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ 
/*     */ public class MoCModelMiniGolem
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer Head;
/*     */   ModelRenderer HeadRed;
/*     */   ModelRenderer Body;
/*     */   ModelRenderer BodyRed;
/*     */   ModelRenderer LeftShoulder;
/*     */   ModelRenderer LeftArm;
/*     */   ModelRenderer LeftArmRingA;
/*     */   ModelRenderer LeftArmRingB;
/*     */   ModelRenderer RightShoulder;
/*     */   ModelRenderer RightArm;
/*     */   ModelRenderer RightArmRingA;
/*     */   ModelRenderer RightArmRingB;
/*     */   ModelRenderer RightLeg;
/*     */   ModelRenderer RightFoot;
/*     */   ModelRenderer LeftLeg;
/*     */   ModelRenderer LeftFoot;
/*  28 */   private float radianF = 57.29578F;
/*     */   
/*     */   public MoCModelMiniGolem() {
/*  31 */     this.textureWidth = 64;
/*  32 */     this.textureHeight = 64;
/*     */     
/*  34 */     this.Head = new ModelRenderer(this, 30, 0);
/*  35 */     this.Head.addBox(-3.0F, -3.0F, -3.0F, 6, 3, 6);
/*  36 */     this.Head.setRotationPoint(0.0F, 8.0F, 0.0F);
/*  37 */     setRotation(this.Head, 0.0F, -0.7853982F, 0.0F);
/*     */     
/*  39 */     this.HeadRed = new ModelRenderer(this, 30, 29);
/*  40 */     this.HeadRed.addBox(-3.0F, -3.0F, -3.0F, 6, 3, 6);
/*  41 */     this.HeadRed.setRotationPoint(0.0F, 8.0F, 0.0F);
/*  42 */     setRotation(this.HeadRed, 0.0F, -0.7853982F, 0.0F);
/*     */     
/*  44 */     this.Body = new ModelRenderer(this, 0, 0);
/*  45 */     this.Body.addBox(-5.0F, -10.0F, -5.0F, 10, 10, 10);
/*  46 */     this.Body.setRotationPoint(0.0F, 18.0F, 0.0F);
/*     */     
/*  48 */     this.BodyRed = new ModelRenderer(this, 0, 28);
/*  49 */     this.BodyRed.addBox(-5.0F, -10.0F, -5.0F, 10, 10, 10);
/*  50 */     this.BodyRed.setRotationPoint(0.0F, 18.0F, 0.0F);
/*     */     
/*  52 */     this.LeftShoulder = new ModelRenderer(this, 0, 4);
/*  53 */     this.LeftShoulder.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2);
/*  54 */     this.LeftShoulder.setRotationPoint(5.0F, 11.0F, 0.0F);
/*     */     
/*  56 */     this.LeftArm = new ModelRenderer(this, 0, 48);
/*  57 */     this.LeftArm.addBox(1.0F, -2.0F, -2.0F, 4, 12, 4);
/*  58 */     this.LeftArm.setRotationPoint(5.0F, 11.0F, 0.0F);
/*     */     
/*  60 */     this.LeftArmRingA = new ModelRenderer(this, 20, 20);
/*  61 */     this.LeftArmRingA.addBox(0.5F, 1.0F, -2.5F, 5, 3, 5);
/*  62 */     this.LeftArmRingA.setRotationPoint(5.0F, 11.0F, 0.0F);
/*     */     
/*  64 */     this.LeftArmRingB = new ModelRenderer(this, 20, 20);
/*  65 */     this.LeftArmRingB.addBox(0.5F, 5.0F, -2.5F, 5, 3, 5);
/*  66 */     this.LeftArmRingB.setRotationPoint(5.0F, 11.0F, 0.0F);
/*     */     
/*  68 */     this.RightShoulder = new ModelRenderer(this, 0, 0);
/*  69 */     this.RightShoulder.addBox(-1.0F, -1.0F, -1.0F, 1, 2, 2);
/*  70 */     this.RightShoulder.setRotationPoint(-5.0F, 11.0F, 0.0F);
/*     */     
/*  72 */     this.RightArm = new ModelRenderer(this, 16, 48);
/*  73 */     this.RightArm.addBox(-5.0F, -2.0F, -2.0F, 4, 12, 4);
/*  74 */     this.RightArm.setRotationPoint(-5.0F, 11.0F, 0.0F);
/*     */     
/*  76 */     this.RightArmRingA = new ModelRenderer(this, 0, 20);
/*  77 */     this.RightArmRingA.addBox(-5.5F, 1.0F, -2.5F, 5, 3, 5);
/*  78 */     this.RightArmRingA.setRotationPoint(-5.0F, 11.0F, 0.0F);
/*     */     
/*  80 */     this.RightArmRingB = new ModelRenderer(this, 0, 20);
/*  81 */     this.RightArmRingB.addBox(-5.5F, 5.0F, -2.5F, 5, 3, 5);
/*  82 */     this.RightArmRingB.setRotationPoint(-5.0F, 11.0F, 0.0F);
/*     */     
/*  84 */     this.RightLeg = new ModelRenderer(this, 40, 9);
/*  85 */     this.RightLeg.addBox(-2.5F, 0.0F, -2.0F, 4, 6, 4);
/*  86 */     this.RightLeg.setRotationPoint(-2.0F, 18.0F, 0.0F);
/*     */     
/*  88 */     this.RightFoot = new ModelRenderer(this, 15, 22);
/*  89 */     this.RightFoot.addBox(-2.5F, 5.0F, -3.0F, 4, 1, 1);
/*  90 */     this.RightFoot.setRotationPoint(-2.0F, 18.0F, 0.0F);
/*     */     
/*  92 */     this.LeftLeg = new ModelRenderer(this, 40, 19);
/*  93 */     this.LeftLeg.addBox(-1.5F, 0.0F, -2.0F, 4, 6, 4);
/*  94 */     this.LeftLeg.setRotationPoint(2.0F, 18.0F, 0.0F);
/*     */     
/*  96 */     this.LeftFoot = new ModelRenderer(this, 15, 20);
/*  97 */     this.LeftFoot.addBox(-1.5F, 5.0F, -3.0F, 4, 1, 1);
/*  98 */     this.LeftFoot.setRotationPoint(2.0F, 18.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 104 */     super.render(entity, f, f1, f2, f3, f4, f5);
/*     */     
/* 106 */     MoCEntityMiniGolem minigolem = (MoCEntityMiniGolem)entity;
/* 107 */     boolean angry = minigolem.getIsAngry();
/* 108 */     boolean hasRock = minigolem.getHasRock();
/*     */     
/* 110 */     setRotationAngles(f, f1, f2, f3, f4, f5, hasRock);
/*     */     
/* 112 */     if (angry) {
/* 113 */       this.HeadRed.render(f5);
/* 114 */       this.BodyRed.render(f5);
/*     */     } else {
/* 116 */       this.Head.render(f5);
/* 117 */       this.Body.render(f5);
/*     */     } 
/*     */     
/* 120 */     this.LeftShoulder.render(f5);
/* 121 */     this.LeftArm.render(f5);
/* 122 */     this.LeftArmRingA.render(f5);
/* 123 */     this.LeftArmRingB.render(f5);
/* 124 */     this.RightShoulder.render(f5);
/* 125 */     this.RightArm.render(f5);
/* 126 */     this.RightArmRingA.render(f5);
/* 127 */     this.RightArmRingB.render(f5);
/* 128 */     this.RightLeg.render(f5);
/* 129 */     this.RightFoot.render(f5);
/* 130 */     this.LeftLeg.render(f5);
/* 131 */     this.LeftFoot.render(f5);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 135 */     model.rotateAngleX = x;
/* 136 */     model.rotateAngleY = y;
/* 137 */     model.rotateAngleZ = z;
/*     */   }
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, boolean hasRock) {
/* 141 */     float hRotY = f3 / 57.29578F;
/* 142 */     float RLegXRot = MathHelper.cos(f * 0.6662F + 3.141593F) * 0.8F * f1;
/* 143 */     float LLegXRot = MathHelper.cos(f * 0.6662F) * 0.8F * f1;
/*     */     
/* 145 */     this.RightLeg.rotateAngleX = RLegXRot;
/* 146 */     this.RightFoot.rotateAngleX = RLegXRot;
/* 147 */     this.LeftLeg.rotateAngleX = LLegXRot;
/* 148 */     this.LeftFoot.rotateAngleX = LLegXRot;
/*     */     
/* 150 */     this.Head.rotateAngleY = -0.7853982F + hRotY;
/* 151 */     this.HeadRed.rotateAngleY = -0.7853982F + hRotY;
/*     */     
/* 153 */     if (hasRock) {
/* 154 */       this.LeftShoulder.rotateAngleZ = 0.0F;
/* 155 */       this.LeftShoulder.rotateAngleX = -180.0F / this.radianF;
/* 156 */       this.RightShoulder.rotateAngleZ = 0.0F;
/* 157 */       this.RightShoulder.rotateAngleX = -180.0F / this.radianF;
/*     */     } else {
/* 159 */       this.LeftShoulder.rotateAngleZ = MathHelper.cos(f2 * 0.09F) * 0.05F - 0.05F;
/* 160 */       this.LeftShoulder.rotateAngleX = RLegXRot;
/* 161 */       this.RightShoulder.rotateAngleZ = -(MathHelper.cos(f2 * 0.09F) * 0.05F) + 0.05F;
/* 162 */       this.RightShoulder.rotateAngleX = LLegXRot;
/*     */     } 
/*     */     
/* 165 */     this.RightArmRingB.rotateAngleX = this.RightShoulder.rotateAngleX;
/* 166 */     this.RightArmRingB.rotateAngleZ = this.RightShoulder.rotateAngleZ;
/*     */     
/* 168 */     this.LeftArmRingB.rotateAngleX = this.LeftShoulder.rotateAngleX;
/* 169 */     this.LeftArmRingB.rotateAngleZ = this.LeftShoulder.rotateAngleZ;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelMiniGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */