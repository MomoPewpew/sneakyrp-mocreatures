/*     */ package drzhark.guiapi.widget;
/*     */ 
/*     */ import de.matthiasmann.twl.Widget;
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
/*     */ public class WidgetSingleRow
/*     */   extends Widget
/*     */ {
/*  18 */   public int defaultHeight = 20;
/*     */ 
/*     */ 
/*     */   
/*  22 */   public int defaultWidth = 150;
/*  23 */   protected ArrayList<Integer> heights = new ArrayList<>();
/*  24 */   protected ArrayList<Widget> widgets = new ArrayList<>();
/*  25 */   protected ArrayList<Integer> widths = new ArrayList<>();
/*     */ 
/*     */ 
/*     */   
/*  29 */   public int xSpacing = 3;
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
/*     */   public WidgetSingleRow(int defwidth, int defheight, Widget... widgets) {
/*  41 */     setTheme("");
/*  42 */     this.defaultWidth = defwidth;
/*  43 */     this.defaultHeight = defheight;
/*  44 */     for (int i = 0; i < widgets.length; i++) {
/*  45 */       add(widgets[i]);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void add(Widget widget) {
/*  51 */     add(widget, this.defaultWidth, this.defaultHeight);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(Widget widget, int width, int height) {
/*  62 */     this.widgets.add(widget);
/*  63 */     this.heights.add(Integer.valueOf(height));
/*  64 */     this.widths.add(Integer.valueOf(width));
/*  65 */     super.add(widget);
/*     */   }
/*     */   
/*     */   private int getHeight(int idx) {
/*  69 */     if (((Integer)this.heights.get(idx)).intValue() >= 0) {
/*  70 */       return ((Integer)this.heights.get(idx)).intValue();
/*     */     }
/*  72 */     return ((Widget)this.widgets.get(idx)).getPreferredHeight();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPreferredHeight() {
/*  78 */     int maxheights = 0;
/*  79 */     for (int i = 0; i < this.heights.size(); i++) {
/*  80 */       if (getHeight(i) > maxheights) {
/*  81 */         maxheights = getHeight(i);
/*     */       }
/*     */     } 
/*  84 */     return maxheights;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredWidth() {
/*  89 */     int totalwidth = (this.widths.size() - 1) * this.xSpacing;
/*  90 */     totalwidth = (totalwidth >= 0) ? totalwidth : 0;
/*  91 */     for (int i = 0; i < this.widths.size(); i++) {
/*  92 */       totalwidth += getWidth(i);
/*     */     }
/*  94 */     return totalwidth;
/*     */   }
/*     */   
/*     */   private int getWidth(int idx) {
/*  98 */     if (((Integer)this.widths.get(idx)).intValue() >= 0) {
/*  99 */       return ((Integer)this.widths.get(idx)).intValue();
/*     */     }
/* 101 */     return ((Widget)this.widgets.get(idx)).getPreferredWidth();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void layout() {
/* 107 */     int curXpos = 0;
/* 108 */     for (int i = 0; i < this.widgets.size(); i++) {
/* 109 */       Widget w = this.widgets.get(i);
/* 110 */       w.setPosition(curXpos + getX(), getY());
/* 111 */       w.setSize(getWidth(i), getHeight(i));
/* 112 */       curXpos += getWidth(i) + this.xSpacing;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Widget removeChild(int idx) {
/* 118 */     this.widgets.remove(idx);
/* 119 */     this.heights.remove(idx);
/* 120 */     this.widths.remove(idx);
/* 121 */     return super.removeChild(idx);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removeChild(Widget widget) {
/* 126 */     int idx = this.widgets.indexOf(widget);
/* 127 */     this.widgets.remove(idx);
/* 128 */     this.heights.remove(idx);
/* 129 */     this.widths.remove(idx);
/* 130 */     return super.removeChild(widget);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\widget\WidgetSingleRow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */