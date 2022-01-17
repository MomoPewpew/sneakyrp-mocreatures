/*     */ package drzhark.mocreatures.client.model;
/*     */ 
/*     */ import drzhark.mocreatures.entity.ambient.MoCEntityCricket;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MoCModelCricket
/*     */   extends ModelBase {
/*     */   ModelRenderer Head;
/*     */   ModelRenderer Antenna;
/*     */   ModelRenderer AntennaB;
/*     */   ModelRenderer Thorax;
/*     */   ModelRenderer Abdomen;
/*     */   ModelRenderer TailA;
/*     */   ModelRenderer TailB;
/*     */   ModelRenderer FrontLegs;
/*     */   ModelRenderer MidLegs;
/*     */   ModelRenderer ThighLeft;
/*     */   ModelRenderer ThighLeftB;
/*     */   ModelRenderer ThighRight;
/*     */   ModelRenderer ThighRightB;
/*     */   ModelRenderer LegLeft;
/*     */   ModelRenderer LegLeftB;
/*     */   ModelRenderer LegRight;
/*     */   ModelRenderer LegRightB;
/*     */   ModelRenderer LeftWing;
/*     */   ModelRenderer RightWing;
/*     */   ModelRenderer FoldedWings;
/*     */   
/*     */   public MoCModelCricket() {
/*  37 */     this.textureWidth = 32;
/*  38 */     this.textureHeight = 32;
/*     */     
/*  40 */     this.Head = new ModelRenderer(this, 0, 4);
/*  41 */     this.Head.addBox(-0.5F, 0.0F, -1.0F, 1, 1, 2);
/*  42 */     this.Head.setRotationPoint(0.0F, 22.5F, -2.0F);
/*  43 */     setRotation(this.Head, -2.171231F, 0.0F, 0.0F);
/*     */     
/*  45 */     this.Antenna = new ModelRenderer(this, 0, 11);
/*  46 */     this.Antenna.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 0);
/*  47 */     this.Antenna.setRotationPoint(0.0F, 22.5F, -3.0F);
/*  48 */     setRotation(this.Antenna, -2.736346F, 0.0F, 0.0F);
/*     */     
/*  50 */     this.AntennaB = new ModelRenderer(this, 0, 9);
/*  51 */     this.AntennaB.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 0);
/*  52 */     this.AntennaB.setRotationPoint(0.0F, 20.7F, -3.8F);
/*  53 */     setRotation(this.AntennaB, 2.88506F, 0.0F, 0.0F);
/*     */     
/*  55 */     this.Thorax = new ModelRenderer(this, 0, 0);
/*  56 */     this.Thorax.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);
/*  57 */     this.Thorax.setRotationPoint(0.0F, 21.0F, -1.0F);
/*  58 */     setRotation(this.Thorax, 0.0F, 0.0F, 0.0F);
/*     */     
/*  60 */     this.Abdomen = new ModelRenderer(this, 8, 0);
/*  61 */     this.Abdomen.addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2);
/*  62 */     this.Abdomen.setRotationPoint(0.0F, 22.0F, 0.0F);
/*  63 */     setRotation(this.Abdomen, 1.427659F, 0.0F, 0.0F);
/*     */     
/*  65 */     this.TailA = new ModelRenderer(this, 4, 9);
/*  66 */     this.TailA.addBox(-1.0F, 0.0F, 0.0F, 2, 3, 0);
/*  67 */     this.TailA.setRotationPoint(0.0F, 22.0F, 2.8F);
/*  68 */     setRotation(this.TailA, 1.308687F, 0.0F, 0.0F);
/*     */     
/*  70 */     this.TailB = new ModelRenderer(this, 4, 7);
/*  71 */     this.TailB.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 0);
/*  72 */     this.TailB.setRotationPoint(0.0F, 23.0F, 2.8F);
/*  73 */     setRotation(this.TailB, 1.665602F, 0.0F, 0.0F);
/*     */     
/*  75 */     this.FrontLegs = new ModelRenderer(this, 0, 7);
/*  76 */     this.FrontLegs.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 0);
/*  77 */     this.FrontLegs.setRotationPoint(0.0F, 23.0F, -1.8F);
/*  78 */     setRotation(this.FrontLegs, -0.8328009F, 0.0F, 0.0F);
/*     */     
/*  80 */     this.MidLegs = new ModelRenderer(this, 0, 13);
/*  81 */     this.MidLegs.addBox(-2.0F, 0.0F, 0.0F, 4, 2, 0);
/*  82 */     this.MidLegs.setRotationPoint(0.0F, 23.0F, -1.2F);
/*  83 */     setRotation(this.MidLegs, 1.070744F, 0.0F, 0.0F);
/*     */     
/*  85 */     this.ThighLeft = new ModelRenderer(this, 8, 5);
/*  86 */     this.ThighLeft.addBox(0.0F, -3.0F, 0.0F, 1, 3, 1);
/*  87 */     this.ThighLeft.setRotationPoint(0.5F, 23.0F, 0.0F);
/*  88 */     setRotation(this.ThighLeft, -0.4886922F, 0.2617994F, 0.0F);
/*     */     
/*  90 */     this.ThighLeftB = new ModelRenderer(this, 8, 5);
/*  91 */     this.ThighLeftB.addBox(0.0F, -3.0F, 0.0F, 1, 3, 1);
/*  92 */     this.ThighLeftB.setRotationPoint(0.5F, 22.5F, 0.0F);
/*  93 */     setRotation(this.ThighLeftB, -1.762782F, 0.0F, 0.0F);
/*     */     
/*  95 */     this.ThighRight = new ModelRenderer(this, 12, 5);
/*  96 */     this.ThighRight.addBox(-1.0F, -3.0F, 0.0F, 1, 3, 1);
/*  97 */     this.ThighRight.setRotationPoint(-0.5F, 23.0F, 0.0F);
/*  98 */     setRotation(this.ThighRight, -0.4886922F, -0.2617994F, 0.0F);
/*     */     
/* 100 */     this.ThighRightB = new ModelRenderer(this, 12, 5);
/* 101 */     this.ThighRightB.addBox(-1.0F, -3.0F, 0.0F, 1, 3, 1);
/* 102 */     this.ThighRightB.setRotationPoint(-0.5F, 22.5F, 0.0F);
/* 103 */     setRotation(this.ThighRightB, -1.762782F, 0.0F, 0.0F);
/*     */     
/* 105 */     this.LegLeft = new ModelRenderer(this, 0, 15);
/* 106 */     this.LegLeft.addBox(0.0F, 0.0F, -1.0F, 0, 3, 2);
/* 107 */     this.LegLeft.setRotationPoint(2.0F, 21.0F, 2.5F);
/*     */     
/* 109 */     this.LegLeftB = new ModelRenderer(this, 4, 15);
/* 110 */     this.LegLeftB.addBox(0.0F, 0.0F, -1.0F, 0, 3, 2);
/* 111 */     this.LegLeftB.setRotationPoint(1.5F, 23.0F, 2.9F);
/* 112 */     setRotation(this.LegLeftB, 1.249201F, 0.0F, 0.0F);
/*     */     
/* 114 */     this.LegRight = new ModelRenderer(this, 4, 15);
/* 115 */     this.LegRight.addBox(0.0F, 0.0F, -1.0F, 0, 3, 2);
/* 116 */     this.LegRight.setRotationPoint(-2.0F, 21.0F, 2.5F);
/*     */     
/* 118 */     this.LegRightB = new ModelRenderer(this, 4, 15);
/* 119 */     this.LegRightB.addBox(0.0F, 0.0F, -1.0F, 0, 3, 2);
/* 120 */     this.LegRightB.setRotationPoint(-1.5F, 23.0F, 2.9F);
/* 121 */     setRotation(this.LegRightB, 1.249201F, 0.0F, 0.0F);
/*     */     
/* 123 */     this.LeftWing = new ModelRenderer(this, 0, 30);
/* 124 */     this.LeftWing.addBox(0.0F, 0.0F, -1.0F, 6, 0, 2);
/* 125 */     this.LeftWing.setRotationPoint(0.0F, 20.9F, -1.0F);
/* 126 */     setRotation(this.LeftWing, 0.0F, -0.1919862F, 0.0F);
/*     */     
/* 128 */     this.RightWing = new ModelRenderer(this, 0, 28);
/* 129 */     this.RightWing.addBox(-6.0F, 0.0F, -1.0F, 6, 0, 2);
/* 130 */     this.RightWing.setRotationPoint(0.0F, 20.9F, -1.0F);
/* 131 */     setRotation(this.RightWing, 0.0F, 0.1919862F, 0.0F);
/*     */     
/* 133 */     this.FoldedWings = new ModelRenderer(this, 0, 26);
/* 134 */     this.FoldedWings.addBox(0.0F, 0.0F, -1.0F, 6, 0, 2);
/* 135 */     this.FoldedWings.setRotationPoint(0.0F, 20.9F, -2.0F);
/* 136 */     setRotation(this.FoldedWings, 0.0F, -1.570796F, 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 141 */     MoCEntityCricket entitycricket = (MoCEntityCricket)entity;
/* 142 */     boolean isFlying = (entitycricket.getIsFlying() || entitycricket.motionY < -0.1D);
/* 143 */     setRotationAngles(f, f1, f2, f3, f4, f5, isFlying);
/* 144 */     this.Head.render(f5);
/* 145 */     this.Antenna.render(f5);
/* 146 */     this.AntennaB.render(f5);
/* 147 */     this.Thorax.render(f5);
/* 148 */     this.Abdomen.render(f5);
/* 149 */     this.TailA.render(f5);
/* 150 */     this.TailB.render(f5);
/* 151 */     this.FrontLegs.render(f5);
/* 152 */     this.MidLegs.render(f5);
/*     */     
/* 154 */     if (!isFlying) {
/*     */       
/* 156 */       this.ThighLeft.render(f5);
/* 157 */       this.ThighRight.render(f5);
/* 158 */       this.LegLeft.render(f5);
/* 159 */       this.LegRight.render(f5);
/* 160 */       this.FoldedWings.render(f5);
/*     */     }
/*     */     else {
/*     */       
/* 164 */       this.ThighLeftB.render(f5);
/* 165 */       this.ThighRightB.render(f5);
/* 166 */       this.LegLeftB.render(f5);
/* 167 */       this.LegRightB.render(f5);
/* 168 */       GL11.glPushMatrix();
/* 169 */       GL11.glEnable(3042);
/* 170 */       float transparency = 0.6F;
/* 171 */       GL11.glBlendFunc(770, 771);
/* 172 */       GL11.glColor4f(0.8F, 0.8F, 0.8F, transparency);
/* 173 */       this.LeftWing.render(f5);
/* 174 */       this.RightWing.render(f5);
/* 175 */       GL11.glDisable(3042);
/* 176 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 186 */     model.rotateAngleX = x;
/* 187 */     model.rotateAngleY = y;
/* 188 */     model.rotateAngleZ = z;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, boolean isFlying) {
/* 193 */     float legMov = 0.0F;
/* 194 */     float legMovB = 0.0F;
/*     */     
/* 196 */     float frontLegAdj = 0.0F;
/*     */     
/* 198 */     if (isFlying) {
/* 199 */       float WingRot = MathHelper.cos(f2 * 2.0F) * 0.7F;
/* 200 */       this.RightWing.rotateAngleZ = WingRot;
/* 201 */       this.LeftWing.rotateAngleZ = -WingRot;
/* 202 */       legMov = f1 * 1.5F;
/* 203 */       legMovB = legMov;
/* 204 */       frontLegAdj = 1.4F;
/*     */     } else {
/*     */       
/* 207 */       legMov = MathHelper.cos(f * 1.5F + 3.141593F) * 2.0F * f1;
/* 208 */       legMovB = MathHelper.cos(f * 1.5F) * 2.0F * f1;
/*     */     } 
/*     */     
/* 211 */     this.AntennaB.rotateAngleX = 2.88506F - legMov;
/*     */     
/* 213 */     this.FrontLegs.rotateAngleX = -0.8328009F + frontLegAdj + legMov;
/* 214 */     this.MidLegs.rotateAngleX = 1.070744F + legMovB;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelCricket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */