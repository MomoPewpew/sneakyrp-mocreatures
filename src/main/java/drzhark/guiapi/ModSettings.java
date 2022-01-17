/*     */ package drzhark.guiapi;
/*     */ 
/*     */ import de.matthiasmann.twl.Widget;
/*     */ import drzhark.guiapi.setting.Setting;
/*     */ import drzhark.guiapi.setting.SettingBoolean;
/*     */ import drzhark.guiapi.setting.SettingDictionary;
/*     */ import drzhark.guiapi.setting.SettingFloat;
/*     */ import drzhark.guiapi.setting.SettingInt;
/*     */ import drzhark.guiapi.setting.SettingKey;
/*     */ import drzhark.guiapi.setting.SettingList;
/*     */ import drzhark.guiapi.setting.SettingMulti;
/*     */ import drzhark.guiapi.setting.SettingText;
/*     */ import drzhark.guiapi.widget.WidgetBoolean;
/*     */ import drzhark.guiapi.widget.WidgetFloat;
/*     */ import drzhark.guiapi.widget.WidgetInt;
/*     */ import drzhark.guiapi.widget.WidgetKeybinding;
/*     */ import drzhark.guiapi.widget.WidgetList;
/*     */ import drzhark.guiapi.widget.WidgetMulti;
/*     */ import drzhark.guiapi.widget.WidgetText;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.security.InvalidParameterException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Properties;
/*     */ import net.minecraft.client.Minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModSettings
/*     */ {
/*  41 */   public static ArrayList<ModSettings> all = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   public static HashMap<String, String> contextDatadirs = new HashMap<>();
/*  57 */   public static String currentContext = ""; static {
/*  58 */     contextDatadirs.put("", "mods");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final boolean debug = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public String backendname;
/*     */ 
/*     */   
/*     */   public ArrayList<Setting> Settings;
/*     */ 
/*     */ 
/*     */   
/*     */   public static void dbgout(String s) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public static File getAppDir(String app) {
/*     */     try {
/*  81 */       return (new File((Minecraft.getMinecraft()).gameDir, app)).getCanonicalFile();
/*  82 */     } catch (IOException e) {
/*     */ 
/*     */ 
/*     */       
/*  86 */       return new File((Minecraft.getMinecraft()).gameDir, app);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Minecraft getMcinst() {
/*  97 */     return Minecraft.getMinecraft();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void loadAll(String context) {
/* 106 */     for (int i = 0; i < all.size(); i++) {
/* 107 */       ((ModSettings)all.get(i)).load(context);
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
/*     */   public static void setContext(String name, String location) {
/* 121 */     if (name != null) {
/* 122 */       contextDatadirs.put(name, location);
/* 123 */       currentContext = name;
/* 124 */       if (!name.equals("")) {
/* 125 */         loadAll(currentContext);
/*     */       }
/*     */     } else {
/* 128 */       currentContext = "";
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
/*     */   public boolean settingsLoaded = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModSettings(String modbackendname) {
/* 151 */     this.backendname = modbackendname;
/* 152 */     this.Settings = new ArrayList<>();
/* 153 */     all.add(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingBoolean addSetting(ModSettingScreen screen, String nicename, String backendname, boolean value) {
/* 160 */     SettingBoolean s = new SettingBoolean(backendname, Boolean.valueOf(value));
/* 161 */     WidgetBoolean w = new WidgetBoolean(s, nicename);
/* 162 */     screen.append((Widget)w);
/* 163 */     append((Setting)s);
/* 164 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingBoolean addSetting(ModSettingScreen screen, String nicename, String backendname, boolean value, String truestring, String falsestring) {
/* 172 */     SettingBoolean s = new SettingBoolean(backendname, Boolean.valueOf(value));
/* 173 */     WidgetBoolean w = new WidgetBoolean(s, nicename, truestring, falsestring);
/* 174 */     screen.append((Widget)w);
/* 175 */     append((Setting)s);
/* 176 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingFloat addSetting(ModSettingScreen screen, String nicename, String backendname, float value) {
/* 183 */     SettingFloat s = new SettingFloat(backendname, value);
/* 184 */     WidgetFloat w = new WidgetFloat(s, nicename);
/* 185 */     screen.append((Widget)w);
/* 186 */     append((Setting)s);
/* 187 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingFloat addSetting(ModSettingScreen screen, String nicename, String backendname, float value, float min, float step, float max) {
/* 194 */     SettingFloat s = new SettingFloat(backendname, value, min, step, max);
/* 195 */     WidgetFloat w = new WidgetFloat(s, nicename);
/* 196 */     screen.append((Widget)w);
/* 197 */     append((Setting)s);
/* 198 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingKey addSetting(ModSettingScreen screen, String nicename, String backendname, int value) {
/* 205 */     SettingKey s = new SettingKey(backendname, value);
/* 206 */     WidgetKeybinding w = new WidgetKeybinding(s, nicename);
/* 207 */     screen.append((Widget)w);
/* 208 */     append((Setting)s);
/* 209 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingInt addSetting(ModSettingScreen screen, String nicename, String backendname, int value, int min, int max) {
/* 216 */     SettingInt s = new SettingInt(backendname, value, min, 1, max);
/* 217 */     WidgetInt w = new WidgetInt(s, nicename);
/* 218 */     screen.append((Widget)w);
/* 219 */     append((Setting)s);
/* 220 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingInt addSetting(ModSettingScreen screen, String nicename, String backendname, int value, int min, int step, int max) {
/* 227 */     SettingInt s = new SettingInt(backendname, value, min, step, max);
/* 228 */     WidgetInt w = new WidgetInt(s, nicename);
/* 229 */     screen.append((Widget)w);
/* 230 */     append((Setting)s);
/* 231 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingMulti addSetting(ModSettingScreen screen, String nicename, String backendname, int value, String... labels) {
/* 238 */     SettingMulti s = new SettingMulti(backendname, value, labels);
/* 239 */     WidgetMulti w = new WidgetMulti(s, nicename);
/* 240 */     screen.append((Widget)w);
/* 241 */     append((Setting)s);
/* 242 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingText addSetting(ModSettingScreen screen, String nicename, String backendname, String value) {
/* 249 */     SettingText s = new SettingText(backendname, value);
/* 250 */     WidgetText w = new WidgetText(s, nicename);
/* 251 */     screen.append((Widget)w);
/* 252 */     append((Setting)s);
/* 253 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingBoolean addSetting(Widget w2, String nicename, String backendname, boolean value) {
/* 260 */     SettingBoolean s = new SettingBoolean(backendname, Boolean.valueOf(value));
/* 261 */     WidgetBoolean w = new WidgetBoolean(s, nicename);
/* 262 */     w2.add((Widget)w);
/* 263 */     append((Setting)s);
/* 264 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingBoolean addSetting(Widget w2, String nicename, String backendname, boolean value, String truestring, String falsestring) {
/* 271 */     SettingBoolean s = new SettingBoolean(backendname, Boolean.valueOf(value));
/* 272 */     WidgetBoolean w = new WidgetBoolean(s, nicename, truestring, falsestring);
/* 273 */     w2.add((Widget)w);
/* 274 */     append((Setting)s);
/* 275 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingFloat addSetting(Widget w2, String nicename, String backendname, float value) {
/* 282 */     SettingFloat s = new SettingFloat(backendname, value);
/* 283 */     WidgetFloat w = new WidgetFloat(s, nicename);
/* 284 */     w2.add((Widget)w);
/* 285 */     append((Setting)s);
/* 286 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingFloat addSetting(Widget w2, String nicename, String backendname, float value, float min, float step, float max) {
/* 293 */     SettingFloat s = new SettingFloat(backendname, value, min, step, max);
/* 294 */     WidgetFloat w = new WidgetFloat(s, nicename);
/* 295 */     w2.add((Widget)w);
/* 296 */     append((Setting)s);
/* 297 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingKey addSetting(Widget w2, String nicename, String backendname, int value) {
/* 304 */     SettingKey s = new SettingKey(backendname, value);
/* 305 */     WidgetKeybinding w = new WidgetKeybinding(s, nicename);
/* 306 */     w2.add((Widget)w);
/* 307 */     append((Setting)s);
/* 308 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingInt addSetting(Widget w2, String nicename, String backendname, int value, int min, int max) {
/* 315 */     SettingInt s = new SettingInt(backendname, value, min, 1, max);
/* 316 */     WidgetInt w = new WidgetInt(s, nicename);
/* 317 */     w2.add((Widget)w);
/* 318 */     append((Setting)s);
/* 319 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingInt addSetting(Widget w2, String nicename, String backendname, int value, int min, int step, int max) {
/* 326 */     SettingInt s = new SettingInt(backendname, value, min, step, max);
/* 327 */     WidgetInt w = new WidgetInt(s, nicename);
/* 328 */     w2.add((Widget)w);
/* 329 */     append((Setting)s);
/* 330 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingMulti addSetting(Widget w2, String nicename, String backendname, int value, String... labels) {
/* 337 */     SettingMulti s = new SettingMulti(backendname, value, labels);
/* 338 */     WidgetMulti w = new WidgetMulti(s, nicename);
/* 339 */     w2.add((Widget)w);
/* 340 */     append((Setting)s);
/* 341 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingList addSetting(Widget w2, String nicename, String backendname, String... options) {
/* 349 */     ArrayList<String> arrayList = new ArrayList<>();
/*     */     
/* 351 */     for (int i = 0; i < options.length; i++) {
/* 352 */       arrayList.add(options[i]);
/*     */     }
/*     */     
/* 355 */     SettingList s = new SettingList(backendname, arrayList);
/* 356 */     WidgetList w = new WidgetList(s, nicename);
/* 357 */     w2.add((Widget)w);
/* 358 */     append((Setting)s);
/* 359 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingText addSetting(Widget w2, String nicename, String backendname, String value) {
/* 366 */     SettingText s = new SettingText(backendname, value);
/* 367 */     WidgetText w = new WidgetText(s, nicename);
/* 368 */     w2.add((Widget)w);
/* 369 */     append((Setting)s);
/* 370 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void append(Setting s) {
/* 381 */     this.Settings.add(s);
/* 382 */     s.parent = this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void copyContextAll(String src, String dest) {
/* 392 */     for (int i = 0; i < this.Settings.size(); i++) {
/* 393 */       ((Setting)this.Settings.get(i)).copyContext(src, dest);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<SettingBoolean> getAllBooleanSettings() {
/* 403 */     return getAllBooleanSettings(currentContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<SettingBoolean> getAllBooleanSettings(String context) {
/* 414 */     ArrayList<SettingBoolean> settings = new ArrayList<>();
/* 415 */     for (Setting setting : this.Settings) {
/* 416 */       if (!SettingBoolean.class.isAssignableFrom(setting.getClass())) {
/*     */         continue;
/*     */       }
/* 419 */       settings.add((SettingBoolean)setting);
/*     */     } 
/* 421 */     return settings;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<SettingFloat> getAllFloatSettings() {
/* 430 */     return getAllFloatSettings(currentContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<SettingFloat> getAllFloatSettings(String context) {
/* 441 */     ArrayList<SettingFloat> settings = new ArrayList<>();
/* 442 */     for (Setting setting : this.Settings) {
/* 443 */       if (!SettingFloat.class.isAssignableFrom(setting.getClass())) {
/*     */         continue;
/*     */       }
/* 446 */       settings.add((SettingFloat)setting);
/*     */     } 
/* 448 */     return settings;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<SettingInt> getAllIntSettings() {
/* 457 */     return getAllIntSettings(currentContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<SettingInt> getAllIntSettings(String context) {
/* 468 */     ArrayList<SettingInt> settings = new ArrayList<>();
/* 469 */     for (Setting setting : this.Settings) {
/* 470 */       if (!SettingInt.class.isAssignableFrom(setting.getClass())) {
/*     */         continue;
/*     */       }
/* 473 */       settings.add((SettingInt)setting);
/*     */     } 
/* 475 */     return settings;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<SettingKey> getAllKeySettings() {
/* 484 */     return getAllKeySettings(currentContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<SettingKey> getAllKeySettings(String context) {
/* 495 */     ArrayList<SettingKey> settings = new ArrayList<>();
/* 496 */     for (Setting setting : this.Settings) {
/* 497 */       if (!SettingKey.class.isAssignableFrom(setting.getClass())) {
/*     */         continue;
/*     */       }
/* 500 */       settings.add((SettingKey)setting);
/*     */     } 
/* 502 */     return settings;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<SettingMulti> getAllMultiSettings() {
/* 511 */     return getAllMultiSettings(currentContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<SettingMulti> getAllMultiSettings(String context) {
/* 522 */     ArrayList<SettingMulti> settings = new ArrayList<>();
/* 523 */     for (Setting setting : this.Settings) {
/* 524 */       if (!SettingMulti.class.isAssignableFrom(setting.getClass())) {
/*     */         continue;
/*     */       }
/* 527 */       settings.add((SettingMulti)setting);
/*     */     } 
/* 529 */     return settings;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<SettingText> getAllTextSettings() {
/* 538 */     return getAllTextSettings(currentContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<SettingText> getAllTextSettings(String context) {
/* 549 */     ArrayList<SettingText> settings = new ArrayList<>();
/* 550 */     for (Setting setting : this.Settings) {
/* 551 */       if (!SettingText.class.isAssignableFrom(setting.getClass())) {
/*     */         continue;
/*     */       }
/* 554 */       settings.add((SettingText)setting);
/*     */     } 
/* 556 */     return settings;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean getBooleanSettingValue(String backendName) {
/* 566 */     return getBooleanSettingValue(backendName, currentContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean getBooleanSettingValue(String backendName, String context) {
/* 577 */     return getSettingBoolean(backendName).get(context);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Float getFloatSettingValue(String backendName) {
/* 587 */     return getFloatSettingValue(backendName, currentContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Float getFloatSettingValue(String backendName, String context) {
/* 598 */     return getSettingFloat(backendName).get(context);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getIntSettingValue(String backendName) {
/* 608 */     return getIntSettingValue(backendName, currentContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getIntSettingValue(String backendName, String context) {
/* 619 */     return getSettingInt(backendName).get(context);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getKeySettingValue(String backendName) {
/* 629 */     return getKeySettingValue(backendName, currentContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getKeySettingValue(String backendName, String context) {
/* 640 */     return getSettingKey(backendName).get(context);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMultiSettingLabel(String backendName) {
/* 650 */     return getMultiSettingLabel(backendName, currentContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMultiSettingLabel(String backendName, String context) {
/* 661 */     SettingMulti setting = getSettingMulti(backendName);
/*     */     
/* 663 */     return setting.labelValues[setting.get(context).intValue()];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getMultiSettingValue(String backendName) {
/* 673 */     return getMultiSettingValue(backendName, currentContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getMultiSettingValue(String backendName, String context) {
/* 684 */     return getSettingMulti(backendName).get(context);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingBoolean getSettingBoolean(String backendName) {
/* 695 */     for (Setting setting : this.Settings) {
/* 696 */       if (!SettingBoolean.class.isAssignableFrom(setting.getClass())) {
/*     */         continue;
/*     */       }
/* 699 */       if (setting.backendName.equals(backendName)) {
/* 700 */         return (SettingBoolean)setting;
/*     */       }
/*     */     } 
/* 703 */     throw new InvalidParameterException("SettingBoolean '" + backendName + "' not found.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingFloat getSettingFloat(String backendName) {
/* 714 */     for (Setting setting : this.Settings) {
/* 715 */       if (!SettingFloat.class.isAssignableFrom(setting.getClass())) {
/*     */         continue;
/*     */       }
/* 718 */       if (setting.backendName.equals(backendName)) {
/* 719 */         return (SettingFloat)setting;
/*     */       }
/*     */     } 
/* 722 */     throw new InvalidParameterException("SettingFloat '" + backendName + "' not found.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingInt getSettingInt(String backendName) {
/* 733 */     for (Setting setting : this.Settings) {
/* 734 */       if (!SettingInt.class.isAssignableFrom(setting.getClass())) {
/*     */         continue;
/*     */       }
/* 737 */       if (setting.backendName.equals(backendName)) {
/* 738 */         return (SettingInt)setting;
/*     */       }
/*     */     } 
/* 741 */     throw new InvalidParameterException("SettingInt '" + backendName + "' not found.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingKey getSettingKey(String backendName) {
/* 752 */     for (Setting setting : this.Settings) {
/* 753 */       if (!SettingKey.class.isAssignableFrom(setting.getClass())) {
/*     */         continue;
/*     */       }
/* 756 */       if (setting.backendName.equals(backendName)) {
/* 757 */         return (SettingKey)setting;
/*     */       }
/*     */     } 
/* 760 */     throw new InvalidParameterException("SettingKey '" + backendName + "' not found.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingDictionary getSettingList(String backendName) {
/* 771 */     for (Setting setting : this.Settings) {
/* 772 */       if (!SettingDictionary.class.isAssignableFrom(setting.getClass())) {
/*     */         continue;
/*     */       }
/* 775 */       if (setting.backendName.equals(backendName)) {
/* 776 */         return (SettingDictionary)setting;
/*     */       }
/*     */     } 
/* 779 */     throw new InvalidParameterException("SettingList '" + backendName + "' not found.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingMulti getSettingMulti(String backendName) {
/* 790 */     for (Setting setting : this.Settings) {
/* 791 */       if (!SettingMulti.class.isAssignableFrom(setting.getClass())) {
/*     */         continue;
/*     */       }
/* 794 */       if (setting.backendName.equals(backendName)) {
/* 795 */         return (SettingMulti)setting;
/*     */       }
/*     */     } 
/* 798 */     throw new InvalidParameterException("SettingMulti '" + backendName + "' not found.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingText getSettingText(String backendName) {
/* 809 */     for (Setting setting : this.Settings) {
/* 810 */       if (!SettingText.class.isAssignableFrom(setting.getClass())) {
/*     */         continue;
/*     */       }
/* 813 */       if (setting.backendName.equals(backendName)) {
/* 814 */         return (SettingText)setting;
/*     */       }
/*     */     } 
/* 817 */     throw new InvalidParameterException("SettingText '" + backendName + "' not found.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTextSettingValue(String backendName) {
/* 827 */     return getTextSettingValue(backendName, currentContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTextSettingValue(String backendName, String context) {
/* 838 */     return getSettingText(backendName).get(context);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void load() {
/* 845 */     load("");
/* 846 */     this.settingsLoaded = true;
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
/*     */   public void load(String context) {
/*     */     try {
/* 860 */       if (contextDatadirs.get(context) != null) {
/*     */ 
/*     */         
/* 863 */         File path = getAppDir("/" + (String)contextDatadirs.get(context) + "/" + this.backendname + "/");
/* 864 */         if (path.exists()) {
/*     */ 
/*     */           
/* 867 */           File file = new File(path, "guiconfig.properties");
/* 868 */           if (file.exists()) {
/*     */ 
/*     */             
/* 871 */             Properties p = new Properties();
/* 872 */             p.load(new FileInputStream(file));
/* 873 */             for (int i = 0; i < this.Settings.size(); i++)
/* 874 */             { dbgout("setting load");
/* 875 */               Setting z = this.Settings.get(i);
/* 876 */               if (p.containsKey(z.backendName))
/* 877 */               { dbgout("setting " + (String)p.get(z.backendName));
/* 878 */                 z.fromString((String)p.get(z.backendName), context); }  } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 882 */     } catch (Exception e) {
/* 883 */       e.printStackTrace();
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
/*     */   public void remove(Setting s) {
/* 896 */     this.Settings.remove(s);
/* 897 */     s.parent = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetAll() {
/* 904 */     resetAll(currentContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetAll(String context) {
/* 913 */     for (int i = 0; i < this.Settings.size(); i++) {
/* 914 */       ((Setting)this.Settings.get(i)).reset(context);
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
/*     */   public void save(String context) {
/* 927 */     if (!this.settingsLoaded) {
/*     */       return;
/*     */     }
/*     */     try {
/* 931 */       File path = getAppDir("/" + (String)contextDatadirs.get(context) + "/" + this.backendname + "/");
/* 932 */       dbgout("saving context " + context + " (" + path.getAbsolutePath() + " [" + (String)contextDatadirs.get(context) + "])");
/* 933 */       if (!path.exists()) {
/* 934 */         path.mkdirs();
/*     */       }
/* 936 */       File file = new File(path, "guiconfig.properties");
/* 937 */       Properties p = new Properties();
/* 938 */       for (int i = 0; i < this.Settings.size(); i++) {
/* 939 */         Setting z = this.Settings.get(i);
/* 940 */         p.put(z.backendName, z.toString(context));
/*     */       } 
/* 942 */       FileOutputStream out = new FileOutputStream(file);
/* 943 */       p.store(out, "");
/* 944 */     } catch (Exception e) {
/* 945 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 953 */     return this.Settings.size();
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\ModSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */