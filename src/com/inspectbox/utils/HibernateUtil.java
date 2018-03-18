/*    */ package com.inspectbox.utils;
/*    */ 
/*    */ import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/*    */ 
/*    */ public class HibernateUtil
/*    */ {
/*    */   private static final SessionFactory sessionFactory;
/*    */   public static final ThreadLocal session;
/*    */ 
/*    */   static
/*    */   {
/*    */     try
/*    */     {
/* 16 */       sessionFactory = new Configuration().configure().buildSessionFactory();
/*    */     } catch (HibernateException ex) {
/* 18 */       throw new RuntimeException("Problème de configuration : " + ex.getStackTrace(), ex);
/*    */     }
/*    */ 
/* 22 */     session = new ThreadLocal(); }
/*    */ 
/*    */   public static Session currentSession() throws HibernateException {
/* 25 */     Session s = (Session)session.get();
/*    */ 
/* 28 */     s = sessionFactory.openSession();
/*    */ 
/* 31 */     return s;
/*    */   }
/*    */ 
/*    */   public static void refresh(Object objet) throws HibernateException {
/* 35 */     Session s = (Session)session.get();
/* 36 */     s.refresh(objet);
/*    */   }
/*    */ 
/*    */   public static void closeSession() throws HibernateException
/*    */   {
/* 41 */     Session s = (Session)session.get();
/*    */ 
/* 43 */     if (s != null)
/* 44 */       s.close();
/*    */   }
/*    */ }

/* Location:           C:\Users\Sébastien MARTIN\workspace\insp\inspectbox\WebRoot\WEB-INF\classes\
 * Qualified Name:     com.inspectbox.utils.HibernateUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.5.3
 */