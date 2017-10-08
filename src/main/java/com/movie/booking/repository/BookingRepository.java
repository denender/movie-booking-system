package com.movie.booking.repository;

import org.springframework.data.repository.CrudRepository;

import com.movie.booking.entities.Booking;

public interface BookingRepository extends CrudRepository<Booking,Long> {

}
