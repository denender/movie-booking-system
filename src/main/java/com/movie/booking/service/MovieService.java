package com.movie.booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.booking.entities.Movie;
import com.movie.booking.repository.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	public List<Movie> getAllMovies() {
		List<Movie> movies = new ArrayList<Movie>();
		movieRepository.findAll().forEach(movies::add);
		return movies;
	}

	public Movie getMovie(String id) {
		return movieRepository.findOne(id);
	}

	public Movie addMovie(Movie movie) {
		movieRepository.save(movie);
		return movie;
	}

	public Movie deleteMovie(String id) {
		Movie movie = null;
		if(movieRepository.exists(id)){
			movie = movieRepository.findOne(id);
			movieRepository.delete(id);
		}
		
		return movie;
	}

}
