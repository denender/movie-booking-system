package com.movie.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.booking.entities.Booking;
import com.movie.booking.entities.Screen;
import com.movie.booking.entities.Show;
import com.movie.booking.entities.Theater;
import com.movie.booking.repository.BookingRepository;
import com.movie.booking.repository.ScreenRepository;
import com.movie.booking.repository.ShowRepository;
import com.movie.booking.request.AvailableSeats;
import com.movie.booking.request.BookingRequest;
import com.movie.booking.request.BookingResponse;
import com.movie.booking.request.ScreenRequest;
import com.movie.booking.request.ShowResponse;
import com.movie.booking.request.TheaterRequest;
import com.movie.booking.utils.JSONUtils;

@Service
public class BookingService {

	@Autowired
	private ShowRepository showRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private ScreenRepository screenRepository;
	
	@Transactional
	public BookingResponse bookTicket(String movieName, BookingRequest bookingRequest) throws Exception {
		BookingResponse response= new BookingResponse();
		
		Booking booking = new Booking();
		List<Show> shows = showRepository.findByMovieNameIgnoreCaseAndShowDateAndShowTiming(movieName,bookingRequest.getShowDate(),bookingRequest.getShowTiming());
		
		if(shows.size()==0){
			throw new Exception("Show does not exists.");
		}
		
		Show show=shows.get(0);
		
		@SuppressWarnings({"unchecked" })
		Map<String,Boolean> seats = JSONUtils.toObject(show.getSeats(),Map.class);
		List<String> bookingSeats = bookingRequest.getSeats();
		
		for(String seatNo:bookingSeats){
			if(seats.get(seatNo)==null || seats.get(seatNo) ){
				throw new Exception("Seats not avaiable");
			}
			seats.put(seatNo, true);
		}
		show.setSeats(JSONUtils.toJson(seats));
		showRepository.save(show);
		booking.setShow(show);
		
		booking.setStatus("Confirmed");
		booking.setSeats(JSONUtils.toJson(bookingSeats));
		
		Booking booking2 = bookingRepository.save(booking);
		response.setBookingId(booking2.getId());
		response.setMessage("Your tickets has been confirmed");
		response.setMovieName(movieName);
		
		
		ShowResponse showResponse=new ShowResponse();
		showResponse.setStartTime(show.getStartTime());
		showResponse.setEndTime(show.getEndTime());
		showResponse.setShowTiming(show.getShowTiming());
		showResponse.setShowDate(show.getShowDate());
		response.setShow(showResponse);
		
		Screen screen = screenRepository.findOne(show.getScreenId());
		ScreenRequest screenRequest = new ScreenRequest();
		screenRequest.setName(screen.getName());
		response.setSeats(bookingRequest.getSeats());
		
		Theater theater = screen.getTheater();
		TheaterRequest theaterRequest= new TheaterRequest();
		theaterRequest.setName(theater.getName());
		theaterRequest.setLocation(theater.getLocation());
		theaterRequest.getScreens().add(screenRequest);
		response.setTheater(theaterRequest);
		
		return response;
	}

	@Transactional
	public BookingResponse cancelTicket(Long bookingId) throws Exception {
		BookingResponse response= new BookingResponse();
		Booking booking = bookingRepository.findOne(bookingId);
		
		if(booking == null){
			throw new Exception("No booking found with booking id "+bookingId);
		}
		
		if(booking.getStatus().equals("Canceled")){
			throw new Exception("Tickets have been already cancled with booking id "+bookingId);
		}
		
		Show show = showRepository.findOne(booking.getShow().getId());
		@SuppressWarnings({"unchecked" })
		Map<String,Boolean> seats = JSONUtils.toObject(show.getSeats(),Map.class);
		@SuppressWarnings({"unchecked" })
		List<String> bookedSeats = JSONUtils.toObject(booking.getSeats(),List.class);
		
		for(String seatNo:bookedSeats){
			seats.put(seatNo, false);
		}
		show.setSeats(JSONUtils.toJson(seats));
		showRepository.save(show);
		booking.setStatus("Canceled");
		bookingRepository.save(booking);
		
		response.setBookingId(bookingId);
		response.setMessage("Your tickets has been canceled.");
		
		return response;
	}

	public AvailableSeats checkAvailability(String movieName, String theaterId, String screenId) {
		AvailableSeats availableSeats=new AvailableSeats();
		availableSeats.setMovieName(movieName);
		List<ShowResponse> showResponses=new ArrayList<>();
		List<Show> shows=null;
		
		if(theaterId !=null && screenId != null){
			shows=showRepository.findByMovieNameAndTheaterIdAndScreenId(movieName, theaterId, screenId);
		}else if(theaterId!=null){
			shows=showRepository.findByMovieNameAndTheaterId(movieName, theaterId);
		} else {
			shows=showRepository.findByMovieNameIgnoreCase(movieName);
		}
		
		if(shows.size()>0){
			for(Show show:shows){
				ShowResponse showResponse=new ShowResponse();
				showResponse.setStartTime(show.getStartTime());
				showResponse.setEndTime(show.getEndTime());
				showResponse.setShowTiming(show.getShowTiming());
				showResponse.setShowDate(show.getShowDate());
				
				Screen screen = screenRepository.findOne(show.getScreenId());
				ScreenRequest screenRequest = new ScreenRequest();
				screenRequest.setName(screen.getName());
				
				Theater theater = screen.getTheater();
				TheaterRequest theaterRequest= new TheaterRequest();
				theaterRequest.setName(theater.getName());
				theaterRequest.setLocation(theater.getLocation());
				theaterRequest.getScreens().add(screenRequest);
				showResponse.setTheater(theaterRequest);
				
				@SuppressWarnings("unchecked")
				Map<String,Boolean> seats = JSONUtils.toObject(show.getSeats(),Map.class);
				List<String> availableSeatsList= new ArrayList<String>();
				for (Entry<String, Boolean> entry : seats.entrySet()){
					if(!entry.getValue()){
						availableSeatsList.add(entry.getKey());
					}
				}
				showResponse.setAvailableSeats(availableSeatsList);
				showResponses.add(showResponse);
			}
		}
		
		
		availableSeats.setShows(showResponses);
		return availableSeats;
	}
	
	

}
