package com.movie.booking.request;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.movie.booking.utils.Shows;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingRequest {

	private String screenId;
	
	private String theaterId;
	
	private Shows showTiming;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date showDate;
	
	private List<String> seats;
	
	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}

	

	public String getScreenId() {
		return screenId;
	}

	public void setScreenId(String screenId) {
		this.screenId = screenId;
	}

	public String getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(String theaterId) {
		this.theaterId = theaterId;
	}

	public Shows getShowTiming() {
		return showTiming;
	}

	public void setShowTiming(Shows showTiming) {
		this.showTiming = showTiming;
	}

	public List<String> getSeats() {
		return seats;
	}

	public void setSeats(List<String> seats) {
		this.seats = seats;
	}

}
