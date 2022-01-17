/*     */ package drzhark.mocreatures.client.model;
/*     */ 
/*     */ import drzhark.mocreatures.entity.ambient.MoCEntityFirefly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MoCModelFirefly
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer Antenna;
/*     */   ModelRenderer RearLegs;
/*     */   ModelRenderer MidLegs;
/*     */   ModelRenderer Head;
/*     */   ModelRenderer Tail;
/*     */   ModelRenderer Abdomen;
/*     */   ModelRenderer FrontLegs;
/*     */   ModelRenderer RightShellOpen;
/*     */   ModelRenderer LeftShellOpen;
/*     */   ModelRenderer Thorax;
/*     */   ModelRenderer RightShell;
/*     */   ModelRenderer LeftShell;
/*     */   ModelRenderer LeftWing;
/*     */   ModelRenderer RightWing;
/*     */   
/*     */   public MoCModelFirefly() {
/*  32 */     this.textureWidth = 32;
/*  33 */     this.textureHeight = 32;
/*     */     
/*  35 */     this.Head = new ModelRenderer(this, 0, 4);
/*  36 */     this.Head.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2);
/*  37 */     this.Head.setRotationPoint(0.0F, 22.5F, -2.0F);
/*  38 */     setRotation(this.Head, -2.171231F, 0.0F, 0.0F);
/*     */     
/*  40 */     this.Antenna = new ModelRenderer(this, 0, 7);
/*  41 */     this.Antenna.addBox(-1.0F, 0.0F, 0.0F, 2, 1, 0);
/*  42 */     this.Antenna.setRotationPoint(0.0F, 22.5F, -3.0F);
/*  43 */     setRotation(this.Antenna, -1.665602F, 0.0F, 0.0F);
/*     */     
/*  45 */     this.Thorax = new ModelRenderer(this, 0, 0);
/*  46 */     this.Thorax.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);
/*  47 */     this.Thorax.setRotationPoint(0.0F, 21.0F, -1.0F);
/*  48 */     setRotation(this.Thorax, 0.0F, 0.0F, 0.0F);
/*     */     
/*  50 */     this.Abdomen = new ModelRenderer(this, 8, 0);
/*  51 */     this.Abdomen.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);
/*  52 */     this.Abdomen.setRotationPoint(0.0F, 22.0F, 0.0F);
/*  53 */     setRotation(this.Abdomen, 1.427659F, 0.0F, 0.0F);
/*     */     
/*  55 */     this.Tail = new ModelRenderer(this, 8, 17);
/*  56 */     this.Tail.addBox(-1.0F, 0.5F, -1.0F, 2, 2, 1);
/*  57 */     this.Tail.setRotationPoint(0.0F, 21.3F, 1.5F);
/*  58 */     setRotation(this.Tail, 1.13023F, 0.0F, 0.0F);
/*     */     
/*  60 */     this.FrontLegs = new ModelRenderer(this, 0, 7);
/*  61 */     this.FrontLegs.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 0);
/*  62 */     this.FrontLegs.setRotationPoint(0.0F, 23.0F, -1.8F);
/*  63 */     setRotation(this.FrontLegs, -0.8328009F, 0.0F, 0.0F);
/*     */     
/*  65 */     this.MidLegs = new ModelRenderer(this, 0, 9);
/*  66 */     this.MidLegs.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 0);
/*  67 */     this.MidLegs.setRotationPoint(0.0F, 23.0F, -1.2F);
/*  68 */     setRotation(this.MidLegs, 1.070744F, 0.0F, 0.0F);
/*     */     
/*  70 */     this.RearLegs = new ModelRenderer(this, 0, 9);
/*  71 */     this.RearLegs.addBox(-1.0F, 0.0F, 0.0F, 2, 3, 0);
/*  72 */     this.RearLegs.setRotationPoint(0.0F, 23.0F, -0.4F);
/*  73 */     setRotation(this.RearLegs, 1.249201F, 0.0F, 0.0F);
/*     */     
/*  75 */     this.RightShellOpen = new ModelRenderer(this, 0, 12);
/*  76 */     this.RightShellOpen.addBox(-1.0F, 0.0F, 0.0F, 2, 0, 5);
/*  77 */     this.RightShellOpen.setRotationPoint(-1.0F, 21.0F, -2.0F);
/*  78 */     setRotation(this.RightShellOpen, 1.22F, 0.0F, -0.6457718F);
/*     */     
/*  80 */     this.LeftShellOpen = new ModelRenderer(this, 0, 12);
/*  81 */     this.LeftShellOpen.addBox(-1.0F, 0.0F, 0.0F, 2, 0, 5);
/*  82 */     this.LeftShellOpen.setRotationPoint(1.0F, 21.0F, -2.0F);
/*  83 */     setRotation(this.LeftShellOpen, 1.22F, 0.0F, 0.6457718F);
/*     */     
/*  85 */     this.RightShell = new ModelRenderer(this, 0, 12);
/*  86 */     this.RightShell.addBox(-1.0F, 0.0F, 0.0F, 2, 0, 5);
/*  87 */     this.RightShell.setRotationPoint(-1.0F, 21.0F, -2.0F);
/*  88 */     setRotation(this.RightShell, 0.0174533F, 0.0F, -0.6457718F);
/*     */     
/*  90 */     this.LeftShell = new ModelRenderer(this, 0, 12);
/*  91 */     this.LeftShell.addBox(-1.0F, 0.0F, 0.0F, 2, 0, 5);
/*  92 */     this.LeftShell.setRotationPoint(1.0F, 21.0F, -2.0F);
/*  93 */     setRotation(this.LeftShell, 0.0174533F, 0.0F, 0.6457718F);
/*     */     
/*  95 */     this.LeftWing = new ModelRenderer(this, 15, 12);
/*  96 */     this.LeftWing.addBox(-1.0F, 0.0F, 0.0F, 2, 0, 5);
/*  97 */     this.LeftWing.setRotationPoint(1.0F, 21.0F, -1.0F);
/*  98 */     setRotation(this.LeftWing, 0.0F, 1.047198F, 0.0F);
/*     */     
/* 100 */     this.RightWing = new ModelRenderer(this, 15, 12);
/* 101 */     this.RightWing.addBox(-1.0F, 0.0F, 0.0F, 2, 0, 5);
/* 102 */     this.RightWing.setRotationPoint(-1.0F, 21.0F, -1.0F);
/* 103 */     setRotation(this.RightWing, 0.0F, -1.047198F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 109 */     MoCEntityFirefly entityfirefly = (MoCEntityFirefly)entity;
/* 110 */     boolean isFlying = (entityfirefly.getIsFlying() || entityfirefly.motionY < -0.1D);
/*     */     
/* 112 */     setRotationAngles(f, f1, f2, f3, f4, f5, isFlying);
/* 113 */     this.Antenna.render(f5);
/* 114 */     this.RearLegs.render(f5);
/* 115 */     this.MidLegs.render(f5);
/* 116 */     this.Head.render(f5);
/*     */     
/* 118 */     this.Abdomen.render(f5);
/* 119 */     this.FrontLegs.render(f5);
/* 120 */     this.Thorax.render(f5);
/* 121 */     this.Tail.render(f5);
/*     */     
/* 123 */     if (!isFlying) {
/* 124 */       this.RightShell.render(f5);
/* 125 */       this.LeftShell.render(f5);
/*     */     } else {
/* 127 */       this.RightShellOpen.render(f5);
/* 128 */       this.LeftShellOpen.render(f5);
/*     */       
/* 130 */       GL11.glPushMatrix();
/* 131 */       GL11.glEnable(3042);
/* 132 */       float transparency = 0.6F;
/* 133 */       GL11.glBlendFunc(770, 771);
/* 134 */       GL11.glColor4f(0.8F, 0.8F, 0.8F, transparency);
/* 135 */       this.LeftWing.render(f5);
/* 136 */       this.RightWing.render(f5);
/* 137 */       GL11.glDisable(3042);
/* 138 */       GL11.glPopMatrix();
/*     */     } 
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
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 165 */     model.rotateAngleX = x;
/* 166 */     model.rotateAngleY = y;
/* 167 */     model.rotateAngleZ = z;
/*     */   }
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, boolean isFlying) {
/* 171 */     float legMov = 0.0F;
/* 172 */     float legMovB = 0.0F;
/*     */     
/* 174 */     float frontLegAdj = 0.0F;
/* 175 */     if (isFlying) {
/* 176 */       float WingRot = MathHelper.cos(f2 * 1.8F) * 0.8F;
/* 177 */       this.RightWing.rotateAngleZ = WingRot;
/* 178 */       this.LeftWing.rotateAngleZ = -WingRot;
/* 179 */       legMov = f1 * 1.5F;
/* 180 */       legMovB = legMov;
/* 181 */       frontLegAdj = 1.4F;
/*     */     } else {
/*     */       
/* 184 */       legMov = MathHelper.cos(f * 1.5F + 3.141593F) * 2.0F * f1;
/* 185 */       legMovB = MathHelper.cos(f * 1.5F) * 2.0F * f1;
/*     */     } 
/* 187 */     this.FrontLegs.rotateAngleX = -0.8328009F + frontLegAdj + legMov;
/* 188 */     this.MidLegs.rotateAngleX = 1.070744F + legMovB;
/* 189 */     this.RearLegs.rotateAngleX = 1.249201F + legMov;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelFirefly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */