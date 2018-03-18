 package com.inspectbox.web;
 
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import com.inspectbox.databaseLayout.ChoixDatabaseLayout;
import com.inspectbox.databaseLayout.SynchDatabaseLayout;
import com.inspectbox.databaseLayout.TerminalDatabaseLayout;
import com.inspectbox.databaseLayout.TypeReponseDatabaseLayout;
import com.inspectbox.model.Choix;
import com.inspectbox.model.SynchPK;
import com.inspectbox.model.Terminal;
import com.inspectbox.model.Typereponse;
import com.inspectbox.objetLayout.ObjetChoix;
import com.inspectbox.objetLayout.ObjetNavigation;
import com.inspectbox.utils.LoginUtil;
 
 @ManagedBean(name="listeTypeReponsesBean")

@ViewScoped
 public class ListeTypeReponsesBean
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private List<ObjetNavigation> listeMapTypeReponse = new ArrayList();
   private List<ObjetChoix> listeMapchoix = new ArrayList();
   public List<ObjetChoix> getListeMapchoix() {
	return listeMapchoix;
}

public void setListeMapchoix(List<ObjetChoix> listeMapchoix) {
	this.listeMapchoix = listeMapchoix;
}

public List<ObjetNavigation> getLister()
   {
     return this.listeMapTypeReponse;
   }
 
   public List<ObjetNavigation> getListeMapTypeReponser() {
     return this.listeMapTypeReponse;
   }
 
   public void setListeTypeReponse(List<ObjetNavigation> listeMapTypeReponse) {
     this.listeMapTypeReponse = listeMapTypeReponse;
   }
 
public String retourback()

{
	   
	   return "liste_choix?faces-redirect=true";
}
   public void afficher()
   {
	   this.listeMapTypeReponse.clear();
     LoginBean bean = LoginUtil.getLoginBean();
 
     TypeReponseDatabaseLayout typeReponse = null;
     try
     {
       typeReponse = new TypeReponseDatabaseLayout();
 
       typeReponse.listerListe(bean.getIdClient(),"Liste");
 
       Iterator it = typeReponse.liste().iterator();
       while (it.hasNext())
       {
         Typereponse reponse = (Typereponse)it.next();
 
         ObjetNavigation objetTypeReponse = new ObjetNavigation();
         objetTypeReponse.setNum(reponse.getIdTypeReponse());
        objetTypeReponse.setLibelle(reponse.getLibelle());
 		 
				objetTypeReponse.setModeReponse(reponse.getModeReponse());
         this.listeMapTypeReponse.add(objetTypeReponse);
       }
     } catch (Exception e) {
       System.out.println("Erreur liste utilisateurs");
       e.printStackTrace();
     }
     finally
     {
      typeReponse.close();
     }
   }
   private Integer idobjet;
public Integer getIdobjet() {
	return idobjet;
}

public void setIdobjet(Integer idobjet) {
	this.idobjet = idobjet;
}

public void voir(Integer num)
{this.listeMapchoix.clear();
	LoginBean bean = LoginUtil.getLoginBean();
	ChoixDatabaseLayout typeReponse = null;
	  try
	  {
	    typeReponse = new ChoixDatabaseLayout();
	    typeReponse.transactionOpen();

	    typeReponse.lister(bean.getIdClient(), num);
	    Iterator it = typeReponse.liste().iterator();
	       while (it.hasNext())
	       {
	         Choix reponse = (Choix)it.next();
	 
	         ObjetChoix objetTypeReponse = new ObjetChoix();
	         objetTypeReponse.setIdChoix(reponse.getIdChoix());
	        objetTypeReponse.setLibelle(reponse.getValeur());
	        objetTypeReponse.setNonConforme(reponse.isNonConforme());
	        objetTypeReponse.setIdTyp(num);
					
	         this.listeMapchoix.add(objetTypeReponse);
	       }
	      this.idobjet=num;
	  }
	  catch (Exception e) {
		  e.printStackTrace();
	   
	  } finally {
	    typeReponse.close();
	  }
}
public String supprimer2(Integer num)
{
	
  LoginBean bean = LoginUtil.getLoginBean();
  TypeReponseDatabaseLayout typeReponse = null;
  try
  {
    typeReponse = new TypeReponseDatabaseLayout();
    typeReponse.transactionOpen();

    typeReponse.afficher(bean.getIdClient(), num);

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
	  e.printStackTrace();
   e.getMessage();
  } finally {
    typeReponse.close();
  }

 return "liste_choix?faces-redirect=true";
}
public void onEditChoix (RowEditEvent event)
{
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
    
    } finally {
   choix.close();
  
    }
}
public void  onEditrep(RowEditEvent event) { 
	
	ObjetNavigation och=(( ObjetNavigation) event.getObject());
	   LoginBean bean = LoginUtil.getLoginBean();
	   TypeReponseDatabaseLayout choix = null;
    try
    {
      choix = new TypeReponseDatabaseLayout();
     choix.transactionOpen();
     choix.afficher(bean.getIdClient(),och.getNum());
    
     
     
     choix.model().setLibelle(och.getLibelle());
       
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
    
    System.out.println("Erreur modif choix");
    } finally {
   choix.close();
  
    }
	   
}
public void onCancel(RowEditEvent event) {  
	   
	       
	    }  
private String nomlist;
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

public String getNomlist() {
	return nomlist;
}

public void setNomlist(String nomlist) {
	this.nomlist = nomlist;
}
public String creer()
{	LoginBean bean = LoginUtil.getLoginBean();
	TypeReponseDatabaseLayout typ = null;
    try
    {
      typ = new TypeReponseDatabaseLayout();
     typ.transactionOpen();
     typ.model().setLibelle(this.nomlist);
     typ.setClient(bean.getIdClient());
     typ.model().setClefTimestamp(new Date());
     typ.model().setMasque(false);
     typ.model().setModeReponse("Liste");
     typ.model().setMode(2);
     typ.save();
     typ.transactionCommit();
     

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
    	typ.close();
  
    }
    return "liste_choix?faces-redirect=true";
}

public void supprimerchoixrep(Integer idch)
{
	   System.out.println("idch"+idch);
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
   
  } finally {
    choix.close(); voir(idobjet);
  }

 
}

public void creerlistrep()
{
	   
	   LoginBean bean = LoginUtil.getLoginBean();
	      ChoixDatabaseLayout choix = null;
	      ChoixDatabaseLayout choixtri = null;
	      Integer tri=0;
	        try
	        {
	          choix = new ChoixDatabaseLayout();
	         choix.transactionOpen();
	         choixtri= new ChoixDatabaseLayout();
	         choixtri.transactionOpen();
	         // chercher le tri
	         choixtri.lister(bean.getIdClient(), idobjet);
	         tri=choixtri.liste().size()+1;
	         
	         
	         choix.setClient(bean.getIdClient());
	         choix.setTypeReponse(idobjet);
	    
	         choix.model().setValeur(this.nomlistrep);
	            choix.model().setNonConforme(this.nclistrep.booleanValue());
	            choix.model().setTri(tri);
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
	       
	        System.out.println("Erreur ajouter choix");
	        } finally {
	       choix.close();this.nomlistrep=""; this.nclistrep=null;
	       voir(idobjet);
	        }
	        	       
	      }
 }

