/*     */ package drzhark.guiapi.setting;
/*     */ 
/*     */ import drzhark.guiapi.ModSettings;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SettingKey
/*     */   extends Setting<Integer>
/*     */ {
/*     */   public SettingKey(String title, int key) {
/*  20 */     this.defaultValue = Integer.valueOf(key);
/*  21 */     this.values.put("", Integer.valueOf(key));
/*  22 */     this.backendName = title;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingKey(String title, String key) {
/*  32 */     this(title, Keyboard.getKeyIndex(key));
/*     */   }
/*     */ 
/*     */   
/*     */   public void fromString(String s, String context) {
/*  37 */     if (s.equals("UNBOUND")) {
/*  38 */       this.values.put(context, Integer.valueOf(0));
/*     */     } else {
/*  40 */       this.values.put(context, Integer.valueOf(Keyboard.getKeyIndex(s)));
/*     */     } 
/*  42 */     if (this.displayWidget != null) {
/*  43 */       this.displayWidget.update();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer get(String context) {
/*  49 */     if (this.values.get(context) != null)
/*  50 */       return this.values.get(context); 
/*  51 */     if (this.values.get("") != null) {
/*  52 */       return this.values.get("");
/*     */     }
/*  54 */     return this.defaultValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isKeyDown() {
/*  65 */     return isKeyDown(ModSettings.currentContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isKeyDown(String context) {
/*  75 */     if (get(context).intValue() != -1) {
/*  76 */       return Keyboard.isKeyDown(get(context).intValue());
/*     */     }
/*  78 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(Integer v, String context) {
/*  83 */     this.values.put(context, v);
/*  84 */     if (this.parent != null) {
/*  85 */       this.parent.save(context);
/*     */     }
/*  87 */     if (this.displayWidget != null) {
/*  88 */       this.displayWidget.update();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(String v) {
/*  98 */     set(v, ModSettings.currentContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(String v, String context) {
/* 108 */     set(Integer.valueOf(Keyboard.getKeyIndex(v)), context);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString(String context) {
/* 113 */     return Keyboard.getKeyName(get(context).intValue());
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\setting\SettingKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */