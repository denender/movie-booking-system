package com.movie.booking.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.movie.booking.utils.Shows;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShowRequest {

	
	private String movieName;

	private Shows showTiming;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date showDate;

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
	
	
}
