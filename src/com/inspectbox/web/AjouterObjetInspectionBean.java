package com.inspectbox.web;
 
 
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import com.inspectbox.databaseLayout.NiveauObjetDatabaseLayout;
import com.inspectbox.databaseLayout.SynchDatabaseLayout;
import com.inspectbox.databaseLayout.TerminalDatabaseLayout;
import com.inspectbox.databaseLayout.TypeReponseDatabaseLayout;
import com.inspectbox.model.SynchPK;
import com.inspectbox.model.Terminal;
import com.inspectbox.model.Typereponse;
import com.inspectbox.utils.LoginUtil;
 
 
@ManagedBean(name="ajouterObjetInspectionBean")



 @ViewScoped
 public class AjouterObjetInspectionBean implements java.io.Serializable
 {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	//variables
	 private String nom = "";
private Integer objtnveau;
private Integer objtparent;
private Integer objtparent2;
private String unitMeasure;

public Integer getObjtparent2() {
	return objtparent2;
}

public void setObjtparent2(Integer objtparent2) {
	this.objtparent2 = objtparent2;
}

public Integer getObjtparent() {
	return objtparent;
}

public void setObjtparent(Integer objtparent) {
	this.objtparent = objtparent;
}

private ArrayList<SelectItem> choixList = new ArrayList();
	 private String modeReponse;
	
	 private String codeBarre = "";
	  
	 
	  
	  private Boolean commentaireAutorise = Boolean.valueOf(true);
	  private Integer choix =null ;
	
	 

	//condtructeur
	
public Integer getObjtnveau() {
		return objtnveau;
	}

	public void setObjtnveau(Integer objtnveau) {
		this.objtnveau = objtnveau;
	}

public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getModeReponse() {
		return modeReponse;
	}

	public void setModeReponse(String modeReponse) {
		this.modeReponse = modeReponse;
	}

	

	public String getCodeBarre() {
		return codeBarre;
	}

	public void setCodeBarre(String codeBarre) {
		this.codeBarre = codeBarre;
	}

	

	

	public ArrayList<SelectItem> getChoixList() {
		return choixList;
	}

	public void setChoixList(ArrayList<SelectItem> choixList) {
		this.choixList = choixList;
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

	public Boolean islist;
public Double minValue;
public Double maxValue;
public Double lowBorder;
public Double highBorder;
public String lowBorderAlert;
public String highBorderAlert;

	//traitement
	 
	 public String getUnitMeasure() {
	return unitMeasure;
}

public void setUnitMeasure(String unitMeasure) {
	this.unitMeasure = unitMeasure;
}

public Boolean getIslist() {
	return islist;
}

public void setIslist(Boolean islist) {
	this.islist = islist;
}

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
public void affichernv()
{
	
}
	public void afficher()
	 {
		 System.out.println(this.objtnveau);
		 LoginBean bean = LoginUtil.getLoginBean();
	 	 NiveauObjetDatabaseLayout niveauObjet = null;
	 	TypeReponseDatabaseLayout choix = null;
	 	  niveauObjet = new NiveauObjetDatabaseLayout();
	       niveauObjet.transactionOpen();
	       choix=new TypeReponseDatabaseLayout();
			 choix.transactionOpen();
	       try
	       {
	    	   niveauObjet.afficher(bean.getIdClient(), this.objtnveau);
	    	   this.nom=niveauObjet.model().getLibelle();
	    	   this.codeBarre=niveauObjet.model().getCodeBarre();
	    	  if (niveauObjet.model().getTypereponse().getMode()==1)
	    	  {
	    		  this.unitMeasure=niveauObjet.model().getUnitmeasure(); this.islist=false;
	    	  }
	    	  else
	    	  {
	    		  choix.afficher(bean.getIdClient(), niveauObjet.model().getTypereponse().getIdTypeReponse());
	    		  this.unitMeasure=choix.model().getLibelle(); this.islist=true;
         		 
	    	  }
	    	  
	    	  if (this.islist==false)
	    	  {
	    		  this.minValue=niveauObjet.model().getLowlimit();
	    		  this.maxValue=niveauObjet.model().getHighlimit();
	    		  this.lowBorder=niveauObjet.model().getLowborder();
	    		  this.highBorder=niveauObjet.model().getHighborder();
	    		  this.lowBorderAlert=niveauObjet.model().getLowborderAlert();
	    		  this.highBorderAlert=niveauObjet.model().getHighborderAlert();
	    		  
	    	  }
	    	  
	    	  
	    	   
	    	   
	       }
	       catch (Exception e) {
   	        
   	      e.printStackTrace();
   	   
   	    
   	       } finally {
         niveauObjet.close();
         choix.close();
   	       }
	       
	 }
	private String page;
	
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String updatev2()
	{
		LoginBean bean = LoginUtil.getLoginBean();
	 	 NiveauObjetDatabaseLayout niveauObjet = null;
	 	niveauObjet = new NiveauObjetDatabaseLayout();
	 	niveauObjet.transactionOpen();
	 	TypeReponseDatabaseLayout typeReponse = null;
	    typeReponse = new TypeReponseDatabaseLayout();
	    typeReponse.transactionOpen();
	    TerminalDatabaseLayout termDB= new TerminalDatabaseLayout();
        termDB.transactionOpen();
        
	 	try{
	 		niveauObjet.afficher(bean.getIdClient(), this.objtnveau);
	 		if (niveauObjet.model().getTypereponse().getMode()==1)
	 		{
	 			niveauObjet.model().setLibelle(this.nom);
		 		 niveauObjet.model().setCodeBarre(this.codeBarre);
		    	
		    	  niveauObjet.model().setUnitmeasure(this.unitMeasure);
		    	  niveauObjet.model().setLowlimit(this.minValue);
		    	  niveauObjet.model().setHighlimit(this.maxValue);
		    	  niveauObjet.model().setLowborder(this.lowBorder);
		    	  niveauObjet.model().setHighborder(this.highBorder);
		    	  niveauObjet.model().setLowborderAlert(this.lowBorderAlert);
		    	  niveauObjet.model().setHighborderAlert(this.highBorderAlert);
		    	  niveauObjet.save();
		    	  niveauObjet.transactionCommit();
	 		}
	 		else
	 		{
	 			niveauObjet.model().setLibelle(this.nom);
		 		 niveauObjet.model().setCodeBarre(this.codeBarre);
		    	
		    	  
		    	   typeReponse.afficher(bean.getIdClient(), niveauObjet.model().getTypereponse().getIdTypeReponse());
		    	   typeReponse.model().setLibelle(this.unitMeasure);
		    	   typeReponse.save();
		    	   typeReponse.transactionCommit();
		    	   niveauObjet.save();
			    	  niveauObjet.transactionCommit();
			    	 
	 		}
	 		
	 		  // save synchro 
	          
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
	 		
	 	}
	 	catch (Exception e) {
   	        System.out.println("Erreur de modification");
   	      e.printStackTrace();
   	   
   	    
   	       } finally {
         niveauObjet.close();
         typeReponse.close(); termDB.close();
         
   	       }
	 	if (page.equals("O"))
	 	{
	 		
	 		
	 			return "liste_objet?faces-redirect=true";	
	 	}
		
	 	else
	 		return "liste_niveau?faces-redirect=true";

	}
	
	 
	 private Integer choixListe;
	 public Integer getChoixListe() {
		return choixListe;
	}

	public void setChoixListe(Integer choixListe) {
		this.choixListe = choixListe;
	}

	public String ajouterobj()
	 {
	 	
	 	LoginBean bean = LoginUtil.getLoginBean();
	 	 NiveauObjetDatabaseLayout niveauObjet = null;
	 	 TypeReponseDatabaseLayout typ= new TypeReponseDatabaseLayout();
	 	  niveauObjet = new NiveauObjetDatabaseLayout();
	       niveauObjet.transactionOpen();
	       typ.transactionOpen();
	       TerminalDatabaseLayout termDB= new TerminalDatabaseLayout();
	          termDB.transactionOpen();
	          
	      try{
	        niveauObjet.setClient(bean.getIdClient());
	        
	     	  niveauObjet.model().setLibelle(this.nom);
	           niveauObjet.model().setCodeBarre(this.codeBarre);
	            niveauObjet.model().setExiste(false);
	            niveauObjet.model().setMasque(false);
	           
	           
	            niveauObjet.setClient(bean.getIdClient());
	            niveauObjet.model().setClefTimestamp(new Date());
	         
	            if (this.choix==1)
	            { 
	            	   typ.afficherByMode(bean.getIdClient(), 1);
	            	niveauObjet.setTypeReponse(typ.model().getIdTypeReponse());
	            	niveauObjet.model().setUnitmeasure(this.unitMeasure);
	            	niveauObjet.model().setLowlimit(this.minValue);
	            	niveauObjet.model().setHighlimit(this.maxValue);
	            	niveauObjet.model().setLowborder(this.lowBorder);
	            	niveauObjet.model().setHighborder(this.highBorder);
	            	niveauObjet.model().setLowborderAlert(this.lowBorderAlert);
	            	niveauObjet.model().setHighborderAlert(this.highBorderAlert);
	            }
	            else
	            {
	            	
	            	niveauObjet.setTypeReponse(choixListe);
	            	typ.afficher(bean.getIdClient(), choixListe);
	            	niveauObjet.model().setUnitmeasure(typ.model().getLibelle());
	            }
	            
	            
	          this.objtnveau= niveauObjet.save();
	          niveauObjet.transactionCommit();
	         
	       // save synchro 
	          
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
	          
		 		
		 		
		 		
		 		 
	      }
	      catch (Exception e) {
	    	        System.out.println("Erreur d'ajout");
	    	       e.printStackTrace();
	    	   
	    	    
	    	       } finally {
	          niveauObjet.close(); 
	          termDB.close();
	    	       }
	       
	 	
	 	
	      return "liste_objet?faces-redirect=true";
	 	//return "ajouter_team_objet?faces-redirect=true&id="+this.objtnveau;   			   
	 }

	 public ArrayList<SelectItem> listTypeReponse()
	   {this.choixList.clear();
	     LoginBean bean = LoginUtil.getLoginBean();
	     TypeReponseDatabaseLayout typeReponse = null;
	     try
	     {
	      typeReponse = new TypeReponseDatabaseLayout();
	 
	       typeReponse.lister(bean.getIdClient());
	     ArrayList list = (ArrayList)typeReponse.liste();
	 
	       for (Integer i = Integer.valueOf(0); i.intValue() < list.size(); i = Integer.valueOf(i.intValue() + 1))
	       {
	       
	 
	        this.choixList.add(new SelectItem(((Typereponse)typeReponse.liste().get(i.intValue())).getIdTypeReponse(), ((Typereponse)typeReponse.liste().get(i.intValue())).getLibelle()));
	       }
	 
	       
	     }
	     catch (Exception e) {
	      e.printStackTrace();
	      e.getMessage();
	      typeReponse.close();
	     }
	 
	    return this.choixList;
	   }
	  
	
	 public String retourback()
	 {
		 return "liste_objet?faces-redirect=true"; 
		 
	 }
	 private ArrayList<SelectItem> objetsInitialList = new ArrayList();
	 
	 public ArrayList<SelectItem> getObjetsInitialList() {
		return objetsInitialList;
	}

	public void setObjetsInitialList(ArrayList<SelectItem> objetsInitialList) {
		this.objetsInitialList = objetsInitialList;
	}

	public ArrayList<SelectItem> listchoix()
	   {
		   if (this.objetsInitialList.size()>0)
			   this.objetsInitialList.clear();
	    LoginBean bean = LoginUtil.getLoginBean();
	    TypeReponseDatabaseLayout listObjetsInitial = null;
	    
	     try
	     {
	     listObjetsInitial = new TypeReponseDatabaseLayout();
	     
		 
		  
	      listObjetsInitial.listerListe(bean.getIdClient(), "Liste");
	      
	      
	      

	       ArrayList list = (ArrayList)listObjetsInitial.liste();
	 
	      for (Integer i = Integer.valueOf(0); i.intValue() < list.size(); i = Integer.valueOf(i.intValue() + 1))
	       { 
	    	  
	    		
	  			this.objetsInitialList.add(new SelectItem(((Typereponse)listObjetsInitial.liste().get(i.intValue())).getIdTypeReponse(), ((Typereponse)listObjetsInitial.liste().get(i.intValue())).getLibelle()));
	  		
	  		
	     
	     }}
	     catch (Exception e)
	     {
	       
	       
	e.printStackTrace();
	     } finally {
	      listObjetsInitial.close();
	     
	      
	     }
	 
	     return this.objetsInitialList;
	   }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
 }
 