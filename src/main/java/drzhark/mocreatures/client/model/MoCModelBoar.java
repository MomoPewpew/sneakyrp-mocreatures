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
/*     */ public class MoCModelBoar
/*     */   extends ModelBase {
/*     */   ModelRenderer Head;
/*     */   ModelRenderer Trout;
/*     */   ModelRenderer Tusks;
/*     */   ModelRenderer Jaw;
/*     */   ModelRenderer LeftEar;
/*     */   ModelRenderer RightEar;
/*     */   ModelRenderer HeadMane;
/*     */   ModelRenderer Body;
/*     */   ModelRenderer BodyMane;
/*     */   ModelRenderer Tail;
/*     */   ModelRenderer UpperLegRight;
/*     */   ModelRenderer LowerLegRight;
/*     */   ModelRenderer UpperLegLeft;
/*     */   ModelRenderer LowerLegLeft;
/*     */   ModelRenderer UpperHindLegRight;
/*     */   ModelRenderer LowerHindLegRight;
/*     */   ModelRenderer UpperHindLegLeft;
/*     */   ModelRenderer LowerHindLegLeft;
/*     */   
/*     */   public MoCModelBoar() {
/*  33 */     this.textureWidth = 64;
/*  34 */     this.textureHeight = 64;
/*     */     
/*  36 */     this.Head = new ModelRenderer(this, 0, 0);
/*  37 */     this.Head.addBox(-3.0F, 0.0F, -5.0F, 6, 6, 5);
/*  38 */     this.Head.setRotationPoint(0.0F, 11.0F, -5.0F);
/*  39 */     setRotation(this.Head, 0.2617994F, 0.0F, 0.0F);
/*     */     
/*  41 */     this.Trout = new ModelRenderer(this, 0, 11);
/*  42 */     this.Trout.addBox(-1.5F, 1.5F, -9.5F, 3, 3, 5);
/*  43 */     this.Trout.setRotationPoint(0.0F, 11.0F, -5.0F);
/*  44 */     setRotation(this.Trout, 0.3490659F, 0.0F, 0.0F);
/*     */     
/*  46 */     this.Tusks = new ModelRenderer(this, 0, 24);
/*  47 */     this.Tusks.addBox(-2.0F, 3.0F, -8.0F, 4, 2, 1);
/*  48 */     this.Tusks.setRotationPoint(0.0F, 11.0F, -5.0F);
/*  49 */     setRotation(this.Tusks, 0.3490659F, 0.0F, 0.0F);
/*     */     
/*  51 */     this.Jaw = new ModelRenderer(this, 0, 19);
/*  52 */     this.Jaw.addBox(-1.0F, 4.9F, -8.5F, 2, 1, 4);
/*  53 */     this.Jaw.setRotationPoint(0.0F, 11.0F, -5.0F);
/*  54 */     setRotation(this.Jaw, 0.2617994F, 0.0F, 0.0F);
/*     */     
/*  56 */     this.LeftEar = new ModelRenderer(this, 16, 11);
/*  57 */     this.LeftEar.addBox(1.0F, -4.0F, -2.0F, 2, 4, 2);
/*  58 */     this.LeftEar.setRotationPoint(0.0F, 11.0F, -5.0F);
/*  59 */     setRotation(this.LeftEar, 0.6981317F, 0.0F, 0.3490659F);
/*     */     
/*  61 */     this.RightEar = new ModelRenderer(this, 16, 17);
/*  62 */     this.RightEar.addBox(-3.0F, -4.0F, -2.0F, 2, 4, 2);
/*  63 */     this.RightEar.setRotationPoint(0.0F, 11.0F, -5.0F);
/*  64 */     setRotation(this.RightEar, 0.6981317F, 0.0F, -0.3490659F);
/*     */     
/*  66 */     this.HeadMane = new ModelRenderer(this, 23, 0);
/*  67 */     this.HeadMane.addBox(-1.0F, -2.0F, -5.0F, 2, 2, 5);
/*  68 */     this.HeadMane.setRotationPoint(0.0F, 11.0F, -5.0F);
/*  69 */     setRotation(this.HeadMane, 0.4363323F, 0.0F, 0.0F);
/*     */     
/*  71 */     this.Body = new ModelRenderer(this, 24, 0);
/*  72 */     this.Body.addBox(-3.5F, 0.0F, 0.0F, 7, 8, 13);
/*  73 */     this.Body.setRotationPoint(0.0F, 11.0F, -5.0F);
/*  74 */     setRotation(this.Body, -0.0872665F, 0.0F, 0.0F);
/*     */     
/*  76 */     this.BodyMane = new ModelRenderer(this, 0, 27);
/*  77 */     this.BodyMane.addBox(-1.0F, -2.0F, -1.0F, 2, 2, 9);
/*  78 */     this.BodyMane.setRotationPoint(0.0F, 11.3F, -4.0F);
/*  79 */     setRotation(this.BodyMane, -0.2617994F, 0.0F, 0.0F);
/*     */     
/*  81 */     this.Tail = new ModelRenderer(this, 60, 38);
/*  82 */     this.Tail.addBox(-0.5F, 0.0F, 0.0F, 1, 5, 1);
/*  83 */     this.Tail.setRotationPoint(0.0F, 13.0F, 7.5F);
/*  84 */     setRotation(this.Tail, 0.0872665F, 0.0F, 0.0F);
/*     */     
/*  86 */     this.UpperLegRight = new ModelRenderer(this, 32, 21);
/*  87 */     this.UpperLegRight.addBox(-1.0F, -2.0F, -2.0F, 1, 5, 3);
/*  88 */     this.UpperLegRight.setRotationPoint(-3.5F, 16.0F, -2.5F);
/*  89 */     setRotation(this.UpperLegRight, 0.1745329F, 0.0F, 0.0F);
/*     */     
/*  91 */     this.LowerLegRight = new ModelRenderer(this, 32, 29);
/*  92 */     this.LowerLegRight.addBox(-0.5F, 2.0F, -1.0F, 2, 6, 2);
/*  93 */     this.LowerLegRight.setRotationPoint(-3.5F, 16.0F, -2.5F);
/*     */     
/*  95 */     this.UpperLegLeft = new ModelRenderer(this, 24, 21);
/*  96 */     this.UpperLegLeft.addBox(0.0F, -2.0F, -2.0F, 1, 5, 3);
/*  97 */     this.UpperLegLeft.setRotationPoint(3.5F, 16.0F, -2.5F);
/*  98 */     setRotation(this.UpperLegLeft, 0.1745329F, 0.0F, 0.0F);
/*     */     
/* 100 */     this.LowerLegLeft = new ModelRenderer(this, 24, 29);
/* 101 */     this.LowerLegLeft.addBox(-1.5F, 2.0F, -1.0F, 2, 6, 2);
/* 102 */     this.LowerLegLeft.setRotationPoint(3.5F, 16.0F, -2.5F);
/*     */     
/* 104 */     this.UpperHindLegRight = new ModelRenderer(this, 44, 21);
/* 105 */     this.UpperHindLegRight.addBox(-1.5F, -2.0F, -2.0F, 1, 5, 4);
/* 106 */     this.UpperHindLegRight.setRotationPoint(-3.0F, 16.0F, 5.5F);
/* 107 */     setRotation(this.UpperHindLegRight, -0.2617994F, 0.0F, 0.0F);
/*     */     
/* 109 */     this.LowerHindLegRight = new ModelRenderer(this, 46, 30);
/* 110 */     this.LowerHindLegRight.addBox(-1.0F, 2.0F, 0.0F, 2, 6, 2);
/* 111 */     this.LowerHindLegRight.setRotationPoint(-3.0F, 16.0F, 5.5F);
/*     */     
/* 113 */     this.UpperHindLegLeft = new ModelRenderer(this, 54, 21);
/* 114 */     this.UpperHindLegLeft.addBox(0.5F, -2.0F, -2.0F, 1, 5, 4);
/* 115 */     this.UpperHindLegLeft.setRotationPoint(3.0F, 16.0F, 5.5F);
/* 116 */     setRotation(this.UpperHindLegLeft, -0.2617994F, 0.0F, 0.0F);
/*     */     
/* 118 */     this.LowerHindLegLeft = new ModelRenderer(this, 56, 30);
/* 119 */     this.LowerHindLegLeft.addBox(-1.0F, 2.0F, 0.0F, 2, 6, 2);
/* 120 */     this.LowerHindLegLeft.setRotationPoint(3.0F, 16.0F, 5.5F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 126 */     super.render(entity, f, f1, f2, f3, f4, f5);
/* 127 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/* 128 */     this.Head.render(f5);
/* 129 */     this.Trout.render(f5);
/* 130 */     this.Tusks.render(f5);
/* 131 */     this.Jaw.render(f5);
/* 132 */     this.LeftEar.render(f5);
/* 133 */     this.RightEar.render(f5);
/* 134 */     this.HeadMane.render(f5);
/* 135 */     this.Body.render(f5);
/* 136 */     this.BodyMane.render(f5);
/* 137 */     this.Tail.render(f5);
/* 138 */     this.UpperLegRight.render(f5);
/* 139 */     this.LowerLegRight.render(f5);
/* 140 */     this.UpperLegLeft.render(f5);
/* 141 */     this.LowerLegLeft.render(f5);
/* 142 */     this.UpperHindLegRight.render(f5);
/* 143 */     this.LowerHindLegRight.render(f5);
/* 144 */     this.UpperHindLegLeft.render(f5);
/* 145 */     this.LowerHindLegLeft.render(f5);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 149 */     model.rotateAngleX = x;
/* 150 */     model.rotateAngleY = y;
/* 151 */     model.rotateAngleZ = z;
/*     */   }
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 155 */     float XAngle = f4 / 57.29578F;
/* 156 */     float YAngle = f3 / 57.29578F;
/* 157 */     this.Head.rotateAngleX = 0.2617994F + XAngle;
/* 158 */     this.Head.rotateAngleY = YAngle;
/* 159 */     this.HeadMane.rotateAngleX = 0.4363323F + XAngle;
/* 160 */     this.HeadMane.rotateAngleY = YAngle;
/* 161 */     this.Trout.rotateAngleX = 0.3490659F + XAngle;
/* 162 */     this.Trout.rotateAngleY = YAngle;
/* 163 */     this.Jaw.rotateAngleX = 0.2617994F + XAngle;
/* 164 */     this.Jaw.rotateAngleY = YAngle;
/* 165 */     this.Tusks.rotateAngleX = 0.3490659F + XAngle;
/* 166 */     this.Tusks.rotateAngleY = YAngle;
/* 167 */     this.LeftEar.rotateAngleX = 0.6981317F + XAngle;
/* 168 */     this.LeftEar.rotateAngleY = YAngle;
/* 169 */     this.RightEar.rotateAngleX = 0.6981317F + XAngle;
/* 170 */     this.RightEar.rotateAngleY = YAngle;
/*     */     
/* 172 */     float LLegRotX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
/* 173 */     float RLegRotX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
/*     */     
/* 175 */     this.UpperLegLeft.rotateAngleX = LLegRotX;
/* 176 */     this.LowerLegLeft.rotateAngleX = LLegRotX;
/* 177 */     this.UpperHindLegRight.rotateAngleX = LLegRotX;
/* 178 */     this.LowerHindLegRight.rotateAngleX = LLegRotX;
/*     */     
/* 180 */     this.UpperLegRight.rotateAngleX = RLegRotX;
/* 181 */     this.LowerLegRight.rotateAngleX = RLegRotX;
/* 182 */     this.UpperHindLegLeft.rotateAngleX = RLegRotX;
/* 183 */     this.LowerHindLegLeft.rotateAngleX = RLegRotX;
/*     */     
/* 185 */     this.Tail.rotateAngleZ = LLegRotX * 0.2F;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelBoar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */