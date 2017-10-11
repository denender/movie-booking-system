package com.intuit.test.moviebookingsystem;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.booking.MovieBookingSystemApplication;
import com.movie.booking.request.BookingRequest;
import com.movie.booking.request.BookingResponse;
import com.movie.booking.utils.Shows;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MovieBookingSystemApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieBookingSystemApplicationTests {


	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void checkAvailabilityTest() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/seats/availability/Spyder?theaterId=T1&showDate=2017-10-13"),
				HttpMethod.GET, entity, String.class);
		assertTrue(response.getStatusCodeValue()==200);
	}

	@Test
	public void bookTicktes() throws Exception  {
		BookingRequest bookingRequest=new BookingRequest();
		bookingRequest.setScreenId("S2");
		bookingRequest.setShowTiming(Shows.MORNING);
		bookingRequest.setSeats(Arrays.asList("A1","A2"));

		DateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		bookingRequest.setShowDate(sdf.parse("2017-10-13"));


		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.valueOf("application/json"));
		HttpEntity<BookingRequest> entity = new HttpEntity<BookingRequest>(bookingRequest, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/seats/book/Spyder"),
				HttpMethod.POST, entity, String.class);
		assertTrue(response.getStatusCodeValue()==200);

	}

	@Test
	public void cancelTicktes() throws ParseException, JsonParseException, JsonMappingException, IOException  {
		BookingRequest bookingRequest=new BookingRequest();
		bookingRequest.setScreenId("S2");
		bookingRequest.setShowTiming(Shows.MORNING);
		bookingRequest.setSeats(Arrays.asList("A4","A5"));

		DateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		bookingRequest.setShowDate(sdf.parse("2017-10-13"));

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.valueOf("application/json"));
		HttpEntity<BookingRequest> entity = new HttpEntity<BookingRequest>(bookingRequest, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/seats/book/Spyder"),
				HttpMethod.POST, entity, String.class);

		assertTrue(response.getStatusCodeValue()==200);
		ObjectMapper mapper = new ObjectMapper();
		BookingResponse bookingResponse = mapper.readValue(response.getBody(), BookingResponse.class);
		
		HttpEntity<String> cacnelEntity = new HttpEntity<String>(null, null);
		ResponseEntity<String> cancelResponse = restTemplate.exchange(
				createURLWithPort("/seats/cancel/"+bookingResponse.getBookingId()),
				HttpMethod.PUT, cacnelEntity, String.class);
		
		assertTrue(cancelResponse.getStatusCodeValue()==200);
		assertTrue(cancelResponse.getBody().contains(""+bookingResponse.getBookingId()+""));
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
