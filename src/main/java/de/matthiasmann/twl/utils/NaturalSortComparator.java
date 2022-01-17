/*     */ package de.matthiasmann.twl.utils;
/*     */ 
/*     */ import java.util.Comparator;
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
/*     */ public class NaturalSortComparator
/*     */ {
/*  41 */   public static final Comparator<String> stringComparator = new Comparator<String>() {
/*     */       public int compare(String n1, String n2) {
/*  43 */         return NaturalSortComparator.naturalCompare(n1, n2);
/*     */       }
/*     */     };
/*  46 */   public static final Comparator<String> stringPathComparator = new Comparator<String>() {
/*     */       public int compare(String n1, String n2) {
/*  48 */         return NaturalSortComparator.naturalCompareWithPaths(n1, n2);
/*     */       }
/*     */     };
/*     */   
/*     */   private static int findDiff(String s1, int idx1, String s2, int idx2) {
/*  53 */     int len = Math.min(s1.length() - idx1, s2.length() - idx2);
/*  54 */     for (int i = 0; i < len; i++) {
/*  55 */       char c1 = s1.charAt(idx1 + i);
/*  56 */       char c2 = s2.charAt(idx2 + i);
/*  57 */       if (c1 != c2 && 
/*  58 */         Character.toLowerCase(c1) != Character.toLowerCase(c2)) {
/*  59 */         return i;
/*     */       }
/*     */     } 
/*     */     
/*  63 */     return len;
/*     */   }
/*     */   
/*     */   private static int findNumberStart(String s, int i) {
/*  67 */     while (i > 0 && Character.isDigit(s.charAt(i - 1))) {
/*  68 */       i--;
/*     */     }
/*  70 */     return i;
/*     */   }
/*     */   
/*     */   private static int findNumberEnd(String s, int i) {
/*  74 */     int len = s.length();
/*  75 */     while (i < len && Character.isDigit(s.charAt(i))) {
/*  76 */       i++;
/*     */     }
/*  78 */     return i;
/*     */   }
/*     */   
/*     */   public static int naturalCompareWithPaths(String n1, String n2) {
/*  82 */     int diffOffset = findDiff(n1, 0, n2, 0);
/*  83 */     int idx0 = n1.indexOf('/', diffOffset);
/*  84 */     int idx1 = n2.indexOf('/', diffOffset);
/*  85 */     if ((idx0 ^ idx1) < 0) {
/*  86 */       return idx0;
/*     */     }
/*  88 */     return naturalCompare(n1, n2, diffOffset, diffOffset);
/*     */   }
/*     */   
/*     */   public static int naturalCompare(String n1, String n2) {
/*  92 */     return naturalCompare(n1, n2, 0, 0);
/*     */   }
/*     */   private static int naturalCompare(String n1, String n2, int i1, int i2) {
/*     */     char c1, c2;
/*     */     while (true) {
/*  97 */       int diffOffset = findDiff(n1, i1, n2, i2);
/*  98 */       i1 += diffOffset;
/*  99 */       i2 += diffOffset;
/* 100 */       if (i1 == n1.length() || i2 == n2.length()) {
/* 101 */         return n1.length() - n2.length();
/*     */       }
/* 103 */       c1 = n1.charAt(i1);
/* 104 */       c2 = n2.charAt(i2);
/* 105 */       if (Character.isDigit(c1) || Character.isDigit(c2)) {
/* 106 */         int s1 = findNumberStart(n1, i1);
/* 107 */         int s2 = findNumberStart(n2, i2);
/* 108 */         if (Character.isDigit(n1.charAt(s1)) && Character.isDigit(n2.charAt(s2))) {
/* 109 */           i1 = findNumberEnd(n1, s1 + 1);
/* 110 */           i2 = findNumberEnd(n2, s2 + 1);
/*     */           
/* 112 */           try { long value1 = Long.parseLong(n1.substring(s1, i1), 10);
/* 113 */             long value2 = Long.parseLong(n2.substring(s2, i2), 10);
/* 114 */             if (value1 != value2) {
/* 115 */               return Long.signum(value1 - value2);
/*     */             }
/*     */             continue; }
/* 118 */           catch (NumberFormatException ex) { break; }
/*     */         
/*     */         } 
/*     */       }  break;
/* 122 */     }  char cl1 = Character.toLowerCase(c1);
/* 123 */     char cl2 = Character.toLowerCase(c2);
/* 124 */     assert cl1 != cl2;
/* 125 */     return cl1 - cl2;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\tw\\utils\NaturalSortComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */