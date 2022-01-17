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
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MoCModelShark
/*     */   extends ModelBase
/*     */ {
/*     */   public ModelRenderer LHead;
/*     */   public ModelRenderer RHead;
/*     */   public ModelRenderer UHead;
/*     */   public ModelRenderer DHead;
/*     */   public ModelRenderer RTail;
/*     */   public ModelRenderer LTail;
/*     */   public ModelRenderer PTail;
/*     */   public ModelRenderer Body;
/*     */   public ModelRenderer UpperFin;
/*     */   public ModelRenderer UpperTailFin;
/*     */   public ModelRenderer LowerTailFin;
/*     */   public ModelRenderer RightFin;
/*     */   public ModelRenderer LeftFin;
/*     */   
/*     */   public MoCModelShark() {
/*  40 */     this.Body = new ModelRenderer(this, 6, 6);
/*  41 */     this.Body.addBox(0.0F, 0.0F, 0.0F, 6, 8, 18, 0.0F);
/*  42 */     this.Body.setRotationPoint(-4.0F, 17.0F, -10.0F);
/*  43 */     this.UHead = new ModelRenderer(this, 0, 0);
/*  44 */     this.UHead.addBox(0.0F, 0.0F, 0.0F, 5, 2, 8, 0.0F);
/*  45 */     this.UHead.setRotationPoint(-3.5F, 21.0F, -16.5F);
/*  46 */     this.UHead.rotateAngleX = 0.5235988F;
/*  47 */     this.DHead = new ModelRenderer(this, 44, 0);
/*  48 */     this.DHead.addBox(0.0F, 0.0F, 0.0F, 5, 2, 5, 0.0F);
/*  49 */     this.DHead.setRotationPoint(-3.5F, 21.5F, -13.5F);
/*  50 */     this.DHead.rotateAngleX = -0.261799F;
/*  51 */     this.RHead = new ModelRenderer(this, 0, 3);
/*  52 */     this.RHead.addBox(0.0F, 0.0F, 0.0F, 1, 6, 6, 0.0F);
/*  53 */     this.RHead.setRotationPoint(-3.45F, 21.3F, -13.85F);
/*  54 */     this.RHead.rotateAngleX = 0.7853981F;
/*  55 */     this.LHead = new ModelRenderer(this, 0, 3);
/*  56 */     this.LHead.addBox(0.0F, 0.0F, 0.0F, 1, 6, 6, 0.0F);
/*  57 */     this.LHead.setRotationPoint(0.45F, 21.3F, -13.8F);
/*  58 */     this.LHead.rotateAngleX = 0.7853981F;
/*  59 */     this.PTail = new ModelRenderer(this, 36, 8);
/*  60 */     this.PTail.addBox(0.0F, 0.0F, 0.0F, 4, 6, 10, 0.0F);
/*  61 */     this.PTail.setRotationPoint(-3.0F, 18.0F, 8.0F);
/*  62 */     this.UpperFin = new ModelRenderer(this, 6, 12);
/*  63 */     this.UpperFin.addBox(0.0F, 0.0F, 0.0F, 1, 4, 8, 0.0F);
/*  64 */     this.UpperFin.setRotationPoint(-1.5F, 17.0F, -1.0F);
/*  65 */     this.UpperFin.rotateAngleX = 0.7853981F;
/*  66 */     this.UpperTailFin = new ModelRenderer(this, 6, 12);
/*  67 */     this.UpperTailFin.addBox(0.0F, 0.0F, 0.0F, 1, 4, 8, 0.0F);
/*  68 */     this.UpperTailFin.setRotationPoint(-1.5F, 18.0F, 16.0F);
/*  69 */     this.UpperTailFin.rotateAngleX = 0.5235988F;
/*  70 */     this.LowerTailFin = new ModelRenderer(this, 8, 14);
/*  71 */     this.LowerTailFin.addBox(0.0F, 0.0F, 0.0F, 1, 4, 6, 0.0F);
/*  72 */     this.LowerTailFin.setRotationPoint(-1.5F, 21.0F, 18.0F);
/*  73 */     this.LowerTailFin.rotateAngleX = -0.7853981F;
/*  74 */     this.LeftFin = new ModelRenderer(this, 18, 0);
/*  75 */     this.LeftFin.addBox(0.0F, 0.0F, 0.0F, 8, 1, 4, 0.0F);
/*  76 */     this.LeftFin.setRotationPoint(2.0F, 24.0F, -5.0F);
/*  77 */     this.LeftFin.rotateAngleY = -0.5235988F;
/*  78 */     this.LeftFin.rotateAngleZ = 0.5235988F;
/*  79 */     this.RightFin = new ModelRenderer(this, 18, 0);
/*  80 */     this.RightFin.addBox(0.0F, 0.0F, 0.0F, 8, 1, 4, 0.0F);
/*  81 */     this.RightFin.setRotationPoint(-10.0F, 27.5F, -1.0F);
/*  82 */     this.RightFin.rotateAngleY = 0.5235988F;
/*  83 */     this.RightFin.rotateAngleZ = -0.5235988F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/*  88 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/*  89 */     this.Body.render(f5);
/*  90 */     this.PTail.render(f5);
/*  91 */     this.UHead.render(f5);
/*  92 */     this.DHead.render(f5);
/*  93 */     this.RHead.render(f5);
/*  94 */     this.LHead.render(f5);
/*  95 */     this.UpperFin.render(f5);
/*  96 */     this.UpperTailFin.render(f5);
/*  97 */     this.LowerTailFin.render(f5);
/*  98 */     this.LeftFin.render(f5);
/*  99 */     this.RightFin.render(f5);
/*     */   }
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 103 */     this.UpperTailFin.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
/* 104 */     this.LowerTailFin.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelShark.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */