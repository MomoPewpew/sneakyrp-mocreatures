/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.utils.XMLParser;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.net.URL;
/*     */ import java.util.Arrays;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.xmlpull.v1.XmlPullParserException;
/*     */ import org.xmlpull.v1.XmlPullParserFactory;
/*     */ import org.xmlpull.v1.XmlSerializer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class InputMap
/*     */ {
/*  53 */   private static final InputMap EMPTY_MAP = new InputMap(new KeyStroke[0]);
/*     */   
/*     */   private final KeyStroke[] keyStrokes;
/*     */   
/*     */   private InputMap(KeyStroke[] keyStrokes) {
/*  58 */     this.keyStrokes = keyStrokes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String mapEvent(Event event) {
/*  67 */     if (event.isKeyEvent()) {
/*  68 */       int mappedEventModifiers = KeyStroke.convertModifier(event);
/*  69 */       for (KeyStroke ks : this.keyStrokes) {
/*  70 */         if (ks.match(event, mappedEventModifiers)) {
/*  71 */           return ks.getAction();
/*     */         }
/*     */       } 
/*     */     } 
/*  75 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputMap addKeyStrokes(LinkedHashSet<KeyStroke> newKeyStrokes) {
/*  86 */     int size = newKeyStrokes.size();
/*  87 */     if (size == 0) {
/*  88 */       return this;
/*     */     }
/*     */     
/*  91 */     KeyStroke[] combined = new KeyStroke[this.keyStrokes.length + size];
/*  92 */     newKeyStrokes.toArray((Object[])combined);
/*  93 */     for (KeyStroke ks : this.keyStrokes) {
/*  94 */       if (!newKeyStrokes.contains(ks)) {
/*  95 */         combined[size++] = ks;
/*     */       }
/*     */     } 
/*     */     
/*  99 */     return new InputMap(shrink(combined, size));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputMap addKeyStrokes(InputMap map) {
/* 110 */     if (map == this || map.keyStrokes.length == 0) {
/* 111 */       return this;
/*     */     }
/* 113 */     if (this.keyStrokes.length == 0) {
/* 114 */       return map;
/*     */     }
/* 116 */     return addKeyStrokes(new LinkedHashSet<KeyStroke>(Arrays.asList(map.keyStrokes)));
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
/*     */   public InputMap addKeyStroke(KeyStroke keyStroke) {
/* 128 */     LinkedHashSet<KeyStroke> newKeyStrokes = new LinkedHashSet<KeyStroke>(1, 1.0F);
/* 129 */     newKeyStrokes.add(keyStroke);
/* 130 */     return addKeyStrokes(newKeyStrokes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputMap removeKeyStrokes(Set<KeyStroke> keyStrokes) {
/* 140 */     if (keyStrokes.isEmpty()) {
/* 141 */       return this;
/*     */     }
/*     */     
/* 144 */     int size = 0;
/* 145 */     KeyStroke[] result = new KeyStroke[this.keyStrokes.length];
/* 146 */     for (KeyStroke ks : this.keyStrokes) {
/* 147 */       if (!keyStrokes.contains(ks)) {
/* 148 */         result[size++] = ks;
/*     */       }
/*     */     } 
/*     */     
/* 152 */     return new InputMap(shrink(result, size));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public KeyStroke[] getKeyStrokes() {
/* 160 */     return (KeyStroke[])this.keyStrokes.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static InputMap empty() {
/* 168 */     return EMPTY_MAP;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static InputMap parse(URL url) throws IOException {
/*     */     try {
/* 180 */       XMLParser xmlp = new XMLParser(url);
/*     */       try {
/* 182 */         xmlp.require(0, null, null);
/* 183 */         xmlp.nextTag();
/* 184 */         xmlp.require(2, null, "inputMapDef");
/* 185 */         xmlp.nextTag();
/* 186 */         LinkedHashSet<KeyStroke> keyStrokes = parseBody(xmlp);
/* 187 */         xmlp.require(3, null, "inputMapDef");
/* 188 */         return new InputMap((KeyStroke[])keyStrokes.toArray((Object[])new KeyStroke[keyStrokes.size()]));
/*     */       } finally {
/* 190 */         xmlp.close();
/*     */       } 
/* 192 */     } catch (XmlPullParserException ex) {
/* 193 */       throw (IOException)(new IOException("Can't parse XML")).initCause(ex);
/*     */     } 
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
/*     */   public void writeXML(OutputStream os) throws IOException {
/*     */     try {
/* 207 */       XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
/* 208 */       XmlSerializer serializer = factory.newSerializer();
/* 209 */       serializer.setOutput(os, "UTF8");
/* 210 */       serializer.startDocument("UTF8", Boolean.TRUE);
/* 211 */       serializer.text("\n");
/* 212 */       serializer.startTag(null, "inputMapDef");
/* 213 */       for (KeyStroke ks : this.keyStrokes) {
/* 214 */         serializer.text("\n    ");
/* 215 */         serializer.startTag(null, "action");
/* 216 */         serializer.attribute(null, "name", ks.getAction());
/* 217 */         serializer.text(ks.getStroke());
/* 218 */         serializer.endTag(null, "action");
/*     */       } 
/* 220 */       serializer.text("\n");
/* 221 */       serializer.endTag(null, "inputMapDef");
/* 222 */       serializer.endDocument();
/* 223 */     } catch (XmlPullParserException ex) {
/* 224 */       throw (IOException)(new IOException("Can't generate XML")).initCause(ex);
/*     */     } 
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
/*     */   public static LinkedHashSet<KeyStroke> parseBody(XMLParser xmlp) throws XmlPullParserException, IOException {
/* 238 */     LinkedHashSet<KeyStroke> newStrokes = new LinkedHashSet<KeyStroke>();
/* 239 */     while (!xmlp.isEndTag()) {
/* 240 */       xmlp.require(2, null, "action");
/* 241 */       String name = xmlp.getAttributeNotNull("name");
/* 242 */       String key = xmlp.nextText();
/*     */       try {
/* 244 */         KeyStroke ks = KeyStroke.parse(key, name);
/* 245 */         if (!newStrokes.add(ks)) {
/* 246 */           Logger.getLogger(InputMap.class.getName()).log(Level.WARNING, "Duplicate key stroke: {0}", ks.getStroke());
/*     */         }
/* 248 */       } catch (IllegalArgumentException ex) {
/* 249 */         throw xmlp.error("can't parse Keystroke", ex);
/*     */       } 
/* 251 */       xmlp.require(3, null, "action");
/* 252 */       xmlp.nextTag();
/*     */     } 
/* 254 */     return newStrokes;
/*     */   }
/*     */   
/*     */   private static KeyStroke[] shrink(KeyStroke[] keyStrokes, int size) {
/* 258 */     if (size != keyStrokes.length) {
/* 259 */       KeyStroke[] tmp = new KeyStroke[size];
/* 260 */       System.arraycopy(keyStrokes, 0, tmp, 0, size);
/* 261 */       keyStrokes = tmp;
/*     */     } 
/* 263 */     return keyStrokes;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\InputMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */