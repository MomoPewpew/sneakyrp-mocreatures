/*     */ package de.matthiasmann.twl.utils;
/*     */ 
/*     */ import de.matthiasmann.twl.renderer.AnimationState;
/*     */ import java.util.Collection;
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
/*     */ public class StateSelect
/*     */ {
/*     */   private static boolean useOptimizer = false;
/*     */   private final StateExpression[] expressions;
/*     */   private final AnimationState.StateKey[] programKeys;
/*     */   private final short[] programCodes;
/*  48 */   public static final StateSelect EMPTY = new StateSelect(new StateExpression[0]); static final int CODE_RESULT = 32768;
/*     */   
/*     */   public StateSelect(Collection<StateExpression> expressions) {
/*  51 */     this(expressions.<StateExpression>toArray(new StateExpression[expressions.size()]));
/*     */   }
/*     */   static final int CODE_MASK = 32767;
/*     */   public StateSelect(StateExpression... expressions) {
/*  55 */     this.expressions = expressions;
/*     */     
/*  57 */     StateSelectOptimizer sso = useOptimizer ? StateSelectOptimizer.optimize(expressions) : null;
/*     */ 
/*     */ 
/*     */     
/*  61 */     if (sso != null) {
/*  62 */       this.programKeys = sso.programKeys;
/*  63 */       this.programCodes = sso.programCodes;
/*     */     } else {
/*  65 */       this.programKeys = null;
/*  66 */       this.programCodes = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean isUseOptimizer() {
/*  71 */     return useOptimizer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setUseOptimizer(boolean useOptimizer) {
/*  80 */     StateSelect.useOptimizer = useOptimizer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumExpressions() {
/*  90 */     return this.expressions.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StateExpression getExpression(int idx) {
/* 100 */     return this.expressions[idx];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int evaluate(AnimationState as) {
/* 111 */     if (this.programKeys != null) {
/* 112 */       return evaluateProgram(as);
/*     */     }
/* 114 */     return evaluateExpr(as);
/*     */   }
/*     */   
/*     */   private int evaluateExpr(AnimationState as) {
/* 118 */     int i = 0;
/* 119 */     for (int n = this.expressions.length; i < n && 
/* 120 */       !this.expressions[i].evaluate(as); i++);
/*     */ 
/*     */ 
/*     */     
/* 124 */     return i;
/*     */   }
/*     */   
/*     */   private int evaluateProgram(AnimationState as) {
/* 128 */     int pos = 0;
/*     */     while (true) {
/* 130 */       if (as == null || !as.getAnimationState(this.programKeys[pos >> 1])) {
/* 131 */         pos++;
/*     */       }
/* 133 */       pos = this.programCodes[pos];
/* 134 */       if (pos < 0)
/* 135 */         return pos & 0x7FFF; 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\tw\\utils\StateSelect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */