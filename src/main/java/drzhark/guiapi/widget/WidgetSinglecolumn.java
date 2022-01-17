/*    */ package drzhark.guiapi.widget;
/*    */ 
/*    */ import de.matthiasmann.twl.Widget;
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
/*    */ public class WidgetSinglecolumn
/*    */   extends WidgetClassicTwocolumn
/*    */ {
/*    */   public WidgetSinglecolumn(Widget... widgets) {
/* 20 */     super(widgets);
/* 21 */     this.childDefaultWidth = 200;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPreferredHeight() {
/* 26 */     int totalheight = this.verticalPadding;
/* 27 */     for (int i = 0; i < getNumChildren(); i++) {
/* 28 */       Widget widget = getChild(i);
/* 29 */       int height = this.childDefaultHeight;
/* 30 */       if (!this.overrideHeight) {
/* 31 */         height = widget.getPreferredHeight();
/*    */       }
/* 33 */       if (this.heightOverrideExceptions.containsKey(widget)) {
/* 34 */         Integer heightSet = this.heightOverrideExceptions.get(widget);
/* 35 */         if (heightSet.intValue() < 1) {
/* 36 */           height = widget.getPreferredHeight();
/* 37 */           heightSet = Integer.valueOf(-heightSet.intValue());
/* 38 */           if (heightSet.intValue() != 0 && heightSet.intValue() > height) {
/* 39 */             height = heightSet.intValue();
/*    */           }
/*    */         } else {
/* 42 */           height = heightSet.intValue();
/*    */         } 
/*    */       } 
/* 45 */       totalheight += height + this.defaultPadding;
/*    */     } 
/* 47 */     return totalheight;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPreferredWidth() {
/* 56 */     return getParent().getWidth();
/*    */   }
/*    */ 
/*    */   
/*    */   public void layout() {
/* 61 */     int totalheight = this.verticalPadding;
/* 62 */     for (int i = 0; i < getNumChildren(); i++) {
/* 63 */       Widget w = getChild(i);
/* 64 */       int height = this.childDefaultHeight;
/* 65 */       int width = this.childDefaultWidth;
/* 66 */       if (!this.overrideHeight) {
/* 67 */         height = w.getPreferredHeight();
/*    */       }
/* 69 */       if (this.heightOverrideExceptions.containsKey(w)) {
/* 70 */         Integer heightSet = this.heightOverrideExceptions.get(w);
/* 71 */         if (heightSet.intValue() < 1) {
/* 72 */           height = w.getPreferredHeight();
/* 73 */           heightSet = Integer.valueOf(-heightSet.intValue());
/* 74 */           if (heightSet.intValue() != 0 && heightSet.intValue() > height) {
/* 75 */             height = heightSet.intValue();
/*    */           }
/*    */         } else {
/* 78 */           height = heightSet.intValue();
/*    */         } 
/*    */       } 
/* 81 */       if (this.widthOverrideExceptions.containsKey(w)) {
/* 82 */         Integer widthSet = this.widthOverrideExceptions.get(w);
/*    */         
/* 84 */         if (widthSet.intValue() < 1) {
/* 85 */           width = w.getPreferredWidth();
/* 86 */           widthSet = Integer.valueOf(-widthSet.intValue());
/* 87 */           if (widthSet.intValue() != 0 && widthSet.intValue() > width) {
/* 88 */             width = widthSet.intValue();
/*    */           }
/*    */         } else {
/* 91 */           width = widthSet.intValue();
/*    */         } 
/*    */       } 
/* 94 */       w.setSize(width, height);
/* 95 */       w.setPosition(getX() + getWidth() / 2 - width / 2, getY() + totalheight);
/* 96 */       totalheight += height + this.defaultPadding;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\widget\WidgetSinglecolumn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */