/*     */ package drzhark.mocreatures.client.model;
/*     */ 
/*     */ import drzhark.mocreatures.entity.monster.MoCEntitySilverSkeleton;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class MoCModelSilverSkeleton
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer Head;
/*     */   ModelRenderer Body;
/*     */   ModelRenderer Back;
/*     */   ModelRenderer RightArm;
/*     */   ModelRenderer RightHand;
/*     */   ModelRenderer RightSwordA;
/*     */   ModelRenderer RightSwordB;
/*     */   ModelRenderer RightSwordC;
/*     */   ModelRenderer LeftArm;
/*     */   ModelRenderer LeftHand;
/*     */   ModelRenderer LeftSwordA;
/*     */   ModelRenderer LeftSwordB;
/*     */   ModelRenderer LeftSwordC;
/*     */   ModelRenderer RightThigh;
/*     */   ModelRenderer RightKnee;
/*     */   ModelRenderer RightLeg;
/*     */   ModelRenderer RightFoot;
/*     */   ModelRenderer LeftThigh;
/*     */   ModelRenderer LeftKnee;
/*     */   ModelRenderer LeftLeg;
/*     */   ModelRenderer LeftFoot;
/*     */   private int leftAttack;
/*     */   private int rightAttack;
/*     */   private boolean riding;
/*  37 */   private float radianF = 57.29578F;
/*     */   
/*     */   public MoCModelSilverSkeleton() {
/*  40 */     this.textureWidth = 64;
/*  41 */     this.textureHeight = 64;
/*     */     
/*  43 */     this.Head = new ModelRenderer(this, 0, 0);
/*  44 */     this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
/*  45 */     this.Head.setRotationPoint(0.0F, -2.0F, 0.0F);
/*     */     
/*  47 */     this.Body = new ModelRenderer(this, 32, 0);
/*  48 */     this.Body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4);
/*  49 */     this.Body.setRotationPoint(0.0F, -2.0F, 0.0F);
/*     */     
/*  51 */     this.Back = new ModelRenderer(this, 44, 54);
/*  52 */     this.Back.addBox(-4.0F, -4.0F, 0.5F, 8, 8, 2);
/*  53 */     this.Back.setRotationPoint(0.0F, 2.0F, 2.0F);
/*  54 */     setRotation(this.Back, -0.1570796F, 0.0F, 0.0F);
/*     */     
/*  56 */     this.RightArm = new ModelRenderer(this, 48, 31);
/*  57 */     this.RightArm.addBox(-3.0F, -2.5F, -2.5F, 4, 11, 4);
/*  58 */     this.RightArm.setRotationPoint(-5.0F, 1.0F, 0.0F);
/*     */     
/*  60 */     this.RightHand = new ModelRenderer(this, 24, 16);
/*  61 */     this.RightHand.addBox(-2.5F, -2.0F, -2.0F, 3, 12, 3);
/*  62 */     this.RightHand.setRotationPoint(-5.0F, 1.0F, 0.0F);
/*     */     
/*  64 */     this.RightSwordA = new ModelRenderer(this, 52, 46);
/*  65 */     this.RightSwordA.addBox(-1.5F, 8.5F, -3.0F, 1, 1, 5);
/*  66 */     this.RightSwordA.setRotationPoint(-5.0F, 1.0F, 0.0F);
/*     */     
/*  68 */     this.RightSwordB = new ModelRenderer(this, 48, 50);
/*  69 */     this.RightSwordB.addBox(-1.5F, 7.5F, -4.0F, 1, 3, 1);
/*  70 */     this.RightSwordB.setRotationPoint(-5.0F, 1.0F, 0.0F);
/*     */     
/*  72 */     this.RightSwordC = new ModelRenderer(this, 28, 28);
/*  73 */     this.RightSwordC.addBox(-1.0F, 7.5F, -14.0F, 0, 3, 10);
/*  74 */     this.RightSwordC.setRotationPoint(-5.0F, 1.0F, 0.0F);
/*     */     
/*  76 */     this.LeftArm = new ModelRenderer(this, 48, 16);
/*  77 */     this.LeftArm.addBox(-1.0F, -2.5F, -2.5F, 4, 11, 4);
/*  78 */     this.LeftArm.setRotationPoint(5.0F, 1.0F, 0.0F);
/*     */     
/*  80 */     this.LeftHand = new ModelRenderer(this, 36, 16);
/*  81 */     this.LeftHand.addBox(-0.5F, -2.0F, -2.0F, 3, 12, 3);
/*  82 */     this.LeftHand.setRotationPoint(5.0F, 1.0F, 0.0F);
/*     */     
/*  84 */     this.LeftSwordA = new ModelRenderer(this, 52, 46);
/*  85 */     this.LeftSwordA.addBox(0.5F, 8.5F, -3.0F, 1, 1, 5);
/*  86 */     this.LeftSwordA.setRotationPoint(5.0F, 1.0F, 0.0F);
/*     */     
/*  88 */     this.LeftSwordB = new ModelRenderer(this, 48, 46);
/*  89 */     this.LeftSwordB.addBox(0.5F, 7.5F, -4.0F, 1, 3, 1);
/*  90 */     this.LeftSwordB.setRotationPoint(5.0F, 1.0F, 0.0F);
/*     */     
/*  92 */     this.LeftSwordC = new ModelRenderer(this, 28, 31);
/*  93 */     this.LeftSwordC.addBox(1.0F, 7.5F, -14.0F, 0, 3, 10);
/*  94 */     this.LeftSwordC.setRotationPoint(5.0F, 1.0F, 0.0F);
/*     */     
/*  96 */     this.RightThigh = new ModelRenderer(this, 0, 16);
/*  97 */     this.RightThigh.addBox(-1.5F, 0.0F, -1.5F, 3, 6, 3);
/*  98 */     this.RightThigh.setRotationPoint(-2.0F, 10.5F, 0.0F);
/*     */     
/* 100 */     this.RightKnee = new ModelRenderer(this, 0, 46);
/* 101 */     this.RightKnee.addBox(-2.0F, 1.0F, -2.0F, 4, 4, 4);
/* 102 */     this.RightKnee.setRotationPoint(-2.0F, 10.5F, 0.0F);
/*     */     
/* 104 */     this.RightLeg = new ModelRenderer(this, 0, 25);
/* 105 */     this.RightLeg.addBox(-1.5F, 0.0F, -1.5F, 3, 6, 3);
/* 106 */     this.RightLeg.setRotationPoint(0.0F, 6.0F, 0.0F);
/* 107 */     this.RightThigh.addChild(this.RightLeg);
/*     */     
/* 109 */     this.RightFoot = new ModelRenderer(this, 0, 54);
/* 110 */     this.RightFoot.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
/* 111 */     this.RightFoot.setRotationPoint(0.0F, 2.0F, 0.0F);
/* 112 */     this.RightLeg.addChild(this.RightFoot);
/*     */     
/* 114 */     this.LeftThigh = new ModelRenderer(this, 12, 16);
/* 115 */     this.LeftThigh.addBox(-1.5F, 0.0F, -1.5F, 3, 6, 3);
/* 116 */     this.LeftThigh.setRotationPoint(2.0F, 10.5F, 0.0F);
/*     */     
/* 118 */     this.LeftKnee = new ModelRenderer(this, 16, 46);
/* 119 */     this.LeftKnee.addBox(-2.0F, 1.0F, -2.0F, 4, 4, 4);
/* 120 */     this.LeftKnee.setRotationPoint(2.0F, 10.5F, 0.0F);
/*     */     
/* 122 */     this.LeftLeg = new ModelRenderer(this, 12, 25);
/* 123 */     this.LeftLeg.addBox(-1.5F, 0.0F, -1.5F, 3, 6, 3);
/* 124 */     this.LeftLeg.setRotationPoint(0.0F, 6.0F, 0.0F);
/* 125 */     this.LeftThigh.addChild(this.LeftLeg);
/*     */     
/* 127 */     this.LeftFoot = new ModelRenderer(this, 16, 54);
/* 128 */     this.LeftFoot.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
/* 129 */     this.LeftFoot.setRotationPoint(0.0F, 2.0F, 0.0F);
/* 130 */     this.LeftLeg.addChild(this.LeftFoot);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 135 */     super.render(entity, f, f1, f2, f3, f4, f5);
/* 136 */     MoCEntitySilverSkeleton samurai = (MoCEntitySilverSkeleton)entity;
/* 137 */     boolean sprinting = samurai.isSprinting();
/* 138 */     this.leftAttack = samurai.attackCounterLeft;
/* 139 */     this.rightAttack = samurai.attackCounterRight;
/* 140 */     this.riding = (samurai.getRidingEntity() != null);
/* 141 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/* 142 */     GL11.glPushMatrix();
/* 143 */     if (sprinting && f1 > 0.3F)
/*     */     {
/* 145 */       GL11.glRotatef((float)(f1 * -20.0D), -1.0F, 0.0F, 0.0F);
/*     */     }
/*     */ 
/*     */     
/* 149 */     if (this.riding)
/*     */     {
/* 151 */       GL11.glTranslatef(0.0F, 0.5F, 0.0F);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 157 */     renderParts(f5);
/* 158 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   private void renderParts(float f5) {
/* 162 */     this.Head.render(f5);
/* 163 */     this.Body.render(f5);
/* 164 */     this.Back.render(f5);
/* 165 */     this.RightArm.render(f5);
/* 166 */     this.RightHand.render(f5);
/* 167 */     this.RightSwordA.render(f5);
/* 168 */     this.RightSwordB.render(f5);
/* 169 */     this.RightSwordC.render(f5);
/* 170 */     this.LeftArm.render(f5);
/* 171 */     this.LeftHand.render(f5);
/* 172 */     this.LeftSwordA.render(f5);
/* 173 */     this.LeftSwordB.render(f5);
/* 174 */     this.LeftSwordC.render(f5);
/* 175 */     this.RightThigh.render(f5);
/* 176 */     this.RightKnee.render(f5);
/* 177 */     this.LeftThigh.render(f5);
/* 178 */     this.LeftKnee.render(f5);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 182 */     model.rotateAngleX = x;
/* 183 */     model.rotateAngleY = y;
/* 184 */     model.rotateAngleZ = z;
/*     */   }
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 188 */     float hRotY = f3 / 57.29578F;
/* 189 */     float hRotX = f4 / 57.29578F;
/*     */     
/* 191 */     this.Head.rotateAngleX = hRotX;
/* 192 */     this.Head.rotateAngleY = hRotY;
/*     */     
/* 194 */     float RLegXRot = MathHelper.cos(f * 0.6662F + 3.141593F) * 0.8F * f1;
/* 195 */     float LLegXRot = MathHelper.cos(f * 0.6662F) * 0.8F * f1;
/*     */ 
/*     */     
/* 198 */     float RLegXRotB = RLegXRot;
/* 199 */     float LLegXRotB = LLegXRot;
/*     */     
/* 201 */     if (this.leftAttack == 0) {
/* 202 */       this.LeftArm.rotateAngleZ = MathHelper.cos(f2 * 0.09F) * 0.05F - 0.05F;
/* 203 */       this.LeftArm.rotateAngleX = RLegXRot;
/*     */     } else {
/* 205 */       float armMov = -(MathHelper.cos(this.leftAttack * 0.18F) * 3.0F);
/* 206 */       this.LeftArm.rotateAngleX = armMov;
/*     */     } 
/*     */     
/* 209 */     if (this.rightAttack == 0) {
/* 210 */       this.RightArm.rotateAngleZ = -(MathHelper.cos(f2 * 0.09F) * 0.05F) + 0.05F;
/* 211 */       this.RightArm.rotateAngleX = LLegXRot;
/*     */     } else {
/*     */       
/* 214 */       float armMov = -(MathHelper.cos(this.rightAttack * 0.18F) * 3.0F);
/* 215 */       this.RightArm.rotateAngleX = armMov;
/*     */     } 
/*     */     
/* 218 */     this.LeftSwordC.rotateAngleX = this.LeftArm.rotateAngleX;
/*     */     
/* 220 */     this.LeftSwordC.rotateAngleZ = this.LeftArm.rotateAngleZ;
/*     */ 
/*     */     
/* 223 */     this.RightSwordC.rotateAngleX = this.RightArm.rotateAngleX;
/*     */     
/* 225 */     this.RightSwordC.rotateAngleZ = this.RightArm.rotateAngleZ;
/*     */ 
/*     */     
/* 228 */     if (this.riding) {
/* 229 */       this.RightLeg.rotateAngleX = 0.0F;
/*     */       
/* 231 */       this.RightThigh.rotateAngleX = -60.0F / this.radianF;
/* 232 */       this.RightThigh.rotateAngleY = 20.0F / this.radianF;
/* 233 */       this.RightKnee.rotateAngleY = 20.0F / this.radianF;
/* 234 */       this.RightKnee.rotateAngleX = -60.0F / this.radianF;
/* 235 */       this.LeftLeg.rotateAngleX = 0.0F;
/* 236 */       this.LeftThigh.rotateAngleY = -20.0F / this.radianF;
/* 237 */       this.LeftKnee.rotateAngleY = -20.0F / this.radianF;
/* 238 */       this.LeftThigh.rotateAngleX = -60.0F / this.radianF;
/* 239 */       this.LeftKnee.rotateAngleX = -60.0F / this.radianF;
/*     */     } else {
/* 241 */       this.RightThigh.rotateAngleY = 0.0F;
/* 242 */       this.RightKnee.rotateAngleY = 0.0F;
/* 243 */       this.LeftThigh.rotateAngleY = 0.0F;
/* 244 */       this.LeftKnee.rotateAngleY = 0.0F;
/* 245 */       this.RightThigh.rotateAngleX = RLegXRot;
/* 246 */       this.LeftThigh.rotateAngleX = LLegXRot;
/* 247 */       this.RightKnee.rotateAngleX = this.RightThigh.rotateAngleX;
/* 248 */       this.LeftKnee.rotateAngleX = this.LeftThigh.rotateAngleX;
/*     */       
/* 250 */       float RLegXRot2 = MathHelper.cos((f + 0.1F) * 0.6662F + 3.141593F) * 0.8F * f1;
/* 251 */       float LLegXRot2 = MathHelper.cos((f + 0.1F) * 0.6662F) * 0.8F * f1;
/*     */       
/* 253 */       if (f1 > 0.15F) {
/* 254 */         if (RLegXRot > RLegXRot2)
/*     */         {
/* 256 */           RLegXRotB = RLegXRot + 0.43633232F;
/*     */         }
/*     */ 
/*     */         
/* 260 */         if (LLegXRot > LLegXRot2)
/*     */         {
/* 262 */           LLegXRotB = LLegXRot + 0.43633232F;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 267 */       this.RightLeg.rotateAngleX = LLegXRotB;
/* 268 */       this.LeftLeg.rotateAngleX = RLegXRotB;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelSilverSkeleton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */