/*    */ package com.inspectbox.web;
/*    */ 
/*    */ import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.StreamedContent;
/*    */ 
/*    */ @ManagedBean(name="homeBean")
/*    */ @RequestScoped
/*    */ public class HomeBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 46 */   private Integer numInitial = Integer.valueOf(0);
/*    */   private StreamedContent myImage;
/*    */ 
/*    */   public Integer getNumInitial()
/*    */   {
/* 50 */     return this.numInitial;
/*    */   }
/*    */ 
/*    */   public void setNumInitial(Integer numInitial) {
/* 54 */     this.numInitial = numInitial;
/*    */   }
/*    */ 
/*    */   public StreamedContent getMyImage()
/*    */   {
/* 59 */     return this.myImage;
/*    */   }
/*    */ 
/*    */   public void setMyImage(StreamedContent myImage) {
/* 63 */     this.myImage = myImage;
/*    */   }
/*    */ }

/* Location:           C:\Users\Sébastien MARTIN\workspace\insp\inspectbox\WebRoot\WEB-INF\classes\
 * Qualified Name:     com.inspectbox.web.HomeBean
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.5.3
 */