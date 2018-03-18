package com.inspectbox.databaseLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.inspectbox.model.Client;
import com.inspectbox.model.Utilisateur;
import com.inspectbox.utils.HibernateUtil;

public class ClientDatabaseLayout
  implements Serializable
{
  private static final long serialVersionUID = 1L;
   private Client model = null;
  private ArrayList liste = null;
  private Session session = null;
  private Transaction tx = null;
  private Integer idClient = null;
  private Integer idDroitUtilisateur = null;
  private Integer type = Integer.valueOf(1);

  public ClientDatabaseLayout()
  {
    this.model = new Client();
     this.liste = new ArrayList();
     this.session = HibernateUtil.currentSession();
  }

  public Integer getIdClient()
  {
    return this.idClient;
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

  public void update()
  {
    this.session.update(this.model);
  }

  public int save()
  {
	  Integer result = (Integer)session.save(this.model);
		return result.intValue();
  }

  public Client model()
  {
   return this.model;
  }

  public List<Client> liste()
  {
    return this.liste;
  }
  public ArrayList<Client> getListe() {
		return this.liste;
	}
  public void close()
  {
     HibernateUtil.closeSession();
  }

  public Boolean verifierDroitAjouterUtilisateur()
  {
     if (this.idClient == null) {
      return Boolean.valueOf(false);
    }

    return Boolean.valueOf(true);
  }

 

  

  public void listerAll()
  {
/* 126 */     this.liste = 
/* 131 */       ((ArrayList)this.session.createQuery("from Client as tr where tr.masque = :masque")
/* 128 */       .setParameter("masque", Boolean.valueOf(false))
/* 129 */       
/* 131 */       .list());
  }

  public void afficher( Integer idClient)
  {
    this.model = 
       ((Client)this.session.createQuery("from Client as no where no.idClient = :utilisateur")
     
      
       .setParameter("utilisateur", idClient)
      .uniqueResult());
  }
}

