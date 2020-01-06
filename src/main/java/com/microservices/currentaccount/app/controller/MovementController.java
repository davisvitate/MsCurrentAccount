package com.microservices.currentaccount.app.controller;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.currentaccount.app.model.CurrentAccount;
import com.microservices.currentaccount.app.model.Movement;
import com.microservices.currentaccount.app.services.CurrentServiceImp;
import com.microservices.currentaccount.app.services.CurrentServices;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/current")
public class MovementController {

	@Autowired
	private CurrentServices service;

	@Autowired
	private CurrentServiceImp serviceclient;

	@GetMapping("/movement")
	public Mono<ResponseEntity<Flux<Movement>>> lista() {
		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(service.findAllMove()));
	}

	// withdrawal is done
	@PutMapping("/retire/{id}")
	public Mono<ResponseEntity<CurrentAccount>> updateretire(@RequestBody CurrentAccount CurrentAccount, @PathVariable String id) {

		Movement mov = new Movement();

		return service.findById(id).flatMap(c -> {
			double montoantes = c.getMonto();
			int num_mov_inicial = c.getNum_mov();
			if (montoantes >= CurrentAccount.getMonto()) {
				c.setMonto(montoantes - CurrentAccount.getMonto());
				c.setNum_mov(num_mov_inicial + 1);
				mov.setNum_count(CurrentAccount.getNum());
				mov.setDescription("Retire");
				mov.setSaldo(CurrentAccount.getMonto());
				mov.setDate(new Date());
				mov.setClient(CurrentAccount.getClientperson());
				mov.setType_account("current account");
				mov.setNum_mov(c.getNum_mov());
				if (c.getNum_mov() >= 4) {
					double comisionantes = c.getCommission();
					c.setCommission(comisionantes + 2);
					c.setMonto(c.getMonto() - 2);
				}
				service.saveMove(mov).subscribe();// registre of the movement

				serviceclient.saveMSMovement(mov).subscribe();// registre of the movement on the microservice
			}
			return service.save(c);
		}).map(c -> ResponseEntity.created(URI.create("/CurrentAccount/retire/".concat(c.getId())))
				.contentType(MediaType.APPLICATION_JSON_UTF8).body(c))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/retire/dni/{id}")
	public Mono<ResponseEntity<CurrentAccount>> updateretiredni(@RequestBody CurrentAccount CurrentAccount, @PathVariable String dni) {

		Movement mov = new Movement();

		return service.findByDniMono(dni).flatMap(c -> {
			double montoantes = c.getMonto();
			int num_mov_inicial = c.getNum_mov();
			if (montoantes >= CurrentAccount.getMonto()) {
				c.setMonto(montoantes - CurrentAccount.getMonto());
				c.setNum_mov(num_mov_inicial + 1);
				mov.setNum_count(CurrentAccount.getNum());
				mov.setDescription("Retire");
				mov.setSaldo(CurrentAccount.getMonto());
				mov.setDate(new Date());
				mov.setClient(CurrentAccount.getClientperson());
				mov.setType_account("current account");
				mov.setNum_mov(c.getNum_mov());
				if (c.getNum_mov() >= 4) {
					double comisionantes = c.getCommission();
					c.setCommission(comisionantes + 2);
					c.setMonto(c.getMonto() - 2);
				}
				service.saveMove(mov).subscribe();// registre of the movement

				serviceclient.saveMSMovement(mov).subscribe();// registre of the movement on the microservice
			}
			return service.save(c);
		}).map(c -> ResponseEntity.created(URI.create("/CurrentAccount/retire/dni/".concat(c.getId())))
				.contentType(MediaType.APPLICATION_JSON_UTF8).body(c))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	// deposit is made
	@PutMapping("/deposite/{id}")
	public Mono<ResponseEntity<CurrentAccount>> updeposit(@RequestBody CurrentAccount CurrentAccount, @PathVariable String id) {
		Movement mov = new Movement();
		return service.findById(id).flatMap(c -> {
			double montoantes = c.getMonto();
			int num_mov_inicial = c.getNum_mov();
			c.setMonto(montoantes + CurrentAccount.getMonto());
			c.setNum_mov(num_mov_inicial + 1);
			// c.setClientperson(CurrentAccount.getClientperson());
			mov.setNum_count(CurrentAccount.getNum());
			mov.setDescription("Deposite");
			mov.setSaldo(CurrentAccount.getMonto());
			mov.setDate(new Date());
			mov.setClient(CurrentAccount.getClientperson());
			mov.setType_account("current account");
			mov.setNum_mov(c.getNum_mov());
			if (c.getNum_mov() >= 4) {
				double comisionantes = c.getCommission();
				c.setCommission(comisionantes + 2);
				c.setMonto(c.getMonto() - 2);
			}
			service.saveMove(mov).subscribe();// deposite of the mevement
			serviceclient.saveMSMovement(mov).subscribe();
			return service.save(c);
		}).map(c -> ResponseEntity.created(URI.create("/CurrentAccount/deposite/".concat(c.getId())))
				.contentType(MediaType.APPLICATION_JSON_UTF8).body(c))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/deposite/dni/{id}")
	public Mono<ResponseEntity<CurrentAccount>> updepositdni(@RequestBody CurrentAccount CurrentAccount, @PathVariable String dni) {
		Movement mov = new Movement();
		return service.findByDniMono(dni).flatMap(c -> {
			double montoantes = c.getMonto();
			int num_mov_inicial = c.getNum_mov();
			c.setMonto(montoantes + CurrentAccount.getMonto());
			c.setNum_mov(num_mov_inicial + 1);
			// c.setClientperson(CurrentAccount.getClientperson());
			mov.setNum_count(CurrentAccount.getNum());
			mov.setDescription("Deposite");
			mov.setSaldo(CurrentAccount.getMonto());
			mov.setDate(new Date());
			mov.setClient(CurrentAccount.getClientperson());
			mov.setType_account("current account");
			mov.setNum_mov(c.getNum_mov());
			if (c.getNum_mov() >= 4) {
				double comisionantes = c.getCommission();
				c.setCommission(comisionantes + 2);
				c.setMonto(c.getMonto() - 2);
			}
			service.saveMove(mov).subscribe();// deposite of the mevement
			serviceclient.saveMSMovement(mov).subscribe();
			return service.save(c);
		}).map(c -> ResponseEntity.created(URI.create("/CurrentAccount/deposite/dni/".concat(c.getId())))
				.contentType(MediaType.APPLICATION_JSON_UTF8).body(c))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

}
