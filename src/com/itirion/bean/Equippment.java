package com.itirion.bean;

public class Equippment {
	private String libelle;

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Equippment() {
		super();		
	}
	public Equippment(String libelle) {
		super();
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Equippment [libelle=" + libelle + "]";
	}
	
}
