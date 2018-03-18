 package com.inspectbox.web;
 
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.validation.constraints.Size;

import org.primefaces.event.RowEditEvent;

import com.inspectbox.databaseLayout.ChoixDatabaseLayout;
import com.inspectbox.databaseLayout.ClientDatabaseLayout;
import com.inspectbox.databaseLayout.SynchDatabaseLayout;
import com.inspectbox.databaseLayout.TeamDatabaseLayout;
import com.inspectbox.databaseLayout.TerminalDatabaseLayout;
import com.inspectbox.databaseLayout.TypeReponseDatabaseLayout;
import com.inspectbox.databaseLayout.UtilisateurDatabaseLayout;
import com.inspectbox.model.Choix;
import com.inspectbox.model.Client;
import com.inspectbox.model.SynchPK;
import com.inspectbox.model.Team;
import com.inspectbox.model.Terminal;
import com.inspectbox.model.Typereponse;
import com.inspectbox.objetLayout.ObjetChoix;
import com.inspectbox.objetLayout.Objeteam;
import com.inspectbox.objetLayout.ObjetUtilisateur;
import com.inspectbox.utils.I18nUtil;
import com.inspectbox.utils.LoginUtil;
 
 @ManagedBean(name="utilisateurBean")
 @ViewScoped
 public class UtilisateurBean implements Serializable
 {
   private Integer idUtilisateur;
   private String beginHour;
   private String endHour;
   private String beginHournew ;
   private String endHournew;
  
   private String newDay;
   public String getNewDay() {
	return newDay;
}

public void setNewDay(String newDay) {
	this.newDay = newDay;
}





public String getBeginHournew() {
	return beginHournew;
}

public void setBeginHournew(String beginHournew) {
	this.beginHournew = beginHournew;
}

public String getEndHournew() {
	return endHournew;
}

public void setEndHournew(String endHournew) {
	this.endHournew = endHournew;
}

public String getBeginHour1() {
	return beginHour1;
}

public void setBeginHour1(String beginHour1) {
	this.beginHour1 = beginHour1;
}

public String getEndHour1() {
	return endHour1;
}

public void setEndHour1(String endHour1) {
	this.endHour1 = endHour1;
}

private String beginHour1="00:00";
private String endHour1="00:00";
private String beginHour11="00:00";
private String endHour11="00:00";
public String getBeginHour11() {
	return beginHour11;
}

public void setBeginHour11(String beginHour11) {
	this.beginHour11 = beginHour11;
}

public String getEndHour11() {
	return endHour11;
}

public void setEndHour11(String endHour11) {
	this.endHour11 = endHour11;
}

private String beginHour2="00:00";
   private String endHour2="00:00";
   private String beginHour22="00:00";
   private String endHour22="00:00";
   
   public String getBeginHour22() {
	return beginHour22;
}

public void setBeginHour22(String beginHour22) {
	this.beginHour22 = beginHour22;
}

public String getEndHour22() {
	return endHour22;
}

public void setEndHour22(String endHour22) {
	this.endHour22 = endHour22;
}

private String beginHour3="00:00";
   private String endHour3="00:00";
   private String beginHour33="00:00";
   private String endHour33="00:00";
   
   public String getBeginHour33() {
	return beginHour33;
}

public void setBeginHour33(String beginHour33) {
	this.beginHour33 = beginHour33;
}

public String getEndHour33() {
	return endHour33;
}

public void setEndHour33(String endHour33) {
	this.endHour33 = endHour33;
}

private String beginHour4="00:00";
   private String endHour4="00:00";
   private String beginHour44="00:00";
   private String endHour44="00:00";
   
   public String getBeginHour44() {
	return beginHour44;
}

public void setBeginHour44(String beginHour44) {
	this.beginHour44 = beginHour44;
}

public String getEndHour44() {
	return endHour44;
}

public void setEndHour44(String endHour44) {
	this.endHour44 = endHour44;
}

private String beginHour5="00:00";
   private String endHour5="00:00";
   private String beginHour55="00:00";
   private String endHour55="00:00";
   
   public String getBeginHour55() {
	return beginHour55;
}

public void setBeginHour55(String beginHour55) {
	this.beginHour55 = beginHour55;
}

public String getEndHour55() {
	return endHour55;
}

public void setEndHour55(String endHour55) {
	this.endHour55 = endHour55;
}

private String beginHour6="00:00";
   private String endHour6="00:00";
   private String beginHour66="00:00";
   private String endHour66="00:00";
   
   public String getBeginHour66() {
	return beginHour66;
}

public void setBeginHour66(String beginHour66) {
	this.beginHour66 = beginHour66;
}

public String getEndHour66() {
	return endHour66;
}

public void setEndHour66(String endHour66) {
	this.endHour66 = endHour66;
}

private String beginHour7="00:00";
   private String endHour7="00:00";
   private String beginHour77="00:00";
   private String endHour77="00:00";
   
   public String getBeginHour77() {
	return beginHour77;
}

public void setBeginHour77(String beginHour77) {
	this.beginHour77 = beginHour77;
}

public String getEndHour77() {
	return endHour77;
}

public void setEndHour77(String endHour77) {
	this.endHour77 = endHour77;
}

public String getBeginHour2() {
	return beginHour2;
}

public void setBeginHour2(String beginHour2) {
	this.beginHour2 = beginHour2;
}

public String getEndHour2() {
	return endHour2;
}

public void setEndHour2(String endHour2) {
	this.endHour2 = endHour2;
}

public String getBeginHour3() {
	return beginHour3;
}

public void setBeginHour3(String beginHour3) {
	this.beginHour3 = beginHour3;
}

public String getEndHour3() {
	return endHour3;
}

public void setEndHour3(String endHour3) {
	this.endHour3 = endHour3;
}

public String getBeginHour4() {
	return beginHour4;
}

public void setBeginHour4(String beginHour4) {
	this.beginHour4 = beginHour4;
}

public String getEndHour4() {
	return endHour4;
}

public void setEndHour4(String endHour4) {
	this.endHour4 = endHour4;
}

public String getBeginHour5() {
	return beginHour5;
}

public void setBeginHour5(String beginHour5) {
	this.beginHour5 = beginHour5;
}

public String getEndHour5() {
	return endHour5;
}

public void setEndHour5(String endHour5) {
	this.endHour5 = endHour5;
}

public String getBeginHour6() {
	return beginHour6;
}

public void setBeginHour6(String beginHour6) {
	this.beginHour6 = beginHour6;
}

public String getEndHour6() {
	return endHour6;
}

public void setEndHour6(String endHour6) {
	this.endHour6 = endHour6;
}

public String getBeginHour7() {
	return beginHour7;
}

public void setBeginHour7(String beginHour7) {
	this.beginHour7 = beginHour7;
}

public String getEndHour7() {
	return endHour7;
}

public void setEndHour7(String endHour7) {
	this.endHour7 = endHour7;
}

   
   

public String getBeginHour() {
	return beginHour;
}
   private Map<String,String> semaine;  
   
public Map<String, String> getSemaine() {
	return semaine;
}

public void setSemaine(Map<String, String> semaine) {
	this.semaine = semaine;
}

public void setBeginHour(String beginHour) {
	this.beginHour = beginHour;
}

public String getEndHour() {
	return endHour;
}





public void setEndHour(String endHour) {
	this.endHour = endHour;
}
private List<String> selectedays; 
public List<String> getSelectedays() {
	return selectedays;
}

public void setSelectedays(List<String> selectedays) {
	this.selectedays = selectedays;
}
private String nom;
   private String typeUser;
   
   public String getTypeUser() {
	return typeUser;
}

public void setTypeUser(String typeUser) {
	this.typeUser = typeUser;
}

private String prenom;
 
 
   private String codeAcces;
   private List<Objeteam> listeMapTeam = new ArrayList();
   public List<Objeteam> getLister()
   {
     return this.listeMapTeam;
   }
   private String motDePasse;
   private String motDePasseModif;
   private Boolean terminal;
   private Boolean lundi;
   private Boolean mardi;
   private Boolean mercredi;
   private Boolean jeudi;
   private Boolean vendredi;
   private Boolean samedi;
   private Boolean dimanche;
   private Integer client;
   private ArrayList<SelectItem> listclt = new ArrayList();
   public ArrayList<SelectItem> getListclt() {
	return listclt;
}

public void setListclt(ArrayList<SelectItem> listclt) {
	this.listclt = listclt;
}

public Integer getClient() {
	return client;
}

public void setClient(Integer client) {
	this.client = client;
}

public Boolean getMardi() {
	return mardi;
}

public void setMardi(Boolean mardi) {
	this.mardi = mardi;
}

public Boolean getMercredi() {
	return mercredi;
}

public void setMercredi(Boolean mercredi) {
	this.mercredi = mercredi;
}

public Boolean getJeudi() {
	return jeudi;
}

public void setJeudi(Boolean jeudi) {
	this.jeudi = jeudi;
}

public Boolean getVendredi() {
	return vendredi;
}

public void setVendredi(Boolean vendredi) {
	this.vendredi = vendredi;
}

public Boolean getSamedi() {
	return samedi;
}

public void setSamedi(Boolean samedi) {
	this.samedi = samedi;
}

public Boolean getDimanche() {
	return dimanche;
}

public void setDimanche(Boolean dimanche) {
	this.dimanche = dimanche;
}

public Boolean getLundi() {
	return lundi;
}

public void setLundi(Boolean lundi) {
	this.lundi = lundi;
}
private Boolean administration;
 
   public String getNom()
   {
     return this.nom;
   }
 
   public void setNom(String nom)
   {
    this.nom = nom;
   }
 
   public String getPrenom()
   {
    return this.prenom;
   }
 
   public void setPrenom(String prenom)
   {
    this.prenom = prenom;
   }
 
   public String getCodeAcces()
   {
     return this.codeAcces;
   }
 
   public void setCodeAcces(String codeAcces)
   {
     this.codeAcces = codeAcces;
   }
 
   public String getMotDePasse()
   {
    return this.motDePasse;
   }
 
   public void setMotDePasse(String motDePasse)
   {
   this.motDePasse = motDePasse;
   }
 
   public Boolean getTerminal()
   {
    return this.terminal;
   }
 
   public void setTerminal(Boolean terminal)
   {
   this.terminal = terminal;
   }
 
   public Boolean getAdministration()
   {
    return this.administration;
   }
 
   public void setAdministration(Boolean administration)
   {
    this.administration = administration;
   }
 
   public Integer getIdUtilisateur() {
    return this.idUtilisateur;
   }
 
   public void setIdUtilisateur(Integer idUtilisateur)
   {
    this.idUtilisateur = idUtilisateur;
   }
 
   public String getMotDePasseModif()
   {
     return this.motDePasseModif;
   }
 
   public void setMotDePasseModif(String motDePasseModif)
   {
     this.motDePasseModif = motDePasseModif;
   }
   public UtilisateurBean() {  
        
   }  
   private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
   public void afficheraj()
   {
	   this.codeAcces="";
	   this.motDePasse="";
   }
   public void afficher()
   {     if (this.idUtilisateur == null)
       return;
   String lg=locale.toString();
   
     LoginBean bean = LoginUtil.getLoginBean();
    UtilisateurDatabaseLayout utilisateur = null;
    TeamDatabaseLayout teambd = null;
     try
     {
    	 this.listeMapTeam.clear();
      utilisateur = new UtilisateurDatabaseLayout();
     utilisateur.transactionOpen();
 
      utilisateur.afficher( this.idUtilisateur);
 
     this.prenom = utilisateur.model().getPrenom();
       this.nom = utilisateur.model().getNom();
      this.codeAcces = utilisateur.model().getCodeAcces();
      this.typeUser=utilisateur.model().getTypeUser();
      this.terminal = Boolean.valueOf(utilisateur.model().getDroitutilisateur().isTerminal());
      this.administration = Boolean.valueOf(utilisateur.model().getDroitutilisateur().isAdminClient());
 
       utilisateur.transactionCommit();
       
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
     		   
     		   objetTeam.setDayOfWeek(I18nUtil.get("form_utilisateur_lundi"));
     		  
     	   }
     	   
     	   
     	   if(teamReponse.getDayOfWeek().equals("2"))
     	   {
     		  objetTeam.setDayOfWeek(I18nUtil.get("form_utilisateur_mardi"));
     	   }
     	   
     	   
     	   
     	   if(teamReponse.getDayOfWeek().equals("3"))
     	   {
     		  objetTeam.setDayOfWeek(I18nUtil.get("form_utilisateur_mercredi"));
     	   }
     	   
     	   
     	   
     	   if(teamReponse.getDayOfWeek().equals("4"))
     	   {
     		  objetTeam.setDayOfWeek(I18nUtil.get("form_utilisateur_jeudi"));
     	   }
     	   
     	   
     	   
     	   if(teamReponse.getDayOfWeek().equals("5"))
     	   {
     		  objetTeam.setDayOfWeek(I18nUtil.get("form_utilisateur_vendredi"));
     	   }
     	   
     	   
     	   
     	   if(teamReponse.getDayOfWeek().equals("6"))
     	   {
     		  objetTeam.setDayOfWeek(I18nUtil.get("form_utilisateur_samedi"));
     	   }
     	   
     	   
     	   if(teamReponse.getDayOfWeek().equals("0"))
     	   {
     		  objetTeam.setDayOfWeek(I18nUtil.get("form_utilisateur_dim"));
     	   }
    	   objetTeam.setBeginHour(teamReponse.getBeginHour());
    	   objetTeam.setEndHour(teamReponse.getEndHour());
    	   this.listeMapTeam.add(objetTeam);
        }
       teambd.transactionCommit();
     }
     catch (Exception e) {
      e.printStackTrace();
     } finally {
       utilisateur.close();
       //teambd.close();
     }
   }
   public String dateTohour(Date date)
   {
	   Integer convh=date.getHours();
		  Integer convm=date.getMinutes();
		  String rt=convh.toString()+convm.toString();
		  
		   return rt;
	 
	   
   }
   
   @SuppressWarnings("deprecation")
public String ajouter()
   {
	   if (typeUser==null)
	   {
		   FacesContext context = FacesContext.getCurrentInstance();  
	        
	        context.addMessage(null, new FacesMessage("typeUser must be selected")); 
	        return "ajouter_utilisateur?faces-redirect=true"; 
	   }
	  
	 
	  
	  
    LoginBean bean = LoginUtil.getLoginBean();
    UtilisateurDatabaseLayout utilisateur = null;
    TeamDatabaseLayout teambd = null;
    TeamDatabaseLayout teambdA = null;
   
    TeamDatabaseLayout teambd2 = null;
    TeamDatabaseLayout teambd2A = null;
   
    TeamDatabaseLayout teambd3 = null;
    TeamDatabaseLayout teambd3A = null;
    
    TeamDatabaseLayout teambd4 = null;
    TeamDatabaseLayout teambd4A = null;
    
    TeamDatabaseLayout teambd5 = null;
    TeamDatabaseLayout teambd5A = null;
    
    TeamDatabaseLayout teambd6 = null;
    TeamDatabaseLayout teambd6A = null;

    TeamDatabaseLayout teambd7 = null;
    TeamDatabaseLayout teambd7A = null;
    
     try
     {
    	 
      utilisateur = new UtilisateurDatabaseLayout();
      utilisateur.transactionOpen();
      teambd = new TeamDatabaseLayout();
      teambdA = new TeamDatabaseLayout();
      
      teambd2 = new TeamDatabaseLayout();
      teambd2A = new TeamDatabaseLayout();
      
      teambd3 = new TeamDatabaseLayout();
      teambd3A = new TeamDatabaseLayout();
      
      teambd4 = new TeamDatabaseLayout();
      teambd4A = new TeamDatabaseLayout();
      
      teambd5 = new TeamDatabaseLayout();
      teambd5A = new TeamDatabaseLayout();
      
      teambd6 = new TeamDatabaseLayout();
      teambd6A = new TeamDatabaseLayout();
      
      teambd7 = new TeamDatabaseLayout();
      teambd7A = new TeamDatabaseLayout();
      
      teambd.transactionOpen();
      teambdA.transactionOpen();
     
      teambd2.transactionOpen();
      teambd2A.transactionOpen();
      
      teambd3.transactionOpen();
      teambd3A.transactionOpen();
      
      teambd4.transactionOpen();
      teambd4A.transactionOpen();
      
      teambd5.transactionOpen();
      teambd5A.transactionOpen();
      
      teambd6.transactionOpen();
      teambd6A.transactionOpen();
      
      teambd7.transactionOpen();
      teambd7A.transactionOpen();
      
      if (typeUser.equals("S"))
 	 {
    	  utilisateur.setClient(bean.getIdClient());
    	     utilisateur.setDroitUtilisateur(_choixId());
    	 
    	     if (utilisateur.verifierDroitAjouterUtilisateur().booleanValue())
    	       {
    	        utilisateur.model().setPrenom(this.prenom);
    	        utilisateur.model().setNom(this.nom);
    	        utilisateur.model().setCodeAcces(this.codeAcces);
    	        utilisateur.model().setMotDePasse(LoginUtil.toSHA1(this.motDePasse.getBytes()));
    	        utilisateur.model().setTypeUser(this.typeUser);
    	        utilisateur.model().setMasque(false);
    	 
    	      utilisateur.model().setClefTimestamp(new Date());
    	        utilisateur.save();
    	       }
 		 
 	 }
      else if (typeUser.equals("E"))
      {
    	  utilisateur.setClient(bean.getIdClient());
 	     utilisateur.setDroitUtilisateur(_choixIdEq());
 	 
 	     if (utilisateur.verifierDroitAjouterUtilisateur().booleanValue())
 	       {
 	      
 	        utilisateur.model().setCodeAcces(this.codeAcces);
 	        utilisateur.model().setMotDePasse(LoginUtil.toSHA1(this.motDePasse.getBytes()));
 	        utilisateur.model().setTypeUser(this.typeUser);
 	        utilisateur.model().setMasque(false);
 	 
 	     utilisateur.model().setClefTimestamp(new Date());
 	        int iduser=utilisateur.save();
 	        if (iduser !=0)
 	        {
 	        	if (lundi)
 	        	{ 
 	        		teambd.model().setIdUtilisateur(iduser);
 	 	        	
 	 	        	System.out.println("beginhour"+this.beginHour1);
 	 	     
 	 	    		teambd.model().setBeginHour(this.beginHour1);
	 	        	teambd.model().setEndHour(this.endHour1);
	 	        	teambd.model().setDayOfWeek("1");
	 	        	teambd.model().setMasque(false);
	 	        	teambd.setClient(bean.getIdClient());
	 	        	teambd.save(); 
	 	        	if (!((this.beginHour11.equals("00:00"))&& (this.endHour11.equals("00:00"))))
	 	        			
	 	        	{
	 	        		teambdA.model().setIdUtilisateur(iduser);
	 	 	        	
	 	 	        	
	 	 	     
	 	 	    		teambdA.model().setBeginHour(this.beginHour11);
		 	        	teambdA.model().setEndHour(this.endHour11);
		 	        	teambdA.model().setDayOfWeek("1");
		 	        	teambdA.model().setMasque(false);
		 	        	teambdA.setClient(bean.getIdClient());
		 	        	teambdA.save();
	 	        		
	 	        	}
 	 	    	 
 	 	        	
 	 	        	 
 	        	}
 	        	if (mardi)
 	        	{
 	        		teambd2.model().setIdUtilisateur(iduser);
 	        		
 	 	        	teambd2.model().setBeginHour(this.beginHour2);
 	 	        	teambd2.model().setEndHour(this.endHour2);
 	 	        	teambd2.model().setDayOfWeek("2");
 	 	        	teambd2.model().setMasque(false);
 	 	        	teambd2.setClient(bean.getIdClient());
 	 	        	teambd2.save();
 	 	        	
 	 	        	if (!((this.beginHour22.equals("00:00"))&& (this.endHour22.equals("00:00"))))
 	        			
	 	        	{
	 	        		teambd2A.model().setIdUtilisateur(iduser);
	 	 	        	teambd2A.model().setBeginHour(this.beginHour22);
		 	        	teambd2A.model().setEndHour(this.endHour22);
		 	        	teambd2A.model().setDayOfWeek("2");
		 	        	teambd2A.model().setMasque(false);
		 	        	teambd2A.setClient(bean.getIdClient());
		 	        	teambd2A.save();
	 	        		
	 	        	}
 	 	 	    	  
 	 	        	
 	        	}
 	        	if (mercredi)
 	        	{
 	        		teambd3.model().setIdUtilisateur(iduser);
 	        		 
 	 	        	teambd3.model().setBeginHour(this.beginHour3);
 	 	        	teambd3.model().setEndHour(this.endHour3);
 	 	        	teambd3.model().setDayOfWeek("3");
 	 	        	teambd3.model().setMasque(false);
 	 	        	teambd3.setClient(bean.getIdClient());
 	 	        	teambd3.save();
 	 	        	
if (!((this.beginHour33.equals("00:00"))&& (this.endHour33.equals("00:00"))))
 	        			
	 	        	{
	 	        		teambd3A.model().setIdUtilisateur(iduser);
	 	 	        	teambd3A.model().setBeginHour(this.beginHour33);
		 	        	teambd3A.model().setEndHour(this.endHour33);
		 	        	teambd3A.model().setDayOfWeek("3");
		 	        	teambd3A.model().setMasque(false);
		 	        	teambd3A.setClient(bean.getIdClient());
		 	        	teambd3A.save();
	 	        		
	 	        	}
  	 	 	    	
 	        	}
 	        	if (jeudi)
 	        	{
 	        		
 	        		teambd4.model().setIdUtilisateur(iduser);
 	        		 
 	        		
 	 	        	teambd4.model().setBeginHour(this.beginHour4);
 	 	        	teambd4.model().setEndHour(this.endHour4);
 	 	        	teambd4.model().setDayOfWeek("4");
 	 	        	teambd4.model().setMasque(false);
 	 	        	teambd4.setClient(bean.getIdClient());
 	 	        	teambd4.save();
 	 	        	
if (!((this.beginHour44.equals("00:00"))&& (this.endHour44.equals("00:00"))))
 	        			
	 	        	{
	 	        		teambd4A.model().setIdUtilisateur(iduser);
	 	 	        	teambd4A.model().setBeginHour(this.beginHour44);
		 	        	teambd4A.model().setEndHour(this.endHour44);
		 	        	teambd4A.model().setDayOfWeek("4");
		 	        	teambd4A.model().setMasque(false);
		 	        	teambd4A.setClient(bean.getIdClient());
		 	        	teambd4A.save();
	 	        		
	 	        	}
   	 	 	    	
 	        	}
 	        	if (vendredi)
 	        	{
 	        		teambd5.model().setIdUtilisateur(iduser);
 	        		
 	 	        	teambd5.model().setBeginHour(this.beginHour5);
 	 	        	teambd5.model().setEndHour(this.endHour5);
 	 	        	teambd5.model().setDayOfWeek("5");
 	 	        	teambd5.model().setMasque(false);
 	 	        	teambd5.setClient(bean.getIdClient());
 	 	        	teambd5.save();
 	 	        	
if (!((this.beginHour55.equals("00:00"))&& (this.endHour55.equals("00:00"))))
 	        			
	 	        	{
	 	        		teambd5A.model().setIdUtilisateur(iduser);
	 	 	        	teambd5A.model().setBeginHour(this.beginHour55);
		 	        	teambd5A.model().setEndHour(this.endHour55);
		 	        	teambd5A.model().setDayOfWeek("5");
		 	        	teambd5A.model().setMasque(false);
		 	        	teambd5A.setClient(bean.getIdClient());
		 	        	teambd5A.save();
	 	        		
	 	        	}
   	 	 	    	
 	        	}
 	        	
 	        	
 	        	if (samedi)
 	        	{
 	        		teambd6.model().setIdUtilisateur(iduser);
 	        		
 	 	        	teambd6.model().setBeginHour(this.beginHour6);
 	 	        	teambd6.model().setEndHour(this.endHour6);
 	 	        	teambd6.model().setDayOfWeek("6");
 	 	        	teambd6.model().setMasque(false);
 	 	        	teambd6.setClient(bean.getIdClient());
 	 	        	teambd6.save();
if (!((this.beginHour66.equals("00:00"))&& (this.endHour66.equals("00:00"))))
 	        			
	 	        	{
	 	        		teambd6A.model().setIdUtilisateur(iduser);
	 	 	        	teambd6A.model().setBeginHour(this.beginHour66);
		 	        	teambd6A.model().setEndHour(this.endHour66);
		 	        	teambd6A.model().setDayOfWeek("6");
		 	        	teambd6A.model().setMasque(false);
		 	        	teambd6A.setClient(bean.getIdClient());
		 	        	teambd6A.save();
	 	        		
	 	        	}
 	        	}
 	        	
 	        	if (dimanche)
 	        	{
 	        		teambd7.model().setIdUtilisateur(iduser);
 	        		
 	 	        	teambd7.model().setBeginHour(this.beginHour7);
 	 	        	teambd7.model().setEndHour(this.endHour7);
 	 	        	teambd7.model().setDayOfWeek("0");
 	 	        	teambd7.model().setMasque(false);
 	 	        	teambd7.setClient(bean.getIdClient());
 	 	        	teambd7.save();
   	 	 	    	
if (!((this.beginHour77.equals("00:00"))&& (this.endHour77.equals("00:00"))))
 	        			
	 	        	{
	 	        		teambd7A.model().setIdUtilisateur(iduser);
	 	 	        	teambd7A.model().setBeginHour(this.beginHour77);
		 	        	teambd7A.model().setEndHour(this.endHour77);
		 	        	teambd7A.model().setDayOfWeek("0");
		 	        	teambd7A.model().setMasque(false);
		 	        	teambd7A.setClient(bean.getIdClient());
		 	        	teambd7A.save();
	 	        		
	 	        	}
 	        	}
 	        	
 	        }
 	        
 	       }
      }
      
      utilisateur.transactionCommit();
      teambd.transactionCommit();
      teambdA.transactionCommit();
    	
    	 teambd2.transactionCommit();
    	 teambd2A.transactionCommit();
     	
     	 teambd3.transactionCommit();
     	teambd3A.transactionCommit();
     	
     	 teambd4.transactionCommit();
     	teambd4A.transactionCommit();
     	
     	 teambd5.transactionCommit();
     	teambd5A.transactionCommit();
     	
     	 teambd6.transactionCommit();
     	teambd6A.transactionCommit();
     	
     	 teambd7.transactionCommit();
     	teambd7A.transactionCommit();
     	

     // save synchro 
     TerminalDatabaseLayout termDB= new TerminalDatabaseLayout();
     	          termDB.transactionOpen();
     	           

     	          
     	          termDB.listerAll();
     	          Iterator it = termDB.liste().iterator();
     	          while (it.hasNext())
     		        {
     		          Terminal term = (Terminal)it.next();
     		           SynchDatabaseLayout synchdb = null;
     			 		synchdb = new SynchDatabaseLayout();
     			 		synchdb.transactionOpen();
     		          synchdb.afficher(bean.getIdClient(), term.getIdTerminal());
     		          if (synchdb.model()!=null)//MAJ
     		          { if (synchdb.model().getEtat()==0)
     		          {
     		        	  synchdb.model().setEtat(1);
     		        	  synchdb.model().setDateInformation(new Date());
     		        	  synchdb.update();
     		        	  synchdb.transactionCommit();
     		          }
     		        	  
     		          }
     		          else // creation
     		          {
     		        	  SynchDatabaseLayout synchdbnw = null;
     		        	  synchdbnw = new SynchDatabaseLayout();
     		        	  synchdbnw.transactionOpen();
     		        	  SynchPK synchpk=new SynchPK();
     		        	  synchpk.setIdClient(bean.getIdClient());
     		        	  synchpk.setIdTerminal(term.getIdTerminal());
     		        	  synchdbnw.model().setEtat(1);
     		        	  synchdbnw.model().setDateInformation(new Date());
     		        	  synchdbnw.model().setSynchPK(synchpk);
     		        	  synchdbnw.save();
     		        	  synchdbnw.transactionCommit();
     		          }
     		          
     		        } 
     	     termDB.close();     
     }
     catch (Exception e) {
    	 e.printStackTrace();
       e.getMessage();
     } finally {
       utilisateur.close();
       teambd.close();
       teambd2.close();
       teambd3.close();
       teambd4.close();
       teambd5.close();
       teambd6.close();
   	teambd7.close();
    teambdA.close();
    teambd2A.close();
    teambd3A.close();
    teambd4A.close();
    teambd5A.close();
    teambd6A.close();
	teambd7A.close();
     }
 
    return "liste_utilisateur?faces-redirect=true";
   }
   public String ajouterAdmin()
   {
	   if (typeUser==null)
	   {
		   FacesContext context = FacesContext.getCurrentInstance();  
	        
	        context.addMessage(null, new FacesMessage("typeUser must be selected")); 
	        return "ajouter_utilisateur?faces-redirect=true"; 
	   }
	  
	 
	  
	  
    
    UtilisateurDatabaseLayout utilisateur = null;
    TeamDatabaseLayout teambd = null;
    TeamDatabaseLayout teambdA = null;
   
    TeamDatabaseLayout teambd2 = null;
    TeamDatabaseLayout teambd2A = null;
   
    TeamDatabaseLayout teambd3 = null;
    TeamDatabaseLayout teambd3A = null;
    
    TeamDatabaseLayout teambd4 = null;
    TeamDatabaseLayout teambd4A = null;
    
    TeamDatabaseLayout teambd5 = null;
    TeamDatabaseLayout teambd5A = null;
    
    TeamDatabaseLayout teambd6 = null;
    TeamDatabaseLayout teambd6A = null;

    TeamDatabaseLayout teambd7 = null;
    TeamDatabaseLayout teambd7A = null;
    
     try
     {
    	 
      utilisateur = new UtilisateurDatabaseLayout();
      utilisateur.transactionOpen();
      teambd = new TeamDatabaseLayout();
      teambdA = new TeamDatabaseLayout();
      
      teambd2 = new TeamDatabaseLayout();
      teambd2A = new TeamDatabaseLayout();
      
      teambd3 = new TeamDatabaseLayout();
      teambd3A = new TeamDatabaseLayout();
      
      teambd4 = new TeamDatabaseLayout();
      teambd4A = new TeamDatabaseLayout();
      
      teambd5 = new TeamDatabaseLayout();
      teambd5A = new TeamDatabaseLayout();
      
      teambd6 = new TeamDatabaseLayout();
      teambd6A = new TeamDatabaseLayout();
      
      teambd7 = new TeamDatabaseLayout();
      teambd7A = new TeamDatabaseLayout();
      
      teambd.transactionOpen();
      teambdA.transactionOpen();
     
      teambd2.transactionOpen();
      teambd2A.transactionOpen();
      
      teambd3.transactionOpen();
      teambd3A.transactionOpen();
      
      teambd4.transactionOpen();
      teambd4A.transactionOpen();
      
      teambd5.transactionOpen();
      teambd5A.transactionOpen();
      
      teambd6.transactionOpen();
      teambd6A.transactionOpen();
      
      teambd7.transactionOpen();
      teambd7A.transactionOpen();
      
      if (typeUser.equals("S"))
 	 {
    	  utilisateur.setClient(this.client);
    	     utilisateur.setDroitUtilisateur(_choixId());
    	 
    	     if (utilisateur.verifierDroitAjouterUtilisateur().booleanValue())
    	       {
    	        utilisateur.model().setPrenom(this.prenom);
    	        utilisateur.model().setNom(this.nom);
    	        utilisateur.model().setCodeAcces(this.codeAcces);
    	        utilisateur.model().setMotDePasse(LoginUtil.toSHA1(this.motDePasse.getBytes()));
    	        utilisateur.model().setTypeUser(this.typeUser);
    	        utilisateur.model().setMasque(false);
    	 
    	      utilisateur.model().setClefTimestamp(new Date());
    	        utilisateur.save();
    	       }
 		 
 	 }
      else if (typeUser.equals("E"))
      {
    	  utilisateur.setClient(this.client);
 	     utilisateur.setDroitUtilisateur(_choixIdEq());
 	 
 	     if (utilisateur.verifierDroitAjouterUtilisateur().booleanValue())
 	       {
 	      
 	        utilisateur.model().setCodeAcces(this.codeAcces);
 	        utilisateur.model().setMotDePasse(LoginUtil.toSHA1(this.motDePasse.getBytes()));
 	        utilisateur.model().setTypeUser(this.typeUser);
 	        utilisateur.model().setMasque(false);
 	 
 	     utilisateur.model().setClefTimestamp(new Date());
 	        int iduser=utilisateur.save();
 	        if (iduser !=0)
 	        {
 	        	if (lundi)
 	        	{ 
 	        		teambd.model().setIdUtilisateur(iduser);
 	 	        	
 	 	        	System.out.println("beginhour"+this.beginHour1);
 	 	     
 	 	    		teambd.model().setBeginHour(this.beginHour1);
	 	        	teambd.model().setEndHour(this.endHour1);
	 	        	teambd.model().setDayOfWeek("1");
	 	        	teambd.model().setMasque(false);
	 	        	teambd.setClient(this.client);
	 	        	teambd.save(); 
	 	        	if (!((this.beginHour11.equals("00:00"))&& (this.endHour11.equals("00:00"))))
	 	        			
	 	        	{
	 	        		teambdA.model().setIdUtilisateur(iduser);
	 	 	        	
	 	 	        	
	 	 	     
	 	 	    		teambdA.model().setBeginHour(this.beginHour11);
		 	        	teambdA.model().setEndHour(this.endHour11);
		 	        	teambdA.model().setDayOfWeek("1");
		 	        	teambdA.model().setMasque(false);
		 	        	teambdA.setClient(this.client);
		 	        	teambdA.save();
	 	        		
	 	        	}
 	 	    	 
 	 	        	
 	 	        	 
 	        	}
 	        	if (mardi)
 	        	{
 	        		teambd2.model().setIdUtilisateur(iduser);
 	        		
 	 	        	teambd2.model().setBeginHour(this.beginHour2);
 	 	        	teambd2.model().setEndHour(this.endHour2);
 	 	        	teambd2.model().setDayOfWeek("2");
 	 	        	teambd2.model().setMasque(false);
 	 	        	teambd2.setClient(this.client);
 	 	        	teambd2.save();
 	 	        	
 	 	        	if (!((this.beginHour22.equals("00:00"))&& (this.endHour22.equals("00:00"))))
 	        			
	 	        	{
	 	        		teambd2A.model().setIdUtilisateur(iduser);
	 	 	        	teambd2A.model().setBeginHour(this.beginHour22);
		 	        	teambd2A.model().setEndHour(this.endHour22);
		 	        	teambd2A.model().setDayOfWeek("2");
		 	        	teambd2A.model().setMasque(false);
		 	        	teambd2A.setClient(this.client);
		 	        	teambd2A.save();
	 	        		
	 	        	}
 	 	 	    	  
 	 	        	
 	        	}
 	        	if (mercredi)
 	        	{
 	        		teambd3.model().setIdUtilisateur(iduser);
 	        		 
 	 	        	teambd3.model().setBeginHour(this.beginHour3);
 	 	        	teambd3.model().setEndHour(this.endHour3);
 	 	        	teambd3.model().setDayOfWeek("3");
 	 	        	teambd3.model().setMasque(false);
 	 	        	teambd3.setClient(this.client);
 	 	        	teambd3.save();
 	 	        	
if (!((this.beginHour33.equals("00:00"))&& (this.endHour33.equals("00:00"))))
 	        			
	 	        	{
	 	        		teambd3A.model().setIdUtilisateur(iduser);
	 	 	        	teambd3A.model().setBeginHour(this.beginHour33);
		 	        	teambd3A.model().setEndHour(this.endHour33);
		 	        	teambd3A.model().setDayOfWeek("3");
		 	        	teambd3A.model().setMasque(false);
		 	        	teambd3A.setClient(this.client);
		 	        	teambd3A.save();
	 	        		
	 	        	}
  	 	 	    	
 	        	}
 	        	if (jeudi)
 	        	{
 	        		
 	        		teambd4.model().setIdUtilisateur(iduser);
 	        		 
 	        		
 	 	        	teambd4.model().setBeginHour(this.beginHour4);
 	 	        	teambd4.model().setEndHour(this.endHour4);
 	 	        	teambd4.model().setDayOfWeek("4");
 	 	        	teambd4.model().setMasque(false);
 	 	        	teambd4.setClient(this.client);
 	 	        	teambd4.save();
 	 	        	
if (!((this.beginHour44.equals("00:00"))&& (this.endHour44.equals("00:00"))))
 	        			
	 	        	{
	 	        		teambd4A.model().setIdUtilisateur(iduser);
	 	 	        	teambd4A.model().setBeginHour(this.beginHour44);
		 	        	teambd4A.model().setEndHour(this.endHour44);
		 	        	teambd4A.model().setDayOfWeek("4");
		 	        	teambd4A.model().setMasque(false);
		 	        	teambd4A.setClient(this.client);
		 	        	teambd4A.save();
	 	        		
	 	        	}
   	 	 	    	
 	        	}
 	        	if (vendredi)
 	        	{
 	        		teambd5.model().setIdUtilisateur(iduser);
 	        		
 	 	        	teambd5.model().setBeginHour(this.beginHour5);
 	 	        	teambd5.model().setEndHour(this.endHour5);
 	 	        	teambd5.model().setDayOfWeek("5");
 	 	        	teambd5.model().setMasque(false);
 	 	        	teambd5.setClient(this.client);
 	 	        	teambd5.save();
 	 	        	
if (!((this.beginHour55.equals("00:00"))&& (this.endHour55.equals("00:00"))))
 	        			
	 	        	{
	 	        		teambd5A.model().setIdUtilisateur(iduser);
	 	 	        	teambd5A.model().setBeginHour(this.beginHour55);
		 	        	teambd5A.model().setEndHour(this.endHour55);
		 	        	teambd5A.model().setDayOfWeek("5");
		 	        	teambd5A.model().setMasque(false);
		 	        	teambd5A.setClient(this.client);
		 	        	teambd5A.save();
	 	        		
	 	        	}
   	 	 	    	
 	        	}
 	        	
 	        	
 	        	if (samedi)
 	        	{
 	        		teambd6.model().setIdUtilisateur(iduser);
 	        		
 	 	        	teambd6.model().setBeginHour(this.beginHour6);
 	 	        	teambd6.model().setEndHour(this.endHour6);
 	 	        	teambd6.model().setDayOfWeek("6");
 	 	        	teambd6.model().setMasque(false);
 	 	        	teambd6.setClient(this.client);
 	 	        	teambd6.save();
if (!((this.beginHour66.equals("00:00"))&& (this.endHour66.equals("00:00"))))
 	        			
	 	        	{
	 	        		teambd6A.model().setIdUtilisateur(iduser);
	 	 	        	teambd6A.model().setBeginHour(this.beginHour66);
		 	        	teambd6A.model().setEndHour(this.endHour66);
		 	        	teambd6A.model().setDayOfWeek("6");
		 	        	teambd6A.model().setMasque(false);
		 	        	teambd6A.setClient(this.client);
		 	        	teambd6A.save();
	 	        		
	 	        	}
 	        	}
 	        	
 	        	if (dimanche)
 	        	{
 	        		teambd7.model().setIdUtilisateur(iduser);
 	        		
 	 	        	teambd7.model().setBeginHour(this.beginHour7);
 	 	        	teambd7.model().setEndHour(this.endHour7);
 	 	        	teambd7.model().setDayOfWeek("0");
 	 	        	teambd7.model().setMasque(false);
 	 	        	teambd7.setClient(this.client);
 	 	        	teambd7.save();
   	 	 	    	
if (!((this.beginHour77.equals("00:00"))&& (this.endHour77.equals("00:00"))))
 	        			
	 	        	{
	 	        		teambd7A.model().setIdUtilisateur(iduser);
	 	 	        	teambd7A.model().setBeginHour(this.beginHour77);
		 	        	teambd7A.model().setEndHour(this.endHour77);
		 	        	teambd7A.model().setDayOfWeek("0");
		 	        	teambd7A.model().setMasque(false);
		 	        	teambd7A.setClient(this.client);
		 	        	teambd7A.save();
	 	        		
	 	        	}
 	        	}
 	        	
 	        }
 	        
 	       }
      }
      
      utilisateur.transactionCommit();
      teambd.transactionCommit();
      teambdA.transactionCommit();
    	
    	 teambd2.transactionCommit();
    	 teambd2A.transactionCommit();
     	
     	 teambd3.transactionCommit();
     	teambd3A.transactionCommit();
     	
     	 teambd4.transactionCommit();
     	teambd4A.transactionCommit();
     	
     	 teambd5.transactionCommit();
     	teambd5A.transactionCommit();
     	
     	 teambd6.transactionCommit();
     	teambd6A.transactionCommit();
     	
     	 teambd7.transactionCommit();
     	teambd7A.transactionCommit();

     // save synchro 
     	 LoginBean bean = LoginUtil.getLoginBean();
     TerminalDatabaseLayout termDB= new TerminalDatabaseLayout();
     	          termDB.transactionOpen();
     	           

     	          
     	          termDB.listerAll();
     	          Iterator it = termDB.liste().iterator();
     	          while (it.hasNext())
     		        {
     		          Terminal term = (Terminal)it.next();
     		           SynchDatabaseLayout synchdb = null;
     			 		synchdb = new SynchDatabaseLayout();
     			 		synchdb.transactionOpen();
     		          synchdb.afficher(bean.getIdClient(), term.getIdTerminal());
     		          if (synchdb.model()!=null)//MAJ
     		          { if (synchdb.model().getEtat()==0)
     		          {
     		        	  synchdb.model().setEtat(1);
     		        	  synchdb.model().setDateInformation(new Date());
     		        	  synchdb.update();
     		        	  synchdb.transactionCommit();
     		          }
     		        	  
     		          }
     		          else // creation
     		          {
     		        	  SynchDatabaseLayout synchdbnw = null;
     		        	  synchdbnw = new SynchDatabaseLayout();
     		        	  synchdbnw.transactionOpen();
     		        	  SynchPK synchpk=new SynchPK();
     		        	  synchpk.setIdClient(bean.getIdClient());
     		        	  synchpk.setIdTerminal(term.getIdTerminal());
     		        	  synchdbnw.model().setEtat(1);
     		        	  synchdbnw.model().setDateInformation(new Date());
     		        	  synchdbnw.model().setSynchPK(synchpk);
     		        	  synchdbnw.save();
     		        	  synchdbnw.transactionCommit();
     		          }
     		          
     		        } 
     	     termDB.close();     
     }
     catch (Exception e) {
    	 e.printStackTrace();
       e.getMessage();
     } finally {
       utilisateur.close();
       teambd.close();
       teambd2.close();
       teambd3.close();
       teambd4.close();
       teambd5.close();
       teambd6.close();
   	teambd7.close();
    teambdA.close();
    teambd2A.close();
    teambd3A.close();
    teambd4A.close();
    teambd5A.close();
    teambd6A.close();
	teambd7A.close();
     }
 
    return "liste_utilisateuradmin?faces-redirect=true";
   }
   public String modifierAdmin()
   {
     LoginBean bean = LoginUtil.getLoginBean();
     UtilisateurDatabaseLayout utilisateur = null;
     try
     {
       utilisateur = new UtilisateurDatabaseLayout();
      utilisateur.transactionOpen();
 
      utilisateur.afficher( this.idUtilisateur);
 if (this.typeUser.equals("S"))
 {
      utilisateur.model().setPrenom(this.prenom);
       utilisateur.model().setNom(this.nom);
      utilisateur.model().setCodeAcces(this.codeAcces);
       if ((((this.motDePasseModif != null) ? 1 : 0) & ((this.motDePasseModif != "") ? 1 : 0)) != 0)
         utilisateur.model().setMotDePasse(LoginUtil.toSHA1(this.motDePasseModif.getBytes()));
       utilisateur.setDroitUtilisateur(_choixId());
 
      utilisateur.model().setClefTimestamp(new Date());
 
       utilisateur.update();
       if (_choixId()!=3)
       bean.setIdacce(false);
       else
    	   bean.setIdacce(true);
 }
 else
 {
	 utilisateur.model().setCodeAcces(this.codeAcces);
	 if ((((this.motDePasseModif != null) ? 1 : 0) & ((this.motDePasseModif != "") ? 1 : 0)) != 0)
         utilisateur.model().setMotDePasse(LoginUtil.toSHA1(this.motDePasseModif.getBytes()));
      utilisateur.setDroitUtilisateur(_choixIdEq());
      utilisateur.model().setClefTimestamp(new Date());
      
      utilisateur.update();
 }
      utilisateur.transactionCommit();
      

   // save synchro 
   TerminalDatabaseLayout termDB= new TerminalDatabaseLayout();
   	          termDB.transactionOpen();
   	           

   	          
   	          termDB.listerAll();
   	          Iterator it = termDB.liste().iterator();
   	          while (it.hasNext())
   		        {
   		          Terminal term = (Terminal)it.next();
   		           SynchDatabaseLayout synchdb = null;
   			 		synchdb = new SynchDatabaseLayout();
   			 		synchdb.transactionOpen();
   		          synchdb.afficher(bean.getIdClient(), term.getIdTerminal());
   		          if (synchdb.model()!=null)//MAJ
   		          { if (synchdb.model().getEtat()==0)
   		          {
   		        	  synchdb.model().setEtat(1);
   		        	  synchdb.model().setDateInformation(new Date());
   		        	  synchdb.update();
   		        	  synchdb.transactionCommit();
   		          }
   		        	  
   		          }
   		          else // creation
   		          {
   		        	  SynchDatabaseLayout synchdbnw = null;
   		        	  synchdbnw = new SynchDatabaseLayout();
   		        	  synchdbnw.transactionOpen();
   		        	  SynchPK synchpk=new SynchPK();
   		        	  synchpk.setIdClient(bean.getIdClient());
   		        	  synchpk.setIdTerminal(term.getIdTerminal());
   		        	  synchdbnw.model().setEtat(1);
   		        	  synchdbnw.model().setDateInformation(new Date());
   		        	  synchdbnw.model().setSynchPK(synchpk);
   		        	  synchdbnw.save();
   		        	  synchdbnw.transactionCommit();
   		          }
   		          
   		        } 
   	     termDB.close();     
     }
     catch (Exception e) {
    	 e.printStackTrace();
      
     } finally {
       utilisateur.close();
     }
 
     return "liste_utilisateuradmin?faces-redirect=true";
   }
   public String modifier()
   {
     LoginBean bean = LoginUtil.getLoginBean();
     UtilisateurDatabaseLayout utilisateur = null;
     try
     {
       utilisateur = new UtilisateurDatabaseLayout();
      utilisateur.transactionOpen();
 
      utilisateur.afficher( this.idUtilisateur);
 if (this.typeUser.equals("S"))
 {
      utilisateur.model().setPrenom(this.prenom);
       utilisateur.model().setNom(this.nom);
      utilisateur.model().setCodeAcces(this.codeAcces);
       if ((((this.motDePasseModif != null) ? 1 : 0) & ((this.motDePasseModif != "") ? 1 : 0)) != 0)
         utilisateur.model().setMotDePasse(LoginUtil.toSHA1(this.motDePasseModif.getBytes()));
       utilisateur.setDroitUtilisateur(_choixId());
 
      utilisateur.model().setClefTimestamp(new Date());
 
       utilisateur.update();
       if (_choixId()!=3)
       bean.setIdacce(false);
       else
    	   bean.setIdacce(true);
 }
 else
 {
	 utilisateur.model().setCodeAcces(this.codeAcces);
	 if ((((this.motDePasseModif != null) ? 1 : 0) & ((this.motDePasseModif != "") ? 1 : 0)) != 0)
         utilisateur.model().setMotDePasse(LoginUtil.toSHA1(this.motDePasseModif.getBytes()));
      utilisateur.setDroitUtilisateur(_choixIdEq());
      utilisateur.model().setClefTimestamp(new Date());
      
      utilisateur.update();
 }
      utilisateur.transactionCommit();
      

   // save synchro 
   TerminalDatabaseLayout termDB= new TerminalDatabaseLayout();
   	          termDB.transactionOpen();
   	           

   	          
   	          termDB.listerAll();
   	          Iterator it = termDB.liste().iterator();
   	          while (it.hasNext())
   		        {
   		          Terminal term = (Terminal)it.next();
   		           SynchDatabaseLayout synchdb = null;
   			 		synchdb = new SynchDatabaseLayout();
   			 		synchdb.transactionOpen();
   		          synchdb.afficher(bean.getIdClient(), term.getIdTerminal());
   		          if (synchdb.model()!=null)//MAJ
   		          { if (synchdb.model().getEtat()==0)
   		          {
   		        	  synchdb.model().setEtat(1);
   		        	  synchdb.model().setDateInformation(new Date());
   		        	  synchdb.update();
   		        	  synchdb.transactionCommit();
   		          }
   		        	  
   		          }
   		          else // creation
   		          {
   		        	  SynchDatabaseLayout synchdbnw = null;
   		        	  synchdbnw = new SynchDatabaseLayout();
   		        	  synchdbnw.transactionOpen();
   		        	  SynchPK synchpk=new SynchPK();
   		        	  synchpk.setIdClient(bean.getIdClient());
   		        	  synchpk.setIdTerminal(term.getIdTerminal());
   		        	  synchdbnw.model().setEtat(1);
   		        	  synchdbnw.model().setDateInformation(new Date());
   		        	  synchdbnw.model().setSynchPK(synchpk);
   		        	  synchdbnw.save();
   		        	  synchdbnw.transactionCommit();
   		          }
   		          
   		        } 
   	     termDB.close();     
     }
     catch (Exception e) {
    	 e.printStackTrace();
     
     } finally {
       utilisateur.close();
     }
 
     return "liste_utilisateur?faces-redirect=true";
   }
 
   public String supprimer(ObjetUtilisateur objetUtilisateur)
   {
	   System.out.println("supp here ");
    LoginBean bean = LoginUtil.getLoginBean();
     UtilisateurDatabaseLayout utilisateur = null;
     TeamDatabaseLayout teamdb = null;
     try
     {
    	 teamdb=new TeamDatabaseLayout();
    	 teamdb.transactionOpen();
    	 teamdb.listerByUser(objetUtilisateur.getIdUtilisateur());
    	 /*
      utilisateur = new UtilisateurDatabaseLayout();
       utilisateur.transactionOpen();
 
      utilisateur.afficher(bean.getIdClient(), this.idUtilisateur);
 
      utilisateur.model().setMasque(true);
       utilisateur.model().setClefTimestamp(new Date());
 
      utilisateur.update();
 
      utilisateur.transactionCommit();*/
     }
     catch (Exception e) {
    	 e.printStackTrace();
      e.getMessage();
     } finally {
      utilisateur.close();
      teamdb.close();
     }
 
     return "liste_utilisateur?faces-redirect=true";
   }
   public String retourback()
   {
	   return "liste_utilisateur?faces-redirect=true";
   }
   public String retourbackAdmin()
   {
	   return "liste_utilisateuradmin?faces-redirect=true";
   }
   private Integer _choixId()
   {
    Integer numId = Integer.valueOf(1);
     if (this.terminal.booleanValue())
     {
      numId = Integer.valueOf(2);
     }
       if (this.administration.booleanValue())
       {
         numId = Integer.valueOf(3);
     }
     System.out.println("***"+numId);
     return numId;
   }
   
   private Integer _choixIdEq()
   {
    Integer numId = Integer.valueOf(1);
     
      numId = Integer.valueOf(numId.intValue() + 1);
       if (this.administration.booleanValue())
         numId = Integer.valueOf(numId.intValue() + 1);
     
     return numId;
   }
   public void onEditTeam(RowEditEvent event) { 
	   
	   
	   Objeteam objt=(( Objeteam) event.getObject());
	   LoginBean bean = LoginUtil.getLoginBean();
	   TeamDatabaseLayout teamBd = null;
       try
       {
    	   teamBd = new TeamDatabaseLayout();
    	   teamBd.transactionOpen();
    	   teamBd.afficherFordel(objt.getIdTeam());
        
       
        
    	   //teamBd.model().setDayOfWeek(objt.getDayOfWeek());
    	   teamBd.model().setBeginHour(objt.getBeginHour());
    	   teamBd.model().setEndHour(objt.getEndHour());
    	   
           
    	   teamBd.save();
    	   teamBd.transactionCommit();
    	   

    	// save synchro 
    	TerminalDatabaseLayout termDB= new TerminalDatabaseLayout();
    		          termDB.transactionOpen();
    		           

    		          
    		          termDB.listerAll();
    		          Iterator it = termDB.liste().iterator();
    		          while (it.hasNext())
    			        {
    			          Terminal term = (Terminal)it.next();
    			           SynchDatabaseLayout synchdb = null;
    				 		synchdb = new SynchDatabaseLayout();
    				 		synchdb.transactionOpen();
    			          synchdb.afficher(bean.getIdClient(), term.getIdTerminal());
    			          if (synchdb.model()!=null)//MAJ
    			          { if (synchdb.model().getEtat()==0)
    			          {
    			        	  synchdb.model().setEtat(1);
    			        	  synchdb.model().setDateInformation(new Date());
    			        	  synchdb.update();
    			        	  synchdb.transactionCommit();
    			          }
    			        	  
    			          }
    			          else // creation
    			          {
    			        	  SynchDatabaseLayout synchdbnw = null;
    			        	  synchdbnw = new SynchDatabaseLayout();
    			        	  synchdbnw.transactionOpen();
    			        	  SynchPK synchpk=new SynchPK();
    			        	  synchpk.setIdClient(bean.getIdClient());
    			        	  synchpk.setIdTerminal(term.getIdTerminal());
    			        	  synchdbnw.model().setEtat(1);
    			        	  synchdbnw.model().setDateInformation(new Date());
    			        	  synchdbnw.model().setSynchPK(synchpk);
    			        	  synchdbnw.save();
    			        	  synchdbnw.transactionCommit();
    			          }
    			          
    			        } 
    		     termDB.close();     
       }
       catch (Exception e) {
  				e.printStackTrace();
  			
      
       System.out.println("Erreur modif tteam");
       } finally {
     
     
       }
	   
   }
   public void onCancel(RowEditEvent event) {
	   
   }
   public String supprimerTeam(Integer idteam)
   {
	   System.out.println("here supp tema list");
	   TeamDatabaseLayout teamBD = null;
	   
	   try
	     {
		   teamBD=new TeamDatabaseLayout();
		   teamBD.transactionOpen();
		   teamBD.afficherFordel(idteam);
		   teamBD.model().setMasque(true);
		   teamBD.save();
		   teamBD.transactionCommit();
		   

		// save synchro 
		TerminalDatabaseLayout termDB= new TerminalDatabaseLayout();
			          termDB.transactionOpen();
			           
			          LoginBean bean = LoginUtil.getLoginBean();
			          
			          termDB.listerAll();
			          Iterator it = termDB.liste().iterator();
			          while (it.hasNext())
				        {
				          Terminal term = (Terminal)it.next();
				           SynchDatabaseLayout synchdb = null;
					 		synchdb = new SynchDatabaseLayout();
					 		synchdb.transactionOpen();
				          synchdb.afficher(bean.getIdClient(), term.getIdTerminal());
				          if (synchdb.model()!=null)//MAJ
				          { if (synchdb.model().getEtat()==0)
				          {
				        	  synchdb.model().setEtat(1);
				        	  synchdb.model().setDateInformation(new Date());
				        	  synchdb.update();
				        	  synchdb.transactionCommit();
				          }
				        	  
				          }
				          else // creation
				          {
				        	  SynchDatabaseLayout synchdbnw = null;
				        	  synchdbnw = new SynchDatabaseLayout();
				        	  synchdbnw.transactionOpen();
				        	  SynchPK synchpk=new SynchPK();
				        	  synchpk.setIdClient(bean.getIdClient());
				        	  synchpk.setIdTerminal(term.getIdTerminal());
				        	  synchdbnw.model().setEtat(1);
				        	  synchdbnw.model().setDateInformation(new Date());
				        	  synchdbnw.model().setSynchPK(synchpk);
				        	  synchdbnw.save();
				        	  synchdbnw.transactionCommit();
				          }
				          
				        } 
			     termDB.close();       
	     }
	   catch (Exception e) {
	       
	         e.printStackTrace();
	        }
	   return "modifier_utilisateur?faces-redirect=true&id="+this.idUtilisateur;
	   
   }
   public String creerTeam()
   {
	  
	   
	   LoginBean bean = LoginUtil.getLoginBean();
	   
	   TeamDatabaseLayout teamBD = null;
	   teamBD=new TeamDatabaseLayout();
	   TeamDatabaseLayout teamBDaj = null;
	   teamBDaj=new TeamDatabaseLayout();
	     try
	     {
	    	 
	    	 teamBD.transactionOpen();
	    	 teamBDaj.transactionOpen();
	    	 teamBD.listerByUserandDay(this.idUtilisateur, this.newDay);
	    	 //if (teamBD.model() ==null)
	    	 //{
	    		 //System.out.println("vide ok");
	    		 teamBDaj.model().setIdUtilisateur(this.idUtilisateur);
	    		 teamBDaj.model().setDayOfWeek(this.newDay);
	    		 teamBDaj.model().setBeginHour(this.beginHournew);
	    		 teamBDaj.model().setEndHour(this.endHournew);
	    		 teamBDaj.model().setMasque(false);
	    		 teamBDaj.setClient(bean.getIdClient());
	    		 teamBDaj.save();
	    		 teamBDaj.transactionCommit();

	    		// save synchro 
	    		TerminalDatabaseLayout termDB= new TerminalDatabaseLayout();
	    			          termDB.transactionOpen();
	    			           

	    			          
	    			          termDB.listerAll();
	    			          Iterator it = termDB.liste().iterator();
	    			          while (it.hasNext())
	    				        {
	    				          Terminal term = (Terminal)it.next();
	    				           SynchDatabaseLayout synchdb = null;
	    					 		synchdb = new SynchDatabaseLayout();
	    					 		synchdb.transactionOpen();
	    				          synchdb.afficher(bean.getIdClient(), term.getIdTerminal());
	    				          if (synchdb.model()!=null)//MAJ
	    				          { if (synchdb.model().getEtat()==0)
	    				          {
	    				        	  synchdb.model().setEtat(1);
	    				        	  synchdb.model().setDateInformation(new Date());
	    				        	  synchdb.update();
	    				        	  synchdb.transactionCommit();
	    				          }
	    				        	  
	    				          }
	    				          else // creation
	    				          {
	    				        	  SynchDatabaseLayout synchdbnw = null;
	    				        	  synchdbnw = new SynchDatabaseLayout();
	    				        	  synchdbnw.transactionOpen();
	    				        	  SynchPK synchpk=new SynchPK();
	    				        	  synchpk.setIdClient(bean.getIdClient());
	    				        	  synchpk.setIdTerminal(term.getIdTerminal());
	    				        	  synchdbnw.model().setEtat(1);
	    				        	  synchdbnw.model().setDateInformation(new Date());
	    				        	  synchdbnw.model().setSynchPK(synchpk);
	    				        	  synchdbnw.save();
	    				        	  synchdbnw.transactionCommit();
	    				          }
	    				          
	    				        } 
	    			     termDB.close();     
	    	 //}
	    	 /*else
	    	 {
	    		 System.out.println("existe no");
	    		 FacesContext context = FacesContext.getCurrentInstance();  
	 	        
	 	        context.addMessage(null, new FacesMessage("Day of work is already existing!")); 
	 	       return "modifier_utilisateur?faces-redirect=true&id="+this.idUtilisateur;
	    	 }*/
	    		 
	     }
	     catch (Exception e) {
	         e.getMessage();
	         e.printStackTrace();
	        } return "modifier_utilisateur?faces-redirect=true&id="+this.idUtilisateur;
   }
   public ArrayList<SelectItem> listClients()
   {this.listclt.clear();
     
     ClientDatabaseLayout cltdb = null;
     try
     {
    	 cltdb = new ClientDatabaseLayout();
 
    	 cltdb.listerAll();
     ArrayList list = (ArrayList)cltdb.liste();
 
       for (Integer i = Integer.valueOf(0); i.intValue() < list.size(); i = Integer.valueOf(i.intValue() + 1))
       {
       
 
        this.listclt.add(new SelectItem(((Client)cltdb.liste().get(i.intValue())).getIdClient(), ((Client)cltdb.liste().get(i.intValue())).getCodeAcces()));
       }
 
       
     }
     catch (Exception e) {
      e.printStackTrace();
      
      cltdb.close();
     }
 
    return this.listclt;
   }
 }

