/*     */ package drzhark.guiapi;
/*     */ 
/*     */ import de.matthiasmann.twl.CallbackWithReason;
/*     */ import de.matthiasmann.twl.ListBox;
/*     */ import de.matthiasmann.twl.TextArea;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.lang.reflect.Method;
/*     */ import java.security.InvalidParameterException;
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
/*     */ public class ModAction
/*     */   implements Runnable, PropertyChangeListener, TextArea.Callback, CallbackWithReason<ListBox.CallbackReason>
/*     */ {
/*     */   private Object[] defaultArguments;
/*     */   
/*     */   private static Boolean checkArguments(Class[] classTypes, Object[] arguments) {
/*  27 */     if (classTypes.length != arguments.length) {
/*  28 */       return Boolean.valueOf(false);
/*     */     }
/*  30 */     for (int i = 0; i < classTypes.length; i++) {
/*  31 */       if (!classTypes[i].isAssignableFrom(arguments[i].getClass())) {
/*  32 */         return Boolean.valueOf(false);
/*     */       }
/*     */     } 
/*  35 */     return Boolean.valueOf(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  40 */   private ArrayList<ModAction> mergedActions = new ArrayList<>();
/*     */   private String methodName;
/*  42 */   private Class[] methodParams = new Class[0];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Object objectRef;
/*     */ 
/*     */ 
/*     */   
/*     */   private Object tag;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModAction(Object o, String method, Class... params) {
/*  57 */     setTag(method);
/*  58 */     this.methodParams = params;
/*  59 */     setupHandler(o, method);
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
/*     */   public ModAction(Object o, String method, String name, Class... params) {
/*  73 */     this(o, method, params);
/*  74 */     setTag(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ModAction(String name) {
/*  84 */     setTag(name);
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
/*     */   public Object[] call(Object... args) throws Exception {
/*     */     try {
/* 100 */       if (this.mergedActions.isEmpty()) {
/* 101 */         return new Object[] { callInternal(args) };
/*     */       }
/* 103 */       Object[] returnvals = new Object[this.mergedActions.size()];
/* 104 */       for (int i = 0; i < returnvals.length; i++) {
/* 105 */         returnvals[i] = ((ModAction)this.mergedActions.get(i)).call(args);
/*     */       }
/* 107 */       return returnvals;
/* 108 */     } catch (Exception e) {
/* 109 */       e.printStackTrace();
/* 110 */       throw new Exception("error calling callback '" + getTag() + "'.", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void callback(ListBox.CallbackReason reason) {
/* 116 */     if (this.methodParams.length != 1 || this.methodParams[0] != ListBox.CallbackReason.class) {
/* 117 */       throw new RuntimeException("invalid method parameters for a CallbackWithReason<ListBox.CallbackReason> callback. Modaction is '" + 
/* 118 */           getTag() + "'.");
/*     */     }
/*     */     try {
/* 121 */       call(new Object[] { reason });
/* 122 */     } catch (Exception e) {
/* 123 */       e.printStackTrace();
/* 124 */       throw new RuntimeException("Error when calling CallbackWithReason<ListBox.CallbackReason> callback. Modaction is '" + getTag() + "'.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private Object callInternal(Object... args) throws Exception {
/* 129 */     if (!checkArguments(this.methodParams, args).booleanValue() && 
/* 130 */       this.defaultArguments != null) {
/* 131 */       args = this.defaultArguments;
/*     */     }
/*     */     
/*     */     try {
/* 135 */       Method meth = getMethodRecursively(this.objectRef, this.methodName);
/* 136 */       return meth.invoke((this.objectRef instanceof Class) ? null : this.objectRef, args);
/* 137 */     } catch (Exception e) {
/* 138 */       throw new Exception("error calling callback '" + getTag() + "'.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private Method getMethodRecursively(Object o, String method) throws Exception {
/* 143 */     Class<?> currentclass = (o instanceof Class) ? (Class)o : o.getClass();
/*     */     while (true) {
/* 145 */       if (currentclass == null) {
/* 146 */         throw new Exception("Unable to locate method '" + method + "' anywhere in the inheritance chain of object '" + ((o instanceof Class) ? (Class)o : o
/* 147 */             .getClass()).getName() + "'!");
/*     */       }
/*     */       try {
/* 150 */         Method returnval = currentclass.getDeclaredMethod(method, this.methodParams);
/* 151 */         if (returnval != null) {
/* 152 */           returnval.setAccessible(true);
/* 153 */           return returnval;
/*     */         } 
/* 155 */       } catch (Throwable throwable) {}
/*     */       
/* 157 */       currentclass = currentclass.getSuperclass();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getTag() {
/* 165 */     return this.tag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleLinkClicked(String link) {
/* 170 */     if (this.methodParams.length != 1 || this.methodParams[0] != String.class) {
/* 171 */       throw new RuntimeException("invalid method parameters for a TextArea.Callback callback. Modaction is '" + getTag() + "'.");
/*     */     }
/*     */     try {
/* 174 */       call(new Object[] { link });
/* 175 */     } catch (Exception e) {
/* 176 */       e.printStackTrace();
/* 177 */       throw new RuntimeException("Error when calling TextArea.Callback callback. Modaction is '" + getTag() + "'.", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModAction mergeAction(ModAction... newActions) {
/* 188 */     if (this.mergedActions.isEmpty()) {
/* 189 */       ModAction merged = new ModAction("Merged ModAction");
/* 190 */       merged.mergedActions.add(this);
/* 191 */       for (ModAction modAction : newActions) {
/* 192 */         merged.mergedActions.add(modAction);
/*     */       }
/*     */       
/* 195 */       return merged;
/*     */     } 
/* 197 */     for (ModAction modAction : newActions) {
/* 198 */       this.mergedActions.add(modAction);
/*     */     }
/* 200 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void propertyChange(PropertyChangeEvent paramPropertyChangeEvent) {
/* 205 */     if (this.methodParams.length != 1 || this.methodParams[0] != PropertyChangeEvent.class) {
/* 206 */       throw new RuntimeException("invalid method parameters for a PropertyChangeListener callback. Modaction is '" + getTag() + "'.");
/*     */     }
/*     */     try {
/* 209 */       call(new Object[] { paramPropertyChangeEvent });
/* 210 */     } catch (Exception e) {
/* 211 */       e.printStackTrace();
/* 212 */       throw new RuntimeException("Error when calling PropertyChangeListener callback. Modaction is '" + getTag() + "'.", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     try {
/* 219 */       call(new Object[0]);
/* 220 */     } catch (Exception e) {
/* 221 */       e.printStackTrace();
/* 222 */       throw new RuntimeException("Error when calling Runnable callback. Modaction is '" + getTag() + "'.", e);
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
/*     */   public ModAction setDefaultArguments(Object... Arguments) {
/* 235 */     if (!checkArguments(this.methodParams, Arguments).booleanValue()) {
/* 236 */       throw new InvalidParameterException("Arguments do not match the parameters.");
/*     */     }
/* 238 */     this.defaultArguments = Arguments;
/* 239 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTag(Object tag) {
/* 249 */     this.tag = tag;
/*     */   }
/*     */   
/*     */   private void setupHandler(Object o, String method) {
/*     */     try {
/* 254 */       getMethodRecursively(o, method);
/* 255 */     } catch (Exception e) {
/* 256 */       e.printStackTrace();
/* 257 */       throw new RuntimeException("Could not locate Method with included information.", e);
/*     */     } 
/* 259 */     this.methodName = method;
/* 260 */     this.objectRef = o;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 265 */     return "ModAction [methodName=" + this.methodName + ", tag=" + this.tag + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\ModAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */