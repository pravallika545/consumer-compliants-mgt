package com.dxctraining.complaintmgt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dxctraining.complaintmgt.entities.Complaint;
import com.dxctraining.complaintmgt.exceptions.ComplaintNotFoundException;

@Repository
public class ComplaintDaoImpl implements IComplaintDao {
	
	@Autowired
	private EntityManager em;

	@Override
	public Complaint add(Complaint complaint) {
		em.persist(complaint);
		return complaint;
	}

	@Override
	public Complaint findComplaintById(int id) {
		Complaint complaint = em.find(Complaint.class, id);
		if(complaint == null) {
			throw new ComplaintNotFoundException("complaint is null");
		}
		
		return complaint;
	}

	@Override
	public List<Complaint> listAll() {
		String jpaql = "from Complaint";
		TypedQuery<Complaint> query = em.createQuery(jpaql, Complaint.class);
		List<Complaint> list = query.getResultList();
		return list;
	}

	@Override
	public List<Complaint> complaintsByConsumer(int consumerId) {
		String jpaql = "from Complaint where consumerId=:consumer";
		TypedQuery<Complaint>query = em.createQuery(jpaql, Complaint.class);
		query.setParameter("consumer", consumerId);
		List<Complaint> list = query.getResultList();
		return list;
	}

}
