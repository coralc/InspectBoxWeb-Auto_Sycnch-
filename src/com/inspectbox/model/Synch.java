package com.inspectbox.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="synch", catalog="inspectboxsa")
public class Synch implements Serializable 
{
	@EmbeddedId
    private SynchPK synchPK;
	private Integer etat;
	private Date dateInformation;
	
	
	
	 
	 public SynchPK getSynchPK() {
		return synchPK;
	}
	public void setSynchPK(SynchPK synchPK) {
		this.synchPK = synchPK;
	}
	@Column(name="etat", nullable=false)
	public Integer getEtat() {
		return etat;
	}
	public void setEtat(Integer etat) {
		this.etat = etat;
	}
	 @Column(name="dateInformation", nullable=false)
	public Date getDateInformation() {
		return dateInformation;
	}
	public void setDateInformation(Date dateInformation) {
		this.dateInformation = dateInformation;
	}
	
}
