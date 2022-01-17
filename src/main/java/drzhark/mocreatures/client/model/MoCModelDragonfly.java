/*     */ package drzhark.mocreatures.client.model;
/*     */ 
/*     */ import drzhark.mocreatures.entity.ambient.MoCEntityDragonfly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MoCModelDragonfly
/*     */   extends ModelBase {
/*     */   ModelRenderer Abdomen;
/*     */   ModelRenderer FrontLegs;
/*     */   ModelRenderer RAntenna;
/*     */   ModelRenderer LAntenna;
/*     */   ModelRenderer RearLegs;
/*     */   ModelRenderer MidLegs;
/*     */   ModelRenderer Mouth;
/*     */   ModelRenderer WingRearRight;
/*     */   ModelRenderer Thorax;
/*     */   ModelRenderer WingFrontRight;
/*     */   ModelRenderer WingFrontLeft;
/*     */   ModelRenderer WingRearLeft;
/*     */   ModelRenderer Head;
/*     */   
/*     */   public MoCModelDragonfly() {
/*  30 */     this.textureWidth = 32;
/*  31 */     this.textureHeight = 32;
/*     */     
/*  33 */     this.Head = new ModelRenderer(this, 0, 4);
/*  34 */     this.Head.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2);
/*  35 */     this.Head.setRotationPoint(0.0F, 21.0F, -2.0F);
/*  36 */     setRotation(this.Head, -2.171231F, 0.0F, 0.0F);
/*     */     
/*  38 */     this.RAntenna = new ModelRenderer(this, 0, 7);
/*  39 */     this.RAntenna.addBox(-0.5F, 0.0F, -1.0F, 1, 0, 1);
/*  40 */     this.RAntenna.setRotationPoint(-0.5F, 19.7F, -2.3F);
/*  41 */     setRotation(this.RAntenna, -1.041001F, 0.7853982F, 0.0F);
/*     */     
/*  43 */     this.LAntenna = new ModelRenderer(this, 4, 7);
/*  44 */     this.LAntenna.addBox(-0.5F, 0.0F, -1.0F, 1, 0, 1);
/*  45 */     this.LAntenna.setRotationPoint(0.5F, 19.7F, -2.3F);
/*  46 */     setRotation(this.LAntenna, -1.041001F, -0.7853982F, 0.0F);
/*     */     
/*  48 */     this.Mouth = new ModelRenderer(this, 0, 11);
/*  49 */     this.Mouth.addBox(-0.5F, 0.0F, 0.0F, 1, 1, 1);
/*  50 */     this.Mouth.setRotationPoint(0.0F, 21.1F, -2.3F);
/*  51 */     setRotation(this.Mouth, -2.171231F, 0.0F, 0.0F);
/*     */     
/*  53 */     this.Thorax = new ModelRenderer(this, 0, 0);
/*  54 */     this.Thorax.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);
/*  55 */     this.Thorax.setRotationPoint(0.0F, 20.0F, -1.0F);
/*     */     
/*  57 */     this.Abdomen = new ModelRenderer(this, 8, 0);
/*  58 */     this.Abdomen.addBox(-0.5F, 0.0F, -1.0F, 1, 7, 1);
/*  59 */     this.Abdomen.setRotationPoint(0.0F, 20.5F, 0.0F);
/*  60 */     setRotation(this.Abdomen, 1.427659F, 0.0F, 0.0F);
/*     */     
/*  62 */     this.FrontLegs = new ModelRenderer(this, 0, 8);
/*  63 */     this.FrontLegs.addBox(-1.0F, 0.0F, 0.0F, 2, 3, 0);
/*  64 */     this.FrontLegs.setRotationPoint(0.0F, 21.5F, -1.8F);
/*  65 */     setRotation(this.FrontLegs, 0.1487144F, 0.0F, 0.0F);
/*     */     
/*  67 */     this.MidLegs = new ModelRenderer(this, 4, 8);
/*  68 */     this.MidLegs.addBox(-1.0F, 0.0F, 0.0F, 2, 3, 0);
/*  69 */     this.MidLegs.setRotationPoint(0.0F, 22.0F, -1.2F);
/*  70 */     setRotation(this.MidLegs, 0.5948578F, 0.0F, 0.0F);
/*     */     
/*  72 */     this.RearLegs = new ModelRenderer(this, 8, 8);
/*  73 */     this.RearLegs.addBox(-1.0F, 0.0F, 0.0F, 2, 3, 0);
/*  74 */     this.RearLegs.setRotationPoint(0.0F, 22.0F, -0.4F);
/*  75 */     setRotation(this.RearLegs, 1.070744F, 0.0F, 0.0F);
/*     */     
/*  77 */     this.WingFrontRight = new ModelRenderer(this, 0, 28);
/*  78 */     this.WingFrontRight.addBox(-7.0F, 0.0F, -1.0F, 7, 0, 2);
/*  79 */     this.WingFrontRight.setRotationPoint(-1.0F, 20.0F, -1.0F);
/*  80 */     setRotation(this.WingFrontRight, 0.0F, -0.1396263F, 0.0872665F);
/*     */     
/*  82 */     this.WingFrontLeft = new ModelRenderer(this, 0, 30);
/*  83 */     this.WingFrontLeft.addBox(0.0F, 0.0F, -1.0F, 7, 0, 2);
/*  84 */     this.WingFrontLeft.setRotationPoint(1.0F, 20.0F, -1.0F);
/*  85 */     setRotation(this.WingFrontLeft, 0.0F, 0.1396263F, -0.0872665F);
/*     */     
/*  87 */     this.WingRearRight = new ModelRenderer(this, 0, 24);
/*  88 */     this.WingRearRight.addBox(-7.0F, 0.0F, -1.0F, 7, 0, 2);
/*  89 */     this.WingRearRight.setRotationPoint(-1.0F, 20.0F, -1.0F);
/*  90 */     setRotation(this.WingRearRight, 0.0F, 0.3490659F, -0.0872665F);
/*     */     
/*  92 */     this.WingRearLeft = new ModelRenderer(this, 0, 26);
/*  93 */     this.WingRearLeft.addBox(0.0F, 0.0F, -1.0F, 7, 0, 2);
/*  94 */     this.WingRearLeft.setRotationPoint(1.0F, 20.0F, -1.0F);
/*  95 */     setRotation(this.WingRearLeft, 0.0F, -0.3490659F, 0.0872665F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 101 */     super.render(entity, f, f1, f2, f3, f4, f5);
/*     */     
/* 103 */     MoCEntityDragonfly dragonfly = (MoCEntityDragonfly)entity;
/*     */     
/* 105 */     boolean isFlying = (dragonfly.getIsFlying() || dragonfly.motionY < -0.1D);
/* 106 */     setRotationAngles(f, f1, f2, f3, f4, f5, isFlying);
/* 107 */     this.Head.render(f5);
/* 108 */     this.Abdomen.render(f5);
/* 109 */     this.FrontLegs.render(f5);
/* 110 */     this.RAntenna.render(f5);
/* 111 */     this.LAntenna.render(f5);
/* 112 */     this.RearLegs.render(f5);
/* 113 */     this.MidLegs.render(f5);
/* 114 */     this.Mouth.render(f5);
/* 115 */     this.Thorax.render(f5);
/*     */     
/* 117 */     GL11.glPushMatrix();
/* 118 */     GL11.glEnable(3042);
/* 119 */     float transparency = 0.6F;
/* 120 */     GL11.glBlendFunc(770, 771);
/* 121 */     GL11.glColor4f(0.8F, 0.8F, 0.8F, transparency);
/*     */     
/* 123 */     this.WingRearRight.render(f5);
/* 124 */     this.WingFrontRight.render(f5);
/* 125 */     this.WingFrontLeft.render(f5);
/* 126 */     this.WingRearLeft.render(f5);
/* 127 */     GL11.glDisable(3042);
/* 128 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 133 */     model.rotateAngleX = x;
/* 134 */     model.rotateAngleY = y;
/* 135 */     model.rotateAngleZ = z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, boolean flying) {
/* 145 */     float WingRot = 0.0F;
/* 146 */     float legMov = 0.0F;
/* 147 */     float legMovB = 0.0F;
/*     */     
/* 149 */     if (flying) {
/* 150 */       WingRot = MathHelper.cos(f2 * 2.0F) * 0.5F;
/* 151 */       legMov = f1 * 1.5F;
/* 152 */       legMovB = legMov;
/*     */     } else {
/* 154 */       legMov = MathHelper.cos(f * 1.5F + 3.141593F) * 2.0F * f1;
/* 155 */       legMovB = MathHelper.cos(f * 1.5F) * 2.0F * f1;
/*     */     } 
/*     */     
/* 158 */     this.WingFrontRight.rotateAngleZ = WingRot;
/* 159 */     this.WingRearLeft.rotateAngleZ = WingRot;
/*     */     
/* 161 */     this.WingFrontLeft.rotateAngleZ = -WingRot;
/* 162 */     this.WingRearRight.rotateAngleZ = -WingRot;
/*     */     
/* 164 */     this.FrontLegs.rotateAngleX = 0.1487144F + legMov;
/* 165 */     this.MidLegs.rotateAngleX = 0.5948578F + legMovB;
/* 166 */     this.RearLegs.rotateAngleX = 1.070744F + legMov;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelDragonfly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */