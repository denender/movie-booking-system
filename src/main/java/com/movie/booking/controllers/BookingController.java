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

import com.movie.booking.errors.Error;
import com.movie.booking.errors.InvalidRequestException;
import com.movie.booking.errors.NotFoundException;
import com.movie.booking.request.AvailableSeats;
import com.movie.booking.request.BookingRequest;
import com.movie.booking.request.BookingResponse;
import com.movie.booking.service.BookingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(description="These endpoints handles operations related to seat availability,booking a seat and canceling a booking.")
@RequestMapping("/seats")
public class BookingController {
	@Autowired
	private BookingService bookingService;

	@RequestMapping(value="/book/{movieName}",method=RequestMethod.POST,produces={"application/json"})
	@ApiOperation(value="Book a ticket")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request" ,response = Error.class),
			@ApiResponse(code = 404, message = "Bad Request" ,response = Error.class),
			@ApiResponse(code = 500, message = "Failure",response = Error.class)})
	public BookingResponse bookTicket(@PathVariable @ApiParam(value="Movie name" ,defaultValue="Spyder") String movieName,
			@RequestBody BookingRequest bookingRequest) throws NotFoundException, InvalidRequestException{
		return bookingService.bookTicket(movieName,bookingRequest);
	}

	@RequestMapping(value="/cancel/{bookingId}",method=RequestMethod.PUT,produces={"application/json"})
	@ApiOperation(value="Cancel a ticket")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 404, message = "Not found" ,response = Error.class),
			@ApiResponse(code = 500, message = "Failure",response = Error.class)})
	public BookingResponse cancelTicket(@PathVariable @ApiParam(value="Booking Id" ,defaultValue="1") Long bookingId) throws InvalidRequestException, NotFoundException{
		return bookingService.cancelTicket(bookingId);
	}


	@RequestMapping(value="/availability/{movieName}",method=RequestMethod.GET,produces={"application/json"})
	@ApiOperation(value="Check Availability of seats ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Bad Request" ,response = Error.class),
			@ApiResponse(code = 404, message = "Not found" ,response = Error.class),
			@ApiResponse(code = 500, message = "Failure",response = Error.class)})
	public AvailableSeats checkAvailability (@PathVariable @ApiParam(value="Movie name" ,defaultValue="Spyder") String movieName,@RequestParam(value="theaterId",required=false)@ApiParam(value="Theater ID" ,defaultValue="T1") String theaterId,
			@RequestParam(value="showDate",required=false) @ApiParam(value="Show Date" ,defaultValue="2017-10-13") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date showDate) throws Exception{
		return bookingService.checkAvailability(movieName,theaterId,showDate);

	}


}
