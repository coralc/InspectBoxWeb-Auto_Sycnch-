/*     */ package com.inspectbox.model;
/*     */ 
/*     */ import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="droitutilisateur",  catalog="inspectboxsa")
/*     */ public class Droitutilisateur
/*     */   implements Serializable
/*     */ {
/*     */   private Integer idDroitUtilisateur;
/*     */   private String nom;
/*     */   private boolean adminClient;
/*     */   private boolean consultation;
/*     */   private boolean terminal;
/*     */   private Date clefTimestamp;
/*  32 */   private Set<Utilisateur> utilisateurs = new HashSet(0);
/*     */ 
/*     */   public Droitutilisateur()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Droitutilisateur(String nom, boolean adminClient, boolean consultation, boolean terminal, Date clefTimestamp) {
/*  39 */     this.nom = nom;
/*  40 */     this.adminClient = adminClient;
/*  41 */     this.consultation = consultation;
/*  42 */     this.terminal = terminal;
/*  43 */     this.clefTimestamp = clefTimestamp;
/*     */   }
/*     */ 
/*     */   public Droitutilisateur(String nom, boolean adminClient, boolean consultation, boolean terminal, Date clefTimestamp, Set<Utilisateur> utilisateurs)
/*     */   {
/*  49 */     this.nom = nom;
/*  50 */     this.adminClient = adminClient;
/*  51 */     this.consultation = consultation;
/*  52 */     this.terminal = terminal;
/*  53 */     this.clefTimestamp = clefTimestamp;
/*  54 */     this.utilisateurs = utilisateurs;
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(strategy=GenerationType.IDENTITY)
/*     */   @Column(name="IdDroitUtilisateur", unique=true, nullable=false)
/*     */   public Integer getIdDroitUtilisateur() {
/*  61 */     return this.idDroitUtilisateur;
/*     */   }
/*     */ 
/*     */   public void setIdDroitUtilisateur(Integer idDroitUtilisateur) {
/*  65 */     this.idDroitUtilisateur = idDroitUtilisateur;
/*     */   }
/*     */ 
/*     */   @Column(name="Nom", nullable=false, length=250)
/*     */   public String getNom() {
/*  70 */     return this.nom;
/*     */   }
/*     */ 
/*     */   public void setNom(String nom) {
/*  74 */     this.nom = nom;
/*     */   }
/*     */ 
/*     */   @Column(name="AdminClient", nullable=false)
/*     */   public boolean isAdminClient() {
/*  79 */     return this.adminClient;
/*     */   }
/*     */ 
/*     */   public void setAdminClient(boolean adminClient) {
/*  83 */     this.adminClient = adminClient;
/*     */   }
/*     */ 
/*     */   @Column(name="Consultation", nullable=false)
/*     */   public boolean isConsultation() {
/*  88 */     return this.consultation;
/*     */   }
/*     */ 
/*     */   public void setConsultation(boolean consultation) {
/*  92 */     this.consultation = consultation;
/*     */   }
/*     */ 
/*     */   @Column(name="Terminal", nullable=false)
/*     */   public boolean isTerminal() {
/*  97 */     return this.terminal;
/*     */   }
/*     */ 
/*     */   public void setTerminal(boolean terminal) {
/* 101 */     this.terminal = terminal;
/*     */   }
/*     */ 
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="ClefTimestamp", nullable=false, length=19)
/*     */   public Date getClefTimestamp() {
/* 107 */     return this.clefTimestamp;
/*     */   }
/*     */ 
/*     */   public void setClefTimestamp(Date clefTimestamp) {
/* 111 */     this.clefTimestamp = clefTimestamp;
/*     */   }
/*     */ 
/*     */   @OneToMany(fetch=FetchType.LAZY, mappedBy="droitutilisateur")
/*     */   public Set<Utilisateur> getUtilisateurs() {
/* 116 */     return this.utilisateurs;
/*     */   }
/*     */ 
/*     */   public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
/* 120 */     this.utilisateurs = utilisateurs;
/*     */   }
/*     */ }

