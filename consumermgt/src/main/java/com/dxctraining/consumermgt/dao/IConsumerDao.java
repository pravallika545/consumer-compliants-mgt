package com.dxctraining.consumermgt.dao;

import java.util.List;

import com.dxctraining.consumermgt.entities.Consumer;

public interface IConsumerDao {
	Consumer add(Consumer consumer);
	Consumer findConsumerById(int id);
	List<Consumer> allConsumers();

}
