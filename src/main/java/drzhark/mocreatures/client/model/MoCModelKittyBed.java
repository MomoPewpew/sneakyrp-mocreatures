/*     */ package drzhark.mocreatures.client.model;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
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
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MoCModelKittyBed
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer TableL;
/*     */   ModelRenderer TableR;
/*     */   ModelRenderer Table_B;
/*     */   ModelRenderer FoodT;
/*     */   ModelRenderer FoodTraySide;
/*     */   ModelRenderer FoodTraySideB;
/*     */   ModelRenderer FoodTraySideC;
/*     */   ModelRenderer FoodTraySideD;
/*     */   ModelRenderer Milk;
/*     */   ModelRenderer PetFood;
/*     */   ModelRenderer Bottom;
/*     */   public boolean hasMilk;
/*     */   public boolean hasFood;
/*     */   public boolean pickedUp;
/*     */   public float milklevel;
/*     */   
/*     */   public MoCModelKittyBed() {
/*  43 */     float f = 0.0F;
/*  44 */     this.TableL = new ModelRenderer(this, 30, 8);
/*  45 */     this.TableL.addBox(-8.0F, 0.0F, 7.0F, 16, 6, 1, f);
/*  46 */     this.TableL.setRotationPoint(0.0F, 18.0F, 0.0F);
/*  47 */     this.TableR = new ModelRenderer(this, 30, 8);
/*  48 */     this.TableR.addBox(-8.0F, 18.0F, -8.0F, 16, 6, 1, f);
/*  49 */     this.TableR.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  50 */     this.Table_B = new ModelRenderer(this, 30, 0);
/*  51 */     this.Table_B.addBox(-8.0F, -3.0F, 0.0F, 16, 6, 1, f);
/*  52 */     this.Table_B.setRotationPoint(8.0F, 21.0F, 0.0F);
/*  53 */     this.Table_B.rotateAngleY = 1.5708F;
/*  54 */     this.FoodT = new ModelRenderer(this, 14, 0);
/*  55 */     this.FoodT.addBox(1.0F, 1.0F, 1.0F, 4, 1, 4, f);
/*  56 */     this.FoodT.setRotationPoint(-16.0F, 22.0F, 0.0F);
/*  57 */     this.FoodTraySide = new ModelRenderer(this, 0, 0);
/*  58 */     this.FoodTraySide.addBox(-16.0F, 21.0F, 5.0F, 5, 3, 1, f);
/*  59 */     this.FoodTraySide.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  60 */     this.FoodTraySideB = new ModelRenderer(this, 0, 0);
/*  61 */     this.FoodTraySideB.addBox(-15.0F, 21.0F, 0.0F, 5, 3, 1, f);
/*  62 */     this.FoodTraySideB.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  63 */     this.FoodTraySideC = new ModelRenderer(this, 0, 0);
/*  64 */     this.FoodTraySideC.addBox(-3.0F, -1.0F, 0.0F, 5, 3, 1, f);
/*  65 */     this.FoodTraySideC.setRotationPoint(-16.0F, 22.0F, 2.0F);
/*  66 */     this.FoodTraySideC.rotateAngleY = 1.5708F;
/*  67 */     this.FoodTraySideD = new ModelRenderer(this, 0, 0);
/*  68 */     this.FoodTraySideD.addBox(-3.0F, -1.0F, 0.0F, 5, 3, 1, f);
/*  69 */     this.FoodTraySideD.setRotationPoint(-11.0F, 22.0F, 3.0F);
/*  70 */     this.FoodTraySideD.rotateAngleY = 1.5708F;
/*  71 */     this.Milk = new ModelRenderer(this, 14, 9);
/*  72 */     this.Milk.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4, f);
/*  73 */     this.Milk.setRotationPoint(-15.0F, 21.0F, 1.0F);
/*  74 */     this.PetFood = new ModelRenderer(this, 0, 9);
/*  75 */     this.PetFood.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4, f);
/*  76 */     this.PetFood.setRotationPoint(-15.0F, 21.0F, 1.0F);
/*  77 */     this.Bottom = new ModelRenderer(this, 16, 15);
/*  78 */     this.Bottom.addBox(-10.0F, 0.0F, -7.0F, 16, 1, 14, f);
/*  79 */     this.Bottom.setRotationPoint(2.0F, 23.0F, 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/*  84 */     this.TableL.render(f5);
/*  85 */     this.TableR.render(f5);
/*  86 */     this.Table_B.render(f5);
/*  87 */     this.Bottom.render(f5);
/*  88 */     if (!this.pickedUp) {
/*  89 */       this.FoodT.render(f5);
/*  90 */       this.FoodTraySide.render(f5);
/*  91 */       this.FoodTraySideB.render(f5);
/*  92 */       this.FoodTraySideC.render(f5);
/*  93 */       this.FoodTraySideD.render(f5);
/*  94 */       if (this.hasMilk) {
/*  95 */         this.Milk.rotationPointY = 21.0F + this.milklevel;
/*  96 */         this.Milk.render(f5);
/*     */       } 
/*  98 */       if (this.hasFood) {
/*  99 */         this.PetFood.rotationPointY = 21.0F + this.milklevel;
/* 100 */         this.PetFood.render(f5);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelKittyBed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */