 package com.inspectbox.databaseLayout;
 
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.inspectbox.model.Choix;
import com.inspectbox.model.Client;
import com.inspectbox.model.Typereponse;
import com.inspectbox.utils.HibernateUtil;
 
 public class ChoixDatabaseLayout
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
/*  22 */   private Choix model = null;
/*  23 */   private ArrayList liste = null;
/*  24 */   private Session session = null;
/*  25 */   private Transaction tx = null;
/*  26 */   private Integer idTypeReponse = null;
/*  27 */   private Integer idClient = null;
/*  28 */   private Integer idParent = null;
   private Client client;
 
   public ChoixDatabaseLayout()
   {
/*  34 */     this.model = new Choix();
/*  35 */     this.liste = new ArrayList();
/*  36 */     this.session = HibernateUtil.currentSession();
   }
 
   public Integer getIdClient()
   {
/*  41 */     return this.idClient;
   }
 
   public void transactionOpen()
   {
/*  46 */     this.tx = this.session.beginTransaction();
   }
 
   public void transactionCommit()
   {
/*  51 */     this.tx.commit();
   }
 
   public void update()
   {
/*  56 */     this.session.update(this.model);
   }
 
   public void save()
   {
	
			try {
				this.session.save(this.model);
				
			} catch (Exception e) {
				System.out.println("Exception occurrs while adding choix");
				e.printStackTrace();
			}   }
 
   public Choix model()
   {
/*  66 */     return this.model;
   }
 
   public List<Choix> liste()
   {
/*  71 */     return this.liste;
   }
 
   public void close()
   {
/*  77 */     HibernateUtil.closeSession();
   }
 
   public Boolean verifierDroitAjouterChoix()
   {
/*  84 */     if (this.idClient == null) {
/*  85 */       return Boolean.valueOf(false);
     }
 
/*  88 */     return Boolean.valueOf(true);
   }
 
   public void supprimer(Integer idClient)
   {
	   this.liste = 
		       ((ArrayList)this.session.createQuery("DELETE from Choix   where idClient = :client ")
		       
		       .setParameter("client", idClient)
		       
		      .list());
  	 
  	// q.executeUpdate();
   }
   
   public void afficher(Integer idClient, Integer idChoix)
   {
/*  94 */     this.model = 
/*  99 */       ((Choix)this.session.createQuery("from Choix as no inner join fetch no.client  where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.idChoix = :choix")
/*  96 */       .setParameter("masque", Boolean.valueOf(false))
/*  97 */       .setParameter("client", idClient)
/*  98 */       .setParameter("choix", idChoix)
/*  99 */       .uniqueResult());

   }
 
   public void setClient(Integer idClient)
   {
     this.idClient = idClient;
    Client client = (Client)this.session.createQuery("from Client where  idClient = :client")
       .setParameter("client", idClient)
      .uniqueResult();
 
     this.model.setClient(client);
   }
 
   public void setTypeReponse(Integer idTypeReponse)
   {
/* 116 */     this.idTypeReponse = idTypeReponse;
/* 117 */     Typereponse typeReponse = (Typereponse)this.session.createQuery("from Typereponse as no inner join fetch no.client  where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.idTypeReponse = :typeReponse")
/* 119 */       .setParameter("masque", Boolean.valueOf(false))
/* 120 */       .setParameter("client", this.idClient)
/* 121 */       .setParameter("typeReponse", idTypeReponse)
/* 122 */       .uniqueResult();
 
/* 124 */     this.model.setTypereponse(typeReponse);
   }
 
   public void lister(Integer idClient, Integer idTypeReponse)
   {
     this.liste = 
       ((ArrayList)this.session.createQuery("from Choix as tr inner join fetch tr.client where tr.masque = :masque and tr.client.idClient = :client  and tr.client.masque = :masque and tr.typereponse.idTypeReponse = :typeReponse order by tr.tri")
       .setParameter("masque", Boolean.valueOf(false))
       .setParameter("client", idClient)
       .setParameter("typeReponse", idTypeReponse)
      .list());

   }
   public void listeByClient(Integer idClient)
   {
     this.liste = 
       ((ArrayList)this.session.createQuery("from Choix as tr inner join fetch tr.client where tr.masque = :masque and tr.client.idClient = :client  and tr.client.masque = :masque ")
       .setParameter("masque", Boolean.valueOf(false))
       .setParameter("client", idClient)
       
      .list());

   }
 }

