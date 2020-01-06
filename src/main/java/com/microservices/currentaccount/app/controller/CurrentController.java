package com.microservices.currentaccount.app.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.microservices.currentaccount.app.model.ClientPerson;
import com.microservices.currentaccount.app.model.CurrentAccount;
import com.microservices.currentaccount.app.services.CurrentServiceImp;
import com.microservices.currentaccount.app.services.CurrentServices;

import java.net.URI;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;




import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/current")
public class CurrentController {
	private static Logger log = LoggerFactory.getLogger(CurrentController.class);

	@Autowired
	private CurrentServices service;

	@Autowired
	private CurrentServiceImp serviceclient;

	@GetMapping
	public Mono<ResponseEntity<Flux<CurrentAccount>>> lista() {
		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(service.findAll()));
	}

	// see the list of savings accounts for id
	@GetMapping("/{id}")
	public Mono<ResponseEntity<CurrentAccount>> ver(@PathVariable String id) {
		return service.findById(id).map(p -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

//	@PostMapping
//	public Mono<CurrentAccount> create(@RequestBody CurrentAccount monoCurrentAccount){
//		String dni =monoCurrentAccount.getClientperson().getDni();
//		Mono<CurrentAccount> mono = null;
//		if( service.findClientPersonByDni(dni)!= null) {
//			 mono= service.save(monoCurrentAccount);			
//		}
//		return mono;
//			 
//									
//	}
	// creation of a savings account
	@PostMapping
	public Mono<CurrentAccount> create(@RequestBody CurrentAccount monoCurrentAccount) {
		ClientPerson client = new ClientPerson();
		client = monoCurrentAccount.getClientperson();
		client.setType("personal client");
		serviceclient.saveMSClient(client).subscribe();
		service.saveClientPerson(client).subscribe();
		return service.save(monoCurrentAccount);

	}

//	@PostMapping
//	public Mono<ResponseEntity<Map<String, Object>>> crear(@RequestBody Mono<CurrentAccount> monoProducto){
//		
//		Map<String, Object> respuesta = new HashMap<String, Object>();
//		
//		return monoProducto.flatMap(CurrentAccount->{
//			return service.save(CurrentAccount).map(c->{
//				respuesta.put("saving account", c);
//				respuesta.put("mensaje", "cuenta de ahoro creada con exito");
//				return ResponseEntity
//						.created(URI.create("/api/CurrentAccount/".concat(c.getId())))
//						.contentType(MediaType.APPLICATION_JSON_UTF8)
//						.body(respuesta);											
//			});
//		}).onErrorResume(t -> {
//			return Mono.just(t).cast(WebExchangeBindException.class)
//					.flatMap(e -> Mono.just(e.getFieldErrors()))
//					.flatMapMany(Flux::fromIterable)
//					.map(fieldError -> "El campo "+fieldError.getField() + " " + fieldError.getDefaultMessage())
//					.collectList()
//					.flatMap(list -> {
//						respuesta.put("errors", list);
//						respuesta.put("status", HttpStatus.BAD_REQUEST.value());
//						return Mono.just(ResponseEntity.badRequest().body(respuesta));
//					});						
//		});
//	}

//	public Mono<> create(@RequestBody Mono<CurrentAccount> monoProducto){
//		

	// edit the savings account
	@PutMapping("/{id}")
	public Mono<ResponseEntity<CurrentAccount>> upadate(@RequestBody CurrentAccount CurrentAccount, @PathVariable String id) {
		return service.findById(id).flatMap(c -> {
			c.setNum(CurrentAccount.getNum());
			c.setMonto(CurrentAccount.getMonto());
			c.setClientperson(CurrentAccount.getClientperson());
			return service.save(c);
		}).map(c -> ResponseEntity.created(URI.create("/current/".concat(c.getId())))
				.contentType(MediaType.APPLICATION_JSON_UTF8).body(c))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	// delete the savings account
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
		return service.findById(id).flatMap(p -> {
			return service.delete(p).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}

}
