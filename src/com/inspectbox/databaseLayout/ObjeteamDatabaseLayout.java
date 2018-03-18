package com.inspectbox.databaseLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.inspectbox.model.Client;
import com.inspectbox.model.Objeteam;
import com.inspectbox.model.Team;
import com.inspectbox.model.Utilisateur;

import com.inspectbox.utils.HibernateUtil;

public class ObjeteamDatabaseLayout implements Serializable {
	private static final long serialVersionUID = 1L;
	   private Objeteam model = null;
	  private ArrayList liste = null;
	  private Session session = null;
	  private Transaction tx = null;
	  private Integer idClient = null;
	  private Integer idTeam=null;
	  public ObjeteamDatabaseLayout()
	  {
	    this.model = new Objeteam();
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

	  public Objeteam model()
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
	  public void setUtilisateur(Integer iduser)
	    {
	       this.idTeam = iduser;
	       Utilisateur user = (Utilisateur)this.session.createQuery("from Utilisateur where  idUtilisateur = :client")
	         .setParameter("client", iduser)
	         .uniqueResult();
	   
	       this.model.setTeam(user);
	   }
	  public List<Objeteam> liste()
	  {
	    return this.liste;
	  }
	  public ArrayList<Objeteam> getListe() {
			return this.liste;
		}
	  public void close()
	  {
	     HibernateUtil.closeSession();
	  }
	  public void listerTeam(Integer idObjet)
	  {
	    this.liste = 
	       ((ArrayList)this.session.createQuery("from Objeteam as tr  where tr.idObjet = :client  and tr.masque = :masque  ")
	       .setParameter("masque", Boolean.valueOf(false))
	        
	       .setParameter("client", idObjet)
	       .list());
	   
	  }
	  public void listerbyId(Integer idField)
	  {
		  this.model  = 
	       ((Objeteam)this.session.createQuery("from Objeteam as tr  where tr.idField = :client  and tr.masque = :masque  ")
	       .setParameter("masque", Boolean.valueOf(false))
	        
	       .setParameter("client", idField)
	       .uniqueResult());
	    
	  }
	 
	  public void listerbyteamObjet(Integer idObjet ,Integer idTeam )
	  {
		  this.model  = 
	       ((Objeteam)this.session.createQuery("from Objeteam as tr  where tr.idObjet = :objet   and tr.team.idUtilisateur = :team and  tr.masque = :masque  ")
	       .setParameter("masque", Boolean.valueOf(false))
	         .setParameter("team", idTeam)
	       .setParameter("objet", idObjet)
	       .uniqueResult());
	    
	  }
}
