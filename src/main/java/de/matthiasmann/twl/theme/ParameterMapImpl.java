/*     */ package de.matthiasmann.twl.theme;
/*     */ 
/*     */ import de.matthiasmann.twl.Color;
/*     */ import de.matthiasmann.twl.DebugHook;
/*     */ import de.matthiasmann.twl.ParameterList;
/*     */ import de.matthiasmann.twl.ParameterMap;
/*     */ import de.matthiasmann.twl.renderer.Font;
/*     */ import de.matthiasmann.twl.renderer.Image;
/*     */ import de.matthiasmann.twl.renderer.MouseCursor;
/*     */ import de.matthiasmann.twl.utils.CascadedHashMap;
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
/*     */ class ParameterMapImpl
/*     */   extends ThemeChildImpl
/*     */   implements ParameterMap
/*     */ {
/*     */   private final CascadedHashMap<String, Object> params;
/*     */   
/*     */   ParameterMapImpl(ThemeManager manager, ThemeInfoImpl parent) {
/*  51 */     super(manager, parent);
/*  52 */     this.params = new CascadedHashMap();
/*     */   }
/*     */   
/*     */   void copy(ParameterMapImpl src) {
/*  56 */     this.params.collapseAndSetFallback(src.params);
/*     */   }
/*     */   
/*     */   public Font getFont(String name) {
/*  60 */     Font value = getParameterValue(name, true, Font.class);
/*  61 */     if (value != null) {
/*  62 */       return value;
/*     */     }
/*  64 */     return this.manager.getDefaultFont();
/*     */   }
/*     */   
/*     */   public Image getImage(String name) {
/*  68 */     Image img = getParameterValue(name, true, Image.class);
/*  69 */     if (img == ImageManager.NONE) {
/*  70 */       return null;
/*     */     }
/*  72 */     return img;
/*     */   }
/*     */   
/*     */   public MouseCursor getMouseCursor(String name) {
/*  76 */     MouseCursor value = getParameterValue(name, false, MouseCursor.class);
/*  77 */     return value;
/*     */   }
/*     */   
/*     */   public ParameterMap getParameterMap(String name) {
/*  81 */     ParameterMap value = getParameterValue(name, true, ParameterMap.class);
/*  82 */     if (value == null) {
/*  83 */       return this.manager.emptyMap;
/*     */     }
/*  85 */     return value;
/*     */   }
/*     */   
/*     */   public ParameterList getParameterList(String name) {
/*  89 */     ParameterList value = getParameterValue(name, true, ParameterList.class);
/*  90 */     if (value == null) {
/*  91 */       return this.manager.emptyList;
/*     */     }
/*  93 */     return value;
/*     */   }
/*     */   
/*     */   public boolean getParameter(String name, boolean defaultValue) {
/*  97 */     Boolean value = getParameterValue(name, true, Boolean.class);
/*  98 */     if (value != null) {
/*  99 */       return value.booleanValue();
/*     */     }
/* 101 */     return defaultValue;
/*     */   }
/*     */   
/*     */   public int getParameter(String name, int defaultValue) {
/* 105 */     Integer value = getParameterValue(name, true, Integer.class);
/* 106 */     if (value != null) {
/* 107 */       return value.intValue();
/*     */     }
/* 109 */     return defaultValue;
/*     */   }
/*     */   
/*     */   public float getParameter(String name, float defaultValue) {
/* 113 */     Float value = getParameterValue(name, true, Float.class);
/* 114 */     if (value != null) {
/* 115 */       return value.floatValue();
/*     */     }
/* 117 */     return defaultValue;
/*     */   }
/*     */   
/*     */   public String getParameter(String name, String defaultValue) {
/* 121 */     String value = getParameterValue(name, true, String.class);
/* 122 */     if (value != null) {
/* 123 */       return value;
/*     */     }
/* 125 */     return defaultValue;
/*     */   }
/*     */   
/*     */   public Color getParameter(String name, Color defaultValue) {
/* 129 */     Color value = getParameterValue(name, true, Color.class);
/* 130 */     if (value != null) {
/* 131 */       return value;
/*     */     }
/* 133 */     return defaultValue;
/*     */   }
/*     */   
/*     */   public <E extends Enum<E>> E getParameter(String name, E defaultValue) {
/* 137 */     Class<E> enumType = defaultValue.getDeclaringClass();
/* 138 */     Enum enum_ = getParameterValue(name, true, enumType);
/* 139 */     if (enum_ != null) {
/* 140 */       return (E)enum_;
/*     */     }
/* 142 */     return defaultValue;
/*     */   }
/*     */   
/*     */   public Object getParameterValue(String name, boolean warnIfNotPresent) {
/* 146 */     Object value = this.params.get(name);
/* 147 */     if (value == null && warnIfNotPresent) {
/* 148 */       missingParameter(name, (Class<?>)null);
/*     */     }
/* 150 */     return value;
/*     */   }
/*     */   
/*     */   public <T> T getParameterValue(String name, boolean warnIfNotPresent, Class<T> clazz) {
/* 154 */     return getParameterValue(name, warnIfNotPresent, clazz, (T)null);
/*     */   }
/*     */   
/*     */   public <T> T getParameterValue(String name, boolean warnIfNotPresent, Class<T> clazz, T defaultValue) {
/* 158 */     Object value = this.params.get(name);
/* 159 */     if (value == null && warnIfNotPresent) {
/* 160 */       missingParameter(name, clazz);
/*     */     }
/* 162 */     if (!clazz.isInstance(value)) {
/* 163 */       if (value != null) {
/* 164 */         wrongParameterType(name, clazz, value.getClass());
/*     */       }
/* 166 */       return defaultValue;
/*     */     } 
/* 168 */     return clazz.cast(value);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void wrongParameterType(String paramName, Class<?> expectedType, Class<?> foundType) {
/* 173 */     DebugHook.getDebugHook().wrongParameterType(this, paramName, expectedType, foundType, getParentDescription());
/*     */   }
/*     */   
/*     */   protected void missingParameter(String paramName, Class<?> dataType) {
/* 177 */     DebugHook.getDebugHook().missingParameter(this, paramName, getParentDescription(), dataType);
/*     */   }
/*     */   
/*     */   protected void replacingWithDifferentType(String paramName, Class<?> oldType, Class<?> newType) {
/* 181 */     DebugHook.getDebugHook().replacingWithDifferentType(this, paramName, oldType, newType, getParentDescription());
/*     */   }
/*     */   
/*     */   Object getParam(String name) {
/* 185 */     return this.params.get(name);
/*     */   }
/*     */   
/*     */   void put(Map<String, ?> params) {
/* 189 */     for (Map.Entry<String, ?> e : params.entrySet()) {
/* 190 */       put(e.getKey(), e.getValue());
/*     */     }
/*     */   }
/*     */   
/*     */   void put(String paramName, Object value) {
/* 195 */     Object old = this.params.put(paramName, value);
/* 196 */     if (old != null && value != null) {
/* 197 */       Class<?> oldClass = old.getClass();
/* 198 */       Class<?> newClass = value.getClass();
/*     */       
/* 200 */       if (oldClass != newClass && !areTypesCompatible(oldClass, newClass)) {
/* 201 */         replacingWithDifferentType(paramName, oldClass, newClass);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean areTypesCompatible(Class<?> classA, Class<?> classB) {
/* 207 */     for (Class<?> clazz : BASE_CLASSES) {
/* 208 */       if (clazz.isAssignableFrom(classA) && clazz.isAssignableFrom(classB)) {
/* 209 */         return true;
/*     */       }
/*     */     } 
/* 212 */     return false;
/*     */   }
/*     */   
/* 215 */   private static final Class<?>[] BASE_CLASSES = new Class[] { Image.class, Font.class, MouseCursor.class };
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\theme\ParameterMapImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */