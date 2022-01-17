/*     */ package de.matthiasmann.twl;
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
/*     */ public class FPSCounter
/*     */   extends Label
/*     */ {
/*     */   private long startTime;
/*     */   private int frames;
/*  49 */   private int framesToCount = 100;
/*     */ 
/*     */   
/*     */   private final StringBuilder fmtBuffer;
/*     */ 
/*     */   
/*     */   private final int decimalPoint;
/*     */ 
/*     */   
/*     */   private final long scale;
/*     */ 
/*     */   
/*     */   public FPSCounter(int numIntegerDigits, int numDecimalDigits) {
/*  62 */     if (numIntegerDigits < 2) {
/*  63 */       throw new IllegalArgumentException("numIntegerDigits must be >= 2");
/*     */     }
/*  65 */     if (numDecimalDigits < 0) {
/*  66 */       throw new IllegalArgumentException("numDecimalDigits must be >= 0");
/*     */     }
/*  68 */     this.decimalPoint = numIntegerDigits + 1;
/*  69 */     this.startTime = System.nanoTime();
/*  70 */     this.fmtBuffer = new StringBuilder();
/*  71 */     this.fmtBuffer.setLength(numIntegerDigits + numDecimalDigits + Integer.signum(numDecimalDigits));
/*     */ 
/*     */     
/*  74 */     long tmp = 1000000000L;
/*  75 */     for (int i = 0; i < numDecimalDigits; i++) {
/*  76 */       tmp *= 10L;
/*     */     }
/*  78 */     this.scale = tmp;
/*     */ 
/*     */     
/*  81 */     updateText(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FPSCounter() {
/*  89 */     this(3, 2);
/*     */   }
/*     */   
/*     */   public int getFramesToCount() {
/*  93 */     return this.framesToCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFramesToCount(int framesToCount) {
/* 103 */     if (framesToCount <= 0) {
/* 104 */       throw new IllegalArgumentException("framesToCount < 1");
/*     */     }
/* 106 */     this.framesToCount = framesToCount;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintWidget(GUI gui) {
/* 111 */     if (++this.frames >= this.framesToCount) {
/* 112 */       updateFPS();
/*     */     }
/* 114 */     super.paintWidget(gui);
/*     */   }
/*     */   
/*     */   private void updateFPS() {
/* 118 */     long curTime = System.nanoTime();
/* 119 */     long elapsed = curTime - this.startTime;
/* 120 */     this.startTime = curTime;
/*     */     
/* 122 */     updateText((int)((this.frames * this.scale + (elapsed >> 1L)) / elapsed));
/* 123 */     this.frames = 0;
/*     */   }
/*     */   
/*     */   private void updateText(int value) {
/* 127 */     StringBuilder buf = this.fmtBuffer;
/* 128 */     int pos = buf.length();
/*     */     while (true) {
/* 130 */       buf.setCharAt(--pos, (char)(48 + value % 10));
/* 131 */       value /= 10;
/* 132 */       if (this.decimalPoint == pos) {
/* 133 */         buf.setCharAt(--pos, '.');
/*     */       }
/* 135 */       if (pos <= 0) {
/* 136 */         if (value > 0) {
/*     */           
/* 138 */           pos = buf.length();
/*     */           do {
/* 140 */             buf.setCharAt(--pos, '9');
/* 141 */             if (this.decimalPoint != pos)
/* 142 */               continue;  pos--;
/*     */           }
/* 144 */           while (pos > 0);
/*     */         } 
/* 146 */         setCharSequence(buf);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\FPSCounter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */