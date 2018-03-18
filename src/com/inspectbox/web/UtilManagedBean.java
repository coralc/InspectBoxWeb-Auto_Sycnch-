package com.inspectbox.web;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
 
@ManagedBean(name="UtilManagedBean")
@SessionScoped
public class UtilManagedBean {
 
    /** Creates a new instance of UtilManagedBean */
    public UtilManagedBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        setLocale(request.getLocale().toString());
        System.out.println("Default Locale is : " + Locale.getDefault());
    }
    Locale loc;
 
    public Locale getLoc() {
        return loc;
    }
 
    public void setLoc(Locale loc) {
        this.loc = loc;
    }
    private String locale;
 
    public void setLocale(String locale) {
        this.locale = locale;
        System.out.println("Locale = " + locale);
 
        int last = locale.lastIndexOf("_");
        String lang = locale.substring(0, last);
        String country = locale.substring(last + 1);
 
        loc = new Locale(lang, country);
        setBid(ResourceBundle.getBundle("test", loc));
    }
 
    public String getLocale() {
        return locale;
    }
    ResourceBundle bid;
 
    public ResourceBundle getBid() {
        return bid;
    }
 
    public void setBid(ResourceBundle bid) {
        this.bid = bid;
    }
 
    public String changeLanguage() {
        ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
        String srt = ext.getRequestPathInfo() + "?faces-redirect=true;";
        return srt;
    }
}