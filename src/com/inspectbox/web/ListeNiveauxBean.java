package com.inspectbox.web;
 
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.event.RowEditEvent;

import com.inspectbox.databaseLayout.LibelleNiveauDatabaseLayout;
import com.inspectbox.databaseLayout.NiveauDatabaseLayout;
import com.inspectbox.databaseLayout.NiveauObjetDatabaseLayout;
import com.inspectbox.databaseLayout.SynchDatabaseLayout;
import com.inspectbox.databaseLayout.TerminalDatabaseLayout;
import com.inspectbox.model.Libelleniveau;
import com.inspectbox.model.Niveau;
import com.inspectbox.model.Niveauobjet;
import com.inspectbox.model.SynchPK;
import com.inspectbox.model.Terminal;
import com.inspectbox.objetLayout.ObjetNavigation;
import com.inspectbox.objetLayout.ObjetNiveau;
import com.inspectbox.objetLayout.ObjetNiveauObjet;
import com.inspectbox.objetLayout.ObjetVue;
import com.inspectbox.utils.HibernateUtil;
import com.inspectbox.utils.I18nUtil;
import com.inspectbox.utils.LoginUtil;
 
 @ManagedBean(name="listeNiveauxBean")
 @SessionScoped
 public class ListeNiveauxBean
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private Integer idNiveau;
      private Integer idNiveauAConsulter = null;
      private Integer idAjouter = Integer.valueOf(0);
 private Integer idtyprep;
      public Integer getIdtyprep() {
	return idtyprep;
}

public void setIdtyprep(Integer idtyprep) {
	this.idtyprep = idtyprep;
}
private String link;
	public String getLink() {
	return link;
}

public void setLink(String link) {
	this.link = link;
}
	private Integer nombreNiveaux = null;
 
      private Integer positionActuelleNiveau = Integer.valueOf(1);
      private Integer idNiveauObjet;
      public Integer getIdNiveauObjet() {
		return idNiveauObjet;
	}

	public void setIdNiveauObjet(Integer idNiveauObjet) {
		this.idNiveauObjet = idNiveauObjet;
	}

	private Boolean isObjet = false;
	private Boolean isLast = false;
   public Boolean getIsLast() {
		return isLast;
	}

	public void setIsLast(Boolean isLast) {
		this.isLast = isLast;
	}

public Boolean getIsObjet() {
		return isObjet;
	}

	public void setIsObjet(Boolean isObjet) {
		this.isObjet = isObjet;
	}

private List<ObjetNiveau> map;

   private List<ObjetNavigation> listeNiveauxParent;
   public List<ObjetNavigation> getListeNiveauxParent() {
	return listeNiveauxParent;
}

public void setListeNiveauxParent(List<ObjetNavigation> listeNiveauxParent) {
	this.listeNiveauxParent = listeNiveauxParent;
}

private Niveau niveauNiveauCoutant;
   private String libelleNiveauCourant;
      private Integer triMax = Integer.valueOf(0);
 
   public ListeNiveauxBean()
   {
        this.map = new ArrayList();
       
        this.listeNiveauxParent = new ArrayList();
        this.niveauNiveauCoutant = new Niveau();
   }
 
   public Integer getIdNiveauAConsulter()
   {
        return this.idNiveauAConsulter; }
 
   public void setIdNiveauAConsulter(Integer idNiveauAConsulter) {
        this.idNiveauAConsulter = idNiveauAConsulter;
   }
 
   public Integer getIdAjouter() {
        return this.idAjouter;
   }
 
   public void setIdAjouter(Integer idAjouter) {
        this.idAjouter = idAjouter;
   }
 
   public Integer getNombreNiveauxObjets()
   {
        return this.nombreNiveaux;
   }
 
   public void setNombreNiveauxObjets(Integer nombreNiveauxObjets)
   {
        this.nombreNiveaux = nombreNiveauxObjets;
   }
 
   public Integer getPositionActuelleNiveau()
   {
        return this.positionActuelleNiveau;
   }
 
   public void setPositionActuelleNiveau(Integer positionActuelleNiveau)
   {
       this.positionActuelleNiveau = positionActuelleNiveau;
   }
 
   public String getLibelleNiveauCourant() {
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
 
   public List<ObjetNiveau> getLister()
   {
       return this.map;
   }
 
   public Integer getIdNiveau() {
       return this.idNiveau;
   }
 
   public void setIdNiveau(Integer idNiveau)
   {
       this.idNiveau = idNiveau;
   }
 public Integer num=Integer.valueOf(0);
 
   public Integer getNum() {
	return num;
}

public void setNum(Integer num) {
	this.num = num;
}

public void afficher ()
   {
	   LoginBean bean = LoginUtil.getLoginBean();
	   this.map.clear();
	   this.isObjet=false;
	   this.isLast=false;
	   this.link="";
	   NiveauDatabaseLayout niveau = null;
  	   niveau = new NiveauDatabaseLayout();
  	 niveau.transactionOpen();
  	 NiveauDatabaseLayout niveau2 = null;
	   niveau2 = new NiveauDatabaseLayout();
	 niveau2.transactionOpen();
	 LibelleNiveauDatabaseLayout libelleNiveau = new LibelleNiveauDatabaseLayout();
     libelleNiveau.transactionOpen();
	 try 
	 {
		  if ((this.idNiveauObjet!=null) && (this.idNiveauObjet!=0) && num<3 )
		  {
			  this.idNiveauObjet=0;
		  }
		  
		 
	 if (this.idNiveau == 0) //site
	   {num=1;
	   this.idNiveauObjet=0;
	   Integer siz=0;
		    niveau.listerSite(bean.getIdClient());
		    siz=niveau.liste().size();
		    
		   Iterator it = niveau.liste().iterator();
           while (it.hasNext())
            {
        	   Niveau nv = (Niveau)it.next();
        	   ObjetNiveau niv = new ObjetNiveau();
               niv.setIdNiveau(nv.getIdNiveau());
               niv.setLibelle(nv.getLibelle());
               niv.setLevel(nv.getLevel());
                if (nv.getTri()==1) niv.setPremier(true); else niv.setPremier(false);
               if (nv.getTri()==siz) niv.setDernier(true); else niv.setDernier(false);
               this.map.add(niv);
        	   
            }
           libelleNiveau.lister2(bean.getIdClient(), 1, 1);
           this.libelleNiveauCourant=libelleNiveau.model().getLibelle();
		   
	   }
	   else if (this.idNiveauObjet==0)
		   
		   {
			   
			niveau.afficherbyId(bean.getIdClient(), this.idNiveau);  
			if (niveau.model().getParentId()!=null)
			{
				this.isLast=true; num=3;
			}
			else
			{
				this.isLast=false; num=2;
			}
		   niveau.listerBat(bean.getIdClient(), this.idNiveau);
			   
			   Iterator it = niveau.liste().iterator();
			   Integer siz=niveau.liste().size();
	           while (it.hasNext())
	            {
	        	   
	        	   Niveau nv = (Niveau)it.next();
	        	   ObjetNiveau niv = new ObjetNiveau();
	               niv.setIdNiveau(nv.getIdNiveau());
	               niv.setLibelle(nv.getLibelle());
	               niv.setLevel(nv.getLevel());
	               if (nv.getTri()==1) niv.setPremier(true); else niv.setPremier(false);
	               if (nv.getTri()==siz) niv.setDernier(true); else niv.setDernier(false);
	               if ((nv.getNiveauobjet()!=null)&& (nv.getNiveauobjet().getIdNiveauObjet()!=0)) //eq
	               {
	            	   niv.setIdNiveauObjet(nv.getNiveauobjet().getIdNiveauObjet());
	            	   
	    	           
	    	           
	               }
	              
	              
	               this.map.add(niv);
	             
	               
	        	   
	            }
	           
	           if (this.isLast==true)
               {   libelleNiveau.lister2(bean.getIdClient(), 1, 3);
    	           this.libelleNiveauCourant=libelleNiveau.model().getLibelle();
    	           niveau2.afficherbyId(bean.getIdClient(), this.idNiveau);
    	           String sit=niveau2.model().getLibelle();
    	           niveau2.afficherbyId(bean.getIdClient(), niveau2.model().getParentId());
    	           this.link=niveau2.model().getLibelle()+">"+sit;
               }
               else //bat
               {
            	   libelleNiveau.lister2(bean.getIdClient(), 1, 2);
    	           this.libelleNiveauCourant=libelleNiveau.model().getLibelle();
    	           niveau2.afficherbyId(bean.getIdClient(), this.idNiveau);
    	           this.link=niveau2.model().getLibelle();
               }
		   }
		   
	   else if ((this.idNiveauObjet!=null) && (this.idNiveauObjet!=0) )
	   {
		   this.isObjet=true;
		   NiveauObjetDatabaseLayout niveauObjetdb = null;
		   niveauObjetdb = new NiveauObjetDatabaseLayout();
		   niveauObjetdb.transactionOpen();
		   niveauObjetdb.afficher(bean.getIdClient(), this.idNiveauObjet);
   	 if (niveauObjetdb!=null)
        
   	 {
   		  ObjetNiveau niv = new ObjetNiveau();
            niv.setIdNiveau(niveauObjetdb.model().getIdNiveauObjet());
            niv.setLibelle(niveauObjetdb.model().getLibelle());
            niv.setPremier(true); niv.setDernier(true);
            this.map.add(niv);
   	 }
     	   
     	   
         
        libelleNiveau.lister2(bean.getIdClient(), 2, 2);
        this.libelleNiveauCourant=libelleNiveau.model().getLibelle();
        niveau2.afficherbyId(bean.getIdClient(), this.idNiveau);
        String eq=niveau2.model().getLibelle();
        niveau2.afficherbyId(bean.getIdClient(), niveau2.model().getParentId());
        String bat=niveau2.model().getLibelle();
        niveau2.afficherbyId(bean.getIdClient(), niveau2.model().getParentId());
        this.link=niveau2.model().getLibelle()+">"+bat+">"+eq;
        niveauObjetdb.close(); 
        this.idNiveauObjet=0;
	   }
	
	 } catch (Exception e) {
         e.printStackTrace();
     }		 
	   
	   finally{
		   niveau.close();
		   niveau2.close();
		   libelleNiveau.close();
	   }
	   
	   
   }
   
   public void lister()
   {
	  
       if (this.idNiveau != null)
     {
    	  
         consulter(this.idNiveau);
     }
     else
     {
    	
         consulter(Integer.valueOf(0));
     }
 
       LoginBean bean = LoginUtil.getLoginBean();
 
       this.map.clear();
       this.listeNiveauxParent.clear();
       this.nombreNiveaux = null;
       this.positionActuelleNiveau = Integer.valueOf(1);
       this.triMax = Integer.valueOf(0);
       this.libelleNiveauCourant = "";
     this.isObjet=false;
       //this.isLast=false;
     try
     {
    	 
    	 if ((this.idNiveauObjet!=null) && (this.idNiveauObjet!=0) )
         { this.isObjet=true;
         
      	  
      	   NiveauObjetDatabaseLayout niveauObjet = null;
      	   niveauObjet = new NiveauObjetDatabaseLayout();
      	   niveauObjet.afficher(bean.getIdClient(), this.idNiveauObjet);
      	   if (niveauObjet!=null)
      	   {
      	   ObjetNiveau niv = new ObjetNiveau();
      	 niv.setIdNiveauObjet(niveauObjet.model().getIdNiveauObjet());
      	System.out.println("niveauObjet.model().getIdNiveauObjet(): ::::" + niveauObjet.model().getIdNiveauObjet());
         niv.setLibelleobj(niveauObjet.model().getLibelle());
         System.out.println("niveauObjet.model().getLibelle(): ::::" + niveauObjet.model().getLibelle());
        
         this.map.add(niv);
         _parcourtArbre(this.niveauNiveauCoutant);
      	   }
         }
    	 
    	 else{
         Session session = HibernateUtil.currentSession();
         Transaction tr = session.beginTransaction();
 
         Iterator it = session.createQuery("from Niveau as no inner join fetch no.client where no.masque = :masque and no.client.idClient = :client and no.client.masque = :masque order by no.tri")
           .setParameter("masque", Boolean.valueOf(false))
           .setParameter("client", bean.getIdClient())
           .list().iterator();
        
         while (it.hasNext())
       {
        	 
           Niveau objet = (Niveau)it.next();
        
           if (this.idNiveauAConsulter == null)
         {
             if (objet.getNiveau() != null)
             {
            	 
             continue;
             }
             ObjetNiveau niv = new ObjetNiveau();
             niv.setIdNiveau(objet.getIdNiveau());
             niv.setLibelle(objet.getLibelle());
             niv.setTri(Integer.valueOf(objet.getTri()));
             if (this.map.isEmpty())
               niv.setPremier(Boolean.valueOf(true));
             this.map.add(niv);
             
             this.triMax = Integer.valueOf(objet.getTri());
           
             //if (objet.getNiveauobjet().getIdNiveauObjet()!=0)
            	// isObjet=true;
            	 
         }
         else
         {
             if ((objet.getNiveau() != null) && 
               (objet.getNiveau().getIdNiveau().equals(this.idNiveauAConsulter)))
           {
               ObjetNiveau niv = new ObjetNiveau();
               niv.setIdNiveau(objet.getIdNiveau());
               niv.setLibelle(objet.getLibelle());
               niv.setTri(Integer.valueOf(objet.getTri()));
               if (this.map.isEmpty())
                 niv.setPremier(Boolean.valueOf(true));
               this.map.add(niv);
               this.triMax = Integer.valueOf(objet.getTri());
               
             
               if (objet.getNiveauobjet()!=null)
               {
              	 isObjet=true;
              	 this.idNiveauObjet=objet.getNiveauobjet().getIdNiveauObjet();
              	niv.setIdNiveauObjet(objet.getNiveauobjet().getIdNiveauObjet());
              	 
              	 
               }
              	 
           }
 
            if (!(objet.getIdNiveau().equals(this.idNiveauAConsulter)))
             continue;
             this.niveauNiveauCoutant = objet;
             this.nombreNiveaux = Integer.valueOf(this.niveauNiveauCoutant.getClient().getNombreNiveaux());
         }
 
       }
 
         if (!(this.map.isEmpty())) {
        	 ((ObjetNiveau)this.map.get(this.map.size() - 1)).setDernier(Boolean.valueOf(true));
       }
 
         _parcourtArbre(this.niveauNiveauCoutant);
         tr.commit();
     }}
   catch (Exception e) {
         e.printStackTrace();
     }
     finally
     {
         HibernateUtil.closeSession();
     }
 
       List sortList = new ArrayList();
       while (!(this.listeNiveauxParent.isEmpty()))
     {
    	   sortList.add((ObjetNavigation)this.listeNiveauxParent.get(this.listeNiveauxParent.size() - 1));
    	   this.listeNiveauxParent.remove(this.listeNiveauxParent.size() - 1);
     }
       this.listeNiveauxParent.addAll(sortList);
 
       if (this.idNiveauAConsulter != null)
     {
    	   this.positionActuelleNiveau = Integer.valueOf(this.listeNiveauxParent.size() + 2);
     }
       System.out.println("PNS : ::::" + this.positionActuelleNiveau);
 
       getAriane();
 
       LibelleNiveauDatabaseLayout libelleNiveau = null;
     try {
         libelleNiveau = new LibelleNiveauDatabaseLayout();
         libelleNiveau.transactionOpen();
 
         libelleNiveau.choisirTypeNiveau();
         libelleNiveau.lister(bean.getIdClient());
 
         ArrayList list = (ArrayList)libelleNiveau.liste();
 
         for (Integer i = Integer.valueOf(0); i.intValue() < list.size(); i = Integer.valueOf(i.intValue() + 1))
       {
           if ((this.positionActuelleNiveau == null) || (this.positionActuelleNiveau.intValue() == 1 ))
         {
             this.libelleNiveauCourant = ((Libelleniveau)list.get(0)).getLibelle();
             System.out.println("LibelleA" + this.libelleNiveauCourant + this.positionActuelleNiveau);
         }
         else
         {
             if (this.positionActuelleNiveau.intValue() > this.nombreNiveaux.intValue())
           {
               libelleNiveau.choisirTypeObjet();
               libelleNiveau.lister(bean.getIdClient());
               ArrayList listObjet = (ArrayList)libelleNiveau.liste();
 
               this.libelleNiveauCourant = ((Libelleniveau)listObjet.get(0)).getLibelle();
           }
           else
           {
        	   this.libelleNiveauCourant = ((Libelleniveau)list.get(this.positionActuelleNiveau.intValue() - 1)).getLibelle();
           }
             System.out.println("LibelleB" + this.libelleNiveauCourant + this.positionActuelleNiveau);
         }
       }

         libelleNiveau.transactionCommit();
     } catch (Exception e) {
         System.out.println("Erreur");
         e.printStackTrace();
     } finally {
         libelleNiveau.close();
     }
     if (this.libelleNiveauCourant.equals(""))
     {
    	 this.libelleNiveauCourant="Messwert";
    	 
     }
   }
 
   public String monter(Integer num)
   {
	   
       return deplacer(num, "haut");
   }
 
   public String descendre(Integer num) {
	   System.out.println("num"+num);
     return deplacer(num, "bas");
   }
   public String descendre2(Integer num) {
	   System.out.println("num"+num);
	   NiveauDatabaseLayout niveau = null;
       try
       {
        niveau = new NiveauDatabaseLayout();
         niveau.transactionOpen();
       }
       catch (Exception e) {
           System.out.println("Erreur de tri");
  					e.printStackTrace();
           e.getMessage();
         } finally {
          niveau.close();
         }
   
       
     
       return "liste_niveau?faces-redirect=true&id=" + this.idNiveau;
 }
   public String deplacer(Integer num, String sens) {
	 
	   lister();
 		
    for (Integer i = Integer.valueOf(0); i.intValue() < this.map.size(); i = Integer.valueOf(i.intValue() + 1))  {
      ObjetNiveau obj = (ObjetNiveau)this.map.get(i.intValue());
      
     if (obj.getIdNiveau() != num.intValue())
         continue;

       Integer tri = obj.getTri();
      Integer num2 = null;
     Integer tri2 = null;
     //System.out.println("deplacer her3 "+obj.getIdNiveau()+obj.getPremier().booleanValue());
      if ((sens.equals("haut")) && (!(obj.getPremier().booleanValue())))
       {
    	  
         num2 = ((ObjetNiveau)this.map.get(i.intValue() - 1)).getIdNiveau();
         
         tri2 = ((ObjetNiveau)this.map.get(i.intValue() -1 )).getTri();
        
       }
      else if ((sens.equals("bas")) && (!(obj.getDernier().booleanValue())))
       {
        num2 = ((ObjetNiveau)this.map.get(i.intValue() + 1)).getIdNiveau();
         tri2 = ((ObjetNiveau)this.map.get(i.intValue() +1 )).getTri();
       }
 
       LoginBean bean = LoginUtil.getLoginBean();
       NiveauDatabaseLayout niveau = null;
       try
       {
        niveau = new NiveauDatabaseLayout();
         niveau.transactionOpen();
 
         if (niveau.verifierDroitModifier(bean.getIdClient(), num).booleanValue())
         {
           niveau.model().setTri(tri2.intValue());
        // niveau.model().setClefTimestamp(new Date());
 
           niveau.update();
         }
 
         if (niveau.verifierDroitModifier(bean.getIdClient(), num2).booleanValue())
         {
           niveau.model().setTri(tri.intValue());
          // niveau.model().setClefTimestamp(new Date());
 
          niveau.update();
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
         System.out.println("Erreur de tri");
					e.printStackTrace();
         
       } finally {
        niveau.close();
       }
 
     }
  this.idNiveauObjet=0;
     return "liste_niveau?faces-redirect=true&id=" + this.idNiveau;
   }
 
   public String consulter(Integer num)
   {
	   
       this.idAjouter = num;
       if (num.intValue() == 0 )
         num = null;
       this.idNiveauAConsulter = num;
 
       return "liste_niveau?faces-redirect=true";
   }
 
   public Boolean isNiveauMax()
   { 
       Integer nombreNiveauxPlus1 = Integer.valueOf(0);
       if (this.nombreNiveaux != null) {
         nombreNiveauxPlus1 = Integer.valueOf(this.nombreNiveaux.intValue() +1 );
     }
       
       if ((this.positionActuelleNiveau == nombreNiveauxPlus1))
    	   { 
    	  // isObjet
    	   //this.isLast=false;
    	   return Boolean.valueOf(true); 
    	   }
    	   else
    	   {
    		 //  this.isLast=false;
    	   return Boolean.valueOf(false);
    	   }
       
   }
  
   public Boolean isNotNiveauMax()
   { this.idNiveauObjet=0;
  // System.out.println("last****"+this.isLast);
       return Boolean.valueOf(!(isNiveauMax().booleanValue()));
   }
 
   public List<ObjetNavigation> getAriane()
   {
       List liste = new ArrayList();
       liste.add(new ObjetNavigation(I18nUtil.get("navigation_parametrage_niveaux"), Integer.valueOf(0)));
 
       if (this.idNiveauAConsulter != null)
     {
    	  
         liste.addAll(this.listeNiveauxParent);
         liste.add(new ObjetNavigation(this.niveauNiveauCoutant.getLibelle(), this.idNiveauAConsulter));
     }
      
       return liste;
   }
 
   private void _parcourtArbre(Niveau objet)
   {
	   System.out.println("objet: " + objet.getNiveau());
       
       if (objet.getNiveau() != null)
     {
         this.listeNiveauxParent.add(new ObjetNavigation(objet.getNiveau().getLibelle(), objet.getNiveau().getIdNiveau()));
         _parcourtArbre(objet.getNiveau());
     }
   
   }
  
   public String supprimerobj(Integer obn)
   {
	   LoginBean bean = LoginUtil.getLoginBean();
       NiveauObjetDatabaseLayout niveauobj = null;
       NiveauDatabaseLayout niveau = null;
      try {
    	  niveauobj = new NiveauObjetDatabaseLayout();
    	  niveauobj.transactionOpen();
    	  niveau = new NiveauDatabaseLayout();
	        niveau.transactionOpen();
    	  niveauobj.afficher(bean.getIdClient(),obn );
    	  niveauobj.model().setMasque(true);
    	  
    	  niveau.afficherunik(bean.getIdClient(), niveauobj.model().getIdNiveauObjet() );
    	  niveau.setNiveauObjet(null);
    	  niveau.update();
    	  niveauobj.update();
    	  

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
        }
      finally {
	         niveau.close(); niveauobj.close();
	        }
	   return "liste_niveau?faces-redirect=true";
   }
   
   
   public String supprimer(ObjetNiveau obn)
   {
	 
	   
	   LoginBean bean = LoginUtil.getLoginBean();
	       NiveauDatabaseLayout niveau = null;
	       NiveauObjetDatabaseLayout niveauobj = null;
	       NiveauDatabaseLayout niveauTri = null;
	       
	      try {
	         niveau = new NiveauDatabaseLayout();
	        niveau.transactionOpen();
	        niveauobj = new NiveauObjetDatabaseLayout();
	    	  niveauobj.transactionOpen();
	    	  niveauTri = new NiveauDatabaseLayout();
	    	  niveauTri.transactionOpen();
	    	  
	    	  
	        if (niveau.verifierDroitModifier(bean.getIdClient(), obn.getIdNiveau()).booleanValue())
	          {
	            niveau.model().setMasque(true);
	            niveau.model().setClefTimestamp(new Date());
	  
	    
	            niveau.update();
	            // maj tri
	            Integer triAct=niveau.model().getTri();
	             // chercher la liste
	            if ((niveau.model().getParentId()== null)||(niveau.model().getParentId()==0))
	            {
	            	niveauTri.listeTriSite(bean.getIdClient(),triAct);
	            	 Iterator itTri = niveauTri.liste().iterator();
	    	         while (itTri.hasNext())
	    	          {
	    	        	 Niveau niv = (Niveau)itTri.next();
	    	        	 NiveauDatabaseLayout niveauTriup = null;
	    		    	  niveauTriup = new NiveauDatabaseLayout();
	    		    	  niveauTriup.transactionOpen();
	    	        	 niveauTriup.afficherbyId(bean.getIdClient(), niv.getIdNiveau());
	    	        	
	    	        	 niveauTriup.model().setTri(niv.getTri()-1);
	    	        	 
	    	        	 niveauTriup.save();
	    	        	 niveauTriup.transactionCommit();
	    	        	 niveauTriup.close();
	    	          }
	            	
	            }
	            else
	            {
	            	niveauTri.listeTriup(bean.getIdClient(),triAct,niveau.model().getParentId());
	            	 Iterator itTri = niveauTri.liste().iterator();
	    	         while (itTri.hasNext())
	    	          {
	    	        	 Niveau niv = (Niveau)itTri.next();
	    	        	 NiveauDatabaseLayout niveauTriup = null;
	    		    	  niveauTriup = new NiveauDatabaseLayout();
	    		    	  niveauTriup.transactionOpen();
	    	        	 niveauTriup.afficherbyId(bean.getIdClient(), niv.getIdNiveau());
	    	        	
	    	        	 niveauTriup.model().setTri(niv.getTri()-1);
	    	        	 
	    	        	 niveauTriup.save();
	    	        	 niveauTriup.transactionCommit();
	    	        	 niveauTriup.close();
	    	          }
	            }
	            
	            if (niveau.model().getNiveauobjet()!=null)
	     	   {
	            	
	     		   niveauobj.afficher(bean.getIdClient(),niveau.model().getNiveauobjet().getIdNiveauObjet());
	     		   niveauobj.model().setExiste(false);
	     		  niveauobj.update();
	     		 niveauobj.transactionCommit();
	     	   }
	        }
	   
	         List listeMasque = new ArrayList();
	        listeMasque.add(obn.getIdNiveau());
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
	         if (niveau.model().getNiveauobjet()!=null)
	     	   {
	     		   niveauobj.afficher(bean.getIdClient(),niveau.model().getNiveauobjet().getIdNiveauObjet());
	     		   niveauobj.model().setExiste(false);
	     		   niveauobj.update();
	     		  niveauobj.transactionCommit();
	     	   }
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
	    
	          return "liste_niveau?faces-redirect=true";
	        } finally {
	         niveau.close(); niveauobj.close();
	        }
	   
	        return "liste_niveau?faces-redirect=true";
	      }
	  
   private ArrayList<SelectItem> niveauList = new ArrayList();
   private ArrayList<SelectItem> batList = new ArrayList();
   private Integer siteChoisi;
   private Integer batChoisi;
   
   public ArrayList<SelectItem> getNiveauList() {
	return niveauList;
}

public void setNiveauList(ArrayList<SelectItem> niveauList) {
	this.niveauList = niveauList;
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

public Integer getBatChoisi() {
	return batChoisi;
}

public void setBatChoisi(Integer batChoisi) {
	this.batChoisi = batChoisi;
}
private Integer tagmodif=0;

public Integer getTagmodif() {
	return tagmodif;
}

public void setTagmodif(Integer tagmodif) {
	this.tagmodif = tagmodif;
}

public void affichertag( Integer idUnit)
   {
   	this.niveauList.clear();
   	this.siteChoisi=0;
	this.batChoisi=0;
	
   	LoginBean bean = LoginUtil.getLoginBean();
   	NiveauDatabaseLayout niveau = new NiveauDatabaseLayout();
   	niveau.transactionOpen();
   	try
   	{
   	System.out.println("idUnit"+idUnit);
   	this.tagmodif=idUnit;
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

public String associer( )
{
	   System.out.println(this.batChoisi+"*"+this.tagmodif);
	   if ((this.batChoisi!=0)&&(this.tagmodif!=0))
	   {
		   LoginBean bean = LoginUtil.getLoginBean();
		   NiveauDatabaseLayout niveau = new NiveauDatabaseLayout();
			niveau.transactionOpen();
			
			NiveauDatabaseLayout niveauTri = new NiveauDatabaseLayout();
			niveauTri.transactionOpen();
			
			Integer triAct=0;
			try
			{
			
			//chercher le tri
				
				
				niveau.afficherbyId(bean.getIdClient(), this.tagmodif);
				triAct=niveau.model().getTri();
				//MAJ ancien Triage
				niveauTri.listeTriup(bean.getIdClient(),triAct,niveau.model().getParentId());
           	 Iterator itTri = niveauTri.liste().iterator();
   	         while (itTri.hasNext())
   	          {
   	        	 Niveau niv = (Niveau)itTri.next();
   	        	 NiveauDatabaseLayout niveauTriup = null;
   		    	  niveauTriup = new NiveauDatabaseLayout();
   		    	  niveauTriup.transactionOpen();
   	        	 niveauTriup.afficherbyId(bean.getIdClient(), niv.getIdNiveau());
   	        	
   	        	 niveauTriup.model().setTri(niv.getTri()-1);
   	        	 
   	        	 niveauTriup.save();
   	        	 niveauTriup.transactionCommit();
   	        	 niveauTriup.close();
   	          }
				//chercher le tri max du nouveau parent
   	      Integer trimax=0;
   	      NiveauDatabaseLayout niveauTrimax = new NiveauDatabaseLayout();
	    	  
   	          niveauTrimax.afficherLasttree(bean.getIdClient(), this.batChoisi);
   	          if (niveauTrimax.model()!=null)
   	        	trimax=niveauTrimax.model().getTri();
   	       niveauTrimax.close();
				//Maj parent
				niveau.setNiveauParent(this.batChoisi);
				niveau.model().setTri(trimax+1);
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
			catch (Exception e) {
		   	 e.printStackTrace();
		    } finally {
		      niveau.close();
		      niveauTri.close();
		      
		    }
	   }
	   
	   return "liste_niveau?faces-redirect=true";
}
public void back()
{
	System.out.println("");
	// return "liste_niveau?faces-redirect=true&id=" + this.idNiveau;
}
   }
 
