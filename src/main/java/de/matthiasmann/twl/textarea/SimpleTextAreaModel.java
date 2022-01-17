/*     */ package de.matthiasmann.twl.textarea;
/*     */ 
/*     */ import de.matthiasmann.twl.model.HasCallback;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimpleTextAreaModel
/*     */   extends HasCallback
/*     */   implements TextAreaModel
/*     */ {
/*  52 */   private Style style = new Style();
/*     */ 
/*     */   
/*     */   private TextAreaModel.Element element;
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleTextAreaModel() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public SimpleTextAreaModel(String text) {
/*  64 */     this();
/*  65 */     setText(text);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Style getStyle() {
/*  73 */     return this.style;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStyle(Style style) {
/*  84 */     if (style == null) {
/*  85 */       throw new NullPointerException("style");
/*     */     }
/*  87 */     this.style = style;
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
/*     */   public void setText(String text) {
/*  99 */     setText(text, true);
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
/*     */   public void setText(String text, boolean preformatted) {
/* 113 */     Style textstyle = this.style.with(StyleAttribute.PREFORMATTED, Boolean.valueOf(preformatted));
/* 114 */     this.element = new TextAreaModel.TextElement(textstyle, text);
/* 115 */     doCallback();
/*     */   }
/*     */   
/*     */   public Iterator<TextAreaModel.Element> iterator() {
/* 119 */     return (Iterator)((this.element != null) ? Collections.<T>singletonList((T)this.element) : Collections.<T>emptyList()).iterator();
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\textarea\SimpleTextAreaModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */