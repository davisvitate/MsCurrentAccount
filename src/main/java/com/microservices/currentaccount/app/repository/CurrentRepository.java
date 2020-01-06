package com.microservices.currentaccount.app.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


import com.microservices.currentaccount.app.model.CurrentAccount;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrentRepository extends ReactiveMongoRepository<CurrentAccount, String> {
	
	@Query("{ 'num': ?0 }")
    Flux<CurrentAccount> findByNumCount(final String num);
	
	@Query("{ 'clientperson.dni': ?0 }")
    Flux<CurrentAccount> findByDniClient(final String dni);
	
	@Query("{ 'clientperson.dni': ?0 }")
    Mono<CurrentAccount> findByDniMono(final String dni);


}
