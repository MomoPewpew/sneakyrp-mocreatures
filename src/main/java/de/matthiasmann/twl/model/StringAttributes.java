/*     */ package de.matthiasmann.twl.model;
/*     */ 
/*     */ import de.matthiasmann.twl.renderer.AnimationState;
/*     */ import de.matthiasmann.twl.renderer.AttributedString;
/*     */ import java.util.ArrayList;
/*     */ import java.util.BitSet;
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
/*     */ public class StringAttributes
/*     */   implements AttributedString
/*     */ {
/*     */   private final CharSequence seq;
/*     */   private final AnimationState baseAnimState;
/*     */   private final ArrayList<Marker> markers;
/*     */   private int position;
/*     */   private int markerIdx;
/*     */   private static final int NOT_FOUND = -2147483648;
/*     */   private static final int IDX_MASK = 2147483647;
/*     */   
/*     */   private StringAttributes(AnimationState baseAnimState, CharSequence seq) {
/*  52 */     if (seq == null) {
/*  53 */       throw new NullPointerException("seq");
/*     */     }
/*     */     
/*  56 */     this.seq = seq;
/*  57 */     this.baseAnimState = baseAnimState;
/*  58 */     this.markers = new ArrayList<Marker>();
/*     */   }
/*     */   
/*     */   public StringAttributes(String text, AnimationState baseAnimState) {
/*  62 */     this(baseAnimState, text);
/*     */   }
/*     */   
/*     */   public StringAttributes(String text) {
/*  66 */     this(text, (AnimationState)null);
/*     */   }
/*     */   
/*     */   public StringAttributes(ObservableCharSequence cs, AnimationState baseAnimState) {
/*  70 */     this(baseAnimState, cs);
/*     */     
/*  72 */     cs.addCallback(new ObservableCharSequence.Callback() {
/*     */           public void charactersChanged(int start, int oldCount, int newCount) {
/*  74 */             if (start < 0) {
/*  75 */               throw new IllegalArgumentException("start");
/*     */             }
/*  77 */             if (oldCount > 0) {
/*  78 */               StringAttributes.this.delete(start, oldCount);
/*     */             }
/*  80 */             if (newCount > 0) {
/*  81 */               StringAttributes.this.insert(start, newCount);
/*     */             }
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public StringAttributes(ObservableCharSequence cs) {
/*  88 */     this(cs, (AnimationState)null);
/*     */   }
/*     */   
/*     */   public char charAt(int index) {
/*  92 */     return this.seq.charAt(index);
/*     */   }
/*     */   
/*     */   public int length() {
/*  96 */     return this.seq.length();
/*     */   }
/*     */   
/*     */   public CharSequence subSequence(int start, int end) {
/* 100 */     return this.seq.subSequence(start, end);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 105 */     return this.seq.toString();
/*     */   }
/*     */   
/*     */   public int getPosition() {
/* 109 */     return this.position;
/*     */   }
/*     */   
/*     */   public void setPosition(int pos) {
/* 113 */     if (pos < 0 || pos > this.seq.length()) {
/* 114 */       throw new IllegalArgumentException("pos");
/*     */     }
/* 116 */     this.position = pos;
/*     */     
/* 118 */     int idx = find(pos);
/* 119 */     if (idx >= 0) {
/* 120 */       this.markerIdx = idx;
/* 121 */     } else if (pos > lastMarkerPos()) {
/* 122 */       this.markerIdx = this.markers.size();
/*     */     } else {
/*     */       
/* 125 */       this.markerIdx = (idx & Integer.MAX_VALUE) - 1;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int advance() {
/* 130 */     if (this.markerIdx + 1 < this.markers.size()) {
/* 131 */       this.markerIdx++;
/* 132 */       this.position = ((Marker)this.markers.get(this.markerIdx)).position;
/*     */     } else {
/* 134 */       this.position = this.seq.length();
/*     */     } 
/* 136 */     return this.position;
/*     */   }
/*     */   
/*     */   public boolean getAnimationState(AnimationState.StateKey state) {
/* 140 */     if (this.markerIdx >= 0 && this.markerIdx < this.markers.size()) {
/* 141 */       Marker marker = this.markers.get(this.markerIdx);
/* 142 */       int bitIdx = state.getID() << 1;
/* 143 */       if (marker.get(bitIdx)) {
/* 144 */         return marker.get(bitIdx + 1);
/*     */       }
/*     */     } 
/* 147 */     if (this.baseAnimState != null) {
/* 148 */       return this.baseAnimState.getAnimationState(state);
/*     */     }
/* 150 */     return false;
/*     */   }
/*     */   
/*     */   public int getAnimationTime(AnimationState.StateKey state) {
/* 154 */     if (this.baseAnimState != null) {
/* 155 */       return this.baseAnimState.getAnimationTime(state);
/*     */     }
/* 157 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean getShouldAnimateState(AnimationState.StateKey state) {
/* 161 */     if (this.baseAnimState != null) {
/* 162 */       return this.baseAnimState.getShouldAnimateState(state);
/*     */     }
/* 164 */     return false;
/*     */   }
/*     */   
/*     */   public void setAnimationState(AnimationState.StateKey key, int from, int end, boolean active) {
/* 168 */     if (key == null) {
/* 169 */       throw new NullPointerException("key");
/*     */     }
/* 171 */     if (from > end) {
/* 172 */       throw new IllegalArgumentException("negative range");
/*     */     }
/* 174 */     if (from < 0 || end > this.seq.length()) {
/* 175 */       throw new IllegalArgumentException("range outside of sequence");
/*     */     }
/* 177 */     if (from == end) {
/*     */       return;
/*     */     }
/* 180 */     int fromIdx = markerIndexAt(from);
/* 181 */     int endIdx = markerIndexAt(end);
/* 182 */     int bitIdx = key.getID() << 1;
/* 183 */     for (int i = fromIdx; i < endIdx; i++) {
/* 184 */       Marker m = this.markers.get(i);
/* 185 */       m.set(bitIdx);
/* 186 */       m.set(bitIdx + 1, active);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removeAnimationState(AnimationState.StateKey key, int from, int end) {
/* 191 */     if (key == null) {
/* 192 */       throw new NullPointerException("key");
/*     */     }
/* 194 */     if (from > end) {
/* 195 */       throw new IllegalArgumentException("negative range");
/*     */     }
/* 197 */     if (from < 0 || end > this.seq.length()) {
/* 198 */       throw new IllegalArgumentException("range outside of sequence");
/*     */     }
/* 200 */     if (from == end) {
/*     */       return;
/*     */     }
/* 203 */     int fromIdx = markerIndexAt(from);
/* 204 */     int endIdx = markerIndexAt(end);
/* 205 */     removeRange(fromIdx, endIdx, key);
/*     */   }
/*     */   
/*     */   public void removeAnimationState(AnimationState.StateKey key) {
/* 209 */     if (key == null) {
/* 210 */       throw new NullPointerException("key");
/*     */     }
/* 212 */     removeRange(0, this.markers.size(), key);
/*     */   }
/*     */   
/*     */   private void removeRange(int start, int end, AnimationState.StateKey key) {
/* 216 */     int bitIdx = key.getID() << 1;
/* 217 */     for (int i = start; i < end; i++) {
/* 218 */       ((Marker)this.markers.get(i)).clear(bitIdx);
/* 219 */       ((Marker)this.markers.get(i)).clear(bitIdx + 1);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clearAnimationStates() {
/* 224 */     this.markers.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void optimize() {
/* 232 */     if (this.markers.size() > 1) {
/* 233 */       Marker prev = this.markers.get(0);
/* 234 */       for (int i = 1; i < this.markers.size(); ) {
/* 235 */         Marker cur = this.markers.get(i);
/* 236 */         if (prev.equals(cur)) {
/* 237 */           this.markers.remove(i); continue;
/*     */         } 
/* 239 */         prev = cur;
/* 240 */         i++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void insert(int pos, int count) {
/* 247 */     int idx = find(pos) & Integer.MAX_VALUE;
/* 248 */     for (int end = this.markers.size(); idx < end; idx++) {
/* 249 */       ((Marker)this.markers.get(idx)).position += count;
/*     */     }
/*     */   }
/*     */   
/*     */   void delete(int pos, int count) {
/* 254 */     int startIdx = find(pos) & Integer.MAX_VALUE;
/* 255 */     int removeIdx = startIdx;
/* 256 */     int end = this.markers.size(); int idx;
/* 257 */     for (idx = startIdx; idx < end; idx++) {
/* 258 */       Marker m = this.markers.get(idx);
/* 259 */       int newPos = m.position - count;
/* 260 */       if (newPos <= pos) {
/* 261 */         newPos = pos;
/* 262 */         removeIdx = idx;
/*     */       } 
/* 264 */       m.position = newPos;
/*     */     } 
/* 266 */     for (idx = removeIdx; idx > startIdx;) {
/* 267 */       this.markers.remove(--idx);
/*     */     }
/*     */   }
/*     */   
/*     */   private int lastMarkerPos() {
/* 272 */     int numMarkers = this.markers.size();
/* 273 */     if (numMarkers > 0) {
/* 274 */       return ((Marker)this.markers.get(numMarkers - 1)).position;
/*     */     }
/* 276 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private int markerIndexAt(int pos) {
/* 281 */     int idx = find(pos);
/* 282 */     if (idx < 0) {
/* 283 */       idx &= Integer.MAX_VALUE;
/* 284 */       insertMarker(idx, pos);
/*     */     } 
/* 286 */     return idx;
/*     */   }
/*     */   
/*     */   private void insertMarker(int idx, int pos) {
/* 290 */     Marker newMarker = new Marker();
/* 291 */     if (idx > 0) {
/* 292 */       Marker leftMarker = this.markers.get(idx - 1);
/* 293 */       assert leftMarker.position < pos;
/* 294 */       newMarker.or(leftMarker);
/*     */     } 
/* 296 */     newMarker.position = pos;
/* 297 */     this.markers.add(idx, newMarker);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int find(int pos) {
/* 304 */     int lo = 0;
/* 305 */     int hi = this.markers.size();
/* 306 */     while (lo < hi) {
/* 307 */       int mid = lo + hi >>> 1;
/* 308 */       int markerPos = ((Marker)this.markers.get(mid)).position;
/* 309 */       if (pos < markerPos) {
/* 310 */         hi = mid; continue;
/* 311 */       }  if (pos > markerPos) {
/* 312 */         lo = mid + 1; continue;
/*     */       } 
/* 314 */       return mid;
/*     */     } 
/*     */     
/* 317 */     return lo | Integer.MIN_VALUE;
/*     */   }
/*     */   
/*     */   static class Marker extends BitSet {
/*     */     int position;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\StringAttributes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */