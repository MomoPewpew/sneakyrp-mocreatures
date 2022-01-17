/*     */ package drzhark.mocreatures.client.model;
/*     */ 
/*     */ import drzhark.mocreatures.entity.aquatic.MoCEntitySmallFish;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class MoCModelSmallFish
/*     */   extends ModelBase {
/*     */   ModelRenderer BodyFlat;
/*     */   ModelRenderer BodyRomboid;
/*     */   ModelRenderer MidBodyFin;
/*     */   ModelRenderer UpperFinA;
/*     */   ModelRenderer UpperFinB;
/*     */   ModelRenderer UpperFinC;
/*     */   ModelRenderer LowerFinA;
/*     */   ModelRenderer LowerFinB;
/*     */   ModelRenderer LowerFinC;
/*     */   ModelRenderer Tail;
/*     */   
/*     */   public MoCModelSmallFish() {
/*  24 */     this.textureWidth = 32;
/*  25 */     this.textureHeight = 32;
/*     */     
/*  27 */     this.BodyFlat = new ModelRenderer(this, 0, 2);
/*  28 */     this.BodyFlat.addBox(0.0F, -1.5F, -1.0F, 5, 3, 2);
/*  29 */     this.BodyFlat.setRotationPoint(-3.0F, 15.0F, 0.0F);
/*     */     
/*  31 */     this.BodyRomboid = new ModelRenderer(this, 0, 7);
/*  32 */     this.BodyRomboid.addBox(0.0F, 0.0F, -0.5F, 4, 4, 1);
/*  33 */     this.BodyRomboid.setRotationPoint(-4.0F, 15.0F, 0.0F);
/*  34 */     setRotation(this.BodyRomboid, 0.0F, 0.0F, -0.7853982F);
/*     */     
/*  36 */     this.MidBodyFin = new ModelRenderer(this, 0, 12);
/*  37 */     this.MidBodyFin.addBox(0.0F, -0.5F, 0.0F, 4, 2, 4);
/*  38 */     this.MidBodyFin.setRotationPoint(-3.0F, 15.0F, 0.0F);
/*  39 */     setRotation(this.MidBodyFin, 0.0F, 0.7853982F, 0.0F);
/*     */     
/*  41 */     this.UpperFinA = new ModelRenderer(this, 10, 0);
/*  42 */     this.UpperFinA.addBox(-0.5F, -1.3F, -0.5F, 2, 1, 1);
/*  43 */     this.UpperFinA.setRotationPoint(-0.65F, 13.5F, 0.0F);
/*     */     
/*  45 */     this.UpperFinB = new ModelRenderer(this, 0, 0);
/*  46 */     this.UpperFinB.addBox(-2.5F, -1.0F, -0.5F, 4, 1, 1);
/*  47 */     this.UpperFinB.setRotationPoint(0.0F, 13.5F, 0.0F);
/*  48 */     this.UpperFinB.setTextureSize(32, 32);
/*     */     
/*  50 */     this.UpperFinC = new ModelRenderer(this, 0, 18);
/*  51 */     this.UpperFinC.addBox(-5.0F, -2.0F, 0.0F, 8, 3, 0);
/*  52 */     this.UpperFinC.setRotationPoint(0.0F, 13.5F, 0.0F);
/*     */     
/*  54 */     this.LowerFinA = new ModelRenderer(this, 16, 0);
/*  55 */     this.LowerFinA.addBox(-0.5F, -0.3F, -0.5F, 2, 1, 1);
/*  56 */     this.LowerFinA.setRotationPoint(-0.65F, 17.2F, 0.0F);
/*     */     
/*  58 */     this.LowerFinB = new ModelRenderer(this, 0, 21);
/*  59 */     this.LowerFinB.addBox(0.0F, 0.0F, -3.0F, 5, 0, 6);
/*  60 */     this.LowerFinB.setRotationPoint(-3.0F, 16.0F, 0.0F);
/*     */     
/*  62 */     this.LowerFinC = new ModelRenderer(this, 16, 18);
/*  63 */     this.LowerFinC.addBox(-5.0F, 0.0F, 0.0F, 8, 3, 0);
/*  64 */     this.LowerFinC.setRotationPoint(0.0F, 15.5F, 0.0F);
/*     */     
/*  66 */     this.Tail = new ModelRenderer(this, 10, 7);
/*  67 */     this.Tail.addBox(0.0F, 0.0F, -0.5F, 3, 3, 1);
/*  68 */     this.Tail.setRotationPoint(1.3F, 15.0F, 0.0F);
/*  69 */     setRotation(this.Tail, 0.0F, 0.0F, -0.7853982F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/*  74 */     super.render(entity, f, f1, f2, f3, f4, f5);
/*  75 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/*  76 */     MoCEntitySmallFish smallFish = (MoCEntitySmallFish)entity;
/*  77 */     float yOffset = smallFish.getAdjustedYOffset();
/*  78 */     float xOffset = smallFish.getAdjustedXOffset();
/*  79 */     float zOffset = smallFish.getAdjustedZOffset();
/*  80 */     GL11.glPushMatrix();
/*  81 */     GL11.glTranslatef(xOffset, yOffset, zOffset);
/*  82 */     this.BodyFlat.render(f5);
/*  83 */     this.BodyRomboid.render(f5);
/*  84 */     this.MidBodyFin.render(f5);
/*  85 */     this.UpperFinA.render(f5);
/*  86 */     this.UpperFinB.render(f5);
/*  87 */     this.UpperFinC.render(f5);
/*  88 */     this.LowerFinA.render(f5);
/*  89 */     this.LowerFinB.render(f5);
/*  90 */     this.LowerFinC.render(f5);
/*  91 */     this.Tail.render(f5);
/*  92 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/*  96 */     model.rotateAngleX = x;
/*  97 */     model.rotateAngleY = y;
/*  98 */     model.rotateAngleZ = z;
/*     */   }
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 102 */     float tailMov = MathHelper.cos(f * 0.8F) * f1 * 0.6F;
/* 103 */     float finMov = MathHelper.cos(f2 * 0.4F) * 0.2F;
/*     */     
/* 105 */     this.Tail.rotateAngleY = tailMov;
/* 106 */     this.MidBodyFin.rotateAngleY = 0.7853982F + finMov;
/* 107 */     this.LowerFinB.rotateAngleZ = finMov;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelSmallFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */