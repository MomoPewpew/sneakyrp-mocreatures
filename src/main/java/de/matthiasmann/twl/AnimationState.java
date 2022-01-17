/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.renderer.AnimationState;
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
/*     */ public class AnimationState
/*     */   implements AnimationState
/*     */ {
/*     */   private final AnimationState parent;
/*     */   private State[] stateTable;
/*     */   private GUI gui;
/*     */   
/*     */   public AnimationState(AnimationState parent, int size) {
/*  54 */     this.parent = parent;
/*  55 */     this.stateTable = new State[size];
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
/*     */   public AnimationState(AnimationState parent) {
/*  68 */     this(parent, 16);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimationState() {
/*  77 */     this(null);
/*     */   }
/*     */   
/*     */   public void setGUI(GUI gui) {
/*  81 */     this.gui = gui;
/*     */     
/*  83 */     long curTime = getCurrentTime();
/*  84 */     for (State s : this.stateTable) {
/*  85 */       if (s != null) {
/*  86 */         s.lastChangedTime = curTime;
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
/*     */   public int getAnimationTime(AnimationState.StateKey stateKey) {
/*  99 */     State state = getState(stateKey);
/* 100 */     if (state != null) {
/* 101 */       return (int)Math.min(2147483647L, getCurrentTime() - state.lastChangedTime);
/*     */     }
/* 103 */     if (this.parent != null) {
/* 104 */       return this.parent.getAnimationTime(stateKey);
/*     */     }
/* 106 */     return (int)getCurrentTime() & Integer.MAX_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getAnimationState(AnimationState.StateKey stateKey) {
/* 116 */     State state = getState(stateKey);
/* 117 */     if (state != null) {
/* 118 */       return state.active;
/*     */     }
/* 120 */     if (this.parent != null) {
/* 121 */       return this.parent.getAnimationState(stateKey);
/*     */     }
/* 123 */     return false;
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
/*     */   public boolean getShouldAnimateState(AnimationState.StateKey stateKey) {
/* 135 */     State state = getState(stateKey);
/* 136 */     if (state != null) {
/* 137 */       return state.shouldAnimate;
/*     */     }
/* 139 */     if (this.parent != null) {
/* 140 */       return this.parent.getShouldAnimateState(stateKey);
/*     */     }
/* 142 */     return false;
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
/*     */   @Deprecated
/*     */   public void setAnimationState(String stateName, boolean active) {
/* 156 */     setAnimationState(AnimationState.StateKey.get(stateName), active);
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
/*     */   public void setAnimationState(AnimationState.StateKey stateKey, boolean active) {
/* 169 */     State state = getOrCreate(stateKey);
/* 170 */     if (state.active != active) {
/* 171 */       state.active = active;
/* 172 */       state.lastChangedTime = getCurrentTime();
/* 173 */       state.shouldAnimate = true;
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
/*     */   @Deprecated
/*     */   public void resetAnimationTime(String stateName) {
/* 187 */     resetAnimationTime(AnimationState.StateKey.get(stateName));
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
/*     */   public void resetAnimationTime(AnimationState.StateKey stateKey) {
/* 199 */     State state = getOrCreate(stateKey);
/* 200 */     state.lastChangedTime = getCurrentTime();
/* 201 */     state.shouldAnimate = true;
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
/*     */   @Deprecated
/*     */   public void dontAnimate(String stateName) {
/* 214 */     dontAnimate(AnimationState.StateKey.get(stateName));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dontAnimate(AnimationState.StateKey stateKey) {
/* 224 */     State state = getState(stateKey);
/* 225 */     if (state != null) {
/* 226 */       state.shouldAnimate = false;
/*     */     }
/*     */   }
/*     */   
/*     */   private State getState(AnimationState.StateKey stateKey) {
/* 231 */     int id = stateKey.getID();
/* 232 */     if (id < this.stateTable.length) {
/* 233 */       return this.stateTable[id];
/*     */     }
/* 235 */     return null;
/*     */   }
/*     */   
/*     */   private State getOrCreate(AnimationState.StateKey stateKey) {
/* 239 */     int id = stateKey.getID();
/* 240 */     if (id < this.stateTable.length) {
/* 241 */       State state = this.stateTable[id];
/* 242 */       if (state != null) {
/* 243 */         return state;
/*     */       }
/*     */     } 
/* 246 */     return createState(id);
/*     */   }
/*     */   
/*     */   private State createState(int id) {
/* 250 */     if (id >= this.stateTable.length) {
/* 251 */       State[] newTable = new State[id + 1];
/* 252 */       System.arraycopy(this.stateTable, 0, newTable, 0, this.stateTable.length);
/* 253 */       this.stateTable = newTable;
/*     */     } 
/* 255 */     State state = new State();
/* 256 */     state.lastChangedTime = getCurrentTime();
/* 257 */     this.stateTable[id] = state;
/* 258 */     return state;
/*     */   }
/*     */   
/*     */   private long getCurrentTime() {
/* 262 */     return (this.gui != null) ? this.gui.curTime : 0L;
/*     */   }
/*     */   
/*     */   static final class State {
/*     */     long lastChangedTime;
/*     */     boolean active;
/*     */     boolean shouldAnimate;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\AnimationState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */