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
@Table(name="team",  catalog="inspectboxsa")
public class Team implements Serializable {
	 private Integer idTeam;
	private String beginHour;
	   private String endHour;
	   private String dayOfWeek;
	   private Integer utilisateur;
	   private boolean masque;
	   private Client client;
	   public Team()
	   {
	   }
	   @Id
	   @GeneratedValue(strategy=GenerationType.IDENTITY)
	   @Column(name="IdTeam", unique=true, nullable=false)
	public Integer getIdTeam() {
		return idTeam;
	}

	public void setIdTeam(Integer idTeam) {
		this.idTeam = idTeam;
	}
	@Column(name="BeginHour" , nullable=false)
	public String getBeginHour() {
		return beginHour;
	}

	public void setBeginHour(String beginHour) {
		this.beginHour = beginHour;
	}
	@Column(name="EndHour" , nullable=false)
	public String getEndHour() {
		return endHour;
	}

	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
	
	@Column(name="DayOfWork" , nullable=false)
	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	@Column(name="IdUser", nullable=false)
	public Integer getIdUtilisateur() {
		return utilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.utilisateur = idUtilisateur;
	}
	@Column(name="Masque", nullable=false)
	public boolean isMasque() {
		return masque;
	}
	public void setMasque(boolean masque) {
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
