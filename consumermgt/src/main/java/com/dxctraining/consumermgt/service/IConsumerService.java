package com.dxctraining.consumermgt.service;

import java.util.List;

import com.dxctraining.consumermgt.entities.Consumer;

public interface IConsumerService {
	Consumer add(Consumer consumer);
	Consumer findConsumerById(int id);
	List<Consumer> allConsumers();

}
