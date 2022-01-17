/*     */ package de.matthiasmann.twl.theme;
/*     */ 
/*     */ import de.matthiasmann.twl.Color;
/*     */ import de.matthiasmann.twl.DebugHook;
/*     */ import de.matthiasmann.twl.ParameterList;
/*     */ import de.matthiasmann.twl.ParameterMap;
/*     */ import de.matthiasmann.twl.renderer.Font;
/*     */ import de.matthiasmann.twl.renderer.Image;
/*     */ import de.matthiasmann.twl.renderer.MouseCursor;
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
/*     */ 
/*     */ public class ParameterListImpl
/*     */   extends ThemeChildImpl
/*     */   implements ParameterList
/*     */ {
/*     */   final ArrayList<Object> params;
/*     */   
/*     */   ParameterListImpl(ThemeManager manager, ThemeInfoImpl parent) {
/*  50 */     super(manager, parent);
/*  51 */     this.params = new ArrayList();
/*     */   }
/*     */   
/*     */   public int getSize() {
/*  55 */     return this.params.size();
/*     */   }
/*     */   
/*     */   public Font getFont(int idx) {
/*  59 */     Font value = getParameterValue(idx, Font.class);
/*  60 */     if (value != null) {
/*  61 */       return value;
/*     */     }
/*  63 */     return this.manager.getDefaultFont();
/*     */   }
/*     */   
/*     */   public Image getImage(int idx) {
/*  67 */     Image img = getParameterValue(idx, Image.class);
/*  68 */     if (img == ImageManager.NONE) {
/*  69 */       return null;
/*     */     }
/*  71 */     return img;
/*     */   }
/*     */   
/*     */   public MouseCursor getMouseCursor(int idx) {
/*  75 */     MouseCursor value = getParameterValue(idx, MouseCursor.class);
/*  76 */     return value;
/*     */   }
/*     */   
/*     */   public ParameterMap getParameterMap(int idx) {
/*  80 */     ParameterMap value = getParameterValue(idx, ParameterMap.class);
/*  81 */     if (value == null) {
/*  82 */       return this.manager.emptyMap;
/*     */     }
/*  84 */     return value;
/*     */   }
/*     */   
/*     */   public ParameterList getParameterList(int idx) {
/*  88 */     ParameterList value = getParameterValue(idx, ParameterList.class);
/*  89 */     if (value == null) {
/*  90 */       return this.manager.emptyList;
/*     */     }
/*  92 */     return value;
/*     */   }
/*     */   
/*     */   public boolean getParameter(int idx, boolean defaultValue) {
/*  96 */     Boolean value = getParameterValue(idx, Boolean.class);
/*  97 */     if (value != null) {
/*  98 */       return value.booleanValue();
/*     */     }
/* 100 */     return defaultValue;
/*     */   }
/*     */   
/*     */   public int getParameter(int idx, int defaultValue) {
/* 104 */     Integer value = getParameterValue(idx, Integer.class);
/* 105 */     if (value != null) {
/* 106 */       return value.intValue();
/*     */     }
/* 108 */     return defaultValue;
/*     */   }
/*     */   
/*     */   public float getParameter(int idx, float defaultValue) {
/* 112 */     Float value = getParameterValue(idx, Float.class);
/* 113 */     if (value != null) {
/* 114 */       return value.floatValue();
/*     */     }
/* 116 */     return defaultValue;
/*     */   }
/*     */   
/*     */   public String getParameter(int idx, String defaultValue) {
/* 120 */     String value = getParameterValue(idx, String.class);
/* 121 */     if (value != null) {
/* 122 */       return value;
/*     */     }
/* 124 */     return defaultValue;
/*     */   }
/*     */   
/*     */   public Color getParameter(int idx, Color defaultValue) {
/* 128 */     Color value = getParameterValue(idx, Color.class);
/* 129 */     if (value != null) {
/* 130 */       return value;
/*     */     }
/* 132 */     return defaultValue;
/*     */   }
/*     */   
/*     */   public <E extends Enum<E>> E getParameter(int idx, E defaultValue) {
/* 136 */     Class<E> enumType = defaultValue.getDeclaringClass();
/* 137 */     Enum enum_ = getParameterValue(idx, enumType);
/* 138 */     if (enum_ != null) {
/* 139 */       return (E)enum_;
/*     */     }
/* 141 */     return defaultValue;
/*     */   }
/*     */   
/*     */   public Object getParameterValue(int idx) {
/* 145 */     return this.params.get(idx);
/*     */   }
/*     */   
/*     */   public <T> T getParameterValue(int idx, Class<T> clazz) {
/* 149 */     Object value = getParameterValue(idx);
/* 150 */     if (value != null && !clazz.isInstance(value)) {
/* 151 */       wrongParameterType(idx, clazz, value.getClass());
/* 152 */       return null;
/*     */     } 
/* 154 */     return clazz.cast(value);
/*     */   }
/*     */   
/*     */   protected void wrongParameterType(int idx, Class<?> expectedType, Class<?> foundType) {
/* 158 */     DebugHook.getDebugHook().wrongParameterType(this, idx, expectedType, foundType, getParentDescription());
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\theme\ParameterListImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */