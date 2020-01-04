package com.microservices.currentaccount.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.microservices.currentaccount.app.model.Movement;



public interface MoveRepository extends ReactiveMongoRepository<Movement, String> {
	
	

}
