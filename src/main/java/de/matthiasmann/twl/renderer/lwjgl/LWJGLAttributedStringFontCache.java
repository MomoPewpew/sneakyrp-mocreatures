/*     */ package de.matthiasmann.twl.renderer.lwjgl;
/*     */ 
/*     */ import de.matthiasmann.twl.renderer.AttributedStringFontCache;
/*     */ import java.nio.FloatBuffer;
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
/*     */ class LWJGLAttributedStringFontCache
/*     */   extends VertexArray
/*     */   implements AttributedStringFontCache
/*     */ {
/*     */   final LWJGLRenderer renderer;
/*     */   final BitmapFont font;
/*     */   int width;
/*     */   int height;
/*     */   private Run[] runs;
/*     */   private int numRuns;
/*     */   
/*     */   LWJGLAttributedStringFontCache(LWJGLRenderer renderer, BitmapFont font) {
/*  51 */     this.renderer = renderer;
/*  52 */     this.font = font;
/*  53 */     this.runs = new Run[8];
/*     */   }
/*     */ 
/*     */   
/*     */   public FloatBuffer allocate(int maxGlyphs) {
/*  58 */     this.numRuns = 0;
/*  59 */     return super.allocate(maxGlyphs);
/*     */   }
/*     */   
/*     */   Run addRun() {
/*  63 */     if (this.runs.length == this.numRuns) {
/*  64 */       grow();
/*     */     }
/*  66 */     Run run = this.runs[this.numRuns];
/*  67 */     if (run == null) {
/*  68 */       run = new Run();
/*  69 */       this.runs[this.numRuns] = run;
/*     */     } 
/*  71 */     this.numRuns++;
/*  72 */     return run;
/*     */   }
/*     */   
/*     */   private void grow() {
/*  76 */     Run[] newRuns = new Run[this.numRuns * 2];
/*  77 */     System.arraycopy(this.runs, 0, newRuns, 0, this.numRuns);
/*  78 */     this.runs = newRuns;
/*     */   }
/*     */ 
/*     */   
/*     */   public void destroy() {}
/*     */   
/*     */   public int getWidth() {
/*  85 */     return this.width;
/*     */   }
/*     */   
/*     */   public int getHeight() {
/*  89 */     return this.height;
/*     */   }
/*     */   
/*     */   public void draw(int x, int y) {
/*  93 */     if (this.font.bind()) {
/*  94 */       bind();
/*  95 */       GL11.glPushMatrix();
/*  96 */       GL11.glTranslatef(x, y, 0.0F);
/*  97 */       TintStack tintStack = this.renderer.tintStack;
/*     */       
/*     */       try {
/* 100 */         int idx = 0;
/* 101 */         for (int i = 0; i < this.numRuns; i++) {
/* 102 */           Run run = this.runs[i];
/* 103 */           LWJGLFont.FontState state = run.state;
/* 104 */           int numVertices = run.numVertices;
/*     */           
/* 106 */           tintStack.setColor(state.color);
/*     */           
/* 108 */           if (numVertices > 0) {
/* 109 */             drawVertices(idx, numVertices);
/* 110 */             idx += numVertices;
/*     */           } 
/*     */           
/* 113 */           if (state.style != 0) {
/* 114 */             drawLines(run);
/*     */           }
/*     */         } 
/*     */       } finally {
/* 118 */         GL11.glPopMatrix();
/* 119 */         unbind();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawLines(Run run) {
/* 125 */     LWJGLFont.FontState state = run.state;
/*     */     
/* 127 */     if ((state.style & 0x1) != 0) {
/* 128 */       this.font.drawLine(run.x, run.y + this.font.getBaseLine() + state.underlineOffset, run.xend);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 133 */     if ((state.style & 0x2) != 0)
/* 134 */       this.font.drawLine(run.x, run.y + this.font.getLineHeight() / 2, run.xend); 
/*     */   }
/*     */   
/*     */   static class Run {
/*     */     LWJGLFont.FontState state;
/*     */     int numVertices;
/*     */     int x;
/*     */     int xend;
/*     */     int y;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\lwjgl\LWJGLAttributedStringFontCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */