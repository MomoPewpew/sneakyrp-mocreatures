/*     */ package drzhark.mocreatures.client.model;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ 
/*     */ public class MoCModelEnt
/*     */   extends ModelBase {
/*     */   ModelRenderer Body;
/*     */   ModelRenderer LShoulder;
/*     */   ModelRenderer LArm;
/*     */   ModelRenderer LWrist;
/*     */   ModelRenderer LHand;
/*     */   ModelRenderer LFingers;
/*     */   ModelRenderer RShoulder;
/*     */   ModelRenderer RArm;
/*     */   ModelRenderer RWrist;
/*     */   ModelRenderer RHand;
/*     */   ModelRenderer RFingers;
/*     */   ModelRenderer LLeg;
/*     */   ModelRenderer LThigh;
/*     */   ModelRenderer LKnee;
/*     */   ModelRenderer LAnkle;
/*     */   ModelRenderer LFoot;
/*     */   ModelRenderer RLeg;
/*     */   ModelRenderer RThigh;
/*     */   ModelRenderer RKnee;
/*     */   ModelRenderer RAnkle;
/*     */   ModelRenderer RFoot;
/*     */   ModelRenderer Neck;
/*     */   ModelRenderer Face;
/*     */   ModelRenderer Head;
/*     */   ModelRenderer Nose;
/*     */   ModelRenderer Mouth;
/*     */   ModelRenderer TreeBase;
/*     */   ModelRenderer Leave1;
/*     */   ModelRenderer Leave2;
/*     */   ModelRenderer Leave3;
/*     */   ModelRenderer Leave4;
/*     */   ModelRenderer Leave5;
/*     */   ModelRenderer Leave6;
/*     */   ModelRenderer Leave7;
/*     */   ModelRenderer Leave8;
/*     */   ModelRenderer Leave9;
/*     */   ModelRenderer Leave10;
/*     */   ModelRenderer Leave11;
/*     */   ModelRenderer Leave12;
/*     */   ModelRenderer Leave13;
/*     */   ModelRenderer Leave14;
/*     */   ModelRenderer Leave15;
/*     */   ModelRenderer Leave16;
/*     */   
/*     */   public MoCModelEnt() {
/*  55 */     this.textureWidth = 128;
/*  56 */     this.textureHeight = 256;
/*     */     
/*  58 */     this.Body = new ModelRenderer(this, 68, 36);
/*  59 */     this.Body.addBox(-7.5F, -12.5F, -4.5F, 15, 25, 9);
/*  60 */     this.Body.setRotationPoint(0.0F, -31.0F, 0.0F);
/*  61 */     this.LShoulder = new ModelRenderer(this, 48, 108);
/*  62 */     this.LShoulder.addBox(6.0F, -14.0F, -4.8F, 9, 7, 7);
/*  63 */     this.LShoulder.setRotationPoint(0.0F, -31.0F, 0.0F);
/*  64 */     setRotation(this.LShoulder, 0.0F, 0.0F, -0.1745329F);
/*  65 */     this.LArm = new ModelRenderer(this, 80, 108);
/*  66 */     this.LArm.addBox(0.0F, -4.0F, -5.0F, 6, 24, 6);
/*  67 */     this.LArm.setRotationPoint(10.0F, -42.0F, 1.0F);
/*  68 */     setRotation(this.LArm, 0.0F, 0.0F, -0.1745329F);
/*  69 */     this.LWrist = new ModelRenderer(this, 0, 169);
/*  70 */     this.LWrist.addBox(2.0F, 17.0F, -6.0F, 8, 15, 8);
/*  71 */     this.LWrist.setRotationPoint(10.0F, -42.0F, 1.0F);
/*  72 */     this.LHand = new ModelRenderer(this, 88, 241);
/*  73 */     this.LHand.addBox(1.0F, 28.0F, -7.0F, 10, 5, 10);
/*  74 */     this.LHand.setRotationPoint(10.0F, -42.0F, 1.0F);
/*  75 */     this.LFingers = new ModelRenderer(this, 88, 176);
/*  76 */     this.LFingers.addBox(1.0F, 33.0F, -7.0F, 10, 15, 10);
/*  77 */     this.LFingers.setRotationPoint(10.0F, -42.0F, 1.0F);
/*  78 */     this.RShoulder = new ModelRenderer(this, 48, 122);
/*  79 */     this.RShoulder.addBox(-15.0F, -14.0F, -4.8F, 9, 7, 7);
/*  80 */     this.RShoulder.setRotationPoint(0.0F, -31.0F, 0.0F);
/*  81 */     setRotation(this.RShoulder, 0.0F, 0.0F, 0.1745329F);
/*  82 */     this.RArm = new ModelRenderer(this, 104, 108);
/*  83 */     this.RArm.addBox(-6.0F, -4.0F, -5.0F, 6, 24, 6);
/*  84 */     this.RArm.setRotationPoint(-10.0F, -42.0F, 1.0F);
/*  85 */     setRotation(this.RArm, 0.0F, 0.0F, 0.1745329F);
/*  86 */     this.RWrist = new ModelRenderer(this, 32, 169);
/*  87 */     this.RWrist.addBox(-10.0F, 17.0F, -6.0F, 8, 15, 8);
/*  88 */     this.RWrist.setRotationPoint(-10.0F, -42.0F, 1.0F);
/*  89 */     this.RHand = new ModelRenderer(this, 88, 226);
/*  90 */     this.RHand.addBox(-11.0F, 28.0F, -7.0F, 10, 5, 10);
/*  91 */     this.RHand.setRotationPoint(-10.0F, -42.0F, 1.0F);
/*  92 */     this.RFingers = new ModelRenderer(this, 88, 201);
/*  93 */     this.RFingers.addBox(-11.0F, 33.0F, -7.0F, 10, 15, 10);
/*  94 */     this.RFingers.setRotationPoint(-10.0F, -42.0F, 1.0F);
/*  95 */     this.LLeg = new ModelRenderer(this, 0, 90);
/*  96 */     this.LLeg.addBox(3.0F, 0.0F, -3.0F, 6, 20, 6);
/*  97 */     this.LLeg.setRotationPoint(0.0F, -21.0F, 0.0F);
/*  98 */     this.LThigh = new ModelRenderer(this, 24, 64);
/*  99 */     this.LThigh.addBox(2.5F, 4.0F, -3.5F, 7, 12, 7);
/* 100 */     this.LThigh.setRotationPoint(0.0F, -21.0F, 0.0F);
/* 101 */     this.LKnee = new ModelRenderer(this, 0, 0);
/* 102 */     this.LKnee.addBox(2.0F, 20.0F, -4.0F, 8, 24, 8);
/* 103 */     this.LKnee.setRotationPoint(0.0F, -21.0F, 0.0F);
/* 104 */     this.LAnkle = new ModelRenderer(this, 32, 29);
/* 105 */     this.LAnkle.addBox(1.5F, 25.0F, -4.5F, 9, 20, 9);
/* 106 */     this.LAnkle.setRotationPoint(0.0F, -21.0F, 0.0F);
/* 107 */     this.LFoot = new ModelRenderer(this, 0, 206);
/* 108 */     this.LFoot.addBox(1.5F, 38.0F, -23.5F, 9, 5, 9);
/* 109 */     this.LFoot.setRotationPoint(0.0F, -21.0F, 0.0F);
/* 110 */     setRotation(this.LFoot, 0.2617994F, 0.0F, 0.0F);
/* 111 */     this.RLeg = new ModelRenderer(this, 0, 64);
/* 112 */     this.RLeg.addBox(-9.0F, 0.0F, -3.0F, 6, 20, 6);
/* 113 */     this.RLeg.setRotationPoint(0.0F, -21.0F, 0.0F);
/* 114 */     this.RThigh = new ModelRenderer(this, 24, 83);
/* 115 */     this.RThigh.addBox(-9.5F, 4.0F, -3.5F, 7, 12, 7);
/* 116 */     this.RThigh.setRotationPoint(0.0F, -21.0F, 0.0F);
/* 117 */     this.RKnee = new ModelRenderer(this, 0, 32);
/* 118 */     this.RKnee.addBox(-10.0F, 20.0F, -4.0F, 8, 24, 8);
/* 119 */     this.RKnee.setRotationPoint(0.0F, -21.0F, 0.0F);
/* 120 */     this.RAnkle = new ModelRenderer(this, 32, 0);
/* 121 */     this.RAnkle.addBox(-10.5F, 25.0F, -4.5F, 9, 20, 9);
/* 122 */     this.RAnkle.setRotationPoint(0.0F, -21.0F, 0.0F);
/* 123 */     this.RFoot = new ModelRenderer(this, 0, 192);
/* 124 */     this.RFoot.addBox(-10.5F, 38.0F, -23.5F, 9, 5, 9);
/* 125 */     this.RFoot.setRotationPoint(0.0F, -21.0F, 0.0F);
/* 126 */     setRotation(this.RFoot, 0.2617994F, 0.0F, 0.0F);
/* 127 */     this.Neck = new ModelRenderer(this, 52, 90);
/* 128 */     this.Neck.addBox(-4.0F, -8.0F, -5.8F, 8, 10, 8);
/* 129 */     this.Neck.setRotationPoint(0.0F, -44.0F, 0.0F);
/* 130 */     setRotation(this.Neck, 0.5235988F, 0.0F, 0.0F);
/* 131 */     this.Face = new ModelRenderer(this, 52, 70);
/* 132 */     this.Face.addBox(-4.5F, -11.0F, -9.0F, 9, 7, 8);
/* 133 */     this.Face.setRotationPoint(0.0F, -44.0F, 0.0F);
/* 134 */     this.Head = new ModelRenderer(this, 84, 88);
/* 135 */     this.Head.addBox(-6.0F, -20.5F, -9.5F, 12, 10, 10);
/* 136 */     this.Head.setRotationPoint(0.0F, -44.0F, 0.0F);
/* 137 */     this.Nose = new ModelRenderer(this, 82, 88);
/* 138 */     this.Nose.addBox(-1.5F, -12.0F, -12.0F, 3, 7, 3);
/* 139 */     this.Nose.setRotationPoint(0.0F, -44.0F, 0.0F);
/* 140 */     setRotation(this.Nose, -0.122173F, 0.0F, 0.0F);
/* 141 */     this.Mouth = new ModelRenderer(this, 77, 36);
/* 142 */     this.Mouth.addBox(-3.0F, -8.0F, -6.8F, 6, 2, 1);
/* 143 */     this.Mouth.setRotationPoint(0.0F, -44.0F, 0.0F);
/* 144 */     setRotation(this.Mouth, 0.5235988F, 0.0F, 0.0F);
/* 145 */     this.TreeBase = new ModelRenderer(this, 0, 136);
/* 146 */     this.TreeBase.addBox(-10.0F, -31.5F, -11.5F, 20, 13, 20);
/* 147 */     this.TreeBase.setRotationPoint(0.0F, -44.0F, 0.0F);
/* 148 */     this.Leave1 = new ModelRenderer(this, 0, 224);
/* 149 */     this.Leave1.addBox(-16.0F, -45.0F, -17.0F, 16, 16, 16);
/* 150 */     this.Leave1.setRotationPoint(0.0F, -44.0F, 0.0F);
/* 151 */     this.Leave2 = new ModelRenderer(this, 0, 224);
/* 152 */     this.Leave2.addBox(0.0F, -45.0F, -17.0F, 16, 16, 16);
/* 153 */     this.Leave2.setRotationPoint(0.0F, -44.0F, 0.0F);
/* 154 */     this.Leave3 = new ModelRenderer(this, 0, 224);
/* 155 */     this.Leave3.addBox(0.0F, -45.0F, -1.0F, 16, 16, 16);
/* 156 */     this.Leave3.setRotationPoint(0.0F, -44.0F, 0.0F);
/* 157 */     this.Leave4 = new ModelRenderer(this, 0, 224);
/* 158 */     this.Leave4.addBox(-16.0F, -45.0F, -1.0F, 16, 16, 16);
/* 159 */     this.Leave4.setRotationPoint(0.0F, -44.0F, 0.0F);
/* 160 */     this.Leave5 = new ModelRenderer(this, 0, 224);
/* 161 */     this.Leave5.addBox(-16.0F, -45.0F, -33.0F, 16, 16, 16);
/* 162 */     this.Leave5.setRotationPoint(0.0F, -44.0F, 0.0F);
/* 163 */     this.Leave6 = new ModelRenderer(this, 0, 224);
/* 164 */     this.Leave6.addBox(0.0F, -45.0F, -33.0F, 16, 16, 16);
/* 165 */     this.Leave6.setRotationPoint(0.0F, -44.0F, 0.0F);
/* 166 */     this.Leave7 = new ModelRenderer(this, 0, 224);
/* 167 */     this.Leave7.addBox(16.0F, -45.0F, -17.0F, 16, 16, 16);
/* 168 */     this.Leave7.setRotationPoint(0.0F, -44.0F, 0.0F);
/* 169 */     this.Leave8 = new ModelRenderer(this, 0, 224);
/* 170 */     this.Leave8.addBox(16.0F, -45.0F, -1.0F, 16, 16, 16);
/* 171 */     this.Leave8.setRotationPoint(0.0F, -44.0F, 0.0F);
/* 172 */     this.Leave9 = new ModelRenderer(this, 0, 224);
/* 173 */     this.Leave9.addBox(0.0F, -45.0F, 15.0F, 16, 16, 16);
/* 174 */     this.Leave9.setRotationPoint(0.0F, -44.0F, 0.0F);
/* 175 */     this.Leave10 = new ModelRenderer(this, 0, 224);
/* 176 */     this.Leave10.addBox(-16.0F, -45.0F, 15.0F, 16, 16, 16);
/* 177 */     this.Leave10.setRotationPoint(0.0F, -44.0F, 0.0F);
/* 178 */     this.Leave11 = new ModelRenderer(this, 0, 224);
/* 179 */     this.Leave11.addBox(-32.0F, -45.0F, -1.0F, 16, 16, 16);
/* 180 */     this.Leave11.setRotationPoint(0.0F, -44.0F, 0.0F);
/* 181 */     this.Leave12 = new ModelRenderer(this, 0, 224);
/* 182 */     this.Leave12.addBox(-32.0F, -45.0F, -17.0F, 16, 16, 16);
/* 183 */     this.Leave12.setRotationPoint(0.0F, -44.0F, 0.0F);
/* 184 */     this.Leave13 = new ModelRenderer(this, 0, 224);
/* 185 */     this.Leave13.addBox(-16.0F, -61.0F, -17.0F, 16, 16, 16);
/* 186 */     this.Leave13.setRotationPoint(0.0F, -44.0F, 0.0F);
/* 187 */     this.Leave14 = new ModelRenderer(this, 0, 224);
/* 188 */     this.Leave14.addBox(0.0F, -61.0F, -17.0F, 16, 16, 16);
/* 189 */     this.Leave14.setRotationPoint(0.0F, -44.0F, 0.0F);
/* 190 */     this.Leave15 = new ModelRenderer(this, 0, 224);
/* 191 */     this.Leave15.addBox(0.0F, -61.0F, -1.0F, 16, 16, 16);
/* 192 */     this.Leave15.setRotationPoint(0.0F, -44.0F, 0.0F);
/* 193 */     this.Leave16 = new ModelRenderer(this, 0, 224);
/* 194 */     this.Leave16.addBox(-16.0F, -61.0F, -1.0F, 16, 16, 16);
/* 195 */     this.Leave16.setRotationPoint(0.0F, -44.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 201 */     super.render(entity, f, f1, f2, f3, f4, f5);
/* 202 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/* 203 */     this.Body.render(f5);
/* 204 */     this.LShoulder.render(f5);
/* 205 */     this.LArm.render(f5);
/* 206 */     this.LWrist.render(f5);
/* 207 */     this.LHand.render(f5);
/* 208 */     this.LFingers.render(f5);
/* 209 */     this.RShoulder.render(f5);
/* 210 */     this.RArm.render(f5);
/* 211 */     this.RWrist.render(f5);
/* 212 */     this.RHand.render(f5);
/* 213 */     this.RFingers.render(f5);
/* 214 */     this.LLeg.render(f5);
/* 215 */     this.LThigh.render(f5);
/* 216 */     this.LKnee.render(f5);
/* 217 */     this.LAnkle.render(f5);
/* 218 */     this.LFoot.render(f5);
/* 219 */     this.RLeg.render(f5);
/* 220 */     this.RThigh.render(f5);
/* 221 */     this.RKnee.render(f5);
/* 222 */     this.RAnkle.render(f5);
/* 223 */     this.RFoot.render(f5);
/* 224 */     this.Neck.render(f5);
/* 225 */     this.Face.render(f5);
/* 226 */     this.Head.render(f5);
/* 227 */     this.Nose.render(f5);
/* 228 */     this.Mouth.render(f5);
/* 229 */     this.TreeBase.render(f5);
/* 230 */     this.Leave1.render(f5);
/* 231 */     this.Leave2.render(f5);
/* 232 */     this.Leave3.render(f5);
/* 233 */     this.Leave4.render(f5);
/* 234 */     this.Leave5.render(f5);
/* 235 */     this.Leave6.render(f5);
/* 236 */     this.Leave7.render(f5);
/* 237 */     this.Leave8.render(f5);
/* 238 */     this.Leave9.render(f5);
/* 239 */     this.Leave10.render(f5);
/* 240 */     this.Leave11.render(f5);
/* 241 */     this.Leave12.render(f5);
/* 242 */     this.Leave13.render(f5);
/* 243 */     this.Leave14.render(f5);
/* 244 */     this.Leave15.render(f5);
/* 245 */     this.Leave16.render(f5);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 249 */     model.rotateAngleX = x;
/* 250 */     model.rotateAngleY = y;
/* 251 */     model.rotateAngleZ = z;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 256 */     float radianF = 57.29578F;
/*     */     
/* 258 */     float RArmXRot = MathHelper.cos(f * 0.6662F + 3.1415927F) * 2.0F * f1 * 0.5F;
/* 259 */     float LArmXRot = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
/* 260 */     float RLegXRot = MathHelper.cos(f * 0.6662F) * 1.0F * f1;
/* 261 */     float LLegXRot = MathHelper.cos(f * 0.6662F + 3.1415927F) * 1.0F * f1;
/*     */     
/* 263 */     this.LWrist.rotateAngleZ = MathHelper.cos(f2 * 0.09F) * 0.05F - 0.05F;
/* 264 */     this.LWrist.rotateAngleX = LArmXRot;
/* 265 */     this.RWrist.rotateAngleZ = -(MathHelper.cos(f2 * 0.09F) * 0.05F) + 0.05F;
/* 266 */     this.RWrist.rotateAngleX = RArmXRot;
/*     */     
/* 268 */     this.LArm.rotateAngleX = this.LWrist.rotateAngleX;
/* 269 */     this.LFingers.rotateAngleZ = this.LWrist.rotateAngleZ;
/* 270 */     this.LArm.rotateAngleZ = -10.0F / radianF + this.LWrist.rotateAngleZ;
/*     */     
/* 272 */     this.RArm.rotateAngleX = this.RWrist.rotateAngleX;
/* 273 */     this.RFingers.rotateAngleZ = this.RWrist.rotateAngleZ;
/* 274 */     this.RArm.rotateAngleZ = 10.0F / radianF + this.RWrist.rotateAngleZ;
/*     */     
/* 276 */     this.RLeg.rotateAngleX = RLegXRot;
/* 277 */     this.LLeg.rotateAngleX = LLegXRot;
/* 278 */     this.LAnkle.rotateAngleX = this.LLeg.rotateAngleX;
/* 279 */     this.RAnkle.rotateAngleX = this.RLeg.rotateAngleX;
/*     */     
/* 281 */     this.LFoot.rotateAngleX = 15.0F / radianF + this.LLeg.rotateAngleX;
/* 282 */     this.RFoot.rotateAngleX = 15.0F / radianF + this.RLeg.rotateAngleX;
/* 283 */     this.Neck.rotateAngleY = f3 / radianF;
/*     */ 
/*     */     
/* 286 */     this.TreeBase.rotateAngleY = this.Neck.rotateAngleY;
/*     */     
/* 288 */     this.Leave6.rotateAngleY = this.Neck.rotateAngleY;
/*     */ 
/*     */ 
/*     */     
/* 292 */     this.Leave11.rotateAngleY = this.Neck.rotateAngleY;
/*     */     
/* 294 */     this.Leave16.rotateAngleY = this.Neck.rotateAngleY;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelEnt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */