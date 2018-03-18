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
 @Table(name="csvcount",  catalog="inspectboxsa")
 public class Csvcount
   implements Serializable
 {
   private Integer count;
   
   private Client client;
  
 
   public Csvcount()
   {
   }
 
   public Csvcount( Client client, int count) {
     this.client = client;
     this.count=count;
   }
 
  
 
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="count", unique=true, nullable=false)
   public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	 
 
   
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="idClient", nullable=false)
   public Client getClient() {
    return this.client;
   }
 
   public void setClient(Client client) {
     this.client = client;
   }


 }
