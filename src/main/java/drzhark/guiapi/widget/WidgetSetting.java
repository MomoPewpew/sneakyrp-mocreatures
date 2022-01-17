/*    */ package drzhark.guiapi.widget;
/*    */ 
/*    */ import de.matthiasmann.twl.Widget;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class WidgetSetting
/*    */   extends Widget
/*    */ {
/* 18 */   public static ArrayList<WidgetSetting> all = new ArrayList<>();
/*    */   
/*    */   public String niceName;
/*    */ 
/*    */   
/*    */   public static void updateAll() {
/* 24 */     for (int i = 0; i < all.size(); i++) {
/* 25 */       ((WidgetSetting)all.get(i)).update();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WidgetSetting(String nicename) {
/* 41 */     this.niceName = nicename;
/* 42 */     all.add(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void add(Widget child) {
/* 47 */     String T = child.getTheme();
/* 48 */     if (T.length() == 0) {
/* 49 */       child.setTheme("/-defaults");
/* 50 */     } else if (!T.substring(0, 1).equals("/")) {
/* 51 */       child.setTheme("/" + T);
/*    */     } 
/* 53 */     super.add(child);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract void addCallback(Runnable paramRunnable);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void layout() {
/* 66 */     for (int i = 0; i < getNumChildren(); i++) {
/* 67 */       Widget w = getChild(i);
/* 68 */       w.setPosition(getX(), getY());
/* 69 */       w.setSize(getWidth(), getHeight());
/*    */     } 
/*    */   }
/*    */   
/*    */   public abstract void removeCallback(Runnable paramRunnable);
/*    */   
/*    */   public abstract void update();
/*    */   
/*    */   public abstract String userString();
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\widget\WidgetSetting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */