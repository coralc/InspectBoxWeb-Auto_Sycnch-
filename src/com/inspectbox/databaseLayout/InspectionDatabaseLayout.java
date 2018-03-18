 package com.inspectbox.databaseLayout;
 
 import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.inspectbox.model.Inspection;
import com.inspectbox.model.Libelleniveau;
import com.inspectbox.utils.HibernateUtil;
 
 public class InspectionDatabaseLayout
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private Libelleniveau model = null;
   private ArrayList liste = null;
   private Session session = null;
  private Transaction tx = null;
  private Integer idClient = null;
  private Integer type = Integer.valueOf(1);
 
   public InspectionDatabaseLayout()
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
 
   public void save()
   {
     this.session.save(this.model);
   }
 
   public Libelleniveau model()
   {
     return this.model;
   }
 
   public List<Inspection> liste()
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
 

	

		public void listerGroupeFilter(Integer idClient, Date dateInspection, Integer idNiveauParent, boolean batiment, Integer idNiveauObjet)
   {
	 
    StringBuffer sbQuery = new StringBuffer();
     sbQuery.append("");
 		
     if (dateInspection != null)
     {
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      String strDate = dateFormat.format(dateInspection);
      sbQuery.append(" and cast(tr.dateInformation as date) = '" + strDate + "' ");
     }
 
			StringBuffer sbQueryLocal = new StringBuffer();
			if (batiment){
				sbQueryLocal.append(" and tr.niveau.niveau.niveau.idNiveau = :niveauLocal and tr.niveau.niveau.niveau.masque = tr.client.masque ");
			}
			else{
				if (idNiveauObjet>0)
					sbQueryLocal.append(" and tr.niveau.niveauobjet.idNiveauObjet = :niveauObjet and tr.niveau.niveauobjet.masque = tr.client.masque and tr.niveau.niveau.idNiveau = :niveauLocal and tr.niveau.niveau.masque = tr.client.masque ");					
				else					
					sbQueryLocal.append(" and tr.niveau.niveau.idNiveau = :niveauLocal and tr.niveau.niveau.masque = tr.client.masque ");				
			}
				
			
    Query query = this.session.createQuery("from Inspection as tr inner join fetch tr.client inner join fetch tr.utilisateur inner join fetch tr.niveau   where tr.client.idClient = :client  and tr.client.masque = :masque " + 

				sbQueryLocal+
			" and tr.niveau.client.idClient = tr.client.idClient and tr.niveau.masque = tr.client.masque "
			+" and tr.niveau.niveauobjet.client.idClient = tr.client.idClient and tr.niveau.niveauobjet.masque = tr.client.masque " +
			sbQuery + " group by tr.niveau.niveau.niveau.niveau.idNiveau, cast(tr.dateInformation as date) Order by tr.dateInformation DESC");
 
     query.setParameter("masque", Boolean.valueOf(false));
     query.setParameter("client", idClient);
			  query.setParameter("niveauLocal", idNiveauParent);
			  if (!batiment && idNiveauObjet>0)
				  query.setParameter("niveauObjet", idNiveauObjet);
     this.liste = ((ArrayList)query.list());
			
   }

			public void listerGroupe(Integer idClient, Date dateInspection)
		   {
				      StringBuffer sbQuery = new StringBuffer();
		     sbQuery.append("");
		 
		     if (dateInspection != null)
		     {
		       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		       String strDate = dateFormat.format(dateInspection);
		       sbQuery.append(" and cast(tr.dateInformation as date) = '" + strDate + "' ");
		     }
		 
		     Query query = this.session.createQuery("from Inspection as tr inner join fetch tr.client inner join fetch tr.utilisateur inner join fetch tr.niveau   where tr.client.idClient = :client  and tr.client.masque = :masque " + 
						" and tr.niveau.client.idClient = tr.client.idClient and tr.niveau.masque = tr.client.masque "
						+" and tr.niveau.niveauobjet.client.idClient = tr.client.idClient and tr.niveau.niveauobjet.masque = tr.client.masque " +
						sbQuery + " group by tr.niveau.niveau.niveau.idNiveau, cast(tr.dateInformation as date) Order by tr.dateInformation DESC");
		 
     query.setParameter("masque", Boolean.valueOf(false));
     query.setParameter("client", idClient);
     this.liste = ((ArrayList)query.list());
     System.out.println("inspection"+liste.size());			
		   }
			
public void lister(Integer idClient, String dateInspection)
			   {
     StringBuffer sbQuery = new StringBuffer();
			     sbQuery.append("");
			 
			     sbQuery.append(" and cast(tr.dateInformation as date) = '" + dateInspection + "' ");
			 
			     Query query = this.session.createQuery("from Inspection as tr inner join fetch tr.client inner join fetch tr.utilisateur inner join fetch tr.niveau   where tr.client.idClient = :client  and tr.client.masque = :masque " + 
					" and tr.niveau.client.idClient = tr.client.idClient and tr.niveau.masque = tr.client.masque "
					+" and tr.niveau.niveauobjet.client.idClient = tr.client.idClient and tr.niveau.niveauobjet.masque = tr.client.masque " +
							sbQuery);
			 
     query.setParameter("masque", Boolean.valueOf(false));
     query.setParameter("client", idClient);

 this.liste = ((ArrayList)query.list());
			   }
			
 
   

public void listerFilter(Integer idClient, String dateInspection, Integer idNiveauParent, boolean batiment, Integer idNiveauObjet)
   {
     StringBuffer sbQuery = new StringBuffer();
     sbQuery.append("");
 
    sbQuery.append(" and cast(tr.dateInformation as date) = '" + dateInspection + "' ");
 
    StringBuffer sbQueryLocal = new StringBuffer();
	if (batiment){
		sbQueryLocal.append(" and tr.niveau.niveau.niveau.idNiveau = :niveauLocal and tr.niveau.niveau.niveau.masque = tr.client.masque ");
	}
	else{
		if (idNiveauObjet>0)
			sbQueryLocal.append(" and tr.niveau.niveauobjet.idNiveauObjet = :niveauObjet and tr.niveau.niveauobjet.masque = tr.client.masque and tr.niveau.niveau.idNiveau = :niveauLocal and tr.niveau.niveau.masque = tr.client.masque ");					
		else
			sbQueryLocal.append(" and tr.niveau.niveau.idNiveau = :niveauLocal and tr.niveau.niveau.masque = tr.client.masque ");		
	}

     Query query = this.session.createQuery("from Inspection as tr inner join fetch tr.client inner join fetch tr.utilisateur inner join fetch tr.niveau   where tr.client.idClient = :client  and tr.client.masque = :masque " + 
    		 sbQueryLocal+
    		 " and tr.niveau.client.idClient = tr.client.idClient and tr.niveau.masque = tr.client.masque "
		+" and tr.niveau.niveauobjet.client.idClient = tr.client.idClient and tr.niveau.niveauobjet.masque = tr.client.masque " +
				sbQuery);
 
     	query.setParameter("masque", Boolean.valueOf(false));
     	query.setParameter("client", idClient);
     	query.setParameter("niveauLocal", idNiveauParent);
     	 if (!batiment && idNiveauObjet>0)
			  query.setParameter("niveauObjet", idNiveauObjet);
 
     this.liste = ((ArrayList)query.list());
  }

	
	
	



 }

