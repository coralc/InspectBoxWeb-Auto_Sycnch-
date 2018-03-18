package com.inspectbox.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class SynchPK implements Serializable {
	private String idTerminal;
	private Integer idClient;
	
	public SynchPK() {}

    public SynchPK(String idTerminal, Integer idClient) {
        this.idTerminal = idTerminal;
        this.idClient = idClient;
    }
	
	
    @Column(name="idTerminal")
	public String getIdTerminal() {
		return idTerminal;
	}
	public void setIdTerminal(String idTerminal) {
		this.idTerminal = idTerminal;
	}
	@Column(name="idClient")
	public Integer getIdClient() {
		return idClient;
	}
	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idClient == null) ? 0 : idClient.hashCode());
		result = prime * result
				+ ((idTerminal == null) ? 0 : idTerminal.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SynchPK other = (SynchPK) obj;
		if (idClient == null) {
			if (other.idClient != null)
				return false;
		} else if (!idClient.equals(other.idClient))
			return false;
		if (idTerminal == null) {
			if (other.idTerminal != null)
				return false;
		} else if (!idTerminal.equals(other.idTerminal))
			return false;
		return true;
	}
}
