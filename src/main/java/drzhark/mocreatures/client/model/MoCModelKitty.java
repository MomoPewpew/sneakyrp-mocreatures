/*     */ package drzhark.mocreatures.client.model;
/*     */ 
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityKitty;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MoCModelKitty
/*     */   extends ModelBase
/*     */ {
/*     */   public boolean isSitting;
/*     */   public boolean isSwinging;
/*     */   public float swingProgress;
/*     */   public int kittystate;
/*     */   public ModelRenderer[] headParts;
/*     */   public ModelRenderer tail;
/*     */   public ModelRenderer rightArm;
/*     */   public ModelRenderer leftArm;
/*     */   public ModelRenderer rightLeg;
/*     */   public ModelRenderer leftLeg;
/*     */   private ModelRenderer body;
/*     */   
/*     */   public MoCModelKitty() {
/*  29 */     this(0.0F);
/*     */   }
/*     */   
/*     */   public MoCModelKitty(float f) {
/*  33 */     this(f, 0.0F);
/*     */   }
/*     */   
/*     */   public MoCModelKitty(float f, float f1) {
/*  37 */     this.headParts = new ModelRenderer[10];
/*  38 */     this.headParts[0] = new ModelRenderer(this, 16, 0);
/*  39 */     this.headParts[0].addBox(-2.0F, -5.0F, -3.0F, 1, 1, 1, f);
/*  40 */     this.headParts[0].setRotationPoint(0.0F, 0.0F + f1, -2.0F);
/*  41 */     this.headParts[1] = new ModelRenderer(this, 16, 0);
/*  42 */     (this.headParts[1]).mirror = true;
/*  43 */     this.headParts[1].addBox(1.0F, -5.0F, -3.0F, 1, 1, 1, f);
/*  44 */     this.headParts[1].setRotationPoint(0.0F, 0.0F + f1, -2.0F);
/*  45 */     this.headParts[2] = new ModelRenderer(this, 20, 0);
/*  46 */     this.headParts[2].addBox(-2.5F, -4.0F, -3.0F, 2, 1, 1, f);
/*  47 */     this.headParts[2].setRotationPoint(0.0F, 0.0F + f1, -2.0F);
/*  48 */     this.headParts[3] = new ModelRenderer(this, 20, 0);
/*  49 */     (this.headParts[3]).mirror = true;
/*  50 */     this.headParts[3].addBox(0.5F, -4.0F, -3.0F, 2, 1, 1, f);
/*  51 */     this.headParts[3].setRotationPoint(0.0F, 0.0F + f1, -2.0F);
/*  52 */     this.headParts[4] = new ModelRenderer(this, 40, 0);
/*  53 */     this.headParts[4].addBox(-4.0F, -1.5F, -5.0F, 3, 3, 1, f);
/*  54 */     this.headParts[4].setRotationPoint(0.0F, 0.0F + f1, -2.0F);
/*  55 */     this.headParts[5] = new ModelRenderer(this, 40, 0);
/*  56 */     (this.headParts[5]).mirror = true;
/*  57 */     this.headParts[5].addBox(1.0F, -1.5F, -5.0F, 3, 3, 1, f);
/*  58 */     this.headParts[5].setRotationPoint(0.0F, 0.0F + f1, -2.0F);
/*  59 */     this.headParts[6] = new ModelRenderer(this, 21, 6);
/*  60 */     this.headParts[6].addBox(-1.0F, -1.0F, -5.0F, 2, 2, 1, f);
/*  61 */     this.headParts[6].setRotationPoint(0.0F, 0.0F + f1, -2.0F);
/*  62 */     this.headParts[7] = new ModelRenderer(this, 50, 0);
/*  63 */     this.headParts[7].addBox(-2.5F, 0.5F, -1.0F, 5, 4, 1, f);
/*  64 */     this.headParts[7].setRotationPoint(0.0F, 0.0F + f1, -2.0F);
/*  65 */     this.headParts[8] = new ModelRenderer(this, 60, 0);
/*  66 */     this.headParts[8].addBox(-1.5F, -2.0F, -4.1F, 3, 1, 1, f);
/*  67 */     this.headParts[8].setRotationPoint(0.0F, 0.0F + f1, -2.0F);
/*  68 */     this.headParts[9] = new ModelRenderer(this, 1, 1);
/*  69 */     this.headParts[9].addBox(-2.5F, -3.0F, -4.0F, 5, 4, 4, f);
/*  70 */     this.headParts[9].setRotationPoint(0.0F, 0.0F + f1, -2.0F);
/*     */     
/*  72 */     this.body = new ModelRenderer(this, 20, 0);
/*  73 */     this.body.addBox(-2.5F, -2.0F, -0.0F, 5, 5, 10, f);
/*  74 */     this.body.setRotationPoint(0.0F, 0.0F + f1, -2.0F);
/*  75 */     this.rightArm = new ModelRenderer(this, 0, 9);
/*  76 */     this.rightArm.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, f);
/*  77 */     this.rightArm.setRotationPoint(-1.5F, 3.0F + f1, -1.0F);
/*  78 */     this.leftArm = new ModelRenderer(this, 0, 9);
/*  79 */     this.leftArm.mirror = true;
/*  80 */     this.leftArm.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, f);
/*  81 */     this.leftArm.setRotationPoint(1.5F, 3.0F + f1, -1.0F);
/*  82 */     this.rightLeg = new ModelRenderer(this, 8, 9);
/*  83 */     this.rightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, f);
/*  84 */     this.rightLeg.setRotationPoint(-1.5F, 3.0F + f1, 7.0F);
/*  85 */     this.leftLeg = new ModelRenderer(this, 8, 9);
/*  86 */     this.leftLeg.mirror = true;
/*  87 */     this.leftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, f);
/*  88 */     this.leftLeg.setRotationPoint(1.5F, 3.0F + f1, 7.0F);
/*  89 */     this.tail = new ModelRenderer(this, 16, 9);
/*  90 */     this.tail.mirror = true;
/*  91 */     this.tail.addBox(-0.5F, -8.0F, -1.0F, 1, 8, 1, f);
/*  92 */     this.tail.setRotationPoint(0.0F, -0.5F + f1, 7.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/*  97 */     MoCEntityKitty kitty = (MoCEntityKitty)entity;
/*  98 */     this.isSitting = kitty.getIsSitting();
/*  99 */     this.isSwinging = kitty.getIsSwinging();
/* 100 */     this.swingProgress = kitty.swingProgress;
/* 101 */     this.kittystate = kitty.getKittyState();
/*     */     
/* 103 */     GL11.glPushMatrix();
/* 104 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/* 105 */     if (this.isSitting) {
/* 106 */       GL11.glTranslatef(0.0F, 0.25F, 0.0F);
/* 107 */       this.tail.rotateAngleZ = 0.0F;
/* 108 */       this.tail.rotateAngleX = -2.3F;
/*     */     } 
/*     */     
/* 111 */     for (int i = 0; i < 7; i++) {
/* 112 */       this.headParts[i].render(f5);
/*     */     }
/*     */     
/* 115 */     if (this.kittystate > 2) {
/* 116 */       this.headParts[7].render(f5);
/*     */     }
/* 118 */     if (this.kittystate == 12) {
/* 119 */       this.headParts[8].render(f5);
/*     */     }
/* 121 */     this.headParts[9].render(f5);
/* 122 */     this.body.render(f5);
/* 123 */     this.tail.render(f5);
/* 124 */     if (this.isSitting) {
/* 125 */       GL11.glTranslatef(0.0F, 0.0625F, 0.0625F);
/* 126 */       float f6 = -1.570796F;
/* 127 */       this.rightArm.rotateAngleX = f6;
/* 128 */       this.leftArm.rotateAngleX = f6;
/* 129 */       this.rightLeg.rotateAngleX = f6;
/* 130 */       this.leftLeg.rotateAngleX = f6;
/* 131 */       this.rightLeg.rotateAngleY = 0.1F;
/* 132 */       this.leftLeg.rotateAngleY = -0.1F;
/*     */     } 
/* 134 */     this.rightArm.render(f5);
/* 135 */     this.leftArm.render(f5);
/* 136 */     this.rightLeg.render(f5);
/* 137 */     this.leftLeg.render(f5);
/* 138 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 142 */     (this.headParts[9]).rotateAngleY = f3 / 57.29578F;
/* 143 */     (this.headParts[9]).rotateAngleX = f4 / 57.29578F;
/* 144 */     for (int i = 0; i < 9; i++) {
/* 145 */       (this.headParts[i]).rotateAngleY = (this.headParts[9]).rotateAngleY;
/* 146 */       (this.headParts[i]).rotateAngleX = (this.headParts[9]).rotateAngleX;
/*     */     } 
/*     */     
/* 149 */     this.rightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 2.0F * f1 * 0.5F;
/* 150 */     this.leftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
/* 151 */     this.rightArm.rotateAngleZ = 0.0F;
/* 152 */     this.leftArm.rotateAngleZ = 0.0F;
/* 153 */     this.rightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
/* 154 */     this.leftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
/* 155 */     this.rightLeg.rotateAngleY = 0.0F;
/* 156 */     this.leftLeg.rotateAngleY = 0.0F;
/* 157 */     if (this.isSwinging) {
/* 158 */       this.rightArm.rotateAngleX = -2.0F + this.swingProgress;
/* 159 */       this.rightArm.rotateAngleY = 2.25F - this.swingProgress * 2.0F;
/*     */     } else {
/* 161 */       this.rightArm.rotateAngleY = 0.0F;
/*     */     } 
/* 163 */     this.leftArm.rotateAngleY = 0.0F;
/* 164 */     this.tail.rotateAngleX = -0.5F;
/* 165 */     this.tail.rotateAngleZ = this.leftLeg.rotateAngleX * 0.625F;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelKitty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */