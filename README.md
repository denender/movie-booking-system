# movie-booking-system

The solution the problem is designed for a theater having multiple screens.Each screen have multiple daily shows.

## Assumptions 
* There is only one theater with unique id T1 at location Kormangala.
* There are two screens with id S1 and S2.
* There are five daily shows MORNING', 'AFTERNOON', 'EVENING', 'LATEEVENING', 'NIGHT'.
* There are 225 seats in a screen with seat numbers starting from A0-A14,B0-B14...,O0-O14.
* On screen S1 Chef moving shows are running from 2017-10-13.
* On screen S2 Spyder movie shows are running from 2017-10-13.
* New theater,screen and daily shows for a movie can be added direcly to database or via api's which are not exposed right now.Those can be enabled by removes  comments @RestController.
* Validation for input fields applied is minimal.


## Software version and libraries 
* Java 8
* Spring boot
* HSQLDB
* Swagger 2 for documentation 
* Maven

## Building and running the solution  
* After cloning the project change  directory to **movie-booking-system**
* Run command **mvn clean package**
* Use command **java -jar target/movie-booking-system-0.0.1-SNAPSHOT.jar**
* Go to URL **http://localhost:8082/swagger-ui.html#/booking-controller** for accessing endpoints via browser.

## Checking availability of seats ##

For checking availability  of seats hit curl to url 

	curl -X GET --header 'Accept: application/json' 'http://localhost:8082/seats/availability/Spyder?theaterId=T1&showDate=2017-10-13'

This get call will give you a lists of available shows and empty seats.

## Booking a seat for a movie.

For booking a movie you need to pass movie name as URL parameter and a request JSON body having details of screen,
show date, show timings and a list of select seats.

Curl command:-

	curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{ \ 
	   "screenId": "S2", \ 
	   "seats": [ \ 
	     "A1","A2" \ 
	   ], \ 
	   "showDate": "2017-10-13", \ 
	   "showTiming": "MORNING" \ 
	 }' 'http://localhost:8082/seats/book/Spyder'




Request JSON body:

	{
	  "screenId": "S2",
	  "seats": [
	    "A1",
	    "A2"
	  ],
	  "showDate": "2017-10-13",
	  "showTiming": "MORNING"
	}

Response JSON:-

	{
	  "bookingId": 1,
	  "movieName": "Spyder",
	  "message": "Your tickets has been confirmed",
	  "show": {
	    "startTime": "2017-10-13 09:00:00",
	    "endTime": "2017-10-13 11:30:00",
	    "showTiming": "MORNING",
	    "showDate": "2017-10-13"
	  },
	  "theater": {
	    "name": "PVR-Forum",
	    "location": "Kormangla",
	    "screens": [
	      {
	        "name": "AUDI2"
	      }
	    ]
	  },
	  "seats": [
	    "A1",
	    "A2"
	  ]
	}

If seats are not available  it will give 400 HTTP status code with message.

## Canceling a booking

For canceling a booking you need to do a below curl call with booking Id as URL parameter.

Curl

	curl -X PUT --header 'Content-Type: application/json' --header 'Accept: application/json' 'http://localhost:8082/seats/cancel/1'

Request URL

	http://localhost:8082/seats/cancel/1

Response Body

	{
	  "bookingId": 1,
	  "message": "Your tickets has been canceled."
	} 

There are no validation applied on show timing for a canceling.
