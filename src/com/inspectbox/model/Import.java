 package com.inspectbox.model;
 
 import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
 
 @Entity
 @Table(name="import", catalog="inspectboxsa")
 public class Import
   implements Serializable
 {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Integer idImport;
  private String filename;
  private Integer status;
  private Date timestamp;
   public Import()
   {
   }
 
   public Import(String filename, int status, Date clefTimestamp) {
     this.filename=filename;
     this.status=status;
    this.timestamp = clefTimestamp;
   }
 
@Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="idImport", unique=true, nullable=false)
public Integer getIdImport() {
	return idImport;
}

public void setIdImport(Integer idImport) {
	this.idImport = idImport;
}
@Column(name="filename", nullable=false)
   public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
 
  
   @Column(name="status", nullable=false)
   public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
 
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="timestamp", nullable=false, length=19)
   public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
 }
