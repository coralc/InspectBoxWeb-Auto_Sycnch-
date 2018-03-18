/*    */ package com.inspectbox.model;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ 
/*    */ @Entity
/*    */ @Table(name="libelleniveau",  catalog="inspectboxsa")
/*    */ public class Libelleniveau
/*    */   implements Serializable
/*    */ {
/*    */   private Integer idLibelleNiveau;
/*    */   private Client client;
/*    */   private int typeNiveau;
/*    */   private int num;
/*    */   private String libelle;
/*    */   private Date clefTimestamp;
/*    */ 
/*    */   public Libelleniveau()
/*    */   {
/*    */   }
/*    */ 
/*    */   public Libelleniveau(Client client, int typeNiveau, int num, String libelle, Date clefTimestamp)
/*    */   {
/* 37 */     this.client = client;
/* 38 */     this.typeNiveau = typeNiveau;
/* 39 */     this.num = num;
/* 40 */     this.libelle = libelle;
/* 41 */     this.clefTimestamp = clefTimestamp;
/*    */   }
/*    */ 
/*    */   @Id
/*    */   @GeneratedValue(strategy=GenerationType.IDENTITY)
/*    */   @Column(name="IdLibelleNiveau", unique=true, nullable=false)
/*    */   public Integer getIdLibelleNiveau() {
/* 48 */     return this.idLibelleNiveau;
/*    */   }
/*    */ 
/*    */   public void setIdLibelleNiveau(Integer idLibelleNiveau) {
/* 52 */     this.idLibelleNiveau = idLibelleNiveau;
/*    */   }
/*    */ 
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="IdClient", nullable=false)
/*    */   public Client getClient() {
/* 58 */     return this.client;
/*    */   }
/*    */ 
/*    */   public void setClient(Client client) {
/* 62 */     this.client = client;
/*    */   }
/*    */ 
/*    */   @Column(name="TypeNiveau", nullable=false)
/*    */   public int getTypeNiveau() {
/* 67 */     return this.typeNiveau;
/*    */   }
/*    */ 
/*    */   public void setTypeNiveau(int typeNiveau) {
/* 71 */     this.typeNiveau = typeNiveau;
/*    */   }
/*    */ 
/*    */   @Column(name="Num", nullable=false)
/*    */   public int getNum() {
/* 76 */     return this.num;
/*    */   }
/*    */ 
/*    */   public void setNum(int num) {
/* 80 */     this.num = num;
/*    */   }
/*    */ 
/*    */   @Column(name="Libelle", nullable=false)
/*    */   public String getLibelle() {
/* 85 */     return this.libelle;
/*    */   }
/*    */ 
/*    */   public void setLibelle(String libelle) {
/* 89 */     this.libelle = libelle;
/*    */   }
/*    */ 
/*    */   @Temporal(TemporalType.TIMESTAMP)
/*    */   @Column(name="ClefTimestamp", nullable=false, length=19)
/*    */   public Date getClefTimestamp() {
/* 95 */     return this.clefTimestamp;
/*    */   }
/*    */ 
/*    */   public void setClefTimestamp(Date clefTimestamp) {
/* 99 */     this.clefTimestamp = clefTimestamp;
/*    */   }
/*    */ }

/* Location:           C:\Users\Sébastien MARTIN\workspace\insp\inspectbox\WebRoot\WEB-INF\classes\
 * Qualified Name:     com.inspectbox.model.Libelleniveau
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.5.3
 */