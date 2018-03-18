 package com.inspectbox.web;
 
 import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.inspectbox.databaseLayout.NiveauDatabaseLayout;
import com.inspectbox.databaseLayout.NiveauObjetDatabaseLayout;
import com.inspectbox.databaseLayout.SynchDatabaseLayout;
import com.inspectbox.databaseLayout.TerminalDatabaseLayout;
import com.inspectbox.model.Niveauobjet;
import com.inspectbox.model.SynchPK;
import com.inspectbox.model.Terminal;
import com.inspectbox.objetLayout.ObjetNavigation;
import com.inspectbox.utils.ArianeUtil;
import com.inspectbox.utils.I18nUtil;
import com.inspectbox.utils.LoginUtil;
 
 @ManagedBean(name="ajouterObjetBean")
 @ViewScoped
 public class AjouterObjetBean  implements java.io.Serializable 
 {
 
   
   private String nom;
/*  36 */   private String codeBarre = "";
/*  37 */   private Integer idObjetParent = Integer.valueOf(0);
/*  38 */   private Integer idObjetCourant = Integer.valueOf(0);
/*  39 */   private Integer triMax = Integer.valueOf(0);
/*  40 */   private List<ObjetNavigation> listeAriane = new ArrayList();
/*  41 */   private Boolean suprimable = Boolean.valueOf(true);
 
   public Boolean getSuprimable()
   {
/*  45 */     return this.suprimable; }
 
   public void setSuprimable(Boolean suprimable) {
/*  48 */     this.suprimable = suprimable; }
 
   public String getNom() {
/*  51 */     return this.nom; }
 
   public void setNom(String nom) {
/*  54 */     this.nom = nom; }
 
   public String getCodeBarre() {
/*  57 */     return this.codeBarre; }
 
   public void setCodeBarre(String codeBarre) {
/*  60 */     this.codeBarre = codeBarre; }
 
   public Integer getIdObjetParent() {
/*  63 */     return this.idObjetParent; }
 
   public void setIdObjetParent(Integer idObjetParent) {
/*  66 */     this.idObjetParent = idObjetParent;
   }
 
   public Integer getIdObjetCourant()
   {
/*  71 */     return this.idObjetCourant; }
 
   public void setIdObjetCourant(Integer idObjetCourant) {
/*  74 */     this.idObjetCourant = idObjetCourant;
   }
 
   public Integer getTriMax() {
/*  78 */     return this.triMax; }
 
   public void setTriMax(Integer triMax) {
/*  81 */     this.triMax = triMax;
   }
 
   public List<ObjetNavigation> getListeAriane()
   {
/*  86 */     return this.listeAriane; }
 
   public void setListeAriane(List<ObjetNavigation> listeAriane) {
/*  89 */     this.listeAriane = listeAriane;
   }
 
   public String consulterAjouter(Integer idObjetParent, Integer triMax)
   {
/*  95 */     this.idObjetParent = idObjetParent;
/*  96 */    // this.idObjetCourant = Integer.valueOf(0);
/*  97 */     this.triMax = triMax;
 
/*  99 */     return "ajouter_objet";
   }
 
   public String consulterModifier(Integer idObjetParent, Integer idObjetCourant) {
/* 103 */     this.idObjetParent = idObjetParent;
/* 104 */     this.idObjetCourant = idObjetCourant;
/* 105 */     this.triMax = Integer.valueOf(0);
 
/* 107 */     return "modifier_objet";
   }
 
   public void afficher()
   {System.out.println("***this.idObjetCourant"+this.idObjetCourant);
/* 118 */     LoginBean bean = LoginUtil.getLoginBean();
/* 119 */     NiveauObjetDatabaseLayout niveauObjet = null;
 
/* 122 */     this.listeAriane.add(new ObjetNavigation(I18nUtil.get("navigation_parametrage_objets"), Integer.valueOf(0)));
     try {
/* 124 */       niveauObjet = new NiveauObjetDatabaseLayout();
/* 125 */       niveauObjet.transactionOpen();
/* 126 */       niveauObjet.afficher(bean.getIdClient(), this.idObjetParent);
 
/* 128 */       this.listeAriane.addAll(ArianeUtil.listObjet(niveauObjet.model()));
/* 129 */       niveauObjet.transactionCommit();
     } catch (Exception e) {
/* 131 */       e.getMessage();
     } finally {
/* 133 */       niveauObjet.close();
     }
 
/* 137 */     if (this.idObjetCourant.intValue() == 0) {
       return;
     }
/* 140 */     this.triMax = Integer.valueOf(0);
/* 141 */     niveauObjet = null;
     try
     {
/* 144 */       niveauObjet = new NiveauObjetDatabaseLayout();
 
/* 147 */       niveauObjet.afficher(bean.getIdClient(), this.idObjetCourant);
 
/* 149 */       this.nom = niveauObjet.model().getLibelle();
/* 150 */       this.codeBarre = niveauObjet.model().getCodeBarre();
 
/* 153 */       if(niveauObjet.model().getNiveauobjet() == null)
					{
						NiveauDatabaseLayout niveau = new NiveauDatabaseLayout();					
						suprimable = niveau.isNoObjetLink(niveauObjet.model().getIdNiveauObjet(),bean.getIdClient());
					
					}
     }
     catch (Exception e)
     {
/* 160 */       e.getMessage();
     } finally {
/* 162 */       niveauObjet.close();
     }
   }
 
   public String ajouter()
   {
     LoginBean bean = LoginUtil.getLoginBean();
     NiveauObjetDatabaseLayout niveauObjet = null;
     try
     {
      niveauObjet = new NiveauObjetDatabaseLayout();
      niveauObjet.transactionOpen();
 
       niveauObjet.setClient(bean.getIdClient());
      if (this.idObjetParent.intValue() != 0)
       {
         niveauObjet.setNiveauObjetParent(this.idObjetParent);
       }
 
      if (niveauObjet.verifierDroitAjouter().booleanValue())
       {
         niveauObjet.model().setLibelle(this.nom);
        niveauObjet.model().setCodeBarre(this.codeBarre);
         niveauObjet.model().setTri(this.triMax.intValue() + 1);
 

        niveauObjet.model().setMasque(false);
         niveauObjet.model().setClefTimestamp(new Date());
 
        niveauObjet.save();
       }
 
       niveauObjet.transactionCommit();

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
      System.out.println("Erreur d'ajout");
     e.printStackTrace();
     return "ajouter_objet";
     } finally {
      niveauObjet.close();
     }
 
     return "liste_objet?faces-redirect=true";
   }
 
   public String modifier()
   { 
	       LoginBean bean = LoginUtil.getLoginBean();
    NiveauObjetDatabaseLayout niveauObjet = null;
    TerminalDatabaseLayout termDB= new TerminalDatabaseLayout();
    termDB.transactionOpen();
     try
     {
      niveauObjet = new NiveauObjetDatabaseLayout();
      niveauObjet.transactionOpen();
 
       if (niveauObjet.verifierDroitModifier(bean.getIdClient(), this.idObjetCourant).booleanValue())
       {
       niveauObjet.model().setLibelle(this.nom);
        niveauObjet.model().setCodeBarre(this.codeBarre);
         niveauObjet.model().setClefTimestamp(new Date());
 
        niveauObjet.update();
       }
 
      niveauObjet.transactionCommit();
   // save synchro 
      
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
      
      
     }
     catch (Exception e) {
      System.out.println("Erreur de modification");
e.printStackTrace();
     
 
       return "modifier_objet";
     } finally {
      niveauObjet.close(); termDB.close();
     }
 
    return "liste_objet?faces-redirect=true";
   }
 
   public String retourback()
   {
	   return "liste_objet?faces-redirect=true";
   }
   
   public String supprimer()
   {
     LoginBean bean = LoginUtil.getLoginBean();
     NiveauObjetDatabaseLayout niveauObjet = null;
     TerminalDatabaseLayout termDB= new TerminalDatabaseLayout();
     termDB.transactionOpen();
     try
     {
       niveauObjet = new NiveauObjetDatabaseLayout();
       niveauObjet.transactionOpen();
 
       if (niveauObjet.verifierDroitModifier(bean.getIdClient(), this.idObjetCourant).booleanValue())
       {
         niveauObjet.model().setMasque(true);
        niveauObjet.model().setClefTimestamp(new Date());
 
         niveauObjet.update();
       }
 
      List listeMasque = new ArrayList();
       listeMasque.add(this.idObjetCourant);
      niveauObjet.listerAllNonInitial(bean.getIdClient());
       Iterator itNiveauObjet = niveauObjet.liste().iterator();
      while (itNiveauObjet.hasNext())
       {
         Niveauobjet niv = (Niveauobjet)itNiveauObjet.next();
 
         if (!(listeMasque.contains(niv.getNiveauobjet().getIdNiveauObjet()))) {
           continue;
         }
 
         niveauObjet.setModel(niv);
         niveauObjet.model().setMasque(true);
        niveauObjet.model().setClefTimestamp(new Date());
 
        niveauObjet.update();
 
         listeMasque.add(niv.getIdNiveauObjet());
       }
 
      niveauObjet.transactionCommit();
      
   // save synchro 
      
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
     }
     catch (Exception e) {
       System.out.println("Erreur de modification");
      e.printStackTrace();
 
       return "modifier_objet";
     } finally {
     niveauObjet.close();  termDB.close();  
     }
 
     return "liste_objet?faces-redirect=true";
   }
 }

