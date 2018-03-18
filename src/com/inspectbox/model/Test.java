/*     */ package com.inspectbox.model;
/*     */ 
/*     */ import java.io.Serializable;
import java.util.Date;
import java.util.Set;

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
/*     */ 
/*     */ @Entity
/*     */ @Table(name="shalini",  catalog="inspectboxsa")
/*     */ public class Test
/*     */   implements Serializable
/*     */ {
/*     */   private Integer idChoix;
/*     */   private Typereponse typereponse;
/*     */   private Client client;
/*     */   private String valeur;
/*     */   private boolean nonConforme;
/*     */   private int tri;
/*     */   private boolean masque;
/*     */   private Date clefTimestamp;
/*  36 */   
/*     */ 
/*     */   public Test()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Test(Typereponse typereponse, Client client, String valeur, boolean nonConforme, int tri, boolean masque, Date clefTimestamp) {
/*  43 */     this.typereponse = typereponse;
/*  44 */     this.client = client;
/*  45 */     this.valeur = valeur;
/*  46 */     this.nonConforme = nonConforme;
/*  47 */     this.tri = tri;
/*  48 */     this.masque = masque;
/*  49 */     this.clefTimestamp = clefTimestamp;
/*     */   }
/*     */ 
/*     */   public Test(Typereponse typereponse, Client client, String valeur, boolean nonConforme, int tri, boolean masque, Date clefTimestamp, Set<Inspection> inspections)
/*     */   {
/*  55 */     this.typereponse = typereponse;
/*  56 */     this.client = client;
/*  57 */     this.valeur = valeur;
/*  58 */     this.nonConforme = nonConforme;
/*  59 */     this.tri = tri;
/*  60 */     this.masque = masque;
/*  61 */     this.clefTimestamp = clefTimestamp;
/*  62 */    
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(strategy=GenerationType.IDENTITY)
/*     */   @Column(name="IdChoix", unique=true, nullable=false)
/*     */   public Integer getIdChoix() {
/*  69 */     return this.idChoix;
/*     */   }
/*     */ 
/*     */   public void setIdChoix(Integer idChoix) {
/*  73 */     this.idChoix = idChoix;
/*     */   }
/*     */ 
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="IdTypeReponse", nullable=false)
/*     */   public Typereponse getTypereponse() {
/*  79 */     return this.typereponse;
/*     */   }
/*     */ 
/*     */   public void setTypereponse(Typereponse typereponse) {
/*  83 */     this.typereponse = typereponse;
/*     */   }
/*     */ 
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="idClient", nullable=false)
/*     */   public Client getClient() {
/*  89 */     return this.client;
/*     */   }
/*     */ 
/*     */   public void setClient(Client client) {
/*  93 */     this.client = client;
/*     */   }
/*     */ 
/*     */   @Column(name="Valeur", nullable=false)
/*     */   public String getValeur() {
/*  98 */     return this.valeur;
/*     */   }
/*     */ 
/*     */   public void setValeur(String valeur) {
/* 102 */     this.valeur = valeur;
/*     */   }
/*     */ 
/*     */   @Column(name="NonConforme")
/*     */   public boolean isNonConforme() {
/* 107 */     return this.nonConforme;
/*     */   }
/*     */ 
/*     */   public void setNonConforme(boolean nonConforme) {
/* 111 */     this.nonConforme = nonConforme;
/*     */   }
/*     */ 
/*     */   @Column(name="Tri")
/*     */   public int getTri() {
/* 116 */     return this.tri;
/*     */   }
/*     */ 
/*     */   public void setTri(int tri) {
/* 120 */     this.tri = tri;
/*     */   }
/*     */ 
/*     */   @Column(name="Masque", nullable=false)
/*     */   public boolean isMasque() {
/* 125 */     return this.masque;
/*     */   }
/*     */ 
/*     */   public void setMasque(boolean masque) {
/* 129 */     this.masque = masque;
/*     */   }
/*     */ 
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   @Column(name="ClefTimestamp", nullable=false, length=19)
/*     */   public Date getClefTimestamp() {
/* 135 */     return this.clefTimestamp;
/*     */   }
/*     */ 
/*     */   public void setClefTimestamp(Date clefTimestamp) {
/* 139 */     this.clefTimestamp = clefTimestamp;
/*     */   }
/*     */ 
/*     */  
/*     */ }

/* Location:           C:\Users\Sébastien MARTIN\workspace\insp\inspectbox\WebRoot\WEB-INF\classes\
 * Qualified Name:     com.inspectbox.model.Choix
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.5.3
 */