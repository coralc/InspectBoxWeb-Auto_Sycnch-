 package com.inspectbox.web;
 
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.primefaces.event.RowEditEvent; 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;




import com.inspectbox.databaseLayout.ChoixDatabaseLayout;
import com.inspectbox.databaseLayout.SynchDatabaseLayout;
import com.inspectbox.databaseLayout.TerminalDatabaseLayout;
import com.inspectbox.databaseLayout.TypeReponseDatabaseLayout;
import com.inspectbox.model.Choix;
import com.inspectbox.model.SynchPK;
import com.inspectbox.model.Terminal;
import com.inspectbox.objetLayout.ObjetChoix;
import com.inspectbox.utils.LoginUtil;
 
 @ManagedBean(name="listeChoixBean")
 
 @ViewScoped
 public class ListeChoixBean
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private Integer idTypeReponse;
   private Integer triMax;
   private String libelleTypeReponse;
   private String nomlistrep;
   private Boolean nclistrep;
   private String nomliste;
   public String getNomliste() {
	return nomliste;
}

public void setNomliste(String nomliste) {
	this.nomliste = nomliste;
}

private List<ObjetChoix> listeMapChoix = new ArrayList();
 
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

public List<ObjetChoix> getLister()
   {
     return this.listeMapChoix;
   }
 
   public List<ObjetChoix> getListeMapTypeReponser() {
     return this.listeMapChoix;
   }
 
   public void setListeTypeReponse(List<ObjetChoix> listeMapChoix) {
     this.listeMapChoix = listeMapChoix;
   }
 
   public Integer getTriMax()
   {
/*  76 */     return this.triMax;
   }
 
   public void setTriMax(Integer triMax) {
/*  80 */     this.triMax = triMax;
   }
 
   public Integer getIdTypeReponse() {
/*  84 */     return this.idTypeReponse;
   }
 
   public void setIdTypeReponse(Integer idTypeReponse) {
/*  88 */     this.idTypeReponse = idTypeReponse;
   }
 
   public String getLibelleTypeReponse() {
/*  92 */     return this.libelleTypeReponse;
   }
 
   public void setLibelleTypeReponse(String libelleTypeReponse) {
/*  96 */     this.libelleTypeReponse = libelleTypeReponse;
   }
 
   public void afficher()
   {
/* 105 */     LoginBean bean = LoginUtil.getLoginBean();
/* 106 */     TypeReponseDatabaseLayout typeReponse = null;
     try
     {
/* 109 */       typeReponse = new TypeReponseDatabaseLayout();
/* 110 */       typeReponse.transactionOpen();
 
/* 112 */       typeReponse.afficher(bean.getIdClient(), this.idTypeReponse);
/* 113 */       this.libelleTypeReponse = typeReponse.model().getLibelle();
 
/* 115 */       typeReponse.transactionCommit();
     }
     catch (Exception e) {
/* 118 */       e.getMessage();
     } finally {
/* 120 */       typeReponse.close();
     }
 
/* 125 */     if (this.idTypeReponse == null)
       return;
/* 127 */     ChoixDatabaseLayout choix = null;
     try
     {
/* 130 */       choix = new ChoixDatabaseLayout();
/* 131 */       choix.transactionOpen();
 
/* 134 */       choix.lister(bean.getIdClient(), this.idTypeReponse);
 
      Iterator it = choix.liste().iterator();
      while (it.hasNext())
       {
        Choix choixReponse = (Choix)it.next();
 
        ObjetChoix objetChoix = new ObjetChoix();
        objetChoix.setIdChoix(choixReponse.getIdChoix());
/* 145 */         objetChoix.setLibelle(choixReponse.getValeur());
/* 146 */         objetChoix.setNonConforme(Boolean.valueOf(choixReponse.isNonConforme()));
/* 147 */         objetChoix.setTri(Integer.valueOf(choixReponse.getTri()));
/* 148 */         this.triMax = Integer.valueOf(choixReponse.getTri());
 
/* 150 */         this.listeMapChoix.add(objetChoix);
       }
/* 152 */       choix.transactionCommit();
     } catch (Exception e) {
/* 154 */       System.out.println("Erreur liste choix");
/* 155 */       e.getMessage();
     }
     finally
     {
/* 159 */       choix.close();
     }
   }
   public String creerlistrep()
   {
	   System.out.println("nom"+this.nomlistrep);
	   System.out.println("ncg"+this.nclistrep);
	   System.out.println("tr"+this.idTypeReponse);
	   LoginBean bean = LoginUtil.getLoginBean();
	      ChoixDatabaseLayout choix = null;
	        try
	        {
	          choix = new ChoixDatabaseLayout();
	         choix.transactionOpen();
	    
	         choix.setClient(bean.getIdClient());
	         choix.setTypeReponse(this.idTypeReponse);
	    
	         choix.model().setValeur(this.nomlistrep);
	            choix.model().setNonConforme(this.nclistrep.booleanValue());
	   				  if (this.triMax!=null)
	           	choix.model().setTri(this.triMax.intValue() + 1);
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
	        e.getMessage();
	        System.out.println("Erreur ajouter choix");
	        } finally {
	       choix.close();
	        }
	        	       return "liste_choix_reponse?faces-redirect=true&id="+this.idTypeReponse;
	      }
	    
   public String supprimer(Integer idch)
   {
	   
     LoginBean bean = LoginUtil.getLoginBean();
     ChoixDatabaseLayout choix = null;
     try
     {
       choix = new ChoixDatabaseLayout();
       choix.transactionOpen();
 
       choix.afficher(bean.getIdClient(), idch);
 
      choix.model().setMasque(true);
       choix.model().setClefTimestamp(new Date());
 
      choix.update();
 
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
     
       System.out.println("Erreur supprimer choix");
     } finally {
       choix.close();
     }
 
    return "liste_choix_reponse?faces-redirect=true&id=" + this.idTypeReponse;
   }
	   
   public void onEditChoix(RowEditEvent event) { 
	    ObjetChoix och=(( ObjetChoix) event.getObject());
	   LoginBean bean = LoginUtil.getLoginBean();
	   ChoixDatabaseLayout choix = null;
       try
       {
         choix = new ChoixDatabaseLayout();
        choix.transactionOpen();
        choix.afficher(bean.getIdClient(),och.getIdChoix());
        choix.setClient(bean.getIdClient());
        
        
        choix.model().setValeur(och.getLibelle());
           choix.model().setNonConforme(och.getNonConforme());
  				  
           
   
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
       
       System.out.println("Erreur modif choix");
       } finally {
      choix.close();
     
       }
	   
   }
   public void onCancel(RowEditEvent event) {  
	   ObjetChoix och=(( ObjetChoix) event.getObject());
	       
	    }  
   
   public String retourback()
   {
	   return "liste_choix?faces-redirect=true";
   }
   public String modifierlabel()
   {
	        LoginBean bean = LoginUtil.getLoginBean();
	    TypeReponseDatabaseLayout typeReponse = null;
	     try
	     {
	      typeReponse = new TypeReponseDatabaseLayout();
	      typeReponse.transactionOpen();
	 
	       typeReponse.afficher(bean.getIdClient(), this.idTypeReponse);
	 
	       typeReponse.model().setLibelle(this.libelleTypeReponse);
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
	 
	   
   
 }

