/*    */ package com.inspectbox.utils;
/*    */ 
/*    */ import java.util.UUID;

import sun.misc.BASE64Encoder;
/*    */ 
/*    */ public class UUIDUtil
/*    */ {
/*    */   public static String getID()
/*    */   {
/* 12 */     UUID uuid = UUID.randomUUID();
/* 13 */     String randomUUIDString = uuid.toString();
/*    */ 
/* 15 */     return randomUUIDString;
/*    */   }
/*    */ 
/*    */   public static String getBase64(String str)
/*    */   {
/* 22 */     BASE64Encoder encoder = new BASE64Encoder();
/* 23 */     String str2 = encoder.encode(str.getBytes());
/*    */ 
/* 25 */     return str2.replaceAll("[\r\n]+", "");
/*    */   }
/*    */ }

/* Location:           C:\Users\Sébastien MARTIN\workspace\insp\inspectbox\WebRoot\WEB-INF\classes\
 * Qualified Name:     com.inspectbox.utils.UUIDUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.5.3
 */