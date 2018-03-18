package com.inspectbox.databaseLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.inspectbox.model.Client;
import com.inspectbox.model.Droitutilisateur;
import com.inspectbox.model.Team;
import com.inspectbox.model.Utilisateur;
import com.inspectbox.utils.HibernateUtil;

public class TeamDatabaseLayout implements Serializable {
	 private static final long serialVersionUID = 1L;
	   private Team model = null;
	  private ArrayList liste = null;
	  private Session session = null;
	  private Transaction tx = null;
	  private Integer idUser = null;
	  private Integer idDroitUtilisateur = null;
	  private Integer type = Integer.valueOf(1);
	  private Integer idClient = null;
	  public TeamDatabaseLayout()
	  {
	    this.model = new Team();
	     this.liste = new ArrayList();
	     this.session = HibernateUtil.currentSession();
	  }

	 

	  public Integer getIdUser() {
		return idUser;
	}



	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}



	public Integer getIdDroitUtilisateur() {
	    return this.idDroitUtilisateur;
	  }

	  public void setIdDroitUtilisateur(Integer idDroitUtilisateur) {
	    this.idDroitUtilisateur = idDroitUtilisateur;
	  }

	  public void transactionOpen()
	  {
	    this.tx = this.session.beginTransaction();
	  }

	  public void transactionCommit()
	  {
	    this.tx.commit();
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
	  
	  public void update()
	  {
	    this.session.update(this.model);
	  }

	  public int save()
	  {
		  Integer result = (Integer)session.save(this.model);
			return result.intValue();
	  }

	  public Team model()
	  {
	   return this.model;
	  }

	  public List<Team> liste()
	  {
	    return this.liste;
	  }

	  public void close()
	  {
	     HibernateUtil.closeSession();
	  }

	  public Boolean verifierDroitAjouterUtilisateur()
	  {
	     if (this.idUser == null) {
	      return Boolean.valueOf(false);
	    }

	    return Boolean.valueOf(true);
	  }

	  public void setUser(Integer idUser)
	  {
	     this.idUser = idUser;
	    Utilisateur utilisateur = (Utilisateur)this.session.createQuery("from Utilisateur where  idUser = :user")
	      .setParameter("user", idUser)
	       .uniqueResult();

	     this.model.setIdUtilisateur(utilisateur.getIdUtilisateur());
	  }

	  public void listerByUser(Integer idUser)
	  {
	    this.liste = 
	       ((ArrayList)this.session.createQuery("from Team as tr where  tr.masque = :masque  and idUser=:user")
	    		   .setParameter("masque", Boolean.valueOf(false))
	       .setParameter("user", idUser)
	       .list());
	    System.out.println("list team"+this.liste.size());
	  }
	  
	  
	  public void listerByUserandDay( Integer idUser, String day)
	  {
	    this.model = 
	       ((Team)this.session.createQuery("from Team as no  where no.masque = :masque and idUser=:user and no.dayOfWeek=:dayw")
	      .setParameter("masque", Boolean.valueOf(false))
	      
	       .setParameter("user", idUser)
	         .setParameter("dayw", day)
	      .uniqueResult());
	  }

	  public void afficherFordel( Integer idTeam)
	  {
	    this.model = 
	       ((Team)this.session.createQuery("from Team as no  where no.masque = :masque and no.idTeam =:team")
	      .setParameter("masque", Boolean.valueOf(false))
	      
	       .setParameter("team", idTeam)
	      .uniqueResult());
	  }

}
