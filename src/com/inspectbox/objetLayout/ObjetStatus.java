package com.inspectbox.objetLayout;

import java.io.Serializable;

public class ObjetStatus implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Integer idStatus;
public Integer getIdStatus() {
	return idStatus;
}
public void setIdStatus(Integer idStatus) {
	this.idStatus = idStatus;
}
public String getLabel() {
	return label;
}
public void setLabel(String label) {
	this.label = label;
}
private String label;

}
