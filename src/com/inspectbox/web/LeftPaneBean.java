package com.inspectbox.web;
 
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
 @ManagedBean(name="leftPaneBean")
 @SessionScoped
 public class LeftPaneBean
   implements Serializable
 {
/*	    private TreeNode root;      
	    private TreeNode[] selectedNodes;  
	    

		HashMap<Integer, ArrayList<Integer>> parentChildrenMap;
		HashMap<Integer, String> niveauLibelleMap;
		
		public TreeNode getRoot() {
	    	if (root==null){
	    		prepareTree();
	    	}
	        return root;  
	    }  
		   public void prepareTree(){
		    	NiveauDatabaseLayout niveauDB = null; 		
		     	try{
		     	HashMap<Integer, Integer> childParentMap= new HashMap<Integer, Integer>();
//		    	HashMap<Integer, ObjetNiveau> niveauMap= new HashMap<Integer, ObjetNiveau>();
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
				 this.root = new DefaultTreeNode("Root", null);		
			     TreeNode node0 = new DefaultTreeNode("Tout les niveau", this.root);
			     System.out.println("******************root.isSelectable(): "+root.isSelectable());
			     System.out.println("******************node0.isSelectable(): "+node0.isSelectable());
//				 addNodes(siteList, root);
			     addNodes(siteList, node0);
			}	


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
*/			
			private TreeNode root;  
			  
		    private TreeNode[] selectedNodes;  
		  
		    public LeftPaneBean() {  
		        root = new DefaultTreeNode("Root", null);  
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
		  
		        TreeNode node100 = new DefaultTreeNode("Node 1.0.0", node10);  
		    }  
		  
		    public TreeNode getRoot() {  
		        return root;  
		    }  
		  
		    public TreeNode[] getSelectedNodes() {  
		        return selectedNodes;  
		    }  
		  
		    public void setSelectedNodes(TreeNode[] selectedNodes) {  
		        this.selectedNodes = selectedNodes;  
		    }  
		  
		    public void displaySelectedMultiple(ActionEvent event) {  
		        if(selectedNodes != null && selectedNodes.length > 0) {  
		            StringBuilder builder = new StringBuilder();  
		  
		            for(TreeNode node : selectedNodes) {  
		                builder.append(node.getData().toString());  
		                builder.append("<br />");  
		            }  
		  
		            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", builder.toString());  
		  
		            FacesContext.getCurrentInstance().addMessage(null, message);  
		        }  
		    }  
		   
 }