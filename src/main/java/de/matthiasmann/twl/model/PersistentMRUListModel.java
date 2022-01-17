/*     */ package de.matthiasmann.twl.model;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import java.util.prefs.Preferences;
/*     */ import java.util.zip.Deflater;
/*     */ import java.util.zip.DeflaterOutputStream;
/*     */ import java.util.zip.InflaterInputStream;
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
/*     */ public class PersistentMRUListModel<T extends Serializable>
/*     */   extends SimpleMRUListModel<T>
/*     */ {
/*     */   private final Class<T> clazz;
/*     */   private final Preferences prefs;
/*     */   private final String prefKey;
/*     */   
/*     */   public PersistentMRUListModel(int maxEntries, Class<T> clazz, Preferences prefs, String prefKey) {
/*  67 */     super(maxEntries);
/*  68 */     if (clazz == null) {
/*  69 */       throw new NullPointerException("clazz");
/*     */     }
/*  71 */     if (prefs == null) {
/*  72 */       throw new NullPointerException("prefs");
/*     */     }
/*  74 */     if (prefKey == null) {
/*  75 */       throw new NullPointerException("prefKey");
/*     */     }
/*  77 */     this.clazz = clazz;
/*  78 */     this.prefs = prefs;
/*  79 */     this.prefKey = prefKey;
/*     */     
/*  81 */     int numEntries = Math.min(prefs.getInt(keyForNumEntries(), 0), maxEntries);
/*  82 */     for (int i = 0; i < numEntries; i++) {
/*  83 */       T entry = null;
/*  84 */       if (clazz == String.class) {
/*  85 */         Serializable serializable = (Serializable)clazz.cast(prefs.get(keyForIndex(i), null));
/*     */       } else {
/*  87 */         byte[] data = prefs.getByteArray(keyForIndex(i), null);
/*  88 */         if (data != null && data.length > 0) {
/*  89 */           entry = deserialize(data);
/*     */         }
/*     */       } 
/*  92 */       if (entry != null) {
/*  93 */         this.entries.add(entry);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addEntry(T entry) {
/* 100 */     if (!this.clazz.isInstance(entry)) {
/* 101 */       throw new ClassCastException();
/*     */     }
/* 103 */     super.addEntry(entry);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void saveEntries() {
/* 108 */     for (int i = 0; i < this.entries.size(); i++) {
/* 109 */       Serializable serializable = (Serializable)this.entries.get(i);
/* 110 */       if (this.clazz == String.class) {
/* 111 */         this.prefs.put(keyForIndex(i), (String)serializable);
/*     */       } else {
/* 113 */         byte[] data = serialize((T)serializable);
/* 114 */         assert data != null;
/* 115 */         this.prefs.putByteArray(keyForIndex(i), data);
/*     */       } 
/*     */     } 
/* 118 */     this.prefs.putInt(keyForNumEntries(), this.entries.size());
/*     */   }
/*     */   
/*     */   protected byte[] serialize(T obj) {
/*     */     try {
/* 123 */       ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 124 */       DeflaterOutputStream dos = new DeflaterOutputStream(baos, new Deflater(9));
/*     */       try {
/* 126 */         ObjectOutputStream oos = new ObjectOutputStream(dos);
/* 127 */         oos.writeObject(obj);
/* 128 */         oos.close();
/*     */       } finally {
/* 130 */         close(dos);
/*     */       } 
/* 132 */       return baos.toByteArray();
/* 133 */     } catch (IOException ex) {
/* 134 */       getLogger().log(Level.SEVERE, "Unable to serialize MRU entry", ex);
/* 135 */       return new byte[0];
/*     */     } 
/*     */   }
/*     */   
/*     */   protected T deserialize(byte[] data) {
/*     */     try {
/* 141 */       ByteArrayInputStream bais = new ByteArrayInputStream(data);
/* 142 */       InflaterInputStream iis = new InflaterInputStream(bais);
/*     */       try {
/* 144 */         ObjectInputStream ois = new ObjectInputStream(iis);
/* 145 */         Object obj = ois.readObject();
/* 146 */         if (this.clazz.isInstance(obj)) {
/* 147 */           return this.clazz.cast(obj);
/*     */         }
/* 149 */         getLogger().log(Level.WARNING, "Deserialized object of type " + obj.getClass() + " expected " + this.clazz);
/*     */       } finally {
/* 151 */         close(iis);
/*     */       } 
/* 153 */     } catch (Exception ex) {
/* 154 */       getLogger().log(Level.SEVERE, "Unable to deserialize MRU entry", ex);
/*     */     } 
/* 156 */     return null;
/*     */   }
/*     */   
/*     */   protected String keyForIndex(int idx) {
/* 160 */     return this.prefKey + "_" + idx;
/*     */   }
/*     */   protected String keyForNumEntries() {
/* 163 */     return this.prefKey + "_entries";
/*     */   }
/*     */   
/*     */   private void close(Closeable c) {
/*     */     try {
/* 168 */       c.close();
/* 169 */     } catch (IOException ex) {
/* 170 */       getLogger().log(Level.WARNING, "exception while closing stream", ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   Logger getLogger() {
/* 175 */     return Logger.getLogger(PersistentMRUListModel.class.getName());
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\PersistentMRUListModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */