package com.movie.booking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movie.booking.request.ShowRequest;
import com.movie.booking.request.ShowResponse;
import com.movie.booking.service.ShowService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description="These endpoints handles all operations related show")
@RequestMapping("/{theaterId}/{screenId}/show")
public class ShowController {

	@Autowired
	private ShowService showService;
	
	@RequestMapping(value="/",method=RequestMethod.POST,produces={"application/json"})
	@ApiOperation(value="Add a show")
	public ShowResponse addShow(@PathVariable String theaterId,@PathVariable String screenId,@RequestBody ShowRequest showRequest){
		return showService.addShow(theaterId,screenId,showRequest);
	}
	
}
