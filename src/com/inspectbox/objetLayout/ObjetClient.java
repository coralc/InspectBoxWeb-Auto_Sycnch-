package com.inspectbox.objetLayout;

import java.io.Serializable;


public class ObjetClient implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idClient;
	private String libelle;
	private String creating;
	
	
	public Integer getIdClient() {
		return idClient;
	}
	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getCreating() {
		return creating;
	}
	public void setCreating(String creating) {
		this.creating = creating;
	}
	
	
	
}
