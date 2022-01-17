/*     */ package de.matthiasmann.twl.textarea;
/*     */ 
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
/*     */ public class Style
/*     */ {
/*     */   private final Style parent;
/*     */   private final StyleSheetKey styleSheetKey;
/*     */   private Object[] values;
/*     */   
/*     */   public Style() {
/*  50 */     this(null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Style(Style parent, StyleSheetKey styleSheetKey) {
/*  60 */     this.parent = parent;
/*  61 */     this.styleSheetKey = styleSheetKey;
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
/*     */   public Style(Style parent, StyleSheetKey styleSheetKey, Map<StyleAttribute<?>, Object> values) {
/*  73 */     this(parent, styleSheetKey);
/*     */     
/*  75 */     if (values != null) {
/*  76 */       putAll(values);
/*     */     }
/*     */   }
/*     */   
/*     */   protected Style(Style src) {
/*  81 */     this.parent = src.parent;
/*  82 */     this.styleSheetKey = src.styleSheetKey;
/*  83 */     this.values = (src.values != null) ? (Object[])src.values.clone() : null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Style resolve(StyleAttribute<?> attribute, StyleSheetResolver resolver) {
/* 102 */     if (!attribute.isInherited()) {
/* 103 */       return this;
/*     */     }
/*     */     
/* 106 */     return doResolve(this, attribute.ordinal(), resolver);
/*     */   }
/*     */   
/*     */   private static Style doResolve(Style style, int ord, StyleSheetResolver resolver) {
/*     */     while (true) {
/* 111 */       if (style.parent == null) {
/* 112 */         return style;
/*     */       }
/* 114 */       if (style.rawGet(ord) != null) {
/* 115 */         return style;
/*     */       }
/* 117 */       if (resolver != null && style.styleSheetKey != null) {
/* 118 */         Style styleSheetStyle = resolver.resolve(style);
/* 119 */         if (styleSheetStyle != null && styleSheetStyle.rawGet(ord) != null)
/*     */         {
/* 121 */           return style;
/*     */         }
/*     */       } 
/* 124 */       style = style.parent;
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
/*     */ 
/*     */   
/*     */   public <V> V getNoResolve(StyleAttribute<V> attribute, StyleSheetResolver resolver) {
/* 141 */     Object value = rawGet(attribute.ordinal());
/* 142 */     if (value == null) {
/* 143 */       if (resolver != null && this.styleSheetKey != null) {
/* 144 */         Style styleSheetStyle = resolver.resolve(this);
/* 145 */         if (styleSheetStyle != null) {
/* 146 */           value = styleSheetStyle.rawGet(attribute.ordinal());
/*     */         }
/*     */       } 
/* 149 */       if (value == null) {
/* 150 */         return attribute.getDefaultValue();
/*     */       }
/*     */     } 
/* 153 */     return attribute.getDataType().cast(value);
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
/*     */   public <V> V get(StyleAttribute<V> attribute, StyleSheetResolver resolver) {
/* 167 */     return resolve(attribute, resolver).getNoResolve(attribute, resolver);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <V> V getRaw(StyleAttribute<V> attribute) {
/* 178 */     Object value = rawGet(attribute.ordinal());
/* 179 */     return attribute.getDataType().cast(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Style getParent() {
/* 190 */     return this.parent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StyleSheetKey getStyleSheetKey() {
/* 200 */     return this.styleSheetKey;
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
/*     */   public Style with(Map<StyleAttribute<?>, Object> values) {
/* 212 */     Style newStyle = new Style(this);
/* 213 */     newStyle.putAll(values);
/* 214 */     return newStyle;
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
/*     */   public <V> Style with(StyleAttribute<V> attribute, V value) {
/* 228 */     Style newStyle = new Style(this);
/* 229 */     newStyle.put(attribute, value);
/* 230 */     return newStyle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Style withoutNonInheritable() {
/* 240 */     if (this.values != null) {
/* 241 */       for (int i = 0, n = this.values.length; i < n; i++) {
/* 242 */         if (this.values[i] != null && !StyleAttribute.getAttribute(i).isInherited()) {
/* 243 */           return withoutNonInheritableCopy();
/*     */         }
/*     */       } 
/*     */     }
/* 247 */     return this;
/*     */   }
/*     */   
/*     */   private Style withoutNonInheritableCopy() {
/* 251 */     Style result = new Style(this.parent, this.styleSheetKey);
/* 252 */     for (int i = 0, n = this.values.length; i < n; i++) {
/* 253 */       Object value = this.values[i];
/* 254 */       if (value != null) {
/* 255 */         StyleAttribute<?> attribute = StyleAttribute.getAttribute(i);
/* 256 */         if (attribute.isInherited()) {
/* 257 */           result.put(attribute, value);
/*     */         }
/*     */       } 
/*     */     } 
/* 261 */     return result;
/*     */   }
/*     */   
/*     */   protected void put(StyleAttribute<?> attribute, Object value) {
/* 265 */     if (attribute == null) {
/* 266 */       throw new IllegalArgumentException("attribute is null");
/*     */     }
/* 268 */     if (value == null) {
/* 269 */       if (this.values == null) {
/*     */         return;
/*     */       }
/*     */     } else {
/* 273 */       if (!attribute.getDataType().isInstance(value)) {
/* 274 */         throw new IllegalArgumentException("value is a " + value.getClass() + " but must be a " + attribute.getDataType());
/*     */       }
/*     */       
/* 277 */       ensureValues();
/*     */     } 
/*     */     
/* 280 */     this.values[attribute.ordinal()] = value;
/*     */   }
/*     */   
/*     */   protected final void putAll(Map<StyleAttribute<?>, Object> values) {
/* 284 */     for (Map.Entry<StyleAttribute<?>, Object> e : values.entrySet()) {
/* 285 */       put(e.getKey(), e.getValue());
/*     */     }
/*     */   }
/*     */   
/*     */   protected final void putAll(Style src) {
/* 290 */     if (src.values != null) {
/* 291 */       ensureValues();
/* 292 */       for (int i = 0, n = this.values.length; i < n; i++) {
/* 293 */         Object value = src.values[i];
/* 294 */         if (value != null) {
/* 295 */           this.values[i] = value;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected final void ensureValues() {
/* 302 */     if (this.values == null) {
/* 303 */       this.values = new Object[StyleAttribute.getNumAttributes()];
/*     */     }
/*     */   }
/*     */   
/*     */   protected final Object rawGet(int idx) {
/* 308 */     Object[] vals = this.values;
/* 309 */     if (vals != null) {
/* 310 */       return vals[idx];
/*     */     }
/* 312 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<StyleAttribute<?>, Object> toMap() {
/* 321 */     HashMap<StyleAttribute<?>, Object> result = new HashMap<StyleAttribute<?>, Object>();
/* 322 */     for (int ord = 0; ord < this.values.length; ord++) {
/* 323 */       Object value = this.values[ord];
/* 324 */       if (value != null) {
/* 325 */         result.put(StyleAttribute.getAttribute(ord), value);
/*     */       }
/*     */     } 
/* 328 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\textarea\Style.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */