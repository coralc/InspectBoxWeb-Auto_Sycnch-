 package com.inspectbox.web;
 
 import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Size;

import com.inspectbox.databaseLayout.LibelleNiveauDatabaseLayout;
import com.inspectbox.databaseLayout.SynchDatabaseLayout;
import com.inspectbox.databaseLayout.TerminalDatabaseLayout;
import com.inspectbox.model.Libelleniveau;
import com.inspectbox.model.SynchPK;
import com.inspectbox.model.Terminal;
import com.inspectbox.utils.LoginUtil;
 
 @ManagedBean(name="libelleNiveauBean")
 @RequestScoped
 public class LibelleNiveauBean
 {
 
   @Size(min=1, max=254)
   private String libelleNiveau1;
 
   @Size(min=1, max=254)
   private String libelleNiveau2;
 
   @Size(min=1, max=254)
   private String libelleNiveau3;
 
   @Size(min=1, max=254)
   private String libelleObjet1;
 
   @Size(min=1, max=254)
   private String libelleObjet2;
/*  47 */   private Boolean modificationOK = Boolean.valueOf(false);
 

			@Size(min=1, max=254)
			private String libelleGamme;
			
   public String getLibelleNiveau1()
   {
/*  56 */     return this.libelleNiveau1;
   }
 
   public void setLibelleNiveau1(String libelleNiveau1)
   {
/*  62 */     this.libelleNiveau1 = libelleNiveau1;
   }
 
   public String getLibelleNiveau2()
   {
/*  68 */     return this.libelleNiveau2;
   }
 
   public void setLibelleNiveau2(String libelleNiveau2)
   {
/*  74 */     this.libelleNiveau2 = libelleNiveau2;
   }
 
   public String getLibelleNiveau3()
   {
/*  80 */     return this.libelleNiveau3;
   }
 
   public void setLibelleNiveau3(String libelleNiveau3)
   {
/*  86 */     this.libelleNiveau3 = libelleNiveau3;
   }
 
   public String getLibelleObjet1()
   {
/*  92 */     return this.libelleObjet1;
   }
 
   public void setLibelleObjet1(String libelleObjet1)
   {
/*  98 */     this.libelleObjet1 = libelleObjet1;
   }
 
   public String getLibelleObjet2()
   {
/* 104 */     return this.libelleObjet2;
   }
 
   public void setLibelleObjet2(String libelleObjet2)
   {
/* 110 */     this.libelleObjet2 = libelleObjet2;
   }
 
   public Boolean getModificationOK()
   {
/* 116 */     return this.modificationOK;
   }
 
   public void setModificationOK(Boolean modificationOK)
   {
  this.modificationOK = modificationOK;
   }


			public String getLibelleGamme() {
				return libelleGamme;
			}
			public void setLibelleGamme(String libelleGamme) {
				this.libelleGamme = libelleGamme;
			}
			
			public void afficher()
			{
				LoginBean bean = LoginUtil.getLoginBean();
				LibelleNiveauDatabaseLayout libelleNiveau11 = null;
			 	LibelleNiveauDatabaseLayout libelleNiveau12 = null;
			 	LibelleNiveauDatabaseLayout libelleNiveau13 = null;
			 	LibelleNiveauDatabaseLayout libelleNiveau21 = null;
			 	LibelleNiveauDatabaseLayout libelleNiveau22 = null ;
			 	
			 	
				 try
			     {
				 libelleNiveau11 = new LibelleNiveauDatabaseLayout();
			 	 libelleNiveau12 = new LibelleNiveauDatabaseLayout();
			 	 libelleNiveau13 = new LibelleNiveauDatabaseLayout();
			 	 libelleNiveau21 = new LibelleNiveauDatabaseLayout();
			 	 libelleNiveau22 = new LibelleNiveauDatabaseLayout();
			 	
			 	libelleNiveau11.transactionOpen();
			 	libelleNiveau11.lister2(bean.getIdClient(), 1, 1);
			 	if (libelleNiveau11.model()!=null)
			 		this.libelleNiveau1=libelleNiveau11.model().getLibelle();
			 	
			 	libelleNiveau12.transactionOpen();
			 	libelleNiveau12.lister2(bean.getIdClient(), 1, 2);
			 	if (libelleNiveau12.model()!=null)
			 		this.libelleNiveau2=libelleNiveau12.model().getLibelle();
			 	
			 	libelleNiveau13.transactionOpen();
			 	libelleNiveau13.lister2(bean.getIdClient(), 1, 3);
			 	if (libelleNiveau13.model()!=null)
			 		this.libelleNiveau3=libelleNiveau13.model().getLibelle();
			 	
			 	libelleNiveau21.transactionOpen();
			 	libelleNiveau21.lister2(bean.getIdClient(), 2, 1);
			 	if (libelleNiveau21.model()!=null)
			 		this.libelleObjet1=libelleNiveau21.model().getLibelle();
			 	
			 	libelleNiveau22.transactionOpen();
			 	libelleNiveau22.lister2(bean.getIdClient(), 2, 2);
			 	if (libelleNiveau22.model()!=null)
			 		this.libelleObjet2=libelleNiveau22.model().getLibelle();
			 	
			     }
				 catch (Exception e)
			     {
			      e.printStackTrace();
			     } finally {
			    	 libelleNiveau11.close(); libelleNiveau12.close(); libelleNiveau13.close(); libelleNiveau21.close(); libelleNiveau22.close();
			     }
			}
  
 
   public String modifier()
   {
	   System.out.println("this"+this.libelleNiveau1);
	   
     LoginBean bean = LoginUtil.getLoginBean();
     LibelleNiveauDatabaseLayout libelleNiveau11 = null;
	 	LibelleNiveauDatabaseLayout libelleNiveau12 =null;
	 	LibelleNiveauDatabaseLayout libelleNiveau13 = null;
	 	LibelleNiveauDatabaseLayout libelleNiveau21 = null;
	 	LibelleNiveauDatabaseLayout libelleNiveau22 = null;
	 	LibelleNiveauDatabaseLayout libelleNiveau31 = null;
	 	
      try
     {
    	   libelleNiveau11 = new LibelleNiveauDatabaseLayout();
  	 	 libelleNiveau12 = new LibelleNiveauDatabaseLayout();
  	 	 libelleNiveau13 = new LibelleNiveauDatabaseLayout();
  	 	 libelleNiveau21 = new LibelleNiveauDatabaseLayout();
  	 	 libelleNiveau22 = new LibelleNiveauDatabaseLayout();
  	 	 libelleNiveau31 = new LibelleNiveauDatabaseLayout();
    	
   	 	// first level
   	 libelleNiveau11.transactionOpen();
   	libelleNiveau11.lister2(bean.getIdClient(), 1, 1);
   	if (libelleNiveau11.model()!=null) //maj	
   	{
   		libelleNiveau11.model().setLibelle(this.libelleNiveau1);
   		libelleNiveau11.model().setClefTimestamp(new Date());
   		libelleNiveau11.save(); libelleNiveau11.transactionCommit();
   	}
   	else
   	{ LibelleNiveauDatabaseLayout libelleNiveau11n = new LibelleNiveauDatabaseLayout();
   	libelleNiveau11n.transactionOpen();
   		libelleNiveau11n.setClient(bean.getIdClient());
 		libelleNiveau11n.model().setClefTimestamp(new Date());
 		libelleNiveau11n.model().setLibelle(this.libelleNiveau1);
 		libelleNiveau11n.model().setTypeNiveau(1);
 		libelleNiveau11n.model().setNum(1);
 		libelleNiveau11n.save();
 		libelleNiveau11n.close();
 		
   	}
  
 // second level
  	 libelleNiveau12.transactionOpen();
  	libelleNiveau12.lister2(bean.getIdClient(), 1, 2);
  	if (libelleNiveau12.model()!=null) //maj	
  	{
  		libelleNiveau12.model().setLibelle(this.libelleNiveau2);
  		libelleNiveau12.model().setClefTimestamp(new Date());
  		libelleNiveau12.save(); libelleNiveau12.transactionCommit();
  	}
  	else
  	{
  		LibelleNiveauDatabaseLayout libelleNiveau21n = new LibelleNiveauDatabaseLayout();
  	   	libelleNiveau21n.transactionOpen();
  		libelleNiveau21n.setClient(bean.getIdClient());
		libelleNiveau21n.model().setClefTimestamp(new Date());
		libelleNiveau21n.model().setLibelle(this.libelleNiveau2);
		libelleNiveau21n.model().setTypeNiveau(1);
		libelleNiveau21n.model().setNum(2);
		libelleNiveau21n.save(); libelleNiveau21n.close();
  	}
  	
 // thirsd level
  	 libelleNiveau13.transactionOpen();
  	libelleNiveau13.lister2(bean.getIdClient(), 1, 3);
  	if (libelleNiveau13.model()!=null) //maj	
  	{
  		libelleNiveau13.model().setLibelle(this.libelleNiveau3);
  		libelleNiveau13.model().setClefTimestamp(new Date());
  		libelleNiveau13.save();  libelleNiveau13.transactionCommit();
  	}
  	else
  	{LibelleNiveauDatabaseLayout libelleNiveau13n = new LibelleNiveauDatabaseLayout();
	   	libelleNiveau13n.transactionOpen();
  		
  		libelleNiveau13n.setClient(bean.getIdClient());
		libelleNiveau13n.model().setClefTimestamp(new Date());
		libelleNiveau13n.model().setLibelle(this.libelleNiveau3);
		libelleNiveau13n.model().setTypeNiveau(1);
		libelleNiveau13n.model().setNum(3);
		libelleNiveau13n.save(); libelleNiveau13n.close();
  	}
  
 // first object
  	 libelleNiveau21.transactionOpen();
  	libelleNiveau21.lister2(bean.getIdClient(), 2, 1);
  	if (libelleNiveau21.model()!=null) //maj	
  	{
  		libelleNiveau21.model().setLibelle(this.libelleObjet1);
  		libelleNiveau21.model().setClefTimestamp(new Date());
  		libelleNiveau21.save(); libelleNiveau21.transactionCommit();
  	}
  	else
  	{ 
  		LibelleNiveauDatabaseLayout libelleNiveau21n = new LibelleNiveauDatabaseLayout();
	   	libelleNiveau21n.transactionOpen();
  		
  		libelleNiveau21n.setClient(bean.getIdClient());
		libelleNiveau21n.model().setClefTimestamp(new Date());
		libelleNiveau21n.model().setLibelle(this.libelleObjet1);
		libelleNiveau21n.model().setTypeNiveau(2);
		libelleNiveau21n.model().setNum(1);
		libelleNiveau21n.save(); libelleNiveau21n.close();
  	}
  	
 // second object
 	 libelleNiveau22.transactionOpen();
 	libelleNiveau22.lister2(bean.getIdClient(), 2, 2);
 	if (libelleNiveau22.model()!=null) //maj	
 	{
 		libelleNiveau22.model().setLibelle(this.libelleObjet2);
 		libelleNiveau22.model().setClefTimestamp(new Date());
 		libelleNiveau22.save(); libelleNiveau22.transactionCommit();
 	}
 	else
 	{
 		LibelleNiveauDatabaseLayout libelleNiveau22n = new LibelleNiveauDatabaseLayout();
	   	libelleNiveau22n.transactionOpen();
  		
 		libelleNiveau22n.setClient(bean.getIdClient());
		libelleNiveau22n.model().setClefTimestamp(new Date());
		libelleNiveau22n.model().setLibelle(this.libelleObjet2);
		libelleNiveau22n.model().setTypeNiveau(2);
		libelleNiveau22n.model().setNum(2);
		libelleNiveau22n.save(); libelleNiveau22n.close();
 	}
 	
 // Hplan
 	 libelleNiveau31.transactionOpen();
 	libelleNiveau31.lister2(bean.getIdClient(), 3, 1);
 	if (libelleNiveau31.model()!=null) //maj	
 	{
 		libelleNiveau31.model().setLibelle("IH-Plan");
 		libelleNiveau31.model().setClefTimestamp(new Date());
 		libelleNiveau31.save(); libelleNiveau31.transactionCommit();
 	}
 	else
 	{
 		LibelleNiveauDatabaseLayout libelleNiveau31n = new LibelleNiveauDatabaseLayout();
	   	libelleNiveau31n.transactionOpen();
  		
 		libelleNiveau31n.setClient(bean.getIdClient());
		libelleNiveau31n.model().setClefTimestamp(new Date());
		libelleNiveau31n.model().setLibelle("IH-Plan");
		libelleNiveau31n.model().setTypeNiveau(3);
		libelleNiveau31n.model().setNum(1);
		libelleNiveau31n.save(); libelleNiveau31n.close();
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
 		
  
     } catch (Exception e) {
       e.printStackTrace();
     } finally {
      libelleNiveau11.close(); libelleNiveau12.close();libelleNiveau13.close();libelleNiveau21.close();libelleNiveau22.close();libelleNiveau31.close();


     }
 
  return "modifier_libelle";
   }
 }

