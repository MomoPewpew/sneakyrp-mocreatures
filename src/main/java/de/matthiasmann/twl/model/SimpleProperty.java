/*     */ package de.matthiasmann.twl.model;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimpleProperty<T>
/*     */   extends AbstractProperty<T>
/*     */ {
/*     */   private final Class<T> type;
/*     */   private final String name;
/*     */   private boolean readOnly;
/*     */   private T value;
/*     */   
/*     */   public SimpleProperty(Class<T> type, String name, T value) {
/*  47 */     this(type, name, value, false);
/*     */   }
/*     */   
/*     */   public SimpleProperty(Class<T> type, String name, T value, boolean readOnly) {
/*  51 */     this.type = type;
/*  52 */     this.name = name;
/*  53 */     this.readOnly = readOnly;
/*  54 */     this.value = value;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  58 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean isReadOnly() {
/*  62 */     return this.readOnly;
/*     */   }
/*     */   
/*     */   public void setReadOnly(boolean readOnly) {
/*  66 */     this.readOnly = readOnly;
/*     */   }
/*     */   
/*     */   public boolean canBeNull() {
/*  70 */     return false;
/*     */   }
/*     */   
/*     */   public T getPropertyValue() {
/*  74 */     return this.value;
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
/*     */   public void setPropertyValue(T value) throws IllegalArgumentException {
/*  88 */     if (value == null && !canBeNull()) {
/*  89 */       throw new NullPointerException("value");
/*     */     }
/*  91 */     if (valueChanged(value)) {
/*  92 */       this.value = value;
/*  93 */       fireValueChangedCallback();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Class<T> getType() {
/*  98 */     return this.type;
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
/*     */   protected boolean valueChanged(T newValue) {
/* 110 */     return (this.value != newValue && (this.value == null || !this.value.equals(newValue)));
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\SimpleProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */