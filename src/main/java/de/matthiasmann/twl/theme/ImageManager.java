/*     */ package de.matthiasmann.twl.theme;
/*     */ 
/*     */ import de.matthiasmann.twl.Border;
/*     */ import de.matthiasmann.twl.Color;
/*     */ import de.matthiasmann.twl.renderer.Gradient;
/*     */ import de.matthiasmann.twl.renderer.Image;
/*     */ import de.matthiasmann.twl.renderer.MouseCursor;
/*     */ import de.matthiasmann.twl.renderer.Renderer;
/*     */ import de.matthiasmann.twl.renderer.Texture;
/*     */ import de.matthiasmann.twl.utils.StateExpression;
/*     */ import de.matthiasmann.twl.utils.StateSelect;
/*     */ import de.matthiasmann.twl.utils.TextUtil;
/*     */ import de.matthiasmann.twl.utils.XMLParser;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
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
/*     */ class ImageManager
/*     */ {
/*     */   private final ParameterMapImpl constants;
/*     */   private final Renderer renderer;
/*     */   private final TreeMap<String, Image> images;
/*     */   private final TreeMap<String, MouseCursor> cursors;
/*     */   private Texture currentTexture;
/*  66 */   static final EmptyImage NONE = new EmptyImage(0, 0);
/*  67 */   static final MouseCursor NOCURSOR = new MouseCursor() {  }
/*     */   ;
/*     */   ImageManager(ParameterMapImpl constants, Renderer renderer) {
/*  70 */     this.constants = constants;
/*  71 */     this.renderer = renderer;
/*  72 */     this.images = new TreeMap<String, Image>();
/*  73 */     this.cursors = new TreeMap<String, MouseCursor>();
/*     */     
/*  75 */     this.images.put("none", NONE);
/*  76 */     this.cursors.put("os-default", NOCURSOR);
/*     */   }
/*     */   
/*     */   Image getImage(String name) {
/*  80 */     return this.images.get(name);
/*     */   }
/*     */   
/*     */   Image getReferencedImage(XMLParser xmlp) throws XmlPullParserException {
/*  84 */     String ref = xmlp.getAttributeNotNull("ref");
/*  85 */     return getReferencedImage(xmlp, ref);
/*     */   }
/*     */   
/*     */   Image getReferencedImage(XMLParser xmlp, String ref) throws XmlPullParserException {
/*  89 */     if (ref.endsWith(".*")) {
/*  90 */       throw xmlp.error("wildcard mapping not allowed");
/*     */     }
/*  92 */     Image img = this.images.get(ref);
/*  93 */     if (img == null) {
/*  94 */       throw xmlp.error("referenced image \"" + ref + "\" not found");
/*     */     }
/*  96 */     return img;
/*     */   }
/*     */   
/*     */   MouseCursor getReferencedCursor(XMLParser xmlp, String ref) throws XmlPullParserException {
/* 100 */     MouseCursor cursor = this.cursors.get(ref);
/* 101 */     if (cursor == null) {
/* 102 */       throw xmlp.error("referenced cursor \"" + ref + "\" not found");
/*     */     }
/* 104 */     return unwrapCursor(cursor);
/*     */   }
/*     */   
/*     */   Map<String, Image> getImages(String ref, String name) {
/* 108 */     return ParserUtil.resolve(this.images, ref, name, null);
/*     */   }
/*     */   
/*     */   public MouseCursor getCursor(String name) {
/* 112 */     return unwrapCursor(this.cursors.get(name));
/*     */   }
/*     */   
/*     */   Map<String, MouseCursor> getCursors(String ref, String name) {
/* 116 */     return ParserUtil.resolve(this.cursors, ref, name, NOCURSOR);
/*     */   }
/*     */   
/*     */   void parseImages(XMLParser xmlp, URL baseUrl) throws XmlPullParserException, IOException {
/* 120 */     xmlp.require(2, null, null);
/*     */     
/* 122 */     Texture texture = null;
/* 123 */     String fileName = xmlp.getAttributeValue(null, "file");
/* 124 */     if (fileName != null) {
/* 125 */       String fmt = xmlp.getAttributeValue(null, "format");
/* 126 */       String filter = xmlp.getAttributeValue(null, "filter");
/*     */       
/* 128 */       xmlp.getAttributeValue(null, "comment");
/*     */       
/*     */       try {
/* 131 */         texture = this.renderer.loadTexture(new URL(baseUrl, fileName), fmt, filter);
/* 132 */         if (texture == null) {
/* 133 */           throw new NullPointerException("loadTexture returned null");
/*     */         }
/* 135 */       } catch (IOException ex) {
/* 136 */         throw xmlp.error("Unable to load image file: " + fileName, ex);
/*     */       } 
/*     */     } 
/*     */     
/* 140 */     this.currentTexture = texture;
/*     */     
/*     */     try {
/* 143 */       xmlp.nextTag();
/* 144 */       while (!xmlp.isEndTag()) {
/* 145 */         String name = xmlp.getAttributeNotNull("name");
/* 146 */         checkImageName(name, xmlp);
/* 147 */         String tagName = xmlp.getName();
/* 148 */         if ("cursor".equals(xmlp.getName())) {
/* 149 */           parseCursor(xmlp, name);
/*     */         } else {
/* 151 */           Image image = parseImage(xmlp, tagName);
/* 152 */           this.images.put(name, image);
/*     */         } 
/* 154 */         xmlp.require(3, null, tagName);
/* 155 */         xmlp.nextTag();
/*     */       } 
/*     */     } finally {
/* 158 */       this.currentTexture = null;
/* 159 */       if (texture != null) {
/* 160 */         texture.themeLoadingDone();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private MouseCursor unwrapCursor(MouseCursor cursor) {
/* 166 */     return (cursor == NOCURSOR) ? null : cursor;
/*     */   }
/*     */   
/*     */   private void checkImageName(String name, XMLParser xmlp) throws XmlPullParserException, XmlPullParserException {
/* 170 */     ParserUtil.checkNameNotEmpty(name, xmlp);
/* 171 */     if (this.images.containsKey(name)) {
/* 172 */       throw xmlp.error("image \"" + name + "\" already defined");
/*     */     }
/*     */   }
/*     */   
/*     */   private Border getBorder(Image image, Border border) {
/* 177 */     if (border == null && image instanceof HasBorder) {
/* 178 */       border = ((HasBorder)image).getBorder();
/*     */     }
/* 180 */     return border;
/*     */   }
/*     */   private void parseCursor(XMLParser xmlp, String name) throws IOException, XmlPullParserException {
/*     */     MouseCursor cursor;
/* 184 */     String ref = xmlp.getAttributeValue(null, "ref");
/*     */     
/* 186 */     if (ref != null) {
/* 187 */       cursor = this.cursors.get(ref);
/* 188 */       if (cursor == null) {
/* 189 */         throw xmlp.error("referenced cursor \"" + ref + "\" not found");
/*     */       }
/*     */     } else {
/* 192 */       ImageParams imageParams = new ImageParams();
/* 193 */       parseRectFromAttribute(xmlp, imageParams);
/* 194 */       int hotSpotX = xmlp.parseIntFromAttribute("hotSpotX");
/* 195 */       int hotSpotY = xmlp.parseIntFromAttribute("hotSpotY");
/* 196 */       String imageRefStr = xmlp.getAttributeValue(null, "imageRef");
/*     */       
/* 198 */       Image imageRef = null;
/* 199 */       if (imageRefStr != null) {
/* 200 */         imageRef = getReferencedImage(xmlp, imageRefStr);
/*     */       }
/* 202 */       cursor = this.currentTexture.createCursor(imageParams.x, imageParams.y, imageParams.w, imageParams.h, hotSpotX, hotSpotY, imageRef);
/* 203 */       if (cursor == null) {
/* 204 */         cursor = NOCURSOR;
/*     */       }
/*     */     } 
/* 207 */     this.cursors.put(name, cursor);
/* 208 */     xmlp.nextTag();
/*     */   }
/*     */   
/*     */   private Image parseImage(XMLParser xmlp, String tagName) throws XmlPullParserException, IOException {
/* 212 */     ImageParams params = new ImageParams();
/* 213 */     params.condition = ParserUtil.parseCondition(xmlp);
/* 214 */     return parseImageNoCond(xmlp, tagName, params);
/*     */   }
/*     */   
/*     */   private Image parseImageNoCond(XMLParser xmlp, String tagName, ImageParams params) throws XmlPullParserException, IOException {
/* 218 */     parseStdAttributes(xmlp, params);
/* 219 */     Image image = parseImageDelegate(xmlp, tagName, params);
/* 220 */     return adjustImage(image, params);
/*     */   }
/*     */   
/*     */   private Image adjustImage(Image image, ImageParams params) {
/* 224 */     Border border = getBorder(image, params.border);
/* 225 */     if (params.tintColor != null && !Color.WHITE.equals(params.tintColor)) {
/* 226 */       image = image.createTintedVersion(params.tintColor);
/*     */     }
/* 228 */     if (params.repeatX || params.repeatY) {
/* 229 */       image = new RepeatImage(image, border, params.repeatX, params.repeatY);
/*     */     }
/* 231 */     Border imgBorder = getBorder(image, null);
/* 232 */     if ((border != null && border != imgBorder) || params.inset != null || params.center || params.condition != null || params.sizeOverwriteH >= 0 || params.sizeOverwriteV >= 0)
/*     */     {
/*     */       
/* 235 */       image = new ImageAdjustments(image, border, params.inset, params.sizeOverwriteH, params.sizeOverwriteV, params.center, params.condition);
/*     */     }
/*     */ 
/*     */     
/* 239 */     return image;
/*     */   }
/*     */   
/*     */   private Image parseImageDelegate(XMLParser xmlp, String tagName, ImageParams params) throws XmlPullParserException, IOException {
/* 243 */     if ("area".equals(tagName))
/* 244 */       return parseArea(xmlp, params); 
/* 245 */     if ("alias".equals(tagName))
/* 246 */       return parseAlias(xmlp); 
/* 247 */     if ("composed".equals(tagName))
/* 248 */       return parseComposed(xmlp, params); 
/* 249 */     if ("select".equals(tagName))
/* 250 */       return parseStateSelect(xmlp, params); 
/* 251 */     if ("grid".equals(tagName))
/* 252 */       return parseGrid(xmlp, params); 
/* 253 */     if ("animation".equals(tagName))
/* 254 */       return parseAnimation(xmlp, params); 
/* 255 */     if ("gradient".equals(tagName)) {
/* 256 */       return parseGradient(xmlp, params);
/*     */     }
/* 258 */     throw xmlp.error("Unexpected '" + tagName + "'");
/*     */   }
/*     */ 
/*     */   
/*     */   private Image parseComposed(XMLParser xmlp, ImageParams params) throws IOException, XmlPullParserException {
/* 263 */     ArrayList<Image> layers = new ArrayList<Image>();
/* 264 */     xmlp.nextTag();
/* 265 */     while (!xmlp.isEndTag()) {
/* 266 */       xmlp.require(2, null, null);
/* 267 */       String tagName = xmlp.getName();
/* 268 */       Image image = parseImage(xmlp, tagName);
/* 269 */       layers.add(image);
/* 270 */       params.border = getBorder(image, params.border);
/* 271 */       xmlp.require(3, null, tagName);
/* 272 */       xmlp.nextTag();
/*     */     } 
/* 274 */     switch (layers.size()) {
/*     */       case 0:
/* 276 */         return NONE;
/*     */       case 1:
/* 278 */         return layers.get(0);
/*     */     } 
/* 280 */     return new ComposedImage(layers.<Image>toArray(new Image[layers.size()]), params.border);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Image parseStateSelect(XMLParser xmlp, ImageParams params) throws IOException, XmlPullParserException {
/* 287 */     ArrayList<Image> stateImages = new ArrayList<Image>();
/* 288 */     ArrayList<StateExpression> conditions = new ArrayList<StateExpression>();
/* 289 */     xmlp.nextTag();
/* 290 */     boolean last = false;
/* 291 */     while (!last && !xmlp.isEndTag()) {
/* 292 */       xmlp.require(2, null, null);
/* 293 */       StateExpression cond = ParserUtil.parseCondition(xmlp);
/* 294 */       String tagName = xmlp.getName();
/* 295 */       Image image1 = parseImageNoCond(xmlp, tagName, new ImageParams());
/* 296 */       params.border = getBorder(image1, params.border);
/* 297 */       xmlp.require(3, null, tagName);
/* 298 */       xmlp.nextTag();
/* 299 */       last = (cond == null);
/*     */       
/* 301 */       if (image1 instanceof ImageAdjustments) {
/* 302 */         ImageAdjustments ia = (ImageAdjustments)image1;
/* 303 */         if (ia.isSimple()) {
/* 304 */           cond = and(cond, ia.condition);
/* 305 */           image1 = ia.image;
/*     */         } 
/*     */       } 
/*     */       
/* 309 */       if (StateSelect.isUseOptimizer() && image1 instanceof StateSelectImage) {
/* 310 */         inlineSelect((StateSelectImage)image1, cond, stateImages, conditions); continue;
/*     */       } 
/* 312 */       stateImages.add(image1);
/* 313 */       if (cond != null) {
/* 314 */         conditions.add(cond);
/*     */       }
/*     */     } 
/*     */     
/* 318 */     if (conditions.size() < 1) {
/* 319 */       throw xmlp.error("state select image needs atleast 1 condition");
/*     */     }
/* 321 */     StateSelect select = new StateSelect(conditions);
/* 322 */     Image image = new StateSelectImage(select, params.border, stateImages.<Image>toArray(new Image[stateImages.size()]));
/* 323 */     return image;
/*     */   }
/*     */   
/*     */   private static void inlineSelect(StateSelectImage src, StateExpression cond, ArrayList<Image> stateImages, ArrayList<StateExpression> conditions) {
/* 327 */     int n = src.images.length;
/* 328 */     int m = src.select.getNumExpressions();
/* 329 */     for (int i = 0; i < n; i++) {
/* 330 */       StateExpression imgCond = (i < m) ? src.select.getExpression(i) : null;
/* 331 */       imgCond = and(imgCond, cond);
/* 332 */       stateImages.add(src.images[i]);
/* 333 */       if (imgCond != null) {
/* 334 */         conditions.add(imgCond);
/*     */       }
/*     */     } 
/* 337 */     if (n == m && cond != null) {
/*     */ 
/*     */ 
/*     */       
/* 341 */       stateImages.add(NONE);
/* 342 */       conditions.add(cond);
/*     */     } 
/*     */   }
/*     */   private static StateExpression and(StateExpression imgCond, StateExpression cond) {
/*     */     StateExpression.Logic logic;
/* 347 */     if (imgCond == null) {
/* 348 */       imgCond = cond;
/* 349 */     } else if (cond != null) {
/* 350 */       logic = new StateExpression.Logic('+', new StateExpression[] { imgCond, cond });
/*     */     } 
/* 352 */     return (StateExpression)logic;
/*     */   }
/*     */   private Image parseArea(XMLParser xmlp, ImageParams params) throws IOException, XmlPullParserException {
/*     */     Image image;
/* 356 */     parseRectFromAttribute(xmlp, params);
/* 357 */     parseRotationFromAttribute(xmlp, params);
/* 358 */     boolean tiled = xmlp.parseBoolFromAttribute("tiled", false);
/* 359 */     int[] splitx = parseSplit2(xmlp, "splitx", Math.abs(params.w));
/* 360 */     int[] splity = parseSplit2(xmlp, "splity", Math.abs(params.h));
/*     */     
/* 362 */     if (splitx != null || splity != null) {
/* 363 */       boolean noCenter = xmlp.parseBoolFromAttribute("nocenter", false);
/* 364 */       int columns = (splitx != null) ? 3 : 1;
/* 365 */       int rows = (splity != null) ? 3 : 1;
/* 366 */       Image[] imageParts = new Image[columns * rows];
/* 367 */       for (int r = 0; r < rows; r++) {
/*     */         int imgY; int imgH;
/* 369 */         if (splity != null) {
/* 370 */           imgY = params.y + splity[r];
/* 371 */           imgH = (splity[r + 1] - splity[r]) * Integer.signum(params.h);
/*     */         } else {
/* 373 */           imgY = params.y;
/* 374 */           imgH = params.h;
/*     */         } 
/* 376 */         for (int c = 0; c < columns; c++) {
/*     */           int imgX, imgW; Image img; int idx;
/* 378 */           if (splitx != null) {
/* 379 */             imgX = params.x + splitx[c];
/* 380 */             imgW = (splitx[c + 1] - splitx[c]) * Integer.signum(params.w);
/*     */           } else {
/* 382 */             imgX = params.x;
/* 383 */             imgW = params.w;
/*     */           } 
/*     */           
/* 386 */           boolean isCenter = (r == rows / 2 && c == columns / 2);
/*     */           
/* 388 */           if (noCenter && isCenter) {
/* 389 */             img = new EmptyImage(imgW, imgH);
/*     */           } else {
/* 391 */             img = createImage(xmlp, imgX, imgY, imgW, imgH, params.tintColor, isCenter & tiled, params.rot);
/*     */           } 
/*     */           
/* 394 */           switch (params.rot) {
/*     */             default:
/* 396 */               idx = r * columns + c;
/*     */               break;
/*     */             case CLOCKWISE_90:
/* 399 */               idx = c * rows + rows - 1 - r;
/*     */               break;
/*     */             case CLOCKWISE_180:
/* 402 */               idx = (rows - 1 - r) * columns + columns - 1 - c;
/*     */               break;
/*     */             case CLOCKWISE_270:
/* 405 */               idx = (columns - 1 - c) * rows + r;
/*     */               break;
/*     */           } 
/*     */           
/* 409 */           imageParts[idx] = img;
/*     */         } 
/*     */       } 
/* 412 */       switch (params.rot) {
/*     */         case CLOCKWISE_90:
/*     */         case CLOCKWISE_270:
/* 415 */           image = new GridImage(imageParts, (splity != null) ? SPLIT_WEIGHTS_3 : SPLIT_WEIGHTS_1, (splitx != null) ? SPLIT_WEIGHTS_3 : SPLIT_WEIGHTS_1, params.border);
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         default:
/* 421 */           image = new GridImage(imageParts, (splitx != null) ? SPLIT_WEIGHTS_3 : SPLIT_WEIGHTS_1, (splity != null) ? SPLIT_WEIGHTS_3 : SPLIT_WEIGHTS_1, params.border);
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */     
/*     */     } else {
/* 428 */       image = createImage(xmlp, params.x, params.y, params.w, params.h, params.tintColor, tiled, params.rot);
/*     */     } 
/* 430 */     xmlp.nextTag();
/* 431 */     params.tintColor = null;
/* 432 */     if (tiled) {
/* 433 */       params.repeatX = false;
/* 434 */       params.repeatY = false;
/*     */     } 
/* 436 */     return image;
/*     */   }
/*     */   
/*     */   private Image parseAlias(XMLParser xmlp) throws XmlPullParserException, XmlPullParserException, IOException {
/* 440 */     Image image = getReferencedImage(xmlp);
/* 441 */     xmlp.nextTag();
/* 442 */     return image;
/*     */   }
/*     */   
/*     */   private static int[] parseSplit2(XMLParser xmlp, String attribName, int size) throws XmlPullParserException {
/* 446 */     String splitStr = xmlp.getAttributeValue(null, attribName);
/* 447 */     if (splitStr != null) {
/* 448 */       int comma = splitStr.indexOf(',');
/* 449 */       if (comma < 0) {
/* 450 */         throw xmlp.error(attribName + " requires 2 values");
/*     */       }
/*     */       try {
/* 453 */         int[] result = new int[4];
/* 454 */         for (int i = 0, start = 0; i < 2; i++) {
/* 455 */           String part = TextUtil.trim(splitStr, start, comma);
/* 456 */           if (part.length() == 0) {
/* 457 */             throw new NumberFormatException("empty string");
/*     */           }
/* 459 */           int off = 0;
/* 460 */           int sign = 1;
/* 461 */           switch (part.charAt(0)) {
/*     */             case 'B':
/*     */             case 'R':
/*     */             case 'b':
/*     */             case 'r':
/* 466 */               off = size;
/* 467 */               sign = -1;
/*     */             
/*     */             case 'L':
/*     */             case 'T':
/*     */             case 'l':
/*     */             case 't':
/* 473 */               part = TextUtil.trim(part, 1);
/*     */               break;
/*     */           } 
/* 476 */           int value = Integer.parseInt(part);
/* 477 */           result[i + 1] = Math.max(0, Math.min(size, off + sign * value));
/*     */           
/* 479 */           start = comma + 1;
/* 480 */           comma = splitStr.length();
/*     */         } 
/* 482 */         if (result[1] > result[2]) {
/* 483 */           int tmp = result[1];
/* 484 */           result[1] = result[2];
/* 485 */           result[2] = tmp;
/*     */         } 
/* 487 */         result[3] = size;
/* 488 */         return result;
/* 489 */       } catch (NumberFormatException ex) {
/* 490 */         throw xmlp.error("Unable to parse " + attribName + ": \"" + splitStr + "\"", ex);
/*     */       } 
/*     */     } 
/* 493 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void parseSubImages(XMLParser xmlp, Image[] textures) throws XmlPullParserException, IOException {
/* 498 */     int idx = 0;
/* 499 */     while (xmlp.isStartTag()) {
/* 500 */       if (idx == textures.length) {
/* 501 */         throw xmlp.error("Too many sub images");
/*     */       }
/* 503 */       String tagName = xmlp.getName();
/* 504 */       textures[idx++] = parseImage(xmlp, tagName);
/* 505 */       xmlp.require(3, null, tagName);
/* 506 */       xmlp.nextTag();
/*     */     } 
/* 508 */     if (idx != textures.length) {
/* 509 */       throw xmlp.error("Not enough sub images");
/*     */     }
/*     */   }
/*     */   
/*     */   private Image parseGrid(XMLParser xmlp, ImageParams params) throws IOException, XmlPullParserException {
/*     */     try {
/* 515 */       int[] weightsX = ParserUtil.parseIntArrayFromAttribute(xmlp, "weightsX");
/* 516 */       int[] weightsY = ParserUtil.parseIntArrayFromAttribute(xmlp, "weightsY");
/* 517 */       Image[] textures = new Image[weightsX.length * weightsY.length];
/* 518 */       xmlp.nextTag();
/* 519 */       parseSubImages(xmlp, textures);
/* 520 */       Image image = new GridImage(textures, weightsX, weightsY, params.border);
/* 521 */       return image;
/* 522 */     } catch (IllegalArgumentException ex) {
/* 523 */       throw xmlp.error("Invalid value", ex);
/*     */     } 
/*     */   }
/*     */   
/* 527 */   private static final int[] SPLIT_WEIGHTS_3 = new int[] { 0, 1, 0 };
/* 528 */   private static final int[] SPLIT_WEIGHTS_1 = new int[] { 1 };
/*     */   
/*     */   private void parseAnimElements(XMLParser xmlp, String tagName, ArrayList<AnimatedImage.Element> frames) throws XmlPullParserException, IOException {
/* 531 */     if ("repeat".equals(tagName)) {
/* 532 */       frames.add(parseAnimRepeat(xmlp));
/* 533 */     } else if ("frame".equals(tagName)) {
/* 534 */       frames.add(parseAnimFrame(xmlp));
/* 535 */     } else if ("frames".equals(tagName)) {
/* 536 */       parseAnimFrames(xmlp, frames);
/*     */     } else {
/* 538 */       throw xmlp.unexpected();
/*     */     } 
/*     */   }
/*     */   
/*     */   private AnimatedImage.Img parseAnimFrame(XMLParser xmlp) throws XmlPullParserException, IOException {
/* 543 */     int duration = xmlp.parseIntFromAttribute("duration");
/* 544 */     if (duration < 0) {
/* 545 */       throw new IllegalArgumentException("duration must be >= 0 ms");
/*     */     }
/* 547 */     AnimParams animParams = parseAnimParams(xmlp);
/* 548 */     Image image = getReferencedImage(xmlp);
/* 549 */     AnimatedImage.Img img = new AnimatedImage.Img(duration, image, animParams.tintColor, animParams.zoomX, animParams.zoomY, animParams.zoomCenterX, animParams.zoomCenterY);
/*     */     
/* 551 */     xmlp.nextTag();
/* 552 */     return img;
/*     */   }
/*     */   
/*     */   private AnimParams parseAnimParams(XMLParser xmlp) throws XmlPullParserException {
/* 556 */     AnimParams params = new AnimParams();
/* 557 */     params.tintColor = ParserUtil.parseColorFromAttribute(xmlp, "tint", this.constants, Color.WHITE);
/* 558 */     float zoom = xmlp.parseFloatFromAttribute("zoom", 1.0F);
/* 559 */     params.zoomX = xmlp.parseFloatFromAttribute("zoomX", zoom);
/* 560 */     params.zoomY = xmlp.parseFloatFromAttribute("zoomY", zoom);
/* 561 */     params.zoomCenterX = xmlp.parseFloatFromAttribute("zoomCenterX", 0.5F);
/* 562 */     params.zoomCenterY = xmlp.parseFloatFromAttribute("zoomCenterY", 0.5F);
/* 563 */     return params;
/*     */   }
/*     */   
/*     */   private void parseAnimFrames(XMLParser xmlp, ArrayList<AnimatedImage.Element> frames) throws XmlPullParserException, IOException {
/* 567 */     ImageParams params = new ImageParams();
/* 568 */     parseRectFromAttribute(xmlp, params);
/* 569 */     parseRotationFromAttribute(xmlp, params);
/* 570 */     int duration = xmlp.parseIntFromAttribute("duration");
/* 571 */     if (duration < 1) {
/* 572 */       throw new IllegalArgumentException("duration must be >= 1 ms");
/*     */     }
/* 574 */     int count = xmlp.parseIntFromAttribute("count");
/* 575 */     if (count < 1) {
/* 576 */       throw new IllegalArgumentException("count must be >= 1");
/*     */     }
/* 578 */     AnimParams animParams = parseAnimParams(xmlp);
/* 579 */     int xOffset = xmlp.parseIntFromAttribute("offsetx", 0);
/* 580 */     int yOffset = xmlp.parseIntFromAttribute("offsety", 0);
/* 581 */     if (count > 1 && xOffset == 0 && yOffset == 0) {
/* 582 */       throw new IllegalArgumentException("offsets required for multiple frames");
/*     */     }
/* 584 */     for (int i = 0; i < count; i++) {
/* 585 */       Image image = createImage(xmlp, params.x, params.y, params.w, params.h, Color.WHITE, false, params.rot);
/* 586 */       AnimatedImage.Img img = new AnimatedImage.Img(duration, image, animParams.tintColor, animParams.zoomX, animParams.zoomY, animParams.zoomCenterX, animParams.zoomCenterY);
/*     */       
/* 588 */       frames.add(img);
/* 589 */       params.x += xOffset;
/* 590 */       params.y += yOffset;
/*     */     } 
/*     */     
/* 593 */     xmlp.nextTag();
/*     */   }
/*     */   
/*     */   private AnimatedImage.Repeat parseAnimRepeat(XMLParser xmlp) throws XmlPullParserException, IOException {
/* 597 */     String strRepeatCount = xmlp.getAttributeValue(null, "count");
/* 598 */     int repeatCount = 0;
/* 599 */     if (strRepeatCount != null) {
/* 600 */       repeatCount = Integer.parseInt(strRepeatCount);
/* 601 */       if (repeatCount <= 0) {
/* 602 */         throw new IllegalArgumentException("Invalid repeat count");
/*     */       }
/*     */     } 
/* 605 */     boolean lastRepeatsEndless = false;
/* 606 */     boolean hasWarned = false;
/* 607 */     ArrayList<AnimatedImage.Element> children = new ArrayList<AnimatedImage.Element>();
/* 608 */     xmlp.nextTag();
/* 609 */     while (xmlp.isStartTag()) {
/* 610 */       if (lastRepeatsEndless && !hasWarned) {
/* 611 */         hasWarned = true;
/* 612 */         getLogger().log(Level.WARNING, "Animation frames after an endless repeat won''t be displayed: {0}", xmlp.getPositionDescription());
/*     */       } 
/* 614 */       String tagName = xmlp.getName();
/* 615 */       parseAnimElements(xmlp, tagName, children);
/* 616 */       AnimatedImage.Element e = children.get(children.size() - 1);
/* 617 */       lastRepeatsEndless = (e instanceof AnimatedImage.Repeat && ((AnimatedImage.Repeat)e).repeatCount == 0);
/*     */ 
/*     */       
/* 620 */       xmlp.require(3, null, tagName);
/* 621 */       xmlp.nextTag();
/*     */     } 
/* 623 */     return new AnimatedImage.Repeat(children.<AnimatedImage.Element>toArray(new AnimatedImage.Element[children.size()]), repeatCount);
/*     */   }
/*     */   
/*     */   private Border getBorder(AnimatedImage.Element e) {
/* 627 */     if (e instanceof AnimatedImage.Repeat) {
/* 628 */       AnimatedImage.Repeat r = (AnimatedImage.Repeat)e;
/* 629 */       for (AnimatedImage.Element c : r.children) {
/* 630 */         Border border = getBorder(c);
/* 631 */         if (border != null) {
/* 632 */           return border;
/*     */         }
/*     */       } 
/* 635 */     } else if (e instanceof AnimatedImage.Img) {
/* 636 */       AnimatedImage.Img i = (AnimatedImage.Img)e;
/* 637 */       if (i.image instanceof HasBorder) {
/* 638 */         return ((HasBorder)i.image).getBorder();
/*     */       }
/*     */     } 
/* 641 */     return null;
/*     */   }
/*     */   
/*     */   private Image parseAnimation(XMLParser xmlp, ImageParams params) throws XmlPullParserException, IOException {
/*     */     try {
/* 646 */       String timeSource = xmlp.getAttributeNotNull("timeSource");
/* 647 */       int frozenTime = xmlp.parseIntFromAttribute("frozenTime", -1);
/* 648 */       AnimatedImage.Repeat root = parseAnimRepeat(xmlp);
/* 649 */       if (params.border == null) {
/* 650 */         params.border = getBorder(root);
/*     */       }
/* 652 */       Image image = new AnimatedImage(this.renderer, root, timeSource, params.border, (params.tintColor == null) ? Color.WHITE : params.tintColor, frozenTime);
/*     */       
/* 654 */       params.tintColor = null;
/* 655 */       return image;
/* 656 */     } catch (IllegalArgumentException ex) {
/* 657 */       throw xmlp.error("Unable to parse", ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private Image parseGradient(XMLParser xmlp, ImageParams params) throws XmlPullParserException, IOException {
/*     */     try {
/* 663 */       Gradient.Type type = (Gradient.Type)xmlp.parseEnumFromAttribute("type", Gradient.Type.class);
/* 664 */       Gradient.Wrap wrap = (Gradient.Wrap)xmlp.parseEnumFromAttribute("wrap", Gradient.Wrap.class, (Enum)Gradient.Wrap.SCALE);
/*     */       
/* 666 */       Gradient gradient = new Gradient(type);
/* 667 */       gradient.setWrap(wrap);
/*     */       
/* 669 */       xmlp.nextTag();
/* 670 */       while (xmlp.isStartTag()) {
/* 671 */         xmlp.require(2, null, "stop");
/* 672 */         float pos = xmlp.parseFloatFromAttribute("pos");
/* 673 */         Color color = ParserUtil.parseColor(xmlp, xmlp.getAttributeNotNull("color"), this.constants);
/* 674 */         gradient.addStop(pos, color);
/* 675 */         xmlp.nextTag();
/* 676 */         xmlp.require(3, null, "stop");
/* 677 */         xmlp.nextTag();
/*     */       } 
/*     */       
/* 680 */       return this.renderer.createGradient(gradient);
/* 681 */     } catch (IllegalArgumentException ex) {
/* 682 */       throw xmlp.error("Unable to parse", ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private Image createImage(XMLParser xmlp, int x, int y, int w, int h, Color tintColor, boolean tiled, Texture.Rotation rotation) {
/* 687 */     if (w == 0 || h == 0) {
/* 688 */       return new EmptyImage(Math.abs(w), Math.abs(h));
/*     */     }
/*     */     
/* 691 */     Texture texture = this.currentTexture;
/* 692 */     int texWidth = texture.getWidth();
/* 693 */     int texHeight = texture.getHeight();
/*     */     
/* 695 */     int x1 = x + Math.abs(w);
/* 696 */     int y1 = y + Math.abs(h);
/*     */     
/* 698 */     if (x < 0 || x >= texWidth || x1 < 0 || x1 > texWidth || y < 0 || y >= texHeight || y1 < 0 || y1 > texHeight) {
/*     */       
/* 700 */       getLogger().log(Level.WARNING, "texture partly outside of file: {0}", xmlp.getPositionDescription());
/* 701 */       x = Math.max(0, Math.min(x, texWidth));
/* 702 */       y = Math.max(0, Math.min(y, texHeight));
/* 703 */       w = Integer.signum(w) * (Math.max(0, Math.min(x1, texWidth)) - x);
/* 704 */       h = Integer.signum(h) * (Math.max(0, Math.min(y1, texHeight)) - y);
/*     */     } 
/*     */     
/* 707 */     return texture.getImage(x, y, w, h, tintColor, tiled, rotation);
/*     */   }
/*     */   
/*     */   private void parseRectFromAttribute(XMLParser xmlp, ImageParams params) throws XmlPullParserException {
/* 711 */     if (this.currentTexture == null) {
/* 712 */       throw xmlp.error("can't create area outside of <imagefile> object");
/*     */     }
/* 714 */     String xywh = xmlp.getAttributeNotNull("xywh");
/* 715 */     if ("*".equals(xywh))
/* 716 */     { params.x = 0;
/* 717 */       params.y = 0;
/* 718 */       params.w = this.currentTexture.getWidth();
/* 719 */       params.h = this.currentTexture.getHeight(); }
/*     */     else { try {
/* 721 */         int[] coords = TextUtil.parseIntArray(xywh);
/* 722 */         if (coords.length != 4) {
/* 723 */           throw xmlp.error("xywh requires 4 integer arguments");
/*     */         }
/* 725 */         params.x = coords[0];
/* 726 */         params.y = coords[1];
/* 727 */         params.w = coords[2];
/* 728 */         params.h = coords[3];
/* 729 */       } catch (IllegalArgumentException ex) {
/* 730 */         throw xmlp.error("can't parse xywh argument", ex);
/*     */       }  }
/*     */   
/*     */   }
/*     */   private void parseRotationFromAttribute(XMLParser xmlp, ImageParams params) throws XmlPullParserException {
/* 735 */     if (this.currentTexture == null) {
/* 736 */       throw xmlp.error("can't create area outside of <imagefile> object");
/*     */     }
/* 738 */     int rot = xmlp.parseIntFromAttribute("rot", 0);
/* 739 */     switch (rot) { case 0:
/* 740 */         params.rot = Texture.Rotation.NONE; return;
/* 741 */       case 90: params.rot = Texture.Rotation.CLOCKWISE_90; return;
/* 742 */       case 180: params.rot = Texture.Rotation.CLOCKWISE_180; return;
/* 743 */       case 270: params.rot = Texture.Rotation.CLOCKWISE_270; return; }
/*     */     
/* 745 */     throw xmlp.error("invalid rotation angle");
/*     */   }
/*     */ 
/*     */   
/*     */   private void parseStdAttributes(XMLParser xmlp, ImageParams params) throws XmlPullParserException {
/* 750 */     params.tintColor = ParserUtil.parseColorFromAttribute(xmlp, "tint", this.constants, null);
/* 751 */     params.border = ParserUtil.parseBorderFromAttribute(xmlp, "border");
/* 752 */     params.inset = ParserUtil.parseBorderFromAttribute(xmlp, "inset");
/* 753 */     params.repeatX = xmlp.parseBoolFromAttribute("repeatX", false);
/* 754 */     params.repeatY = xmlp.parseBoolFromAttribute("repeatY", false);
/* 755 */     params.sizeOverwriteH = xmlp.parseIntFromAttribute("sizeOverwriteH", -1);
/* 756 */     params.sizeOverwriteV = xmlp.parseIntFromAttribute("sizeOverwriteV", -1);
/* 757 */     params.center = xmlp.parseBoolFromAttribute("center", false);
/*     */   }
/*     */   
/*     */   Logger getLogger() {
/* 761 */     return Logger.getLogger(ImageManager.class.getName());
/*     */   }
/*     */ 
/*     */   
/*     */   static class ImageParams
/*     */   {
/*     */     int x;
/*     */     int y;
/*     */     int w;
/*     */     int h;
/* 771 */     int sizeOverwriteH = -1; Color tintColor; Border border; Border inset; boolean repeatX; boolean repeatY;
/* 772 */     int sizeOverwriteV = -1;
/*     */     boolean center;
/*     */     StateExpression condition;
/*     */     Texture.Rotation rot;
/*     */   }
/*     */   
/*     */   static class AnimParams {
/*     */     Color tintColor;
/*     */     float zoomX;
/*     */     float zoomY;
/*     */     float zoomCenterX;
/*     */     float zoomCenterY;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\theme\ImageManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */