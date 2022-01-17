/*     */ package drzhark.mocreatures.client.renderer.entity;
/*     */ 
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.client.model.MoCModelKitty;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityKitty;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MoCRenderKitty
/*     */   extends MoCRenderMoC<MoCEntityKitty> {
/*     */   protected ResourceLocation getEntityTexture(MoCEntityKitty entitykitty) {
/*  21 */     return entitykitty.getTexture();
/*     */   }
/*     */   public MoCModelKitty pussy1;
/*     */   public MoCRenderKitty(ModelBase modelkitty, float f) {
/*  25 */     super(modelkitty, f);
/*  26 */     this.pussy1 = (MoCModelKitty)modelkitty;
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRender(MoCEntityKitty entitykitty, double d, double d1, double d2, float f, float f1) {
/*  31 */     super.doRender(entitykitty, d, d1, d2, f, f1);
/*  32 */     boolean flag2 = MoCreatures.proxy.getDisplayPetIcons();
/*  33 */     if (entitykitty.renderName()) {
/*  34 */       float f2 = 1.6F;
/*  35 */       float f3 = 0.01666667F * f2;
/*  36 */       float f4 = entitykitty.getDistance(this.renderManager.renderViewEntity);
/*  37 */       if (f4 < 12.0F) {
/*  38 */         float f5 = 0.2F;
/*  39 */         if (this.pussy1.isSitting) {
/*  40 */           f5 = 0.4F;
/*     */         }
/*     */         
/*  43 */         GL11.glPushMatrix();
/*  44 */         GL11.glTranslatef((float)d + 0.0F, (float)d1 - f5, (float)d2);
/*  45 */         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/*  46 */         GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/*  47 */         GL11.glScalef(-f3, -f3, f3);
/*  48 */         GL11.glDisable(2896);
/*  49 */         Tessellator tessellator = Tessellator.getInstance();
/*     */         
/*  51 */         if (flag2 && entitykitty.getIsEmo()) {
/*  52 */           bindTexture(entitykitty.getEmoteIcon());
/*  53 */           int i = -90;
/*  54 */           int k = 32;
/*  55 */           int l = k / 2 * -1;
/*  56 */           float f9 = 0.0F;
/*  57 */           float f11 = 1.0F / k;
/*  58 */           float f12 = 1.0F / k;
/*  59 */           tessellator.getBuffer().begin(7, DefaultVertexFormats.POSITION_TEX);
/*  60 */           tessellator.getBuffer().pos(l, (i + k), f9).tex(0.0D, (k * f12)).endVertex();
/*  61 */           tessellator.getBuffer().pos((l + k), (i + k), f9).tex((k * f11), (k * f12)).endVertex();
/*  62 */           tessellator.getBuffer().pos((l + k), i, f9).tex((k * f11), 0.0D).endVertex();
/*  63 */           tessellator.getBuffer().pos(l, i, f9).tex(0.0D, 0.0D).endVertex();
/*  64 */           tessellator.draw();
/*     */         } 
/*     */         
/*  67 */         GL11.glEnable(2896);
/*  68 */         GL11.glPopMatrix();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void doRender2(MoCEntityKitty entitykitty, double d, double d1, double d2, float f, float f1) {
/*  74 */     super.doRender(entitykitty, d, d1, d2, f, f1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected float handleRotationFloat(MoCEntityKitty entitykitty, float f) {
/*  79 */     if (!entitykitty.getIsAdult()) {
/*  80 */       stretch(entitykitty);
/*     */     }
/*  82 */     return entitykitty.ticksExisted + f;
/*     */   }
/*     */   
/*     */   protected void onMaBack(MoCEntityKitty entitykitty) {
/*  86 */     GL11.glRotatef(90.0F, 0.0F, 0.0F, -1.0F);
/*     */     
/*  88 */     if (!entitykitty.world.isRemote && entitykitty.getRidingEntity() != null) {
/*  89 */       GL11.glTranslatef(-1.5F, 0.2F, -0.2F);
/*     */     } else {
/*  91 */       GL11.glTranslatef(0.1F, 0.2F, -0.2F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onTheSide(MoCEntityKitty entityliving) {
/*  97 */     GL11.glRotatef(90.0F, 0.0F, 0.0F, -1.0F);
/*  98 */     GL11.glTranslatef(0.2F, 0.0F, -0.2F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void preRenderCallback(MoCEntityKitty entitykitty, float f) {
/* 103 */     this.pussy1.isSitting = entitykitty.getIsSitting();
/* 104 */     this.pussy1.isSwinging = entitykitty.getIsSwinging();
/* 105 */     this.pussy1.swingProgress = entitykitty.swingProgress;
/* 106 */     this.pussy1.kittystate = entitykitty.getKittyState();
/* 107 */     if (entitykitty.getKittyState() == 20) {
/* 108 */       onTheSide(entitykitty);
/*     */     }
/* 110 */     if (entitykitty.climbingTree()) {
/* 111 */       rotateAnimal(entitykitty);
/*     */     }
/* 113 */     if (entitykitty.upsideDown()) {
/* 114 */       upsideDown(entitykitty);
/*     */     }
/* 116 */     if (entitykitty.onMaBack()) {
/* 117 */       onMaBack(entitykitty);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void rotateAnimal(MoCEntityKitty entitykitty) {
/* 122 */     if (!entitykitty.onGround) {
/* 123 */       GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void stretch(MoCEntityKitty entitykitty) {
/* 128 */     GL11.glScalef(entitykitty.getEdad() * 0.01F, entitykitty.getEdad() * 0.01F, entitykitty.getEdad() * 0.01F);
/*     */   }
/*     */   
/*     */   protected void upsideDown(MoCEntityKitty entitykitty) {
/* 132 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, -1.0F);
/* 133 */     GL11.glTranslatef(-0.35F, 0.0F, -0.55F);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderKitty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */