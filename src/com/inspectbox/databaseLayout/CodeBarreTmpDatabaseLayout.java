/*     */ package com.inspectbox.databaseLayout;
/*     */ 
/*     */ import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.inspectbox.model.Client;
import com.inspectbox.model.Codebarretmp;
import com.inspectbox.utils.HibernateUtil;
/*     */ 
/*     */ public class CodeBarreTmpDatabaseLayout
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  24 */   private Codebarretmp model = null;
/*  25 */   private Session session = null;
/*  26 */   private Transaction tx = null;
/*  27 */   private ArrayList liste = null;
/*  28 */   private Integer idNiveauObjet = null;
/*  29 */   private Integer idNiveau = null;
/*  30 */   private Integer idClient = null;
/*  31 */   private Integer idParent = null;
/*     */   private Client client;
/*     */ 
/*     */   public CodeBarreTmpDatabaseLayout()
/*     */   {
/*  37 */     reloadModel();
/*  38 */     this.session = HibernateUtil.currentSession();
/*     */   }
/*     */ 
/*     */   public void reloadModel()
/*     */   {
/*  43 */     this.model = new Codebarretmp();
/*     */   }
/*     */ 
/*     */   public Integer getIdClient()
/*     */   {
/*  48 */     return this.idClient;
/*     */   }
/*     */ 
/*     */   public void transactionOpen()
/*     */   {
/*  53 */     this.tx = this.session.beginTransaction();
/*     */   }
/*     */ 
/*     */   public void transactionCommit()
/*     */   {
/*  58 */     this.tx.commit();
/*     */   }
/*     */ 
/*     */   public void save()
/*     */   {
/*  63 */     this.session.save(this.model);
/*     */   }
/*     */ 
/*     */   public void update()
/*     */   {
/*  68 */     this.session.update(this.model);
/*     */   }
/*     */ 
/*     */   public Codebarretmp model()
/*     */   {
/*  73 */     return this.model;
/*     */   }
/*     */ 
/*     */   public void setModel(Codebarretmp niv)
/*     */   {
/*  78 */     this.model = niv;
/*     */   }
/*     */ 
/*     */   public List<Codebarretmp> liste()
/*     */   {
/*  83 */     return this.liste;
/*     */   }
/*     */ 
/*     */   public void close()
/*     */   {
/*  88 */     HibernateUtil.closeSession();
/*     */   }
/*     */ 
/*     */   public Boolean verifierDroitAjouterInspection()
/*     */   {
/*  95 */     if (this.idClient == null) {
/*  96 */       return Boolean.valueOf(false);
/*     */     }
/*     */ 
/* 108 */     return Boolean.valueOf(true);
/*     */   }
/*     */ 
/*     */   public Boolean verifierDroitModifier(Integer idClient, Integer idNiveau)
/*     */   {
/* 127 */     this.idClient = idClient;
/* 128 */     return Boolean.valueOf(true);
/*     */   }
/*     */ 
/*     */   public void listerInitial(Integer idClient)
/*     */   {
/* 137 */     this.liste = 
/* 141 */       ((ArrayList)this.session.createQuery("from Niveau as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.niveau is null")
/* 139 */       .setParameter("masque", Boolean.valueOf(false))
/* 140 */       .setParameter("client", idClient)
/* 141 */       .list());
/*     */   }
/*     */ 
/*     */   public void listerFinalNonMasque(Integer idClient)
/*     */   {
/* 149 */     this.liste = 
/* 153 */       ((ArrayList)this.session.createQuery("from Niveau as no inner join fetch no.client where no.client.idClient = :client  and no.client.masque = :masque ")
/* 151 */       .setParameter("masque", Boolean.valueOf(false))
/* 152 */       .setParameter("client", idClient)
/* 153 */       .list());
/*     */   }
/*     */ 
/*     */   public Boolean isNoObjetLink(Integer idNiveauObjet)
/*     */   {
/* 159 */     return 
/* 163 */       Boolean.valueOf(this.session.createQuery("from Niveau as no inner join fetch no.client where no.niveauobjet.idNiveauObjet = :idNiveauObjet and  no.client.idClient = :client  and no.client.masque = :masque and no.masque = :masque")
/* 161 */       .setParameter("masque", Boolean.valueOf(false))
/* 162 */       .setParameter("idNiveauObjet", idNiveauObjet)
.setParameter("client", idClient)
/* 163 */       .list().isEmpty());
/*     */   }
/*     */ 
/*     */   public void listerAllNonInitial(Integer idClient)
/*     */   {
/* 168 */     this.liste = 
/* 172 */       ((ArrayList)this.session.createQuery("from Niveau as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.niveau is not null order by no.idNiveau")
/* 170 */       .setParameter("masque", Boolean.valueOf(false))
/* 171 */       .setParameter("client", idClient)
/* 172 */       .list());
/*     */   }
/*     */ }

/* Location:           C:\Users\Sébastien MARTIN\workspace\insp\inspectbox\WebRoot\WEB-INF\classes\
 * Qualified Name:     com.inspectbox.databaseLayout.CodeBarreTmpDatabaseLayout
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.5.3
 */