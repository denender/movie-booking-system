package com.movie.booking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.booking.entities.Movie;
import com.movie.booking.service.MovieService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description="These endpoints handles all operations related movies")
@RequestMapping("/movies")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@RequestMapping(method=RequestMethod.GET,produces={"application/json"})
	@ApiOperation(value="Get list all Movies.")
	public List<Movie> getAllMovies() {
		return movieService.getAllMovies();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET,produces={"application/json"})
	@ApiOperation(value="Get a movie based on id.")
	public Movie getMovie(@PathVariable String id){
		return movieService.getMovie(id);
	}
	
	@RequestMapping(value="/",method=RequestMethod.POST,produces={"application/json"})
	@ApiOperation(value="Add a Movie")
	public Movie addMovie(@RequestBody Movie movie){
		return movieService.addMovie(movie);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE,produces={"application/json"})
	@ApiOperation(value="Delete a movie based on id.")
	public Movie deleteMovie(@RequestParam String id){
		return movieService.deleteMovie(id);
	}

}
