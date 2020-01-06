package com.microservices.currentaccount.app.services;




import java.util.Map;


import com.microservices.currentaccount.app.model.ClientPerson;
import com.microservices.currentaccount.app.model.CurrentAccount;
import com.microservices.currentaccount.app.model.Firmante;
import com.microservices.currentaccount.app.model.Movement;
import com.microservices.currentaccount.app.model.Titular;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrentServices {

	public Flux<CurrentAccount> findAll(); 
	
	public Mono<CurrentAccount> findById(String id); 
	
	public Mono<CurrentAccount> findByDniMono(String dni);
	
	public Mono<CurrentAccount> save(CurrentAccount CurrentAccount);
	
	public Mono<Void> delete(CurrentAccount CurrentAccount);
	
	public Flux<CurrentAccount> findByDniClient(String dni);
	
	public Mono<Map<String, Object>> getMoney(String dni);
	
	
	public Flux<ClientPerson> findAllClientPerson();
	
	public Mono<ClientPerson> findClientPersonById(String id);
	
	public Flux<ClientPerson> findClientPersonByDni(String dni);
	
	public Flux<ClientPerson> findClientPersonByName(String Name);
	
	public Flux<ClientPerson> findClientPersonByLastname(String lastname);
	
	public Mono<ClientPerson> saveClientPerson(ClientPerson clientperson);
	
	public Mono<Void> deleteClientPerson(String idCliente);
	

	public Flux<Titular> findAllTitular();

	public Mono<Titular> findByIdTitular(String id);

	public Mono<Titular> saveTitular(Titular titular);

	public Mono<Void> deleteTitular(Titular titular);
	

	public Flux<Firmante> findAllFirmante();

	public Mono<Firmante> findByIdFirmante(String id);

	public Mono<Firmante> saveFirmante(Firmante firmante);

	public Mono<Void> deleteFirmante(Firmante firmante);
	

	public Flux<Movement> findAllMove();

	public Mono<Movement> findByIdMove(String id);

	public Mono<Movement> saveMove(Movement move);

	public Mono<Void> deleteMove(Movement move);

}
