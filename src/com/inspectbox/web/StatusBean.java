package com.inspectbox.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import com.inspectbox.databaseLayout.StatusDatabaseLayout;
import com.inspectbox.databaseLayout.SynchDatabaseLayout;
import com.inspectbox.databaseLayout.TerminalDatabaseLayout;
import com.inspectbox.model.Statut;
import com.inspectbox.model.SynchPK;
import com.inspectbox.model.Terminal;
import com.inspectbox.objetLayout.ObjetStatus;
import com.inspectbox.utils.LoginUtil;

@ManagedBean(name="statusBean")
@ViewScoped
public class StatusBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ObjetStatus> listeMapStatus = new ArrayList();
private String statchoisit;
	
	public List<ObjetStatus> getListeMapStatus() {
		return listeMapStatus;
	}


	public void setListeMapStatus(List<ObjetStatus> listeMapStatus) {
		this.listeMapStatus = listeMapStatus;
	}


	public String getStatchoisit() {
		return statchoisit;
	}


	public void setStatchoisit(String statchoisit) {
		this.statchoisit = statchoisit;
	}


	public void afficher()
	{this.listeMapStatus.clear();
		LoginBean bean = LoginUtil.getLoginBean();
		StatusDatabaseLayout statusDB = new StatusDatabaseLayout();
		statusDB.transactionOpen();
		try{
			statusDB.listAll(bean.getIdClient());
			Iterator itobj = statusDB.liste().iterator();
			   
	        while (itobj.hasNext())
	        {
	        	
	        	Statut nvobj=(Statut)itobj.next();
	       	 
	       	 
	        	ObjetStatus objetvue = new ObjetStatus();
	        	objetvue.setIdStatus(nvobj.getIdStatus());
	        	objetvue.setLabel(nvobj.getLabel());
	        	listeMapStatus.add(objetvue);
	        }
		}
		catch (Exception e) {
			   e.printStackTrace();
		   }

	finally {
		statusDB.close();
		   
	}
	}
	
	public void onCancel(RowEditEvent event) {  
	    
    }  
	public void onEditLine(RowEditEvent event) {  
		LoginBean bean = LoginUtil.getLoginBean();
		ObjetStatus objetVue=((ObjetStatus) event.getObject());
		StatusDatabaseLayout statusDB = new StatusDatabaseLayout();
		statusDB.transactionOpen();
		try{
			statusDB.listBYid(objetVue.getIdStatus());
			statusDB.model().setLabel(objetVue.getLabel());
			statusDB.update();
			statusDB.transactionCommit();
			

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
		   }

	finally {
		statusDB.close();
		   
	}
	}
	
	public String supprimer(Integer idStatus)
	{
		StatusDatabaseLayout statusDB = new StatusDatabaseLayout();
		statusDB.transactionOpen();
		try{
			statusDB.listBYid(idStatus);
			statusDB.model().setMasque(true);
			statusDB.update();
			statusDB.transactionCommit();
			

			// save synchro 
			LoginBean bean = LoginUtil.getLoginBean();
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
		   }

	finally {
		statusDB.close();
		   
	}
		return "liste_status?faces-redirect=true";	 
	}
	public String creer()
	{LoginBean bean = LoginUtil.getLoginBean();
		StatusDatabaseLayout statusDB = new StatusDatabaseLayout();
	statusDB.transactionOpen();
	try{
		statusDB.model().setLabel(this.statchoisit);
		statusDB.model().setMasque(false);
		statusDB.setClient(bean.getIdClient());
		statusDB.save();
		statusDB.transactionCommit();

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
	   }

finally {
	statusDB.close();
	   
}
		
		return "liste_status?faces-redirect=true";	
	}
}
