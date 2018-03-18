package com.inspectbox.databaseLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.inspectbox.model.Choix;
import com.inspectbox.model.Client;
import com.inspectbox.model.Synch;
import com.inspectbox.model.SynchPK;
import com.inspectbox.utils.HibernateUtil;

public class SynchDatabaseLayout implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6953174833101864417L;
	private Synch model = null;
	  private ArrayList liste = null;
	  private Session session = null;
	  private Transaction tx = null;
	  private SynchPK synchpk;
	  public SynchDatabaseLayout()
	  {
	    this.model = new Synch();
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

	  public void save()
	  {
		  this.session.save(this.model);
		  
	  }

	  public Synch model()
	  {
	   return this.model;
	  }
	  
	  public List<Synch> liste()
	  {
	    return this.liste;
	  }
	  public ArrayList<Synch> getListe() {
			return this.liste;
		}
	  public void close()
	  {
	     HibernateUtil.closeSession();
	  }

	  public void listByClientEtat(Integer idClient)
	   {
	       this.liste = 
	         ((ArrayList)this.session.createQuery("from Synch as tr inner join fetch tr.synchPK where tr.synchPK.idClient = :client ")
	         .setParameter("client", idClient)
	         .list());
	     }
	  
	  public void listByTerminal(String  idTerminal)
	   {
	       this.liste = 
	         ((ArrayList)this.session.createQuery("from Synch as tr inner join fetch tr.synchPK where tr.synchPK.idTerminal = :client ")
	         .setParameter("client", idTerminal)
	         .list());
	      
	     }
	  
	  public void afficher(Integer idClient, String idTerminal)
	   {
	   this.model = 
	       ((Synch)this.session.createQuery("from Synch as no inner join fetch no.synchPK  where  no.synchPK.idClient = :client  and no.synchPK.idTerminal = :term")
	      
	      .setParameter("client", idClient)
	      .setParameter("term", idTerminal)
	       .uniqueResult());

	   }
	  
	  public void setSynchPK(SynchPK synchpk)
	  {
	     

	    this.model.setSynchPK(synchpk);
	  }
}
