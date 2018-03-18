/*    */ package com.inspectbox.utils;
/*    */ 
/*    */ import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
/*    */ 
/*    */ public class I18nUtil
/*    */ {
/*    */   public static String get(String key)
/*    */   {
/* 11 */     FacesContext context = FacesContext.getCurrentInstance();
/* 12 */     ResourceBundle bundle = ResourceBundle.getBundle("com.inspectbox.lang.messages", context.getViewRoot().getLocale());
/* 13 */     return bundle.getString(key);
/*    */   }
/*    */ }

/* Location:           C:\Users\Sébastien MARTIN\workspace\insp\inspectbox\WebRoot\WEB-INF\classes\
 * Qualified Name:     com.inspectbox.utils.I18nUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.5.3
 */