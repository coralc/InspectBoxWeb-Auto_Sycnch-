package com.inspectbox.objetLayout;

import java.io.Serializable;


public class ObjetGammes implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer idGammes;
	private Integer idClient;
	private String libelle;
	private int tri;
	public Integer getIdGammes() {
		return idGammes;
	}
	public void setIdGammes(Integer idGammes) {
		this.idGammes = idGammes;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public int getTri() {
		return tri;
	}
	public void setTri(int tri) {
		this.tri = tri;
	}
	public Integer getIdClient() {
		return idClient;
	}
	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	
	
}
