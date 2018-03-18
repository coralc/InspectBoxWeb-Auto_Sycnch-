package com.inspectbox.web;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
 
@ManagedBean(name="language")
@SessionScoped
public class LanguageBean implements Serializable{
 
	private static final long serialVersionUID = 1L;
 
	private String localeCode;
 
	private static Map<String,Object> countries;
	static{
		countries = new LinkedHashMap<String,Object>();
		 countries.put("French", Locale.FRENCH);
		countries.put("English", Locale.ENGLISH); //label, value
		
	}
 
	public Map<String, Object> getCountriesInMap() {
		return countries;
	}
 
 
	public String getLocaleCode() {
		return localeCode;
	}
 
 
	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}
	public void countryLocaleCodeChanged2()
	{
		System.out.println("***ici vide");
	}
	//value change event listener
	public void countryLocaleCodeChanged(ValueChangeEvent e){
 System.out.println("***ici"+e);
		String newLocaleValue = e.getNewValue().toString();
 
		//loop country map to compare the locale code
                for (Map.Entry<String, Object> entry : countries.entrySet()) {
 
        	   if(entry.getValue().toString().equals(newLocaleValue)){
 
        		FacesContext.getCurrentInstance()
        			.getViewRoot().setLocale((Locale)entry.getValue());
 
        	  }
               }
	}
	public class LocaleChangeListener implements ValueChangeListener {
		   @Override
		   public void processValueChange(ValueChangeEvent event)
		      throws AbortProcessingException {
		     //access country bean directly
			   System.out.println("***ici yrdy");
			   LanguageBean userData = (LanguageBean) FacesContext.getCurrentInstance().
		        getExternalContext().getSessionMap().get("language"); 
		     userData.setLocaleCode(event.getNewValue().toString());
		   }
		}
}