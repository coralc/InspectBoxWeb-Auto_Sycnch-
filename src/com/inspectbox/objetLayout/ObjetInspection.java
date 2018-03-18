 package com.inspectbox.objetLayout;
 
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.primefaces.model.DefaultStreamedContent;
 
 public class ObjetInspection
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private Integer idInspection;
   private String niveau2Libelle;
private String idiniformation;

   private String niveau3Libelle;
   private String reponse;
   
private String niveau4Libelle;
   private String objet1Libelle;
   private String objet2Libelle;
   private String choix;
   private String idTerminal;
   private String dateins;
private boolean defect;
List listePhotos= new ArrayList();
private String utilisateur;
private Date inspectioneDate;
public Integer getIdInspection() {
	return idInspection;
}
public void setIdInspection(Integer idInspection) {
	this.idInspection = idInspection;
}
public String getNiveau2Libelle() {
	return niveau2Libelle;
}
public void setNiveau2Libelle(String niveau2Libelle) {
	this.niveau2Libelle = niveau2Libelle;
}
public String getIdiniformation() {
	return idiniformation;
}
public void setIdiniformation(String idiniformation) {
	this.idiniformation = idiniformation;
}
public String getNiveau3Libelle() {
	return niveau3Libelle;
}
public void setNiveau3Libelle(String niveau3Libelle) {
	this.niveau3Libelle = niveau3Libelle;
}
public String getReponse() {
	return reponse;
}
public void setReponse(String reponse) {
	this.reponse = reponse;
}
public String getNiveau4Libelle() {
	return niveau4Libelle;
}
public void setNiveau4Libelle(String niveau4Libelle) {
	this.niveau4Libelle = niveau4Libelle;
}
public String getObjet1Libelle() {
	return objet1Libelle;
}
public void setObjet1Libelle(String objet1Libelle) {
	this.objet1Libelle = objet1Libelle;
}
public String getObjet2Libelle() {
	return objet2Libelle;
}
public void setObjet2Libelle(String objet2Libelle) {
	this.objet2Libelle = objet2Libelle;
}
public String getChoix() {
	return choix;
}
public void setChoix(String choix) {
	this.choix = choix;
}
public String getIdTerminal() {
	return idTerminal;
}
public void setIdTerminal(String idTerminal) {
	this.idTerminal = idTerminal;
}
public boolean isDefect() {
	return defect;
}
public void setDefect(boolean defect) {
	this.defect = defect;
}
public List getListePhotos() {
	return listePhotos;
}
public void setListePhotos(List listePhotos) {
	this.listePhotos = listePhotos;
}
public String getUtilisateur() {
	return utilisateur;
}
public void setUtilisateur(String utilisateur) {
	this.utilisateur = utilisateur;
}
public Date getInspectioneDate() {
	return inspectioneDate;
}
public void setInspectioneDate(Date inspectioneDate) {
	this.inspectioneDate = inspectioneDate;
}
public String getDateins() {
	return dateins;
}
public void setDateins(String dateins) {
	this.dateins = dateins;
}

 
 


 }

