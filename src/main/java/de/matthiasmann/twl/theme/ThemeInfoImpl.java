/*     */ package de.matthiasmann.twl.theme;
/*     */ 
/*     */ import de.matthiasmann.twl.DebugHook;
/*     */ import de.matthiasmann.twl.ThemeInfo;
/*     */ import de.matthiasmann.twl.utils.CascadedHashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ThemeInfoImpl
/*     */   extends ParameterMapImpl
/*     */   implements ThemeInfo
/*     */ {
/*     */   private final String name;
/*     */   private final CascadedHashMap<String, ThemeInfoImpl> children;
/*     */   boolean maybeUsedFromWildcard;
/*     */   String wildcardImportPath;
/*     */   
/*     */   public ThemeInfoImpl(ThemeManager manager, String name, ThemeInfoImpl parent) {
/*  49 */     super(manager, parent);
/*  50 */     this.name = name;
/*  51 */     this.children = new CascadedHashMap();
/*     */   }
/*     */   
/*     */   void copy(ThemeInfoImpl src) {
/*  55 */     copy(src);
/*  56 */     this.children.collapseAndSetFallback(src.children);
/*  57 */     this.wildcardImportPath = src.wildcardImportPath;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  61 */     return this.name;
/*     */   }
/*     */   
/*     */   public ThemeInfo getChildTheme(String theme) {
/*  65 */     return getChildThemeImpl(theme, true);
/*     */   }
/*     */   
/*     */   ThemeInfo getChildThemeImpl(String theme, boolean useFallback) {
/*  69 */     ThemeInfo info = (ThemeInfo)this.children.get(theme);
/*  70 */     if (info == null) {
/*  71 */       if (this.wildcardImportPath != null) {
/*  72 */         info = this.manager.resolveWildcard(this.wildcardImportPath, theme, useFallback);
/*     */       }
/*  74 */       if (info == null && useFallback) {
/*  75 */         DebugHook.getDebugHook().missingChildTheme(this, theme);
/*     */       }
/*     */     } 
/*  78 */     return info;
/*     */   }
/*     */   
/*     */   final ThemeInfoImpl getTheme(String name) {
/*  82 */     return (ThemeInfoImpl)this.children.get(name);
/*     */   }
/*     */   
/*     */   void putTheme(String name, ThemeInfoImpl child) {
/*  86 */     this.children.put(name, child);
/*     */   }
/*     */   
/*     */   public String getThemePath() {
/*  90 */     return getThemePath(0).toString();
/*     */   }
/*     */   
/*     */   private StringBuilder getThemePath(int length) {
/*     */     StringBuilder sb;
/*  95 */     length += getName().length();
/*  96 */     if (this.parent != null) {
/*  97 */       sb = this.parent.getThemePath(length + 1);
/*  98 */       sb.append('.');
/*     */     } else {
/* 100 */       sb = new StringBuilder(length);
/*     */     } 
/* 102 */     sb.append(getName());
/* 103 */     return sb;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\theme\ThemeInfoImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */