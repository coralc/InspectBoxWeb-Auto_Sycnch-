/*    */ package com.inspectbox.utils;
/*    */ 
/*    */ import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.inspectbox.model.Niveau;
import com.inspectbox.model.Niveauobjet;
import com.inspectbox.objetLayout.ObjetNavigation;
/*    */ 
/*    */ public class ArianeUtil
/*    */ {
/*    */   public static List<ObjetNavigation> listNiveau(Niveau niv)
/*    */   {
/* 15 */     List listeAriane = new ArrayList();
/* 16 */     while (niv.getNiveau() != null)
/*    */     {
/* 18 */       listeAriane.add(new ObjetNavigation(niv.getLibelle(), niv.getIdNiveau()));
/* 19 */       niv = niv.getNiveau();
/*    */     }
/* 21 */     listeAriane.add(new ObjetNavigation(niv.getLibelle(), niv.getIdNiveau()));
/* 22 */     Collections.reverse(listeAriane);
/*    */ 
/* 24 */     return listeAriane;
/*    */   }
/*    */ 
/*    */   public static List<ObjetNavigation> listObjet(Niveauobjet niv)
/*    */   {
/* 30 */     List listeAriane = new ArrayList();
/* 31 */     while (niv.getNiveauobjet() != null)
/*    */     {
/* 33 */       listeAriane.add(new ObjetNavigation(niv.getLibelle(), niv.getIdNiveauObjet()));
/* 34 */       niv = niv.getNiveauobjet();
/*    */     }
/* 36 */     listeAriane.add(new ObjetNavigation(niv.getLibelle(), niv.getIdNiveauObjet()));
/* 37 */     Collections.reverse(listeAriane);
/*    */ 
/* 39 */     return listeAriane;
/*    */   }
/*    */ }

/* Location:           C:\Users\Sébastien MARTIN\workspace\insp\inspectbox\WebRoot\WEB-INF\classes\
 * Qualified Name:     com.inspectbox.utils.ArianeUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.5.3
 */