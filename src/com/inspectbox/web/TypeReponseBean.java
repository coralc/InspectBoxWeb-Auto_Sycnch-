 package com.inspectbox.web;
 
 import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Size;

import com.inspectbox.databaseLayout.ChoixDatabaseLayout;
import com.inspectbox.databaseLayout.NiveauObjetDatabaseLayout;
import com.inspectbox.databaseLayout.SynchDatabaseLayout;
import com.inspectbox.databaseLayout.TerminalDatabaseLayout;
import com.inspectbox.databaseLayout.TypeReponseDatabaseLayout;
import com.inspectbox.model.Choix;
import com.inspectbox.model.SynchPK;
import com.inspectbox.model.Terminal;
import com.inspectbox.objetLayout.ObjetChoix;
import com.inspectbox.utils.I18nUtil;
import com.inspectbox.utils.LoginUtil;
 
 @ManagedBean(name="typeReponseBean")
 @RequestScoped
 @ViewScoped
 public class TypeReponseBean implements java.io.Serializable
 {
   private Integer idTypeReponse;
 
   private List<ObjetChoix> listech = new ArrayList();
   public List<ObjetChoix> getLister()
   {
     return this.listech;
   }
 
   public List<ObjetChoix> getListeMapTypeReponser() {
     return this.listech;
   }
 
   public void setListeTypeReponse(List<ObjetChoix> listech) {
     this.listech = listech;
   }
   private Integer trimaxl;
   public Integer getTrimaxl() {
	return trimaxl;
}

public void setTrimaxl(Integer trimaxl) {
	this.trimaxl = trimaxl;
}
private String nom="";
   private String nomlistrep;
   private Boolean nclistrep;
   public String getNomlistrep() {
	return nomlistrep;
}

public void setNomlistrep(String nomlistrep) {
	this.nomlistrep = nomlistrep;
}

public Boolean getNclistrep() {
	return nclistrep;
}

public void setNclistrep(Boolean nclistrep) {
	this.nclistrep = nclistrep;
}

private Boolean supprimable;
 	private String modeReponse;

			
		public String getModeReponse() {
			return this.modeReponse;
		}
		public void setModeReponse(String modeReponse) {
			this.modeReponse = modeReponse;
		}
		
		
		
   public String getNom()
   {
    return this.nom;
   }
 
   public void setNom(String nom)
   {
     this.nom = nom;
   }
 
   public Integer getIdTypeReponse()
   {
     return this.idTypeReponse;
   }
 
   public void setIdTypeReponse(Integer idTypeReponse)
   {
    this.idTypeReponse = idTypeReponse;
   }
 
   public Boolean getSupprimable()
   {
/*  71 */     return this.supprimable;
   }
 
   public void setSupprimable(Boolean supprimable)
   {
/*  77 */     this.supprimable = supprimable;
   }
 
   public void afficher()
   {
/*  87 */     if (this.idTypeReponse == null)
       return;
/*  89 */     LoginBean bean = LoginUtil.getLoginBean();
 
/*  91 */     TypeReponseDatabaseLayout typeReponse = null;
     try
     {
/*  94 */       typeReponse = new TypeReponseDatabaseLayout();
/*  95 */       typeReponse.transactionOpen();
 
/*  97 */       typeReponse.afficher(bean.getIdClient(), this.idTypeReponse);
 
/*  99 */       this.nom = typeReponse.model().getLibelle();
 
/* 103 */       NiveauObjetDatabaseLayout niveau = new NiveauObjetDatabaseLayout();
/* 104 */       this.supprimable = niveau.isNoTypeReponseLink(this.idTypeReponse);
 
/* 107 */       typeReponse.transactionCommit();
     }
     catch (Exception e) {
/* 110 */       System.out.println("Erreur afficher type réponse");
/* 111 */       e.getMessage();
     } finally {
/* 113 */       typeReponse.close();
     }
   }
   private String err;
   public String getErr() {
	return err;
}
public void setErr(String err) {
	this.err = err;
}
/* Function to add a new typereponse field from ajouter_choix.xhtml*/
public String ajouter()
   { 
	   
	   if (( (this.nom.equals("")) &&(this.nom==null)) || ((this.modeReponse.equals(""))&& (this.modeReponse==null))  ) 
	   {
			this.err=I18nUtil.get("form_erreur_ajout_choix");
		   FacesContext context = FacesContext.getCurrentInstance();  
	        
	        context.addMessage(null, new FacesMessage(err));  
	        
		return "ajouter_choix?faces-redirect=true";
	   }
	   
	else
	   {
     LoginBean bean = LoginUtil.getLoginBean();
     TypeReponseDatabaseLayout typeReponse = null;
				int reponseTypeNewID=0;
     try
     {
       typeReponse = new TypeReponseDatabaseLayout();
       typeReponse.transactionOpen();
 
       typeReponse.setClient(bean.getIdClient());
 
       if (typeReponse.verifierDroitAjouterTypeReponse().booleanValue())
       {
         typeReponse.model().setLibelle(this.nom);

				  typeReponse.model().setModeReponse(this.modeReponse); 
				  /*
				   *  this.modeReponse is selected from ajouter_choix.xhtml :
				   *  <h:selectOneRadio value="#{typeReponseBean.modeReponse}" id="typemode" layout="pageDirection" onclick="showvalue()"  >
			   	<f:selectItem itemValue="Liste" itemLabel="#{msg.form_choix_liste}" id="listechoix" />
			   	<f:selectItem itemValue="Valeur" itemLabel="#{msg.form_choix_valeur}" id="valuechoix"  />
			   	</h:selectOneRadio>
				   */
        typeReponse.model().setMasque(false);
        
         typeReponse.model().setClefTimestamp(new Date());
        reponseTypeNewID=typeReponse.save();
        

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
       typeReponse.transactionCommit();

     }
     catch (Exception e) {
       e.printStackTrace();
     } finally {
       typeReponse.close();
     }
 
			
						}
			
			/* if its a LISTE type go to liste_choix.xhtml to define the element of liste*/

    return "liste_choix?faces-redirect=true";
   }
   
   public String retourback()
   
   {
	   
	   return "liste_choix?faces-redirect=true";
   }
   public String modifier()
   {
     LoginBean bean = LoginUtil.getLoginBean();
     TypeReponseDatabaseLayout typeReponse = null;
     try
     {
       typeReponse = new TypeReponseDatabaseLayout();
       typeReponse.transactionOpen();
 
       typeReponse.afficher(bean.getIdClient(), this.idTypeReponse);
 
       typeReponse.model().setLibelle(this.nom);
       typeReponse.model().setClefTimestamp(new Date());
 
       typeReponse.update();
 
       typeReponse.transactionCommit();

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
       typeReponse.close();
     }
 
    return "liste_choix?faces-redirect=true";
   }
  
   public String supprimer()
   {
     LoginBean bean = LoginUtil.getLoginBean();
     TypeReponseDatabaseLayout typeReponse = null;
     try
     {
       typeReponse = new TypeReponseDatabaseLayout();
       typeReponse.transactionOpen();
 
       typeReponse.afficher(bean.getIdClient(), this.idTypeReponse);
 
       typeReponse.model().setMasque(true);
      typeReponse.model().setClefTimestamp(new Date());
 
      typeReponse.update();
 
      typeReponse.transactionCommit();
      

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
      e.getMessage();
     } finally {
       typeReponse.close();
     }
 
    return "liste_choix?faces-redirect=true";
   }
   
   @SuppressWarnings("null")
public String creerlistrep()
   {
	   System.out.println("nom"+this.nom+this.modeReponse);
	   Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		if (locale.equals("de"))
		this.err="Bezeichnung müssen ausgefüllt werden!";
		else if (locale.equals("fr"))
			this.err="Le Libellé doit être renseigné!";
		else
			this.err="Label must be filled!";
	   if (  (this.nom==null) ||  (this.modeReponse==null))  
	   {
		   FacesContext context = FacesContext.getCurrentInstance();  
	        
	        context.addMessage(null, new FacesMessage(err));  
	        return "ajouter_choix?faces-redirect=true";
	         }
	   
	else
	   {
    LoginBean bean = LoginUtil.getLoginBean();
    TypeReponseDatabaseLayout typeReponse = null;
				int reponseTypeNewID=0;
    try
    {
      typeReponse = new TypeReponseDatabaseLayout();
      typeReponse.transactionOpen();

      typeReponse.setClient(bean.getIdClient());

      if (typeReponse.verifierDroitAjouterTypeReponse().booleanValue())
      {
        typeReponse.model().setLibelle(this.nom);

				  typeReponse.model().setModeReponse(this.modeReponse);
       typeReponse.model().setMasque(false);

       typeReponse.model().setClefTimestamp(new Date());
       reponseTypeNewID=typeReponse.save();
       

         
					
      }
      typeReponse.transactionCommit();

    }
    catch (Exception e) {
    	e.printStackTrace();
      e.getMessage();
    } finally {
      typeReponse.close();
    }
    ChoixDatabaseLayout choix2 = null;
    choix2 = new ChoixDatabaseLayout();
    ChoixDatabaseLayout choix = null;
    choix = new ChoixDatabaseLayout();
    try
    {
    	System.out.println("reponseTypeNewID"+reponseTypeNewID);
    	choix2.lister(bean.getIdClient(), reponseTypeNewID);
        
        Iterator it = choix2.liste().iterator();
        while (it.hasNext())
        {
        	Choix choixReponse2 = (Choix)it.next();
        	ObjetChoix objetChoix2 = new ObjetChoix();
        	this.trimaxl = Integer.valueOf(choixReponse2.getTri());
        	objetChoix2.setIdChoix(choixReponse2.getIdChoix());
        	objetChoix2.setLibelle(choixReponse2.getValeur());
            objetChoix2.setNonConforme(Boolean.valueOf(choixReponse2.isNonConforme()));
           objetChoix2.setTri(Integer.valueOf(choixReponse2.getTri()));
             this.listech.add(objetChoix2);
        	
        }
        if ((this.nomlistrep!=null) && (! this.nomlistrep.equals("")))
        {
       choix = new ChoixDatabaseLayout();
           choix.transactionOpen();
     
          choix.setClient(bean.getIdClient());
          choix.setTypeReponse(reponseTypeNewID);
     
    
            choix.model().setValeur(this.nomlistrep);
             choix.model().setNonConforme(this.nclistrep.booleanValue());
    				  if (this.trimaxl!=null)
           	choix.model().setTri(this.trimaxl.intValue() + 1);
     			else
    					choix.model().setTri(1);
            choix.model().setMasque(false);
     
          choix.model().setClefTimestamp(new Date());
            choix.save();
          
          choix.transactionCommit();
        }
        

     // save synchro 
     TerminalDatabaseLayout termDB= new TerminalDatabaseLayout();
     	          termDB.transactionOpen();
     	           

     	          
     	          termDB.listerAll();
     	          Iterator its = termDB.liste().iterator();
     	          while (its.hasNext())
     		        {
     		          Terminal term = (Terminal)its.next();
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
           System.out.println("Erreur ajouter choix");
         } finally {
        	 choix2.close();
           choix.close();
         }
    }
	   return "liste_choix?faces-redirect=true";
	   }
   }
 

