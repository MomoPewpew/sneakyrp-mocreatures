/*     */ package de.matthiasmann.twl.model;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
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
/*     */ public class SimpleTableModel
/*     */   extends AbstractTableModel
/*     */ {
/*     */   private final String[] columnHeaders;
/*     */   private final ArrayList<Object[]> rows;
/*     */   
/*     */   public SimpleTableModel(String[] columnHeaders) {
/*  46 */     if (columnHeaders.length < 1) {
/*  47 */       throw new IllegalArgumentException("must have atleast one column");
/*     */     }
/*  49 */     this.columnHeaders = (String[])columnHeaders.clone();
/*  50 */     this.rows = new ArrayList();
/*     */   }
/*     */   
/*     */   public int getNumColumns() {
/*  54 */     return this.columnHeaders.length;
/*     */   }
/*     */   
/*     */   public String getColumnHeaderText(int column) {
/*  58 */     return this.columnHeaders[column];
/*     */   }
/*     */   
/*     */   public void setColumnHeaderText(int column, String text) {
/*  62 */     if (text == null) {
/*  63 */       throw new NullPointerException("text");
/*     */     }
/*  65 */     this.columnHeaders[column] = text;
/*  66 */     fireColumnHeaderChanged(column);
/*     */   }
/*     */   
/*     */   public int getNumRows() {
/*  70 */     return this.rows.size();
/*     */   }
/*     */   
/*     */   public Object getCell(int row, int column) {
/*  74 */     return ((Object[])this.rows.get(row))[column];
/*     */   }
/*     */   
/*     */   public void setCell(int row, int column, Object data) {
/*  78 */     ((Object[])this.rows.get(row))[column] = data;
/*  79 */     fireCellChanged(row, column);
/*     */   }
/*     */   
/*     */   public void addRow(Object... data) {
/*  83 */     insertRow(this.rows.size(), data);
/*     */   }
/*     */   
/*     */   public void addRows(Collection<Object[]> rows) {
/*  87 */     insertRows(this.rows.size(), rows);
/*     */   }
/*     */   
/*     */   public void insertRow(int index, Object... data) {
/*  91 */     this.rows.add(index, createRowData(data));
/*  92 */     fireRowsInserted(index, 1);
/*     */   }
/*     */   
/*     */   public void insertRows(int index, Collection<Object[]> rows) {
/*  96 */     if (!rows.isEmpty()) {
/*  97 */       ArrayList<Object[]> rowData = new ArrayList();
/*  98 */       for (Object[] row : rows) {
/*  99 */         rowData.add(createRowData(row));
/*     */       }
/* 101 */       this.rows.addAll(index, rowData);
/* 102 */       fireRowsInserted(index, rowData.size());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void deleteRow(int index) {
/* 107 */     this.rows.remove(index);
/* 108 */     fireRowsDeleted(index, 1);
/*     */   }
/*     */   
/*     */   public void deleteRows(int index, int count) {
/* 112 */     int numRows = this.rows.size();
/* 113 */     if (index < 0 || count < 0 || index >= numRows || count > numRows - index) {
/* 114 */       throw new IndexOutOfBoundsException("index=" + index + " count=" + count + " numRows=" + numRows);
/*     */     }
/* 116 */     if (count > 0) {
/*     */       
/* 118 */       for (int i = count; i-- > 0;) {
/* 119 */         this.rows.remove(index + i);
/*     */       }
/* 121 */       fireRowsDeleted(index, count);
/*     */     } 
/*     */   }
/*     */   
/*     */   private Object[] createRowData(Object[] data) {
/* 126 */     Object[] rowData = new Object[getNumColumns()];
/* 127 */     System.arraycopy(data, 0, rowData, 0, Math.min(rowData.length, data.length));
/* 128 */     return rowData;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\SimpleTableModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */