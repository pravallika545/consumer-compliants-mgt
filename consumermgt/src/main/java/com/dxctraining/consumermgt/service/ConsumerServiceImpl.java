package com.dxctraining.consumermgt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dxctraining.consumermgt.dao.IConsumerDao;
import com.dxctraining.consumermgt.entities.Consumer;
import com.dxctraining.consumermgt.exceptions.ConsumerNotFoundException;
import com.dxctraining.consumermgt.exceptions.InvalidConsumerArgumentException;

@Transactional
@Service
public class ConsumerServiceImpl implements IConsumerService {
	
	@Autowired
	private IConsumerDao dao;

	@Override
	public Consumer add(Consumer consumer) {
		validateConsumer(consumer);
		consumer = dao.add(consumer);
		return consumer;
	}

	private void validateConsumer(Consumer consumer) {
		if(consumer == null) {
			throw new ConsumerNotFoundException("consumer should not be null");
		}
		
	}

	@Override
	public Consumer findConsumerById(int id) {
		validateId(id);
		Consumer consumer = dao.findConsumerById(id);
		return consumer;
	}

	private void validateId(int id) {
		if(id == 0) {
			throw new InvalidConsumerArgumentException("id should not be null");
		}
		
	}

	@Override
	public List<Consumer> allConsumers() {
		List<Consumer>list = dao.allConsumers();
		return list;
	}
	

}
