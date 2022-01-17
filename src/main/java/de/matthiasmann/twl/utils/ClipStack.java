/*     */ package de.matthiasmann.twl.utils;
/*     */ 
/*     */ import de.matthiasmann.twl.Rect;
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
/*     */ public class ClipStack
/*     */ {
/*  45 */   private Entry[] clipRects = new Entry[8];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int numClipRects;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void push(int x, int y, int w, int h) {
/*  59 */     Entry tos = push();
/*  60 */     tos.setXYWH(x, y, w, h);
/*  61 */     intersect(tos);
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
/*     */   public void push(Rect rect) {
/*  73 */     if (rect == null) {
/*  74 */       throw new NullPointerException("rect");
/*     */     }
/*  76 */     Entry tos = push();
/*  77 */     tos.set(rect);
/*  78 */     intersect(tos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pushDisable() {
/*  86 */     Entry rect = push();
/*  87 */     rect.disabled = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pop() {
/*  95 */     if (this.numClipRects == 0) {
/*  96 */       underflow();
/*     */     }
/*  98 */     this.numClipRects--;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isClipEmpty() {
/* 108 */     Entry tos = this.clipRects[this.numClipRects - 1];
/* 109 */     return (tos.isEmpty() && !tos.disabled);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getClipRect(Rect rect) {
/* 118 */     if (this.numClipRects == 0) {
/* 119 */       return false;
/*     */     }
/* 121 */     Entry tos = this.clipRects[this.numClipRects - 1];
/* 122 */     rect.set(tos);
/* 123 */     return !tos.disabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStackSize() {
/* 131 */     return this.numClipRects;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearStack() {
/* 138 */     this.numClipRects = 0;
/*     */   }
/*     */   
/*     */   protected Entry push() {
/* 142 */     if (this.numClipRects == this.clipRects.length) {
/* 143 */       grow();
/*     */     }
/*     */     Entry rect;
/* 146 */     if ((rect = this.clipRects[this.numClipRects]) == null) {
/* 147 */       rect = new Entry();
/* 148 */       this.clipRects[this.numClipRects] = rect;
/*     */     } 
/* 150 */     rect.disabled = false;
/* 151 */     this.numClipRects++;
/* 152 */     return rect;
/*     */   }
/*     */   
/*     */   protected void intersect(Rect tos) {
/* 156 */     if (this.numClipRects > 1) {
/* 157 */       Entry prev = this.clipRects[this.numClipRects - 2];
/* 158 */       if (!prev.disabled) {
/* 159 */         tos.intersect(prev);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void grow() {
/* 165 */     Entry[] newRects = new Entry[this.numClipRects * 2];
/* 166 */     System.arraycopy(this.clipRects, 0, newRects, 0, this.numClipRects);
/* 167 */     this.clipRects = newRects;
/*     */   }
/*     */   
/*     */   private void underflow() {
/* 171 */     throw new IllegalStateException("empty");
/*     */   }
/*     */   
/*     */   protected static class Entry extends Rect {
/*     */     boolean disabled;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\tw\\utils\ClipStack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */