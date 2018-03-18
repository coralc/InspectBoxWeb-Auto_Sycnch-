 package com.inspectbox.objetLayout;
 
 import java.io.Serializable;
 
 public class ObjetNiveau
 implements java.io.Serializable
 {
   private static final long serialVersionUID = 1L;
   private Integer idNiveau;
   private Integer idNiveauParent;
private Integer idNiveauObjet;
private Integer idtyprep;
private Integer level;
public Integer getLevel() {
	return level;
}
public void setLevel(Integer level) {
	this.level = level;
}
public Integer getIdNiveauObjet() {
	return idNiveauObjet;
}
public void setIdNiveauObjet(Integer idNiveauObjet) {
	this.idNiveauObjet = idNiveauObjet;
}
private String nomFichier;
private byte[] donnees;
public String getNomFichier() {
	return nomFichier;
}
public void setNomFichier(String nomFichier) {
	this.nomFichier = nomFichier;
}
public byte[] getDonnees() {
	return donnees;
}
public void setDonnees(byte[] donnees) {
	this.donnees = donnees;
}
   private String libelle;
   private String libelleobj;
   public String getLibelleobj() {
	return libelleobj;
}
public void setLibelleobj(String libelleobj) {
	this.libelleobj = libelleobj;
}
private String codeBarre;
/*  16 */   private Boolean premier = Boolean.valueOf(false);
/*  17 */   private Boolean dernier = Boolean.valueOf(false);
   private Integer tri;
   private Integer position;
   private String ariane;
   private String space;
   private Integer spacer;
 
   public Integer getIdNiveau()
   {
/*  37 */     return this.idNiveau;
   }
 
   public void setIdNiveau(Integer idNiveau)
   {
/*  43 */     this.idNiveau = idNiveau;
   }
 
   public String getLibelle()
   {
/*  54 */     return this.libelle;
   }
 
   public void setLibelle(String libelle)
   {
/*  62 */     this.libelle = libelle;
   }
 
   public Boolean getPremier()
   {
/*  70 */     return this.premier;
   }
 
   public void setPremier(Boolean premier)
   {
/*  78 */     this.premier = premier;
   }
 
   public Boolean getDernier()
   {
/*  86 */     return this.dernier;
   }
 
   public void setDernier(Boolean dernier)
   {
/*  94 */     this.dernier = dernier;
   }
 
   public Integer getIdtyprep() {
	return idtyprep;
}
public void setIdtyprep(Integer idtyprep) {
	this.idtyprep = idtyprep;
}
public Integer getTri()
   {
/* 102 */     return this.tri;
   }
 
   public void setTri(Integer tri)
   {
/* 110 */     this.tri = tri;
   }
 
   public String getCodeBarre()
   {
/* 117 */     return this.codeBarre;
   }
 
   public void setCodeBarre(String codeBarre)
   {
/* 124 */     this.codeBarre = codeBarre;
   }
 
   public Integer getPosition()
   {
/* 131 */     return this.position;
   }
 
   public void setPosition(Integer position)
   {
/* 138 */     this.position = position;
   }
 
   public String getAriane()
   {
/* 145 */     return this.ariane;
   }
 
   public void setAriane(String ariane)
   {
/* 152 */     this.ariane = ariane;
   }
 
   public Integer getIdNiveauParent()
   {
/* 159 */     return this.idNiveauParent;
   }
 
   public void setIdNiveauParent(Integer idNiveauParent)
   {
/* 166 */     this.idNiveauParent = idNiveauParent;
   }
 
   public String getSpace()
   {
/* 173 */     this.space = "";
/* 174 */     for (int i = 0; i < this.position.intValue(); ++i) {
/* 175 */       this.space += "&#160;&#160;&#160;";
     }
 
/* 179 */     return this.space;
   }
 
   public void setSpace(String space)
   {
/* 187 */     this.space = space;
   }
 
   public Integer getSpacer()
   {
/* 194 */     this.spacer = Integer.valueOf(this.position.intValue() * 15);
/* 195 */     return this.spacer;
   }
 
   public void setSpacer(Integer spacer)
   {
/* 202 */     this.spacer = spacer;
   }

			@Override
			public String toString() {
				return this.libelle;
			}	
 }

