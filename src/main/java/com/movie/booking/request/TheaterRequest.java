package com.movie.booking.request;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TheaterRequest {

	private String id;
	
	private String name;

	private String location;
	
	private List<ScreenRequest> screens = new ArrayList<ScreenRequest>();

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<ScreenRequest> getScreens() {
		return screens;
	}

	public void setScreens(List<ScreenRequest> screens) {
		this.screens = screens;
	}
}
