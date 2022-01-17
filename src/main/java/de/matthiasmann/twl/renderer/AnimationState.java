/*     */ package de.matthiasmann.twl.renderer;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
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
/*     */ public interface AnimationState
/*     */ {
/*     */   int getAnimationTime(StateKey paramStateKey);
/*     */   
/*     */   boolean getAnimationState(StateKey paramStateKey);
/*     */   
/*     */   boolean getShouldAnimateState(StateKey paramStateKey);
/*     */   
/*     */   public static final class StateKey
/*     */   {
/*     */     private final String name;
/*     */     private final int id;
/*  77 */     private static final HashMap<String, StateKey> keys = new HashMap<String, StateKey>();
/*     */     
/*  79 */     private static final ArrayList<StateKey> keysByID = new ArrayList<StateKey>();
/*     */ 
/*     */     
/*     */     private StateKey(String name, int id) {
/*  83 */       this.name = name;
/*  84 */       this.id = id;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getName() {
/*  93 */       return this.name;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getID() {
/* 103 */       return this.id;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object obj) {
/* 108 */       if (obj instanceof StateKey) {
/* 109 */         StateKey other = (StateKey)obj;
/* 110 */         return (this.id == other.id);
/*     */       } 
/* 112 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 117 */       return this.id;
/*     */     }
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
/*     */     public static synchronized StateKey get(String name) {
/* 130 */       if (name.length() == 0) {
/* 131 */         throw new IllegalArgumentException("name");
/*     */       }
/* 133 */       StateKey key = keys.get(name);
/* 134 */       if (key == null) {
/* 135 */         key = new StateKey(name, keys.size());
/* 136 */         keys.put(name, key);
/* 137 */         keysByID.add(key);
/*     */       } 
/* 139 */       return key;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static synchronized StateKey get(int id) {
/* 149 */       return keysByID.get(id);
/*     */     }
/*     */     
/*     */     public static synchronized int getNumStateKeys() {
/* 153 */       return keys.size();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\AnimationState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */