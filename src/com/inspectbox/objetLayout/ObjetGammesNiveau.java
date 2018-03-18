package com.inspectbox.objetLayout;

import java.io.Serializable;


public class ObjetGammesNiveau implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer idGammes;
	private Integer idNiveau;
	private Integer idClient;
	private Boolean masque;

	public Integer getIdGammes() {
		return idGammes;
	}
	public void setIdGammes(Integer idGammes) {
		this.idGammes = idGammes;
	}
	public Integer getIdClient() {
		return idClient;
	}
	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}
	public Boolean getMasque() {
		return masque;
	}
	public void setMasque(Boolean masque) {
		this.masque = masque;
	}
	public Integer getIdNiveau() {
		return idNiveau;
	}
	public void setIdNiveau(Integer idNiveau) {
		this.idNiveau = idNiveau;
	}
	@Override
	public String toString() {
		return "ObjetGammesNiveau [idGammes=" + idGammes + ", idNiveau="
				+ idNiveau + ", idClient=" + idClient + ", masque=" + masque
				+ "]";
	}

	
	
}
