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
 
 @Entity
 @Table(name="typereponse",  catalog="inspectboxsa")
 public class Typereponse
   implements Serializable
 {
   private Integer idTypeReponse;
  private Integer mode;
   private Client client;
   private String libelle;
   private String modeReponse;
   private boolean masque;

   private Date clefTimestamp;
/*  34 */   private Set<Choix> choixes = new HashSet(0);
/*  35 */   private Set<Niveau> niveaus = new HashSet(0);
/*  36 */   private Set<Niveauobjet> niveauobjets = new HashSet(0);
 
   public Typereponse()
   {
   }
 
   public Typereponse(Client client, Integer mode,  String libelle, String modeReponse, boolean masque, Date clefTimestamp) {
/*  43 */     this.client = client;
/*  44 */     this.libelle = libelle;
/*  45 */     this.modeReponse = modeReponse;
/*  46 */     this.masque = masque;
				this.mode=mode;
/*  47 */     this.clefTimestamp = clefTimestamp;
   }
 
   public Typereponse(Client client, Integer mode, String libelle, String modeReponse, boolean masque,boolean existe, Date clefTimestamp, Set<Choix> choixes, Set<Niveau> niveaus, Set<Niveauobjet> niveauobjets)
   {
/*  53 */     this.client = client;
/*  54 */     this.libelle = libelle;
/*  55 */     this.modeReponse = modeReponse;
/*  56 */     this.masque = masque;
				this.mode=mode;
/*  57 */     this.clefTimestamp = clefTimestamp;
/*  58 */     this.choixes = choixes;
/*  59 */     this.niveaus = niveaus;
/*  60 */     this.niveauobjets = niveauobjets;
   }
 
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="IdTypeReponse", unique=true, nullable=false)
   public Integer getIdTypeReponse() {
/*  67 */     return this.idTypeReponse;
   }
 
   public void setIdTypeReponse(Integer idTypeReponse) {
/*  71 */     this.idTypeReponse = idTypeReponse;
   }
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="IdClient", nullable=false)
   public Client getClient() {
/*  77 */     return this.client;
   }
 
   public void setClient(Client client) {
/*  81 */     this.client = client;
   }
 
   @Column(name="Libelle", nullable=false)
   public String getLibelle() {
/*  86 */     return this.libelle;
   }
 
   public void setLibelle(String libelle) {
/*  90 */     this.libelle = libelle;
   }
 
   @Column(name="ModeReponse", nullable=false)
   public String getModeReponse() {
/*  95 */     return this.modeReponse;
   }
 
   public void setModeReponse(String modeReponse) {
/*  99 */     this.modeReponse = modeReponse;
   }
 
   @Column(name="Masque", nullable=false)
   public boolean isMasque() {
/* 104 */     return this.masque;
   }
 
   public void setMasque(boolean masque) {
/* 108 */     this.masque = masque;
   }

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="ClefTimestamp", nullable=false, length=19)
   public Date getClefTimestamp() {
/* 114 */     return this.clefTimestamp;
   }
 
   public void setClefTimestamp(Date clefTimestamp) {
/* 118 */     this.clefTimestamp = clefTimestamp;
   }
 
   @OneToMany(fetch=FetchType.LAZY, mappedBy="typereponse")
   public Set<Choix> getChoixes() {
/* 123 */     return this.choixes;
   }
 
   public void setChoixes(Set<Choix> choixes) {
/* 127 */     this.choixes = choixes;
   }
 
   @OneToMany(fetch=FetchType.LAZY, mappedBy="typereponse")
   public Set<Niveau> getNiveaus() {
     return this.niveaus;
   }
 
   public void setNiveaus(Set<Niveau> niveaus) {
     this.niveaus = niveaus;
   }
 
   @OneToMany(fetch=FetchType.LAZY, mappedBy="typereponse")
   public Set<Niveauobjet> getNiveauobjets() {
     return this.niveauobjets;
   }
 
   public void setNiveauobjets(Set<Niveauobjet> niveauobjets) {
     this.niveauobjets = niveauobjets;
   }
   @Column(name="Mode", nullable=false)
public Integer getMode() {
	return mode;
}

public void setMode(Integer mode) {
	this.mode = mode;
}
 }

