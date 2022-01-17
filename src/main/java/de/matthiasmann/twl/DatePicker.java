/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.model.BooleanModel;
/*     */ import de.matthiasmann.twl.model.DateModel;
/*     */ import de.matthiasmann.twl.model.HasCallback;
/*     */ import de.matthiasmann.twl.renderer.AnimationState;
/*     */ import de.matthiasmann.twl.utils.CallbackSupport;
/*     */ import de.matthiasmann.twl.utils.TextUtil;
/*     */ import java.text.DateFormat;
/*     */ import java.text.DateFormatSymbols;
/*     */ import java.text.ParseException;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DatePicker
/*     */   extends DialogLayout
/*     */ {
/*  75 */   public static final AnimationState.StateKey STATE_PREV_MONTH = AnimationState.StateKey.get("prevMonth");
/*  76 */   public static final AnimationState.StateKey STATE_NEXT_MONTH = AnimationState.StateKey.get("nextMonth");
/*     */   
/*     */   private final ArrayList<ToggleButton> dayButtons;
/*     */   
/*     */   private final MonthAdjuster monthAdjuster;
/*     */   
/*     */   private final Runnable modelChangedCB;
/*     */   private Locale locale;
/*     */   private DateFormatSymbols formatSymbols;
/*     */   String[] monthNamesLong;
/*     */   String[] monthNamesShort;
/*     */   Calendar calendar;
/*     */   private DateFormat dateFormat;
/*     */   private DateFormat dateParser;
/*     */   private ParseHook parseHook;
/*     */   private Callback[] callbacks;
/*     */   private DateModel model;
/*     */   private boolean cbAdded;
/*     */   
/*     */   public DatePicker() {
/*  96 */     this(Locale.getDefault(), DateFormat.getDateInstance());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DatePicker(Locale locale, int style) {
/* 106 */     this(locale, DateFormat.getDateInstance(style, locale));
/*     */   }
/*     */ 
/*     */   
/*     */   public DatePicker(Locale locale, DateFormat dateFormat) {
/* 111 */     this.dayButtons = new ArrayList<ToggleButton>();
/* 112 */     this.monthAdjuster = new MonthAdjuster();
/* 113 */     this.calendar = Calendar.getInstance();
/*     */     
/* 115 */     this.modelChangedCB = new Runnable() {
/*     */         public void run() {
/* 117 */           DatePicker.this.modelChanged();
/*     */         }
/*     */       };
/*     */     
/* 121 */     setDateFormat(locale, dateFormat);
/*     */   }
/*     */   
/*     */   public DateModel getModel() {
/* 125 */     return this.model;
/*     */   }
/*     */   
/*     */   public void setModel(DateModel model) {
/* 129 */     if (this.model != model) {
/* 130 */       if (this.cbAdded && this.model != null) {
/* 131 */         this.model.removeCallback(this.modelChangedCB);
/*     */       }
/* 133 */       this.model = model;
/* 134 */       if (this.cbAdded && this.model != null) {
/* 135 */         this.model.addCallback(this.modelChangedCB);
/*     */       }
/* 137 */       modelChanged();
/*     */     } 
/*     */   }
/*     */   
/*     */   public DateFormat getDateFormat() {
/* 142 */     return this.dateFormat;
/*     */   }
/*     */   
/*     */   public Locale getLocale() {
/* 146 */     return this.locale;
/*     */   }
/*     */   
/*     */   public void setDateFormat(Locale locale, DateFormat dateFormat) {
/* 150 */     if (dateFormat == null) {
/* 151 */       throw new NullPointerException("dateFormat");
/*     */     }
/* 153 */     if (locale == null) {
/* 154 */       throw new NullPointerException("locale");
/*     */     }
/* 156 */     if (this.dateFormat != dateFormat || this.locale != locale) {
/* 157 */       long time = (this.calendar != null) ? this.calendar.getTimeInMillis() : System.currentTimeMillis();
/* 158 */       this.locale = locale;
/* 159 */       this.dateFormat = dateFormat;
/* 160 */       this.dateParser = DateFormat.getDateInstance(3, locale);
/* 161 */       this.calendar = (Calendar)dateFormat.getCalendar().clone();
/* 162 */       this.formatSymbols = new DateFormatSymbols(locale);
/* 163 */       this.monthNamesLong = this.formatSymbols.getMonths();
/* 164 */       this.monthNamesShort = this.formatSymbols.getShortMonths();
/* 165 */       this.calendar.setTimeInMillis(time);
/* 166 */       create();
/* 167 */       modelChanged();
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParseHook getParseHook() {
/* 172 */     return this.parseHook;
/*     */   }
/*     */   
/*     */   public void setParseHook(ParseHook parseHook) {
/* 176 */     this.parseHook = parseHook;
/*     */   }
/*     */   
/*     */   public void addCallback(Callback callback) {
/* 180 */     this.callbacks = (Callback[])CallbackSupport.addCallbackToList((Object[])this.callbacks, callback, Callback.class);
/*     */   }
/*     */   
/*     */   public void removeCallback(Callback callback) {
/* 184 */     this.callbacks = (Callback[])CallbackSupport.removeCallbackFromList((Object[])this.callbacks, callback);
/*     */   }
/*     */   
/*     */   public String formatDate() {
/* 188 */     return this.dateFormat.format(this.calendar.getTime());
/*     */   }
/*     */   
/*     */   public void parseDate(String date) throws ParseException {
/* 192 */     parseDateImpl(date, true);
/*     */   }
/*     */   
/*     */   protected void parseDateImpl(String text, boolean update) throws ParseException {
/* 196 */     if (this.parseHook != null && 
/* 197 */       this.parseHook.parse(text, this.calendar, update)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 202 */     ParsePosition position = new ParsePosition(0);
/* 203 */     Date parsed = this.dateParser.parse(text, position);
/* 204 */     if (position.getIndex() > 0) {
/* 205 */       if (update) {
/* 206 */         this.calendar.setTime(parsed);
/* 207 */         calendarChanged();
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/* 212 */     String lowerText = text.trim().toLowerCase(this.locale);
/* 213 */     String[][] monthNamesStyles = { this.monthNamesLong, this.monthNamesShort };
/*     */ 
/*     */     
/* 216 */     int month = -1;
/* 217 */     int year = -1;
/* 218 */     boolean hasYear = false;
/*     */     
/* 220 */     label41: for (String[] monthNames : monthNamesStyles) {
/* 221 */       for (int i = 0; i < monthNames.length; i++) {
/* 222 */         String name = monthNames[i].toLowerCase(this.locale);
/* 223 */         if (name.length() > 0 && lowerText.startsWith(name)) {
/* 224 */           month = i;
/* 225 */           lowerText = TextUtil.trim(lowerText, name.length());
/*     */           
/*     */           break label41;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     try {
/* 232 */       year = Integer.parseInt(lowerText);
/* 233 */       if (year < 100) {
/* 234 */         year = fixupSmallYear(year);
/*     */       }
/* 236 */       hasYear = true;
/* 237 */     } catch (IllegalArgumentException ignore) {}
/*     */ 
/*     */     
/* 240 */     if (month < 0 && !hasYear) {
/* 241 */       throw new ParseException("Unparseable date: \"" + text + "\"", position.getErrorIndex());
/*     */     }
/*     */     
/* 244 */     if (update) {
/* 245 */       if (month >= 0) {
/* 246 */         this.calendar.set(2, month + this.calendar.getMinimum(2));
/*     */       }
/* 248 */       if (hasYear) {
/* 249 */         this.calendar.set(1, year);
/*     */       }
/* 251 */       calendarChanged();
/*     */     } 
/*     */   }
/*     */   
/*     */   private int fixupSmallYear(int year) {
/* 256 */     Calendar cal = (Calendar)this.calendar.clone();
/* 257 */     cal.setTimeInMillis(System.currentTimeMillis());
/* 258 */     int futureYear = cal.get(1) + 20;
/* 259 */     int tripPoint = futureYear % 100;
/* 260 */     if (year > tripPoint) {
/* 261 */       year -= 100;
/*     */     }
/* 263 */     year += futureYear - tripPoint;
/* 264 */     return year;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void afterAddToGUI(GUI gui) {
/* 269 */     super.afterAddToGUI(gui);
/* 270 */     if (!this.cbAdded && this.model != null) {
/* 271 */       this.model.addCallback(this.modelChangedCB);
/*     */     }
/* 273 */     this.cbAdded = true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void beforeRemoveFromGUI(GUI gui) {
/* 278 */     if (this.cbAdded && this.model != null) {
/* 279 */       this.model.removeCallback(this.modelChangedCB);
/*     */     }
/* 281 */     this.cbAdded = false;
/* 282 */     super.beforeRemoveFromGUI(gui);
/*     */   }
/*     */   
/*     */   private void create() {
/* 286 */     int minDay = this.calendar.getMinimum(5);
/* 287 */     int maxDay = this.calendar.getMaximum(5);
/* 288 */     int minDayOfWeek = this.calendar.getMinimum(7);
/* 289 */     int maxDayOfWeek = this.calendar.getMaximum(7);
/* 290 */     int daysPerWeek = maxDayOfWeek - minDayOfWeek + 1;
/* 291 */     int numWeeks = (maxDay - minDay + daysPerWeek * 2 - 1) / daysPerWeek;
/*     */     
/* 293 */     setHorizontalGroup((DialogLayout.Group)null);
/* 294 */     setVerticalGroup((DialogLayout.Group)null);
/* 295 */     removeAllChildren();
/* 296 */     this.dayButtons.clear();
/*     */     
/* 298 */     String[] weekDays = this.formatSymbols.getShortWeekdays();
/*     */     
/* 300 */     DialogLayout.Group daysHorz = createSequentialGroup();
/* 301 */     DialogLayout.Group daysVert = createSequentialGroup();
/* 302 */     DialogLayout.Group[] daysOfWeekHorz = new DialogLayout.Group[daysPerWeek];
/* 303 */     DialogLayout.Group daysRow = createParallelGroup();
/* 304 */     daysVert.addGroup(daysRow);
/*     */     
/* 306 */     for (int i = 0; i < daysPerWeek; i++) {
/* 307 */       daysOfWeekHorz[i] = createParallelGroup();
/* 308 */       daysHorz.addGroup(daysOfWeekHorz[i]);
/*     */       
/* 310 */       Label l = new Label(weekDays[i + minDay]);
/* 311 */       daysOfWeekHorz[i].addWidget(l);
/* 312 */       daysRow.addWidget(l);
/*     */     } 
/*     */     
/* 315 */     for (int week = 0; week < numWeeks; week++) {
/* 316 */       daysRow = createParallelGroup();
/* 317 */       daysVert.addGroup(daysRow);
/*     */       
/* 319 */       for (int day = 0; day < daysPerWeek; day++) {
/* 320 */         ToggleButton tb = new ToggleButton();
/* 321 */         tb.setTheme("daybutton");
/* 322 */         this.dayButtons.add(tb);
/*     */         
/* 324 */         daysOfWeekHorz[day].addWidget(tb);
/* 325 */         daysRow.addWidget(tb);
/*     */       } 
/*     */     } 
/*     */     
/* 329 */     setHorizontalGroup(createParallelGroup().addWidget(this.monthAdjuster).addGroup(daysHorz));
/*     */ 
/*     */     
/* 332 */     setVerticalGroup(createSequentialGroup().addWidget(this.monthAdjuster).addGroup(daysVert));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void modelChanged() {
/* 338 */     if (this.model != null) {
/* 339 */       this.calendar.setTimeInMillis(this.model.getValue());
/*     */     }
/* 341 */     updateDisplay();
/*     */   }
/*     */   
/*     */   void calendarChanged() {
/* 345 */     if (this.model != null) {
/* 346 */       this.model.setValue(this.calendar.getTimeInMillis());
/*     */     }
/* 348 */     updateDisplay();
/*     */   }
/*     */   
/*     */   void updateDisplay() {
/* 352 */     this.monthAdjuster.syncWithModel();
/* 353 */     Calendar cal = (Calendar)this.calendar.clone();
/*     */     
/* 355 */     int minDay = this.calendar.getMinimum(5);
/* 356 */     int maxDay = this.calendar.getActualMaximum(5);
/* 357 */     int minDayOfWeek = cal.getMinimum(7);
/* 358 */     int maxDayOfWeek = cal.getMaximum(7);
/* 359 */     int daysPerWeek = maxDayOfWeek - minDayOfWeek + 1;
/*     */     
/* 361 */     int day = this.calendar.get(5);
/* 362 */     int weekDay = this.calendar.get(7);
/*     */     
/* 364 */     if (weekDay > minDayOfWeek) {
/* 365 */       int adj = minDayOfWeek - weekDay;
/* 366 */       day += adj;
/* 367 */       cal.add(5, adj);
/*     */     } 
/*     */     
/* 370 */     while (day > minDay) {
/* 371 */       day -= daysPerWeek;
/* 372 */       cal.add(5, -daysPerWeek);
/*     */     } 
/*     */     
/* 375 */     for (ToggleButton tb : this.dayButtons) {
/* 376 */       DayModel dayModel = new DayModel(day);
/* 377 */       tb.setText(Integer.toString(cal.get(5)));
/* 378 */       tb.setModel(dayModel);
/* 379 */       AnimationState animState = tb.getAnimationState();
/* 380 */       animState.setAnimationState(STATE_PREV_MONTH, (day < minDay));
/* 381 */       animState.setAnimationState(STATE_NEXT_MONTH, (day > maxDay));
/* 382 */       dayModel.update();
/* 383 */       cal.add(5, 1);
/* 384 */       day++;
/*     */     } 
/*     */     
/* 387 */     if (this.callbacks != null)
/* 388 */       for (Callback cb : this.callbacks)
/* 389 */         cb.calendarChanged(this.calendar);  
/*     */   }
/*     */   public static interface ParseHook {
/*     */     boolean parse(String param1String, Calendar param1Calendar, boolean param1Boolean) throws ParseException; }
/*     */   public static interface Callback {
/*     */     void calendarChanged(Calendar param1Calendar); }
/*     */   
/*     */   class DayModel extends HasCallback implements BooleanModel { final int day;
/*     */     
/*     */     DayModel(int day) {
/* 399 */       this.day = day;
/*     */     }
/*     */     boolean active;
/*     */     public boolean getValue() {
/* 403 */       return this.active;
/*     */     }
/*     */     
/*     */     void update() {
/* 407 */       boolean newActive = (DatePicker.this.calendar.get(5) == this.day);
/* 408 */       if (this.active != newActive) {
/* 409 */         this.active = newActive;
/* 410 */         doCallback();
/*     */       } 
/*     */     }
/*     */     
/*     */     public void setValue(boolean value) {
/* 415 */       if (value && !this.active) {
/* 416 */         DatePicker.this.calendar.set(5, this.day);
/* 417 */         DatePicker.this.calendarChanged();
/*     */       } 
/*     */     } }
/*     */ 
/*     */   
/*     */   class MonthAdjuster
/*     */     extends ValueAdjuster {
/*     */     private long dragStartDate;
/*     */     
/*     */     protected void doDecrement() {
/* 427 */       DatePicker.this.calendar.add(2, -1);
/* 428 */       DatePicker.this.calendarChanged();
/*     */     }
/*     */ 
/*     */     
/*     */     protected void doIncrement() {
/* 433 */       DatePicker.this.calendar.add(2, 1);
/* 434 */       DatePicker.this.calendarChanged();
/*     */     }
/*     */ 
/*     */     
/*     */     protected String formatText() {
/* 439 */       return DatePicker.this.monthNamesLong[DatePicker.this.calendar.get(2)] + " " + DatePicker.this.calendar.get(1);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void onDragCancelled() {
/* 445 */       DatePicker.this.calendar.setTimeInMillis(this.dragStartDate);
/* 446 */       DatePicker.this.calendarChanged();
/*     */     }
/*     */ 
/*     */     
/*     */     protected void onDragStart() {
/* 451 */       this.dragStartDate = DatePicker.this.calendar.getTimeInMillis();
/*     */     }
/*     */ 
/*     */     
/*     */     protected void onDragUpdate(int dragDelta) {
/* 456 */       dragDelta /= 5;
/* 457 */       DatePicker.this.calendar.setTimeInMillis(this.dragStartDate);
/* 458 */       DatePicker.this.calendar.add(2, dragDelta);
/* 459 */       DatePicker.this.calendarChanged();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void onEditCanceled() {}
/*     */ 
/*     */     
/*     */     protected boolean onEditEnd(String text) {
/*     */       try {
/* 469 */         DatePicker.this.parseDateImpl(text, true);
/* 470 */         return true;
/* 471 */       } catch (ParseException ex) {
/* 472 */         return false;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected String onEditStart() {
/* 478 */       return formatText();
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean shouldStartEdit(char ch) {
/* 483 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     protected void syncWithModel() {
/* 488 */       setDisplayText();
/*     */     }
/*     */ 
/*     */     
/*     */     protected String validateEdit(String text) {
/*     */       try {
/* 494 */         DatePicker.this.parseDateImpl(text, false);
/* 495 */         return null;
/* 496 */       } catch (ParseException ex) {
/* 497 */         return ex.getLocalizedMessage();
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\DatePicker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */