package com.microservices.currentaccount.app.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;





@Document(collection="clientpersoninfo")
public class ClientPersonInfo {
	
	@Id
	private String id;

	@Field("name")
	private String name;

	@Field("lastname")
	private String lastname;

	@Field("dni")
	private String dni;

	@Field("type")
	private String type;

	
	private String num_current;

	



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


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getNum_current() {
		return num_current;
	}


	public void setNum_current(String num_current) {
		this.num_current = num_current;
	}


	
	

}
