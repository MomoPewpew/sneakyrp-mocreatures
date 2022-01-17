/*     */ package drzhark.guiapi.widget;
/*     */ 
/*     */ import de.matthiasmann.twl.GUI;
/*     */ import de.matthiasmann.twl.ScrollPane;
/*     */ import de.matthiasmann.twl.Widget;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ public class WidgetClassicTwocolumn
/*     */   extends Widget
/*     */ {
/*  22 */   public int childDefaultHeight = 20;
/*     */ 
/*     */ 
/*     */   
/*  26 */   public int childDefaultWidth = 150;
/*     */ 
/*     */ 
/*     */   
/*  30 */   public int defaultPadding = 4;
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paintChildren(GUI gui) {
/*  35 */     ScrollPane pane = ScrollPane.getContainingScrollPane(this);
/*  36 */     boolean isScrolling = (pane != null);
/*     */     
/*  38 */     int minY = 0;
/*  39 */     int maxY = 0;
/*  40 */     if (isScrolling) {
/*  41 */       minY = getParent().getY();
/*  42 */       maxY = minY + pane.getContentAreaHeight();
/*     */     } 
/*     */     
/*  45 */     for (int i = 0, n = getNumChildren(); i < n; i++) {
/*  46 */       Widget child = getChild(i);
/*  47 */       if (child.isVisible()) {
/*  48 */         boolean draw = !isScrolling;
/*  49 */         if (!draw) {
/*  50 */           if (child instanceof IWidgetAlwaysDraw) {
/*  51 */             draw = true;
/*     */           }
/*  53 */           else if (child.getY() + child.getHeight() >= minY && child.getY() <= maxY) {
/*  54 */             draw = true;
/*     */           } 
/*     */         }
/*     */         
/*  58 */         if (draw) {
/*  59 */           paintChild(gui, child);
/*     */         }
/*     */       } 
/*     */     } 
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
/*     */ 
/*     */   
/*  75 */   public Map<Widget, Integer> heightOverrideExceptions = new HashMap<>();
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean overrideHeight = true;
/*     */ 
/*     */ 
/*     */   
/*  83 */   public int splitDistance = 10;
/*     */ 
/*     */ 
/*     */   
/*  87 */   public int verticalPadding = 0;
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
/*  98 */   public Map<Widget, Integer> widthOverrideExceptions = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WidgetClassicTwocolumn(Widget... widgets) {
/* 106 */     for (int i = 0; i < widgets.length; i++) {
/* 107 */       add(widgets[i]);
/*     */     }
/* 109 */     setTheme("");
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredHeight() {
/* 114 */     int totalheight = this.verticalPadding;
/* 115 */     for (int i = 0; i < getNumChildren(); i += 2) {
/* 116 */       Widget w = getChild(i);
/* 117 */       Widget w2 = null;
/* 118 */       if (i + 1 != getNumChildren()) {
/* 119 */         w2 = getChild(i + 1);
/*     */       }
/* 121 */       int height = this.childDefaultHeight;
/* 122 */       if (!this.overrideHeight) {
/* 123 */         height = w.getPreferredHeight();
/*     */       }
/* 125 */       if (this.heightOverrideExceptions.containsKey(w)) {
/* 126 */         Integer heightSet = this.heightOverrideExceptions.get(w);
/* 127 */         if (heightSet.intValue() < 1) {
/* 128 */           height = w.getPreferredHeight();
/* 129 */           heightSet = Integer.valueOf(-heightSet.intValue());
/* 130 */           if (heightSet.intValue() != 0 && heightSet.intValue() > height) {
/* 131 */             height = heightSet.intValue();
/*     */           }
/*     */         } else {
/* 134 */           height = heightSet.intValue();
/*     */         } 
/*     */       } 
/* 137 */       if (w2 != null) {
/* 138 */         int temp = height;
/* 139 */         if (!this.overrideHeight) {
/* 140 */           temp = w2.getPreferredHeight();
/*     */         }
/* 142 */         if (this.heightOverrideExceptions.containsKey(w2)) {
/* 143 */           Integer heightSet = this.heightOverrideExceptions.get(w2);
/* 144 */           if (heightSet.intValue() < 1) {
/* 145 */             height = w.getPreferredHeight();
/* 146 */             heightSet = Integer.valueOf(-heightSet.intValue());
/* 147 */             if (heightSet.intValue() != 0 && heightSet.intValue() > height) {
/* 148 */               height = heightSet.intValue();
/*     */             }
/*     */           } else {
/* 151 */             height = heightSet.intValue();
/*     */           } 
/*     */         } 
/* 154 */         if (temp > height) {
/* 155 */           height = temp;
/*     */         }
/*     */       } 
/* 158 */       totalheight += height + this.defaultPadding;
/*     */     } 
/* 160 */     return totalheight;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredWidth() {
/* 165 */     return getParent().getWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public void layout() {
/* 170 */     if (getParent().getTheme().equals("scrollpane-notch")) {
/* 171 */       this.verticalPadding = 10;
/*     */     }
/* 173 */     int totalheight = this.verticalPadding;
/* 174 */     for (int i = 0; i < getNumChildren(); i += 2) {
/* 175 */       Widget w = getChild(i);
/* 176 */       Widget w2 = null;
/*     */       try {
/* 178 */         w2 = getChild(i + 1);
/* 179 */       } catch (IndexOutOfBoundsException indexOutOfBoundsException) {}
/*     */ 
/*     */       
/* 182 */       int height = this.childDefaultHeight;
/* 183 */       int width = this.childDefaultWidth;
/* 184 */       if (!this.overrideHeight) {
/* 185 */         height = w.getPreferredHeight();
/*     */       }
/* 187 */       if (this.heightOverrideExceptions.containsKey(w)) {
/* 188 */         Integer heightSet = this.heightOverrideExceptions.get(w);
/*     */         
/* 190 */         if (heightSet.intValue() < 1) {
/* 191 */           height = w.getPreferredHeight();
/* 192 */           heightSet = Integer.valueOf(-heightSet.intValue());
/* 193 */           if (heightSet.intValue() != 0 && heightSet.intValue() > height) {
/* 194 */             height = heightSet.intValue();
/*     */           }
/*     */         } else {
/* 197 */           height = heightSet.intValue();
/*     */         } 
/*     */       } 
/* 200 */       if (this.widthOverrideExceptions.containsKey(w)) {
/* 201 */         Integer widthSet = this.widthOverrideExceptions.get(w);
/*     */         
/* 203 */         if (widthSet.intValue() < 1) {
/* 204 */           width = w.getPreferredWidth();
/* 205 */           widthSet = Integer.valueOf(-widthSet.intValue());
/* 206 */           if (widthSet.intValue() != 0 && widthSet.intValue() > width) {
/* 207 */             width = widthSet.intValue();
/*     */           }
/*     */         } else {
/* 210 */           width = widthSet.intValue();
/*     */         } 
/*     */       } 
/* 213 */       if (w2 != null) {
/* 214 */         int temph = height;
/* 215 */         int tempw = width;
/* 216 */         if (!this.overrideHeight) {
/* 217 */           temph = w2.getPreferredHeight();
/*     */         }
/* 219 */         if (this.heightOverrideExceptions.containsKey(w2)) {
/* 220 */           Integer heightSet = this.heightOverrideExceptions.get(w2);
/* 221 */           if (heightSet.intValue() < 1) {
/* 222 */             height = w.getPreferredHeight();
/* 223 */             heightSet = Integer.valueOf(-heightSet.intValue());
/* 224 */             if (heightSet.intValue() != 0 && heightSet.intValue() > height) {
/* 225 */               height = heightSet.intValue();
/*     */             }
/*     */           } else {
/* 228 */             height = heightSet.intValue();
/*     */           } 
/*     */         } 
/* 231 */         if (this.widthOverrideExceptions.containsKey(w2)) {
/* 232 */           Integer widthSet = this.widthOverrideExceptions.get(w2);
/*     */           
/* 234 */           if (widthSet.intValue() < 1) {
/* 235 */             width = w2.getPreferredWidth();
/* 236 */             widthSet = Integer.valueOf(-widthSet.intValue());
/* 237 */             if (widthSet.intValue() != 0 && widthSet.intValue() > width) {
/* 238 */               width = widthSet.intValue();
/*     */             }
/*     */           } else {
/* 241 */             width = widthSet.intValue();
/*     */           } 
/*     */         } 
/* 244 */         if (temph > height) {
/* 245 */           height = temph;
/*     */         }
/* 247 */         if (tempw > width) {
/* 248 */           width = tempw;
/*     */         }
/*     */       } 
/* 251 */       w.setSize(width, height);
/* 252 */       w.setPosition(getX() + getWidth() / 2 - width + this.splitDistance / 2, getY() + totalheight);
/* 253 */       if (w2 != null) {
/* 254 */         w2.setSize(width, height);
/* 255 */         w2.setPosition(getX() + getWidth() / 2 + this.splitDistance / 2, getY() + totalheight);
/*     */       } 
/* 257 */       totalheight += height + this.defaultPadding;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\widget\WidgetClassicTwocolumn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */