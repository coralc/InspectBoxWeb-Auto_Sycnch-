 package com.inspectbox.model;
 
 import java.io.Serializable;
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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
 
 @Entity
 @Table(name="niveau",  catalog="inspectboxsa")
 public class Niveau
   implements Serializable
 {
   private Integer idNiveau;
   private Niveauobjet niveauobjet;
   private Niveau niveau;
   private Typereponse typereponse;
   private Client client;
   private int tri;
   private String libelle;
   private String areaTag;
   private String codeBarre;
   private boolean masque;
   private Date clefTimestamp;
   private int level;
   private Set<Inspection> inspections = new HashSet(0);
   private Set<Niveau> niveaus = new HashSet(0);
   private Set<Niveau> niveaus_1 = new HashSet(0);

            private Integer parentId;
 
   public Niveau()
   {
   }
 
   public Niveau(Client client, int tri, String libelle, String codeBarre, boolean masque, Date clefTimestamp) {
     this.client = client;
     this.tri = tri;
    this.libelle = libelle;
     this.codeBarre = codeBarre;
    this.masque = masque;
     this.clefTimestamp = clefTimestamp;
   }
 
   public Niveau(Niveauobjet niveauobjet, Niveau niveau, Typereponse typereponse, Client client, int level,int tri, String libelle, String codeBarre, boolean masque, Date clefTimestamp, Set<Inspection> inspections, Set<Niveau> niveaus, Set<Niveau> niveaus_1)
   {
/*  60 */     this.niveauobjet = niveauobjet;
/*  61 */     this.niveau = niveau;
/*  62 */     this.typereponse = typereponse;
/*  63 */     this.client = client;
/*  64 */     this.tri = tri;
			  this.level=level;
/*  65 */     this.libelle = libelle;
/*  66 */     this.codeBarre = codeBarre;
/*  67 */     this.masque = masque;
/*  68 */     this.clefTimestamp = clefTimestamp;
/*  69 */     this.inspections = inspections;
/*  70 */     this.niveaus = niveaus;
/*  71 */     this.niveaus_1 = niveaus_1;
   }
 
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="IdNiveau", unique=true, nullable=false)
   public Integer getIdNiveau() {
/*  78 */     return this.idNiveau;
   }
 
   public void setIdNiveau(Integer idNiveau) {
/*  82 */     this.idNiveau = idNiveau;
   }
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="IdNiveauObjet")
   public Niveauobjet getNiveauobjet() {
/*  88 */     return this.niveauobjet;
   }
 
   public void setNiveauobjet(Niveauobjet niveauobjet) {
/*  92 */     this.niveauobjet = niveauobjet;
   }
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="IdNiveauParent")
			@NotFound( action = NotFoundAction.IGNORE )
   public Niveau getNiveau() {
/*  98 */     return this.niveau;
   }
 
   public void setNiveau(Niveau niveau) {
/* 102 */     this.niveau = niveau;
   }


		@Column(name="IdNiveauParent", updatable=false, insertable=false)
		public Integer getParentId() {
			return parentId;
		}
		public void setParentId(Integer parentId) {
			this.parentId = parentId;
		} 

 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="IdTypeReponse")
   public Typereponse getTypereponse() {
/* 108 */     return this.typereponse;
   }
 
   public void setTypereponse(Typereponse typereponse) {
/* 112 */     this.typereponse = typereponse;
   }
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="IdClient", nullable=false)
   public Client getClient() {
/* 118 */     return this.client;
   }
 
   public void setClient(Client client) {
/* 122 */     this.client = client;
   }
 
   @Column(name="Tri", nullable=false)
   public int getTri() {
/* 127 */     return this.tri;
   }
 
   public void setTri(int tri) {
/* 131 */     this.tri = tri;
   }
 
   @Column(name="Libelle", nullable=false)
   public String getLibelle() {
/* 136 */     return this.libelle;
   }
 
   public void setLibelle(String libelle) {
/* 140 */     this.libelle = libelle;
   }
 
   @Column(name="CodeBarre")
   public String getCodeBarre() {
/* 145 */     return this.codeBarre;
   }
 
   public void setCodeBarre(String codeBarre) {
/* 149 */     this.codeBarre = codeBarre;
   }
@Column(name="AreaTag")
public String getAreaTag() {
	return areaTag;
}
public void setAreaTag(String areaTag) {
	this.areaTag = areaTag;
}
 
   @Column(name="Masque", nullable=false)
   public boolean isMasque() {
/* 154 */     return this.masque;
   }
 
   public void setMasque(boolean masque) {
/* 158 */     this.masque = masque;
   }
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="ClefTimestamp", nullable=false, length=19)
   public Date getClefTimestamp() {
/* 164 */     return this.clefTimestamp;
   }
 
   public void setClefTimestamp(Date clefTimestamp) {
/* 168 */     this.clefTimestamp = clefTimestamp;
   }
 
   @Column(name="Level", nullable=false)
   public int getLevel() {
	return level;
}

public void setLevel(int level) {
	this.level = level;
}

@OneToMany(fetch=FetchType.LAZY, mappedBy="niveau")
   public Set<Inspection> getInspections() {
/* 173 */     return this.inspections;
   }
 
   public void setInspections(Set<Inspection> inspections) {
/* 177 */     this.inspections = inspections;
   }
 
   @OneToMany(fetch=FetchType.LAZY, mappedBy="niveau")
   public Set<Niveau> getNiveaus() {
/* 182 */     return this.niveaus;
   }
 
   public void setNiveaus(Set<Niveau> niveaus) {
/* 186 */     this.niveaus = niveaus;
   }
 
   @OneToMany(fetch=FetchType.LAZY, mappedBy="niveau")
   public Set<Niveau> getNiveaus_1() {
/* 191 */     return this.niveaus_1;
   }
 
   public void setNiveaus_1(Set<Niveau> niveaus_1) {
/* 195 */     this.niveaus_1 = niveaus_1;
   }
 }

