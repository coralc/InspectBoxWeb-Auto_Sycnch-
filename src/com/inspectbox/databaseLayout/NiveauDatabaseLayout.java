 package com.inspectbox.databaseLayout;
 
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import com.inspectbox.model.Client;
import com.inspectbox.model.Niveau;
import com.inspectbox.model.Niveauobjet;
import com.inspectbox.utils.HibernateUtil;
 
 public class NiveauDatabaseLayout
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
/*  23 */   private Niveau model = null;
/*  24 */   private Session session = null;
/*  25 */   private Transaction tx = null;
/*  26 */   private ArrayList liste = null;
/*  27 */   private Integer idNiveauObjet = null;
/*  28 */   private Integer idNiveau = null;
/*  29 */   private Integer idClient = null;
/*  30 */   private Integer idParent = null;
   private Client client;
 
   public NiveauDatabaseLayout()
   {
/*  36 */     this.model = new Niveau();
/*  37 */     this.session = HibernateUtil.currentSession();
   }
 
   public Integer getIdClient()
   {
/*  42 */     return this.idClient;
   }
 
   public void transactionOpen()
   {
/*  47 */     this.tx = this.session.beginTransaction();
   }
 
   public void transactionCommit()
   {
/*  52 */     this.tx.commit();
   }
 
   public void save()
   {
/*  57 */      this.session.save(this.model);

   }
   public int save2()
   {
/*  57 */      this.session.save(this.model);
return this.model.getIdNiveau();
   }
   public void update()
   {
/*  62 */     this.session.update(this.model);
   }
 
   public Niveau model()
   {
/*  67 */     return this.model;
   }
 
   public void setModel(Niveau niv)
   {
/*  72 */     this.model = niv;
   }
   public ArrayList<Niveau> getListe() {
		return this.liste;
	}
   public List<Niveau> liste()
   {
/*  77 */     return this.liste;
   }
   
   public void close()
   {
/*  82 */     HibernateUtil.closeSession();
   }
 
   public Boolean verifierDroitAjouter()
   {
/*  88 */     if (this.idClient == null) {
/*  89 */       return Boolean.valueOf(false);
     }
 
/*  93 */     if ((this.idParent != null) && 
/*  94 */       (!(_verifierNiveauParent().booleanValue()))) {
/*  95 */       return Boolean.valueOf(false);
     }
 
/*  99 */     return Boolean.valueOf(true);
   }
 
   public Boolean verifierDroitAjouterInspection()
   {
/* 105 */     if (this.idClient == null) {
/* 106 */       return Boolean.valueOf(false);
     }
 
/* 118 */     return Boolean.valueOf(true);
   }
 
   public Boolean verifierDroitModifier(Integer idClient, Integer idNiveau)
   {
/* 124 */     this.model = 
/* 129 */       ((Niveau)this.session.createQuery("from Niveau as no inner join fetch no.client where no.masque = :masque and  no.client.idClient = :client and no.client.masque = :masque and no.idNiveau = :Niveau")
/* 126 */       .setParameter("masque", Boolean.valueOf(false))
/* 127 */       .setParameter("client", idClient)
/* 128 */       .setParameter("Niveau", idNiveau)
/* 129 */       .uniqueResult());
 
/* 137 */     this.idClient = idClient;
/* 138 */     return Boolean.valueOf(true);
   }
 
   private Boolean _verifierNiveauParent()
   {
/* 149 */     if ((this.idParent != null) && 
/* 152 */       (this.model.getNiveau().getClient().getIdClient().equals(this.idClient))) {
/* 153 */       return Boolean.valueOf(true);
     }
 
/* 156 */     return Boolean.valueOf(false);
   }
 
   public void setClient(Integer idClient)
   {
/* 162 */     this.idClient = idClient;
/* 163 */     this.client = 
/* 165 */       ((Client)this.session.createQuery("from Client where  idClient = :client")
/* 164 */       .setParameter("client", idClient)
/* 165 */       .uniqueResult());
 
/* 170 */     this.model.setClient(this.client);
   }
 
   public void setNiveauObjet(Integer idNiveauObjet)
   {
/* 175 */     this.idNiveauObjet = idNiveauObjet;
/* 176 */     Niveauobjet niveauObjet = (Niveauobjet)this.session.createQuery("from Niveauobjet where idNiveauObjet = :idNiveauObjet ")
/* 177 */       .setParameter("idNiveauObjet", idNiveauObjet)
/* 178 */       .uniqueResult();
 
/* 181 */     this.model.setNiveauobjet(niveauObjet);
   }
   public void setNiveauObjet2(Integer idclient, Integer idNiveauObjet)
   {
/* 175 */     this.idNiveauObjet = idNiveauObjet;
/* 176 */     Niveauobjet niveauObjet = (Niveauobjet)this.session.createQuery("from Niveauobjet where idNiveauObjet = :idNiveauObjet ")
/* 177 */       .setParameter("idNiveauObjet", idNiveauObjet)
/* 178 */       .uniqueResult();
 
/* 181 */     this.model.setNiveauobjet(niveauObjet);
   }
   public void setNiveauParent(Integer idParent)
   {
/* 186 */     this.idParent = idParent;
/* 187 */     Niveau objetParent = (Niveau)this.session.createQuery("from Niveau where idNiveau = :idNiveau")
/* 188 */       .setParameter("idNiveau", idParent)
/* 189 */       .uniqueResult();
 
/* 192 */     this.model.setNiveau(objetParent);
   }
 
   public void afficher(Integer idClient, Integer idObjet)
   {
   this.model = 
       ((Niveau)this.session.createQuery("from Niveau as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.idNiveau = :objet")
       .setParameter("masque", Boolean.valueOf(false))
       .setParameter("client", idClient)
       .setParameter("objet", idObjet)
       .uniqueResult());
   }
   public void maxIndex()
   {
	   
	   this.model = 
       ((Niveau)this.session.createCriteria(Niveau.class)
    		   .addOrder(Order.desc("idNiveau"))
    		    .setMaxResults(1)
    		    .uniqueResult());
   }
   public void afficher2(Integer idClient, Integer idObjet)
   {
   this.model = 
       ((Niveau)this.session.createQuery("from Niveau as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and idNiveauObjet = :objet")
       .setParameter("masque", Boolean.valueOf(false))
       .setParameter("client", idClient)
       .setParameter("objet", idObjet)
       .uniqueResult());
   }
   public void afficherunik(Integer idClient, Integer idObjet)
   {
   this.model = 
       ((Niveau)this.session.createQuery("from Niveau as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.niveauobjet.idNiveauObjet = :objet")
       .setParameter("masque", Boolean.valueOf(false))
       .setParameter("client", idClient)
       .setParameter("objet", idObjet)
       .uniqueResult());
   
		
   }
   public void listerInitial(Integer idClient)
   {
/* 209 */     this.liste = 
/* 213 */       ((ArrayList)this.session.createQuery("from Niveau as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.niveau is null")
/* 211 */       .setParameter("masque", Boolean.valueOf(false))
/* 212 */       .setParameter("client", idClient)
/* 213 */       .list());
   }
 
   public void listerTotal(Integer idClient)
   {
/* 218 */     this.liste = 
/* 222 */       ((ArrayList)this.session.createQuery("from Niveau as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque ORDER BY no.niveau.idNiveau, no.tri DESC")
/* 220 */       .setParameter("masque", Boolean.valueOf(false))
/* 221 */       .setParameter("client", idClient)
/* 222 */       .list());
   }
 
   public void listerFinalNonMasque(Integer idClient)
   {
/* 229 */     this.liste = 
/* 233 */       ((ArrayList)this.session.createQuery("from Niveau as no inner join fetch no.client where no.client.idClient = :client  and no.client.masque = :masque ")
/* 231 */       .setParameter("masque", Boolean.valueOf(false))
/* 232 */       .setParameter("client", idClient)
/* 233 */       .list());
   }
 
   public Boolean isNoObjetLink(Integer idNiveauObjet,Integer idClient)
   {
/* 239 */     return 
/* 243 */       Boolean.valueOf(this.session.createQuery("from Niveau as no inner join fetch no.client where no.niveauobjet.idNiveauObjet = :idNiveauObjet  and no.client.masque = :masque and no.masque = :masque and no.client.idClient = :client")
/* 241 */       .setParameter("masque", Boolean.valueOf(false))

/* 242 */       .setParameter("idNiveauObjet", idNiveauObjet)
			.setParameter("client", idClient)
/* 243 */       .list().isEmpty());
   }
 
   public void listerAllNonInitial(Integer idClient)
   {
/* 248 */     this.liste = 
/* 252 */       ((ArrayList)this.session.createQuery("from Niveau as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.niveau is not null order by no.idNiveau")
/* 250 */       .setParameter("masque", Boolean.valueOf(false))
/* 251 */       .setParameter("client", idClient)
/* 252 */       .list());
   }
			public void listerChild(Integer idClient, Integer idNiveauParent)
   {
/* 248 */     this.liste = 
/* 252 */       ((ArrayList)this.session.createQuery("from Niveau as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.niveau.idNiveau = :niveauParent order by no.idNiveau")
/* 250 */       .setParameter("masque", Boolean.valueOf(false))
/* 251 */       .setParameter("client", idClient)
       			.setParameter("niveauParent", idNiveauParent)
/* 252 */       .list());
   }
			
			public void listerForTree(Integer idClient)
			   {
			     this.liste = 
			       ((ArrayList)this.session.createQuery("from Niveau as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque ")
			       .setParameter("masque", Boolean.valueOf(false))
			       .setParameter("client", idClient)
			       .list());
			   }
			public void listerSite(Integer idClient)
			   {
			     this.liste = 
			       ((ArrayList)this.session.createQuery("from Niveau as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.parentId is NULL order by no.tri ")
			       .setParameter("masque", Boolean.valueOf(false))
			       .setParameter("client", idClient)
			       
			       .list());
			   }
			public void listerBat(Integer idClient ,Integer idparent)
			   {
			     this.liste = 
			       ((ArrayList)this.session.createQuery("from Niveau as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.parentId = :parent order by no.tri ")
			       .setParameter("masque", Boolean.valueOf(false))
			       .setParameter("client", idClient)
			       .setParameter("parent", idparent)
			       .list());
			     System.out.println("this.liste bat"+this.liste.size());
			   }
			public void afficherLasttree(Integer idClient, Integer idParent)
			  {
				this.model = 
					       ((Niveau)this.session.createQuery("from Niveau as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.parentId = :parent order by no.tri DESC  ")
					       .setParameter("masque", Boolean.valueOf(false))
					       .setParameter("client", idClient)
					       .setParameter("parent", idParent)
					       .setMaxResults(1)
					       
					       .uniqueResult());
				  
			  }
			public void afficherLasttreeSite(Integer idClient)
			  {
				this.model = 
					       ((Niveau)this.session.createQuery("from Niveau as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and  no.parentId is NULL order by no.tri DESC  ")
					       .setParameter("masque", Boolean.valueOf(false))
					       .setParameter("client", idClient)
					       
					       .setMaxResults(1)
					       
					       .uniqueResult());
				  
			  }
			public void listeTriSite(Integer idClient, Integer triAct )
			   {
			     this.liste = 
			       ((ArrayList)this.session.createQuery("from Niveau as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and  no.parentId is NULL and no.tri > :triact order by no.tri  ")
			       .setParameter("masque", Boolean.valueOf(false))
			       .setParameter("client", idClient)
			       .setParameter("triact", triAct)
			       .list());
			   }
			
			public void listeTriup(Integer idClient, Integer triAct, Integer idPar )
			   {
			     this.liste = 
			       ((ArrayList)this.session.createQuery("from Niveau as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and  no.parentId = :parent and no.tri > :triact order by no.tri  ")
			       .setParameter("masque", Boolean.valueOf(false))
			       .setParameter("client", idClient)
			        .setParameter("parent", idPar)
			       .setParameter("triact", triAct)
			       .list());
			   }
			
			public void listeTriup2(Integer idClient, Integer triAct, Integer idPar )
			   {
			     this.liste = 
			       ((ArrayList)this.session.createQuery("from Niveau as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and  no.parentId = :parent and no.tri >= :triact order by no.tri  ")
			       .setParameter("masque", Boolean.valueOf(false))
			       .setParameter("client", idClient)
			        .setParameter("parent", idPar)
			       .setParameter("triact", triAct)
			       .list());
			   }
			public void listerequi(Integer idClient )
			   {
			     this.liste = 
			       ((ArrayList)this.session.createQuery("from Niveau as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.niveauobjet.idNiveauObjet is not null order by no.parentId ")
			       .setParameter("masque", Boolean.valueOf(false))
			       .setParameter("client", idClient)
			       
			       .list());
			   }
			public void listerequi2(Integer idClient )
			   {
			     this.liste = 
			       ((ArrayList)this.session.createQuery("from Niveau as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.niveauobjet.idNiveauObjet is not null order by no.parentId, no.tri ")
			       .setParameter("masque", Boolean.valueOf(false))
			       .setParameter("client", idClient)
			       
			       .list());
			   }
			
			public void afficherbyId(Integer idClient, Integer idniveau)
			   {
			   this.model = 
			       ((Niveau)this.session.createQuery("from Niveau as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.idNiveau = :objet ")
			       .setParameter("masque", Boolean.valueOf(false))
			       .setParameter("client", idClient)
			       .setParameter("objet", idniveau)
			       .uniqueResult());
			   
			   }
			public void afficherbyIdParent(Integer idClient, Integer idniveaup)
			   {
			   this.model = 
			       ((Niveau)this.session.createQuery("from Niveau as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.idNiveau = :parent ")
			       .setParameter("masque", Boolean.valueOf(false))
			       .setParameter("client", idClient)
			       .setParameter("parent", idniveaup)
			       .uniqueResult());
			   
			   }
			public void afficherbyIdObjet(Integer idClient, Integer idObjet)
			   {
			   this.model = 
			       ((Niveau)this.session.createQuery("from Niveau as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.niveauobjet.idNiveauObjet = :objet ")
			       .setParameter("masque", Boolean.valueOf(false))
			       .setParameter("client", idClient)
			       .setParameter("objet", idObjet)
			       .uniqueResult());
			   
			   }
			
			public void afficherbyLabel(Integer idClient, String label)
			   {
			   this.model = 
			       ((Niveau)this.session.createQuery("from Niveau as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and lower( no.libelle )= :objet ")
			       .setParameter("masque", Boolean.valueOf(false))
			       .setParameter("client", idClient)
			       .setParameter("objet", label.toLowerCase())
			       .uniqueResult());
			   
			   }
			public void afficherbyLabelbat(Integer idClient, String label, Integer idniveau)
			   {
			   this.model = 
			       ((Niveau)this.session.createQuery("from Niveau as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and lower( no.libelle )= :objet and no.parentId = :parent ")
			       .setParameter("masque", Boolean.valueOf(false))
			       .setParameter("client", idClient)
			       .setParameter("objet", label.toLowerCase())
			        .setParameter("parent", idniveau)
			       .uniqueResult());
			   
			   }
			
			
 }

