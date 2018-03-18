package com.inspectbox.databaseLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.inspectbox.model.Choix;
import com.inspectbox.model.Client;
import com.inspectbox.model.Niveau;
import com.inspectbox.model.Niveauobjet;
import com.inspectbox.model.Typereponse;
import com.inspectbox.utils.HibernateUtil;

public class NiveauObjetDatabaseLayout
  implements Serializable
{
  private static final long serialVersionUID = 1L;
   private Niveauobjet model = null;
   private Session session = null;
  private Transaction tx = null;
   private ArrayList liste = null;
   private Integer idTypeReponse = null;
   private Integer idClient = null;
   private Integer idParent = null;
  private Client client;

  public NiveauObjetDatabaseLayout()
  {
     this.model = new Niveauobjet();
     this.session = HibernateUtil.currentSession();
     this.liste = new ArrayList();
  }

  public Integer getIdClient()
  {
     return this.idClient;
  }

  public void transactionOpen()
  {
     this.tx = this.session.beginTransaction();
  }

  public void transactionCommit()
  {
     this.tx.commit();
  }

  public int save()
  {
	  Integer result = (Integer)session.save(this.model);
		return result.intValue();
    
  }

  public void update()
  {
     this.session.update(this.model);
  }

  public Niveauobjet model()
  {
     return this.model;
  }

  public void setModel(Niveauobjet niv)
  {
     this.model = niv;
  }

  public List<Niveauobjet> liste()
  {
    return this.liste;
  }
  public ArrayList<Niveauobjet> getListe() {
		return this.liste;
	}
  public void close()
  {
     HibernateUtil.closeSession();
  }

  public Boolean verifierDroitAjouter()
  {
     if (this.idClient == null) {
       return Boolean.valueOf(false);
    }

     if ((this.idParent != null) && 
       (!(_verifierNiveauObjetParent().booleanValue()))) {
       return Boolean.valueOf(false);
    }

     return Boolean.valueOf(true);
  }

  public Boolean verifierDroitAjouterInspection()
  {
     if (this.idClient == null) {
       return Boolean.valueOf(false);
    }

    if (!(_verifierTypeReponse().booleanValue())) {
       return Boolean.valueOf(false);
    }

     return Boolean.valueOf(true);
  }

  public Boolean verifierDroitModifier(Integer idClient, Integer idNiveauObjet)
  {
/* 123 */     this.model = 
/* 128 */       ((Niveauobjet)this.session.createQuery("from Niveauobjet as no inner join fetch no.client where no.masque = :masque and  no.client.idClient = :client and no.client.masque = :masque and no.idNiveauObjet = :niveauObjet")
/* 125 */       .setParameter("masque", Boolean.valueOf(false))
/* 126 */       .setParameter("client", idClient)
/* 127 */       .setParameter("niveauObjet", idNiveauObjet)
/* 128 */       .uniqueResult());

/* 130 */     this.idClient = idClient;
/* 131 */     return Boolean.valueOf(true);
  }

  private Boolean _verifierTypeReponse()
  {
/* 140 */     if ((this.idTypeReponse != null) && 
/* 143 */       (this.model.getTypereponse().getClient().getIdClient().equals(this.idClient))) {
/* 144 */       return Boolean.valueOf(true);
    }

/* 147 */     return Boolean.valueOf(false);
  }

  private Boolean _verifierNiveauObjetParent()
  {
/* 152 */     if ((this.idParent != null) && 
/* 155 */       (this.model.getNiveauobjet().getClient().getIdClient().equals(this.idClient))) {
/* 156 */       return Boolean.valueOf(true);
    }

/* 159 */     return Boolean.valueOf(false);
  }

  public void setClient(Integer idClient)
  {
/* 165 */     this.idClient = idClient;
/* 166 */     this.client = 
/* 168 */       ((Client)this.session.createQuery("from Client where  idClient = :client")
/* 167 */       .setParameter("client", idClient)
/* 168 */       .uniqueResult());

/* 173 */     this.model.setClient(this.client);
  }

  public void setTypeReponse(Integer idTypeReponse)
  {
/* 178 */     this.idTypeReponse = idTypeReponse;
/* 179 */     Typereponse typeReponse = (Typereponse)this.session.createQuery("from Typereponse where idTypeReponse = :idTypeReponse")
/* 180 */       .setParameter("idTypeReponse", idTypeReponse)
/* 181 */       .uniqueResult();

/* 184 */     this.model.setTypereponse(typeReponse);
  }

  public void setNiveauObjetParent(Integer idParent)
  {
/* 189 */     this.idParent = idParent;
/* 190 */     Niveauobjet objetParent = (Niveauobjet)this.session.createQuery("from Niveauobjet where idNiveauObjet = :idNiveauObjet")
/* 191 */       .setParameter("idNiveauObjet", idParent)
/* 192 */       .uniqueResult();

/* 195 */     this.model.setNiveauobjet(objetParent);
  }

  public void afficher(Integer idClient, Integer idObjet)
  {
     this.model = 
       ((Niveauobjet)this.session.createQuery("from Niveauobjet as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.idNiveauObjet = :objet")
       .setParameter("masque", Boolean.valueOf(false))
       .setParameter("client", idClient)
       .setParameter("objet", idObjet)
       .uniqueResult());
     
  }

  
  
  public void listerInitial(Integer idClient)
  {
     this.liste = 
      ((ArrayList)this.session.createQuery("from Niveauobjet as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.niveauobjet is null")
       .setParameter("masque", Boolean.valueOf(false))
      .setParameter("client", idClient)
       .list());
  }

  public void listerNotexisting(Integer idClient)
  {
     this.liste = 
      ((ArrayList)this.session.createQuery("from Niveauobjet as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.existe = :existe")
       .setParameter("masque", Boolean.valueOf(false))
      .setParameter("client", idClient)
      .setParameter("existe", Boolean.valueOf(false))
       .list());
  }
  public void listerChild(Integer idClient)
  {
     this.liste = 
      ((ArrayList)this.session.createQuery("from Niveauobjet as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.typereponse.idTypeReponse = :idTypeReponse")
       .setParameter("masque", Boolean.valueOf(false))
      .setParameter("client", idClient)
      
      
       .list());
  }
  
  public void listerAllNonInitial(Integer idClient)
  {
     this.liste = 
       ((ArrayList)this.session.createQuery("from Niveauobjet as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.niveauobjet is not null order by no.parentId")
       .setParameter("masque", Boolean.valueOf(false))
       .setParameter("client", idClient)
       .list());
  }
  public void listerAllNonInitial2(Integer idClient, Integer idparent)
  {
     this.liste = 
       ((ArrayList)this.session.createQuery("from Niveauobjet as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.parentId = :parent order by no.parentId")
       .setParameter("masque", Boolean.valueOf(false))
       .setParameter("client", idClient)
       .setParameter("parent", idparent)
       .list());
  }
  public Boolean isNoTypeReponseLink(Integer idTypeReponse)
  {
/* 231 */     return 
/* 235 */       Boolean.valueOf(this.session.createQuery("from Niveauobjet as no inner join fetch no.client where no.typereponse.idTypeReponse = :idTypeReponse  and no.client.masque = :masque and no.masque = :masque")
/* 233 */       .setParameter("masque", Boolean.valueOf(false))
/* 234 */       .setParameter("idTypeReponse", idTypeReponse)
/* 235 */       .list().isEmpty());
  }

  public void listerAll(Integer idClient)
  {
/* 221 */     this.liste = 
/* 225 */       ((ArrayList)this.session.createQuery("from Niveauobjet as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque order by tri, no.idNiveauObjet")
/* 223 */       .setParameter("masque", Boolean.valueOf(false))
/* 224 */       .setParameter("client", idClient)
/* 225 */       .list());
System.out.println("ici**"+liste.size());
  }
  public void listerAllexisting(Integer idClient)
  {
     this.liste = 
       ((ArrayList)this.session.createQuery("from Niveauobjet as no inner join fetch no.client where no.masque = :masque and no.existe = :existe and no.client.idClient = :client  and no.client.masque = :masque order by no.idNiveauObjet")
       .setParameter("masque", Boolean.valueOf(false))
      .setParameter("client", idClient)
      .setParameter("existe", Boolean.valueOf(true))
       .list());

  }
  
  public void listerByLibelle(Integer idClient,String label)
  {
     this.liste = 
       ((ArrayList)this.session.createQuery("from Niveauobjet as no inner join fetch no.client where no.masque = :masque  and no.client.idClient = :client  and no.client.masque = :masque and no.libelle = :lab order by tri, no.idNiveauObjet")
       .setParameter("masque", Boolean.valueOf(false))
      .setParameter("client", idClient)
      .setParameter("lab", label)
       .list());

  }
  
  public void listerAllchild(Integer idClient)
  {
/* 221 */     this.liste = 
/* 225 */       ((ArrayList)this.session.createQuery("from Niveauobjet as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.parentId is NOT NULL order by no.tri, no.idNiveauObjet")
/* 223 */       .setParameter("masque", Boolean.valueOf(false))
/* 224 */       .setParameter("client", idClient)
/* 225 */       .list());
  }
public void listerObjetForTree(Integer idClient)
{
  this.liste = 
    ((ArrayList)this.session.createQuery("from Niveauobjet as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.parentId = :objet ")
    .setParameter("masque", Boolean.valueOf(false))
    .setParameter("client", idClient)
    
    .list());
  System.out.println("liste tree objet"+liste.size());
}
public void afficherbyid(Integer idClient, Integer idObjet)
  {
/*  94 */     this.model = 
/*  99 */       ((Niveauobjet)this.session.createQuery("from Niveauobjet as no inner join fetch no.client  where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.idNiveauObjet = :ido")
/*  96 */       .setParameter("masque", Boolean.valueOf(false))
/*  97 */       .setParameter("client", idClient)
/*  98 */       .setParameter("ido", idObjet)
/*  99 */       .uniqueResult());
  
  
  }


public void listerbyParent(Integer idClient,Integer idP)
  {
/* 212 */     this.liste = 
/* 216 */       ((ArrayList)this.session.createQuery("from Niveauobjet as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.parentId=:parent")
/* 214 */       .setParameter("masque", Boolean.valueOf(false))
/* 215 */       .setParameter("client", idClient)
				.setParameter("parent", idP)
/* 216 */       .list());

  }
public void listerParent(Integer idClient)
{
/* 212 */     this.liste = 
/* 216 */       ((ArrayList)this.session.createQuery("from Niveauobjet as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client  and no.client.masque = :masque and no.parentId is NULL")
/* 214 */       .setParameter("masque", Boolean.valueOf(false))
/* 215 */       .setParameter("client", idClient)
				
/* 216 */       .list());

}

 }

