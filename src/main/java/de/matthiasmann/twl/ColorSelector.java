/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.model.AbstractFloatModel;
/*     */ import de.matthiasmann.twl.model.AbstractIntegerModel;
/*     */ import de.matthiasmann.twl.model.ColorModel;
/*     */ import de.matthiasmann.twl.model.ColorSpace;
/*     */ import de.matthiasmann.twl.model.FloatModel;
/*     */ import de.matthiasmann.twl.model.IntegerModel;
/*     */ import de.matthiasmann.twl.renderer.DynamicImage;
/*     */ import de.matthiasmann.twl.renderer.Image;
/*     */ import de.matthiasmann.twl.utils.CallbackSupport;
/*     */ import de.matthiasmann.twl.utils.TintAnimator;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.IntBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ColorSelector
/*     */   extends DialogLayout
/*     */ {
/*  51 */   private static final String[] RGBA_NAMES = new String[] { "Red", "Green", "Blue", "Alpha" };
/*  52 */   private static final String[] RGBA_PREFIX = new String[] { "R: ", "G: ", "B: ", "A: " };
/*     */   
/*     */   final ByteBuffer imgData;
/*     */   
/*     */   final IntBuffer imgDataInt;
/*     */   
/*     */   ColorSpace colorSpace;
/*     */   float[] colorValues;
/*     */   ColorValueModel[] colorValueModels;
/*     */   private boolean useColorArea2D = true;
/*     */   private boolean showPreview = false;
/*     */   private boolean useLabels = true;
/*     */   private boolean showHexEditField = false;
/*     */   private boolean showNativeAdjuster = true;
/*     */   private boolean showRGBAdjuster = true;
/*     */   private boolean showAlphaAdjuster = true;
/*     */   private Runnable[] callbacks;
/*     */   private ColorModel model;
/*     */   private Runnable modelCallback;
/*     */   private boolean inModelSetValue;
/*     */   int currentColor;
/*     */   private ARGBModel[] argbModels;
/*     */   EditField hexColorEditField;
/*     */   private TintAnimator previewTintAnimator;
/*     */   private boolean recreateLayout;
/*     */   private static final int IMAGE_SIZE = 64;
/*     */   
/*     */   public ColorSelector(ColorSpace colorSpace) {
/*  80 */     this.imgData = ByteBuffer.allocateDirect(16384);
/*  81 */     this.imgData.order(ByteOrder.BIG_ENDIAN);
/*  82 */     this.imgDataInt = this.imgData.asIntBuffer();
/*     */     
/*  84 */     this.currentColor = Color.WHITE.toARGB();
/*     */     
/*  86 */     setColorSpace(colorSpace);
/*     */   }
/*     */   
/*     */   public ColorSpace getColorSpace() {
/*  90 */     return this.colorSpace;
/*     */   }
/*     */   
/*     */   public void setColorSpace(ColorSpace colorModel) {
/*  94 */     if (colorModel == null) {
/*  95 */       throw new NullPointerException("colorModel");
/*     */     }
/*  97 */     if (this.colorSpace != colorModel) {
/*  98 */       boolean hasColor = (this.colorSpace != null);
/*     */       
/* 100 */       this.colorSpace = colorModel;
/* 101 */       this.colorValues = new float[colorModel.getNumComponents()];
/*     */       
/* 103 */       if (hasColor) {
/* 104 */         setColor(this.currentColor);
/*     */       } else {
/* 106 */         setDefaultColor();
/*     */       } 
/*     */       
/* 109 */       this.recreateLayout = true;
/* 110 */       invalidateLayout();
/*     */     } 
/*     */   }
/*     */   
/*     */   public ColorModel getModel() {
/* 115 */     return this.model;
/*     */   }
/*     */   
/*     */   public void setModel(ColorModel model) {
/* 119 */     if (this.model != model) {
/* 120 */       removeModelCallback();
/* 121 */       this.model = model;
/* 122 */       if (model != null) {
/* 123 */         addModelCallback();
/* 124 */         modelValueChanged();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Color getColor() {
/* 130 */     return new Color(this.currentColor);
/*     */   }
/*     */   
/*     */   public void setColor(Color color) {
/* 134 */     setColor(color.toARGB());
/* 135 */     updateModel();
/*     */   }
/*     */   
/*     */   public void setDefaultColor() {
/* 139 */     this.currentColor = Color.WHITE.toARGB();
/* 140 */     for (int i = 0; i < this.colorSpace.getNumComponents(); i++) {
/* 141 */       this.colorValues[i] = this.colorSpace.getDefaultValue(i);
/*     */     }
/* 143 */     updateAllColorAreas();
/* 144 */     colorChanged();
/*     */   }
/*     */   
/*     */   public boolean isUseColorArea2D() {
/* 148 */     return this.useColorArea2D;
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
/*     */   public void setUseColorArea2D(boolean useColorArea2D) {
/* 163 */     if (this.useColorArea2D != useColorArea2D) {
/* 164 */       this.useColorArea2D = useColorArea2D;
/* 165 */       this.recreateLayout = true;
/* 166 */       invalidateLayout();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isShowPreview() {
/* 171 */     return this.showPreview;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShowPreview(boolean showPreview) {
/* 181 */     if (this.showPreview != showPreview) {
/* 182 */       this.showPreview = showPreview;
/* 183 */       this.recreateLayout = true;
/* 184 */       invalidateLayout();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isShowHexEditField() {
/* 189 */     return this.showHexEditField;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShowHexEditField(boolean showHexEditField) {
/* 199 */     if (this.showHexEditField != showHexEditField) {
/* 200 */       this.showHexEditField = showHexEditField;
/* 201 */       this.recreateLayout = true;
/* 202 */       invalidateLayout();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isShowAlphaAdjuster() {
/* 207 */     return this.showAlphaAdjuster;
/*     */   }
/*     */   
/*     */   public void setShowAlphaAdjuster(boolean showAlphaAdjuster) {
/* 211 */     if (this.showAlphaAdjuster != showAlphaAdjuster) {
/* 212 */       this.showAlphaAdjuster = showAlphaAdjuster;
/* 213 */       this.recreateLayout = true;
/* 214 */       invalidateLayout();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isShowNativeAdjuster() {
/* 219 */     return this.showNativeAdjuster;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShowNativeAdjuster(boolean showNativeAdjuster) {
/* 229 */     if (this.showNativeAdjuster != showNativeAdjuster) {
/* 230 */       this.showNativeAdjuster = showNativeAdjuster;
/* 231 */       this.recreateLayout = true;
/* 232 */       invalidateLayout();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isShowRGBAdjuster() {
/* 237 */     return this.showRGBAdjuster;
/*     */   }
/*     */   
/*     */   public void setShowRGBAdjuster(boolean showRGBAdjuster) {
/* 241 */     if (this.showRGBAdjuster != showRGBAdjuster) {
/* 242 */       this.showRGBAdjuster = showRGBAdjuster;
/* 243 */       this.recreateLayout = true;
/* 244 */       invalidateLayout();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isUseLabels() {
/* 249 */     return this.useLabels;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUseLabels(boolean useLabels) {
/* 259 */     if (this.useLabels != useLabels) {
/* 260 */       this.useLabels = useLabels;
/* 261 */       this.recreateLayout = true;
/* 262 */       invalidateLayout();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addCallback(Runnable cb) {
/* 267 */     this.callbacks = (Runnable[])CallbackSupport.addCallbackToList((Object[])this.callbacks, cb, Runnable.class);
/*     */   }
/*     */   
/*     */   public void removeCallback(Runnable cb) {
/* 271 */     this.callbacks = (Runnable[])CallbackSupport.removeCallbackFromList((Object[])this.callbacks, cb);
/*     */   }
/*     */   
/*     */   protected void updateModel() {
/* 275 */     if (this.model != null) {
/* 276 */       this.inModelSetValue = true;
/*     */       try {
/* 278 */         this.model.setValue(getColor());
/*     */       } finally {
/* 280 */         this.inModelSetValue = false;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void colorChanged() {
/* 286 */     this.currentColor = this.currentColor & 0xFF000000 | this.colorSpace.toRGB(this.colorValues);
/* 287 */     CallbackSupport.fireCallbacks(this.callbacks);
/* 288 */     updateModel();
/* 289 */     if (this.argbModels != null) {
/* 290 */       for (ARGBModel m : this.argbModels) {
/* 291 */         m.fireCallback();
/*     */       }
/*     */     }
/* 294 */     if (this.previewTintAnimator != null) {
/* 295 */       this.previewTintAnimator.setColor(getColor());
/*     */     }
/* 297 */     updateHexEditField();
/*     */   }
/*     */   
/*     */   protected void setColor(int argb) {
/* 301 */     this.currentColor = argb;
/* 302 */     this.colorValues = this.colorSpace.fromRGB(argb & 0xFFFFFF);
/* 303 */     updateAllColorAreas();
/*     */   }
/*     */   
/*     */   protected int getNumComponents() {
/* 307 */     return this.colorSpace.getNumComponents();
/*     */   }
/*     */ 
/*     */   
/*     */   public void layout() {
/* 312 */     if (this.recreateLayout) {
/* 313 */       createColorAreas();
/*     */     }
/* 315 */     super.layout();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinWidth() {
/* 320 */     if (this.recreateLayout) {
/* 321 */       createColorAreas();
/*     */     }
/* 323 */     return super.getMinWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinHeight() {
/* 328 */     if (this.recreateLayout) {
/* 329 */       createColorAreas();
/*     */     }
/* 331 */     return super.getMinHeight();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerWidth() {
/* 336 */     if (this.recreateLayout) {
/* 337 */       createColorAreas();
/*     */     }
/* 339 */     return super.getPreferredInnerWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerHeight() {
/* 344 */     if (this.recreateLayout) {
/* 345 */       createColorAreas();
/*     */     }
/* 347 */     return super.getPreferredInnerHeight();
/*     */   }
/*     */   
/*     */   protected void createColorAreas() {
/* 351 */     this.recreateLayout = false;
/* 352 */     setVerticalGroup((DialogLayout.Group)null);
/* 353 */     removeAllChildren();
/*     */ 
/*     */     
/* 356 */     this.argbModels = new ARGBModel[4];
/* 357 */     this.argbModels[0] = new ARGBModel(16);
/* 358 */     this.argbModels[1] = new ARGBModel(8);
/* 359 */     this.argbModels[2] = new ARGBModel(0);
/* 360 */     this.argbModels[3] = new ARGBModel(24);
/*     */     
/* 362 */     int numComponents = getNumComponents();
/*     */     
/* 364 */     DialogLayout.Group horzAreas = createSequentialGroup().addGap();
/* 365 */     DialogLayout.Group vertAreas = createParallelGroup();
/*     */     
/* 367 */     DialogLayout.Group horzLabels = null;
/* 368 */     DialogLayout.Group horzAdjuster = createParallelGroup();
/* 369 */     DialogLayout.Group horzControlls = createSequentialGroup();
/*     */     
/* 371 */     if (this.useLabels) {
/* 372 */       horzLabels = createParallelGroup();
/* 373 */       horzControlls.addGroup(horzLabels);
/*     */     } 
/* 375 */     horzControlls.addGroup(horzAdjuster);
/*     */     
/* 377 */     DialogLayout.Group[] vertAdjuster = new DialogLayout.Group[4 + numComponents];
/* 378 */     int numAdjuters = 0;
/*     */     
/* 380 */     for (int k = 0; k < vertAdjuster.length; k++) {
/* 381 */       vertAdjuster[k] = createParallelGroup();
/*     */     }
/*     */     
/* 384 */     this.colorValueModels = new ColorValueModel[numComponents];
/* 385 */     for (int j = 0; j < numComponents; j++) {
/* 386 */       this.colorValueModels[j] = new ColorValueModel(j);
/*     */       
/* 388 */       if (this.showNativeAdjuster) {
/* 389 */         ValueAdjusterFloat vaf = new ValueAdjusterFloat((FloatModel)this.colorValueModels[j]);
/*     */         
/* 391 */         if (this.useLabels) {
/* 392 */           Label label = new Label(this.colorSpace.getComponentName(j));
/* 393 */           label.setLabelFor(vaf);
/* 394 */           horzLabels.addWidget(label);
/* 395 */           vertAdjuster[numAdjuters].addWidget(label);
/*     */         } else {
/* 397 */           vaf.setDisplayPrefix(this.colorSpace.getComponentShortName(j).concat(": "));
/* 398 */           vaf.setTooltipContent(this.colorSpace.getComponentName(j));
/*     */         } 
/*     */         
/* 401 */         horzAdjuster.addWidget(vaf);
/* 402 */         vertAdjuster[numAdjuters].addWidget(vaf);
/* 403 */         numAdjuters++;
/*     */       } 
/*     */     } 
/*     */     
/* 407 */     for (int i = 0; i < this.argbModels.length; i++) {
/* 408 */       if ((i == 3 && this.showAlphaAdjuster) || (i < 3 && this.showRGBAdjuster)) {
/* 409 */         ValueAdjusterInt vai = new ValueAdjusterInt((IntegerModel)this.argbModels[i]);
/*     */         
/* 411 */         if (this.useLabels) {
/* 412 */           Label label = new Label(RGBA_NAMES[i]);
/* 413 */           label.setLabelFor(vai);
/* 414 */           horzLabels.addWidget(label);
/* 415 */           vertAdjuster[numAdjuters].addWidget(label);
/*     */         } else {
/* 417 */           vai.setDisplayPrefix(RGBA_PREFIX[i]);
/* 418 */           vai.setTooltipContent(RGBA_NAMES[i]);
/*     */         } 
/*     */         
/* 421 */         horzAdjuster.addWidget(vai);
/* 422 */         vertAdjuster[numAdjuters].addWidget(vai);
/* 423 */         numAdjuters++;
/*     */       } 
/*     */     } 
/*     */     
/* 427 */     int component = 0;
/*     */     
/* 429 */     if (this.useColorArea2D) {
/* 430 */       for (; component + 1 < numComponents; component += 2) {
/* 431 */         ColorArea2D area = new ColorArea2D(component, component + 1);
/* 432 */         area.setTooltipContent(this.colorSpace.getComponentName(component) + " / " + this.colorSpace.getComponentName(component + 1));
/*     */ 
/*     */         
/* 435 */         horzAreas.addWidget(area);
/* 436 */         vertAreas.addWidget(area);
/*     */       } 
/*     */     }
/*     */     
/* 440 */     for (; component < numComponents; component++) {
/* 441 */       ColorArea1D area = new ColorArea1D(component);
/* 442 */       area.setTooltipContent(this.colorSpace.getComponentName(component));
/*     */       
/* 444 */       horzAreas.addWidget(area);
/* 445 */       vertAreas.addWidget(area);
/*     */     } 
/*     */     
/* 448 */     if (this.showHexEditField && this.hexColorEditField == null) {
/* 449 */       createHexColorEditField();
/*     */     }
/*     */     
/* 452 */     if (this.showPreview) {
/* 453 */       if (this.previewTintAnimator == null) {
/* 454 */         this.previewTintAnimator = new TintAnimator(this, getColor());
/*     */       }
/*     */       
/* 457 */       Widget previewArea = new Widget();
/* 458 */       previewArea.setTheme("colorarea");
/* 459 */       previewArea.setTintAnimator(this.previewTintAnimator);
/*     */       
/* 461 */       Widget preview = new Container();
/* 462 */       preview.setTheme("preview");
/* 463 */       preview.add(previewArea);
/*     */       
/* 465 */       Label label = new Label();
/* 466 */       label.setTheme("previewLabel");
/* 467 */       label.setLabelFor(preview);
/*     */       
/* 469 */       DialogLayout.Group horz = createParallelGroup();
/* 470 */       DialogLayout.Group vert = createSequentialGroup();
/*     */       
/* 472 */       horzAreas.addGroup(horz.addWidget(label).addWidget(preview));
/* 473 */       vertAreas.addGroup(vert.addGap().addWidget(label).addWidget(preview));
/*     */       
/* 475 */       if (this.showHexEditField) {
/* 476 */         horz.addWidget(this.hexColorEditField);
/* 477 */         vert.addGap().addWidget(this.hexColorEditField);
/*     */       } 
/*     */     } 
/*     */     
/* 481 */     DialogLayout.Group horzMainGroup = createParallelGroup().addGroup(horzAreas.addGap()).addGroup(horzControlls);
/*     */ 
/*     */     
/* 484 */     DialogLayout.Group vertMainGroup = createSequentialGroup().addGroup(vertAreas);
/*     */ 
/*     */     
/* 487 */     for (int m = 0; m < numAdjuters; m++) {
/* 488 */       vertMainGroup.addGroup(vertAdjuster[m]);
/*     */     }
/*     */     
/* 491 */     if (this.showHexEditField) {
/* 492 */       if (this.hexColorEditField == null) {
/* 493 */         createHexColorEditField();
/*     */       }
/*     */       
/* 496 */       if (!this.showPreview) {
/* 497 */         horzMainGroup.addWidget(this.hexColorEditField);
/* 498 */         vertMainGroup.addWidget(this.hexColorEditField);
/*     */       } 
/*     */       
/* 501 */       updateHexEditField();
/*     */     } 
/* 503 */     setHorizontalGroup(horzMainGroup);
/* 504 */     setVerticalGroup(vertMainGroup.addGap());
/*     */   }
/*     */   
/*     */   protected void updateAllColorAreas() {
/* 508 */     if (this.colorValueModels != null) {
/* 509 */       for (ColorValueModel cvm : this.colorValueModels) {
/* 510 */         cvm.fireCallback();
/*     */       }
/* 512 */       colorChanged();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void afterAddToGUI(GUI gui) {
/* 518 */     super.afterAddToGUI(gui);
/* 519 */     addModelCallback();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void beforeRemoveFromGUI(GUI gui) {
/* 524 */     removeModelCallback();
/* 525 */     super.beforeRemoveFromGUI(gui);
/*     */   }
/*     */   
/*     */   private void removeModelCallback() {
/* 529 */     if (this.model != null) {
/* 530 */       this.model.removeCallback(this.modelCallback);
/*     */     }
/*     */   }
/*     */   
/*     */   private void addModelCallback() {
/* 535 */     if (this.model != null && getGUI() != null) {
/* 536 */       if (this.modelCallback == null) {
/* 537 */         this.modelCallback = new Runnable() {
/*     */             public void run() {
/* 539 */               ColorSelector.this.modelValueChanged();
/*     */             }
/*     */           };
/*     */       }
/* 543 */       this.model.addCallback(this.modelCallback);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void createHexColorEditField() {
/* 548 */     this.hexColorEditField = new EditField()
/*     */       {
/*     */         protected void insertChar(char ch) {
/* 551 */           if (isValid(ch)) {
/* 552 */             super.insertChar(ch);
/*     */           }
/*     */         }
/*     */ 
/*     */         
/*     */         public void insertText(String str) {
/* 558 */           for (int i = 0, n = str.length(); i < n; i++) {
/* 559 */             if (!isValid(str.charAt(i))) {
/* 560 */               StringBuilder sb = new StringBuilder(str);
/* 561 */               for (int j = n; j-- >= i;) {
/* 562 */                 if (!isValid(sb.charAt(j))) {
/* 563 */                   sb.deleteCharAt(j);
/*     */                 }
/*     */               } 
/* 566 */               str = sb.toString();
/*     */               break;
/*     */             } 
/*     */           } 
/* 570 */           super.insertText(str);
/*     */         }
/*     */         
/*     */         private boolean isValid(char ch) {
/* 574 */           int digit = Character.digit(ch, 16);
/* 575 */           return (digit >= 0 && digit < 16);
/*     */         }
/*     */       };
/* 578 */     this.hexColorEditField.setTheme("hexColorEditField");
/* 579 */     this.hexColorEditField.setColumns(8);
/* 580 */     this.hexColorEditField.addCallback(new EditField.Callback() {
/*     */           public void callback(int key) {
/* 582 */             if (key == 1) {
/* 583 */               ColorSelector.this.updateHexEditField();
/*     */               return;
/*     */             } 
/* 586 */             Color color = null;
/*     */             try {
/* 588 */               color = Color.parserColor("#".concat(ColorSelector.this.hexColorEditField.getText()));
/* 589 */               ColorSelector.this.hexColorEditField.setErrorMessage((Object)null);
/* 590 */             } catch (Exception ex) {
/* 591 */               ColorSelector.this.hexColorEditField.setErrorMessage("Invalid color format");
/*     */             } 
/* 593 */             if (key == 28 && color != null) {
/* 594 */               ColorSelector.this.setColor(color);
/*     */             }
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   void updateHexEditField() {
/* 601 */     if (this.hexColorEditField != null) {
/* 602 */       this.hexColorEditField.setText(String.format("%08X", new Object[] { Integer.valueOf(this.currentColor) }));
/*     */     }
/*     */   }
/*     */   
/*     */   void modelValueChanged() {
/* 607 */     if (!this.inModelSetValue && this.model != null)
/*     */     {
/* 609 */       setColor(this.model.getValue().toARGB());
/*     */     }
/*     */   }
/*     */   
/*     */   class ColorValueModel
/*     */     extends AbstractFloatModel
/*     */   {
/*     */     private final int component;
/*     */     
/*     */     ColorValueModel(int component) {
/* 619 */       this.component = component;
/*     */     }
/*     */     
/*     */     public float getMaxValue() {
/* 623 */       return ColorSelector.this.colorSpace.getMaxValue(this.component);
/*     */     }
/*     */     
/*     */     public float getMinValue() {
/* 627 */       return ColorSelector.this.colorSpace.getMinValue(this.component);
/*     */     }
/*     */     
/*     */     public float getValue() {
/* 631 */       return ColorSelector.this.colorValues[this.component];
/*     */     }
/*     */     
/*     */     public void setValue(float value) {
/* 635 */       ColorSelector.this.colorValues[this.component] = value;
/* 636 */       doCallback();
/* 637 */       ColorSelector.this.colorChanged();
/*     */     }
/*     */     
/*     */     void fireCallback() {
/* 641 */       doCallback();
/*     */     }
/*     */   }
/*     */   
/*     */   class ARGBModel extends AbstractIntegerModel {
/*     */     private final int startBit;
/*     */     
/*     */     ARGBModel(int startBit) {
/* 649 */       this.startBit = startBit;
/*     */     }
/*     */     
/*     */     public int getMaxValue() {
/* 653 */       return 255;
/*     */     }
/*     */     
/*     */     public int getMinValue() {
/* 657 */       return 0;
/*     */     }
/*     */     
/*     */     public int getValue() {
/* 661 */       return ColorSelector.this.currentColor >> this.startBit & 0xFF;
/*     */     }
/*     */     
/*     */     public void setValue(int value) {
/* 665 */       ColorSelector.this.setColor(ColorSelector.this.currentColor & (255 << this.startBit ^ 0xFFFFFFFF) | value << this.startBit);
/*     */     }
/*     */     
/*     */     void fireCallback() {
/* 669 */       doCallback();
/*     */     }
/*     */   }
/*     */   
/*     */   abstract class ColorArea
/*     */     extends Widget implements Runnable {
/*     */     DynamicImage img;
/*     */     Image cursorImage;
/*     */     boolean needsUpdate;
/*     */     
/*     */     protected void applyTheme(ThemeInfo themeInfo) {
/* 680 */       super.applyTheme(themeInfo);
/* 681 */       this.cursorImage = themeInfo.getImage("cursor");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     abstract void createImage(GUI param1GUI);
/*     */ 
/*     */     
/*     */     protected void paintWidget(GUI gui) {
/* 690 */       if (this.img == null) {
/* 691 */         createImage(gui);
/* 692 */         this.needsUpdate = true;
/*     */       } 
/* 694 */       if (this.img != null) {
/* 695 */         if (this.needsUpdate) {
/* 696 */           updateImage();
/*     */         }
/* 698 */         this.img.draw(getAnimationState(), getInnerX(), getInnerY(), getInnerWidth(), getInnerHeight());
/*     */       } 
/*     */     }
/*     */     abstract void updateImage();
/*     */     abstract void handleMouse(int param1Int1, int param1Int2);
/*     */     public void destroy() {
/* 704 */       super.destroy();
/* 705 */       if (this.img != null) {
/* 706 */         this.img.destroy();
/* 707 */         this.img = null;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean handleEvent(Event evt) {
/* 713 */       switch (evt.getType()) {
/*     */         case MOUSE_BTNDOWN:
/*     */         case MOUSE_DRAGGED:
/* 716 */           handleMouse(evt.getMouseX() - getInnerX(), evt.getMouseY() - getInnerY());
/* 717 */           return true;
/*     */         case MOUSE_WHEEL:
/* 719 */           return false;
/*     */       } 
/* 721 */       if (evt.isMouseEvent()) {
/* 722 */         return true;
/*     */       }
/*     */ 
/*     */       
/* 726 */       return super.handleEvent(evt);
/*     */     }
/*     */     
/*     */     public void run() {
/* 730 */       this.needsUpdate = true;
/*     */     }
/*     */   }
/*     */   
/*     */   class ColorArea1D
/*     */     extends ColorArea {
/*     */     final int component;
/*     */     
/*     */     ColorArea1D(int component) {
/* 739 */       this.component = component;
/*     */       
/* 741 */       for (int i = 0, n = ColorSelector.this.getNumComponents(); i < n; i++) {
/* 742 */         if (i != component) {
/* 743 */           ColorSelector.this.colorValueModels[i].addCallback(this);
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected void paintWidget(GUI gui) {
/* 750 */       super.paintWidget(gui);
/* 751 */       if (this.cursorImage != null) {
/* 752 */         float minValue = ColorSelector.this.colorSpace.getMinValue(this.component);
/* 753 */         float maxValue = ColorSelector.this.colorSpace.getMaxValue(this.component);
/* 754 */         int pos = (int)((ColorSelector.this.colorValues[this.component] - maxValue) * (getInnerHeight() - 1) / (minValue - maxValue) + 0.5F);
/* 755 */         this.cursorImage.draw(getAnimationState(), getInnerX(), getInnerY() + pos, getInnerWidth(), 1);
/*     */       } 
/*     */     }
/*     */     
/*     */     protected void createImage(GUI gui) {
/* 760 */       this.img = gui.getRenderer().createDynamicImage(1, 64);
/*     */     }
/*     */     
/*     */     protected void updateImage() {
/* 764 */       float[] temp = (float[])ColorSelector.this.colorValues.clone();
/* 765 */       IntBuffer buf = ColorSelector.this.imgDataInt;
/* 766 */       ColorSpace cs = ColorSelector.this.colorSpace;
/*     */       
/* 768 */       float x = cs.getMaxValue(this.component);
/* 769 */       float dx = (cs.getMinValue(this.component) - x) / 63.0F;
/*     */       
/* 771 */       for (int i = 0; i < 64; i++) {
/* 772 */         temp[this.component] = x;
/* 773 */         buf.put(i, cs.toRGB(temp) << 8 | 0xFF);
/* 774 */         x += dx;
/*     */       } 
/*     */       
/* 777 */       this.img.update(ColorSelector.this.imgData, DynamicImage.Format.RGBA);
/* 778 */       this.needsUpdate = false;
/*     */     }
/*     */ 
/*     */     
/*     */     void handleMouse(int x, int y) {
/* 783 */       float minValue = ColorSelector.this.colorSpace.getMinValue(this.component);
/* 784 */       float maxValue = ColorSelector.this.colorSpace.getMaxValue(this.component);
/* 785 */       int innerHeight = getInnerHeight();
/* 786 */       int pos = Math.max(0, Math.min(innerHeight, y));
/* 787 */       float value = maxValue + (minValue - maxValue) * pos / innerHeight;
/* 788 */       ColorSelector.this.colorValueModels[this.component].setValue(value);
/*     */     }
/*     */   }
/*     */   
/*     */   class ColorArea2D
/*     */     extends ColorArea {
/*     */     private final int componentX;
/*     */     private final int componentY;
/*     */     
/*     */     ColorArea2D(int componentX, int componentY) {
/* 798 */       this.componentX = componentX;
/* 799 */       this.componentY = componentY;
/*     */       
/* 801 */       for (int i = 0, n = ColorSelector.this.getNumComponents(); i < n; i++) {
/* 802 */         if (i != componentX && i != componentY) {
/* 803 */           ColorSelector.this.colorValueModels[i].addCallback(this);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*     */     protected void paintWidget(GUI gui) {
/* 809 */       super.paintWidget(gui);
/* 810 */       if (this.cursorImage != null) {
/* 811 */         float minValueX = ColorSelector.this.colorSpace.getMinValue(this.componentX);
/* 812 */         float maxValueX = ColorSelector.this.colorSpace.getMaxValue(this.componentX);
/* 813 */         float minValueY = ColorSelector.this.colorSpace.getMinValue(this.componentY);
/* 814 */         float maxValueY = ColorSelector.this.colorSpace.getMaxValue(this.componentY);
/* 815 */         int posX = (int)((ColorSelector.this.colorValues[this.componentX] - maxValueX) * (getInnerWidth() - 1) / (minValueX - maxValueX) + 0.5F);
/* 816 */         int posY = (int)((ColorSelector.this.colorValues[this.componentY] - maxValueY) * (getInnerHeight() - 1) / (minValueY - maxValueY) + 0.5F);
/* 817 */         this.cursorImage.draw(getAnimationState(), getInnerX() + posX, getInnerY() + posY, 1, 1);
/*     */       } 
/*     */     }
/*     */     
/*     */     protected void createImage(GUI gui) {
/* 822 */       this.img = gui.getRenderer().createDynamicImage(64, 64);
/*     */     }
/*     */     
/*     */     protected void updateImage() {
/* 826 */       float[] temp = (float[])ColorSelector.this.colorValues.clone();
/* 827 */       IntBuffer buf = ColorSelector.this.imgDataInt;
/* 828 */       ColorSpace cs = ColorSelector.this.colorSpace;
/*     */       
/* 830 */       float x0 = cs.getMaxValue(this.componentX);
/* 831 */       float dx = (cs.getMinValue(this.componentX) - x0) / 63.0F;
/*     */       
/* 833 */       float y = cs.getMaxValue(this.componentY);
/* 834 */       float dy = (cs.getMinValue(this.componentY) - y) / 63.0F;
/*     */       
/* 836 */       for (int i = 0, idx = 0; i < 64; i++) {
/* 837 */         temp[this.componentY] = y;
/* 838 */         float x = x0;
/* 839 */         for (int j = 0; j < 64; j++) {
/* 840 */           temp[this.componentX] = x;
/* 841 */           buf.put(idx++, cs.toRGB(temp) << 8 | 0xFF);
/* 842 */           x += dx;
/*     */         } 
/* 844 */         y += dy;
/*     */       } 
/*     */       
/* 847 */       this.img.update(ColorSelector.this.imgData, DynamicImage.Format.RGBA);
/* 848 */       this.needsUpdate = false;
/*     */     }
/*     */ 
/*     */     
/*     */     void handleMouse(int x, int y) {
/* 853 */       float minValueX = ColorSelector.this.colorSpace.getMinValue(this.componentX);
/* 854 */       float maxValueX = ColorSelector.this.colorSpace.getMaxValue(this.componentX);
/* 855 */       float minValueY = ColorSelector.this.colorSpace.getMinValue(this.componentY);
/* 856 */       float maxValueY = ColorSelector.this.colorSpace.getMaxValue(this.componentY);
/* 857 */       int innerWidtht = getInnerWidth();
/* 858 */       int innerHeight = getInnerHeight();
/* 859 */       int posX = Math.max(0, Math.min(innerWidtht, x));
/* 860 */       int posY = Math.max(0, Math.min(innerHeight, y));
/* 861 */       float valueX = maxValueX + (minValueX - maxValueX) * posX / innerWidtht;
/* 862 */       float valueY = maxValueY + (minValueY - maxValueY) * posY / innerHeight;
/* 863 */       ColorSelector.this.colorValueModels[this.componentX].setValue(valueX);
/* 864 */       ColorSelector.this.colorValueModels[this.componentY].setValue(valueY);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\ColorSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */