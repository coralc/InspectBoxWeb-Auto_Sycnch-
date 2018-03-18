package com.inspectbox.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import com.inspectbox.databaseLayout.ChoixDatabaseLayout;
import com.inspectbox.databaseLayout.ClientDatabaseLayout;
import com.inspectbox.databaseLayout.LibelleNiveauDatabaseLayout;
import com.inspectbox.databaseLayout.ObjeteamDatabaseLayout;
import com.inspectbox.databaseLayout.StatusDatabaseLayout;
import com.inspectbox.databaseLayout.SynchDatabaseLayout;
import com.inspectbox.databaseLayout.TeamDatabaseLayout;
import com.inspectbox.databaseLayout.TerminalDatabaseLayout;
import com.inspectbox.databaseLayout.TypeReponseDatabaseLayout;
import com.inspectbox.databaseLayout.UtilisateurDatabaseLayout;
import com.inspectbox.model.Choix;
import com.inspectbox.model.Client;
import com.inspectbox.model.SynchPK;
import com.inspectbox.model.Terminal;
import com.inspectbox.objetLayout.ObjetClient;
import com.inspectbox.objetLayout.ObjetObjeteam;
import com.inspectbox.utils.LoginUtil;
@ManagedBean(name="clientBean")

@ViewScoped
public class ClientBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 private List<ObjetClient> listeclt = new ArrayList();
	 private String codeAcces;
	 private String clefClient;
	 private String libelleNiveau1;
	 private String libelleNiveau2;
	 private String libelleNiveau3;
	 private String libelleObjet;
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

	public String getLibelleNiveau3() {
		return libelleNiveau3;
	}

	public void setLibelleNiveau3(String libelleNiveau3) {
		this.libelleNiveau3 = libelleNiveau3;
	}

	public String getLibelleObjet() {
		return libelleObjet;
	}

	public void setLibelleObjet(String libelleObjet) {
		this.libelleObjet = libelleObjet;
	}

	public String getClefClient() {
		return clefClient;
	}

	public void setClefClient(String clefClient) {
		this.clefClient = clefClient;
	}

	public String getCodeAcces() {
		return codeAcces;
	}

	public void setCodeAcces(String codeAcces) {
		this.codeAcces = codeAcces;
	}

	public List<ObjetClient> getListeclt() {
		return listeclt;
	}

	public void setListeclt(List<ObjetClient> listeclt) {
		this.listeclt = listeclt;
	}

	public void afficher()
	   {
		
		this.listeclt.clear();
		ClientDatabaseLayout gammesDB = null;
	   try
	     {
	 	gammesDB = new ClientDatabaseLayout();
	 	gammesDB.transactionOpen();
	 
	 	gammesDB.listerAll();
	 	Iterator it = gammesDB.liste().iterator();
		while (it.hasNext()){
			Client reponse = (Client)it.next();	    
		 	ObjetClient objetClient = new ObjetClient();
		 	
		 	objetClient.setLibelle(reponse.getCodeAcces());
		 	//objetClient.setCreating(reponse.getClefClient());
		 	objetClient.setIdClient(reponse.getIdClient());
		 	this.listeclt.add(objetClient);
	       	}
	     } catch (Exception e) {
	 	      System.out.println("Erreur liste utilisateurs");
	 	      e.printStackTrace();
		//      e.getMessage();
	        }
	        finally
	        {
	        	if (gammesDB!=null){
	        		gammesDB.close();
	        	}	        	
	        }   
		   
		   
		   
	}	
	 public void onCancel(RowEditEvent event) {
		 
	   }
	 
	 public void onEdit(RowEditEvent event) { 
		   
		 
		   ObjetClient objt=(( ObjetClient) event.getObject());
		   
		   
		   ClientDatabaseLayout objteamDB=null;
		     objteamDB=new ClientDatabaseLayout();
		     objteamDB.transactionOpen();
		     
		     TerminalDatabaseLayout termDB= new TerminalDatabaseLayout();
	          termDB.transactionOpen();
		    try{
		    	objteamDB.afficher(objt.getIdClient());
		    	
		    	objteamDB.model().setCodeAcces(objt.getLibelle());
		    	objteamDB.model().setClefClient(LoginUtil.toSHA1(objt.getCreating().getBytes()));
		    	objteamDB.update();
		    	
		    	// save synchro 
		          
		          termDB.listerAll();
		          Iterator it = termDB.liste().iterator();
		          while (it.hasNext())
			        {
			          Terminal term = (Terminal)it.next();
			           SynchDatabaseLayout synchdb = null;
				 		synchdb = new SynchDatabaseLayout();
				 		synchdb.transactionOpen();
			          synchdb.afficher(objt.getIdClient(), term.getIdTerminal());
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
			        	  synchpk.setIdClient(objt.getIdClient());
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
	        System.out.println("Erreur modif rapide");
	      e.printStackTrace();
	   
	    
	       } finally {

 objteamDB.close();

	       }
	 } 
	
	 
	 public String supprimer(Integer idclt)
	 {
	 	 ClientDatabaseLayout objteamDB=null;
	      objteamDB=new ClientDatabaseLayout();
	      objteamDB.transactionOpen();
	     try{
	     	objteamDB.afficher(idclt);
	     	
	     	objteamDB.model().setMasque(true);
	     	objteamDB.update();
	     	objteamDB.transactionCommit();
	     	// supp les choix

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
	     		          synchdb.afficher(idclt, term.getIdTerminal());
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
	     		        	  synchpk.setIdClient(idclt);
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

	 objteamDB.close();

	    }
	     return "liste_clients?faces-redirect=true";
	 }	
	 public String creer()
	 {
		 Integer idclientnew=0;
		 Integer idList=0;
	 	 ClientDatabaseLayout cltDB=null;
	 	cltDB=new ClientDatabaseLayout();
	 	cltDB.transactionOpen();
	 	LibelleNiveauDatabaseLayout libelleNiveau = new LibelleNiveauDatabaseLayout();
	 	LibelleNiveauDatabaseLayout libelleNiveau1 = new LibelleNiveauDatabaseLayout();
	 	LibelleNiveauDatabaseLayout libelleNiveau2 = new LibelleNiveauDatabaseLayout();
	 	LibelleNiveauDatabaseLayout libelleNiveau22 = new LibelleNiveauDatabaseLayout();
	 	LibelleNiveauDatabaseLayout libelleNiveau3 = new LibelleNiveauDatabaseLayout();
	 	LibelleNiveauDatabaseLayout libelleNiveau33 = new LibelleNiveauDatabaseLayout();
	 	TypeReponseDatabaseLayout typ = new TypeReponseDatabaseLayout();
	 	TypeReponseDatabaseLayout typ2 = new TypeReponseDatabaseLayout();
	 	ChoixDatabaseLayout choix = new ChoixDatabaseLayout();
	 	ChoixDatabaseLayout choix2 = new ChoixDatabaseLayout();
	 	StatusDatabaseLayout statusDB = new StatusDatabaseLayout();
	 	StatusDatabaseLayout statusDB2 = new StatusDatabaseLayout();
	 	StatusDatabaseLayout statusDB3 = new StatusDatabaseLayout();
	 	StatusDatabaseLayout statusDB4 = new StatusDatabaseLayout();
	 	StatusDatabaseLayout statusDB5 = new StatusDatabaseLayout();
	 	StatusDatabaseLayout statusDB6 = new StatusDatabaseLayout();
	 	UtilisateurDatabaseLayout utilisateur = new UtilisateurDatabaseLayout();
		UtilisateurDatabaseLayout utilisateur2 = new UtilisateurDatabaseLayout();
		UtilisateurDatabaseLayout utilisateur3 = new UtilisateurDatabaseLayout();
 	    TeamDatabaseLayout teambdf0 = new TeamDatabaseLayout() ;
 	   TeamDatabaseLayout teambdf1 = new TeamDatabaseLayout() ;
 	  TeamDatabaseLayout teambdf2 = new TeamDatabaseLayout() ;
 	 TeamDatabaseLayout teambdf3 = new TeamDatabaseLayout() ;
 	TeamDatabaseLayout teambdf4 = new TeamDatabaseLayout() ;
 	TeamDatabaseLayout teambdf5 = new TeamDatabaseLayout() ;
 	TeamDatabaseLayout teambdf6 = new TeamDatabaseLayout() ;
 	
 	TeamDatabaseLayout teambds0 = new TeamDatabaseLayout() ;
	   TeamDatabaseLayout teambds1 = new TeamDatabaseLayout() ;
	  TeamDatabaseLayout teambds2 = new TeamDatabaseLayout() ;
	 TeamDatabaseLayout teambds3 = new TeamDatabaseLayout() ;
	TeamDatabaseLayout teambds4 = new TeamDatabaseLayout() ;
	TeamDatabaseLayout teambds5 = new TeamDatabaseLayout() ;
	TeamDatabaseLayout teambds6 = new TeamDatabaseLayout() ;
	
	TeamDatabaseLayout teambdn0 = new TeamDatabaseLayout() ;
	   TeamDatabaseLayout teambdn1 = new TeamDatabaseLayout() ;
	  TeamDatabaseLayout teambdn2 = new TeamDatabaseLayout() ;
	 TeamDatabaseLayout teambdn3 = new TeamDatabaseLayout() ;
	TeamDatabaseLayout teambdn4 = new TeamDatabaseLayout() ;
	TeamDatabaseLayout teambdn5 = new TeamDatabaseLayout() ;
	TeamDatabaseLayout teambdn6 = new TeamDatabaseLayout() ;
	TeamDatabaseLayout teambdn00 = new TeamDatabaseLayout() ;
	   TeamDatabaseLayout teambdn11 = new TeamDatabaseLayout() ;
	  TeamDatabaseLayout teambdn22 = new TeamDatabaseLayout() ;
	 TeamDatabaseLayout teambdn33 = new TeamDatabaseLayout() ;
	TeamDatabaseLayout teambdn44 = new TeamDatabaseLayout() ;
	TeamDatabaseLayout teambdn55 = new TeamDatabaseLayout() ;
	TeamDatabaseLayout teambdn66 = new TeamDatabaseLayout() ;
	     try{
	     	
	     	if ((this.codeAcces!=null)&&(this.clefClient!=null))
	     	{
	     		cltDB.model().setCodeAcces(this.codeAcces);
	     		cltDB.model().setNom(this.codeAcces);
	     		cltDB.model().setClefClient(LoginUtil.toSHA1(this.clefClient.getBytes()));
	     		cltDB.model().setNombreNiveaux(2);
	     		cltDB.model().setNombreNiveauxObjets(1);
	     		cltDB.model().setNombrePhotos(0);
	     		cltDB.model().setMasque(false);
	     		idclientnew=cltDB.save();
	     		
	     		
		     	
	     	}
	     	
	     	if ((idclientnew!=null)&& (idclientnew!=0))
	     	{
	     		//remplir les libelles
	     		 
	     		libelleNiveau.transactionOpen();
	     		libelleNiveau.setClient(idclientnew);
	     		libelleNiveau.model().setClefTimestamp(new Date());
	     		libelleNiveau.model().setLibelle("Verfahrensbereich");
	     		libelleNiveau.model().setTypeNiveau(1);
	     		libelleNiveau.model().setNum(1);
	     		libelleNiveau.save();
	     		
	     		
	     		libelleNiveau1.transactionOpen();
	     		libelleNiveau1.setClient(idclientnew);
	     		libelleNiveau1.model().setClefTimestamp(new Date());
	     		libelleNiveau1.model().setLibelle("Anlagenteil");
	     		libelleNiveau1.model().setTypeNiveau(1);
	     		libelleNiveau1.model().setNum(2);
	     		libelleNiveau1.save();
	     		
	     		libelleNiveau2.transactionOpen();
	     		libelleNiveau2.setClient(idclientnew);
	     		libelleNiveau2.model().setClefTimestamp(new Date());
	     		libelleNiveau2.model().setLibelle("Messtelle");
	     		libelleNiveau2.model().setTypeNiveau(1);
	     		libelleNiveau2.model().setNum(3);
	     		libelleNiveau2.save();
	     		
	     		libelleNiveau22.transactionOpen();
	     		libelleNiveau22.setClient(idclientnew);
	     		libelleNiveau22.model().setClefTimestamp(new Date());
	     		libelleNiveau22.model().setLibelle("Messtelle");
	     		libelleNiveau22.model().setTypeNiveau(2);
	     		libelleNiveau22.model().setNum(1);
	     		libelleNiveau22.save();
	     		
	     		libelleNiveau3.transactionOpen();
	     		libelleNiveau3.setClient(idclientnew);
	     		libelleNiveau3.model().setClefTimestamp(new Date());
	     		libelleNiveau3.model().setLibelle("Messwert");
	     		libelleNiveau3.model().setNum(2);
	     		libelleNiveau3.model().setTypeNiveau(2);
	     		libelleNiveau3.save();
	     		
	     		libelleNiveau33.transactionOpen();
	     		libelleNiveau33.setClient(idclientnew);
	     		libelleNiveau33.model().setClefTimestamp(new Date());
	     		libelleNiveau33.model().setLibelle("IH-Plan");
	     		libelleNiveau33.model().setNum(1);
	     		libelleNiveau33.model().setTypeNiveau(3);
	     		libelleNiveau33.save();
	     		
	     		// remplir les type rep
	     		
	     	     typ.transactionOpen();
	     	    typ.model().setMode(1);
	     	    typ.setClient(idclientnew);
	     	     typ.model().setLibelle("valeur");
	     	     
	     	     typ.model().setClefTimestamp(new Date());
	     	     typ.model().setMasque(false);
	     	     typ.model().setModeReponse("Valeur");
	     	     
	     	     typ.save();
	     	     
	     	    typ2.transactionOpen();
	     	    typ2.model().setMode(2);
	     	    typ2.setClient(idclientnew);
	     	     typ2.model().setLibelle("OK/NOK");
	     	     
	     	     typ2.model().setClefTimestamp(new Date());
	     	     typ2.model().setMasque(false);
	     	     typ2.model().setModeReponse("Liste");
	     	     
	     	    idList= typ2.save();
	     	    System.out.println("idList"+idList);
	     	     // ajouter les choix
	     	   
	     	    	choix.transactionOpen();
	     	    	choix.setClient(idclientnew);
		     	      choix.setTypeReponse(idList);
		     	     choix.model().setValeur("OK");
		     	        choix.model().setNonConforme(false);
		     	       choix.model().setTri(1);
		     	         choix.model().setMasque(false);
		     	   	      choix.model().setClefTimestamp(new Date());
		     	        choix.save();
		     	        choix.transactionCommit();
		     	        
		     	       choix2.transactionOpen();
		     	    	choix2.setClient(idclientnew);
			     	      choix2.setTypeReponse(idList);
			     	     choix2.model().setValeur("NOK");
			     	        choix2.model().setNonConforme(true);
			     	       choix2.model().setTri(2);
			     	         choix2.model().setMasque(false);
			     	   	      choix2.model().setClefTimestamp(new Date());
			     	        choix2.save();
			     	       choix2.transactionCommit();
	     	      // ajouter les statuts
	     	   
	     		statusDB.transactionOpen();
	     		statusDB.model().setLabel("ERFASST");
	     		statusDB.model().setMasque(false);
	     		statusDB.setClient(idclientnew);
	     		statusDB.save();
	     		statusDB.transactionCommit();
	     		
	     		statusDB2.transactionOpen();
	     		statusDB2.model().setLabel("NICHTERFASST");
	     		statusDB2.model().setMasque(false);
	     		statusDB2.setClient(idclientnew);
	     		statusDB2.save();
	     		statusDB2.transactionCommit();
	     		
	     	
	     		statusDB3.transactionOpen();
	     		statusDB3.model().setLabel("LEER");
	     		statusDB3.model().setMasque(false);
	     		statusDB3.setClient(idclientnew);
	     		statusDB3.save();
	     		statusDB3.transactionCommit();
	     		
	     		statusDB4.transactionOpen();
	     		statusDB4.model().setLabel("GRENZWERT");
	     		statusDB4.model().setMasque(false);
	     		statusDB4.setClient(idclientnew);
	     		statusDB4.save();
	     		statusDB4.transactionCommit();
	     		
	     		statusDB5.transactionOpen();
	     		statusDB5.model().setLabel("DEFEKT");
	     		statusDB5.model().setMasque(false);
	     		statusDB5.setClient(idclientnew);
	     		statusDB5.save();
	     		statusDB5.transactionCommit();
	     		
	     		statusDB6.transactionOpen();
	     		statusDB6.model().setLabel("INAKTIV");
	     		statusDB6.model().setMasque(false);
	     		statusDB6.setClient(idclientnew);
	     		statusDB6.save();
	     		statusDB6.transactionCommit();
	     		
	     				// ajouter les teams
	     		 	 
	     	       Integer iduserf=0;
	     	      Integer idusers=0;
	     	     Integer idusern=0;
	     	      utilisateur.transactionOpen();
	     	        utilisateur.setClient(idclientnew);
	     	 	     utilisateur.setDroitUtilisateur(3);
	     	 	    utilisateur.model().setCodeAcces("Frühschicht");
	     	 	        utilisateur.model().setMotDePasse("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3");
	     	 	        utilisateur.model().setTypeUser("E");
	     	 	        utilisateur.model().setMasque(false);
	     	 	 	      utilisateur.model().setClefTimestamp(new Date());
	     	 	         iduserf=utilisateur.save();
	     	 	         
	     	 	        System.out.println("iduserf"+iduserf);
	     	 	        
	     	 	      utilisateur2.transactionOpen();
		     	        utilisateur2.setClient(idclientnew);
		     	 	     utilisateur2.setDroitUtilisateur(3);
		     	 	    utilisateur2.model().setCodeAcces("Spätschicht");
		     	 	        utilisateur2.model().setMotDePasse("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3");
		     	 	        utilisateur2.model().setTypeUser("E");
		     	 	        utilisateur2.model().setMasque(false);
		     	 	 	     utilisateur2.model().setClefTimestamp(new Date());
		     	 	         idusers=utilisateur2.save();
		     	 	         
		     	 	        
	     	 	      utilisateur3.transactionOpen();
		     	        utilisateur3.setClient(idclientnew);
		     	 	     utilisateur3.setDroitUtilisateur(3);
		     	 	    utilisateur3.model().setCodeAcces("Nachtschicht");
		     	 	        utilisateur3.model().setMotDePasse("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3");
		     	 	        utilisateur3.model().setTypeUser("E");
		     	 	        utilisateur3.model().setMasque(false);
		     	 	 	    utilisateur3.model().setClefTimestamp(new Date());
		     	 	         idusern=utilisateur3.save();
		     	 	       
	     	 	         // ajouter les horaires
	     	 	      teambdf0.transactionOpen();
	     	 	    teambdf0.model().setIdUtilisateur(iduserf);
	     	 	  teambdf0.model().setBeginHour("05:30");
	     	 	teambdf0.model().setEndHour("13:30");
	     	 	teambdf0.model().setDayOfWeek("0");
	     	 	teambdf0.model().setMasque(false);
	     	 	teambdf0.setClient(idclientnew);
	     	 	teambdf0.save();
	 	        	
	     	 	teambdf1.transactionOpen();
	     	 	teambdf1.model().setIdUtilisateur(iduserf);
	     	 	teambdf1.model().setBeginHour("05:30");
	     	 	teambdf1.model().setEndHour("13:30");
	     	 	teambdf1.model().setDayOfWeek("1");
	     	 	teambdf1.model().setMasque(false);
	     	 	teambdf1.setClient(idclientnew);
	     	 	teambdf1.save();
	     	 	teambdf1.transactionCommit();
	     	 	
	 	        	teambdf2.transactionOpen();
	 	        	teambdf2.model().setIdUtilisateur(iduserf);
	 	        	teambdf2.model().setBeginHour("05:30");
	 	        	teambdf2.model().setEndHour("13:30");
	 	        	teambdf2.model().setDayOfWeek("2");
	 	        	teambdf2.model().setMasque(false);
	 	        	teambdf2.setClient(idclientnew);
	 	        	teambdf2.save();
	 	        	teambdf2.transactionCommit();
	 	        	teambdf3.transactionOpen();
	 	        	
	 	        	teambdf3.model().setIdUtilisateur(iduserf);
	 	        	teambdf3.model().setBeginHour("05:30");
	 	        	teambdf3.model().setEndHour("13:30");
	 	        	teambdf3.model().setDayOfWeek("3");
	 	        	teambdf3.model().setMasque(false);
	 	        	teambdf3.setClient(idclientnew);
	 	        	teambdf3.save();
	 	        	teambdf3.transactionCommit();
	 	        	
	 	        	teambdf4.transactionOpen();
	 	        	teambdf4.model().setIdUtilisateur(iduserf);
	 	        	teambdf4.model().setBeginHour("05:30");
	 	        	teambdf4.model().setEndHour("13:30");
	 	        	teambdf4.model().setDayOfWeek("4");
	 	        	teambdf4.model().setMasque(false);
	 	        	teambdf4.setClient(idclientnew);
	 	        	teambdf4.save();
	 	        	teambdf4.transactionCommit();
	 	        	teambdf5.transactionOpen();
	 	        	teambdf5.model().setIdUtilisateur(iduserf);
	 	        	teambdf5.model().setBeginHour("05:30");
	 	        	teambdf5.model().setEndHour("13:30");
	 	        	teambdf5.model().setDayOfWeek("5");
	 	        	teambdf5.model().setMasque(false);
	 	        	teambdf5.setClient(idclientnew);
	 	        	teambdf5.save();
	 	        	teambdf5.transactionCommit();
	 	        	teambdf6.transactionOpen();
	 	        	teambdf6.model().setIdUtilisateur(iduserf);
	 	        	teambdf6.model().setBeginHour("05:30");
	 	        	teambdf6.model().setEndHour("13:30");
	 	        	teambdf6.model().setDayOfWeek("6");
	 	        	teambdf6.model().setMasque(false);
	 	        	teambdf6.setClient(idclientnew);
	 	        	teambdf6.save();
	 	        	teambdf6.transactionCommit();
	 	        	
	 	        	
	     	 	        
	     	 	      // ajouter les horaires
	 	        	teambds0.transactionOpen();
	 	        	teambds0.model().setIdUtilisateur(idusers);
	 	        	teambds0.model().setBeginHour("13:30");
	 	        	teambds0.model().setEndHour("21:30");
	 	        	teambds0.model().setDayOfWeek("0");
	 	        	teambds0.model().setMasque(false);
	 	        	teambds0.setClient(idclientnew);
	 	        	teambds0.save();
	 	        	teambds0.transactionCommit();
	 	        	teambds1.transactionOpen();
	 	        	teambds1.model().setIdUtilisateur(idusers);
	 	        	teambds1.model().setBeginHour("13:30");
	 	        	teambds1.model().setEndHour("21:30");
	 	        	teambds1.model().setDayOfWeek("1");
	 	        	teambds1.model().setMasque(false);
	 	        	teambds1.setClient(idclientnew);
	 	        	teambds1.save();
	 	        	teambds1.transactionCommit();
	 	        	teambds2.transactionOpen();
	 	        	teambds2.model().setIdUtilisateur(idusers);
	 	        	teambds2.model().setBeginHour("13:30");
	 	        	teambds2.model().setEndHour("21:30");
	 	        	teambds2.model().setDayOfWeek("2");
	 	        	teambds2.model().setMasque(false);
	 	        	teambds2.setClient(idclientnew);
	 	        	teambds2.save();
	 	        	teambds2.transactionCommit();
		 	        	teambds3.transactionOpen();
		     	 	    teambds3.model().setIdUtilisateur(idusers);
		     	 	  teambds3.model().setBeginHour("13:30");
		 	        	teambds3.model().setEndHour("21:30");
		 	        	teambds3.model().setDayOfWeek("3");
		 	        	teambds3.model().setMasque(false);
		 	        	teambds3.setClient(idclientnew);
		 	        	teambds3.save();
		 	        	teambds3.transactionCommit();
		 	        	teambds4.transactionOpen();
		 	        	teambds4.model().setIdUtilisateur(idusers);
		 	        	teambds4.model().setBeginHour("13:30");
		 	        	teambds4.model().setEndHour("21:30");
		 	        	teambds4.model().setDayOfWeek("4");
		 	        	teambds4.model().setMasque(false);
		 	        	teambds4.setClient(idclientnew);
		 	        	teambds4.save();
		 	        	teambds4.transactionCommit();
		 	        	teambds5.transactionOpen();
		 	        	teambds5.model().setIdUtilisateur(idusers);
		 	        	teambds5.model().setBeginHour("13:30");
		 	        	teambds5.model().setEndHour("21:30");
		 	        	teambds5.model().setDayOfWeek("5");
		 	        	teambds5.model().setMasque(false);
		 	        	teambds5.setClient(idclientnew);
		 	        	teambds5.save();
		 	        	teambds5.transactionCommit();
		 	        	teambds6.transactionOpen();
		 	        	teambds6.model().setIdUtilisateur(idusers);
		 	        	teambds6.model().setBeginHour("13:30");
		 	        	teambds6.model().setEndHour("21:30");
		 	        	teambds6.model().setDayOfWeek("6");
		 	        	teambds6.model().setMasque(false);
		 	        	teambds6.setClient(idclientnew);
		 	        	teambds6.save();
		 	        	teambds6.transactionCommit();
		 	        	
		 	        	
		     	 	        
		     	 	      // ajouter les horaires
		 	        	teambdn0.transactionOpen();
		 	        	teambdn0.model().setIdUtilisateur(idusern);
		 	        	teambdn0.model().setBeginHour("00:00");
		 	        	teambdn0.model().setEndHour("05:30");
		 	        	teambdn0.model().setDayOfWeek("0");
		 	        	teambdn0.model().setMasque(false);
		 	        	teambdn0.setClient(idclientnew);
		 	        	teambdn0.save();
		 	        	teambdn0.transactionCommit();
		 	        	
		 	        	teambdn00.transactionOpen();
		 	        	teambdn00.model().setIdUtilisateur(idusern);
		 	        	teambdn00.model().setBeginHour("21:30");
		 	        	teambdn00.model().setEndHour("24:00");
		 	        	teambdn00.model().setDayOfWeek("0");
		 	        	teambdn00.model().setMasque(false);
		 	        	teambdn00.setClient(idclientnew);
		 	        	teambdn00.save();
		 	        	teambdn00.transactionCommit();
			 	        	
		 	        	teambdn1.transactionOpen();
		 	        	teambdn1.model().setIdUtilisateur(idusern);
		 	        	teambdn1.model().setBeginHour("00:00");
		 	        	teambdn1.model().setEndHour("05:30");
		 	        	teambdn1.model().setDayOfWeek("1");
		 	        	teambdn1.model().setMasque(false);
		 	        	teambdn1.setClient(idclientnew);
		 	        	teambdn1.save();
		 	        	teambdn1.transactionCommit();	
		 	        	
			 	        	teambdn11.transactionOpen();
			 	        	teambdn11.model().setIdUtilisateur(idusern);
			 	        	teambdn11.model().setBeginHour("21:30");
			 	        	teambdn11.model().setEndHour("24:00");
			 	        	teambdn11.model().setDayOfWeek("1");
			 	        	teambdn11.model().setMasque(false);
			 	        	teambdn11.setClient(idclientnew);
			 	        	teambdn11.save();
			 	        	teambdn11.transactionCommit();
			 	        	
			 	        	teambdn2.transactionOpen();
			 	        	teambdn2.model().setIdUtilisateur(idusern);
			 	        	teambdn2.model().setBeginHour("00:00");
			 	        	teambdn2.model().setEndHour("05:30");
			 	        	teambdn2.model().setDayOfWeek("2");
			 	        	teambdn2.model().setMasque(false);
			 	        	teambdn2.setClient(idclientnew);
			 	        	teambdn2.save();
			 	        	teambdn2.transactionCommit();
			 	        	teambdn22.transactionOpen();
			 	        	teambdn22.model().setIdUtilisateur(idusern);
			 	        	teambdn22.model().setBeginHour("21:30");
			 	        	teambdn22.model().setEndHour("24:00");
			 	        	teambdn22.model().setDayOfWeek("2");
			 	        	teambdn22.model().setMasque(false);
			 	        	teambdn22.setClient(idclientnew);
			 	        	teambdn22.save();
			 	        	teambdn22.transactionCommit();
			 	        
			 	        	teambdn3.transactionOpen();
			 	        	teambdn3.model().setIdUtilisateur(idusers);
			 	        	teambdn3.model().setBeginHour("00:00");
			 	        	teambdn3.model().setEndHour("05:30");
			 	        	teambdn3.model().setDayOfWeek("3");
			 	        	teambdn3.model().setMasque(false);
			 	        	teambdn3.setClient(idclientnew);
			 	        	teambdn3.save();
			 	        	teambdn3.transactionCommit();
			 	        	
			 	        	teambdn33.transactionOpen();
			 	        	teambdn33.model().setIdUtilisateur(idusers);
			 	        	teambdn33.model().setBeginHour("21:30");
			 	        	teambdn33.model().setEndHour("24:00");
			 	        	teambdn33.model().setDayOfWeek("3");
			 	        	teambdn33.model().setMasque(false);
			 	        	teambdn33.setClient(idclientnew);
			 	        	teambdn33.save();
			 	        	teambdn33.transactionCommit();
			 	        	
			 	        	teambdn4.transactionOpen();
			 	        	teambdn4.model().setIdUtilisateur(idusers);
			 	        	teambdn4.model().setBeginHour("00:00");
			 	        	teambdn4.model().setEndHour("05:30");
			 	        	teambdn4.model().setDayOfWeek("4");
			 	        	teambdn4.model().setMasque(false);
			 	        	teambdn4.setClient(idclientnew);
			 	        	teambdn4.save();
			 	        	teambdn4.transactionCommit();
			 	        	
			 	        	teambdn44.transactionOpen();
			 	        	teambdn44.model().setIdUtilisateur(idusers);
			 	        	teambdn44.model().setBeginHour("21:30");
			 	        	teambdn44.model().setEndHour("24:00");
			 	        	teambdn44.model().setDayOfWeek("4");
			 	        	teambdn44.model().setMasque(false);
			 	        	teambdn44.setClient(idclientnew);
			 	        	teambdn44.save();
			 	        	teambdn44.transactionCommit();
			 	        	
			 	        	teambdn5.transactionOpen();
			 	        	teambdn5.model().setIdUtilisateur(idusers);
			     	 	  teambdn5.model().setBeginHour("00:00");
			     	 	teambdn5.model().setEndHour("05:30");
			     	 	teambdn5.model().setDayOfWeek("5");
			     	 	teambdn5.model().setMasque(false);
			     	 	teambdn5.setClient(idclientnew);
			     	 	teambdn5.save();
			     	 	teambdn5.transactionCommit();	
			     	 	
			 	        	teambdn55.transactionOpen();
			 	        	teambdn55.model().setIdUtilisateur(idusers);
			 	        	teambdn55.model().setBeginHour("21:30");
			 	        	teambdn55.model().setEndHour("24:00");
			 	        	teambdn55.model().setDayOfWeek("5");
			 	        	teambdn55.model().setMasque(false);
			 	        	teambdn55.setClient(idclientnew);
			 	        	teambdn55.save();
			 	        	teambdn55.transactionCommit();
			 	        	
			 	        	teambdn6.transactionOpen();
			 	        	teambdn6.model().setIdUtilisateur(idusers);
			 	        	teambdn6.model().setBeginHour("00:00");
			 	        	teambdn6.model().setEndHour("05:30");
			 	        	teambdn6.model().setDayOfWeek("6");
			 	        	teambdn6.model().setMasque(false);
			 	        	teambdn6.setClient(idclientnew);
			 	        	teambdn6.save();
			 	        	teambdn6.transactionCommit();
			 	        	
			 	        	teambdn66.transactionOpen();
			 	        	teambdn66.model().setIdUtilisateur(idusers);
			 	        	teambdn66.model().setBeginHour("21:30");
			 	        	teambdn66.model().setEndHour("24:00");
			 	        	teambdn66.model().setDayOfWeek("6");
			 	        	teambdn66.model().setMasque(false);
			 	        	teambdn66.setClient(idclientnew);
			 	        	teambdn66.save();
			 	        	teambdn66.transactionCommit();
	     	

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
			 	        		          synchdb.afficher(idclientnew, term.getIdTerminal());
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
			 	        		        	  synchpk.setIdClient(idclientnew);
			 	        		        	  synchpk.setIdTerminal(term.getIdTerminal());
			 	        		        	  synchdbnw.model().setEtat(1);
			 	        		        	  synchdbnw.model().setDateInformation(new Date());
			 	        		        	  synchdbnw.model().setSynchPK(synchpk);
			 	        		        	  synchdbnw.save();
			 	        		        	  synchdbnw.transactionCommit();
			 	        		          }
			 	        		          
			 	        		        } 
			 	        	     termDB.close();     
	     	}// end if
	     	
	     }

	 catch (Exception e) {
	     
	   e.printStackTrace();


	    } finally {

	    	cltDB.close();
	    	libelleNiveau1.close();libelleNiveau2.close();libelleNiveau3.close();libelleNiveau.close();libelleNiveau22.close();libelleNiveau33.close();
	    	typ.close();typ2.close();
	    	choix.close();choix2.close();
	    	statusDB.close();statusDB2.close();statusDB3.close();statusDB4.close();statusDB5.close();statusDB6.close();
	    	utilisateur.close(); utilisateur2.close();utilisateur3.close();
	    	teambdf0.close();teambdf1.close();teambdf2.close();teambdf3.close();teambdf4.close();teambdf5.close();teambdf6.close();
	    	teambds0.close();teambds1.close();teambds2.close();teambds3.close();teambds4.close();teambds5.close();teambds6.close();
	    	teambdn0.close();teambdn1.close();teambdn2.close();teambdn3.close();teambdn4.close();teambdn5.close();teambdn6.close();
	    	teambdn00.close();teambdn11.close();teambdn22.close();teambdn33.close();teambdn44.close();teambdn55.close();teambdn66.close();
	  	  
	    }
	     return "liste_clients?faces-redirect=true";
	 }	
}
