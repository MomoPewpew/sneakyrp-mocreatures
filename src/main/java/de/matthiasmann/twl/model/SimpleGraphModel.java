/*     */ package de.matthiasmann.twl.model;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimpleGraphModel
/*     */   implements GraphModel
/*     */ {
/*     */   private final ArrayList<GraphLineModel> lines;
/*     */   private boolean scaleLinesIndependant;
/*     */   
/*     */   public SimpleGraphModel() {
/*  46 */     this.lines = new ArrayList<GraphLineModel>();
/*     */   }
/*     */   
/*     */   public SimpleGraphModel(GraphLineModel... lines) {
/*  50 */     this(Arrays.asList(lines));
/*     */   }
/*     */   
/*     */   public SimpleGraphModel(Collection<GraphLineModel> lines) {
/*  54 */     this.lines = new ArrayList<GraphLineModel>(lines);
/*     */   }
/*     */   
/*     */   public GraphLineModel getLine(int idx) {
/*  58 */     return this.lines.get(idx);
/*     */   }
/*     */   
/*     */   public int getNumLines() {
/*  62 */     return this.lines.size();
/*     */   }
/*     */   
/*     */   public boolean getScaleLinesIndependant() {
/*  66 */     return this.scaleLinesIndependant;
/*     */   }
/*     */   
/*     */   public void setScaleLinesIndependant(boolean scaleLinesIndependant) {
/*  70 */     this.scaleLinesIndependant = scaleLinesIndependant;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addLine(GraphLineModel line) {
/*  78 */     insertLine(this.lines.size(), line);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertLine(int idx, GraphLineModel line) {
/*  89 */     if (line == null) {
/*  90 */       throw new NullPointerException("line");
/*     */     }
/*  92 */     if (indexOfLine(line) >= 0) {
/*  93 */       throw new IllegalArgumentException("line already added");
/*     */     }
/*  95 */     this.lines.add(idx, line);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int indexOfLine(GraphLineModel line) {
/* 105 */     for (int i = 0, n = this.lines.size(); i < n; i++) {
/* 106 */       if (this.lines.get(i) == line) {
/* 107 */         return i;
/*     */       }
/*     */     } 
/* 110 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphLineModel removeLine(int idx) {
/* 119 */     return this.lines.remove(idx);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\SimpleGraphModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */