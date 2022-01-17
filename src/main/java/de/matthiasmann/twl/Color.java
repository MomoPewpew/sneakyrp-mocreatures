/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.Locale;
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
/*     */ public final class Color
/*     */ {
/*  43 */   public static final Color BLACK = new Color(-16777216);
/*  44 */   public static final Color SILVER = new Color(-4144960);
/*  45 */   public static final Color GRAY = new Color(-8355712);
/*  46 */   public static final Color WHITE = new Color(-1);
/*  47 */   public static final Color MAROON = new Color(-8388608);
/*  48 */   public static final Color RED = new Color(-65536);
/*  49 */   public static final Color PURPLE = new Color(-8388480);
/*  50 */   public static final Color FUCHSIA = new Color(-65281);
/*  51 */   public static final Color GREEN = new Color(-16744448);
/*  52 */   public static final Color LIME = new Color(-16711936);
/*  53 */   public static final Color OLIVE = new Color(-8355840);
/*  54 */   public static final Color ORANGE = new Color(-23296);
/*  55 */   public static final Color YELLOW = new Color(-256);
/*  56 */   public static final Color NAVY = new Color(-16777088);
/*  57 */   public static final Color BLUE = new Color(-16776961);
/*  58 */   public static final Color TEAL = new Color(-16744320);
/*  59 */   public static final Color AQUA = new Color(-16711681);
/*  60 */   public static final Color SKYBLUE = new Color(-7876885);
/*     */   
/*  62 */   public static final Color LIGHTBLUE = new Color(-5383962);
/*  63 */   public static final Color LIGHTCORAL = new Color(-1015680);
/*  64 */   public static final Color LIGHTCYAN = new Color(-2031617);
/*  65 */   public static final Color LIGHTGRAY = new Color(-2894893);
/*  66 */   public static final Color LIGHTGREEN = new Color(-7278960);
/*  67 */   public static final Color LIGHTPINK = new Color(-18751);
/*  68 */   public static final Color LIGHTSALMON = new Color(-24454);
/*  69 */   public static final Color LIGHTSKYBLUE = new Color(-7876870);
/*  70 */   public static final Color LIGHTYELLOW = new Color(-32);
/*     */   
/*  72 */   public static final Color TRANSPARENT = new Color(0);
/*     */   
/*     */   private final byte r;
/*     */   private final byte g;
/*     */   private final byte b;
/*     */   private final byte a;
/*     */   
/*     */   public Color(byte r, byte g, byte b, byte a) {
/*  80 */     this.r = r;
/*  81 */     this.g = g;
/*  82 */     this.b = b;
/*  83 */     this.a = a;
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
/*     */   public Color(int argb) {
/*  97 */     this.a = (byte)(argb >> 24);
/*  98 */     this.r = (byte)(argb >> 16);
/*  99 */     this.g = (byte)(argb >> 8);
/* 100 */     this.b = (byte)argb;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int toARGB() {
/* 110 */     return (this.a & 0xFF) << 24 | (this.r & 0xFF) << 16 | (this.g & 0xFF) << 8 | this.b & 0xFF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getR() {
/* 117 */     return this.r;
/*     */   }
/*     */   
/*     */   public byte getG() {
/* 121 */     return this.g;
/*     */   }
/*     */   
/*     */   public byte getB() {
/* 125 */     return this.b;
/*     */   }
/*     */   
/*     */   public byte getA() {
/* 129 */     return this.a;
/*     */   }
/*     */   
/*     */   public int getRed() {
/* 133 */     return this.r & 0xFF;
/*     */   }
/*     */   
/*     */   public int getGreen() {
/* 137 */     return this.g & 0xFF;
/*     */   }
/*     */   
/*     */   public int getBlue() {
/* 141 */     return this.b & 0xFF;
/*     */   }
/*     */   
/*     */   public int getAlpha() {
/* 145 */     return this.a & 0xFF;
/*     */   }
/*     */   
/*     */   public float getRedFloat() {
/* 149 */     return (this.r & 0xFF) * 0.003921569F;
/*     */   }
/*     */   
/*     */   public float getGreenFloat() {
/* 153 */     return (this.g & 0xFF) * 0.003921569F;
/*     */   }
/*     */   
/*     */   public float getBlueFloat() {
/* 157 */     return (this.b & 0xFF) * 0.003921569F;
/*     */   }
/*     */   
/*     */   public float getAlphaFloat() {
/* 161 */     return (this.a & 0xFF) * 0.003921569F;
/*     */   }
/*     */   
/*     */   public void getFloats(float[] dst, int off) {
/* 165 */     dst[off + 0] = getRedFloat();
/* 166 */     dst[off + 1] = getGreenFloat();
/* 167 */     dst[off + 2] = getBlueFloat();
/* 168 */     dst[off + 3] = getAlphaFloat();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Color getColorByName(String name) {
/* 179 */     name = name.toUpperCase(Locale.ENGLISH);
/*     */     try {
/* 181 */       Field f = Color.class.getField(name);
/* 182 */       if (Modifier.isStatic(f.getModifiers()) && f.getType() == Color.class) {
/* 183 */         return (Color)f.get(null);
/*     */       }
/* 185 */     } catch (Throwable ex) {}
/*     */ 
/*     */     
/* 188 */     return null;
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
/*     */   public static Color parserColor(String value) throws NumberFormatException {
/* 206 */     if (value.length() > 0 && value.charAt(0) == '#') {
/* 207 */       int rgb4, r, a, g, i, b, j, k; String hexcode = value.substring(1);
/* 208 */       switch (value.length()) {
/*     */         case 4:
/* 210 */           rgb4 = Integer.parseInt(hexcode, 16);
/* 211 */           r = (rgb4 >> 8 & 0xF) * 17;
/* 212 */           g = (rgb4 >> 4 & 0xF) * 17;
/* 213 */           b = (rgb4 & 0xF) * 17;
/* 214 */           return new Color(0xFF000000 | r << 16 | g << 8 | b);
/*     */         
/*     */         case 5:
/* 217 */           rgb4 = Integer.parseInt(hexcode, 16);
/* 218 */           a = (rgb4 >> 12 & 0xF) * 17;
/* 219 */           i = (rgb4 >> 8 & 0xF) * 17;
/* 220 */           j = (rgb4 >> 4 & 0xF) * 17;
/* 221 */           k = (rgb4 & 0xF) * 17;
/* 222 */           return new Color(a << 24 | i << 16 | j << 8 | k);
/*     */         
/*     */         case 7:
/* 225 */           return new Color(0xFF000000 | Integer.parseInt(hexcode, 16));
/*     */         case 9:
/* 227 */           return new Color((int)Long.parseLong(hexcode, 16));
/*     */       } 
/* 229 */       throw new NumberFormatException("Can't parse '" + value + "' as hex color");
/*     */     } 
/*     */     
/* 232 */     return getColorByName(value);
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
/*     */   public String toString() {
/* 245 */     if (this.a != -1) {
/* 246 */       return String.format("#%08X", new Object[] { Integer.valueOf(toARGB()) });
/*     */     }
/* 248 */     return String.format("#%06X", new Object[] { Integer.valueOf(toARGB() & 0xFFFFFF) });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 254 */     if (!(obj instanceof Color)) {
/* 255 */       return false;
/*     */     }
/* 257 */     Color other = (Color)obj;
/* 258 */     return (toARGB() == other.toARGB());
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 263 */     return toARGB();
/*     */   }
/*     */   
/*     */   public Color multiply(Color other) {
/* 267 */     return new Color(mul(this.r, other.r), mul(this.g, other.g), mul(this.b, other.b), mul(this.a, other.a));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private byte mul(byte a, byte b) {
/* 275 */     return (byte)((a & 0xFF) * (b & 0xFF) / 255);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\Color.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */