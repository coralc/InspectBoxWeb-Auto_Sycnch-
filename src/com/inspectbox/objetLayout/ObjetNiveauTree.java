package com.inspectbox.objetLayout;

import java.io.Serializable;

import org.richfaces.model.TreeNodeImpl;

public class ObjetNiveauTree extends TreeNodeImpl implements Serializable {
	private boolean expanded;
	private String type;
	/*	private ObjetNiveau data;
	
	public ObjetNiveau getData() {
		return data;
	}

	public void setData(ObjetNiveau data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return this.data.getLibelle();
	}
*/	
	private boolean checked;
	public boolean isExpanded()
	{
	    return expanded;
	}

	public void setExpanded(boolean expanded)
	{
	    this.expanded = expanded;
	}
	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		return this.data;
	}	
	
}
