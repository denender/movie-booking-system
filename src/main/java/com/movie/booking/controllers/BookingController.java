package com.movie.booking.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.booking.request.AvailableSeats;
import com.movie.booking.request.BookingRequest;
import com.movie.booking.request.BookingResponse;
import com.movie.booking.service.BookingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(description="These endpoints handles all operations related to seats")
@RequestMapping("/seats")
public class BookingController {
	@Autowired
	private BookingService bookingService;

	@RequestMapping(value="/book/{movieName}",method=RequestMethod.POST,produces={"application/json"})
	@ApiOperation(value="Book a ticket")
	public BookingResponse bookTicket(@PathVariable @ApiParam(value="Movie name" ,defaultValue="Spyder") String movieName,@RequestBody BookingRequest bookingRequest) throws Exception{
		return bookingService.bookTicket(movieName,bookingRequest);
	}

	@RequestMapping(value="/cancel/{bookingId}",method=RequestMethod.PUT,produces={"application/json"})
	@ApiOperation(value="Cancel a ticket")
	public BookingResponse cancelTicket(@PathVariable Long bookingId) throws Exception{
		return bookingService.cancelTicket(bookingId);
	}


	@RequestMapping(value="/availability/{movieName}",method=RequestMethod.GET,produces={"application/json"})
	@ApiOperation(value="Check Availability of seats ")
	public AvailableSeats checkAvailability (@PathVariable @ApiParam(value="Movie name" ,defaultValue="Spyder") String movieName,@RequestParam(value="theaterId",required=false) String theaterId,
			@RequestParam(value="screenId",required=false) String screenId,@RequestParam(value="showDate",required=false) @DateTimeFormat(pattern="yyyy-MM-dd") @ApiParam(value="Show date" ,defaultValue="2017-10-13") Date showDate) throws Exception{
		return bookingService.checkAvailability(movieName,theaterId,screenId);

	}


}
