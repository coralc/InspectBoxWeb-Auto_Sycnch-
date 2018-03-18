 package com.inspectbox.web;
 
 import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.inspectbox.databaseLayout.NiveauDatabaseLayout;
import com.inspectbox.databaseLayout.NiveauObjetDatabaseLayout;
import com.inspectbox.databaseLayout.SynchDatabaseLayout;
import com.inspectbox.databaseLayout.TerminalDatabaseLayout;
import com.inspectbox.databaseLayout.TypeReponseDatabaseLayout;
import com.inspectbox.model.Client;
import com.inspectbox.model.Niveau;
import com.inspectbox.model.Niveauobjet;
import com.inspectbox.model.SynchPK;
import com.inspectbox.model.Terminal;
import com.inspectbox.model.Typereponse;
import com.inspectbox.objetLayout.ObjetNavigation;
import com.inspectbox.objetLayout.ObjetNiveauObjet;
import com.inspectbox.utils.ArianeUtil;
import com.inspectbox.utils.I18nUtil;
import com.inspectbox.utils.LoginUtil;
 
 @ManagedBean(name="niveauInspectionBean")
 @ViewScoped
 

 public class NiveauInspectionBean implements java.io.Serializable 
 {
 
   
  private String nom = "";
  private String link;
  
public String getLink() {
	return link;
}
public void setLink(String link) {
	this.link = link;
}
private String nomobj ;
private String codeBarreobj;
private Boolean commentaireAutorise = Boolean.valueOf(true);
private Integer choix =null ;
private Integer choixobj =null ;

public Integer getChoixobj() {
	return choixobj;
}
public void setChoixobj(Integer choixobj) {
	this.choixobj = choixobj;
}
public Boolean getCommentaireAutorise() {
	return commentaireAutorise;
}
public void setCommentaireAutorise(Boolean commentaireAutorise) {
	this.commentaireAutorise = commentaireAutorise;
}
public Integer getChoix() {
	return choix;
}
public void setChoix(Integer choix) {
	this.choix = choix;
}
public String getNomobj() {
	return nomobj;
}
public void setNomobj(String nomobj) {
	this.nomobj = nomobj;
}
private String nomequip ;
public String getNomequip() {
	return nomequip;
}
public void setNomequip(String nomequip) {
	this.nomequip = nomequip;
}
private List<ObjetNiveauObjet> map=new ArrayList();
public List<ObjetNiveauObjet> getLister1()
{
 return this.map;
}




public List<ObjetNiveauObjet> getMap() {
	return map;
}
public void setMap(List<ObjetNiveauObjet> map) {
	this.map = map;
}
public String getCodeBarreobj() {
	return codeBarreobj;
}
public void setCodeBarreobj(String codeBarreobj) {
	this.codeBarreobj = codeBarreobj;
}
   private String codeBarre = "";
   private Integer idObjetParent = Integer.valueOf(0);
  private Integer idObjetCourant = Integer.valueOf(0);
   private Integer triMax = Integer.valueOf(0);
   private List<ObjetNavigation> listeAriane = new ArrayList();
 
   
   private Integer objetInitial ;
   private Integer listchoisi ;
private String libelleInital;
public String getLibelleInital() {
	return libelleInital;
}
public void setLibelleInital(String libelleInital) {
	this.libelleInital = libelleInital;
}
  private ArrayList<SelectItem> objetsInitialList = new ArrayList();
  private ArrayList<SelectItem> listes = new ArrayList();
 public ArrayList<SelectItem> getListes() {
	return listes;
}
public void setListes(ArrayList<SelectItem> listes) {
	this.listes = listes;
}
String err="";

public String getErr() {
	return err;
}
public void setErr(String err) {
	this.err = err;
}

private boolean showDialog;

public void showDialog() {
    showDialog = true;
}

public boolean isShowDialog() {
    return showDialog;
}
   public String getNom()
   {
/*  53 */     return this.nom; }
 
   public void setNom(String nom) {
/*  56 */     this.nom = nom; }
 
   public String getCodeBarre() {
/*  59 */     return this.codeBarre; }
 
   public void setCodeBarre(String codeBarre) {
/*  62 */     this.codeBarre = codeBarre; }
 
   public Integer getObjetInitial() {
/*  65 */     return this.objetInitial; }
 
   public void setObjetInitial(Integer objetInitial) {
/*  68 */     this.objetInitial = objetInitial; }
 
   public Integer getListchoisi() {
	return listchoisi;
}
public void setListchoisi(Integer listchoisi) {
	this.listchoisi = listchoisi;
}
public ArrayList<SelectItem> getObjetsInitialList() {
/*  71 */     return this.objetsInitialList;
   }
 
   public Integer getIdObjetParent()
   {
/*  76 */     return this.idObjetParent; }
 
   public void setIdObjetParent(Integer idObjetParent) {
/*  79 */     this.idObjetParent = idObjetParent;
   }
 
   public Integer getIdObjetCourant()
   {
/*  85 */     return this.idObjetCourant; }
 
   public void setIdObjetCourant(Integer idObjetCourant) {
/*  88 */     this.idObjetCourant = idObjetCourant;
   }
 
   public Integer getTriMax()
   {
/*  93 */     return this.triMax; }
 
   public void setTriMax(Integer triMax) {
/*  96 */     this.triMax = triMax; }
 
   public List<ObjetNavigation> getListeAriane() {
/*  99 */     return this.listeAriane; }
 
   public void setListeAriane(List<ObjetNavigation> listeAriane) {
/* 102 */     this.listeAriane = listeAriane;
   }
 
   public String consulterAjouter(Integer idObjetParent, Integer triMax)
   {
/* 108 */     this.idObjetParent = idObjetParent;
/* 109 */     this.idObjetCourant = Integer.valueOf(0);
/* 110 */     this.triMax = triMax;
 
/* 113 */     if ((idObjetParent == null) || (idObjetParent.intValue() == 0)) {
/* 114 */       return "liste_niveau";
     }
 
/* 117 */     return "ajouter_niveau_inspection";
   }
 
   public String consulterModifier(Integer idObjetParent, Integer idObjetCourant) {
/* 121 */     this.idObjetParent = idObjetParent;
/* 122 */     this.idObjetCourant = idObjetCourant;
/* 123 */     this.triMax = Integer.valueOf(0);
 
/* 126 */     if ((idObjetParent == null) || (idObjetParent.intValue() == 0)) {
/* 127 */       return "liste_niveau";
     }
/* 129 */     return "modifier_niveau_inspection";
   }
   private ArrayList<SelectItem> choixList = new ArrayList();
   public ArrayList<SelectItem> getChoixList() {
	return choixList;
}
public void setChoixList(ArrayList<SelectItem> choixList) {
	this.choixList = choixList;
}
/*public ArrayList<SelectItem> listTypeReponse()
   {this.choixList.clear();
     LoginBean bean = LoginUtil.getLoginBean();
     TypeReponseDatabaseLayout typeReponse = null;
     try
     {
      typeReponse = new TypeReponseDatabaseLayout();
 
       typeReponse.listerExisting(bean.getIdClient());
     ArrayList list = (ArrayList)typeReponse.liste();
 
       for (Integer i = Integer.valueOf(0); i.intValue() < list.size(); i = Integer.valueOf(i.intValue() + 1))
       {
       
 
        this.choixList.add(new SelectItem(((Typereponse)typeReponse.liste().get(i.intValue())).getIdTypeReponse(), ((Typereponse)typeReponse.liste().get(i.intValue())).getLibelle()));
       }
 
       if (list.size() == 1)
         this.choix = ((Typereponse)list.get(0)).getIdTypeReponse();
     }
     catch (Exception e) {
      e.printStackTrace();
      e.getMessage();
      typeReponse.close();
     }
 
    return this.choixList;
   }
*/
public void afficher()
   {
	
}
   public void afficher2()
   {
     LoginBean bean = LoginUtil.getLoginBean();
    NiveauDatabaseLayout niveauObjet = null;
 
    this.listeAriane.add(new ObjetNavigation(I18nUtil.get("navigation_parametrage_niveaux"), Integer.valueOf(0)));
     try {
    niveauObjet = new NiveauDatabaseLayout();
      niveauObjet.afficher(bean.getIdClient(), this.idObjetParent);
 
       this.listeAriane.addAll(ArianeUtil.listNiveau(niveauObjet.model()));
     } catch (Exception e) {
       e.getMessage();
     } finally {
       niveauObjet.close();
     }
 
    if (this.idObjetCourant.intValue() == 0) {
       return;
     }
 
     niveauObjet = null;
     try
     {
      niveauObjet = new NiveauDatabaseLayout();
       niveauObjet.afficher(bean.getIdClient(), this.idObjetCourant);
 
      this.nom = niveauObjet.model().getLibelle();
      this.codeBarre = niveauObjet.model().getCodeBarre();
 
       this.objetInitial = niveauObjet.model().getNiveauobjet().getIdNiveauObjet();
       
     }
     catch (Exception e) {
	e.printStackTrace();
       e.getMessage();
     } finally {
       niveauObjet.close();
     }
   }
   @SuppressWarnings("unused")
  public ArrayList<SelectItem> listdesListes()
      {
   	   if (this.listes.size()>0)
   		   this.listes.clear();
       LoginBean bean = LoginUtil.getLoginBean();
       TypeReponseDatabaseLayout listlistes = null;
        try
        {
        	listlistes = new TypeReponseDatabaseLayout();
       
   	 
   	  
        	listlistes.afficherByMode2(bean.getIdClient(), 2);
        	
        	 ArrayList list = (ArrayList)listlistes.liste();


			
			for (Integer i = Integer.valueOf(0); i.intValue() < list.size(); i = Integer.valueOf(i.intValue() + 1))
		       {
		       
		 
		        this.listes.add(new SelectItem(((Typereponse)listlistes.liste().get(i.intValue())).getIdTypeReponse(), 
		        		((Typereponse)listlistes.liste().get(i.intValue())).getLibelle()));
				
			       
		       }
			
}
        catch (Exception e)
        {
          
          
   e.printStackTrace();
        } finally {
        	listlistes.close();
        
         
        }
    
        return this.listes;
      }
      
   @SuppressWarnings("unused")
public ArrayList<SelectItem> listObjetsInitial()
   {
	   if (this.objetsInitialList.size()>0)
		   this.objetsInitialList.clear();
    LoginBean bean = LoginUtil.getLoginBean();
    NiveauObjetDatabaseLayout listObjetsInitial = null;
    NiveauObjetDatabaseLayout nvbj=null;
     try
     {
     listObjetsInitial = new NiveauObjetDatabaseLayout();
     nvbj= new NiveauObjetDatabaseLayout();
	 
	  
      listObjetsInitial.listerNotexisting(bean.getIdClient());
      if ((this.objetInitial !=null)&& (this.objetInitial !=0))
      {
    	  System.out.println("this.objinit"+this.objetInitial);
    	  nvbj.afficherbyid(bean.getIdClient(), this.objetInitial);
    	  if (nvbj.model()!=null)
    	  this.objetsInitialList.add(new SelectItem(nvbj.model().getIdNiveauObjet(), nvbj.model().getLibelle()));
      }
      
      

       ArrayList list = (ArrayList)listObjetsInitial.liste();
 
      for (Integer i = Integer.valueOf(0); i.intValue() < list.size(); i = Integer.valueOf(i.intValue() + 1))
       { 
    	  
    		
  			this.objetsInitialList.add(new SelectItem(((Niveauobjet)listObjetsInitial.liste().get(i.intValue())).getIdNiveauObjet(), ((Niveauobjet)listObjetsInitial.liste().get(i.intValue())).getLibelle()));
  		
  		
     
     }}
     catch (Exception e)
     {
       
       
e.printStackTrace();
     } finally {
      listObjetsInitial.close();
     
      nvbj.close();
     }
 
     return this.objetsInitialList;
   }
   
 
   public void modifierLibelle()
   {
/* 216 */     if (this.nom == "")
/* 217 */       this.nom = ((SelectItem)this.objetsInitialList.get(0)).getLabel();
   }
 
   public String ajouter()
   {
	   
	   
		this.err=I18nUtil.get("form_erreur_ajouter_inspection");
		if ((this.nom.equals("")|| (this.nom ==null) || this.objetInitial==0))
		{
			FacesContext context = FacesContext.getCurrentInstance();  
	        
	        context.addMessage(null, new FacesMessage(err));  
	        
		return "ajouter_niveau_inspection";
		}
		else
		{
	    if (this.idObjetParent.intValue() == 0) {
      return "liste_niveau";
     }
 
    LoginBean bean = LoginUtil.getLoginBean();
    NiveauDatabaseLayout niveau = null;
    NiveauObjetDatabaseLayout nvobjDB=new NiveauObjetDatabaseLayout();
	   nvobjDB.transactionOpen();
     try
     {
      niveau = new NiveauDatabaseLayout();
      niveau.transactionOpen();
 
      niveau.setClient(bean.getIdClient());
       niveau.setNiveauObjet(this.objetInitial);
       
	    	 nvobjDB.afficherbyid(bean.getIdClient(), objetInitial);
	    	 if (nvobjDB.model()!=null)
	    	 {
	    	 nvobjDB.model().setExiste(true);
	    	 nvobjDB.update();
	    	 nvobjDB.transactionCommit();
	     }
       niveau.setNiveauParent(this.idObjetParent);
 
      if (niveau.verifierDroitAjouterInspection().booleanValue())
       {
         niveau.model().setLibelle(this.nom);
        niveau.model().setCodeBarre(this.codeBarre);
       niveau.model().setTri(this.triMax.intValue() + 1);
 
         niveau.model().setMasque(false);
       niveau.model().setClefTimestamp(new Date());
 
        niveau.save(); niveau.transactionCommit();

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
 
       
     }
     catch (Exception e) {
     System.out.println("Erreur d'ajout");
e.printStackTrace();
 
     return "ajouter_niveau_inspection";
     } finally {
      niveau.close();
      nvobjDB.close();
     }
 
    return "liste_niveau?faces-redirect=true";
   }}
   
   
   
   public String ajouterandgo()
   {
	   Integer tri=1;
	   Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
	  
		if ((this.nom.equals("")|| (this.nom ==null) || this.objetInitial==0))
		{
			FacesContext context = FacesContext.getCurrentInstance();  
	        
	        context.addMessage(null, new FacesMessage( I18nUtil.get("erreur_libelle_gamme")));  
	        
		return "ajouter_niveau_inspection";
		}
		else
		{
	   
	   
	   
    if (this.idObjetParent.intValue() == 0) {
       return "liste_niveau";
     }
 
     LoginBean bean = LoginUtil.getLoginBean();
   NiveauDatabaseLayout niveau = null;
   NiveauDatabaseLayout niveauTri = null;
   NiveauObjetDatabaseLayout niveauobj = null;
     try
     {
      niveau = new NiveauDatabaseLayout();
      niveau.transactionOpen();
      niveauobj = new NiveauObjetDatabaseLayout();
      niveauobj.transactionOpen();
      niveauTri = new NiveauDatabaseLayout();
      niveauTri.transactionOpen();
    //chercher le bon tri
      niveauTri.afficherLasttree(bean.getIdClient(), this.idObjetParent);
      if (niveauTri.model()!=null)
    	  tri=niveauTri.model().getTri()+1;
     
     
      //if (niveau.verifierDroitAjouterInspection().booleanValue())
      //{ 
       niveau.setClient(bean.getIdClient());
       niveau.setNiveauObjet(this.objetInitial);
      niveau.setNiveauParent(this.idObjetParent);
 
      niveau.model().setLevel(3);
        niveau.model().setLibelle(this.nom);
         niveau.model().setCodeBarre(this.codeBarre);
        
         niveau.model().setTri(tri);
         niveau.model().setMasque(false);
       // niveau.model().setClefTimestamp(new Date());
          
         
         niveau.save();
      // }
 
       niveauobj.afficher(bean.getIdClient(), this.objetInitial);
       niveauobj.model().setExiste(true);
       niveauobj.update();
       niveauobj.transactionCommit();
       

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
      System.out.println("Erreur d'ajoutand go");
e.printStackTrace();
     
 
      return "ajouter_niveau_inspection";
     } finally {
      niveau.close(); niveauobj.close();niveauTri.close();
     }
     return "liste_niveau?faces-redirect=true";
    //return "liste_objet.jsf?faces-redirect=true&id="+this.objetInitial;
   }}
   public String retourback()
   {
	   return "liste_niveau?faces-redirect=true";
	   
   }
   
   public String modifier()
   {
	

     LoginBean bean = LoginUtil.getLoginBean();
    NiveauDatabaseLayout niveau = null;
    NiveauObjetDatabaseLayout nvobjDB=new NiveauObjetDatabaseLayout();
	   nvobjDB.transactionOpen();
	   NiveauObjetDatabaseLayout nvobjDB2=new NiveauObjetDatabaseLayout();
	   nvobjDB2.transactionOpen();
     try {
      niveau = new NiveauDatabaseLayout();
       niveau.transactionOpen();
 
      niveau.afficherbyId(bean.getIdClient(), this.idObjetCourant );
     if( niveau.model().getNiveauobjet()!=null)
     {
    	 nvobjDB.afficherbyid(bean.getIdClient(), niveau.model().getNiveauobjet().getIdNiveauObjet());
    	 if (nvobjDB.model()!=null)
    	 {
    	 nvobjDB.model().setExiste(false);
    	 nvobjDB.update();
    	 nvobjDB.transactionCommit();
     }}
        niveau.model().setLibelle(this.nom);
    niveau.model().setCodeBarre(this.codeBarre);
 niveau.setNiveauObjet(this.objetInitial);
 
      niveau.model().setClefTimestamp(new Date());
 
        niveau.update();
       
 
      niveau.transactionCommit();
      nvobjDB2.afficherbyid(bean.getIdClient(), this.objetInitial);
      if (nvobjDB2.model()!=null)
 	 {
 	 nvobjDB2.model().setExiste(true);
 	 nvobjDB2.update();
 	 nvobjDB2.transactionCommit();
  }

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
      System.out.println("Erreur de modification");
e.printStackTrace();
     
 
       return "modifier_niveau_inspection";
     } finally {
       niveau.close();
       nvobjDB.close();
       nvobjDB2.close();
     }
 
    return "liste_niveau?faces-redirect=true";
   }
 
   public String supprimer()
   {
	
     LoginBean bean = LoginUtil.getLoginBean();
     NiveauDatabaseLayout niveau = null;
     NiveauObjetDatabaseLayout nvobjDB=new NiveauObjetDatabaseLayout();
	   nvobjDB.transactionOpen();
     try {
      niveau = new NiveauDatabaseLayout();
       niveau.transactionOpen();
 
       if (niveau.verifierDroitModifier(bean.getIdClient(), this.idObjetCourant).booleanValue())
       {
    	   if( niveau.model().getNiveauobjet()!=null)
    	     {
    	    	 nvobjDB.afficherbyid(bean.getIdClient(), niveau.model().getNiveauobjet().getIdNiveauObjet());
    	    	 if (nvobjDB.model()!=null)
    	    	 {
    	    	 nvobjDB.model().setExiste(false);
    	    	 nvobjDB.update();
    	    	 nvobjDB.transactionCommit();
    	     }}
         niveau.model().setMasque(true);
        // niveau.model().setClefTimestamp(new Date());
 
        niveau.update();
        

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
 
      niveau.transactionCommit();
     }
     catch (Exception e) {
       System.out.println("Erreur de modification");
       e.printStackTrace();
 
     return "modifier_niveau_inspection";
     } finally {
      niveau.close();
     }
 
     return "liste_niveau?faces-redirect=true";
   }
private Double minValue;
private Double maxValue;
private Double lowBorder;
private Double highBorder;
private String lowBorderAlert;
private String highBorderAlert;
private String unitMeasure;
   public Double getMinValue() {
	return minValue;
}
public void setMinValue(Double minValue) {
	this.minValue = minValue;
}
public Double getMaxValue() {
	return maxValue;
}
public void setMaxValue(Double maxValue) {
	this.maxValue = maxValue;
}
public Double getLowBorder() {
	return lowBorder;
}
public void setLowBorder(Double lowBorder) {
	this.lowBorder = lowBorder;
}
public Double getHighBorder() {
	return highBorder;
}
public void setHighBorder(Double highBorder) {
	this.highBorder = highBorder;
}
public String getLowBorderAlert() {
	return lowBorderAlert;
}
public void setLowBorderAlert(String lowBorderAlert) {
	this.lowBorderAlert = lowBorderAlert;
}
public String getHighBorderAlert() {
	return highBorderAlert;
}
public void setHighBorderAlert(String highBorderAlert) {
	this.highBorderAlert = highBorderAlert;
}

public String getUnitMeasure() {
	return unitMeasure;
}
public void setUnitMeasure(String unitMeasure) {
	this.unitMeasure = unitMeasure;
}
public void ajouterobj()
	 {
	   
	 	LoginBean bean = LoginUtil.getLoginBean();
	 	 NiveauObjetDatabaseLayout niveauObjet = null;
	 	  niveauObjet = new NiveauObjetDatabaseLayout();
	       niveauObjet.transactionOpen();
	      TypeReponseDatabaseLayout typdb= new TypeReponseDatabaseLayout();
	      typdb.transactionOpen();
	      
	        niveauObjet.setClient(bean.getIdClient());
	        if ((this.nomobj.equals(""))|| (this.nomobj==null))
	        	return;
	        
	     	  niveauObjet.model().setLibelle(this.nomobj);
	           niveauObjet.model().setCodeBarre(this.codeBarreobj);
	            niveauObjet.model().setExiste(false);
	            niveauObjet.model().setMasque(false);
	           
	            
	            if (this.choixobj==1)
	            {
	            	typdb.afficherByMode(bean.getIdClient(), 1);
	            	if (typdb!=null)
	            		niveauObjet.setTypeReponse(typdb.model().getIdTypeReponse());
	            	niveauObjet.model().setLowlimit(this.minValue);
	            	niveauObjet.model().setHighlimit(this.maxValue);
	            	niveauObjet.model().setLowborder(this.lowBorder);
	            	niveauObjet.model().setHighborder(this.highBorder);
	            	niveauObjet.model().setLowborderAlert(this.lowBorderAlert);
	            	niveauObjet.model().setHighborderAlert(this.highBorderAlert);
	            	niveauObjet.model().setUnitmeasure(this.unitMeasure);
	            }
	            else // liste 
	            {
	            	
	            		niveauObjet.setTypeReponse(this.listchoisi);
	            		typdb.afficher(bean.getIdClient(), this.listchoisi);
	            		
	            	niveauObjet.model().setUnitmeasure(typdb.model().getLibelle());
	            }
	           niveauObjet.save();
	  
	           
	           niveauObjet.transactionCommit();
	       
	 	niveauObjet.close();
	 	//effacer les champs:
	 	this.nomobj="";
	 	this.codeBarreobj="";
	 	this.choix=0;
	 	this.minValue=null;
	 	this.maxValue=null;
	 	this.lowBorder=null;
	 	this.highBorder=null;
	 	this.lowBorderAlert="";
	 	this.highBorderAlert="";
	 	this.unitMeasure="";

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





 }

