package com.movie.booking.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.movie.booking.entities.Show;
import com.movie.booking.utils.Shows;

public interface ShowRepository extends CrudRepository<Show,Integer> {

	public List<Show> findByScreenId(String screenId);
	
	public List<Show> findByMovieNameIgnoreCaseAndShowDateAndShowTiming(String movieName,Date date,Shows showTiming);
	
	public List<Show> findByMovieNameIgnoreCase(String movieName);
	
	public List<Show> findByMovieNameIgnoreCaseAndTheaterId(String movieName,String theaterId);
	
	public List<Show> findByMovieNameIgnoreCaseAndTheaterIdAndShowDate(String movieName,String theaterId,Date showDate);

	public List<Show> findByMovieNameIgnoreCaseAndShowDate(String movieName, Date showDate);
}
