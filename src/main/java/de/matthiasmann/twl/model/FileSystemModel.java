package de.matthiasmann.twl.model;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;

public interface FileSystemModel {
  public static final String SPECIAL_FOLDER_HOME = "user.home";
  
  String getSeparator();
  
  Object getFile(String paramString);
  
  Object getParent(Object paramObject);
  
  boolean isFolder(Object paramObject);
  
  boolean isFile(Object paramObject);
  
  boolean isHidden(Object paramObject);
  
  String getName(Object paramObject);
  
  String getPath(Object paramObject);
  
  String getRelativePath(Object paramObject1, Object paramObject2);
  
  long getSize(Object paramObject);
  
  long getLastModified(Object paramObject);
  
  boolean equals(Object paramObject1, Object paramObject2);
  
  int find(Object[] paramArrayOfObject, Object paramObject);
  
  Object[] listRoots();
  
  Object[] listFolder(Object paramObject, FileFilter paramFileFilter);
  
  Object getSpecialFolder(String paramString);
  
  InputStream openStream(Object paramObject) throws IOException;
  
  ReadableByteChannel openChannel(Object paramObject) throws IOException;
  
  public static interface FileFilter {
    boolean accept(FileSystemModel param1FileSystemModel, Object param1Object);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\FileSystemModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */