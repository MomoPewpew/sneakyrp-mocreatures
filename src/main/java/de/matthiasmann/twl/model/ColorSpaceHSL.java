/*     */ package de.matthiasmann.twl.model;
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
/*     */ public class ColorSpaceHSL
/*     */   extends AbstractColorSpace
/*     */ {
/*     */   public ColorSpaceHSL() {
/*  43 */     super("HSL", new String[] { "Hue", "Saturation", "Lightness" });
/*     */   }
/*     */   
/*     */   public String getComponentShortName(int component) {
/*  47 */     return "HSL".substring(component, component + 1);
/*     */   }
/*     */   
/*     */   public float getMaxValue(int component) {
/*  51 */     return (component == 0) ? 360.0F : 100.0F;
/*     */   }
/*     */   
/*     */   public float getDefaultValue(int component) {
/*  55 */     return (component == 0) ? 0.0F : 50.0F;
/*     */   }
/*     */   
/*     */   public float[] fromRGB(int rgb) {
/*  59 */     float r = (rgb >> 16 & 0xFF) / 255.0F;
/*  60 */     float g = (rgb >> 8 & 0xFF) / 255.0F;
/*  61 */     float b = (rgb & 0xFF) / 255.0F;
/*     */     
/*  63 */     float max = Math.max(Math.max(r, g), b);
/*  64 */     float min = Math.min(Math.min(r, g), b);
/*     */     
/*  66 */     float summe = max + min;
/*  67 */     float saturation = max - min;
/*     */     
/*  69 */     if (saturation > 0.0F) {
/*  70 */       saturation /= (summe > 1.0F) ? (2.0F - summe) : summe;
/*     */     }
/*  72 */     return new float[] { 360.0F * getHue(r, g, b, max, min), 100.0F * saturation, 50.0F * summe };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int toRGB(float[] color) {
/*  79 */     float r, g, b, hue = color[0] / 360.0F;
/*  80 */     float saturation = color[1] / 100.0F;
/*  81 */     float lightness = color[2] / 100.0F;
/*     */ 
/*     */ 
/*     */     
/*  85 */     if (saturation > 0.0F) {
/*  86 */       hue = (hue < 1.0F) ? (hue * 6.0F) : 0.0F;
/*  87 */       float q = lightness + saturation * ((lightness > 0.5F) ? (1.0F - lightness) : lightness);
/*  88 */       float p = 2.0F * lightness - q;
/*  89 */       r = normalize(q, p, (hue < 4.0F) ? (hue + 2.0F) : (hue - 4.0F));
/*  90 */       g = normalize(q, p, hue);
/*  91 */       b = normalize(q, p, (hue < 2.0F) ? (hue + 4.0F) : (hue - 2.0F));
/*     */     } else {
/*  93 */       r = g = b = lightness;
/*     */     } 
/*     */     
/*  96 */     return toByte(r) << 16 | toByte(g) << 8 | toByte(b);
/*     */   }
/*     */   
/*     */   static float getHue(float red, float green, float blue, float max, float min) {
/* 100 */     float hue = max - min;
/* 101 */     if (hue > 0.0F) {
/* 102 */       if (max == red) {
/* 103 */         hue = (green - blue) / hue;
/* 104 */         if (hue < 0.0F) {
/* 105 */           hue += 6.0F;
/*     */         }
/* 107 */       } else if (max == green) {
/* 108 */         hue = 2.0F + (blue - red) / hue;
/*     */       } else {
/* 110 */         hue = 4.0F + (red - green) / hue;
/*     */       } 
/* 112 */       hue /= 6.0F;
/*     */     } 
/* 114 */     return hue;
/*     */   }
/*     */   
/*     */   private static float normalize(float q, float p, float color) {
/* 118 */     if (color < 1.0F) {
/* 119 */       return p + (q - p) * color;
/*     */     }
/* 121 */     if (color < 3.0F) {
/* 122 */       return q;
/*     */     }
/* 124 */     if (color < 4.0F) {
/* 125 */       return p + (q - p) * (4.0F - color);
/*     */     }
/* 127 */     return p;
/*     */   }
/*     */   
/*     */   private static int toByte(float value) {
/* 131 */     return Math.max(0, Math.min(255, (int)(255.0F * value)));
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\ColorSpaceHSL.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */