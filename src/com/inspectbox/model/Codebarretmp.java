 package com.inspectbox.model;
 
 import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
 
 @Entity
 @Table(name="codebarretmp",  catalog="inspectboxsa")
 public class Codebarretmp
   implements Serializable
 {
   private Integer idCodeBarreTmp;
   private String idImpression;
   private Date date;
   private String codeBarre;
   private String libelle;
   private String ariane;
 
   public Codebarretmp()
   {
   }
 
   public Codebarretmp(String idImpression, Date date, String codeBarre, String libelle, String ariane)
   {
/* 34 */     this.idImpression = idImpression;
/* 35 */     this.date = date;
/* 36 */     this.codeBarre = codeBarre;
/* 37 */     this.libelle = libelle;
/* 38 */     this.ariane = ariane;
   }
 
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="IdCodeBarreTmp", unique=true, nullable=false)
   public Integer getIdCodeBarreTmp() {
/* 45 */     return this.idCodeBarreTmp;
   }
 
   public void setIdCodeBarreTmp(Integer idCodeBarreTmp) {
/* 49 */     this.idCodeBarreTmp = idCodeBarreTmp;
   }
 
   @Column(name="IdImpression", nullable=false, length=250)
   public String getIdImpression() {
/* 54 */     return this.idImpression;
   }
 
   public void setIdImpression(String idImpression) {
/* 58 */     this.idImpression = idImpression;
   }
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="Date", nullable=false, length=19)
   public Date getDate() {
/* 64 */     return this.date;
   }
 
   public void setDate(Date date) {
/* 68 */     this.date = date;
   }
 
   @Column(name="CodeBarre", nullable=false, length=250)
   public String getCodeBarre() {
/* 73 */     return this.codeBarre;
   }
 
   public void setCodeBarre(String codeBarre) {
/* 77 */     this.codeBarre = codeBarre;
   }
 
   @Column(name="Libelle", nullable=false, length=250)
   public String getLibelle() {
/* 82 */     return this.libelle;
   }
 
   public void setLibelle(String libelle) {
/* 86 */     this.libelle = libelle;
   }
 
   @Column(name="Ariane", nullable=false, length=250)
   public String getAriane() {
/* 91 */     return this.ariane;
   }
 
   public void setAriane(String ariane) {
/* 95 */     this.ariane = ariane;
   }
 }

