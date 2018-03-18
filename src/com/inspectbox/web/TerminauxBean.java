package com.inspectbox.web;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.inspectbox.databaseLayout.ClientDatabaseLayout;
import com.inspectbox.databaseLayout.SynchDatabaseLayout;
import com.inspectbox.databaseLayout.TerminalDatabaseLayout;

import com.inspectbox.model.Synch;
import com.inspectbox.model.Terminal;
import com.inspectbox.objetLayout.ObjetTerminal;
import com.inspectbox.utils.Themed;


@ManagedBean(name="terminauxBean")
@ViewScoped
public class TerminauxBean implements  Serializable {
	/**
	 * 
	 */
	 private static final long serialVersionUID = 1L;
	private String idTerminal;
	 private List<ObjetTerminal> listeTerm = new ArrayList();
	public String getIdTerminal() {
		return idTerminal;
	}

	public void setIdTerminal(String idTerminal) {
		this.idTerminal = idTerminal;
	}
	public List<ObjetTerminal> getListeTerm() {
		return listeTerm;
	}

	public void setListeTerm(List<ObjetTerminal> listeTerm) {
		this.listeTerm = listeTerm;
	}

	public void afficher()
	{
		this.listeTerm.clear();
		SimpleDateFormat formater = null;
   	 formater = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		TerminalDatabaseLayout termDB= new TerminalDatabaseLayout();
		termDB.transactionOpen();
		
		try{
			termDB.listerAll();
			Iterator it = termDB.liste().iterator();
			while (it.hasNext()){
				Terminal term = (Terminal)it.next();	   
				
				
				SynchDatabaseLayout synchDB =new SynchDatabaseLayout();
				synchDB.transactionOpen();
				
				synchDB.listByTerminal(term.getIdTerminal());
				
				Iterator its = synchDB.liste().iterator();
				while (its.hasNext()){
					Synch sync=(Synch)its.next();
					ObjetTerminal objterm=new ObjetTerminal();
					objterm.setIdTerminal(term.getIdTerminal());
					if (sync.getEtat()==1)
					objterm.setEtat(false);
					else
						objterm.setEtat(true);
					
					objterm.setDateLastSynchro(formater.format(sync.getDateInformation()));
					ClientDatabaseLayout cltDB=new ClientDatabaseLayout();
					cltDB.transactionOpen();
					
					cltDB.afficher(sync.getSynchPK().getIdClient());
					
					
					if (cltDB.model()!=null) objterm.setClient(cltDB.model().getCodeAcces());
					this.listeTerm.add(objterm);
				}
				
			}
			
			
		}
		 catch (Exception e) {
			      e.printStackTrace();
	 } finally {
		 termDB.close();
	      }
	}
}
