/*     */ package de.matthiasmann.twl.utils;
/*     */ 
/*     */ import java.util.Arrays;
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
/*     */ public class SparseGrid
/*     */ {
/*     */   Node root;
/*     */   int numLevels;
/*     */   
/*     */   public SparseGrid(int pageSize) {
/*  50 */     this.root = new Node(pageSize);
/*  51 */     this.numLevels = 1;
/*     */   }
/*     */   
/*     */   public Entry get(int row, int column) {
/*  55 */     if (this.root.size > 0) {
/*  56 */       int levels = this.numLevels;
/*  57 */       Entry e = this.root;
/*     */       
/*     */       do {
/*  60 */         Node node = (Node)e;
/*  61 */         int pos = node.findPos(row, column, node.size);
/*  62 */         if (pos == node.size) {
/*  63 */           return null;
/*     */         }
/*  65 */         e = node.children[pos];
/*  66 */       } while (--levels > 0);
/*     */       
/*  68 */       assert e != null;
/*  69 */       if (e.compare(row, column) == 0) {
/*  70 */         return e;
/*     */       }
/*     */     } 
/*  73 */     return null;
/*     */   }
/*     */   
/*     */   public void set(int row, int column, Entry entry) {
/*  77 */     entry.row = row;
/*  78 */     entry.column = column;
/*     */     
/*  80 */     if (this.root.size == 0) {
/*  81 */       this.root.insertAt(0, entry);
/*  82 */       this.root.updateRowColumn();
/*  83 */     } else if (!this.root.insert(entry, this.numLevels)) {
/*  84 */       splitRoot();
/*  85 */       this.root.insert(entry, this.numLevels);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Entry remove(int row, int column) {
/*  90 */     if (this.root.size == 0) {
/*  91 */       return null;
/*     */     }
/*  93 */     Entry e = this.root.remove(row, column, this.numLevels);
/*  94 */     if (e != null) {
/*  95 */       maybeRemoveRoot();
/*     */     }
/*  97 */     return e;
/*     */   }
/*     */   
/*     */   public void insertRows(int row, int count) {
/* 101 */     if (count > 0 && this.root.size > 0) {
/* 102 */       this.root.insertRows(row, count, this.numLevels);
/*     */     }
/*     */   }
/*     */   
/*     */   public void insertColumns(int column, int count) {
/* 107 */     if (count > 0 && this.root.size > 0) {
/* 108 */       this.root.insertColumns(column, count, this.numLevels);
/*     */     }
/*     */   }
/*     */   
/*     */   public void removeRows(int row, int count) {
/* 113 */     if (count > 0) {
/* 114 */       this.root.removeRows(row, count, this.numLevels);
/* 115 */       maybeRemoveRoot();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removeColumns(int column, int count) {
/* 120 */     if (count > 0) {
/* 121 */       this.root.removeColumns(column, count, this.numLevels);
/* 122 */       maybeRemoveRoot();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void iterate(int startRow, int startColumn, int endRow, int endColumn, GridFunction func) {
/* 128 */     if (this.root.size > 0) {
/* 129 */       Node node; int pos, levels = this.numLevels;
/* 130 */       Entry e = this.root;
/*     */ 
/*     */ 
/*     */       
/*     */       do {
/* 135 */         node = (Node)e;
/* 136 */         pos = node.findPos(startRow, startColumn, node.size - 1);
/* 137 */         e = node.children[pos];
/* 138 */       } while (--levels > 0);
/*     */       
/* 140 */       assert e != null;
/* 141 */       if (e.compare(startRow, startColumn) < 0) {
/*     */         return;
/*     */       }
/*     */       
/*     */       do {
/* 146 */         for (int size = node.size; pos < size; pos++) {
/* 147 */           e = node.children[pos];
/* 148 */           if (e.row > endRow) {
/*     */             return;
/*     */           }
/* 151 */           if (e.column >= startColumn && e.column <= endColumn) {
/* 152 */             func.apply(e.row, e.column, e);
/*     */           }
/*     */         } 
/* 155 */         pos = 0;
/* 156 */         node = node.next;
/* 157 */       } while (node != null);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 162 */     return (this.root.size == 0);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 166 */     Arrays.fill((Object[])this.root.children, (Object)null);
/* 167 */     this.root.size = 0;
/* 168 */     this.numLevels = 1;
/*     */   }
/*     */   
/*     */   private void maybeRemoveRoot() {
/* 172 */     while (this.numLevels > 1 && this.root.size == 1) {
/* 173 */       this.root = (Node)this.root.children[0];
/* 174 */       this.root.prev = null;
/* 175 */       this.root.next = null;
/* 176 */       this.numLevels--;
/*     */     } 
/* 178 */     if (this.root.size == 0) {
/* 179 */       this.numLevels = 1;
/*     */     }
/*     */   }
/*     */   
/*     */   private void splitRoot() {
/* 184 */     Node newNode = this.root.split();
/* 185 */     Node newRoot = new Node(this.root.children.length);
/* 186 */     newRoot.children[0] = this.root;
/* 187 */     newRoot.children[1] = newNode;
/* 188 */     newRoot.size = 2;
/* 189 */     this.root = newRoot;
/* 190 */     this.numLevels++;
/*     */   }
/*     */   
/*     */   public static interface GridFunction {
/*     */     void apply(int param1Int1, int param1Int2, SparseGrid.Entry param1Entry); }
/*     */   
/*     */   static class Node extends Entry { final SparseGrid.Entry[] children;
/*     */     int size;
/*     */     
/*     */     public Node(int size) {
/* 200 */       this.children = new SparseGrid.Entry[size];
/*     */     }
/*     */     Node next; Node prev;
/*     */     boolean insert(SparseGrid.Entry e, int levels) {
/* 204 */       if (--levels == 0) {
/* 205 */         return insertLeaf(e);
/*     */       }
/*     */       
/*     */       while (true) {
/* 209 */         int pos = findPos(e.row, e.column, this.size - 1);
/* 210 */         assert pos < this.size;
/* 211 */         Node node = (Node)this.children[pos];
/* 212 */         if (!node.insert(e, levels)) {
/* 213 */           if (isFull()) {
/* 214 */             return false;
/*     */           }
/* 216 */           Node node2 = node.split();
/* 217 */           insertAt(pos + 1, node2); continue;
/*     */         }  break;
/*     */       } 
/* 220 */       updateRowColumn();
/* 221 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     boolean insertLeaf(SparseGrid.Entry e) {
/* 226 */       int pos = findPos(e.row, e.column, this.size);
/* 227 */       if (pos < this.size) {
/* 228 */         SparseGrid.Entry c = this.children[pos];
/* 229 */         assert c.getClass() != Node.class;
/* 230 */         int cmp = c.compare(e.row, e.column);
/* 231 */         if (cmp == 0) {
/* 232 */           this.children[pos] = e;
/* 233 */           return true;
/*     */         } 
/* 235 */         assert cmp > 0;
/*     */       } 
/*     */       
/* 238 */       if (isFull()) {
/* 239 */         return false;
/*     */       }
/* 241 */       insertAt(pos, e);
/* 242 */       return true;
/*     */     }
/*     */     
/*     */     SparseGrid.Entry remove(int row, int column, int levels) {
/* 246 */       if (--levels == 0) {
/* 247 */         return removeLeaf(row, column);
/*     */       }
/*     */       
/* 250 */       int pos = findPos(row, column, this.size - 1);
/* 251 */       assert pos < this.size;
/* 252 */       Node node = (Node)this.children[pos];
/* 253 */       SparseGrid.Entry e = node.remove(row, column, levels);
/* 254 */       if (e != null) {
/* 255 */         if (node.size == 0) {
/* 256 */           removeNodeAt(pos);
/* 257 */         } else if (node.isBelowHalf()) {
/* 258 */           tryMerge(pos);
/*     */         } 
/* 260 */         updateRowColumn();
/*     */       } 
/* 262 */       return e;
/*     */     }
/*     */     
/*     */     SparseGrid.Entry removeLeaf(int row, int column) {
/* 266 */       int pos = findPos(row, column, this.size);
/* 267 */       if (pos == this.size) {
/* 268 */         return null;
/*     */       }
/*     */       
/* 271 */       SparseGrid.Entry c = this.children[pos];
/* 272 */       assert c.getClass() != Node.class;
/* 273 */       int cmp = c.compare(row, column);
/* 274 */       if (cmp == 0) {
/* 275 */         removeAt(pos);
/* 276 */         if (pos == this.size && this.size > 0) {
/* 277 */           updateRowColumn();
/*     */         }
/* 279 */         return c;
/*     */       } 
/* 281 */       return null;
/*     */     }
/*     */     
/*     */     int findPos(int row, int column, int high) {
/* 285 */       int low = 0;
/* 286 */       while (low < high) {
/* 287 */         int mid = low + high >>> 1;
/* 288 */         SparseGrid.Entry e = this.children[mid];
/* 289 */         int cmp = e.compare(row, column);
/* 290 */         if (cmp > 0) {
/* 291 */           high = mid; continue;
/* 292 */         }  if (cmp < 0) {
/* 293 */           low = mid + 1; continue;
/*     */         } 
/* 295 */         return mid;
/*     */       } 
/*     */       
/* 298 */       return low;
/*     */     }
/*     */     
/*     */     void insertRows(int row, int count, int levels) {
/* 302 */       if (--levels > 0) {
/* 303 */         for (int i = this.size; i-- > 0; ) {
/* 304 */           Node n = (Node)this.children[i];
/* 305 */           if (n.row < row) {
/*     */             break;
/*     */           }
/* 308 */           n.insertRows(row, count, levels);
/*     */         } 
/*     */       } else {
/* 311 */         for (int i = this.size; i-- > 0; ) {
/* 312 */           SparseGrid.Entry e = this.children[i];
/* 313 */           if (e.row < row) {
/*     */             break;
/*     */           }
/* 316 */           e.row += count;
/*     */         } 
/*     */       } 
/* 319 */       updateRowColumn();
/*     */     }
/*     */     
/*     */     void insertColumns(int column, int count, int levels) {
/* 323 */       if (--levels > 0) {
/* 324 */         for (int i = 0; i < this.size; i++) {
/* 325 */           Node n = (Node)this.children[i];
/* 326 */           n.insertColumns(column, count, levels);
/*     */         } 
/*     */       } else {
/* 329 */         for (int i = 0; i < this.size; i++) {
/* 330 */           SparseGrid.Entry e = this.children[i];
/* 331 */           if (e.column >= column) {
/* 332 */             e.column += count;
/*     */           }
/*     */         } 
/*     */       } 
/* 336 */       updateRowColumn();
/*     */     }
/*     */     
/*     */     boolean removeRows(int row, int count, int levels) {
/* 340 */       if (--levels > 0) {
/* 341 */         boolean needsMerging = false;
/* 342 */         for (int i = this.size; i-- > 0; ) {
/* 343 */           Node n = (Node)this.children[i];
/* 344 */           if (n.row < row) {
/*     */             break;
/*     */           }
/* 347 */           if (n.removeRows(row, count, levels)) {
/* 348 */             removeNodeAt(i); continue;
/*     */           } 
/* 350 */           needsMerging |= n.isBelowHalf();
/*     */         } 
/*     */         
/* 353 */         if (needsMerging && this.size > 1) {
/* 354 */           tryMerge();
/*     */         }
/*     */       } else {
/* 357 */         for (int i = this.size; i-- > 0; ) {
/* 358 */           SparseGrid.Entry e = this.children[i];
/* 359 */           if (e.row < row) {
/*     */             break;
/*     */           }
/* 362 */           e.row -= count;
/* 363 */           if (e.row < row) {
/* 364 */             removeAt(i);
/*     */           }
/*     */         } 
/*     */       } 
/* 368 */       if (this.size == 0) {
/* 369 */         return true;
/*     */       }
/* 371 */       updateRowColumn();
/* 372 */       return false;
/*     */     }
/*     */     
/*     */     boolean removeColumns(int column, int count, int levels) {
/* 376 */       if (--levels > 0) {
/* 377 */         boolean needsMerging = false;
/* 378 */         for (int i = this.size; i-- > 0; ) {
/* 379 */           Node n = (Node)this.children[i];
/* 380 */           if (n.removeColumns(column, count, levels)) {
/* 381 */             removeNodeAt(i); continue;
/*     */           } 
/* 383 */           needsMerging |= n.isBelowHalf();
/*     */         } 
/*     */         
/* 386 */         if (needsMerging && this.size > 1) {
/* 387 */           tryMerge();
/*     */         }
/*     */       } else {
/* 390 */         for (int i = this.size; i-- > 0; ) {
/* 391 */           SparseGrid.Entry e = this.children[i];
/* 392 */           if (e.column >= column) {
/* 393 */             e.column -= count;
/* 394 */             if (e.column < column) {
/* 395 */               removeAt(i);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/* 400 */       if (this.size == 0) {
/* 401 */         return true;
/*     */       }
/* 403 */       updateRowColumn();
/* 404 */       return false;
/*     */     }
/*     */     
/*     */     void insertAt(int idx, SparseGrid.Entry what) {
/* 408 */       System.arraycopy(this.children, idx, this.children, idx + 1, this.size - idx);
/* 409 */       this.children[idx] = what;
/* 410 */       if (idx == this.size++) {
/* 411 */         updateRowColumn();
/*     */       }
/*     */     }
/*     */     
/*     */     void removeAt(int idx) {
/* 416 */       this.size--;
/* 417 */       System.arraycopy(this.children, idx + 1, this.children, idx, this.size - idx);
/* 418 */       this.children[this.size] = null;
/*     */     }
/*     */     
/*     */     void removeNodeAt(int idx) {
/* 422 */       Node n = (Node)this.children[idx];
/* 423 */       if (n.next != null) {
/* 424 */         n.next.prev = n.prev;
/*     */       }
/* 426 */       if (n.prev != null) {
/* 427 */         n.prev.next = n.next;
/*     */       }
/* 429 */       n.next = null;
/* 430 */       n.prev = null;
/* 431 */       removeAt(idx);
/*     */     }
/*     */     
/*     */     void tryMerge() {
/* 435 */       if (this.size == 2) {
/* 436 */         tryMerge2(0);
/*     */       } else {
/* 438 */         for (int i = this.size - 1; i-- > 1;) {
/* 439 */           if (tryMerge3(i)) {
/* 440 */             i--;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     void tryMerge(int pos) {
/* 447 */       switch (this.size) {
/*     */         case 0:
/*     */         case 1:
/*     */           return;
/*     */         
/*     */         case 2:
/* 453 */           tryMerge2(0);
/*     */       } 
/*     */       
/* 456 */       if (pos + 1 == this.size)
/* 457 */         tryMerge3(pos - 1); 
/* 458 */       if (pos == 0) {
/* 459 */         tryMerge3(1);
/*     */       }
/* 461 */       tryMerge3(pos);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private void tryMerge2(int pos) {
/* 468 */       Node n1 = (Node)this.children[pos];
/* 469 */       Node n2 = (Node)this.children[pos + 1];
/* 470 */       if (n1.isBelowHalf() || n2.isBelowHalf()) {
/* 471 */         int sumSize = n1.size + n2.size;
/* 472 */         if (sumSize < this.children.length) {
/* 473 */           System.arraycopy(n2.children, 0, n1.children, n1.size, n2.size);
/* 474 */           n1.size = sumSize;
/* 475 */           n1.updateRowColumn();
/* 476 */           removeNodeAt(pos + 1);
/*     */         } else {
/* 478 */           Object[] temp = collect2(sumSize, n1, n2);
/* 479 */           distribute2(temp, n1, n2);
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     private boolean tryMerge3(int pos) {
/* 485 */       Node n0 = (Node)this.children[pos - 1];
/* 486 */       Node n1 = (Node)this.children[pos];
/* 487 */       Node n2 = (Node)this.children[pos + 1];
/* 488 */       if (n0.isBelowHalf() || n1.isBelowHalf() || n2.isBelowHalf()) {
/* 489 */         int sumSize = n0.size + n1.size + n2.size;
/* 490 */         if (sumSize < this.children.length) {
/* 491 */           System.arraycopy(n1.children, 0, n0.children, n0.size, n1.size);
/* 492 */           System.arraycopy(n2.children, 0, n0.children, n0.size + n1.size, n2.size);
/* 493 */           n0.size = sumSize;
/* 494 */           n0.updateRowColumn();
/* 495 */           removeNodeAt(pos + 1);
/* 496 */           removeNodeAt(pos);
/* 497 */           return true;
/*     */         } 
/* 499 */         Object[] temp = collect3(sumSize, n0, n1, n2);
/* 500 */         if (sumSize < 2 * this.children.length) {
/* 501 */           distribute2(temp, n0, n1);
/* 502 */           removeNodeAt(pos + 1);
/*     */         } else {
/* 504 */           distribute3(temp, n0, n1, n2);
/*     */         } 
/*     */       } 
/*     */       
/* 508 */       return false;
/*     */     }
/*     */     
/*     */     private Object[] collect2(int sumSize, Node n0, Node n1) {
/* 512 */       Object[] temp = new Object[sumSize];
/* 513 */       System.arraycopy(n0.children, 0, temp, 0, n0.size);
/* 514 */       System.arraycopy(n1.children, 0, temp, n0.size, n1.size);
/* 515 */       return temp;
/*     */     }
/*     */     
/*     */     private Object[] collect3(int sumSize, Node n0, Node n1, Node n2) {
/* 519 */       Object[] temp = new Object[sumSize];
/* 520 */       System.arraycopy(n0.children, 0, temp, 0, n0.size);
/* 521 */       System.arraycopy(n1.children, 0, temp, n0.size, n1.size);
/* 522 */       System.arraycopy(n2.children, 0, temp, n0.size + n1.size, n2.size);
/* 523 */       return temp;
/*     */     }
/*     */     
/*     */     private void distribute2(Object[] src, Node n0, Node n1) {
/* 527 */       int sumSize = src.length;
/*     */       
/* 529 */       n0.size = sumSize / 2;
/* 530 */       n1.size = sumSize - n0.size;
/*     */       
/* 532 */       System.arraycopy(src, 0, n0.children, 0, n0.size);
/* 533 */       System.arraycopy(src, n0.size, n1.children, 0, n1.size);
/*     */       
/* 535 */       n0.updateRowColumn();
/* 536 */       n1.updateRowColumn();
/*     */     }
/*     */     
/*     */     private void distribute3(Object[] src, Node n0, Node n1, Node n2) {
/* 540 */       int sumSize = src.length;
/*     */       
/* 542 */       n0.size = sumSize / 3;
/* 543 */       n1.size = (sumSize - n0.size) / 2;
/* 544 */       n2.size = sumSize - n0.size + n1.size;
/*     */       
/* 546 */       System.arraycopy(src, 0, n0.children, 0, n0.size);
/* 547 */       System.arraycopy(src, n0.size, n1.children, 0, n1.size);
/* 548 */       System.arraycopy(src, n0.size + n1.size, n2.children, 0, n2.size);
/*     */       
/* 550 */       n0.updateRowColumn();
/* 551 */       n1.updateRowColumn();
/* 552 */       n2.updateRowColumn();
/*     */     }
/*     */     
/*     */     boolean isFull() {
/* 556 */       return (this.size == this.children.length);
/*     */     }
/*     */     
/*     */     boolean isBelowHalf() {
/* 560 */       return (this.size * 2 < this.children.length);
/*     */     }
/*     */     
/*     */     Node split() {
/* 564 */       Node newNode = new Node(this.children.length);
/* 565 */       int size1 = this.size / 2;
/* 566 */       int size2 = this.size - size1;
/* 567 */       System.arraycopy(this.children, size1, newNode.children, 0, size2);
/* 568 */       Arrays.fill((Object[])this.children, size1, this.size, (Object)null);
/* 569 */       newNode.size = size2;
/* 570 */       newNode.updateRowColumn();
/* 571 */       newNode.prev = this;
/* 572 */       newNode.next = this.next;
/* 573 */       this.size = size1;
/* 574 */       updateRowColumn();
/* 575 */       this.next = newNode;
/* 576 */       if (newNode.next != null) {
/* 577 */         newNode.next.prev = newNode;
/*     */       }
/* 579 */       return newNode;
/*     */     }
/*     */     
/*     */     void updateRowColumn() {
/* 583 */       SparseGrid.Entry e = this.children[this.size - 1];
/* 584 */       this.row = e.row;
/* 585 */       this.column = e.column;
/*     */     } }
/*     */ 
/*     */   
/*     */   public static class Entry {
/*     */     int row;
/*     */     int column;
/*     */     
/*     */     int compare(int row, int column) {
/* 594 */       int diff = this.row - row;
/* 595 */       if (diff == 0) {
/* 596 */         diff = this.column - column;
/*     */       }
/* 598 */       return diff;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\tw\\utils\SparseGrid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */