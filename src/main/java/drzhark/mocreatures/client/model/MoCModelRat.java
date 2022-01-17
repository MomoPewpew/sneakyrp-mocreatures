/*     */ package drzhark.mocreatures.client.model;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
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
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MoCModelRat
/*     */   extends ModelBase
/*     */ {
/*     */   public ModelRenderer Head;
/*     */   public ModelRenderer EarR;
/*     */   public ModelRenderer EarL;
/*     */   public ModelRenderer WhiskerR;
/*     */   public ModelRenderer WhiskerL;
/*     */   public ModelRenderer Body;
/*     */   public ModelRenderer Tail;
/*     */   public ModelRenderer FrontL;
/*     */   public ModelRenderer FrontR;
/*     */   public ModelRenderer RearL;
/*     */   public ModelRenderer RearR;
/*     */   public ModelRenderer BodyF;
/*     */   
/*     */   public MoCModelRat() {
/*  38 */     this.Head = new ModelRenderer(this, 0, 0);
/*  39 */     this.Head.addBox(-1.5F, -1.0F, -6.0F, 3, 4, 6, 0.0F);
/*  40 */     this.Head.setRotationPoint(0.0F, 18.0F, -9.0F);
/*  41 */     this.EarR = new ModelRenderer(this, 16, 26);
/*  42 */     this.EarR.addBox(-3.5F, -3.0F, -2.0F, 3, 3, 1, 0.0F);
/*  43 */     this.EarR.setRotationPoint(0.0F, 18.0F, -9.0F);
/*  44 */     this.EarL = new ModelRenderer(this, 24, 26);
/*  45 */     this.EarL.addBox(0.5F, -3.0F, -2.0F, 3, 3, 1, 0.0F);
/*  46 */     this.EarL.setRotationPoint(0.0F, 18.0F, -9.0F);
/*  47 */     this.WhiskerR = new ModelRenderer(this, 24, 16);
/*  48 */     this.WhiskerR.addBox(-4.5F, -1.0F, -6.0F, 3, 3, 1, 0.0F);
/*  49 */     this.WhiskerR.setRotationPoint(0.0F, 18.0F, -9.0F);
/*  50 */     this.WhiskerL = new ModelRenderer(this, 24, 20);
/*  51 */     this.WhiskerL.addBox(1.5F, -1.0F, -6.0F, 3, 3, 1, 0.0F);
/*  52 */     this.WhiskerL.setRotationPoint(0.0F, 18.0F, -9.0F);
/*  53 */     this.Body = new ModelRenderer(this, 24, 0);
/*  54 */     this.Body.addBox(-4.0F, -3.0F, -3.0F, 8, 8, 8, 0.0F);
/*  55 */     this.Body.setRotationPoint(0.0F, 19.0F, 0.0F);
/*  56 */     this.Body.rotateAngleX = 1.570796F;
/*  57 */     this.Tail = new ModelRenderer(this, 56, 0);
/*  58 */     this.Tail.addBox(-1.0F, 0.0F, -1.0F, 2, 18, 2, 0.0F);
/*  59 */     this.Tail.setRotationPoint(0.0F, 19.0F, 5.0F);
/*  60 */     this.Tail.rotateAngleX = 1.570796F;
/*  61 */     this.FrontL = new ModelRenderer(this, 0, 18);
/*  62 */     this.FrontL.addBox(-2.0F, 0.0F, -3.0F, 2, 1, 4, 0.0F);
/*  63 */     this.FrontL.setRotationPoint(3.0F, 22.0F, -7.0F);
/*  64 */     this.FrontR = new ModelRenderer(this, 0, 18);
/*  65 */     this.FrontR.addBox(0.0F, 0.0F, -3.0F, 2, 1, 4, 0.0F);
/*  66 */     this.FrontR.setRotationPoint(-3.0F, 22.0F, -7.0F);
/*  67 */     this.RearL = new ModelRenderer(this, 0, 24);
/*  68 */     this.RearL.addBox(-2.0F, 0.0F, -4.0F, 2, 1, 5, 0.0F);
/*  69 */     this.RearL.setRotationPoint(4.0F, 22.0F, 2.0F);
/*  70 */     this.RearR = new ModelRenderer(this, 0, 24);
/*  71 */     this.RearR.addBox(0.0F, 0.0F, -4.0F, 2, 1, 5, 0.0F);
/*  72 */     this.RearR.setRotationPoint(-4.0F, 22.0F, 2.0F);
/*  73 */     this.BodyF = new ModelRenderer(this, 32, 16);
/*  74 */     this.BodyF.addBox(-3.0F, -3.0F, -7.0F, 6, 6, 6, 0.0F);
/*  75 */     this.BodyF.setRotationPoint(0.0F, 19.0F, -2.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/*  80 */     super.render(entity, f, f1, f2, f3, f4, f5);
/*  81 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/*  82 */     this.Head.render(f5);
/*  83 */     this.EarR.render(f5);
/*  84 */     this.EarL.render(f5);
/*  85 */     this.WhiskerR.render(f5);
/*  86 */     this.WhiskerL.render(f5);
/*  87 */     this.Body.render(f5);
/*  88 */     this.Tail.render(f5);
/*  89 */     this.FrontL.render(f5);
/*  90 */     this.FrontR.render(f5);
/*  91 */     this.RearL.render(f5);
/*  92 */     this.RearR.render(f5);
/*  93 */     this.BodyF.render(f5);
/*     */   }
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/*  97 */     this.Head.rotateAngleX = -(f4 / 57.29578F);
/*  98 */     this.Head.rotateAngleY = f3 / 57.29578F;
/*  99 */     this.EarR.rotateAngleX = this.Head.rotateAngleX;
/* 100 */     this.EarR.rotateAngleY = this.Head.rotateAngleY;
/* 101 */     this.EarL.rotateAngleX = this.Head.rotateAngleX;
/* 102 */     this.EarL.rotateAngleY = this.Head.rotateAngleY;
/* 103 */     this.WhiskerR.rotateAngleX = this.Head.rotateAngleX;
/* 104 */     this.WhiskerR.rotateAngleY = this.Head.rotateAngleY;
/* 105 */     this.WhiskerL.rotateAngleX = this.Head.rotateAngleX;
/* 106 */     this.WhiskerL.rotateAngleY = this.Head.rotateAngleY;
/* 107 */     this.FrontL.rotateAngleX = MathHelper.cos(f * 0.6662F) * 0.6F * f1;
/* 108 */     this.RearL.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 0.8F * f1;
/* 109 */     this.RearR.rotateAngleX = MathHelper.cos(f * 0.6662F) * 0.6F * f1;
/* 110 */     this.FrontR.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 0.8F * f1;
/* 111 */     this.Tail.rotateAngleY = this.FrontL.rotateAngleX * 0.625F;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelRat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */