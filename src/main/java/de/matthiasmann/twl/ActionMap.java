/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.utils.ClassUtils;
/*     */ import de.matthiasmann.twl.utils.HashEntry;
/*     */ import java.lang.annotation.Documented;
/*     */ import java.lang.annotation.ElementType;
/*     */ import java.lang.annotation.Retention;
/*     */ import java.lang.annotation.RetentionPolicy;
/*     */ import java.lang.annotation.Target;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ActionMap
/*     */ {
/*     */   public static final int FLAG_ON_PRESSED = 1;
/*     */   public static final int FLAG_ON_RELEASE = 2;
/*     */   public static final int FLAG_ON_REPEAT = 4;
/*  86 */   private Mapping[] mappings = new Mapping[16];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int numMappings;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean invoke(String action, Event event) {
/*  99 */     Mapping mapping = (Mapping)HashEntry.get((HashEntry[])this.mappings, action);
/* 100 */     if (mapping != null) {
/* 101 */       mapping.call(event);
/* 102 */       return true;
/*     */     } 
/* 104 */     return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addMapping(String action, Object target, String methodName, Object[] params, int flags) throws IllegalArgumentException {
/* 130 */     if (action == null) {
/* 131 */       throw new NullPointerException("action");
/*     */     }
/* 133 */     for (Method m : target.getClass().getMethods()) {
/* 134 */       if (m.getName().equals(methodName) && !Modifier.isStatic(m.getModifiers()) && 
/* 135 */         ClassUtils.isParamsCompatible(m.getParameterTypes(), params)) {
/* 136 */         addMappingImpl(action, target, m, params, flags);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 141 */     throw new IllegalArgumentException("Can't find matching method: " + methodName);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addMapping(String action, Class<?> targetClass, String methodName, Object[] params, int flags) throws IllegalArgumentException {
/* 167 */     if (action == null) {
/* 168 */       throw new NullPointerException("action");
/*     */     }
/* 170 */     for (Method m : targetClass.getMethods()) {
/* 171 */       if (m.getName().equals(methodName) && Modifier.isStatic(m.getModifiers()) && 
/* 172 */         ClassUtils.isParamsCompatible(m.getParameterTypes(), params)) {
/* 173 */         addMappingImpl(action, null, m, params, flags);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 178 */     throw new IllegalArgumentException("Can't find matching method: " + methodName);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addMapping(String action, Object target, Method method, Object[] params, int flags) {
/* 204 */     if (action == null) {
/* 205 */       throw new NullPointerException("action");
/*     */     }
/* 207 */     if (!Modifier.isPublic(method.getModifiers())) {
/* 208 */       throw new IllegalArgumentException("Method is not public");
/*     */     }
/* 210 */     if (target == null && !Modifier.isStatic(method.getModifiers())) {
/* 211 */       throw new IllegalArgumentException("Method is not static but target is null");
/*     */     }
/* 213 */     if (target != null && method.getDeclaringClass().isInstance(target)) {
/* 214 */       throw new IllegalArgumentException("method does not belong to target");
/*     */     }
/* 216 */     if (!ClassUtils.isParamsCompatible(method.getParameterTypes(), params)) {
/* 217 */       throw new IllegalArgumentException("Paramters don't match method");
/*     */     }
/* 219 */     addMappingImpl(action, target, method, params, flags);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addMapping(Object target) {
/* 230 */     for (Method m : target.getClass().getMethods()) {
/* 231 */       Action action = m.<Action>getAnnotation(Action.class);
/* 232 */       if (action != null) {
/* 233 */         if ((m.getParameterTypes()).length > 0) {
/* 234 */           throw new UnsupportedOperationException("automatic binding of actions not supported for methods with parameters");
/*     */         }
/* 236 */         String name = m.getName();
/* 237 */         if (action.name().length() > 0) {
/* 238 */           name = action.name();
/*     */         }
/* 240 */         int flags = (action.onPressed() ? 1 : 0) | (action.onRelease() ? 2 : 0) | (action.onRepeat() ? 4 : 0);
/*     */ 
/*     */ 
/*     */         
/* 244 */         addMappingImpl(name, target, m, null, flags);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void addMappingImpl(String action, Object target, Method method, Object[] params, int flags) {
/* 250 */     this.mappings = (Mapping[])HashEntry.maybeResizeTable((HashEntry[])this.mappings, this.numMappings++);
/* 251 */     HashEntry.insertEntry((HashEntry[])this.mappings, new Mapping(action, target, method, params, flags));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Mapping
/*     */     extends HashEntry<String, Mapping>
/*     */   {
/*     */     final Object target;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     final Method method;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     final Object[] params;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     final int flags;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Mapping(String key, Object target, Method method, Object[] params, int flags) {
/* 293 */       super(key);
/* 294 */       this.target = target;
/* 295 */       this.method = method;
/* 296 */       this.params = params;
/* 297 */       this.flags = flags;
/*     */     }
/*     */     
/*     */     void call(Event e) {
/* 301 */       Event.Type type = e.getType();
/* 302 */       if ((type == Event.Type.KEY_RELEASED && (this.flags & 0x2) != 0) || (type == Event.Type.KEY_PRESSED && (this.flags & 0x1) != 0 && (!e.isKeyRepeated() || (this.flags & 0x4) != 0)))
/*     */         
/*     */         try {
/*     */           
/* 306 */           this.method.invoke(this.target, this.params);
/* 307 */         } catch (Exception ex) {
/* 308 */           Logger.getLogger(ActionMap.class.getName()).log(Level.SEVERE, "Exception while invoking action handler", ex);
/*     */         }  
/*     */     }
/*     */   }
/*     */   
/*     */   @Documented
/*     */   @Retention(RetentionPolicy.RUNTIME)
/*     */   @Target({ElementType.METHOD})
/*     */   public static @interface Action {
/*     */     String name() default "";
/*     */     
/*     */     boolean onPressed() default true;
/*     */     
/*     */     boolean onRelease() default false;
/*     */     
/*     */     boolean onRepeat() default true;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\ActionMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */