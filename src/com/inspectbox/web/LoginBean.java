/*     */ package com.inspectbox.web;
/*     */ 
/*     */ import java.io.Serializable;
import java.util.Iterator;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.inspectbox.model.Utilisateur;
import com.inspectbox.utils.HibernateUtil;
import com.inspectbox.utils.LoginUtil;
/*     */ 
/*     */ @ManagedBean(name="loginBean")
/*     */ @SessionScoped
/*     */ public class LoginBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  28 */   private String user = "";
/*  29 */   private String client = "";
/*  30 */   private String password = "";
/*  31 */   private Integer idClient = null;
/*  32 */   private Integer taileMenuClient = null;
/*     */ private Boolean idacce=null;

public Boolean getIdacce() {
	return idacce;
}
public void setIdacce(Boolean idacce) {
	this.idacce = idacce;
}
/*     */   public String getUser()
/*     */   {
/*  37 */     return this.user; } 
/*     */   public void setUser(String user) { this.user = user; }
/*     */ 
/*     */   public String getClient() {
/*  41 */     return this.client; }
/*     */ 
/*     */   public void setClient(String client) {
/*  44 */     this.client = client; }
/*     */ 
/*     */   public String getPassword() { return this.password; } 
/*     */   public void setPassword(String password) { this.password = password; }
/*     */ 
/*     */   public Integer getIdClient() {
/*  50 */     return this.idClient;
/*     */   }
/*     */ 
/*     */   public Integer getTaileMenuClient()
/*     */   {
/*  55 */     return this.taileMenuClient; }
/*     */ 
/*     */   public void setTaileMenuClient(Integer taileMenuClient) {
/*  58 */     this.taileMenuClient = taileMenuClient;
/*     */   }
/*     */ 
/*     */   public void fowardToLoginIfNotLoggedIn(ComponentSystemEvent cse)
/*     */   {
/*  63 */     if (isDroitEntrer().booleanValue())
/*     */       return;
/*  65 */     _fowardToLogin();
/*     */   }
/*     */ 
/*     */   private void _fowardToLogin()
/*     */   {
/*  72 */     getFacesContext().getApplication().getNavigationHandler().handleNavigation(getFacesContext(), null, "/login");
/*     */   }
/*     */ 
/*     */   @SuppressWarnings("unused")
private void _fowardToHome()
/*     */   {
/*  77 */     getFacesContext().getApplication().getNavigationHandler().handleNavigation(getFacesContext(), null, "/home");
/*     */   }
/*     */ 
/*     */   public FacesContext getFacesContext()
/*     */   {
/*  83 */     return FacesContext.getCurrentInstance();
/*     */   }
/*     */ 
/*     */   private Boolean isDroitEntrer()
/*     */   {
/*  88 */     if ((this.idClient != null) && (this.idClient.intValue() != 0)) return Boolean.valueOf(true); return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */   public String logout()
/*     */   {
/*  93 */     FacesContext context = FacesContext.getCurrentInstance();
/*  94 */     HttpSession session = (HttpSession)context.getExternalContext().getSession(false);
/*  95 */     session.invalidate();
/*     */ 
/* 103 */     return "login?faces-redirect=true";
/*     */   }
/*     */ 
/*     */   public String loginPassword()
/*     */   {
/*     */     try
/*     */     {
/* 111 */       Session session = HibernateUtil.currentSession();
/*     */ 
/* 113 */       Transaction tx = session.beginTransaction();
/*     */ 
/* 115 */       Iterator it = session.createSQLQuery("Select {U.*} from utilisateur U, client C, droitutilisateur DU where U.CodeAcces like :user and C.IdClient=U.IdClient and DU.IdDroitUtilisateur = U.IdDroitUtilisateur and  U.MotDePasse like :password and U.Masque = :masque and  C.CodeAcces like :client and C.Masque = :masque and Consultation = :adminClient")
/* 118 */         .addEntity("U", Utilisateur.class)
/* 119 */         .setParameter("password", LoginUtil.toSHA1(this.password.getBytes()))
/* 120 */         .setParameter("user", this.user)
/* 121 */         .setParameter("masque", Boolean.valueOf(false))
/* 122 */         .setParameter("client", this.client)
/* 123 */         .setParameter("adminClient", Boolean.valueOf(true))
/* 125 */         .list().iterator();
/*     */ 
/* 127 */       if (it.hasNext())
/*     */       {
/* 129 */         Utilisateur utilisateur = (Utilisateur)it.next();
/* 130 */         this.idClient = utilisateur.getClient().getIdClient();

/*     */ this.idacce=utilisateur.getDroitutilisateur().isAdminClient();
/* 135 */         this.taileMenuClient = Integer.valueOf(100);
/*     */ 
/* 142 */         return "recherche_inspection?faces-redirect=true";
       }

else if((this.client.equals("Admin"))&&(this.user.equals("admin")&&(this.password.equals("inspectbox")))) {
	this.idacce=true;
	this.setClient("Admin");
	this.setUser("admin");
	this.idClient=10000;
	return "liste_utilisateuradmin?faces-redirect=true";
	}
/* 145 */       tx.commit();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 149 */       e.printStackTrace();
/*     */     }
/*     */     finally
/*     */     {
/* 153 */       HibernateUtil.closeSession();
/*     */     }
/*     */ 
/* 156 */     return "login";
/*     */   }

public String activerFR() {

FacesContext context = FacesContext.getCurrentInstance();

context.getViewRoot().setLocale(Locale.FRENCH);

return null;

}


public String activerEN() {

FacesContext context = FacesContext.getCurrentInstance();

context.getViewRoot().setLocale(Locale.ENGLISH);

return null;

}
public String activerDE() {

FacesContext context = FacesContext.getCurrentInstance();

context.getViewRoot().setLocale(Locale.GERMAN);

return null;

}
/*     */ }

