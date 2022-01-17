/*     */ package de.matthiasmann.twl.model;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimplePropertyList
/*     */   extends AbstractProperty<PropertyList>
/*     */   implements PropertyList
/*     */ {
/*     */   private final String name;
/*     */   private final ArrayList<Property<?>> properties;
/*     */   
/*     */   public SimplePropertyList(String name) {
/*  46 */     this.name = name;
/*  47 */     this.properties = new ArrayList<Property<?>>();
/*     */   }
/*     */   
/*     */   public SimplePropertyList(String name, Property<?>... properties) {
/*  51 */     this(name);
/*  52 */     this.properties.addAll(Arrays.asList(properties));
/*     */   }
/*     */   
/*     */   public String getName() {
/*  56 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean isReadOnly() {
/*  60 */     return true;
/*     */   }
/*     */   
/*     */   public boolean canBeNull() {
/*  64 */     return false;
/*     */   }
/*     */   
/*     */   public PropertyList getPropertyValue() {
/*  68 */     return this;
/*     */   }
/*     */   
/*     */   public void setPropertyValue(PropertyList value) throws IllegalArgumentException {
/*  72 */     throw new UnsupportedOperationException("Not supported");
/*     */   }
/*     */   
/*     */   public Class<PropertyList> getType() {
/*  76 */     return PropertyList.class;
/*     */   }
/*     */   
/*     */   public int getNumProperties() {
/*  80 */     return this.properties.size();
/*     */   }
/*     */   
/*     */   public Property<?> getProperty(int idx) {
/*  84 */     return this.properties.get(idx);
/*     */   }
/*     */   
/*     */   public void addProperty(Property<?> property) {
/*  88 */     this.properties.add(property);
/*  89 */     fireValueChangedCallback();
/*     */   }
/*     */   
/*     */   public void addProperty(int idx, Property<?> property) {
/*  93 */     this.properties.add(idx, property);
/*  94 */     fireValueChangedCallback();
/*     */   }
/*     */   
/*     */   public void removeProperty(int idx) {
/*  98 */     this.properties.remove(idx);
/*  99 */     fireValueChangedCallback();
/*     */   }
/*     */   
/*     */   public void removeAllProperties() {
/* 103 */     this.properties.clear();
/* 104 */     fireValueChangedCallback();
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\SimplePropertyList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */