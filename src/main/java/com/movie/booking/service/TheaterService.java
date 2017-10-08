package com.movie.booking.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.booking.entities.Screen;
import com.movie.booking.entities.Theater;
import com.movie.booking.repository.ScreenRepository;
import com.movie.booking.repository.TheaterRepository;
import com.movie.booking.request.ScreenRequest;
import com.movie.booking.request.TheaterRequest;

@Service
public class TheaterService {

	@Autowired
	private TheaterRepository theaterRepository;
	
	@Autowired
	private ScreenRepository screenRepository;

	public List<TheaterRequest> getAllTheaters() {
		List<TheaterRequest> theaters = new ArrayList<TheaterRequest>();
		Iterator<Theater> itr = theaterRepository.findAll().iterator();
		while(itr.hasNext()){
			theaters.add(responseMapper(itr.next()));
		}
		return theaters;
	}

	public TheaterRequest getTheater(String id) {
		
		return responseMapper(theaterRepository.findOne(id));
	}

	public TheaterRequest addTheater(TheaterRequest theaterRequest) {

		theaterRepository.save(mapperToTheater(theaterRequest));
		return theaterRequest;
	}

	private static Theater mapperToTheater(TheaterRequest theaterRequest){
		Theater theater = new Theater();
		theater.setId(theaterRequest.getId());
		theater.setName(theaterRequest.getName());
		theater.setLocation(theaterRequest.getLocation());
		
		for(ScreenRequest screenRequest:theaterRequest.getScreens()){
			Screen screen=new Screen();
			screen.setId(screenRequest.getId());
			screen.setName(screenRequest.getName());
			screen.setNoOfSeats(screenRequest.getSeats());
			screen.setNoOfShows(screenRequest.getNoOfShows());
			screen.setTheater(theater);
			theater.getScreens().add(screen);
		}
		
		return theater;

	}

	private static  TheaterRequest responseMapper(Theater theater){
		TheaterRequest theaterResponse = new TheaterRequest();
		theaterResponse.setId(theater.getId());
		theaterResponse.setLocation(theater.getLocation());
		theaterResponse.setName(theater.getName());
		
		for(Screen screen:theater.getScreens()){
			ScreenRequest screenRequest = new ScreenRequest();
			screenRequest.setId(screen.getId());
			screenRequest.setName(screen.getName());
			screenRequest.setNoOfShows(screen.getNoOfShows());
			screenRequest.setSeats(screen.getNoOfSeats());
			theaterResponse.getScreens().add(screenRequest);
		}
		
		return theaterResponse;
	}

	public ScreenRequest addScreen(String theaterId, ScreenRequest screenRequest) {
		Screen screen=new Screen();
		screen.setId(screenRequest.getId());
		screen.setName(screenRequest.getName());
		screen.setNoOfSeats(screenRequest.getSeats());
		screen.setNoOfShows(screenRequest.getNoOfShows());
		screen.setTheater(new Theater(theaterId));
		screenRepository.save(screen);
		return screenRequest;
	}
}
