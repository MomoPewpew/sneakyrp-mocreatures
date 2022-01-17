/*     */ package de.matthiasmann.twl.utils;
/*     */ 
/*     */ import de.matthiasmann.twl.AnimationState;
/*     */ import de.matthiasmann.twl.renderer.AnimationState;
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
/*     */ 
/*     */ final class StateSelectOptimizer
/*     */ {
/*     */   private final AnimationState.StateKey[] keys;
/*     */   private final byte[] matrix;
/*     */   final AnimationState.StateKey[] programKeys;
/*     */   final short[] programCodes;
/*     */   int programIdx;
/*     */   
/*     */   static StateSelectOptimizer optimize(StateExpression... expressions) {
/*  50 */     int numExpr = expressions.length;
/*  51 */     if (numExpr == 0 || numExpr >= 255) {
/*  52 */       return null;
/*     */     }
/*     */     
/*  55 */     BitSet bs = new BitSet();
/*  56 */     for (StateExpression e : expressions) {
/*  57 */       e.getUsedStateKeys(bs);
/*     */     }
/*     */     
/*  60 */     int numKeys = bs.cardinality();
/*  61 */     if (numKeys == 0 || numKeys > 16) {
/*  62 */       return null;
/*     */     }
/*     */     
/*  65 */     AnimationState.StateKey[] keys = new AnimationState.StateKey[numKeys];
/*  66 */     for (int keyIdx = 0, keyID = -1; (keyID = bs.nextSetBit(keyID + 1)) >= 0; keyIdx++) {
/*  67 */       keys[keyIdx] = AnimationState.StateKey.get(keyID);
/*     */     }
/*     */     
/*  70 */     int matrixSize = 1 << numKeys;
/*  71 */     byte[] matrix = new byte[matrixSize];
/*  72 */     AnimationState as = new AnimationState(null, keys[numKeys - 1].getID() + 1);
/*     */     
/*  74 */     for (int matrixIdx = 0; matrixIdx < matrixSize; matrixIdx++) {
/*  75 */       for (int i = 0; i < numKeys; i++) {
/*  76 */         as.setAnimationState(keys[i], ((matrixIdx & 1 << i) != 0));
/*     */       }
/*  78 */       int exprIdx = 0;
/*  79 */       for (; exprIdx < numExpr && 
/*  80 */         !expressions[exprIdx].evaluate((AnimationState)as); exprIdx++);
/*     */ 
/*     */ 
/*     */       
/*  84 */       matrix[matrixIdx] = (byte)exprIdx;
/*     */     } 
/*     */     
/*  87 */     StateSelectOptimizer sso = new StateSelectOptimizer(keys, matrix);
/*  88 */     sso.compute(0, 0);
/*  89 */     return sso;
/*     */   }
/*     */   
/*     */   private StateSelectOptimizer(AnimationState.StateKey[] keys, byte[] matrix) {
/*  93 */     this.keys = keys;
/*  94 */     this.matrix = matrix;
/*     */     
/*  96 */     this.programKeys = new AnimationState.StateKey[matrix.length - 1];
/*  97 */     this.programCodes = new short[matrix.length * 2 - 2];
/*     */   }
/*     */   
/*     */   private int compute(int bits, int mask) {
/* 101 */     if (mask == this.matrix.length - 1) {
/* 102 */       return this.matrix[bits] & 0xFF | 0x8000;
/*     */     }
/*     */     
/* 105 */     int best = -1;
/* 106 */     int bestScore = -1;
/* 107 */     int bestSet0 = 0;
/* 108 */     int bestSet1 = 0;
/*     */     
/* 110 */     int matrixIdxInc = (bits == 0) ? 1 : Integer.lowestOneBit(bits);
/*     */     
/* 112 */     for (int keyIdx = 0; keyIdx < this.keys.length; keyIdx++) {
/* 113 */       int test = 1 << keyIdx;
/*     */       
/* 115 */       if ((mask & test) == 0) {
/* 116 */         int set0 = 0;
/* 117 */         int set1 = 0;
/*     */         int matrixIdx;
/* 119 */         for (matrixIdx = bits; matrixIdx < this.matrix.length; matrixIdx += matrixIdxInc) {
/* 120 */           if ((matrixIdx & mask) == bits) {
/* 121 */             int resultMask = 1 << (this.matrix[matrixIdx] & 0xFF);
/* 122 */             if ((matrixIdx & test) == 0) {
/* 123 */               set0 |= resultMask;
/*     */             } else {
/* 125 */               set1 |= resultMask;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 130 */         int score = Integer.bitCount(set0 ^ set1);
/* 131 */         if (score > bestScore) {
/* 132 */           bestScore = score;
/* 133 */           bestSet0 = set0;
/* 134 */           bestSet1 = set1;
/* 135 */           best = keyIdx;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 140 */     if (best < 0) {
/* 141 */       throw new AssertionError();
/*     */     }
/*     */     
/* 144 */     if (bestSet0 == bestSet1 && (bestSet0 & bestSet0 - 1) == 0) {
/* 145 */       int result = Integer.numberOfTrailingZeros(bestSet0);
/* 146 */       return result | 0x8000;
/*     */     } 
/*     */     
/* 149 */     int bestMask = 1 << best;
/* 150 */     mask |= bestMask;
/*     */     
/* 152 */     int idx = this.programIdx;
/* 153 */     this.programIdx += 2;
/* 154 */     this.programKeys[idx >> 1] = this.keys[best];
/* 155 */     this.programCodes[idx + 0] = (short)compute(bits | bestMask, mask);
/* 156 */     this.programCodes[idx + 1] = (short)compute(bits, mask);
/*     */     
/* 158 */     return idx;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\tw\\utils\StateSelectOptimizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */