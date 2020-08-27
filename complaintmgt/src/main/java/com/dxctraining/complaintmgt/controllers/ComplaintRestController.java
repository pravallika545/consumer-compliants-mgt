package com.dxctraining.complaintmgt.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.dxctraining.complaintmgt.dto.ComplaintDto;
import com.dxctraining.complaintmgt.dto.ConsumerDto;
import com.dxctraining.complaintmgt.dto.CreateComplaintRequest;
import com.dxctraining.complaintmgt.entities.Complaint;
import com.dxctraining.complaintmgt.service.IComplaintService;
import com.dxctraining.complaintmgt.util.ComplaintUtil;

@RestController
@RequestMapping("/complaint")
public class ComplaintRestController {
	
	@Autowired
	private IComplaintService service;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ComplaintUtil complaintUtil;
	
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public ComplaintDto registerComplaint(@RequestBody CreateComplaintRequest requestData) {
		Complaint complaint = new Complaint(requestData.getDescription(),requestData.getConsumerId());
		service.add(complaint);
		ConsumerDto consumerDto = fetchFromConsumerById(requestData.getConsumerId());
		ComplaintDto response = complaintUtil.complaintDto(complaint, consumerDto.getId(), consumerDto.getName());
		return response;
	}
	
	@GetMapping("/get/{id}")
	@ResponseStatus(HttpStatus.FOUND)
	public ComplaintDto getComplaint(@PathVariable("id")int id) {
		Complaint complaint = service.findComplaintById(id);
		int consumerId = complaint.getConsumerId();
		ConsumerDto consumerDto = fetchFromConsumerById(consumerId);
		ComplaintDto response = complaintUtil.complaintDto(complaint, consumerId, consumerDto.getName());
		return response;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.FOUND)
	public List<ComplaintDto> fetchAll(){
		List<Complaint> list = service.listAll();
		List<ComplaintDto> response = new ArrayList<>();
		for(Complaint complaint:list) {
			int consumerId = complaint.getConsumerId();
			ConsumerDto consumerDto = fetchFromConsumerById(consumerId);
			ComplaintDto complaintDto = complaintUtil.complaintDto(complaint, consumerId, consumerDto.getName());
			response.add(complaintDto);
		}
		return response;
	}
	
	@GetMapping("/consumer/{consumerId}")
@ResponseStatus(HttpStatus.FOUND)
	public List<ComplaintDto> fetchAllComplaintsByConsumer(@PathVariable("consumerId")int consumerId){
		List<Complaint> list = service.complaintsByConsumer(consumerId);
		List<ComplaintDto> response = new ArrayList<>();
		ConsumerDto consumerDto = fetchFromConsumerById(consumerId);
		for(Complaint complaint:list) {
			ComplaintDto complaintDto = complaintUtil
					.complaintDto(complaint, consumerId, consumerDto.getName());
			response.add(complaintDto);
		}
		return response;
	}

	private ConsumerDto fetchFromConsumerById(int consumerId) {
		String url = "http://localhost:8585/consumer/get/"+consumerId;
		ConsumerDto dto = restTemplate.getForObject(url, ConsumerDto.class);
		return dto;
	}

}
