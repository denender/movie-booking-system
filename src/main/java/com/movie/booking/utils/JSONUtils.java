package com.movie.booking.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils {

	private static ObjectMapper mapper = new ObjectMapper();
	
	public static  String toJson(Object object){
		try {
			return  mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static <T> T toObject(String json,Class<T> claaz){
		
		try {
			return mapper.readValue(json, claaz);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
}
