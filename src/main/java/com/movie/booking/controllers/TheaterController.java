package com.movie.booking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.movie.booking.request.ScreenRequest;
import com.movie.booking.request.TheaterRequest;
import com.movie.booking.service.TheaterService;

import io.swagger.annotations.ApiOperation;

//@RestController
//@Api(description="These endpoints handles all operations related theater")
//@RequestMapping("/theater")
public class TheaterController {

	@Autowired
	private TheaterService theaterService;
	
	@RequestMapping(method=RequestMethod.GET,produces={"application/json"})
	@ApiOperation(value="Get list all theaters")
	public List<TheaterRequest> getAllTheaters() {
		return theaterService.getAllTheaters();
	}
	
	@RequestMapping(value="/{theaterId}",method=RequestMethod.GET,produces={"application/json"})
	@ApiOperation(value="Get a theater based on id.")
	public TheaterRequest getTheater(@PathVariable String theaterId){
		return theaterService.getTheater(theaterId);
	}
	
	@RequestMapping(value="/",method=RequestMethod.POST,produces={"application/json"})
	@ApiOperation(value="Add a theater")
	public TheaterRequest addTheater(@RequestBody TheaterRequest theaterRequest){
		return theaterService.addTheater(theaterRequest);
	}
	
	@RequestMapping(value="/{theaterId}/screen",method=RequestMethod.POST,produces={"application/json"})
	@ApiOperation(value="Add a screen")
	public ScreenRequest addScreen(@PathVariable String theaterId,@RequestBody ScreenRequest screenRequest){
		return theaterService.addScreen(theaterId,screenRequest);
	}
	
}
