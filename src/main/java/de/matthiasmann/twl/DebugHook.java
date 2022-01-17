/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import java.util.Collection;
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
/*     */ public class DebugHook
/*     */ {
/*  42 */   private static ThreadLocal<DebugHook> tls = new ThreadLocal<DebugHook>()
/*     */     {
/*     */       protected DebugHook initialValue() {
/*  45 */         return new DebugHook();
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DebugHook getDebugHook() {
/*  54 */     return tls.get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DebugHook installHook(DebugHook hook) {
/*  65 */     if (hook == null) {
/*  66 */       throw new NullPointerException("hook");
/*     */     }
/*  68 */     DebugHook old = tls.get();
/*  69 */     tls.set(hook);
/*  70 */     return old;
/*     */   }
/*     */ 
/*     */   
/*     */   public void beforeApplyTheme(Widget widget) {}
/*     */ 
/*     */   
/*     */   public void afterApplyTheme(Widget widget) {}
/*     */   
/*     */   public void missingTheme(String themePath) {
/*  80 */     System.err.println("Could not find theme: " + themePath);
/*     */   }
/*     */   
/*     */   public void missingChildTheme(ThemeInfo parent, String theme) {
/*  84 */     System.err.println("Missing child theme \"" + theme + "\" for \"" + parent.getThemePath() + "\"");
/*     */   }
/*     */   
/*     */   public void missingParameter(ParameterMap map, String paramName, String parentDescription, Class<?> dataType) {
/*  88 */     StringBuilder sb = (new StringBuilder("Parameter \"")).append(paramName).append("\" ");
/*  89 */     if (dataType != null) {
/*  90 */       sb.append("of type ");
/*  91 */       if (dataType.isEnum()) {
/*  92 */         sb.append("enum ");
/*     */       }
/*  94 */       sb.append('"').append(dataType.getSimpleName()).append('"');
/*     */     } 
/*  96 */     sb.append(" not set");
/*  97 */     if (map instanceof ThemeInfo) {
/*  98 */       sb.append(" for \"").append(((ThemeInfo)map).getThemePath()).append("\"");
/*     */     } else {
/* 100 */       sb.append(parentDescription);
/*     */     } 
/* 102 */     System.err.println(sb.toString());
/*     */   }
/*     */   
/*     */   public void wrongParameterType(ParameterMap map, String paramName, Class<?> expectedType, Class<?> foundType, String parentDescription) {
/* 106 */     System.err.println("Parameter \"" + paramName + "\" is a " + foundType.getSimpleName() + " expected a " + expectedType.getSimpleName() + parentDescription);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void wrongParameterType(ParameterList map, int idx, Class<?> expectedType, Class<?> foundType, String parentDescription) {
/* 112 */     System.err.println("Parameter at index " + idx + " is a " + foundType.getSimpleName() + " expected a " + expectedType.getSimpleName() + parentDescription);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void replacingWithDifferentType(ParameterMap map, String paramName, Class<?> oldType, Class<?> newType, String parentDescription) {
/* 118 */     System.err.println("Paramter \"" + paramName + "\" of type " + oldType + " is replaced with type " + newType + parentDescription);
/*     */   }
/*     */ 
/*     */   
/*     */   public void missingImage(String name) {
/* 123 */     System.err.println("Could not find image: " + name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void guiLayoutValidated(int iterations, Collection<Widget> loop) {
/* 132 */     if (loop != null) {
/* 133 */       System.err.println("WARNING: layout loop detected - printing");
/* 134 */       int index = 1;
/* 135 */       for (Widget w : loop) {
/* 136 */         System.err.println(index + ": " + w);
/* 137 */         index++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void usingFallbackTheme(String themePath) {}
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\DebugHook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */