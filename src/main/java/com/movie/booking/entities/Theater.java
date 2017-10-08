package com.movie.booking.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Theater {

	@Id
	private String id;
	
	private String name;
	
	private String location;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="theater")
	private Set<Screen> screens = new HashSet<Screen>();

	public Theater( ) {
		
	}
	
	public Theater(String id) {
		super();
		this.id = id;
	}

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Set<Screen> getScreens() {
		return screens;
	}

	public void setScreens(Set<Screen> screens) {
		this.screens = screens;
	}

	@Override
	public String toString() {
		return "Theater [id=" + id + ", name=" + name + ", location=" + location + ", screens=" + screens + "]";
	}
	
}
