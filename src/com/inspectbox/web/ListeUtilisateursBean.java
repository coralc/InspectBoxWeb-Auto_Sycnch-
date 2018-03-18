 package com.inspectbox.web;
 
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.inspectbox.databaseLayout.SynchDatabaseLayout;
import com.inspectbox.databaseLayout.TeamDatabaseLayout;
import com.inspectbox.databaseLayout.TerminalDatabaseLayout;
import com.inspectbox.databaseLayout.UtilisateurDatabaseLayout;
import com.inspectbox.model.SynchPK;
import com.inspectbox.model.Team;
import com.inspectbox.model.Terminal;
import com.inspectbox.model.Utilisateur;
import com.inspectbox.objetLayout.ObjetGroupeInspection;
import com.inspectbox.objetLayout.ObjetNavigation;
import com.inspectbox.objetLayout.Objeteam;
import com.inspectbox.objetLayout.ObjetUtilisateur;
import com.inspectbox.utils.LoginUtil;
 
 @ManagedBean(name="listeUtilisateursBean")
 
 @ViewScoped
 public class ListeUtilisateursBean
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
  private Integer idNiveauAConsulter = null;
   private Integer idAjouter = Integer.valueOf(0);
 
   private Integer nombreNiveaux = null;
   private String codeAcces;
   public String getCodeAcces() {
	return codeAcces;
}

public void setCodeAcces(String codeAcces) {
	this.codeAcces = codeAcces;
}

private Integer positionActuelleNiveau = Integer.valueOf(1);
   private List<ObjetGroupeInspection> listeMapInspection;
  private List<ObjetUtilisateur> listeMapUtilisateur = new ArrayList();
   private String libelleNiveauCourant;
  private ArrayList<SelectItem> utilisateurList = new ArrayList();
   private ArrayList<SelectItem> niveauList = new ArrayList();
   private Integer idUtilisateurChoisi = Integer.valueOf(0);
   private Integer idNiveauChoisi = Integer.valueOf(0);
   private Date dateInspection = null;
   private Map<Integer, Integer> mapNiveauInitialFinal = new HashMap();
   private Map<Integer, String> mapNiveauInitialLibelle = new HashMap();
 
   private Boolean aAfficher = Boolean.valueOf(false);
   private String libelleNiveau0;
 
   public ArrayList<SelectItem> getUtilisateurList()
   {
    return this.utilisateurList;
   }
 
   public void setUtilisateurList(ArrayList<SelectItem> utilisateurList)
   {
     this.utilisateurList = utilisateurList;
   }
 
   public ListeUtilisateursBean()
   {
     this.listeMapInspection = new ArrayList();
    new ArrayList();
     this.mapNiveauInitialFinal = new HashMap();
     this.mapNiveauInitialLibelle = new HashMap();
   }

   private List<Objeteam> listeMapTeam = new ArrayList();
   public List<Objeteam> getLister2()
   {
     return this.listeMapTeam;
   }
   public Integer getIdNiveauAConsulter()
   {
/* 102 */     return this.idNiveauAConsulter; }
 
   public void setIdNiveauAConsulter(Integer idNiveauAConsulter) {
/* 105 */     this.idNiveauAConsulter = idNiveauAConsulter;
   }
 
   public Integer getIdAjouter() {
/* 109 */     return this.idAjouter;
   }
 
   public void setIdAjouter(Integer idAjouter) {
/* 113 */     this.idAjouter = idAjouter;
   }
 
   public Integer getNombreNiveauxObjets()
   {
/* 119 */     return this.nombreNiveaux;
   }
 
   public void setNombreNiveauxObjets(Integer nombreNiveauxObjets)
   {
/* 125 */     this.nombreNiveaux = nombreNiveauxObjets;
   }
 
   public Integer getPositionActuelleNiveau()
   {
/* 131 */     return this.positionActuelleNiveau;
   }
 
   public void setPositionActuelleNiveau(Integer positionActuelleNiveau)
   {
/* 138 */     this.positionActuelleNiveau = positionActuelleNiveau;
   }
 
   public String getLibelleNiveauCourant() {
/* 142 */     return this.libelleNiveauCourant;
   }
 
   public void setLibelleNiveauCourant(String libelleNiveauCourant)
   {
/* 147 */     this.libelleNiveauCourant = libelleNiveauCourant;
   }
 
   public List<ObjetUtilisateur> getLister()
   {
/* 152 */     return this.listeMapUtilisateur;
   }
 
   public ArrayList<SelectItem> getNiveauList() {
/* 156 */     return this.niveauList;
   }
 
   public void setNiveauList(ArrayList<SelectItem> niveauList)
   {
/* 162 */     this.niveauList = niveauList;
   }
 
   public Integer getIdUtilisateurChoisi()
   {
/* 168 */     return this.idUtilisateurChoisi;
   }
 
   public void setIdUtilisateurChoisi(Integer idUtilisateurChoisi)
   {
/* 174 */     this.idUtilisateurChoisi = idUtilisateurChoisi;
   }
 
   public Integer getIdNiveauChoisi()
   {
/* 180 */     return this.idNiveauChoisi;
   }
 
   public void setIdNiveauChoisi(Integer idNiveauChoisi)
   {
/* 186 */     this.idNiveauChoisi = idNiveauChoisi;
   }
 
   public Date getDateInspection()
   {
/* 192 */     return this.dateInspection;
   }
 
   public void setDateInspection(Date dateInspection)
   {
    this.dateInspection = dateInspection;
   }
 
   public Map<Integer, Integer> getMapNiveauInitialFinal()
   {
     return this.mapNiveauInitialFinal;
   }
 
   public void setMapNiveauInitialFinal(Map<Integer, Integer> mapNiveauInitialFinal)
   {
     this.mapNiveauInitialFinal = mapNiveauInitialFinal;
   }
 
   public Map<Integer, String> getMapNiveauInitialLibelle()
   {
    return this.mapNiveauInitialLibelle;
   }
 
   public void setMapNiveauInitialLibelle(Map<Integer, String> mapNiveauInitialLibelle)
   {
    this.mapNiveauInitialLibelle = mapNiveauInitialLibelle;
   }
 
   public Boolean getaAfficher()
   {
    return this.aAfficher;
   }
 
   public void setaAfficher(Boolean aAfficher)
   {
    this.aAfficher = aAfficher;
   }
   public void afficherAdmin()
   {
    LoginUtil.getLoginBean();
 
     UtilisateurDatabaseLayout utilisateur = null;
     try
     {
       utilisateur = new UtilisateurDatabaseLayout();
 
      
       utilisateur.listerAllAdmin();
      
         
      Iterator it = utilisateur.liste().iterator();
      while (it.hasNext())
       {
    	  
        Utilisateur user = (Utilisateur)it.next();
 
        ObjetUtilisateur objetUtilisateur = new ObjetUtilisateur();
        objetUtilisateur.setIdUtilisateur(user.getIdUtilisateur());
         
         objetUtilisateur.setCodeAcess(user.getCodeAcces());
         if (user.getTypeUser().equals("E"))
         {
        	 objetUtilisateur.setTypeUser("Team");
        	 objetUtilisateur.setNom("**");
             objetUtilisateur.setPrenom("**");
         }
         
         else
         {
        	 objetUtilisateur.setTypeUser("Simple");
        	 objetUtilisateur.setNom(user.getNom());
             objetUtilisateur.setPrenom(user.getPrenom());
         }
        	 
         objetUtilisateur.setDroitAdminClient(Boolean.valueOf(user.getDroitutilisateur().isAdminClient()));
        objetUtilisateur.setDroitTerminal(Boolean.valueOf(user.getDroitutilisateur().isTerminal()));
        objetUtilisateur.setClient(user.getClient().getCodeAcces());
        this.listeMapUtilisateur.add(objetUtilisateur);
       }
     } catch (Exception e) {
      
      e.printStackTrace();
       
     }
     finally
     {
      utilisateur.close();
     }
   }
   public void afficher()
   {
    LoginBean bean = LoginUtil.getLoginBean();
 
     UtilisateurDatabaseLayout utilisateur = null;
     try
     {
       utilisateur = new UtilisateurDatabaseLayout();
 
      
       utilisateur.listerAll(bean.getIdClient());
      
         
      Iterator it = utilisateur.liste().iterator();
      while (it.hasNext())
       {
    	  
        Utilisateur user = (Utilisateur)it.next();
 
        ObjetUtilisateur objetUtilisateur = new ObjetUtilisateur();
        objetUtilisateur.setIdUtilisateur(user.getIdUtilisateur());
         
         objetUtilisateur.setCodeAcess(user.getCodeAcces());
         if (user.getTypeUser().equals("E"))
         {
        	 objetUtilisateur.setTypeUser("Team");
        	 objetUtilisateur.setNom("**");
             objetUtilisateur.setPrenom("**");
         }
         
         else
         {
        	 objetUtilisateur.setTypeUser("Simple");
        	 objetUtilisateur.setNom(user.getNom());
             objetUtilisateur.setPrenom(user.getPrenom());
         }
        	 
         objetUtilisateur.setDroitAdminClient(Boolean.valueOf(user.getDroitutilisateur().isAdminClient()));
        objetUtilisateur.setDroitTerminal(Boolean.valueOf(user.getDroitutilisateur().isTerminal()));
        this.listeMapUtilisateur.add(objetUtilisateur);
       }
     } catch (Exception e) {
      
      e.printStackTrace();
       e.getMessage();
     }
     finally
     {
      utilisateur.close();
     }
   }
 
   public String rechercher()
   {
/* 288 */     this.aAfficher = Boolean.valueOf(true);
/* 289 */     this.listeMapInspection.clear();
 
/* 293 */     return "recherche_inspection?faces-redirect=true";
   }
 
   public String consulter()
   {
/* 299 */     this.aAfficher = Boolean.valueOf(true);
/* 300 */     this.listeMapInspection.clear();
/* 301 */     this.idUtilisateurChoisi = Integer.valueOf(0);
/* 302 */     this.idNiveauChoisi = Integer.valueOf(0);
/* 303 */     this.dateInspection = null;
 
/* 307 */     return "recherche_inspection?faces-redirect=true";
   }
 
   public String consulterMenu() {
/* 311 */     this.aAfficher = Boolean.valueOf(false);
/* 312 */     this.listeMapInspection.clear();
/* 313 */     this.idUtilisateurChoisi = Integer.valueOf(0);
/* 314 */     this.idNiveauChoisi = Integer.valueOf(0);
/* 315 */     this.dateInspection = null;
 
/* 317 */     return "recherche_inspection?faces-redirect=true";
   }
 
   public String getLibelleNiveau0()
   {
/* 323 */     return this.libelleNiveau0;
   }
 
   public void setLibelleNiveau0(String libelleNiveau0)
   {
/* 329 */     this.libelleNiveau0 = libelleNiveau0;
   }
 
   public List<ObjetUtilisateur> getListeMapUtilisateur()
   {
/* 335 */     return this.listeMapUtilisateur;
   }
 
   public void setListeMapUtilisateur(List<ObjetUtilisateur> listeMapUtilisateur)
   {
    this.listeMapUtilisateur = listeMapUtilisateur;
   }
   
   
   public String supprimer(ObjetUtilisateur objetUtilisateur)
   {
	   System.out.println("supp here ");
    LoginBean bean = LoginUtil.getLoginBean();
     UtilisateurDatabaseLayout utilisateur = null;
     TeamDatabaseLayout teamdb = null;
     TeamDatabaseLayout teamdb2 = null;
     try
     {
    	 if (objetUtilisateur.getTypeUser().equals("Team"))
    	 {
    		 teamdb=new TeamDatabaseLayout();
        	 teamdb.transactionOpen();
        	 
        	 teamdb.listerByUser(objetUtilisateur.getIdUtilisateur());
        	 Iterator it = teamdb.liste().iterator();
             while (it.hasNext())
              {
            	 Team team = (Team)it.next();
            	 teamdb2=new TeamDatabaseLayout();
            	 teamdb2.transactionOpen();
            	 teamdb2.afficherFordel(team.getIdTeam());
            	 
            	
            	teamdb2.model().setMasque(true);
            	teamdb2.update();
            	teamdb2.transactionCommit();
            	teamdb2.close();
              }
             teamdb.transactionCommit(); 
             teamdb.close();
    	 }
    	 
    	 
      utilisateur = new UtilisateurDatabaseLayout();
       utilisateur.transactionOpen();
 
      utilisateur.afficher( objetUtilisateur.getIdUtilisateur());
 
      utilisateur.model().setMasque(true);
       //utilisateur.model().setClefTimestamp(new Date());
 
      utilisateur.update();
 
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
      e.getMessage();
     } finally {
      utilisateur.close();
      
     }
 
     return "liste_utilisateur?faces-redirect=true";
   }
   
   public String supprimerAdmin(ObjetUtilisateur objetUtilisateur)
   {
	   
     UtilisateurDatabaseLayout utilisateur = null;
     TeamDatabaseLayout teamdb = null;
     TeamDatabaseLayout teamdb2 = null;
     try
     {
    	 if (objetUtilisateur.getTypeUser().equals("Team"))
    	 {
    		 teamdb=new TeamDatabaseLayout();
        	 teamdb.transactionOpen();
        	 
        	 teamdb.listerByUser(objetUtilisateur.getIdUtilisateur());
        	 Iterator it = teamdb.liste().iterator();
             while (it.hasNext())
              {
            	 Team team = (Team)it.next();
            	 teamdb2=new TeamDatabaseLayout();
            	 teamdb2.transactionOpen();
            	 teamdb2.afficherFordel(team.getIdTeam());
            	 
            	
            	teamdb2.model().setMasque(true);
            	teamdb2.update();
            	teamdb2.transactionCommit();
            	teamdb2.close();
              }
             teamdb.transactionCommit(); 
             teamdb.close();
    	 }
    	 
    	 
      utilisateur = new UtilisateurDatabaseLayout();
       utilisateur.transactionOpen();
 
      utilisateur.afficher( objetUtilisateur.getIdUtilisateur());
 
      utilisateur.model().setMasque(true);
       //utilisateur.model().setClefTimestamp(new Date());
 
      utilisateur.update();
 
      utilisateur.transactionCommit();
      

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
      e.getMessage();
     } finally {
      utilisateur.close();
      
     }
 
     return "liste_utilisateuradmin?faces-redirect=true";
   }
   private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
   public void affiTeam(Integer idUser)
   {
	    String lg=locale.toString();
	  
	   LoginUtil.getLoginBean();
	    UtilisateurDatabaseLayout utilisateur = null;
  TeamDatabaseLayout teambd = null;
    try
    {
    	utilisateur = new UtilisateurDatabaseLayout();
        utilisateur.transactionOpen();
    
         utilisateur.afficher( idUser);
    
        
         this.codeAcces = utilisateur.model().getCodeAcces();
   	 teambd = new TeamDatabaseLayout();
        teambd.transactionOpen();
        teambd.listerByUser(idUser);
        Iterator it = teambd.liste().iterator();
        if (this.listeMapTeam!=null)
        	this.listeMapTeam.clear();
        while (it.hasNext())
         {
     	   Team teamReponse = (Team)it.next();
     	   Objeteam objetTeam = new Objeteam();
     	   objetTeam.setIdTeam(teamReponse.getIdTeam());
     	   if(teamReponse.getDayOfWeek().equals("1"))
     	   {
     		   if (lg.equals("fr"))
     		   objetTeam.setDayOfWeek("Lundi");
     		  if (lg.equals("de"))
     		 objetTeam.setDayOfWeek("Montag");
     		 if (lg.equals("en"))
         		 objetTeam.setDayOfWeek("Monday");
     	   }
     	   
     	   
     	   if(teamReponse.getDayOfWeek().equals("2"))
     	   {
     		  if (lg.equals("fr"))
        		   objetTeam.setDayOfWeek("Mardi");
        		  if (lg.equals("de"))
        		 objetTeam.setDayOfWeek("Dienstag");
        		 if (lg.equals("en"))
            		 objetTeam.setDayOfWeek("Tuesday");
     	   }
     	   
     	   
     	   
     	   if(teamReponse.getDayOfWeek().equals("3"))
     	   {
     		  if (lg.equals("fr"))
       		   objetTeam.setDayOfWeek("Mercredi");
       		  if (lg.equals("de"))
       		 objetTeam.setDayOfWeek("Mittwoch");
       		 if (lg.equals("en"))
           		 objetTeam.setDayOfWeek("Wednesday");
     	   }
     	   
     	   
     	   
     	   if(teamReponse.getDayOfWeek().equals("4"))
     	   {
     		  if (lg.equals("fr"))
          		   objetTeam.setDayOfWeek("Jeudi");
          		  if (lg.equals("de"))
          		 objetTeam.setDayOfWeek("Donnerstag");
          		 if (lg.equals("en"))
              		 objetTeam.setDayOfWeek("Thursday");
     	   }
     	   
     	   
     	   
     	   if(teamReponse.getDayOfWeek().equals("5"))
     	   {
     		  if (lg.equals("fr"))
         		   objetTeam.setDayOfWeek("Vendredi");
         		  if (lg.equals("de"))
         		 objetTeam.setDayOfWeek("Freitag");
         		 if (lg.equals("en"))
             		 objetTeam.setDayOfWeek("Friday");
     	   }
     	   
     	   
     	   
     	   if(teamReponse.getDayOfWeek().equals("6"))
     	   {
     		  if (lg.equals("fr"))
        		   objetTeam.setDayOfWeek("Samedi");
        		  if (lg.equals("de"))
        		 objetTeam.setDayOfWeek("Samstag ");
        		 if (lg.equals("en"))
            		 objetTeam.setDayOfWeek("Saturday");
     	   }
     	   
     	   
     	   if(teamReponse.getDayOfWeek().equals("0"))
     	   {
     		  if (lg.equals("fr"))
       		   objetTeam.setDayOfWeek("Dimanche");
       		  if (lg.equals("de"))
       		 objetTeam.setDayOfWeek("Sonntag ");
       		 if (lg.equals("en"))
           		 objetTeam.setDayOfWeek("Sunday");
     	   }
     	   
     	   
     	   objetTeam.setBeginHour(teamReponse.getBeginHour());
     	   objetTeam.setEndHour(teamReponse.getEndHour());
     	   this.listeMapTeam.add(objetTeam);
         }
       // teambd.transactionCommit();
      }
      catch (Exception e) {
       e.getMessage();
       e.printStackTrace();
      } 
   
   }
 }

