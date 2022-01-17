/*     */ package de.matthiasmann.twl.utils;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.text.ParseException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
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
/*     */ public abstract class AbstractMathInterpreter
/*     */   implements SimpleMathParser.Interpreter
/*     */ {
/*     */   private final ArrayList<Object> stack;
/*     */   private final HashMap<String, Function> functions;
/*     */   
/*     */   public AbstractMathInterpreter() {
/*  56 */     this.stack = new ArrayList();
/*  57 */     this.functions = new HashMap<String, Function>();
/*     */     
/*  59 */     registerFunction("min", new FunctionMin());
/*  60 */     registerFunction("max", new FunctionMax());
/*     */   }
/*     */   
/*     */   public final void registerFunction(String name, Function function) {
/*  64 */     if (function == null) {
/*  65 */       throw new NullPointerException("function");
/*     */     }
/*  67 */     this.functions.put(name, function);
/*     */   }
/*     */   
/*     */   public Number execute(String str) throws ParseException {
/*  71 */     this.stack.clear();
/*  72 */     SimpleMathParser.interpret(str, this);
/*  73 */     if (this.stack.size() != 1) {
/*  74 */       throw new IllegalStateException("Expected one return value on the stack");
/*     */     }
/*  76 */     return popNumber();
/*     */   }
/*     */   
/*     */   public int[] executeIntArray(String str) throws ParseException {
/*  80 */     this.stack.clear();
/*  81 */     int count = SimpleMathParser.interpretArray(str, this);
/*  82 */     if (this.stack.size() != count) {
/*  83 */       throw new IllegalStateException("Expected " + count + " return values on the stack");
/*     */     }
/*  85 */     int[] result = new int[count];
/*  86 */     for (int i = count; i-- > 0;) {
/*  87 */       result[i] = popNumber().intValue();
/*     */     }
/*  89 */     return result;
/*     */   }
/*     */   
/*     */   public <T> T executeCreateObject(String str, Class<T> type) throws ParseException {
/*  93 */     this.stack.clear();
/*  94 */     int count = SimpleMathParser.interpretArray(str, this);
/*  95 */     if (this.stack.size() != count) {
/*  96 */       throw new IllegalStateException("Expected " + count + " return values on the stack");
/*     */     }
/*  98 */     if (count == 1 && type.isInstance(this.stack.get(0))) {
/*  99 */       return type.cast(this.stack.get(0));
/*     */     }
/* 101 */     for (Constructor<?> c : type.getConstructors()) {
/* 102 */       Class<?>[] params = c.getParameterTypes();
/* 103 */       if (params.length == count) {
/* 104 */         boolean match = true;
/* 105 */         for (int i = 0; i < count; i++) {
/* 106 */           if (!ClassUtils.isParamCompatible(params[i], this.stack.get(i))) {
/* 107 */             match = false;
/*     */             break;
/*     */           } 
/*     */         } 
/* 111 */         if (match) {
/*     */           try {
/* 113 */             return type.cast(c.newInstance(this.stack.toArray(new Object[count])));
/* 114 */           } catch (Exception ex) {
/* 115 */             Logger.getLogger(AbstractMathInterpreter.class.getName()).log(Level.SEVERE, "can't instantiate object", ex);
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 121 */     throw new IllegalArgumentException("Can't construct a " + type + " from expression: \"" + str + "\"");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void push(Object obj) {
/* 126 */     this.stack.add(obj);
/*     */   }
/*     */   
/*     */   protected Object pop() {
/* 130 */     int size = this.stack.size();
/* 131 */     if (size == 0) {
/* 132 */       throw new IllegalStateException("stack underflow");
/*     */     }
/* 134 */     return this.stack.remove(size - 1);
/*     */   }
/*     */   
/*     */   protected Number popNumber() {
/* 138 */     Object obj = pop();
/* 139 */     if (obj instanceof Number) {
/* 140 */       return (Number)obj;
/*     */     }
/* 142 */     throw new IllegalStateException("expected number on stack - found: " + ((obj != null) ? obj.getClass() : "null"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadConst(Number n) {
/* 147 */     push(n);
/*     */   }
/*     */   
/*     */   public void add() {
/* 151 */     Number b = popNumber();
/* 152 */     Number a = popNumber();
/* 153 */     boolean isFloat = (isFloat(a) || isFloat(b));
/* 154 */     if (isFloat) {
/* 155 */       push(Float.valueOf(a.floatValue() + b.floatValue()));
/*     */     } else {
/* 157 */       push(Integer.valueOf(a.intValue() + b.intValue()));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void sub() {
/* 162 */     Number b = popNumber();
/* 163 */     Number a = popNumber();
/* 164 */     boolean isFloat = (isFloat(a) || isFloat(b));
/* 165 */     if (isFloat) {
/* 166 */       push(Float.valueOf(a.floatValue() - b.floatValue()));
/*     */     } else {
/* 168 */       push(Integer.valueOf(a.intValue() - b.intValue()));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void mul() {
/* 173 */     Number b = popNumber();
/* 174 */     Number a = popNumber();
/* 175 */     boolean isFloat = (isFloat(a) || isFloat(b));
/* 176 */     if (isFloat) {
/* 177 */       push(Float.valueOf(a.floatValue() * b.floatValue()));
/*     */     } else {
/* 179 */       push(Integer.valueOf(a.intValue() * b.intValue()));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void div() {
/* 184 */     Number b = popNumber();
/* 185 */     Number a = popNumber();
/* 186 */     boolean isFloat = (isFloat(a) || isFloat(b));
/* 187 */     if (isFloat) {
/* 188 */       if (Math.abs(b.floatValue()) == 0.0F) {
/* 189 */         throw new IllegalStateException("division by zero");
/*     */       }
/* 191 */       push(Float.valueOf(a.floatValue() / b.floatValue()));
/*     */     } else {
/* 193 */       if (b.intValue() == 0) {
/* 194 */         throw new IllegalStateException("division by zero");
/*     */       }
/* 196 */       push(Integer.valueOf(a.intValue() / b.intValue()));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void negate() {
/* 201 */     Number a = popNumber();
/* 202 */     if (isFloat(a)) {
/* 203 */       push(Float.valueOf(-a.floatValue()));
/*     */     } else {
/* 205 */       push(Integer.valueOf(-a.intValue()));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void accessArray() {
/* 210 */     Number idx = popNumber();
/* 211 */     Object obj = pop();
/* 212 */     if (obj == null) {
/* 213 */       throw new IllegalStateException("null pointer");
/*     */     }
/* 215 */     if (!obj.getClass().isArray()) {
/* 216 */       throw new IllegalStateException("array expected");
/*     */     }
/*     */     try {
/* 219 */       push(Array.get(obj, idx.intValue()));
/* 220 */     } catch (ArrayIndexOutOfBoundsException ex) {
/* 221 */       throw new IllegalStateException("array index out of bounds", ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void accessField(String field) {
/* 226 */     Object obj = pop();
/* 227 */     if (obj == null) {
/* 228 */       throw new IllegalStateException("null pointer");
/*     */     }
/* 230 */     Object result = accessField(obj, field);
/* 231 */     push(result);
/*     */   }
/*     */   
/*     */   protected Object accessField(Object obj, String field) {
/* 235 */     Class<? extends Object> clazz = (Class)obj.getClass();
/*     */     try {
/* 237 */       if (clazz.isArray()) {
/* 238 */         if ("length".equals(field)) {
/* 239 */           return Integer.valueOf(Array.getLength(obj));
/*     */         }
/*     */       } else {
/* 242 */         Method m = findGetter(clazz, field);
/* 243 */         if (m == null) {
/* 244 */           for (Class<?> i : clazz.getInterfaces()) {
/* 245 */             m = findGetter(i, field);
/* 246 */             if (m != null) {
/*     */               break;
/*     */             }
/*     */           } 
/*     */         }
/* 251 */         if (m != null) {
/* 252 */           return m.invoke(obj, new Object[0]);
/*     */         }
/*     */       } 
/* 255 */     } catch (Throwable ex) {
/* 256 */       throw new IllegalStateException("error accessing field '" + field + "' of class '" + clazz + "'", ex);
/*     */     } 
/*     */     
/* 259 */     throw new IllegalStateException("unknown field '" + field + "' of class '" + clazz + "'");
/*     */   }
/*     */ 
/*     */   
/*     */   private static Method findGetter(Class<?> clazz, String field) {
/* 264 */     for (Method m : clazz.getMethods()) {
/* 265 */       if (!Modifier.isStatic(m.getModifiers()) && m.getReturnType() != void.class && Modifier.isPublic(m.getDeclaringClass().getModifiers()) && (m.getParameterTypes()).length == 0 && (cmpName(m, field, "get") || cmpName(m, field, "is")))
/*     */       {
/*     */ 
/*     */ 
/*     */         
/* 270 */         return m;
/*     */       }
/*     */     } 
/* 273 */     return null;
/*     */   }
/*     */   
/*     */   private static boolean cmpName(Method m, String fieldName, String prefix) {
/* 277 */     String methodName = m.getName();
/* 278 */     int prefixLength = prefix.length();
/* 279 */     int fieldNameLength = fieldName.length();
/* 280 */     return (methodName.length() == prefixLength + fieldNameLength && methodName.startsWith(prefix) && methodName.charAt(prefixLength) == Character.toUpperCase(fieldName.charAt(0)) && methodName.regionMatches(prefixLength + 1, fieldName, 1, fieldNameLength - 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void callFunction(String name, int args) {
/* 287 */     Object[] values = new Object[args];
/* 288 */     for (int i = args; i-- > 0;) {
/* 289 */       values[i] = pop();
/*     */     }
/* 291 */     Function function = this.functions.get(name);
/* 292 */     if (function == null) {
/* 293 */       throw new IllegalArgumentException("Unknown function");
/*     */     }
/* 295 */     push(function.execute(values));
/*     */   } public static interface Function {
/*     */     Object execute(Object... param1VarArgs); }
/*     */   protected static boolean isFloat(Number n) {
/* 299 */     return !(n instanceof Integer);
/*     */   }
/*     */   
/*     */   public static abstract class NumberFunction
/*     */     implements Function {
/*     */     protected abstract Object execute(int... param1VarArgs);
/*     */     
/*     */     public Object execute(Object... args) {
/* 307 */       for (Object o : args) {
/* 308 */         if (!(o instanceof Integer)) {
/* 309 */           float[] arrayOfFloat = new float[args.length];
/* 310 */           for (int j = 0; j < arrayOfFloat.length; j++) {
/* 311 */             arrayOfFloat[j] = ((Number)args[j]).floatValue();
/*     */           }
/* 313 */           return execute(arrayOfFloat);
/*     */         } 
/*     */       } 
/* 316 */       int[] values = new int[args.length];
/* 317 */       for (int i = 0; i < values.length; i++) {
/* 318 */         values[i] = ((Number)args[i]).intValue();
/*     */       }
/* 320 */       return execute(values);
/*     */     }
/*     */     
/*     */     protected abstract Object execute(float... param1VarArgs); }
/*     */   
/*     */   static class FunctionMin extends NumberFunction {
/*     */     protected Object execute(int... values) {
/* 327 */       int result = values[0];
/* 328 */       for (int i = 1; i < values.length; i++) {
/* 329 */         result = Math.min(result, values[i]);
/*     */       }
/* 331 */       return Integer.valueOf(result);
/*     */     }
/*     */     
/*     */     protected Object execute(float... values) {
/* 335 */       float result = values[0];
/* 336 */       for (int i = 1; i < values.length; i++) {
/* 337 */         result = Math.min(result, values[i]);
/*     */       }
/* 339 */       return Float.valueOf(result);
/*     */     }
/*     */   }
/*     */   
/*     */   static class FunctionMax
/*     */     extends NumberFunction {
/*     */     protected Object execute(int... values) {
/* 346 */       int result = values[0];
/* 347 */       for (int i = 1; i < values.length; i++) {
/* 348 */         result = Math.max(result, values[i]);
/*     */       }
/* 350 */       return Integer.valueOf(result);
/*     */     }
/*     */     
/*     */     protected Object execute(float... values) {
/* 354 */       float result = values[0];
/* 355 */       for (int i = 1; i < values.length; i++) {
/* 356 */         result = Math.max(result, values[i]);
/*     */       }
/* 358 */       return Float.valueOf(result);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\tw\\utils\AbstractMathInterpreter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */