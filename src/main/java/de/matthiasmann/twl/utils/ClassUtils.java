/*    */ package de.matthiasmann.twl.utils;
/*    */ 
/*    */ import java.util.HashMap;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClassUtils
/*    */ {
/* 43 */   private static final HashMap<Class<?>, Class<?>> primitiveTypeMap = new HashMap<Class<?>, Class<?>>();
/*    */   static {
/* 45 */     primitiveTypeMap.put(boolean.class, Boolean.class);
/* 46 */     primitiveTypeMap.put(byte.class, Byte.class);
/* 47 */     primitiveTypeMap.put(short.class, Short.class);
/* 48 */     primitiveTypeMap.put(char.class, Character.class);
/* 49 */     primitiveTypeMap.put(int.class, Integer.class);
/* 50 */     primitiveTypeMap.put(long.class, Long.class);
/* 51 */     primitiveTypeMap.put(float.class, Float.class);
/* 52 */     primitiveTypeMap.put(double.class, Double.class);
/*    */   }
/*    */   
/*    */   public static Class<?> mapPrimitiveToWrapper(Class<?> clazz) {
/* 56 */     Class<?> mappedClass = primitiveTypeMap.get(clazz);
/* 57 */     return (mappedClass != null) ? mappedClass : clazz;
/*    */   }
/*    */   
/*    */   public static boolean isParamCompatible(Class<?> type, Object obj) {
/* 61 */     if (obj == null && !type.isPrimitive()) {
/* 62 */       return true;
/*    */     }
/* 64 */     type = mapPrimitiveToWrapper(type);
/* 65 */     return type.isInstance(obj);
/*    */   }
/*    */   
/*    */   public static boolean isParamsCompatible(Class<?>[] types, Object[] params) {
/* 69 */     if (types.length != params.length) {
/* 70 */       return false;
/*    */     }
/* 72 */     for (int i = 0; i < types.length; i++) {
/* 73 */       if (!isParamCompatible(types[i], params[i])) {
/* 74 */         return false;
/*    */       }
/*    */     } 
/* 77 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\tw\\utils\ClassUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */