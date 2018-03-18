package com.inspectbox.lang;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

import com.inspectbox.web.UserDataBean;
public class CountryValueListener implements ValueChangeListener{
 
	@Override
	public void processValueChange(ValueChangeEvent event)
			throws AbortProcessingException {
 
		//access country bean directly
		UserDataBean country = (UserDataBean) FacesContext.getCurrentInstance().
			getExternalContext().getSessionMap().get("country");
 
		country.setLocale(event.getNewValue().toString());
 
	}
 
}