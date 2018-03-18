 package com.inspectbox.databaseLayout;
 
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.inspectbox.model.Import;

import com.inspectbox.utils.HibernateUtil;
 
 public class ImportDatabaseLayout
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private Import model = null;
   private ArrayList liste = null;
   private Session session = null;
  private Transaction tx = null;

 
   public ImportDatabaseLayout()
   {
     this.model = new Import();
   this.liste = new ArrayList();
 this.session = HibernateUtil.currentSession();
   }
 
  
  
   public void transactionOpen()
   {
     this.tx = this.session.beginTransaction();
   }
 
   public void transactionCommit()
   {
     this.tx.commit();
   }
 
   public void update()
   {
    this.session.update(this.model);
   }
 
   public void save()
   {
	
			try {
				this.session.save(this.model);
				
			} catch (Exception e) {
				System.out.println("Exception occurrs while adding Import");
				e.printStackTrace();
			}   }
 
   public Import model()
   {
     return this.model;
   }
 
   public List<Import> liste()
   {
    return this.liste;
   }
 
   public void close()
   {
    HibernateUtil.closeSession();
   }
 
   

 
  
 }

