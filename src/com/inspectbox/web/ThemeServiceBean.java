package com.inspectbox.web;


import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.inspectbox.databaseLayout.NiveauObjetDatabaseLayout;
import com.inspectbox.model.Niveauobjet;
import com.inspectbox.utils.LoginUtil;
import com.inspectbox.utils.Themed;
import com.inspectbox.web.LoginBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 
@ManagedBean(name="themeServiceBean")

public class ThemeServiceBean {
     
    private List<Themed> themes;
     
    @PostConstruct
    public void init() {
        themes = new ArrayList<Themed>();
        
    	
    	
    	    
    	NiveauObjetDatabaseLayout niveauObjet = null;
    	
        try
        {
             niveauObjet = new NiveauObjetDatabaseLayout();
             niveauObjet.transactionOpen();
              niveauObjet.listerAll(19);
             Iterator it = niveauObjet.liste().iterator();
             while (it.hasNext())
              {
            	 Niveauobjet objet = (Niveauobjet)it.next();
            	 themes.add(new Themed(objet.getIdNiveauObjet(), objet.getLibelle(), objet.getLibelle().toLowerCase())); 
              }
        }
        catch (Exception e) {
 	       e.printStackTrace();
 	      }
finally{
	niveauObjet.close();
	
}
       
    }
     
    public List<Themed> getThemes() {
        return themes;
    } 
}