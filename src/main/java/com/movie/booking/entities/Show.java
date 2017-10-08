package com.movie.booking.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.movie.booking.utils.Shows;

@Entity(name="DailyShows")
public class Show {

	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	
	private String movieName;
	
	private String theaterId;
	
	private String screenId;
	
	private Date startTime;
	
	private Date endTime;
	
	private Date showDate;
	
	@Enumerated(EnumType.STRING)
	private Shows showTiming;
	
	@Column(length=10000)
	private String seats;

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


	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}


	public String getSeats() {
		return seats;
	}

	public void setSeats(String seats) {
		this.seats = seats;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
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
