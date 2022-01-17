/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.utils.TextUtil;
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
/*     */ public final class KeyStroke
/*     */ {
/*     */   private static final int SHIFT = 1;
/*     */   private static final int CTRL = 2;
/*     */   private static final int META = 4;
/*     */   private static final int ALT = 8;
/*     */   private static final int CMD = 20;
/*     */   private final int modifier;
/*     */   private final int keyCode;
/*     */   private final char keyChar;
/*     */   private final String action;
/*     */   
/*     */   private KeyStroke(int modifier, int keyCode, char keyChar, String action) {
/*  56 */     this.modifier = modifier;
/*  57 */     this.keyCode = keyCode;
/*  58 */     this.keyChar = keyChar;
/*  59 */     this.action = action;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAction() {
/*  67 */     return this.action;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStroke() {
/*  76 */     StringBuilder sb = new StringBuilder();
/*  77 */     if ((this.modifier & 0x1) == 1) {
/*  78 */       sb.append("shift ");
/*     */     }
/*  80 */     if ((this.modifier & 0x2) == 2) {
/*  81 */       sb.append("ctrl ");
/*     */     }
/*  83 */     if ((this.modifier & 0x8) == 8) {
/*  84 */       sb.append("alt ");
/*     */     }
/*  86 */     if ((this.modifier & 0x14) == 20) {
/*  87 */       sb.append("cmd ");
/*  88 */     } else if ((this.modifier & 0x4) == 4) {
/*  89 */       sb.append("meta ");
/*     */     } 
/*  91 */     if (this.keyCode != 0) {
/*  92 */       sb.append(Event.getKeyNameForCode(this.keyCode));
/*     */     } else {
/*  94 */       sb.append("typed ").append(this.keyChar);
/*     */     } 
/*  96 */     return sb.toString();
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
/*     */   public boolean equals(Object obj) {
/* 108 */     if (obj instanceof KeyStroke) {
/* 109 */       KeyStroke other = (KeyStroke)obj;
/* 110 */       return (this.modifier == other.modifier && this.keyCode == other.keyCode && this.keyChar == other.keyChar);
/*     */     } 
/*     */ 
/*     */     
/* 114 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 123 */     int hash = 5;
/* 124 */     hash = 83 * hash + this.modifier;
/* 125 */     hash = 83 * hash + this.keyCode;
/* 126 */     hash = 83 * hash + this.keyChar;
/* 127 */     return hash;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static KeyStroke parse(String stroke, String action) {
/* 152 */     if (stroke == null) {
/* 153 */       throw new NullPointerException("stroke");
/*     */     }
/* 155 */     if (action == null) {
/* 156 */       throw new NullPointerException("action");
/*     */     }
/*     */     
/* 159 */     int idx = TextUtil.skipSpaces(stroke, 0);
/* 160 */     int modifers = 0;
/* 161 */     char keyChar = Character.MIN_VALUE;
/* 162 */     int keyCode = 0;
/* 163 */     boolean typed = false;
/* 164 */     boolean end = false;
/*     */     
/* 166 */     while (idx < stroke.length()) {
/* 167 */       int endIdx = TextUtil.indexOf(stroke, ' ', idx);
/* 168 */       String part = stroke.substring(idx, endIdx);
/*     */       
/* 170 */       if (end) {
/* 171 */         throw new IllegalArgumentException("Unexpected: " + part);
/*     */       }
/*     */       
/* 174 */       if (typed) {
/* 175 */         if (part.length() != 1) {
/* 176 */           throw new IllegalArgumentException("Expected single character after 'typed'");
/*     */         }
/* 178 */         keyChar = part.charAt(0);
/* 179 */         if (keyChar == '\000') {
/* 180 */           throw new IllegalArgumentException("Unknown character: " + part);
/*     */         }
/* 182 */         end = true;
/* 183 */       } else if ("ctrl".equalsIgnoreCase(part) || "control".equalsIgnoreCase(part)) {
/* 184 */         modifers |= 0x2;
/* 185 */       } else if ("shift".equalsIgnoreCase(part)) {
/* 186 */         modifers |= 0x1;
/* 187 */       } else if ("meta".equalsIgnoreCase(part)) {
/* 188 */         modifers |= 0x4;
/* 189 */       } else if ("cmd".equalsIgnoreCase(part)) {
/* 190 */         modifers |= 0x14;
/* 191 */       } else if ("alt".equalsIgnoreCase(part)) {
/* 192 */         modifers |= 0x8;
/* 193 */       } else if ("typed".equalsIgnoreCase(part)) {
/* 194 */         typed = true;
/*     */       } else {
/* 196 */         keyCode = Event.getKeyCodeForName(part.toUpperCase(Locale.ENGLISH));
/* 197 */         if (keyCode == 0) {
/* 198 */           throw new IllegalArgumentException("Unknown key: " + part);
/*     */         }
/* 200 */         end = true;
/*     */       } 
/*     */       
/* 203 */       idx = TextUtil.skipSpaces(stroke, endIdx + 1);
/*     */     } 
/*     */     
/* 206 */     if (!end) {
/* 207 */       throw new IllegalArgumentException("Unexpected end of string");
/*     */     }
/*     */     
/* 210 */     return new KeyStroke(modifers, keyCode, keyChar, action);
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
/*     */   public static KeyStroke fromEvent(Event event, String action) {
/* 222 */     if (event == null) {
/* 223 */       throw new NullPointerException("event");
/*     */     }
/* 225 */     if (action == null) {
/* 226 */       throw new NullPointerException("action");
/*     */     }
/* 228 */     if (event.getType() != Event.Type.KEY_PRESSED) {
/* 229 */       throw new IllegalArgumentException("Event is not a Type.KEY_PRESSED");
/*     */     }
/* 231 */     int modifiers = convertModifier(event);
/* 232 */     return new KeyStroke(modifiers, event.getKeyCode(), false, action);
/*     */   }
/*     */   
/*     */   boolean match(Event e, int mappedEventModifiers) {
/* 236 */     if (mappedEventModifiers != this.modifier) {
/* 237 */       return false;
/*     */     }
/* 239 */     if (this.keyCode != 0 && this.keyCode != e.getKeyCode()) {
/* 240 */       return false;
/*     */     }
/* 242 */     if (this.keyChar != '\000' && (!e.hasKeyChar() || this.keyChar != e.getKeyChar())) {
/* 243 */       return false;
/*     */     }
/* 245 */     return true;
/*     */   }
/*     */   
/*     */   static int convertModifier(Event event) {
/* 249 */     int eventModifiers = event.getModifiers();
/* 250 */     int modifiers = 0;
/* 251 */     if ((eventModifiers & 0x9) != 0) {
/* 252 */       modifiers |= 0x1;
/*     */     }
/* 254 */     if ((eventModifiers & 0x24) != 0) {
/* 255 */       modifiers |= 0x2;
/*     */     }
/* 257 */     if ((eventModifiers & 0x12) != 0) {
/* 258 */       modifiers |= 0x4;
/*     */     }
/* 260 */     if ((eventModifiers & 0x2) != 0) {
/* 261 */       modifiers |= 0x14;
/*     */     }
/* 263 */     if ((eventModifiers & 0x600) != 0) {
/* 264 */       modifiers |= 0x8;
/*     */     }
/* 266 */     return modifiers;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\KeyStroke.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */