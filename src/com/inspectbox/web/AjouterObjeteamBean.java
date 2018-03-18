package com.inspectbox.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.RowEditEvent;

import com.inspectbox.databaseLayout.NiveauDatabaseLayout;
import com.inspectbox.databaseLayout.NiveauObjetDatabaseLayout;
import com.inspectbox.databaseLayout.ObjeteamDatabaseLayout;
import com.inspectbox.databaseLayout.SynchDatabaseLayout;
import com.inspectbox.databaseLayout.TerminalDatabaseLayout;
import com.inspectbox.databaseLayout.TypeReponseDatabaseLayout;
import com.inspectbox.databaseLayout.UtilisateurDatabaseLayout;
import com.inspectbox.model.Objeteam;
import com.inspectbox.model.SynchPK;
import com.inspectbox.model.Terminal;
import com.inspectbox.model.Typereponse;
import com.inspectbox.model.Utilisateur;
import com.inspectbox.objetLayout.ObjetObjeteam;
import com.inspectbox.utils.LoginUtil;
@ManagedBean(name="ajouterObjeteamBean")



 @ViewScoped
public class AjouterObjeteamBean implements java.io.Serializable {
	
	private Integer idObj;
	private Integer objtparent;
	private String nom="";
	private Integer teamchoisi;
	private boolean lunchoisi;
	private boolean marchoisi;
	private boolean merchoisi;
	private boolean jeuchoisi;
	private boolean venchoisi;
	private boolean samchoisi;
	private boolean dimchoisi;
	
	 private List<ObjetObjeteam> teamselect = new ArrayList();
	 private ArrayList<SelectItem> choixteam = new ArrayList();
	 

	public Integer getObjtparent() {
		return objtparent;
	}

	public void setObjtparent(Integer objtparent) {
		this.objtparent = objtparent;
	}

	public Integer getIdObj() {
		return idObj;
	}

	public void setIdObj(Integer idObj) {
		this.idObj = idObj;
	}
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<ObjetObjeteam> getTeamselect() {
		return teamselect;
	}

	public void setTeamselect(List<ObjetObjeteam> teamselect) {
		this.teamselect = teamselect;
	}

	public Integer getTeamchoisi() {
		return teamchoisi;
	}

	public void setTeamchoisi(Integer teamchoisi) {
		this.teamchoisi = teamchoisi;
	}

	public ArrayList<SelectItem> getChoixteam() {
		return choixteam;
	}

	public void setChoixteam(ArrayList<SelectItem> choixteam) {
		this.choixteam = choixteam;
	}

	

	

	public boolean isLunchoisi() {
		return lunchoisi;
	}

	public void setLunchoisi(boolean lunchoisi) {
		this.lunchoisi = lunchoisi;
	}

	public boolean isMarchoisi() {
		return marchoisi;
	}

	public void setMarchoisi(boolean marchoisi) {
		this.marchoisi = marchoisi;
	}

	public boolean isMerchoisi() {
		return merchoisi;
	}

	public void setMerchoisi(boolean merchoisi) {
		this.merchoisi = merchoisi;
	}

	public boolean isJeuchoisi() {
		return jeuchoisi;
	}

	public void setJeuchoisi(boolean jeuchoisi) {
		this.jeuchoisi = jeuchoisi;
	}

	public boolean isVenchoisi() {
		return venchoisi;
	}

	public void setVenchoisi(boolean venchoisi) {
		this.venchoisi = venchoisi;
	}

	public boolean isSamchoisi() {
		return samchoisi;
	}

	public void setSamchoisi(boolean samchoisi) {
		this.samchoisi = samchoisi;
	}

	public boolean isDimchoisi() {
		return dimchoisi;
	}

	public void setDimchoisi(boolean dimchoisi) {
		this.dimchoisi = dimchoisi;
	}

	public void afficher()
	{
		System.out.println("ici idobj**"+this.idObj);
		
		 LoginBean bean = LoginUtil.getLoginBean();
	 	 NiveauObjetDatabaseLayout niveauObjet = null;
	 	  niveauObjet = new NiveauObjetDatabaseLayout();
	 	  ObjeteamDatabaseLayout objteamDB=null;
		     objteamDB=new ObjeteamDatabaseLayout();
		     UtilisateurDatabaseLayout utilisateur = null;
		        utilisateur = new UtilisateurDatabaseLayout();
		        utilisateur.transactionOpen();
	       niveauObjet.transactionOpen();
	       objteamDB.transactionOpen();
	       try
	       {
	    	   niveauObjet.afficher(bean.getIdClient(), this.idObj);
	    	   this.nom=niveauObjet.model().getLibelle();
	    	   if (this.teamselect.size()>0)
	    		   this.teamselect.clear();
	    	   objteamDB.listerTeam(this.idObj) ;	
		       Iterator it = objteamDB.liste().iterator();
		       while (it.hasNext())
			      {
			     	Objeteam ot =(Objeteam)it.next();
			     	
			        utilisateur.afficherByTeamId(ot.getTeam().getIdUtilisateur());
			     	ObjetObjeteam obt=new ObjetObjeteam();
			     //	obt.setIdTeam(ot.getIdTeam());
			     	obt.setLibelle(utilisateur.model().getCodeAcces());
			     	obt.setIdField(ot.getIdField());
			     	obt.setLun(ot.isLun());
			     
			     	obt.setMar(ot.isMar());
			     	obt.setMer(ot.isMer());
			     	obt.setJeu(ot.isJeu());
			     	obt.setVen(ot.isVen());
			     	obt.setSam(ot.isSam());
			     	obt.setDim(ot.isDim());
			     	
			     	this.teamselect.add(obt);
			      }
		       utilisateur.listerTeam(bean.getIdClient());
		 	  
		 	  ArrayList list = (ArrayList)utilisateur.liste();
		 	  
		 	  for (Integer i = Integer.valueOf(0); i.intValue() < list.size(); i = Integer.valueOf(i.intValue() + 1))
		 	    {
		 	    Integer numid=((Utilisateur)utilisateur.liste().get(i.intValue())).getIdUtilisateur();
		 	    objteamDB.listerbyteamObjet(this.idObj,numid);
		 	    if (objteamDB.model()==null)
		 	    {
		 	    	System.out.println("numid"+((Utilisateur)utilisateur.liste().get(i.intValue())).getIdUtilisateur());
		 	     this.choixteam.add(new SelectItem(((Utilisateur)utilisateur.liste().get(i.intValue())).getIdUtilisateur(), ((Utilisateur)utilisateur.liste().get(i.intValue())).getCodeAcces()));
		 	    }
		 	    }
		 	   
	       }
	       catch (Exception e) {
   	        System.out.println("Erreur affichage");
   	      e.printStackTrace();
   	   
   	    
   	       } finally {
         niveauObjet.close();
         objteamDB.close();
         utilisateur.close();
   	       }
	     
		     
			 
		       
			     
			     
			     
	}
	 public void onCancel(RowEditEvent event) {
		 
	   }
	 
	 public void onEditTeam(RowEditEvent event) { 
		   
		  
		   ObjetObjeteam objt=(( ObjetObjeteam) event.getObject());
		   LoginBean bean = LoginUtil.getLoginBean();
		   
		   ObjeteamDatabaseLayout objteamDB=null;
		     objteamDB=new ObjeteamDatabaseLayout();
		     objteamDB.transactionOpen();
		    try{
		    	objteamDB.listerbyId(objt.getIdField());
		    	objteamDB.model().setLun(objt.isLun());
		    	objteamDB.model().setMar(objt.isMar());
		    	objteamDB.model().setMer(objt.isMer());
		    	objteamDB.model().setJeu(objt.isJeu());
		    	objteamDB.model().setVen(objt.isVen());
		    	objteamDB.model().setSam(objt.isSam());
		    	objteamDB.model().setDim(objt.isDim());
		    	objteamDB.save();
		    	objteamDB.transactionCommit();

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
	        System.out.println("Erreur modif rapide");
	      e.printStackTrace();
	   
	    
	       } finally {
  
   objteamDB.close();
  
	       }
		   
		   
	 }
	 public String creerObjetTeam()
	 {
		  LoginBean bean = LoginUtil.getLoginBean();
		 ObjeteamDatabaseLayout objteamDB=null;
	     objteamDB=new ObjeteamDatabaseLayout();
	     objteamDB.transactionOpen();
	    try{
	    	if((this.teamchoisi!=0)&& (this.teamchoisi!=null))
	    	{
	    	objteamDB.setClient(bean.getIdClient());
	    	objteamDB.model().setIdObjet(this.idObj);
	    	objteamDB.setUtilisateur(this.teamchoisi);
	    	objteamDB.model().setLun(this.lunchoisi);
	    	objteamDB.model().setMar(this.marchoisi);
	    	objteamDB.model().setMer(this.merchoisi);
	    	objteamDB.model().setJeu(this.jeuchoisi);
	    	objteamDB.model().setVen(this.venchoisi);
	    	objteamDB.model().setSam(this.samchoisi);
	    	objteamDB.model().setDim(this.dimchoisi);
	    	objteamDB.model().setMasque(false);
	    	objteamDB.save();
	    	objteamDB.transactionCommit();
	    	

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
	        System.out.println("Erreur modif rapide");
	      e.printStackTrace();


	       } finally {

	    objteamDB.close();

	       }
		 
		 return "ajouter_team_objet?faces-redirect=true&id="+this.idObj; 
	 }
public String supprimerTeam(Integer idfield)
{
	 ObjeteamDatabaseLayout objteamDB=null;
     objteamDB=new ObjeteamDatabaseLayout();
     objteamDB.transactionOpen();
    try{
    	objteamDB.listerbyId(idfield);
    	
    	objteamDB.model().setMasque(true);
    	objteamDB.save();
    	objteamDB.transactionCommit();
    	

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
    System.out.println("Erreur modif rapide");
  e.printStackTrace();


   } finally {

objteamDB.close();

   }
    return "ajouter_team_objet?faces-redirect=true&id="+this.idObj;
}

public ArrayList<SelectItem> listTeam()
{
	if (this.choixteam.size()>0)
	this.choixteam.clear();
	
  LoginBean bean = LoginUtil.getLoginBean();
  UtilisateurDatabaseLayout utilisateur = null;
  utilisateur = new UtilisateurDatabaseLayout();
  utilisateur.transactionOpen();
  ObjeteamDatabaseLayout objteamDB=null;
  objteamDB=new ObjeteamDatabaseLayout();
  objteamDB.transactionOpen();
  try
  {
	  utilisateur.listerTeam(bean.getIdClient());
	  
	  ArrayList list = (ArrayList)utilisateur.liste();
	  
	  for (Integer i = Integer.valueOf(0); i.intValue() < list.size(); i = Integer.valueOf(i.intValue() + 1))
	    {
	    Integer numid=((Utilisateur)utilisateur.liste().get(i.intValue())).getIdUtilisateur();
	    objteamDB.listerbyteamObjet(this.idObj,numid);
	    if (objteamDB.model()==null)
	     this.choixteam.add(new SelectItem(((Utilisateur)utilisateur.liste().get(i.intValue())).getIdUtilisateur(), ((Utilisateur)utilisateur.liste().get(i.intValue())).getCodeAcces()));
	    }
	  
	  
	  
   

	  if (choixteam.size() == 0)
		  this.choixteam.add(new SelectItem(Integer.valueOf(0), " Les Equipes sont completes"));
	   
    
  }
  catch (Exception e) {
   e.printStackTrace();
   e.getMessage();
   
   
  }
  objteamDB.close();
utilisateur.close();
 return this.choixteam;
}

public String retourback()
{
	LoginBean bean = LoginUtil.getLoginBean();
	NiveauDatabaseLayout niveau = null;
    niveau = new NiveauDatabaseLayout();
      niveau.transactionOpen();
      niveau.afficherbyIdObjet(bean.getIdClient(), this.idObj);
      this.objtparent=niveau.model().getIdNiveau();
      niveau.close();
	return "liste_niveau?faces-redirect=true&id="+this.objtparent+"&ido="+this.idObj+"&idl=true"; 
}
}
