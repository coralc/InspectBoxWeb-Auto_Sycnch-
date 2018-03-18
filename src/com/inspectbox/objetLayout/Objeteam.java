package com.inspectbox.objetLayout;

import java.io.Serializable;

public class Objeteam implements Serializable {
	 private static final long serialVersionUID = 1L;
	 private Integer idTeam;
		private String beginHour;
		   private String endHour;
		   private String dayOfWeek;
		   private Integer utilisateur;
		  
		public Integer getIdTeam() {
			return idTeam;
		}
		public void setIdTeam(Integer idTeam) {
			this.idTeam = idTeam;
		}
		public String getBeginHour() {
			return beginHour;
		}
		public void setBeginHour(String beginHour) {
			this.beginHour = beginHour;
		}
		public String getEndHour() {
			return endHour;
		}
		public void setEndHour(String endHour) {
			this.endHour = endHour;
		}
		public String getDayOfWeek() {
			return dayOfWeek;
		}
		public void setDayOfWeek(String dayOfWeek) {
			this.dayOfWeek = dayOfWeek;
		}
		public Integer getUtilisateur() {
			return utilisateur;
		}
		public void setUtilisateur(Integer utilisateur) {
			this.utilisateur = utilisateur;
		}
		

}
