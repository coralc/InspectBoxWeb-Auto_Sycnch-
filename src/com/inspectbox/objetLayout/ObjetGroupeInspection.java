 package com.inspectbox.objetLayout;
 
 import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
 
 public class ObjetGroupeInspection
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private Integer idInspection;
   private String utilisateur;
   private String date;
   private String nomNiveauInitial;
   private Integer idNiveau;
   private Integer idNiveauInitial;
   private Integer idUtilisateur;
   private String clefDate;
			private Integer idNiveauBatiment;
			private Integer idNiveauLocalisation;
			private Integer idNiveauObjet;
			private boolean defect;
 
   public String getUtilisateur()
   {
/*  29 */     return this.utilisateur;
   }
 
   public void setUtilisateur(String utilisateur) {
/*  33 */     this.utilisateur = utilisateur;
   }
 
   public String getDate()
   {
/*  39 */     return this.date;
   }
 
   public void setDate(String date)
   {
/*  45 */     this.date = date;
   }
 
   public String getNomNiveauInitial()
   {
/*  51 */     return this.nomNiveauInitial;
   }
 
   public void setNomNiveauInitial(String nomNiveauInitial)
   {
/*  57 */     this.nomNiveauInitial = nomNiveauInitial;
   }
 
   public Integer getIdNiveau()
   {
/*  63 */     return this.idNiveau;
   }
 
   public void setIdNiveau(Integer idNiveau)
   {
/*  69 */     this.idNiveau = idNiveau;
   }
 
   public Integer getIdNiveauInitial()
   {
/*  75 */     return this.idNiveauInitial;
   }
 
   public void setIdNiveauInitial(Integer idNiveauInitial)
   {
/*  81 */     this.idNiveauInitial = idNiveauInitial;
   }
 
   public Integer getIdUtilisateur() {
/*  85 */     return this.idUtilisateur;
   }
 
   public void setIdUtilisateur(Integer idUtilisateur)
   {
/*  90 */     this.idUtilisateur = idUtilisateur;
   }
 
   public String getClefDate()
   {
/*  98 */     return this.clefDate;
   }
 
   public void setClefDate(String clefDate)
   {
/* 103 */     this.clefDate = clefDate;
   }
 
   public Integer getIdInspection()
   {
/* 108 */     return this.idInspection;
   }
 
   public void setIdInspection(Integer idInspection)
   {
/* 113 */     this.idInspection = idInspection;
   }
 
			public Integer getIdNiveauBatiment() {
				return idNiveauBatiment;
			}
			public void setIdNiveauBatiment(Integer idNiveauBatiment) {
				this.idNiveauBatiment = idNiveauBatiment;
			}
			public Integer getIdNiveauLocalisation() {
				return idNiveauLocalisation;
			}
			public void setIdNiveauLocalisation(Integer idNiveauLocalisation) {
				this.idNiveauLocalisation = idNiveauLocalisation;
			} 

			

			public Integer getIdNiveauObjet() {
				return idNiveauObjet;
			}
			public void setIdNiveauObjet(Integer idNiveauObjet) {
				this.idNiveauObjet = idNiveauObjet;
			}
			
public boolean isDefect() {
				return defect;
			}
			public void setDefect(boolean defect) {
				this.defect = defect;
			}
   public void convertDate(Date date)
   {
/* 119 */     SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
/* 120 */     this.date = simpleFormat.format(date);
 
/* 122 */     SimpleDateFormat simpleFormatClef = new SimpleDateFormat("yyyy-MM-dd");
/* 123 */     this.clefDate = simpleFormatClef.format(date);
 
/* 125 */     System.out.println(simpleFormat.format(date));
   }

}
