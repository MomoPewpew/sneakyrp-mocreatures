/*     */ package drzhark.mocreatures.client.model;
/*     */ 
/*     */ import drzhark.mocreatures.entity.ambient.MoCEntityButterfly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MoCModelButterfly
/*     */   extends ModelBase {
/*     */   ModelRenderer Abdomen;
/*     */   ModelRenderer FrontLegs;
/*     */   ModelRenderer RightAntenna;
/*     */   ModelRenderer LeftAntenna;
/*     */   ModelRenderer RearLegs;
/*     */   ModelRenderer MidLegs;
/*     */   ModelRenderer Head;
/*     */   ModelRenderer Thorax;
/*     */   ModelRenderer WingRight;
/*     */   ModelRenderer WingLeft;
/*     */   ModelRenderer Mouth;
/*     */   ModelRenderer WingLeftFront;
/*     */   ModelRenderer WingRightFront;
/*     */   ModelRenderer WingRightBack;
/*     */   ModelRenderer WingLeftBack;
/*     */   
/*     */   public MoCModelButterfly() {
/*  32 */     this.textureWidth = 32;
/*  33 */     this.textureHeight = 32;
/*     */     
/*  35 */     this.Head = new ModelRenderer(this, 0, 11);
/*  36 */     this.Head.addBox(-0.5F, 0.0F, 0.0F, 1, 1, 1);
/*  37 */     this.Head.setRotationPoint(0.0F, 21.9F, -1.3F);
/*  38 */     setRotation(this.Head, -2.171231F, 0.0F, 0.0F);
/*     */     
/*  40 */     this.Mouth = new ModelRenderer(this, 0, 8);
/*  41 */     this.Mouth.addBox(0.0F, 0.0F, 0.0F, 1, 2, 0);
/*  42 */     this.Mouth.setRotationPoint(-0.2F, 22.0F, -2.5F);
/*  43 */     setRotation(this.Mouth, 0.6548599F, 0.0F, 0.0F);
/*     */     
/*  45 */     this.RightAntenna = new ModelRenderer(this, 0, 7);
/*  46 */     this.RightAntenna.addBox(-0.5F, 0.0F, -1.0F, 1, 0, 1);
/*  47 */     this.RightAntenna.setRotationPoint(-0.5F, 21.7F, -2.3F);
/*  48 */     setRotation(this.RightAntenna, -1.041001F, 0.7853982F, 0.0F);
/*     */     
/*  50 */     this.LeftAntenna = new ModelRenderer(this, 4, 7);
/*  51 */     this.LeftAntenna.addBox(-0.5F, 0.0F, -1.0F, 1, 0, 1);
/*  52 */     this.LeftAntenna.setRotationPoint(0.5F, 21.7F, -2.3F);
/*  53 */     setRotation(this.LeftAntenna, -1.041001F, -0.7853982F, 0.0F);
/*     */     
/*  55 */     this.Thorax = new ModelRenderer(this, 0, 0);
/*  56 */     this.Thorax.addBox(-0.5F, 1.5F, -1.0F, 1, 1, 2);
/*  57 */     this.Thorax.setRotationPoint(0.0F, 20.0F, -1.0F);
/*     */     
/*  59 */     this.Abdomen = new ModelRenderer(this, 8, 1);
/*  60 */     this.Abdomen.addBox(-0.5F, 0.0F, -1.0F, 1, 3, 1);
/*  61 */     this.Abdomen.setRotationPoint(0.0F, 21.5F, 0.0F);
/*  62 */     setRotation(this.Abdomen, 1.427659F, 0.0F, 0.0F);
/*     */     
/*  64 */     this.FrontLegs = new ModelRenderer(this, 0, 8);
/*  65 */     this.FrontLegs.addBox(-1.0F, 0.0F, 0.0F, 2, 3, 0);
/*  66 */     this.FrontLegs.setRotationPoint(0.0F, 21.5F, -1.8F);
/*  67 */     setRotation(this.FrontLegs, 0.1487144F, 0.0F, 0.0F);
/*     */     
/*  69 */     this.MidLegs = new ModelRenderer(this, 4, 8);
/*  70 */     this.MidLegs.addBox(-1.0F, 0.0F, 0.0F, 2, 3, 0);
/*  71 */     this.MidLegs.setRotationPoint(0.0F, 22.0F, -1.2F);
/*  72 */     setRotation(this.MidLegs, 0.5948578F, 0.0F, 0.0F);
/*     */     
/*  74 */     this.RearLegs = new ModelRenderer(this, 0, 8);
/*  75 */     this.RearLegs.addBox(-1.0F, 0.0F, 0.0F, 2, 3, 0);
/*  76 */     this.RearLegs.setRotationPoint(0.0F, 22.5F, -0.4F);
/*  77 */     setRotation(this.RearLegs, 1.070744F, 0.0F, 0.0F);
/*     */     
/*  79 */     this.WingLeftFront = new ModelRenderer(this, 4, 20);
/*  80 */     this.WingLeftFront.addBox(0.0F, 0.0F, -4.0F, 8, 0, 6);
/*  81 */     this.WingLeftFront.setRotationPoint(0.3F, 21.4F, -1.0F);
/*     */     
/*  83 */     this.WingLeft = new ModelRenderer(this, 4, 26);
/*  84 */     this.WingLeft.addBox(0.0F, 0.0F, -1.0F, 8, 0, 6);
/*  85 */     this.WingLeft.setRotationPoint(0.3F, 21.5F, -0.5F);
/*     */     
/*  87 */     this.WingLeftBack = new ModelRenderer(this, 4, 0);
/*  88 */     this.WingLeftBack.addBox(0.0F, 0.0F, -1.0F, 5, 0, 8);
/*  89 */     this.WingLeftBack.setRotationPoint(0.3F, 21.2F, -1.0F);
/*  90 */     setRotation(this.WingLeftBack, 0.0F, 0.0F, 0.5934119F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  99 */     this.WingRightFront = new ModelRenderer(this, 4, 8);
/* 100 */     this.WingRightFront.addBox(-8.0F, 0.0F, -4.0F, 8, 0, 6);
/* 101 */     this.WingRightFront.setRotationPoint(-0.3F, 21.4F, -1.0F);
/*     */     
/* 103 */     this.WingRight = new ModelRenderer(this, 4, 14);
/* 104 */     this.WingRight.addBox(-8.0F, 0.0F, -1.0F, 8, 0, 6);
/* 105 */     this.WingRight.setRotationPoint(-0.3F, 21.5F, -0.5F);
/*     */     
/* 107 */     this.WingRightBack = new ModelRenderer(this, 14, 0);
/* 108 */     this.WingRightBack.addBox(-5.0F, 0.0F, -1.0F, 5, 0, 8);
/* 109 */     this.WingRightBack.setRotationPoint(0.3F, 21.2F, -1.0F);
/* 110 */     setRotation(this.WingRightBack, 0.0F, 0.0F, -0.5934119F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 115 */     super.render(entity, f, f1, f2, f3, f4, f5);
/* 116 */     MoCEntityButterfly butterfly = (MoCEntityButterfly)entity;
/* 117 */     boolean flying = (butterfly.getIsFlying() || butterfly.motionY < -0.1D);
/* 118 */     setRotationAngles(f, f1, f2, f3, f4, f5, !flying);
/* 119 */     this.Abdomen.render(f5);
/* 120 */     this.FrontLegs.render(f5);
/* 121 */     this.RightAntenna.render(f5);
/* 122 */     this.LeftAntenna.render(f5);
/* 123 */     this.RearLegs.render(f5);
/* 124 */     this.MidLegs.render(f5);
/* 125 */     this.Head.render(f5);
/* 126 */     this.Thorax.render(f5);
/*     */     
/* 128 */     this.Mouth.render(f5);
/*     */     
/* 130 */     GL11.glPushMatrix();
/* 131 */     GL11.glEnable(3042);
/* 132 */     float transparency = 0.8F;
/* 133 */     GL11.glBlendFunc(770, 771);
/* 134 */     GL11.glColor4f(0.8F, 0.8F, 0.8F, transparency);
/*     */     
/* 136 */     this.WingRight.render(f5);
/* 137 */     this.WingLeft.render(f5);
/* 138 */     this.WingRightFront.render(f5);
/* 139 */     this.WingLeftFront.render(f5);
/* 140 */     this.WingRightBack.render(f5);
/* 141 */     this.WingLeftBack.render(f5);
/* 142 */     GL11.glDisable(3042);
/* 143 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 147 */     model.rotateAngleX = x;
/* 148 */     model.rotateAngleY = y;
/* 149 */     model.rotateAngleZ = z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, boolean onGround) {
/* 165 */     float f2a = f2 % 100.0F;
/* 166 */     float WingRot = 0.0F;
/* 167 */     float legMov = 0.0F;
/* 168 */     float legMovB = 0.0F;
/*     */     
/* 170 */     if (!onGround) {
/*     */       
/* 172 */       WingRot = MathHelper.cos(f2 * 0.9F) * 0.9F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 178 */       legMov = f1 * 1.5F;
/* 179 */       legMovB = legMov;
/*     */     } else {
/* 181 */       legMov = MathHelper.cos(f * 1.5F + 3.141593F) * 2.0F * f1;
/* 182 */       legMovB = MathHelper.cos(f * 1.5F) * 2.0F * f1;
/* 183 */       if ((((f2a > 40.0F) ? 1 : 0) & ((f2a < 60.0F) ? 1 : 0)) != 0)
/*     */       {
/* 185 */         WingRot = MathHelper.cos(f2 * 0.15F) * 0.9F;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 190 */     float baseAngle = 0.52359F;
/*     */     
/* 192 */     this.WingLeft.rotateAngleZ = -baseAngle + WingRot;
/* 193 */     this.WingRight.rotateAngleZ = baseAngle - WingRot;
/* 194 */     this.WingLeftFront.rotateAngleZ = -baseAngle + WingRot;
/*     */     
/* 196 */     this.WingLeftBack.rotateAngleZ = 0.5934119F + -baseAngle + WingRot;
/* 197 */     this.WingRightFront.rotateAngleZ = baseAngle - WingRot;
/* 198 */     this.WingRightBack.rotateAngleZ = -0.5934119F + baseAngle - WingRot;
/*     */     
/* 200 */     this.FrontLegs.rotateAngleX = 0.1487144F + legMov;
/* 201 */     this.MidLegs.rotateAngleX = 0.5948578F + legMovB;
/* 202 */     this.RearLegs.rotateAngleX = 1.070744F + legMov;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelButterfly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */