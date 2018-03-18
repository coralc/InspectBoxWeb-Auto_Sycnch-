package com.inspectbox.web;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name = "userDataBean")
@SessionScoped
public class UserDataBean implements Serializable {

   private static final long serialVersionUID = 1L;
   private String locale;

   private static Map<String,Object> countries;
   
   static{
      countries = new LinkedHashMap<String,Object>();
     
      countries.put("French", Locale.FRENCH);
      countries.put("English", Locale.ENGLISH);
   }

   public Map<String, Object> getCountries() {
      return countries;
   }

   public String getLocale() {
      return locale;
   }

   public void setLocale(String locale) {
      this.locale = locale;
   }

   //value change event listener
   public String changeLanguage() {
       ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
       String srt = ext.getRequestPathInfo() + "?faces-redirect=true;";
       return srt;
   }
   public void localeChanged(ValueChangeEvent e){
	   System.out.println("******newLocaleValue***");
      String newLocaleValue = e.getNewValue().toString();
      System.out.println("******newLocaleValue"+newLocaleValue);
      for (Map.Entry<String, Object> entry : countries.entrySet()) {
         if(entry.getValue().toString().equals(newLocaleValue)){
            FacesContext.getCurrentInstance()
               .getViewRoot().setLocale((Locale)entry.getValue());         
         }
      }
   }
}