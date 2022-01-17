/*     */ package drzhark.mocreatures.configuration;
/*     */ 
/*     */ import com.google.common.base.CharMatcher;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.PushbackInputStream;
/*     */ import java.io.Reader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import net.minecraftforge.fml.common.Loader;
/*     */ import net.minecraftforge.fml.relauncher.FMLInjectionData;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MoCConfiguration
/*     */ {
/*     */   public static final String CATEGORY_GENERAL = "general";
/*     */   public static final String CATEGORY_BLOCK = "block";
/*     */   public static final String CATEGORY_ITEM = "item";
/*     */   public static final String ALLOWED_CHARS = "._-";
/*     */   public static final String DEFAULT_ENCODING = "UTF-8";
/*     */   public static final String CATEGORY_SPLITTER = ".";
/*     */   public static final String NEW_LINE;
/*  51 */   private static final Pattern CONFIG_START = Pattern.compile("START: \"([^\\\"]+)\"");
/*  52 */   private static final Pattern CONFIG_END = Pattern.compile("END: \"([^\\\"]+)\"");
/*  53 */   public static final CharMatcher allowedProperties = CharMatcher.JAVA_LETTER_OR_DIGIT.or(CharMatcher.anyOf("._-"));
/*  54 */   private static MoCConfiguration PARENT = null;
/*     */   
/*     */   File file;
/*     */   
/*  58 */   public Map<String, MoCConfigCategory> categories = new TreeMap<>();
/*  59 */   private Map<String, MoCConfiguration> children = new TreeMap<>();
/*     */   
/*     */   private boolean caseSensitiveCustomCategories;
/*  62 */   public String defaultEncoding = "UTF-8";
/*  63 */   private String fileName = null;
/*     */   public boolean isChild = false;
/*     */   private boolean changed = false;
/*     */   
/*     */   static {
/*  68 */     NEW_LINE = System.getProperty("line.separator");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MoCConfiguration(File file) {
/*  78 */     this.file = file;
/*  79 */     String basePath = ((File)FMLInjectionData.data()[6]).getAbsolutePath().replace(File.separatorChar, '/').replace("/.", "");
/*  80 */     String path = file.getAbsolutePath().replace(File.separatorChar, '/').replace("/./", "/").replace(basePath, "");
/*  81 */     if (PARENT != null) {
/*  82 */       PARENT.setChild(path, this);
/*  83 */       this.isChild = true;
/*     */     } else {
/*  85 */       this.fileName = path;
/*  86 */       load();
/*     */     } 
/*     */   }
/*     */   
/*     */   public MoCConfiguration(File file, boolean caseSensitiveCustomCategories) {
/*  91 */     this(file);
/*  92 */     this.caseSensitiveCustomCategories = caseSensitiveCustomCategories;
/*     */   }
/*     */   
/*     */   public MoCConfiguration(File file, boolean caseSensitiveCustomCategories, boolean useNewLine) {
/*  96 */     this(file);
/*     */   }
/*     */   
/*     */   public MoCProperty get(String category, String key) {
/* 100 */     MoCConfigCategory cat = getCategory(category);
/* 101 */     if (cat.containsKey(key)) {
/* 102 */       MoCProperty prop = cat.get(key);
/* 103 */       return prop;
/*     */     } 
/* 105 */     return null;
/*     */   }
/*     */   
/*     */   public MoCProperty get(String category, String key, int defaultValue) {
/* 109 */     return get(category, key, defaultValue, (String)null);
/*     */   }
/*     */   
/*     */   public MoCProperty get(String category, String key, int defaultValue, String comment) {
/* 113 */     MoCProperty prop = get(category, key, Integer.toString(defaultValue), comment, MoCProperty.Type.INTEGER);
/* 114 */     if (!prop.isIntValue()) {
/* 115 */       prop.set(defaultValue);
/*     */     }
/* 117 */     return prop;
/*     */   }
/*     */   
/*     */   public MoCProperty get(String category, String key, boolean defaultValue) {
/* 121 */     return get(category, key, defaultValue, (String)null);
/*     */   }
/*     */   
/*     */   public MoCProperty get(String category, String key, boolean defaultValue, String comment) {
/* 125 */     MoCProperty prop = get(category, key, Boolean.toString(defaultValue), comment, MoCProperty.Type.BOOLEAN);
/* 126 */     if (!prop.isBooleanValue()) {
/* 127 */       prop.set(defaultValue);
/*     */     }
/* 129 */     return prop;
/*     */   }
/*     */   
/*     */   public MoCProperty get(String category, String key, double defaultValue) {
/* 133 */     return get(category, key, defaultValue, (String)null);
/*     */   }
/*     */   
/*     */   public MoCProperty get(String category, String key, double defaultValue, String comment) {
/* 137 */     MoCProperty prop = get(category, key, Double.toString(defaultValue), comment, MoCProperty.Type.DOUBLE);
/* 138 */     if (!prop.isDoubleValue()) {
/* 139 */       prop.set(defaultValue);
/*     */     }
/* 141 */     return prop;
/*     */   }
/*     */   
/*     */   public MoCProperty get(String category, String key, String defaultValue) {
/* 145 */     return get(category, key, defaultValue, (String)null);
/*     */   }
/*     */   
/*     */   public MoCProperty get(String category, String key, String defaultValue, String comment) {
/* 149 */     return get(category, key, defaultValue, comment, MoCProperty.Type.STRING);
/*     */   }
/*     */   
/*     */   public MoCProperty get(String category, String key, List<String> defaultValue) {
/* 153 */     return get(category, key, defaultValue, (String)null);
/*     */   }
/*     */   
/*     */   public MoCProperty get(String category, String key, List<String> defaultValue, String comment) {
/* 157 */     return get(category, key, defaultValue, comment, MoCProperty.Type.STRING);
/*     */   }
/*     */   
/*     */   public MoCProperty get(String category, String key, int[] defaultValue) {
/* 161 */     return get(category, key, defaultValue, (String)null);
/*     */   }
/*     */   
/*     */   public MoCProperty get(String category, String key, int[] defaultValue, String comment) {
/* 165 */     List<String> values = new ArrayList<>();
/* 166 */     for (int i = 0; i < defaultValue.length; i++) {
/* 167 */       values.add(Integer.toString(defaultValue[i]));
/*     */     }
/*     */     
/* 170 */     MoCProperty prop = get(category, key, values, comment, MoCProperty.Type.INTEGER);
/* 171 */     if (!prop.isIntList()) {
/* 172 */       prop.valueList = values;
/*     */     }
/*     */     
/* 175 */     return prop;
/*     */   }
/*     */   
/*     */   public MoCProperty get(String category, String key, double[] defaultValue) {
/* 179 */     return get(category, key, defaultValue, (String)null);
/*     */   }
/*     */   
/*     */   public MoCProperty get(String category, String key, double[] defaultValue, String comment) {
/* 183 */     List<String> values = new ArrayList<>();
/* 184 */     for (int i = 0; i < defaultValue.length; i++) {
/* 185 */       values.add(Double.toString(defaultValue[i]));
/*     */     }
/*     */     
/* 188 */     MoCProperty prop = get(category, key, values, comment, MoCProperty.Type.DOUBLE);
/*     */     
/* 190 */     if (!prop.isDoubleList()) {
/* 191 */       prop.valueList = values;
/*     */     }
/*     */     
/* 194 */     return prop;
/*     */   }
/*     */   
/*     */   public MoCProperty get(String category, String key, boolean[] defaultValue) {
/* 198 */     return get(category, key, defaultValue, (String)null);
/*     */   }
/*     */   
/*     */   public MoCProperty get(String category, String key, boolean[] defaultValue, String comment) {
/* 202 */     List<String> values = new ArrayList<>();
/* 203 */     for (int i = 0; i < defaultValue.length; i++) {
/* 204 */       values.add(Boolean.toString(defaultValue[i]));
/*     */     }
/*     */     
/* 207 */     MoCProperty prop = get(category, key, values, comment, MoCProperty.Type.BOOLEAN);
/*     */     
/* 209 */     if (!prop.isBooleanList()) {
/* 210 */       prop.valueList = values;
/*     */     }
/*     */     
/* 213 */     return prop;
/*     */   }
/*     */   
/*     */   public MoCProperty get(String category, String key, String defaultValue, String comment, MoCProperty.Type type) {
/* 217 */     if (!this.caseSensitiveCustomCategories) {
/* 218 */       category = category.toLowerCase(Locale.ENGLISH);
/*     */     }
/*     */     
/* 221 */     MoCConfigCategory cat = getCategory(category);
/*     */     
/* 223 */     if (cat.containsKey(key)) {
/* 224 */       MoCProperty prop = cat.get(key);
/*     */       
/* 226 */       if (prop.getType() == null) {
/* 227 */         prop = new MoCProperty(prop.getName(), prop.value, type);
/* 228 */         cat.set(key, prop);
/*     */       } 
/*     */       
/* 231 */       prop.comment = comment;
/* 232 */       return prop;
/* 233 */     }  if (defaultValue != null) {
/* 234 */       MoCProperty prop = new MoCProperty(key, defaultValue, type);
/* 235 */       prop.set(defaultValue);
/* 236 */       cat.put(key, prop);
/* 237 */       prop.comment = comment;
/* 238 */       return prop;
/*     */     } 
/* 240 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public MoCProperty get(String category, String key, List<String> defaultValue, String comment, MoCProperty.Type type) {
/* 245 */     if (!this.caseSensitiveCustomCategories) {
/* 246 */       category = category.toLowerCase(Locale.ENGLISH);
/*     */     }
/*     */     
/* 249 */     MoCConfigCategory cat = getCategory(category);
/*     */     
/* 251 */     if (cat.containsKey(key)) {
/* 252 */       MoCProperty prop = cat.get(key);
/*     */       
/* 254 */       if (prop.getType() == null) {
/* 255 */         prop = new MoCProperty(prop.getName(), prop.getString(), type);
/* 256 */         cat.put(key, prop);
/*     */       } 
/*     */       
/* 259 */       prop.comment = comment;
/*     */       
/* 261 */       return prop;
/* 262 */     }  if (defaultValue != null) {
/* 263 */       MoCProperty prop = new MoCProperty(key, defaultValue, type);
/* 264 */       prop.comment = comment;
/* 265 */       cat.put(key, prop);
/* 266 */       return prop;
/*     */     } 
/* 268 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasCategory(String category) {
/* 273 */     return (this.categories.get(category) != null);
/*     */   }
/*     */   
/*     */   public boolean hasKey(String category, String key) {
/* 277 */     MoCConfigCategory cat = this.categories.get(category);
/* 278 */     return (cat != null && cat.containsKey(key));
/*     */   }
/*     */   
/*     */   public void load() {
/* 282 */     if (PARENT != null && PARENT != this) {
/*     */       return;
/*     */     }
/*     */     
/* 286 */     BufferedReader buffer = null;
/* 287 */     UnicodeInputStreamReader input = null;
/*     */     try {
/* 289 */       if (this.file.getParentFile() != null) {
/* 290 */         this.file.getParentFile().mkdirs();
/*     */       }
/*     */       
/* 293 */       if (!this.file.exists() && !this.file.createNewFile()) {
/*     */         return;
/*     */       }
/*     */       
/* 297 */       if (this.file.canRead()) {
/* 298 */         input = new UnicodeInputStreamReader(new FileInputStream(this.file), this.defaultEncoding);
/* 299 */         this.defaultEncoding = input.getEncoding();
/* 300 */         buffer = new BufferedReader(input);
/*     */ 
/*     */         
/* 303 */         MoCConfigCategory currentCat = null;
/* 304 */         MoCProperty.Type type = null;
/* 305 */         ArrayList<String> tmpList = null;
/* 306 */         int lineNum = 0;
/* 307 */         String name = null;
/*     */         
/*     */         while (true) {
/* 310 */           lineNum++;
/* 311 */           String line = buffer.readLine();
/* 312 */           if (line == null) {
/*     */             break;
/*     */           }
/*     */           
/* 316 */           Matcher start = CONFIG_START.matcher(line);
/* 317 */           Matcher end = CONFIG_END.matcher(line);
/*     */           
/* 319 */           if (start.matches()) {
/* 320 */             this.fileName = start.group(1);
/*     */             
/* 322 */             this.categories = new TreeMap<>(); continue;
/*     */           } 
/* 324 */           if (end.matches()) {
/* 325 */             this.fileName = end.group(1);
/* 326 */             MoCConfiguration child = new MoCConfiguration();
/* 327 */             child.categories = this.categories;
/* 328 */             this.children.put(this.fileName, child);
/*     */             
/*     */             continue;
/*     */           } 
/* 332 */           int nameStart = -1, nameEnd = -1;
/* 333 */           boolean skip = false;
/* 334 */           boolean quoted = false;
/*     */           
/* 336 */           for (int i = 0; i < line.length() && !skip; i++) {
/* 337 */             if (Character.isLetterOrDigit(line.charAt(i)) || "._-".indexOf(line.charAt(i)) != -1 || (quoted && line
/* 338 */               .charAt(i) != '"')) {
/* 339 */               if (nameStart == -1) {
/* 340 */                 nameStart = i;
/*     */               }
/*     */               
/* 343 */               nameEnd = i;
/* 344 */             } else if (!Character.isWhitespace(line.charAt(i))) {
/*     */               String qualifiedName; MoCConfigCategory cat;
/*     */               MoCProperty prop;
/* 347 */               switch (line.charAt(i)) {
/*     */                 case '#':
/* 349 */                   skip = true;
/*     */                   break;
/*     */                 
/*     */                 case '"':
/* 353 */                   if (quoted) {
/* 354 */                     quoted = false;
/*     */                   }
/* 356 */                   if (!quoted && nameStart == -1) {
/* 357 */                     quoted = true;
/*     */                   }
/*     */                   break;
/*     */                 
/*     */                 case '{':
/* 362 */                   name = line.substring(nameStart, nameEnd + 1);
/* 363 */                   qualifiedName = MoCConfigCategory.getQualifiedName(name, currentCat);
/*     */                   
/* 365 */                   cat = this.categories.get(qualifiedName);
/* 366 */                   if (cat == null) {
/* 367 */                     currentCat = new MoCConfigCategory(name, currentCat);
/* 368 */                     this.categories.put(qualifiedName, currentCat);
/*     */                   } else {
/* 370 */                     currentCat = cat;
/*     */                   } 
/* 372 */                   name = null;
/*     */                   break;
/*     */ 
/*     */                 
/*     */                 case '}':
/* 377 */                   if (currentCat == null) {
/* 378 */                     throw new RuntimeException(String.format("Config file corrupt, attepted to close to many categories '%s:%d'", new Object[] { this.fileName, 
/* 379 */                             Integer.valueOf(lineNum) }));
/*     */                   }
/* 381 */                   currentCat = currentCat.parent;
/*     */                   break;
/*     */ 
/*     */                 
/*     */                 case '=':
/* 386 */                   name = line.substring(nameStart, nameEnd + 1);
/*     */                   
/* 388 */                   if (currentCat == null) {
/* 389 */                     throw new RuntimeException(String.format("'%s' has no scope in '%s:%d'", new Object[] { name, this.fileName, Integer.valueOf(lineNum) }));
/*     */                   }
/*     */                   
/* 392 */                   prop = new MoCProperty(name, line.substring(i + 1), type, true);
/* 393 */                   i = line.length();
/*     */                   
/* 395 */                   currentCat.set(name, prop);
/*     */                   break;
/*     */ 
/*     */                 
/*     */                 case ':':
/* 400 */                   type = MoCProperty.Type.tryParse(line.substring(nameStart, nameEnd + 1).charAt(0));
/* 401 */                   nameStart = nameEnd = -1;
/*     */                   break;
/*     */ 
/*     */                 
/*     */                 case '<':
/* 406 */                   if (tmpList != null) {
/* 407 */                     throw new RuntimeException(String.format("Malformed list MoCProperty \"%s:%d\"", new Object[] { this.fileName, Integer.valueOf(lineNum) }));
/*     */                   }
/*     */                   
/* 410 */                   name = line.substring(nameStart, nameEnd + 1);
/*     */                   
/* 412 */                   if (currentCat == null) {
/* 413 */                     throw new RuntimeException(String.format("'%s' has no scope in '%s:%d'", new Object[] { name, this.fileName, Integer.valueOf(lineNum) }));
/*     */                   }
/*     */                   
/* 416 */                   tmpList = new ArrayList<>();
/*     */                   
/* 418 */                   if (line.length() > i + 1) {
/* 419 */                     if (line.charAt(i + 1) == '>') {
/* 420 */                       i++;
/*     */                     } else {
/* 422 */                       line = line.substring(i + 1, line.length());
/* 423 */                       String[] values = line.split(":|\\>");
/* 424 */                       for (int j = 0; j < values.length; j++) {
/* 425 */                         tmpList.add(values[j]);
/*     */                       }
/* 427 */                       i = line.length() - 1;
/*     */                     } 
/*     */                   } else {
/* 430 */                     skip = true;
/*     */                     break;
/*     */                   } 
/*     */                 
/*     */                 case '>':
/* 435 */                   if (tmpList == null) {
/* 436 */                     throw new RuntimeException(String.format("Corrupted config detected! Malformed list MoCProperty \"%s:%d\". Please delete your MoCreatures/CMS configs and restart to fix error.", new Object[] { this.fileName, Integer.valueOf(lineNum) }));
/*     */                   }
/* 438 */                   currentCat.set(name, new MoCProperty(name, tmpList, type));
/* 439 */                   name = null;
/* 440 */                   tmpList = null;
/* 441 */                   type = null;
/*     */                   break;
/*     */                 
/*     */                 default:
/* 445 */                   throw new RuntimeException(String.format("Corrupted config detected! Unknown character '%s' in '%s:%d'. Please delete your MoCreatures/CMS configs and restart to fix error.", new Object[] { Character.valueOf(line.charAt(i)), this.fileName, 
/* 446 */                           Integer.valueOf(lineNum) }));
/*     */               } 
/*     */             
/*     */             } 
/*     */           } 
/* 451 */           if (quoted)
/* 452 */             throw new RuntimeException(String.format("Corrupted config detected! Unmatched quote in '%s:%d'. Please delete your MoCreatures/CMS configs and restart to fix error.", new Object[] { this.fileName, Integer.valueOf(lineNum) })); 
/* 453 */           if (tmpList != null && !skip) {
/* 454 */             tmpList.add(line.trim());
/*     */           }
/*     */         } 
/*     */       } 
/* 458 */     } catch (IOException e) {
/* 459 */       e.printStackTrace();
/*     */     } finally {
/* 461 */       if (buffer != null) {
/*     */         try {
/* 463 */           buffer.close();
/* 464 */         } catch (IOException iOException) {}
/*     */       }
/*     */       
/* 467 */       if (input != null) {
/*     */         try {
/* 469 */           input.close();
/* 470 */         } catch (IOException iOException) {}
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 475 */     resetChangedState();
/*     */   }
/*     */   
/*     */   public void save() {
/* 479 */     if (PARENT != null && PARENT != this) {
/* 480 */       PARENT.save();
/*     */       
/*     */       return;
/*     */     } 
/*     */     try {
/* 485 */       if (this.file.getParentFile() != null) {
/* 486 */         this.file.getParentFile().mkdirs();
/*     */       }
/*     */       
/* 489 */       if (!this.file.exists() && !this.file.createNewFile()) {
/*     */         return;
/*     */       }
/* 492 */       if (this.file.canWrite()) {
/* 493 */         FileOutputStream fos = new FileOutputStream(this.file);
/* 494 */         BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(fos, this.defaultEncoding));
/*     */         
/* 496 */         buffer.write("# Configuration file" + NEW_LINE + NEW_LINE);
/*     */         
/* 498 */         if (this.children.isEmpty()) {
/* 499 */           save(buffer);
/*     */         } else {
/* 501 */           for (Map.Entry<String, MoCConfiguration> entry : this.children.entrySet()) {
/* 502 */             buffer.write("START: \"" + (String)entry.getKey() + "\"" + NEW_LINE);
/* 503 */             ((MoCConfiguration)entry.getValue()).save(buffer);
/* 504 */             buffer.write("END: \"" + (String)entry.getKey() + "\"" + NEW_LINE + NEW_LINE);
/*     */           } 
/*     */         } 
/*     */         
/* 508 */         buffer.close();
/* 509 */         fos.close();
/*     */       } 
/* 511 */     } catch (IOException e) {
/* 512 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void save(BufferedWriter out) throws IOException {
/* 517 */     for (MoCConfigCategory cat : this.categories.values()) {
/* 518 */       if (!cat.isChild()) {
/*     */         
/* 520 */         cat.write(out, 0);
/* 521 */         out.newLine();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public MoCConfigCategory getCategory(String category) {
/* 527 */     MoCConfigCategory ret = this.categories.get(category.toLowerCase());
/*     */     
/* 529 */     if (ret == null) {
/* 530 */       if (category.contains(".")) {
/* 531 */         String[] hierarchy = category.split("\\.");
/* 532 */         MoCConfigCategory parent = this.categories.get(hierarchy[0]);
/*     */         
/* 534 */         if (parent == null) {
/* 535 */           parent = new MoCConfigCategory(hierarchy[0]);
/* 536 */           this.categories.put(parent.getQualifiedName(), parent);
/* 537 */           this.changed = true;
/*     */         } 
/*     */         
/* 540 */         for (int i = 1; i < hierarchy.length; i++) {
/* 541 */           String name = MoCConfigCategory.getQualifiedName(hierarchy[i], parent);
/* 542 */           MoCConfigCategory child = this.categories.get(name);
/*     */           
/* 544 */           if (child == null) {
/* 545 */             child = new MoCConfigCategory(hierarchy[i], parent);
/* 546 */             this.categories.put(name, child);
/* 547 */             this.changed = true;
/*     */           } 
/*     */           
/* 550 */           ret = child;
/* 551 */           parent = child;
/*     */         } 
/*     */       } else {
/* 554 */         ret = new MoCConfigCategory(category);
/* 555 */         this.categories.put(category, ret);
/* 556 */         this.changed = true;
/*     */       } 
/*     */     }
/*     */     
/* 560 */     return ret;
/*     */   }
/*     */   
/*     */   public void removeCategory(MoCConfigCategory category) {
/* 564 */     for (MoCConfigCategory child : category.getChildren()) {
/* 565 */       removeCategory(child);
/*     */     }
/*     */     
/* 568 */     if (this.categories.containsKey(category.getQualifiedName())) {
/* 569 */       this.categories.remove(category.getQualifiedName());
/* 570 */       if (category.parent != null) {
/* 571 */         category.parent.removeChild(category);
/*     */       }
/* 573 */       this.changed = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addCustomCategoryComment(String category, String comment) {
/* 578 */     if (!this.caseSensitiveCustomCategories) {
/* 579 */       category = category.toLowerCase(Locale.ENGLISH);
/*     */     }
/* 581 */     getCategory(category).setComment(comment);
/*     */   }
/*     */   
/*     */   private void setChild(String name, MoCConfiguration child) {
/* 585 */     if (!this.children.containsKey(name)) {
/* 586 */       this.children.put(name, child);
/* 587 */       this.changed = true;
/*     */     } else {
/* 589 */       MoCConfiguration old = this.children.get(name);
/* 590 */       child.categories = old.categories;
/* 591 */       child.fileName = old.fileName;
/* 592 */       old.changed = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void enableGlobalConfig() {
/* 597 */     PARENT = new MoCConfiguration(new File(Loader.instance().getConfigDir(), "global.cfg"));
/* 598 */     PARENT.load();
/*     */   }
/*     */   
/*     */   public static class UnicodeInputStreamReader
/*     */     extends Reader {
/*     */     private final InputStreamReader input;
/*     */     
/*     */     public UnicodeInputStreamReader(InputStream source, String encoding) throws IOException {
/* 606 */       String enc = encoding;
/* 607 */       byte[] data = new byte[4];
/*     */       
/* 609 */       PushbackInputStream pbStream = new PushbackInputStream(source, data.length);
/* 610 */       int read = pbStream.read(data, 0, data.length);
/* 611 */       int size = 0;
/*     */       
/* 613 */       int bom16 = (data[0] & 0xFF) << 8 | data[1] & 0xFF;
/* 614 */       int bom24 = bom16 << 8 | data[2] & 0xFF;
/* 615 */       int bom32 = bom24 << 8 | data[3] & 0xFF;
/*     */       
/* 617 */       if (bom24 == 15711167) {
/* 618 */         enc = "UTF-8";
/* 619 */         size = 3;
/* 620 */       } else if (bom16 == 65279) {
/* 621 */         enc = "UTF-16BE";
/* 622 */         size = 2;
/* 623 */       } else if (bom16 == 65534) {
/* 624 */         enc = "UTF-16LE";
/* 625 */         size = 2;
/* 626 */       } else if (bom32 == 65279) {
/* 627 */         enc = "UTF-32BE";
/* 628 */         size = 4;
/* 629 */       } else if (bom32 == -131072) {
/*     */         
/* 631 */         enc = "UTF-32LE";
/* 632 */         size = 4;
/*     */       } 
/*     */       
/* 635 */       if (size < read) {
/* 636 */         pbStream.unread(data, size, read - size);
/*     */       }
/*     */       
/* 639 */       this.input = new InputStreamReader(pbStream, enc);
/*     */     }
/*     */     
/*     */     public String getEncoding() {
/* 643 */       return this.input.getEncoding();
/*     */     }
/*     */ 
/*     */     
/*     */     public int read(char[] cbuf, int off, int len) throws IOException {
/* 648 */       return this.input.read(cbuf, off, len);
/*     */     }
/*     */ 
/*     */     
/*     */     public void close() throws IOException {
/* 653 */       this.input.close();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean hasChanged() {
/* 658 */     if (this.changed) {
/* 659 */       return true;
/*     */     }
/*     */     
/* 662 */     for (MoCConfigCategory cat : this.categories.values()) {
/* 663 */       if (cat.hasChanged()) {
/* 664 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 668 */     for (MoCConfiguration child : this.children.values()) {
/* 669 */       if (child.hasChanged()) {
/* 670 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 674 */     return false;
/*     */   }
/*     */   
/*     */   private void resetChangedState() {
/* 678 */     this.changed = false;
/* 679 */     for (MoCConfigCategory cat : this.categories.values()) {
/* 680 */       cat.resetChangedState();
/*     */     }
/*     */     
/* 683 */     for (MoCConfiguration child : this.children.values()) {
/* 684 */       child.resetChangedState();
/*     */     }
/*     */   }
/*     */   
/*     */   public Set<String> getCategoryNames() {
/* 689 */     return (Set<String>)ImmutableSet.copyOf(this.categories.keySet());
/*     */   }
/*     */   
/*     */   public String getFileName() {
/* 693 */     if (this.file != null) {
/* 694 */       String fullName = this.file.getName();
/* 695 */       return fullName.substring(0, fullName.indexOf('.'));
/*     */     } 
/* 697 */     return "undefined";
/*     */   }
/*     */   
/*     */   public File getFile() {
/* 701 */     return this.file;
/*     */   }
/*     */   
/*     */   public MoCConfiguration() {}
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\configuration\MoCConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */