package com.inspectbox.databaseLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.inspectbox.model.Client;
import com.inspectbox.model.Libelleniveau;
import com.inspectbox.utils.HibernateUtil;

public class LibelleNiveauDatabaseLayout
  implements Serializable
{
  private static final long serialVersionUID = 1L;
   private Libelleniveau model = null;
   private ArrayList liste = null;
   private Session session = null;
   private Transaction tx = null;
   private Integer idClient = null;
   private Integer type = Integer.valueOf(1);
 private Client client;
  public LibelleNiveauDatabaseLayout()
  {
     this.model = new Libelleniveau();
     this.liste = new ArrayList();
     this.session = HibernateUtil.currentSession();
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

  public void update()
  {
     this.session.update(this.model);
  }

  public void update(Libelleniveau libelle)
  {
     this.session.update(libelle);
  }

  public void save()
  {
     this.session.save(this.model);
  }

  public Libelleniveau model()
  {
     return this.model;
  }

  public List<Libelleniveau> liste()
  {
     return this.liste;
  }

  public void close()
  {
     HibernateUtil.closeSession();
  }

  public Boolean verifierDroitAjouterInspection()
  {
    if (this.idClient == null) {
       return Boolean.valueOf(false);
    }

     return Boolean.valueOf(true);
  }

  public void choisirTypeNiveau()
  {
     this.type = Integer.valueOf(1);
  }

  public void choisirTypeObjet()
  {
     this.type = Integer.valueOf(2);
  }

			public void choisirTypeGamme()
			{
			     this.type = Integer.valueOf(3);
		    }
			 
public void setClient(Integer idClient)
			   {
			     this.idClient = idClient;
			    Client client = (Client)this.session.createQuery("from Client where  idClient = :client")
			       .setParameter("client", idClient)
			      .uniqueResult();
			 
			     this.model.setClient(client);
			     
			    
			   }		
			
  public void lister(Integer idClient)
  {
     this.liste = 
      ((ArrayList)this.session.createQuery("from Libelleniveau as tr inner join fetch tr.client where tr.client.idClient = :client  and tr.client.masque = :masque and tr.typeNiveau = :type ORDER BY tr.num")
      .setParameter("masque", Boolean.valueOf(false))
      .setParameter("client", idClient)
       .setParameter("type", this.type)
       .list());
  }
 
public void lister2(Integer idClient, Integer typ, Integer num)
   {
	this.model =  
       ((Libelleniveau)this.session.createQuery("from Libelleniveau as tr inner join fetch tr.client where tr.client.idClient = :client  and tr.client.masque = :masque and tr.typeNiveau = :type and tr.num = :num")
       .setParameter("masque", Boolean.valueOf(false))
       .setParameter("client", idClient)
       .setParameter("type", typ)
       .setParameter("num", num)
       .uniqueResult());
  }
}

