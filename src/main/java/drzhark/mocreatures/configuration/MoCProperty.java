/*     */ package drzhark.mocreatures.configuration;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class MoCProperty {
/*     */   private String name;
/*     */   public String value;
/*     */   public String comment;
/*     */   public List<String> valueList;
/*     */   private final boolean wasRead;
/*     */   private final boolean isList;
/*     */   private final Type type;
/*     */
/*     */   public enum Type {
/*  14 */     STRING, INTEGER, BOOLEAN, DOUBLE;
/*     */
/*  16 */     private static Type[] values = new Type[] { STRING, INTEGER, BOOLEAN, DOUBLE };
/*     */
/*     */     public static Type tryParse(char id) {
/*  19 */       for (int x = 0; x < values.length; x++) {
/*  20 */         if (values[x].getID() == id) {
/*  21 */           return values[x];
/*     */         }
/*     */       }
/*     */
/*  25 */       return STRING;
/*     */     } static {
/*     */
/*     */     } public char getID() {
/*  29 */       return name().charAt(0);
/*     */     }
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   private boolean changed = false;
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public MoCProperty() {
/*  44 */     this.wasRead = false;
/*  45 */     this.type = null;
/*  46 */     this.isList = false;
/*     */   }
/*     */
/*     */   public MoCProperty(String name, String value, Type type) {
/*  50 */     this(name, value, type, false);
/*     */   }
/*     */
/*     */   public MoCProperty(String name, String value, Type type, String comment) {
/*  54 */     this(name, value, type, false);
/*  55 */     this.comment = comment;
/*     */   }
/*     */
/*     */   MoCProperty(String name, String value, Type type, boolean read) {
/*  59 */     setName(name);
/*  60 */     this.value = value;
/*  61 */     this.type = type;
/*  62 */     this.wasRead = read;
/*  63 */     this.isList = false;
/*     */   }
/*     */
/*     */   public MoCProperty(String name, List<String> values, Type type) {
/*  67 */     this(name, values, type, false);
/*     */   }
/*     */
/*     */   public MoCProperty(String name, List<String> values, Type type, String comment) {
/*  71 */     this(name, values, type, false);
/*  72 */     this.comment = comment;
/*     */   }
/*     */
/*     */   MoCProperty(String name, List<String> values, Type type, boolean read) {
/*  76 */     setName(name);
/*  77 */     this.type = type;
/*  78 */     this.valueList = values;
/*  79 */     this.wasRead = read;
/*  80 */     this.isList = true;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public String getString() {
/*  89 */     return this.value;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public int getInt() {
/*  99 */     return getInt(-1);
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
/*     */   public int getInt(int _default) {
/*     */     try {
/* 112 */       return Integer.parseInt(this.value);
/* 113 */     } catch (NumberFormatException e) {
/* 114 */       return _default;
/*     */     }
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public boolean isIntValue() {
/*     */     try {
/* 126 */       Integer.parseInt(this.value);
/* 127 */       return true;
/* 128 */     } catch (NumberFormatException e) {
/* 129 */       return false;
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
/*     */   public boolean getBoolean(boolean _default) {
/* 141 */     if (isBooleanValue()) {
/* 142 */       return Boolean.parseBoolean(this.value);
/*     */     }
/* 144 */     return _default;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public boolean isBooleanValue() {
/* 155 */     return ("true".equals(this.value.toLowerCase()) || "false".equals(this.value.toLowerCase()));
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public boolean isDoubleValue() {
/*     */     try {
/* 166 */       Double.parseDouble(this.value);
/* 167 */       return true;
/* 168 */     } catch (NumberFormatException e) {
/* 169 */       return false;
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
/*     */   public double getDouble(double _default) {
/*     */     try {
/* 183 */       return Double.parseDouble(this.value);
/* 184 */     } catch (NumberFormatException e) {
/* 185 */       return _default;
/*     */     }
/*     */   }
/*     */
/*     */   public List<String> getStringList() {
/* 190 */     return this.valueList;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public int[] getIntList() {
/* 199 */     ArrayList<Integer> nums = new ArrayList<>();
/*     */
/* 201 */     for (String value : this.valueList) {
/*     */       try {
/* 203 */         nums.add(Integer.valueOf(Integer.parseInt(value)));
/* 204 */       } catch (NumberFormatException numberFormatException) {}
/*     */     }
/*     */
/*     */
/* 208 */     int[] primitives = new int[nums.size()];
/*     */
/* 210 */     for (int i = 0; i < nums.size(); i++) {
/* 211 */       primitives[i] = ((Integer)nums.get(i)).intValue();
/*     */     }
/*     */
/* 214 */     return primitives;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public boolean isIntList() {
/* 224 */     for (String value : this.valueList) {
/*     */       try {
/* 226 */         Integer.parseInt(value);
/* 227 */       } catch (NumberFormatException e) {
/* 228 */         return false;
/*     */       }
/*     */     }
/* 231 */     return true;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public boolean[] getBooleanList() {
/* 240 */     ArrayList<Boolean> tmp = new ArrayList<>();
/* 241 */     for (String value : this.valueList) {
/*     */       try {
/* 243 */         tmp.add(Boolean.valueOf(Boolean.parseBoolean(value)));
/* 244 */       } catch (NumberFormatException numberFormatException) {}
/*     */     }
/*     */
/*     */
/* 248 */     boolean[] primitives = new boolean[tmp.size()];
/*     */
/* 250 */     for (int i = 0; i < tmp.size(); i++) {
/* 251 */       primitives[i] = ((Boolean)tmp.get(i)).booleanValue();
/*     */     }
/*     */
/* 254 */     return primitives;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public boolean isBooleanList() {
/* 264 */     for (String value : this.valueList) {
/* 265 */       if (!"true".equalsIgnoreCase(value) && !"false".equalsIgnoreCase(value)) {
/* 266 */         return false;
/*     */       }
/*     */     }
/*     */
/* 270 */     return true;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public double[] getDoubleList() {
/* 279 */     ArrayList<Double> tmp = new ArrayList<>();
/* 280 */     for (String value : this.valueList) {
/*     */       try {
/* 282 */         tmp.add(Double.valueOf(Double.parseDouble(value)));
/* 283 */       } catch (NumberFormatException numberFormatException) {}
/*     */     }
/*     */
/*     */
/* 287 */     double[] primitives = new double[tmp.size()];
/*     */
/* 289 */     for (int i = 0; i < tmp.size(); i++) {
/* 290 */       primitives[i] = ((Double)tmp.get(i)).doubleValue();
/*     */     }
/*     */
/* 293 */     return primitives;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public boolean isDoubleList() {
/* 303 */     for (String value : this.valueList) {
/*     */       try {
/* 305 */         Double.parseDouble(value);
/* 306 */       } catch (NumberFormatException e) {
/* 307 */         return false;
/*     */       }
/*     */     }
/*     */
/* 311 */     return true;
/*     */   }
/*     */
/*     */   public String getName() {
/* 315 */     return this.name;
/*     */   }
/*     */
/*     */   public void setName(String name) {
/* 319 */     this.name = name;
/*     */   }
/*     */
/*     */   public void setValueList(List<String> list) {
/* 323 */     this.valueList = list;
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
/*     */   public boolean wasRead() {
/* 336 */     return this.wasRead;
/*     */   }
/*     */
/*     */   public Type getType() {
/* 340 */     return this.type;
/*     */   }
/*     */
/*     */   public boolean isList() {
/* 344 */     return this.isList;
/*     */   }
/*     */
/*     */   public boolean hasChanged() {
/* 348 */     return this.changed;
/*     */   }
/*     */
/*     */   void resetChangedState() {
/* 352 */     this.changed = false;
/*     */   }
/*     */
/*     */   public void set(String value) {
/* 356 */     this.value = value;
/* 357 */     this.changed = true;
/*     */   }
/*     */
/*     */   public void set(List<String> values) {
/* 361 */     this.valueList = values;
/* 362 */     this.changed = true;
/*     */   }
/*     */
/*     */   public void set(int value) {
/* 366 */     set(Integer.toString(value));
/*     */   }
/*     */
/*     */   public void set(boolean value) {
/* 370 */     set(Boolean.toString(value));
/*     */   }
/*     */
/*     */   public void set(double value) {
/* 374 */     set(Double.toString(value));
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\configuration\MoCProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
