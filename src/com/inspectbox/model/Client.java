 package com.inspectbox.model;
 
 import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
 
 @Entity
 @Table(name="client",  catalog="inspectboxsa")
 public class Client
   implements Serializable
 {
   private Integer idClient;
   private String nom;
   private String codeAcces;
   private String clefClient;
   private int nombreNiveaux;
   private int nombreNiveauxObjets;
private int nombrePhotos;
   private boolean masque;
    private Set<Libelleniveau> libelleniveaus = new HashSet(0);
   

private Set<Typereponse> typereponses = new HashSet(0);
     private Set<Inspection> inspections = new HashSet(0);
     private Set<Utilisateur> utilisateurs = new HashSet(0);
     private Set<Niveau> niveaus = new HashSet(0);
     
     private Set<Niveauobjet> niveauobjets = new HashSet(0);
     private Set<Choix> choixes = new HashSet(0);
 
   public Client()
   {
   }
 
   public Client(String nom, String codeAcces, String clefClient, int nombreNiveaux, int nombreNiveauxObjets, boolean masque) {
       this.nom = nom;
       this.codeAcces = codeAcces;
       this.clefClient = clefClient;
       this.nombreNiveaux = nombreNiveaux;
       this.nombreNiveauxObjets = nombreNiveauxObjets;
       this.masque = masque;
   }
 
   public Client(String nom, String codeAcces, String clefClient, int nombreNiveaux, int nombreNiveauxObjets, boolean masque, Set<Libelleniveau> libelleniveaus, Set<Typereponse> typereponses, Set<Inspection> inspections, Set<Utilisateur> utilisateurs, Set<Niveau> niveaus,  Set<Niveauobjet> niveauobjets, Set<Choix> choixes)
   {
       this.nom = nom;
       this.codeAcces = codeAcces;
       this.clefClient = clefClient;
       this.nombreNiveaux = nombreNiveaux;
       this.nombreNiveauxObjets = nombreNiveauxObjets;
       this.masque = masque;
       this.libelleniveaus = libelleniveaus;
       this.typereponses = typereponses;
       this.inspections = inspections;
       this.utilisateurs = utilisateurs;
       this.niveaus = niveaus;
      
       this.niveauobjets = niveauobjets;
       this.choixes = choixes;
   }
 
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="IdClient", unique=true, nullable=false)
   public Integer getIdClient() {
       return this.idClient;
   }
 
   public void setIdClient(Integer idClient) {
       this.idClient = idClient;
   }
 
   @Column(name="Nom", nullable=false, length=0)
   public String getNom() {
    return this.nom;
   }
 
   public void setNom(String nom) {
    this.nom = nom;
   }
   @Column(name="NombrePhoto", nullable=false, length=0)
   public int getNombrePhotos() {
		return nombrePhotos;
	}

	public void setNombrePhotos(int nombrePhotos) {
		this.nombrePhotos = nombrePhotos;
	}
   @Column(name="CodeAcces", nullable=false)
   public String getCodeAcces() {
     return this.codeAcces;
   }
 
   public void setCodeAcces(String codeAcces) {
     this.codeAcces = codeAcces;
   }
 
   @Column(name="ClefClient", nullable=false)
   public String getClefClient() {
    return this.clefClient;
   }
 
   public void setClefClient(String clefClient) {
    this.clefClient = clefClient;
   }
 
   @Column(name="NombreNiveaux", nullable=false)
   public int getNombreNiveaux() {
    return this.nombreNiveaux;
   }
 
   public void setNombreNiveaux(int nombreNiveaux) {
     this.nombreNiveaux = nombreNiveaux;
   }
 
   @Column(name="NombreNiveauxObjets", nullable=false)
   public int getNombreNiveauxObjets() {
     return this.nombreNiveauxObjets;
   }
 
   public void setNombreNiveauxObjets(int nombreNiveauxObjets) {
     this.nombreNiveauxObjets = nombreNiveauxObjets;
   }
 
   @Column(name="Masque", nullable=false)
   public boolean isMasque() {
     return this.masque;
   }
 
   public void setMasque(boolean masque) {
     this.masque = masque;
   }
 
   @OneToMany(fetch=FetchType.LAZY, mappedBy="client")
   public Set<Libelleniveau> getLibelleniveaus() {
     return this.libelleniveaus;
   }
 
   public void setLibelleniveaus(Set<Libelleniveau> libelleniveaus) {
    this.libelleniveaus = libelleniveaus;
   }
 
   @OneToMany(fetch=FetchType.LAZY, mappedBy="client")
   public Set<Typereponse> getTypereponses() {
     return this.typereponses;
   }
 
   public void setTypereponses(Set<Typereponse> typereponses) {
     this.typereponses = typereponses;
   }
 
   @OneToMany(fetch=FetchType.LAZY, mappedBy="client")
   public Set<Inspection> getInspections() {
     return this.inspections;
   }
 
   public void setInspections(Set<Inspection> inspections) {
    this.inspections = inspections;
   }
 
   @OneToMany(fetch=FetchType.LAZY, mappedBy="client")
   public Set<Utilisateur> getUtilisateurs() {
   return this.utilisateurs;
   }
 
   public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
     this.utilisateurs = utilisateurs;
   }
 
   @OneToMany(fetch=FetchType.LAZY, mappedBy="client")
   public Set<Niveau> getNiveaus() {
    return this.niveaus;
   }
 
   public void setNiveaus(Set<Niveau> niveaus) {
     this.niveaus = niveaus;
   }
 
   
 
   @OneToMany(fetch=FetchType.LAZY, mappedBy="client")
   public Set<Niveauobjet> getNiveauobjets() {
     return this.niveauobjets;
   }
 
   public void setNiveauobjets(Set<Niveauobjet> niveauobjets) {
    this.niveauobjets = niveauobjets;
   }
 
   @OneToMany(fetch=FetchType.LAZY, mappedBy="client")
   public Set<Choix> getChoixes() {
    return this.choixes;
   }
 
   public void setChoixes(Set<Choix> choixes) {
    this.choixes = choixes;
   }
 }
