package com.movie.booking.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.movie.booking.entities.Show;
import com.movie.booking.utils.Shows;

public interface ShowRepository extends CrudRepository<Show,Integer> {

	public List<Show> findByScreenId(String screenId);
	
	public List<Show> findByShowDateAndShowTiming(Date date,Shows showTiming);
	
	public List<Show> findByMovieName(String movieName);
	
	public List<Show> findByMovieNameAndTheaterId(String movieName,String theaterId);
	
	public List<Show> findByMovieNameAndTheaterIdAndScreenId(String movieName,String theaterId,String screenId);
}
