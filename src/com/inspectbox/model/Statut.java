package com.inspectbox.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="statut", catalog="inspectboxsa")
public class Statut implements Serializable {
	 private Integer idStatus;
	 private String label;
	private Boolean masque;
	private Client client;
	@Id
	   @GeneratedValue(strategy=GenerationType.IDENTITY)
	   @Column(name="IdStatus", unique=true, nullable=false)
	public Integer getIdStatus() {
		return idStatus;
	}
	public void setIdStatus(Integer idStatus) {
		this.idStatus = idStatus;
	}
	@Column(name="Label", nullable=false)
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	@Column(name="Masque", nullable=false)
	public Boolean getMasque() {
		return masque;
	}
	public void setMasque(Boolean masque) {
		this.masque = masque;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	   @JoinColumn(name="idClient", nullable=false)
	   public Client getClient() {
	    return this.client;
	   }
	 
	   public void setClient(Client client) {
	     this.client = client;
	   }

}
