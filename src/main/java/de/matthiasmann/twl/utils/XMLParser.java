/*     */ package de.matthiasmann.twl.utils;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.util.BitSet;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.xmlpull.mxp1.MXParserCachingStrings;
/*     */ import org.xmlpull.v1.XmlPullParser;
/*     */ import org.xmlpull.v1.XmlPullParserException;
/*     */ import org.xmlpull.v1.XmlPullParserFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMLParser
/*     */   implements Closeable
/*     */ {
/*  58 */   private static final Class<?>[] XPP_CLASS = new Class[] { XmlPullParser.class };
/*     */   
/*     */   private static boolean hasXMP1 = true;
/*     */   private final XmlPullParser xpp;
/*     */   private final String source;
/*     */   private final InputStream inputStream;
/*  64 */   private final BitSet unusedAttributes = new BitSet();
/*  65 */   private String loggerName = XMLParser.class.getName();
/*     */   
/*     */   public static XmlPullParser createParser() throws XmlPullParserException {
/*  68 */     if (hasXMP1) {
/*     */       try {
/*  70 */         MXParserCachingStrings mXParserCachingStrings = new MXParserCachingStrings();
/*  71 */         mXParserCachingStrings.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
/*     */         
/*  73 */         return (XmlPullParser)mXParserCachingStrings;
/*  74 */       } catch (Throwable ex) {
/*  75 */         hasXMP1 = false;
/*  76 */         Logger.getLogger(XMLParser.class.getName()).log(Level.WARNING, "Failed direct instantation", ex);
/*     */       } 
/*     */     }
/*     */     
/*  80 */     return XPPF.newPullParser();
/*     */   }
/*     */   
/*     */   public XMLParser(XmlPullParser xpp, String source) {
/*  84 */     if (xpp == null) {
/*  85 */       throw new NullPointerException("xpp");
/*     */     }
/*  87 */     this.xpp = xpp;
/*  88 */     this.source = source;
/*  89 */     this.inputStream = null;
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
/*     */   public XMLParser(URL url) throws XmlPullParserException, IOException {
/* 106 */     if (url == null) {
/* 107 */       throw new NullPointerException("url");
/*     */     }
/*     */     
/* 110 */     XmlPullParser xpp_ = null;
/* 111 */     InputStream is = null;
/*     */     
/* 113 */     this.source = url.toString();
/*     */     
/*     */     try {
/* 116 */       xpp_ = (XmlPullParser)url.getContent(XPP_CLASS);
/* 117 */     } catch (IOException ex) {}
/*     */ 
/*     */ 
/*     */     
/* 121 */     if (xpp_ == null) {
/* 122 */       xpp_ = createParser();
/* 123 */       is = url.openStream();
/* 124 */       if (is == null) {
/* 125 */         throw new FileNotFoundException(this.source);
/*     */       }
/* 127 */       xpp_.setInput(is, "UTF8");
/*     */     } 
/*     */     
/* 130 */     this.xpp = xpp_;
/* 131 */     this.inputStream = is;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 135 */     if (this.inputStream != null) {
/* 136 */       this.inputStream.close();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setLoggerName(String loggerName) {
/* 141 */     this.loggerName = loggerName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int next() throws XmlPullParserException, IOException {
/* 148 */     warnUnusedAttributes();
/* 149 */     int type = this.xpp.next();
/* 150 */     handleType(type);
/* 151 */     return type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int nextTag() throws XmlPullParserException, IOException {
/* 158 */     warnUnusedAttributes();
/* 159 */     int type = this.xpp.nextTag();
/* 160 */     handleType(type);
/* 161 */     return type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String nextText() throws XmlPullParserException, IOException {
/* 168 */     warnUnusedAttributes();
/* 169 */     return this.xpp.nextText();
/*     */   }
/*     */   public char[] nextText(int[] startAndLength) throws XmlPullParserException, IOException {
/*     */     int token;
/* 173 */     warnUnusedAttributes(); while (true) {
/*     */       String replaced;
/* 175 */       token = this.xpp.nextToken();
/* 176 */       switch (token) {
/*     */         case 4:
/* 178 */           return this.xpp.getTextCharacters(startAndLength);
/*     */         case 6:
/* 180 */           replaced = this.xpp.getText();
/* 181 */           startAndLength[0] = 0;
/* 182 */           startAndLength[1] = replaced.length();
/* 183 */           return replaced.toCharArray();
/*     */         case 9:
/*     */           continue;
/*     */       }  break;
/*     */     } 
/* 188 */     handleType(token);
/* 189 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void skipText() throws XmlPullParserException, IOException {
/* 195 */     int token = this.xpp.getEventType();
/* 196 */     while (token == 4 || token == 6 || token == 9) {
/* 197 */       token = this.xpp.nextToken();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isStartTag() throws XmlPullParserException {
/* 202 */     return (this.xpp.getEventType() == 2);
/*     */   }
/*     */   
/*     */   public boolean isEndTag() throws XmlPullParserException {
/* 206 */     return (this.xpp.getEventType() == 3);
/*     */   }
/*     */   
/*     */   public String getPositionDescription() {
/* 210 */     String desc = this.xpp.getPositionDescription();
/* 211 */     if (this.source != null) {
/* 212 */       return desc + " in " + this.source;
/*     */     }
/* 214 */     return desc;
/*     */   }
/*     */   
/*     */   public int getLineNumber() {
/* 218 */     return this.xpp.getLineNumber();
/*     */   }
/*     */   
/*     */   public int getColumnNumber() {
/* 222 */     return this.xpp.getColumnNumber();
/*     */   }
/*     */   
/*     */   public String getName() {
/* 226 */     return this.xpp.getName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void require(int type, String namespace, String name) throws XmlPullParserException, IOException {
/* 233 */     this.xpp.require(type, namespace, name);
/*     */   }
/*     */   
/*     */   public String getAttributeValue(int index) {
/* 237 */     this.unusedAttributes.clear(index);
/* 238 */     return this.xpp.getAttributeValue(index);
/*     */   }
/*     */   
/*     */   public String getAttributeNamespace(int index) {
/* 242 */     return this.xpp.getAttributeNamespace(index);
/*     */   }
/*     */   
/*     */   public String getAttributeName(int index) {
/* 246 */     return this.xpp.getAttributeName(index);
/*     */   }
/*     */   
/*     */   public int getAttributeCount() {
/* 250 */     return this.xpp.getAttributeCount();
/*     */   }
/*     */   
/*     */   public String getAttributeValue(String namespace, String name) {
/* 254 */     for (int i = 0, n = this.xpp.getAttributeCount(); i < n; i++) {
/* 255 */       if ((namespace == null || namespace.equals(this.xpp.getAttributeNamespace(i))) && name.equals(this.xpp.getAttributeName(i)))
/*     */       {
/* 257 */         return getAttributeValue(i);
/*     */       }
/*     */     } 
/* 260 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAttributeNotNull(String attribute) throws XmlPullParserException {
/* 265 */     String value = getAttributeValue(null, attribute);
/* 266 */     if (value == null) {
/* 267 */       missingAttribute(attribute);
/*     */     }
/* 269 */     return value;
/*     */   }
/*     */   
/*     */   public boolean parseBoolFromAttribute(String attribName) throws XmlPullParserException {
/* 273 */     return parseBool(getAttributeNotNull(attribName));
/*     */   }
/*     */   
/*     */   public boolean parseBoolFromText() throws XmlPullParserException, IOException {
/* 277 */     return parseBool(nextText());
/*     */   }
/*     */   
/*     */   public boolean parseBoolFromAttribute(String attribName, boolean defaultValue) throws XmlPullParserException {
/* 281 */     String value = getAttributeValue(null, attribName);
/* 282 */     if (value == null) {
/* 283 */       return defaultValue;
/*     */     }
/* 285 */     return parseBool(value);
/*     */   }
/*     */   
/*     */   public int parseIntFromAttribute(String attribName) throws XmlPullParserException {
/* 289 */     return parseInt(getAttributeNotNull(attribName));
/*     */   }
/*     */   
/*     */   public int parseIntFromAttribute(String attribName, int defaultValue) throws XmlPullParserException {
/* 293 */     String value = getAttributeValue(null, attribName);
/* 294 */     if (value == null) {
/* 295 */       return defaultValue;
/*     */     }
/* 297 */     return parseInt(value);
/*     */   }
/*     */   
/*     */   public float parseFloatFromAttribute(String attribName) throws XmlPullParserException {
/* 301 */     return parseFloat(getAttributeNotNull(attribName));
/*     */   }
/*     */   
/*     */   public float parseFloatFromAttribute(String attribName, float defaultValue) throws XmlPullParserException {
/* 305 */     String value = getAttributeValue(null, attribName);
/* 306 */     if (value == null) {
/* 307 */       return defaultValue;
/*     */     }
/* 309 */     return parseFloat(value);
/*     */   }
/*     */   
/*     */   public <E extends Enum<E>> E parseEnumFromAttribute(String attribName, Class<E> enumClazz) throws XmlPullParserException {
/* 313 */     return parseEnum(enumClazz, getAttributeNotNull(attribName));
/*     */   }
/*     */   
/*     */   public <E extends Enum<E>> E parseEnumFromAttribute(String attribName, Class<E> enumClazz, E defaultValue) throws XmlPullParserException {
/* 317 */     String value = getAttributeValue(null, attribName);
/* 318 */     if (value == null) {
/* 319 */       return defaultValue;
/*     */     }
/* 321 */     return parseEnum(enumClazz, value);
/*     */   }
/*     */   
/*     */   public <E extends Enum<E>> E parseEnumFromText(Class<E> enumClazz) throws XmlPullParserException, IOException {
/* 325 */     return parseEnum(enumClazz, nextText());
/*     */   }
/*     */   
/*     */   public Map<String, String> getUnusedAttributes() {
/* 329 */     if (this.unusedAttributes.isEmpty()) {
/* 330 */       return Collections.emptyMap();
/*     */     }
/* 332 */     LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();
/* 333 */     for (int i = -1; (i = this.unusedAttributes.nextSetBit(i + 1)) >= 0;) {
/* 334 */       result.put(this.xpp.getAttributeName(i), this.xpp.getAttributeValue(i));
/*     */     }
/* 336 */     this.unusedAttributes.clear();
/* 337 */     return result;
/*     */   }
/*     */   
/*     */   public void ignoreOtherAttributes() {
/* 341 */     this.unusedAttributes.clear();
/*     */   }
/*     */   
/*     */   public boolean isAttributeUnused(int idx) {
/* 345 */     return this.unusedAttributes.get(idx);
/*     */   }
/*     */   
/*     */   public XmlPullParserException error(String msg) {
/* 349 */     return new XmlPullParserException(msg, this.xpp, null);
/*     */   }
/*     */   
/*     */   public XmlPullParserException error(String msg, Throwable cause) {
/* 353 */     return (XmlPullParserException)(new XmlPullParserException(msg, this.xpp, cause)).initCause(cause);
/*     */   }
/*     */   
/*     */   public XmlPullParserException unexpected() {
/* 357 */     return new XmlPullParserException("Unexpected '" + this.xpp.getName() + "'", this.xpp, null);
/*     */   }
/*     */   
/*     */   protected <E extends Enum<E>> E parseEnum(Class<E> enumClazz, String value) throws XmlPullParserException {
/*     */     try {
/* 362 */       return Enum.valueOf(enumClazz, value.toUpperCase(Locale.ENGLISH));
/* 363 */     } catch (IllegalArgumentException unused) {
/*     */       
/*     */       try {
/* 366 */         return Enum.valueOf(enumClazz, value);
/* 367 */       } catch (IllegalArgumentException illegalArgumentException) {
/*     */         
/* 369 */         throw new XmlPullParserException("Unknown enum value \"" + value + "\" for enum class " + enumClazz, this.xpp, null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public boolean parseBool(String value) throws XmlPullParserException {
/* 374 */     if ("true".equals(value))
/* 375 */       return true; 
/* 376 */     if ("false".equals(value)) {
/* 377 */       return false;
/*     */     }
/* 379 */     throw new XmlPullParserException("boolean value must be 'true' or 'false'", this.xpp, null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int parseInt(String value) throws XmlPullParserException {
/*     */     try {
/* 385 */       return Integer.parseInt(value);
/* 386 */     } catch (NumberFormatException ex) {
/* 387 */       throw (XmlPullParserException)(new XmlPullParserException("Unable to parse integer", this.xpp, ex)).initCause(ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected float parseFloat(String value) throws XmlPullParserException {
/*     */     try {
/* 394 */       return Float.parseFloat(value);
/* 395 */     } catch (NumberFormatException ex) {
/* 396 */       throw (XmlPullParserException)(new XmlPullParserException("Unable to parse float", this.xpp, ex)).initCause(ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void missingAttribute(String attribute) throws XmlPullParserException {
/* 402 */     throw new XmlPullParserException("missing '" + attribute + "' on '" + this.xpp.getName() + "'", this.xpp, null);
/*     */   }
/*     */   
/*     */   protected void handleType(int type) {
/* 406 */     this.unusedAttributes.clear();
/* 407 */     switch (type) {
/*     */       case 2:
/* 409 */         this.unusedAttributes.set(0, this.xpp.getAttributeCount());
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void warnUnusedAttributes() {
/* 415 */     if (!this.unusedAttributes.isEmpty()) {
/* 416 */       String positionDescription = getPositionDescription();
/* 417 */       for (int i = -1; (i = this.unusedAttributes.nextSetBit(i + 1)) >= 0;) {
/* 418 */         getLogger().log(Level.WARNING, "Unused attribute ''{0}'' on ''{1}'' at {2}", new Object[] { this.xpp.getAttributeName(i), this.xpp.getName(), positionDescription });
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected Logger getLogger() {
/* 425 */     return Logger.getLogger(this.loggerName);
/*     */   }
/*     */   
/*     */   static class XPPF {
/*     */     private static final XmlPullParserFactory xppf;
/*     */     private static XmlPullParserException xppfex;
/*     */     
/*     */     static {
/* 433 */       XmlPullParserFactory f = null;
/*     */       try {
/* 435 */         f = XmlPullParserFactory.newInstance();
/* 436 */         f.setNamespaceAware(false);
/* 437 */         f.setValidating(false);
/* 438 */       } catch (XmlPullParserException ex) {
/* 439 */         Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, "Unable to construct XmlPullParserFactory", (Throwable)ex);
/*     */         
/* 441 */         xppfex = ex;
/*     */       } 
/* 443 */       xppf = f;
/*     */     }
/*     */     
/*     */     static XmlPullParser newPullParser() throws XmlPullParserException {
/* 447 */       if (xppf != null) {
/* 448 */         return xppf.newPullParser();
/*     */       }
/* 450 */       throw xppfex;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\tw\\utils\XMLParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */