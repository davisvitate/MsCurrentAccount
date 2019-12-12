package com.microservices.currentaccount.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="curerntaccount")
public class CurrentAccount {
	
	@Id
	private String id;
	private String num;
	private double monto;
	private ClientPerson clientperson;
	

}
