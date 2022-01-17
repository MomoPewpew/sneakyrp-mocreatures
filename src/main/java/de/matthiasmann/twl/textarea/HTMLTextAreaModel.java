/*     */ package de.matthiasmann.twl.textarea;
/*     */ 
/*     */ import de.matthiasmann.twl.model.HasCallback;
/*     */ import de.matthiasmann.twl.utils.MultiStringReader;
/*     */ import de.matthiasmann.twl.utils.TextUtil;
/*     */ import de.matthiasmann.twl.utils.XMLParser;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.xmlpull.v1.XmlPullParser;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HTMLTextAreaModel
/*     */   extends HasCallback
/*     */   implements TextAreaModel
/*     */ {
/* 110 */   private final ArrayList<TextAreaModel.Element> elements = new ArrayList<TextAreaModel.Element>();
/* 111 */   private final ArrayList<String> styleSheetLinks = new ArrayList<String>();
/* 112 */   private final HashMap<String, TextAreaModel.Element> idMap = new HashMap<String, TextAreaModel.Element>(); private String title;
/* 113 */   private final ArrayList<Style> styleStack = new ArrayList<Style>();
/* 114 */   private final StringBuilder sb = new StringBuilder();
/* 115 */   private final int[] startLength = new int[2];
/*     */ 
/*     */   
/*     */   private TextAreaModel.ContainerElement curContainer;
/*     */ 
/*     */   
/*     */   public HTMLTextAreaModel() {}
/*     */ 
/*     */   
/*     */   public HTMLTextAreaModel(String html) {
/* 125 */     this();
/* 126 */     setHtml(html);
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
/*     */   public HTMLTextAreaModel(Reader r) throws IOException {
/* 139 */     this();
/* 140 */     parseXHTML(r);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHtml(String html) {
/*     */     MultiStringReader multiStringReader;
/* 150 */     if (isXHTML(html)) {
/* 151 */       Reader r = new StringReader(html);
/*     */     } else {
/* 153 */       multiStringReader = new MultiStringReader(new String[] { "<html><body>", html, "</body></html>" });
/*     */     } 
/* 155 */     parseXHTML((Reader)multiStringReader);
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
/*     */   public void readHTMLFromStream(Reader r) throws IOException {
/* 167 */     parseXHTML(r);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readHTMLFromURL(URL url) throws IOException {
/* 178 */     InputStream in = url.openStream();
/*     */     try {
/* 180 */       parseXHTML(new InputStreamReader(in, "UTF8"));
/*     */     } finally {
/*     */       try {
/* 183 */         in.close();
/* 184 */       } catch (IOException ex) {
/* 185 */         Logger.getLogger(HTMLTextAreaModel.class.getName()).log(Level.SEVERE, "Exception while closing InputStream", ex);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator<TextAreaModel.Element> iterator() {
/* 192 */     return this.elements.iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterable<String> getStyleSheetLinks() {
/* 200 */     return this.styleSheetLinks;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTitle() {
/* 208 */     return this.title;
/*     */   }
/*     */   
/*     */   public TextAreaModel.Element getElementById(String id) {
/* 212 */     return this.idMap.get(id);
/*     */   }
/*     */   
/*     */   public void domModified() {
/* 216 */     doCallback();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parseXHTML(Reader reader) {
/* 224 */     this.elements.clear();
/* 225 */     this.styleSheetLinks.clear();
/* 226 */     this.idMap.clear();
/* 227 */     this.title = null;
/*     */     
/*     */     try {
/* 230 */       XmlPullParser xpp = XMLParser.createParser();
/* 231 */       xpp.setInput(reader);
/* 232 */       xpp.defineEntityReplacementText("nbsp", " ");
/* 233 */       xpp.require(0, null, null);
/* 234 */       xpp.nextTag();
/* 235 */       xpp.require(2, null, "html");
/*     */       
/* 237 */       this.styleStack.clear();
/* 238 */       this.styleStack.add(new Style(null, null));
/* 239 */       this.curContainer = null;
/* 240 */       this.sb.setLength(0);
/*     */       
/* 242 */       while (xpp.nextTag() != 3) {
/* 243 */         xpp.require(2, null, null);
/* 244 */         String name = xpp.getName();
/* 245 */         if ("head".equals(name)) {
/* 246 */           parseHead(xpp); continue;
/* 247 */         }  if ("body".equals(name)) {
/* 248 */           pushStyle(xpp);
/* 249 */           TextAreaModel.BlockElement be = new TextAreaModel.BlockElement(getStyle());
/* 250 */           this.elements.add(be);
/* 251 */           parseContainer(xpp, be);
/*     */         } 
/*     */       } 
/*     */       
/* 255 */       parseMain(xpp);
/* 256 */       finishText();
/* 257 */     } catch (Throwable ex) {
/* 258 */       Logger.getLogger(HTMLTextAreaModel.class.getName()).log(Level.SEVERE, "Unable to parse XHTML document", ex);
/*     */     } finally {
/*     */       
/* 261 */       doCallback();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void parseContainer(XmlPullParser xpp, TextAreaModel.ContainerElement container) throws XmlPullParserException, IOException {
/* 266 */     TextAreaModel.ContainerElement prevContainer = this.curContainer;
/* 267 */     this.curContainer = container;
/* 268 */     pushStyle(null);
/* 269 */     parseMain(xpp);
/* 270 */     popStyle();
/* 271 */     this.curContainer = prevContainer;
/*     */   }
/*     */   
/*     */   private void parseMain(XmlPullParser xpp) throws XmlPullParserException, IOException {
/* 275 */     int level = 1;
/*     */     int type;
/* 277 */     while (level > 0 && (type = xpp.nextToken()) != 1) {
/* 278 */       String name; char[] buf; Style style; TextAreaModel.Element element; switch (type) {
/*     */         case 2:
/* 280 */           name = xpp.getName();
/* 281 */           if ("head".equals(name)) {
/* 282 */             parseHead(xpp);
/*     */             continue;
/*     */           } 
/* 285 */           level++;
/* 286 */           if ("br".equals(name)) {
/* 287 */             this.sb.append("\n");
/*     */             continue;
/*     */           } 
/* 290 */           finishText();
/* 291 */           style = pushStyle(xpp);
/*     */ 
/*     */           
/* 294 */           if ("img".equals(name)) {
/* 295 */             String src = TextUtil.notNull(xpp.getAttributeValue(null, "src"));
/* 296 */             String alt = xpp.getAttributeValue(null, "alt");
/* 297 */             element = new TextAreaModel.ImageElement(style, src, alt);
/* 298 */           } else if ("p".equals(name)) {
/* 299 */             TextAreaModel.ParagraphElement pe = new TextAreaModel.ParagraphElement(style);
/* 300 */             parseContainer(xpp, pe);
/* 301 */             element = pe;
/* 302 */             level--;
/* 303 */           } else if ("button".equals(name)) {
/* 304 */             String btnName = TextUtil.notNull(xpp.getAttributeValue(null, "name"));
/* 305 */             String btnParam = TextUtil.notNull(xpp.getAttributeValue(null, "value"));
/* 306 */             element = new TextAreaModel.WidgetElement(style, btnName, btnParam);
/* 307 */           } else if ("ul".equals(name)) {
/* 308 */             TextAreaModel.ContainerElement ce = new TextAreaModel.ContainerElement(style);
/* 309 */             parseContainer(xpp, ce);
/* 310 */             element = ce;
/* 311 */             level--;
/* 312 */           } else if ("ol".equals(name)) {
/* 313 */             element = parseOL(xpp, style);
/* 314 */             level--;
/* 315 */           } else if ("li".equals(name)) {
/* 316 */             TextAreaModel.ListElement le = new TextAreaModel.ListElement(style);
/* 317 */             parseContainer(xpp, le);
/* 318 */             element = le;
/* 319 */             level--;
/* 320 */           } else if ("div".equals(name) || isHeading(name)) {
/* 321 */             TextAreaModel.BlockElement be = new TextAreaModel.BlockElement(style);
/* 322 */             parseContainer(xpp, be);
/* 323 */             element = be;
/* 324 */             level--;
/* 325 */           } else if ("a".equals(name)) {
/* 326 */             String href = xpp.getAttributeValue(null, "href");
/* 327 */             if (href == null) {
/*     */               continue;
/*     */             }
/* 330 */             TextAreaModel.LinkElement le = new TextAreaModel.LinkElement(style, href);
/* 331 */             parseContainer(xpp, le);
/* 332 */             element = le;
/* 333 */             level--;
/* 334 */           } else if ("table".equals(name)) {
/* 335 */             element = parseTable(xpp, style);
/* 336 */             level--;
/*     */           } else {
/*     */             continue;
/*     */           } 
/*     */           
/* 341 */           this.curContainer.add(element);
/* 342 */           registerElement(element);
/*     */ 
/*     */         
/*     */         case 3:
/* 346 */           level--;
/* 347 */           name = xpp.getName();
/* 348 */           if ("br".equals(name)) {
/*     */             continue;
/*     */           }
/* 351 */           finishText();
/* 352 */           popStyle();
/*     */ 
/*     */         
/*     */         case 4:
/* 356 */           buf = xpp.getTextCharacters(this.startLength);
/* 357 */           if (this.startLength[1] > 0) {
/* 358 */             int pos = this.sb.length();
/* 359 */             this.sb.append(buf, this.startLength[0], this.startLength[1]);
/* 360 */             if (!isPre()) {
/* 361 */               removeBreaks(pos);
/*     */             }
/*     */           } 
/*     */ 
/*     */         
/*     */         case 6:
/* 367 */           this.sb.append(xpp.getText());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void parseHead(XmlPullParser xpp) throws XmlPullParserException, IOException {
/* 374 */     int level = 1;
/* 375 */     while (level > 0) {
/* 376 */       String name; switch (xpp.nextTag()) {
/*     */         case 2:
/* 378 */           level++;
/* 379 */           name = xpp.getName();
/* 380 */           if ("link".equals(name)) {
/* 381 */             String linkhref = xpp.getAttributeValue(null, "href");
/* 382 */             if ("stylesheet".equals(xpp.getAttributeValue(null, "rel")) && "text/css".equals(xpp.getAttributeValue(null, "type")) && linkhref != null)
/*     */             {
/*     */               
/* 385 */               this.styleSheetLinks.add(linkhref);
/*     */             }
/*     */           } 
/* 388 */           if ("title".equals(name)) {
/* 389 */             this.title = xpp.nextText();
/* 390 */             level--;
/*     */           } 
/*     */ 
/*     */         
/*     */         case 3:
/* 395 */           level--;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private TextAreaModel.TableElement parseTable(XmlPullParser xpp, Style tableStyle) throws XmlPullParserException, IOException {
/* 403 */     ArrayList<TextAreaModel.TableCellElement> cells = new ArrayList<TextAreaModel.TableCellElement>();
/* 404 */     ArrayList<Style> rowStyles = new ArrayList<Style>();
/* 405 */     int numColumns = 0;
/* 406 */     int cellSpacing = parseInt(xpp, "cellspacing", 0);
/* 407 */     int cellPadding = parseInt(xpp, "cellpadding", 0);
/*     */     while (true) {
/*     */       String name;
/* 410 */       switch (xpp.nextTag()) {
/*     */         case 2:
/* 412 */           pushStyle(xpp);
/* 413 */           name = xpp.getName();
/* 414 */           if ("td".equals(name) || "th".equals(name)) {
/* 415 */             int colspan = parseInt(xpp, "colspan", 1);
/* 416 */             TextAreaModel.TableCellElement cell = new TextAreaModel.TableCellElement(getStyle(), colspan);
/* 417 */             parseContainer(xpp, cell);
/* 418 */             registerElement(cell);
/*     */             
/* 420 */             cells.add(cell);
/* 421 */             for (int col = 1; col < colspan; col++) {
/* 422 */               cells.add(null);
/*     */             }
/*     */           } 
/* 425 */           if ("tr".equals(name)) {
/* 426 */             rowStyles.add(getStyle());
/*     */           }
/*     */ 
/*     */         
/*     */         case 3:
/* 431 */           popStyle();
/* 432 */           name = xpp.getName();
/* 433 */           if ("tr".equals(name) && 
/* 434 */             numColumns == 0) {
/* 435 */             numColumns = cells.size();
/*     */           }
/*     */           
/* 438 */           if ("table".equals(name)) {
/* 439 */             TextAreaModel.TableElement tableElement = new TextAreaModel.TableElement(tableStyle, numColumns, rowStyles.size(), cellSpacing, cellPadding);
/*     */             
/* 441 */             for (int row = 0, idx = 0; row < rowStyles.size(); row++) {
/* 442 */               tableElement.setRowStyle(row, rowStyles.get(row));
/* 443 */               for (int col = 0; col < numColumns && idx < cells.size(); col++, idx++) {
/* 444 */                 TextAreaModel.TableCellElement cell = cells.get(idx);
/* 445 */                 tableElement.setCell(row, col, cell);
/*     */               } 
/*     */             } 
/* 448 */             return tableElement;
/*     */           } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private TextAreaModel.OrderedListElement parseOL(XmlPullParser xpp, Style olStyle) throws XmlPullParserException, IOException {
/* 456 */     int start = parseInt(xpp, "start", 1);
/* 457 */     TextAreaModel.OrderedListElement ole = new TextAreaModel.OrderedListElement(olStyle, start);
/* 458 */     registerElement(ole); while (true) {
/*     */       String name;
/* 460 */       switch (xpp.nextTag()) {
/*     */         case 2:
/* 462 */           pushStyle(xpp);
/* 463 */           name = xpp.getName();
/* 464 */           if ("li".equals(name)) {
/* 465 */             TextAreaModel.ContainerElement ce = new TextAreaModel.ContainerElement(getStyle());
/* 466 */             parseContainer(xpp, ce);
/* 467 */             registerElement(ce);
/* 468 */             ole.add(ce);
/*     */           } 
/*     */ 
/*     */         
/*     */         case 3:
/* 473 */           popStyle();
/* 474 */           name = xpp.getName();
/* 475 */           if ("ol".equals(name)) {
/* 476 */             return ole;
/*     */           }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void registerElement(TextAreaModel.Element element) {
/* 485 */     StyleSheetKey styleSheetKey = element.getStyle().getStyleSheetKey();
/* 486 */     if (styleSheetKey != null) {
/* 487 */       String id = styleSheetKey.getId();
/* 488 */       if (id != null) {
/* 489 */         this.idMap.put(id, element);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int parseInt(XmlPullParser xpp, String attribute, int defaultValue) {
/* 495 */     String value = xpp.getAttributeValue(null, attribute);
/* 496 */     if (value != null) {
/*     */       try {
/* 498 */         return Integer.parseInt(value);
/* 499 */       } catch (IllegalArgumentException ignore) {}
/*     */     }
/*     */     
/* 502 */     return defaultValue;
/*     */   }
/*     */   
/*     */   private static boolean isXHTML(String doc) {
/* 506 */     if (doc.length() > 5 && doc.charAt(0) == '<') {
/* 507 */       return (doc.startsWith("<?xml") || doc.startsWith("<!DOCTYPE") || doc.startsWith("<html>"));
/*     */     }
/* 509 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isHeading(String name) {
/* 513 */     return (name.length() == 2 && name.charAt(0) == 'h' && name.charAt(1) >= '0' && name.charAt(1) <= '6');
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isPre() {
/* 518 */     return ((Boolean)getStyle().<Boolean>get(StyleAttribute.PREFORMATTED, null)).booleanValue();
/*     */   }
/*     */   
/*     */   private Style getStyle() {
/* 522 */     return this.styleStack.get(this.styleStack.size() - 1);
/*     */   }
/*     */   
/*     */   private Style pushStyle(XmlPullParser xpp) {
/* 526 */     Style newStyle, parent = getStyle();
/* 527 */     StyleSheetKey key = null;
/* 528 */     String style = null;
/*     */     
/* 530 */     if (xpp != null) {
/* 531 */       String className = xpp.getAttributeValue(null, "class");
/* 532 */       String element = xpp.getName();
/* 533 */       String id = xpp.getAttributeValue(null, "id");
/* 534 */       key = new StyleSheetKey(element, className, id);
/* 535 */       style = xpp.getAttributeValue(null, "style");
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 540 */     if (style != null) {
/* 541 */       newStyle = new CSSStyle(parent, key, style);
/*     */     } else {
/* 543 */       newStyle = new Style(parent, key);
/*     */     } 
/*     */     
/* 546 */     if (xpp != null && "pre".equals(xpp.getName())) {
/* 547 */       newStyle.put(StyleAttribute.PREFORMATTED, Boolean.TRUE);
/*     */     }
/*     */     
/* 550 */     this.styleStack.add(newStyle);
/* 551 */     return newStyle;
/*     */   }
/*     */   
/*     */   private void popStyle() {
/* 555 */     int stackSize = this.styleStack.size();
/* 556 */     if (stackSize > 1) {
/* 557 */       this.styleStack.remove(stackSize - 1);
/*     */     }
/*     */   }
/*     */   
/*     */   private void finishText() {
/* 562 */     if (this.sb.length() > 0) {
/* 563 */       Style style = getStyle();
/* 564 */       TextAreaModel.TextElement e = new TextAreaModel.TextElement(style, this.sb.toString());
/* 565 */       registerElement(e);
/* 566 */       this.curContainer.add(e);
/* 567 */       this.sb.setLength(0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void removeBreaks(int pos) {
/* 573 */     for (int idx = this.sb.length(); idx-- > pos; ) {
/* 574 */       char ch = this.sb.charAt(idx);
/* 575 */       if (Character.isWhitespace(ch) || Character.isISOControl(ch)) {
/* 576 */         this.sb.setCharAt(idx, ' ');
/*     */       }
/*     */     } 
/*     */     
/* 580 */     if (pos > 0) {
/* 581 */       pos--;
/*     */     }
/* 583 */     boolean wasSpace = false;
/* 584 */     for (int i = this.sb.length(); i-- > pos; ) {
/* 585 */       boolean isSpace = (this.sb.charAt(i) == ' ');
/* 586 */       if (isSpace && wasSpace) {
/* 587 */         this.sb.deleteCharAt(i);
/*     */       }
/* 589 */       wasSpace = isSpace;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\textarea\HTMLTextAreaModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */