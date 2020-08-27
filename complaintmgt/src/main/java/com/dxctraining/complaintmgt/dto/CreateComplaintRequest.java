package com.dxctraining.complaintmgt.dto;

public class CreateComplaintRequest {
	
	private String description;
	private int consumerId;
	
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

}
