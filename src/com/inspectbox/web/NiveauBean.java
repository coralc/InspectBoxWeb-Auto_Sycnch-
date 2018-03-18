 package com.inspectbox.web;
 
 import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.validation.constraints.Size;

import com.inspectbox.databaseLayout.NiveauDatabaseLayout;
import com.inspectbox.databaseLayout.SynchDatabaseLayout;
import com.inspectbox.databaseLayout.TerminalDatabaseLayout;
import com.inspectbox.model.Niveau;
import com.inspectbox.model.SynchPK;
import com.inspectbox.model.Terminal;
import com.inspectbox.objetLayout.ObjetNavigation;
import com.inspectbox.utils.ArianeUtil;
import com.inspectbox.utils.I18nUtil;
import com.inspectbox.utils.LoginUtil;
 
 @ManagedBean(name="niveauBean")

@ViewScoped
 public class NiveauBean implements java.io.Serializable 
 {
 
   private Integer par=Integer.valueOf(0);
   private String nom;
private String err;
public String getErr() {
	return err;
}

public void setErr(String err) {
	this.err = err;
}
/*  37 */   private String codeBarre = "";
private String areaTag = "";
public String getAreaTag() {
	return areaTag;
}

public void setAreaTag(String areaTag) {
	this.areaTag = areaTag;
}
/*  38 */   private Integer idNiveauParent = Integer.valueOf(0);
/*  39 */   private Integer idNiveauCourant = Integer.valueOf(0);
/*  40 */   private Integer triMax = Integer.valueOf(0);
private String link;
private String libelleNiveauCourant;
public String getLink() {
	return link;
}

public void setLink(String link) {
	this.link = link;
}

public String getLibelleNiveauCourant() {
	return libelleNiveauCourant;
}

public void setLibelleNiveauCourant(String libelleNiveauCourant) {
	this.libelleNiveauCourant = libelleNiveauCourant;
}
/*  41 */   private List<ObjetNavigation> listeAriane = new ArrayList();
 
   public String getNom()
   {
/*  45 */     return this.nom; }

 
   public void setNom(String nom) {
/*  48 */     this.nom = nom; }
 
   public String getCodeBarre() {
/*  51 */     return this.codeBarre; }
 
   public void setCodeBarre(String codeBarre) {
/*  54 */     this.codeBarre = codeBarre; }
 
   public Integer getIdNiveauParent() {
/*  57 */     return this.idNiveauParent; }
 
   public void setIdNiveauParent(Integer IdNiveauParent) {
/*  60 */     this.idNiveauParent = IdNiveauParent;
   }
 
   public Integer getIdNiveauCourant()
   {
/*  65 */     return this.idNiveauCourant; }
 
   public void setIdNiveauCourant(Integer IdNiveauCourant) {
/*  68 */     this.idNiveauCourant = IdNiveauCourant;
   }
 
   public Integer getTriMax()
   {
/*  73 */     return this.triMax; }
 
   public void setTriMax(Integer triMax) {
/*  76 */     this.triMax = triMax; }
 
   public List<ObjetNavigation> getListeAriane() {
/*  79 */     return this.listeAriane; }
 
   public void setListeAriane(List<ObjetNavigation> listeAriane) {
/*  82 */     this.listeAriane = listeAriane;
   }
 
   public String consulterAjouter(Integer IdNiveauParent, Integer triMax)
   {
/*  88 */     this.idNiveauParent = IdNiveauParent;
/*  89 */     this.idNiveauCourant = Integer.valueOf(0);
/*  90 */     this.triMax = triMax;
 
/*  92 */     return "ajouter_niveau";
   }
 
   public Integer getPar() {
	return par;
}

public void setPar(Integer par) {
	this.par = par;
}

public String consulterModifier(Integer IdNiveauParent, Integer IdNiveauCourant) {
/*  96 */     this.idNiveauParent = IdNiveauParent;
/*  97 */     this.idNiveauCourant = IdNiveauCourant;
/*  98 */     this.triMax = Integer.valueOf(0);
 
/* 100 */     return "modifier_niveau";
   }
 
public String retourback()
   {
	
	return "liste_niveau?faces-redirect=true";
}

public void afficher2()
{
	
}
   public void afficher()
   {
/* 109 */     LoginBean bean = LoginUtil.getLoginBean();
/* 110 */     NiveauDatabaseLayout niveau = null;
 
/* 113 */     this.listeAriane.add(new ObjetNavigation(I18nUtil.get("navigation_parametrage_niveaux"), Integer.valueOf(0)));
     try {
/* 115 */       niveau = new NiveauDatabaseLayout();
/* 116 */       niveau.afficher(bean.getIdClient(), this.idNiveauParent);
 
/* 118 */       this.listeAriane.addAll(ArianeUtil.listNiveau(niveau.model()));
     } catch (Exception e) {
/* 120 */       e.getMessage();
     } finally {
/* 122 */       niveau.close();
     }
 
/* 128 */     if (this.idNiveauCourant.intValue() == 0)
       return;
/* 130 */     this.triMax = Integer.valueOf(0);
 
/* 132 */     niveau = null;
     try
     {
/* 135 */       niveau = new NiveauDatabaseLayout();
 
/* 138 */       niveau.afficher(bean.getIdClient(), this.idNiveauCourant);
 
/* 140 */       this.nom = niveau.model().getLibelle();
/* 141 */       this.codeBarre = niveau.model().getCodeBarre();
				this.areaTag=niveau.model().getAreaTag();
				this.par=niveau.model().getParentId();
				if (this.par ==null) par=0;
     }
     catch (Exception e) {
      e.printStackTrace();
     } finally {
       niveau.close();
     }
   }
 
   

public String ajouter()
  {
 
	Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
	
	Integer tri=1;
	if ((this.nom.equals(""))|| (this.nom ==null))
	{
		FacesContext context = FacesContext.getCurrentInstance();  
        
        context.addMessage(null, new FacesMessage(I18nUtil.get("erreur_libelle_gamme")));  
        
	return "ajouter_niveau";
	}
	else
	{
    LoginBean bean = LoginUtil.getLoginBean();
    NiveauDatabaseLayout niveau = null;
    NiveauDatabaseLayout niveauTri = null;
     try
     {
      niveau = new NiveauDatabaseLayout();
      niveau.transactionOpen();
 
      niveauTri = new NiveauDatabaseLayout();
      niveauTri.transactionOpen();
 
       niveau.setClient(bean.getIdClient());
       if (this.idNiveauParent.intValue() != 0)
       {
         niveau.setNiveauParent(this.idNiveauParent);
         niveau.model().setLevel(2);
       }
       else
       {
    	   niveau.model().setLevel(1);
       }
 
      
         niveau.model().setLibelle(this.nom);
         niveau.model().setCodeBarre(this.codeBarre);
        niveau.model().setAreaTag(this.areaTag);
        niveau.model().setMasque(false);
        niveau.model().setClefTimestamp(new Date());
      //chercher le bon tri
        if ((this.idNiveauParent==0)|| (this.idNiveauParent==null))
        {
        	niveauTri.afficherLasttreeSite(bean.getIdClient());
        	 if (niveauTri.model()!=null)
             	  tri=niveauTri.model().getTri()+1;
        	
        }
        else
        {
        	 niveauTri.afficherLasttree(bean.getIdClient(), this.idNiveauParent);
             if (niveauTri.model()!=null)
           	  tri=niveauTri.model().getTri()+1;
        }
       
        niveau.model().setTri(tri);
         niveau.save();

 
      niveau.transactionCommit();

   // save synchro 
   TerminalDatabaseLayout termDB= new TerminalDatabaseLayout();
   	          termDB.transactionOpen();
   	           

   	          
   	          termDB.listerAll();
   	          Iterator it = termDB.liste().iterator();
   	          while (it.hasNext())
   		        {
   		          Terminal term = (Terminal)it.next();
   		           SynchDatabaseLayout synchdb = null;
   			 		synchdb = new SynchDatabaseLayout();
   			 		synchdb.transactionOpen();
   		          synchdb.afficher(bean.getIdClient(), term.getIdTerminal());
   		          if (synchdb.model()!=null)//MAJ
   		          { if (synchdb.model().getEtat()==0)
   		          {
   		        	  synchdb.model().setEtat(1);
   		        	  synchdb.model().setDateInformation(new Date());
   		        	  synchdb.update();
   		        	  synchdb.transactionCommit();
   		          }
   		        	  
   		          }
   		          else // creation
   		          {
   		        	  SynchDatabaseLayout synchdbnw = null;
   		        	  synchdbnw = new SynchDatabaseLayout();
   		        	  synchdbnw.transactionOpen();
   		        	  SynchPK synchpk=new SynchPK();
   		        	  synchpk.setIdClient(bean.getIdClient());
   		        	  synchpk.setIdTerminal(term.getIdTerminal());
   		        	  synchdbnw.model().setEtat(1);
   		        	  synchdbnw.model().setDateInformation(new Date());
   		        	  synchdbnw.model().setSynchPK(synchpk);
   		        	  synchdbnw.save();
   		        	  synchdbnw.transactionCommit();
   		          }
   		          
   		        } 
   	     termDB.close();     
     }
     catch (Exception e)
     {
      System.out.println("Erreur d'ajout niveau");
      e.printStackTrace();
 
       return "ajouter_niveau";
     } finally {
      niveau.close();
     }
 
     return "liste_niveau?faces-redirect=true";
   }}
 
   public String modifier()
   {
     LoginBean bean = LoginUtil.getLoginBean();
    NiveauDatabaseLayout niveau = null;
     try {
      niveau = new NiveauDatabaseLayout();
       niveau.transactionOpen();
 
       if (niveau.verifierDroitModifier(bean.getIdClient(), this.idNiveauCourant).booleanValue())
       {
         niveau.model().setLibelle(this.nom);
         niveau.model().setCodeBarre(this.codeBarre);
					niveau.model().setAreaTag(this.areaTag);
        niveau.model().setClefTimestamp(new Date());
 
        niveau.update();niveau.transactionCommit();
        

     // save synchro 
     TerminalDatabaseLayout termDB= new TerminalDatabaseLayout();
     	          termDB.transactionOpen();
     	           

     	          
     	          termDB.listerAll();
     	          Iterator it = termDB.liste().iterator();
     	          while (it.hasNext())
     		        {
     		          Terminal term = (Terminal)it.next();
     		           SynchDatabaseLayout synchdb = null;
     			 		synchdb = new SynchDatabaseLayout();
     			 		synchdb.transactionOpen();
     		          synchdb.afficher(bean.getIdClient(), term.getIdTerminal());
     		          if (synchdb.model()!=null)//MAJ
     		          { if (synchdb.model().getEtat()==0)
     		          {
     		        	  synchdb.model().setEtat(1);
     		        	  synchdb.model().setDateInformation(new Date());
     		        	  synchdb.update();
     		        	  synchdb.transactionCommit();
     		          }
     		        	  
     		          }
     		          else // creation
     		          {
     		        	  SynchDatabaseLayout synchdbnw = null;
     		        	  synchdbnw = new SynchDatabaseLayout();
     		        	  synchdbnw.transactionOpen();
     		        	  SynchPK synchpk=new SynchPK();
     		        	  synchpk.setIdClient(bean.getIdClient());
     		        	  synchpk.setIdTerminal(term.getIdTerminal());
     		        	  synchdbnw.model().setEtat(1);
     		        	  synchdbnw.model().setDateInformation(new Date());
     		        	  synchdbnw.model().setSynchPK(synchpk);
     		        	  synchdbnw.save();
     		        	  synchdbnw.transactionCommit();
     		          }
     		          
     		        } 
     	     termDB.close();     
       }
 
       
     }
     catch (Exception e) {
       System.out.println("Erreur de modification");
       e.printStackTrace();
 
      return "modifier_niveau?faces-redirect=true";
     } finally {
      niveau.close();
     }
 
     return "liste_niveau?faces-redirect=true";
   }
 
   public String supprimer()
   {
	
     LoginBean bean = LoginUtil.getLoginBean();
     NiveauDatabaseLayout niveau = null;
     try {
       niveau = new NiveauDatabaseLayout();
      niveau.transactionOpen();
 
      if (niveau.verifierDroitModifier(bean.getIdClient(), this.idNiveauCourant).booleanValue())
       {
        niveau.model().setMasque(true);
         niveau.model().setClefTimestamp(new Date());
 
         niveau.update();
       }
 
      List listeMasque = new ArrayList();
       listeMasque.add(this.idNiveauCourant);
      niveau.listerAllNonInitial(bean.getIdClient());
      Iterator itNiveau = niveau.liste().iterator();
       while (itNiveau.hasNext())
       {
        Niveau niv = (Niveau)itNiveau.next();
 
         if (!(listeMasque.contains(niv.getNiveau().getIdNiveau()))) {
           continue;
         }
 
         niveau.setModel(niv);
        niveau.model().setMasque(true);
        niveau.model().setClefTimestamp(new Date());
 
        niveau.update();
 
        listeMasque.add(niv.getIdNiveau());
       }
 
      niveau.transactionCommit();
      

   // save synchro 
   TerminalDatabaseLayout termDB= new TerminalDatabaseLayout();
   	          termDB.transactionOpen();
   	           

   	          
   	          termDB.listerAll();
   	          Iterator it = termDB.liste().iterator();
   	          while (it.hasNext())
   		        {
   		          Terminal term = (Terminal)it.next();
   		           SynchDatabaseLayout synchdb = null;
   			 		synchdb = new SynchDatabaseLayout();
   			 		synchdb.transactionOpen();
   		          synchdb.afficher(bean.getIdClient(), term.getIdTerminal());
   		          if (synchdb.model()!=null)//MAJ
   		          { if (synchdb.model().getEtat()==0)
   		          {
   		        	  synchdb.model().setEtat(1);
   		        	  synchdb.model().setDateInformation(new Date());
   		        	  synchdb.update();
   		        	  synchdb.transactionCommit();
   		          }
   		        	  
   		          }
   		          else // creation
   		          {
   		        	  SynchDatabaseLayout synchdbnw = null;
   		        	  synchdbnw = new SynchDatabaseLayout();
   		        	  synchdbnw.transactionOpen();
   		        	  SynchPK synchpk=new SynchPK();
   		        	  synchpk.setIdClient(bean.getIdClient());
   		        	  synchpk.setIdTerminal(term.getIdTerminal());
   		        	  synchdbnw.model().setEtat(1);
   		        	  synchdbnw.model().setDateInformation(new Date());
   		        	  synchdbnw.model().setSynchPK(synchpk);
   		        	  synchdbnw.save();
   		        	  synchdbnw.transactionCommit();
   		          }
   		          
   		        } 
   	     termDB.close();     
     }
     catch (Exception e) {
       System.out.println("Erreur de modification");
      e.printStackTrace();
 
       return "modifier_niveau";
     } finally {
       niveau.close();
     }
 
     return "liste_niveau?faces-redirect=true";
   }
 }

