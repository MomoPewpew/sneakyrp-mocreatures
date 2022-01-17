/*     */ package de.matthiasmann.twl.textarea;
/*     */ 
/*     */ import de.matthiasmann.twl.renderer.FontMapper;
/*     */ import de.matthiasmann.twl.utils.StringList;
/*     */ import de.matthiasmann.twl.utils.TextUtil;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.IdentityHashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StyleSheet
/*     */   implements StyleSheetResolver
/*     */ {
/*  57 */   static final Object NULL = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   private final ArrayList<Selector> rules = new ArrayList<Selector>();
/*  65 */   private final IdentityHashMap<Style, Object> cache = new IdentityHashMap<Style, Object>();
/*     */   private ArrayList<AtRule> atrules;
/*     */   
/*     */   public void parse(URL url) throws IOException {
/*  69 */     InputStream is = url.openStream();
/*     */     try {
/*  71 */       parse(new InputStreamReader(is, "UTF8"));
/*     */     } finally {
/*  73 */       is.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void parse(String style) throws IOException {
/*  78 */     parse(new StringReader(style));
/*     */   }
/*     */   
/*     */   public void parse(Reader r) throws IOException {
/*  82 */     Parser parser = new Parser(r);
/*  83 */     ArrayList<Selector> selectors = new ArrayList<Selector>();
/*     */     int what;
/*  85 */     while ((what = parser.yylex()) != 0) {
/*  86 */       CSSStyle style; int i, n; if (what == 11) {
/*  87 */         parser.expect(1);
/*  88 */         AtRule atrule = new AtRule(parser.yytext());
/*  89 */         parser.expect(7);
/*     */         
/*  91 */         while ((what = parser.yylex()) != 8) {
/*  92 */           if (what != 1) {
/*  93 */             parser.unexpected();
/*     */           }
/*  95 */           String key = parser.yytext();
/*  96 */           parser.expect(9);
/*  97 */           what = parser.yylex();
/*  98 */           if (what != 10 && what != 8) {
/*  99 */             parser.unexpected();
/*     */           }
/* 101 */           String value = TextUtil.trim(parser.sb, 0);
/*     */           try {
/* 103 */             atrule.entries.put(key, value);
/* 104 */           } catch (IllegalArgumentException ex) {}
/*     */           
/* 106 */           if (what == 8) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */         
/* 111 */         if (this.atrules == null) {
/* 112 */           this.atrules = new ArrayList<AtRule>();
/*     */         }
/* 114 */         this.atrules.add(atrule);
/*     */         
/*     */         continue;
/*     */       } 
/* 118 */       Selector selector = null;
/*     */       while (true) {
/* 120 */         String element = null;
/* 121 */         String className = null;
/* 122 */         String pseudoClass = null;
/* 123 */         String id = null;
/* 124 */         parser.sawWhitespace = false;
/* 125 */         switch (what) {
/*     */           default:
/* 127 */             parser.unexpected();
/*     */             break;
/*     */           case 3:
/*     */           case 4:
/*     */           case 9:
/*     */             break;
/*     */           case 1:
/* 134 */             element = parser.yytext();
/*     */           
/*     */           case 2:
/* 137 */             what = parser.yylex();
/*     */             break;
/*     */         } 
/* 140 */         while ((what == 3 || what == 4 || what == 9) && !parser.sawWhitespace) {
/* 141 */           parser.expect(1);
/* 142 */           String text = parser.yytext();
/* 143 */           if (what == 3) {
/* 144 */             className = text;
/* 145 */           } else if (what == 9) {
/* 146 */             pseudoClass = text;
/*     */           } else {
/* 148 */             id = text;
/*     */           } 
/* 150 */           what = parser.yylex();
/*     */         } 
/* 152 */         selector = new Selector(element, className, id, pseudoClass, selector);
/* 153 */         switch (what) {
/*     */           case 5:
/* 155 */             selector.directChild = true;
/* 156 */             what = parser.yylex();
/*     */ 
/*     */ 
/*     */           
/*     */           case 6:
/*     */           case 7:
/*     */             break;
/*     */         } 
/*     */ 
/*     */       
/*     */       } 
/* 167 */       selector.directChild = true;
/* 168 */       selectors.add(selector);
/*     */       
/* 170 */       switch (what) {
/*     */         default:
/* 172 */           parser.unexpected();
/*     */ 
/*     */         
/*     */         case 7:
/* 176 */           style = new CSSStyle();
/*     */           
/* 178 */           while ((what = parser.yylex()) != 8) {
/* 179 */             if (what != 1) {
/* 180 */               parser.unexpected();
/*     */             }
/* 182 */             String key = parser.yytext();
/* 183 */             parser.expect(9);
/* 184 */             what = parser.yylex();
/* 185 */             if (what != 10 && what != 8) {
/* 186 */               parser.unexpected();
/*     */             }
/* 188 */             String value = TextUtil.trim(parser.sb, 0);
/*     */             try {
/* 190 */               style.parseCSSAttribute(key, value);
/* 191 */             } catch (IllegalArgumentException ex) {}
/*     */             
/* 193 */             if (what == 8) {
/*     */               break;
/*     */             }
/*     */           } 
/*     */           
/* 198 */           for (i = 0, n = selectors.size(); i < n; i++) {
/* 199 */             Style selectorStyle = style;
/* 200 */             selector = selectors.get(i);
/* 201 */             if (selector.pseudoClass != null) {
/* 202 */               selectorStyle = transformStyle(style, selector.pseudoClass);
/*     */             }
/* 204 */             this.rules.add(selector);
/* 205 */             int score = 0;
/* 206 */             for (Selector s = selector; s != null; s = s.tail) {
/* 207 */               if (s.directChild) {
/* 208 */                 score++;
/*     */               }
/* 210 */               if (s.element != null) {
/* 211 */                 score += 256;
/*     */               }
/* 213 */               if (s.className != null) {
/* 214 */                 score += 65536;
/*     */               }
/* 216 */               if (s.id != null) {
/* 217 */                 score += 16777216;
/*     */               }
/*     */             } 
/*     */             
/* 221 */             selector.score = score;
/* 222 */             selector.style = selectorStyle;
/*     */           } 
/*     */           
/* 225 */           selectors.clear();
/*     */         case 6:
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumAtRules() {
/* 236 */     return (this.atrules != null) ? this.atrules.size() : 0;
/*     */   }
/*     */   
/*     */   public AtRule getAtRule(int idx) {
/* 240 */     if (this.atrules == null) {
/* 241 */       throw new IndexOutOfBoundsException();
/*     */     }
/* 243 */     return this.atrules.get(idx);
/*     */   }
/*     */   
/*     */   public void registerFonts(FontMapper fontMapper, URL baseUrl) {
/* 247 */     if (this.atrules == null) {
/*     */       return;
/*     */     }
/* 250 */     for (AtRule atrule : this.atrules) {
/* 251 */       if ("font-face".equals(atrule.name)) {
/* 252 */         String family = atrule.get("font-family");
/* 253 */         String src = atrule.get("src");
/*     */         
/* 255 */         if (family != null && src != null) {
/* 256 */           StringList srcs = CSSStyle.parseList(src, 0);
/* 257 */           for (; srcs != null; srcs = srcs.getNext()) {
/* 258 */             String url = CSSStyle.stripURL(srcs.getValue());
/*     */             try {
/* 260 */               fontMapper.registerFont(family, new URL(baseUrl, url));
/* 261 */             } catch (IOException ex) {
/* 262 */               Logger.getLogger(StyleSheet.class.getName()).log(Level.SEVERE, "Could not register font: " + url, ex);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void layoutFinished() {
/* 272 */     this.cache.clear();
/*     */   }
/*     */   
/*     */   public void startLayout() {
/* 276 */     this.cache.clear();
/*     */   }
/*     */   
/*     */   public Style resolve(Style style) {
/* 280 */     Object cacheData = this.cache.get(style);
/* 281 */     if (cacheData == null) {
/* 282 */       return resolveSlow(style);
/*     */     }
/* 284 */     if (cacheData == NULL) {
/* 285 */       return null;
/*     */     }
/* 287 */     return (Style)cacheData;
/*     */   }
/*     */   
/*     */   private Style resolveSlow(Style style) {
/* 291 */     Selector[] candidates = new Selector[this.rules.size()];
/* 292 */     int numCandidates = 0;
/*     */ 
/*     */     
/* 295 */     for (int i = 0, n = this.rules.size(); i < n; i++) {
/* 296 */       Selector selector = this.rules.get(i);
/* 297 */       if (matches(selector, style)) {
/* 298 */         candidates[numCandidates++] = selector;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 303 */     if (numCandidates > 1) {
/* 304 */       Arrays.sort((Object[])candidates, 0, numCandidates);
/*     */     }
/*     */     
/* 307 */     Style result = null;
/* 308 */     boolean copy = true;
/*     */ 
/*     */     
/* 311 */     for (int j = 0, k = numCandidates; j < k; j++) {
/* 312 */       Style ruleStyle = (candidates[j]).style;
/* 313 */       if (result == null) {
/* 314 */         result = ruleStyle;
/*     */       } else {
/* 316 */         if (copy) {
/* 317 */           result = new Style(result);
/* 318 */           copy = false;
/*     */         } 
/* 320 */         result.putAll(ruleStyle);
/*     */       } 
/*     */     } 
/*     */     
/* 324 */     putIntoCache(style, result);
/* 325 */     return result;
/*     */   }
/*     */   
/*     */   private void putIntoCache(Style key, Style style) {
/* 329 */     this.cache.put(key, (style == null) ? NULL : style);
/*     */   }
/*     */   
/*     */   private boolean matches(Selector selector, Style style) {
/*     */     while (true) {
/* 334 */       StyleSheetKey styleSheetKey = style.getStyleSheetKey();
/* 335 */       if (styleSheetKey != null) {
/* 336 */         if (selector.matches(styleSheetKey)) {
/* 337 */           selector = selector.tail;
/* 338 */           if (selector == null) {
/* 339 */             return true;
/*     */           }
/* 341 */         } else if (selector.directChild) {
/* 342 */           return false;
/*     */         } 
/*     */       }
/* 345 */       style = style.getParent();
/* 346 */       if (style == null)
/* 347 */         return false; 
/*     */     } 
/*     */   }
/*     */   private Style transformStyle(CSSStyle style, String pseudoClass) {
/* 351 */     Style result = new Style(style.getParent(), style.getStyleSheetKey());
/* 352 */     if ("hover".equals(pseudoClass)) {
/* 353 */       result.put(StyleAttribute.COLOR_HOVER, style.getRaw(StyleAttribute.COLOR));
/* 354 */       result.put(StyleAttribute.BACKGROUND_COLOR_HOVER, style.getRaw(StyleAttribute.BACKGROUND_COLOR));
/* 355 */       result.put(StyleAttribute.TEXT_DECORATION_HOVER, style.getRaw(StyleAttribute.TEXT_DECORATION));
/*     */     } 
/* 357 */     return result;
/*     */   }
/*     */   
/*     */   static class Selector extends StyleSheetKey implements Comparable<Selector> {
/*     */     final String pseudoClass;
/*     */     final Selector tail;
/*     */     boolean directChild;
/*     */     Style style;
/*     */     int score;
/*     */     
/*     */     Selector(String element, String className, String id, String pseudoClass, Selector tail) {
/* 368 */       super(element, className, id);
/* 369 */       this.pseudoClass = pseudoClass;
/* 370 */       this.tail = tail;
/*     */     }
/*     */     
/*     */     public int compareTo(Selector other) {
/* 374 */       return this.score - other.score;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class AtRule implements Iterable<Map.Entry<String, String>> {
/*     */     final String name;
/*     */     final HashMap<String, String> entries;
/*     */     
/*     */     public AtRule(String name) {
/* 383 */       this.name = name;
/* 384 */       this.entries = new HashMap<String, String>();
/*     */     }
/*     */     
/*     */     public String getName() {
/* 388 */       return this.name;
/*     */     }
/*     */     
/*     */     public String get(String key) {
/* 392 */       return this.entries.get(key);
/*     */     }
/*     */     
/*     */     public Iterator<Map.Entry<String, String>> iterator() {
/* 396 */       return Collections.<Map.Entry<String, String>>unmodifiableSet(this.entries.entrySet()).iterator();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\textarea\StyleSheet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */