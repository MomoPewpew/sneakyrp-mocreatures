/*     */ package de.matthiasmann.twl.renderer;
/*     */ 
/*     */ import de.matthiasmann.twl.Color;
/*     */ import java.util.ArrayList;
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
/*     */ public class Gradient
/*     */ {
/*     */   private final Type type;
/*     */   private Wrap wrap;
/*     */   private final ArrayList<Stop> stops;
/*     */   
/*     */   public enum Type
/*     */   {
/*  44 */     HORIZONTAL,
/*  45 */     VERTICAL;
/*     */   }
/*     */   
/*     */   public enum Wrap {
/*  49 */     SCALE,
/*  50 */     CLAMP,
/*  51 */     REPEAT,
/*  52 */     MIRROR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Gradient(Type type) {
/*  60 */     if (type == null) {
/*  61 */       throw new NullPointerException("type");
/*     */     }
/*  63 */     this.type = type;
/*  64 */     this.wrap = Wrap.SCALE;
/*  65 */     this.stops = new ArrayList<Stop>();
/*     */   }
/*     */   
/*     */   public Type getType() {
/*  69 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setWrap(Wrap wrap) {
/*  73 */     if (wrap == null) {
/*  74 */       throw new NullPointerException("wrap");
/*     */     }
/*  76 */     this.wrap = wrap;
/*     */   }
/*     */   
/*     */   public Wrap getWrap() {
/*  80 */     return this.wrap;
/*     */   }
/*     */   
/*     */   public int getNumStops() {
/*  84 */     return this.stops.size();
/*     */   }
/*     */   
/*     */   public Stop getStop(int index) {
/*  88 */     return this.stops.get(index);
/*     */   }
/*     */   
/*     */   public Stop[] getStops() {
/*  92 */     return this.stops.<Stop>toArray(new Stop[this.stops.size()]);
/*     */   }
/*     */   
/*     */   public void addStop(float pos, Color color) {
/*  96 */     if (color == null) {
/*  97 */       throw new NullPointerException("color");
/*     */     }
/*  99 */     int numStops = this.stops.size();
/* 100 */     if (numStops == 0) {
/* 101 */       if (pos < 0.0F) {
/* 102 */         throw new IllegalArgumentException("first stop must be >= 0.0f");
/*     */       }
/* 104 */       if (pos > 0.0F) {
/* 105 */         this.stops.add(new Stop(0.0F, color));
/*     */       }
/*     */     } 
/* 108 */     if (numStops > 0 && pos <= ((Stop)this.stops.get(numStops - 1)).pos) {
/* 109 */       throw new IllegalArgumentException("pos must be monotone increasing");
/*     */     }
/* 111 */     this.stops.add(new Stop(pos, color));
/*     */   }
/*     */   
/*     */   public static class Stop {
/*     */     final float pos;
/*     */     final Color color;
/*     */     
/*     */     public Stop(float pos, Color color) {
/* 119 */       this.pos = pos;
/* 120 */       this.color = color;
/*     */     }
/*     */     
/*     */     public float getPos() {
/* 124 */       return this.pos;
/*     */     }
/*     */     
/*     */     public Color getColor() {
/* 128 */       return this.color;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\Gradient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */