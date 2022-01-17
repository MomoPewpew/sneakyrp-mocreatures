package de.matthiasmann.twl.model;

public interface ObservableCharSequence extends CharSequence {
  void addCallback(Callback paramCallback);
  
  void removeCallback(Callback paramCallback);
  
  public static interface Callback {
    void charactersChanged(int param1Int1, int param1Int2, int param1Int3);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\ObservableCharSequence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */