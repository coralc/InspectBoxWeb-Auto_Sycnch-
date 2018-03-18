package com.inspectbox.databaseLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.inspectbox.model.Client;
import com.inspectbox.model.Objeteam;
import com.inspectbox.model.Statut;
import com.inspectbox.model.Team;
import com.inspectbox.utils.HibernateUtil;

public class StatusDatabaseLayout implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Statut model = null;
	  private ArrayList liste = null;
	  private Session session = null;
	  private Transaction tx = null;
	  private Integer idClient = null;
	  public StatusDatabaseLayout()
	  {
	    this.model = new Statut();
	     this.liste = new ArrayList();
	     this.session = HibernateUtil.currentSession();
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

	  public int save()
	  {
		  Integer result = (Integer)session.save(this.model);
			return result.intValue();
	  }

	  public Statut model()
	  {
	   return this.model;
	  }
	  public Integer getIdClient()
	   {
	    return this.idClient;
	    }
	  
	  
	  public void setClient(Integer idClient)
	    {
	       this.idClient = idClient;
	       Client client = (Client)this.session.createQuery("from Client where  idClient = :client")
	         .setParameter("client", idClient)
	         .uniqueResult();
	   
	       this.model.setClient(client);
	   }
	  
	  public List<Statut> liste()
	  {
	    return this.liste;
	  }
	  public ArrayList<Statut> getListe() {
			return this.liste;
		}
	  public void close()
	  {
	     HibernateUtil.closeSession();
	  }
	  public void listAll(Integer idClient)
	   {
	       this.liste = 
	         ((ArrayList)this.session.createQuery("from Statut as tr inner join fetch tr.client where tr.masque = :masque and tr.client.idClient = :client  and tr.client.masque = :masque ")
	         .setParameter("client", idClient)
	         .setParameter("masque", Boolean.valueOf(false))
	        .list());
	     }
	  public void listBYid( Integer idStatus)
	  {
	    this.model = 
	       ((Statut)this.session.createQuery("from Statut as no inner join fetch no.client where no.masque = :masque and no.idStatus =:stat and no.client.idClient = :client  and no.client.masque = :masque")
	      .setParameter("masque", Boolean.valueOf(false))
	      .setParameter("client", idClient)
	       .setParameter("stat", idStatus)
	      .uniqueResult());
	  }

}
