/*     */ package de.matthiasmann.twl.renderer.lwjgl;
/*     */ 
/*     */ import de.matthiasmann.twl.Color;
/*     */ import de.matthiasmann.twl.renderer.AnimationState;
/*     */ import de.matthiasmann.twl.renderer.Gradient;
/*     */ import de.matthiasmann.twl.renderer.Image;
/*     */ import org.lwjgl.opengl.GL11;
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
/*     */ public class GradientImage
/*     */   implements Image
/*     */ {
/*     */   private final LWJGLRenderer renderer;
/*     */   private final Gradient.Type type;
/*     */   private final Gradient.Wrap wrap;
/*     */   private final Gradient.Stop[] stops;
/*     */   private final Color tint;
/*     */   private final float endPos;
/*     */   
/*     */   GradientImage(GradientImage src, Color tint) {
/*  55 */     this.renderer = src.renderer;
/*  56 */     this.type = src.type;
/*  57 */     this.wrap = src.wrap;
/*  58 */     this.stops = src.stops;
/*  59 */     this.endPos = src.endPos;
/*  60 */     this.tint = tint;
/*     */   }
/*     */   
/*     */   public GradientImage(LWJGLRenderer renderer, Gradient gradient) {
/*  64 */     if (gradient == null) {
/*  65 */       throw new NullPointerException("gradient");
/*     */     }
/*  67 */     if (gradient.getNumStops() < 1) {
/*  68 */       throw new IllegalArgumentException("Need at least 1 stop for a gradient");
/*     */     }
/*     */     
/*  71 */     this.renderer = renderer;
/*  72 */     this.type = gradient.getType();
/*  73 */     this.tint = Color.WHITE;
/*  74 */     if (gradient.getNumStops() == 1) {
/*  75 */       Color color = gradient.getStop(0).getColor();
/*  76 */       this.wrap = Gradient.Wrap.SCALE;
/*  77 */       this.stops = new Gradient.Stop[] { new Gradient.Stop(0.0F, color), new Gradient.Stop(1.0F, color) };
/*     */ 
/*     */ 
/*     */       
/*  81 */       this.endPos = 1.0F;
/*  82 */     } else if (gradient.getWrap() == Gradient.Wrap.MIRROR) {
/*  83 */       int numStops = gradient.getNumStops();
/*  84 */       this.wrap = Gradient.Wrap.REPEAT;
/*  85 */       this.stops = new Gradient.Stop[numStops * 2 - 1]; int i;
/*  86 */       for (i = 0; i < numStops; i++) {
/*  87 */         this.stops[i] = gradient.getStop(i);
/*     */       }
/*  89 */       this.endPos = this.stops[numStops - 1].getPos() * 2.0F; int j;
/*  90 */       for (i = numStops, j = numStops - 2; j >= 0; i++, j--) {
/*  91 */         this.stops[i] = new Gradient.Stop(this.endPos - this.stops[j].getPos(), this.stops[j].getColor());
/*     */       }
/*     */     } else {
/*  94 */       this.wrap = gradient.getWrap();
/*  95 */       this.stops = gradient.getStops();
/*  96 */       this.endPos = this.stops[this.stops.length - 1].getPos();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Image createTintedVersion(Color color) {
/* 102 */     return new GradientImage(this, this.tint.multiply(color));
/*     */   }
/*     */   
/*     */   private boolean isHorz() {
/* 106 */     return (this.type == Gradient.Type.HORIZONTAL);
/*     */   }
/*     */   
/*     */   private int getLastPos() {
/* 110 */     return Math.round(this.stops[this.stops.length - 1].getPos());
/*     */   }
/*     */   
/*     */   public int getHeight() {
/* 114 */     return isHorz() ? 1 : getLastPos();
/*     */   }
/*     */   
/*     */   public int getWidth() {
/* 118 */     return isHorz() ? getLastPos() : 1;
/*     */   }
/*     */   
/*     */   public void draw(AnimationState as, int x, int y) {
/* 122 */     if (isHorz()) {
/* 123 */       drawHorz(x, y, getLastPos(), 1);
/*     */     } else {
/* 125 */       drawVert(x, y, 1, getLastPos());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void draw(AnimationState as, int x, int y, int width, int height) {
/* 130 */     if (isHorz()) {
/* 131 */       drawHorz(x, y, width, height);
/*     */     } else {
/* 133 */       drawVert(x, y, width, height);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawHorz(int x, int y, int width, int height) {
/* 138 */     if (width <= 0 || height <= 0) {
/*     */       return;
/*     */     }
/* 141 */     TintStack tintStack = this.renderer.tintStack.push(this.tint);
/* 142 */     GL11.glDisable(3553);
/* 143 */     GL11.glBegin(8);
/* 144 */     if (this.wrap == Gradient.Wrap.SCALE) {
/* 145 */       for (Gradient.Stop stop : this.stops) {
/* 146 */         tintStack.setColor(stop.getColor());
/* 147 */         float pos = stop.getPos() * width / this.endPos;
/* 148 */         GL11.glVertex2f(x + pos, y);
/* 149 */         GL11.glVertex2f(x + pos, (y + height));
/*     */       } 
/*     */     } else {
/* 152 */       float lastPos = 0.0F;
/* 153 */       float offset = 0.0F;
/* 154 */       Color lastColor = this.stops[0].getColor();
/*     */       label30: do {
/* 156 */         for (Gradient.Stop stop : this.stops) {
/* 157 */           float pos = stop.getPos() + offset;
/* 158 */           Color color = stop.getColor();
/* 159 */           if (pos >= width) {
/* 160 */             float t = (width - lastPos) / (pos - lastPos);
/* 161 */             setColor(tintStack, lastColor, color, t);
/*     */             break label30;
/*     */           } 
/* 164 */           tintStack.setColor(color);
/* 165 */           GL11.glVertex2f(x + pos, y);
/* 166 */           GL11.glVertex2f(x + pos, (y + height));
/* 167 */           lastPos = pos;
/* 168 */           lastColor = color;
/*     */         } 
/* 170 */         offset += this.endPos;
/* 171 */       } while (this.wrap == Gradient.Wrap.REPEAT);
/* 172 */       GL11.glVertex2f((x + width), y);
/* 173 */       GL11.glVertex2f((x + width), (y + height));
/*     */     } 
/* 175 */     GL11.glEnd();
/* 176 */     GL11.glEnable(3553);
/*     */   }
/*     */   
/*     */   private void drawVert(int x, int y, int width, int height) {
/* 180 */     if (width <= 0 || height <= 0) {
/*     */       return;
/*     */     }
/* 183 */     TintStack tintStack = this.renderer.tintStack.push(this.tint);
/* 184 */     GL11.glDisable(3553);
/* 185 */     GL11.glBegin(8);
/* 186 */     if (this.wrap == Gradient.Wrap.SCALE) {
/* 187 */       for (Gradient.Stop stop : this.stops) {
/* 188 */         tintStack.setColor(stop.getColor());
/* 189 */         float pos = stop.getPos() * height / this.endPos;
/* 190 */         GL11.glVertex2f(x, y + pos);
/* 191 */         GL11.glVertex2f((x + width), y + pos);
/*     */       } 
/*     */     } else {
/* 194 */       float lastPos = 0.0F;
/* 195 */       float offset = 0.0F;
/* 196 */       Color lastColor = this.stops[0].getColor();
/*     */       label30: do {
/* 198 */         for (Gradient.Stop stop : this.stops) {
/* 199 */           float pos = stop.getPos() + offset;
/* 200 */           Color color = stop.getColor();
/* 201 */           if (pos >= height) {
/* 202 */             float t = (height - lastPos) / (pos - lastPos);
/* 203 */             setColor(tintStack, lastColor, color, t);
/*     */             break label30;
/*     */           } 
/* 206 */           tintStack.setColor(color);
/* 207 */           GL11.glVertex2f(x, y + pos);
/* 208 */           GL11.glVertex2f((x + width), y + pos);
/* 209 */           lastPos = pos;
/* 210 */           lastColor = color;
/*     */         } 
/* 212 */         offset += this.endPos;
/* 213 */       } while (this.wrap == Gradient.Wrap.REPEAT);
/* 214 */       GL11.glVertex2f(x, (y + height));
/* 215 */       GL11.glVertex2f((x + width), (y + height));
/*     */     } 
/* 217 */     GL11.glEnd();
/* 218 */     GL11.glEnable(3553);
/*     */   }
/*     */   
/*     */   private static void setColor(TintStack tintStack, Color a, Color b, float t) {
/* 222 */     tintStack.setColor(mix(a.getRed(), b.getRed(), t), mix(a.getGreen(), b.getGreen(), t), mix(a.getBlue(), b.getBlue(), t), mix(a.getAlpha(), b.getAlpha(), t));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static float mix(int a, int b, float t) {
/* 230 */     return a + (b - a) * t;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\lwjgl\GradientImage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */