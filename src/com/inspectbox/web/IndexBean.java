package com.inspectbox.web;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.inspectbox.databaseLayout.NiveauDatabaseLayout;

@ManagedBean(name="indexBean")
@ViewScoped
public class IndexBean  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2024407275458956305L;
	private Integer areaID;
	
	 public Integer getAreaID() {
		return areaID;
	}

	public void setAreaID(Integer areaID) {
		this.areaID = areaID;
	}

	public void afficher()
		{
		 NiveauDatabaseLayout niveau = null;
		 niveau = new NiveauDatabaseLayout();
		 try{
			 niveau.maxIndex();
			 if (niveau!=null)
				 areaID=niveau.model().getIdNiveau()+1;
			 
		 }
		 catch (Exception e) {
				e.printStackTrace();
			       
			     } finally {
			       niveau.close();
			     }
		}

}
