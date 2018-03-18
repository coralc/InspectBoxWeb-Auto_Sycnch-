 package com.inspectbox.web;
 
 import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.inspectbox.databaseLayout.LibelleNiveauDatabaseLayout;
import com.inspectbox.databaseLayout.NiveauDatabaseLayout;
import com.inspectbox.databaseLayout.NiveauObjetDatabaseLayout;
import com.inspectbox.databaseLayout.SynchDatabaseLayout;
import com.inspectbox.databaseLayout.TerminalDatabaseLayout;
import com.inspectbox.databaseLayout.TypeReponseDatabaseLayout;
import com.inspectbox.model.Libelleniveau;
import com.inspectbox.model.Niveau;
import com.inspectbox.model.Niveauobjet;
import com.inspectbox.model.SynchPK;
import com.inspectbox.model.Terminal;
import com.inspectbox.objetLayout.ObjetNavigation;
import com.inspectbox.objetLayout.ObjetNiveauObjet;
import com.inspectbox.utils.HibernateUtil;
import com.inspectbox.utils.I18nUtil;
import com.inspectbox.utils.LoginUtil;
import com.inspectbox.utils.Themed;
 
 @ManagedBean(name="listeObjetsBean")
 @SessionScoped
@ViewScoped
 public class ListeObjetsBean    implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private Integer idObjet;


   private Integer idNiveauObjetAConsulter = null;
  private Integer idAjouter = Integer.valueOf(0);
 
  private Integer nombreNiveauxObjets = null;
  private Integer positionActuelleNiveauObjet = Integer.valueOf(1);
   private List<ObjetNiveauObjet> map;
private List <String> libsh;
public List<String> getLibsh() {
	return libsh;
}
public void setLibsh(List<String> libsh) {
	this.libsh = libsh;
}
   private List<ObjetNavigation> listeObjetsParent;
public List<ObjetNavigation> getListeObjetsParent() {
	return listeObjetsParent;
}
public void setListeObjetsParent(List<ObjetNavigation> listeObjetsParent) {
	this.listeObjetsParent = listeObjetsParent;
}
   private Niveauobjet niveauObjetCoutant;
   private List<Libelleniveau> listeLibelleNiveau;
   private String libelleNiveauCourant;
   private String libelleNiveauCourant2;
public String getLibelleNiveauCourant2() {
	return libelleNiveauCourant2;
}
public void setLibelleNiveauCourant2(String libelleNiveauCourant2) {
	this.libelleNiveauCourant2 = libelleNiveauCourant2;
}
   private Integer triMax = Integer.valueOf(0);
 
   public ListeObjetsBean()
   {
     this.map = new ArrayList();
this.libsh=new ArrayList();
     this.listeObjetsParent = new ArrayList();
     this.niveauObjetCoutant = new Niveauobjet();
     this.listeLibelleNiveau = new ArrayList();
   }
 
   public Integer getIdNiveauObjetAConsulter()
   {
    return this.idNiveauObjetAConsulter; }
 
   public void setIdNiveauObjetAConsulter(Integer idNiveauObjetAConsulter) {
     this.idNiveauObjetAConsulter = idNiveauObjetAConsulter;
   }
 
   public Integer getIdAjouter() {
    return this.idAjouter;
   }
 
   public void setIdAjouter(Integer idAjouter) {
     this.idAjouter = idAjouter;
   }
 
   public Integer getNombreNiveauxObjets()
   {
     return this.nombreNiveauxObjets;
   }
 
   public void setNombreNiveauxObjets(Integer nombreNiveauxObjets)
   {
     this.nombreNiveauxObjets = nombreNiveauxObjets;
   }
 
   public Integer getPositionActuelleNiveauObjet()
   {
    return this.positionActuelleNiveauObjet;
   }
 
   public void setPositionActuelleNiveauObjet(Integer positionActuelleNiveauObjet)
   {
     this.positionActuelleNiveauObjet = positionActuelleNiveauObjet;
   }
 
   public String getLibelleNiveauCourant()
   {
    return this.libelleNiveauCourant;
   }
 
   public void setLibelleNiveauCourant(String libelleNiveauCourant)
   {
     this.libelleNiveauCourant = libelleNiveauCourant;
   }
 
   public Integer getTriMax()
   {
     return this.triMax;
   }
 
   public void setTriMax(Integer triMax)
   {
     this.triMax = triMax;
   }
 
   public Integer getIdObjet()
   {
     return this.idObjet;
   }
 
   public void setIdObjet(Integer idObjet)
   {
     this.idObjet = idObjet;
   }
 
   public List<ObjetNiveauObjet> getLister()
   {
    return this.map;
   }
 
public void affichelib()
{
	LoginBean bean = LoginUtil.getLoginBean();
	LibelleNiveauDatabaseLayout libelleNiveau = new LibelleNiveauDatabaseLayout();
    libelleNiveau.transactionOpen();
    libelleNiveau.lister2(bean.getIdClient(),2, 2);
    libelleNiveauCourant=libelleNiveau.model().getLibelle();
    libelleNiveau.lister2(bean.getIdClient(),2, 1);
	 libelleNiveauCourant2=libelleNiveau.model().getLibelle();
	 libelleNiveau.close();
	 NiveauObjetDatabaseLayout niveauObjet = null;
 	
    
          niveauObjet = new NiveauObjetDatabaseLayout();
          niveauObjet.transactionOpen();
          themes.clear();
     	 niveauObjet.listerAll(bean.getIdClient());
          Iterator it = niveauObjet.liste().iterator();
          while (it.hasNext())
           {
         	 Niveauobjet objet = (Niveauobjet)it.next();
         	 themes.add(new Themed(objet.getIdNiveauObjet(), objet.getLibelle(), objet.getLibelle().toLowerCase())); 
           }
          niveauObjet.close();
          
}
public void afficher ()
{ 
	LoginBean bean = LoginUtil.getLoginBean();
	this.map.clear();
	
	    
	NiveauObjetDatabaseLayout niveauObjet = null;
	NiveauDatabaseLayout niveau = null;
	TypeReponseDatabaseLayout choix = null;
    try
    {
         niveauObjet = new NiveauObjetDatabaseLayout();
         niveauObjet.transactionOpen();
         
    	 niveau=new NiveauDatabaseLayout();
         niveau.transactionOpen();
         
		 choix=new TypeReponseDatabaseLayout();
		 choix.transactionOpen();
         
    	
    	 if ((this.objetchoisi!=null)&& ( ! this.objetchoisi.equals(" ")))
    	 {
    		 
    		 niveauObjet.listerByLibelle(bean.getIdClient(),this.objetchoisi);
             Iterator it = niveauObjet.liste().iterator();
             
             while (it.hasNext())
              {
            	 Niveauobjet objet = (Niveauobjet)it.next();
            	  ObjetNiveauObjet objetNiv = new ObjetNiveauObjet();
            	 objetNiv.setIdNiveauObjet(objet.getIdNiveauObjet());
            	 objetNiv.setLibelle(objet.getLibelle());
            	 objetNiv.setExist(objet.isExiste());
            	 if (objet.getUnitmeasure()!=null)
            	 {
            	 objetNiv.setUnitmeasure(objet.getUnitmeasure());  objetNiv.setIslist(false);
            	 }
            	 else
            	 {
            		 
            		 choix.afficher(bean.getIdClient(), objet.getTypereponse().getIdTypeReponse());
            		 objetNiv.setUnitmeasure(choix.model().getLibelle());
            		 
            		 objetNiv.setIslist(true);
            	 }
            	 if(objet.getLowlimit()!=null)
            	 objetNiv.setReponseminvalue(objet.getLowlimit());
            	 if(objet.getHighlimit()!=null)
                	 objetNiv.setReponsemaxvalue(objet.getHighlimit());
            	 if(objet.getLowborder()!=null)
            	 objetNiv.setLowborder(objet.getLowborder());
            	 
            	 if(objet.getHighborder()!=null)
                	 objetNiv.setHighborder(objet.getHighborder());
                	
            	
            	 //chercher le niveau lie a cet objet
            	 if (objet.isExiste())
            	 {
            		
                	 niveau.afficher2(bean.getIdClient(), objet.getIdNiveauObjet());
                	 if (niveau!=null)
                	 objetNiv.setLibelleNiveau(niveau.model().getLibelle());
                	 
            	 }
            	 
            		 
            	 this.map.add(objetNiv);
              }
            
    	 }
    	 }
    	 catch (Exception e) {
  	       e.printStackTrace();
  	      }
finally{
	
	
	niveauObjet.close();
	niveau.close(); 
	choix.close();
	
} 
    }
    public void reset()
    {
    	this.map.clear();
    	this.objetchoisi=null;
    	affichelib();
    }
    public void show()
    {
    	affichelib();
    	if (this.objetchoisi==null)
    	{
    		afficherall();
    		
    	}
    	else
    		afficher();
    }
    public void afficherall()
    {
    	
    	
    	LoginBean bean = LoginUtil.getLoginBean();
    	this.map.clear();
    	
    	    
    	NiveauObjetDatabaseLayout niveauObjet = null;
    	NiveauDatabaseLayout niveau = null;
    	TypeReponseDatabaseLayout choix = null;
        try
        {
             niveauObjet = new NiveauObjetDatabaseLayout();
             niveauObjet.transactionOpen();
             
        	 niveau=new NiveauDatabaseLayout();
             niveau.transactionOpen();
             
    		 choix=new TypeReponseDatabaseLayout();
    		 choix.transactionOpen();
    		
    		 niveauObjet.listerAll(bean.getIdClient());
             Iterator it = niveauObjet.liste().iterator();
             while (it.hasNext())
              {
            	 Niveauobjet objet = (Niveauobjet)it.next();
            	 //themes.add(new Themed(objet.getIdNiveauObjet(), objet.getLibelle(), objet.getLibelle().toLowerCase())); 
            	 ObjetNiveauObjet objetNiv = new ObjetNiveauObjet();
            	 objetNiv.setIdNiveauObjet(objet.getIdNiveauObjet());
            	 objetNiv.setLibelle(objet.getLibelle());
            	 objetNiv.setExist(objet.isExiste());
            	 if (objet.getTypereponse().getMode()==1)
            	 { objetNiv.setUnitmeasure(objet.getUnitmeasure());  objetNiv.setIslist(false);
            	 }
            	 else
            	 {
            		 choix.afficher(bean.getIdClient(), objet.getTypereponse().getIdTypeReponse());
            		 objetNiv.setUnitmeasure(choix.model().getLibelle());
            		 
            		 objetNiv.setIslist(true);
            	 }
            	 if(objet.getLowlimit()!=null)
            	 objetNiv.setReponseminvalue(objet.getLowlimit());
            	 if(objet.getHighlimit()!=null)
                	 objetNiv.setReponsemaxvalue(objet.getHighlimit());
            	 if(objet.getLowborder()!=null)
            	 objetNiv.setLowborder(objet.getLowborder());
            	 
            	 if(objet.getHighborder()!=null)
                	 objetNiv.setHighborder(objet.getHighborder());
                	
            	
            	 //chercher le niveau lie a cet objet
            	 if (objet.isExiste())
            	 {
            		
                	 niveau.afficher2(bean.getIdClient(), objet.getIdNiveauObjet());
                	 if (niveau!=null)
                	 objetNiv.setLibelleNiveau(niveau.model().getLibelle());
                	 
            	 }
            	 else
            		 objetNiv.setLibelleNiveau("-"); 
            	 this.map.add(objetNiv);
            	
              }
    	 
         
    }
         catch (Exception e) {
        	       e.printStackTrace();
        	      }
    finally{
    	
    	
    	niveauObjet.close();
    	niveau.close(); 
    	choix.close();
    	
    }
}

public List<Themed> getFilteredThemes() {
	return filteredThemes;
}
public void setFilteredThemes(List<Themed> filteredThemes) {
	this.filteredThemes = filteredThemes;
}
public List<Themed> filteredThemes = new ArrayList<Themed>();
public List<Themed> themes =new ArrayList<Themed>();


public List<Themed> getThemes() {
	return themes;
}
public void setThemes(List<Themed> themes) {
	this.themes = themes;
}
public List<Themed> completeTheme(String query) {
	filteredThemes.clear();
 for(Themed item : themes){
    	 if(item.getName().toLowerCase().startsWith(query)) {
             filteredThemes.add(item);
         }
     }
	  
    return filteredThemes;
}
public String objetchoisi;
   public String getObjetchoisi() {
	return objetchoisi;
}
public void setObjetchoisi(String objetchoisi) {
	this.objetchoisi = objetchoisi;
}
public void lister()
   {
	     
/* 170 */     LoginBean bean = LoginUtil.getLoginBean();
 
/* 172 */     this.map.clear();
/* 173 */    // this.listeObjetsParent.clear();
/* 174 */     this.nombreNiveauxObjets = null;
/* 175 */     this.positionActuelleNiveauObjet = Integer.valueOf(1);
     try
     {
/* 182 */       Session session = HibernateUtil.currentSession();
/* 183 */       Transaction tr = session.beginTransaction();
 
/* 185 */       Iterator it = session.createQuery("from Niveauobjet as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client and no.client.masque = :masque order by no.tri")
/* 187 */         .setParameter("masque", Boolean.valueOf(false))
/* 188 */         .setParameter("client", bean.getIdClient())
/* 189 */         .list().iterator();
 
/* 193 */       while (it.hasNext())
       {
/* 195 */         Niveauobjet objet = (Niveauobjet)it.next();
 
/* 198 */         if (this.idNiveauObjetAConsulter == null)
         {
/* 200 */           if (objet.getNiveauobjet() != null)
             continue;
/* 202 */           ObjetNiveauObjet objetNiv = new ObjetNiveauObjet();
/* 203 */           objetNiv.setIdNiveauObjet(objet.getIdNiveauObjet());
/* 204 */           objetNiv.setLibelle(objet.getLibelle());
/* 205 */           objetNiv.setTri(Integer.valueOf(objet.getTri()));
/* 206 */           if (this.map.isEmpty())
/* 207 */             objetNiv.setPremier(Boolean.valueOf(true));
/* 208 */           this.map.add(objetNiv);
/* 209 */           this.triMax = Integer.valueOf(objet.getTri());
         }
         else
         {
/* 214 */           if ((objet.getNiveauobjet() != null) && 
/* 216 */             (objet.getNiveauobjet().getIdNiveauObjet().equals(this.idNiveauObjetAConsulter)))
           {
/* 218 */             ObjetNiveauObjet objetNiv = new ObjetNiveauObjet();
/* 219 */             objetNiv.setIdNiveauObjet(objet.getIdNiveauObjet());
/* 220 */             objetNiv.setLibelle(objet.getLibelle());
/* 221 */             objetNiv.setTri(Integer.valueOf(objet.getTri()));
/* 222 */             if (this.map.isEmpty())
/* 223 */               objetNiv.setPremier(Boolean.valueOf(true));
/* 224 */             this.map.add(objetNiv);
/* 225 */             this.triMax = Integer.valueOf(objet.getTri());
           }
 
/* 230 */           if (!(objet.getIdNiveauObjet().equals(this.idNiveauObjetAConsulter)))
             continue;
/* 232 */           this.niveauObjetCoutant = objet;
/* 233 */           this.nombreNiveauxObjets = Integer.valueOf(this.niveauObjetCoutant.getClient().getNombreNiveauxObjets());
         }
 
       }
 
/* 239 */       if (!(this.map.isEmpty())) {
/* 240 */         ((ObjetNiveauObjet)this.map.get(this.map.size() - 1)).setDernier(Boolean.valueOf(true));
       }
 
/* 243 */       _parcourtArbre(this.niveauObjetCoutant);
/* 244 */       tr.commit();
     }
     catch (Exception e) {
/* 247 */       e.getMessage();
     }
     finally
     {
/* 251 */       HibernateUtil.closeSession();
     }
 
/* 256 */     List sortList = new ArrayList();
/* 257 */     while (!(this.listeObjetsParent.isEmpty()))
     {
/* 259 */       sortList.add((ObjetNavigation)this.listeObjetsParent.get(this.listeObjetsParent.size() - 1));
/* 260 */       this.listeObjetsParent.remove(this.listeObjetsParent.size() - 1);
     }
/* 262 */     this.listeObjetsParent.addAll(sortList);
 
/* 265 */     if (this.idNiveauObjetAConsulter != null)
     {
/* 267 */       this.positionActuelleNiveauObjet = Integer.valueOf(this.listeObjetsParent.size() + 2);
     }
 
/* 271 */     getAriane();
 
/* 274 */     LibelleNiveauDatabaseLayout libelleNiveau = null;
     try {
/* 276 */       libelleNiveau = new LibelleNiveauDatabaseLayout();
/* 277 */       libelleNiveau.transactionOpen();
 
/* 279 */       libelleNiveau.choisirTypeObjet();
/* 280 */       libelleNiveau.lister(bean.getIdClient());
 
/* 282 */       ArrayList list = (ArrayList)libelleNiveau.liste();
 
/* 285 */       for (Integer i = Integer.valueOf(0); i.intValue() < list.size(); i = Integer.valueOf(i.intValue() + 1))
       {
/* 288 */         if ((this.positionActuelleNiveauObjet == null) || (this.positionActuelleNiveauObjet.intValue() == 0))
         {
/* 290 */           this.libelleNiveauCourant = ((Libelleniveau)list.get(0)).getLibelle();
         }
         else
         {
/* 294 */           this.libelleNiveauCourant = ((Libelleniveau)list.get(this.positionActuelleNiveauObjet.intValue() - 1)).getLibelle();
         }
       }
 
/* 298 */       libelleNiveau.transactionCommit();
     } catch (Exception e) {
/* 300 */       System.out.println("Erreur");
/* 301 */       e.getMessage();
     } finally {
/* 303 */       libelleNiveau.close();
     }
   }
 
   public String monter(Integer num)
   {
/* 309 */     return deplacer(num, "haut");
   }
 
   public String descendre(Integer num) {
/* 313 */     return deplacer(num, "bas");
   }
 
   public String deplacer(Integer num, String sens) {
/* 317 */     lister();
 
/* 319 */     for (Integer i = Integer.valueOf(0); i.intValue() < this.map.size(); i = Integer.valueOf(i.intValue() + 1))
     {
/* 321 */       ObjetNiveauObjet obj = (ObjetNiveauObjet)this.map.get(i.intValue());
 
/* 323 */       if (obj.getIdNiveauObjet() != num.intValue())
         continue;
/* 325 */       Integer tri = obj.getTri();
/* 326 */       Integer num2 = null;
/* 327 */       Integer tri2 = null;
 
/* 329 */       if ((sens.equals("haut")) && (!(obj.getPremier().booleanValue())))
       {
/* 331 */         num2 = ((ObjetNiveauObjet)this.map.get(i.intValue() - 1)).getIdNiveauObjet();
/* 332 */         tri2 = ((ObjetNiveauObjet)this.map.get(i.intValue() - 1)).getTri();
       }
/* 334 */       else if ((sens.equals("bas")) && (!(obj.getDernier().booleanValue())))
       {
/* 336 */         num2 = ((ObjetNiveauObjet)this.map.get(i.intValue() + 1)).getIdNiveauObjet();
/* 337 */         tri2 = ((ObjetNiveauObjet)this.map.get(i.intValue() + 1)).getTri();
       }
 
/* 340 */       LoginBean bean = LoginUtil.getLoginBean();
/* 341 */       NiveauObjetDatabaseLayout niveauObjet = null;
       try
       {
/* 344 */         niveauObjet = new NiveauObjetDatabaseLayout();
/* 345 */         niveauObjet.transactionOpen();
 
/* 347 */         if (niveauObjet.verifierDroitModifier(bean.getIdClient(), num).booleanValue())
         {
           niveauObjet.model().setTri(tri2.intValue());
         //  niveauObjet.model().setClefTimestamp(new Date());
 
           niveauObjet.update();
         }
 
        if (niveauObjet.verifierDroitModifier(bean.getIdClient(), num2).booleanValue())
         {
           niveauObjet.model().setTri(tri.intValue());
         //  niveauObjet.model().setClefTimestamp(new Date());
 
          niveauObjet.update();
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
       catch (Exception e) {
        System.out.println("Erreur de tri");
        e.printStackTrace();
       } finally {
        niveauObjet.close();
       }
 
     }
 
/* 374 */     return "liste_objet?faces-redirect=true&id=" + this.idObjet;
   }
 
   public String consulter(Integer num)
   {
/* 379 */     this.idAjouter = num;
/* 380 */     if (num.intValue() == 0)
/* 381 */       num = null;
/* 382 */     this.idNiveauObjetAConsulter = num;
 
/* 385 */     this.map.clear();
/* 386 */     this.listeObjetsParent.clear();
 
/* 390 */     return "liste_objet?faces-redirect=true";
   }
 
   public Boolean isNiveauMax()
   {
/* 396 */     if (this.positionActuelleNiveauObjet == this.nombreNiveauxObjets) return Boolean.valueOf(true); return Boolean.valueOf(false);
   }
 
   public Boolean isNotNiveauMax()
   {
/* 401 */     return Boolean.valueOf(!(isNiveauMax().booleanValue()));
   }
 
   public List<ObjetNavigation> getAriane()
   {
/* 407 */     List liste = new ArrayList();
/* 408 */    // liste.add(new ObjetNavigation(I18nUtil.get("navigation_parametrage_objets"), Integer.valueOf(0)));
 
/* 411 */     if (this.idNiveauObjetAConsulter != null)
     {
	
/* 413 */       liste.addAll(this.listeObjetsParent);
/* 414 */       liste.add(new ObjetNavigation(this.niveauObjetCoutant.getLibelle(), this.idNiveauObjetAConsulter));
     }
/* 416 */     return liste;
   }
 
   private void _parcourtArbre(Niveauobjet objet)
   {
/* 423 */     if (objet.getNiveauobjet() != null)
     {
/* 425 */       System.out.println("Parent: " + objet.getNiveauobjet().getLibelle() + " > ");
/* 426 */       this.listeObjetsParent.add(new ObjetNavigation(objet.getNiveauobjet().getLibelle(), objet.getNiveauobjet().getIdNiveauObjet()));
/* 427 */       _parcourtArbre(objet.getNiveauobjet());
     }
     else
     {
/* 431 */       System.out.println("Parent: " + objet.getNiveauobjet().getLibelle() + " > ");
/* 432 */       this.listeObjetsParent.add(new ObjetNavigation(objet.getNiveauobjet().getLibelle(), objet.getNiveauobjet().getIdNiveauObjet()));
     }
   }
   
   public String supprimerv2(ObjetNiveauObjet objet )
   {
	   LoginBean bean = LoginUtil.getLoginBean();
	      NiveauObjetDatabaseLayout niveauObjet = null;
	      NiveauDatabaseLayout niveau = null;
	        try
	        {
	          niveauObjet = new NiveauObjetDatabaseLayout();
	          niveauObjet.transactionOpen();
	          niveauObjet.afficher(bean.getIdClient(), objet.getIdNiveauObjet());
	          niveauObjet.model().setMasque(true);
	          niveauObjet.update();
	          niveauObjet.transactionCommit();
	          
	          //supprimer le lien des niveaux
	          niveau = new NiveauDatabaseLayout();
	          niveau.transactionOpen();
	          niveau.afficher2(bean.getIdClient(),objet.getIdNiveauObjet() );
	          if (niveau!=null)
	          {
	        	  niveau.setNiveauObjet(null);
	        	  niveau.update();
	        	  niveau.transactionCommit();
	          }
	          

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
	        }catch (Exception e) {
		          System.out.println("Erreur de suppression");
		         e.printStackTrace();
		    
		          
		        } finally {
		          niveauObjet.close();
		          niveau.close();
		        }
	        
	   return "liste_objet?faces-redirect=true";
   }
   
   public String supprimer(ObjetNiveauObjet objet )
     {
	   
	  
	   
	        LoginBean bean = LoginUtil.getLoginBean();
	      NiveauObjetDatabaseLayout niveauObjet = null;
	        try
	        {
	          niveauObjet = new NiveauObjetDatabaseLayout();
	          niveauObjet.transactionOpen();
	    
	          if (niveauObjet.verifierDroitModifier(bean.getIdClient(), objet.getIdNiveauObjet()).booleanValue())
	          {
	            niveauObjet.model().setMasque(true);
	          // niveauObjet.model().setClefTimestamp(new Date());
	    
	          niveauObjet.update();
	          }
	    
	         List listeMasque = new ArrayList();
	          listeMasque.add(objet.getIdNiveauObjet());
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
	         //  niveauObjet.model().setClefTimestamp(new Date());
	    
	           niveauObjet.update();
	    
	            listeMasque.add(niv.getIdNiveauObjet());
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
	        catch (Exception e) {
	          System.out.println("Erreur de suppression");
	          e.printStackTrace();
	          return "liste_objet?faces-redirect=true";
	        } finally {
	          niveauObjet.close();
	        }
	    
	        return "liste_objet?faces-redirect=true";
	      }
	    
   private List<ObjetNiveauObjet> listObjet = new ArrayList(); 
   
   
   
public List<ObjetNiveauObjet> getListObjet() {
	return listObjet;
}
public void setListObjet(List<ObjetNiveauObjet> listObjet) {
	this.listObjet = listObjet;
}


public void aff()
   {
	
	//this.listObjet.clear();
	   LoginBean bean = LoginUtil.getLoginBean();
	   NiveauObjetDatabaseLayout niveauObjet = null;
	   niveauObjet = new NiveauObjetDatabaseLayout();
       niveauObjet.transactionOpen();
       try
       {
    	   niveauObjet.listerAll(bean.getIdClient());
    	   Iterator it = niveauObjet.liste().iterator();
	          while (it.hasNext())
	          {
	            Niveauobjet niv = (Niveauobjet)it.next();
	            System.out.println("Erreur de aff"+niv.getIdNiveauObjet());
	            ObjetNiveauObjet obj=new ObjetNiveauObjet();
	            obj.setIdNiveauObjet(niv.getIdNiveauObjet());
	            obj.setLibelle(niv.getLibelle());
	            this.listObjet.add(obj);
	          }
       }
       catch (Exception e) {
	          System.out.println("Erreur de aff");
	          e.printStackTrace();
	    
	          
	        } finally {
	          niveauObjet.close();
	        }
   }
   

public String dissocier(ObjetNiveauObjet objet )
{
	   LoginBean bean = LoginUtil.getLoginBean();
	      NiveauObjetDatabaseLayout niveauObjet = null;
	      NiveauDatabaseLayout niveau = null;
	        try
	        {
	          niveauObjet = new NiveauObjetDatabaseLayout();
	          niveauObjet.transactionOpen();
	          niveauObjet.afficher(bean.getIdClient(), objet.getIdNiveauObjet());
	          niveauObjet.model().setExiste(false);
	          niveauObjet.update();
	          niveauObjet.transactionCommit();
	          
	          //supprimer le lien des niveaux
	          niveau = new NiveauDatabaseLayout();
	          niveau.transactionOpen();
	          niveau.afficher2(bean.getIdClient(),objet.getIdNiveauObjet() );
	          if (niveau!=null)
	          {
	        	  niveau.setNiveauObjet(null);
	        	  niveau.update();
	        	  niveau.transactionCommit();
	          }
	          

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
	        }catch (Exception e) {
		          System.out.println("Erreur de suppression");
		         e.printStackTrace();
		    
		          
		        } finally {
		          niveauObjet.close();
		          niveau.close();
		        }
	        
	   return "liste_objet?faces-redirect=true";
}
private Integer siteChoisi;
private Integer batChoisi;
private Integer tagChoisi;
private ArrayList<SelectItem> niveauList = new ArrayList();
private ArrayList<SelectItem> batList = new ArrayList();
private ArrayList<SelectItem> tagList = new ArrayList();
public Integer getTagChoisi() {
	return tagChoisi;
}
public void setTagChoisi(Integer tagChoisi) {
	this.tagChoisi = tagChoisi;
}
public ArrayList<SelectItem> getTagList() {
	return tagList;
}
public void setTagList(ArrayList<SelectItem> tagList) {
	this.tagList = tagList;
}
public Integer getBatChoisi() {
	return batChoisi;
}
public void setBatChoisi(Integer batChoisi) {
	this.batChoisi = batChoisi;
}
public ArrayList<SelectItem> getBatList() {
	return batList;
}
public void setBatList(ArrayList<SelectItem> batList) {
	this.batList = batList;
}
public Integer getSiteChoisi() {
	return siteChoisi;
}
public void setSiteChoisi(Integer siteChoisi) {
	this.siteChoisi = siteChoisi;
}
public ArrayList<SelectItem> getNiveauList() {
	return niveauList;
}
public void setNiveauList(ArrayList<SelectItem> niveauList) {
	this.niveauList = niveauList;
}
private Integer obChoisi;
public Integer getObChoisi() {
	return obChoisi;
}
public void setObChoisi(Integer obChoisi) {
	this.obChoisi = obChoisi;
}
public void affichertag(ObjetNiveauObjet objet)
{
	this.niveauList.clear();
	this.siteChoisi=0;
	this.batChoisi=0;
	this.obChoisi=0;
	LoginBean bean = LoginUtil.getLoginBean();
	NiveauDatabaseLayout niveau = new NiveauDatabaseLayout();
	niveau.transactionOpen();
	try
	{
	this.obChoisi=objet.getIdNiveauObjet();
	 Object listeNiveaux = new ArrayList();
     niveau.listerInitial(bean.getIdClient());

      listeNiveaux = niveau.liste();
      Iterator itNiveau = ((List)listeNiveaux).iterator(); 
      this.niveauList.add(new SelectItem(Integer.valueOf(0), I18nUtil.get("form_rechercher_liste_niveau_all")));

      while (itNiveau.hasNext())
      {
       Niveau niv = (Niveau)itNiveau.next();
        this.niveauList.add(new SelectItem(niv.getIdNiveau(), niv.getLibelle()));
      }
	}
	catch (Exception e) {
   	 e.printStackTrace();
    } finally {
      niveau.close();
      
    }
}
public void siteChanged(AjaxBehaviorEvent event) {
	this.batList.clear();
	
	LoginBean bean = LoginUtil.getLoginBean();
	NiveauDatabaseLayout niveau = new NiveauDatabaseLayout();
	niveau.transactionOpen();
	try
	{
	
	 Object listeNiveaux = new ArrayList();
     niveau.listerChild(bean.getIdClient(),this.siteChoisi);
      listeNiveaux = niveau.liste();
      Iterator itNiveau = ((List)listeNiveaux).iterator(); 
      this.batList.add(new SelectItem(Integer.valueOf(0), I18nUtil.get("form_rechercher_liste_niveau_libelle1_all")));

      while (itNiveau.hasNext())
      {
       Niveau niv = (Niveau)itNiveau.next();
        this.batList.add(new SelectItem(niv.getIdNiveau(), niv.getLibelle()));
      }
	}
	catch (Exception e) {
   	 e.printStackTrace();
    } finally {
      niveau.close();
      
    }
}
public void batChanged(AjaxBehaviorEvent event) {
	this.tagList.clear();
	LoginBean bean = LoginUtil.getLoginBean();
	NiveauDatabaseLayout niveau = new NiveauDatabaseLayout();
	niveau.transactionOpen();
	try
	{
	
	 Object listeNiveaux = new ArrayList();
     niveau.listerChild(bean.getIdClient(),this.batChoisi);
      listeNiveaux = niveau.liste();
      Iterator itNiveau = ((List)listeNiveaux).iterator(); 
      this.tagList.add(new SelectItem(Integer.valueOf(0), I18nUtil.get("form_rechercher_liste_niveau_libelle2_all")));

      while (itNiveau.hasNext())
      {
       Niveau niv = (Niveau)itNiveau.next();
        this.tagList.add(new SelectItem(niv.getIdNiveau(), niv.getLibelle()));
      }
	}
	catch (Exception e) {
   	 e.printStackTrace();
    } finally {
      niveau.close();
      
    }
}
public String associer( )
{
	    if ((this.tagChoisi!=0)&&(this.obChoisi!=0))
	   {
		   LoginBean bean = LoginUtil.getLoginBean();
		   NiveauDatabaseLayout niveau = new NiveauDatabaseLayout();
			niveau.transactionOpen();
			NiveauObjetDatabaseLayout objet = new NiveauObjetDatabaseLayout();
			objet.transactionOpen();
			try
			{
			
			
				niveau.afficherbyId(bean.getIdClient(), this.tagChoisi);
				objet.afficherbyid(bean.getIdClient(), this.obChoisi);
				//
				
				// voir si le tag a deja un objet
				if (niveau.model().getNiveauobjet()!=null)
				{
					if (niveau.model().getNiveauobjet().getIdNiveauObjet().equals(this.obChoisi))
					{
						System.out.println("sortir ici");
						return "liste_objet?faces-redirect=true";
					}
					NiveauObjetDatabaseLayout objetoff = new NiveauObjetDatabaseLayout();
					objetoff.transactionOpen();
					objetoff.afficherbyid(bean.getIdClient(), niveau.model().getNiveauobjet().getIdNiveauObjet());
					if ((objetoff.model()!=null))
					{
						objetoff.model().setExiste(false);
						objetoff.save();
						objetoff.transactionCommit();
						objetoff.close();
					}
				}
				//voir si l objet a un tag
				 NiveauDatabaseLayout niveauoff = new NiveauDatabaseLayout();
				 niveauoff.transactionOpen();
				 niveauoff.afficherbyIdObjet(bean.getIdClient(), this.obChoisi);
				if ((niveauoff.model()!=null)&& (niveauoff.model().getIdNiveau()!=this.tagChoisi))
				{
					niveauoff.setNiveauObjet(null);
					niveauoff.save();
					niveauoff.transactionCommit();
					niveauoff.close();
				}
				//inscrire le nouveau objet
				
				niveau.setNiveauObjet(this.obChoisi);
				niveau.save();
				niveau.transactionCommit();
				
				//mettre a jour l objet
				objet.model().setExiste(true);
				objet.save();
				objet.transactionCommit();
				

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
		   	 e.printStackTrace();
		    } finally {
		      niveau.close();
		      objet.close();
		    }
	   }
	   
	   return "liste_objet?faces-redirect=true";
}
 }

