/*     */ package de.matthiasmann.twl.utils;
/*     */ 
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.Arrays;
/*     */ import java.util.zip.CRC32;
/*     */ import java.util.zip.Inflater;
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
/*     */ public class PNGDecoder
/*     */ {
/*     */   public enum Format
/*     */   {
/*  49 */     ALPHA(1, true),
/*  50 */     LUMINANCE(1, false),
/*  51 */     LUMINANCE_ALPHA(2, true),
/*  52 */     RGB(3, false),
/*  53 */     RGBA(4, true),
/*  54 */     BGRA(4, true),
/*  55 */     ABGR(4, true);
/*     */     
/*     */     final int numComponents;
/*     */     final boolean hasAlpha;
/*     */     
/*     */     Format(int numComponents, boolean hasAlpha) {
/*  61 */       this.numComponents = numComponents;
/*  62 */       this.hasAlpha = hasAlpha;
/*     */     }
/*     */     
/*     */     public int getNumComponents() {
/*  66 */       return this.numComponents;
/*     */     }
/*     */     
/*     */     public boolean isHasAlpha() {
/*  70 */       return this.hasAlpha;
/*     */     }
/*     */   }
/*     */   
/*  74 */   private static final byte[] SIGNATURE = new byte[] { -119, 80, 78, 71, 13, 10, 26, 10 };
/*     */   
/*     */   private static final int IHDR = 1229472850;
/*     */   
/*     */   private static final int PLTE = 1347179589;
/*     */   
/*     */   private static final int tRNS = 1951551059;
/*     */   
/*     */   private static final int IDAT = 1229209940;
/*     */   
/*     */   private static final int IEND = 1229278788;
/*     */   private static final byte COLOR_GREYSCALE = 0;
/*     */   private static final byte COLOR_TRUECOLOR = 2;
/*     */   private static final byte COLOR_INDEXED = 3;
/*     */   private static final byte COLOR_GREYALPHA = 4;
/*     */   private static final byte COLOR_TRUEALPHA = 6;
/*     */   private final InputStream input;
/*     */   private final CRC32 crc;
/*     */   private final byte[] buffer;
/*     */   private int chunkLength;
/*     */   private int chunkType;
/*     */   private int chunkRemaining;
/*     */   private int width;
/*     */   private int height;
/*     */   private int bitdepth;
/*     */   private int colorType;
/*     */   private int bytesPerPixel;
/*     */   private byte[] palette;
/*     */   private byte[] paletteA;
/*     */   private byte[] transPixel;
/*     */   
/*     */   public PNGDecoder(InputStream input) throws IOException {
/* 106 */     this.input = input;
/* 107 */     this.crc = new CRC32();
/* 108 */     this.buffer = new byte[4096];
/*     */     
/* 110 */     readFully(this.buffer, 0, SIGNATURE.length);
/* 111 */     if (!checkSignature(this.buffer)) {
/* 112 */       throw new IOException("Not a valid PNG file");
/*     */     }
/*     */     
/* 115 */     openChunk(1229472850);
/* 116 */     readIHDR();
/* 117 */     closeChunk();
/*     */     
/*     */     while (true) {
/* 120 */       openChunk();
/* 121 */       switch (this.chunkType) {
/*     */         case 1229209940:
/*     */           break;
/*     */         case 1347179589:
/* 125 */           readPLTE();
/*     */           break;
/*     */         case 1951551059:
/* 128 */           readtRNS();
/*     */           break;
/*     */       } 
/* 131 */       closeChunk();
/*     */     } 
/*     */     
/* 134 */     if (this.colorType == 3 && this.palette == null) {
/* 135 */       throw new IOException("Missing PLTE chunk");
/*     */     }
/*     */   }
/*     */   
/*     */   public int getHeight() {
/* 140 */     return this.height;
/*     */   }
/*     */   
/*     */   public int getWidth() {
/* 144 */     return this.width;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasAlphaChannel() {
/* 155 */     return (this.colorType == 6 || this.colorType == 4);
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
/*     */   public boolean hasAlpha() {
/* 167 */     return (hasAlphaChannel() || this.paletteA != null || this.transPixel != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRGB() {
/* 172 */     return (this.colorType == 6 || this.colorType == 2 || this.colorType == 3);
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
/*     */   public void overwriteTRNS(byte r, byte g, byte b) {
/* 189 */     if (hasAlphaChannel()) {
/* 190 */       throw new UnsupportedOperationException("image has an alpha channel");
/*     */     }
/* 192 */     byte[] pal = this.palette;
/* 193 */     if (pal == null) {
/* 194 */       this.transPixel = new byte[] { 0, r, 0, g, 0, b };
/*     */     } else {
/* 196 */       this.paletteA = new byte[pal.length / 3];
/* 197 */       for (int i = 0, j = 0; i < pal.length; i += 3, j++) {
/* 198 */         if (pal[i] != r || pal[i + 1] != g || pal[i + 2] != b) {
/* 199 */           this.paletteA[j] = -1;
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
/*     */   public Format decideTextureFormat(Format fmt) {
/* 213 */     switch (this.colorType) {
/*     */       case 2:
/* 215 */         switch (fmt) { case ABGR:
/*     */           case RGBA:
/*     */           case BGRA:
/*     */           case RGB:
/* 219 */             return fmt; }
/* 220 */          return Format.RGB;
/*     */       
/*     */       case 6:
/* 223 */         switch (fmt) { case ABGR:
/*     */           case RGBA:
/*     */           case BGRA:
/*     */           case RGB:
/* 227 */             return fmt; }
/* 228 */          return Format.RGBA;
/*     */       
/*     */       case 0:
/* 231 */         switch (fmt) { case LUMINANCE:
/*     */           case ALPHA:
/* 233 */             return fmt; }
/* 234 */          return Format.LUMINANCE;
/*     */       
/*     */       case 4:
/* 237 */         return Format.LUMINANCE_ALPHA;
/*     */       case 3:
/* 239 */         switch (fmt) { case ABGR:
/*     */           case RGBA:
/*     */           case BGRA:
/* 242 */             return fmt; }
/* 243 */          return Format.RGBA;
/*     */     } 
/*     */     
/* 246 */     throw new UnsupportedOperationException("Not yet implemented");
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
/*     */   public void decode(ByteBuffer buffer, int stride, Format fmt) throws IOException {
/* 263 */     int offset = buffer.position();
/* 264 */     int lineSize = (this.width * this.bitdepth + 7) / 8 * this.bytesPerPixel;
/* 265 */     byte[] curLine = new byte[lineSize + 1];
/* 266 */     byte[] prevLine = new byte[lineSize + 1];
/* 267 */     byte[] palLine = (this.bitdepth < 8) ? new byte[this.width + 1] : null;
/*     */     
/* 269 */     Inflater inflater = new Inflater();
/*     */     try {
/* 271 */       for (int y = 0; y < this.height; y++) {
/* 272 */         readChunkUnzip(inflater, curLine, 0, curLine.length);
/* 273 */         unfilter(curLine, prevLine);
/*     */         
/* 275 */         buffer.position(offset + y * stride);
/*     */         
/* 277 */         switch (this.colorType) {
/*     */           case 2:
/* 279 */             switch (fmt) { case ABGR:
/* 280 */                 copyRGBtoABGR(buffer, curLine); break;
/* 281 */               case RGBA: copyRGBtoRGBA(buffer, curLine); break;
/* 282 */               case BGRA: copyRGBtoBGRA(buffer, curLine); break;
/* 283 */               case RGB: copy(buffer, curLine); break; }
/* 284 */              throw new UnsupportedOperationException("Unsupported format for this image");
/*     */ 
/*     */           
/*     */           case 6:
/* 288 */             switch (fmt) { case ABGR:
/* 289 */                 copyRGBAtoABGR(buffer, curLine); break;
/* 290 */               case RGBA: copy(buffer, curLine); break;
/* 291 */               case BGRA: copyRGBAtoBGRA(buffer, curLine); break;
/* 292 */               case RGB: copyRGBAtoRGB(buffer, curLine); break; }
/* 293 */              throw new UnsupportedOperationException("Unsupported format for this image");
/*     */ 
/*     */           
/*     */           case 0:
/* 297 */             switch (fmt) { case LUMINANCE:
/*     */               case ALPHA:
/* 299 */                 copy(buffer, curLine); break; }
/* 300 */              throw new UnsupportedOperationException("Unsupported format for this image");
/*     */ 
/*     */           
/*     */           case 4:
/* 304 */             switch (fmt) { case LUMINANCE_ALPHA:
/* 305 */                 copy(buffer, curLine); break; }
/* 306 */              throw new UnsupportedOperationException("Unsupported format for this image");
/*     */ 
/*     */           
/*     */           case 3:
/* 310 */             switch (this.bitdepth) { case 8:
/* 311 */                 palLine = curLine; break;
/* 312 */               case 4: expand4(curLine, palLine); break;
/* 313 */               case 2: expand2(curLine, palLine); break;
/* 314 */               case 1: expand1(curLine, palLine); break;
/* 315 */               default: throw new UnsupportedOperationException("Unsupported bitdepth for this image"); }
/*     */             
/* 317 */             switch (fmt) { case ABGR:
/* 318 */                 copyPALtoABGR(buffer, palLine); break;
/* 319 */               case RGBA: copyPALtoRGBA(buffer, palLine); break;
/* 320 */               case BGRA: copyPALtoBGRA(buffer, palLine); break; }
/* 321 */              throw new UnsupportedOperationException("Unsupported format for this image");
/*     */ 
/*     */           
/*     */           default:
/* 325 */             throw new UnsupportedOperationException("Not yet implemented");
/*     */         } 
/*     */         
/* 328 */         byte[] tmp = curLine;
/* 329 */         curLine = prevLine;
/* 330 */         prevLine = tmp;
/*     */       } 
/*     */     } finally {
/* 333 */       inflater.end();
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
/*     */   public void decodeFlipped(ByteBuffer buffer, int stride, Format fmt) throws IOException {
/* 350 */     if (stride <= 0) {
/* 351 */       throw new IllegalArgumentException("stride");
/*     */     }
/* 353 */     int pos = buffer.position();
/* 354 */     int posDelta = (this.height - 1) * stride;
/* 355 */     buffer.position(pos + posDelta);
/* 356 */     decode(buffer, -stride, fmt);
/* 357 */     buffer.position(buffer.position() + posDelta);
/*     */   }
/*     */   
/*     */   private void copy(ByteBuffer buffer, byte[] curLine) {
/* 361 */     buffer.put(curLine, 1, curLine.length - 1);
/*     */   }
/*     */   
/*     */   private void copyRGBtoABGR(ByteBuffer buffer, byte[] curLine) {
/* 365 */     if (this.transPixel != null) {
/* 366 */       byte tr = this.transPixel[1];
/* 367 */       byte tg = this.transPixel[3];
/* 368 */       byte tb = this.transPixel[5];
/* 369 */       for (int i = 1, n = curLine.length; i < n; i += 3) {
/* 370 */         byte r = curLine[i];
/* 371 */         byte g = curLine[i + 1];
/* 372 */         byte b = curLine[i + 2];
/* 373 */         byte a = -1;
/* 374 */         if (r == tr && g == tg && b == tb) {
/* 375 */           a = 0;
/*     */         }
/* 377 */         buffer.put(a).put(b).put(g).put(r);
/*     */       } 
/*     */     } else {
/* 380 */       for (int i = 1, n = curLine.length; i < n; i += 3) {
/* 381 */         buffer.put((byte)-1).put(curLine[i + 2]).put(curLine[i + 1]).put(curLine[i]);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void copyRGBtoRGBA(ByteBuffer buffer, byte[] curLine) {
/* 387 */     if (this.transPixel != null) {
/* 388 */       byte tr = this.transPixel[1];
/* 389 */       byte tg = this.transPixel[3];
/* 390 */       byte tb = this.transPixel[5];
/* 391 */       for (int i = 1, n = curLine.length; i < n; i += 3) {
/* 392 */         byte r = curLine[i];
/* 393 */         byte g = curLine[i + 1];
/* 394 */         byte b = curLine[i + 2];
/* 395 */         byte a = -1;
/* 396 */         if (r == tr && g == tg && b == tb) {
/* 397 */           a = 0;
/*     */         }
/* 399 */         buffer.put(r).put(g).put(b).put(a);
/*     */       } 
/*     */     } else {
/* 402 */       for (int i = 1, n = curLine.length; i < n; i += 3) {
/* 403 */         buffer.put(curLine[i]).put(curLine[i + 1]).put(curLine[i + 2]).put((byte)-1);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void copyRGBtoBGRA(ByteBuffer buffer, byte[] curLine) {
/* 409 */     if (this.transPixel != null) {
/* 410 */       byte tr = this.transPixel[1];
/* 411 */       byte tg = this.transPixel[3];
/* 412 */       byte tb = this.transPixel[5];
/* 413 */       for (int i = 1, n = curLine.length; i < n; i += 3) {
/* 414 */         byte r = curLine[i];
/* 415 */         byte g = curLine[i + 1];
/* 416 */         byte b = curLine[i + 2];
/* 417 */         byte a = -1;
/* 418 */         if (r == tr && g == tg && b == tb) {
/* 419 */           a = 0;
/*     */         }
/* 421 */         buffer.put(b).put(g).put(r).put(a);
/*     */       } 
/*     */     } else {
/* 424 */       for (int i = 1, n = curLine.length; i < n; i += 3) {
/* 425 */         buffer.put(curLine[i + 2]).put(curLine[i + 1]).put(curLine[i]).put((byte)-1);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void copyRGBAtoABGR(ByteBuffer buffer, byte[] curLine) {
/* 431 */     for (int i = 1, n = curLine.length; i < n; i += 4) {
/* 432 */       buffer.put(curLine[i + 3]).put(curLine[i + 2]).put(curLine[i + 1]).put(curLine[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   private void copyRGBAtoBGRA(ByteBuffer buffer, byte[] curLine) {
/* 437 */     for (int i = 1, n = curLine.length; i < n; i += 4) {
/* 438 */       buffer.put(curLine[i + 2]).put(curLine[i + 1]).put(curLine[i]).put(curLine[i + 3]);
/*     */     }
/*     */   }
/*     */   
/*     */   private void copyRGBAtoRGB(ByteBuffer buffer, byte[] curLine) {
/* 443 */     for (int i = 1, n = curLine.length; i < n; i += 4) {
/* 444 */       buffer.put(curLine[i]).put(curLine[i + 1]).put(curLine[i + 2]);
/*     */     }
/*     */   }
/*     */   
/*     */   private void copyPALtoABGR(ByteBuffer buffer, byte[] curLine) {
/* 449 */     if (this.paletteA != null) {
/* 450 */       for (int i = 1, n = curLine.length; i < n; i++) {
/* 451 */         int idx = curLine[i] & 0xFF;
/* 452 */         byte r = this.palette[idx * 3 + 0];
/* 453 */         byte g = this.palette[idx * 3 + 1];
/* 454 */         byte b = this.palette[idx * 3 + 2];
/* 455 */         byte a = this.paletteA[idx];
/* 456 */         buffer.put(a).put(b).put(g).put(r);
/*     */       } 
/*     */     } else {
/* 459 */       for (int i = 1, n = curLine.length; i < n; i++) {
/* 460 */         int idx = curLine[i] & 0xFF;
/* 461 */         byte r = this.palette[idx * 3 + 0];
/* 462 */         byte g = this.palette[idx * 3 + 1];
/* 463 */         byte b = this.palette[idx * 3 + 2];
/* 464 */         byte a = -1;
/* 465 */         buffer.put(a).put(b).put(g).put(r);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void copyPALtoRGBA(ByteBuffer buffer, byte[] curLine) {
/* 471 */     if (this.paletteA != null) {
/* 472 */       for (int i = 1, n = curLine.length; i < n; i++) {
/* 473 */         int idx = curLine[i] & 0xFF;
/* 474 */         byte r = this.palette[idx * 3 + 0];
/* 475 */         byte g = this.palette[idx * 3 + 1];
/* 476 */         byte b = this.palette[idx * 3 + 2];
/* 477 */         byte a = this.paletteA[idx];
/* 478 */         buffer.put(r).put(g).put(b).put(a);
/*     */       } 
/*     */     } else {
/* 481 */       for (int i = 1, n = curLine.length; i < n; i++) {
/* 482 */         int idx = curLine[i] & 0xFF;
/* 483 */         byte r = this.palette[idx * 3 + 0];
/* 484 */         byte g = this.palette[idx * 3 + 1];
/* 485 */         byte b = this.palette[idx * 3 + 2];
/* 486 */         byte a = -1;
/* 487 */         buffer.put(r).put(g).put(b).put(a);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void copyPALtoBGRA(ByteBuffer buffer, byte[] curLine) {
/* 493 */     if (this.paletteA != null) {
/* 494 */       for (int i = 1, n = curLine.length; i < n; i++) {
/* 495 */         int idx = curLine[i] & 0xFF;
/* 496 */         byte r = this.palette[idx * 3 + 0];
/* 497 */         byte g = this.palette[idx * 3 + 1];
/* 498 */         byte b = this.palette[idx * 3 + 2];
/* 499 */         byte a = this.paletteA[idx];
/* 500 */         buffer.put(b).put(g).put(r).put(a);
/*     */       } 
/*     */     } else {
/* 503 */       for (int i = 1, n = curLine.length; i < n; i++) {
/* 504 */         int idx = curLine[i] & 0xFF;
/* 505 */         byte r = this.palette[idx * 3 + 0];
/* 506 */         byte g = this.palette[idx * 3 + 1];
/* 507 */         byte b = this.palette[idx * 3 + 2];
/* 508 */         byte a = -1;
/* 509 */         buffer.put(b).put(g).put(r).put(a);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void expand4(byte[] src, byte[] dst) {
/* 515 */     for (int i = 1, n = dst.length; i < n; i += 2) {
/* 516 */       int val = src[1 + (i >> 1)] & 0xFF;
/* 517 */       switch (n - i) { default:
/* 518 */           dst[i + 1] = (byte)(val & 0xF); break;
/* 519 */         case 1: break; }  dst[i] = (byte)(val >> 4);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void expand2(byte[] src, byte[] dst) {
/* 525 */     for (int i = 1, n = dst.length; i < n; i += 4) {
/* 526 */       int val = src[1 + (i >> 2)] & 0xFF;
/* 527 */       switch (n - i) { default:
/* 528 */           dst[i + 3] = (byte)(val & 0x3);
/* 529 */         case 3: dst[i + 2] = (byte)(val >> 2 & 0x3);
/* 530 */         case 2: dst[i + 1] = (byte)(val >> 4 & 0x3); break;
/* 531 */         case 1: break; }  dst[i] = (byte)(val >> 6);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void expand1(byte[] src, byte[] dst) {
/* 537 */     for (int i = 1, n = dst.length; i < n; i += 8) {
/* 538 */       int val = src[1 + (i >> 3)] & 0xFF;
/* 539 */       switch (n - i) { default:
/* 540 */           dst[i + 7] = (byte)(val & 0x1);
/* 541 */         case 7: dst[i + 6] = (byte)(val >> 1 & 0x1);
/* 542 */         case 6: dst[i + 5] = (byte)(val >> 2 & 0x1);
/* 543 */         case 5: dst[i + 4] = (byte)(val >> 3 & 0x1);
/* 544 */         case 4: dst[i + 3] = (byte)(val >> 4 & 0x1);
/* 545 */         case 3: dst[i + 2] = (byte)(val >> 5 & 0x1);
/* 546 */         case 2: dst[i + 1] = (byte)(val >> 6 & 0x1); break;
/* 547 */         case 1: break; }  dst[i] = (byte)(val >> 7);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void unfilter(byte[] curLine, byte[] prevLine) throws IOException {
/* 553 */     switch (curLine[0]) {
/*     */       case 0:
/*     */         return;
/*     */       case 1:
/* 557 */         unfilterSub(curLine);
/*     */       
/*     */       case 2:
/* 560 */         unfilterUp(curLine, prevLine);
/*     */       
/*     */       case 3:
/* 563 */         unfilterAverage(curLine, prevLine);
/*     */       
/*     */       case 4:
/* 566 */         unfilterPaeth(curLine, prevLine);
/*     */     } 
/*     */     
/* 569 */     throw new IOException("invalide filter type in scanline: " + curLine[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   private void unfilterSub(byte[] curLine) {
/* 574 */     int bpp = this.bytesPerPixel;
/* 575 */     for (int i = bpp + 1, n = curLine.length; i < n; i++) {
/* 576 */       curLine[i] = (byte)(curLine[i] + curLine[i - bpp]);
/*     */     }
/*     */   }
/*     */   
/*     */   private void unfilterUp(byte[] curLine, byte[] prevLine) {
/* 581 */     int bpp = this.bytesPerPixel;
/* 582 */     for (int i = 1, n = curLine.length; i < n; i++) {
/* 583 */       curLine[i] = (byte)(curLine[i] + prevLine[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   private void unfilterAverage(byte[] curLine, byte[] prevLine) {
/* 588 */     int bpp = this.bytesPerPixel;
/*     */     
/*     */     int i;
/* 591 */     for (i = 1; i <= bpp; i++) {
/* 592 */       curLine[i] = (byte)(curLine[i] + (byte)((prevLine[i] & 0xFF) >>> 1));
/*     */     }
/* 594 */     for (int n = curLine.length; i < n; i++) {
/* 595 */       curLine[i] = (byte)(curLine[i] + (byte)((prevLine[i] & 0xFF) + (curLine[i - bpp] & 0xFF) >>> 1));
/*     */     }
/*     */   }
/*     */   
/*     */   private void unfilterPaeth(byte[] curLine, byte[] prevLine) {
/* 600 */     int bpp = this.bytesPerPixel;
/*     */     
/*     */     int i;
/* 603 */     for (i = 1; i <= bpp; i++) {
/* 604 */       curLine[i] = (byte)(curLine[i] + prevLine[i]);
/*     */     }
/* 606 */     for (int n = curLine.length; i < n; i++) {
/* 607 */       int a = curLine[i - bpp] & 0xFF;
/* 608 */       int b = prevLine[i] & 0xFF;
/* 609 */       int c = prevLine[i - bpp] & 0xFF;
/* 610 */       int p = a + b - c;
/* 611 */       int pa = p - a; if (pa < 0) pa = -pa; 
/* 612 */       int pb = p - b; if (pb < 0) pb = -pb; 
/* 613 */       int pc = p - c; if (pc < 0) pc = -pc; 
/* 614 */       if (pa <= pb && pa <= pc) {
/* 615 */         c = a;
/* 616 */       } else if (pb <= pc) {
/* 617 */         c = b;
/* 618 */       }  curLine[i] = (byte)(curLine[i] + (byte)c);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readIHDR() throws IOException {
/* 623 */     checkChunkLength(13);
/* 624 */     readChunk(this.buffer, 0, 13);
/* 625 */     this.width = readInt(this.buffer, 0);
/* 626 */     this.height = readInt(this.buffer, 4);
/* 627 */     this.bitdepth = this.buffer[8] & 0xFF;
/* 628 */     this.colorType = this.buffer[9] & 0xFF;
/*     */     
/* 630 */     switch (this.colorType) {
/*     */       case 0:
/* 632 */         if (this.bitdepth != 8) {
/* 633 */           throw new IOException("Unsupported bit depth: " + this.bitdepth);
/*     */         }
/* 635 */         this.bytesPerPixel = 1;
/*     */         break;
/*     */       case 4:
/* 638 */         if (this.bitdepth != 8) {
/* 639 */           throw new IOException("Unsupported bit depth: " + this.bitdepth);
/*     */         }
/* 641 */         this.bytesPerPixel = 2;
/*     */         break;
/*     */       case 2:
/* 644 */         if (this.bitdepth != 8) {
/* 645 */           throw new IOException("Unsupported bit depth: " + this.bitdepth);
/*     */         }
/* 647 */         this.bytesPerPixel = 3;
/*     */         break;
/*     */       case 6:
/* 650 */         if (this.bitdepth != 8) {
/* 651 */           throw new IOException("Unsupported bit depth: " + this.bitdepth);
/*     */         }
/* 653 */         this.bytesPerPixel = 4;
/*     */         break;
/*     */       case 3:
/* 656 */         switch (this.bitdepth) {
/*     */           case 1:
/*     */           case 2:
/*     */           case 4:
/*     */           case 8:
/* 661 */             this.bytesPerPixel = 1;
/*     */             break;
/*     */         } 
/* 664 */         throw new IOException("Unsupported bit depth: " + this.bitdepth);
/*     */ 
/*     */       
/*     */       default:
/* 668 */         throw new IOException("unsupported color format: " + this.colorType);
/*     */     } 
/*     */     
/* 671 */     if (this.buffer[10] != 0) {
/* 672 */       throw new IOException("unsupported compression method");
/*     */     }
/* 674 */     if (this.buffer[11] != 0) {
/* 675 */       throw new IOException("unsupported filtering method");
/*     */     }
/* 677 */     if (this.buffer[12] != 0) {
/* 678 */       throw new IOException("unsupported interlace method");
/*     */     }
/*     */   }
/*     */   
/*     */   private void readPLTE() throws IOException {
/* 683 */     int paletteEntries = this.chunkLength / 3;
/* 684 */     if (paletteEntries < 1 || paletteEntries > 256 || this.chunkLength % 3 != 0) {
/* 685 */       throw new IOException("PLTE chunk has wrong length");
/*     */     }
/* 687 */     this.palette = new byte[paletteEntries * 3];
/* 688 */     readChunk(this.palette, 0, this.palette.length);
/*     */   }
/*     */   
/*     */   private void readtRNS() throws IOException {
/* 692 */     switch (this.colorType) {
/*     */       case 0:
/* 694 */         checkChunkLength(2);
/* 695 */         this.transPixel = new byte[2];
/* 696 */         readChunk(this.transPixel, 0, 2);
/*     */         break;
/*     */       case 2:
/* 699 */         checkChunkLength(6);
/* 700 */         this.transPixel = new byte[6];
/* 701 */         readChunk(this.transPixel, 0, 6);
/*     */         break;
/*     */       case 3:
/* 704 */         if (this.palette == null) {
/* 705 */           throw new IOException("tRNS chunk without PLTE chunk");
/*     */         }
/* 707 */         this.paletteA = new byte[this.palette.length / 3];
/* 708 */         Arrays.fill(this.paletteA, (byte)-1);
/* 709 */         readChunk(this.paletteA, 0, this.paletteA.length);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void closeChunk() throws IOException {
/* 717 */     if (this.chunkRemaining > 0) {
/*     */       
/* 719 */       skip((this.chunkRemaining + 4));
/*     */     } else {
/* 721 */       readFully(this.buffer, 0, 4);
/* 722 */       int expectedCrc = readInt(this.buffer, 0);
/* 723 */       int computedCrc = (int)this.crc.getValue();
/* 724 */       if (computedCrc != expectedCrc) {
/* 725 */         throw new IOException("Invalid CRC");
/*     */       }
/*     */     } 
/* 728 */     this.chunkRemaining = 0;
/* 729 */     this.chunkLength = 0;
/* 730 */     this.chunkType = 0;
/*     */   }
/*     */   
/*     */   private void openChunk() throws IOException {
/* 734 */     readFully(this.buffer, 0, 8);
/* 735 */     this.chunkLength = readInt(this.buffer, 0);
/* 736 */     this.chunkType = readInt(this.buffer, 4);
/* 737 */     this.chunkRemaining = this.chunkLength;
/* 738 */     this.crc.reset();
/* 739 */     this.crc.update(this.buffer, 4, 4);
/*     */   }
/*     */   
/*     */   private void openChunk(int expected) throws IOException {
/* 743 */     openChunk();
/* 744 */     if (this.chunkType != expected) {
/* 745 */       throw new IOException("Expected chunk: " + Integer.toHexString(expected));
/*     */     }
/*     */   }
/*     */   
/*     */   private void checkChunkLength(int expected) throws IOException {
/* 750 */     if (this.chunkLength != expected) {
/* 751 */       throw new IOException("Chunk has wrong size");
/*     */     }
/*     */   }
/*     */   
/*     */   private int readChunk(byte[] buffer, int offset, int length) throws IOException {
/* 756 */     if (length > this.chunkRemaining) {
/* 757 */       length = this.chunkRemaining;
/*     */     }
/* 759 */     readFully(buffer, offset, length);
/* 760 */     this.crc.update(buffer, offset, length);
/* 761 */     this.chunkRemaining -= length;
/* 762 */     return length;
/*     */   }
/*     */   
/*     */   private void refillInflater(Inflater inflater) throws IOException {
/* 766 */     while (this.chunkRemaining == 0) {
/* 767 */       closeChunk();
/* 768 */       openChunk(1229209940);
/*     */     } 
/* 770 */     int read = readChunk(this.buffer, 0, this.buffer.length);
/* 771 */     inflater.setInput(this.buffer, 0, read);
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
/*     */   private void readChunkUnzip(Inflater inflater, byte[] buffer, int offset, int length) throws IOException {
/*     */     // Byte code:
/*     */     //   0: getstatic de/matthiasmann/twl/utils/PNGDecoder.$assertionsDisabled : Z
/*     */     //   3: ifne -> 22
/*     */     //   6: aload_2
/*     */     //   7: aload_0
/*     */     //   8: getfield buffer : [B
/*     */     //   11: if_acmpne -> 22
/*     */     //   14: new java/lang/AssertionError
/*     */     //   17: dup
/*     */     //   18: invokespecial <init> : ()V
/*     */     //   21: athrow
/*     */     //   22: aload_1
/*     */     //   23: aload_2
/*     */     //   24: iload_3
/*     */     //   25: iload #4
/*     */     //   27: invokevirtual inflate : ([BII)I
/*     */     //   30: istore #5
/*     */     //   32: iload #5
/*     */     //   34: ifgt -> 102
/*     */     //   37: aload_1
/*     */     //   38: invokevirtual finished : ()Z
/*     */     //   41: ifeq -> 52
/*     */     //   44: new java/io/EOFException
/*     */     //   47: dup
/*     */     //   48: invokespecial <init> : ()V
/*     */     //   51: athrow
/*     */     //   52: aload_1
/*     */     //   53: invokevirtual needsInput : ()Z
/*     */     //   56: ifeq -> 67
/*     */     //   59: aload_0
/*     */     //   60: aload_1
/*     */     //   61: invokespecial refillInflater : (Ljava/util/zip/Inflater;)V
/*     */     //   64: goto -> 114
/*     */     //   67: new java/io/IOException
/*     */     //   70: dup
/*     */     //   71: new java/lang/StringBuilder
/*     */     //   74: dup
/*     */     //   75: invokespecial <init> : ()V
/*     */     //   78: ldc_w 'Can't inflate '
/*     */     //   81: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   84: iload #4
/*     */     //   86: invokevirtual append : (I)Ljava/lang/StringBuilder;
/*     */     //   89: ldc_w ' bytes'
/*     */     //   92: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   95: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   98: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   101: athrow
/*     */     //   102: iload_3
/*     */     //   103: iload #5
/*     */     //   105: iadd
/*     */     //   106: istore_3
/*     */     //   107: iload #4
/*     */     //   109: iload #5
/*     */     //   111: isub
/*     */     //   112: istore #4
/*     */     //   114: iload #4
/*     */     //   116: ifgt -> 22
/*     */     //   119: goto -> 146
/*     */     //   122: astore #5
/*     */     //   124: new java/io/IOException
/*     */     //   127: dup
/*     */     //   128: ldc_w 'inflate error'
/*     */     //   131: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   134: aload #5
/*     */     //   136: invokevirtual initCause : (Ljava/lang/Throwable;)Ljava/lang/Throwable;
/*     */     //   139: checkcast java/io/IOException
/*     */     //   142: checkcast java/io/IOException
/*     */     //   145: athrow
/*     */     //   146: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #775	-> 0
/*     */     //   #778	-> 22
/*     */     //   #779	-> 32
/*     */     //   #780	-> 37
/*     */     //   #781	-> 44
/*     */     //   #783	-> 52
/*     */     //   #784	-> 59
/*     */     //   #786	-> 67
/*     */     //   #789	-> 102
/*     */     //   #790	-> 107
/*     */     //   #792	-> 114
/*     */     //   #795	-> 119
/*     */     //   #793	-> 122
/*     */     //   #794	-> 124
/*     */     //   #796	-> 146
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   32	82	5	read	I
/*     */     //   124	22	5	ex	Ljava/util/zip/DataFormatException;
/*     */     //   0	147	0	this	Lde/matthiasmann/twl/utils/PNGDecoder;
/*     */     //   0	147	1	inflater	Ljava/util/zip/Inflater;
/*     */     //   0	147	2	buffer	[B
/*     */     //   0	147	3	offset	I
/*     */     //   0	147	4	length	I
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   22	119	122	java/util/zip/DataFormatException
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
/*     */   private void readFully(byte[] buffer, int offset, int length) throws IOException {
/*     */     do {
/* 800 */       int read = this.input.read(buffer, offset, length);
/* 801 */       if (read < 0) {
/* 802 */         throw new EOFException();
/*     */       }
/* 804 */       offset += read;
/* 805 */       length -= read;
/* 806 */     } while (length > 0);
/*     */   }
/*     */   
/*     */   private int readInt(byte[] buffer, int offset) {
/* 810 */     return buffer[offset] << 24 | (buffer[offset + 1] & 0xFF) << 16 | (buffer[offset + 2] & 0xFF) << 8 | buffer[offset + 3] & 0xFF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void skip(long amount) throws IOException {
/* 818 */     while (amount > 0L) {
/* 819 */       long skipped = this.input.skip(amount);
/* 820 */       if (skipped < 0L) {
/* 821 */         throw new EOFException();
/*     */       }
/* 823 */       amount -= skipped;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean checkSignature(byte[] buffer) {
/* 828 */     for (int i = 0; i < SIGNATURE.length; i++) {
/* 829 */       if (buffer[i] != SIGNATURE[i]) {
/* 830 */         return false;
/*     */       }
/*     */     } 
/* 833 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\tw\\utils\PNGDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */