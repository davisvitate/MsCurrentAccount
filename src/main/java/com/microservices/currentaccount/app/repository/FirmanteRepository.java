package com.microservices.currentaccount.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.microservices.currentaccount.app.model.Firmante;


public interface FirmanteRepository extends ReactiveMongoRepository<Firmante, String>{

}
