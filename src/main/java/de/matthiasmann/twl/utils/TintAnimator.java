/*     */ package de.matthiasmann.twl.utils;
/*     */ 
/*     */ import de.matthiasmann.twl.AnimationState;
/*     */ import de.matthiasmann.twl.Color;
/*     */ import de.matthiasmann.twl.GUI;
/*     */ import de.matthiasmann.twl.Widget;
/*     */ import de.matthiasmann.twl.renderer.AnimationState;
/*     */ import de.matthiasmann.twl.renderer.Renderer;
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
/*     */ public class TintAnimator
/*     */ {
/*     */   private static final float ZERO_EPSILON = 0.001F;
/*     */   private static final float ONE_EPSILON = 0.999F;
/*     */   private final TimeSource timeSource;
/*     */   private final float[] currentTint;
/*     */   private int fadeDuration;
/*     */   private boolean fadeActive;
/*     */   private boolean hasTint;
/*     */   private Runnable[] fadeDoneCallbacks;
/*     */   
/*     */   public TintAnimator(TimeSource timeSource, Color color) {
/*  79 */     if (timeSource == null) {
/*  80 */       throw new NullPointerException("timeSource");
/*     */     }
/*  82 */     if (color == null) {
/*  83 */       throw new NullPointerException("color");
/*     */     }
/*  85 */     this.timeSource = timeSource;
/*  86 */     this.currentTint = new float[12];
/*  87 */     setColor(color);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TintAnimator(GUI gui, Color color) {
/*  98 */     this(new GUITimeSource(gui), color);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TintAnimator(Widget owner, Color color) {
/* 109 */     this(new GUITimeSource(owner), color);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TintAnimator(TimeSource timeSource) {
/* 118 */     this(timeSource, Color.WHITE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TintAnimator(GUI gui) {
/* 129 */     this(new GUITimeSource(gui));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TintAnimator(Widget owner) {
/* 140 */     this(new GUITimeSource(owner));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColor(Color color) {
/* 150 */     color.getFloats(this.currentTint, 0);
/* 151 */     color.getFloats(this.currentTint, 4);
/* 152 */     this.hasTint = !Color.WHITE.equals(color);
/* 153 */     this.fadeActive = false;
/* 154 */     this.fadeDuration = 0;
/* 155 */     this.timeSource.resetTime();
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
/*     */   public void addFadeDoneCallback(Runnable cb) {
/* 167 */     this.fadeDoneCallbacks = CallbackSupport.<Runnable>addCallbackToList(this.fadeDoneCallbacks, cb, Runnable.class);
/*     */   }
/*     */   
/*     */   public void removeFadeDoneCallback(Runnable cb) {
/* 171 */     this.fadeDoneCallbacks = CallbackSupport.<Runnable>removeCallbackFromList(this.fadeDoneCallbacks, cb);
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
/*     */   public void fadeTo(Color color, int fadeDuration) {
/* 188 */     if (fadeDuration <= 0) {
/* 189 */       setColor(color);
/*     */     } else {
/* 191 */       color.getFloats(this.currentTint, 8);
/* 192 */       System.arraycopy(this.currentTint, 0, this.currentTint, 4, 4);
/* 193 */       this.fadeActive = true;
/* 194 */       this.fadeDuration = fadeDuration;
/* 195 */       this.hasTint = true;
/* 196 */       this.timeSource.resetTime();
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
/*     */   public void fadeToHide(int fadeDuration) {
/* 217 */     if (fadeDuration <= 0) {
/* 218 */       this.currentTint[3] = 0.0F;
/* 219 */       this.fadeActive = false;
/* 220 */       this.fadeDuration = 0;
/* 221 */       this.hasTint = true;
/*     */     } else {
/* 223 */       System.arraycopy(this.currentTint, 0, this.currentTint, 4, 8);
/* 224 */       this.currentTint[11] = 0.0F;
/* 225 */       this.fadeActive = !isZeroAlpha();
/* 226 */       this.fadeDuration = fadeDuration;
/* 227 */       this.hasTint = true;
/* 228 */       this.timeSource.resetTime();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 236 */     if (this.fadeActive) {
/* 237 */       int time = this.timeSource.getTime();
/* 238 */       float t = Math.min(time, this.fadeDuration) / this.fadeDuration;
/* 239 */       float tm1 = 1.0F - t;
/* 240 */       float[] tint = this.currentTint;
/* 241 */       for (int i = 0; i < 4; i++) {
/* 242 */         tint[i] = tm1 * tint[i + 4] + t * tint[i + 8];
/*     */       }
/* 244 */       if (time >= this.fadeDuration) {
/* 245 */         this.fadeActive = false;
/*     */         
/* 247 */         this.hasTint = (this.currentTint[0] < 0.999F || this.currentTint[1] < 0.999F || this.currentTint[2] < 0.999F || this.currentTint[3] < 0.999F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 253 */         CallbackSupport.fireCallbacks(this.fadeDoneCallbacks);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFadeActive() {
/* 263 */     return this.fadeActive;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasTint() {
/* 271 */     return this.hasTint;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isZeroAlpha() {
/* 279 */     return (this.currentTint[3] <= 0.001F);
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
/*     */   public void paintWithTint(Renderer renderer) {
/* 293 */     float[] tint = this.currentTint;
/* 294 */     renderer.pushGlobalTintColor(tint[0], tint[1], tint[2], tint[3]);
/*     */   }
/*     */   
/*     */   public static interface TimeSource
/*     */   {
/*     */     void resetTime();
/*     */     
/*     */     int getTime();
/*     */   }
/*     */   
/*     */   public static final class GUITimeSource
/*     */     implements TimeSource {
/*     */     private final Widget owner;
/*     */     private final GUI gui;
/*     */     private long startTime;
/*     */     private boolean pendingReset;
/*     */     
/*     */     public GUITimeSource(Widget owner) {
/* 312 */       if (owner == null) {
/* 313 */         throw new NullPointerException("owner");
/*     */       }
/* 315 */       this.owner = owner;
/* 316 */       this.gui = null;
/* 317 */       resetTime();
/*     */     }
/*     */     
/*     */     public GUITimeSource(GUI gui) {
/* 321 */       if (gui == null) {
/* 322 */         throw new NullPointerException("gui");
/*     */       }
/* 324 */       this.owner = null;
/* 325 */       this.gui = gui;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getTime() {
/* 330 */       GUI g = getGUI();
/* 331 */       if (g != null) {
/* 332 */         if (this.pendingReset) {
/* 333 */           this.pendingReset = false;
/* 334 */           this.startTime = g.getCurrentTime();
/*     */         } 
/* 336 */         return (int)(g.getCurrentTime() - this.startTime) & Integer.MAX_VALUE;
/*     */       } 
/* 338 */       return 0;
/*     */     }
/*     */     
/*     */     public void resetTime() {
/* 342 */       GUI g = getGUI();
/* 343 */       if (g != null) {
/* 344 */         this.startTime = g.getCurrentTime();
/* 345 */         this.pendingReset = false;
/*     */       } else {
/* 347 */         this.pendingReset = true;
/*     */       } 
/*     */     }
/*     */     
/*     */     private GUI getGUI() {
/* 352 */       return (this.gui != null) ? this.gui : this.owner.getGUI();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class AnimationStateTimeSource
/*     */     implements TimeSource
/*     */   {
/*     */     private final AnimationState animState;
/*     */     private final AnimationState.StateKey animStateKey;
/*     */     
/*     */     public AnimationStateTimeSource(AnimationState animState, String animStateName) {
/* 364 */       this(animState, AnimationState.StateKey.get(animStateName));
/*     */     }
/*     */     
/*     */     public AnimationStateTimeSource(AnimationState animState, AnimationState.StateKey animStateKey) {
/* 368 */       if (animState == null) {
/* 369 */         throw new NullPointerException("animState");
/*     */       }
/* 371 */       if (animStateKey == null) {
/* 372 */         throw new NullPointerException("animStateKey");
/*     */       }
/* 374 */       this.animState = animState;
/* 375 */       this.animStateKey = animStateKey;
/*     */     }
/*     */     
/*     */     public int getTime() {
/* 379 */       return this.animState.getAnimationTime(this.animStateKey);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void resetTime() {
/* 387 */       this.animState.resetAnimationTime(this.animStateKey);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\tw\\utils\TintAnimator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */