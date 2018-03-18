 package com.inspectbox.model;
 
 import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
 
 @Entity
 @Table(name="inspection",  catalog="inspectboxsa")
 public class Inspection
   implements Serializable
 {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Integer idInspection;
   private Niveauobjet niveauobjet;
   private Client client;
   private Niveau niveau;
   private Choix choix;
   
   private Utilisateur utilisateur;
   private String idTerminal;
   private String idInformation;
   private Date dateInformation;
   private Date dateRemise;
   private String reponse;
   
private boolean defekt;

 
   public Inspection()
   {
   }
 
   public Inspection(Client client, Niveau niveau, Utilisateur utilisateur, String idTerminal, String idInformation, Date dateInformation, Date dateRemise,boolean defekt)
   {
     this.client = client;
    this.niveau = niveau;
    this.utilisateur = utilisateur;
     this.idTerminal = idTerminal;
     this.idInformation = idInformation;
     this.dateInformation = dateInformation;
     this.dateRemise = dateRemise;
     this.defekt=defekt;
   }
 
   public Inspection(Niveauobjet niveauobjet, Client client, Niveau niveau, Choix choix,  Utilisateur utilisateur, String idTerminal, String idInformation, Date dateInformation, Date dateRemise, String reponse,  boolean defekt)
   {
    this.niveauobjet = niveauobjet;
    this.client = client;
    this.niveau = niveau;
     this.choix = choix;
    this.utilisateur = utilisateur;
     this.idTerminal = idTerminal;
     this.idInformation = idInformation;
     this.dateInformation = dateInformation;
    this.dateRemise = dateRemise;
     this.reponse = reponse;
     this.defekt=defekt;
     }
 
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="IdInspection", unique=true, nullable=false)
   public Integer getIdInspection() {
     return this.idInspection;
   }
 
   public void setIdInspection(Integer idInspection) {
     this.idInspection = idInspection;
   }
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="IdNiveauObjet")
   public Niveauobjet getNiveauobjet() {
     return this.niveauobjet;
   }
 
   public void setNiveauobjet(Niveauobjet niveauobjet) {
     this.niveauobjet = niveauobjet;
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
   @JoinColumn(name="IdNiveau", nullable=false)
   public Niveau getNiveau() {
     return this.niveau;
   }
 
   public void setNiveau(Niveau niveau) {
     this.niveau = niveau;
   }
 
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="IdChoix")
   public Choix getChoix() {
     return this.choix;
   }
 
   public void setChoix(Choix choix) {
     this.choix = choix;
   }
 
     @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="IdUtilisateur", nullable=false)
   public Utilisateur getUtilisateur() {
    return this.utilisateur;
   }
 
   public void setUtilisateur(Utilisateur utilisateur) {
     this.utilisateur = utilisateur;
   }
 
   @Column(name="IdTerminal", nullable=false)
   public String getIdTerminal() {
     return this.idTerminal;
   }
 
   public void setIdTerminal(String idTerminal) {
     this.idTerminal = idTerminal;
   }
 
   @Column(name="IdInformation", nullable=false, length=65535)
   public String getIdInformation() {
     return this.idInformation;
   }
 
   public void setIdInformation(String idInformation) {
     this.idInformation = idInformation;
   }
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="DateInformation", nullable=false, length=19)
   public Date getDateInformation() {
     return this.dateInformation;
   }
 
   public void setDateInformation(Date dateInformation) {
    this.dateInformation = dateInformation;
   }
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="DateRemise", nullable=false, length=19)
   public Date getDateRemise() {
     return this.dateRemise;
   }
 
   public void setDateRemise(Date dateRemise) {
     this.dateRemise = dateRemise;
   }
 
   @Column(name="Reponse", length=50)
   public String getReponse() {
    return this.reponse;
   }
 
   public void setReponse(String reponse) {
     this.reponse = reponse;
   }


   @Column(name="defekt")
   
   public boolean isDefekt() {
		return defekt;
	}

	public void setDefekt(boolean defekt) {
		this.defekt = defekt;
	}
 }

