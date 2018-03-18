 package com.inspectbox.objetLayout;
 
 import java.io.Serializable;
 
 public class ObjetUtilisateur
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private Integer idUtilisateur;
   private String nom;
   private String prenom;
   private String codeAcess;
   private String motDePass;
   private Boolean droitAdminClient;
   private Boolean droitTerminal;
   private Integer tri;
   private String typeUser;
   private String client;
   public String getNom()
   {
     return this.nom;
   }
 
   public Integer getIdUtilisateur()
   {
     return this.idUtilisateur;
   }
 
   public void setIdUtilisateur(Integer idUtilisateur)
   {
/*  45 */     this.idUtilisateur = idUtilisateur;
   }
 
   public void setNom(String nom)
   {
/*  52 */     this.nom = nom;
   }
 
   public String getPrenom()
   {
/*  59 */     return this.prenom;
   }
 
   public void setPrenom(String prenom)
   {
/*  66 */     this.prenom = prenom;
   }
 
   public String getCodeAcess()
   {
/*  73 */     return this.codeAcess;
   }
 
   public void setCodeAcess(String codeAcess)
   {
/*  80 */     this.codeAcess = codeAcess;
   }
 
   public String getMotDePass()
   {
/*  87 */     return this.motDePass;
   }
 
   public void setMotDePass(String motDePass)
   {
/*  94 */     this.motDePass = motDePass;
   }
 
   public Boolean getDroitTerminal()
   {
/* 101 */     return this.droitTerminal;
   }
 
   public void setDroitTerminal(Boolean droitTerminal)
   {
/* 108 */     this.droitTerminal = droitTerminal;
   }
 
   public Boolean getDroitAdminClient()
   {
/* 115 */     return this.droitAdminClient;
   }
 
   public void setDroitAdminClient(Boolean droitAdminClient)
   {
/* 122 */     this.droitAdminClient = droitAdminClient;
   }
 
   public Integer getTri()
   {
/* 129 */     return this.tri;
   }
 
   public void setTri(Integer tri)
   {
/* 136 */     this.tri = tri;
   }

public String getTypeUser() {
	return typeUser;
}

public void setTypeUser(String typeUser) {
	this.typeUser = typeUser;
}

public String getClient() {
	return client;
}

public void setClient(String client) {
	this.client = client;
}
 }

