 package com.inspectbox.web;
 
 import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.inspectbox.databaseLayout.CodeBarreTmpDatabaseLayout;
import com.inspectbox.databaseLayout.NiveauDatabaseLayout;
import com.inspectbox.databaseLayout.SynchDatabaseLayout;
import com.inspectbox.databaseLayout.TerminalDatabaseLayout;
import com.inspectbox.model.Niveau;
import com.inspectbox.model.SynchPK;
import com.inspectbox.model.Terminal;
import com.inspectbox.objetLayout.Document;
import com.inspectbox.objetLayout.ObjetNiveau;
import com.inspectbox.utils.LoginUtil;
import com.inspectbox.utils.UUIDUtil;
 
 @ManagedBean(name="documentsController")
 @ViewScoped
 public class DocumentsController
   implements Serializable
 {
/*  38 */   private static final Logger logger = Logger.getLogger(DocumentsController.class.getName());
   private TreeNode root;
   private Document selectedDocument;
   private ObjetNiveau selectedNiveau;
   private ObjetNiveau[] selectedNiveaux;
   private List<ObjetNiveau> listeNiveaux;
 
   public List<ObjetNiveau> getListeNiveaux()
   {
/*  54 */     return this.listeNiveaux;
   }
 
   public void setListeNiveaux(List<ObjetNiveau> listeNiveaux) {
/*  58 */     this.listeNiveaux = listeNiveaux;
   }
 
   public TreeNode getRoot() {
/*  62 */     this.root = new DefaultTreeNode("root", null);
/*  63 */     TreeNode documents = new DefaultTreeNode(new Document("Nem Alacak Felek Benim", "1500 KB", "Audio File"), this.root);
/*  64 */     TreeNode documents2 = new DefaultTreeNode(new Document("Nem 2", "1500 KB", "Audio File"), this.root);
/*  65 */     TreeNode documents3 = new DefaultTreeNode(new Document("aUTRE", "1500 KB", "Audio File"), documents);
/*  66 */     new DefaultTreeNode(new Document("a2", "1500 KB", "Audio File"), documents3);
 
/*  68 */     return this.root;
   }
 
   public Document getSelectedDocument() {
/*  72 */     return this.selectedDocument;
   }
 
   public void setSelectedDocument(Document selectedDocument) {
/*  76 */     this.selectedDocument = selectedDocument;
   }
 
   public ObjetNiveau getSelectedNiveau() {
/*  80 */     return this.selectedNiveau;
   }
 
   public void setSelectedNiveau(ObjetNiveau selectedNiveau) {
/*  84 */     this.selectedNiveau = selectedNiveau;
   }
 
   public ObjetNiveau[] getSelectedNiveaux() {
/*  88 */     return this.selectedNiveaux;
   }
 
   public void setSelectedNiveaux(ObjetNiveau[] selectedNiveaux) {
/*  92 */     this.selectedNiveaux = selectedNiveaux;
   }
 
   public void selection() {
/*  96 */     for (TreeNode doc : this.root.getChildren()) {
/*  97 */       Document document = (Document)doc.getData();
/*  98 */       if (document.getSelect().booleanValue())
/*  99 */         System.out.println(" >>>>>>>>>> " + document.getName());
     }
   }
 
   public void afficher()
   {
     LoginBean bean = LoginUtil.getLoginBean();
 
    NiveauDatabaseLayout niveaux = null;
 
     this.listeNiveaux = new ArrayList();
     try
     {
      niveaux = new NiveauDatabaseLayout();
      niveaux.transactionOpen();
 
       niveaux.listerTotal(bean.getIdClient());
 
      Iterator it = niveaux.liste().iterator();
       List listNivTMP = new ArrayList();
       while (it.hasNext())
       {
         Niveau niveauReponse = (Niveau)it.next();
 
         ObjetNiveau objetNiveau = new ObjetNiveau();
        objetNiveau.setIdNiveau(niveauReponse.getIdNiveau());
         objetNiveau.setLibelle(niveauReponse.getLibelle());
         objetNiveau.setCodeBarre(niveauReponse.getCodeBarre());
        objetNiveau.setTri(Integer.valueOf(niveauReponse.getTri()));
         try
         {
           if (niveauReponse.getNiveau() == null)
           {
             objetNiveau.setIdNiveauParent(Integer.valueOf(0));
             objetNiveau.setAriane("");
             objetNiveau.setPosition(Integer.valueOf(0));
 
             listNivTMP.add(0, objetNiveau); 
						//break label375:
           }
 
          List listNivAclasserTMP = new ArrayList();
           Iterator itNivTMP = listNivTMP.iterator();
           while (itNivTMP.hasNext()) {
            ObjetNiveau objetNiveauClasse = (ObjetNiveau)itNivTMP.next();
            
             listNivAclasserTMP.add(objetNiveauClasse);
             if ((niveauReponse.getNiveau()!=null)&& (niveauReponse!=null))
             if (niveauReponse.getNiveau().getIdNiveau() != objetNiveauClasse.getIdNiveau())
               continue;
             objetNiveau.setIdNiveauParent(objetNiveauClasse.getIdNiveau());
             String ariane = objetNiveauClasse.getAriane();
             if (!(ariane.equals("")))
               ariane = ariane + " > ";
             ariane = ariane + objetNiveauClasse.getLibelle();
 
           objetNiveau.setAriane(ariane);
             objetNiveau.setPosition(Integer.valueOf(objetNiveauClasse.getPosition().intValue() + 1));
 
             listNivAclasserTMP.add(objetNiveau);
           }
 
           listNivTMP = listNivAclasserTMP;
 
           label375: objetNiveau.setIdNiveauParent(niveauReponse.getNiveau().getIdNiveau());
         }
         catch (Exception e)
         {
        	 e.printStackTrace();
           e.getMessage();
         }
       }
       this.listeNiveaux = listNivTMP;
      niveaux.transactionCommit();
     } catch (Exception e) {
      System.out.println("Erreur liste code barre");
      e.printStackTrace();
       e.getMessage();
     }
     finally
     {
       niveaux.close();
     }
   }
 
   public String imprimer() {
/* 189 */     String uuid = UUIDUtil.getID();
/* 190 */     ExternalContext exCont = FacesContext.getCurrentInstance().getExternalContext();
/* 191 */     String url = "http://" + exCont.getRequestServerName() + ":" + exCont.getRequestServerPort() + "/InspectPrint/InspectPort";
 
/* 193 */     CodeBarreTmpDatabaseLayout codeBarre = null;
     try
     {
/* 196 */       codeBarre = new CodeBarreTmpDatabaseLayout();
/* 197 */       codeBarre.transactionOpen();
 
/* 200 */       for (int i = 0; i < this.selectedNiveaux.length; ++i) {
/* 201 */         ObjetNiveau obj = this.selectedNiveaux[i];
/* 202 */         codeBarre.model().setIdImpression(uuid);
/* 203 */         codeBarre.model().setDate(new Date());
/* 204 */         codeBarre.model().setCodeBarre(obj.getCodeBarre());
/* 205 */         codeBarre.model().setLibelle(obj.getLibelle());
/* 206 */         codeBarre.model().setAriane(obj.getAriane());
 
/* 208 */         codeBarre.save();
/* 209 */         codeBarre.reloadModel();
       }
 
/* 213 */       codeBarre.transactionCommit();

//save synchro 
TerminalDatabaseLayout termDB= new TerminalDatabaseLayout();
	          termDB.transactionOpen();
	           
	          LoginBean bean = LoginUtil.getLoginBean();
	          
	          
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
     } catch (Exception e) {
/* 215 */       System.out.println("Erreur d'ajout");
/* 216 */       e.getMessage();
 
/* 218 */       return "ajouter_niveau";
     } finally {
/* 220 */       codeBarre.close();
     }
 
     try
     {
/* 227 */       FacesContext.getCurrentInstance().getExternalContext().redirect("inspectbox:" + UUIDUtil.getBase64(new StringBuilder(String.valueOf(url)).append(";").append(uuid).toString()));
     }
     catch (IOException e) {
/* 230 */       e.printStackTrace();
     }
 
/* 234 */     return "inspectbox:http://178.32.102.172:8080/inspectbox/urlwebservice.asmx;21EC2020-3AEA-1069-A2DD-08002B30309D";
   }
 }

