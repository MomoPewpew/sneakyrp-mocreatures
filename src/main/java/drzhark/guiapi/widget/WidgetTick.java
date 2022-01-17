/*     */ package drzhark.guiapi.widget;
/*     */ 
/*     */ import de.matthiasmann.twl.GUI;
/*     */ import de.matthiasmann.twl.Widget;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
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
/*     */ public class WidgetTick
/*     */   extends Widget
/*     */   implements IWidgetAlwaysDraw
/*     */ {
/*     */   public class DelayTick
/*     */     implements iTick
/*     */   {
/*     */     long lastTick;
/*     */     boolean removeSelf = false;
/*     */     Runnable tickCallback;
/*     */     long timeToTick;
/*     */     
/*     */     public DelayTick(Runnable callback, int delay) {
/*  40 */       if (callback == null) {
/*  41 */         throw new IllegalArgumentException("Callback cannot be null.");
/*     */       }
/*  43 */       if (delay < 0) {
/*  44 */         throw new IllegalArgumentException("Delay must be 0 or higher.");
/*     */       }
/*  46 */       this.lastTick = 0L;
/*  47 */       this.timeToTick = delay;
/*  48 */       this.tickCallback = callback;
/*     */     }
/*     */ 
/*     */     
/*     */     public void checkTick() {
/*  53 */       long millis = System.currentTimeMillis();
/*  54 */       if (this.lastTick + this.timeToTick < millis) {
/*  55 */         this.lastTick = millis;
/*  56 */         this.tickCallback.run();
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void removeSelf() {
/*  64 */       this.removeSelf = true;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean shouldRemove() {
/*  69 */       return this.removeSelf;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class FrameTick
/*     */     implements iTick
/*     */   {
/*     */     boolean removeSelf = false;
/*     */ 
/*     */ 
/*     */     
/*     */     Runnable tickCallback;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public FrameTick(Runnable callback) {
/*  90 */       if (callback == null) {
/*  91 */         throw new IllegalArgumentException("Callback cannot be null.");
/*     */       }
/*  93 */       this.tickCallback = callback;
/*     */     }
/*     */ 
/*     */     
/*     */     public void checkTick() {
/*  98 */       this.tickCallback.run();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void removeSelf() {
/* 105 */       this.removeSelf = true;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean shouldRemove() {
/* 110 */       return this.removeSelf;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 115 */       return "FrameTick [tickCallback=" + this.tickCallback + "]";
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface iTick
/*     */   {
/*     */     void checkTick();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     boolean shouldRemove();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class SingleTick
/*     */     implements iTick
/*     */   {
/*     */     int delayBeforeRemove;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Runnable tickCallback;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     long timeToTick;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SingleTick(Runnable callback, int delay) {
/* 161 */       if (callback == null) {
/* 162 */         throw new IllegalArgumentException("Callback cannot be null.");
/*     */       }
/* 164 */       if (delay < 0) {
/* 165 */         throw new IllegalArgumentException("Delay must be 0 or higher.");
/*     */       }
/* 167 */       this.timeToTick = -1L;
/* 168 */       this.delayBeforeRemove = delay;
/* 169 */       this.tickCallback = callback;
/*     */     }
/*     */ 
/*     */     
/*     */     public void checkTick() {
/* 174 */       if (this.delayBeforeRemove == 0) {
/* 175 */         this.tickCallback.run();
/*     */       }
/* 177 */       if (this.timeToTick == -1L) {
/* 178 */         this.timeToTick = System.currentTimeMillis() + this.delayBeforeRemove;
/*     */       }
/* 180 */       else if (this.timeToTick < System.currentTimeMillis()) {
/* 181 */         this.tickCallback.run();
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean shouldRemove() {
/* 188 */       if (this.delayBeforeRemove == 0) {
/* 189 */         return true;
/*     */       }
/* 191 */       return (this.timeToTick < System.currentTimeMillis());
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 196 */       return "SingleTick [tickCallback=" + this.tickCallback + "]";
/*     */     }
/*     */   }
/*     */   
/* 200 */   protected ArrayList<iTick> ticks = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WidgetTick() {
/* 207 */     setMaxSize(0, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FrameTick addCallback(Runnable callback) {
/* 217 */     FrameTick tick = new FrameTick(callback);
/* 218 */     this.ticks.add(tick);
/* 219 */     return tick;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DelayTick addCallback(Runnable callback, int timepertick) {
/* 230 */     DelayTick tick = new DelayTick(callback, timepertick);
/* 231 */     this.ticks.add(tick);
/* 232 */     return tick;
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
/*     */   public boolean addCustomTick(iTick tick) {
/* 244 */     return this.ticks.add(tick);
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
/*     */   public SingleTick addTimedCallback(Runnable callback, int delay) {
/* 256 */     SingleTick tick = new SingleTick(callback, delay);
/* 257 */     this.ticks.add(tick);
/* 258 */     return tick;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<iTick> getTickArrayCopy() {
/* 267 */     return Collections.unmodifiableList(this.ticks);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintWidget(GUI gui) {
/* 272 */     iTick[] removedTicks = new iTick[this.ticks.size()]; int i;
/* 273 */     for (i = 0; i < this.ticks.size(); i++) {
/* 274 */       iTick tick = this.ticks.get(i);
/*     */       try {
/* 276 */         tick.checkTick();
/* 277 */       } catch (Exception e) {
/* 278 */         e.printStackTrace();
/* 279 */         throw new RuntimeException("Error when calling tick " + tick + " at position " + i + " in WidgetTick.", e);
/*     */       } 
/* 281 */       if (tick.shouldRemove()) {
/* 282 */         removedTicks[i] = tick;
/*     */       }
/*     */     } 
/* 285 */     for (i = 0; i < removedTicks.length; i++) {
/* 286 */       iTick tick = removedTicks[i];
/* 287 */       if (tick != null)
/* 288 */         this.ticks.remove(tick); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\widget\WidgetTick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */