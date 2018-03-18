 package com.inspectbox.model;
 
 import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
 
 @Entity
 @Table(name="niveauobjet",  catalog="inspectboxsa")
 public class Niveauobjet
   implements java.io.Serializable
 {
   private Integer idNiveauObjet;
   private Typereponse typereponse;
   private Client client;
   private Niveauobjet niveauobjet;
   private int tri;
   private String libelle;
   private String codeBarre;
   private boolean masque;
   private boolean existe;
  
private Date clefTimestamp;

private String unitmeasure;
private Double lowlimit;
private Double highlimit;
private Double highborder;
private Double lowborder;
private String highborderAlert;
private String lowborderAlert;
   private Set<Niveauobjet> niveauobjets = new HashSet(0);
  private Set<Inspection> inspections = new HashSet(0);
   private Set<Niveau> niveaus = new HashSet(0);
 	
			private Integer parentId;

   public Niveauobjet()
   {
   }
 
   public Niveauobjet(Client client, int tri, String libelle, String codeBarre,  boolean masque, boolean existe, Date clefTimestamp)
   {
     this.client = client;
     this.tri = tri;
     this.libelle = libelle;
     this.codeBarre = codeBarre;
      this.masque = masque;
this.existe = existe;
     this.clefTimestamp = clefTimestamp;
 }
 
   public Niveauobjet(Typereponse typereponse, Client client, Niveauobjet niveauobjet, int tri, String libelle, String codeBarre, boolean masque,boolean existe, Date clefTimestamp, Set<Niveauobjet> niveauobjets, Set<Inspection> inspections, Set<Niveau> niveaus, String nomFichier, byte[] donnees)
   {
/*  64 */     this.typereponse = typereponse;
/*  65 */     this.client = client;
/*  66 */     this.niveauobjet = niveauobjet;
/*  67 */     this.tri = tri;
/*  68 */     this.libelle = libelle;
/*  69 */     this.codeBarre = codeBarre;
     this.masque = masque;
			  this.existe = existe;
/*  73 */     this.clefTimestamp = clefTimestamp;
/*  74 */     this.niveauobjets = niveauobjets;
/*  75 */     this.inspections = inspections;
/*  76 */     this.niveaus = niveaus;

   }
 
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="IdNiveauObjet", unique=true, nullable=false)
   public Integer getIdNiveauObjet() {
/*  83 */     return this.idNiveauObjet;
   }
 
   public void setIdNiveauObjet(Integer idNiveauObjet) {
/*  87 */     this.idNiveauObjet = idNiveauObjet;
   }
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="IdTypeReponse")
   public Typereponse getTypereponse() {
     return this.typereponse;
   }
 
   public void setTypereponse(Typereponse typereponse) {
     this.typereponse = typereponse;
   }
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="IdClient", nullable=false)
   public Client getClient() {
     return this.client;
   }
 
   public void setClient(Client client) {
     this.client = client;
   }
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="IdNiveauObjetParent")
   public Niveauobjet getNiveauobjet() {
     return this.niveauobjet;
   }
 
   public void setNiveauobjet(Niveauobjet niveauobjet) {
     this.niveauobjet = niveauobjet;
   }

			@Column(name="IdNiveauObjetParent", updatable=false, insertable=false)
			public Integer getParentId() {
				return parentId;
			}
			public void setParentId(Integer parentId) {
				this.parentId = parentId;
			} 
 
   @Column(name="Tri", nullable=false)
   public int getTri() {
     return this.tri;
   }
 
   public void setTri(int tri) {
     this.tri = tri;
   }
 


   @Column(name="Libelle", nullable=false)
   public String getLibelle() {
     return this.libelle;
   }
 
   public void setLibelle(String libelle) {
     this.libelle = libelle;
   }
 
   @Column(name="CodeBarre", nullable=false)
   public String getCodeBarre() {
     return this.codeBarre;
   }
 
   public void setCodeBarre(String codeBarre) {
	   if (codeBarre!=null)
     this.codeBarre = codeBarre;
	   else
		   this.codeBarre=null;
   }
 
  
   @Column(name="Masque", nullable=false)
   public boolean isMasque() {
     return this.masque;
   }
 
   public void setMasque(boolean masque) {
     this.masque = masque;
   }
   @Column(name="existe", nullable=false)
   public boolean isExiste() {
		return existe;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
	}

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="ClefTimestamp", nullable=false, length=19)
   public Date getClefTimestamp() {
     return this.clefTimestamp;
   }
 
   public void setClefTimestamp(Date clefTimestamp) {
     this.clefTimestamp = clefTimestamp;
   }
   @Column(name="unitmeasure", nullable=true)
   public String getUnitmeasure() {
	   
	return unitmeasure;
}

public void setUnitmeasure(String unitmeasure) {
	if (unitmeasure!=null)
	this.unitmeasure = unitmeasure;
	else
		this.unitmeasure=null;
}
@Column(name="lowlimit", nullable=true)
public Double getLowlimit() {
	return lowlimit;
}

public void setLowlimit(Double lowlimit) {
	if (lowlimit!=null)
	this.lowlimit = lowlimit;
	else
		lowlimit=null;
}
@Column(name="highlimit", nullable=false)
public Double getHighlimit() {
	return highlimit;
}

public void setHighlimit(Double highlimit) {
	if(highlimit!=null)
	this.highlimit = highlimit;
	else
		this.highlimit=null;
}
@Column(name="highborder")
public Double getHighborder() {
	return highborder;
}

public void setHighborder(Double highborder) {
	if (highborder!=null)
	this.highborder = highborder;
	else
		this.highborder=null;
}
@Column(name="lowborder", nullable=true)
public Double getLowborder() {
	return lowborder;
}

public void setLowborder(Double lowborder) {
	if(lowborder!=null)
	this.lowborder = lowborder;
	else
		this.lowborder=null;
}
@Column(name="highborderAlert", nullable=true)
public String getHighborderAlert() {
	return highborderAlert;
}

public void setHighborderAlert(String highborderAlert) {
	this.highborderAlert = highborderAlert;
}
@Column(name="lowborderAlert", nullable=true)
public String getLowborderAlert() {
	return lowborderAlert;
}

public void setLowborderAlert(String lowborderAlert) {
	this.lowborderAlert = lowborderAlert;
}

@OneToMany(fetch=FetchType.LAZY, mappedBy="niveauobjet")
   public Set<Niveauobjet> getNiveauobjets() {
    return this.niveauobjets;
   }
 
   public void setNiveauobjets(Set<Niveauobjet> niveauobjets) {
     this.niveauobjets = niveauobjets;
   }
 
   @OneToMany(fetch=FetchType.LAZY, mappedBy="niveauobjet")
   public Set<Inspection> getInspections() {
     return this.inspections;
   }
 
   public void setInspections(Set<Inspection> inspections) {
     this.inspections = inspections;
   }
 
   @OneToMany(fetch=FetchType.LAZY, mappedBy="niveauobjet")
   public Set<Niveau> getNiveaus() {
     return this.niveaus;
   }
 
   public void setNiveaus(Set<Niveau> niveaus) {
     this.niveaus = niveaus;
   }
 }

