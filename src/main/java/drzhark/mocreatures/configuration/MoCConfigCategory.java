/*     */ package drzhark.mocreatures.configuration;
/*     */ 
/*     */ import com.google.common.base.Splitter;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MoCConfigCategory
/*     */   implements Map<String, MoCProperty>
/*     */ {
/*     */   private String name;
/*     */   private String comment;
/*  21 */   private ArrayList<MoCConfigCategory> children = new ArrayList<>();
/*  22 */   private Map<String, MoCProperty> properties = new TreeMap<>();
/*     */   public final MoCConfigCategory parent;
/*     */   private boolean changed = false;
/*     */   
/*     */   public MoCConfigCategory(String name) {
/*  27 */     this(name, null, true);
/*     */   }
/*     */   
/*     */   public MoCConfigCategory(String name, MoCConfigCategory parent) {
/*  31 */     this(name, parent, true);
/*     */   }
/*     */   
/*     */   public MoCConfigCategory(String name, boolean newline) {
/*  35 */     this(name, null, newline);
/*     */   }
/*     */   
/*     */   public MoCConfigCategory(String name, MoCConfigCategory parent, boolean newline) {
/*  39 */     this.name = name;
/*  40 */     this.parent = parent;
/*  41 */     if (parent != null) {
/*  42 */       parent.children.add(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  48 */     if (obj instanceof MoCConfigCategory) {
/*  49 */       MoCConfigCategory cat = (MoCConfigCategory)obj;
/*  50 */       return (this.name.equals(cat.name) && this.children.equals(cat.children));
/*     */     } 
/*     */     
/*  53 */     return false;
/*     */   }
/*     */   
/*     */   public String getQualifiedName() {
/*  57 */     return getQualifiedName(this.name, this.parent);
/*     */   }
/*     */   
/*     */   public static String getQualifiedName(String name, MoCConfigCategory parent) {
/*  61 */     return (parent == null) ? name : (parent.getQualifiedName() + "." + name);
/*     */   }
/*     */   
/*     */   public MoCConfigCategory getFirstParent() {
/*  65 */     return (this.parent == null) ? this : this.parent.getFirstParent();
/*     */   }
/*     */   
/*     */   public boolean isChild() {
/*  69 */     return (this.parent != null);
/*     */   }
/*     */   
/*     */   public Map<String, MoCProperty> getValues() {
/*  73 */     return this.properties;
/*     */   }
/*     */   
/*     */   public void setComment(String comment) {
/*  77 */     this.comment = comment;
/*     */   }
/*     */   
/*     */   public boolean containsKey(String key) {
/*  81 */     return this.properties.containsKey(key);
/*     */   }
/*     */   
/*     */   public MoCProperty get(String key) {
/*  85 */     return this.properties.get(key);
/*     */   }
/*     */   
/*     */   public void set(String key, MoCProperty value) {
/*  89 */     this.properties.put(key, value);
/*     */   }
/*     */   
/*     */   private void write(BufferedWriter out, String... data) throws IOException {
/*  93 */     write(out, true, data);
/*     */   }
/*     */   
/*     */   private void write(BufferedWriter out, boolean new_line, String... data) throws IOException {
/*  97 */     for (int x = 0; x < data.length; x++) {
/*  98 */       if (data[x] != null) {
/*  99 */         out.write(data[x]);
/*     */       }
/*     */     } 
/* 102 */     if (new_line) {
/* 103 */       out.write(MoCConfiguration.NEW_LINE);
/*     */     }
/*     */   }
/*     */   
/*     */   public void write(BufferedWriter out, int indent) throws IOException {
/* 108 */     String pad0 = getIndent(indent);
/* 109 */     String pad1 = getIndent(indent + 1);
/*     */     
/* 111 */     write(out, new String[] { pad0, "####################" });
/* 112 */     write(out, new String[] { pad0, "# ", this.name });
/*     */     
/* 114 */     if (this.comment != null) {
/* 115 */       write(out, new String[] { pad0, "#===================" });
/* 116 */       Splitter splitter = Splitter.onPattern("\r?\n");
/*     */       
/* 118 */       for (String line : splitter.split(this.comment)) {
/* 119 */         write(out, new String[] { pad0, "# ", line });
/*     */       } 
/*     */     } 
/*     */     
/* 123 */     write(out, new String[] { pad0, "####################", MoCConfiguration.NEW_LINE });
/*     */     
/* 125 */     if (!MoCConfiguration.allowedProperties.matchesAllOf(this.name)) {
/* 126 */       this.name = '"' + this.name + '"';
/*     */     }
/*     */     
/* 129 */     write(out, new String[] { pad0, this.name, " {" });
/*     */     
/* 131 */     MoCProperty[] props = (MoCProperty[])this.properties.values().toArray((Object[])new MoCProperty[this.properties.size()]);
/*     */     
/* 133 */     for (int x = 0; x < props.length; x++) {
/* 134 */       MoCProperty prop = props[x];
/*     */       
/* 136 */       if (prop.comment != null) {
/* 137 */         if (x != 0) {
/* 138 */           out.newLine();
/*     */         }
/*     */         
/* 141 */         Splitter splitter = Splitter.onPattern("\r?\n");
/* 142 */         for (String commentLine : splitter.split(prop.comment)) {
/* 143 */           write(out, new String[] { pad1, "# ", commentLine });
/*     */         } 
/*     */       } 
/*     */       
/* 147 */       String propName = prop.getName();
/*     */       
/* 149 */       if (!MoCConfiguration.allowedProperties.matchesAllOf(propName)) {
/* 150 */         propName = '"' + propName + '"';
/*     */       }
/*     */       
/* 153 */       if (prop.isList()) {
/* 154 */         char type = prop.getType().getID();
/* 155 */         write(out, false, new String[] { pad1 + String.valueOf(type), ":", propName, " <" });
/* 156 */         for (int i = 0; i < prop.valueList.size(); i++) {
/* 157 */           String line = prop.valueList.get(i);
/* 158 */           if (prop.valueList.size() == i + 1) {
/*     */             
/* 160 */             write(out, false, new String[] { line });
/*     */           } else {
/* 162 */             write(out, false, new String[] { line + ":" });
/*     */           } 
/*     */         } 
/* 165 */         write(out, false, new String[] { ">", MoCConfiguration.NEW_LINE });
/* 166 */       } else if (prop.getType() == null) {
/* 167 */         write(out, false, new String[] { propName, "=", prop.getString() });
/*     */       } else {
/* 169 */         char type = prop.getType().getID();
/* 170 */         write(out, new String[] { pad1, String.valueOf(type), ":", propName, "=", prop.getString() });
/*     */       } 
/*     */     } 
/*     */     
/* 174 */     for (MoCConfigCategory child : this.children) {
/* 175 */       child.write(out, indent + 1);
/*     */     }
/*     */     
/* 178 */     write(out, new String[] { pad0, "}", MoCConfiguration.NEW_LINE });
/*     */   }
/*     */   
/*     */   private String getIndent(int indent) {
/* 182 */     StringBuilder buf = new StringBuilder("");
/* 183 */     for (int x = 0; x < indent; x++) {
/* 184 */       buf.append("    ");
/*     */     }
/* 186 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public boolean hasChanged() {
/* 190 */     if (this.changed) {
/* 191 */       return true;
/*     */     }
/* 193 */     for (MoCProperty prop : this.properties.values()) {
/* 194 */       if (prop.hasChanged()) {
/* 195 */         return true;
/*     */       }
/*     */     } 
/* 198 */     return false;
/*     */   }
/*     */   
/*     */   void resetChangedState() {
/* 202 */     this.changed = false;
/* 203 */     for (MoCProperty prop : this.properties.values()) {
/* 204 */       prop.resetChangedState();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 211 */     return this.properties.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 216 */     return this.properties.isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 221 */     return this.properties.containsKey(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 226 */     return this.properties.containsValue(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public MoCProperty get(Object key) {
/* 231 */     return this.properties.get(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public MoCProperty put(String key, MoCProperty value) {
/* 236 */     this.changed = true;
/* 237 */     return this.properties.put(key, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public MoCProperty remove(Object key) {
/* 242 */     this.changed = true;
/* 243 */     return this.properties.remove(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public void putAll(Map<? extends String, ? extends MoCProperty> m) {
/* 248 */     this.changed = true;
/* 249 */     this.properties.putAll(m);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 254 */     this.changed = true;
/* 255 */     this.properties.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<String> keySet() {
/* 260 */     return this.properties.keySet();
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<MoCProperty> values() {
/* 265 */     return this.properties.values();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<Map.Entry<String, MoCProperty>> entrySet() {
/* 272 */     return (Set<Map.Entry<String, MoCProperty>>)ImmutableSet.copyOf(this.properties.entrySet());
/*     */   }
/*     */   
/*     */   public Set<MoCConfigCategory> getChildren() {
/* 276 */     return (Set<MoCConfigCategory>)ImmutableSet.copyOf(this.children);
/*     */   }
/*     */   
/*     */   public void removeChild(MoCConfigCategory child) {
/* 280 */     if (this.children.contains(child)) {
/* 281 */       this.children.remove(child);
/* 282 */       this.changed = true;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\configuration\MoCConfigCategory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */