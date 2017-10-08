package com.movie.booking.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.booking.entities.Show;
import com.movie.booking.repository.ShowRepository;
import com.movie.booking.request.ShowRequest;
import com.movie.booking.request.ShowResponse;
import com.movie.booking.utils.APPContants;
import com.movie.booking.utils.JSONUtils;
import com.movie.booking.utils.Shows;

@Service
public class ShowService {

	@Autowired
	private ShowRepository showRepository;

	@SuppressWarnings("unchecked")
	@Transactional
	public ShowResponse addShow(String theaterId, String screenId, ShowRequest showRequest) {
		Show show = new Show();
		show.setMovieName(showRequest.getMovieName());
		show.setScreenId(screenId);
		show.setTheaterId(theaterId);
		show.setShowTiming(showRequest.getShowTiming());
		show.setShowDate(showRequest.getShowDate());
		Map<String, Date> timings = getShowTiming(showRequest.getShowTiming(), showRequest.getShowDate());
		show.setStartTime(timings.get("startTime"));
		show.setEndTime(timings.get("endTime"));
		show.setSeats(getSeats());
		Show show2 = showRepository.save(show);
		ShowResponse response=new ShowResponse();
		response.setId(show2.getId());
		response.setMovieName(show2.getMovieName());
		response.setTheaterId(show2.getTheaterId());
		response.setScreenId(show2.getScreenId());
		response.setStartTime(show2.getStartTime());
		response.setEndTime(show2.getEndTime());
		response.setShowTiming(show2.getShowTiming());
		response.setShowDate(show2.getShowDate());
		response.setSeats(JSONUtils.toObject(show2.getSeats(), Map.class));
		return response;
	}

	private Map<String, Date> getShowTiming(Shows show, Date showDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(showDate);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
		Map<String,Date> shows= new HashMap<String,Date>();
		try {
			if(show.equals(Shows.MORNING)){
				shows.put("startTime", sdf2.parse(date+" 03:30:00+0000"));
				shows.put("endTime", sdf2.parse(date+" 06:00:00+0000"));
			} else if(show.equals(Shows.AFTERNOON)){
				shows.put("startTime", sdf2.parse(date+" 6:30:00+0000"));
				shows.put("endTime", sdf2.parse(date+" 09:00:00+0000"));
			}else if(show.equals(Shows.EVENING)){
				shows.put("startTime", sdf2.parse(date+" 09:30:00+0000"));
				shows.put("endTime", sdf2.parse(date+" 12:00:00+0000"));
			}else if(show.equals(Shows.LATEEVENING)){
				shows.put("startTime", sdf2.parse(date+" 12:30:00+0000"));
				shows.put("endTime", sdf2.parse(date+" 15:00:00+0000"));
			}else if(show.equals(Shows.NIGHT)){
				shows.put("startTime", sdf2.parse(date+" 15:30:00+0000"));
				shows.put("endTime", sdf2.parse(date+" 17:00:00+0000"));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return shows;
	}
	
	private String getSeats(){
		Map<String,Boolean> seatsMap=new HashMap<String,Boolean>();
		
		for(int i=0;i<APPContants.ROWS;i++){
			for(int j=0;j<APPContants.COLOUMNS;j++){
				seatsMap.put(String.valueOf((char)(i + 65))+j, false);
			}
		}
		return JSONUtils.toJson(seatsMap);
	}
}
