package com.eng.gp.project.domain;

import java.io.Serializable;





public class Premises implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String description;
    private String timezone;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	

	
}
