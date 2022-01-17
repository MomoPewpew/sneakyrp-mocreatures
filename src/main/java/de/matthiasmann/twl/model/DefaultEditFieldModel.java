/*     */ package de.matthiasmann.twl.model;
/*     */ 
/*     */ import de.matthiasmann.twl.utils.CallbackSupport;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultEditFieldModel
/*     */   implements EditFieldModel
/*     */ {
/*  45 */   private final StringBuilder sb = new StringBuilder();
/*     */   private ObservableCharSequence.Callback[] callbacks;
/*     */   
/*     */   public int length() {
/*  49 */     return this.sb.length();
/*     */   }
/*     */   
/*     */   public char charAt(int index) {
/*  53 */     return this.sb.charAt(index);
/*     */   }
/*     */   
/*     */   public CharSequence subSequence(int start, int end) {
/*  57 */     return this.sb.subSequence(start, end);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  62 */     return this.sb.toString();
/*     */   }
/*     */   
/*     */   public void addCallback(ObservableCharSequence.Callback callback) {
/*  66 */     this.callbacks = (ObservableCharSequence.Callback[])CallbackSupport.addCallbackToList((Object[])this.callbacks, callback, ObservableCharSequence.Callback.class);
/*     */   }
/*     */   
/*     */   public void removeCallback(ObservableCharSequence.Callback callback) {
/*  70 */     this.callbacks = (ObservableCharSequence.Callback[])CallbackSupport.removeCallbackFromList((Object[])this.callbacks, callback);
/*     */   }
/*     */   
/*     */   public int replace(int start, int count, String replacement) {
/*  74 */     checkRange(start, count);
/*  75 */     int replacementLength = replacement.length();
/*  76 */     if (count > 0 || replacementLength > 0) {
/*  77 */       this.sb.replace(start, start + count, replacement);
/*  78 */       fireCallback(start, count, replacementLength);
/*     */     } 
/*  80 */     return replacementLength;
/*     */   }
/*     */   
/*     */   public boolean replace(int start, int count, char replacement) {
/*  84 */     checkRange(start, count);
/*  85 */     if (count == 0) {
/*  86 */       this.sb.insert(start, replacement);
/*     */     } else {
/*  88 */       this.sb.delete(start, start + count - 1);
/*  89 */       this.sb.setCharAt(start, replacement);
/*     */     } 
/*  91 */     fireCallback(start, count, 1);
/*  92 */     return true;
/*     */   }
/*     */   
/*     */   public String substring(int start, int end) {
/*  96 */     return this.sb.substring(start, end);
/*     */   }
/*     */   
/*     */   private void checkRange(int start, int count) {
/* 100 */     int len = this.sb.length();
/* 101 */     if (start < 0 || start > len) {
/* 102 */       throw new StringIndexOutOfBoundsException(start);
/*     */     }
/* 104 */     if (count < 0 || count > len - start) {
/* 105 */       throw new StringIndexOutOfBoundsException();
/*     */     }
/*     */   }
/*     */   
/*     */   private void fireCallback(int start, int oldCount, int newCount) {
/* 110 */     ObservableCharSequence.Callback[] cbs = this.callbacks;
/* 111 */     if (cbs != null)
/* 112 */       for (ObservableCharSequence.Callback cb : cbs)
/* 113 */         cb.charactersChanged(start, oldCount, newCount);  
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\DefaultEditFieldModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */