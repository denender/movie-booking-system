package com.movie.booking.repository;

import org.springframework.data.repository.CrudRepository;

import com.movie.booking.entities.Screen;

public interface ScreenRepository extends CrudRepository<Screen,String> {

}
