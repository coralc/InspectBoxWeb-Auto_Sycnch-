/*    */ package com.inspectbox.utils;
/*    */ 
/*    */ import java.security.MessageDigest;

import javax.faces.context.FacesContext;

import com.inspectbox.web.LoginBean;
/*    */ 
/*    */ public class LoginUtil
/*    */ {
/*    */   public static LoginBean getLoginBean()
/*    */   {
/* 13 */     FacesContext fc = FacesContext.getCurrentInstance();
/* 14 */     LoginBean bean = (LoginBean)fc.getExternalContext().getSessionMap().get("loginBean");
/* 15 */     return bean;
/*    */   }
/*    */ 
/*    */   public static String toSHA1(byte[] convertme) throws Exception {
/* 19 */     MessageDigest md = null;
/*    */     try {
/* 21 */       md = MessageDigest.getInstance("SHA-1");
/*    */     }
/*    */     catch (Exception e) {
/* 24 */       e.printStackTrace();
/*    */     }
/* 26 */     byte[] b = md.digest(convertme);
/*    */ 
/* 28 */     String result = "";
/* 29 */     for (int i = 0; i < b.length; ++i) {
/* 30 */       result = result + 
/* 31 */         Integer.toString((b[i] & 0xFF) + 256, 16).substring(1);
/*    */     }
/* 33 */     return result;
/*    */   }
/*    */ }

