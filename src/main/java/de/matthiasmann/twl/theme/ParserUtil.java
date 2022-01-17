/*     */ package de.matthiasmann.twl.theme;
/*     */ 
/*     */ import de.matthiasmann.twl.Border;
/*     */ import de.matthiasmann.twl.Color;
/*     */ import de.matthiasmann.twl.utils.StateExpression;
/*     */ import de.matthiasmann.twl.utils.TextUtil;
/*     */ import de.matthiasmann.twl.utils.XMLParser;
/*     */ import java.text.ParseException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.SortedMap;
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
/*     */ final class ParserUtil
/*     */ {
/*     */   static void checkNameNotEmpty(String name, XMLParser xmlp) throws XmlPullParserException {
/*  54 */     if (name == null) {
/*  55 */       throw xmlp.error("missing 'name' on '" + xmlp.getName() + "'");
/*     */     }
/*  57 */     if (name.length() == 0) {
/*  58 */       throw xmlp.error("empty name not allowed");
/*     */     }
/*  60 */     if ("none".equals(name)) {
/*  61 */       throw xmlp.error("can't use reserved name \"none\"");
/*     */     }
/*  63 */     if (name.indexOf('*') >= 0) {
/*  64 */       throw xmlp.error("'*' is not allowed in names");
/*     */     }
/*  66 */     if (name.indexOf('/') >= 0) {
/*  67 */       throw xmlp.error("'/' is not allowed in names");
/*     */     }
/*     */   }
/*     */   
/*     */   static Border parseBorderFromAttribute(XMLParser xmlp, String attribute) throws XmlPullParserException {
/*  72 */     String value = xmlp.getAttributeValue(null, attribute);
/*  73 */     if (value == null) {
/*  74 */       return null;
/*     */     }
/*  76 */     return parseBorder(xmlp, value);
/*     */   }
/*     */   
/*     */   static Border parseBorder(XMLParser xmlp, String value) throws XmlPullParserException {
/*     */     try {
/*  81 */       int[] values = TextUtil.parseIntArray(value);
/*  82 */       switch (values.length) {
/*     */         case 1:
/*  84 */           return new Border(values[0]);
/*     */         case 2:
/*  86 */           return new Border(values[0], values[1]);
/*     */         case 4:
/*  88 */           return new Border(values[0], values[1], values[2], values[3]);
/*     */       } 
/*  90 */       throw xmlp.error("Unsupported border format");
/*     */     }
/*  92 */     catch (NumberFormatException ex) {
/*  93 */       throw xmlp.error("Unable to parse border size", ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   static Color parseColorFromAttribute(XMLParser xmlp, String attribute, ParameterMapImpl constants, Color defaultColor) throws XmlPullParserException {
/*  98 */     String value = xmlp.getAttributeValue(null, attribute);
/*  99 */     if (value == null) {
/* 100 */       return defaultColor;
/*     */     }
/* 102 */     return parseColor(xmlp, value, constants);
/*     */   }
/*     */   
/*     */   static Color parseColor(XMLParser xmlp, String value, ParameterMapImpl constants) throws XmlPullParserException {
/*     */     try {
/* 107 */       Color color = Color.parserColor(value);
/* 108 */       if (color == null && constants != null) {
/* 109 */         color = constants.<Color>getParameterValue(value, false, Color.class);
/*     */       }
/* 111 */       if (color == null) {
/* 112 */         throw xmlp.error("Unknown color name: " + value);
/*     */       }
/* 114 */       return color;
/* 115 */     } catch (NumberFormatException ex) {
/* 116 */       throw xmlp.error("unable to parse color code", ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   static String appendDot(String name) {
/* 121 */     int len = name.length();
/* 122 */     if (len > 0 && name.charAt(len - 1) != '.') {
/* 123 */       name = name.concat(".");
/*     */     }
/* 125 */     return name;
/*     */   }
/*     */   
/*     */   static int[] parseIntArrayFromAttribute(XMLParser xmlp, String attribute) throws XmlPullParserException {
/*     */     try {
/* 130 */       String value = xmlp.getAttributeNotNull(attribute);
/* 131 */       return TextUtil.parseIntArray(value);
/* 132 */     } catch (NumberFormatException ex) {
/* 133 */       throw xmlp.error("Unable to parse", ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   static <V> SortedMap<String, V> find(SortedMap<String, V> map, String baseName) {
/* 138 */     return map.subMap(baseName, baseName.concat("￿"));
/*     */   }
/*     */   
/*     */   static <V> Map<String, V> resolve(SortedMap<String, V> map, String ref, String name, V mapToNull) {
/* 142 */     name = appendDot(name);
/* 143 */     int refLen = ref.length() - 1;
/* 144 */     ref = ref.substring(0, refLen);
/*     */     
/* 146 */     SortedMap<String, V> matched = find(map, ref);
/* 147 */     if (matched.isEmpty()) {
/* 148 */       return matched;
/*     */     }
/*     */     
/* 151 */     HashMap<String, V> result = new HashMap<String, V>();
/* 152 */     for (Map.Entry<String, V> texEntry : matched.entrySet()) {
/* 153 */       String entryName = texEntry.getKey();
/* 154 */       assert entryName.startsWith(ref);
/* 155 */       V value = texEntry.getValue();
/* 156 */       if (value == mapToNull) {
/* 157 */         value = null;
/*     */       }
/* 159 */       result.put(name.concat(entryName.substring(refLen)), value);
/*     */     } 
/*     */     
/* 162 */     return result;
/*     */   }
/*     */   
/*     */   static StateExpression parseCondition(XMLParser xmlp) throws XmlPullParserException {
/* 166 */     String expression = xmlp.getAttributeValue(null, "if");
/* 167 */     boolean negate = (expression == null);
/* 168 */     if (expression == null) {
/* 169 */       expression = xmlp.getAttributeValue(null, "unless");
/*     */     }
/* 171 */     if (expression != null) {
/*     */       try {
/* 173 */         return StateExpression.parse(expression, negate);
/* 174 */       } catch (ParseException ex) {
/* 175 */         throw xmlp.error("Unable to parse condition", ex);
/*     */       } 
/*     */     }
/* 178 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\theme\ParserUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */