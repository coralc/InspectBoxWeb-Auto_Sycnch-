package com.inspectbox.databaseLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;


import com.inspectbox.model.Terminal;
import com.inspectbox.utils.HibernateUtil;

public class TerminalDatabaseLayout implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4510659047866754745L;
	private Terminal model = null;
	 private ArrayList liste = null;
	private Session session = null;
    private Transaction tx = null;

    public TerminalDatabaseLayout()
    {
       this.model = new Terminal();
       this.session = HibernateUtil.currentSession();
       this.liste = new ArrayList();
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
 				System.out.println("Exception occurrs while adding terminal");
 				e.printStackTrace();
 			}   }
  
    public Terminal model()
    {
    return this.model;
    }
    
    public void close()
    {
      HibernateUtil.closeSession();
    }
    public List<Terminal> liste()
    {
   return this.liste;
    }
  
    public void listerAll()
    {
      this.liste = 
        ((ArrayList)this.session.createQuery("from Terminal as tr where tr.masque=:masque ")
        		 .setParameter("masque",Boolean.valueOf(false))
       .list());

    }
}
