/*    */ package com.inspectbox.objetLayout;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class Document
/*    */   implements Serializable
/*    */ {
/*    */   private String name;
/*    */   private String size;
/*    */   private String type;
/*  9 */   private Boolean select = Boolean.valueOf(false);
/*    */ 
/*    */   public Document()
/*    */   {
/*    */   }
/*    */ 
/*    */   public Document(String name, String size, String type)
/*    */   {
/* 18 */     this.name = name;
/* 19 */     this.size = size;
/* 20 */     this.type = type;
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 25 */     return this.name; }
/*    */ 
/*    */   public void setName(String name) {
/* 28 */     this.name = name; }
/*    */ 
/*    */   public String getSize() {
/* 31 */     return this.size; }
/*    */ 
/*    */   public void setSize(String size) {
/* 34 */     this.size = size; }
/*    */ 
/*    */   public String getType() {
/* 37 */     return this.type; }
/*    */ 
/*    */   public void setType(String type) {
/* 40 */     this.type = type;
/*    */   }
/*    */ 
/*    */   public Boolean getSelect() {
/* 44 */     return this.select;
/*    */   }
/*    */ 
/*    */   public void setSelect(Boolean select) {
/* 48 */     this.select = select;
/*    */   }
/*    */ }

/* Location:           C:\Users\Sébastien MARTIN\workspace\insp\inspectbox\WebRoot\WEB-INF\classes\
 * Qualified Name:     com.inspectbox.objetLayout.Document
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.5.3
 */