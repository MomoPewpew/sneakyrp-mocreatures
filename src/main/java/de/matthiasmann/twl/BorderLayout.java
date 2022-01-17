/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import java.util.EnumMap;
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
/*     */ public class BorderLayout
/*     */   extends Widget
/*     */ {
/*     */   private final EnumMap<Location, Widget> widgets;
/*     */   private int hgap;
/*     */   private int vgap;
/*     */   
/*     */   public enum Location
/*     */   {
/*  77 */     EAST, WEST, NORTH, SOUTH, CENTER;
/*     */   }
/*     */   
/*     */   public BorderLayout() {
/*  81 */     this.widgets = new EnumMap<Location, Widget>(Location.class);
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
/*     */   public void add(Widget widget, Location location) {
/*  93 */     if (widget == null) {
/*  94 */       throw new NullPointerException("widget is null");
/*     */     }
/*  96 */     if (location == null) {
/*  97 */       throw new NullPointerException("location is null");
/*     */     }
/*  99 */     if (this.widgets.containsKey(location)) {
/* 100 */       throw new IllegalStateException("a widget was already added to that location: " + location);
/*     */     }
/*     */     
/* 103 */     this.widgets.put(location, widget);
/*     */     try {
/* 105 */       super.insertChild(widget, getNumChildren());
/* 106 */     } catch (Exception e) {
/* 107 */       removeChild(location);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Widget getChild(Location location) {
/* 118 */     if (location == null) {
/* 119 */       throw new NullPointerException("location is null");
/*     */     }
/* 121 */     return this.widgets.get(location);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Widget removeChild(Location location) {
/* 132 */     if (location == null) {
/* 133 */       throw new NullPointerException("location is null");
/*     */     }
/* 135 */     Widget w = this.widgets.remove(location);
/* 136 */     if (w != null) {
/* 137 */       removeChild(w);
/*     */     }
/*     */     
/* 140 */     return w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(Widget child) {
/* 148 */     add(child, Location.CENTER);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertChild(Widget child, int index) throws UnsupportedOperationException {
/* 158 */     throw new UnsupportedOperationException("insert child is not supported by the BorderLayout");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void childRemoved(Widget exChild) {
/* 163 */     for (Location loc : this.widgets.keySet()) {
/* 164 */       if (this.widgets.get(loc) == exChild) {
/* 165 */         this.widgets.remove(loc);
/*     */         break;
/*     */       } 
/*     */     } 
/* 169 */     super.childRemoved(exChild);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void allChildrenRemoved() {
/* 174 */     this.widgets.clear();
/* 175 */     super.allChildrenRemoved();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyTheme(ThemeInfo themeInfo) {
/* 180 */     this.hgap = themeInfo.getParameter("hgap", 0);
/* 181 */     this.vgap = themeInfo.getParameter("vgap", 0);
/*     */     
/* 183 */     super.applyTheme(themeInfo);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void layout() {
/* 188 */     int top = getInnerY();
/* 189 */     int bottom = getInnerBottom();
/* 190 */     int left = getInnerX();
/* 191 */     int right = getInnerRight();
/*     */     
/*     */     Widget w;
/*     */     
/* 195 */     if ((w = this.widgets.get(Location.NORTH)) != null) {
/* 196 */       w.setPosition(left, top);
/* 197 */       w.setSize(Math.max(right - left, 0), Math.max(w.getPreferredHeight(), 0));
/* 198 */       top += w.getPreferredHeight() + this.vgap;
/*     */     } 
/* 200 */     if ((w = this.widgets.get(Location.SOUTH)) != null) {
/* 201 */       w.setPosition(left, bottom - w.getPreferredHeight());
/* 202 */       w.setSize(Math.max(right - left, 0), Math.max(w.getPreferredHeight(), 0));
/* 203 */       bottom -= w.getPreferredHeight() + this.vgap;
/*     */     } 
/* 205 */     if ((w = this.widgets.get(Location.EAST)) != null) {
/* 206 */       w.setPosition(right - w.getPreferredWidth(), top);
/* 207 */       w.setSize(Math.max(w.getPreferredWidth(), 0), Math.max(bottom - top, 0));
/* 208 */       right -= w.getPreferredWidth() + this.hgap;
/*     */     } 
/* 210 */     if ((w = this.widgets.get(Location.WEST)) != null) {
/* 211 */       w.setPosition(left, top);
/* 212 */       w.setSize(Math.max(w.getPreferredWidth(), 0), Math.max(bottom - top, 0));
/* 213 */       left += w.getPreferredWidth() + this.hgap;
/*     */     } 
/* 215 */     if ((w = this.widgets.get(Location.CENTER)) != null) {
/* 216 */       w.setPosition(left, top);
/* 217 */       w.setSize(Math.max(right - left, 0), Math.max(bottom - top, 0));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinWidth() {
/* 223 */     return computeMinWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinHeight() {
/* 228 */     return computeMinHeight();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerWidth() {
/* 233 */     return computePrefWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerHeight() {
/* 238 */     return computePrefHeight();
/*     */   }
/*     */   
/*     */   private int computeMinWidth() {
/* 242 */     int size = 0;
/*     */     
/* 244 */     size += getChildMinWidth(this.widgets.get(Location.EAST), this.hgap);
/* 245 */     size += getChildMinWidth(this.widgets.get(Location.WEST), this.hgap);
/* 246 */     size += getChildMinWidth(this.widgets.get(Location.CENTER), 0);
/*     */     
/* 248 */     size = Math.max(size, getChildMinWidth(this.widgets.get(Location.NORTH), 0));
/* 249 */     size = Math.max(size, getChildMinWidth(this.widgets.get(Location.SOUTH), 0));
/*     */     
/* 251 */     return size;
/*     */   }
/*     */   
/*     */   private int computeMinHeight() {
/* 255 */     int size = 0;
/*     */     
/* 257 */     size = Math.max(size, getChildMinHeight(this.widgets.get(Location.EAST), 0));
/* 258 */     size = Math.max(size, getChildMinHeight(this.widgets.get(Location.WEST), 0));
/* 259 */     size = Math.max(size, getChildMinHeight(this.widgets.get(Location.CENTER), 0));
/*     */     
/* 261 */     size += getChildMinHeight(this.widgets.get(Location.NORTH), this.vgap);
/* 262 */     size += getChildMinHeight(this.widgets.get(Location.SOUTH), this.vgap);
/*     */     
/* 264 */     return size;
/*     */   }
/*     */   
/*     */   private int computePrefWidth() {
/* 268 */     int size = 0;
/*     */     
/* 270 */     size += getChildPrefWidth(this.widgets.get(Location.EAST), this.hgap);
/* 271 */     size += getChildPrefWidth(this.widgets.get(Location.WEST), this.hgap);
/* 272 */     size += getChildPrefWidth(this.widgets.get(Location.CENTER), 0);
/*     */     
/* 274 */     size = Math.max(size, getChildPrefWidth(this.widgets.get(Location.NORTH), 0));
/* 275 */     size = Math.max(size, getChildPrefWidth(this.widgets.get(Location.SOUTH), 0));
/*     */     
/* 277 */     return size;
/*     */   }
/*     */   
/*     */   private int computePrefHeight() {
/* 281 */     int size = 0;
/*     */     
/* 283 */     size = Math.max(size, getChildPrefHeight(this.widgets.get(Location.EAST), 0));
/* 284 */     size = Math.max(size, getChildPrefHeight(this.widgets.get(Location.WEST), 0));
/* 285 */     size = Math.max(size, getChildPrefHeight(this.widgets.get(Location.CENTER), 0));
/*     */     
/* 287 */     size += getChildPrefHeight(this.widgets.get(Location.NORTH), this.vgap);
/* 288 */     size += getChildPrefHeight(this.widgets.get(Location.SOUTH), this.vgap);
/*     */     
/* 290 */     return size;
/*     */   }
/*     */ 
/*     */   
/*     */   private int getChildMinWidth(Widget w, int gap) {
/* 295 */     if (w != null) {
/* 296 */       return w.getMinWidth() + gap;
/*     */     }
/* 298 */     return 0;
/*     */   }
/*     */   
/*     */   private int getChildMinHeight(Widget w, int gap) {
/* 302 */     if (w != null) {
/* 303 */       return w.getMinHeight() + gap;
/*     */     }
/* 305 */     return 0;
/*     */   }
/*     */   
/*     */   private int getChildPrefWidth(Widget w, int gap) {
/* 309 */     if (w != null) {
/* 310 */       return computeSize(w.getMinWidth(), w.getPreferredWidth(), w.getMaxWidth()) + gap;
/*     */     }
/* 312 */     return 0;
/*     */   }
/*     */   
/*     */   private int getChildPrefHeight(Widget w, int gap) {
/* 316 */     if (w != null) {
/* 317 */       return computeSize(w.getMinHeight(), w.getPreferredHeight(), w.getMaxHeight()) + gap;
/*     */     }
/* 319 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\BorderLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */