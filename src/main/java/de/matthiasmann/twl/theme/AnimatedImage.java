/*     */ package de.matthiasmann.twl.theme;
/*     */ 
/*     */ import de.matthiasmann.twl.Border;
/*     */ import de.matthiasmann.twl.Color;
/*     */ import de.matthiasmann.twl.renderer.AnimationState;
/*     */ import de.matthiasmann.twl.renderer.Image;
/*     */ import de.matthiasmann.twl.renderer.Renderer;
/*     */ 
/*     */ public class AnimatedImage
/*     */   implements Image, HasBorder {
/*     */   final Renderer renderer;
/*     */   final Element root;
/*     */   final AnimationState.StateKey timeSource;
/*     */   final Border border;
/*     */   final float r;
/*     */   final float g;
/*     */   final float b;
/*     */   final float a;
/*     */   final int width;
/*     */   final int height;
/*     */   final int frozenTime;
/*     */   
/*     */   static abstract class Element {
/*     */     int duration;
/*     */     
/*     */     abstract int getWidth();
/*     */     
/*     */     abstract int getHeight();
/*     */     
/*     */     abstract AnimatedImage.Img getFirstImg();
/*     */     
/*     */     abstract void render(int param1Int1, AnimatedImage.Img param1Img, int param1Int2, int param1Int3, int param1Int4, int param1Int5, AnimatedImage param1AnimatedImage, AnimationState param1AnimationState);
/*     */   }
/*     */   
/*     */   static class Img
/*     */     extends Element {
/*     */     final Image image;
/*     */     final float r;
/*     */     final float g;
/*     */     final float b;
/*     */     
/*     */     Img(int duration, Image image, Color tintColor, float zoomX, float zoomY, float zoomCenterX, float zoomCenterY) {
/*  43 */       if (duration < 0) {
/*  44 */         throw new IllegalArgumentException("duration");
/*     */       }
/*  46 */       this.duration = duration;
/*  47 */       this.image = image;
/*  48 */       this.r = tintColor.getRedFloat();
/*  49 */       this.g = tintColor.getGreenFloat();
/*  50 */       this.b = tintColor.getBlueFloat();
/*  51 */       this.a = tintColor.getAlphaFloat();
/*  52 */       this.zoomX = zoomX;
/*  53 */       this.zoomY = zoomY;
/*  54 */       this.zoomCenterX = zoomCenterX;
/*  55 */       this.zoomCenterY = zoomCenterY;
/*     */     }
/*     */     final float a; final float zoomX; final float zoomY; final float zoomCenterX; final float zoomCenterY;
/*     */     int getWidth() {
/*  59 */       return this.image.getWidth();
/*     */     }
/*     */     
/*     */     int getHeight() {
/*  63 */       return this.image.getHeight();
/*     */     }
/*     */     
/*     */     Img getFirstImg() {
/*  67 */       return this;
/*     */     }
/*     */     
/*     */     void render(int time, Img next, int x, int y, int width, int height, AnimatedImage ai, AnimationState as) {
/*  71 */       float rr = this.r, gg = this.g, bb = this.b, aa = this.a;
/*  72 */       float zx = this.zoomX, zy = this.zoomY, cx = this.zoomCenterX, cy = this.zoomCenterY;
/*  73 */       if (next != null) {
/*  74 */         float t = time / this.duration;
/*  75 */         rr = blend(rr, next.r, t);
/*  76 */         gg = blend(gg, next.g, t);
/*  77 */         bb = blend(bb, next.b, t);
/*  78 */         aa = blend(aa, next.a, t);
/*  79 */         zx = blend(zx, next.zoomX, t);
/*  80 */         zy = blend(zy, next.zoomY, t);
/*  81 */         cx = blend(cx, next.zoomCenterX, t);
/*  82 */         cy = blend(cy, next.zoomCenterY, t);
/*     */       } 
/*  84 */       ai.renderer.pushGlobalTintColor(rr * ai.r, gg * ai.g, bb * ai.b, aa * ai.a);
/*     */       try {
/*  86 */         int zWidth = (int)(width * zx);
/*  87 */         int zHeight = (int)(height * zy);
/*  88 */         this.image.draw(as, x + (int)((width - zWidth) * cx), y + (int)((height - zHeight) * cy), zWidth, zHeight);
/*     */       
/*     */       }
/*     */       finally {
/*     */         
/*  93 */         ai.renderer.popGlobalTintColor();
/*     */       } 
/*     */     }
/*     */     
/*     */     private static float blend(float a, float b, float t) {
/*  98 */       return a + (b - a) * t;
/*     */     }
/*     */   }
/*     */   
/*     */   static class Repeat extends Element {
/*     */     final AnimatedImage.Element[] children;
/*     */     final int repeatCount;
/*     */     final int singleDuration;
/*     */     
/*     */     Repeat(AnimatedImage.Element[] children, int repeatCount) {
/* 108 */       this.children = children;
/* 109 */       this.repeatCount = repeatCount;
/* 110 */       assert repeatCount >= 0;
/* 111 */       assert children.length > 0;
/*     */       
/* 113 */       for (AnimatedImage.Element e : children) {
/* 114 */         this.duration += e.duration;
/*     */       }
/* 116 */       this.singleDuration = this.duration;
/* 117 */       if (repeatCount == 0) {
/* 118 */         this.duration = Integer.MAX_VALUE;
/*     */       } else {
/* 120 */         this.duration *= repeatCount;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     int getHeight() {
/* 126 */       int tmp = 0;
/* 127 */       for (AnimatedImage.Element e : this.children) {
/* 128 */         tmp = Math.max(tmp, e.getHeight());
/*     */       }
/* 130 */       return tmp;
/*     */     }
/*     */ 
/*     */     
/*     */     int getWidth() {
/* 135 */       int tmp = 0;
/* 136 */       for (AnimatedImage.Element e : this.children) {
/* 137 */         tmp = Math.max(tmp, e.getWidth());
/*     */       }
/* 139 */       return tmp;
/*     */     }
/*     */     
/*     */     AnimatedImage.Img getFirstImg() {
/* 143 */       return this.children[0].getFirstImg();
/*     */     }
/*     */     
/*     */     void render(int time, AnimatedImage.Img next, int x, int y, int width, int height, AnimatedImage ai, AnimationState as) {
/* 147 */       if (this.singleDuration == 0) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 152 */       int iteration = 0;
/* 153 */       if (this.repeatCount == 0) {
/* 154 */         time %= this.singleDuration;
/*     */       } else {
/* 156 */         iteration = time / this.singleDuration;
/* 157 */         time -= Math.min(iteration, this.repeatCount - 1) * this.singleDuration;
/*     */       } 
/*     */       
/* 160 */       AnimatedImage.Element e = null;
/* 161 */       for (int i = 0; i < this.children.length; i++) {
/* 162 */         e = this.children[i];
/* 163 */         if (time < e.duration && e.duration > 0) {
/* 164 */           if (i + 1 < this.children.length) {
/* 165 */             next = this.children[i + 1].getFirstImg(); break;
/* 166 */           }  if (this.repeatCount == 0 || iteration + 1 < this.repeatCount) {
/* 167 */             next = getFirstImg();
/*     */           }
/*     */           
/*     */           break;
/*     */         } 
/* 172 */         time -= e.duration;
/*     */       } 
/*     */       
/* 175 */       if (e != null) {
/* 176 */         e.render(time, next, x, y, width, height, ai, as);
/*     */       }
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
/*     */   AnimatedImage(Renderer renderer, Element root, String timeSource, Border border, Color tintColor, int frozenTime) {
/* 194 */     this.renderer = renderer;
/* 195 */     this.root = root;
/* 196 */     this.timeSource = AnimationState.StateKey.get(timeSource);
/* 197 */     this.border = border;
/* 198 */     this.r = tintColor.getRedFloat();
/* 199 */     this.g = tintColor.getGreenFloat();
/* 200 */     this.b = tintColor.getBlueFloat();
/* 201 */     this.a = tintColor.getAlphaFloat();
/* 202 */     this.width = root.getWidth();
/* 203 */     this.height = root.getHeight();
/* 204 */     this.frozenTime = frozenTime;
/*     */   }
/*     */   
/*     */   AnimatedImage(AnimatedImage src, Color tintColor) {
/* 208 */     this.renderer = src.renderer;
/* 209 */     this.root = src.root;
/* 210 */     this.timeSource = src.timeSource;
/* 211 */     this.border = src.border;
/* 212 */     src.r *= tintColor.getRedFloat();
/* 213 */     src.g *= tintColor.getGreenFloat();
/* 214 */     src.b *= tintColor.getBlueFloat();
/* 215 */     src.a *= tintColor.getAlphaFloat();
/* 216 */     this.width = src.width;
/* 217 */     this.height = src.height;
/* 218 */     this.frozenTime = src.frozenTime;
/*     */   }
/*     */   
/*     */   public int getWidth() {
/* 222 */     return this.width;
/*     */   }
/*     */   
/*     */   public int getHeight() {
/* 226 */     return this.height;
/*     */   }
/*     */   
/*     */   public void draw(AnimationState as, int x, int y) {
/* 230 */     draw(as, x, y, this.width, this.height);
/*     */   }
/*     */   
/*     */   public void draw(AnimationState as, int x, int y, int width, int height) {
/* 234 */     int time = 0;
/* 235 */     if (as != null) {
/* 236 */       if (this.frozenTime < 0 || as.getShouldAnimateState(this.timeSource)) {
/* 237 */         time = as.getAnimationTime(this.timeSource);
/*     */       } else {
/* 239 */         time = this.frozenTime;
/*     */       } 
/*     */     }
/* 242 */     this.root.render(time, null, x, y, width, height, this, as);
/*     */   }
/*     */   
/*     */   public Border getBorder() {
/* 246 */     return this.border;
/*     */   }
/*     */   
/*     */   public Image createTintedVersion(Color color) {
/* 250 */     return new AnimatedImage(this, color);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\theme\AnimatedImage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */