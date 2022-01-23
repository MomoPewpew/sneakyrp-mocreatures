package drzhark.mocreatures.client.renderer.texture;

import com.google.common.collect.Maps;
import drzhark.mocreatures.MoCProxy;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import net.minecraft.util.ResourceLocation;


public class MoCTextures
{
  private static final Map<String, ResourceLocation> RESOURCE_CACHE = Maps.newHashMap();

  public void loadTextures() {
  Map<String, String[]> TEXTURE_RESOURCES = Maps.newHashMap();
    try {
      TEXTURE_RESOURCES.put(MoCProxy.ARMOR_TEXTURE, getResourceListing(getClass(), "assets/mocreatures/textures/armor/"));
      TEXTURE_RESOURCES.put(MoCProxy.BLOCK_TEXTURE, getResourceListing(getClass(), "assets/mocreatures/textures/blocks/"));
      TEXTURE_RESOURCES.put(MoCProxy.GUI_TEXTURE, getResourceListing(getClass(), "assets/mocreatures/textures/gui/"));
      TEXTURE_RESOURCES.put(MoCProxy.ITEM_TEXTURE, getResourceListing(getClass(), "assets/mocreatures/textures/items/"));
      TEXTURE_RESOURCES.put(MoCProxy.MISC_TEXTURE, getResourceListing(getClass(), "assets/mocreatures/textures/misc/"));
      TEXTURE_RESOURCES.put(MoCProxy.MODEL_TEXTURE, getResourceListing(getClass(), "assets/mocreatures/textures/models/"));
    } catch (URISyntaxException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    for (Map.Entry<String, String[]> textureEntry : TEXTURE_RESOURCES.entrySet()) {
      String[] resources = textureEntry.getValue();
      if (resources != null && resources.length > 0) {
        for (int i = 0; i < resources.length; i++) {
          if (resources[i].contains(".png")) {
            RESOURCE_CACHE.put(resources[i], new ResourceLocation("mocreatures", (String)textureEntry.getKey() + resources[i]));
          }
        }
      }
    }
  }















  String[] getResourceListing(Class clazz, String path) throws URISyntaxException, IOException {
    URL dirURL = clazz.getClassLoader().getResource(path);
    if (dirURL != null && dirURL.getProtocol().equals("file"))
    {
      return (new File(dirURL.toURI())).list();
    }

    if (dirURL == null) {




      String me = clazz.getName().replace(".", "/") + ".class";
      dirURL = clazz.getClassLoader().getResource(me);
    }

    if (dirURL.getProtocol().equals("jar")) {

      String jarPath = dirURL.getPath().substring(5, dirURL.getPath().indexOf("!"));
      JarFile jar = new JarFile(URLDecoder.decode(jarPath, "UTF-8"));
      Enumeration<JarEntry> entries = jar.entries();
      Set<String> result = new HashSet<>();
      while (entries.hasMoreElements()) {
        String name = ((JarEntry)entries.nextElement()).getName();
        if (name.startsWith(path)) {
          String entry = name.substring(path.length());
          int checkSubdir = entry.indexOf("/");
          if (checkSubdir >= 0)
          {
            entry = entry.substring(0, checkSubdir);
          }
          result.add(entry);
        }
      }
      jar.close();
      return result.<String>toArray(new String[result.size()]);
    }

    throw new UnsupportedOperationException("Cannot list files for URL " + dirURL);
  }

  public ResourceLocation getTexture(String texture) {
    return RESOURCE_CACHE.get(texture);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\texture\MoCTextures.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
