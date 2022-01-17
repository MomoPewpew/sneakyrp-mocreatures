package de.matthiasmann.twl.model;

public interface EditFieldModel extends ObservableCharSequence {
  int replace(int paramInt1, int paramInt2, String paramString);
  
  boolean replace(int paramInt1, int paramInt2, char paramChar);
  
  String substring(int paramInt1, int paramInt2);
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\EditFieldModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */