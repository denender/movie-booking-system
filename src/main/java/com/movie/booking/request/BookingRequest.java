package com.movie.booking.request;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.movie.booking.utils.Shows;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel
public class BookingRequest {

	@JsonProperty(required=true,index=0)
    @ApiModelProperty(value = "Scrren Id", required = true, example = "S2")
	private String screenId;
	
	@JsonProperty(required=true,index=1)
	private Shows showTiming;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "Show date", required = true, example = "2017-10-13")
	@JsonProperty(required=true,index=2)
	private Date showDate;
	
	@JsonProperty(required=true,index=3)
	@ApiModelProperty(value = "List of selected seats", required = true)
	private List<String> seats;

	public String getScreenId() {
		return screenId;
	}

	public void setScreenId(String screenId) {
		this.screenId = screenId;
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

	public List<String> getSeats() {
		return seats;
	}

	public void setSeats(List<String> seats) {
		this.seats = seats;
	}
	
	

}
