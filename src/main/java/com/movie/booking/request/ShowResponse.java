package com.movie.booking.request;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.movie.booking.utils.Shows;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShowResponse {

	private Integer id;
	
	private String theaterId;
	
	private String screenId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone="IST")
	private Date startTime;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone="IST")
	private Date endTime;
	
	private String movieName;

	private Shows showTiming;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date showDate;

	private Map<String,Boolean> seats;
	
	@JsonProperty(value="availableSeats")
	private List<String> availableSeats;
	
	private TheaterRequest theater;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(String theaterId) {
		this.theaterId = theaterId;
	}

	public String getScreenId() {
		return screenId;
	}

	public void setScreenId(String screenId) {
		this.screenId = screenId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Shows getShowTiming() {
		return showTiming;
	}

	public void setShowTiming(Shows showTiming) {
		this.showTiming = showTiming;
	}

	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}

	public Map<String, Boolean> getSeats() {
		
		return seats;
	}

	public void setSeats(Map<String, Boolean> seats) {
		this.seats = seats;
	}

	public List<String> getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(List<String> availableSeats) {
		this.availableSeats = availableSeats;
	}

	public TheaterRequest getTheater() {
		return theater;
	}

	public void setTheater(TheaterRequest theater) {
		this.theater = theater;
	}

}
