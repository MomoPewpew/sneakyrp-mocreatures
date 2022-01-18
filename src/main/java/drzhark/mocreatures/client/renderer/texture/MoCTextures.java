/*     */ package drzhark.mocreatures.client.renderer.texture;
/*     */
/*     */ import com.google.common.collect.Maps;
/*     */ import drzhark.mocreatures.MoCProxy;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URL;
/*     */ import java.net.URLDecoder;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.jar.JarEntry;
/*     */ import java.util.jar.JarFile;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */
/*     */
/*     */ public class MoCTextures
/*     */ {
/*  21 */   private static final Map<String, ResourceLocation> RESOURCE_CACHE = Maps.newHashMap();
/*     */
/*     */   public void loadTextures() {
/*  22 */   Map<String, String[]> TEXTURE_RESOURCES = Maps.newHashMap();
/*     */     try {
/*  26 */       TEXTURE_RESOURCES.put(MoCProxy.ARMOR_TEXTURE, getResourceListing(getClass(), "assets/mocreatures/textures/armor/"));
/*  27 */       TEXTURE_RESOURCES.put(MoCProxy.BLOCK_TEXTURE, getResourceListing(getClass(), "assets/mocreatures/textures/blocks/"));
/*  28 */       TEXTURE_RESOURCES.put(MoCProxy.GUI_TEXTURE, getResourceListing(getClass(), "assets/mocreatures/textures/gui/"));
/*  29 */       TEXTURE_RESOURCES.put(MoCProxy.ITEM_TEXTURE, getResourceListing(getClass(), "assets/mocreatures/textures/items/"));
/*  30 */       TEXTURE_RESOURCES.put(MoCProxy.MISC_TEXTURE, getResourceListing(getClass(), "assets/mocreatures/textures/misc/"));
/*  31 */       TEXTURE_RESOURCES.put(MoCProxy.MODEL_TEXTURE, getResourceListing(getClass(), "assets/mocreatures/textures/models/"));
/*  32 */     } catch (URISyntaxException e) {
/*  33 */       e.printStackTrace();
/*  34 */     } catch (IOException e) {
/*  35 */       e.printStackTrace();
/*     */     }
/*  37 */     for (Map.Entry<String, String[]> textureEntry : TEXTURE_RESOURCES.entrySet()) {
/*  38 */       String[] resources = textureEntry.getValue();
/*  39 */       if (resources != null && resources.length > 0) {
/*  40 */         for (int i = 0; i < resources.length; i++) {
/*  41 */           if (resources[i].contains(".png")) {
/*  42 */             RESOURCE_CACHE.put(resources[i], new ResourceLocation("mocreatures", (String)textureEntry.getKey() + resources[i]));
/*     */           }
/*     */         }
/*     */       }
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
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   String[] getResourceListing(Class clazz, String path) throws URISyntaxException, IOException {
/*  64 */     URL dirURL = clazz.getClassLoader().getResource(path);
/*  65 */     if (dirURL != null && dirURL.getProtocol().equals("file"))
/*     */     {
/*  67 */       return (new File(dirURL.toURI())).list();
/*     */     }
/*     */
/*  70 */     if (dirURL == null) {
/*     */
/*     */
/*     */
/*     */
/*  75 */       String me = clazz.getName().replace(".", "/") + ".class";
/*  76 */       dirURL = clazz.getClassLoader().getResource(me);
/*     */     }
/*     */
/*  79 */     if (dirURL.getProtocol().equals("jar")) {
/*     */
/*  81 */       String jarPath = dirURL.getPath().substring(5, dirURL.getPath().indexOf("!"));
/*  82 */       JarFile jar = new JarFile(URLDecoder.decode(jarPath, "UTF-8"));
/*  83 */       Enumeration<JarEntry> entries = jar.entries();
/*  84 */       Set<String> result = new HashSet<>();
/*  85 */       while (entries.hasMoreElements()) {
/*  86 */         String name = ((JarEntry)entries.nextElement()).getName();
/*  87 */         if (name.startsWith(path)) {
/*  88 */           String entry = name.substring(path.length());
/*  89 */           int checkSubdir = entry.indexOf("/");
/*  90 */           if (checkSubdir >= 0)
/*     */           {
/*  92 */             entry = entry.substring(0, checkSubdir);
/*     */           }
/*  94 */           result.add(entry);
/*     */         }
/*     */       }
/*  97 */       jar.close();
/*  98 */       return result.<String>toArray(new String[result.size()]);
/*     */     }
/*     */
/* 101 */     throw new UnsupportedOperationException("Cannot list files for URL " + dirURL);
/*     */   }
/*     */
/*     */   public ResourceLocation getTexture(String texture) {
/* 105 */     return RESOURCE_CACHE.get(texture);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\texture\MoCTextures.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
