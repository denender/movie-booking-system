package com.movie.booking.request;

import java.util.List;

public class AvailableSeats {

	private String movieName;
	
	private List<ShowResponse> shows;

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public List<ShowResponse> getShows() {
		return shows;
	}

	public void setShows(List<ShowResponse> shows) {
		this.shows = shows;
	}
	
}
