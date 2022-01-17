/*     */ package de.matthiasmann.twl.textarea;
/*     */ 
/*     */ import de.matthiasmann.twl.Color;
/*     */ import de.matthiasmann.twl.utils.ParameterStringParser;
/*     */ import de.matthiasmann.twl.utils.StringList;
/*     */ import de.matthiasmann.twl.utils.TextUtil;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
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
/*     */ 
/*     */ 
/*     */ public class CSSStyle
/*     */   extends Style
/*     */ {
/*     */   protected CSSStyle() {}
/*     */   
/*     */   public CSSStyle(String cssStyle) {
/*  52 */     parseCSS(cssStyle);
/*     */   }
/*     */   
/*     */   public CSSStyle(Style parent, StyleSheetKey styleSheetKey, String cssStyle) {
/*  56 */     super(parent, styleSheetKey);
/*  57 */     parseCSS(cssStyle);
/*     */   }
/*     */   
/*     */   private void parseCSS(String style) {
/*  61 */     ParameterStringParser psp = new ParameterStringParser(style, ';', ':');
/*  62 */     psp.setTrim(true);
/*  63 */     while (psp.next()) {
/*     */       try {
/*  65 */         parseCSSAttribute(psp.getKey(), psp.getValue());
/*  66 */       } catch (IllegalArgumentException ex) {
/*  67 */         Logger.getLogger(CSSStyle.class.getName()).log(Level.SEVERE, "Unable to parse CSS attribute: " + psp.getKey() + "=" + psp.getValue(), ex);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void parseCSSAttribute(String key, String value) {
/*  74 */     if (key.startsWith("margin")) {
/*  75 */       parseBox(key.substring(6), value, StyleAttribute.MARGIN);
/*     */       return;
/*     */     } 
/*  78 */     if (key.startsWith("padding")) {
/*  79 */       parseBox(key.substring(7), value, StyleAttribute.PADDING);
/*     */       return;
/*     */     } 
/*  82 */     if (key.startsWith("font")) {
/*  83 */       parseFont(key, value);
/*     */       return;
/*     */     } 
/*  86 */     if ("text-indent".equals(key)) {
/*  87 */       parseValueUnit(StyleAttribute.TEXT_INDENT, value);
/*     */       return;
/*     */     } 
/*  90 */     if ("-twl-font".equals(key)) {
/*  91 */       put(StyleAttribute.FONT_FAMILIES, new StringList(value));
/*     */       return;
/*     */     } 
/*  94 */     if ("-twl-hover".equals(key)) {
/*  95 */       parseEnum(StyleAttribute.INHERIT_HOVER, INHERITHOVER, value);
/*     */       return;
/*     */     } 
/*  98 */     if ("text-align".equals(key)) {
/*  99 */       parseEnum(StyleAttribute.HORIZONTAL_ALIGNMENT, value);
/*     */       return;
/*     */     } 
/* 102 */     if ("text-decoration".equals(key)) {
/* 103 */       parseEnum(StyleAttribute.TEXT_DECORATION, TEXTDECORATION, value);
/*     */       return;
/*     */     } 
/* 106 */     if ("vertical-align".equals(key)) {
/* 107 */       parseEnum(StyleAttribute.VERTICAL_ALIGNMENT, value);
/*     */       return;
/*     */     } 
/* 110 */     if ("white-space".equals(key)) {
/* 111 */       parseEnum(StyleAttribute.PREFORMATTED, PRE, value);
/*     */       return;
/*     */     } 
/* 114 */     if ("word-wrap".equals(key)) {
/* 115 */       parseEnum(StyleAttribute.BREAKWORD, BREAKWORD, value);
/*     */       return;
/*     */     } 
/* 118 */     if ("list-style-image".equals(key)) {
/* 119 */       parseURL(StyleAttribute.LIST_STYLE_IMAGE, value);
/*     */       return;
/*     */     } 
/* 122 */     if ("list-style-type".equals(key)) {
/* 123 */       parseEnum(StyleAttribute.LIST_STYLE_TYPE, OLT, value);
/*     */       return;
/*     */     } 
/* 126 */     if ("clear".equals(key)) {
/* 127 */       parseEnum(StyleAttribute.CLEAR, value);
/*     */       return;
/*     */     } 
/* 130 */     if ("float".equals(key)) {
/* 131 */       parseEnum(StyleAttribute.FLOAT_POSITION, value);
/*     */       return;
/*     */     } 
/* 134 */     if ("display".equals(key)) {
/* 135 */       parseEnum(StyleAttribute.DISPLAY, value);
/*     */       return;
/*     */     } 
/* 138 */     if ("width".equals(key)) {
/* 139 */       parseValueUnit(StyleAttribute.WIDTH, value);
/*     */       return;
/*     */     } 
/* 142 */     if ("height".equals(key)) {
/* 143 */       parseValueUnit(StyleAttribute.HEIGHT, value);
/*     */       return;
/*     */     } 
/* 146 */     if ("background-image".equals(key)) {
/* 147 */       parseURL(StyleAttribute.BACKGROUND_IMAGE, value);
/*     */       return;
/*     */     } 
/* 150 */     if ("background-color".equals(key) || "-twl-background-color".equals(key)) {
/* 151 */       parseColor(StyleAttribute.BACKGROUND_COLOR, value);
/*     */       return;
/*     */     } 
/* 154 */     if ("color".equals(key)) {
/* 155 */       parseColor(StyleAttribute.COLOR, value);
/*     */       return;
/*     */     } 
/* 158 */     if ("tab-size".equals(key) || "-moz-tab-size".equals(key)) {
/* 159 */       parseInteger(StyleAttribute.TAB_SIZE, value);
/*     */       return;
/*     */     } 
/* 162 */     throw new IllegalArgumentException("Unsupported key: " + key);
/*     */   }
/*     */   
/*     */   private void parseBox(String key, String value, BoxAttribute box) {
/* 166 */     if ("-top".equals(key)) {
/* 167 */       parseValueUnit(box.top, value);
/* 168 */     } else if ("-left".equals(key)) {
/* 169 */       parseValueUnit(box.left, value);
/* 170 */     } else if ("-right".equals(key)) {
/* 171 */       parseValueUnit(box.right, value);
/* 172 */     } else if ("-bottom".equals(key)) {
/* 173 */       parseValueUnit(box.bottom, value);
/* 174 */     } else if ("".equals(key)) {
/* 175 */       Value[] vu = parseValueUnits(value);
/* 176 */       switch (vu.length) {
/*     */         case 1:
/* 178 */           put(box.top, vu[0]);
/* 179 */           put(box.left, vu[0]);
/* 180 */           put(box.right, vu[0]);
/* 181 */           put(box.bottom, vu[0]);
/*     */           return;
/*     */         case 2:
/* 184 */           put(box.top, vu[0]);
/* 185 */           put(box.left, vu[1]);
/* 186 */           put(box.right, vu[1]);
/* 187 */           put(box.bottom, vu[0]);
/*     */           return;
/*     */         case 3:
/* 190 */           put(box.top, vu[0]);
/* 191 */           put(box.left, vu[1]);
/* 192 */           put(box.right, vu[1]);
/* 193 */           put(box.bottom, vu[2]);
/*     */           return;
/*     */         case 4:
/* 196 */           put(box.top, vu[0]);
/* 197 */           put(box.left, vu[3]);
/* 198 */           put(box.right, vu[1]);
/* 199 */           put(box.bottom, vu[2]);
/*     */           return;
/*     */       } 
/* 202 */       throw new IllegalArgumentException("Invalid number of margin values: " + vu.length);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void parseFont(String key, String value) {
/* 208 */     if ("font-family".equals(key)) {
/* 209 */       parseList(StyleAttribute.FONT_FAMILIES, value);
/*     */       return;
/*     */     } 
/* 212 */     if ("font-weight".equals(key)) {
/* 213 */       Integer weight = WEIGHTS.get(value);
/* 214 */       if (weight == null) {
/* 215 */         weight = Integer.valueOf(value);
/*     */       }
/* 217 */       put(StyleAttribute.FONT_WEIGHT, weight);
/*     */       return;
/*     */     } 
/* 220 */     if ("font-size".equals(key)) {
/* 221 */       parseValueUnit(StyleAttribute.FONT_SIZE, value);
/*     */       return;
/*     */     } 
/* 224 */     if ("font-style".equals(key)) {
/* 225 */       parseEnum(StyleAttribute.FONT_ITALIC, ITALIC, value);
/*     */       return;
/*     */     } 
/* 228 */     if ("font".equals(key)) {
/* 229 */       value = parseStartsWith(StyleAttribute.FONT_WEIGHT, WEIGHTS, value);
/* 230 */       value = parseStartsWith(StyleAttribute.FONT_ITALIC, ITALIC, value);
/* 231 */       if (value.length() > 0 && Character.isDigit(value.charAt(0))) {
/* 232 */         int end = TextUtil.indexOf(value, ' ', 0);
/* 233 */         parseValueUnit(StyleAttribute.FONT_SIZE, value.substring(0, end));
/* 234 */         end = TextUtil.skipSpaces(value, end);
/* 235 */         value = value.substring(end);
/*     */       } 
/* 237 */       parseList(StyleAttribute.FONT_FAMILIES, value);
/*     */     } 
/*     */   }
/*     */   
/*     */   private Value parseValueUnit(String value) {
/*     */     Value.Unit unit;
/* 243 */     int suffixLength = 2;
/* 244 */     if (value.endsWith("px"))
/* 245 */     { unit = Value.Unit.PX; }
/* 246 */     else if (value.endsWith("pt"))
/* 247 */     { unit = Value.Unit.PT; }
/* 248 */     else if (value.endsWith("em"))
/* 249 */     { unit = Value.Unit.EM; }
/* 250 */     else if (value.endsWith("ex"))
/* 251 */     { unit = Value.Unit.EX; }
/* 252 */     else if (value.endsWith("%"))
/* 253 */     { suffixLength = 1;
/* 254 */       unit = Value.Unit.PERCENT; }
/* 255 */     else { if ("0".equals(value))
/* 256 */         return Value.ZERO_PX; 
/* 257 */       if ("auto".equals(value)) {
/* 258 */         return Value.AUTO;
/*     */       }
/* 260 */       throw new IllegalArgumentException("Unknown numeric suffix: " + value); }
/*     */ 
/*     */     
/* 263 */     String numberPart = TextUtil.trim(value, 0, value.length() - suffixLength);
/* 264 */     return new Value(Float.parseFloat(numberPart), unit);
/*     */   }
/*     */   
/*     */   private Value[] parseValueUnits(String value) {
/* 268 */     String[] parts = value.split("\\s+");
/* 269 */     Value[] result = new Value[parts.length];
/* 270 */     for (int i = 0; i < parts.length; i++) {
/* 271 */       result[i] = parseValueUnit(parts[i]);
/*     */     }
/* 273 */     return result;
/*     */   }
/*     */   
/*     */   private void parseValueUnit(StyleAttribute<?> attribute, String value) {
/* 277 */     put(attribute, parseValueUnit(value));
/*     */   }
/*     */   
/*     */   private void parseInteger(StyleAttribute<Integer> attribute, String value) {
/* 281 */     if ("inherit".equals(value)) {
/* 282 */       put(attribute, null);
/*     */     } else {
/* 284 */       int intval = Integer.parseInt(value);
/* 285 */       put(attribute, Integer.valueOf(intval));
/*     */     } 
/*     */   }
/*     */   
/*     */   private <T> void parseEnum(StyleAttribute<T> attribute, HashMap<String, T> map, String value) {
/* 290 */     T obj = map.get(value);
/* 291 */     if (obj == null) {
/* 292 */       throw new IllegalArgumentException("Unknown value: " + value);
/*     */     }
/* 294 */     put(attribute, obj);
/*     */   }
/*     */   
/*     */   private <E extends Enum<E>> void parseEnum(StyleAttribute<E> attribute, String value) {
/* 298 */     E obj = Enum.valueOf(attribute.getDataType(), value.toUpperCase(Locale.ENGLISH));
/* 299 */     put(attribute, obj);
/*     */   }
/*     */   
/*     */   private <E> String parseStartsWith(StyleAttribute<E> attribute, HashMap<String, E> map, String value) {
/* 303 */     int end = TextUtil.indexOf(value, ' ', 0);
/* 304 */     E obj = map.get(value.substring(0, end));
/* 305 */     if (obj != null) {
/* 306 */       end = TextUtil.skipSpaces(value, end);
/* 307 */       value = value.substring(end);
/*     */     } 
/* 309 */     put(attribute, obj);
/* 310 */     return value;
/*     */   }
/*     */   
/*     */   private void parseURL(StyleAttribute<String> attribute, String value) {
/* 314 */     put(attribute, stripURL(value));
/*     */   }
/*     */   
/*     */   static String stripTrim(String value, int start, int end) {
/* 318 */     return TextUtil.trim(value, start, value.length() - end);
/*     */   }
/*     */   
/*     */   static String stripURL(String value) {
/* 322 */     if (value.startsWith("url(") && value.endsWith(")")) {
/* 323 */       value = stripQuotes(stripTrim(value, 4, 1));
/*     */     }
/* 325 */     return value;
/*     */   }
/*     */   
/*     */   static String stripQuotes(String value) {
/* 329 */     if ((value.startsWith("\"") && value.endsWith("\"")) || (value.startsWith("'") && value.endsWith("'")))
/*     */     {
/* 331 */       value = value.substring(1, value.length() - 1);
/*     */     }
/* 333 */     return value;
/*     */   }
/*     */   
/*     */   private void parseColor(StyleAttribute<Color> attribute, String value) {
/*     */     Color color;
/* 338 */     if (value.startsWith("rgb(") && value.endsWith(")")) {
/* 339 */       value = stripTrim(value, 4, 1);
/* 340 */       byte[] rgb = parseRGBA(value, 3);
/* 341 */       color = new Color(rgb[0], rgb[1], rgb[2], (byte)-1);
/* 342 */     } else if (value.startsWith("rgba(") && value.endsWith(")")) {
/* 343 */       value = stripTrim(value, 5, 1);
/* 344 */       byte[] rgba = parseRGBA(value, 4);
/* 345 */       color = new Color(rgba[0], rgba[1], rgba[2], rgba[3]);
/*     */     } else {
/* 347 */       color = Color.parserColor(value);
/* 348 */       if (color == null) {
/* 349 */         throw new IllegalArgumentException("unknown color name: " + value);
/*     */       }
/*     */     } 
/* 352 */     put(attribute, color);
/*     */   }
/*     */   
/*     */   private byte[] parseRGBA(String value, int numElements) {
/* 356 */     String[] parts = value.split(",");
/* 357 */     if (parts.length != numElements) {
/* 358 */       throw new IllegalArgumentException("3 values required for rgb()");
/*     */     }
/* 360 */     byte[] rgba = new byte[numElements];
/* 361 */     for (int i = 0; i < numElements; i++) {
/* 362 */       int v; String part = parts[i].trim();
/*     */       
/* 364 */       if (i == 3) {
/*     */         
/* 366 */         float f = Float.parseFloat(part);
/* 367 */         v = Math.round(f * 255.0F);
/*     */       } else {
/* 369 */         boolean percent = part.endsWith("%");
/* 370 */         if (percent) {
/* 371 */           part = stripTrim(value, 0, 1);
/*     */         }
/* 373 */         v = Integer.parseInt(part);
/* 374 */         if (percent) {
/* 375 */           v = 255 * v / 100;
/*     */         }
/*     */       } 
/* 378 */       rgba[i] = (byte)Math.max(0, Math.min(255, v));
/*     */     } 
/* 380 */     return rgba;
/*     */   }
/*     */   
/*     */   private void parseList(StyleAttribute<StringList> attribute, String value) {
/* 384 */     put(attribute, parseList(value, 0));
/*     */   } static StringList parseList(String value, int idx) {
/*     */     int end;
/*     */     String part;
/* 388 */     idx = TextUtil.skipSpaces(value, idx);
/* 389 */     if (idx >= value.length()) {
/* 390 */       return null;
/*     */     }
/*     */     
/* 393 */     char startChar = value.charAt(idx);
/*     */ 
/*     */ 
/*     */     
/* 397 */     if (startChar == '"' || startChar == '\'') {
/* 398 */       idx++;
/* 399 */       end = TextUtil.indexOf(value, startChar, idx);
/* 400 */       part = value.substring(idx, end);
/* 401 */       end = TextUtil.skipSpaces(value, ++end);
/* 402 */       if (end < value.length() && value.charAt(end) != ',') {
/* 403 */         throw new IllegalArgumentException("',' expected at " + idx);
/*     */       }
/*     */     } else {
/* 406 */       end = TextUtil.indexOf(value, ',', idx);
/* 407 */       part = TextUtil.trim(value, idx, end);
/*     */     } 
/*     */     
/* 410 */     return new StringList(part, parseList(value, end + 1));
/*     */   }
/*     */   
/* 413 */   static final HashMap<String, Boolean> PRE = new HashMap<String, Boolean>();
/* 414 */   static final HashMap<String, Boolean> BREAKWORD = new HashMap<String, Boolean>();
/* 415 */   static final HashMap<String, OrderedListType> OLT = new HashMap<String, OrderedListType>();
/* 416 */   static final HashMap<String, Boolean> ITALIC = new HashMap<String, Boolean>();
/* 417 */   static final HashMap<String, Integer> WEIGHTS = new HashMap<String, Integer>();
/* 418 */   static final HashMap<String, TextDecoration> TEXTDECORATION = new HashMap<String, TextDecoration>();
/* 419 */   static final HashMap<String, Boolean> INHERITHOVER = new HashMap<String, Boolean>();
/*     */   
/*     */   static OrderedListType createRoman(final boolean lowercase) {
/* 422 */     return new OrderedListType()
/*     */       {
/*     */         public String format(int nr) {
/* 425 */           if (nr >= 1 && nr <= 39999) {
/* 426 */             String str = TextUtil.toRomanNumberString(nr);
/* 427 */             return lowercase ? str.toLowerCase() : str;
/*     */           } 
/* 429 */           return Integer.toString(nr);
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 436 */     PRE.put("pre", Boolean.TRUE);
/* 437 */     PRE.put("normal", Boolean.FALSE);
/*     */     
/* 439 */     BREAKWORD.put("normal", Boolean.FALSE);
/* 440 */     BREAKWORD.put("break-word", Boolean.TRUE);
/*     */     
/* 442 */     OrderedListType upper_alpha = new OrderedListType("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
/* 443 */     OrderedListType lower_alpha = new OrderedListType("abcdefghijklmnopqrstuvwxyz");
/* 444 */     OLT.put("decimal", OrderedListType.DECIMAL);
/* 445 */     OLT.put("upper-alpha", upper_alpha);
/* 446 */     OLT.put("lower-alpha", lower_alpha);
/* 447 */     OLT.put("upper-latin", upper_alpha);
/* 448 */     OLT.put("lower-latin", lower_alpha);
/* 449 */     OLT.put("upper-roman", createRoman(false));
/* 450 */     OLT.put("lower-roman", createRoman(true));
/* 451 */     OLT.put("lower-greek", new OrderedListType("αβγδεζηθικλμνξοπρστυφχψω"));
/* 452 */     OLT.put("upper-norwegian", new OrderedListType("ABCDEFGHIJKLMNOPQRSTUVWXYZÆØÅ"));
/* 453 */     OLT.put("lower-norwegian", new OrderedListType("abcdefghijklmnopqrstuvwxyzæøå"));
/* 454 */     OLT.put("upper-russian-short", new OrderedListType("АБВГДЕЖЗИКЛМНОПРСТУФХЦЧШЩЭЮЯ"));
/* 455 */     OLT.put("lower-russian-short", new OrderedListType("абвгдежзиклмнопрстуфхцчшщэюя"));
/*     */     
/* 457 */     ITALIC.put("normal", Boolean.FALSE);
/* 458 */     ITALIC.put("italic", Boolean.TRUE);
/* 459 */     ITALIC.put("oblique", Boolean.TRUE);
/*     */     
/* 461 */     WEIGHTS.put("normal", Integer.valueOf(400));
/* 462 */     WEIGHTS.put("bold", Integer.valueOf(700));
/*     */     
/* 464 */     TEXTDECORATION.put("none", TextDecoration.NONE);
/* 465 */     TEXTDECORATION.put("underline", TextDecoration.UNDERLINE);
/* 466 */     TEXTDECORATION.put("line-through", TextDecoration.LINE_THROUGH);
/*     */     
/* 468 */     INHERITHOVER.put("inherit", Boolean.TRUE);
/* 469 */     INHERITHOVER.put("normal", Boolean.FALSE);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\textarea\CSSStyle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */