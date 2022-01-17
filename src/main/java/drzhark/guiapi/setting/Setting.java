/*     */ package drzhark.guiapi.setting;
/*     */ 
/*     */ import de.matthiasmann.twl.Widget;
/*     */ import drzhark.guiapi.ModSettings;
/*     */ import drzhark.guiapi.widget.WidgetSetting;
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
/*     */ public abstract class Setting<T>
/*     */   extends Widget
/*     */ {
/*     */   public String backendName;
/*     */   public T defaultValue;
/*  28 */   public WidgetSetting displayWidget = null;
/*     */ 
/*     */ 
/*     */   
/*  32 */   public ModSettings parent = null;
/*     */ 
/*     */ 
/*     */   
/*  36 */   public HashMap<String, T> values = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void copyContext(String srccontext, String destcontext) {
/*  52 */     this.values.put(destcontext, this.values.get(srccontext));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void fromString(String paramString1, String paramString2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public T get() {
/*  66 */     return get(ModSettings.currentContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract T get(String paramString);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/*  82 */     reset(ModSettings.currentContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset(String context) {
/*  92 */     set(this.defaultValue, context);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(T v) {
/* 101 */     set(v, ModSettings.currentContext);
/*     */   }
/*     */   
/*     */   public abstract void set(T paramT, String paramString);
/*     */   
/*     */   public abstract String toString(String paramString);
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\setting\Setting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */