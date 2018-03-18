 package com.inspectbox.web;
 
 import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import com.inspectbox.databaseLayout.InspectionDatabaseLayout;
import com.inspectbox.databaseLayout.LibelleNiveauDatabaseLayout;
import com.inspectbox.databaseLayout.NiveauDatabaseLayout;
import com.inspectbox.databaseLayout.NiveauObjetDatabaseLayout;
import com.inspectbox.databaseLayout.UtilisateurDatabaseLayout;
import com.inspectbox.model.Inspection;
import com.inspectbox.model.Libelleniveau;
import com.inspectbox.model.Niveau;
import com.inspectbox.model.Niveauobjet;
import com.inspectbox.model.Utilisateur;
import com.inspectbox.objetLayout.ObjetGroupeInspection;
import com.inspectbox.objetLayout.ObjetNavigation;
import com.inspectbox.utils.I18nUtil;
import com.inspectbox.utils.LoginUtil;
 
 @ManagedBean(name="listeInspectionBean")
 @SessionScoped
 public class ListeInspectionsBean
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private Integer idNiveauAConsulter = null;
   private Integer idAjouter = Integer.valueOf(0);
   private Integer nombreNiveaux = null;
   private Integer positionActuelleNiveau = Integer.valueOf(1);
   private List<ObjetGroupeInspection> listeMapInspection;
   private List<ObjetNavigation> listeNiveauxParent;
   private String libelleNiveauCourant;
   private ArrayList<SelectItem> utilisateurList = new ArrayList();
   private ArrayList<SelectItem> niveauList = new ArrayList();
  private Integer idUtilisateurChoisi = Integer.valueOf(0);
   private Integer idNiveauChoisi = Integer.valueOf(0);
   private Date dateInspection = null;
   private Map<Integer, Integer> mapNiveauInitialFinal = new HashMap();
   private Map<Integer, String> mapNiveauInitialLibelle = new HashMap();
   private ArrayList<SelectItem> libelleNiveau1List = new ArrayList();
	private ArrayList<SelectItem> libelleNiveau2List = new ArrayList();
	private ArrayList<SelectItem> niveauObjetList = new ArrayList();
	private Integer idLibelleNiveau1Choisi  = Integer.valueOf(0);
	private Integer idLibelleNiveau2Choisi  = Integer.valueOf(0);
	private Integer idNiveauObjetChoisi  = Integer.valueOf(0);
	private Boolean aAfficher = Boolean.valueOf(false);
   private String libelleNiveau0;
	private String libelleNiveau1;
	private String libelleNiveau2;
	private String libelleNiveauObjet0;
 
   public ArrayList<SelectItem> getUtilisateurList()
   {
    return this.utilisateurList;
   }
 
   public void setUtilisateurList(ArrayList<SelectItem> utilisateurList)
   {
    this.utilisateurList = utilisateurList;
   }
 
   public ListeInspectionsBean()
   {
     this.listeMapInspection = new ArrayList();
     this.listeNiveauxParent = new ArrayList();
     this.mapNiveauInitialFinal = new HashMap();
     this.mapNiveauInitialLibelle = new HashMap();
	 this.libelleNiveau1List.clear();
	this.libelleNiveau2List.clear();
	 this.niveauObjetList.clear();

			  this.libelleNiveau1List.add(new SelectItem(Integer.valueOf(0), I18nUtil.get("form_rechercher_liste_niveau_libelle1_all")));
			  this.libelleNiveau2List.add(new SelectItem(Integer.valueOf(0), I18nUtil.get("form_rechercher_liste_niveau_libelle2_all")));
			  this.niveauObjetList.add(new SelectItem(Integer.valueOf(0), I18nUtil.get("form_rechercher_liste_niveau_objet_all")));

   }
 
   public Integer getIdNiveauAConsulter()
   {
     return this.idNiveauAConsulter; }
 
   public void setIdNiveauAConsulter(Integer idNiveauAConsulter) {
     this.idNiveauAConsulter = idNiveauAConsulter;
   }
 
   public Integer getIdAjouter() {
    return this.idAjouter;
   }
 
   public void setIdAjouter(Integer idAjouter) {
     this.idAjouter = idAjouter;
   }
 
   public Integer getNombreNiveauxObjets()
   {
     return this.nombreNiveaux;
   }
 
   public void setNombreNiveauxObjets(Integer nombreNiveauxObjets)
   {
    this.nombreNiveaux = nombreNiveauxObjets;
   }
 
   public Integer getPositionActuelleNiveau()
   {
     return this.positionActuelleNiveau;
   }
 
   public void setPositionActuelleNiveau(Integer positionActuelleNiveau)
   {
     this.positionActuelleNiveau = positionActuelleNiveau;
   }
 
   public String getLibelleNiveauCourant() {
     return this.libelleNiveauCourant;
   }
 
   public void setLibelleNiveauCourant(String libelleNiveauCourant)
   {
     this.libelleNiveauCourant = libelleNiveauCourant;
   }
 
   public List<ObjetGroupeInspection> getLister()
   {
     return this.listeMapInspection;
   }
 
   public ArrayList<SelectItem> getNiveauList() {
     return this.niveauList;
   }
 
   public void setNiveauList(ArrayList<SelectItem> niveauList)
   {
     this.niveauList = niveauList;
   }
 
   public Integer getIdUtilisateurChoisi()
   {
     return this.idUtilisateurChoisi;
   }
 
   public void setIdUtilisateurChoisi(Integer idUtilisateurChoisi)
   {
     this.idUtilisateurChoisi = idUtilisateurChoisi;
   }
 
   public Integer getIdNiveauChoisi()
   {
    return this.idNiveauChoisi;
   }
 
   public void setIdNiveauChoisi(Integer idNiveauChoisi)
   {
    this.idNiveauChoisi = idNiveauChoisi;
   }
 
   public Date getDateInspection()
   {
     return this.dateInspection;
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
/* 209 */     this.mapNiveauInitialFinal = mapNiveauInitialFinal;
   }
 
   public Map<Integer, String> getMapNiveauInitialLibelle()
   {
/* 215 */     return this.mapNiveauInitialLibelle;
   }
 
   public void setMapNiveauInitialLibelle(Map<Integer, String> mapNiveauInitialLibelle)
   {
/* 221 */     this.mapNiveauInitialLibelle = mapNiveauInitialLibelle;
   }
 
   public Boolean getaAfficher()
   {
/* 227 */     return this.aAfficher;
   }
 
   public void setaAfficher(Boolean aAfficher)
   {
/* 233 */     this.aAfficher = aAfficher;
   }
 
  public void afficher()
   {
	  
	Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
	
	 FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	
     LoginBean bean = LoginUtil.getLoginBean();
 
    LibelleNiveauDatabaseLayout libelleNiveau = null;
     try {
      libelleNiveau = new LibelleNiveauDatabaseLayout();
       libelleNiveau.choisirTypeNiveau();
      libelleNiveau.lister(bean.getIdClient());
       this.libelleNiveau0 = ((Libelleniveau)libelleNiveau.liste().get(0)).getLibelle();
				if (libelleNiveau.liste().size()>1)
					this.libelleNiveau1 = ((Libelleniveau)libelleNiveau.liste().get(1)).getLibelle();
				if (libelleNiveau.liste().size()>2)
					this.libelleNiveau2 = ((Libelleniveau)libelleNiveau.liste().get(2)).getLibelle();

     } catch (Exception e) {
      e.printStackTrace();
      
     }
    finally
     {
				if (libelleNiveau!=null){
					libelleNiveau.close();
				}
				
    }

			try{
				libelleNiveau = new LibelleNiveauDatabaseLayout();
				libelleNiveau.choisirTypeObjet();
				libelleNiveau.lister(bean.getIdClient());
				if (libelleNiveau.liste().size()>0)
					this.libelleNiveauObjet0 = ((Libelleniveau)libelleNiveau.liste().get(0)).getLibelle();
				} catch (Exception e) {
					e.printStackTrace();
			    	}
				finally{
					if (libelleNiveau!=null){
			       libelleNiveau.close();
					}
					
			     }


     this.listeMapInspection.clear();
     this.nombreNiveaux = null;
     this.positionActuelleNiveau = Integer.valueOf(1);
     this.libelleNiveauCourant = "";
     this.utilisateurList.clear();
     this.niveauList.clear();


 
     UtilisateurDatabaseLayout utilisateur = null;
     Object listeUtilisateurs = new ArrayList();
    try
     {
       utilisateur = new UtilisateurDatabaseLayout();
 
       utilisateur.listerTerminal(bean.getIdClient());
 
       listeUtilisateurs = utilisateur.liste();
     } catch (Exception e) {
    	 e.printStackTrace();
     } finally {
       utilisateur.close();
       
     }
 
     Iterator itUtilisateur = ((List)listeUtilisateurs).iterator();
     this.utilisateurList.add(new SelectItem(Integer.valueOf(0), I18nUtil.get("form_rechercher_liste_utilisateur_all")));
     while (itUtilisateur.hasNext())
     {
       Utilisateur ut = (Utilisateur)itUtilisateur.next();
       if (ut.getTypeUser().equals("S"))
       this.utilisateurList.add(new SelectItem(ut.getIdUtilisateur(), ut.getPrenom() + " " + ut.getNom()));
       else
    	   this.utilisateurList.add(new SelectItem(ut.getIdUtilisateur(), ut.getCodeAcces()));
     }
 
     Object listeNiveaux = new ArrayList();
     NiveauDatabaseLayout niveau = null;
     try {
       niveau = new NiveauDatabaseLayout();
 
      niveau.listerInitial(bean.getIdClient());
 
       listeNiveaux = niveau.liste();
     } catch (Exception e) {
    	 e.printStackTrace();
     } finally {
       niveau.close();
       
     }
 
     Iterator itNiveau = ((List)listeNiveaux).iterator(); 
     this.niveauList.add(new SelectItem(Integer.valueOf(0), I18nUtil.get("form_rechercher_liste_niveau_all")));
     while (itNiveau.hasNext())
     {
      Niveau niv = (Niveau)itNiveau.next();
       this.niveauList.add(new SelectItem(niv.getIdNiveau(), niv.getLibelle()));
     }
     if (!((this.aAfficher == null | this.aAfficher.booleanValue()))) {
       return;
     }
     
     lister();
   }
 


   public String lister()
   { 
    LoginBean bean = LoginUtil.getLoginBean();
 
    InspectionDatabaseLayout inspection = null;
     NiveauDatabaseLayout niveau = null;
     List liste = new ArrayList();
     Map mapNiveauInitialLibelle = new HashMap();
     Map mapNiveauFinalInitial = new HashMap();
     try
     {
       niveau = new NiveauDatabaseLayout();
      niveau.transactionOpen();
 
       niveau.listerFinalNonMasque(bean.getIdClient());
       Iterator itNiv = niveau.liste().iterator();
 
       while (itNiv.hasNext())
       {
        Niveau niv = (Niveau)itNiv.next();
         Integer niveauFinal = niv.getIdNiveau();
         while (niv.getNiveau() != null)
         {
           niv = niv.getNiveau();
         }
 
         liste.add(niv.getIdNiveau());
 
         mapNiveauFinalInitial.put(niveauFinal, niv.getIdNiveau());
         if (!(mapNiveauInitialLibelle.containsKey(niv.getIdNiveau()))) {
           mapNiveauInitialLibelle.put(niv.getIdNiveau(), niv.getLibelle().toString());
         }
 

       }
 
      niveau.transactionCommit();
     } catch (Exception e) {

				e.printStackTrace();

     }
     finally
     {
       niveau.close();
     }
 
     try
     {
			inspection = new InspectionDatabaseLayout(); 
			inspection.transactionOpen();
			//	if (this.idNiveauChoisi)
			if ( this.idLibelleNiveau1Choisi >0 || this.idLibelleNiveau2Choisi>0){
				if (idLibelleNiveau2Choisi>0){
					if (idNiveauObjetChoisi>0)
						inspection.listerGroupeFilter(bean.getIdClient(), this.dateInspection,idLibelleNiveau2Choisi,false, idNiveauObjetChoisi);
					else
						inspection.listerGroupeFilter(bean.getIdClient(), this.dateInspection,idLibelleNiveau2Choisi,false, -99);						
				}
				else
					inspection.listerGroupeFilter(bean.getIdClient(), this.dateInspection,idLibelleNiveau1Choisi,true,-99);
				int i=0;
				Iterator it = inspection.liste().iterator();
				while (it.hasNext())
				       {
					
						Inspection inspect = (Inspection)it.next();
						
						Niveau niveautemp=inspect.getNiveau();
						Integer idNiveauInitial = (Integer)mapNiveauFinalInitial.get(niveautemp.getIdNiveau());
				         ObjetGroupeInspection groupeInepection = new ObjetGroupeInspection();
				         groupeInepection.setIdNiveau(niveautemp.getIdNiveau());
				         groupeInepection.setIdNiveauInitial(idNiveauInitial);
				         Utilisateur utilisateur = inspect.getUtilisateur();
				         groupeInepection.setUtilisateur(utilisateur.getPrenom() + " " + utilisateur.getNom());
				         groupeInepection.convertDate(inspect.getDateInformation());
				         groupeInepection.setNomNiveauInitial((String)mapNiveauInitialLibelle.get(idNiveauInitial));
				         groupeInepection.setIdUtilisateur(utilisateur.getIdUtilisateur());
				         groupeInepection.setIdInspection(i++);
				         groupeInepection.setIdNiveauBatiment(idLibelleNiveau1Choisi);
				         groupeInepection.setIdNiveauLocalisation(idLibelleNiveau2Choisi);
				         groupeInepection.setIdNiveauObjet(this.idNiveauObjetChoisi);				         			        
				         this.listeMapInspection.add(groupeInepection);
				         								         
				       }
				inspection.transactionCommit();
				if (inspection!=null){
					inspection.close();
					inspection=null;
				}
			}
			else
			{
				 
    inspection = new InspectionDatabaseLayout();
 
       inspection.transactionOpen();
 
      inspection.listerGroupe(bean.getIdClient(), this.dateInspection);
 
       Iterator it = inspection.liste().iterator();
 
       List listeNiveauInitialDejaAffiche = new ArrayList();
       while (it.hasNext())
       {
        Inspection inspect = (Inspection)it.next();
 		  Niveau niveautemp=inspect.getNiveau();
        Integer idNiveauInitial = (Integer)mapNiveauFinalInitial.get(niveautemp.getIdNiveau());
 
         Integer i = Integer.valueOf(0);
         if ((this.idNiveauChoisi.intValue() != 0) && (!(idNiveauInitial.equals(this.idNiveauChoisi)))) {
           continue;
         }
         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
 			Utilisateur utilisateur = inspect.getUtilisateur();
         String strClef = dateFormat.format(inspect.getDateInformation()) + "_" + utilisateur.getIdUtilisateur() + "_" + idNiveauInitial;
         if (listeNiveauInitialDejaAffiche.contains(strClef)) {
           continue;
         }
         ObjetGroupeInspection groupeInepection = new ObjetGroupeInspection();
         groupeInepection.setIdNiveau(niveautemp.getIdNiveau());
         groupeInepection.setIdNiveauInitial(idNiveauInitial);
         groupeInepection.setUtilisateur(utilisateur.getPrenom() + " " + utilisateur.getNom());
         groupeInepection.convertDate(inspect.getDateInformation());
         groupeInepection.setNomNiveauInitial((String)mapNiveauInitialLibelle.get(idNiveauInitial));
         groupeInepection.setIdUtilisateur(utilisateur.getIdUtilisateur());

         groupeInepection.setIdNiveauBatiment(idLibelleNiveau1Choisi);
				  groupeInepection.setIdNiveauLocalisation(idLibelleNiveau2Choisi);
				  groupeInepection.setIdNiveauObjet(this.idNiveauObjetChoisi);	


				  Integer tmp535_533 = i; i = Integer.valueOf(tmp535_533.intValue() + 1); groupeInepection.setIdInspection(tmp535_533);
        this.listeMapInspection.add(groupeInepection);
         
         listeNiveauInitialDejaAffiche.add(strClef);
       }
 
      inspection.transactionCommit();
     }
				}
     catch (Exception e) {
      System.out.println("Erreur liste groupe inspection");
       e.printStackTrace();
     }
     finally
     {
				if (inspection!=null)
      	inspection.close();
     }
 
     LibelleNiveauDatabaseLayout libelleNiveau = null;
     try {
       libelleNiveau = new LibelleNiveauDatabaseLayout();
 
       libelleNiveau.choisirTypeNiveau();
      libelleNiveau.lister(bean.getIdClient());
 
       ArrayList list = (ArrayList)libelleNiveau.liste();
 
       for (Integer i = Integer.valueOf(0); i.intValue() < list.size(); i = Integer.valueOf(i.intValue() + 1))
       {
        if ((this.positionActuelleNiveau == null) || (this.positionActuelleNiveau.intValue() == 1))
         {
          this.libelleNiveauCourant = ((Libelleniveau)list.get(0)).getLibelle();
           System.out.println("LibelleA" + this.libelleNiveauCourant + this.positionActuelleNiveau);
         }
         else
         {
           if (this.positionActuelleNiveau.intValue() > this.nombreNiveaux.intValue())
           {
             this.libelleNiveauCourant = "Objet";
           }
           else
           {
             this.libelleNiveauCourant = ((Libelleniveau)list.get(this.positionActuelleNiveau.intValue() - 1)).getLibelle();
           }
           System.out.println("LibelleB" + this.libelleNiveauCourant + this.positionActuelleNiveau);
         }
       }
     }
     catch (Exception e)
     {
      System.out.println("Erreur");
      e.printStackTrace();
     } finally {
      libelleNiveau.close();
     }

    return "recherche_inspection?faces-redirect=true";
   }
 
   public String rechercher()
   {
/* 496 */     this.aAfficher = Boolean.valueOf(true);
/* 497 */     this.listeMapInspection.clear();
 
/* 503 */     return "recherche_inspection?faces-redirect=true";
   }
 
   public String consulter()
   {
/* 509 */     this.aAfficher = Boolean.valueOf(true);
/* 510 */     this.listeMapInspection.clear();
/* 511 */     this.idUtilisateurChoisi = Integer.valueOf(0);
/* 512 */     this.idNiveauChoisi = Integer.valueOf(0);
/* 513 */     this.dateInspection = null;
 
/* 517 */     return "recherche_inspection?faces-redirect=true";
   }
 
   public String consulterMenu() {
/* 521 */     this.aAfficher = Boolean.valueOf(false);
/* 522 */     this.listeMapInspection.clear();
/* 523 */     this.idUtilisateurChoisi = Integer.valueOf(0);
/* 524 */     this.idNiveauChoisi = Integer.valueOf(0);
/* 525 */     this.dateInspection = null;
 
/* 527 */     return "recherche_inspection?faces-redirect=true";
   }
 
   public String getLibelleNiveau0()
   {
/* 533 */     return this.libelleNiveau0;
   }
 
   public void setLibelleNiveau0(String libelleNiveau0)
   {
/* 539 */     this.libelleNiveau0 = libelleNiveau0;
   }

			public String getLibelleNiveau1() {
				return libelleNiveau1;
			}
			public void setLibelleNiveau1(String libelleNiveau1) {
				this.libelleNiveau1 = libelleNiveau1;
			}
			public String getLibelleNiveau2() {
				return libelleNiveau2;
			}
			public void setLibelleNiveau2(String libelleNiveau2) {
				this.libelleNiveau2 = libelleNiveau2;
			}
			public ArrayList<SelectItem> getLibelleNiveau1List() {
				return libelleNiveau1List;
			}
			public void setLibelleNiveau1List(ArrayList<SelectItem> libelleNiveau1List) {
				this.libelleNiveau1List = libelleNiveau1List;
			}
			public ArrayList<SelectItem> getLibelleNiveau2List() {
				return libelleNiveau2List;
			}
			public void setLibelleNiveau2List(ArrayList<SelectItem> libelleNiveau2List) {
				this.libelleNiveau2List = libelleNiveau2List;
			}
			public Integer getIdLibelleNiveau1Choisi() {
				return idLibelleNiveau1Choisi;
			}
			public void setIdLibelleNiveau1Choisi(Integer idLibelleNiveau1Choisi) {
				this.idLibelleNiveau1Choisi = idLibelleNiveau1Choisi;
			}
			public Integer getIdLibelleNiveau2Choisi() {
				return idLibelleNiveau2Choisi;
			}
			public void setIdLibelleNiveau2Choisi(Integer idLibelleNiveau2Choisi) {
				this.idLibelleNiveau2Choisi = idLibelleNiveau2Choisi;
			}
			
			
		    public Integer getIdNiveauObjetChoisi() {
				return idNiveauObjetChoisi;
			}
			public void setIdNiveauObjetChoisi(Integer idNiveauObjetChoisi) {
				this.idNiveauObjetChoisi = idNiveauObjetChoisi;
			}
			
			
			public ArrayList<SelectItem> getNiveauObjetList() {
				return niveauObjetList;
			}
			public void setNiveauObjetList(ArrayList<SelectItem> niveauObjetList) {
				this.niveauObjetList = niveauObjetList;
			}
			
			
			public String getLibelleNiveauObjet0() {
				return libelleNiveauObjet0;
			}
			public void setLibelleNiveauObjet0(String libelleNiveauObjet0) {
				this.libelleNiveauObjet0 = libelleNiveauObjet0;
			}
			
			public void batimentChanged(AjaxBehaviorEvent event) {
				libelleNiveau2List.clear();
		    	this.niveauObjetList.clear();				
		    	this.libelleNiveau2List.add(new SelectItem(Integer.valueOf(0), I18nUtil.get("form_rechercher_liste_niveau_libelle2_all")));
		    	this.niveauObjetList.add(new SelectItem(Integer.valueOf(0), I18nUtil.get("form_rechercher_liste_niveau_objet_all")));
			
		    	if (this.idLibelleNiveau1Choisi==0 ||  this.idLibelleNiveau1Choisi<1){
//			    	libelleNiveau2List.clear();
//			    	this.niveauObjetList.clear();
			    	return;
		    	}
		    		
		    	
		    	System.out.println("*************inside batimentChanged method");
		    	System.out.println("*************idLibelleNiveau1Choisi"+this.idLibelleNiveau1Choisi);
//		    	libelleNiveau2List.clear();
//		    	this.niveauObjetList.clear();
		    	LoginBean bean = LoginUtil.getLoginBean();
		    	
//		    	this.libelleNiveau2List.add(new SelectItem(Integer.valueOf(0), I18nUtil.get("form_rechercher_liste_niveau_libelle2_all")));
//		    	this.niveauObjetList.add(new SelectItem(Integer.valueOf(0), I18nUtil.get("form_rechercher_liste_niveau_objet_all")));

		    	List listeNiveaux = new ArrayList();
		    	     NiveauDatabaseLayout niveau = null;
		    	     try {
		    	       niveau = new NiveauDatabaseLayout();
		    	 
		    	       niveau.listerChild(bean.getIdClient(),this.idLibelleNiveau1Choisi);
		    	 
		    	       listeNiveaux = niveau.liste();
		    	     } catch (Exception e) {
		    	       System.out.println("Erreur liste batiment");
		    	       e.getMessage();
		    	     } finally {
		    	       niveau.close();
		    	     }
		    	     try { 
		    	     Iterator itNiveau = ((List)listeNiveaux).iterator(); 
		    	     while (itNiveau.hasNext())
		    	     {
		    	      Niveau niv = (Niveau)itNiveau.next();
		    	       this.libelleNiveau2List.add(new SelectItem(niv.getIdNiveau(), niv.getLibelle()));
		    	     }
			   	     } catch (Exception e) {
			   	    	 System.out.println("Erreur liste batiment");
			   	     }  
				    	System.out.println("*************exit batimentChanged method");

		    }
		    
		    public void siteChanged(AjaxBehaviorEvent event) {
	    		libelleNiveau1List.clear();
		    	libelleNiveau2List.clear();
		    	niveauObjetList.clear();
		    	this.libelleNiveau1List.add(new SelectItem(Integer.valueOf(0), I18nUtil.get("form_rechercher_liste_niveau_libelle1_all")));
		    	this.libelleNiveau2List.add(new SelectItem(Integer.valueOf(0), I18nUtil.get("form_rechercher_liste_niveau_libelle2_all")));
		    	this.niveauObjetList.add(new SelectItem(Integer.valueOf(0), I18nUtil.get("form_rechercher_liste_niveau_objet_all")));

		    	if (this.idNiveauChoisi==0 ||  this.idNiveauChoisi<1){
//		    		libelleNiveau1List.clear();
//			    	libelleNiveau2List.clear();
//			    	niveauObjetList.clear();
			    	return;
		    	}
		    		
		    	System.out.println("*************inside siteChanged method");
		    	System.out.println("*************idNiveauChoisi"+this.idNiveauChoisi);

//		    	libelleNiveau1List.clear();
//		    	libelleNiveau2List.clear();
//		    	niveauObjetList.clear();
		    	
		    	LoginBean bean = LoginUtil.getLoginBean();
		    	
//		    	this.libelleNiveau1List.add(new SelectItem(Integer.valueOf(0), I18nUtil.get("form_rechercher_liste_niveau_libelle1_all")));
//		    	this.libelleNiveau2List.add(new SelectItem(Integer.valueOf(0), I18nUtil.get("form_rechercher_liste_niveau_libelle2_all")));
//		    	this.niveauObjetList.add(new SelectItem(Integer.valueOf(0), I18nUtil.get("form_rechercher_liste_niveau_objet_all")));

		    	List listeNiveaux = new ArrayList();
		    	     NiveauDatabaseLayout niveau = null;
		    	     try {
		    	       niveau = new NiveauDatabaseLayout();
		    	 
		    	       niveau.listerChild(bean.getIdClient(),this.idNiveauChoisi);
		    	 
		    	       listeNiveaux = niveau.liste();
		    	       System.out.println("******************niveau.liste().size()"+niveau.liste().size());
		    	     } catch (Exception e) {
		    	       System.out.println("Erreur liste site");
		    	       e.printStackTrace();
		    	       e.getMessage();
		    	     } finally {
		    	       niveau.close();
		    	     }
		    	     try { 
		    	     Iterator itNiveau = ((List)listeNiveaux).iterator(); 
		    	     while (itNiveau.hasNext())
		    	     {
		    	      Niveau niv = (Niveau)itNiveau.next();
		    	       this.libelleNiveau1List.add(new SelectItem(niv.getIdNiveau(), niv.getLibelle()));
		    	     }	
		    	     } catch (Exception e) {
		    	    	 System.out.println("Erreur liste site");
		    	    	 e.printStackTrace();
		    	     }
				    	System.out.println("*************exit siteChanged method");
		    }
		    
		    public void localisationChanged(AjaxBehaviorEvent event) {
		    	niveauObjetList.clear();		    	
		    	this.niveauObjetList.add(new SelectItem(Integer.valueOf(0), I18nUtil.get("form_rechercher_liste_niveau_objet_all2")));

			    	if (this.idLibelleNiveau2Choisi<1 ){
	    		return;	
		    	}
		    	LoginBean bean = LoginUtil.getLoginBean();
		    	
		    	NiveauDatabaseLayout niveau = null;
	    	     
	    	       niveau = new NiveauDatabaseLayout();
		    	
		    	 NiveauObjetDatabaseLayout listObjetsInitial = null;
		    	 listObjetsInitial = new NiveauObjetDatabaseLayout();
		    	    NiveauObjetDatabaseLayout nvbj=null;
		    	    nvbj= new NiveauObjetDatabaseLayout();
		    	    Integer idobjchoisi;
		    	     try
		    	     {
		    	    	 niveau.afficherbyId(bean.getIdClient(), this.idLibelleNiveau2Choisi);
		    	    	
		    	    	 if( niveau.model().getNiveauobjet() !=null)
		    	    	 
		    	    	  {
		    	    		 nvbj.afficherbyid(bean.getIdClient(), niveau.model().getNiveauobjet().getIdNiveauObjet());
		    	    	  if (nvbj.model()!=null)
			    	    	  this.niveauObjetList.add(new SelectItem(nvbj.model().getIdNiveauObjet(), nvbj.model().getLibelle()));
		    	    	  }
		    	    	 
		    	   } catch (Exception e) {
			   	    	 System.out.println("Erreur liste equipmment");
			   	    	 e.printStackTrace();
			   	     }  
				    	
		    }
		    
	    public String resetMe()
	     {
	      aAfficher = Boolean.valueOf(false);
		  afficher();	    	
	      this.idLibelleNiveau1Choisi=0;
	      this.idLibelleNiveau2Choisi=0;
	      this.idNiveauChoisi=0;
	      this.idNiveauObjetChoisi=0;	      
		  this.libelleNiveau1List.clear();
		  this.libelleNiveau2List.clear();
		  this.niveauObjetList.clear();
		  this.dateInspection=null;
		  this.libelleNiveau1List.add(new SelectItem(Integer.valueOf(0), I18nUtil.get("form_rechercher_liste_niveau_libelle1_all")));
		  this.libelleNiveau2List.add(new SelectItem(Integer.valueOf(0), I18nUtil.get("form_rechercher_liste_niveau_libelle2_all")));
		  this.niveauObjetList.add(new SelectItem(Integer.valueOf(0), I18nUtil.get("form_rechercher_liste_niveau_objet_all")));
		  return "recherche_inspection?faces-redirect=true";
	     }

 }

