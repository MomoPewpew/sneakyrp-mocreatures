/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.beans.PropertyChangeSupport;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class MenuElement
/*     */ {
/*     */   private String name;
/*     */   private String theme;
/*     */   private boolean enabled = true;
/*     */   private Object tooltipContent;
/*     */   private PropertyChangeSupport pcs;
/*     */   private Alignment alignment;
/*     */   
/*     */   public MenuElement() {}
/*     */   
/*     */   public MenuElement(String name) {
/*  53 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  57 */     return this.name;
/*     */   }
/*     */   
/*     */   public MenuElement setName(String name) {
/*  61 */     String oldName = this.name;
/*  62 */     this.name = name;
/*  63 */     firePropertyChange("name", oldName, name);
/*  64 */     return this;
/*     */   }
/*     */   
/*     */   public String getTheme() {
/*  68 */     return this.theme;
/*     */   }
/*     */   
/*     */   public MenuElement setTheme(String theme) {
/*  72 */     String oldTheme = this.theme;
/*  73 */     this.theme = theme;
/*  74 */     firePropertyChange("theme", oldTheme, theme);
/*  75 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isEnabled() {
/*  79 */     return this.enabled;
/*     */   }
/*     */   
/*     */   public MenuElement setEnabled(boolean enabled) {
/*  83 */     boolean oldEnabled = this.enabled;
/*  84 */     this.enabled = enabled;
/*  85 */     firePropertyChange("enabled", oldEnabled, enabled);
/*  86 */     return this;
/*     */   }
/*     */   
/*     */   public Object getTooltipContent() {
/*  90 */     return this.tooltipContent;
/*     */   }
/*     */   
/*     */   public MenuElement setTooltipContent(Object tooltip) {
/*  94 */     Object oldTooltip = this.tooltipContent;
/*  95 */     this.tooltipContent = tooltip;
/*  96 */     firePropertyChange("tooltipContent", oldTooltip, tooltip);
/*  97 */     return this;
/*     */   }
/*     */   
/*     */   public Alignment getAlignment() {
/* 101 */     return this.alignment;
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
/*     */   public MenuElement setAlignment(Alignment alignment) {
/* 115 */     Alignment oldAlignment = this.alignment;
/* 116 */     this.alignment = alignment;
/* 117 */     firePropertyChange("alignment", oldAlignment, alignment);
/* 118 */     return this;
/*     */   }
/*     */   
/*     */   protected abstract Widget createMenuWidget(MenuManager paramMenuManager, int paramInt);
/*     */   
/*     */   public void addPropertyChangeListener(PropertyChangeListener listener) {
/* 124 */     if (this.pcs == null) {
/* 125 */       this.pcs = new PropertyChangeSupport(this);
/*     */     }
/* 127 */     this.pcs.addPropertyChangeListener(listener);
/*     */   }
/*     */   
/*     */   public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
/* 131 */     if (this.pcs == null) {
/* 132 */       this.pcs = new PropertyChangeSupport(this);
/*     */     }
/* 134 */     this.pcs.addPropertyChangeListener(propertyName, listener);
/*     */   }
/*     */   
/*     */   public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
/* 138 */     if (this.pcs != null) {
/* 139 */       this.pcs.removePropertyChangeListener(propertyName, listener);
/*     */     }
/*     */   }
/*     */   
/*     */   public void removePropertyChangeListener(PropertyChangeListener listener) {
/* 144 */     if (this.pcs != null) {
/* 145 */       this.pcs.removePropertyChangeListener(listener);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {
/* 150 */     if (this.pcs != null) {
/* 151 */       this.pcs.firePropertyChange(propertyName, oldValue, newValue);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void firePropertyChange(String propertyName, int oldValue, int newValue) {
/* 156 */     if (this.pcs != null) {
/* 157 */       this.pcs.firePropertyChange(propertyName, oldValue, newValue);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
/* 162 */     if (this.pcs != null) {
/* 163 */       this.pcs.firePropertyChange(propertyName, oldValue, newValue);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setWidgetTheme(Widget w, String defaultTheme) {
/* 174 */     if (this.theme != null) {
/* 175 */       w.setTheme(this.theme);
/*     */     } else {
/* 177 */       w.setTheme(defaultTheme);
/*     */     } 
/*     */   }
/*     */   
/*     */   class MenuBtn extends Button implements PropertyChangeListener {
/*     */     public MenuBtn() {
/* 183 */       sync();
/*     */     }
/*     */ 
/*     */     
/*     */     protected void afterAddToGUI(GUI gui) {
/* 188 */       super.afterAddToGUI(gui);
/* 189 */       MenuElement.this.addPropertyChangeListener(this);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void beforeRemoveFromGUI(GUI gui) {
/* 194 */       MenuElement.this.removePropertyChangeListener(this);
/* 195 */       super.beforeRemoveFromGUI(gui);
/*     */     }
/*     */     
/*     */     public void propertyChange(PropertyChangeEvent evt) {
/* 199 */       sync();
/*     */     }
/*     */     
/*     */     protected void sync() {
/* 203 */       setEnabled(MenuElement.this.isEnabled());
/* 204 */       setTooltipContent(MenuElement.this.getTooltipContent());
/* 205 */       setText(MenuElement.this.getName());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\MenuElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */