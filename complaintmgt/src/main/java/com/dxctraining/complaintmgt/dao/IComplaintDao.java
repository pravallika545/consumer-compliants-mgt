package com.dxctraining.complaintmgt.dao;

import java.util.List;

import com.dxctraining.complaintmgt.entities.Complaint;

public interface IComplaintDao {
	Complaint add(Complaint complaint);
	Complaint findComplaintById(int id);
	List<Complaint>listAll();
	List<Complaint>complaintsByConsumer(int consumerId);

}
