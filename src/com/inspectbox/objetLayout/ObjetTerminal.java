package com.inspectbox.objetLayout;

import java.io.Serializable;
import java.util.Date;

public class ObjetTerminal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String idTerminal;
	Boolean etat;
	String dateLastSynchro;
	String Client;
	public String getIdTerminal() {
		return idTerminal;
	}
	public void setIdTerminal(String idTerminal) {
		this.idTerminal = idTerminal;
	}
	
	
	
	public String getClient() {
		return Client;
	}
	public void setClient(String client) {
		Client = client;
	}
	public Boolean getEtat() {
		return etat;
	}
	public void setEtat(Boolean etat) {
		this.etat = etat;
	}
	public String getDateLastSynchro() {
		return dateLastSynchro;
	}
	public void setDateLastSynchro(String dateLastSynchro) {
		this.dateLastSynchro = dateLastSynchro;
	}

}
