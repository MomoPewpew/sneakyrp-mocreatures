/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
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
/*     */ public final class Timer
/*     */ {
/*     */   private static final int TIMER_COUNTER_IN_CALLBACK = -1;
/*     */   private static final int TIMER_COUNTER_DO_START = -2;
/*     */   private static final int TIMER_COUNTER_DO_STOP = -3;
/*     */   final GUI gui;
/*     */   int counter;
/*  48 */   int delay = 10;
/*     */ 
/*     */   
/*     */   boolean continuous;
/*     */ 
/*     */   
/*     */   Runnable callback;
/*     */ 
/*     */ 
/*     */   
/*     */   public Timer(GUI gui) {
/*  59 */     if (gui == null) {
/*  60 */       throw new NullPointerException("gui");
/*     */     }
/*  62 */     this.gui = gui;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRunning() {
/*  70 */     return (this.counter > 0 || (this.continuous && this.counter == -1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDelay(int delay) {
/*  80 */     if (delay < 1) {
/*  81 */       throw new IllegalArgumentException("delay < 1");
/*     */     }
/*  83 */     this.delay = delay;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void start() {
/*  90 */     if (this.counter == 0) {
/*  91 */       this.counter = this.delay;
/*  92 */       this.gui.activeTimers.add(this);
/*  93 */     } else if (this.counter < 0) {
/*  94 */       this.counter = -2;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stop() {
/* 102 */     if (this.counter > 0) {
/* 103 */       this.counter = 0;
/* 104 */       this.gui.activeTimers.remove(this);
/* 105 */     } else if (this.counter < 0) {
/* 106 */       this.counter = -3;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCallback(Runnable callback) {
/* 115 */     this.callback = callback;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isContinuous() {
/* 123 */     return this.continuous;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContinuous(boolean continuous) {
/* 131 */     this.continuous = continuous;
/*     */   }
/*     */   
/*     */   boolean tick(int delta) {
/* 135 */     int newCounter = this.counter - delta;
/* 136 */     if (newCounter <= 0) {
/* 137 */       boolean doStop = !this.continuous;
/* 138 */       this.counter = -1;
/* 139 */       doCallback();
/* 140 */       if (this.counter == -3) {
/* 141 */         this.counter = 0;
/* 142 */         return false;
/*     */       } 
/* 144 */       if (doStop && this.counter != -2) {
/* 145 */         this.counter = 0;
/* 146 */         return false;
/*     */       } 
/*     */       
/* 149 */       this.counter = Math.max(1, newCounter + this.delay);
/*     */     } else {
/* 151 */       this.counter = newCounter;
/*     */     } 
/* 153 */     return true;
/*     */   }
/*     */   
/*     */   private void doCallback() {
/* 157 */     if (this.callback != null)
/*     */       try {
/* 159 */         this.callback.run();
/* 160 */       } catch (Throwable ex) {
/* 161 */         Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, "Exception in callback", ex);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\Timer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */