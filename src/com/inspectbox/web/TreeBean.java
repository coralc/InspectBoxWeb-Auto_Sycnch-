package com.inspectbox.web;
import java.io.Serializable;  
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import javax.faces.application.FacesMessage;  
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;  
import javax.faces.event.ActionEvent;  

import org.primefaces.model.DefaultTreeNode;  
import org.primefaces.model.TreeNode;

import com.inspectbox.databaseLayout.NiveauDatabaseLayout;
import com.inspectbox.databaseLayout.NiveauObjetDatabaseLayout;
import com.inspectbox.model.Niveau;
import com.inspectbox.model.Niveauobjet;
import com.inspectbox.objetLayout.ObjetNiveau;
import com.inspectbox.objetLayout.ObjetNiveauObjet;
import com.inspectbox.objetLayout.ObjetNiveauTree;
import com.inspectbox.utils.LoginUtil;

@ManagedBean(name="treeBean")
@ViewScoped
public class TreeBean implements Serializable {
    private TreeNode root;  
    
    private TreeNode[] selectedNodes;  
    
    private TreeNode rootNiveauObjet;  
    private TreeNode[] selectedNodesNiveauObjet;  
    
	HashMap<Integer, ArrayList<Integer>> parentChildrenMap;
	HashMap<Integer, String> niveauLibelleMap;

	HashMap<Integer, ArrayList<Integer>> parentChildrenNiveauObjetMap;
	HashMap<Integer, String> niveauObjetLibelleMap;

    public TreeBean() {  
 /*       root = new DefaultTreeNode("Root", null);  
        TreeNode node0 = new DefaultTreeNode("Node 0", root);  
        TreeNode node1 = new DefaultTreeNode("Node 1", root);  
        TreeNode node2 = new DefaultTreeNode("Node 2", root);  
  
        TreeNode node00 = new DefaultTreeNode("Node 0.0", node0);  
        TreeNode node01 = new DefaultTreeNode("Node 0.1", node0);  
  
        TreeNode node10 = new DefaultTreeNode("Node 1.0", node1);  
        TreeNode node11 = new DefaultTreeNode("Node 1.1", node1);  
  
        TreeNode node000 = new DefaultTreeNode("Node 0.0.0", node00);  
        TreeNode node001 = new DefaultTreeNode("Node 0.0.1", node00);  
        TreeNode node010 = new DefaultTreeNode("Node 0.1.0", node01);  
  
        TreeNode node100 = new DefaultTreeNode("Node 1.0.0", node10);  */
    }  
  
    public TreeNode getRoot() {
    	if (root==null){
    		prepareTree();
    	}    		
        return root;  
    }  
 
    public TreeNode getRootNiveauObjet() {
    	if (rootNiveauObjet==null){
    		prepareNiveauObjetTree();
    	}    		
        return rootNiveauObjet;  
    }  

    public void prepareTree(){
    	NiveauDatabaseLayout niveauDB = null; 		
     	try{
     	HashMap<Integer, Integer> childParentMap= new HashMap<Integer, Integer>();
//    	HashMap<Integer, ObjetNiveau> niveauMap= new HashMap<Integer, ObjetNiveau>();
    	niveauLibelleMap= new HashMap<Integer, String>();
    	parentChildrenMap= new HashMap<Integer, ArrayList<Integer>>();
    	ArrayList<Integer> childrenList;
    	ArrayList<Integer> siteList= new ArrayList<Integer>();
    	LoginBean bean = LoginUtil.getLoginBean();

    	niveauDB = new NiveauDatabaseLayout();
    	niveauDB.transactionOpen();
    	 
    	niveauDB.listerForTree(bean.getIdClient());
    	Iterator itNiv = niveauDB.liste().iterator();
    	 while (itNiv.hasNext()){
    		 Niveau niv = (Niveau)itNiv.next();
    		 Integer niveauId = niv.getIdNiveau();
    		 Integer parentId = niv.getParentId();
    		 String niveauLibelle =niv.getLibelle();
    		// System.out.println("niv.getParentId()"+niv.getParentId());
    		 if (!childParentMap.containsKey(niv.getIdNiveau())){
    			 childParentMap.put(niveauId, parentId);
    		 }
    		 if (!niveauLibelleMap.containsKey(niv.getIdNiveau())){
    			 niveauLibelleMap.put(niveauId, niveauLibelle);
    		 }
    		 if (parentId!=null){
	    		 if (!parentChildrenMap.containsKey(parentId)){
	    			 childrenList = new ArrayList<Integer>();
	    		 } else{
	    			 childrenList = parentChildrenMap.get(parentId);
	    		 }
	    		 childrenList.add(niveauId);
				 parentChildrenMap.put(parentId, childrenList);
	    	} else{
	    		siteList.add(niveauId);
	    	}
		 }
    	// Collections.sort(siteList);
   	 prepareNodes(siteList);
    	}catch (Exception e){
    		e.printStackTrace();
    	}finally {
    		if (niveauDB!=null){
    			niveauDB.close();
    		}
    	}
 
    	}

	public void prepareNodes(ArrayList<Integer> siteList){
		 root = new DefaultTreeNode("Root", null);		
		 addNodes(siteList, root);
	}	

/*
 * 	public void addNodesV1( ArrayList<Integer> children, TreeNode parentNode){
		if (children==null)
			return;
		for (int j=0; j<children.size(); j++){
			Integer niveauId=children.get(j);
			TreeNode siteNode = new DefaultTreeNode(niveauLibelleMap.get(niveauId), parentNode); 
			//ObjetNiveauTree siteNode = new ObjetNiveauTree();
//			siteNode.setData(niveauLibelleMap.get(niveauId));
//			siteNode.setType("niveau");
//			parentNode.addChild(niveauId, siteNode);
			ArrayList<Integer> newchildren=parentChildrenMap.get(niveauId);
			addNodesV1(newchildren, siteNode);
		}		
	} 
 **
 *
 */ 
	
	
	public void addNodes( ArrayList<Integer> children, TreeNode parentNode){
		if (children==null)
			return;
		for (int j=0; j<children.size(); j++){
			Integer niveauId=children.get(j);
			ObjetNiveau niveau= new ObjetNiveau();
			niveau.setLibelle(niveauLibelleMap.get(niveauId));
			niveau.setIdNiveau(niveauId);
			TreeNode siteNode = new DefaultTreeNode(niveau, parentNode); 
			ArrayList<Integer> newchildren=parentChildrenMap.get(niveauId);
			addNodes(newchildren, siteNode);
		}		
	}
  
    public void prepareNiveauObjetTree(){
    	NiveauObjetDatabaseLayout niveauObjetDB = null; 		
     	try{
     	HashMap<Integer, Integer> childParentMap= new HashMap<Integer, Integer>();
//    	HashMap<Integer, ObjetNiveau> niveauMap= new HashMap<Integer, ObjetNiveau>();
    	niveauObjetLibelleMap= new HashMap<Integer, String>();
    	parentChildrenNiveauObjetMap= new HashMap<Integer, ArrayList<Integer>>();
    	ArrayList<Integer> childrenList;
    	ArrayList<Integer> siteList= new ArrayList<Integer>();
    	LoginBean bean = LoginUtil.getLoginBean();

    	niveauObjetDB = new NiveauObjetDatabaseLayout();
    	niveauObjetDB.transactionOpen();
    	 
    	niveauObjetDB.listerAll(bean.getIdClient());
    	Iterator itNiv = niveauObjetDB.liste().iterator();
    	 while (itNiv.hasNext()){
    		 Niveauobjet nivObjet = (Niveauobjet)itNiv.next();
    		 Integer niveauObjetId = nivObjet.getIdNiveauObjet();
    		 Integer parentId = nivObjet.getParentId();
    		 String niveauLibelle =nivObjet.getLibelle();
    		 if (!childParentMap.containsKey(nivObjet.getIdNiveauObjet())){
    			 childParentMap.put(niveauObjetId, parentId);
    		 }
    		 if (!niveauObjetLibelleMap.containsKey(nivObjet.getIdNiveauObjet())){
    			 niveauObjetLibelleMap.put(niveauObjetId, niveauLibelle);
    		 }
    		 if (parentId!=null){
	    		 if (!parentChildrenNiveauObjetMap.containsKey(parentId)){
	    			 childrenList = new ArrayList<Integer>();
	    		 } else{
	    			 childrenList = parentChildrenNiveauObjetMap.get(parentId);
	    		 }
	    		 childrenList.add(niveauObjetId);
	    		 parentChildrenNiveauObjetMap.put(parentId, childrenList);
	    	} else{
	    		siteList.add(niveauObjetId);
	    	}
		 }
    	 Collections.sort(siteList);
   	 prepareNodesForNiveauObjet(siteList);
    	}catch (Exception e){
    		e.printStackTrace();
    	}finally {
    		if (niveauObjetDB!=null){
    			niveauObjetDB.close();
    		}
    	}
  	
    }
    
	public void prepareNodesForNiveauObjet(ArrayList<Integer> siteList){
		 rootNiveauObjet = new DefaultTreeNode("Root", null);		
		 addNodesForNiveauObjet(siteList, rootNiveauObjet);
	}	
	
	public void addNodesForNiveauObjet( ArrayList<Integer> children, TreeNode parentNode){
		if (children==null)
			return;
		for (int j=0; j<children.size(); j++){
			Integer niveauObjetId=children.get(j);
			ObjetNiveauObjet niveauObjet= new ObjetNiveauObjet();
			niveauObjet.setLibelle(niveauObjetLibelleMap.get(niveauObjetId));
			niveauObjet.setIdNiveauObjet(niveauObjetId);
			TreeNode siteNode = new DefaultTreeNode(niveauObjet, parentNode); 
			ArrayList<Integer> newchildren=parentChildrenNiveauObjetMap.get(niveauObjetId);
			addNodesForNiveauObjet(newchildren, siteNode);
		}		
	}

    public TreeNode[] getSelectedNodes() {  
        return selectedNodes;  
    }  
  
    public void setSelectedNodes(TreeNode[] selectedNodes) {  
        this.selectedNodes = selectedNodes;  
    }  
  
    public void displaySelectedMultiple(ActionEvent event) {
    	System.out.println("***************it is coming here");
    	System.out.println("*************** selectedNodes"+selectedNodes.length);
    	
        if(selectedNodes != null && selectedNodes.length > 0) {  
            StringBuilder builder = new StringBuilder();  
  
            for(TreeNode node : selectedNodes) {
            	System.out.println("***************node.getData().toString()"+node.getData().toString());
                builder.append(node.getData().toString());  
                builder.append("<br />");  
            }  
  
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", builder.toString());  
  
            FacesContext.getCurrentInstance().addMessage(null, message);  
        }  
    }

	public TreeNode[] getSelectedNodesNiveauObjet() {
		return selectedNodesNiveauObjet;
	}

	public void setSelectedNodesNiveauObjet(TreeNode[] selectedNodesNiveauObjet) {
		this.selectedNodesNiveauObjet = selectedNodesNiveauObjet;
	}
    
    
    
}
