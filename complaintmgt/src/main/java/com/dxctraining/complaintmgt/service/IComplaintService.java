package com.dxctraining.complaintmgt.service;

import java.util.List;

import com.dxctraining.complaintmgt.entities.Complaint;

public interface IComplaintService {
	Complaint add(Complaint complaint);
	Complaint findComplaintById(int id);
	List<Complaint>listAll();
	List<Complaint>complaintsByConsumer(int consumerId);

}
