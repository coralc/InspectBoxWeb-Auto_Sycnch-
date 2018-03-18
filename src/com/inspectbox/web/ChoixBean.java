 package com.inspectbox.web;
 
 import java.util.Date;
import java.util.Iterator;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Size;

import com.inspectbox.databaseLayout.ChoixDatabaseLayout;
import com.inspectbox.databaseLayout.SynchDatabaseLayout;
import com.inspectbox.databaseLayout.TerminalDatabaseLayout;
import com.inspectbox.databaseLayout.TypeReponseDatabaseLayout;
import com.inspectbox.model.SynchPK;
import com.inspectbox.model.Terminal;
import com.inspectbox.utils.LoginUtil;
 
 @ManagedBean(name="choixBean")
 @RequestScoped
 public class ChoixBean
 {
   private Integer idChoix;
   private Integer idTypeReponse;
   private Integer tri;
   private Boolean nonConforme;
   private String libelleTypeReponse;
 
   @Size(min=1, max=254)
   private String nom;
 
   public String getNom()
   {
     return this.nom;
   }
 
   public void setNom(String nom)
   {
    this.nom = nom;
   }
 
   public Integer getIdChoix()
   {
    return this.idChoix;
   }
 
   public void setIdChoix(Integer idChoix)
   {
     this.idChoix = idChoix;
   }
 
   public Integer getIdTypeReponse()
   {
     return this.idTypeReponse;
   }
 
   public void setIdTypeReponse(Integer idTypeReponse)
   {
    this.idTypeReponse = idTypeReponse;
   }
 
   public Integer getTri()
   {
     return this.tri;
   }
 
   public void setTri(Integer tri)
   {
     this.tri = tri;
   }
 
   public Boolean getNonConforme()
   {
    return this.nonConforme;
   }
 
   public void setNonConforme(Boolean nonConforme)
   {
     this.nonConforme = nonConforme;
   }
 
   public String getLibelleTypeReponse()
   {
     return this.libelleTypeReponse;
   }
 
   public void setLibelleTypeReponse(String libelleTypeReponse)
   {
/* 116 */     this.libelleTypeReponse = libelleTypeReponse;
   }
 
   public void afficher()
   {
/* 126 */     LoginBean bean = LoginUtil.getLoginBean();
/* 127 */     TypeReponseDatabaseLayout typeReponse = null;
     try
     {
/* 130 */       typeReponse = new TypeReponseDatabaseLayout();
/* 131 */       typeReponse.transactionOpen();
 
/* 133 */       typeReponse.afficher(bean.getIdClient(), this.idTypeReponse);
/* 134 */       this.libelleTypeReponse = typeReponse.model().getLibelle();
 
/* 136 */       typeReponse.transactionCommit();
     }
     catch (Exception e) {
/* 139 */       e.getMessage();
     } finally {
/* 141 */       typeReponse.close();
     }
 
/* 145 */     if (this.idChoix == null)
       return;
/* 147 */     ChoixDatabaseLayout choix = null;
     try
     {
/* 150 */       choix = new ChoixDatabaseLayout();
/* 151 */       choix.transactionOpen();
 
/* 153 */       choix.afficher(bean.getIdClient(), this.idChoix);
 
/* 155 */       this.nom = choix.model().getValeur();
 
/* 157 */       choix.transactionCommit();
     }
     catch (Exception e) {
/* 160 */       System.out.println("Erreur afficher choix");
/* 161 */      e.printStackTrace();
     } finally {
/* 163 */       choix.close();
     }
   }
 
   public String ajouter()
   {
	    LoginBean bean = LoginUtil.getLoginBean();
     ChoixDatabaseLayout choix = null;
     try
     {
       choix = new ChoixDatabaseLayout();
      choix.transactionOpen();
 
       choix.setClient(bean.getIdClient());
       choix.setTypeReponse(this.idTypeReponse);
 

       
        choix.model().setValeur(this.nom);
         choix.model().setNonConforme(this.nonConforme.booleanValue());
				  if (this.tri!=null)
         	choix.model().setTri(this.tri.intValue() + 1);
 			else
					choix.model().setTri(1);
        choix.model().setMasque(false);
 
       choix.model().setClefTimestamp(new Date());
         choix.save();
       
 	
       choix.transactionCommit();
       

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
       choix.close();
     }
 
    return "liste_choix_reponse?faces-redirect=true&id=" + this.idTypeReponse;
   }
 
   public String modifier()
   {
    return "liste_utilisateur?faces-redirect=true";
   }
 
   public String supprimer()
   {
     LoginBean bean = LoginUtil.getLoginBean();
     ChoixDatabaseLayout choix = null;
     TerminalDatabaseLayout termDB= new TerminalDatabaseLayout();
     termDB.transactionOpen();
     try
     {
       choix = new ChoixDatabaseLayout();
       choix.transactionOpen();
 
       choix.afficher(bean.getIdClient(), this.idChoix);
 
       choix.model().setMasque(true);
       choix.model().setClefTimestamp(new Date());
 
      choix.update();
 
       choix.transactionCommit();
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
      e.printStackTrace();
       System.out.println("Erreur supprimer choix");
     } finally {
       choix.close();  termDB.close();  
     }
 
    return "liste_choix_reponse?faces-redirect=true&id=" + this.idTypeReponse;
   }
 }

