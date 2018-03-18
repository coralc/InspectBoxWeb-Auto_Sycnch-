package com.inspectbox.objetLayout;

import java.io.Serializable;

public class ObjetObjeteam implements Serializable{
	 private static final long serialVersionUID = 1L;
	 private Integer idTeam;
	 private Integer idField;
	 public Integer getIdField() {
		return idField;
	}
	public void setIdField(Integer idField) {
		this.idField = idField;
	}
	public Integer getIdTeam() {
		return idTeam;
	}
	public void setIdTeam(Integer idTeam) {
		this.idTeam = idTeam;
	}
	public Integer getIdObjet() {
		return idObjet;
	}
	public void setIdObjet(Integer idObjet) {
		this.idObjet = idObjet;
	}
	private Integer idObjet;
	private boolean lun ;
	   private boolean mar;
	   private boolean mer;
	   private boolean jeu;
	   private boolean ven;
	   private boolean sam;
	   private boolean dim;
	   private String libelle ;
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public boolean isLun() {
		return lun;
	}
	public void setLun(boolean lun) {
		this.lun = lun;
	}
	public boolean isMar() {
		return mar;
	}
	public void setMar(boolean mar) {
		this.mar = mar;
	}
	public boolean isMer() {
		return mer;
	}
	public void setMer(boolean mer) {
		this.mer = mer;
	}
	public boolean isJeu() {
		return jeu;
	}
	public void setJeu(boolean jeu) {
		this.jeu = jeu;
	}
	public boolean isVen() {
		return ven;
	}
	public void setVen(boolean ven) {
		this.ven = ven;
	}
	public boolean isSam() {
		return sam;
	}
	public void setSam(boolean sam) {
		this.sam = sam;
	}
	public boolean isDim() {
		return dim;
	}
	public void setDim(boolean dim) {
		this.dim = dim;
	}
	
	
	
}
