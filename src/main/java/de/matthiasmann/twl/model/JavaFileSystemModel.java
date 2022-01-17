/*     */ package de.matthiasmann.twl.model;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.channels.ReadableByteChannel;
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
/*     */ public class JavaFileSystemModel
/*     */   implements FileSystemModel
/*     */ {
/*  45 */   private static final JavaFileSystemModel instance = new JavaFileSystemModel();
/*     */   
/*     */   public static JavaFileSystemModel getInstance() {
/*  48 */     return instance;
/*     */   }
/*     */   
/*     */   public String getSeparator() {
/*  52 */     return File.separator;
/*     */   }
/*     */   
/*     */   public Object getFile(String path) {
/*  56 */     File file = new File(path);
/*  57 */     return file.exists() ? file : null;
/*     */   }
/*     */   
/*     */   public Object getParent(Object file) {
/*  61 */     return ((File)file).getParentFile();
/*     */   }
/*     */   
/*     */   public boolean isFolder(Object file) {
/*  65 */     return ((File)file).isDirectory();
/*     */   }
/*     */   
/*     */   public boolean isFile(Object file) {
/*  69 */     return ((File)file).isFile();
/*     */   }
/*     */   
/*     */   public boolean isHidden(Object file) {
/*  73 */     return ((File)file).isHidden();
/*     */   }
/*     */   
/*     */   public String getName(Object file) {
/*  77 */     String name = ((File)file).getName();
/*  78 */     if (name.length() == 0) {
/*  79 */       return file.toString();
/*     */     }
/*  81 */     return name;
/*     */   }
/*     */   
/*     */   public String getPath(Object file) {
/*  85 */     return ((File)file).getPath();
/*     */   }
/*     */   
/*     */   public String getRelativePath(Object from, Object to) {
/*  89 */     return getRelativePath(this, from, to);
/*     */   }
/*     */   
/*     */   public static String getRelativePath(FileSystemModel fsm, Object from, Object to) {
/*  93 */     int levelFrom = countLevel(fsm, from);
/*  94 */     int levelTo = countLevel(fsm, to);
/*  95 */     int prefixes = 0;
/*  96 */     StringBuilder sb = new StringBuilder();
/*  97 */     while (!fsm.equals(from, to)) {
/*  98 */       int diff = levelTo - levelFrom;
/*  99 */       if (diff <= 0) {
/* 100 */         prefixes++;
/* 101 */         levelFrom--;
/* 102 */         from = fsm.getParent(from);
/*     */       } 
/* 104 */       if (diff >= 0) {
/* 105 */         sb.insert(0, '/');
/* 106 */         sb.insert(0, fsm.getName(to));
/* 107 */         levelTo--;
/* 108 */         to = fsm.getParent(to);
/*     */       } 
/*     */     } 
/* 111 */     while (prefixes-- > 0) {
/* 112 */       sb.insert(0, "../");
/*     */     }
/* 114 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public static int countLevel(FileSystemModel fsm, Object file) {
/* 118 */     int level = 0;
/* 119 */     while (file != null) {
/* 120 */       file = fsm.getParent(file);
/* 121 */       level++;
/*     */     } 
/* 123 */     return level;
/*     */   }
/*     */   
/*     */   public static int countLevel(FileSystemModel fsm, Object parent, Object child) {
/* 127 */     int level = 0;
/* 128 */     while (fsm.equals(child, parent)) {
/* 129 */       if (child == null) {
/* 130 */         return -1;
/*     */       }
/* 132 */       child = fsm.getParent(child);
/* 133 */       level++;
/*     */     } 
/* 135 */     return level;
/*     */   }
/*     */   
/*     */   public long getLastModified(Object file) {
/*     */     try {
/* 140 */       return ((File)file).lastModified();
/* 141 */     } catch (Throwable ex) {
/* 142 */       return -1L;
/*     */     } 
/*     */   }
/*     */   
/*     */   public long getSize(Object file) {
/*     */     try {
/* 148 */       return ((File)file).length();
/* 149 */     } catch (Throwable ex) {
/* 150 */       return -1L;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object file1, Object file2) {
/* 155 */     return (file1 != null && file1.equals(file2));
/*     */   }
/*     */   
/*     */   public int find(Object[] list, Object file) {
/* 159 */     if (file == null) {
/* 160 */       return -1;
/*     */     }
/* 162 */     for (int i = 0; i < list.length; i++) {
/* 163 */       if (file.equals(list[i])) {
/* 164 */         return i;
/*     */       }
/*     */     } 
/* 167 */     return -1;
/*     */   }
/*     */   
/*     */   public Object[] listRoots() {
/* 171 */     return (Object[])File.listRoots();
/*     */   }
/*     */   
/*     */   public Object[] listFolder(Object file, final FileSystemModel.FileFilter filter) {
/*     */     try {
/* 176 */       if (filter == null) {
/* 177 */         return (Object[])((File)file).listFiles();
/*     */       }
/* 179 */       return (Object[])((File)file).listFiles(new FileFilter() {
/*     */             public boolean accept(File pathname) {
/* 181 */               return filter.accept(JavaFileSystemModel.this, pathname);
/*     */             }
/*     */           });
/* 184 */     } catch (Throwable ex) {
/* 185 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object getSpecialFolder(String key) {
/* 190 */     File file = null;
/* 191 */     if ("user.home".equals(key)) {
/*     */       try {
/* 193 */         file = new File(System.getProperty("user.home"));
/* 194 */       } catch (SecurityException ex) {}
/*     */     }
/*     */ 
/*     */     
/* 198 */     if (file != null && file.canRead() && file.isDirectory()) {
/* 199 */       return file;
/*     */     }
/* 201 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ReadableByteChannel openChannel(Object file) throws IOException {
/* 206 */     return (new FileInputStream((File)file)).getChannel();
/*     */   }
/*     */   
/*     */   public InputStream openStream(Object file) throws IOException {
/* 210 */     return new FileInputStream((File)file);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\JavaFileSystemModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */