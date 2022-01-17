/*     */ package drzhark.mocreatures.client.model;
/*     */ 
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityTurkey;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MoCModelTurkey
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer Beak;
/*     */   ModelRenderer Head;
/*     */   ModelRenderer Neck;
/*     */   ModelRenderer Chest;
/*     */   ModelRenderer RWing;
/*     */   ModelRenderer LWing;
/*     */   ModelRenderer UBody;
/*     */   ModelRenderer Body;
/*     */   ModelRenderer Tail;
/*     */   ModelRenderer RLeg;
/*     */   ModelRenderer RFoot;
/*     */   ModelRenderer LLeg;
/*     */   ModelRenderer LFoot;
/*     */   private boolean male;
/*     */   
/*     */   public MoCModelTurkey() {
/*  32 */     this.textureWidth = 64;
/*  33 */     this.textureHeight = 64;
/*     */     
/*  35 */     this.Beak = new ModelRenderer(this, 17, 17);
/*  36 */     this.Beak.addBox(-0.5F, -1.866667F, -3.366667F, 1, 1, 2);
/*  37 */     this.Beak.setRotationPoint(0.0F, 9.7F, -5.1F);
/*  38 */     setRotation(this.Beak, 0.7807508F, 0.0F, 0.0F);
/*     */     
/*  40 */     this.Head = new ModelRenderer(this, 0, 27);
/*  41 */     this.Head.addBox(-1.0F, -2.0F, -2.0F, 2, 2, 3);
/*  42 */     this.Head.setRotationPoint(0.0F, 9.7F, -5.1F);
/*  43 */     setRotation(this.Head, 0.4833219F, 0.0F, 0.0F);
/*     */     
/*  45 */     this.Neck = new ModelRenderer(this, 0, 32);
/*  46 */     this.Neck.addBox(-1.0F, -6.0F, -1.0F, 2, 6, 2);
/*  47 */     this.Neck.setRotationPoint(0.0F, 14.7F, -6.5F);
/*  48 */     setRotation(this.Neck, -0.2246208F, 0.0F, 0.0F);
/*     */     
/*  50 */     this.Chest = new ModelRenderer(this, 0, 17);
/*  51 */     this.Chest.addBox(-3.0F, 0.0F, -4.0F, 6, 6, 4);
/*  52 */     this.Chest.setRotationPoint(0.0F, 12.5F, -4.0F);
/*  53 */     setRotation(this.Chest, 0.5934119F, 0.0F, 0.0F);
/*     */     
/*  55 */     this.RWing = new ModelRenderer(this, 32, 30);
/*  56 */     this.RWing.addBox(-1.0F, -2.0F, 0.0F, 1, 6, 7);
/*  57 */     this.RWing.setRotationPoint(-4.0F, 14.0F, -3.0F);
/*  58 */     setRotation(this.RWing, -0.3346075F, 0.0F, 0.0F);
/*     */     
/*  60 */     this.LWing = new ModelRenderer(this, 48, 30);
/*  61 */     this.LWing.addBox(0.0F, -2.0F, 0.0F, 1, 6, 7);
/*  62 */     this.LWing.setRotationPoint(4.0F, 14.0F, -3.0F);
/*  63 */     setRotation(this.LWing, -0.3346075F, 0.0F, 0.0F);
/*     */     
/*  65 */     this.UBody = new ModelRenderer(this, 34, 0);
/*  66 */     this.UBody.addBox(-2.5F, -4.0F, 0.0F, 5, 7, 9);
/*  67 */     this.UBody.setRotationPoint(0.0F, 15.0F, -3.0F);
/*     */     
/*  69 */     this.Body = new ModelRenderer(this, 0, 0);
/*  70 */     this.Body.addBox(-4.0F, -4.0F, 0.0F, 8, 8, 9);
/*  71 */     this.Body.setRotationPoint(0.0F, 16.0F, -4.0F);
/*     */     
/*  73 */     this.Tail = new ModelRenderer(this, 32, 17);
/*  74 */     this.Tail.addBox(-8.0F, -9.0F, 0.0F, 16, 12, 0);
/*  75 */     this.Tail.setRotationPoint(0.0F, 14.0F, 6.0F);
/*  76 */     setRotation(this.Tail, -0.2974289F, 0.0F, 0.0F);
/*     */     
/*  78 */     this.RLeg = new ModelRenderer(this, 27, 17);
/*  79 */     this.RLeg.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1);
/*  80 */     this.RLeg.setRotationPoint(-2.0F, 19.0F, 0.5F);
/*     */     
/*  82 */     this.RFoot = new ModelRenderer(this, 20, 23);
/*  83 */     this.RFoot.addBox(-1.5F, 5.0F, -2.5F, 3, 0, 3);
/*  84 */     this.RFoot.setRotationPoint(-2.0F, 19.0F, 0.5F);
/*     */     
/*  86 */     this.LLeg = new ModelRenderer(this, 23, 17);
/*  87 */     this.LLeg.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1);
/*  88 */     this.LLeg.setRotationPoint(2.0F, 19.0F, 0.5F);
/*     */     
/*  90 */     this.LFoot = new ModelRenderer(this, 20, 26);
/*  91 */     this.LFoot.addBox(-1.5F, 5.0F, -2.5F, 3, 0, 3);
/*  92 */     this.LFoot.setRotationPoint(2.0F, 19.0F, 0.5F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/*  98 */     super.render(entity, f, f1, f2, f3, f4, f5);
/*  99 */     this.male = (((MoCEntityTurkey)entity).getType() == 1);
/* 100 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/* 101 */     this.Beak.render(f5);
/* 102 */     this.Head.render(f5);
/* 103 */     this.Neck.render(f5);
/* 104 */     this.RWing.render(f5);
/* 105 */     this.LWing.render(f5);
/* 106 */     this.Tail.render(f5);
/* 107 */     this.RLeg.render(f5);
/* 108 */     this.RFoot.render(f5);
/* 109 */     this.LLeg.render(f5);
/* 110 */     this.LFoot.render(f5);
/* 111 */     if (this.male) {
/* 112 */       this.UBody.render(f5);
/* 113 */       this.Body.render(f5);
/* 114 */       this.Chest.render(f5);
/*     */     } else {
/*     */       
/* 117 */       GL11.glPushMatrix();
/* 118 */       GL11.glScalef(0.8F, 0.8F, 1.0F);
/* 119 */       this.Body.render(f5);
/* 120 */       this.Chest.render(f5);
/*     */       
/* 122 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 128 */     model.rotateAngleX = x;
/* 129 */     model.rotateAngleY = y;
/* 130 */     model.rotateAngleZ = z;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 135 */     float LLegXRot = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
/* 136 */     float RLegXRot = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
/* 137 */     float wingF = MathHelper.cos(f * 0.6662F) * 1.4F * f1 / 4.0F;
/*     */     
/* 139 */     this.Head.rotateAngleX = 0.4833219F + f4 / 57.29578F;
/* 140 */     this.Head.rotateAngleY = f3 / 57.295776F;
/* 141 */     this.Beak.rotateAngleX = 0.2974F + this.Head.rotateAngleX;
/* 142 */     this.Beak.rotateAngleY = this.Head.rotateAngleY;
/*     */     
/* 144 */     this.LLeg.rotateAngleX = LLegXRot;
/* 145 */     this.LFoot.rotateAngleX = this.LLeg.rotateAngleX;
/* 146 */     this.RLeg.rotateAngleX = RLegXRot;
/* 147 */     this.RFoot.rotateAngleX = this.RLeg.rotateAngleX;
/*     */     
/* 149 */     this.LWing.rotateAngleY = wingF;
/* 150 */     this.RWing.rotateAngleY = -wingF;
/*     */     
/* 152 */     if (this.male) {
/* 153 */       this.Tail.rotateAngleX = -0.2974289F + wingF;
/* 154 */       this.Tail.rotationPointY = 14.0F;
/* 155 */       this.Tail.rotationPointZ = 6.0F;
/* 156 */       this.Chest.rotationPointY = 12.5F;
/* 157 */       this.Body.rotationPointY = 16.0F;
/* 158 */       this.LWing.rotationPointX = 4.0F;
/* 159 */       this.RWing.rotationPointX = -4.0F;
/*     */     } else {
/* 161 */       this.Tail.rotateAngleX = wingF - 1.9198622F;
/* 162 */       this.Tail.rotationPointY = 17.0F;
/* 163 */       this.Tail.rotationPointZ = 7.0F;
/* 164 */       this.Chest.rotationPointY = 16.0F;
/* 165 */       this.Body.rotationPointY = 20.0F;
/* 166 */       this.LWing.rotationPointX = 3.2F;
/* 167 */       this.RWing.rotationPointX = -3.2F;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelTurkey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */