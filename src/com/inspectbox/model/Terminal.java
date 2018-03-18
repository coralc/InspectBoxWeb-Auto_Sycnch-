package com.inspectbox.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="terminal", catalog="inspectboxsa")
public class Terminal implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idTerminal;
	  private boolean masque;
	@Id
	   @GeneratedValue(strategy=GenerationType.IDENTITY)
	   @Column(name="idTerminal", unique=true, nullable=false)
	public String getIdTerminal() {
		return idTerminal;
	}

	public void setIdTerminal(String idTerminal) {
		this.idTerminal = idTerminal;
	}
	@Column(name="masque")
	public boolean isMasque() {
		return masque;
	}

	public void setMasque(boolean masque) {
		this.masque = masque;
	}
}
