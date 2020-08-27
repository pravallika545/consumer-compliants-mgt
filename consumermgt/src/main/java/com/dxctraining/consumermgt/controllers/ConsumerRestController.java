package com.dxctraining.consumermgt.controllers;

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

import com.dxctraining.consumermgt.dto.ConsumerDto;
import com.dxctraining.consumermgt.dto.CreateConsumerRequest;
import com.dxctraining.consumermgt.entities.Consumer;
import com.dxctraining.consumermgt.service.IConsumerService;

@RestController
@RequestMapping("/consumer")
public class ConsumerRestController {
	
	@Autowired
	private IConsumerService service;
	
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public ConsumerDto addConsumer(@RequestBody CreateConsumerRequest requestData) {
		Consumer consumer = new Consumer(requestData.getName());
		service.add(consumer);
		ConsumerDto response = toDto(consumer);
		return response;
	}

	private ConsumerDto toDto(Consumer consumer) {
		ConsumerDto dto = new ConsumerDto();
		dto.setId(consumer.getId());
		dto.setName(consumer.getName());
		return dto;
	}
	
	@GetMapping("/get/{id}")
	@ResponseStatus(HttpStatus.FOUND)
	public ConsumerDto findConsumerById(@PathVariable("id")int id) {
		Consumer consumer = service.findConsumerById(id);
		ConsumerDto response = toDto(consumer);
		return response;
	}
	
	@GetMapping
	public List<ConsumerDto> fetchAll(){
		List<Consumer> list = service.allConsumers();
		List<ConsumerDto> response = new ArrayList<>();
		for(Consumer consumer:list) {
			ConsumerDto consumerDto = toDto(consumer);
			response.add(consumerDto);
		}
		return response;
	}

}
