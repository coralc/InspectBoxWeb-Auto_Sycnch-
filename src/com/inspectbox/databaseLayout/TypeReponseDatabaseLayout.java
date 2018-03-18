 package com.inspectbox.databaseLayout;
 
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.inspectbox.model.Client;
import com.inspectbox.model.Typereponse;
import com.inspectbox.utils.HibernateUtil;
 
 public class TypeReponseDatabaseLayout
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
/*  21 */   private Typereponse model = null;
/*  22 */   private ArrayList liste = null;
/*  23 */   private Session session = null;
/*  24 */   private Transaction tx = null;
/*  25 */   private Integer idTypeReponse = null;
/*  26 */   private Integer idClient = null;
/*  27 */   private Integer idParent = null;
   private Client client;
 
   public TypeReponseDatabaseLayout()
   {
/*  33 */     this.model = new Typereponse();
/*  34 */     this.liste = new ArrayList();
/*  35 */     this.session = HibernateUtil.currentSession();
   }
 
   public Integer getIdClient()
   {
/*  40 */     return this.idClient;
   }
 
   public void transactionOpen()
   {
/*  45 */     this.tx = this.session.beginTransaction();
   }
 
   public void transactionCommit()
   {
/*  50 */     this.tx.commit();
   }
 
   public void update()
   {
/*  55 */     this.session.update(this.model);
   }
 
   public int save()
   {
/*  60 */    // this.session.save(this.model);
			Integer result = (Integer)session.save(this.model);
			return result.intValue();
   }
 
   public Typereponse model()
   {
/*  65 */     return this.model;
   }
 
   public List<Typereponse> liste()
   {
/*  70 */     return this.liste;
   }
 
   public void close()
   {
/*  76 */     HibernateUtil.closeSession();
   }
 
   public Boolean verifierDroitAjouterTypeReponse()
   {
/*  83 */     if (this.idClient == null) {
/*  84 */       return Boolean.valueOf(false);
     }
 
/*  87 */     return Boolean.valueOf(true);
   }
 
   public void afficher(Integer idClient, Integer idTypeReponse)
   {
/*  93 */     this.model = 
/*  98 */       ((Typereponse)this.session.createQuery("from Typereponse as no inner join fetch no.client  where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.idTypeReponse = :typeReponse")
/*  95 */       .setParameter("masque", Boolean.valueOf(false))
/*  96 */       .setParameter("client", idClient)
/*  97 */       .setParameter("typeReponse", idTypeReponse)
/*  98 */       .uniqueResult());
   }
 
   public void setClient(Integer idClient)
   {
/* 104 */     this.idClient = idClient;
/* 105 */     Client client = (Client)this.session.createQuery("from Client where  idClient = :client")
/* 106 */       .setParameter("client", idClient)
/* 107 */       .uniqueResult();
 
/* 109 */     this.model.setClient(client);
   }
 
   public void lister(Integer idClient)
 {
     this.liste = 
       ((ArrayList)this.session.createQuery("from Typereponse as tr inner join fetch tr.client where tr.masque = :masque and tr.client.idClient = :client  and tr.client.masque = :masque")
       .setParameter("masque", Boolean.valueOf(false))
       .setParameter("client", idClient)
       .list());
   }
   public void listerListe(Integer idClient, String rep)
   {
       this.liste = 
         ((ArrayList)this.session.createQuery("from Typereponse as tr inner join fetch tr.client where tr.masque = :masque and tr.client.idClient = :client  and tr.client.masque = :masque and tr.modeReponse =:rep")
         .setParameter("masque", Boolean.valueOf(false))
         .setParameter("client", idClient)
         .setParameter("rep", rep)
         .list());
     }
   public void afficherByMode(Integer idClient, Integer mode)
   {
    this.model = 
       ((Typereponse)this.session.createQuery("from Typereponse as no inner join fetch no.client  where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.mode = :mode")
       .setParameter("masque", Boolean.valueOf(false))
       .setParameter("client", idClient)
       .setParameter("mode", mode)
       .uniqueResult());
   }
   
   public void afficherByMode2(Integer idClient, Integer mode)
   {
    this.liste = 
    		((ArrayList)this.session.createQuery("from Typereponse as no inner join fetch no.client  where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.mode = :mode")
       .setParameter("masque", Boolean.valueOf(false))
       .setParameter("client", idClient)
       .setParameter("mode", mode)
       .list());
   }
   
 }

