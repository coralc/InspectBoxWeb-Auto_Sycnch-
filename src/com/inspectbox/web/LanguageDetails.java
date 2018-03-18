package com.inspectbox.web;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
@ManagedBean (name="languageDetails")
@SessionScoped
public class LanguageDetails implements java.io.Serializable  {
	
	private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
private String lg="";
private String vis="";
private String vien="";
    public String getVien() {
	return vien;
}

public void setVien(String vien) {
	this.vien = vien;
}

	public String getVis() {
	return vis;
}

public void setVis(String vis) {
	this.vis = vis;
}

	public String getLg() {
	return lg;
}

public void setLg(String lg) {
	this.lg = lg;
}

	public Locale getLocale() {
    	System.out.println("iciget locale ****************"+ locale);
        return locale;
    }

    public String getLanguage() {
    	
        return locale.getLanguage();
    }

    public void setLanguage(String language) {
    	
        locale = new Locale(language);
        
        
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
       FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        FacesContext.getCurrentInstance().getExternalContext().getRequestPathInfo();
    }
    public void languagechen()
    {
    	
    	this.setLanguage("en");
    }
    public void languagechfr()
    {
    	
    	this.setLanguage("fr");
    }
    public void languagechde()
    {
    	
    	this.setLanguage("de");
    }
    public void lister()
    {
    	
    	this.lg=locale.toString();
    	
    	System.out.println("ici lister lg ****************"+lg);
    	if (lg.equals("en"))
    	this.vis="none";
    	else
    		this.vis="block";
    	if (lg.equals("fr"))
        	this.vien="none";
        	else
        		this.vien="block";
    }

}
