package com.movie.booking.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingResponse {

	private Long bookingId;
	
	private String movieName;
	
	private ShowResponse show;
	
	private TheaterRequest theater;
	
	private String message;
	
	private List<String> seats;

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public ShowResponse getShow() {
		return show;
	}

	public void setShow(ShowResponse show) {
		this.show = show;
	}

	public TheaterRequest getTheater() {
		return theater;
	}

	public void setTheater(TheaterRequest theater) {
		this.theater = theater;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getSeats() {
		return seats;
	}

	public void setSeats(List<String> seats) {
		this.seats = seats;
	}

	
}
