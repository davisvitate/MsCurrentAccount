package com.microservices.currentaccount.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="clientperson")
public class ClientPerson {
	
	@Id
	private String id;
	private String name;
	private String lastnam;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastnam() {
		return lastnam;
	}
	public void setLastnam(String lastnam) {
		this.lastnam = lastnam;
	}
	
	

}
