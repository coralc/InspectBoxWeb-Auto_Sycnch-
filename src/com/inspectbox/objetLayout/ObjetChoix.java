 package com.inspectbox.objetLayout;
 
 import java.io.Serializable;
 
 public class ObjetChoix
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private Integer idChoix;
   private String libelle;
   private Boolean nonConforme;
   private Integer tri;
 private Integer idTyp;
   public Integer getIdChoix()
   {
/* 31 */     return this.idChoix;
   }
 
   public void setIdChoix(Integer idChoix)
   {
/* 39 */     this.idChoix = idChoix;
   }
 
   public String getLibelle()
   {
/* 47 */     return this.libelle;
   }
 
   public void setLibelle(String libelle)
   {
/* 55 */     this.libelle = libelle;
   }
 
   public Boolean getNonConforme()
   {
/* 63 */     return this.nonConforme;
   }
 
   public void setNonConforme(Boolean nonConforme)
   {
/* 71 */     this.nonConforme = nonConforme;
   }
 
   public Integer getTri()
   {
/* 79 */     return this.tri;
   }
 
   public void setTri(Integer tri)
   {
/* 87 */     this.tri = tri;
   }

public Integer getIdTyp() {
	return idTyp;
}

public void setIdTyp(Integer idTyp) {
	this.idTyp = idTyp;
}
 }

