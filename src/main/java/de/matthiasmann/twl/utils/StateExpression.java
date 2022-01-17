/*     */ package de.matthiasmann.twl.utils;
/*     */ 
/*     */ import de.matthiasmann.twl.renderer.AnimationState;
/*     */ import java.text.ParseException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.BitSet;
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
/*     */ public abstract class StateExpression
/*     */ {
/*     */   boolean negate;
/*     */   
/*     */   public abstract boolean evaluate(AnimationState paramAnimationState);
/*     */   
/*     */   public static StateExpression parse(String exp, boolean negate) throws ParseException {
/*  48 */     StringIterator si = new StringIterator(exp);
/*  49 */     StateExpression expr = parse(si);
/*  50 */     if (si.hasMore()) {
/*  51 */       si.unexpected();
/*     */     }
/*  53 */     expr.negate ^= negate;
/*  54 */     return expr;
/*     */   }
/*     */   
/*     */   private static StateExpression parse(StringIterator si) throws ParseException {
/*  58 */     ArrayList<StateExpression> children = new ArrayList<StateExpression>();
/*  59 */     char kind = ' ';
/*     */     
/*     */     while (true) {
/*  62 */       if (!si.skipSpaces()) {
/*  63 */         si.unexpected();
/*     */       }
/*  65 */       char ch = si.peek();
/*  66 */       boolean negate = (ch == '!');
/*  67 */       if (negate) {
/*  68 */         si.pos++;
/*  69 */         if (!si.skipSpaces()) {
/*  70 */           si.unexpected();
/*     */         }
/*  72 */         ch = si.peek();
/*     */       } 
/*     */       
/*  75 */       StateExpression child = null;
/*  76 */       if (Character.isJavaIdentifierStart(ch))
/*  77 */       { child = new Check(AnimationState.StateKey.get(si.getIdent())); }
/*  78 */       else if (ch == '(')
/*  79 */       { si.pos++;
/*  80 */         child = parse(si);
/*  81 */         si.expect(')'); }
/*  82 */       else { if (ch == ')') {
/*     */           break;
/*     */         }
/*  85 */         si.unexpected(); }
/*     */ 
/*     */       
/*  88 */       child.negate = negate;
/*  89 */       children.add(child);
/*     */       
/*  91 */       if (!si.skipSpaces()) {
/*     */         break;
/*     */       }
/*     */       
/*  95 */       ch = si.peek();
/*  96 */       if ("|+^".indexOf(ch) < 0) {
/*     */         break;
/*     */       }
/*     */       
/* 100 */       if (children.size() == 1) {
/* 101 */         kind = ch;
/* 102 */       } else if (kind != ch) {
/* 103 */         si.expect(kind);
/*     */       } 
/* 105 */       si.pos++;
/*     */     } 
/*     */     
/* 108 */     if (children.isEmpty()) {
/* 109 */       si.unexpected();
/*     */     }
/*     */     
/* 112 */     assert kind != ' ' || children.size() == 1;
/*     */     
/* 114 */     if (children.size() == 1) {
/* 115 */       return children.get(0);
/*     */     }
/*     */     
/* 118 */     return new Logic(kind, children.<StateExpression>toArray(new StateExpression[children.size()]));
/*     */   }
/*     */   
/*     */   abstract void getUsedStateKeys(BitSet paramBitSet);
/*     */   
/*     */   private static class StringIterator { final String str;
/*     */     
/*     */     StringIterator(String str) {
/* 126 */       this.str = str;
/*     */     }
/*     */     int pos;
/*     */     boolean hasMore() {
/* 130 */       return (this.pos < this.str.length());
/*     */     }
/*     */     
/*     */     char peek() {
/* 134 */       return this.str.charAt(this.pos);
/*     */     }
/*     */     
/*     */     void expect(char what) throws ParseException {
/* 138 */       if (!hasMore() || peek() != what) {
/* 139 */         throw new ParseException("Expected '" + what + "' got " + describePosition(), this.pos);
/*     */       }
/* 141 */       this.pos++;
/*     */     }
/*     */     
/*     */     void unexpected() throws ParseException {
/* 145 */       throw new ParseException("Unexpected " + describePosition(), this.pos);
/*     */     }
/*     */     
/*     */     String describePosition() {
/* 149 */       if (this.pos >= this.str.length()) {
/* 150 */         return "end of expression";
/*     */       }
/* 152 */       return "'" + peek() + "' at " + (this.pos + 1);
/*     */     }
/*     */     
/*     */     boolean skipSpaces() {
/* 156 */       while (hasMore() && Character.isWhitespace(peek())) {
/* 157 */         this.pos++;
/*     */       }
/* 159 */       return hasMore();
/*     */     }
/*     */     
/*     */     String getIdent() {
/* 163 */       int start = this.pos;
/* 164 */       while (hasMore() && Character.isJavaIdentifierPart(peek())) {
/* 165 */         this.pos++;
/*     */       }
/*     */       
/* 168 */       return this.str.substring(start, this.pos).intern();
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Logic
/*     */     extends StateExpression
/*     */   {
/*     */     final StateExpression[] children;
/*     */     
/*     */     final boolean and;
/*     */     
/*     */     final boolean xor;
/*     */ 
/*     */     
/*     */     public Logic(char kind, StateExpression... children) {
/* 185 */       if (kind != '|' && kind != '+' && kind != '^') {
/* 186 */         throw new IllegalArgumentException("kind");
/*     */       }
/* 188 */       this.children = children;
/* 189 */       this.and = (kind == '+');
/* 190 */       this.xor = (kind == '^');
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean evaluate(AnimationState as) {
/* 195 */       int i, j = this.and ^ this.negate;
/* 196 */       for (StateExpression e : this.children) {
/* 197 */         int value = e.evaluate(as);
/* 198 */         if (this.xor) {
/* 199 */           i = j ^ value;
/* 200 */         } else if (this.and != value) {
/* 201 */           return i ^ 0x1;
/*     */         } 
/*     */       } 
/* 204 */       return i;
/*     */     }
/*     */ 
/*     */     
/*     */     void getUsedStateKeys(BitSet bs) {
/* 209 */       for (StateExpression e : this.children)
/* 210 */         e.getUsedStateKeys(bs); 
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Check
/*     */     extends StateExpression {
/*     */     final AnimationState.StateKey state;
/*     */     
/*     */     public Check(AnimationState.StateKey state) {
/* 219 */       this.state = state;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean evaluate(AnimationState as) {
/* 224 */       return this.negate ^ ((as != null && as.getAnimationState(this.state)));
/*     */     }
/*     */ 
/*     */     
/*     */     void getUsedStateKeys(BitSet bs) {
/* 229 */       bs.set(this.state.getID());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\tw\\utils\StateExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */