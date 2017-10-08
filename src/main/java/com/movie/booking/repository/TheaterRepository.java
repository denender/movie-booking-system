package com.movie.booking.repository;

import org.springframework.data.repository.CrudRepository;

import com.movie.booking.entities.Theater;

public interface TheaterRepository extends CrudRepository<Theater,String>{

}
