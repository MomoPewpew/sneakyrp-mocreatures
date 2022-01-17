/*     */ package drzhark.mocreatures.client.model;
/*     */ 
/*     */ import drzhark.mocreatures.entity.ambient.MoCEntityBee;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MoCModelBee
/*     */   extends ModelBase {
/*     */   ModelRenderer Abdomen;
/*     */   ModelRenderer FrontLegs;
/*     */   ModelRenderer RAntenna;
/*     */   ModelRenderer LAntenna;
/*     */   ModelRenderer RightWing;
/*     */   ModelRenderer RearLegs;
/*     */   ModelRenderer MidLegs;
/*     */   ModelRenderer Head;
/*     */   ModelRenderer Mouth;
/*     */   ModelRenderer Tail;
/*     */   ModelRenderer FoldedWings;
/*     */   ModelRenderer LeftWing;
/*     */   ModelRenderer Thorax;
/*     */   
/*     */   public MoCModelBee() {
/*  30 */     this.textureWidth = 32;
/*  31 */     this.textureHeight = 32;
/*     */     
/*  33 */     this.Head = new ModelRenderer(this, 0, 9);
/*  34 */     this.Head.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2);
/*  35 */     this.Head.setRotationPoint(0.0F, 21.5F, -2.0F);
/*  36 */     setRotation(this.Head, -2.171231F, 0.0F, 0.0F);
/*     */     
/*  38 */     this.RAntenna = new ModelRenderer(this, 0, 17);
/*  39 */     this.RAntenna.addBox(-0.5F, 0.0F, -1.0F, 1, 0, 1);
/*  40 */     this.RAntenna.setRotationPoint(-0.5F, 20.2F, -2.3F);
/*  41 */     setRotation(this.RAntenna, -1.041001F, 0.7853982F, 0.0F);
/*     */     
/*  43 */     this.LAntenna = new ModelRenderer(this, 0, 12);
/*  44 */     this.LAntenna.addBox(-0.5F, 0.0F, -1.0F, 1, 0, 1);
/*  45 */     this.LAntenna.setRotationPoint(0.5F, 20.2F, -2.3F);
/*  46 */     setRotation(this.LAntenna, -1.041001F, -0.7853982F, 0.0F);
/*     */     
/*  48 */     this.Mouth = new ModelRenderer(this, 0, 13);
/*  49 */     this.Mouth.addBox(0.0F, 0.0F, -1.0F, 1, 1, 1);
/*  50 */     this.Mouth.setRotationPoint(0.0F, 21.5F, -2.0F);
/*  51 */     setRotation(this.Mouth, -0.4461433F, 0.3569147F, 0.7853982F);
/*     */     
/*  53 */     this.Thorax = new ModelRenderer(this, 0, 5);
/*  54 */     this.Thorax.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);
/*  55 */     this.Thorax.setRotationPoint(0.0F, 20.5F, -1.0F);
/*     */     
/*  57 */     this.Abdomen = new ModelRenderer(this, 0, 0);
/*  58 */     this.Abdomen.addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2);
/*  59 */     this.Abdomen.setRotationPoint(0.0F, 21.5F, 0.0F);
/*  60 */     setRotation(this.Abdomen, 1.249201F, 0.0F, 0.0F);
/*     */     
/*  62 */     this.Tail = new ModelRenderer(this, 0, 15);
/*  63 */     this.Tail.addBox(-0.5F, 0.0F, 0.0F, 1, 1, 1);
/*  64 */     this.Tail.setRotationPoint(0.0F, 22.0F, 2.0F);
/*  65 */     setRotation(this.Tail, 0.2379431F, 0.0F, 0.0F);
/*     */     
/*  67 */     this.FrontLegs = new ModelRenderer(this, 4, 14);
/*  68 */     this.FrontLegs.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 0);
/*  69 */     this.FrontLegs.setRotationPoint(0.0F, 22.0F, -1.8F);
/*  70 */     setRotation(this.FrontLegs, 0.1487144F, 0.0F, 0.0F);
/*     */     
/*  72 */     this.RearLegs = new ModelRenderer(this, 8, 1);
/*  73 */     this.RearLegs.addBox(-1.5F, 0.0F, 0.0F, 3, 3, 0);
/*  74 */     this.RearLegs.setRotationPoint(0.0F, 22.5F, -0.4F);
/*  75 */     setRotation(this.RearLegs, 0.8922867F, 0.0F, 0.0F);
/*     */     
/*  77 */     this.MidLegs = new ModelRenderer(this, 4, 12);
/*  78 */     this.MidLegs.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 0);
/*  79 */     this.MidLegs.setRotationPoint(0.0F, 22.5F, -1.2F);
/*  80 */     setRotation(this.MidLegs, 0.5948578F, 0.0F, 0.0F);
/*     */     
/*  82 */     this.LeftWing = new ModelRenderer(this, 0, 17);
/*  83 */     this.LeftWing.addBox(-1.0F, 0.0F, 0.5F, 2, 0, 4);
/*  84 */     this.LeftWing.setRotationPoint(0.0F, 20.4F, -1.0F);
/*  85 */     setRotation(this.LeftWing, 0.0F, 1.047198F, 0.0F);
/*     */     
/*  87 */     this.RightWing = new ModelRenderer(this, 0, 17);
/*  88 */     this.RightWing.addBox(-1.0F, 0.0F, 0.5F, 2, 0, 4);
/*  89 */     this.RightWing.setRotationPoint(0.0F, 20.4F, -1.0F);
/*  90 */     setRotation(this.RightWing, 0.0F, -1.047198F, 0.0F);
/*     */     
/*  92 */     this.FoldedWings = new ModelRenderer(this, 0, 17);
/*  93 */     this.FoldedWings.addBox(-1.0F, 0.0F, 0.0F, 2, 0, 4);
/*  94 */     this.FoldedWings.setRotationPoint(0.0F, 20.5F, -1.0F);
/*  95 */     setRotation(this.FoldedWings, 1.745E-4F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 101 */     super.render(entity, f, f1, f2, f3, f4, f5);
/* 102 */     MoCEntityBee entitybee = (MoCEntityBee)entity;
/* 103 */     boolean isFlying = (entitybee.getIsFlying() || entitybee.motionY < -0.1D);
/* 104 */     setRotationAngles(f, f1, f2, f3, f4, f5, !isFlying);
/* 105 */     this.Abdomen.render(f5);
/* 106 */     this.FrontLegs.render(f5);
/* 107 */     this.RAntenna.render(f5);
/* 108 */     this.LAntenna.render(f5);
/*     */     
/* 110 */     this.RearLegs.render(f5);
/* 111 */     this.MidLegs.render(f5);
/* 112 */     this.Head.render(f5);
/* 113 */     this.Mouth.render(f5);
/* 114 */     this.Tail.render(f5);
/* 115 */     this.Thorax.render(f5);
/*     */     
/* 117 */     if (!isFlying) {
/* 118 */       this.FoldedWings.render(f5);
/*     */     } else {
/* 120 */       GL11.glPushMatrix();
/* 121 */       GL11.glEnable(3042);
/* 122 */       float transparency = 0.6F;
/* 123 */       GL11.glBlendFunc(770, 771);
/* 124 */       GL11.glColor4f(0.8F, 0.8F, 0.8F, transparency);
/* 125 */       this.LeftWing.render(f5);
/* 126 */       this.RightWing.render(f5);
/* 127 */       GL11.glDisable(3042);
/* 128 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 133 */     model.rotateAngleX = x;
/* 134 */     model.rotateAngleY = y;
/* 135 */     model.rotateAngleZ = z;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, boolean onGround) {
/* 140 */     float WingRot = MathHelper.cos(f2 * 3.0F) * 0.7F;
/* 141 */     this.RightWing.rotateAngleZ = WingRot;
/* 142 */     this.LeftWing.rotateAngleZ = -WingRot;
/* 143 */     float legMov = 0.0F;
/* 144 */     float legMovB = 0.0F;
/*     */     
/* 146 */     if (!onGround) {
/* 147 */       legMov = f1 * 1.5F;
/* 148 */       legMovB = legMov;
/*     */     } else {
/* 150 */       legMov = MathHelper.cos(f * 1.5F + 3.141593F) * 2.0F * f1;
/* 151 */       legMovB = MathHelper.cos(f * 1.5F) * 2.0F * f1;
/*     */     } 
/*     */     
/* 154 */     this.FrontLegs.rotateAngleX = 0.1487144F + legMov;
/* 155 */     this.MidLegs.rotateAngleX = 0.5948578F + legMovB;
/* 156 */     this.RearLegs.rotateAngleX = 1.070744F + legMov;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelBee.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */