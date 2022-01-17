/*     */ package de.matthiasmann.twl.renderer;
/*     */ 
/*     */ import de.matthiasmann.twl.Color;
/*     */ import java.util.HashMap;
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
/*     */ public final class FontParameter
/*     */ {
/*  42 */   static final HashMap<String, Parameter<?>> parameterMap = new HashMap<String, Parameter<?>>();
/*     */   
/*  44 */   public static final Parameter<Color> COLOR = newParameter("color", Color.WHITE);
/*  45 */   public static final Parameter<Boolean> UNDERLINE = newParameter("underline", Boolean.valueOf(false));
/*  46 */   public static final Parameter<Boolean> LINETHROUGH = newParameter("linethrough", Boolean.valueOf(false));
/*     */   
/*     */   private Object[] values;
/*     */   
/*     */   public FontParameter() {
/*  51 */     this.values = new Object[8];
/*     */   }
/*     */   
/*     */   public FontParameter(FontParameter base) {
/*  55 */     this.values = (Object[])base.values.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> void put(Parameter<T> param, T value) {
/*  65 */     if (param == null) {
/*  66 */       throw new NullPointerException("type");
/*     */     }
/*  68 */     if (value != null && !param.dataClass.isInstance(value)) {
/*  69 */       throw new ClassCastException("value");
/*     */     }
/*  71 */     int ordinal = param.ordinal;
/*  72 */     int curLength = this.values.length;
/*  73 */     if (ordinal >= curLength) {
/*  74 */       Object[] tmp = new Object[Math.max(ordinal + 1, curLength * 2)];
/*  75 */       System.arraycopy(this.values, 0, tmp, 0, curLength);
/*  76 */       this.values = tmp;
/*     */     } 
/*  78 */     this.values[ordinal] = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> T get(Parameter<T> param) {
/*  88 */     if (param.ordinal < this.values.length) {
/*  89 */       Object raw = this.values[param.ordinal];
/*  90 */       if (raw != null) {
/*  91 */         return param.dataClass.cast(raw);
/*     */       }
/*     */     } 
/*  94 */     return param.defaultValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Parameter[] getRegisteredParameter() {
/* 102 */     synchronized (parameterMap) {
/* 103 */       return (Parameter[])parameterMap.values().toArray((Object[])new Parameter[parameterMap.size()]);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Parameter<?> getParameter(String name) {
/* 113 */     synchronized (parameterMap) {
/* 114 */       return parameterMap.get(name);
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
/*     */ 
/*     */   
/*     */   public static <T> Parameter<T> newParameter(String name, T defaultValue) {
/* 133 */     if (defaultValue == null) {
/* 134 */       throw new NullPointerException("defaultValue");
/*     */     }
/*     */     
/* 137 */     Class<T> dataClass = (Class)defaultValue.getClass();
/* 138 */     return newParameter(name, dataClass, defaultValue);
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
/*     */   public static <T> Parameter<T> newParameter(String name, Class<T> dataClass, T defaultValue) {
/* 156 */     if (name == null) {
/* 157 */       throw new NullPointerException("name");
/*     */     }
/* 159 */     if (dataClass == null) {
/* 160 */       throw new NullPointerException("dataClass");
/*     */     }
/*     */     
/* 163 */     synchronized (parameterMap) {
/* 164 */       Parameter<?> existing = parameterMap.get(name);
/* 165 */       if (existing != null) {
/* 166 */         if (existing.dataClass != dataClass || !equals(existing.defaultValue, defaultValue)) {
/* 167 */           throw new IllegalStateException("type '" + name + "' already registered but different");
/*     */         }
/*     */ 
/*     */         
/* 171 */         Parameter<T> parameter = (Parameter)existing;
/* 172 */         return parameter;
/*     */       } 
/*     */       
/* 175 */       Parameter<T> type = new Parameter<T>(name, dataClass, defaultValue, parameterMap.size());
/* 176 */       parameterMap.put(name, type);
/* 177 */       return type;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean equals(Object a, Object b) {
/* 182 */     return (a == b || (a != null && a.equals(b)));
/*     */   }
/*     */   
/*     */   public static final class Parameter<T> {
/*     */     final String name;
/*     */     final Class<T> dataClass;
/*     */     final T defaultValue;
/*     */     final int ordinal;
/*     */     
/*     */     Parameter(String name, Class<T> dataClass, T defaultValue, int ordinal) {
/* 192 */       this.name = name;
/* 193 */       this.dataClass = dataClass;
/* 194 */       this.defaultValue = defaultValue;
/* 195 */       this.ordinal = ordinal;
/*     */     }
/*     */     
/*     */     public final String getName() {
/* 199 */       return this.name;
/*     */     }
/*     */     
/*     */     public final Class<T> getDataClass() {
/* 203 */       return this.dataClass;
/*     */     }
/*     */     
/*     */     public final T getDefaultValue() {
/* 207 */       return this.defaultValue;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 212 */       return this.ordinal + ":" + this.name + ":" + this.dataClass.getSimpleName();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\FontParameter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */