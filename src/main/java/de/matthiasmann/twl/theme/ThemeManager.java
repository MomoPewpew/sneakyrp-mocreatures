/*     */ package de.matthiasmann.twl.theme;
/*     */ 
/*     */ import de.matthiasmann.twl.Alignment;
/*     */ import de.matthiasmann.twl.Border;
/*     */ import de.matthiasmann.twl.Color;
/*     */ import de.matthiasmann.twl.DebugHook;
/*     */ import de.matthiasmann.twl.DialogLayout;
/*     */ import de.matthiasmann.twl.Dimension;
/*     */ import de.matthiasmann.twl.InputMap;
/*     */ import de.matthiasmann.twl.KeyStroke;
/*     */ import de.matthiasmann.twl.ParameterMap;
/*     */ import de.matthiasmann.twl.PositionAnimatedPanel;
/*     */ import de.matthiasmann.twl.ThemeInfo;
/*     */ import de.matthiasmann.twl.renderer.CacheContext;
/*     */ import de.matthiasmann.twl.renderer.Font;
/*     */ import de.matthiasmann.twl.renderer.FontMapper;
/*     */ import de.matthiasmann.twl.renderer.FontParameter;
/*     */ import de.matthiasmann.twl.renderer.Image;
/*     */ import de.matthiasmann.twl.renderer.Renderer;
/*     */ import de.matthiasmann.twl.utils.AbstractMathInterpreter;
/*     */ import de.matthiasmann.twl.utils.StateExpression;
/*     */ import de.matthiasmann.twl.utils.StateSelect;
/*     */ import de.matthiasmann.twl.utils.StringList;
/*     */ import de.matthiasmann.twl.utils.TextUtil;
/*     */ import de.matthiasmann.twl.utils.XMLParser;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.text.ParseException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Map;
/*     */ import org.lwjgl.opengl.Util;
/*     */ import org.xmlpull.v1.XmlPullParserException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ThemeManager
/*     */ {
/*  75 */   private static final HashMap<String, Class<? extends Enum<?>>> enums = new HashMap<String, Class<? extends Enum<?>>>();
/*     */ 
/*     */   
/*     */   static {
/*  79 */     registerEnumType("alignment", Alignment.class);
/*  80 */     registerEnumType("direction", PositionAnimatedPanel.Direction.class);
/*     */   }
/*     */   
/*  83 */   static final Object NULL = new Object();
/*     */   
/*     */   final ParameterMapImpl constants;
/*     */   
/*     */   private final Renderer renderer;
/*     */   private final CacheContext cacheContext;
/*     */   private final ImageManager imageManager;
/*     */   private final HashMap<String, Font> fonts;
/*     */   private final HashMap<String, ThemeInfoImpl> themes;
/*     */   private final HashMap<String, InputMap> inputMaps;
/*     */   private final MathInterpreter mathInterpreter;
/*     */   private Font defaultFont;
/*     */   private Font firstFont;
/*     */   final ParameterMapImpl emptyMap;
/*     */   final ParameterListImpl emptyList;
/*     */   
/*     */   private ThemeManager(Renderer renderer, CacheContext cacheContext) throws XmlPullParserException, IOException {
/* 100 */     this.constants = new ParameterMapImpl(this, null);
/* 101 */     this.renderer = renderer;
/* 102 */     this.cacheContext = cacheContext;
/* 103 */     this.imageManager = new ImageManager(this.constants, renderer);
/* 104 */     this.fonts = new HashMap<String, Font>();
/* 105 */     this.themes = new HashMap<String, ThemeInfoImpl>();
/* 106 */     this.inputMaps = new HashMap<String, InputMap>();
/* 107 */     this.emptyMap = new ParameterMapImpl(this, null);
/* 108 */     this.emptyList = new ParameterListImpl(this, null);
/* 109 */     this.mathInterpreter = new MathInterpreter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CacheContext getCacheContext() {
/* 118 */     return this.cacheContext;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void destroy() {
/* 127 */     for (Font font : this.fonts.values()) {
/* 128 */       font.destroy();
/*     */     }
/* 130 */     this.cacheContext.destroy();
/*     */   }
/*     */   
/*     */   public Font getDefaultFont() {
/* 134 */     return this.defaultFont;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public static ThemeManager createThemeManager(URL url, Renderer renderer) throws IOException {
/* 151 */     if (url == null) {
/* 152 */       throw new IllegalArgumentException("url is null");
/*     */     }
/* 154 */     if (renderer == null) {
/* 155 */       throw new IllegalArgumentException("renderer is null");
/*     */     }
/* 157 */     return createThemeManager(url, renderer, renderer.createNewCacheContext());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ThemeManager createThemeManager(URL url, Renderer renderer, CacheContext cacheContext) throws IOException {
/* 179 */     if (url == null) {
/* 180 */       throw new IllegalArgumentException("url is null");
/*     */     }
/* 182 */     if (renderer == null) {
/* 183 */       throw new IllegalArgumentException("renderer is null");
/*     */     }
/* 185 */     if (cacheContext == null) {
/* 186 */       throw new IllegalArgumentException("cacheContext is null");
/*     */     }
/*     */     try {
/* 189 */       renderer.setActiveCacheContext(cacheContext);
/* 190 */       ThemeManager tm = new ThemeManager(renderer, cacheContext);
/* 191 */       tm.insertDefaultConstants();
/* 192 */       tm.parseThemeFile(url);
/* 193 */       if (tm.defaultFont == null) {
/* 194 */         tm.defaultFont = tm.firstFont;
/*     */       }
/* 196 */       return tm;
/* 197 */     } catch (XmlPullParserException ex) {
/* 198 */       throw (IOException)(new IOException()).initCause(ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static <E extends Enum<E>> void registerEnumType(String name, Class<E> enumClazz) {
/* 203 */     if (!enumClazz.isEnum()) {
/* 204 */       throw new IllegalArgumentException("not an enum class");
/*     */     }
/* 206 */     Class<?> curClazz = enums.get(name);
/* 207 */     if (curClazz != null && curClazz != enumClazz) {
/* 208 */       throw new IllegalArgumentException("Enum type name \"" + name + "\" is already in use by " + curClazz);
/*     */     }
/*     */     
/* 211 */     enums.put(name, enumClazz);
/*     */   }
/*     */   
/*     */   public ThemeInfo findThemeInfo(String themePath) {
/* 215 */     return findThemeInfo(themePath, true, true);
/*     */   }
/*     */   
/*     */   private ThemeInfo findThemeInfo(String themePath, boolean warn, boolean useFallback) {
/* 219 */     int start = TextUtil.indexOf(themePath, '.', 0);
/* 220 */     ThemeInfo info = this.themes.get(themePath.substring(0, start));
/* 221 */     if (info == null) {
/* 222 */       info = this.themes.get("*");
/* 223 */       if (info != null) {
/* 224 */         if (!useFallback) {
/* 225 */           return null;
/*     */         }
/* 227 */         DebugHook.getDebugHook().usingFallbackTheme(themePath);
/*     */       } 
/*     */     } 
/* 230 */     while (info != null && ++start < themePath.length()) {
/* 231 */       int next = TextUtil.indexOf(themePath, '.', start);
/* 232 */       info = info.getChildTheme(themePath.substring(start, next));
/* 233 */       start = next;
/*     */     } 
/* 235 */     if (info == null && warn) {
/* 236 */       DebugHook.getDebugHook().missingTheme(themePath);
/*     */     }
/* 238 */     return info;
/*     */   }
/*     */   
/*     */   public Image getImageNoWarning(String name) {
/* 242 */     return this.imageManager.getImage(name);
/*     */   }
/*     */   
/*     */   public Image getImage(String name) {
/* 246 */     Image img = this.imageManager.getImage(name);
/* 247 */     if (img == null) {
/* 248 */       DebugHook.getDebugHook().missingImage(name);
/*     */     }
/* 250 */     return img;
/*     */   }
/*     */   
/*     */   public Object getCursor(String name) {
/* 254 */     return this.imageManager.getCursor(name);
/*     */   }
/*     */   
/*     */   public ParameterMap getConstants() {
/* 258 */     return this.constants;
/*     */   }
/*     */   
/*     */   private void insertDefaultConstants() {
/* 262 */     this.constants.put("SINGLE_COLUMN", Integer.valueOf(-1));
/* 263 */     this.constants.put("MAX", Short.valueOf('翿'));
/*     */   }
/*     */   
/*     */   private void parseThemeFile(URL url) throws IOException {
/*     */     try {
/* 268 */       XMLParser xmlp = new XMLParser(url);
/*     */       try {
/* 270 */         xmlp.setLoggerName(ThemeManager.class.getName());
/* 271 */         xmlp.require(0, null, null);
/* 272 */         xmlp.nextTag();
/* 273 */         parseThemeFile(xmlp, url);
/*     */       } finally {
/* 275 */         xmlp.close();
/*     */       } 
/* 277 */     } catch (XmlPullParserException ex) {
/* 278 */       throw new ThemeException(ex.getMessage(), url, ex.getLineNumber(), ex.getColumnNumber(), ex);
/* 279 */     } catch (ThemeException ex) {
/* 280 */       throw ex;
/* 281 */     } catch (Exception ex) {
/* 282 */       throw (IOException)(new IOException("while parsing Theme XML: " + url)).initCause(ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void parseThemeFile(XMLParser xmlp, URL baseUrl) throws XmlPullParserException, IOException {
/* 287 */     xmlp.require(2, null, "themes");
/* 288 */     xmlp.nextTag();
/*     */     
/* 290 */     while (!xmlp.isEndTag()) {
/* 291 */       xmlp.require(2, null, null);
/* 292 */       String tagName = xmlp.getName();
/* 293 */       if ("images".equals(tagName) || "textures".equals(tagName)) {
/* 294 */         this.imageManager.parseImages(xmlp, baseUrl);
/* 295 */       } else if ("include".equals(tagName)) {
/* 296 */         String fontFileName = xmlp.getAttributeNotNull("filename");
/*     */         try {
/* 298 */           parseThemeFile(new URL(baseUrl, fontFileName));
/* 299 */         } catch (ThemeException ex) {
/* 300 */           ex.addIncludedBy(baseUrl, xmlp.getLineNumber(), xmlp.getColumnNumber());
/* 301 */           throw ex;
/*     */         } 
/* 303 */         xmlp.nextTag();
/*     */       } else {
/* 305 */         String name = xmlp.getAttributeNotNull("name");
/* 306 */         if ("theme".equals(tagName)) {
/* 307 */           if (this.themes.containsKey(name)) {
/* 308 */             throw xmlp.error("theme \"" + name + "\" already defined");
/*     */           }
/* 310 */           this.themes.put(name, parseTheme(xmlp, name, null, baseUrl));
/* 311 */         } else if ("inputMapDef".equals(tagName)) {
/* 312 */           if (this.inputMaps.containsKey(name)) {
/* 313 */             throw xmlp.error("inputMap \"" + name + "\" already defined");
/*     */           }
/* 315 */           this.inputMaps.put(name, parseInputMap(xmlp, name, null));
/* 316 */         } else if ("fontDef".equals(tagName)) {
/* 317 */           if (this.fonts.containsKey(name)) {
/* 318 */             throw xmlp.error("font \"" + name + "\" already defined");
/*     */           }
/* 320 */           boolean makeDefault = xmlp.parseBoolFromAttribute("default", false);
/* 321 */           Font font = parseFont(xmlp, baseUrl);
/* 322 */           this.fonts.put(name, font);
/* 323 */           if (this.firstFont == null) {
/* 324 */             this.firstFont = font;
/*     */           }
/* 326 */           if (makeDefault) {
/* 327 */             if (this.defaultFont != null) {
/* 328 */               throw xmlp.error("default font already set");
/*     */             }
/* 330 */             this.defaultFont = font;
/*     */           } 
/* 332 */         } else if ("constantDef".equals(tagName)) {
/* 333 */           parseParam(xmlp, baseUrl, "constantDef", null, this.constants);
/*     */         } else {
/* 335 */           throw xmlp.unexpected();
/*     */         } 
/*     */       } 
/* 338 */       xmlp.require(3, null, tagName);
/* 339 */       xmlp.nextTag();
/*     */     } 
/* 341 */     xmlp.require(3, null, "themes");
/*     */   }
/*     */   
/*     */   private InputMap getInputMap(XMLParser xmlp, String name) throws XmlPullParserException {
/* 345 */     InputMap im = this.inputMaps.get(name);
/* 346 */     if (im == null) {
/* 347 */       throw xmlp.error("Undefined input map: " + name);
/*     */     }
/* 349 */     return im;
/*     */   }
/*     */   
/*     */   private InputMap parseInputMap(XMLParser xmlp, String name, ThemeInfoImpl parent) throws XmlPullParserException, IOException {
/* 353 */     InputMap base = InputMap.empty();
/* 354 */     if (xmlp.parseBoolFromAttribute("merge", false)) {
/* 355 */       if (parent == null) {
/* 356 */         throw xmlp.error("Can't merge on top level");
/*     */       }
/* 358 */       Object o = parent.getParam(name);
/* 359 */       if (o instanceof InputMap) {
/* 360 */         base = (InputMap)o;
/* 361 */       } else if (o != null) {
/* 362 */         throw xmlp.error("Can only merge with inputMap - found a " + o.getClass().getSimpleName());
/*     */       } 
/*     */     } 
/* 365 */     String baseName = xmlp.getAttributeValue(null, "ref");
/* 366 */     if (baseName != null) {
/* 367 */       base = base.addKeyStrokes(getInputMap(xmlp, baseName));
/*     */     }
/*     */     
/* 370 */     xmlp.nextTag();
/*     */     
/* 372 */     LinkedHashSet<KeyStroke> keyStrokes = InputMap.parseBody(xmlp);
/* 373 */     InputMap im = base.addKeyStrokes(keyStrokes);
/* 374 */     return im;
/*     */   }
/*     */   
/*     */   private Font parseFont(XMLParser xmlp, URL baseUrl) throws XmlPullParserException, IOException {
/* 378 */     URL url = baseUrl;
/* 379 */     String fileName = xmlp.getAttributeValue(null, "filename");
/* 380 */     if (fileName != null) {
/* 381 */       url = new URL(url, fileName);
/*     */     }
/*     */     
/* 384 */     StringList fontFamilies = parseList(xmlp, "families");
/* 385 */     int fontSize = 0;
/* 386 */     int fontStyle = 0;
/* 387 */     if (fontFamilies != null) {
/* 388 */       fontSize = xmlp.parseIntFromAttribute("size");
/* 389 */       StringList style = parseList(xmlp, "style");
/* 390 */       while (style != null) {
/* 391 */         if ("bold".equalsIgnoreCase(style.getValue())) {
/* 392 */           fontStyle |= 0x1;
/* 393 */         } else if ("italic".equalsIgnoreCase(style.getValue())) {
/* 394 */           fontStyle |= 0x2;
/*     */         } 
/* 396 */         style = style.getNext();
/*     */       } 
/*     */     } 
/*     */     
/* 400 */     FontParameter baseParams = new FontParameter();
/* 401 */     parseFontParameter(xmlp, baseParams);
/* 402 */     ArrayList<FontParameter> fontParams = new ArrayList<FontParameter>();
/* 403 */     ArrayList<StateExpression> stateExpr = new ArrayList<StateExpression>();
/*     */     
/* 405 */     xmlp.nextTag();
/* 406 */     while (!xmlp.isEndTag()) {
/* 407 */       xmlp.require(2, null, "fontParam");
/*     */       
/* 409 */       StateExpression cond = ParserUtil.parseCondition(xmlp);
/* 410 */       if (cond == null) {
/* 411 */         throw xmlp.error("Condition required");
/*     */       }
/* 413 */       stateExpr.add(cond);
/*     */       
/* 415 */       FontParameter params = new FontParameter(baseParams);
/* 416 */       parseFontParameter(xmlp, params);
/* 417 */       fontParams.add(params);
/*     */       
/* 419 */       xmlp.nextTag();
/* 420 */       xmlp.require(3, null, "fontParam");
/* 421 */       xmlp.nextTag();
/*     */     } 
/*     */     
/* 424 */     fontParams.add(baseParams);
/* 425 */     StateSelect stateSelect = new StateSelect(stateExpr);
/* 426 */     FontParameter[] stateParams = fontParams.<FontParameter>toArray(new FontParameter[fontParams.size()]);
/* 427 */     Util.checkGLError();
/* 428 */     if (fontFamilies != null) {
/* 429 */       FontMapper fontMapper = this.renderer.getFontMapper();
/* 430 */       if (fontMapper != null) {
/* 431 */         Font font = fontMapper.getFont(fontFamilies, fontSize, fontStyle, stateSelect, stateParams);
/* 432 */         if (font != null) {
/* 433 */           return font;
/*     */         }
/*     */       } 
/*     */     } 
/* 437 */     Util.checkGLError();
/* 438 */     return this.renderer.loadFont(url, stateSelect, stateParams);
/*     */   }
/*     */   
/*     */   private void parseFontParameter(XMLParser xmlp, FontParameter fp) throws XmlPullParserException {
/* 442 */     for (int i = 0, n = xmlp.getAttributeCount(); i < n; i++) {
/* 443 */       if (xmlp.isAttributeUnused(i)) {
/* 444 */         String name = xmlp.getAttributeName(i);
/* 445 */         FontParameter.Parameter<?> type = FontParameter.getParameter(name);
/* 446 */         if (type != null) {
/* 447 */           String value = xmlp.getAttributeValue(i);
/* 448 */           Class<?> dataClass = type.getDataClass();
/*     */           
/* 450 */           if (dataClass == Color.class) {
/*     */             
/* 452 */             FontParameter.Parameter<Color> colorType = (FontParameter.Parameter)type;
/* 453 */             fp.put(colorType, ParserUtil.parseColor(xmlp, value, this.constants));
/*     */           }
/* 455 */           else if (dataClass == Integer.class) {
/*     */             
/* 457 */             FontParameter.Parameter<Integer> intType = (FontParameter.Parameter)type;
/* 458 */             fp.put(intType, Integer.valueOf(parseMath(xmlp, value).intValue()));
/*     */           }
/* 460 */           else if (dataClass == Boolean.class) {
/*     */             
/* 462 */             FontParameter.Parameter<Boolean> boolType = (FontParameter.Parameter)type;
/* 463 */             fp.put(boolType, Boolean.valueOf(xmlp.parseBool(value)));
/*     */           }
/* 465 */           else if (dataClass == String.class) {
/*     */             
/* 467 */             FontParameter.Parameter<String> strType = (FontParameter.Parameter)type;
/* 468 */             fp.put(strType, value);
/*     */           } else {
/*     */             
/* 471 */             throw xmlp.error("dataClass not yet implemented: " + dataClass);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static StringList parseList(XMLParser xmlp, String name) {
/* 479 */     String value = xmlp.getAttributeValue(null, name);
/* 480 */     if (value != null) {
/* 481 */       return parseList(value, 0);
/*     */     }
/* 483 */     return null;
/*     */   }
/*     */   
/*     */   private static StringList parseList(String value, int idx) {
/* 487 */     idx = TextUtil.skipSpaces(value, idx);
/* 488 */     if (idx >= value.length()) {
/* 489 */       return null;
/*     */     }
/*     */     
/* 492 */     int end = TextUtil.indexOf(value, ',', idx);
/* 493 */     String part = TextUtil.trim(value, idx, end);
/*     */     
/* 495 */     return new StringList(part, parseList(value, end + 1));
/*     */   }
/*     */   
/*     */   private void parseThemeWildcardRef(XMLParser xmlp, ThemeInfoImpl parent) throws IOException, XmlPullParserException {
/* 499 */     String ref = xmlp.getAttributeValue(null, "ref");
/* 500 */     if (parent == null) {
/* 501 */       throw xmlp.error("Can't declare wildcard themes on top level");
/*     */     }
/* 503 */     if (ref == null) {
/* 504 */       throw xmlp.error("Reference required for wildcard theme");
/*     */     }
/* 506 */     if (!ref.endsWith("*")) {
/* 507 */       throw xmlp.error("Wildcard reference must end with '*'");
/*     */     }
/* 509 */     String refPath = ref.substring(0, ref.length() - 1);
/* 510 */     if (refPath.length() > 0 && !refPath.endsWith(".")) {
/* 511 */       throw xmlp.error("Wildcard must end with \".*\" or be \"*\"");
/*     */     }
/* 513 */     parent.wildcardImportPath = refPath;
/* 514 */     xmlp.nextTag();
/*     */   }
/*     */ 
/*     */   
/*     */   private ThemeInfoImpl parseTheme(XMLParser xmlp, String themeName, ThemeInfoImpl parent, URL baseUrl) throws IOException, XmlPullParserException {
/* 519 */     if (!themeName.equals("*") || parent != null) {
/* 520 */       ParserUtil.checkNameNotEmpty(themeName, xmlp);
/* 521 */       if (themeName.indexOf('.') >= 0) {
/* 522 */         throw xmlp.error("'.' is not allowed in names");
/*     */       }
/*     */     } 
/* 525 */     ThemeInfoImpl ti = new ThemeInfoImpl(this, themeName, parent);
/* 526 */     ThemeInfoImpl oldEnv = this.mathInterpreter.setEnv(ti);
/*     */     try {
/* 528 */       if (xmlp.parseBoolFromAttribute("merge", false)) {
/* 529 */         if (parent == null) {
/* 530 */           throw xmlp.error("Can't merge on top level");
/*     */         }
/* 532 */         ThemeInfoImpl tiPrev = parent.getTheme(themeName);
/* 533 */         if (tiPrev != null) {
/* 534 */           ti.copy(tiPrev);
/*     */         }
/*     */       } 
/* 537 */       String ref = xmlp.getAttributeValue(null, "ref");
/* 538 */       if (ref != null) {
/* 539 */         ThemeInfoImpl tiRef = null;
/* 540 */         if (parent != null) {
/* 541 */           tiRef = parent.getTheme(ref);
/*     */         }
/* 543 */         if (tiRef == null) {
/* 544 */           tiRef = (ThemeInfoImpl)findThemeInfo(ref);
/*     */         }
/* 546 */         if (tiRef == null) {
/* 547 */           throw xmlp.error("referenced theme info not found: " + ref);
/*     */         }
/* 549 */         ti.copy(tiRef);
/*     */       } 
/* 551 */       ti.maybeUsedFromWildcard = xmlp.parseBoolFromAttribute("allowWildcard", true);
/* 552 */       xmlp.nextTag();
/* 553 */       while (!xmlp.isEndTag()) {
/* 554 */         xmlp.require(2, null, null);
/* 555 */         String tagName = xmlp.getName();
/* 556 */         String name = xmlp.getAttributeNotNull("name");
/* 557 */         if ("param".equals(tagName)) {
/* 558 */           parseParam(xmlp, baseUrl, "param", ti, ti);
/* 559 */         } else if ("theme".equals(tagName)) {
/* 560 */           if (name.length() == 0) {
/* 561 */             parseThemeWildcardRef(xmlp, ti);
/*     */           } else {
/* 563 */             ThemeInfoImpl tiChild = parseTheme(xmlp, name, ti, baseUrl);
/* 564 */             ti.putTheme(name, tiChild);
/*     */           } 
/*     */         } else {
/* 567 */           throw xmlp.unexpected();
/*     */         } 
/* 569 */         xmlp.require(3, null, tagName);
/* 570 */         xmlp.nextTag();
/*     */       } 
/*     */     } finally {
/* 573 */       this.mathInterpreter.setEnv(oldEnv);
/*     */     } 
/* 575 */     return ti;
/*     */   }
/*     */   
/*     */   private void parseParam(XMLParser xmlp, URL baseUrl, String tagName, ThemeInfoImpl parent, ParameterMapImpl target) throws XmlPullParserException, IOException {
/*     */     try {
/* 580 */       xmlp.require(2, null, tagName);
/* 581 */       String name = xmlp.getAttributeNotNull("name");
/* 582 */       xmlp.nextTag();
/* 583 */       String valueTagName = xmlp.getName();
/* 584 */       Object value = parseValue(xmlp, valueTagName, name, baseUrl, parent);
/* 585 */       xmlp.require(3, null, valueTagName);
/* 586 */       xmlp.nextTag();
/* 587 */       xmlp.require(3, null, tagName);
/* 588 */       if (value instanceof Map) {
/*     */         
/* 590 */         Map<String, ?> map = (Map<String, ?>)value;
/* 591 */         if (parent == null && map.size() != 1) {
/* 592 */           throw xmlp.error("constant definitions must define exactly 1 value");
/*     */         }
/* 594 */         target.put(map);
/*     */       } else {
/* 596 */         ParserUtil.checkNameNotEmpty(name, xmlp);
/* 597 */         target.put(name, value);
/*     */       } 
/* 599 */     } catch (NumberFormatException ex) {
/* 600 */       throw xmlp.error("unable to parse value", ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private ParameterListImpl parseList(XMLParser xmlp, URL baseUrl, ThemeInfoImpl parent) throws XmlPullParserException, IOException {
/* 605 */     ParameterListImpl result = new ParameterListImpl(this, parent);
/* 606 */     xmlp.nextTag();
/* 607 */     while (xmlp.isStartTag()) {
/* 608 */       String tagName = xmlp.getName();
/* 609 */       Object obj = parseValue(xmlp, tagName, null, baseUrl, parent);
/* 610 */       xmlp.require(3, null, tagName);
/* 611 */       result.params.add(obj);
/* 612 */       xmlp.nextTag();
/*     */     } 
/* 614 */     return result;
/*     */   }
/*     */   
/*     */   private ParameterMapImpl parseMap(XMLParser xmlp, URL baseUrl, String name, ThemeInfoImpl parent) throws XmlPullParserException, IOException, NumberFormatException {
/* 618 */     ParameterMapImpl result = new ParameterMapImpl(this, parent);
/* 619 */     if (xmlp.parseBoolFromAttribute("merge", false)) {
/* 620 */       if (parent == null) {
/* 621 */         throw xmlp.error("Can't merge on top level");
/*     */       }
/* 623 */       Object obj = parent.getParam(name);
/* 624 */       if (obj instanceof ParameterMapImpl) {
/* 625 */         ParameterMapImpl base = (ParameterMapImpl)obj;
/* 626 */         result.copy(base);
/* 627 */       } else if (obj != null) {
/* 628 */         throw xmlp.error("Can only merge with map - found a " + obj.getClass().getSimpleName());
/*     */       } 
/*     */     } 
/* 631 */     String ref = xmlp.getAttributeValue(null, "ref");
/* 632 */     if (ref != null) {
/* 633 */       Object obj = parent.getParam(ref);
/* 634 */       if (obj == null) {
/* 635 */         obj = this.constants.getParam(ref);
/* 636 */         if (obj == null) {
/* 637 */           throw new IOException("Referenced map not found: " + ref);
/*     */         }
/*     */       } 
/* 640 */       if (obj instanceof ParameterMapImpl) {
/* 641 */         ParameterMapImpl base = (ParameterMapImpl)obj;
/* 642 */         result.copy(base);
/*     */       } else {
/* 644 */         throw new IOException("Expected a map got a " + obj.getClass().getSimpleName());
/*     */       } 
/*     */     } 
/* 647 */     xmlp.nextTag();
/* 648 */     while (xmlp.isStartTag()) {
/* 649 */       String tagName = xmlp.getName();
/* 650 */       parseParam(xmlp, baseUrl, "param", parent, result);
/* 651 */       xmlp.require(3, null, tagName);
/* 652 */       xmlp.nextTag();
/*     */     } 
/* 654 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   private Object parseValue(XMLParser xmlp, String tagName, String wildcardName, URL baseUrl, ThemeInfoImpl parent) throws XmlPullParserException, IOException, NumberFormatException {
/*     */     try {
/* 660 */       if ("list".equals(tagName)) {
/* 661 */         return parseList(xmlp, baseUrl, parent);
/*     */       }
/* 663 */       if ("map".equals(tagName)) {
/* 664 */         return parseMap(xmlp, baseUrl, wildcardName, parent);
/*     */       }
/* 666 */       if ("inputMapDef".equals(tagName)) {
/* 667 */         return parseInputMap(xmlp, wildcardName, parent);
/*     */       }
/* 669 */       if ("fontDef".equals(tagName)) {
/* 670 */         return parseFont(xmlp, baseUrl);
/*     */       }
/* 672 */       if ("enum".equals(tagName)) {
/* 673 */         String enumType = xmlp.getAttributeNotNull("type");
/* 674 */         Class<? extends Enum> enumClazz = (Class<? extends Enum>)enums.get(enumType);
/* 675 */         if (enumClazz == null) {
/* 676 */           throw xmlp.error("enum type \"" + enumType + "\" not registered");
/*     */         }
/* 678 */         return xmlp.parseEnumFromText(enumClazz);
/*     */       } 
/* 680 */       if ("bool".equals(tagName)) {
/* 681 */         return Boolean.valueOf(xmlp.parseBoolFromText());
/*     */       }
/*     */       
/* 684 */       String value = xmlp.nextText();
/*     */       
/* 686 */       if ("color".equals(tagName)) {
/* 687 */         return ParserUtil.parseColor(xmlp, value, this.constants);
/*     */       }
/* 689 */       if ("float".equals(tagName)) {
/* 690 */         return Float.valueOf(parseMath(xmlp, value).floatValue());
/*     */       }
/* 692 */       if ("int".equals(tagName)) {
/* 693 */         return Integer.valueOf(parseMath(xmlp, value).intValue());
/*     */       }
/* 695 */       if ("string".equals(tagName)) {
/* 696 */         return value;
/*     */       }
/* 698 */       if ("font".equals(tagName)) {
/* 699 */         Font font = this.fonts.get(value);
/* 700 */         if (font == null) {
/* 701 */           throw xmlp.error("Font \"" + value + "\" not found");
/*     */         }
/* 703 */         return font;
/*     */       } 
/* 705 */       if ("border".equals(tagName)) {
/* 706 */         return parseObject(xmlp, value, Border.class);
/*     */       }
/* 708 */       if ("dimension".equals(tagName)) {
/* 709 */         return parseObject(xmlp, value, Dimension.class);
/*     */       }
/* 711 */       if ("gap".equals(tagName) || "size".equals(tagName)) {
/* 712 */         return parseObject(xmlp, value, DialogLayout.Gap.class);
/*     */       }
/* 714 */       if ("constant".equals(tagName)) {
/* 715 */         Object result = this.constants.getParam(value);
/* 716 */         if (result == null) {
/* 717 */           throw xmlp.error("Unknown constant: " + value);
/*     */         }
/* 719 */         if (result == NULL) {
/* 720 */           result = null;
/*     */         }
/* 722 */         return result;
/*     */       } 
/* 724 */       if ("image".equals(tagName)) {
/* 725 */         if (value.endsWith(".*")) {
/* 726 */           if (wildcardName == null) {
/* 727 */             throw new IllegalArgumentException("Wildcard's not allowed");
/*     */           }
/* 729 */           return this.imageManager.getImages(value, wildcardName);
/*     */         } 
/* 731 */         return this.imageManager.getReferencedImage(xmlp, value);
/*     */       } 
/* 733 */       if ("cursor".equals(tagName)) {
/* 734 */         if (value.endsWith(".*")) {
/* 735 */           if (wildcardName == null) {
/* 736 */             throw new IllegalArgumentException("Wildcard's not allowed");
/*     */           }
/* 738 */           return this.imageManager.getCursors(value, wildcardName);
/*     */         } 
/* 740 */         return this.imageManager.getReferencedCursor(xmlp, value);
/*     */       } 
/* 742 */       if ("inputMap".equals(tagName)) {
/* 743 */         return getInputMap(xmlp, value);
/*     */       }
/* 745 */       throw xmlp.error("Unknown type \"" + tagName + "\" specified");
/* 746 */     } catch (NumberFormatException ex) {
/* 747 */       throw xmlp.error("unable to parse value", ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private Number parseMath(XMLParser xmlp, String str) throws XmlPullParserException {
/*     */     try {
/* 753 */       return this.mathInterpreter.execute(str);
/* 754 */     } catch (ParseException ex) {
/* 755 */       throw xmlp.error("unable to evaluate", unwrap(ex));
/*     */     } 
/*     */   }
/*     */   
/*     */   private <T> T parseObject(XMLParser xmlp, String str, Class<T> type) throws XmlPullParserException {
/*     */     try {
/* 761 */       return (T)this.mathInterpreter.executeCreateObject(str, type);
/* 762 */     } catch (ParseException ex) {
/* 763 */       throw xmlp.error("unable to evaluate", unwrap(ex));
/*     */     } 
/*     */   }
/*     */   
/*     */   private Throwable unwrap(ParseException ex) {
/* 768 */     if (ex.getCause() != null) {
/* 769 */       return ex.getCause();
/*     */     }
/* 771 */     return ex;
/*     */   }
/*     */ 
/*     */   
/*     */   ThemeInfo resolveWildcard(String base, String name, boolean useFallback) {
/* 776 */     assert base.length() == 0 || base.endsWith(".");
/* 777 */     String fullPath = base.concat(name);
/* 778 */     ThemeInfo info = findThemeInfo(fullPath, false, useFallback);
/* 779 */     if (info != null && ((ThemeInfoImpl)info).maybeUsedFromWildcard) {
/* 780 */       return info;
/*     */     }
/* 782 */     return null;
/*     */   }
/*     */   
/*     */   class MathInterpreter extends AbstractMathInterpreter {
/*     */     private ThemeInfoImpl env;
/*     */     
/*     */     public ThemeInfoImpl setEnv(ThemeInfoImpl env) {
/* 789 */       ThemeInfoImpl oldEnv = this.env;
/* 790 */       this.env = env;
/* 791 */       return oldEnv;
/*     */     }
/*     */     
/*     */     public void accessVariable(String name) {
/* 795 */       for (ThemeInfoImpl e = this.env; e != null; e = e.parent) {
/* 796 */         Object object = e.getParam(name);
/* 797 */         if (object != null) {
/* 798 */           push(object);
/*     */           return;
/*     */         } 
/* 801 */         object = e.getChildThemeImpl(name, false);
/* 802 */         if (object != null) {
/* 803 */           push(object);
/*     */           return;
/*     */         } 
/*     */       } 
/* 807 */       Object obj = ThemeManager.this.constants.getParam(name);
/* 808 */       if (obj != null) {
/* 809 */         push(obj);
/*     */         return;
/*     */       } 
/* 812 */       Font font = (Font)ThemeManager.this.fonts.get(name);
/* 813 */       if (font != null) {
/* 814 */         push(font);
/*     */         return;
/*     */       } 
/* 817 */       throw new IllegalArgumentException("variable not found: " + name);
/*     */     }
/*     */ 
/*     */     
/*     */     protected Object accessField(Object obj, String field) {
/* 822 */       if (obj instanceof ThemeInfoImpl) {
/* 823 */         Object result = ((ThemeInfoImpl)obj).getTheme(field);
/* 824 */         if (result != null) {
/* 825 */           return result;
/*     */         }
/*     */       } 
/* 828 */       if (obj instanceof ParameterMapImpl) {
/* 829 */         Object result = ((ParameterMapImpl)obj).getParam(field);
/* 830 */         if (result == null) {
/* 831 */           throw new IllegalArgumentException("field not found: " + field);
/*     */         }
/* 833 */         return result;
/*     */       } 
/* 835 */       if (obj instanceof Image && "border".equals(field)) {
/* 836 */         Border border = null;
/* 837 */         if (obj instanceof HasBorder) {
/* 838 */           border = ((HasBorder)obj).getBorder();
/*     */         }
/* 840 */         return (border != null) ? border : Border.ZERO;
/*     */       } 
/* 842 */       return super.accessField(obj, field);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\theme\ThemeManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */