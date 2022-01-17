/*     */ package drzhark.mocreatures.client.model;
/*     */ 
/*     */ import drzhark.mocreatures.entity.ambient.MoCEntityFly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MoCModelFly
/*     */   extends ModelBase {
/*     */   ModelRenderer FrontLegs;
/*     */   ModelRenderer RearLegs;
/*     */   ModelRenderer MidLegs;
/*     */   ModelRenderer FoldedWings;
/*     */   ModelRenderer Head;
/*     */   ModelRenderer Tail;
/*     */   ModelRenderer Abdomen;
/*     */   ModelRenderer RightWing;
/*     */   ModelRenderer Thorax;
/*     */   ModelRenderer LeftWing;
/*     */   
/*     */   public MoCModelFly() {
/*  27 */     this.textureWidth = 32;
/*  28 */     this.textureHeight = 32;
/*     */     
/*  30 */     this.Head = new ModelRenderer(this, 0, 4);
/*  31 */     this.Head.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2);
/*  32 */     this.Head.setRotationPoint(0.0F, 21.5F, -2.0F);
/*  33 */     setRotation(this.Head, -2.171231F, 0.0F, 0.0F);
/*     */     
/*  35 */     this.Thorax = new ModelRenderer(this, 0, 0);
/*  36 */     this.Thorax.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);
/*  37 */     this.Thorax.setRotationPoint(0.0F, 20.5F, -1.0F);
/*  38 */     setRotation(this.Thorax, 0.0F, 0.0F, 0.0F);
/*     */     
/*  40 */     this.Abdomen = new ModelRenderer(this, 8, 0);
/*  41 */     this.Abdomen.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);
/*  42 */     this.Abdomen.setRotationPoint(0.0F, 21.5F, 0.0F);
/*  43 */     setRotation(this.Abdomen, 1.427659F, 0.0F, 0.0F);
/*     */     
/*  45 */     this.Tail = new ModelRenderer(this, 10, 2);
/*  46 */     this.Tail.addBox(-1.0F, 0.0F, -1.0F, 1, 1, 1);
/*  47 */     this.Tail.setRotationPoint(0.5F, 21.2F, 1.5F);
/*  48 */     setRotation(this.Tail, 1.427659F, 0.0F, 0.0F);
/*     */     
/*  50 */     this.FrontLegs = new ModelRenderer(this, 0, 7);
/*  51 */     this.FrontLegs.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 0);
/*  52 */     this.FrontLegs.setRotationPoint(0.0F, 22.5F, -1.8F);
/*  53 */     setRotation(this.FrontLegs, 0.1487144F, 0.0F, 0.0F);
/*     */     
/*  55 */     this.RearLegs = new ModelRenderer(this, 0, 11);
/*  56 */     this.RearLegs.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 0);
/*  57 */     this.RearLegs.setRotationPoint(0.0F, 22.5F, -0.4F);
/*  58 */     setRotation(this.RearLegs, 1.070744F, 0.0F, 0.0F);
/*     */     
/*  60 */     this.MidLegs = new ModelRenderer(this, 0, 9);
/*  61 */     this.MidLegs.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 0);
/*  62 */     this.MidLegs.setRotationPoint(0.0F, 22.5F, -1.2F);
/*  63 */     setRotation(this.MidLegs, 0.5948578F, 0.0F, 0.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  72 */     this.LeftWing = new ModelRenderer(this, 4, 4);
/*  73 */     this.LeftWing.addBox(-1.0F, 0.0F, 0.5F, 2, 0, 4);
/*  74 */     this.LeftWing.setRotationPoint(0.0F, 20.4F, -1.0F);
/*  75 */     setRotation(this.LeftWing, 0.0F, 1.047198F, 0.0F);
/*     */     
/*  77 */     this.RightWing = new ModelRenderer(this, 4, 4);
/*  78 */     this.RightWing.addBox(-1.0F, 0.0F, 0.5F, 2, 0, 4);
/*  79 */     this.RightWing.setRotationPoint(0.0F, 20.4F, -1.0F);
/*  80 */     setRotation(this.RightWing, 0.0F, -1.047198F, 0.0F);
/*     */     
/*  82 */     this.FoldedWings = new ModelRenderer(this, 4, 4);
/*  83 */     this.FoldedWings.addBox(-1.0F, 0.0F, 0.0F, 2, 0, 4);
/*  84 */     this.FoldedWings.setRotationPoint(0.0F, 20.5F, -2.0F);
/*  85 */     setRotation(this.FoldedWings, 0.0872665F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/*  91 */     super.render(entity, f, f1, f2, f3, f4, f5);
/*  92 */     MoCEntityFly fly = (MoCEntityFly)entity;
/*  93 */     boolean isFlying = (fly.getIsFlying() || fly.motionY < -0.1D);
/*  94 */     setRotationAngles(f, f1, f2, f3, f4, f5, !isFlying);
/*  95 */     this.FrontLegs.render(f5);
/*  96 */     this.RearLegs.render(f5);
/*  97 */     this.MidLegs.render(f5);
/*  98 */     this.Head.render(f5);
/*  99 */     this.Tail.render(f5);
/* 100 */     this.Abdomen.render(f5);
/* 101 */     this.Thorax.render(f5);
/*     */     
/* 103 */     if (!isFlying) {
/* 104 */       this.FoldedWings.render(f5);
/*     */     } else {
/* 106 */       GL11.glPushMatrix();
/* 107 */       GL11.glEnable(3042);
/* 108 */       float transparency = 0.6F;
/* 109 */       GL11.glBlendFunc(770, 771);
/* 110 */       GL11.glColor4f(0.8F, 0.8F, 0.8F, transparency);
/* 111 */       this.LeftWing.render(f5);
/* 112 */       this.RightWing.render(f5);
/* 113 */       GL11.glDisable(3042);
/* 114 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 119 */     model.rotateAngleX = x;
/* 120 */     model.rotateAngleY = y;
/* 121 */     model.rotateAngleZ = z;
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
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, boolean onGround) {
/* 136 */     float WingRot = MathHelper.cos(f2 * 3.0F) * 0.7F;
/* 137 */     this.RightWing.rotateAngleZ = WingRot;
/* 138 */     this.LeftWing.rotateAngleZ = -WingRot;
/* 139 */     float legMov = 0.0F;
/* 140 */     float legMovB = 0.0F;
/*     */     
/* 142 */     if (!onGround) {
/* 143 */       legMov = f1 * 1.5F;
/* 144 */       legMovB = legMov;
/*     */     } else {
/* 146 */       legMov = MathHelper.cos(f * 1.5F + 3.141593F) * 2.0F * f1;
/* 147 */       legMovB = MathHelper.cos(f * 1.5F) * 2.0F * f1;
/*     */     } 
/*     */     
/* 150 */     this.FrontLegs.rotateAngleX = 0.1487144F + legMov;
/* 151 */     this.MidLegs.rotateAngleX = 0.5948578F + legMovB;
/* 152 */     this.RearLegs.rotateAngleX = 1.070744F + legMov;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelFly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */