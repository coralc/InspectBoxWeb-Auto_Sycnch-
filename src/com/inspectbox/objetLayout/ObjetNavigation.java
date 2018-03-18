/*    */ package com.inspectbox.objetLayout;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ObjetNavigation
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String libelle;
/*    */   private Integer num;
/* 11 */   private Boolean premier = Boolean.valueOf(false);
/* 12 */   private Boolean dernier = Boolean.valueOf(false);
/*    */   private Integer tri;

			private String modeReponse;

			public String getModeReponse() {
				return modeReponse;
			}
			public void setModeReponse(String modeReponse) {
				this.modeReponse = modeReponse;
			}
/*    */   public ObjetNavigation()
/*    */   {
/*    */   }
/*    */ 
/*    */   public ObjetNavigation(String libelle, Integer num)
/*    */   {
/* 22 */     this.libelle = libelle;
/* 23 */     this.num = num;
/*    */   }
/*    */ 
/*    */   public String getLibelle()
/*    */   {
/* 28 */     return this.libelle;
/*    */   }
/*    */ 
/*    */   public void setLibelle(String libelle) {
/* 32 */     this.libelle = libelle;
/*    */   }
/*    */ 
/*    */   public Integer getNum() {
/* 36 */     return this.num; }
/*    */ 
/*    */   public void setNum(Integer num) {
/* 39 */     this.num = num;
/*    */   }
/*    */ 
/*    */   public Boolean getPremier() {
/* 43 */     return this.premier;
/*    */   }
/*    */ 
/*    */   public void setPremier(Boolean premier) {
/* 47 */     this.premier = premier;
/*    */   }
/*    */ 
/*    */   public Boolean getDernier() {
/* 51 */     return this.dernier;
/*    */   }
/*    */ 
/*    */   public void setDernier(Boolean dernier) {
/* 55 */     this.dernier = dernier;
/*    */   }
/*    */ 
/*    */   public Integer getTri() {
/* 59 */     return this.tri;
/*    */   }
/*    */ 
/*    */   public void setTri(Integer tri) {
/* 63 */     this.tri = tri;
/*    */   }
/*    */ }

/* Location:           C:\Users\Sébastien MARTIN\workspace\insp\inspectbox\WebRoot\WEB-INF\classes\
 * Qualified Name:     com.inspectbox.objetLayout.ObjetNavigation
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.5.3
 */