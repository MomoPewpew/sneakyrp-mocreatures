/*    */ package de.matthiasmann.twl.model;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SimpleGraphLineModel
/*    */   implements GraphLineModel
/*    */ {
/*    */   private String visualStyleName;
/* 40 */   private float minValue = 0.0F;
/* 41 */   private float maxValue = 100.0F;
/*    */   private float[] data;
/*    */   
/*    */   public SimpleGraphLineModel(String style, int size, float minValue, float maxValue) {
/* 45 */     setVisualStyleName(style);
/* 46 */     this.data = new float[size];
/* 47 */     this.minValue = minValue;
/* 48 */     this.maxValue = maxValue;
/*    */   }
/*    */   
/*    */   public String getVisualStyleName() {
/* 52 */     return this.visualStyleName;
/*    */   }
/*    */   
/*    */   public void setVisualStyleName(String visualStyleName) {
/* 56 */     if (visualStyleName.length() < 1) {
/* 57 */       throw new IllegalArgumentException("Invalid style name");
/*    */     }
/* 59 */     this.visualStyleName = visualStyleName;
/*    */   }
/*    */   
/*    */   public int getNumPoints() {
/* 63 */     return this.data.length;
/*    */   }
/*    */   
/*    */   public float getPoint(int idx) {
/* 67 */     return this.data[idx];
/*    */   }
/*    */   
/*    */   public float getMinValue() {
/* 71 */     return this.minValue;
/*    */   }
/*    */   
/*    */   public float getMaxValue() {
/* 75 */     return this.maxValue;
/*    */   }
/*    */   
/*    */   public void addPoint(float value) {
/* 79 */     System.arraycopy(this.data, 1, this.data, 0, this.data.length - 1);
/* 80 */     this.data[this.data.length - 1] = value;
/*    */   }
/*    */   
/*    */   public void setMaxValue(float maxValue) {
/* 84 */     this.maxValue = maxValue;
/*    */   }
/*    */   
/*    */   public void setMinValue(float minValue) {
/* 88 */     this.minValue = minValue;
/*    */   }
/*    */   
/*    */   public void setNumPoints(int numPoints) {
/* 92 */     float[] newData = new float[numPoints];
/* 93 */     int overlap = Math.min(this.data.length, numPoints);
/* 94 */     System.arraycopy(this.data, this.data.length - overlap, newData, numPoints - overlap, overlap);
/*    */ 
/*    */     
/* 97 */     this.data = newData;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\SimpleGraphLineModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */