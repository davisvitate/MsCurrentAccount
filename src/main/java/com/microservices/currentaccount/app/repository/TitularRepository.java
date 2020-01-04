package com.microservices.currentaccount.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.microservices.currentaccount.app.model.Titular;



public interface TitularRepository extends ReactiveMongoRepository<Titular, String>{

}
