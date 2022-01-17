/*     */ package de.matthiasmann.twl.textarea;
/*     */ 
/*     */ import de.matthiasmann.twl.Color;
/*     */ import de.matthiasmann.twl.utils.StringList;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class StyleAttribute<T>
/*     */ {
/*  50 */   private static final ArrayList<StyleAttribute<?>> attributes = new ArrayList<StyleAttribute<?>>();
/*     */ 
/*     */   
/*  53 */   public static final StyleAttribute<TextAreaModel.HAlignment> HORIZONTAL_ALIGNMENT = new StyleAttribute(true, (Class)TextAreaModel.HAlignment.class, (T)TextAreaModel.HAlignment.LEFT);
/*  54 */   public static final StyleAttribute<TextAreaModel.VAlignment> VERTICAL_ALIGNMENT = new StyleAttribute(true, (Class)TextAreaModel.VAlignment.class, (T)TextAreaModel.VAlignment.BOTTOM);
/*  55 */   public static final StyleAttribute<Value> TEXT_INDENT = new StyleAttribute(true, (Class)Value.class, (T)Value.ZERO_PX);
/*  56 */   public static final StyleAttribute<TextDecoration> TEXT_DECORATION = new StyleAttribute(true, (Class)TextDecoration.class, (T)TextDecoration.NONE);
/*  57 */   public static final StyleAttribute<TextDecoration> TEXT_DECORATION_HOVER = new StyleAttribute(true, (Class)TextDecoration.class, null);
/*  58 */   public static final StyleAttribute<StringList> FONT_FAMILIES = new StyleAttribute(true, (Class)StringList.class, (T)new StringList("default"));
/*  59 */   public static final StyleAttribute<Value> FONT_SIZE = new StyleAttribute(true, (Class)Value.class, (T)new Value(14.0F, Value.Unit.PX));
/*  60 */   public static final StyleAttribute<Integer> FONT_WEIGHT = new StyleAttribute(true, (Class)Integer.class, (T)Integer.valueOf(400));
/*  61 */   public static final StyleAttribute<Boolean> FONT_ITALIC = new StyleAttribute(true, (Class)Boolean.class, (T)Boolean.FALSE);
/*  62 */   public static final StyleAttribute<Integer> TAB_SIZE = new StyleAttribute(true, (Class)Integer.class, (T)Integer.valueOf(8));
/*  63 */   public static final StyleAttribute<String> LIST_STYLE_IMAGE = new StyleAttribute(true, (Class)String.class, (T)"ul-bullet");
/*  64 */   public static final StyleAttribute<OrderedListType> LIST_STYLE_TYPE = new StyleAttribute(true, (Class)OrderedListType.class, (T)OrderedListType.DECIMAL);
/*  65 */   public static final StyleAttribute<Boolean> PREFORMATTED = new StyleAttribute(true, (Class)Boolean.class, (T)Boolean.FALSE);
/*  66 */   public static final StyleAttribute<Boolean> BREAKWORD = new StyleAttribute(true, (Class)Boolean.class, (T)Boolean.FALSE);
/*  67 */   public static final StyleAttribute<Color> COLOR = new StyleAttribute(true, (Class)Color.class, (T)Color.WHITE);
/*  68 */   public static final StyleAttribute<Color> COLOR_HOVER = new StyleAttribute(true, (Class)Color.class, null);
/*  69 */   public static final StyleAttribute<Boolean> INHERIT_HOVER = new StyleAttribute(true, (Class)Boolean.class, (T)Boolean.FALSE);
/*     */ 
/*     */   
/*  72 */   public static final StyleAttribute<TextAreaModel.Clear> CLEAR = new StyleAttribute(false, (Class)TextAreaModel.Clear.class, (T)TextAreaModel.Clear.NONE);
/*  73 */   public static final StyleAttribute<TextAreaModel.Display> DISPLAY = new StyleAttribute(false, (Class)TextAreaModel.Display.class, (T)TextAreaModel.Display.INLINE);
/*  74 */   public static final StyleAttribute<TextAreaModel.FloatPosition> FLOAT_POSITION = new StyleAttribute(false, (Class)TextAreaModel.FloatPosition.class, (T)TextAreaModel.FloatPosition.NONE);
/*  75 */   public static final StyleAttribute<Value> WIDTH = new StyleAttribute(false, (Class)Value.class, (T)Value.AUTO);
/*  76 */   public static final StyleAttribute<Value> HEIGHT = new StyleAttribute(false, (Class)Value.class, (T)Value.AUTO);
/*  77 */   public static final StyleAttribute<String> BACKGROUND_IMAGE = new StyleAttribute(false, (Class)String.class, null);
/*  78 */   public static final StyleAttribute<Color> BACKGROUND_COLOR = new StyleAttribute(false, (Class)Color.class, (T)Color.TRANSPARENT);
/*  79 */   public static final StyleAttribute<Color> BACKGROUND_COLOR_HOVER = new StyleAttribute(false, (Class)Color.class, (T)Color.TRANSPARENT);
/*  80 */   public static final StyleAttribute<Value> MARGIN_TOP = new StyleAttribute(false, (Class)Value.class, (T)Value.ZERO_PX);
/*  81 */   public static final StyleAttribute<Value> MARGIN_LEFT = new StyleAttribute(false, (Class)Value.class, (T)Value.ZERO_PX);
/*  82 */   public static final StyleAttribute<Value> MARGIN_RIGHT = new StyleAttribute(false, (Class)Value.class, (T)Value.ZERO_PX);
/*  83 */   public static final StyleAttribute<Value> MARGIN_BOTTOM = new StyleAttribute(false, (Class)Value.class, (T)Value.ZERO_PX);
/*  84 */   public static final StyleAttribute<Value> PADDING_TOP = new StyleAttribute(false, (Class)Value.class, (T)Value.ZERO_PX);
/*  85 */   public static final StyleAttribute<Value> PADDING_LEFT = new StyleAttribute(false, (Class)Value.class, (T)Value.ZERO_PX);
/*  86 */   public static final StyleAttribute<Value> PADDING_RIGHT = new StyleAttribute(false, (Class)Value.class, (T)Value.ZERO_PX);
/*  87 */   public static final StyleAttribute<Value> PADDING_BOTTOM = new StyleAttribute(false, (Class)Value.class, (T)Value.ZERO_PX);
/*     */ 
/*     */   
/*  90 */   public static final BoxAttribute MARGIN = new BoxAttribute(MARGIN_TOP, MARGIN_LEFT, MARGIN_RIGHT, MARGIN_BOTTOM);
/*  91 */   public static final BoxAttribute PADDING = new BoxAttribute(PADDING_TOP, PADDING_LEFT, PADDING_RIGHT, PADDING_BOTTOM);
/*     */   
/*     */   private final boolean inherited;
/*     */   private final Class<T> dataType;
/*     */   private final T defaultValue;
/*     */   private final int ordinal;
/*     */   
/*     */   public boolean isInherited() {
/*  99 */     return this.inherited;
/*     */   }
/*     */   
/*     */   public Class<T> getDataType() {
/* 103 */     return this.dataType;
/*     */   }
/*     */   
/*     */   public T getDefaultValue() {
/* 107 */     return this.defaultValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int ordinal() {
/* 117 */     return this.ordinal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String name() {
/*     */     try {
/* 127 */       for (Field f : StyleAttribute.class.getFields()) {
/* 128 */         if (Modifier.isStatic(f.getModifiers()) && f.get(null) == this) {
/* 129 */           return f.getName();
/*     */         }
/*     */       } 
/* 132 */     } catch (Throwable ex) {}
/*     */ 
/*     */     
/* 135 */     return "?";
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 140 */     return name();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private StyleAttribute(boolean inherited, Class<T> dataType, T defaultValue) {
/* 150 */     this.inherited = inherited;
/* 151 */     this.dataType = dataType;
/* 152 */     this.defaultValue = defaultValue;
/* 153 */     this.ordinal = attributes.size();
/* 154 */     attributes.add(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getNumAttributes() {
/* 162 */     return attributes.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static StyleAttribute<?> getAttribute(int ordinal) throws IndexOutOfBoundsException {
/* 173 */     return attributes.get(ordinal);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static StyleAttribute<?> getAttribute(String name) throws IllegalArgumentException {
/*     */     try {
/* 185 */       Field f = StyleAttribute.class.getField(name);
/* 186 */       if (Modifier.isStatic(f.getModifiers()) && f.getType() == StyleAttribute.class)
/*     */       {
/* 188 */         return (StyleAttribute)f.get(null);
/*     */       }
/* 190 */     } catch (Throwable ex) {}
/*     */ 
/*     */     
/* 193 */     throw new IllegalArgumentException("No style attribute " + name);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\textarea\StyleAttribute.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */