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
 @Table(name="utilisateur",  catalog="inspectboxsa")
 public class Utilisateur
   implements Serializable
 {
   private Integer idUtilisateur;
   private Client client;
   private Droitutilisateur droitutilisateur;
   private String codeAcces;
   private String motDePasse;
   private String prenom;
   private String nom;
   private boolean masque;
   private Date clefTimestamp;
   private String typeUser;
   
   private Set<Inspection> inspections = new HashSet(0);
 
   public Utilisateur()
   {
   }
 
   public Utilisateur(Client client, Droitutilisateur droitutilisateur, String codeAcces, String motDePasse, String prenom, String nom, boolean masque, Date clefTimestamp)
   {
     this.client = client;
     this.droitutilisateur = droitutilisateur;
     this.codeAcces = codeAcces;
     this.motDePasse = motDePasse;
     this.prenom = prenom;
     this.nom = nom;
    this.masque = masque;
    this.clefTimestamp = clefTimestamp;
   }
 
   public Utilisateur(Client client, Droitutilisateur droitutilisateur, String codeAcces, String motDePasse, String prenom, String nom, boolean masque, Date clefTimestamp, Set<Inspection> inspections)
   {
     this.client = client;
    this.droitutilisateur = droitutilisateur;
    this.codeAcces = codeAcces;
     this.motDePasse = motDePasse;
     this.prenom = prenom;
     this.nom = nom;
     this.masque = masque;
     this.clefTimestamp = clefTimestamp;
    this.inspections = inspections;
   }
 
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="IdUtilisateur", unique=true, nullable=false)
   public Integer getIdUtilisateur() {
     return this.idUtilisateur;
   }
 
   public void setIdUtilisateur(Integer idUtilisateur) {
    this.idUtilisateur = idUtilisateur;
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
   @JoinColumn(name="IdDroitUtilisateur", nullable=false)
   public Droitutilisateur getDroitutilisateur() {
     return this.droitutilisateur;
   }
 
   public void setDroitutilisateur(Droitutilisateur droitutilisateur) {
     this.droitutilisateur = droitutilisateur;
   }
 
   @Column(name="CodeAcces", nullable=false)
   public String getCodeAcces() {
     return this.codeAcces;
   }
 
   public void setCodeAcces(String codeAcces) {
     this.codeAcces = codeAcces;
   }
 
   @Column(name="MotDePasse", nullable=false)
   public String getMotDePasse() {
     return this.motDePasse;
   }
 
   public void setMotDePasse(String motDePasse) {
     this.motDePasse = motDePasse;
   }
 
   @Column(name="Prenom")
   public String getPrenom() {
     return this.prenom;
   }
 
   public void setPrenom(String prenom) {
     this.prenom = prenom;
   }
 
   @Column(name="Nom")
   public String getNom() {
     return this.nom;
   }
 
   public void setNom(String nom) {
    this.nom = nom;
   }
 
   @Column(name="Masque", nullable=false)
   public boolean isMasque() {
    return this.masque;
   }
 
   public void setMasque(boolean masque) {
/* 142 */     this.masque = masque;
   }
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="ClefTimestamp", nullable=false, length=19)
   public Date getClefTimestamp() {
     return this.clefTimestamp;
   }
 
   public void setClefTimestamp(Date clefTimestamp) {
     this.clefTimestamp = clefTimestamp;
   }
   @Column(name="TypeUser", nullable=false)
   public String getTypeUser() {
	return typeUser;
}

public void setTypeUser(String typeUser) {
	this.typeUser = typeUser;
}

@OneToMany(fetch=FetchType.LAZY, mappedBy="utilisateur")
   public Set<Inspection> getInspections() {
    return this.inspections;
   }
 
   public void setInspections(Set<Inspection> inspections) {
    this.inspections = inspections;
   }
 }

