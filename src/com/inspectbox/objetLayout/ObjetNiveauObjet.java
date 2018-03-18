 package com.inspectbox.objetLayout;
 
 import java.io.Serializable;
 
 public class ObjetNiveauObjet
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private Integer idNiveauObjet;
   private String libelle;
   private String unitmeasure;
   private double reponseminvalue;
   private double reponsemaxvalue;
   private double highborder;
   private double lowborder;
   private String highborderAlert;
   private String lowborderAlert;
   private String libelleNiveau;
   private Boolean exist;
   private Boolean premier = Boolean.valueOf(false);
   private Boolean dernier = Boolean.valueOf(false);
   private Integer tri;
   private Boolean islist = Boolean.valueOf(false);
   public Boolean getIslist() {
	return islist;
}

public void setIslist(Boolean islist) {
	this.islist = islist;
}

public Integer getIdNiveauObjet()
   {
/*  32 */     return this.idNiveauObjet;
   }
 
   public void setIdNiveauObjet(Integer idNiveauObjet)
   {
/*  40 */     this.idNiveauObjet = idNiveauObjet;
   }
 
   public String getLibelle()
   {
/*  48 */     return this.libelle;
   }
 
   public void setLibelle(String libelle)
   {
/*  56 */     this.libelle = libelle;
   }
 
   public Boolean getPremier()
   {
/*  64 */     return this.premier;
   }
 
   public void setPremier(Boolean premier)
   {
/*  72 */     this.premier = premier;
   }
 
   public Boolean getDernier()
   {
/*  80 */     return this.dernier;
   }
 
   public void setDernier(Boolean dernier)
   {
/*  88 */     this.dernier = dernier;
   }
 
   public Integer getTri()
   {
/*  96 */     return this.tri;
   }
 
   public void setTri(Integer tri)
   {
/* 104 */     this.tri = tri;
   }

			public String getUnitmeasure() {
	return unitmeasure;
}

public void setUnitmeasure(String unitmeasure) {
	this.unitmeasure = unitmeasure;
}

public double getReponseminvalue() {
	return reponseminvalue;
}

public void setReponseminvalue(double reponseminvalue) {
	this.reponseminvalue = reponseminvalue;
}

public double getReponsemaxvalue() {
	return reponsemaxvalue;
}

public void setReponsemaxvalue(double reponsemaxvalue) {
	this.reponsemaxvalue = reponsemaxvalue;
}

public double getHighborder() {
	return highborder;
}

public void setHighborder(double highborder) {
	this.highborder = highborder;
}

public double getLowborder() {
	return lowborder;
}

public void setLowborder(double lowborder) {
	this.lowborder = lowborder;
}

public String getHighborderAlert() {
	return highborderAlert;
}

public void setHighborderAlert(String highborderAlert) {
	this.highborderAlert = highborderAlert;
}

public String getLowborderAlert() {
	return lowborderAlert;
}

public void setLowborderAlert(String lowborderAlert) {
	this.lowborderAlert = lowborderAlert;
}

public String getLibelleNiveau() {
	return libelleNiveau;
}

public void setLibelleNiveau(String libelleNiveau) {
	this.libelleNiveau = libelleNiveau;
}

			public Boolean getExist() {
	return exist;
}

public void setExist(Boolean exist) {
	this.exist = exist;
}

			@Override
			public String toString() {
				return this.libelle;
			}	


 }

