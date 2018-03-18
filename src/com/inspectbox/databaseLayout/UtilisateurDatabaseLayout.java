package com.inspectbox.databaseLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.inspectbox.model.Client;
import com.inspectbox.model.Droitutilisateur;
import com.inspectbox.model.Niveau;
import com.inspectbox.model.Utilisateur;
import com.inspectbox.utils.HibernateUtil;

public class UtilisateurDatabaseLayout
  implements Serializable
{
  private static final long serialVersionUID = 1L;
   private Utilisateur model = null;
  private ArrayList liste = null;
  private Session session = null;
  private Transaction tx = null;
  private Integer idClient = null;
  private Integer idDroitUtilisateur = null;
  private Integer type = Integer.valueOf(1);

  public UtilisateurDatabaseLayout()
  {
    this.model = new Utilisateur();
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

  public Utilisateur model()
  {
   return this.model;
  }

  public List<Utilisateur> liste()
  {
    return this.liste;
  }
  public ArrayList<Utilisateur> getListe() {
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

  public void setClient(Integer idClient)
  {
     this.idClient = idClient;
    Client client = (Client)this.session.createQuery("from Client where  idClient = :client")
      .setParameter("client", idClient)
       .uniqueResult();

     this.model.setClient(client);
  }

  public void setDroitUtilisateur(Integer idDroitUtilisateur)
  {
/* 115 */     this.idDroitUtilisateur = idDroitUtilisateur;
/* 116 */     Droitutilisateur droitUtilisateur = (Droitutilisateur)this.session.createQuery("from Droitutilisateur where  idDroitUtilisateur = :droitUtilisateur")
/* 117 */       .setParameter("droitUtilisateur", idDroitUtilisateur)
/* 118 */       .uniqueResult();

/* 120 */     this.model.setDroitutilisateur(droitUtilisateur);
  }

  public void listerTerminal(Integer idClient)
  {
/* 126 */     this.liste = 
/* 131 */       ((ArrayList)this.session.createQuery("from Utilisateur as tr inner join fetch tr.client where tr.client.idClient = :client  and tr.masque = :masque and tr.client.masque = :masque and tr.droitutilisateur.terminal = :terminal")
/* 128 */       .setParameter("masque", Boolean.valueOf(false))
/* 129 */       .setParameter("client", idClient)
/* 130 */       .setParameter("terminal", Boolean.valueOf(true))
/* 131 */       .list());
  }

  public void listerAll(Integer idClient)
  {
    this.liste = 
       ((ArrayList)this.session.createQuery("from Utilisateur as tr inner join fetch tr.client  inner join fetch tr.droitutilisateur where tr.client.idClient = :client  and tr.masque = :masque and tr.client.masque = :masque order by tr.codeAcces")
       .setParameter("masque", Boolean.valueOf(false))
       .setParameter("client", idClient)
       .list());
  }
  public void listerAllAdmin()
  {
    this.liste = 
       ((ArrayList)this.session.createQuery("from Utilisateur as tr  inner join fetch tr.client where tr.masque = :masque  order by tr.client.idClient")
       .setParameter("masque", Boolean.valueOf(false))
       
       .list());
  }
  public void listerTeam(Integer idClient)
  {
    this.liste = 
       ((ArrayList)this.session.createQuery("from Utilisateur as tr inner join fetch tr.client  inner join fetch tr.droitutilisateur where tr.client.idClient = :client  and tr.masque = :masque and tr.client.masque = :masque and tr.typeUser=:type order by tr.codeAcces ")
       .setParameter("masque", Boolean.valueOf(false))
         .setParameter("type", "E")
       .setParameter("client", idClient)
       .list());
    
  }
  public void afficher( Integer idUtilisateur)
  {
    this.model = 
       ((Utilisateur)this.session.createQuery("from Utilisateur as no inner join fetch no.client inner join fetch no.droitutilisateur where no.masque = :masque    and no.idUtilisateur = :utilisateur")
      .setParameter("masque", Boolean.valueOf(false))
      
       .setParameter("utilisateur", idUtilisateur)
      .uniqueResult());
  }
  public void afficherAdmin(Integer idUtilisateur)
  {
    this.model = 
       ((Utilisateur)this.session.createQuery("from Utilisateur as no  inner join fetch no.droitutilisateur where no.masque = :masque   and no.idUtilisateur = :utilisateur")
      .setParameter("masque", Boolean.valueOf(false))
      
       .setParameter("utilisateur", idUtilisateur)
      .uniqueResult());
  }
  
  public void afficherByTeamId( Integer idUtilisateur)
  {
    this.model = 
       ((Utilisateur)this.session.createQuery("from Utilisateur as no where   no.masque = :masque and      no.idUtilisateur = :utilisateur")
      .setParameter("masque", Boolean.valueOf(false))
     
       .setParameter("utilisateur", idUtilisateur)
      .uniqueResult());
    
  }
  
  public void afficherByLibel( String nom,Integer idClient)
  {
    this.model = 
       ((Utilisateur)this.session.createQuery("from Utilisateur as no where   no.masque = :masque and  no.client.idClient = :client and  lower(no.codeAcces) = :utilisateur")
      .setParameter("masque", Boolean.valueOf(false))
     .setParameter("client", idClient)
       .setParameter("utilisateur", nom.toUpperCase())
      .uniqueResult());
    }
}

