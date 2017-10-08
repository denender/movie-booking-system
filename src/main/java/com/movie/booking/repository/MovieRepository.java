package com.movie.booking.repository;

import org.springframework.data.repository.CrudRepository;

import com.movie.booking.entities.Movie;


public interface MovieRepository extends CrudRepository<Movie,String> {

}
