package com.dxctraining.complaintmgt.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Complaint {
	
	@Id
	@GeneratedValue
	private int id;
	private String description;
	private int consumerId;
	
	public Complaint() {
		
	}
	
	public Complaint(String description, int consumerId) {
		this.description = description;
		this.consumerId = consumerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(int consumerId) {
		this.consumerId = consumerId;
	}
	
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}
		Complaint that = (Complaint)o;
		boolean isEquals = this.id == that.id;
		return isEquals;
	}

}
