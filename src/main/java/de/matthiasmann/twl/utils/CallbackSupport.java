/*     */ package de.matthiasmann.twl.utils;
/*     */ 
/*     */ import de.matthiasmann.twl.CallbackWithReason;
/*     */ import java.lang.reflect.Array;
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
/*     */ public class CallbackSupport
/*     */ {
/*     */   private static void checkNotNull(Object callback) {
/*  52 */     if (callback == null) {
/*  53 */       throw new NullPointerException("callback");
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
/*     */   public static <T> T[] addCallbackToList(T[] curList, T callback, Class<T> clazz) {
/*  71 */     checkNotNull(callback);
/*  72 */     int curLength = (curList == null) ? 0 : curList.length;
/*  73 */     T[] newList = (T[])Array.newInstance(clazz, curLength + 1);
/*  74 */     if (curLength > 0) {
/*  75 */       System.arraycopy(curList, 0, newList, 0, curLength);
/*     */     }
/*  77 */     newList[curLength] = callback;
/*  78 */     return newList;
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
/*     */   public static <T> int findCallbackPosition(T[] list, T callback) {
/*  90 */     checkNotNull(callback);
/*  91 */     if (list != null) {
/*  92 */       for (int i = 0, n = list.length; i < n; i++) {
/*  93 */         if (list[i] == callback) {
/*  94 */           return i;
/*     */         }
/*     */       } 
/*     */     }
/*  98 */     return -1;
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
/*     */   public static <T> T[] removeCallbackFromList(T[] curList, int index) {
/* 114 */     int curLength = curList.length;
/* 115 */     assert index >= 0 && index < curLength;
/* 116 */     if (curLength == 1) {
/* 117 */       return null;
/*     */     }
/* 119 */     int newLength = curLength - 1;
/* 120 */     T[] newList = (T[])Array.newInstance(curList.getClass().getComponentType(), newLength);
/* 121 */     System.arraycopy(curList, 0, newList, 0, index);
/* 122 */     System.arraycopy(curList, index + 1, newList, index, newLength - index);
/* 123 */     return newList;
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
/*     */   public static <T> T[] removeCallbackFromList(T[] curList, T callback) {
/* 138 */     int idx = findCallbackPosition(curList, callback);
/* 139 */     if (idx >= 0) {
/* 140 */       curList = removeCallbackFromList(curList, idx);
/*     */     }
/* 142 */     return curList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void fireCallbacks(Runnable[] callbacks) {
/* 151 */     if (callbacks != null) {
/* 152 */       for (Runnable cb : callbacks) {
/* 153 */         cb.run();
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
/*     */   public static <T extends Enum<T>> void fireCallbacks(CallbackWithReason<?>[] callbacks, T reason) {
/* 167 */     if (callbacks != null)
/* 168 */       for (CallbackWithReason<?> cb : callbacks)
/* 169 */         cb.callback((Enum)reason);  
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\tw\\utils\CallbackSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */