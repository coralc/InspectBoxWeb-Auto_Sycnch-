package com.inspectbox.objetTree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.richfaces.model.TreeNode;
import org.richfaces.model.TreeNodeImpl;

import com.inspectbox.objetLayout.ObjetNiveau;

public class NiveauTree extends TreeNodeImpl implements Serializable {
	 private String type;
	 private String name;
	 
	 private ObjetNiveau data;
	 private List<NiveauTree> children = new ArrayList<NiveauTree>();	 
	 private NiveauTree parent;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ObjetNiveau getData() {
		return data;
	}

	public void setData(ObjetNiveau data) {
		this.data = data;
	}
	
	
	public NiveauTree() {
		super();		
	}
	
	public NiveauTree(String type) {
		super();
		this.type=type;
	}


	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = (NiveauTree) parent;
	}
	
	 public TreeNode getChildAt(int childIndex) {
	        return children.get(childIndex);
	    }
	 
	 public int getChildCount() {
	        return children.size();
	    }
	 

	 
	public List<NiveauTree> getChildren() {
		return children;
	}

	public void setChildren(List<NiveauTree> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return this.data.getLibelle();
	}

	
}
