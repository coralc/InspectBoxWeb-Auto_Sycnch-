package com.inspectbox.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.inspectbox.databaseLayout.TeamDatabaseLayout;
import com.inspectbox.databaseLayout.UtilisateurDatabaseLayout;
import com.inspectbox.model.Team;
import com.inspectbox.objetLayout.Objeteam;
import com.inspectbox.utils.LoginUtil;

@ManagedBean(name="teamBean")
@ViewScoped
public class TeamBean implements Serializable {
	private Integer idUtilisateur;
private String codeAcces;

	public String getCodeAcces() {
	return codeAcces;
}

public void setCodeAcces(String codeAcces) {
	this.codeAcces = codeAcces;
}

	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	
	private List<Objeteam> listeMapTeam = new ArrayList();
	   public List<Objeteam> getLister()
	   {
	     return this.listeMapTeam;
	   }
	   
	   public void afficher()
	   {    
		   System.out.println("***teambean"+this.idUtilisateur);
		   if (this.idUtilisateur == null)
	       return;
		   LoginBean bean = LoginUtil.getLoginBean();
		    UtilisateurDatabaseLayout utilisateur = null;
	   TeamDatabaseLayout teambd = null;
	     try
	     {
	    	 utilisateur = new UtilisateurDatabaseLayout();
	         utilisateur.transactionOpen();
	     
	          utilisateur.afficher(this.idUtilisateur);
	     
	         
	          this.codeAcces = utilisateur.model().getCodeAcces();
	    	 teambd = new TeamDatabaseLayout();
	         teambd.transactionOpen();
	         teambd.listerByUser(this.idUtilisateur);
	         Iterator it = teambd.liste().iterator();
	         while (it.hasNext())
	          {
	      	   Team teamReponse = (Team)it.next();
	      	   Objeteam objetTeam = new Objeteam();
	      	   objetTeam.setIdTeam(teamReponse.getIdTeam());
	      	   if(teamReponse.getDayOfWeek().equals("1"))
	      	   {
	      		   objetTeam.setDayOfWeek("Lundi");
	      	   }
	      	   if(teamReponse.getDayOfWeek().equals("2"))
	      	   {
	      		   objetTeam.setDayOfWeek("Mardi");
	      	   }
	      	   if(teamReponse.getDayOfWeek().equals("3"))
	      	   {
	      		   objetTeam.setDayOfWeek("Mercredi");
	      	   }
	      	   if(teamReponse.getDayOfWeek().equals("4"))
	      	   {
	      		   objetTeam.setDayOfWeek("Jeudi");
	      	   }
	      	   if(teamReponse.getDayOfWeek().equals("5"))
	      	   {
	      		   objetTeam.setDayOfWeek("Vendredi");
	      	   }
	      	   if(teamReponse.getDayOfWeek().equals("6"))
	      	   {
	      		   objetTeam.setDayOfWeek("Samedi");
	      	   }
	      	   if(teamReponse.getDayOfWeek().equals("0"))
	      	   {
	      		   objetTeam.setDayOfWeek("Dimanche");
	      	   }
	      	   objetTeam.setBeginHour(teamReponse.getBeginHour());
	      	   objetTeam.setEndHour(teamReponse.getEndHour());
	      	   this.listeMapTeam.add(objetTeam);
	          }
	         teambd.transactionCommit();
	       }
	       catch (Exception e) {
	        e.getMessage();
	        e.printStackTrace();
	       } 
	   }
	   public String retourback()
	   {
		   return "liste_utilisateur?faces-redirect=true";
	   }
}
