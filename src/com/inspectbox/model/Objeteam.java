package com.inspectbox.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

@Table(name="objeteam",  catalog="inspectboxsa")
public class Objeteam  implements Serializable {
	   private Integer idObjet;
	  
	   private Integer idField;
	   private boolean masque;
	   private boolean lun;
	   private boolean mar;
	   private boolean mer;
	   private boolean jeu;
	   private boolean ven;
	   private boolean sam;
	   private boolean dim;
	   private Client client;
	   private Utilisateur team;
	   @Column(name="masque", nullable=false)
	   public boolean isMasque() {
		return masque;
	}
	public void setMasque(boolean masque) {
		this.masque = masque;
	}
	@Id
	   @GeneratedValue(strategy=GenerationType.IDENTITY)
	   @Column(name="idField", unique=true, nullable=false)
	   public Integer getIdField() {
		return idField;
	}
	public void setIdField(Integer idField) {
		this.idField = idField;
	}
	
	   @Column(name="idObjet", unique=true, nullable=false)
	public Integer getIdObjet() {
		return idObjet;
	}
	public void setIdObjet(Integer idObjet) {
		this.idObjet = idObjet;
	}
	
	   
	@ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name="idClient", nullable=false)
	   public Client getClient() {
	    return this.client;
	   }
	 
	   public void setClient(Client client) {
	     this.client = client;
	   }
	   @Column(name="lun", nullable=false)
	public boolean isLun() {
		return lun;
	}
	public void setLun(boolean lun) {
		this.lun = lun;
	}
	@Column(name="mar", nullable=false)
	public boolean isMar() {
		return mar;
	}
	public void setMar(boolean mar) {
		this.mar = mar;
	}
	@Column(name="mer", nullable=false)
	public boolean isMer() {
		return mer;
	}
	public void setMer(boolean mer) {
		this.mer = mer;
	}
	@Column(name="jeu", nullable=false)
	public boolean isJeu() {
		return jeu;
	}
	public void setJeu(boolean jeu) {
		this.jeu = jeu;
	}
	@Column(name="ven", nullable=false)
	public boolean isVen() {
		return ven;
	}
	public void setVen(boolean ven) {
		this.ven = ven;
	}
	@Column(name="sam", nullable=false)
	public boolean isSam() {
		return sam;
	}
	public void setSam(boolean sam) {
		this.sam = sam;
	}
	@Column(name="dim", nullable=false)
	public boolean isDim() {
		return dim;
	}
	public void setDim(boolean dim) {
		this.dim = dim;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name="idTeam", nullable=false)
	public Utilisateur getTeam() {
		return team;
	}
	public void setTeam(Utilisateur team) {
		this.team = team;
	}
	
	

}
