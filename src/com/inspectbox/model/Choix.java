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
 @Table(name="choix", catalog="inspectboxsa")
 public class Choix
   implements Serializable
 {
   private Integer idChoix;
   private Typereponse typereponse;
   private Client client;
   private String valeur;
   private boolean nonConforme;
   private int tri;
   private boolean masque;
   private Date clefTimestamp;
   private Set<Inspection> inspections = new HashSet(0);
 
   public Choix()
   {
   }
 
   public Choix(Typereponse typereponse, Client client, String valeur, boolean nonConforme, int tri, boolean masque, Date clefTimestamp) {
     this.typereponse = typereponse;
     this.client = client;
     this.valeur = valeur;
     this.nonConforme = nonConforme;
     this.tri = tri;
     this.masque = masque;
    this.clefTimestamp = clefTimestamp;
   }
 
   public Choix(Typereponse typereponse, Client client, String valeur, boolean nonConforme, int tri, boolean masque, Date clefTimestamp, Set<Inspection> inspections)
   {
     this.typereponse = typereponse;
    this.client = client;
    this.valeur = valeur;
    this.nonConforme = nonConforme;
    this.tri = tri;
    this.masque = masque;
     this.clefTimestamp = clefTimestamp;
   this.inspections = inspections;
   }
 
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="IdChoix", unique=true, nullable=false)
   public Integer getIdChoix() {
     return this.idChoix;
   }
 
   public void setIdChoix(Integer idChoix) {
    this.idChoix = idChoix;
   }
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="IdTypeReponse", nullable=false)
   public Typereponse getTypereponse() {
    return this.typereponse;
   }
 
   public void setTypereponse(Typereponse typereponse) {
     this.typereponse = typereponse;
   }
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="idClient", nullable=false)
   public Client getClient() {
    return this.client;
   }
 
   public void setClient(Client client) {
     this.client = client;
   }
 
   @Column(name="Valeur", nullable=false)
   public String getValeur() {
     return this.valeur;
   }
 
   public void setValeur(String valeur) {
     this.valeur = valeur;
   }
 
   @Column(name="NonConforme", nullable=false)
   public boolean isNonConforme() {
    return this.nonConforme;
   }
 
   public void setNonConforme(boolean nonConforme) {
    this.nonConforme = nonConforme;
   }
 
   @Column(name="Tri", nullable=false)
   public int getTri() {
    return this.tri;
   }
 
   public void setTri(int tri) {
    this.tri = tri;
   }
 
   @Column(name="Masque", nullable=false)
   public boolean isMasque() {
     return this.masque;
   }
 
   public void setMasque(boolean masque) {
     this.masque = masque;
   }
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="ClefTimestamp", nullable=false, length=19)
   public Date getClefTimestamp() {
     return this.clefTimestamp;
   }
 
   public void setClefTimestamp(Date clefTimestamp) {
     this.clefTimestamp = clefTimestamp;
   }
 
   @OneToMany(fetch=FetchType.LAZY, mappedBy="choix")
   public Set<Inspection> getInspections() {
     return this.inspections;
   }
 
   public void setInspections(Set<Inspection> inspections) {
     this.inspections = inspections;
   }
 }
